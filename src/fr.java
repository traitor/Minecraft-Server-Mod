/* One part of the login process */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class fr {
    public static final Logger a = Logger.getLogger("Minecraft");
    public List b = new ArrayList();
    private MinecraftServer c;
    private he d;
    private int e;
    private ArrayList f = new ArrayList();
    private ArrayList g = new ArrayList();
    private ArrayList h = new ArrayList();
    private File i;
    private File j;
    private File k;

    public fr(MinecraftServer paramMinecraftServer) {
        this.c = paramMinecraftServer;
        this.i = paramMinecraftServer.a("banned-players.txt");
        this.j = paramMinecraftServer.a("banned-ips.txt");
        this.k = paramMinecraftServer.a("ops.txt");
        this.d = new he(paramMinecraftServer);
        this.e = paramMinecraftServer.d.a("max-players", 20);
        d();
        f();
        h();
        e();
        g();
        i();
    }

    public int a() {
        return this.d.b();
    }

    public void a(dy paramdy) {
        this.b.add(paramdy);
        this.c.e.a(paramdy);
        this.d.a(paramdy);
        for (String str : etc.getInstance().motd) {
            paramdy.a.b(new az(str));
        }
    }

    public void b(dy paramdy) {
        this.d.c(paramdy);
    }

    public void c(dy paramdy) {
        this.c.e.d(paramdy);
        this.b.remove(paramdy);
        this.d.b(paramdy);
    }

    public dy a(eu parameu, String paramString1, String paramString2) {
        if (this.f.contains(paramString1.trim().toLowerCase())) {
            parameu.b("You are banned from this server!");
            return null;
        }
        String ip = parameu.b.b().toString().split(":")[0].substring(1);
        if (this.g.contains(ip)) {
            parameu.b("Your IP address is banned from this server!");
            return null;
        }
        for (int i = 0; i < this.b.size(); ++i) {
            dy localdy = (dy) this.b.get(i);
            if (localdy.ap.equalsIgnoreCase(paramString1)) {
                String ip2 = localdy.a.b.b().toString().split(":")[0].substring(1);

                // perhaps they timed out since they're coming from the same IP
                if (ip2.equals(ip)) {
                    localdy.a.b("You logged in from another location.");
                } else {
                    parameu.b("You are currently logged in.");
                }
                // ^ otherwise no.
            }
        }
        if (etc.getInstance().getDataSource().hasWhitelist() && !(etc.getInstance().getDataSource().isUserOnWhitelist(paramString1)
                || etc.getInstance().isAdmin(paramString1))) {
            parameu.b("Not on whitelist.");
            return null;
        } else if (this.b.size() >= this.e && !(etc.getInstance().getDataSource().hasReserveList() && (etc.getInstance().isAdmin(paramString1)
                || etc.getInstance().getDataSource().isUserOnReserveList(paramString1)))) {
            parameu.b("Server is full.");
            return null;
        }
        return new dy(this.c, this.c.e, paramString1, new ik(this.c.e));
    }

    public void b() {
        this.d.a();
    }

    public void a(int paramInt1, int paramInt2, int paramInt3) {
        this.d.a(paramInt1, paramInt2, paramInt3);
    }

    public void a(hn paramhn) {
        for (int l = 0; l < this.b.size(); ++l) {
            dy localdy = (dy) this.b.get(l);
            localdy.a.b(paramhn);
        }
    }

    public String c() {
        StringBuilder builder = new StringBuilder();
        for (int l = 0; l < this.b.size(); ++l) {
            if (l > 0) {
                builder.append(", ");
            }
            builder.append(((dy) this.b.get(l)).ap);
        }
        return builder.toString();
    }

    public String getBans() {
        StringBuilder builder = new StringBuilder();
        for (int l = 0; l < this.f.size(); ++l) {
            if (l > 0) {
                builder.append(", ");
            }
            builder.append(this.f.get(l));
        }
        return builder.toString();
    }

    public String getIpBans() {
        StringBuilder builder = new StringBuilder();
        for (int l = 0; l < this.g.size(); ++l) {
            if (l > 0) {
                builder.append(", ");
            }
            builder.append(this.g.get(l));
        }
        return builder.toString();
    }

    public void a(String paramString) {
        this.f.add(paramString.toLowerCase());
        e();
    }

    public void b(String paramString) {
        this.f.remove(paramString.toLowerCase());
        e();
    }

    private void d() {
        try {
            this.f.clear();
            BufferedReader localBufferedReader = new BufferedReader(new FileReader(this.i));
            String str = "";
            while ((str = localBufferedReader.readLine()) != null) {
                this.f.add(str.trim().toLowerCase());
            }
            localBufferedReader.close();
        } catch (Exception localException) {
            a.log(Level.WARNING, "Failed to load ban list", localException);
        }
    }

    private void e() {
        try {
            PrintWriter localPrintWriter = new PrintWriter(new FileWriter(this.i, false));
            for (Object str : this.f) {
                localPrintWriter.println(str);
            }
            localPrintWriter.close();
        } catch (Exception localException) {
            a.log(Level.WARNING, "Failed to save ban list", localException);
        }
    }

    public void c(String paramString) {
        this.g.add(paramString.toLowerCase());
        g();
    }

    public void d(String paramString) {
        this.g.remove(paramString.toLowerCase());
        g();
    }

    private void f() {
        try {
            this.g.clear();
            BufferedReader localBufferedReader = new BufferedReader(new FileReader(this.j));
            String str = "";
            while ((str = localBufferedReader.readLine()) != null) {
                this.g.add(str.trim().toLowerCase());
            }
            localBufferedReader.close();
        } catch (Exception localException) {
            a.log(Level.WARNING, "Failed to load ip ban list", localException);
        }
    }

    private void g() {
        try {
            PrintWriter localPrintWriter = new PrintWriter(new FileWriter(this.j, false));
            for (Object str : this.g) {
                localPrintWriter.println(str);
            }
            localPrintWriter.close();
        } catch (Exception localException) {
            a.log(Level.WARNING, "Failed to save ip ban list", localException);
        }
    }

    public void e(String paramString) {
        this.h.add(paramString.toLowerCase());
        i();
    }

    public void f(String paramString) {
        this.h.remove(paramString.toLowerCase());
        i();
    }

    private void h() {
        try {
            this.h.clear();
            BufferedReader localBufferedReader = new BufferedReader(new FileReader(this.k));
            String str = "";
            while ((str = localBufferedReader.readLine()) != null) {
                this.h.add(str.trim().toLowerCase());
            }
            localBufferedReader.close();
        } catch (Exception localException) {
            a.log(Level.WARNING, "Failed to load ip ban list", localException);
        }
    }

    private void i() {
        try {
            PrintWriter localPrintWriter = new PrintWriter(new FileWriter(this.k, false));
            for (Object str : this.h) {
                localPrintWriter.println(str);
            }
            localPrintWriter.close();
        } catch (Exception localException) {
            a.log(Level.WARNING, "Failed to save ip ban list", localException);
        }
    }

    public boolean g(String paramString) {
        return this.h.contains(paramString.trim().toLowerCase());
    }

    public dy h(String paramString) {
        for (int l = 0; l < this.b.size(); ++l) {
            dy localdy = (dy) this.b.get(l);
            if (localdy.ap.equalsIgnoreCase(paramString)) {
                return localdy;
            }
        }
        return null;
    }

    public void a(String paramString1, String paramString2) {
        dy localdy = h(paramString1);
        if (localdy != null) {
            localdy.a.b(new az(paramString2));
        }
    }

    public void i(String paramString) {
        az localaz = new az(paramString);
        for (int l = 0; l < this.b.size(); ++l) {
            dy localdy = (dy) this.b.get(l);
            if (g(localdy.ap)) {
                localdy.a.b(localaz);
            }
        }
    }

    public boolean a(String paramString, hn paramhn) {
        dy localdy = h(paramString);
        if (localdy != null) {
            localdy.a.b(paramhn);
            return true;
        }
        return false;
    }
}
