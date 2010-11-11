
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import net.minecraft.server.MinecraftServer;

public class by extends Thread {

    private MinecraftServer server;

    public by(MinecraftServer paramMinecraftServer) {
        this.server = paramMinecraftServer;
    }

    public void run() {
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        try {
            while (!server.g && (str = localBufferedReader.readLine()) != null) {
                if (!etc.getInstance().parseConsoleCommand(str, server)) {
                    server.a(str, server);
                }
            }
        } catch (IOException localIOException) {
            localIOException.printStackTrace();
        }
    }
}
