package org.rjj.config.selectors;

import org.rjj.config.Implementer;

/**
 * Created by rj on 20/12/16.
 */
public interface Selector {

    Provider provide(String fileName);

    Implementer implement(String fileName);
}
