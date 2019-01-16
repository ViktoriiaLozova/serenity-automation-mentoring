package serenity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class EnvironmentPropertyLoader {
    private static Logger log = LoggerFactory.getLogger(EnvironmentPropertyLoader.class);
    private static EnvironmentPropertyLoader instance = null;
    public static final String DEFAULT_CONFIG = "environments/env1.properties";
    public static final String ENVIRONMENT_CONFIG_SYSTEM_PROPERTY = "environment.config";
    private final Properties properties = new Properties();

    private EnvironmentPropertyLoader() {
        try {
            if (System.getProperty(ENVIRONMENT_CONFIG_SYSTEM_PROPERTY) == null) {
                this.loadProperties(DEFAULT_CONFIG);
            } else {
                this.loadProperties("environments/" + System.getProperty(ENVIRONMENT_CONFIG_SYSTEM_PROPERTY));
            }

        } catch (IOException var2) {
            throw new IllegalStateException("Failed to load configuration file", var2);
        }
    }

    public static String getProperty(String propertyName) {
        if (instance == null) {
            instance = new EnvironmentPropertyLoader();
        }

        return System.getProperty(propertyName, instance.properties.getProperty(propertyName));
    }

    public static Properties getProperties() {
        return instance.properties;
    }

    private void loadProperties(String resource) throws IOException {
        log.info("Reading config properties: " + resource);
        InputStream inputStream = EnvironmentPropertyLoader.class.getClassLoader().getResourceAsStream(resource);
        if (inputStream == null) {
            throw new IOException("Unable to open stream for resource " + resource);
        } else {
            Properties props = new Properties();
            props.load(inputStream);
            inputStream.close();
            this.properties.putAll(props);
        }
    }

}
