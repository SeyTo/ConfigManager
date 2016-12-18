package org.rjj.config.parser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Consumer;

/**
 * Created by rj on 15/12/16.
 */
public class PropertiesParser implements Parser{

    @Override
    public void parse(String value, Properties properties) {
        //TODO
    }

    @Override
    public void parse(File file, Properties preparedProps) throws IOException {
        if (preparedProps == null) preparedProps = new Properties();
        preparedProps.load(new FileReader(file));
    }
}
