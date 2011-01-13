
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

public class ii {

    public static Logger a = Logger.getLogger("Minecraft");
    // hMod set list to contain <fi> objects.
    public List<fy> b = new ArrayList<fy>();
    private MinecraftServer c;
    private kj d;
    private int e;
    // hMod set these to Set<String> to remove errors and warnings.
    private Set<String> f = new HashSet<String>();
    private Set<String> g = new HashSet<String>();
    private Set<String> h = new HashSet<String>();
    private File i;
    private File j;
    private File k;
    private dy l;

    public ii(MinecraftServer paramMinecraftServer) {
        etc.setServer(paramMinecraftServer);
        etc.getInstance().loadData();
        a.info("Note: your current classpath is: " + System.getProperty("java.class.path", "*UNKNOWN*"));
        if (!etc.getInstance().getTainted()) {
            a.info("Hey0 Server Mod Build " + etc.getInstance().getVersion());
        } else {
            a.info("hMod Build Information: " + etc.getInstance().getVersionStr());
        }
        c = paramMinecraftServer;
        i = paramMinecraftServer.a("banned-players.txt");
        j = paramMinecraftServer.a("banned-ips.txt");
        k = paramMinecraftServer.a("ops.txt");
        d = new kj(paramMinecraftServer);
        e = paramMinecraftServer.d.a("max-players", 20);
        e();
        g();
        i();
        f();
        h();
        j();
    }

    public void a(gc paramgc) {
        l = new dy(new File(paramgc.t, "players"));
    }

    public int a() {
        return d.b();
    }

    public void a(fy paramfy) {
        b.add(paramfy);
        l.b(paramfy);

        c.e.A.d((int) paramfy.p >> 4, (int) paramfy.r >> 4);

        while (c.e.a(paramfy, paramfy.z).size() != 0) {
            paramfy.a(paramfy.p, paramfy.q + 1.0D, paramfy.r);
        }
        c.e.a(paramfy);
        d.a(paramfy);
        // hMod: Handle login (send MOTD and call hook)
        String[] motd = etc.getInstance().getMotd();
        if (!(motd.length == 1 && motd[0].equals(""))) {
            for (String str : etc.getInstance().getMotd()) {
                paramfy.a.b(new bz(str));
            }
        }
        etc.getLoader().callHook(PluginLoader.Hook.LOGIN, paramfy.getPlayer());
    }

    public void b(fy paramfy) {
        d.c(paramfy);
    }

    public void c(fy paramfy) {
        l.a(paramfy);
        c.e.d(paramfy);
        b.remove(paramfy);
        d.b(paramfy);
    }

