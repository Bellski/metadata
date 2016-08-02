package org.vaadin.rise.place;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.place.api.*;
import org.vaadin.rise.place.deprecated.PlaceRequest;
import org.vaadin.rise.place.deprecated.token.TokenFormatException;
import org.vaadin.rise.place.deprecated.token.UrlUtils;
import org.vaadin.rise.place.reveal.RevealException;
import org.vaadin.rise.place.reveal.Supplier;
import org.vaadin.rise.security.Gatekeeper;
import org.vaadin.rise.error.ErrorManager;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by Aleksandr on 25.07.2016.
 */
public class BasePlaceManager implements PlaceManager, UriFragmentChangeListener {
    private final PlaceDirectory placeDirectory;
    private final PlaceBus placeBus;
    private final UriFragmentSource uriFragmentSource;
    private final Gatekeeper gatekeeper;
    private final Set<String> nameTokens;
    private List<PlaceRequest> placeHierarchy = new ArrayList<>();

    private static final Logger logger = Logger.getLogger("BasePlaceManager");

    private String currentHistoryToken;

    private ErrorManager errorManager;

    public BasePlaceManager(Map<String, Place> placeMap, Set<String> nameTokens, UriFragmentSource uriFragmentSource, ErrorManager errorManager) {
       this(placeMap, nameTokens, null, uriFragmentSource, null, errorManager);
    }

    public BasePlaceManager(Map<String, Place> placeMap, Set<String> nameTokens, UriFragmentSource uriFragmentSource, Gatekeeper gatekeeper, ErrorManager errorManager) {
        this(placeMap, nameTokens, null, uriFragmentSource, gatekeeper, errorManager);
    }

    public BasePlaceManager(Map<String, Place> placeMap, Set<String> nameTokens, PlaceBus placeBus, UriFragmentSource uriFragmentSource, ErrorManager errorManager) {
        this(placeMap, nameTokens, placeBus, uriFragmentSource, null, errorManager);
    }

    public BasePlaceManager(Map<String, Place> placeMap,
                            Set<String> nameTokens,
                            PlaceBus placeBus,
                            UriFragmentSource uriFragmentSource,
                            Gatekeeper gatekeeper,
                            ErrorManager errorManager) {
        this.nameTokens = nameTokens;
        this.placeDirectory = new PlaceDirectory(placeMap, nameTokens, placeBus);
        this.placeBus = placeBus;
        this.uriFragmentSource = uriFragmentSource;
        this.gatekeeper = gatekeeper;
        this.errorManager = errorManager;
        this.uriFragmentSource.setUriFragmentChangeListener(this);

        errorManager.setPlaceManager(this);
    }

    @Override
    public void onUriFragmentChanged(String fragmentUri) {
        handleTokenChange(fragmentUri);
    }

    private void handleTokenChange(final String fragmentUri) {
        if (fragmentUri.trim().isEmpty()) {
            revealDefaultPlace();
            return;
        }

        final Place place = placeDirectory.getPlace(fragmentUri);

        if (place != null) {
            placeHierarchy = toPlaceRequestHierarchy(fragmentUri, place);
            doRevealPlace(getCurrentPlaceRequest(), place, true);
        } else {
            errorManager.onPlaceNotFound(fragmentUri);
        }
    }


    private boolean canRevealPlace(Place place) {
        String fallBackNamePlace = null;

        if (gatekeeper != null) {
            if (gatekeeper.canReveal() && !place.canReveal()) {
                fallBackNamePlace = place.getFallBackNamePlace();
            } else if (!gatekeeper.canReveal()) {
                fallBackNamePlace = gatekeeper.fallBackNamePlace();
            }
        } else if (!place.canReveal()) {
            fallBackNamePlace = place.getFallBackNamePlace();
        }


        if (fallBackNamePlace != null && !fallBackNamePlace.equals(place.getNameToken())) {
            revealPlace(
                toPlaceRequest(fallBackNamePlace, placeDirectory.getPlace(fallBackNamePlace)),
                false
            );

            return false;
        }

        return true;
    }

    @Override
    public PlaceRequest getCurrentPlaceRequest() {
        if (placeHierarchy.size() > 0) {
            return placeHierarchy.get(placeHierarchy.size() - 1);
        } else {
            return new PlaceRequest.Builder().build();
        }
    }

