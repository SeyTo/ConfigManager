package org.rjj.config.binder;

import org.rjj.config.Cleanable;
import org.rjj.config.environment.Environment;
import org.rjj.config.selectors.RefLang;

/**
 * Created by rj on 19/12/16.
 */
public interface Binder extends Cleanable{  //??

    void bind(Environment environment, RefLang reflang, Wrapper wrapper);

    void update(RefLang reflang, Object object);

    void unbind(RefLang reflang, Wrapper wrapper);



}
