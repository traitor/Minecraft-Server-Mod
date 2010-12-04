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

public class gr {
    public static Logger a = Logger.getLogger("Minecraft");
    public List b = new ArrayList();
    private MinecraftServer c;
    private ih d;
    private int e;
    private Set f = new HashSet();
    private Set g = new HashSet();
    private Set h = new HashSet();
    private File i;
    private File j;
    private File k;
    private da l;

    public gr(MinecraftServer paramMinecraftServer) {
        etc.setServer(paramMinecraftServer);
        etc.getInstance().loadData();
        a.info("Note: your current classpath is: " + System.getProperty("java.class.path", "*UNKNOWN*"));
        if (!etc.getInstance().getTainted())
            a.info("Hey0 Server Mod Build " + etc.getInstance().getVersion());
        else {
            a.info("hMod Build Information: " + etc.getInstance().getVersionStr());
        }
        this.c = paramMinecraftServer;
        this.i = paramMinecraftServer.a("banned-players.txt");
        this.j = paramMinecraftServer.a("banned-ips.txt");
        this.k = paramMinecraftServer.a("ops.txt");
        this.d = new ih(paramMinecraftServer);
        this.e = paramMinecraftServer.d.a("max-players", 20);
        e();
        g();
        i();
        f();
        h();
        j();
    }

    public void a(ex paramex) {
        this.l = new da(new File(paramex.t, "players"));
    }

    public int a() {
        return this.d.b();
    }

    public void a(et paramet) {
        this.b.add(paramet);
        this.l.b(paramet);

        this.c.e.A.d((int) paramet.p >> 4, (int) paramet.r >> 4);

        while (this.c.e.a(paramet, paramet.z).size() != 0) {
            paramet.a(paramet.p, paramet.q + 1.0D, paramet.r);
        }
        this.c.e.a(paramet);
        this.d.a(paramet);

        // hMod: Handle login (send MOTD and call hook)
        String[] motd = etc.getInstance().getMotd();
        if (!(motd.length == 1 && motd[0].equals(""))) {
            for (String str : etc.getInstance().getMotd()) {
                paramet.a.b(new bh(str));
            }
        }
        etc.getLoader().callHook(PluginLoader.Hook.LOGIN, paramet.getPlayer());
    }

    public void b(et paramet) {
        this.d.c(paramet);
    }

    public void c(et paramet) {
        this.l.a(paramet);
        this.c.e.d(paramet);
        this.b.remove(paramet);
        this.d.b(paramet);
    }

    public et a(fs paramfs, String paramString1, String paramString2) {
        if (this.f.contains(paramString1.trim().toLowerCase())) {
            paramfs.b("You are banned from this server!");
            return null;
        }

        // hMod: whole section below is modified to handle whitelists etc
        et temp = new et(this.c, this.c.e, paramString1, new jv(this.c.e));
        Player player = temp.getPlayer();

        String ip = paramfs.b.b().toString().split(":")[0].substring(1);
        if (this.g.contains(ip)) {
            paramfs.b("Your IP address is banned from this server!");
            return null;
        }

        for (int m = 0; m < this.b.size(); m++) {
            et localet = (et) this.b.get(m);
            if (localet.at.equalsIgnoreCase(paramString1)) {
                String ip2 = localet.a.b.b().toString().split(":")[0].substring(1);
                // perhaps they timed out since they're coming from the same IP
                if (ip2.equals(ip)) {
                    localet.a.b("You logged in from another location.");
                } else {
                    // otherwise no.
                    paramfs.b("You are currently logged in.");
                }
            }
        }

        if (etc.getInstance().isWhitelistEnabled() && !(etc.getDataSource().isUserOnWhitelist(paramString1) || player.isAdmin())) {
            paramfs.b(etc.getInstance().getWhitelistMessage());
            return null;
        } else if (this.b.size() >= this.e && !(player.isAdmin() || etc.getDataSource().isUserOnReserveList(paramString1))) {
            paramfs.b("The server is full!");
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
                paramfs.b("IP doesn't match specified IP.");
                return null;
            }
        }

        Object obj = etc.getLoader().callHook(PluginLoader.Hook.LOGINCHECK, paramString1);
        if (obj instanceof String) {
            String result = (String) obj;
            if (result != null && !result.equals("")) {
                paramfs.b(result);
                return null;
            }
        }
        return new et(this.c, this.c.e, paramString1, new jv(this.c.e));
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

    public et d(et paramet) {
        this.c.k.a(paramet);
        this.c.k.b(paramet);
        this.d.b(paramet);
        this.b.remove(paramet);
        this.c.e.e(paramet);

        et localet = new et(this.c, this.c.e, paramet.at, new jv(this.c.e));

        localet.g = paramet.g;
        localet.a = paramet.a;

        this.c.e.A.d((int) localet.p >> 4, (int) localet.r >> 4);

        while (this.c.e.a(localet, localet.z).size() != 0) {
            localet.a(localet.p, localet.q + 1.0D, localet.r);
        }

        localet.a.b(new az());
        localet.a.a(localet.p, localet.q, localet.r, localet.v, localet.w);

        this.d.a(localet);
        this.c.e.a(localet);
        this.b.add(localet);

        return localet;
    }

    public void b() {
        this.d.a();
    }

    public void a(int paramInt1, int paramInt2, int paramInt3) {
        this.d.a(paramInt1, paramInt2, paramInt3);
    }

    public void a(it paramit) {
        for (int m = 0; m < this.b.size(); m++) {
            et localet = (et) this.b.get(m);
            localet.a.b(paramit);
        }
    }

    public String c() {
        String str = "";
        for (int m = 0; m < this.b.size(); m++) {
            if (m > 0) {
                str = str + ", ";
            }
            str = str + ((et) this.b.get(m)).at;
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

    public et h(String paramString) {
        for (int m = 0; m < this.b.size(); m++) {
            et localet = (et) this.b.get(m);
            if (localet.at.equalsIgnoreCase(paramString)) {
                return localet;
            }
        }
        return null;
    }

    public void a(String paramString1, String paramString2) {
        et localet = h(paramString1);
        if (localet != null) {
            localet.a.b(new bh(paramString2));
        }
    }

    public void a(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, it paramit) {
        for (int m = 0; m < this.b.size(); m++) {
            et localet = (et) this.b.get(m);
            double d1 = paramDouble1 - localet.p;
            double d2 = paramDouble2 - localet.q;
            double d3 = paramDouble3 - localet.r;
            if (d1 * d1 + d2 * d2 + d3 * d3 < paramDouble4 * paramDouble4) {
                localet.a.b(paramit);
            }
        }
    }

    public void i(String paramString) {
        bh localbh = new bh(paramString);
        for (int m = 0; m < this.b.size(); m++) {
            et localet = (et) this.b.get(m);
            if (g(localet.at)) {
                localet.a.b(localbh);
            }
        }
    }

    public boolean a(String paramString, it paramit) {
        et localet = h(paramString);
        if (localet != null) {
            localet.a.b(paramit);
            return true;
        }
        return false;
    }

    public void a(int paramInt1, int paramInt2, int paramInt3, ay paramay) {
        this.d.a(new jh(paramInt1, paramInt2, paramInt3, paramay), paramInt1, paramInt2, paramInt3);
    }

    public void d() {
        for (int m = 0; m < this.b.size(); m++) {
            this.l.a((et) this.b.get(m));
        }
    }
}
