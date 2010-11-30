import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PropertiesFile.java - Used for accessing and creating .properties files
 * 
 * @author Nijiko
 */
public final class PropertiesFile {

    private static final Logger log = Logger.getLogger("Minecraft");
    private String fileName;
    // Data
    private List<String> lines = new ArrayList<String>();
    private Map<String, String> props = new HashMap<String, String>();

    /**
     * Creates or opens a properties file using specified filename
     * 
     * @param fileName
     */
    public PropertiesFile(String fileName) {
        this.fileName = fileName;
        File file = new File(fileName);

        if (file.exists()) {
            try {
                load();
            } catch (IOException ex) {
                log.severe("[PropertiesFile] Unable to load " + fileName + "!");
            }
        } else {
            save();
        }
    }

    /**
     * Loads, or reloads, the properties file
     * @throws IOException
     */
    public void load() throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(this.fileName));
        String line;

        // Clear the file
        lines.clear();

        while ((line = reader.readLine()) != null) {
            char c = 0;
            int pos = 0;

            // Leading whitespaces must be deleted first.
            while (pos < line.length() && Character.isWhitespace(c = line.charAt(pos))) {
                pos++;
            }

            // If empty line or begins with a comment character, save this line
            // in lineData and save a "" in keyData.
            if ((line.length() - pos) == 0 || line.charAt(pos) == '#' || line.charAt(pos) == '!') {
                lines.add(line);
                continue;
            }

            // The characters up to the next Whitespace, ':', or '=' describe
            // the key. But look for escape sequences.
            // Try to short-circuit when there is no escape char.
            int start = pos;
            boolean needsEscape = line.indexOf('\\', pos) != -1;
            StringBuffer key = needsEscape ? new StringBuffer() : null;

            while (pos < line.length() && !Character.isWhitespace(c = line.charAt(pos++)) && c != '=' && c != ':') {
                if (needsEscape && c == '\\') {
                    if (pos == line.length()) {
                        // The line continues on the next line. If there is no next line, just
                        // treat it as a key with an empty value.
                        line = reader.readLine();
                        if (line == null) {
                            line = "";
                        }
                        pos = 0;
                        while (pos < line.length() && Character.isWhitespace(c = line.charAt(pos))) {
                            pos++;
                        }
                    } else {
                        c = line.charAt(pos++);
                        switch (c) {
                            case 'n':
                                key.append('\n');
                                break;
                            case 't':
                                key.append('\t');
                                break;
                            case 'r':
                                key.append('\r');
                                break;
                            case 'u':
                                if (pos + 4 <= line.length()) {
                                    char uni = (char) Integer.parseInt(line.substring(pos, pos + 4), 16);
                                    key.append(uni);
                                    pos += 4;
                                }

                                break;
                            default:
                                key.append(c);
                                break;
                        }
                    }
                } else if (needsEscape) {
                    key.append(c);
                }
            }

            boolean isDelim = (c == ':' || c == '=');
            String keyString;

            if (needsEscape) {
                keyString = key.toString();
            } else if (isDelim || Character.isWhitespace(c)) {
                keyString = line.substring(start, pos - 1);
            } else {
                keyString = line.substring(start, pos);
            }

            while (pos < line.length() && Character.isWhitespace(c = line.charAt(pos))) {
                pos++;
            }

            if (!isDelim && (c == ':' || c == '=')) {
                pos++;

                while (pos < line.length() && Character.isWhitespace(c = line.charAt(pos))) {
                    pos++;
                }
            }

            // Short-circuit if no escape chars found.
            if (!needsEscape) {
                lines.add(line);
                continue;
            }

            // Escape char found so iterate through the rest of the line.
            StringBuilder element = new StringBuilder(line.length() - pos);
            while (pos < line.length()) {
                c = line.charAt(pos++);
                if (c == '\\') {
                    if (pos == line.length()) {
                        // The line continues on the next line.
                        line = reader.readLine();

                        // We might have seen a backslash at the end of the file. The JDK
                        // ignores the backslash in this case, so we follow for compatibility.
                        if (line == null) {
                            break;
                        }

                        pos = 0;
                        while (pos < line.length() && Character.isWhitespace(c = line.charAt(pos))) {
                            pos++;
                        }
                        element.ensureCapacity(line.length() - pos + element.length());
                    } else {
                        c = line.charAt(pos++);
                        switch (c) {
                            case 'n':
                                element.append('\n');
                                break;
                            case 't':
                                element.append('\t');
                                break;
                            case 'r':
                                element.append('\r');
                                break;
                            case 'u':
                                if (pos + 4 <= line.length()) {
                                    char uni = (char) Integer.parseInt(line.substring(pos, pos + 4), 16);
                                    element.append(uni);
                                    pos += 4;
                                }
                                break;
                            default:
                                element.append(c);
                                break;
                        }
                    }
                } else {
                    element.append(c);
                }
            }
            lines.add(keyString + "=" + element.toString());
        }

        reader.close();
    }

    /**
     * Saves the properties file
     */
    public void save() {
        OutputStream os = null;

        try {
            os = new FileOutputStream(this.fileName);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PropertiesFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        PrintStream ps = new PrintStream(os);

        // Keep track of properties that were set
        List<String> usedProps = new ArrayList<String>();

        for (String line : this.lines) {
            if (line.trim().length() == 0) {
                ps.println(line);
                continue;
            }

            if (line.charAt(0) == '#') {
                ps.println(line);
                continue;
            }

            if (line.contains("=")) {
                int delimPosition = line.indexOf('=');
                String key = line.substring(0, delimPosition).trim();

                if (this.props.containsKey(key)) {
                    String value = this.props.get(key);
                    ps.println(key + "=" + value);
                    usedProps.add(key);
                } else {
                    ps.println(line);
                }
            } else {
                ps.println(line);
            }
        }

        // Add any new properties
        for (Map.Entry<String, String> entry : this.props.entrySet()) {
            if (!usedProps.contains(entry.getKey())) {
                ps.println(entry.getKey() + "=" + entry.getValue());
            }
        }

        // Exit that stream
        ps.close();

        // Reload
        try {
            lines.clear();
            this.load();
        } catch (IOException ex) {
            log.severe("[propertiesFile] Unable to load " + fileName + "!");
        }
    }

    /**
     * Returns a map with all key/value pairs
     * 
     * @return
     * @throws Exception
     */
    public Map<String, String> returnMap() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        BufferedReader reader = new BufferedReader(new FileReader(this.fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.trim().length() == 0) {
                continue;
            }
            if (line.charAt(0) == '#') {
                continue;
            }
            if (line.contains("=")) {
                int delimPosition = line.indexOf('=');
                String key = line.substring(0, delimPosition).trim();
                String value = line.substring(delimPosition + 1).trim();
                map.put(key, value);
            } else {
                continue;
            }
        }
        reader.close();
        return map;
    }

    /**
     * Checks to see if this key exists
     * 
     * @param var the key to check
     * @return true if key exists
     */
    public boolean containsKey(String var) {
        for (String line : this.lines) {
            if (line.trim().length() == 0) {
                continue;
            }

            if (line.charAt(0) == '#') {
                continue;
            }

            if (line.contains("=")) {
                int delimPosition = line.indexOf('=');

                String key = line.substring(0, delimPosition);

                if (key.equals(var)) {
                    return true;
                }
            } else {
                continue;
            }
        }

        return false;
    }

    /**
     * Grabs the value from a key
     * 
     * @param var property to retreive
     * @return string if key exists
     */
    public String getProperty(String var) {
        for (String line : this.lines) {
            if (line.trim().length() == 0) {
                continue;
            }
            if (line.charAt(0) == '#') {
                continue;
            }

            if (line.contains("=")) {
                int delimPosition = line.indexOf('=');

                String key = line.substring(0, delimPosition).trim();
                String value = line.substring(delimPosition + 1);

                if (key.equals(var)) {
                    return value;
                }
            } else {
                continue;
            }
        }

        return "";
    }

    /**
     * Remove a key from the file
     *
     * @param var
     *            the key to remove
     */
    public void removeKey(String var) {
        Boolean changed = false;

        if(this.props.containsKey(var)) {
            this.props.remove(var);
            changed = true;
        }

        for (int i = 0; i < this.lines.size(); i++) {
            String line = this.lines.get(i);

            if (line.trim().length() == 0) {
                continue;
            }

            if (line.charAt(0) == '#') {
                continue;
            }

            if (line.contains("=")) {
                int delimPosition = line.indexOf('=');
                String key = line.substring(0, delimPosition).trim();

                if (key.equals(var)) {
                    this.lines.remove(i);
                    changed = true;
                }
            } else {
                continue;
            }
        }

        // Save on change
        if(changed)
            save();
    }

    /**
     * Checks to see if this key exists
     * 
     * @param key
     *            the key to check
     * @return true if key exists
     */
    public boolean keyExists(String key) {
        try {
            return (this.containsKey(key)) ? true : false;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Returns the string value of the key
     * 
     * @param key
     *            key to retrieve value from
     * @return value
     */
    public String getString(String key) {
        if (this.containsKey(key)) {
            return this.getProperty(key);
        }

        return "";
    }

    /**
     * Returns the string value of a key
     * 
     * @param key
     *            the key to use
     * @param value
     *            the default value
     * @return
     */
    public String getString(String key, String value) {
        if (this.containsKey(key)) {
            return this.getProperty(key);
        }

        setString(key, value);
        return value;
    }

    /**
     * Sets the key
     * 
     * @param key
     * @param value
     */
    public void setString(String key, String value) {
        props.put(key, value);
        save();
    }

    /**
     * Returns the int value of the key
     * 
     * @param key
     *            key to retrieve value from
     * @return value
     */
    public int getInt(String key) {
        if (this.containsKey(key)) {
            return Integer.parseInt(this.getProperty(key));
        }

        return 0;
    }

    /**
     * Returns the int value of a key
     * 
     * @param key
     * @param value
     * @return
     */
    public int getInt(String key, int value) {
        if (this.containsKey(key)) {
            return Integer.parseInt(this.getProperty(key));
        }

        setInt(key, value);
        return value;

    }

    /**
     * Sets the key
     * 
     * @param key
     * @param value
     */
    public void setInt(String key, int value) {
        props.put(key, String.valueOf(value));
        save();
    }

    /**
     * Returns the double value of the key
     * 
     * @param key
     *            key to retrieve value from
     * @return value
     */
    public double getDouble(String key) {
        if (this.containsKey(key)) {
            return Double.parseDouble(this.getProperty(key));
        }

        return 0;
    }

    /**
     * Returns the double value of a key
     * 
     * @param key
     * @param value
     * @return
     */
    public double getDouble(String key, double value) {
        if (this.containsKey(key)) {
            return Double.parseDouble(this.getProperty(key));
        }

        setDouble(key, value);
        return value;
    }

    /**
     * Sets a key
     * 
     * @param key
     * @param value
     */
    public void setDouble(String key, double value) {
        props.put(key, String.valueOf(value));
        save();
    }

    /**
     * Returns the long value of the key
     * 
     * @param key
     *            key to retrieve value from
     * @return value
     */
    public long getLong(String key) {
        if (this.containsKey(key)) {
            return Long.parseLong(this.getProperty(key));
        }

        return 0;
    }

    /**
     * Returns the long value of a key
     * 
     * @param key
     * @param value
     * @return
     */
    public long getLong(String key, long value) {
        if (this.containsKey(key)) {
            return Long.parseLong(this.getProperty(key));
        }

        setLong(key, value);
        return value;
    }

    /**
     * Sets a key
     * 
     * @param key
     * @param value
     */
    public void setLong(String key, long value) {
        props.put(key, String.valueOf(value));
        save();
    }

    /**
     * Returns the boolean value of the key
     * 
     * @param key
     *            key to retrieve value from
     * @return value
     */
    public boolean getBoolean(String key) {
        if (this.containsKey(key)) {
            return Boolean.parseBoolean(this.getProperty(key));
        }

        return false;
    }

    /**
     * Returns the boolean value of a key
     * 
     * @param key
     * @param value
     * @return
     */
    public boolean getBoolean(String key, boolean value) {
        if (this.containsKey(key)) {
            return Boolean.parseBoolean(this.getProperty(key));
        }

        setBoolean(key, value);
        return value;
    }

    /**
     * Sets a key
     * 
     * @param key
     * @param value
     */
    public void setBoolean(String key, boolean value) {
        props.put(key, String.valueOf(value));
        save();
    }
}
