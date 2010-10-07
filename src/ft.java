
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class ft {

    public static Logger a = Logger.getLogger("Minecraft");
    public List b = new ArrayList();
    private MinecraftServer c;
    private hg d;
    private int e;
    private ArrayList f = new ArrayList();
    private ArrayList g = new ArrayList();
    private ArrayList h = new ArrayList();
    private File i;
    private File j;
    private File k;
    private cl l;

    public ft(MinecraftServer paramMinecraftServer) {
        etc.setServer(paramMinecraftServer);
        etc.getInstance().loadData();
        this.c = paramMinecraftServer;
        this.i = paramMinecraftServer.a("banned-players.txt");
        this.j = paramMinecraftServer.a("banned-ips.txt");
        this.k = paramMinecraftServer.a("ops.txt");
        this.d = new hg(paramMinecraftServer);
        this.e = paramMinecraftServer.d.a("max-players", 20);
        e();
        g();
        i();
        f();
        h();
        j();
    }

    public void a(ee paramee) {
        this.l = new cl(new File(paramee.s, "players"));
    }

    public int a() {
        return this.d.b();
    }

    public void a(ea paramea) {
        this.b.add(paramea);
        this.l.b(paramea);

        this.c.e.y.d((int) paramea.l >> 4, (int) paramea.n >> 4);

        while (this.c.e.a(paramea, paramea.v).size() != 0) {
            paramea.a(paramea.l, paramea.m + 1.0D, paramea.n);
        }
        this.c.e.a(paramea);
        this.d.a(paramea);

        for (String str : etc.getInstance().getMotd()) {
            paramea.a.b(new ba(str));
        }
        etc.getInstance().getLoader().callHook(PluginLoader.Hook.LOGIN, new Object[]{paramea});
    }

    public void b(ea paramea) {
        try {
            this.d.c(paramea);
        } catch (NullPointerException ex) {
            a.info("What?");
        }
    }

    public void c(ea paramea) {
        this.d.b(paramea);
        this.l.a(paramea);
        this.c.e.d(paramea);
        this.b.remove(paramea);
    }

    public ea a(ew paramew, String paramString1, String paramString2) {
        if (this.f.contains(paramString1.trim().toLowerCase())) {
            paramew.b("You are banned from this server!");
            return null;
        }

        ea temp = new ea(this.c, this.c.e, paramString1, new in(this.c.e));
        Player player = temp.getPlayer();
        
        String ip = paramew.b.b().toString().split(":")[0].substring(1);
        if (this.g.contains(ip)) {
            paramew.b("Your IP address is banned from this server!");
            return null;
        }
        for (int i = 0; i < this.b.size(); ++i) {
            ea localea = (ea) this.b.get(i);
            if (localea.aq.equalsIgnoreCase(paramString1)) {
                String ip2 = localea.a.b.b().toString().split(":")[0].substring(1);

                // perhaps they timed out since they're coming from the same IP
                if (ip2.equals(ip)) {
                    localea.a.b("You logged in from another location.");
                } else {
                    paramew.b("You are currently logged in.");
                }
                // ^ otherwise no.
            }
        }
        if (etc.getInstance().isWhitelistEnabled() && !(etc.getDataSource().isUserOnWhitelist(paramString1)
                || player.isAdmin())) {
            paramew.b(etc.getInstance().getWhitelistMessage());
            return null;
        } else if (this.b.size() >= this.e && !(etc.getDataSource().hasReserveList() && (player.isAdmin()
                || etc.getDataSource().isUserOnReserveList(paramString1)))) {
            paramew.b("Server is full.");
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
                player.kick("IP doesn't match specified IP.");
                return null;
            }
        }
        
        Object obj = etc.getInstance().getLoader().callHook(PluginLoader.Hook.LOGINCHECK, new Object[]{paramString1});
        if (obj instanceof String) {
            String result = (String)obj;
            if (result != null && !result.equals("")) {
                paramew.b(result);
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
        for (int l = 0; l < this.f.size(); ++l) {
            if (l > 0) {
                builder.append(", ");
            }
            builder.append(this.f.get(l));
        }
        return builder.toString();
    }

    /**
     * Returns the list of IP bans
     * @return
     */
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

    public void b() {
        this.d.a();
    }

    public void a(int paramInt1, int paramInt2, int paramInt3) {
        this.d.a(paramInt1, paramInt2, paramInt3);
    }

    public void a(hp paramhp) {
        for (int m = 0; m < this.b.size(); m++) {
            ea localea = (ea) this.b.get(m);
            localea.a.b(paramhp);
        }
    }

    public String c() {
        String str = "";
        for (int m = 0; m < this.b.size(); m++) {
            if (m > 0) {
                str = str + ", ";
            }
            str = str + ((ea) this.b.get(m)).aq;
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

    public ea h(String paramString) {
        for (int m = 0; m < this.b.size(); m++) {
            ea localea = (ea) this.b.get(m);
            if (localea.aq.equalsIgnoreCase(paramString)) {
                return localea;
            }
        }
        return null;
    }

    public void a(String paramString1, String paramString2) {
        ea localea = h(paramString1);
        if (localea != null) {
            localea.a.b(new ba(paramString2));
        }
    }

    public void i(String paramString) {
        ba localba = new ba(paramString);
        for (int m = 0; m < this.b.size(); m++) {
            ea localea = (ea) this.b.get(m);
            if (g(localea.aq)) {
                localea.a.b(localba);
            }
        }
    }

    public boolean a(String paramString, hp paramhp) {
        ea localea = h(paramString);
        if (localea != null) {
            localea.a.b(paramhp);
            return true;
        }
        return false;
    }

    public void a(int paramInt1, int paramInt2, int paramInt3, as paramas) {
        this.d.a(new ib(paramInt1, paramInt2, paramInt3, paramas), paramInt1, paramInt2, paramInt3);
    }

    public void d() {
        for (int m = 0; m < this.b.size(); m++) {
            this.l.a((ea) this.b.get(m));
        }
    }
}
