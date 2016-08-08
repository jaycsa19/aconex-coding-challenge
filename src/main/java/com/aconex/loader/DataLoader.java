package com.aconex.loader;

import com.aconex.inputadapter.ConsoleInputAdapter;
import com.aconex.inputadapter.FileInputAdapter;
import com.aconex.inputadapter.InputAdapter;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Set;

/**
 * Loads dictionary and phone number data.
 * Loads dictionary file given by user, if not, loads default dictionary.
 * Loads phone numbers file if given by user,otherwise reads from console.
 */
public class DataLoader {

    /**
     * If input stream is null, reads default dictionary, otherwise, reads the file given.
     *
     * @param inputStream - stream of dictionary file.
     * @return Set of words in the file.
     */
    public Set<String> loadDictionaryFile(InputStream inputStream) throws FileNotFoundException {
        if (inputStream == null) {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            return getFileData(loader.getResourceAsStream("dictionary.txt"));
        } else {
            return getFileData(inputStream);
        }
    }

    /**
     * If input stream is null, reads from console, otherwise, reads the file given.
     *
     * @param inputStream - stream of phone number file.
     * @return Set of words in the file/console.
     */
    public Set<String> loadPhoneNumbers(InputStream inputStream) throws FileNotFoundException {
        if (inputStream == null) {
            System.out.println("Please enter phone numbers, press q or quit to exit.");
            InputAdapter inputAdapter = new ConsoleInputAdapter();
            return inputAdapter.readData(System.in);
        } else {
            return getFileData(inputStream);
        }
    }

    private Set<String> getFileData(InputStream inputStream) throws FileNotFoundException {
        InputAdapter inputAdapter = new FileInputAdapter();
        return inputAdapter.readData(inputStream);
    }
}
