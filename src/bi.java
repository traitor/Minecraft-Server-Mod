// Decompiled by DJ v3.11.11.95 Copyright 2009 Atanas Neshkov  Date: 12/1/2010 2:31:45 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) definits fieldsfirst safe 
// Source File Name:   SourceFile

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.*;

public class bi {

    public static final Object a = new Object();
    public static int b = 0;
    public static int c = 0;
    private Object d = new Object();
    private Socket e = null;
    private DataInputStream f = null;
    private DataOutputStream g = null;
    private boolean h = true;
    private List i = Collections.synchronizedList(new LinkedList());
    private List j = Collections.synchronizedList(new LinkedList());
    private List k = Collections.synchronizedList(new LinkedList());
    private fb l = null;
    private boolean m = false;
    private Thread n;
    private Thread o;
    private boolean p = false;
    private String q = "";
    private int r = 0;
    private int s = 0;
    private int t = 0;
    
    // hMod: cache
    private SocketAddress cachedRemote;

    public bi(Socket socket, String paramString, fb paramfb) throws IOException {
        this.e = socket;
        
        // hMod: Cache the 'remote' because this.e gets nulled to early
        cachedRemote = e.getRemoteSocketAddress();
        this.l = paramfb;
        socket.setTrafficClass(24);
        this.f = new DataInputStream(socket.getInputStream());
        this.g = new DataOutputStream(socket.getOutputStream());
        this.o = new iq(this, paramString + " read thread");
        this.n = new ir(this, paramString + " write thread");
        this.o.start();
        this.n.start();
    }

    public void a(fb paramfb) {
        this.l = paramfb;
    }

    public void a(is paramis) {
        if (this.m) {
            return;
        }
        synchronized (this.d) {
            this.s += paramis.a() + 1;
            if (paramis.j) {
                this.k.add(paramis);
            } else {
                this.j.add(paramis);
            }
        }
    }

    private void e() {
        try {
            is localis;
            boolean flag = true;
            if (!j.isEmpty()) {
                flag = false;
                synchronized (this.d) {
                    localis = (is) j.remove(0);
                    this.s -= localis.a() + 1;
                }
                is.a(localis, this.g);
            }
            if ((flag || this.t-- <= 0) && !this.k.isEmpty()) {
                flag = false;
                synchronized (this.d) {
                    localis = (is) this.k.remove(0);
                    this.s -= localis.a() + 1;
                }
                is.a(localis, this.g);
                this.t = 50;
            }
            if (flag) {
                Thread.sleep(10L);
            }
        } catch (InterruptedException interruptedexception) {
        } catch (Exception exception) {
            if (!this.p) {
                a(exception);
            }
        }
    }

    private void f() {
        try {
            is localis = is.b(this.f);
            if (localis != null) {
                this.i.add(localis);
            } else {
                a("End of stream");
            }
        } catch (Exception exception) {
            if (!this.p) {
                a(exception);
            }
        }
    }

    private void a(Exception paramException) {
        paramException.printStackTrace();
        a("Internal exception: " + paramException);
    }

    public void a(String s1) {
        if (!this.h) {
            return;
        }
        this.p = true;
        this.q = s1;
        new io(this).start();
        this.h = false;
        try {
            this.f.close();
            this.f = null;
        } catch (Throwable throwable) {
        }
        try {
            this.g.close();
            this.g = null;
        } catch (Throwable throwable1) {
        }
        try {
            this.e.close();
            this.e = null;
        } catch (Throwable throwable2) {
        }
    }

    public void a() {
        if (this.s > 0x100000) {
            a("Send buffer overflow");
        }
        if (this.i.isEmpty()) {
            if (this.r++ == 1200) {
                a("Timed out");
            }
        } else {
            this.r = 0;
        }
        is localis;
        for (int i1 = 100; !i.isEmpty() && i1-- >= 0; localis.a(l)) {
            localis = (is) i.remove(0);
        }

        if (this.p && this.i.isEmpty()) {
            this.l.a(this.q);
        }
    }

    // hMod: return the cached version
    public SocketAddress b() {
        return this.cachedRemote;
    }

    public void c() {
        this.m = true;
        this.o.interrupt();
        new ip(this).start();
    }

    public int d() {
        return this.k.size();
    }

    static boolean a(bi parambi) {
        return parambi.h;
    }

    static boolean b(bi parambi) {
        return parambi.m;
    }

    static void c(bi parambi) {
        parambi.f();
    }

    static void d(bi parambi) {
        parambi.e();
    }

    static Thread e(bi parambi) {
        return parambi.o;
    }

    static Thread f(bi parambi) {
        return parambi.n;
    }

}
