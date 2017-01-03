package integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.rjj.config.ConfigurationProviderBuilder;
import org.rjj.config.binder.MutableBinder;
import org.rjj.config.binder.Wrapper;
import org.rjj.config.environment.Environment;
import org.rjj.config.provider.ConfigurationProvider;
import org.rjj.config.selectors.RefLang;
import org.rjj.config.source.FilesConfigurationSource;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

/**
 * Created by rj on 19/12/16.
 */
public class Main {

    //TODO integrate logger
    //TODO complete tests
    private static final String LOG = Main.class.getName() + " > ";

    private String[] config_files;

    @Before
    public void setUp() throws Exception {
        config_files = new String[] {
                "./test-res/moreconfigs.txt",
                "./test-res/properties-config.properties",
                "./test-res/properties-xml-config.xml",
                "./test-res/xml-config.xml",
                "./test-res/yaml-config.yaml"
        };
    }

    @Test
    public void propertiesProviderMain() throws Exception {

        Environment myEnv = new Environment() {
            @Override
            public String getName() {
                return "init.config";
            }

            @Override
            public Path getPath() {
                return new File("./test-res/properties-config.properties").toPath();
            }

            @Override
            public Path[] getPaths() throws IOException {
                return new Path[] {new File("./test-res/properties-config.properties").toPath()};
            }
        };

        //SETUP PROVIDER
        ConfigurationProvider provider =
                new ConfigurationProviderBuilder().
                        with(myEnv).
                        with(new FilesConfigurationSource()).
                        with(new MutableBinder()).
                        build();

        //START GETTING CONFIGS
        RefLang reflang = RefLang.new_("Key1");
        Object pref = provider.getProperties("init.config", reflang);
        assertEquals("Value1", pref);

        String a = "";
        TestClass myTestObject = new TestClass();
        Wrapper wrapper = object -> myTestObject.a = (String)object;

        provider.bind("init.config", reflang, wrapper);
        assertEquals("Value1", myTestObject.a);

        //refresh first time
        provider.refreshProperties(myEnv);
        //>this will add the environment's file's date for the first time

        Properties properties = new Properties();
        properties.load(new FileReader("./test-res/properties-config.properties"));
        properties.setProperty("Key1", "newValue2");
        properties.store(new FileWriter("./test-res/properties-config.properties"), "");
        new File("./test-res/properties-config.properties").setLastModified(System.currentTimeMillis());

        provider.refreshProperties(myEnv);  //this will now check the modification date and then re-add accordingly
        assertEquals("newValue2", myTestObject.a);

        //resetting value of the property
        properties.setProperty("Key1", "Value1");
        properties.store(new FileWriter("./test-res/properties-config.properties"), "");


    }

    class TestClass {
        String a = "0";
    }

    @Test
    public void xmlProviderMain() throws Exception {
        Environment myEnv = new Environment() {
            @Override
            public String getName() {
                return "init.config";
            }

            @Override
            public Path getPath() {
                return new File("./test-res/xml-config.xml").toPath();
            }

            @Override
            public Path[] getPaths() throws IOException {
                return new Path[] {
                        new File("./test-res/properties-xml-config.xml").toPath(),
                        new File("./test-res/xml-config.xml").toPath()
                };
            }
        };

        ConfigurationProvider provider =
                new ConfigurationProviderBuilder().
                        with(new FilesConfigurationSource()).
                        with(myEnv).
                        with(new MutableBinder()).
                        build();

       assertEquals("983217123", provider.getProperties(myEnv, RefLang.new_("/all/PhoneNumbers")));

       //TODO mroe tests required
    }

    @Test
    public void jsonProviderTest() throws Exception {

    }

    @Test
    public void random() throws Exception {


    }

    @After
    public void tearDown() throws Exception {


    }

}
