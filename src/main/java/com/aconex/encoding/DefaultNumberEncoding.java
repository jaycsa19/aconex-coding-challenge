package com.aconex.encoding;

import java.util.HashMap;
import java.util.Map;

/**
 * DefaultNumberEncoding class represents encoding for the given input problem.
 */
public class DefaultNumberEncoding implements NumberEncoding {

    private final Map<Character, Integer> letterToNumberEncoding;

    public DefaultNumberEncoding() {
        this.letterToNumberEncoding = new HashMap<Character, Integer>();
        addEncoding(2, new char[]{'a', 'b', 'c'});
        addEncoding(3, new char[]{'d', 'e', 'f'});
        addEncoding(4, new char[]{'g', 'h', 'i'});
        addEncoding(5, new char[]{'j', 'k', 'l'});
        addEncoding(6, new char[]{'m', 'n', 'o'});
        addEncoding(7, new char[]{'p', 'q', 'r', 's'});
        addEncoding(8, new char[]{'t', 'u', 'v'});
        addEncoding(9, new char[]{'w', 'x', 'y', 'z'});
    }

    public Integer getLetterToNumberEncoding(char letter) {
        if (letterToNumberEncoding.containsKey(letter)) {
            return letterToNumberEncoding.get(letter);
        }
        return null;
    }

    private void addEncoding(int number, char[] letters) {
        for (char letter : letters) {
            letterToNumberEncoding.put(letter, number);
        }
    }
}
