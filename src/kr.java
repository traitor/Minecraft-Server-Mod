
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class kr
        implements cg {

    private Set a = new HashSet();
    private kw b;
    private cg c;
    private bd d;
    private Map e = new HashMap();
    private List f = new ArrayList();
    private fm g;

    public kr(fm paramfm, bd parambd, cg paramcg) {
        this.b = new kw(paramfm, new byte[32768], 0, 0);
        this.b.q = true;
        this.b.p = true;

        this.g = paramfm;
        this.d = parambd;
        this.c = paramcg;
    }

    public boolean a(int paramInt1, int paramInt2) {
        la localla = new la(paramInt1, paramInt2);
        return this.e.containsKey(localla);
    }

    public void c(int paramInt1, int paramInt2) {
        int i = paramInt1 * 16 + 8 - this.g.m;
        int j = paramInt2 * 16 + 8 - this.g.o;
        int k = 128;
        if ((i < -k) || (i > k) || (j < -k) || (j > k)) {
            this.a.add(new la(paramInt1, paramInt2));
        }
    }

    public kw d(int paramInt1, int paramInt2) {
        la localla = new la(paramInt1, paramInt2);
        this.a.remove(new la(paramInt1, paramInt2));

        kw localkw = (kw) this.e.get(localla);
        if (localkw == null) {
            localkw = e(paramInt1, paramInt2);
            if (localkw == null) {
                if (this.c == null) {
                    localkw = this.b;
                } else {
                    localkw = this.c.b(paramInt1, paramInt2);
                }

            }

            this.e.put(localla, localkw);
            this.f.add(localkw);
            localkw.c();

            if (localkw != null) {
                localkw.d();
            }

            if ((!localkw.n) && (a(paramInt1 + 1, paramInt2 + 1)) && (a(paramInt1, paramInt2 + 1)) && (a(paramInt1 + 1, paramInt2))) {
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

        return localkw;
    }

    public kw b(int paramInt1, int paramInt2) {
        la localla = new la(paramInt1, paramInt2);
        kw localkw = (kw) this.e.get(localla);

        if (localkw == null) {
            if (this.g.x) {
                return d(paramInt1, paramInt2);
            }
            return this.b;
        }

        return localkw;
    }

    private kw e(int paramInt1, int paramInt2) {
        if (this.d == null) {
            return null;
        }
        try {
            kw localkw = this.d.a(this.g, paramInt1, paramInt2);
            if (localkw != null) {
                localkw.s = this.g.e;
            }
            return localkw;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }

    private void a(kw paramkw) {
        if (this.d == null) {
            return;
        }
        try {
            this.d.b(this.g, paramkw);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    private void b(kw paramkw) {
        if (this.d == null) {
            return;
        }
        // hMod: fix decompiler error, comment this out.
        //try {
            paramkw.s = this.g.e;
            this.d.a(this.g, paramkw);
        //} catch (IOException localIOException) {
        //localIOException.printStackTrace();
        //}
    }

    public void a(cg paramcg, int paramInt1, int paramInt2) {
        kw localkw = b(paramInt1, paramInt2);
        if (!localkw.n) {
            localkw.n = true;
            if (this.c != null) {
                this.c.a(paramcg, paramInt1, paramInt2);
                localkw.f();
            }
        }
    }

    public boolean a(boolean paramBoolean, ke paramke) {
        int i = 0;
        for (int j = 0; j < this.f.size(); j++) {
            kw localkw = (kw) this.f.get(j);
            if ((paramBoolean) && (!localkw.p)) {
                a(localkw);
            }
            if (localkw.a(paramBoolean)) {
                b(localkw);
                localkw.o = false;
                i++;
                if ((i == 24) && (!paramBoolean)) {
                    return false;
                }
            }
        }

        if (paramBoolean) {
            if (this.d == null) {
                return true;
            }
            this.d.b();
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
        if (!this.g.C) {
            for (int i = 0; i < 100; i++) {
                if (!this.a.isEmpty()) {
                    la localla = (la) this.a.iterator().next();

                    kw localkw = b(localla.a, localla.b);
                    localkw.e();
                    b(localkw);
                    a(localkw);
                    this.a.remove(localla);

                    this.e.remove(localla);
                    this.f.remove(localkw);
                }
            }

            if (this.d != null) {
                this.d.a();
            }
        }

        return this.c.a();
    }

    public boolean b() {
        return !this.g.C;
    }
}
