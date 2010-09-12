
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Properties {
    public static final Logger log = Logger.getLogger("Minecraft");
    private java.util.Properties props = new java.util.Properties();
    private File file;

    public Properties(String fileName) {
        file = new File(fileName);
        if (file.exists()) {
            FileInputStream stream = null;
            try {
                stream = new FileInputStream(file);
                props.load(stream);
            } catch (Exception localException) {
                log.log(Level.WARNING, "Failed to load " + file, localException);
                generatePropertiesFile();
            } finally {
                try {
                    if (stream != null) {
                        stream.close();
                    }
                } catch (IOException ex) {
                }
            }
        } else {
            log.log(Level.WARNING, fileName + " does not exist");
            generatePropertiesFile();
        }
    }

    public void generatePropertiesFile() {
        log.log(Level.INFO, "Generating new properties file");
        save();
    }

    public void save() {
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
            props.store(stream, "Minecraft server properties");
        } catch (Exception localException) {
            log.log(Level.WARNING, "Failed to save " + file, localException);
            generatePropertiesFile();
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException ex) {
            }
        }
    }

    public String getString(String paramString1, String paramString2) {
        if (!props.containsKey(paramString1)) {
            props.setProperty(paramString1, paramString2);
            save();
        }
        return props.getProperty(paramString1, paramString2);
    }

    public int getInt(String paramString, int paramInt) {
        try {
            return Integer.parseInt(getString(paramString, "" + paramInt));
        } catch (Exception localException) {
            props.setProperty(paramString, "" + paramInt);
        }
        return paramInt;
    }

    public long getLong(String key, long value) {
        try {
            return Long.parseLong(getString(key, "" + value));
        } catch (Exception localException) {
            props.setProperty(key, "" + value);
        }
        return value;
    }

    public boolean getBoolean(String paramString, boolean paramBoolean) {
        try {
            return Boolean.parseBoolean(getString(paramString, "" + paramBoolean));
        } catch (Exception localException) {
            this.props.setProperty(paramString, "" + paramBoolean);
        }
        return paramBoolean;
    }

    public void reload(File paramFile) {
        file = paramFile;
        if (paramFile.exists()) {
            FileInputStream stream = null;
            try {
                stream = new FileInputStream(paramFile);
                props.load(stream);
            } catch (Exception localException) {
                log.log(Level.WARNING, "Failed to load " + paramFile, localException);
                generatePropertiesFile();
            } finally {
                try {
                    if (stream != null) {
                        stream.close();
                    }
                } catch (IOException ex) {
                }
            }
        } else {
            log.log(Level.WARNING, paramFile + " does not exist");
            generatePropertiesFile();
        }
    }
}
