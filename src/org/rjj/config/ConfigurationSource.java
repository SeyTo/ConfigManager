package org.rjj.config;

import java.util.Properties;

/**
 * Created by rj on 14/12/16.
 */
public interface ConfigurationSource {

    Properties getProperties(Environment environment);

    void init();
}
