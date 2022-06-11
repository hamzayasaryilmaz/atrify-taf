package helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHelper {
    public static Properties prop = null;
    public static void loadPropertiesFile(String filePath) {
        if (prop == null) {
            prop = new Properties();

            try (InputStream resourceAsStream = PropertiesHelper.class.getClassLoader().getResourceAsStream(filePath)) {
                prop.load(resourceAsStream);
            } catch (IOException e) {
                System.err.println("Unable to load properties file : " + filePath);
            }
        }
    }
}