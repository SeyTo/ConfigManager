package org.rjj.config;

import java.net.URL;
import java.util.Properties;

/**
 * Created by rj on 14/12/16.
 */
public class CachedConfigurationSource implements ConfigurationSource {


    private final ConfigFilesProvider configFilesProvider;

    public CachedConfigurationSource(ConfigFilesProvider configFilesProvider) {
        this.configFilesProvider = configFilesProvider;
    }

    @Override
    public Properties getProperties(Environment environment) {
        return null;
    }

    @Override
    public void init() {
        //read all configurations
    }
}
