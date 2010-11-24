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

public class gp {
    public static Logger a = Logger.getLogger("Minecraft");
    public List b = new ArrayList();
    private MinecraftServer c;
    private if d;
    private int e;
    private Set f = new HashSet();
    private Set g = new HashSet();
    private Set h = new HashSet();
    private File i;
    private File j;
    private File k;
    private cy l;

    public gp(MinecraftServer paramMinecraftServer) {
        etc.setServer(paramMinecraftServer);
        etc.getInstance().loadData();
        a.info("Hey0 Server Mod Build " + etc.getInstance().getVersion());
        this.c = paramMinecraftServer;
        this.i = paramMinecraftServer.a("banned-players.txt");
        this.j = paramMinecraftServer.a("banned-ips.txt");
        this.k = paramMinecraftServer.a("ops.txt");
        this.d = new if(paramMinecraftServer);
        this.e = paramMinecraftServer.d.a("max-players", 20);
        e();
        g();
        i();
        f();
        h();
        j();
    }

    public void a(ev paramev) {
        this.l = new cy(new File(paramev.t, "players"));
    }

    public int a() {
        return this.d.b();
    }

    public void a(er paramer) {
        this.b.add(paramer);
        this.l.b(paramer);

        this.c.e.A.d((int) paramer.p >> 4, (int) paramer.r >> 4);

        while (this.c.e.a(paramer, paramer.z).size() != 0) {
            paramer.a(paramer.p, paramer.q + 1.0D, paramer.r);
        }
        this.c.e.a(paramer);
        this.d.a(paramer);
        
        // Login
        for (String str : etc.getInstance().getMotd()) {
            paramer.a.b(new bh(str));
        }
        etc.getLoader().callHook(PluginLoader.Hook.LOGIN, new Object[]{paramep});
    }

    public void b(er paramer) {
        this.d.c(paramer);
    }

    public void c(er paramer) {
        this.d.b(paramer);
        this.l.a(paramer);
        this.c.e.d(paramer);
        this.b.remove(paramer);
    }

    public er a(fq paramfq, String paramString1, String paramString2) {
        if (this.f.contains(paramString1.trim().toLowerCase())) {
            paramfq.b("You are banned from this server!");
            return null;
        }
        
        er temp = new er(this.c, this.c.e, paramString1, new jt(this.c.e));
        Player player = temp.getPlayer();

        String ip = paramfq.b.b().toString().split(":")[0].substring(1);
        if (this.g.contains(ip)) {
            paramfq.b("Your IP address is banned from this server!");
            return null;
        }
        for (int m = 0; m < this.b.size(); m++) {
            er localer = (er) this.b.get(m);
            if (localer.as.equalsIgnoreCase(paramString1)) {
                String ip2 = localer.a.b.b().toString().split(":")[0].substring(1);
                // perhaps they timed out since they're coming from the same IP
                if (ip2.equals(ip)) {
                    localer.a.b("You logged in from another location.");
                } else {
                    // otherwise no.
                    paramfq.b("You are currently logged in.");
                }
            }
        }
        if (etc.getInstance().isWhitelistEnabled() && !(etc.getDataSource().isUserOnWhitelist(paramString1) || player.isAdmin())) {
            paramfq.b(etc.getInstance().getWhitelistMessage());
            return null;
        } else if (this.b.size() >= this.e && !(player.isAdmin() || etc.getDataSource().isUserOnReserveList(paramString1))) {
            paramfq.b("Server is full.");
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
                paramfq.b("IP doesn't match specified IP.");
                return null;
            }
        }

        Object obj = etc.getLoader().callHook(PluginLoader.Hook.LOGINCHECK, new Object[]{paramString1});
        if (obj instanceof String) {
            String result = (String) obj;
            if (result != null && !result.equals("")) {
                paramfq.b(result);
                return null;
            }
        }
        return new er(this.c, this.c.e, paramString1, new jt(this.c.e));
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


    public void b() {
        this.d.a();
    }

    public void a(int paramInt1, int paramInt2, int paramInt3) {
        this.d.a(paramInt1, paramInt2, paramInt3);
    }

    public void a(ir paramir) {
        for (int m = 0; m < this.b.size(); m++) {
            er localer = (er) this.b.get(m);
            localer.a.b(paramir);
        }
    }

    public String c() {
        String str = "";
        for (int m = 0; m < this.b.size(); m++) {
            if (m > 0) {
                str = str + ", ";
            }
            str = str + ((er) this.b.get(m)).as;
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

    public er h(String paramString) {
        for (int m = 0; m < this.b.size(); m++) {
            er localer = (er) this.b.get(m);
            if (localer.as.equalsIgnoreCase(paramString)) {
                return localer;
            }
        }
        return null;
    }

    public void a(String paramString1, String paramString2) {
        er localer = h(paramString1);
        if (localer != null) {
            localer.a.b(new bh(paramString2));
        }
    }

    public void i(String paramString) {
        bh localbh = new bh(paramString);
        for (int m = 0; m < this.b.size(); m++) {
            er localer = (er) this.b.get(m);
            if (g(localer.as)) {
                localer.a.b(localbh);
            }
        }
    }

    public boolean a(String paramString, ir paramir) {
        er localer = h(paramString);
        if (localer != null) {
            localer.a.b(paramir);
            return true;
        }
        return false;
    }

    public void a(int paramInt1, int paramInt2, int paramInt3, ay paramay) {
        this.d.a(new jf(paramInt1, paramInt2, paramInt3, paramay), paramInt1, paramInt2, paramInt3);
    }

    public void d() {
        for (int m = 0; m < this.b.size(); m++) {
            this.l.a((er) this.b.get(m));
        }
    }
}
