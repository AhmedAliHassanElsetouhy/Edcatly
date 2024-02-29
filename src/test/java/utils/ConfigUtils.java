package utils;

import java.util.Properties;

public class ConfigUtils {
    private Properties properties;
    private static ConfigUtils configUtils;

    //Read Environment from properties file
    private ConfigUtils() {
        String env = System.getProperty("env", "PRODUCTION");
        switch (env) {
            case "PRODUCTION":
                properties =
                        PropertiesUtils.loadProperties("src/test/java/config/production.properties");
                break;
            case "LOCAL":
                properties =
                        PropertiesUtils.loadProperties("src/test/java/config/local.properties");
                break;
            default:
                throw new RuntimeException("The Environment is not supported");
        }
    }

    public static ConfigUtils getInstance() {
        if (configUtils == null) {
            configUtils = new ConfigUtils();
        }
        return configUtils;
    }
    //Read BaseURL from Properties File
    public String getBaseUrl() {
        String prop = properties.getProperty("baseUrl");
        if (prop != null) {
            return prop;
        }
        throw new RuntimeException("Could not find the base url in the property file");
    }
    //Read WebsiteTitle from Properties File
    public String getWebsiteTitle() {
        String prop = properties.getProperty("websiteTitle");
        if (prop != null) {
            return prop;
        }
        throw new RuntimeException("Could not find the base url in the property file");
    }
    //Read whatToStudy from Properties File
    public String getWhatToStudy() {
        String prop = properties.getProperty("whatToStudy");
        if (prop != null) {
            return prop;
        }
        throw new RuntimeException("Could not find the base url in the property file");
    }
    //Read whereToStudy from Properties File
    public String getWhereToStudy() {
        String prop = properties.getProperty("whereToStudy");
        if (prop != null) {
            return prop;
        }
        throw new RuntimeException("Could not find the base url in the property file");
    }
}
