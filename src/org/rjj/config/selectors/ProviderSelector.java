package org.rjj.config.selectors;

import org.rjj.config.Implementer;

/**
 * Created by rj on 19/12/16.
 */
public class ProviderSelector implements Selector{

    private Provider xmlProvider;
    private Provider propertiesProvider;

    public ProviderSelector() {
        xmlProvider = new XMLProvider();
        propertiesProvider = new PropertiesProvider();
    }

    private static final String[] SUPPORTED_EXTENSION = {
            ".xml", ".properties"
    };

    public Provider provide(String fileName) {
        if (fileName.endsWith(SUPPORTED_EXTENSION[0]))
            return xmlProvider;
        else if (fileName.endsWith(SUPPORTED_EXTENSION[1]))
            return propertiesProvider;
        else
            return null;
    }

    public Implementer implement(String fileName) {
        return null;
    }
}
