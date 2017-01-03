package org.rjj.config;

import org.rjj.config.binder.Binder;
import org.rjj.config.configreload.DefaultLooper;
import org.rjj.config.configreload.Looper;
import org.rjj.config.environment.Environment;
import org.rjj.config.logg.cLog;
import org.rjj.config.provider.BasicConfigurationProvider;
import org.rjj.config.provider.ConfigurationProvider;
import org.rjj.config.provider.Updater;
import org.rjj.config.selectors.ProviderSelector;
import org.rjj.config.source.ConfigurationSource;

import java.util.HashSet;

/**
 * Builder used for building <code>ConfigurationProvider</code> with
 * <ol>
 *     <li>
 *          <code>ConfigurationProvider</code>
 *     </li>
 *     <li>
 *         <code>Set&lt;Environment&gt;</code>
 *     </li>
 *     <li>
 *         <code>Looper</code>
 *     </li>
 *     <li>
 *         <code>Binder</code>
 *     </li>
 * </ol>
 * Created by rj on 19/12/16.
 */
public class ConfigurationProviderBuilder {

    private ConfigurationSource configurationSource;
    private HashSet<Environment> environments = new HashSet<>();
    private Looper looper;
    private Binder binder;

    public ConfigurationProviderBuilder with(Environment environment) {
        environments.add(environment);
        return this;
    }

    public ConfigurationProviderBuilder with(Looper looper) {
        this.looper = looper;
        return this;
    }

    public ConfigurationProviderBuilder with(ConfigurationSource configurationSource) {
        this.configurationSource = configurationSource;
        return this;
    }

    public ConfigurationProviderBuilder with(Binder binder) {
        this.binder = binder;
        return this;
    }

    public ConfigurationProvider build() {
        this.configurationSource.setSelector(new ProviderSelector());

        BasicConfigurationProvider provider = new BasicConfigurationProvider(this.configurationSource, this.environments, this.binder);

        Updater updater = provider::getProperties;
        DefaultLooper.Logic.__().setUpdater(updater);   //TODO Logic should be separate
        if (looper != null) {
            provider.setCleanable(looper.getCleanable());
            this.looper.setBinder(this.binder);
            this.looper.start();
        }
        return provider;
    }





}
