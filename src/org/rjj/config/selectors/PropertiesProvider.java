package org.rjj.config.selectors;

import java.io.*;
import java.util.Objects;
import java.util.Properties;
import java.util.Vector;

/**
 * Created by rj on 19/12/16.
 */
public class PropertiesProvider implements Provider {

    @Override
    public Object getProperty(InputStream is, RefLang reflang) {
        confirm = false;
        String key = (String)reflang.getCats().get(0);
        System.out.println("Key = " + key);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("#") && line.contains("=")) {
                    String[] lineSplit = line.split("=");
                    if (lineSplit[0].equals(key)) {
                        confirm = true;
                        return lineSplit.length == 1? null : lineSplit[1];
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to read properties");
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException ignored) {}
        }
        return null;
    }

    private boolean confirm = false;
    @Override
    public boolean confirm() {
        return false;
    }

    @Override
    public void implementTo(InputStream is, RefLang reflang, Object object) {

    }

    public Vector<?> breakdown(String lang) throws LanguageException {
        Vector<String> classes = new Vector<>();
        classes.add(lang);
        return classes;
    }

}
