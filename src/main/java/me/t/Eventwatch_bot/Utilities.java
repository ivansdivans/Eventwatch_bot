package me.t.Eventwatch_bot;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Utilities {

    private Utilities() {}

    public static String getSecretProperty(String propertyName) {
        Properties properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try (InputStream stream = loader.getResourceAsStream("secret.properties")) {
            properties.load(stream);
            return properties.getProperty(propertyName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Getting secret property failed with exception: " + e);
            return null;
        }
    }
}
