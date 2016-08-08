package com.aconex.inputadapter;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Reads data from console, one String per line.
 */
public class ConsoleInputAdapter implements InputAdapter {
    /**
     * If input stream is null, returns null, otherwise, reads from console, until user enters 'q' or 'quit'.
     *
     * @param stream - console stream.
     * @return Set of words from the console.
     */
    public Set<String> readData(InputStream stream) {
        if (stream == null) {
            return null;
        }
        Set<String> data = new HashSet<String>();
        Scanner scanner = new Scanner(stream);
        while (scanner.hasNext()) {
            String text = scanner.nextLine();
            if (text.equalsIgnoreCase("q") || text.equalsIgnoreCase("quit")) {
                break;
            }
            data.add(text);
        }
        return data;
    }
}

