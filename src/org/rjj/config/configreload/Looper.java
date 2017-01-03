package org.rjj.config.configreload;

import org.rjj.config.Cleanable;
import org.rjj.config.binder.Binder;

/**
 * Created by rj on 19/12/16.
 */
public interface Looper {

    /**
     * Sets a binder from which binds are updates, environments are queried etc.
     * @param binder the binder to set
     */
    void setBinder(Binder binder);

    /**
     * Updaters helps to provide an interface for which this looper uses to get new values of properties.
     * @param updater implemented updater by which getProperties are asked.
     */
    //void setUpdater(Updater updater);

    /**
     * Starts the loop of updating binder values in certain intervals.
     */
    void start();

    /**
     * Stops the loop of updating binder.
     */
    void stop();

    /**
     * Gets closeable means of stopping this looper thread.
     * @return closeable method
     */
    Cleanable getCleanable();
}
