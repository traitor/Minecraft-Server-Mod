
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class cq {

    public static final Logger a = Logger.getLogger("Minecraft");
    private Properties b = new Properties();
    private File c;

    public cq(File paramFile) {
        this.c = paramFile;
        if (paramFile.exists()) {
            FileInputStream stream = null;
            try {
                stream = new FileInputStream(paramFile);
                this.b.load(stream);
            } catch (Exception localException) {
                a.log(Level.WARNING, "Failed to load " + paramFile, localException);
                a();
            } finally {
                try {
                    if (stream != null) {
                        stream.close();
                    }
                } catch (IOException ex) {
                }
            }
        } else {
            a.log(Level.WARNING, paramFile + " does not exist");
            a();
        }
    }

    public void a() {
        a.log(Level.INFO, "Generating new properties file");
        b();
    }

    public void b() {
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(this.c);
            this.b.store(stream, "Minecraft server properties");
        } catch (Exception localException) {
            a.log(Level.WARNING, "Failed to save " + this.c, localException);
            a();
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException ex) {
            }
        }
    }

    public String a(String paramString1, String paramString2) {
        if (!this.b.containsKey(paramString1)) {
            this.b.setProperty(paramString1, paramString2);
            b();
        }
        return this.b.getProperty(paramString1, paramString2);
    }

    public String getString(String key, String value) {
        return a(key, value);
    }

    public int a(String paramString, int paramInt) {
        try {
            return Integer.parseInt(a(paramString, "" + paramInt));
        } catch (Exception localException) {
            this.b.setProperty(paramString, "" + paramInt);
        }
        return paramInt;
    }

    public int getInt(String key, int value) {
        return a(key, value);
    }

    public long getLong(String key, long value) {
        try {
            return Long.parseLong(a(key, "" + value));
        } catch (Exception localException) {
            this.b.setProperty(key, "" + value);
        }
        return value;
    }

    public boolean a(String paramString, boolean paramBoolean) {
        try {
            return Boolean.parseBoolean(a(paramString, "" + paramBoolean));
        } catch (Exception localException) {
            this.b.setProperty(paramString, "" + paramBoolean);
        }
        return paramBoolean;
    }

    public boolean getBoolean(String key, boolean value) {
        return a(key, value);
    }

    public void reload(File paramFile) {
        this.c = paramFile;
        if (paramFile.exists()) {
            FileInputStream stream = null;
            try {
                stream = new FileInputStream(paramFile);
                this.b.load(stream);
            } catch (Exception localException) {
                a.log(Level.WARNING, "Failed to load " + paramFile, localException);
                a();
            } finally {
                try {
                    if (stream != null) {
                        stream.close();
                    }
                } catch (IOException ex) {
                }
            }
        } else {
            a.log(Level.WARNING, paramFile + " does not exist");
            a();
        }
    }
}
