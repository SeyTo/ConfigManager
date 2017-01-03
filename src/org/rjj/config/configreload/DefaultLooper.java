package org.rjj.config.configreload;

import org.rjj.config.Cleanable;
import org.rjj.config.binder.Binder;
import org.rjj.config.binder.MutableBinder;
import org.rjj.config.binder.Wrapper;
import org.rjj.config.provider.Updater;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Loops in certain interval and updates binders when it sees that a configuration file has been modified.
 * Created by rj on 19/12/16.
 */
public class DefaultLooper implements Looper {

    /**
     * The interval in which loop is executed.
     */
    private int INTERVAL = 3000;   //30 sec
    /**
     * Restart the loop when the loop crashes.
     */
    private boolean AUTORESTART = false;
    private boolean isRunning = false;

    private Binder binder;

    public DefaultLooper() {
    }

    public DefaultLooper(int INTERVAL, boolean AUTORESTART) {
        this.INTERVAL = INTERVAL;
        this.AUTORESTART = AUTORESTART;
    }

    public void setBinder(Binder binder) {
        this.binder = binder;
    }

    @Override


    public void start() {
        if (!isRunning)
            isRunning = true;
        else
            return;

        MutableBinder muteBinder = binder instanceof MutableBinder ? (MutableBinder) binder : null;
        Thread looperThread = new Thread(() -> {
            while (isRunning) {
                try {
                    Thread.sleep(INTERVAL);
                } catch (InterruptedException e) { //TODO new_
                    System.out.println("looper thread was stopped");
                    isRunning = false;
                    if (AUTORESTART) start();
                }
                if (!isRunning) break;

                if (muteBinder != null) {
                    System.out.println("Binding");
                    Logic.__().refreshOnModified_Logic(muteBinder);
                }
            }
        });
        looperThread.start();

    }



    //??should i separate logic?
    public static class Logic {
        private Map<Path, Long> modifiedDate;

        private Logic() {
            modifiedDate = new HashMap<>();
        }

        /**
         * Stores modified date for each path
         */
        private Updater updater;

        public void setUpdater(Updater updater) {
            this.updater = updater;
        }

        private static Logic logic = new Logic();
        public static Logic __() {
            return logic;
        }

        /**
         * <p>
         *     A refreshOnModified_Logic for updating <code>{@link MutableBinder}</code>.
         * </p>
         * <p>
         *     This refreshOnModified_Logic first looks if a file has been modified. If yes then it draws all its values by using updater
         *     and updates using the {@link Wrapper} class.
         * </p>
         * @param binder a <code>MutableBinder</code> to which updates are made.
         */
        public void refreshOnModified_Logic(MutableBinder binder) {
            binder.getObjectMap().forEach((environment, refLangWrapperMap) -> {
                try {

                    //check if any files has been modified to their prior date
                    Arrays.stream(environment.getPaths()).forEach(path -> {

                        long date = path.toFile().lastModified();
                        System.out.println("Date : " + date);
                        //if path does not have a modified date then dont just add the new date

                        if (modifiedDate.get(path) == null) {

                            modifiedDate.put(path, date);
                            System.out.println("First time finding out");

                        } else {

                            //if file has been modified then change the date and update the wrapper

                            if (modifiedDate.get(path) != date) {
                                System.out.println("Modified date != date");
                                refLangWrapperMap.forEach(
                                        (refLang, wrapper) -> {
                                            Object newVal = updater.get(environment, refLang);
                                            wrapper.change(newVal);
                                            System.out.println("Updated value = " + newVal);
                                        }
                                );
                                modifiedDate.put(path, date);
                                System.out.println("Date modified");
                            }
                            System.out.println("somehow checked the date");
                        }

                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        }
    }

    public Cleanable getCleanable() {
        return DefaultLooper.this::stop;
    }

    public void stop() {
        isRunning = false;
    }
}
