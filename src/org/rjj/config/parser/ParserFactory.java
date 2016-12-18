package org.rjj.config.parser;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by rj on 14/12/16.
 */
public class ParserFactory {

    private ParserFactory() {}

    private static ParserFactory factory;

    public static ParserFactory get() {
        if (factory == null) factory = new ParserFactory();
        return factory;
    }

    public void parse(File file, Properties properties) throws IOException {
        Properties _properties = (properties == null)? new Properties() : properties;
        String fileName = file.getName().toLowerCase();

        if (fileName.endsWith(".xml")) {
            System.out.println("Use XmlParser's look() or lookForAll() and getAll() instead.");
        } else if (fileName.endsWith(".json")) {
            new JSONParser().parse(file, _properties);
        } else if (fileName.endsWith(".yaml") || (fileName.endsWith(".yml"))) {

        } else if (fileName.endsWith(".properties")) {
            new PropertiesParser().parse(file, _properties);
        } else {
            System.out.println(
                    "Unrecognized file type (make sure the file ends with either \".xml\",\".json\",\".yaml\",\".yml\", \".properties\")"
            );  //TODO hook with logger
        }
    }

    public Properties parse(String values, Properties properties) {
        return null;    //TODO
    }

    public <T> T implementTo(File file, T object) {
        return null;    //TODO
    }

}
