package demo.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;

public class JsonObjectUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonObjectUtils.class);

    public static JSONObject jsonFromFile(String file) throws JSONException, FileNotFoundException {
        return FileUtils.loadJsonFromFile(file);
    }

    public static boolean areEqual(Object obj1, Object obj2) throws JSONException {
        if (obj1 instanceof JSONObject && obj2 instanceof JSONObject) {
            return compareJsonObjects((JSONObject) obj1, (JSONObject) obj2);
        } else if (obj1 instanceof JSONArray && obj2 instanceof JSONArray) {
            return compareJsonArrays((JSONArray) obj1, (JSONArray) obj2);
        }
        return obj1.equals(obj2);
    }

    private static boolean compareJsonObjects(JSONObject obj1, JSONObject obj2) throws JSONException {
        if (obj1.length() != obj2.length()) {
            return false;
        }
        for (String key : obj1.keySet()) {
            if (!obj2.has(key)) {
                return false;
            }
            Object value1 = obj1.get(key);
            Object value2 = obj2.get(key);

            if (value1.equals("ignoreVal")) {
                continue;
            }

            if (!areEqual(value1, value2)) {
                return false;
            }
        }
        return true;
    }

    private static boolean compareJsonArrays(JSONArray arr1, JSONArray arr2) throws JSONException {
        if (arr1.length() != arr2.length()) {
            return false;
        }
        for (int i = 0; i < arr1.length(); i++) {
            if (!areEqual(arr1.get(i), arr2.get(i))) {
                return false;
            }
        }
        return true;
    }
}
