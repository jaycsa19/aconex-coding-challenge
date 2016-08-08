package com.aconex;

import com.aconex.encoding.NumberEncoding;
import com.aconex.util.Utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a set of words, and letter to number encoding, it loads the inverted index (number to words mapping) of words,
 * and uses this index to provide a set of dictionary words for any number.
 */
public class Dictionary {
    private Set<String> words;
    private NumberEncoding numberEncoding;
    private Map<String, Set<String>> invertedIndex;

    public Dictionary(Set<String> words, NumberEncoding numberEncoding) {
        this.words = words;
        this.numberEncoding = numberEncoding;
        this.invertedIndex = new HashMap<String, Set<String>>();
        loadInvertedIndexOfEncodedWords();
    }

    /**
     * @param number - string containing number for which dictionary words are to be found
     * @return Set of words of there exists any matched word. null, otherwise.
     */

    public Set<String> getMatchedWords(String number) {
        return invertedIndex.get(number);
    }

    /**
     * Removes punctuation and whitespace from each word of the dictionary, finds number encoding of the word,
     * puts the number to word mapping in invertedIndex. If no mapping exists for a word, it is ignored.
     */
    private void loadInvertedIndexOfEncodedWords() {
        for (String word : words) {
            word = Utils.removePunctuation(word);
            if (word != null) {
                String number = getNumberEncodingOfWord(word);
                if (number != null) {
                    Set<String> values = new HashSet<String>();
                    if (invertedIndex.containsKey(number)) {
                        values = invertedIndex.get(number);
                        values.add(word);
                    } else {
                        values.add(word);
                        invertedIndex.put(number, values);
                    }
                }
            }
        }
    }


    private String getNumberEncodingOfWord(String word) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char letter : word.toCharArray()) {
            Integer number = numberEncoding.getLetterToNumberEncoding(letter);
            if (number == null) {
                return null;
            }
            stringBuilder.append(number);
        }
        return stringBuilder.toString();
    }
}
