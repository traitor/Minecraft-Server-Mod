
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class eh {

    public static Logger a = Logger.getLogger("Minecraft");
    private ServerSocket d;
    private Thread e;
    public volatile boolean b = false;
    private int f = 0;
    private ArrayList g = new ArrayList();
    private ArrayList h = new ArrayList();
    public MinecraftServer c;

    // hMod: These static methods are here because dx.java is calling them statically... >.>
    static ServerSocket a(eh self) { return self.d; }
    static int b(eh self) { return self.f; }
    static void a(eh self, gi newgh) { ++self.f; self.a(newgh); }

    public eh(MinecraftServer paramMinecraftServer, InetAddress paramInetAddress, int paramInt) {
        this.c = paramMinecraftServer;
        // hMod: Catch me!
        try {
            this.d = new ServerSocket(paramInt, 0, paramInetAddress);
        } catch (IOException ex) {
            Logger.getLogger(eh.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.d.setPerformancePreferences(0, 2, 1);

        this.b = true;
        this.e = new dx(this, "Listen thread", paramMinecraftServer);

        this.e.start();
    }

    public void a(kk paramkj) {
        this.h.add(paramkj);
    }

    private void a(gi paramgh) {
        if (paramgh == null) {
            throw new IllegalArgumentException("Got null pendingconnection!");
        }
        this.g.add(paramgh);
    }

    public void a() {
        Object localObject;
        for (int i = 0; i < this.g.size(); i++) {
            localObject = (gi) this.g.get(i);
            try {
                ((gi) localObject).a();
            } catch (Exception localException1) {
                ((gi) localObject).a("Internal server error");
                a.log(Level.WARNING, "Failed to handle packet: " + localException1, localException1);
            }
            if (((gi) localObject).c) {
                this.g.remove(i--);
            }
        }

        for (int i = 0; i < this.h.size(); i++) {
            localObject = (kk) this.h.get(i);
            try {
                ((kk) localObject).a();
            } catch (Exception localException2) {
                a.log(Level.WARNING, "Failed to handle packet: " + localException2, localException2);
                ((kk) localObject).a("Internal server error");
            }
            if (((kk) localObject).c) {
                this.h.remove(i--);
            }
        }
    }
}
