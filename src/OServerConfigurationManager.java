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

public class OServerConfigurationManager {

    public static Logger         a = Logger.getLogger("Minecraft");
    // hMod set list to contain <OEntityPlayerMP> objects.
    public List<OEntityPlayerMP> b = new ArrayList<OEntityPlayerMP>();
    private MinecraftServer      c;
    private OPlayerManager       d;
    private int                  e;
    // hMod set these to Set<String> to remove errors and warnings.
    private Set<String>          f = new HashSet<String>();
    private Set<String>          g = new HashSet<String>();
    private Set<String>          h = new HashSet<String>();
    private File                 i;
    private File                 j;
    private File                 k;
    private OPlayerNBTManager    l;

    public OServerConfigurationManager(MinecraftServer paramMinecraftServer) {
        etc.setServer(paramMinecraftServer);
        etc.getInstance().loadData();
        a.info("Note: your current classpath is: " + System.getProperty("java.class.path", "*UNKNOWN*"));
        if (!etc.getInstance().getTainted())
            a.info("Hey0 Server Mod Build " + etc.getInstance().getVersion());
        else
            a.info("hMod Build Information: " + etc.getInstance().getVersionStr());
        c = paramMinecraftServer;
        i = paramMinecraftServer.a("banned-players.txt");
        j = paramMinecraftServer.a("banned-ips.txt");
        k = paramMinecraftServer.a("ops.txt");
        d = new OPlayerManager(paramMinecraftServer);
        e = paramMinecraftServer.d.a("max-players", 20);
        e();
        g();
        i();
        f();
        h();
        j();
    }

    public void a(OWorldServer paramOWorldServer) {
        l = new OPlayerNBTManager(new File(paramOWorldServer.t, "players"));
    }

    public int a() {
        return d.b();
    }

    public void a(OEntityPlayerMP paramOEntityPlayerMP) {
        b.add(paramOEntityPlayerMP);
        l.b(paramOEntityPlayerMP);

        c.e.A.d((int) paramOEntityPlayerMP.p >> 4, (int) paramOEntityPlayerMP.r >> 4);

        while (c.e.a(paramOEntityPlayerMP, paramOEntityPlayerMP.z).size() != 0)
            paramOEntityPlayerMP.a(paramOEntityPlayerMP.p, paramOEntityPlayerMP.q + 1.0D, paramOEntityPlayerMP.r);
        c.e.a(paramOEntityPlayerMP);
        d.a(paramOEntityPlayerMP);
        // hMod: Handle login (send MOTD and call hook)
        String[] motd = etc.getInstance().getMotd();
        if (!(motd.length == 1 && motd[0].equals("")))
            for (String str : etc.getInstance().getMotd())
                paramOEntityPlayerMP.a.b(new OPacket3Chat(str));
        etc.getLoader().callHook(PluginLoader.Hook.LOGIN, paramOEntityPlayerMP.getPlayer());
    }

    public void b(OEntityPlayerMP paramOEntityPlayerMP) {
        d.c(paramOEntityPlayerMP);
    }

    public void c(OEntityPlayerMP paramOEntityPlayerMP) {
        l.a(paramOEntityPlayerMP);
        c.e.d(paramOEntityPlayerMP);
        b.remove(paramOEntityPlayerMP);
        d.b(paramOEntityPlayerMP);
    }

