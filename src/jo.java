import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class jo implements bu {
    private Set a = new HashSet();
    private js b;
    private bu c;
    private av d;
    private Map e = new HashMap();
    private List f = new ArrayList();
    private ev g;

    public jo(ev paramev, av paramav, bu parambu) {
        this.b = new js(paramev, new byte[32768], 0, 0);
        this.b.q = true;
        this.b.p = true;

        this.g = paramev;
        this.d = paramav;
        this.c = parambu;
    }

    @Override
    public boolean a(int paramInt1, int paramInt2) {
        jx localjx = new jx(paramInt1, paramInt2);
        return this.e.containsKey(localjx);
    }

    public void c(int paramInt1, int paramInt2) {
        int i = paramInt1 * 16 + 8 - this.g.m;
        int j = paramInt2 * 16 + 8 - this.g.o;
        int k = 128;
        if ((i < -k) || (i > k) || (j < -k) || (j > k)) {
            this.a.add(new jx(paramInt1, paramInt2));
        }
    }

    public js d(int paramInt1, int paramInt2) {
        jx localjx = new jx(paramInt1, paramInt2);
        this.a.remove(new jx(paramInt1, paramInt2));

        js localjs = (js) this.e.get(localjx);
        if (localjs == null) {
            localjs = e(paramInt1, paramInt2);
            if (localjs == null) {
                if (this.c == null) {
                    localjs = this.b;
                } else {
                    localjs = this.c.b(paramInt1, paramInt2);
                }

            }

            this.e.put(localjx, localjs);
            this.f.add(localjs);
            localjs.c();

            if (localjs != null) {
                localjs.d();
            }

            if ((!localjs.n) && (a(paramInt1 + 1, paramInt2 + 1)) && (a(paramInt1, paramInt2 + 1)) && (a(paramInt1 + 1, paramInt2))) {
                a(this, paramInt1, paramInt2);
            }
            if ((a(paramInt1 - 1, paramInt2)) && (!b(paramInt1 - 1, paramInt2).n) && (a(paramInt1 - 1, paramInt2 + 1))
                    && (a(paramInt1, paramInt2 + 1)) && (a(paramInt1 - 1, paramInt2))) {
                a(this, paramInt1 - 1, paramInt2);
            }
            if ((a(paramInt1, paramInt2 - 1)) && (!b(paramInt1, paramInt2 - 1).n) && (a(paramInt1 + 1, paramInt2 - 1))
                    && (a(paramInt1, paramInt2 - 1)) && (a(paramInt1 + 1, paramInt2))) {
                a(this, paramInt1, paramInt2 - 1);
            }
            if ((a(paramInt1 - 1, paramInt2 - 1)) && (!b(paramInt1 - 1, paramInt2 - 1).n) && (a(paramInt1 - 1, paramInt2 - 1))
                    && (a(paramInt1, paramInt2 - 1)) && (a(paramInt1 - 1, paramInt2))) {
                a(this, paramInt1 - 1, paramInt2 - 1);
            }

        }

        return localjs;
    }

    @Override
    public js b(int paramInt1, int paramInt2) {
        jx localjx = new jx(paramInt1, paramInt2);
        js localjs = (js) this.e.get(localjx);

        if (localjs == null) {
            if (this.g.x) {
                return d(paramInt1, paramInt2);
            }
            return this.b;
        }

        return localjs;
    }

    private js e(int paramInt1, int paramInt2) {
        if (this.d == null) {
            return null;
        }
        try {
            js localjs = this.d.a(this.g, paramInt1, paramInt2);
            if (localjs != null) {
                localjs.s = this.g.e;
            }
            return localjs;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }

    private void a(js paramjs) {
        if (this.d == null) {
            return;
        }
        try {
            this.d.b(this.g, paramjs);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    private void b(js paramjs) {
        if (this.d == null) {
            return;
        }

        // try {
        paramjs.s = this.g.e;
        this.d.a(this.g, paramjs);
        /*
         * } catch (IOException localIOException) {
         * localIOException.printStackTrace(); }
         */
    }

    @Override
    public void a(bu parambu, int paramInt1, int paramInt2) {
        js localjs = b(paramInt1, paramInt2);
        if (!localjs.n) {
            localjs.n = true;
            if (this.c != null) {
                this.c.a(parambu, paramInt1, paramInt2);
                localjs.f();
            }
        }
    }

    @Override
    public boolean a(boolean paramBoolean, ja paramja) {
        int i = 0;
        for (int j = 0; j < this.f.size(); j++) {
            js localjs = (js) this.f.get(j);
            if ((paramBoolean) && (!localjs.p)) {
                a(localjs);
            }
            if (localjs.a(paramBoolean)) {
                b(localjs);
                localjs.o = false;
                i++;
                if ((i == 2) && (!paramBoolean)) {
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

    boolean loaded = false;

    public boolean a() {
        if (!loaded) {
            etc.getLoader();
            loaded = true;
        }
        if (!this.g.C) {
            for (int i = 0; i < 16; i++) {
                if (!this.a.isEmpty()) {
                    jx localjx = (jx) this.a.iterator().next();

                    js localjs = b(localjx.a, localjx.b);
                    localjs.e();
                    b(localjs);
                    a(localjs);
                    this.a.remove(localjx);

                    this.e.remove(localjx);
                    this.f.remove(localjs);
                }
            }

            if (this.d != null) {
                this.d.a();
            }
        }
        return this.c.a();
    }

    @Override
    public boolean b() {
        return !this.g.C;
    }
}