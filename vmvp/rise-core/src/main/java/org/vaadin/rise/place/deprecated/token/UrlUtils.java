package org.vaadin.rise.place.deprecated.token;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Aleksandr on 09.07.2016.
 */
public class UrlUtils {


    public static String decodePathSegment(final String encodedPathSegment) {
        return decode(encodedPathSegment);
    }


    public static String decodeQueryString(final String encodedUrlComponent) {
        return decode(encodedUrlComponent);
    }


    public static String decodeMatrixParameter(String encodedMatrixParameter) {
        return decode(encodedMatrixParameter);
    }


    public static String encodePathSegment(final String decodedPathSegment) {
        return encode(decodedPathSegment);
    }


    public static String encodeQueryString(final String decodedUrlComponent) {
        return encode(decodedUrlComponent);
    }


    public static String encodeMatrixParameter(String decodedMatrixParameter) {
        return encodePathSegment(decodedMatrixParameter);
    }

    private static String decode(String encodedPathSegment) {
        try {
            return URLDecoder.decode(encodedPathSegment, "UTF-8");
        } catch (final UnsupportedEncodingException e) {
            // THIS won't happen
            return null;
        }
    }

    private static String encode(String decodedPathSegment) {
        try {
            return URLEncoder.encode(decodedPathSegment, "UTF-8");
        } catch (final UnsupportedEncodingException e) {
            // THIS won't happen
            return null;
        }
    }
}