    public OEntityPlayerMP a(ONetLoginHandler paramONetLoginHandler, String paramString1, String paramString2) {
        if (f.contains(paramString1.trim().toLowerCase())) {
            paramONetLoginHandler.a("You are banned from this server!");
            return null;
        }
        // hMod: whole section below is modified to handle whitelists etc
        OEntityPlayerMP temp = new OEntityPlayerMP(c, c.e, paramString1, new OItemInWorldManager(c.e));
        Player player = temp.getPlayer();

        String str = paramONetLoginHandler.b.b().toString();
        str = str.substring(str.indexOf("/") + 1);
        str = str.substring(0, str.indexOf(":"));
        if (g.contains(str)) {
            paramONetLoginHandler.a("Your IP address is banned from this server!");
            return null;
        }
        for (int m = 0; m < b.size(); m++) {
            OEntityPlayerMP localfy = b.get(m);
            if (localfy.aw.equalsIgnoreCase(paramString1))
                localfy.a.a("You logged in from another location");
        }

        // hMod whitelist block
        if (etc.getInstance().isWhitelistEnabled() && !(etc.getDataSource().isUserOnWhitelist(paramString1) || player.isAdmin())) {
            paramONetLoginHandler.a(etc.getInstance().getWhitelistMessage());
            return null;
        } else if (b.size() >= e && (!etc.getInstance().isReservelistEnabled() || !etc.getDataSource().isUserOnReserveList(paramString1))) {
            paramONetLoginHandler.a("The server is full!");
            return null;
        }

        if (!player.getIps()[0].equals("")) {
            boolean kick = true;
            for (int i = 0; i < player.getIps().length; i++)
                if (!player.getIps()[i].equals("") && str.equals(player.getIps()[i]))
                    kick = false;
            if (kick) {
                paramONetLoginHandler.a("IP doesn't match specified IP.");
                return null;
            }
        }
        // hMod: user passed basic login check, inform plugins.
        Object obj = etc.getLoader().callHook(PluginLoader.Hook.LOGINCHECK, paramString1);
        if (obj instanceof String) {
            String result = (String) obj;
            if (result != null && !result.equals("")) {
                paramONetLoginHandler.a(result);
                return null;
            }
        }
        return temp;
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
            if (l > 0)
                builder.append(", ");
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
            if (l > 0)
                builder.append(", ");
            builder.append(o);
            l++;
        }
        return builder.toString();
    }

    public OEntityPlayerMP d(OEntityPlayerMP paramOEntityPlayerMP) {
        c.k.a(paramOEntityPlayerMP);
        c.k.b(paramOEntityPlayerMP);
        d.b(paramOEntityPlayerMP);
        b.remove(paramOEntityPlayerMP);
        c.e.e(paramOEntityPlayerMP);

        OEntityPlayerMP localOEntityPlayerMP = new OEntityPlayerMP(c, c.e, paramOEntityPlayerMP.aw, new OItemInWorldManager(c.e));
        localOEntityPlayerMP.g = paramOEntityPlayerMP.g;
        localOEntityPlayerMP.a = paramOEntityPlayerMP.a;

        c.e.A.d((int) localOEntityPlayerMP.p >> 4, (int) localOEntityPlayerMP.r >> 4);

        while (c.e.a(localOEntityPlayerMP, localOEntityPlayerMP.z).size() != 0)
            localOEntityPlayerMP.a(localOEntityPlayerMP.p, localOEntityPlayerMP.q + 1.0D, localOEntityPlayerMP.r);

        localOEntityPlayerMP.a.b(new OPacket9());
        localOEntityPlayerMP.a.a(localOEntityPlayerMP.p, localOEntityPlayerMP.q, localOEntityPlayerMP.r, localOEntityPlayerMP.v, localOEntityPlayerMP.w);

        d.a(localOEntityPlayerMP);
        c.e.a(localOEntityPlayerMP);
        b.add(localOEntityPlayerMP);

        localOEntityPlayerMP.l();
        return localOEntityPlayerMP;
    }

    public void b() {
        d.a();
    }

    public void a(int paramInt1, int paramInt2, int paramInt3) {
        d.a(paramInt1, paramInt2, paramInt3);
    }

    public void a(OPacket paramOPacket) {
        for (int m = 0; m < b.size(); m++) {
            OEntityPlayerMP localOEntityPlayerMP = b.get(m);
            localOEntityPlayerMP.a.b(paramOPacket);
        }
    }

    public String c() {
        String str = "";
        for (int m = 0; m < b.size(); m++) {
            if (m > 0)
                str = str + ", ";
            str = str + (b.get(m)).aw;
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
            while ((str = localBufferedReader.readLine()) != null)
                f.add(str.trim().toLowerCase());
            localBufferedReader.close();
        } catch (Exception localException) {
            a.warning("Failed to load ban list: " + localException);
        }
    }

    private void f() {
        try {
            PrintWriter localPrintWriter = new PrintWriter(new FileWriter(i, false));
            for (String str : f)
                localPrintWriter.println(str);
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
            while ((str = localBufferedReader.readLine()) != null)
                g.add(str.trim().toLowerCase());
            localBufferedReader.close();
        } catch (Exception localException) {
            a.warning("Failed to load ip ban list: " + localException);
        }
    }

    private void h() {
        try {
            PrintWriter localPrintWriter = new PrintWriter(new FileWriter(j, false));
            for (String str : g)
                localPrintWriter.println(str);
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
            while ((str = localBufferedReader.readLine()) != null)
                h.add(str.trim().toLowerCase());
            localBufferedReader.close();
        } catch (Exception localException) {
            a.warning("Failed to load ip ban list: " + localException);
        }
    }

    private void j() {
        try {
            PrintWriter localPrintWriter = new PrintWriter(new FileWriter(k, false));
            for (String str : h)
                localPrintWriter.println(str);
            localPrintWriter.close();
        } catch (Exception localException) {
            a.warning("Failed to save ip ban list: " + localException);
        }
    }

    public boolean g(String paramString) {
        return h.contains(paramString.trim().toLowerCase());
    }

    public OEntityPlayerMP h(String paramString) {
        for (int m = 0; m < b.size(); m++) {
            OEntityPlayerMP localOEntityPlayerMP = b.get(m);
            if (localOEntityPlayerMP.aw.equalsIgnoreCase(paramString))
                return localOEntityPlayerMP;
        }
        return null;
    }

    public void a(String paramString1, String paramString2) {
        OEntityPlayerMP localOEntityPlayerMP = h(paramString1);
        if (localOEntityPlayerMP != null)
            localOEntityPlayerMP.a.b(new OPacket3Chat(paramString2));
    }

    public void a(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, OPacket paramOPacket) {
        for (int m = 0; m < b.size(); m++) {
            OEntityPlayerMP localOEntityPlayerMP = b.get(m);
            double d1 = paramDouble1 - localOEntityPlayerMP.p;
            double d2 = paramDouble2 - localOEntityPlayerMP.q;
            double d3 = paramDouble3 - localOEntityPlayerMP.r;
            if (d1 * d1 + d2 * d2 + d3 * d3 < paramDouble4 * paramDouble4)
                localOEntityPlayerMP.a.b(paramOPacket);
        }
    }

    public void i(String paramString) {
        OPacket3Chat localOPacket3Chat = new OPacket3Chat(paramString);
        for (int m = 0; m < b.size(); m++) {
            OEntityPlayerMP localOEntityPlayerMP = b.get(m);
            if (g(localOEntityPlayerMP.aw))
                localOEntityPlayerMP.a.b(localOPacket3Chat);
        }
    }

    public boolean a(String paramString, OPacket paramOPacket) {
        OEntityPlayerMP localOEntityPlayerMP = h(paramString);
        if (localOEntityPlayerMP != null) {
            localOEntityPlayerMP.a.b(paramOPacket);
            return true;
        }
        return false;
    }

    public void d() {
        for (int m = 0; m < b.size(); m++)
            l.a(b.get(m));
    }

    public void a(int paramInt1, int paramInt2, int paramInt3, OTileEntity paramOTileEntity) {
        // hMod: fix sign updating in beta 1.1_02
        // Check if bg (TileEntity) is a Sign
        if (paramOTileEntity instanceof OTileEntitySign)
            d.sendPacketToChunk(((OTileEntitySign) paramOTileEntity).g(), paramInt1, paramInt2, paramInt3);
    }
}
