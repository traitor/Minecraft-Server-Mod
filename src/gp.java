
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
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
    // hMod: reflection to get around 'if'
    private Object d;
    private int e;
    private Set f = new HashSet();
    private Set g = new HashSet();
    private Set h = new HashSet();
    private File i;
    private File j;
    private File k;
    private cy l;
    // hMod: reflection to get around 'if'
    private Method if_a, if_a1, if_a2, if_a3, if_b, if_b2, if_c;

    public gp(MinecraftServer paramMinecraftServer) {
        // hMod: Store the server, load data, get cracking!
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

        // hMod: reflection to get around 'if'
        // Create an object of class 'if'.
        try {
            Class<?> reallyIf = Class.forName("if");
            Constructor<?> ct = reallyIf.getConstructor(MinecraftServer.class);
            d = ct.newInstance(paramMinecraftServer);

            Class<?> aParamTypes[] = {ir.class, Integer.TYPE, Integer.TYPE, Integer.TYPE};
            if_a = reallyIf.getMethod("a", aParamTypes);
            if_a.setAccessible(true);

            Class<?> a1ParamTypes[] = {Integer.TYPE, Integer.TYPE, Integer.TYPE};
            if_a1 = reallyIf.getMethod("a", a1ParamTypes);
            if_a1.setAccessible(true);

            if_a2 = reallyIf.getMethod("a", null);
            if_a2.setAccessible(true);

            if_a3 = reallyIf.getMethod("a", er.class);
            if_a3.setAccessible(true);

            if_b = reallyIf.getMethod("b", null);
            if_b.setAccessible(true);

            if_b2 = reallyIf.getMethod("b", er.class);
            if_b2.setAccessible(true);

            if_c = reallyIf.getMethod("c", er.class);
            if_c.setAccessible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

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
        int p = 0;
        // hMod: reflection to get around 'if'
        try {
            p = (Integer) if_b.invoke(d);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return p;
    }

    public void a(er paramer) {
        this.b.add(paramer);
        this.l.b(paramer);

        this.c.e.A.d((int) paramer.p >> 4, (int) paramer.r >> 4);

        while (this.c.e.a(paramer, paramer.z).size() != 0) {
            paramer.a(paramer.p, paramer.q + 1.0D, paramer.r);
        }
        this.c.e.a(paramer);
        // hMod: reflection to get around 'if'
        try {
            if_a3.invoke(d, paramer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // hMod: Handle login (send MOTD and call hook)
        String[] motd = etc.getInstance().getMotd();
        if (!(motd.length == 1 && motd[0].equals(""))) {
            for (String str : etc.getInstance().getMotd()) {
                paramer.a.b(new bh(str));
            }
        }
        etc.getLoader().callHook(PluginLoader.Hook.LOGIN, new Object[]{paramer});
    }

    public void b(er paramer) {
        // hMod: reflection to get around 'if'
        try {
            if_c.invoke(d, paramer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void c(er paramer) {
        this.l.a(paramer);
        this.c.e.d(paramer);
        this.b.remove(paramer);
        // hMod: reflection to get around 'if'
        try {
            this.if_b2.invoke(d, paramer);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public er a(fq paramfq, String paramString1, String paramString2) {
        if (this.f.contains(paramString1.trim().toLowerCase())) {
            paramfq.b("You are banned from this server!");
            return null;
        }

        // hMod: whole section below is modified to handle whitelists etc
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

    public er d(er paramer) {
        this.c.k.a(paramer);
        this.c.k.b(paramer);
        // hMod: reflection to get around 'if'
        try {
            this.if_b2.invoke(d, paramer);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        this.b.remove(paramer);
        this.c.e.e(paramer);

        er localer = new er(this.c, this.c.e, paramer.as, new jt(this.c.e));

        localer.g = paramer.g;
        localer.a = paramer.a;

        this.c.e.A.d((int) localer.p >> 4, (int) localer.r >> 4);
        while (this.c.e.a(localer, localer.z).size() != 0) {
            localer.a(localer.p, localer.q + 1.0D, localer.r);
        }

        localer.a.b(new az());
        localer.a.a(localer.p, localer.q, localer.r, localer.v, localer.w);

        // hMod: reflection to get around 'if'
        try {
            this.if_a3.invoke(d, localer);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        this.c.e.a(localer);
        this.b.add(localer);

        return localer;
    }

    public void b() {
        // hMod: reflection to get around 'if'
        try {
            this.if_a2.invoke(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(int paramInt1, int paramInt2, int paramInt3) {
        // hMod: reflection to get around 'if'
        Object args[] = {paramInt1, paramInt2, paramInt3};
        try {
            this.if_a1.invoke(d, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        // hMod: reflection to get around 'if'
        Object args[] = {new jf(paramInt1, paramInt2, paramInt3, paramay), paramInt1, paramInt2, paramInt3};
        try {
            if_a.invoke(d, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void d() {
        for (int m = 0; m < this.b.size(); m++) {
            this.l.a((er) this.b.get(m));
        }
    }
}
