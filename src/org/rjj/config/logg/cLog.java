package org.rjj.config.logg;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

/**
 * Created by rj on 28/12/16.
 */
public class cLog {

    private static MasterLogger logger = null;

    static {
        try {
            logger = (MasterLogger) Class.forName("logging.SuperLogger").newInstance();
            logger.__().addHandler(handlers.getConsoleHandler());
            logger.__().addHandler(handlers.getFileHandler());
        } catch (ClassNotFoundException e) {
            System.out.println("SuperLogger expected in test packages. No MasterLogger found");
        } catch (InstantiationException e) {
            System.out.println("Not able to instantiate the SuperLogger");
        } catch (IllegalAccessException e) {
            System.out.println("Illegal access to constructor SuperLogger. Is it private?");
        }
    }

    public static MasterLogger __() {
        if (logger == null)
            logger = () -> {
                System.out.println("logging.SuperLogger not found. Creating an anonymous logger.");
                return Logger.getAnonymousLogger();
            };

        return logger;
    }

    private static class handlers {

        private static Handler getFileHandler() {
            try {
                return new FileHandler("org-rj-config-%g-%u.log", 26214400, 1, true);
            } catch (IOException e) {
                System.out.println("Error writing log to a file.");
            }
            return getConsoleHandler();
        }

        private static Handler getConsoleHandler() {
            return new ConsoleHandler();
        }

    }


}
