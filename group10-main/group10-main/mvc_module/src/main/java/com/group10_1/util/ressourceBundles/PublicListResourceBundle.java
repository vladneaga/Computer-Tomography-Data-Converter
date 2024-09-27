package com.group10_1.util.ressourceBundles;

import java.util.Map;


/**
 * Abstract base class for public list resource bundles for internationalization.
 * Provides a method to retrieve the contents as a map.
 */
public abstract class PublicListResourceBundle {

    /**
     * Returns the contents of the resource bundle as a map.
     *
     * @return a map containing the key-value pairs from the resource bundle
     */
    public Map<String, String> getContents() {
        // Implementation-specific logic for retrieving resource bundle contents
        // Subclasses should override this method to provide actual contents
        return null;
    }
}