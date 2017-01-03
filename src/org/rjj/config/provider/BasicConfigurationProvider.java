package org.rjj.config.provider;

import org.rjj.config.Cleanable;
import org.rjj.config.binder.Binder;
import org.rjj.config.binder.MutableBinder;
import org.rjj.config.binder.Wrapper;
import org.rjj.config.configreload.DefaultLooper;
import org.rjj.config.environment.Environment;
import org.rjj.config.selectors.RefLang;
import org.rjj.config.source.ConfigurationSource;

import java.util.HashSet;

/**
 * <p>
 *     Provides the user with property getters, binders and implementers.
 * </p>
 * Created by rj on 14/12/16.
 */
public class BasicConfigurationProvider implements ConfigurationProvider, Cleanable {  //this class seems a little bit like a middle man, useless.

    private ConfigurationSource configurationSource;
    private HashSet<Environment> environment;
    private Binder binder;

    public BasicConfigurationProvider(ConfigurationSource source, HashSet<Environment> environment, Binder binder) {
        this.configurationSource = source;
        this.environment = environment;
        this.binder = binder;
    }

    @Override
    public Object getProperties(String environmentName, RefLang lang) {
        return this.getProperties(getEnvironment(environmentName), lang);
    }

    public Object getProperties(Environment environment, RefLang lang) {
        return configurationSource.getProperties(environment, lang);
    }

    public void refreshProperties(String environmentName) {
        this.refreshProperties(getEnvironment(environmentName));
    }

    public void refreshProperties(Environment environment) {
        if (binder instanceof MutableBinder) {
            DefaultLooper.Logic.__().refreshOnModified_Logic((MutableBinder) binder);
            System.out.println("refreshed");
        }
    }

    @Override
    public void bind(String environmentName, RefLang lang, Wrapper wrapper) {
        binder.bind(getEnvironment(environmentName), lang, wrapper);
        binder.update(lang, getProperties(environmentName, lang));
    }

    private Environment getEnvironment(String name) {
        Environment env = null;
        //look for matching environment,
        for (Environment envr : this.environment)
            if (envr.getName().equals(name)) {
                env = envr;
                break;
            }
        if (env == null) {
            System.out.println("No matching environment found");
            return null;
        }
        return env;
    }

    private Cleanable cleanable;
    public void setCleanable(Cleanable cleanable) {
        this.cleanable = cleanable;
    }

    @Override
    public void clean() {
        if (cleanable != null)
            this.cleanable.clean();
        this.environment.clear();
        this.binder.clean();
    }
}
