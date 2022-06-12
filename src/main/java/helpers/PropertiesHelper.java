package helpers;

import com.google.common.collect.ImmutableMap;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

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

    public static void writeAllureEnvironmentProperties() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("base uri", PropertiesHelper.prop.getProperty("application.baseURI"))
                        .put("base path", PropertiesHelper.prop.getProperty("application.basePath"))
                        .put("database name", PropertiesHelper.prop.getProperty("db.name"))
                        .build(), System.getProperty("user.dir") + "/target/allure-results/");
    }
}