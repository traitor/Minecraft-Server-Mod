
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

public class gn {

    public static Logger a = Logger.getLogger("Minecraft");
    public List b = new ArrayList();
    private MinecraftServer c;
    private ic d;
    private int e;
    private Set f = new HashSet();
    private Set g = new HashSet();
    private Set h = new HashSet();
    private File i;
    private File j;
    private File k;
    private cx l;

    public gn(MinecraftServer paramMinecraftServer) {
        etc.setServer(paramMinecraftServer);
        etc.getInstance().loadData();
        a.info("Hey0 Server Mod Build " + etc.getInstance().getVersion());
        this.c = paramMinecraftServer;
        this.i = paramMinecraftServer.a("banned-players.txt");
        this.j = paramMinecraftServer.a("banned-ips.txt");
        this.k = paramMinecraftServer.a("ops.txt");
        this.d = new ic(paramMinecraftServer);
        this.e = paramMinecraftServer.d.a("max-players", 20);
        e();
        g();
        i();
        f();
        h();
        j();
    }

    public void a(et paramet) {
        this.l = new cx(new File(paramet.t, "players"));
    }

    public int a() {
        return this.d.b();
    }

    public void a(ep paramep) {
        this.b.add(paramep);
        this.l.b(paramep);

        this.c.e.A.d((int) paramep.p >> 4, (int) paramep.r >> 4);

        while (this.c.e.a(paramep, paramep.z).size() != 0) {
            paramep.a(paramep.p, paramep.q + 1.0D, paramep.r);
        }
        this.c.e.a(paramep);
        this.d.a(paramep);

        for (String str : etc.getInstance().getMotd()) {
            paramep.a.b(new bg(str));
        }
        etc.getLoader().callHook(PluginLoader.Hook.LOGIN, new Object[]{paramep});
    }

    public void b(ep paramep) {
        this.d.c(paramep);
    }

    public void c(ep paramep) {
        this.d.b(paramep);
        this.l.a(paramep);
        this.c.e.d(paramep);
        this.b.remove(paramep);
    }

    public ep a(fo paramfo, String paramString1, String paramString2) {
        if (this.f.contains(paramString1.trim().toLowerCase())) {
            paramfo.b("You are banned from this server!");
            return null;
        }

        ep temp = new ep(this.c, this.c.e, paramString1, new jq(this.c.e));
        Player player = temp.getPlayer();

        String ip = paramfo.b.b().toString().split(":")[0].substring(1);
        if (this.g.contains(ip)) {
            paramfo.b("Your IP address is banned from this server!");
            return null;
        }
        for (int i = 0; i < this.b.size(); i++) {
            ep localep = (ep) this.b.get(i);
            if (localep.ar.equalsIgnoreCase(paramString1)) {
                String ip2 = localep.a.b.b().toString().split(":")[0].substring(1);

                // perhaps they timed out since they're coming from the same IP
                if (ip2.equals(ip)) {
                    localep.a.b("You logged in from another location.");
                } else {
                    paramfo.b("You are currently logged in.");
                }
                // ^ otherwise no.
            }
        }
        if (etc.getInstance().isWhitelistEnabled() && !(etc.getDataSource().isUserOnWhitelist(paramString1) || player.isAdmin())) {
            paramfo.b(etc.getInstance().getWhitelistMessage());
            return null;
        } else if (this.b.size() >= this.e && !(player.isAdmin() || etc.getDataSource().isUserOnReserveList(paramString1))) {
            paramfo.b("Server is full.");
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
                paramfo.b("IP doesn't match specified IP.");
                return null;
            }
        }

        Object obj = etc.getLoader().callHook(PluginLoader.Hook.LOGINCHECK, new Object[]{paramString1});
        if (obj instanceof String) {
            String result = (String) obj;
            if (result != null && !result.equals("")) {
                paramfo.b(result);
                return null;
            }
        }

        return new ep(this.c, this.c.e, paramString1, new jq(this.c.e));
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

    public void a(io paramio) {
        for (int m = 0; m < this.b.size(); m++) {
            ep localep = (ep) this.b.get(m);
            localep.a.b(paramio);
        }
    }

    public String c() {
        String str = "";
        for (int m = 0; m < this.b.size(); m++) {
            if (m > 0) {
                str = str + ", ";
            }
            str = str + ((ep) this.b.get(m)).ar;
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

    public ep h(String paramString) {
        for (int m = 0; m < this.b.size(); m++) {
            ep localep = (ep) this.b.get(m);
            if (localep.ar.equalsIgnoreCase(paramString)) {
                return localep;
            }
        }
        return null;
    }

    public void a(String paramString1, String paramString2) {
        ep localep = h(paramString1);
        if (localep != null) {
            localep.a.b(new bg(paramString2));
        }
    }

    public void i(String paramString) {
        bg localbg = new bg(paramString);
        for (int m = 0; m < this.b.size(); m++) {
            ep localep = (ep) this.b.get(m);
            if (g(localep.ar)) {
                localep.a.b(localbg);
            }
        }
    }

    public boolean a(String paramString, io paramio) {
        ep localep = h(paramString);
        if (localep != null) {
            localep.a.b(paramio);
            return true;
        }
        return false;
    }

    public void a(int paramInt1, int paramInt2, int paramInt3, ay paramay) {
        this.d.a(new jc(paramInt1, paramInt2, paramInt3, paramay), paramInt1, paramInt2, paramInt3);
    }

    public void d() {
        for (int m = 0; m < this.b.size(); m++) {
            this.l.a((ep) this.b.get(m));
        }
    }
}
