
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class jj
        implements br {

    private Set a = new HashSet();
    private jn b;
    private br c;
    private at d;
    private Map e = new HashMap();
    private List f = new ArrayList();
    private es g;

    public jj(es parames, at paramat, br parambr) {
        this.b = new jn(parames, new byte[32768], 0, 0);
        this.b.q = true;
        this.b.p = true;

        this.g = parames;
        this.d = paramat;
        this.c = parambr;
    }

    public boolean a(int paramInt1, int paramInt2) {
        js localjs = new js(paramInt1, paramInt2);
        return this.e.containsKey(localjs);
    }

    public void c(int paramInt1, int paramInt2) {
        int i = paramInt1 * 16 + 8 - this.g.m;
        int j = paramInt2 * 16 + 8 - this.g.o;
        int k = 20;
        if ((i < -k) || (i > k) || (j < -k) || (j > k)) {
            this.a.add(new js(paramInt1, paramInt2));
        }
    }

    public jn d(int paramInt1, int paramInt2) {
        js localjs = new js(paramInt1, paramInt2);
        this.a.remove(new js(paramInt1, paramInt2));

        jn localjn = (jn) this.e.get(localjs);
        if (localjn == null) {
            localjn = e(paramInt1, paramInt2);
            if (localjn == null) {
                if (this.c == null) {
                    localjn = this.b;
                } else {
                    localjn = this.c.b(paramInt1, paramInt2);
                }

            }

            this.e.put(localjs, localjn);
            this.f.add(localjn);
            localjn.c();

            if (localjn != null) {
                localjn.d();
            }

            if ((!localjn.n) && (a(paramInt1 + 1, paramInt2 + 1)) && (a(paramInt1, paramInt2 + 1)) && (a(paramInt1 + 1, paramInt2))) {
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

        return localjn;
    }

    public jn b(int paramInt1, int paramInt2) {
        js localjs = new js(paramInt1, paramInt2);
        jn localjn = (jn) this.e.get(localjs);

        if (localjn == null) {
            if (this.g.x) {
                return d(paramInt1, paramInt2);
            }
            return this.b;
        }

        return localjn;
    }

    private jn e(int paramInt1, int paramInt2) {
        if (this.d == null) {
            return null;
        }
        try {
            jn localjn = this.d.a(this.g, paramInt1, paramInt2);
            if (localjn != null) {
                localjn.s = this.g.e;
            }
            return localjn;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }

    private void a(jn paramjn) {
        if (this.d == null) {
            return;
        }
        try {
            this.d.b(this.g, paramjn);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    private void b(jn paramjn) {
        if (this.d == null) {
            return;
        }
        //try {
            paramjn.s = this.g.e;
            this.d.a(this.g, paramjn);
        /*} catch (IOException localIOException) {
            localIOException.printStackTrace();
        }*/
    }

    public void a(br parambr, int paramInt1, int paramInt2) {
        jn localjn = b(paramInt1, paramInt2);
        if (!localjn.n) {
            localjn.n = true;
            if (this.c != null) {
                this.c.a(parambr, paramInt1, paramInt2);
                localjn.f();
            }
        }
    }

    public boolean a(boolean paramBoolean, iv paramiv) {
        int i = 0;
        for (int j = 0; j < this.f.size(); j++) {
            jn localjn = (jn) this.f.get(j);
            if ((paramBoolean) && (!localjn.p)) {
                a(localjn);
            }
            if (localjn.a(paramBoolean)) {
                b(localjn);
                localjn.o = false;
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
                    js localjs = (js) this.a.iterator().next();

                    jn localjn = b(localjs.a, localjs.b);
                    localjn.e();
                    b(localjn);
                    a(localjn);
                    this.a.remove(localjs);

                    this.e.remove(localjs);
                    this.f.remove(localjn);
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
