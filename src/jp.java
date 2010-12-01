import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class jp implements bu {
    private Set a = new HashSet();
    private jt b;
    private bu c;
    private av d;
    private Map e = new HashMap();
    private List f = new ArrayList();
    private ew g;

    public jp(ew paramew, av paramav, bu parambu) {
        b = new jt(paramew, new byte[32768], 0, 0);
        b.q = true;
        b.p = true;

        g = paramew;
        d = paramav;
        c = parambu;
    }

    @Override
    public boolean a(int paramInt1, int paramInt2) {
        jy localjy = new jy(paramInt1, paramInt2);
        return e.containsKey(localjy);
    }

    public void c(int paramInt1, int paramInt2) {
        int i = paramInt1 * 16 + 8 - g.m;
        int j = paramInt2 * 16 + 8 - g.o;
        int k = 128;
        if ((i < -k) || (i > k) || (j < -k) || (j > k)) {
            a.add(new jy(paramInt1, paramInt2));
        }
    }

    public jt d(int paramInt1, int paramInt2) {
        jy localjy = new jy(paramInt1, paramInt2);
        a.remove(new jy(paramInt1, paramInt2));

        jt localjt = (jt) e.get(localjy);
        if (localjt == null) {
            localjt = e(paramInt1, paramInt2);
            if (localjt == null) {
                if (c == null) {
                    localjt = b;
                } else {
                    localjt = c.b(paramInt1, paramInt2);
                }

            }

            e.put(localjy, localjt);
            f.add(localjt);
            localjt.c();

            if (localjt != null) {
                localjt.d();
            }

            if ((!localjt.n) && (a(paramInt1 + 1, paramInt2 + 1)) && (a(paramInt1, paramInt2 + 1)) && (a(paramInt1 + 1, paramInt2))) {
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

        return localjt;
    }

    @Override
    public jt b(int paramInt1, int paramInt2) {
        jy localjy = new jy(paramInt1, paramInt2);
        jt localjt = (jt) e.get(localjy);

        if (localjt == null) {
            if (g.x) {
                return d(paramInt1, paramInt2);
            }
            return b;
        }

        return localjt;
    }

    private jt e(int paramInt1, int paramInt2) {
        if (d == null) {
            return null;
        }
        try {
            jt localjt = d.a(g, paramInt1, paramInt2);
            if (localjt != null) {
                localjt.s = g.e;
            }
            return localjt;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }

    private void a(jt paramjt) {
        if (d == null) {
            return;
        }
        try {
            d.b(g, paramjt);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    private void b(jt paramjt) {
        if (d == null) {
            return;
        }
        // hMod: fix decompiler error, comment this out.
        // try {
        paramjt.s = g.e;
        d.a(g, paramjt);
        /*
         * } catch (IOException localIOException) { localIOException.printStackTrace(); }
         */
    }

    @Override
    public void a(bu parambu, int paramInt1, int paramInt2) {
        jt localjt = b(paramInt1, paramInt2);
        if (!localjt.n) {
            localjt.n = true;
            if (c != null) {
                c.a(parambu, paramInt1, paramInt2);
                localjt.f();
            }
        }
    }

    @Override
    public boolean a(boolean paramBoolean, jb paramjb) {
        int i = 0;
        for (int j = 0; j < f.size(); j++) {
            jt localjt = (jt) f.get(j);
            if ((paramBoolean) && (!localjt.p)) {
                a(localjt);
            }
            if (localjt.a(paramBoolean)) {
                b(localjt);
                localjt.o = false;
                i++;
                if ((i == 32) && (!paramBoolean)) {
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

    @Override
    public boolean a() {
        // hMod: Load once!
        if (!loaded) {
            etc.getLoader();
            loaded = true;
        }
        if (!g.C) {
            for (int i = 0; i < 100; i++) {
                if (!a.isEmpty()) {
                    jy localjy = (jy) a.iterator().next();

                    jt localjt = b(localjy.a, localjy.b);
                    localjt.e();
                    b(localjt);
                    a(localjt);
                    a.remove(localjy);

                    e.remove(localjy);
                    f.remove(localjt);
                }
            }

            if (d != null) {
                d.a();
            }
        }

        return c.a();
    }

    @Override
    public boolean b() {
        return !g.C;
    }
}
