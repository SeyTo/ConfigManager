package org.rjj.config;

import org.rjj.config.selectors.RefLang;

import java.io.InputStream;

/**
 * Object implementer. NOOP.
 * Created by rj on 19/12/16.
 */
public interface Implementer {

    /**
     * ?w?Implements a given object by implementing values from markup languagues like json/yaml.
     * @param is input stream to the file.
     * @param reflang a reference code.
     * @param object object on which values are implemented.
     */
    void implementTo(InputStream is, RefLang reflang, Object object);
}
