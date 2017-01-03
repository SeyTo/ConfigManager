package org.rjj.config.selectors;

import org.rjj.config.logg.cLog;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;
import java.util.Vector;

/**
 * Created by rj on 19/12/16.
 */
public class XMLProvider implements Provider {

    private static final String LOG = XMLProvider.class.getName();

    private XPath xPath;
    public XMLProvider() {
        xPath = XPathFactory.newInstance().newXPath();
    }

    @Override
    public Vector<?> breakdown(String lang) throws LanguageException {
        Vector<String> broken = new Vector<>();
        broken.add(lang);
        return broken;
    }

    @Override
    public Object getProperty(InputStream is, RefLang reflang) {
        try {
            confirm = false;
            xPath.reset();
            String result = xPath.evaluate((String) reflang.getCats().get(0), new InputSource(is));
            if (!result.isEmpty()) {
                confirm = true;
                return result;
            }
        } catch (XPathExpressionException e) {
            cLog.__().__().throwing(LOG, "getProperty", e);
            e.printStackTrace();
        }
        return null;
    }

    private boolean confirm = false;
    @Override
    public boolean confirm() {
        return confirm;
    }

    @Override
    public void implementTo(InputStream is, RefLang reflang, Object object) {
        //NO-OP
    }
}
