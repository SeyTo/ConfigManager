package org.rjj.config.provider;

import org.rjj.config.environment.Environment;
import org.rjj.config.selectors.RefLang;

/**
 * Created by rj on 26/12/16.
 */
public interface Updater {

    Object get(Environment environment, RefLang lang);
}
