package com.aconex;

import com.aconex.encoding.DefaultNumberEncoding;
import com.aconex.loader.ArgumentParser;
import com.aconex.loader.DataLoader;

import java.util.Set;

/**
 * Class which triggers the entire flow - parsing arguments, loading data, and converting the phone numbers to words,
 * displaying the results
 */
public class Orchestrator {

    public static void main(String[] args) throws Exception {
        ArgumentParser arguments = new ArgumentParser(args);
        DataLoader dataLoader = new DataLoader();
        Set<String> words = dataLoader.loadDictionaryFile(arguments.dictionaryInputStream);
        Set<String> phoneNumbers = dataLoader.loadPhoneNumbers(arguments.phoneNumberInputStream);
        Dictionary dictionary = new Dictionary(words, new DefaultNumberEncoding());
        PhoneToWordConverter converter = new PhoneToWordConverter(dictionary);
        for (String phone : phoneNumbers) {
            displayResults(phone, converter.getMatchedWords(phone));
        }
    }

    public static void displayResults(String phone, Set<String> matches) {
        if (matches == null) {
            System.out.println("No matches found for: " + phone);
        } else {
            System.out.println("Number:" + phone);
            for (String match : matches) {
                System.out.println(match.toUpperCase());
            }
        }
    }

}
