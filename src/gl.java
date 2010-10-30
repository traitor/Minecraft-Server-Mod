
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

public class gl {

    public static Logger a = Logger.getLogger("Minecraft");
    public List b = new ArrayList();
    private MinecraftServer c;
    private ia d;
    private int e;
    private Set f = new HashSet();
    private Set g = new HashSet();
    private Set h = new HashSet();
    private File i;
    private File j;
    private File k;
    private cw l;

    public gl(MinecraftServer paramMinecraftServer) {
        etc.setServer(paramMinecraftServer);
        etc.getInstance().loadData();
        a.info("Hey0 Server Mod Build " + etc.getInstance().getVersion());
        this.c = paramMinecraftServer;
        this.i = paramMinecraftServer.a("banned-players.txt");
        this.j = paramMinecraftServer.a("banned-ips.txt");
        this.k = paramMinecraftServer.a("ops.txt");
        this.d = new ia(paramMinecraftServer);
        this.e = paramMinecraftServer.d.a("max-players", 20);
        e();
        g();
        i();
        f();
        h();
        j();
    }

    public void a(es parames) {
        this.l = new cw(new File(parames.t, "players"));
    }

    public int a() {
        return this.d.b();
    }

    public void a(eo parameo) {
        this.b.add(parameo);
        this.l.b(parameo);

        this.c.e.A.d((int) parameo.p >> 4, (int) parameo.r >> 4);

        while (this.c.e.a(parameo, parameo.z).size() != 0) {
            parameo.a(parameo.p, parameo.q + 1.0D, parameo.r);
        }
        this.c.e.a(parameo);
        this.d.a(parameo);

        for (String str : etc.getInstance().getMotd()) {
            parameo.a.b(new be(str));
        }
        etc.getLoader().callHook(PluginLoader.Hook.LOGIN, new Object[]{parameo});
    }

    public void b(eo parameo) {
        this.d.c(parameo);
    }

    public void c(eo parameo) {
        this.d.b(parameo);
        this.l.a(parameo);
        this.c.e.d(parameo);
        this.b.remove(parameo);
    }

    public eo a(fn paramfn, String paramString1, String paramString2) {
        if (this.f.contains(paramString1.trim().toLowerCase())) {
            paramfn.b("You are banned from this server!");
            return null;
        }

        eo temp = new eo(this.c, this.c.e, paramString1, new jo(this.c.e));
        Player player = temp.getPlayer();

        String ip = paramfn.b.b().toString().split(":")[0].substring(1);
        if (this.g.contains(ip)) {
            paramfn.b("Your IP address is banned from this server!");
            return null;
        }
        for (int i = 0; i < this.b.size(); ++i) {
            eo localea = (eo) this.b.get(i);
            if (localea.ar.equalsIgnoreCase(paramString1)) {
                String ip2 = localea.a.b.b().toString().split(":")[0].substring(1);

                // perhaps they timed out since they're coming from the same IP
                if (ip2.equals(ip)) {
                    localea.a.b("You logged in from another location.");
                } else {
                    paramfn.b("You are currently logged in.");
                }
                // ^ otherwise no.
            }
        }
        if (etc.getInstance().isWhitelistEnabled() && !(etc.getDataSource().isUserOnWhitelist(paramString1)
                || player.isAdmin())) {
            paramfn.b(etc.getInstance().getWhitelistMessage());
            return null;
        } else if (this.b.size() >= this.e &&
                !(player.isAdmin() || etc.getDataSource().isUserOnReserveList(paramString1))) {
            paramfn.b("Server is full.");
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
                paramfn.b("IP doesn't match specified IP.");
                return null;
            }
        }

        Object obj = etc.getLoader().callHook(PluginLoader.Hook.LOGINCHECK, new Object[]{paramString1});
        if (obj instanceof String) {
            String result = (String)obj;
            if (result != null && !result.equals("")) {
                paramfn.b(result);
                return null;
            }
        }

        return temp;
    }

    /**
     * Returns the list of bans
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

    public void a(im paramim) {
        for (int m = 0; m < this.b.size(); m++) {
            eo localeo = (eo) this.b.get(m);
            localeo.a.b(paramim);
        }
    }

    public String c() {
        String str = "";
        for (int m = 0; m < this.b.size(); m++) {
            if (m > 0) {
                str = str + ", ";
            }
            str = str + ((eo) this.b.get(m)).ar;
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

    public eo h(String paramString) {
        for (int m = 0; m < this.b.size(); m++) {
            eo localeo = (eo) this.b.get(m);
            if (localeo.ar.equalsIgnoreCase(paramString)) {
                return localeo;
            }
        }
        return null;
    }

    public void a(String paramString1, String paramString2) {
        eo localeo = h(paramString1);
        if (localeo != null) {
            localeo.a.b(new be(paramString2));
        }
    }

    public void i(String paramString) {
        be localbe = new be(paramString);
        for (int m = 0; m < this.b.size(); m++) {
            eo localeo = (eo) this.b.get(m);
            if (g(localeo.ar)) {
                localeo.a.b(localbe);
            }
        }
    }

    public boolean a(String paramString, im paramim) {
        eo localeo = h(paramString);
        if (localeo != null) {
            localeo.a.b(paramim);
            return true;
        }
        return false;
    }

    public void a(int paramInt1, int paramInt2, int paramInt3, av paramav) {
        this.d.a(new ja(paramInt1, paramInt2, paramInt3, paramav), paramInt1, paramInt2, paramInt3);
    }

    public void d() {
        for (int m = 0; m < this.b.size(); m++) {
            this.l.a((eo) this.b.get(m));
        }
    }
}
