package unit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.extensions.compactnotation.CompactConstructor;
import org.yaml.snakeyaml.nodes.Node;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Created by rj on 16/12/16.
 */
public class YAMLTest {

    private String yamlFile;

    @Before
    public void init() throws Exception {
        yamlFile = "./test-res/yaml-config.yaml";
    }

    @Test
    public void basic() throws Exception {
        Yaml yaml = new Yaml(new CompactConstructor());
        LinkedHashMap<Object, Object> node = (LinkedHashMap<Object, Object>)yaml.load(new FileReader("./test-res/yaml-config.yaml"));
        node.forEach(new BiConsumer<Object, Object>() {
            @Override
            public void accept(Object s, Object s2) {

                System.out.println("key > " + s);
                System.out.println("value > " + s2);
            }
        });

    }
}
