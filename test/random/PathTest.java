package random;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.junit.Before;
import org.junit.Test;
import org.yaml.snakeyaml.tokens.TagTuple;

import java.io.*;
import java.nio.file.Path;
import java.util.function.Consumer;

/**
 * Created by rj on 19/12/16.
 */
public class PathTest {

    private String more_config_path;

    @Before
    public void setUp() throws Exception {
        more_config_path = "./test-res/moreconfigs.txt";

    }

    @Test
    public void test1() throws Exception {
        File file = new File("./test-res/yaml-config.yaml");
        Path path = file.toPath();

        path.forEach(new Consumer<Path>() {
            @Override
            public void accept(Path path) {
                System.out.println(path);
            }
        });
        System.out.println(path);
        System.out.println(path.getFileName());
        System.out.println(path.toUri().getPath());
    }

    @Test
    public void test2() throws Exception {
        File moreConfig = new File(more_config_path);
        System.out.println(moreConfig.toPath().getFileName());
        //file needs to be readable
        if (moreConfig.toPath().getFileName().toString().endsWith(".txt")) {
            System.out.println("Found more configs");
            BufferedReader stream = new BufferedReader(new FileReader(moreConfig));
            stream.lines().forEach(new Consumer<String>() {
                @Override
                public void accept(String s) {
                    System.out.println(s);
                }
            });
        }

    }
}
