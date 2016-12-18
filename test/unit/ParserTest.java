package unit;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.rjj.config.parser.ParserFactory;
import org.rjj.config.parser.XMLParser;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by rj on 14/12/16.
 */
public class ParserTest {

    private String jsonConfigPath;
    private String propertiesConfigPath;
    private String propertiesConfigXMLPath;
    private String xmlConfigPath;


    @Before
    public void init() {
        jsonConfigPath = "./res/json-config.json";
        propertiesConfigPath = "./res/properties-config.properties";
        propertiesConfigXMLPath = "./res/properties-xml-config.xml";
        xmlConfigPath = "./res/xml-config.xml";
    }

    @Ignore ("Impartial Test")
    public void generateJsonFile() throws Exception{
        Gson gson = new Gson();

        Map<String, String> maps = new HashMap<>();
        maps.put("Key1", "Value1");
        maps.put("Key2", "Value2");
        maps.put("Key3", "Value3");

        TestObject object = new TestObject(
                "id_value", "name_value",
                new InnerObject[] {
                        new InnerObject(1, 11),
                        new InnerObject(2, 22)
                },
                maps
        );
        FileWriter writer = new FileWriter(jsonConfigPath);
        String val = gson.toJson(object);
        System.out.println(val);
        writer.write(val);
        writer.close();
    }

    @Test
    public void generatePropertiesFile() throws Exception {
        Properties properties = new Properties();
        properties.put("Key1", "Value1");
        properties.put("Key2", "Value2");
        properties.put("Key3", "Value3");
        properties.put("Key4.4", "Value4");
        properties.put("Key5.5", "Value5");
        properties.put("Key6.6", "Value6");
        properties.store(new FileWriter(propertiesConfigPath), "");
        properties.storeToXML(new FileOutputStream(propertiesConfigXMLPath), "");
    }

    @Test
    public void jsonTest() throws Exception{
        Properties properties = new Properties();
        ParserFactory.get().parse(new File(jsonConfigPath), properties);
        System.out.println(properties); //TODO complete
    }

    @Test
    public void yamlTest() throws Exception {

    }

    @Test
    public void xmlTest() throws Exception {
        Properties properties = new Properties();
        XMLParser parser = new XMLParser(new File(xmlConfigPath));
        parser.look("bad_lat", XMLParser.Attr.__("val", "b1"), XMLParser.Attr.__("val2", "b2")).getSimilar();//.look("e", XMLParser.Attr.__("val", "1")).getRoot();
        System.out.println(parser.getSimilar());
        //parser.lookForAll();
        //System.out.println(parser.getAll());
    }

    @Test
    public void propertiesTest() throws Exception {
        Properties properties = new Properties();
        properties.put("Extra", "ExtraV");
        ParserFactory.get().parse(new File(propertiesConfigPath), properties);
        System.out.println(properties);
    }
}

/*
    innerObject.id
    innerObject.1.id

    maps.Key2   Value2
*/