    protected void doRevealPlace(PlaceRequest request, Place place, boolean updateBrowser) {
        if (canRevealPlace(place)) {
            PlaceRequest originalRequest = getCurrentPlaceRequest();

            place.reveal(request, new Supplier() {

                @Override
                public void onFailure(RevealException e) {
                    errorManager.onErrorOccurrence(request, e);
                }

                @Override
                public void onFailure() {
                    errorManager.onErrorOccurrence();
                }

                @Override
                public void onSuccess(RisePresenterImpl<?> presenter) {
                    if (originalRequest == getCurrentPlaceRequest()) {
                        updateHistory(request, updateBrowser);
                    }
                    manualReveal(presenter);
                }

            });

        }
    }

    private void manualReveal(RisePresenterImpl<?> thatPresenter) {
        if (!thatPresenter.isVisible()) {
            thatPresenter.forceReveal();
        }
    }

    @Override
    public void revealCurrentPlace() {
        handleTokenChange(uriFragmentSource.getUriFragment());
    }

    @Override
    public void revealPlace(PlaceRequest request) {
        revealPlace(request, true);
    }


    @Override
    public void revealPlace(PlaceRequest request, boolean updateBrowserUrl) {
        placeHierarchy.clear();
        placeHierarchy.add(request);

        doRevealPlace(request, placeDirectory.getPlace(request), updateBrowserUrl);
    }

    @Override
    public void revealUnauthorizedPlace(String unauthorizedHistoryToken) {
        errorManager.onErrorOccurrence();
    }

    @Override
    public void updateHistory(PlaceRequest request, boolean updateBrowserUrl) {
        try {
            // Make sure the request match
            assert request.hasSameNameToken(getCurrentPlaceRequest()) : "Internal error, PlaceRequest passed to" +
                    "updateHistory doesn't match the tail of the place hierarchy.";
            placeHierarchy.set(placeHierarchy.size() - 1, request);
            if (updateBrowserUrl) {
                String historyToken = toHistoryToken(placeHierarchy);
                String browserHistoryToken = uriFragmentSource.getUriFragment();
                if (browserHistoryToken == null
                        || !browserHistoryToken.equals(historyToken)) {
                    setBrowserHistoryToken(historyToken, false);
                }
                saveHistoryToken(historyToken);
            }
        } catch (TokenFormatException e) {
            // Do nothing.
        }
    }

    private void saveHistoryToken(String historyToken) {
        currentHistoryToken = historyToken;
    }

    private void setBrowserHistoryToken(String historyToken, boolean update) {
        uriFragmentSource.setUriFragment(historyToken, update);
    }

    private String toHistoryToken(List<PlaceRequest> placeHierarchy) {
        assert placeHierarchy.size() == 1 : "Expected a place hierarchy with exactly one place.";

        return toPlaceToken(placeHierarchy.get(0));
    }

    @Override
    public String toPlaceToken(PlaceRequest placeRequest) throws TokenFormatException {
        String placeToken = placeRequest.getNameToken();
        StringBuilder queryStringBuilder = new StringBuilder();
        String querySeparator = "";

        for (String parameterName : placeRequest.getParameterNames()) {
            String parameterValue = placeRequest.getParameter(parameterName, null);
            if (parameterValue != null) {
                String encodedParameterValue = UrlUtils.encodeQueryString(parameterValue);

                if (placeToken.contains("/{" + parameterName + "}")) {
                    // route parameter
                    placeToken = placeToken.replace("{" + parameterName + "}", encodedParameterValue);
                } else {
                    // query parameter
                    queryStringBuilder.append(querySeparator).append(parameterName).append("=")
                            .append(encodedParameterValue);
                    querySeparator = "&";
                }
            }
        }

        String queryString = queryStringBuilder.toString();
        if (!queryString.isEmpty()) {
            placeToken = placeToken + "?" + queryString;
        }

        return placeToken;
    }

    @Override
    public void revealDefaultPlace() {

    }


    private List<PlaceRequest> toPlaceRequestHierarchy(String historyToken, Place place) {
        List<PlaceRequest> result = new ArrayList<>(); //TODO: надо будет потом упрозднить.
        result.add(toPlaceRequest(historyToken, place));

        return result;
    }

    public PlaceRequest toPlaceRequest(String uriFragment, Place place) {
        int split = uriFragment.indexOf('?');

        String query = (split != -1) ? uriFragment.substring(split + 1) : ""; //TODO: добавить поддержку параметров через вопросик

        final String[] placeParts = uriFragment.split("/");

        Map<String, String> params = null;

        if (place.hasParameters()) {
            params = new HashMap<>(3);

            for (int i = 0; i < place.getParamIndexes().length; i++) {
                params.put(place.getParamNames()[i], placeParts[place.getParamIndexes()[i]]);
            }
        }

        return new PlaceRequest
                .Builder()
                .nameToken(place.toString())
                .with(params)
                .build();
    }


}
