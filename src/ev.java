import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class ev
{
  public static Logger a = Logger.getLogger("Minecraft");
  private ServerSocket d;
  private Thread e;
  public volatile boolean b = false;
  private int f = 0;

  private ArrayList g = new ArrayList();
  private ArrayList h = new ArrayList();
  public MinecraftServer c;

  // hMod: These static methods are here because dx.java is calling them statically... >.>
    static ServerSocket a(ev self) { return self.d; }
    static int b(ev self) { return self.f; }
    static void a(ev self, hc newgi) { ++self.f; self.a(newgi); }

  public ev(MinecraftServer paramMinecraftServer, InetAddress paramInetAddress, int paramInt)
  {
    c = paramMinecraftServer;
        try {
            d = new ServerSocket(paramInt, 0, paramInetAddress);
        } catch (IOException ex) {
            Logger.getLogger(ev.class.getName()).log(Level.SEVERE, null, ex);
        }
    d.setPerformancePreferences(0, 2, 1);

    b = true;
    e = new ek(this, "Listen thread", paramMinecraftServer);

    e.start();
  }

  public void a(lp paramlp)
  {
    h.add(paramlp);
  }

  private void a(hc paramhc) {
    if (paramhc == null) {
      throw new IllegalArgumentException("Got null pendingconnection!");
    }
    g.add(paramhc);
  }

  public void a()
  {
    Object localObject;
    for (int i = 0; i < g.size(); i++) {
      localObject = (hc)g.get(i);
      try {
        ((hc)localObject).a();
      } catch (Exception localException1) {
        ((hc)localObject).a("Internal server error");
        a.log(Level.WARNING, "Failed to handle packet: " + localException1, localException1);
      }
      if (((hc)localObject).c) {
        g.remove(i--);
      }
    }

    for (int i = 0; i < h.size(); i++) {
      localObject = (lp)h.get(i);
      try {
        ((lp)localObject).a();
      } catch (Exception localException2) {
        a.log(Level.WARNING, "Failed to handle packet: " + localException2, localException2);
        ((lp)localObject).a("Internal server error");
      }
      if (((lp)localObject).c)
        h.remove(i--);
    }
  }
}