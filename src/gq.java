import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class gq {
    public static Logger a = Logger.getLogger("Minecraft");
    public List b = new ArrayList();
    private MinecraftServer c;
    private ig d;
    private int e;
    private Set f = new HashSet();
    private Set g = new HashSet();
    private Set h = new HashSet();
    private File i;
    private File j;
    private File k;
    private cz l;

    public gq(MinecraftServer paramMinecraftServer) {
        etc.setServer(paramMinecraftServer);
        etc.getInstance().loadData();
        if (!etc.getInstance().getTainted())
            a.info("Hey0 Server Mod Build " + etc.getInstance().getVersion());
        else {
            a.info("hMod Build Information: " + etc.getInstance().getVersionStr());
        }
        this.c = paramMinecraftServer;
        this.i = paramMinecraftServer.a("banned-players.txt");
        this.j = paramMinecraftServer.a("banned-ips.txt");
        this.k = paramMinecraftServer.a("ops.txt");
        this.d = new ig(paramMinecraftServer);
        this.e = paramMinecraftServer.d.a("max-players", 20);
        e();
        g();
        i();
        f();
        h();
        j();
    }

    public void a(ew paramew) {
        this.l = new cz(new File(paramew.t, "players"));
    }

    public int a() {
        return this.d.b();
    }

    public void a(es parames) {
        this.b.add(parames);
        this.l.b(parames);

        this.c.e.A.d((int) parames.p >> 4, (int) parames.r >> 4);

        while (this.c.e.a(parames, parames.z).size() != 0) {
            parames.a(parames.p, parames.q + 1.0D, parames.r);
        }
        this.c.e.a(parames);
        this.d.a(parames);
        
        // hMod: Handle login (send MOTD and call hook)
        String[] motd = etc.getInstance().getMotd();
        if (!(motd.length == 1 && motd[0].equals(""))) {
            for (String str : etc.getInstance().getMotd()) {
                parames.a.b(new bh(str));
            }
        }
        etc.getLoader().callHook(PluginLoader.Hook.LOGIN, new Object[]{parames});
    }

    public void b(es parames) {
        this.d.c(parames);
    }

    public void c(es parames) {
        this.l.a(parames);
        this.c.e.d(parames);
        this.b.remove(parames);
        this.d.b(parames);
    }

    public es a(fr paramfr, String paramString1, String paramString2) {
        if (this.f.contains(paramString1.trim().toLowerCase())) {
            paramfr.b("You are banned from this server!");
            return null;
        }

        // hMod: whole section below is modified to handle whitelists etc
        es temp = new es(this.c, this.c.e, paramString1, new ju(this.c.e));
        Player player = temp.getPlayer();

        String ip = paramfr.b.b().toString().split(":")[0].substring(1);
        if (this.g.contains(ip)) {
            paramfr.b("Your IP address is banned from this server!");
            return null;
        }
        for (int m = 0; m < this.b.size(); m++) {
            es locales = (es) this.b.get(m);
            if (locales.at.equalsIgnoreCase(paramString1)) {
                String ip2 = locales.a.b.b().toString().split(":")[0].substring(1);
                // perhaps they timed out since they're coming from the same IP
                if (ip2.equals(ip)) {
                    locales.a.b("You logged in from another location.");
                } else {
                    // otherwise no.
                    paramfr.b("You are currently logged in.");
                }
            }
        }

        if (etc.getInstance().isWhitelistEnabled() && !(etc.getDataSource().isUserOnWhitelist(paramString1) || player.isAdmin())) {
            paramfr.b(etc.getInstance().getWhitelistMessage());
            return null;
        } else if (this.b.size() >= this.e && !(player.isAdmin() || etc.getDataSource().isUserOnReserveList(paramString1))) {
            paramfr.b("The server is full!");
            return null;
        }

        if (!player.getIps()[0].equals("")) {
            boolean kick = true;
            for (int i = 0; i < player.getIps().length; i++) {
                if (!player.getIps()[i].equals("") && ip.equals(player.getIps()[i])) {
                    kick = false;
                }
            }
            if (kick) {
                paramfr.b("IP doesn't match specified IP.");
                return null;
            }
        }

        Object obj = etc.getLoader().callHook(PluginLoader.Hook.LOGINCHECK, new Object[]{paramString1});
        if (obj instanceof String) {
            String result = (String) obj;
            if (result != null && !result.equals("")) {
                paramfr.b(result);
                return null;
            }
        }
        return new es(this.c, this.c.e, paramString1, new ju(this.c.e));
    }

    /**
     * Returns the list of bans
     * 
     * @return
     */
    public String getBans() {
        StringBuilder builder = new StringBuilder();
        int l = 0;
        for (Object o : f) {
            if (l > 0) {
                builder.append(", ");
            }
            builder.append(o);
            l++;
        }
        return builder.toString();
    }

    /**
     * Returns the list of IP bans
     * 
     * @return
     */
    public String getIpBans() {
        StringBuilder builder = new StringBuilder();
        int l = 0;
        for (Object o : g) {
            if (l > 0) {
                builder.append(", ");
            }
            builder.append(o);
            l++;
        }
        return builder.toString();
    }

    public es d(es parames) {
        this.c.k.a(parames);
        this.c.k.b(parames);
        this.d.b(parames);
        this.b.remove(parames);
        this.c.e.e(parames);

        es locales = new es(this.c, this.c.e, parames.at, new ju(this.c.e));

        locales.g = parames.g;
        locales.a = parames.a;

        this.c.e.A.d((int) locales.p >> 4, (int) locales.r >> 4);

        while (this.c.e.a(locales, locales.z).size() != 0) {
            locales.a(locales.p, locales.q + 1.0D, locales.r);
        }

        locales.a.b(new az());
        locales.a.a(locales.p, locales.q, locales.r, locales.v, locales.w);

        this.d.a(locales);
        this.c.e.a(locales);
        this.b.add(locales);

        return locales;
    }

    public void b() {
        this.d.a();
    }

    public void a(int paramInt1, int paramInt2, int paramInt3) {
        this.d.a(paramInt1, paramInt2, paramInt3);
    }

    public void a(is paramis) {
        for (int m = 0; m < this.b.size(); m++) {
            es locales = (es) this.b.get(m);
            locales.a.b(paramis);
        }
    }

    public String c() {
        String str = "";
        for (int m = 0; m < this.b.size(); m++) {
            if (m > 0) {
                str = str + ", ";
            }
            str = str + ((es) this.b.get(m)).at;
        }
        return str;
    }

    public void a(String paramString) {
        this.f.add(paramString.toLowerCase());
        f();
    }

    public void b(String paramString) {
        this.f.remove(paramString.toLowerCase());
        f();
    }

    private void e() {
        try {
            this.f.clear();
            BufferedReader localBufferedReader = new BufferedReader(new FileReader(this.i));
            String str = "";
            while ((str = localBufferedReader.readLine()) != null) {
                this.f.add(str.trim().toLowerCase());
            }
            localBufferedReader.close();
        } catch (Exception localException) {
            a.warning("Failed to load ban list: " + localException);
        }
    }

    private void f() {
        try {
            PrintWriter localPrintWriter = new PrintWriter(new FileWriter(this.i, false));
            for (Object str : this.f) {
                localPrintWriter.println(str);
            }
            localPrintWriter.close();
        } catch (Exception localException) {
            a.warning("Failed to save ban list: " + localException);
        }
    }

    public void c(String paramString) {
        this.g.add(paramString.toLowerCase());
        h();
    }

    public void d(String paramString) {
        this.g.remove(paramString.toLowerCase());
        h();
    }

    private void g() {
        try {
            this.g.clear();
            BufferedReader localBufferedReader = new BufferedReader(new FileReader(this.j));
            String str = "";
            while ((str = localBufferedReader.readLine()) != null) {
                this.g.add(str.trim().toLowerCase());
            }
            localBufferedReader.close();
        } catch (Exception localException) {
            a.warning("Failed to load ip ban list: " + localException);
        }
    }

    private void h() {
        try {
            PrintWriter localPrintWriter = new PrintWriter(new FileWriter(this.j, false));
            for (Object str : this.g) {
                localPrintWriter.println(str);
            }
            localPrintWriter.close();
        } catch (Exception localException) {
            a.warning("Failed to save ip ban list: " + localException);
        }
    }

    public void e(String paramString) {
        this.h.add(paramString.toLowerCase());
        j();
    }

    public void f(String paramString) {
        this.h.remove(paramString.toLowerCase());
        j();
    }

    private void i() {
        try {
            this.h.clear();
            BufferedReader localBufferedReader = new BufferedReader(new FileReader(this.k));
            String str = "";
            while ((str = localBufferedReader.readLine()) != null) {
                this.h.add(str.trim().toLowerCase());
            }
            localBufferedReader.close();
        } catch (Exception localException) {
            a.warning("Failed to load ip ban list: " + localException);
        }
    }

    private void j() {
        try {
            PrintWriter localPrintWriter = new PrintWriter(new FileWriter(this.k, false));
            for (Object str : this.h) {
                localPrintWriter.println(str);
            }
            localPrintWriter.close();
        } catch (Exception localException) {
            a.warning("Failed to save ip ban list: " + localException);
        }
    }

    public boolean g(String paramString) {
        return this.h.contains(paramString.trim().toLowerCase());
    }

    public es h(String paramString) {
        for (int m = 0; m < this.b.size(); m++) {
            es locales = (es) this.b.get(m);
            if (locales.at.equalsIgnoreCase(paramString)) {
                return locales;
            }
        }
        return null;
    }

    public void a(String paramString1, String paramString2) {
        es locales = h(paramString1);
        if (locales != null) {
            locales.a.b(new bh(paramString2));
        }
    }

    public void a(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, is paramis) {
        for (int m = 0; m < this.b.size(); m++) {
            es locales = (es) this.b.get(m);
            double d1 = paramDouble1 - locales.p;
            double d2 = paramDouble2 - locales.q;
            double d3 = paramDouble3 - locales.r;
            if (d1 * d1 + d2 * d2 + d3 * d3 < paramDouble4 * paramDouble4) {
                locales.a.b(paramis);
            }
        }
    }

    public void i(String paramString) {
        bh localbh = new bh(paramString);
        for (int m = 0; m < this.b.size(); m++) {
            es locales = (es) this.b.get(m);
            if (g(locales.at)) {
                locales.a.b(localbh);
            }
        }
    }

    public boolean a(String paramString, is paramis) {
        es locales = h(paramString);
        if (locales != null) {
            locales.a.b(paramis);
            return true;
        }
        return false;
    }

    public void a(int paramInt1, int paramInt2, int paramInt3, ay paramay) {
        this.d.a(new jg(paramInt1, paramInt2, paramInt3, paramay), paramInt1, paramInt2, paramInt3);
    }

    public void d() {
        for (int m = 0; m < this.b.size(); m++) {
            this.l.a((es) this.b.get(m));
        }
    }
}
