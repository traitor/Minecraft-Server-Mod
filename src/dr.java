import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class dr {
    public static Logger a = Logger.getLogger("Minecraft");
    private ServerSocket d;
    private Thread e;
    public volatile boolean b = false;
    private int f = 0;

    private ArrayList<fr> g = new ArrayList<fr>();
    private ArrayList<jh> h = new ArrayList<jh>();
    public MinecraftServer c;

    // hMod: Something is calling this staticly ! >.<
    static ServerSocket a(dr self) { return self.d; }
    static int b(dr self) { return self.f; }
    static void a(dr self, fr newfr) { ++self.f; self.a(newfr); }

    public dr(MinecraftServer paramMinecraftServer, InetAddress paramInetAddress, int paramInt) {
        c = paramMinecraftServer;
        // hMod: Catch me!
        try {
            d = new ServerSocket(paramInt, 0, paramInetAddress);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        this.d.setPerformancePreferences(0, 2, 1);

        this.b = true;
        this.e = new di(this, "Listen thread", paramMinecraftServer);

        this.e.start();
    }

    public void a(jh paramjh) {
        this.h.add(paramjh);
    }

    private void a(fr paramfr) {
        if (paramfr == null) {
            throw new IllegalArgumentException("Got null pendingconnection!");
        }
        this.g.add(paramfr);
    }

    public void a() {
        for (int i = 0; i < this.g.size(); i++) {
            fr localObject = this.g.get(i);
            try {
                localObject.a();
            } catch (Exception localException1) {
                a.log(Level.WARNING, "Failed to handle packet: " + localException1, localException1);
                // hMod: handle exception, removing the object
                try {
                    localObject.b("Internal server error");
                } catch (Exception e) {
                    a.log(Level.WARNING, "Exception while handling internal server error", e);
                    localObject.c = true; // let the code remove it
                }
            }
            if (localObject.c) {
                this.g.remove(i--);
            }
        }

        for (int i = 0; i < this.h.size(); i++) {
            jh localObject = this.h.get(i);
            try {
                localObject.a();
            } catch (Exception localException2) {
                a.log(Level.WARNING, "Failed to handle packet: " + localException2, localException2);
                // hMod: handle exception, removing the object
                try {
                    localObject.c("Internal server error");
                } catch (Exception e) {
                    a.log(Level.WARNING, "Exception while handling internal server error", e);
                    localObject.c = true; // let the code remove it
                }
            }
            if (localObject.c) {
                this.h.remove(i--);
            }
        }
    }
}
