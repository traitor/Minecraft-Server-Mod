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
    public List<OEntityPlayerMP> b = new ArrayList();
    private MinecraftServer      c;
    private OPlayerManager       d;
    private int                  e;
    // hMod set these to Set<String> to remove errors and warnings.
    private Set<String>          f = new HashSet();
    private Set<String>          g = new HashSet();
    private Set<String>          h = new HashSet();
    private Set<String>          i = new HashSet();
    private File                 j;
    private File                 k;
    private File                 l;
    private File                 m;
    private OIPlayerFileData     n;
    private boolean              o;

    public OServerConfigurationManager(MinecraftServer paramMinecraftServer) {
        etc.setServer(paramMinecraftServer);
        etc.getInstance().loadData();
        a.info("Note: your current classpath is: " + System.getProperty("java.class.path", "*UNKNOWN*"));
        if (!etc.getInstance().getTainted())
            a.info("Hey0 Server Mod Build " + etc.getInstance().getVersion());
        else
            a.info("hMod Build Information: " + etc.getInstance().getVersionStr());
        c = paramMinecraftServer;
        j = paramMinecraftServer.a("banned-players.txt");
        k = paramMinecraftServer.a("banned-ips.txt");
        l = paramMinecraftServer.a("ops.txt");
        m = paramMinecraftServer.a("white-list.txt");
        d = new OPlayerManager(paramMinecraftServer);
        e = paramMinecraftServer.d.a("max-players", 20);
        o = paramMinecraftServer.d.a("white-list", false);
        g();
        i();
        k();
        m();
        h();
        j();
        l();
        n();
    }

    public void a(OWorldServer paramOWorldServer) {
        n = paramOWorldServer.o().d();
    }

    public int a() {
        return d.b();
    }

    public void a(OEntityPlayerMP paramOEntityPlayerMP) {
        b.add(paramOEntityPlayerMP);
        n.b(paramOEntityPlayerMP);

        c.e.u.c((int) paramOEntityPlayerMP.aK >> 4, (int) paramOEntityPlayerMP.aM >> 4);

        while (c.e.a(paramOEntityPlayerMP, paramOEntityPlayerMP.aU).size() != 0)
            paramOEntityPlayerMP.a(paramOEntityPlayerMP.aK, paramOEntityPlayerMP.aL + 1.0D, paramOEntityPlayerMP.aM);
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
        n.a(paramOEntityPlayerMP);
        c.e.d(paramOEntityPlayerMP);
        b.remove(paramOEntityPlayerMP);
        d.b(paramOEntityPlayerMP);
    }

    public OEntityPlayerMP a(ONetLoginHandler paramONetLoginHandler, String paramString1, String paramString2) {
        if (!etc.getLoader().isLoaded())
            paramONetLoginHandler.a("The server is not finished loading yet!");

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

        if (!g(paramString1) || (etc.getInstance().isWhitelistEnabled() && !(etc.getDataSource().isUserOnWhitelist(paramString1) || player.isAdmin()))) {
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

        for (int i1 = 0; i1 < b.size(); i1++) {
            OEntityPlayerMP localOEntityPlayerMP = b.get(i1);
            if (localOEntityPlayerMP.r.equalsIgnoreCase(paramString1))
                localOEntityPlayerMP.a.a("You logged in from another location");
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

        OChunkCoordinates localOChunkCoordinates1 = paramOEntityPlayerMP.H();
        
        OEntityPlayerMP localOEntityPlayerMP = new OEntityPlayerMP(c, c.e, paramOEntityPlayerMP.r, new OItemInWorldManager(c.e));
        localOEntityPlayerMP.aB = paramOEntityPlayerMP.aB;
        localOEntityPlayerMP.a = paramOEntityPlayerMP.a;

        if (localOChunkCoordinates1 != null) {
            OChunkCoordinates localOChunkCoordinates2 = OEntityPlayer.a(c.e, localOChunkCoordinates1);
            if (localOChunkCoordinates2 != null) {
                localOEntityPlayerMP.c(localOChunkCoordinates2.a + 0.5F, localOChunkCoordinates2.b + 0.1F, localOChunkCoordinates2.c + 0.5F, 0.0F, 0.0F);
                localOEntityPlayerMP.a(localOChunkCoordinates1);
            } else
                localOEntityPlayerMP.a.b(new OPacket70(0));

        }

        c.e.u.c((int) localOEntityPlayerMP.aK >> 4, (int) localOEntityPlayerMP.aM >> 4);

        while (c.e.a(localOEntityPlayerMP, localOEntityPlayerMP.aU).size() != 0)
            localOEntityPlayerMP.a(localOEntityPlayerMP.aK, localOEntityPlayerMP.aL + 1.0D, localOEntityPlayerMP.aM);

        localOEntityPlayerMP.a.b(new OPacket9());
        localOEntityPlayerMP.a.a(localOEntityPlayerMP.aK, localOEntityPlayerMP.aL, localOEntityPlayerMP.aM, localOEntityPlayerMP.aQ, localOEntityPlayerMP.aR);

        d.a(localOEntityPlayerMP);
        c.e.a(localOEntityPlayerMP);
        b.add(localOEntityPlayerMP);

        localOEntityPlayerMP.m();
        localOEntityPlayerMP.t();
        return localOEntityPlayerMP;
    }

    public void b() {
        d.a();
    }

    public void a(int paramInt1, int paramInt2, int paramInt3) {
        d.a(paramInt1, paramInt2, paramInt3);
    }

    public void a(OPacket paramOPacket) {
        for (int i1 = 0; i1 < b.size(); i1++) {
            OEntityPlayerMP localOEntityPlayerMP = b.get(i1);
            localOEntityPlayerMP.a.b(paramOPacket);
        }
    }

    public String c() {
        String str = "";
        for (int i1 = 0; i1 < b.size(); i1++) {
            if (i1 > 0)
                str = str + ", ";
            str = str + b.get(i1).r;
        }
        return str;
    }

    public void a(String paramString) {
        f.add(paramString.toLowerCase());
        h();
    }

    public void b(String paramString) {
        f.remove(paramString.toLowerCase());
        h();
    }

    private void g() {
        try {
            f.clear();
            BufferedReader localBufferedReader = new BufferedReader(new FileReader(j));
            String str = "";
            while ((str = localBufferedReader.readLine()) != null)
                f.add(str.trim().toLowerCase());
            localBufferedReader.close();
        } catch (Exception localException) {
            a.warning("Failed to load ban list: " + localException);
        }
    }

    private void h() {
        try {
            PrintWriter localPrintWriter = new PrintWriter(new FileWriter(j, false));
            for (String str : f)
                localPrintWriter.println(str);
            localPrintWriter.close();
        } catch (Exception localException) {
            a.warning("Failed to save ban list: " + localException);
        }
    }

    public void c(String paramString) {
        g.add(paramString.toLowerCase());
        j();
    }

    public void d(String paramString) {
        g.remove(paramString.toLowerCase());
        j();
    }

    private void i() {
        try {
            g.clear();
            BufferedReader localBufferedReader = new BufferedReader(new FileReader(k));
            String str = "";
            while ((str = localBufferedReader.readLine()) != null)
                g.add(str.trim().toLowerCase());
            localBufferedReader.close();
        } catch (Exception localException) {
            a.warning("Failed to load ip ban list: " + localException);
        }
    }

    private void j() {
        try {
            PrintWriter localPrintWriter = new PrintWriter(new FileWriter(k, false));
            for (String str : g)
                localPrintWriter.println(str);
            localPrintWriter.close();
        } catch (Exception localException) {
            a.warning("Failed to save ip ban list: " + localException);
        }
    }

    public void e(String paramString) {
        h.add(paramString.toLowerCase());
        l();
    }

    public void f(String paramString) {
        h.remove(paramString.toLowerCase());
        l();
    }

    private void k() {
        try {
            h.clear();
            BufferedReader localBufferedReader = new BufferedReader(new FileReader(l));
            String str = "";
            while ((str = localBufferedReader.readLine()) != null)
                h.add(str.trim().toLowerCase());
            localBufferedReader.close();
        } catch (Exception localException) {
            a.warning("Failed to load ip ban list: " + localException);
        }
    }

    private void l() {
        try {
            PrintWriter localPrintWriter = new PrintWriter(new FileWriter(l, false));
            for (String str : h)
                localPrintWriter.println(str);
            localPrintWriter.close();
        } catch (Exception localException) {
            a.warning("Failed to save ip ban list: " + localException);
        }
    }

    private void m() {
        try {
            i.clear();
            BufferedReader localBufferedReader = new BufferedReader(new FileReader(m));
            String str = "";
            while ((str = localBufferedReader.readLine()) != null)
                i.add(str.trim().toLowerCase());
            localBufferedReader.close();
        } catch (Exception localException) {
            a.warning("Failed to load white-list: " + localException);
        }
    }

    private void n() {
        try {
            PrintWriter localPrintWriter = new PrintWriter(new FileWriter(m, false));
            for (String str : i)
                localPrintWriter.println(str);
            localPrintWriter.close();
        } catch (Exception localException) {
            a.warning("Failed to save white-list: " + localException);
        }
    }

    public boolean g(String paramString) {
        paramString = paramString.trim().toLowerCase();
        return (!o) || (h.contains(paramString)) || (i.contains(paramString));
    }

    public boolean h(String paramString) {
        return h.contains(paramString.trim().toLowerCase());
    }

    public OEntityPlayerMP i(String paramString) {
        for (int i1 = 0; i1 < b.size(); i1++) {
            OEntityPlayerMP localOEntityPlayerMP = b.get(i1);
            if (localOEntityPlayerMP.r.equalsIgnoreCase(paramString))
                return localOEntityPlayerMP;
        }
        return null;
    }

    public void a(String paramString1, String paramString2) {
        OEntityPlayerMP localOEntityPlayerMP = i(paramString1);
        if (localOEntityPlayerMP != null)
            localOEntityPlayerMP.a.b(new OPacket3Chat(paramString2));
    }

    public void a(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, OPacket paramOPacket) {
        for (int i1 = 0; i1 < b.size(); i1++) {
            OEntityPlayerMP localOEntityPlayerMP = b.get(i1);
            double d1 = paramDouble1 - localOEntityPlayerMP.aK;
            double d2 = paramDouble2 - localOEntityPlayerMP.aL;
            double d3 = paramDouble3 - localOEntityPlayerMP.aM;
            if (d1 * d1 + d2 * d2 + d3 * d3 < paramDouble4 * paramDouble4)
                localOEntityPlayerMP.a.b(paramOPacket);
        }
    }

    public void j(String paramString) {
        OPacket3Chat localOPacket3Chat = new OPacket3Chat(paramString);
        for (int i1 = 0; i1 < b.size(); i1++) {
            OEntityPlayerMP localOEntityPlayerMP = b.get(i1);
            if (h(localOEntityPlayerMP.r))
                localOEntityPlayerMP.a.b(localOPacket3Chat);
        }
    }

    public boolean a(String paramString, OPacket paramOPacket) {
        OEntityPlayerMP localOEntityPlayerMP = i(paramString);
        if (localOEntityPlayerMP != null) {
            localOEntityPlayerMP.a.b(paramOPacket);
            return true;
        }
        return false;
    }

    public void d() {
        for (int i1 = 0; i1 < b.size(); i1++)
            n.a(b.get(i1));
    }

    public void a(int paramInt1, int paramInt2, int paramInt3, OTileEntity paramOTileEntity) {
        // hMod: fix sign updating in beta 1.1_02
        // Check if bg (TileEntity) is a Sign
        if (paramOTileEntity instanceof OTileEntitySign)
            d.sendPacketToChunk(((OTileEntitySign) paramOTileEntity).e(), paramInt1, paramInt2, paramInt3);
    }

    public void k(String paramString) {
        i.add(paramString);
        n();
    }

    public void l(String paramString) {
        i.remove(paramString);
        n();
    }

    public Set e() {
        return i;
    }

    public void f() {
        m();
    }
}
