package com.aconex.inputadapter;

import java.io.InputStream;
import java.util.Set;

/**
 * InputAdapter interface, to be implemented for reading data from file or console.
 */
public interface InputAdapter {
    public Set<String> readData(InputStream stream);
}
