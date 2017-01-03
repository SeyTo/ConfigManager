package org.rjj.config.binder;

import org.rjj.config.environment.Environment;
import org.rjj.config.selectors.RefLang;

import java.util.HashMap;
import java.util.Map;

/**
 * Binds a RefLang expression to a value and an environment.
 * Created by rj on 19/12/16.
 */
public class MutableBinder implements Binder {

    private Map<Environment, Map<RefLang, Wrapper>> objectMap;

    public MutableBinder() {
        this.objectMap = new HashMap<>();
    }

    @Override
    public void bind(Environment environment, RefLang reflang, Wrapper value) {
        // if environment exists then just use the previous map to put the object and reflang
        if (objectMap.containsKey(environment)) {
            objectMap.get(environment).put(reflang, value);
        } else {
            Map<RefLang, Wrapper> refObjectMap = new HashMap<>();
            refObjectMap.put(reflang, value);
            objectMap.put(environment, refObjectMap);
        }

    }

    /**
     * <u>called only by the providers and loopers.</u>
     * @param reflang find the object value using this value
     * @param object the object to bind the value to
     */
    @Override
    public void update(RefLang reflang, Object object) {
        //find the object's reflang and environment
        objectMap.forEach((environment, refLangObjectMap) -> refLangObjectMap.forEach((refLang, o) -> {
            if (refLang == reflang)
                refLangObjectMap.get(refLang).change(object);
        }));
    }

    @Override
    public void unbind(RefLang reflang, Wrapper wrapper) {
        //find the object,
        for (Environment environment : objectMap.keySet()) {
            if (objectMap.get(environment).get(reflang) == wrapper) {
                objectMap.get(environment).remove(reflang);
                if (objectMap.get(environment).size() == 0) objectMap.remove(environment);
                break;
            }
        }
    }

    public Map<Environment, Map<RefLang, Wrapper>> getObjectMap() {
        return objectMap;
    }

    @Override
    public void clean() {
        objectMap.clear();
    }
}
