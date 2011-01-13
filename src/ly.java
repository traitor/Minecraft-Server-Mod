
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ly
        implements cq {

    private Set a = new HashSet();
    private me b;
    private cq c;
    private bj d;
    private Map e = new HashMap();
    private List f = new ArrayList();
    private gc g;

    public ly(gc paramgc, bj parambj, cq paramcq) {
        b = new lz(paramgc, new byte[32768], 0, 0);

        g = paramgc;
        d = parambj;
        c = paramcq;
    }

    public boolean a(int paramInt1, int paramInt2) {
        mi localmi = new mi(paramInt1, paramInt2);
        return e.containsKey(localmi);
    }

    public void c(int paramInt1, int paramInt2) {
        int i = paramInt1 * 16 + 8 - g.m;
        int j = paramInt2 * 16 + 8 - g.o;
        int k = 128;
        if ((i < -k) || (i > k) || (j < -k) || (j > k)) {
            a.add(new mi(paramInt1, paramInt2));
        }
    }

    public me d(int paramInt1, int paramInt2) {
        mi localmi = new mi(paramInt1, paramInt2);
        a.remove(new mi(paramInt1, paramInt2));

        me localme = (me) e.get(localmi);
        if (localme == null) {
            localme = e(paramInt1, paramInt2);
            if (localme == null) {
                if (c == null) {
                    localme = b;
                } else {
                    localme = c.b(paramInt1, paramInt2);
                }

            }

            e.put(localmi, localme);
            f.add(localme);

            if (localme != null) {
                localme.c();
                localme.d();
            }

            if ((!localme.n) && (a(paramInt1 + 1, paramInt2 + 1)) && (a(paramInt1, paramInt2 + 1)) && (a(paramInt1 + 1, paramInt2))) {
                a(this, paramInt1, paramInt2);
            }
            if ((a(paramInt1 - 1, paramInt2)) && (!b(paramInt1 - 1, paramInt2).n) && (a(paramInt1 - 1, paramInt2 + 1)) && (a(paramInt1, paramInt2 + 1)) && (a(paramInt1 - 1, paramInt2))) {
                a(this, paramInt1 - 1, paramInt2);
            }
            if ((a(paramInt1, paramInt2 - 1)) && (!b(paramInt1, paramInt2 - 1).n) && (a(paramInt1 + 1, paramInt2 - 1)) && (a(paramInt1, paramInt2 - 1)) && (a(paramInt1 + 1, paramInt2))) {
                a(this, paramInt1, paramInt2 - 1);
            }
            if ((a(paramInt1 - 1, paramInt2 - 1)) && (!b(paramInt1 - 1, paramInt2 - 1).n) && (a(paramInt1 - 1, paramInt2 - 1)) && (a(paramInt1, paramInt2 - 1)) && (a(paramInt1 - 1, paramInt2))) {
                a(this, paramInt1 - 1, paramInt2 - 1);
            }

        }

        return localme;
    }

    public me b(int paramInt1, int paramInt2) {
        mi localmi = new mi(paramInt1, paramInt2);
        me localme = (me) e.get(localmi);

        if (localme == null) {
            if (g.x) {
                return d(paramInt1, paramInt2);
            }
            return b;
        }

        return localme;
    }

    private me e(int paramInt1, int paramInt2) {
        if (d == null) {
            return null;
        }
        try {
            me localme = d.a(g, paramInt1, paramInt2);
            if (localme != null) {
                localme.r = g.e;
            }
            return localme;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }

    private void a(me paramme) {
        if (d == null) {
            return;
        }
        try {
            d.b(g, paramme);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    private void b(me paramme) {
        if (d == null) {
            return;
        }
        // hMod: fix decompiler error, comment this out.
        paramme.r = g.e;
        d.a(g, paramme);
    }

    public void a(cq paramcq, int paramInt1, int paramInt2) {
        me localme = b(paramInt1, paramInt2);
        if (!localme.n) {
            localme.n = true;
            if (c != null) {
                c.a(paramcq, paramInt1, paramInt2);
                localme.f();
            }
        }
    }

    public boolean a(boolean paramBoolean, lk paramlk) {
        int i = 0;
        for (int j = 0; j < f.size(); j++) {
            me localme = (me) f.get(j);
            if ((paramBoolean) && (!localme.p)) {
                a(localme);
            }
            if (localme.a(paramBoolean)) {
                b(localme);
                localme.o = false;
                i++;
                if ((i == 24) && (!paramBoolean)) {
                    return false;
                }
            }
        }

        if (paramBoolean) {
            if (d == null) {
                return true;
            }
            d.b();
        }
        return true;
    }
// hMod: Load status
    boolean loaded = false;

    public boolean a() {
        // hMod: Load once!
        if (!loaded) {
            etc.getLoader();
            loaded = true;
        }
        if (!g.C) {
            for (int i = 0; i < 100; i++) {
                if (!a.isEmpty()) {
                    mi localmi = (mi) a.iterator().next();

                    me localme = b(localmi.a, localmi.b);
                    localme.e();
                    b(localme);
                    a(localme);
                    a.remove(localmi);

                    e.remove(localmi);
                    f.remove(localme);
                }
            }

            if (d != null) {
                d.a();
            }
        }

        return c.a();
    }

    public boolean b() {
        return !g.C;
    }
}
