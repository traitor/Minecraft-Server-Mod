
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class PropertiesFile {

    private static final Logger log = Logger.getLogger("Minecraft");
    private Properties properties;
    private String fileName;

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

    public void load() {
        try {
            properties.load(new FileInputStream(fileName));
        } catch (IOException ex) {
            log.log(Level.SEVERE, "Unable to load " + fileName, ex);
        }
    }

    public void save() {
        try {
            properties.store(new FileOutputStream(fileName), "Minecraft Properties File");
        } catch (IOException ex) {
            log.log(Level.SEVERE, "Unable to save " + fileName, ex);
        }
    }

    public String getString(String key, String value) {
        if (properties.containsKey(key)) {
            return properties.getProperty(key);
        }
        setString(key, value);
        return value;
    }

    public void setString(String key, String value) {
        properties.setProperty(key, value);
        save();
    }

    public int getInt(String key, int value) {
        if (properties.containsKey(key)) {
            return Integer.parseInt(properties.getProperty(key));
        }
        setInt(key, value);
        return value;
    }

    public void setInt(String key, int value) {
        properties.setProperty(key, String.valueOf(value));
        save();
    }

    public long getLong(String key, long value) {
        if (properties.containsKey(key)) {
            return Long.parseLong(properties.getProperty(key));
        }
        setLong(key, value);
        return value;
    }

    public void setLong(String key, long value) {
        properties.setProperty(key, String.valueOf(value));
        save();
    }

    public boolean getBoolean(String key, boolean value) {
        if (properties.containsKey(key)) {
            return Boolean.parseBoolean(properties.getProperty(key));
        }
        setBoolean(key, value);
        return value;
    }

    public void setBoolean(String key, boolean value) {
        properties.setProperty(key, String.valueOf(value));
        save();
    }
}
