package org.vaadin.rise.place.token;

import org.vaadin.rise.place.PlaceRequest;

import javax.inject.Singleton;
import java.util.*;

@Singleton
public class RouteTokenFormatter implements TokenFormatter {

    private static class RouteMatch implements Comparable<RouteMatch> {

        final String route;

        final int staticMatches;

        Map<String, String> parameters;


        RouteMatch(String route,
                   int staticMatches,
                   Map<String, String> parameters) {
            this.route = route;
            this.staticMatches = staticMatches;
            this.parameters = parameters;
        }


        @Override
        public int compareTo(RouteMatch other) {
            return Integer.valueOf(staticMatches).compareTo(other.staticMatches);
        }
    }

    private class RouteMatcher {

        final TreeSet<RouteMatch> allMatches;

        final String[] placeParts;


        RouteMatcher(String placeToken) {
            assert placeTokenIsValid(placeToken) : "Place-token should start with a '/' or '!/'";
            assert placeToken.indexOf('?') == -1 : "No Query string expected here";

            this.allMatches = new TreeSet<>();
            this.placeParts = placeToken.split("/");

            for (String route : allRegisteredPlaceTokens.getAllPlaceTokens()) {
                RouteMatch match = matchRoute(route);
                if (match != null) {
                    allMatches.add(match);
                }
            }
        }

        RouteMatch matchRoute(String route) {
            String[] routeParts = route.split("/");

            if (placeParts.length != routeParts.length) {
                return null;
            }

            if (placeParts.length == 0) {
                assert routeIsEmpty(route);
                return new RouteMatch(route, 0, null);
            }

            Map<String, String> recordedParameters = new HashMap<>();
            int staticMatches = 0;
            for (int i = 0; i < placeParts.length; i++) {
                if (placeParts[i].equals(routeParts[i])) {
                    staticMatches++;
                } else if (routeParts[i].matches("\\{.*\\}")) {
                    String parameterName = routeParts[i].substring(1, routeParts[i].length() - 1);
                    recordedParameters.put(parameterName, placeParts[i]);
                } else {
                    return null;
                }
            }

            return new RouteMatch(route, staticMatches, recordedParameters);
        }

        private boolean routeIsEmpty(String route) {
            return "/".equals(route) || "!/".equals(route);
        }
    }

    private final PlaceTokenRegistry allRegisteredPlaceTokens;



    public RouteTokenFormatter(PlaceTokenRegistry tokenRegistry) {
        this.allRegisteredPlaceTokens = tokenRegistry;
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
    public String toHistoryToken(List<PlaceRequest> placeRequestHierarchy) throws TokenFormatException {
        assert placeRequestHierarchy.size() == 1 : "Expected a place hierarchy with exactly one place.";

        return toPlaceToken(placeRequestHierarchy.get(0));
    }

    @Override
    public PlaceRequest toPlaceRequest(String placeToken) throws TokenFormatException {

        int split = placeToken.indexOf('?');
        String place = (split != -1) ? placeToken.substring(0, split) : placeToken;
        String query = (split != -1) ? placeToken.substring(split + 1) : "";

        RouteMatcher matcher = new RouteMatcher(place);
        RouteMatch match = (!matcher.allMatches.isEmpty()) ? matcher.allMatches.last() : new RouteMatch(place, 0, null);

        match.parameters = decodeEmbeddedParams(match.parameters);
        match.parameters = parseQueryString(query, match.parameters);

        return new PlaceRequest.Builder().nameToken(match.route).with(match.parameters).build();
    }

    private Map<String, String> decodeEmbeddedParams(Map<String, String> parameters) {
        if (parameters != null) {
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                entry.setValue(UrlUtils.decodeQueryString(entry.getValue()));
            }
        }
        return parameters;
    }

    @Override
    public List<PlaceRequest> toPlaceRequestHierarchy(String historyToken) throws TokenFormatException {
        List<PlaceRequest> result = new ArrayList<>();
        result.add(toPlaceRequest(historyToken));

        return result;
    }

    Map<String, String> parseQueryString(String queryString, Map<String, String> into) {
        Map<String, String> result = (into != null) ? into : new HashMap<>();

        if (queryString != null && !queryString.isEmpty()) {
            for (String keyValuePair : queryString.split("&")) {
                String[] keyValue = keyValuePair.split("=", 2);
                if (keyValue.length > 1) {
                    result.put(keyValue[0], UrlUtils.decodeQueryString(keyValue[1]));
                } else {
                    result.put(keyValue[0], "");
                }
            }
        }

        return result;
    }

    private boolean placeTokenIsValid(String placeToken) {
        return placeToken.startsWith("/") || placeToken.startsWith("!/");
    }
}