package org.vaadin.rise.place;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.place.api.*;
import org.vaadin.rise.place.deprecated.PlaceRequest;
import org.vaadin.rise.place.deprecated.token.TokenFormatException;
import org.vaadin.rise.place.deprecated.token.UrlUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Aleksandr on 25.07.2016.
 */
public class BasePlaceManager implements PlaceManager, UriFragmentChangeListener {
    private final Map<Place, Place> placeMap;
    private final Map<String, String> nameTokenMap;
    private final PlaceBus placeBus;
    private final UriFragmentSource uriFragmentSource;
    private List<PlaceRequest> placeHierarchy = new ArrayList<>();

    private String currentHistoryToken;

    public BasePlaceManager(Map<Place, Place> placeMap, Map<String, String> nameTokenMap, UriFragmentSource uriFragmentSource) {
       this(placeMap, nameTokenMap, null, uriFragmentSource);
    }

    public BasePlaceManager(Map<Place, Place> placeMap, Map<String, String> nameTokenMap, PlaceBus placeBus, UriFragmentSource uriFragmentSource) {
        this.placeMap = placeMap;
        this.nameTokenMap = nameTokenMap;
        this.placeBus = placeBus;
        this.uriFragmentSource = uriFragmentSource;
        this.uriFragmentSource.setUriFragmentChangeListener(this);
    }

    @Override
    public void onUriFragmentChanged(String fragmentUri) {
        Place place = placeMap.get(new CompareToPlace(fragmentUri, nameTokenMap));

        if (place == null && placeBus != null) {
            place = placeBus.getPlace(fragmentUri);
        }

        if (place != null) {
            handleTokenChange(fragmentUri, place);
        } else {
            revealErrorPlace(fragmentUri);
        }
    }

    private void handleTokenChange(final String historyToken, Place place) {
        try {
            if (historyToken.trim().isEmpty()) {
                revealDefaultPlace();
            } else {
                placeHierarchy = toPlaceRequestHierarchy(historyToken, place);
                doRevealPlace(getCurrentPlaceRequest(), place, true);
            }
        } catch (TokenFormatException ignored) {
            //TODO: later
        }
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
        final RisePresenterImpl<?> thatPresenter = place.reveal();

        PlaceRequest originalRequest = getCurrentPlaceRequest();
        thatPresenter.prepareFromRequest(request);
        if (originalRequest == getCurrentPlaceRequest()) {
            updateHistory(request, updateBrowser);
        }

        if (!thatPresenter.useManualReveal()) {
            // Automatic reveal
            manualReveal(thatPresenter);
        }
    }

    private void manualReveal(RisePresenterImpl<?> thatPresenter) {
        if (!thatPresenter.isVisible()) {
            thatPresenter.forceReveal();
        }
    }

    @Override
    public void revealCurrentPlace() {
        onUriFragmentChanged(uriFragmentSource.getUriFragment());
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

    @Override
    public void revealErrorPlace(String invalidHistoryToken) {

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
