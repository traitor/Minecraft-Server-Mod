import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class ds {
    public static Logger a = Logger.getLogger("Minecraft");
    private ServerSocket d;
    private Thread e;
    public volatile boolean b = false;
    private int f = 0;

    private ArrayList<fs> g = new ArrayList();
    private ArrayList<ji> h = new ArrayList();
    public MinecraftServer c;

    // hMod: Something is calling this staticly ! >.<
    static ServerSocket a(ds self) { return self.d; }
    static int b(ds self) { return self.f; }
    static void a(ds self, fs newfs) { ++self.f; self.a(newfs); }

    public ds(MinecraftServer paramMinecraftServer, InetAddress paramInetAddress, int paramInt) {
        this.c = paramMinecraftServer;
        // hMod: Catch me!
        try {
            this.d = new ServerSocket(paramInt, 0, paramInetAddress);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        this.d.setPerformancePreferences(0, 2, 1);

        this.b = true;
        this.e = new dj(this, "Listen thread", paramMinecraftServer);

        this.e.start();
    }

    public void a(ji paramji) {
        this.h.add(paramji);
    }

    private void a(fs paramfs) {
        if (paramfs == null) {
            throw new IllegalArgumentException("Got null pendingconnection!");
        }
        this.g.add(paramfs);
    }

    public void a() {
        for (int i = 0; i < this.g.size(); i++) {
            fs localObject = this.g.get(i);
            try {
                ((fs) localObject).a();
            } catch (Exception localException1) {
                a.log(Level.WARNING, "Failed to handle packet: " + localException1, localException1);
                // hMod: handle exception, removing the object
                try {
                    ((fs) localObject).b("Internal server error");
                } catch (Exception e) {
                    a.log(Level.WARNING, "Exception while handling internal server error", e);
                    localObject.c = true; // let the code remove it
                }
            }
            if (((fs) localObject).c) {
                this.g.remove(i--);
            }
        }

        for (int i = 0; i < this.h.size(); i++) {
            ji localObject = (ji) this.h.get(i);
            try {
                ((ji) localObject).a();
            } catch (Exception localException2) {
                a.log(Level.WARNING, "Failed to handle packet: " + localException2, localException2);
                // hMod: handle exception, removing the object
                try {
                    ((ji) localObject).c("Internal server error");
                } catch (Exception e) {
                    a.log(Level.WARNING, "Exception while handling internal server error", e);
                    localObject.c = true; // let the code remove it
                }
            }
            if (((ji) localObject).c) {
                this.h.remove(i--);
            }
        }
    }
}
