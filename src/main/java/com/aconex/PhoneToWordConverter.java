package com.aconex;

import com.aconex.util.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a loaded dictionary, finds all possible matched phrases/words for a given phone number.
 */
public class PhoneToWordConverter {

    private Dictionary dictionary;

    public PhoneToWordConverter(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    /**
     * Removes all punctuation(except '.') and whitespace from the phone number string.
     * If the resultant string is empty, its an invalid number, returns null.
     * Checks if the string has '.', If so, splits the word on '.', and for each string, finds matched words,
     * and combines them using '-'.
     * If any of these split strings(except the last one) id empty, its an invalid number, returns null. (eg, 1..3, .1)
     * If no match is found for any of these stings, returns null.
     *
     * @param phoneNumber - string containing number for which dictionary words are to be found, may contain '.'
     * @return Set of all possible matches. null, otherwise.
     */
    public Set<String> getMatchedWords(String phoneNumber) {
        String phone = Utils.removeUnwantedCharsFromPhone(phoneNumber);
        if (phone == null) {
            System.out.println("Invalid number: " + phoneNumber);
            return null;
        }
        List<List<String>> result = new ArrayList<List<String>>();
        if (phone.contains(".")) {
            String[] numbers = phone.split("\\.");
            for (String number : numbers) {
                if (!number.isEmpty()) {
                    Set<String> matches = getPossiblePhrases(number);
                    if (matches == null) {
                        return null;
                    }
                    result.add(new ArrayList<String>(matches));
                } else {
                    System.out.println("Invalid number: " + phoneNumber);
                    return null;
                }
            }
            return Utils.combinePhrases(result);
        } else {
            return getPossiblePhrases(phone);
        }

    }

    /**
     * Finds dictionary words for the given number. If there are matched words, returns them.
     * If no matches, and number is of one digit, then returns the digit.
     * Otherwise finds skip digit results. All possible matches by keeping any one of the digits as it is.
     *
     * @param number - string containing number for which dictionary words are to be found
     * @return Set of all possible matches. null, if no match is found.
     */
    private Set<String> getPossiblePhrases(String number) {
        Set<String> words = dictionary.getMatchedWords(number);
        if (words != null) {
            return words;
        } else {
            if (number.length() == 1) {
                Set<String> digit = new HashSet<String>();
                digit.add(number);
                return digit;
            }
            return getSkipDigitResults(number);
        }
    }

    private Set<String> getSkipDigitResults(String word) {
        Set<String> results = new HashSet<String>();
        for (int i = 0; i < word.length(); i++) {
            List<List<String>> matches;
            if (i == 0) {
                matches = getSkipDigitMatches(null, String.valueOf(word.charAt(0)), word.substring(1));
            } else if (i == word.length() - 1) {
                matches = getSkipDigitMatches(word.substring(0, word.length() - 1), String.valueOf(word.charAt(word.length() - 1)), null);
            } else {
                matches = getSkipDigitMatches(word.substring(0, i), String.valueOf(word.charAt(i)), word.substring(i + 1));
            }
            if (matches != null) {
                results.addAll(Utils.combinePhrases(matches));
            }
        }
        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    private List<List<String>> getSkipDigitMatches(String firstPart, String digit, String secondPart) {
        List<List<String>> result = new ArrayList<List<String>>();
        Set<String> firstSet = new HashSet<String>();
        Set<String> secondSet = new HashSet<String>();
        if (firstPart != null) {
            firstSet = dictionary.getMatchedWords(firstPart);
            if (firstSet == null) {
                return null;
            }
        }
        if (secondPart != null) {
            secondSet = dictionary.getMatchedWords(secondPart);
            if (secondSet == null) {
                return null;
            }
        }
        List<String> digitList = new ArrayList<String>();
        digitList.add(digit);
        if (firstPart != null) {
            result.add(new ArrayList<String>(firstSet));
        }
        result.add(digitList);
        if (secondPart != null) {
            result.add(new ArrayList<String>(secondSet));
        }
        return result;
    }


}
