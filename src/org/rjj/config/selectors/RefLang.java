package org.rjj.config.selectors;

import com.sun.istack.internal.NotNull;

import java.util.Vector;

/**
 * @author rj
 */
public class RefLang {

    private
    @NotNull
    String lang;

    private RefLang(@NotNull String lang) {
        this.lang = lang;
    }

    public static RefLang new_(String lang) throws LanguageException {
        return new RefLang(lang);
    }

    private Vector<?> cats;
    public void breakdown(Provider provider) {
        if (provider instanceof PropertiesProvider) {
            cats = provider.breakdown(lang);
        } else if (provider instanceof XMLProvider) {
            cats = provider.breakdown(lang);
        } else {
            System.out.println("Unknown provider found.");
        }
    }

    @Override
    public String toString() {
        return cats.toString();
    }

    public Vector<?> getCats() {
        return cats;
    }
}