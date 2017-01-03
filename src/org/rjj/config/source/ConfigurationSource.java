package org.rjj.config.source;

import org.rjj.config.environment.Environment;
import org.rjj.config.selectors.RefLang;
import org.rjj.config.selectors.Selector;

/**
 * Created by rj on 14/12/16.
 */
public interface ConfigurationSource {

    void setSelector(Selector selector);

    Object getProperties(Environment environment, RefLang lang);

    void implement(Environment environment, RefLang lang, Object object);
}
