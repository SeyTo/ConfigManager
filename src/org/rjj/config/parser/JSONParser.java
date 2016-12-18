package org.rjj.config.parser;

import com.google.gson.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 *
 * Created by rj on 14/12/16.
 */
class JSONParser implements Parser, ClassImplementer{

    public void parse(String json, Properties properties) {
        JsonParser parser = new JsonParser();

        JsonElement element = parser.parse(json);
        for (Map.Entry<String, JsonElement> map : element.getAsJsonObject ().entrySet()) {
            collate(map.getValue(), map.getKey(), properties);
        }
    }

    public void parse(File file, Properties properties) throws IOException {
        JsonParser parser = new JsonParser();

        JsonElement element = parser.parse(new FileReader(file));
        for (Map.Entry<String, JsonElement> map : element.getAsJsonObject ().entrySet()) {
            collate(map.getValue(), map.getKey(), properties);
        }
    }

    /**
     * Binds keys together and forms a single key. Upon finding a value that is not an object or array, the key and
     * value are put into property.
     * @param element
     * The JsonElement from which elements are extracted.
     * @param keyCollate
     * The header key element upon which many keys are collated.
     * @param properties
     * The property onto with items are parsed.
     */
    private void collate(JsonElement element, String keyCollate, Properties properties) {
        if (element.isJsonObject()) {
            for (Map.Entry<String, JsonElement> maps : element.getAsJsonObject().entrySet()) {
                if (maps.getValue().isJsonObject())
                    collate(
                            maps.getValue().getAsJsonObject(),
                            keyCollate + "." + maps.getKey(),
                            properties
                    );
                else if (maps.getValue().isJsonArray())
                    collate(element, keyCollate + maps.getKey(), properties);
                else
                    properties.put(keyCollate + "." + maps.getKey(), removeQuote(maps.getValue().toString()));
            }
        } else if (element.isJsonArray()) {
            JsonArray array = element.getAsJsonArray();
            for (int i = 0; i < array.size(); i++) {
                JsonElement inner_ele = array.get(i);
                collate(inner_ele, keyCollate + "." + i, properties);
            }
        } else
            properties.put(keyCollate, removeQuote(element.toString()));
    }

    private String removeQuote(String value) {
        if (value.startsWith("\""))
            value = value.substring(1);
        if (value.endsWith("\""))
            value = value.substring(0, value.length()-1);
        return value;
    }

    @Override
    public <T> T implementTo(File file, T object) throws IOException {
        return (T)new Gson().fromJson(new FileReader(file), object.getClass());
    }
}
