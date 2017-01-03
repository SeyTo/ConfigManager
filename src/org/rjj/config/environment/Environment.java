package org.rjj.config.environment;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Explains a list of files and their types
 * Created by rj on 14/12/16.
 */
public interface Environment {
    String getName();

    Path getPath();

    /**
     *
     * @return
     * @throws IOException
     * exception while this method performs actions like checking if file exists or even viewing its content.
     */
    Path[] getPaths() throws IOException;
}

/*
    /mnt/mydrive/configs/   <- all configurations within this

*/
