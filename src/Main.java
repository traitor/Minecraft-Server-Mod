
import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        Updater updater = new Updater();
        updater.addFileToUpdate("minecraft_server.jar", 
                "http://minecraft.net/download/minecraft_server.jar",
                false);
        /*
        if (!new File("minecraft_server.jar").exists()) {
            System.out.println("minecraft_server.jar not found, downloading...");

            URL url = new URL("http://minecraft.net/download/minecraft_server.jar");
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream("minecraft_server.jar");
            fos.getChannel().transferFrom(rbc, 0, 1 << 24);

            System.out.println("Finished downloading, starting server");
        }

        if (checkForUpdate()) {
            System.out.println("Update found.");
            // derp.
        }
         */
        System.out.println("Starting server"); 
        // My mod doesn't work with gui.
        try {
            net.minecraft.server.MinecraftServer.main(args);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static boolean checkForUpdate() {
        return false;
    }
}
