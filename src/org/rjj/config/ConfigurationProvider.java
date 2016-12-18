package org.rjj.config;

/**
 * Created by rj on 14/12/16.
 */
public interface ConfigurationProvider {

    <T> T getProperty(String var1, Class<T> clazz);

    //<T> T getProperty(String var1, GenericTypeInterface var2);

    <T> T bind(String var1, Class<T> clazz);

}
