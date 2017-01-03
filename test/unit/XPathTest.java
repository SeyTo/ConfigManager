package unit;

import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.FileInputStream;

/**
 * Created by rj on 25/12/16.
 */
public class XPathTest {

    @Test
    public void init() throws Exception {
        Document document =
                DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new FileInputStream("./test-res/xml-config.xml"));

        XPath xPath = XPathFactory.newInstance().newXPath();
        System.out.println(xPath.compile("/all/PhoneNumbers").evaluate(document));

    }
}
