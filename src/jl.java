
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class jl implements bt {

    private Set a = new HashSet();
    private jp b;
    private bt c;
    private av d;
    private Map e = new HashMap();
    private List f = new ArrayList();
    private et g;

    public jl(et paramet, av paramav, bt parambt) {
        this.b = new jp(paramet, new byte[32768], 0, 0);
        this.b.q = true;
        this.b.p = true;

        this.g = paramet;
        this.d = paramav;
        this.c = parambt;
    }

    public boolean a(int paramInt1, int paramInt2) {
        ju localju = new ju(paramInt1, paramInt2);
        return this.e.containsKey(localju);
    }

    public void c(int paramInt1, int paramInt2) {
        int i = paramInt1 * 16 + 8 - this.g.m;
        int j = paramInt2 * 16 + 8 - this.g.o;
        int k = 20;
        if ((i < -k) || (i > k) || (j < -k) || (j > k)) {
            this.a.add(new ju(paramInt1, paramInt2));
        }
    }

    public jp d(int paramInt1, int paramInt2) {
        ju localju = new ju(paramInt1, paramInt2);
        this.a.remove(new ju(paramInt1, paramInt2));

        jp localjp = (jp) this.e.get(localju);
        if (localjp == null) {
            localjp = e(paramInt1, paramInt2);
            if (localjp == null) {
                if (this.c == null) {
                    localjp = this.b;
                } else {
                    localjp = this.c.b(paramInt1, paramInt2);
                }

            }

            this.e.put(localju, localjp);
            this.f.add(localjp);
            localjp.c();

            if (localjp != null) {
                localjp.d();
            }

            if ((!localjp.n) && (a(paramInt1 + 1, paramInt2 + 1)) && (a(paramInt1, paramInt2 + 1)) && (a(paramInt1 + 1, paramInt2))) {
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

        return localjp;
    }

    public jp b(int paramInt1, int paramInt2) {
        ju localju = new ju(paramInt1, paramInt2);
        jp localjp = (jp) this.e.get(localju);

        if (localjp == null) {
            if (this.g.x) {
                return d(paramInt1, paramInt2);
            }
            return this.b;
        }

        return localjp;
    }

    private jp e(int paramInt1, int paramInt2) {
        if (this.d == null) {
            return null;
        }
        try {
            jp localjp = this.d.a(this.g, paramInt1, paramInt2);
            if (localjp != null) {
                localjp.s = this.g.e;
            }
            return localjp;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }

    private void a(jp paramjp) {
        if (this.d == null) {
            return;
        }
        try {
            this.d.b(this.g, paramjp);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    private void b(jp paramjp) {
        if (this.d == null) {
            return;
        }
        // try {
        paramjp.s = this.g.e;
        this.d.a(this.g, paramjp);
        /*
         * } catch (IOException localIOException) {
         * localIOException.printStackTrace(); }
         */
    }

    public void a(bt parambt, int paramInt1, int paramInt2) {
        jp localjp = b(paramInt1, paramInt2);
        if (!localjp.n) {
            localjp.n = true;
            if (this.c != null) {
                this.c.a(parambt, paramInt1, paramInt2);
                localjp.f();
            }
        }
    }

    public boolean a(boolean paramBoolean, ix paramix) {
        int i = 0;
        for (int j = 0; j < this.f.size(); j++) {
            jp localjp = (jp) this.f.get(j);
            if ((paramBoolean) && (!localjp.p)) {
                a(localjp);
            }
            if (localjp.a(paramBoolean)) {
                b(localjp);
                localjp.o = false;
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
                    ju localju = (ju) this.a.iterator().next();

                    jp localjp = b(localju.a, localju.b);
                    localjp.e();
                    b(localjp);
                    a(localjp);
                    this.a.remove(localju);

                    this.e.remove(localju);
                    this.f.remove(localjp);
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
