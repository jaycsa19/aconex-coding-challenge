package com.aconex.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Utils class provides functions like removing punctuation, whitespace from string,
 * combining multiple lists of strings with '-'.
 */
public final class Utils {
    /**
     * Returns null if input string is null.
     * Removes all punctuation and whitespace from string.
     * If the resultant string is empty, returns null.
     *
     * @param word - String containing the word
     * @return String after removing all the punctuation and whitespace
     */
    public static String removePunctuation(String word) {
        if (word != null) {
            word = word.replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim();
            return (word.isEmpty()) ? null : word;
        }
        return null;
    }

    /**
     * Returns null if input string is null.
     * Removes all punctuation (except separator '.') and whitespace from string.
     * If the resultant string is empty, returns null.
     *
     * @param phone - String containing the phone number
     * @return String after removing all the punctuation and whitespace
     */
    public static String removeUnwantedCharsFromPhone(String phone) {
        if (phone != null) {
            phone = phone.replaceAll("[^0-9.]", "").toLowerCase().trim();
            return (phone.isEmpty()) ? null : phone;
        }
        return null;
    }

    /**
     * Returns null if input list is null or empty.
     * Returns the same set of strings if there is only one list.
     * Combines the strings of lists with '-', eg, input:[[a,b], [c,d]], output: [a-c,a-d,b-c,b-d]
     *
     * @param input - List of list of strings to be combined
     * @return Set of strings after combining with '-'
     */
    public static Set<String> combinePhrases(List<List<String>> input) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        if (input.size() == 1) {
            Set<String> result = new HashSet<String>();
            result.addAll(input.get(0));
            return result;
        }
        return combine(input, new String[input.size()], 0, new HashSet<String>());
    }

    private static Set<String> combine(List<List<String>> input, String[] current, int index, Set<String> result) {
        if (index == input.size()) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < index - 1; i++) {
                builder.append(current[i] + "-");
            }
            builder.append(current[index - 1]);
            result.add(builder.toString());

        } else {
            for (int j = 0; j < input.get(index).size(); j++) {
                current[index] = input.get(index).get(j);
                combine(input, current, index + 1, result);
            }
        }
        return result;
    }
}
