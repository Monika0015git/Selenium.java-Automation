package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties prop;

    public ConfigReader() {
        prop = new Properties();
        String filePath = System.getProperty("user.dir") + "/configProperties/config.properties";
        try (FileInputStream fis = new FileInputStream(new File(filePath))) {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage());
        }
    }

    public String getBrowser() {
        return prop.getProperty("browser");
    }

    public String getUrl() {
        String url = prop.getProperty("url");
        if (url == null || url.isEmpty()) {
            throw new RuntimeException("url is not specified in config.properties");
        }
        return url;
    }
}
