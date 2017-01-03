package org.rjj.config.selectors;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Parses files to properties object.
 * Created by rj on 14/12/16.
 */
public interface Parser {
    void parse(String value, Properties properties);
    void parse(File file, Properties properties) throws IOException;
}
