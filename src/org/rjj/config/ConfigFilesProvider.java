package org.rjj.config;

import java.nio.file.Path;

/**
 * Created by rj on 14/12/16.
 */
public interface ConfigFilesProvider {

    Iterable<Path> getConfigFiles();
}
