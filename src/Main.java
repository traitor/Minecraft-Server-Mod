import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws IOException {
        if (!new File("minecraft_servero.jar").exists()) {
            System.out.println("minecraft_servero.jar not found please download the proper modified jar!");
            //System.out.println("minecraft_server.jar not found, downloading...");

            //URL url = new URL("http://minecraft.net/download/minecraft_server.jar");
            //ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            //FileOutputStream fos = new FileOutputStream("minecraft_server.jar");
            //fos.getChannel().transferFrom(rbc, 0, 1 << 24);

            //System.out.println("Finished downloading, starting server");
        }

        if (checkForUpdate()) {
            System.out.println("Update found.");
            // derp.
        }

        // My mod doesn't work with gui.
        try {
            net.minecraft.server.MinecraftServer.main(new String[] {"nogui"});
        } catch (Throwable t) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, t);
        }
        new DeadLockDetector();
    }

    public static boolean checkForUpdate() {
        return false;
    }
}
