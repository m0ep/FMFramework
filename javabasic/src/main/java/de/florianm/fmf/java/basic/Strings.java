package de.florianm.fmf.java.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Utility collection of string related operations.
 */
public class Strings {

    /**
     * Check if a string is null or empty.
     *
     * @param value
     *     The string to check.
     *
     * @return true if value is null or empty.
     */
    public static boolean isNullOrEmpty(String value) {
        return null == value || value.isEmpty();
    }

    /**
     * Check if a string is null or the trimmed string is empty..
     *
     * @param value
     *     The string to check.
     *
     * @return true if value is null or the trimmed string is empty.
     */
    public static boolean isNullOrTrimmedEmpty(String value) {
        return null == value || value.trim().isEmpty();
    }

    /**
     * Check if a string is not null or empty.
     *
     * @param value
     *     The string to check.
     *
     * @return true if value is not null or empty.
     */
    public static boolean isNotNullOrEmpty(String value) {
        return !isNullOrEmpty(value);
    }

    /**
     * Converts a string that is null to an empty string.
     *
     * @param value
     *     The string to convert.
     *
     * @return An empty string if value was null, otherwise the provided value.
     */
    public static String nullToEmpty(String value) {
        return null == value ? "" : value;
    }


    /**
     * Splits a string with {@link String#split(String)} and removes all
     * null and empty elements.
     *
     * @param value
     *     The string to split
     * @param regex
     *     The regular expression where to split
     *
     * @return The string array with all non empty Strings.
     */
    public static String[] splitWithNonEmpty(String value,
                                             String regex) {
        if (isNullOrEmpty(value)) {
            return new String[0];
        }

        String[] split = value.split(regex);
        List<String> result = new ArrayList<>();
        for (String element : split) {
            if (isNotNullOrEmpty(element)) {
                result.add(element.trim());
            }
        }

        return result.toArray(new String[result.size()]);
    }

    /**
     * Returns a string containing the elements joined by delimiters.
     *
     * @param delimiter
     *     The delimiter.
     * @param elements
     *     The collection of elements.
     * @param <T>
     *     The type of the elements to join.
     *
     * @return The joined string.
     */
    public <T> String join(String delimiter, Collection<T> elements) {
        if (null == elements || elements.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        Iterator<T> iter = elements.iterator();
        while (iter.hasNext()) {
            T element = iter.next();
            sb.append(String.valueOf(element));

            if (iter.hasNext()) {
                sb.append(delimiter);
            }
        }

        return sb.toString();
    }
}
