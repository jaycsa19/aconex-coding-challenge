package com.aconex.loader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * ArgumentParser parses arguments given by user to check if the user has given phone number file or dictionary file.
 */
public class ArgumentParser {

    public InputStream phoneNumberInputStream = null;
    public InputStream dictionaryInputStream = null;

    public ArgumentParser(String[] args) {
        processArgs(args);
    }

    /**
     * @param args - array of string which can have -p followed by file path containing phone numbers and -d followed
     *             by dictionary file path. If there is a valid phone number file/dictionary file given by the user,
     *             phoneNumberInputStream/dictionaryInputStream are initialized with the input stream of the file.
     *             If not, they remain null.
     */
    private void processArgs(String args[]) {
        int index = 0;
        while (index < args.length) {
            if (args[index].equals("-p")) {
                this.phoneNumberInputStream = processFile(args[index + 1]);
                index++;
            } else if (args[index].equals("-d")) {
                this.dictionaryInputStream = processFile(args[index + 1]);
                index++;
            } else {
                printUsage();
                break;
            }
            index++;
        }
    }

    private InputStream processFile(String filePath) {
        try {
            InputStream inputStream = new FileInputStream(filePath);
            return inputStream;
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist: " + filePath);
            return null;
        }
    }

    private void printUsage() {
        System.out.println(
                "Usage: java -jar aconex-coding-challenge-1.0-SNAPSHOT [OPTIONS]\n\n"
                        + "Aconex 1800 coding challenge "
                        + "Options:\n"
                        + "-p <path for phone number file> If not provided, it will read input from stdin\n"
                        + "-d <path for dictionary file> If not provided, it will use the default one");
    }
}
