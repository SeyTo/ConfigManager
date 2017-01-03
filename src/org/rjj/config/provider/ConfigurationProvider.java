package org.rjj.config.provider;

import org.rjj.config.binder.Wrapper;
import org.rjj.config.environment.Environment;
import org.rjj.config.selectors.RefLang;

/**
 * Created by rj on 14/12/16.
 */
public interface ConfigurationProvider {

    Object getProperties(String environmentName, RefLang lang);

    Object getProperties(Environment environment, RefLang lang);

    void refreshProperties(String environmentName);

    void refreshProperties(Environment environment);

    void bind(String environmentName, RefLang lang, Wrapper o);

}
