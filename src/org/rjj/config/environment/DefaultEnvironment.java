package org.rjj.config.environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Looks for configuration files which ends with '.yaml','.yml','.xml','.json','.properties' in the given directory and
 * subdirectories.
 * Created by rj on 19/12/16.
 */
public class DefaultEnvironment implements Environment {

    private static final String[] RECOGNIZED_EXTENSION = {
        ".yml", ".yaml", ".xml", ".properties", ".json"
    };

    private String name;
    private Path path;
    public DefaultEnvironment(String name, Path path) {
        this.name = name;
        this.path = path;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Path getPath() {
        return path;
    }

    @Override
    public Path[] getPaths() throws IOException {
        File file = path.toFile();
        List<Path> paths = new ArrayList<>();
        if (!file.exists()) throw new FileNotFoundException("file : " + file + " does not exits.");
        collate(file, paths);
        return paths.toArray(new Path[paths.size()]);
    }

    private void collate(File file, List<Path> paths) {
        if (file.isDirectory() && file.list() != null) {
            if (file.list().length != 0)
                Arrays.stream(file.listFiles()).forEach(file1 -> {
                    if (Arrays.stream(RECOGNIZED_EXTENSION).anyMatch(file1.toString()::endsWith))
                        paths.add(file1.toPath());
                    else if (file1.isDirectory())
                        collate(file1, paths);
                });
        } else if (file.isFile()) {
            if (Arrays.stream(RECOGNIZED_EXTENSION).anyMatch(file.toString()::endsWith))
                paths.add(file.toPath());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DefaultEnvironment that = (DefaultEnvironment) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return path != null ? path.equals(that.path) : that.path == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        return result;
    }
}
