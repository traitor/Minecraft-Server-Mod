import java.io.IOException;
import java.net.Socket;
import java.net.InetAddress;
import java.util.Random;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class fq extends fa {
    public InetAddress addr;
    public String overiddenName = null;

    public fq(MinecraftServer minecraftserver, Socket socket, String s) {
        c = false;
        f = 0;
        g = null;
        h = null;
        i = "";
        e = minecraftserver;
        b = new bi(socket, s, this);
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
        b.a(new ju(s));
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
        if(ad1.a != 5) {
            b("Outdated client!");
            return;
        }
        overiddenName = (String)etc.getLoader().callHook(PluginLoader.Hook.NAME_RESOLUTION, new Object[] {ad1.b, addr});
        if(!e.l || overiddenName != null)
            b(ad1);
        else
            (new dr(this, ad1)).start();
    }

    public void b(ad ad1) {
        er er1 = e.f.a(this, ad1.b, ad1.c);
        if(er1 != null) {
            a.info((new StringBuilder()).append(b()).append(" logged in with entity id ").append(er1.g).toString());
            jg jg1 = new jg(e, b, er1);
            jg1.b(new ad("", "", er1.g, e.e.u, (byte)e.e.q.g));
            jg1.b(new co(e.e.m, e.e.n, e.e.o));
            e.f.a(new bh((new StringBuilder()).append("\247e").append(er1.as).append(" joined the game.").toString()));
            e.f.a(er1);
            jg1.a(er1.p, er1.q, er1.r, er1.v, er1.w);
            jg1.d();
            e.c.a(jg1);
            jg1.b(new gh(e.e.e));
        }
        c = true;
    }

    public void a(String s) {
        a.info((new StringBuilder()).append(b()).append(" lost connection").toString());
        c = true;
    }

    public void a(ir ir) {
        b("Protocol error");
    }

    public String b() {
        if(g != null)
            return (new StringBuilder()).append(g).append(" [").append(b.b().toString()).append("]").toString();
        else
            return b.b().toString();
    }

    static String a(fq fq1) {
        return fq1.i;
    }

    static ad a(fq fq1, ad ad1) {
        return fq1.h = ad1;
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
