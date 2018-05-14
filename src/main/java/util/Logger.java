package util;

import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.util.Properties;


public class Logger {

    public static void init() {

        Properties log4jProperties = new Properties();
        try {
            log4jProperties.load(Logger.class.getResourceAsStream("/log4j.properties"));
            PropertyConfigurator.configure(log4jProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
