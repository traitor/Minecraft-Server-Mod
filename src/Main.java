import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        // thread the updates.
        Updater updater = new Updater();
        updater.addFileForUpdate("minecraft_server.jar", 
                "http://minecraft.net/download/minecraft_server.jar",
                etc.getInstance().isAutoUpdateMCServer());

        //My mod doesn't work with gui.
        System.out.println("Starting server");
        try {
            net.minecraft.server.MinecraftServer.main(args);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
