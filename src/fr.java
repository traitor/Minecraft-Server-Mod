import java.io.IOException;
import java.net.Socket;
import java.net.InetAddress;
import java.util.Random;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class fr extends fb {
    public InetAddress addr;
    public String overiddenName = null;

    public fr(MinecraftServer minecraftserver, Socket socket, String s) {
        c = false;
        f = 0;
        g = null;
        h = null;
        i = "";
        e = minecraftserver;
        try {
            b = new bi(socket, s, this);
        } catch (IOException e) {
        }
        addr = socket.getInetAddress();
    }

    public void a() {
        if(h != null) {
            b(h);
            h = null;
        }
        if(f++ == 600)
            b("Took too long to log in");
        else
            b.a();
    }

    public void b(String s) {
        a.info((new StringBuilder()).append("Disconnecting ").append(b()).append(": ").append(s).toString());
        b.a(new jv(s));
        b.c();
        c = true;
    }

    public void a(f f1) {
        if(e.l && !(Boolean)etc.getLoader().callHook(PluginLoader.Hook.VERIFICATION_CHECK, new Object[] { f1.a, addr })) {
            i = Long.toHexString(d.nextLong());
            b.a(new f(i));
        } else {
            b.a(new f("-"));
        }
    }

    public void a(ad ad1) {
        g = ad1.b;
        if(ad1.a != 6) {
            if(ad1.a > 6)
                b("Outdated server!");
            else
                b("Outdated client!");
            return;
        }
        overiddenName = (String)etc.getLoader().callHook(PluginLoader.Hook.NAME_RESOLUTION, new Object[] {ad1.b, addr});
        if(!e.l || overiddenName != null)
            b(ad1);
        else
            (new ds(this, ad1)).start();
    }

    public void b(ad ad1) {
        es es1 = e.f.a(this, ad1.b, ad1.c);
        if(es1 != null) {
            a.info((new StringBuilder()).append(b()).append(" logged in with entity id ").append(es1.g).toString());
            jh jh1 = new jh(e, b, es1);
            jh1.b(new ad("", "", es1.g, e.e.u, (byte)e.e.q.g));
            jh1.b(new co(e.e.m, e.e.n, e.e.o));
            e.f.a(new bh((new StringBuilder()).append("\247e").append(es1.at).append(" joined the game.").toString()));
            e.f.a(es1);
            jh1.a(es1.p, es1.q, es1.r, es1.v, es1.w);
            jh1.d();
            e.c.a(jh1);
            jh1.b(new gi(e.e.e));
        }
        c = true;
    }

    public void a(String s) {
        a.info((new StringBuilder()).append(b()).append(" lost connection").toString());
        c = true;
    }

    public void a(is is) {
        b("Protocol error");
    }

    public String b() {
        if(g != null)
            return (new StringBuilder()).append(g).append(" [").append(b.b().toString()).append("]").toString();
        else
            return b.b().toString();
    }

    static String a(fr fr1) {
        return fr1.i;
    }

    static ad a(fr fr1, ad ad1) {
        return fr1.h = ad1;
    }

    public static Logger a = Logger.getLogger("Minecraft");
    private static Random d = new Random();
    public bi b;
    public boolean c;
    private MinecraftServer e;
    private int f;
    private String g;
    private ad h;
    private String i;

}
