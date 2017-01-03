package org.rjj.config.environment;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Looks for a text file in given by path within which a list of other configuration files are found. The refreshOnModified_Logic of
 * getting paths from the text file is implemented here. While you may want to change that to suit your needs.
 * Created by rj on 19/12/16.
 */
public class LookupEnviroment implements Environment {

    private String name;
    private Path path;

    public LookupEnviroment(String name, Path path) {
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
    public Path[] getPaths() throws IOException{
        List<Path> paths = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(path.toFile()));
        String line;

        while ((line = reader.readLine()) != null) {
            File file = new File(line);
            if (!file.exists())
                throw new FileNotFoundException("file : " + file.toString() + " does not exists. Or is invalid file");
            paths.add(file.toPath());
        }

        return paths.toArray(new Path[paths.size()]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LookupEnviroment that = (LookupEnviroment) o;

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
