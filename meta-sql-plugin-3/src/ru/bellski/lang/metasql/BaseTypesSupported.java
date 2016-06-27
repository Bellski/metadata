package ru.bellski.lang.metasql;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by oem on 6/27/16.
 */
public class BaseTypesSupported {
    private static Set<String> types = new HashSet<>();

    public final static String STRING = "String";
    public final static String BOOLEAN = "Boolean";
    public final static String DATE = "Date";
    public final static String LONG = "Long";
    public final static String ARRAY_LIST = "ArrayList";

    static {
        types.add(STRING);
        types.add(BOOLEAN);
        types.add(DATE);
        types.add(LONG);
        types.add(ARRAY_LIST);
    }

    public static boolean supported(String type) {
        return types.contains(type);
    }
}
