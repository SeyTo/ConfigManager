package unit;

import java.util.Map;

/**
 * Created by rj on 15/12/16.
 */
public class TestObject {

    private String id;
    private String name;
    private InnerObject[] innerObject;
    private Map<String, String> maps;

    public TestObject(String id, String name, InnerObject[] innerObject, Map<String, String> maps) {
        this.id = id;
        this.name = name;
        this.innerObject = innerObject;
        this.maps = maps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class InnerObject {
    private int id;
    private int innerName;

    public InnerObject(int id, int innerName) {
        this.id = id;
        this.innerName = innerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInnerName() {
        return innerName;
    }

    public void setInnerName(int innerName) {
        this.innerName = innerName;
    }
}
