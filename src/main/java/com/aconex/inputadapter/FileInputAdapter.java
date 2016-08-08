package com.aconex.inputadapter;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Reads data from file, one String per line.
 */
public class FileInputAdapter implements InputAdapter {
    /**
     * If input stream is null, returns null, otherwise, reads the given file.
     *
     * @param inputStream - stream of file.
     * @return Set of words in the file.
     */
    public Set<String> readData(InputStream inputStream) {
        Set<String> data = new HashSet<String>();
        if (inputStream == null) {
            return null;
        }

        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()) {
            data.add(scanner.nextLine());
        }
        return data;
    }
}
