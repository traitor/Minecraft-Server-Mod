import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

public class Main {

    public static final long   minecraft_server                   = 280458825L;

    public static final long   minecraft_servero                  = 2976914608L;

    public static final long   minecraft_servero_runecraft        = 2581072959L;
    public static final long   minecraft_servero_runecraft_winrar = 3296853903L;

    public static final long[] minecraft_servero_list             = new long[] { minecraft_servero, minecraft_servero_runecraft, minecraft_servero_runecraft_winrar };

    public static final long   mysql                              = 3001390372L;
    public static final long   jarjar                             = 233379283L;
    public static final long   rules                              = 2575805698L;

    public static final Logger log                                = Logger.getLogger("Minecraft");

    public static void main(String[] args) throws IOException {

        if (!fileExists("minecraft_servero.jar")) {
            if (!fileExists("jarjar.jar") || !fileExists("rules.rules")) {
                log("-----------------------------");
                log("jarjar.jar and/or rules.rules are missing!");
                log("-----------------------------");
                System.exit(0);
            }
            checkCRC32("jarjar.jar", jarjar);
            checkCRC32("rules.rules", rules);

            if (!fileExists("minecraft_server.jar")) {
                log("Missing minecraft_servero.jar, Downloading minecraft_server.jar...");
                downloadFile("http://minecraft.net/download/minecraft_server.jar", "minecraft_server.jar");
                checkCRC32("minecraft_server.jar", minecraft_server);

                log("Finished downloading minecraft_server.jar, start converting minecraft_server.jar to minecraft_servero.jar...");
            } else
                log("Missing minecraft_servero.jar, start converting minecraft_server.jar to minecraft_servero.jar...");

            try {
                com.tonicsystems.jarjar.Main.main(new String[] { "process", "rules.rules", "minecraft_server.jar", "minecraft_servero.jar" });
            } catch (Throwable t) {
                log.log(Level.SEVERE, null, t);
            }
            checkCRC32("minecraft_servero.jar", minecraft_servero_list);

            log("Finished converting minecraft_server.jar, Starting minecraft server...");
            dynamicLoadJar("minecraft_servero.jar");
        } else
            checkCRC32("minecraft_servero.jar", minecraft_servero_list);

        if (etc.getInstance().getDataSourceType().equalsIgnoreCase("mysql"))
            checkCRC32("mysql-connector-java-bin.jar", mysql);

        if (checkForUpdate())
            System.out.println("Update found.");
        // derp.

        // My mod doesn't work with gui.
        try {
            net.minecraft.server.MinecraftServer.main(new String[] { "nogui" });
        } catch (Throwable t) {
            log.log(Level.SEVERE, null, t);
        }
        new DeadLockDetector();
    }

    public static boolean fileExists(String filename) {
        return new File(filename).exists();
    }

    public static void checkCRC32(String fileName, long[] crcs) throws IOException {
        if (etc.getInstance().getTainted())
            return;

        long checksum = getCRC32(fileName);
        for (long i : crcs)
            if (i == checksum)
                return;
        log("-----------------------------");
        log(fileName + " does not match checksum!");
        log("if you still want to run the server, delete version.txt to run the server in tainted mode.");
        log("This means some of your files are either corrupted,outdated or to new(minecraft got updated?).");
        log("-----------------------------");
        System.exit(0);

    }

    public static void checkCRC32(String fileName, long crc) throws IOException {
        if (etc.getInstance().getTainted())
            return;

        long checksum = getCRC32(fileName);
        if (checksum != crc) {
            log("-----------------------------");
            log(fileName + " does not match checksum! Checksum found: " + checksum + ", required checksum: " + crc + ".");
            log("This means some of your files are either corrupted,outdated or to new(minecraft got updated?).");
            log("If you still want to run the server, delete version.txt to run the server in tainted mode.");
            log("-----------------------------");
            System.exit(0);
        }
    }

    public static long getCRC32(String fileName) throws IOException {

        FileInputStream stream = new FileInputStream(fileName);
        CheckedInputStream cis = new CheckedInputStream(stream, new CRC32());
        byte[] buf = new byte[128];
        while (cis.read(buf) >= 0) {
        }

        long rt = cis.getChecksum().getValue();
        stream.close();
        cis.close();

        return rt;
    }

    public static void printCRC() throws IOException {
        log("Minecraft_server CRC32: \t" + getCRC32("minecraft_server.jar"));
        log("Minecraft_servero CRC32: \t" + getCRC32("minecraft_servero.jar"));
        log("Jarjar CRC32: \t\t\t" + getCRC32("jarjar.jar"));
        log("Rules CRC32: \t\t\t" + getCRC32("rules.rules"));
        log("Mysql CRC32: \t\t\t" + getCRC32("mysql-connector-java-bin.jar"));
    }

    public static void downloadFile(String website, String fileLocation) throws IOException {
        URL url = new URL(website);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(fileLocation);
        fos.getChannel().transferFrom(rbc, 0, 1 << 24);
    }

    public static void log(String str) {
        System.out.println(str);
    }

    public static void dynamicLoadJar(String fileName) throws IOException {
        URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class<?> sysclass = URLClassLoader.class;
        try {
            Method method = sysclass.getDeclaredMethod("addURL", new Class[] { URL.class });
            method.setAccessible(true);
            method.invoke(sysloader, new Object[] { (new File(fileName)).toURI().toURL() });
        } catch (Throwable t) {
            t.printStackTrace();
            throw new IOException("Error, could not add URL to system classloader");
        }
    }

    public static boolean checkForUpdate() {
        return false;
    }
}
