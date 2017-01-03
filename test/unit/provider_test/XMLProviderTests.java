package unit.provider_test;

import org.junit.Test;
import org.rjj.config.selectors.RefLang;
import org.rjj.config.selectors.XMLProvider;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by rj on 21/12/16.
 */
public class XMLProviderTests {

    @Test
    public void basic() throws Exception {
        XMLProvider provider = new XMLProvider();
        Object result =
                provider.getProperty(new FileInputStream(new File("./test-res/xml-config.xml")), RefLang.new_("a.bad_lat.val='b1'"));

        System.out.println(result);

    }
}