    public fy a(hc paramhc, String paramString1, String paramString2) {
        if (f.contains(paramString1.trim().toLowerCase())) {
            paramhc.a("You are banned from this server!");
            return null;
        }
        //hMod: whole section below is modified to handle whitelists etc
        fy temp = new fy(this.c, this.c.e, paramString1, new md(this.c.e));
        Player player = temp.getPlayer();

        String str = paramhc.b.b().toString();
        str = str.substring(str.indexOf("/") + 1);
        str = str.substring(0, str.indexOf(":"));
        if (g.contains(str)) {
            paramhc.a("Your IP address is banned from this server!");
            return null;
        }
        for (int m = 0; m < b.size(); m++) {
            fy localfy = (fy) b.get(m);
            if (localfy.aw.equalsIgnoreCase(paramString1)) {
                localfy.a.a("You logged in from another location");
            }
        }

        //hMod whitelist block
        if (etc.getInstance().isWhitelistEnabled() && !(etc.getDataSource().isUserOnWhitelist(paramString1) || player.isAdmin())) {
            paramhc.a(etc.getInstance().getWhitelistMessage());
            return null;
        } else if (b.size() >= e) {
            paramhc.a("The server is full!");
            return null;
        }

        if (!player.getIps()[0].equals("")) {
            boolean kick = true;
            for (int i = 0; i < player.getIps().length; i++) {
                if (!player.getIps()[i].equals("") && str.equals(player.getIps()[i])) {
                    kick = false;
                }
            }
            if (kick) {
                paramhc.a("IP doesn't match specified IP.");
                return null;
            }
        }
        //hMod: user passed basic login check, inform plugins.
        Object obj = etc.getLoader().callHook(PluginLoader.Hook.LOGINCHECK, paramString1);
        if (obj instanceof String) {
            String result = (String) obj;
            if (result != null && !result.equals("")) {
                paramhc.a(result);
                return null;
            }
        }

        return new fy(c, c.e, paramString1, new md(c.e));
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

    public fy d(fy paramfy) {
        c.k.a(paramfy);
        c.k.b(paramfy);
        d.b(paramfy);
        b.remove(paramfy);
        c.e.e(paramfy);

        fy localfy = new fy(c, c.e, paramfy.aw, new md(c.e));
        localfy.g = paramfy.g;
        localfy.a = paramfy.a;

        c.e.A.d((int) localfy.p >> 4, (int) localfy.r >> 4);

        while (c.e.a(localfy, localfy.z).size() != 0) {
            localfy.a(localfy.p, localfy.q + 1.0D, localfy.r);
        }

        localfy.a.b(new bo());
        localfy.a.a(localfy.p, localfy.q, localfy.r, localfy.v, localfy.w);

        d.a(localfy);
        c.e.a(localfy);
        b.add(localfy);

        localfy.l();
        return localfy;
    }

    public void b() {
        d.a();
    }

    public void a(int paramInt1, int paramInt2, int paramInt3) {
        d.a(paramInt1, paramInt2, paramInt3);
    }

    public void a(kx paramkx) {
        for (int m = 0; m < b.size(); m++) {
            fy localfy = (fy) b.get(m);
            localfy.a.b(paramkx);
        }
    }

    public String c() {
        String str = "";
        for (int m = 0; m < b.size(); m++) {
            if (m > 0) {
                str = str + ", ";
            }
            str = str + ((fy) b.get(m)).aw;
        }
        return str;
    }

    public void a(String paramString) {
        f.add(paramString.toLowerCase());
        f();
    }

    public void b(String paramString) {
        f.remove(paramString.toLowerCase());
        f();
    }

    private void e() {
        try {
            f.clear();
            BufferedReader localBufferedReader = new BufferedReader(new FileReader(i));
            String str = "";
            while ((str = localBufferedReader.readLine()) != null) {
                f.add(str.trim().toLowerCase());
            }
            localBufferedReader.close();
        } catch (Exception localException) {
            a.warning("Failed to load ban list: " + localException);
        }
    }

    private void f() {
        try {
            PrintWriter localPrintWriter = new PrintWriter(new FileWriter(i, false));
            for (String str : f) {
                localPrintWriter.println(str);
            }
            localPrintWriter.close();
        } catch (Exception localException) {
            a.warning("Failed to save ban list: " + localException);
        }
    }

    public void c(String paramString) {
        g.add(paramString.toLowerCase());
        h();
    }

    public void d(String paramString) {
        g.remove(paramString.toLowerCase());
        h();
    }

    private void g() {
        try {
            g.clear();
            BufferedReader localBufferedReader = new BufferedReader(new FileReader(j));
            String str = "";
            while ((str = localBufferedReader.readLine()) != null) {
                g.add(str.trim().toLowerCase());
            }
            localBufferedReader.close();
        } catch (Exception localException) {
            a.warning("Failed to load ip ban list: " + localException);
        }
    }

    private void h() {
        try {
            PrintWriter localPrintWriter = new PrintWriter(new FileWriter(j, false));
            for (String str : g) {
                localPrintWriter.println(str);
            }
            localPrintWriter.close();
        } catch (Exception localException) {
            a.warning("Failed to save ip ban list: " + localException);
        }
    }

    public void e(String paramString) {
        h.add(paramString.toLowerCase());
        j();
    }

    public void f(String paramString) {
        h.remove(paramString.toLowerCase());
        j();
    }

    private void i() {
        try {
            h.clear();
            BufferedReader localBufferedReader = new BufferedReader(new FileReader(k));
            String str = "";
            while ((str = localBufferedReader.readLine()) != null) {
                h.add(str.trim().toLowerCase());
            }
            localBufferedReader.close();
        } catch (Exception localException) {
            a.warning("Failed to load ip ban list: " + localException);
        }
    }

    private void j() {
        try {
            PrintWriter localPrintWriter = new PrintWriter(new FileWriter(k, false));
            for (String str : h) {
                localPrintWriter.println(str);
            }
            localPrintWriter.close();
        } catch (Exception localException) {
            a.warning("Failed to save ip ban list: " + localException);
        }
    }

    public boolean g(String paramString) {
        return h.contains(paramString.trim().toLowerCase());
    }

    public fy h(String paramString) {
        for (int m = 0; m < b.size(); m++) {
            fy localfy = (fy) b.get(m);
            if (localfy.aw.equalsIgnoreCase(paramString)) {
                return localfy;
            }
        }
        return null;
    }

    public void a(String paramString1, String paramString2) {
        fy localfy = h(paramString1);
        if (localfy != null) {
            localfy.a.b(new bz(paramString2));
        }
    }

    public void a(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, kx paramkx) {
        for (int m = 0; m < b.size(); m++) {
            fy localfy = (fy) b.get(m);
            double d1 = paramDouble1 - localfy.p;
            double d2 = paramDouble2 - localfy.q;
            double d3 = paramDouble3 - localfy.r;
            if (d1 * d1 + d2 * d2 + d3 * d3 < paramDouble4 * paramDouble4) {
                localfy.a.b(paramkx);
            }
        }
    }

    public void i(String paramString) {
        bz localbz = new bz(paramString);
        for (int m = 0; m < b.size(); m++) {
            fy localfy = (fy) b.get(m);
            if (g(localfy.aw)) {
                localfy.a.b(localbz);
            }
        }
    }

    public boolean a(String paramString, kx paramkx) {
        fy localfy = h(paramString);
        if (localfy != null) {
            localfy.a.b(paramkx);
            return true;
        }
        return false;
    }

    public void d() {
        for (int m = 0; m < b.size(); m++) {
            l.a((fy) b.get(m));
        }
    }

    public void a(int paramInt1, int paramInt2, int paramInt3, bm parambm) {
        // hMod: fix sign updating in beta 1.1_02
        // Check if bg (TileEntity) is a Sign
        if (parambm instanceof lv) {
            d.sendPacketToChunk((jv) parambm.g(), paramInt1, paramInt2, paramInt3);
        }
        // end hMod
    }
}
