
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PropertiesFile.java - Used for accessing and creating .properties files
 * @author James
 */
public final class PropertiesFile {

    private static final Logger log = Logger.getLogger("Minecraft");
    private Properties properties;
    private String fileName;

    /**
     * Creates or opens a properties file using specified filename
     * @param fileName
     */
    public PropertiesFile(String fileName) {
        this.fileName = fileName;
        properties = new Properties();
        File file = new File(fileName);

        if (file.exists()) {
            load();
        } else {
            save();
        }
    }

    /**
     * Loads, or reloads, the properties file
     */
    public void load() {
        try {
            properties.load(new FileInputStream(fileName));
        } catch (IOException ex) {
            log.log(Level.SEVERE, "Unable to load " + fileName, ex);
        }
    }

    /**
     * Saves the properties file
     */
    public void save() {
        try {
            properties.store(new FileOutputStream(fileName), "Minecraft Properties File");
        } catch (IOException ex) {
            log.log(Level.SEVERE, "Unable to save " + fileName, ex);
        }
    }

    /**
     * Checks to see if this key exists
     * @param key the key to check
     * @return true if key exists
     */
    public boolean keyExists(String key) {
        return this.properties.containsKey(key);
    }

    /**
     * Returns the string value of the key
     * @param key key to retrieve value from
     * @return value
     */
    public String getString(String key) {
        return this.properties.getProperty(key);
    }

    /**
     * Returns the string value of a key
     * @param key the key to use
     * @param value the default value
     * @return
     */
    public String getString(String key, String value) {
        if (properties.containsKey(key)) {
            return properties.getProperty(key);
        }
        setString(key, value);
        return value;
    }

    /**
     * Sets the key
     * @param key
     * @param value
     */
    public void setString(String key, String value) {
        properties.setProperty(key, value);
        save();
    }

    /**
     * Returns the int value of the key
     * @param key key to retrieve value from
     * @return value
     */
    public int getInt(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    /**
     * Returns the int value of a key
     * @param key
     * @param value
     * @return
     */
    public int getInt(String key, int value) {
        if (properties.containsKey(key)) {
            return Integer.parseInt(properties.getProperty(key));
        }
        setInt(key, value);
        return value;
    }

    /**
     * Sets the key
     * @param key
     * @param value
     */
    public void setInt(String key, int value) {
        properties.setProperty(key, String.valueOf(value));
        save();
    }

    /**
     * Returns the long value of the key
     * @param key key to retrieve value from
     * @return value
     */
    public long getLong(String key) {
        return Long.parseLong(properties.getProperty(key));
    }

    /**
     * Returns the long value of a key
     * @param key
     * @param value
     * @return
     */
    public long getLong(String key, long value) {
        if (properties.containsKey(key)) {
            return Long.parseLong(properties.getProperty(key));
        }
        setLong(key, value);
        return value;
    }

    /**
     * Sets a key
     * @param key
     * @param value
     */
    public void setLong(String key, long value) {
        properties.setProperty(key, String.valueOf(value));
        save();
    }

    /**
     * Returns the boolean value of the key
     * @param key key to retrieve value from
     * @return value
     */
    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(properties.getProperty(key));
    }

    /**
     * Returns the boolean value of a key
     * @param key
     * @param value
     * @return
     */
    public boolean getBoolean(String key, boolean value) {
        if (properties.containsKey(key)) {
            return Boolean.parseBoolean(properties.getProperty(key));
        }
        setBoolean(key, value);
        return value;
    }

    /**
     * Sets a key
     * @param key
     * @param value
     */
    public void setBoolean(String key, boolean value) {
        properties.setProperty(key, String.valueOf(value));
        save();
    }
}
