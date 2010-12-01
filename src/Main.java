import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Main {

    public static void main(String[] argsin) throws IOException {
        if (!new File("minecraft_server.jar").exists()) {
            System.out.println("minecraft_server.jar not found, downloading...");

            URL url = new URL("http://minecraft.net/download/minecraft_server.jar");
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream("minecraft_server.jar");
            fos.getChannel().transferFrom(rbc, 0, 1 << 24);

            System.out.println("Finished downloading, starting server");
        }

        try {
            if (!etc.MINECRAFT_SERVER_SHA1.equals("debug-ignore-sha1") && !generateSHA1HexFromFile(new File("minecraft_server.jar")).equals(etc.MINECRAFT_SERVER_SHA1)) {
                System.out.println("You appear to be trying to run a version of hMod that is incompatible with the version of Minecraft server. Please head to http://forum.hey0.net and get a new version!");
                System.err.println("Developer note: expected: " + etc.MINECRAFT_SERVER_SHA1 + ". Got: " + generateSHA1HexFromFile(new File("minecraft_server.jar"))); // yes, this is inefficient
                System.exit(9);
            }
        } catch (Exception e) {
            System.out.println("An exception occurred whilst trying to verify the Minecraft server version. Stack trace:");
            e.printStackTrace();
            System.out.println("Continuing anyway.");
        }

        if (checkForUpdate()) {
            System.out.println("Update found.");
            // derp. - reminder: updater needs to check for new hMod version as well as new server version, so we don't have a self-bricking updater!
        }

        // My mod doesn't work with GUI.
        // So enforce nogui.
        List<String> argsList = new ArrayList<String>();
        Collections.addAll(argsList, argsin);
        argsList.add(0, "nogui");
        String[] args = argsList.toArray(argsin);

        try {
            net.minecraft.server.MinecraftServer.main(args);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static boolean checkForUpdate() {
        return false;
    }

    public static String generateSHA1HexFromFile(File fileToSum) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
        MessageDigest sha1md = MessageDigest.getInstance("SHA1"); // get the SHA1 MessageDigester
        FileInputStream sha1fis = new FileInputStream(fileToSum); // get the FIS for the file...
        byte[] tmpBytes = new byte[1024]; // a temporary storage array
        int actualBytesRead = 0; // int for storing the number of bytes we actually read...

        do { // and start!
             actualBytesRead = sha1fis.read(tmpBytes); // read into array, and check how many we actually read...
             if (actualBytesRead != -1)
                 sha1md.update(tmpBytes, 0, actualBytesRead); // and read into the digestor
        } while (actualBytesRead != -1); // actualBytesRead = -1 when EOF
        byte[] shaDigest = sha1md.digest();

        StringBuffer shaDigestHex = new StringBuffer(); // temporary holding area...
        for (int i = 0; i < shaDigest.length; i++) // now make it
            shaDigestHex.append(Integer.toString((shaDigest[i] & 0xff) + 0x100, 16).substring(1)); // random maths...
        return shaDigestHex.toString(); // and out!
    }
}
