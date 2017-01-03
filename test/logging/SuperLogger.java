package logging;

import org.rjj.config.logg.MasterLogger;

import java.util.logging.Logger;

/**
 * SuperLogger is test based logging class which allows logging only for testing purposes.
 *
 * Created by rj on 28/12/16.
 */
public class SuperLogger implements MasterLogger{

    private Logger logger;

    public SuperLogger() {
        logger = Logger.getLogger("config");        //TODO get name from "settings.gradle"
    }

    @Override
    public Logger __() {
        return logger;
    }

}
