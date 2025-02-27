package demo.utils;

import org.json.JSONObject;

import java.io.*;
import java.util.Properties;

public class FileUtils {

    public static Properties loadPropertiesFromFile(String filePath) throws FileNotFoundException {
        Properties properties = new Properties();
        try {
            InputStream inputStream = getFileAsInputStream(filePath);
            properties.load(inputStream);
            return properties;
        } catch (IOException var17) {
            throw new RuntimeException(var17);
        }
    }

    public static JSONObject loadJsonFromFile(String filePath) throws FileNotFoundException {
        return new JSONObject(loadFileAsString(filePath));
    }

    public static String loadFileAsString(String filePath) throws FileNotFoundException {
        InputStream inputStream = getFileAsInputStream(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder responseStringBuilder = new StringBuilder();

        String inputString;
        try {
            while((inputString = bufferedReader.readLine()) != null) {
                responseStringBuilder.append(inputString).append("\n");
            }
        } catch (IOException var6) {
            throw new RuntimeException(var6);
        }
        return responseStringBuilder.toString();
    }

    protected static InputStream getFileAsInputStream(String filePath) throws FileNotFoundException {
        InputStream inputStream = FileUtils.class.getClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new FileNotFoundException("file not found: " + filePath);
        } else {
            return inputStream;
        }
    }
}
