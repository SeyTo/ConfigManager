package org.rjj.config.selectors;

import org.rjj.config.Implementer;

import java.io.InputStream;
import java.util.Vector;

/**
 * Created by rj on 19/12/16.
 */
public interface Provider extends Implementer {

    Object getProperty(InputStream is, RefLang reflang);

    Vector<?> breakdown(String lang) throws LanguageException;

    boolean confirm();
}
