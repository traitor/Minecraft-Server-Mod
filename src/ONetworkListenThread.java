import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.server.MinecraftServer;

public class ONetworkListenThread {

    public static Logger                 a = Logger.getLogger("Minecraft");
    private ServerSocket                 d;
    private Thread                       e;
    public volatile boolean              b = false;
    private int                          f = 0;
    private ArrayList<ONetLoginHandler>  g = new ArrayList<ONetLoginHandler>();
    private ArrayList<ONetServerHandler> h = new ArrayList<ONetServerHandler>();
    public MinecraftServer               c;

    // hMod: These static methods are here because dx.java is calling them
    // statically... >.>
    static ServerSocket a(ONetworkListenThread self) {
        return self.d;
    }

    static int b(ONetworkListenThread self) {
        return self.f;
    }

    static void a(ONetworkListenThread self, ONetLoginHandler newhc) {
        ++self.f;
        self.a(newhc);
    }

    public ONetworkListenThread(MinecraftServer paramMinecraftServer, InetAddress paramInetAddress, int paramInt) {
        c = paramMinecraftServer;
        try {
            d = new ServerSocket(paramInt, 0, paramInetAddress);
        } catch (IOException ex) {
            Logger.getLogger(ONetworkListenThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        d.setPerformancePreferences(0, 2, 1);

        b = true;
        e = new ONetworkAcceptThread(this, "Listen thread", paramMinecraftServer);

        e.start();
    }

    public void a(ONetServerHandler paramONetServerHandler) {
        h.add(paramONetServerHandler);
    }

    private void a(ONetLoginHandler paramONetLoginHandler) {
        if (paramONetLoginHandler == null)
            throw new IllegalArgumentException("Got null pendingconnection!");
        g.add(paramONetLoginHandler);
    }

    public void a() {
        ONetLoginHandler loginHandler;
        for (int i = 0; i < g.size(); i++) {
            loginHandler = g.get(i);
            try {
                loginHandler.a();
            } catch (Exception localException1) {
                loginHandler.a("Internal server error");
                a.log(Level.WARNING, "Failed to handle packet: " + localException1, localException1);
            }
            if (loginHandler.c)
                g.remove(i--);
        }
        ONetServerHandler handler;
        for (int i = 0; i < h.size(); i++) {
            handler = h.get(i);
            try {
                handler.a();
            } catch (Exception localException2) {
                a.log(Level.WARNING, "Failed to handle packet: " + localException2, localException2);
                handler.a("Internal server error");
            }
            if (handler.c)
                h.remove(i--);
        }
    }
}
