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

public class hk {
    public static Logger a = Logger.getLogger("Minecraft");
    // hMod set list to contain <fi> objects.
    public List<fi> b = new ArrayList<fi>();
    private MinecraftServer c;
    private jg d;
    private int e;
    // hMod set these to Set<String> to remove errors and warnings.
    private Set<String> f = new HashSet<String>();
    private Set<String> g = new HashSet<String>();
    private Set<String> h = new HashSet<String>();
    private File i;
    private File j;
    private File k;
    private dm l;

    public hk(MinecraftServer paramMinecraftServer) {        
        etc.setServer(paramMinecraftServer);
        etc.getInstance().loadData();
        a.info("Note: your current classpath is: " + System.getProperty("java.class.path", "*UNKNOWN*"));
        if (!etc.getInstance().getTainted()) {
            a.info("Hey0 Server Mod Build " + etc.getInstance().getVersion());
        } else {
            a.info("hMod Build Information: " + etc.getInstance().getVersionStr());
        }
        this.c = paramMinecraftServer;
        this.i = paramMinecraftServer.a("banned-players.txt");
        this.j = paramMinecraftServer.a("banned-ips.txt");
        this.k = paramMinecraftServer.a("ops.txt");
        this.d = new jg(paramMinecraftServer);
        this.e = paramMinecraftServer.d.a("max-players", 20);
        e();
        g();
        i();
        f();
        h();
        j();
    }

    public void a(fm paramfm) {
        this.l = new dm(new File(paramfm.t, "players"));
    }

    public int a() {
        return this.d.b();
    }

    public void a(fi paramfi) {
        this.b.add(paramfi);
        this.l.b(paramfi);

        this.c.e.A.d((int) paramfi.p >> 4, (int) paramfi.r >> 4);

        while (this.c.e.a(paramfi, paramfi.z).size() != 0) {
            paramfi.a(paramfi.p, paramfi.q + 1.0D, paramfi.r);
        }
        this.c.e.a(paramfi);
        this.d.a(paramfi);
        
        // hMod: Handle login (send MOTD and call hook)
        String[] motd = etc.getInstance().getMotd();
        if (!(motd.length == 1 && motd[0].equals(""))) {
            for (String str : etc.getInstance().getMotd()) {
                paramfi.a.b(new br(str));
            }
        }
        etc.getLoader().callHook(PluginLoader.Hook.LOGIN, paramfi.getPlayer());
    }

    public void b(fi paramfi) {
        this.d.c(paramfi);
    }

    public void c(fi paramfi) {
        this.l.a(paramfi);
        this.c.e.d(paramfi);
        this.b.remove(paramfi);
        this.d.b(paramfi);
    }

    public fi a(gh paramgh, String paramString1, String paramString2) {
        if (this.f.contains(paramString1.trim().toLowerCase())) {
            paramgh.a("You are banned from this server!");
            return null;
        }
        
        //hMod: whole section below is modified to handle whitelists etc
        fi temp = new fi(this.c, this.c.e, paramString1, new kv(this.c.e));
        Player player = temp.getPlayer();
        
        String str = paramgh.b.b().toString();
        str = str.substring(str.indexOf("/") + 1);
        str = str.substring(0, str.indexOf(":"));
        if (this.g.contains(str)) {
            paramgh.a("Your IP address is banned from this server!");
            return null;
        }

        for (int m = 0; m < this.b.size(); m++) {
            fi localfi = this.b.get(m);
            if (localfi.aw.equalsIgnoreCase(paramString1)) {
                localfi.a.a("You logged in from another location");
            }
        }

        //hMod whitelist block
        if (etc.getInstance().isWhitelistEnabled() && !(etc.getDataSource().isUserOnWhitelist(paramString1) || player.isAdmin())) {
            paramgh.a(etc.getInstance().getWhitelistMessage());
            return null;
        } else if (this.b.size() >= this.e && !(player.isAdmin() || etc.getDataSource().isUserOnReserveList(paramString1))) {
            paramgh.a("The server is full!");
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
                paramgh.a("IP doesn't match specified IP.");
                return null;
            }
        } 

        //hMod: user passed basic login check, inform plugins.
        Object obj = etc.getLoader().callHook(PluginLoader.Hook.LOGINCHECK, paramString1);
        if (obj instanceof String) {
            String result = (String) obj;
            if (result != null && !result.equals("")) {
                paramgh.a(result);
                return null;
            }
        }
        
        return new fi(this.c, this.c.e, paramString1, new kv(this.c.e));
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

    public fi d(fi paramfi) {
        this.c.k.a(paramfi);
        this.c.k.b(paramfi);
        this.d.b(paramfi);
        this.b.remove(paramfi);
        this.c.e.e(paramfi);

        fi localfi = new fi(this.c, this.c.e, paramfi.aw, new kv(this.c.e));
        localfi.g = paramfi.g;
        localfi.a = paramfi.a;

        this.c.e.A.d((int) localfi.p >> 4, (int) localfi.r >> 4);

        while (this.c.e.a(localfi, localfi.z).size() != 0) {
            localfi.a(localfi.p, localfi.q + 1.0D, localfi.r);
        }

        localfi.a.b(new bh());
        localfi.a.a(localfi.p, localfi.q, localfi.r, localfi.v, localfi.w);

        this.d.a(localfi);
        this.c.e.a(localfi);
        this.b.add(localfi);

        localfi.k();
        return localfi;
    }

    public void b() {
        this.d.a();
    }

    public void a(int paramInt1, int paramInt2, int paramInt3) {
        this.d.a(paramInt1, paramInt2, paramInt3);
    }

    public void a(ju paramju) {
        for (int m = 0; m < this.b.size(); m++) {
            fi localfi = this.b.get(m);
            localfi.a.b(paramju);
        }
    }

    public String c() {
        String str = "";
        for (int m = 0; m < this.b.size(); m++) {
            if (m > 0) {
                str = str + ", ";
            }
            str = str + (this.b.get(m)).aw;
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
            for (String str : this.f) {
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
            for (String str : this.g) {
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
            for (String str : this.h) {
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

    public fi h(String paramString) {
        for (int m = 0; m < this.b.size(); m++) {
            fi localfi = this.b.get(m);
            if (localfi.aw.equalsIgnoreCase(paramString)) {
                return localfi;
            }
        }
        return null;
    }

    public void a(String paramString1, String paramString2) {
        fi localfi = h(paramString1);
        if (localfi != null) {
            localfi.a.b(new br(paramString2));
        }
    }

    public void a(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, ju paramju) {
        for (int m = 0; m < this.b.size(); m++) {
            fi localfi = this.b.get(m);
            double d1 = paramDouble1 - localfi.p;
            double d2 = paramDouble2 - localfi.q;
            double d3 = paramDouble3 - localfi.r;
            if (d1 * d1 + d2 * d2 + d3 * d3 < paramDouble4 * paramDouble4) {
                localfi.a.b(paramju);
            }
        }
    }

    public void i(String paramString) {
        br localbr = new br(paramString);
        for (int m = 0; m < this.b.size(); m++) {
            fi localfi = this.b.get(m);
            if (g(localfi.aw)) {
                localfi.a.b(localbr);
            }
        }
    }

    public boolean a(String paramString, ju paramju) {
        fi localfi = h(paramString);
        if (localfi != null) {
            localfi.a.b(paramju);
            return true;
        }
        return false;
    }

    public void d() {
        for (int m = 0; m < this.b.size(); m++) {
            this.l.a(this.b.get(m));
        }
    }

    public void a(int paramInt1, int paramInt2, int paramInt3, bg parambg) {
    }
}