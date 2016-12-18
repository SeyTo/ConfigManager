package org.rjj.config.parser;

import java.io.File;
import java.io.IOException;

/**
 * Created by rj on 14/12/16.
 */
public interface ClassImplementer {

    <T> T implementTo(File file, T object) throws IOException;
}
