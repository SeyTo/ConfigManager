package org.rjj.config.source;

import org.rjj.config.environment.Environment;
import org.rjj.config.logg.cLog;
import org.rjj.config.selectors.Provider;
import org.rjj.config.selectors.RefLang;
import org.rjj.config.selectors.Selector;

import java.io.*;
import java.nio.file.Path;
import java.util.logging.Level;

/**
 * <p>
 *     FilesConfigurationSource iterates through each configuration files for requested property, selects the proper
 *     streamer for the type of file, initialize the RefLang's breaker and finally provides with a property or null or empty
 *     String.
 * </p>
 * Created by rj on 19/12/16.
 */
public class FilesConfigurationSource implements ConfigurationSource{

    private static final String LOG = FilesConfigurationSource.class.getName() + " > ";

    private Selector selector;

    public FilesConfigurationSource() {}

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    @Override
    public Object getProperties(Environment environment, RefLang lang) {
        try {
            Path[] paths = environment.getPaths();
            for (Path path : paths) {
                //for each path get properties using lang. if null is returned then go to next path.
                try {
                    Provider provide = selector.provide(path.toString());
                    lang.breakdown(provide);
                    Object o = provide.getProperty(getInputStream(path), lang);
                    if (o != null || provide.confirm())
                        return o;
                } catch (FileNotFoundException e) {
                    cLog.__().__().log(Level.WARNING, LOG + " (" + path + ") config file not found");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void implement(Environment environment, RefLang lang, Object object) {}

    /**
     * Sets up different types of streams
     * @param path
     * @return
     * @throws IOException
     */
    private InputStream getInputStream(Path path) throws IOException{
        return new FileInputStream(path.toFile());
    }

}
