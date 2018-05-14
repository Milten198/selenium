package util.configurations;

import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import util.enums.BrowserType;
import util.enums.EnvironmentName;

import java.io.IOException;
import java.util.Properties;

@Log4j
public class TestConfiguration extends Properties {

    private static TestConfiguration properties;

    public static EnvironmentName environmentName;

    public static BrowserType browserParameter;

    public static String baseUri;

    public static String[] dbDefaultUser;
    public static String dbDefaultServer;
    public static String dbDefaultDatabase;
    public static String connectionString;

    public static String downloadFolderPath;

    public static void initialise() {
        setProperties();
    }

    public static String getProp(String key) {
        return properties.getProperty(key);
    }

    public static Properties getProps() {
        return properties;
    }

    private static void setProperties() {
        if (properties == null) {
            properties = new TestConfiguration();

            properties.putAll(loadFromResource("/TestConfiguration.properties"));

            init();
        }
    }

    private static Properties loadFromResource(final String resourceName) {
        final Properties props = new Properties();
        try {
            props.load(TestConfiguration.class.getResourceAsStream(resourceName));
        } catch (IOException e) {
            log.error("Unable to load '{}' file " + resourceName);
        }
        return props;
    }

    private static void init() {

        environmentName = getEnvironmentName();

        switch (environmentName) {

            case DEV:
                getPropertiesForEnvName(EnvironmentName.DEV);
                getSharedProperties();
                break;

            case TEST:
                getPropertiesForEnvName(EnvironmentName.TEST);
                getSharedProperties();
                break;
        }
    }

    private static EnvironmentName getEnvironmentName() {
        EnvironmentName environmentName;
        String environmentNameValue = System.getProperty("environmentName");
        if (StringUtils.isNotEmpty(environmentNameValue)) {
            environmentName = EnvironmentName.valueOf(environmentNameValue);
        } else {
            environmentName = EnvironmentName.valueOf(properties.getProperty("environmentName"));
        }
        return environmentName;
    }

    private static void getPropertiesForEnvName(EnvironmentName environmentName) {

        String envName = environmentName.toString() + ".";

        baseUri = properties.getProperty(envName + "baseUri");

        dbDefaultUser = properties.getProperty(envName + "dbUser").split(",");
        dbDefaultServer = properties.getProperty(envName + "dbServer");
        dbDefaultDatabase = properties.getProperty(envName + "dbDatabase");
        connectionString = properties.getProperty(envName + "connectionString");
    }

    private static void getSharedProperties() {
        browserParameter = BrowserType.valueOf(properties.getProperty("browserParameter"));
        downloadFolderPath = System.getProperty("user.dir") + "\\" + properties.getProperty("downloadFolderPath");
    }
}
