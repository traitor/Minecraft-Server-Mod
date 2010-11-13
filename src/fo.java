// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 11/13/2010 12:08:55 PM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) nonlb
// Source File Name:   SourceFile

import java.io.IOException;
import java.net.Socket;
import java.net.InetAddress;
import java.util.Random;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class fo extends ey {
    public InetAddress addr;

    public fo(MinecraftServer minecraftserver, Socket socket, String s) {
        c = false;
        f = 0;
        g = null;
        h = null;
        i = "";
        e = minecraftserver;
        b = new bh(socket, s, this);
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
        b.a(new jr(s));
        b.c();
        c = true;
    }

    public void a(f f1) {
        if(e.l && !(Boolean)etc.getLoader().callHook(PluginLoader.Hook.VERIFICATION_CHECK, new Object[] { addr })) {
            i = Long.toHexString(d.nextLong());
            b.a(new f(i));
        } else {
            b.a(new f("-"));
        }
    }

    public void a(ad ad1) {
        g = ad1.b;
        if(ad1.a != 4) {
            b("Outdated client!");
            return;
        }
        if(!e.l || (Boolean)etc.getLoader().callHook(PluginLoader.Hook.VERIFICATION_CHECK, new Object[] { addr }))
            b(ad1);
        else
            (new dq(this, ad1)).start();
    }

    public void b(ad ad1) {
        ep ep1 = e.f.a(this, ad1.b, ad1.c);
        if(ep1 != null) {
            a.info((new StringBuilder()).append(b()).append(" logged in with entity id ").append(ep1.g).toString());
            je je1 = new je(e, b, ep1);
            je1.b(new ad("", "", ep1.g, e.e.u, (byte)e.e.q.g));
            je1.b(new cn(e.e.m, e.e.n, e.e.o));
            e.f.a(ep1);
            je1.a(ep1.p, ep1.q, ep1.r, ep1.v, ep1.w);
            je1.d();
            e.c.a(je1);
            je1.b(new gf(e.e.e));
        }
        c = true;
    }

    public void a(String s) {
        a.info((new StringBuilder()).append(b()).append(" lost connection").toString());
        c = true;
    }

    public void a(io io) {
        b("Protocol error");
    }

    public String b() {
        if(g != null)
            return (new StringBuilder()).append(g).append(" [").append(b.b().toString()).append("]").toString();
        else
            return b.b().toString();
    }

    static String a(fo fo1) {
        return fo1.i;
    }

    static ad a(fo fo1, ad ad1) {
        return fo1.h = ad1;
    }

    public static Logger a = Logger.getLogger("Minecraft");
    private static Random d = new Random();
    public bh b;
    public boolean c;
    private MinecraftServer e;
    private int f;
    private String g;
    private ad h;
    private String i;

}