import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import net.minecraft.server.MinecraftServer;

public class OThreadCommandReader extends Thread {
    public OThreadCommandReader(MinecraftServer paramMinecraftServer) {
    }

    @Override
    public void run() {
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        try {
            while ((!a.g) && (MinecraftServer.a(a)) && ((str = localBufferedReader.readLine()) != null))
                a.a(str, a);
        } catch (IOException localIOException) {
            localIOException.printStackTrace();
        }
    }
}