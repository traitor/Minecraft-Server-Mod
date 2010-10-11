
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ij
        implements bj {

    private Set a = new HashSet();
    private im b;
    private bj c;
    private aq d;
    private Map e = new HashMap();
    private List f = new ArrayList();
    private ee g;

    public ij(ee paramee, aq paramaq, bj parambj) {
        this.b = new im(paramee, new byte[32768], 0, 0);
        this.b.q = true;
        this.b.p = true;

        this.g = paramee;
        this.d = paramaq;
        this.c = parambj;
    }

    public boolean a(int paramInt1, int paramInt2) {
        ir localir = new ir(paramInt1, paramInt2);
        return this.e.containsKey(localir);
    }

    public void c(int paramInt1, int paramInt2) {
        int i = paramInt1 * 16 + 8 - this.g.n;
        int j = paramInt2 * 16 + 8 - this.g.p;
        int k = 20;
        if ((i < -k) || (i > k) || (j < -k) || (j > k)) {
            this.a.add(new ir(paramInt1, paramInt2));
        }
    }

    public im d(int paramInt1, int paramInt2) {
        ir localir = new ir(paramInt1, paramInt2);
        this.a.remove(new ir(paramInt1, paramInt2));

        im localim = (im) this.e.get(localir);
        if (localim == null) {
            localim = e(paramInt1, paramInt2);
            if (localim == null) {
                if (this.c == null) {
                    localim = this.b;
                } else {
                    localim = this.c.b(paramInt1, paramInt2);
                }

            }

            this.e.put(localir, localim);
            this.f.add(localim);

            if (localim != null) {
                localim.c();
            }

            if ((!localim.n) && (a(paramInt1 + 1, paramInt2 + 1)) && (a(paramInt1, paramInt2 + 1)) && (a(paramInt1 + 1, paramInt2))) {
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

        return localim;
    }

    public im b(int paramInt1, int paramInt2) {
        ir localir = new ir(paramInt1, paramInt2);
        im localim = (im) this.e.get(localir);

        if (localim == null) {
            if (this.g.w) {
                return d(paramInt1, paramInt2);
            }
            return this.b;
        }

        return localim;
    }

    private im e(int paramInt1, int paramInt2) {
        if (this.d == null) {
            return null;
        }
        try {
            im localim = this.d.a(this.g, paramInt1, paramInt2);
            if (localim != null) {
                localim.s = this.g.c;
            }
            return localim;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }

    private void a(im paramim) {
        if (this.d == null) {
            return;
        }
        try {
            this.d.b(this.g, paramim);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    private void b(im paramim) {
        if (this.d == null) {
            return;
        }
        paramim.s = this.g.c;
        this.d.a(this.g, paramim);
    }

    public void a(bj parambj, int paramInt1, int paramInt2) {
        im localim = b(paramInt1, paramInt2);
        if (!localim.n) {
            localim.n = true;
            if (this.c != null) {
                this.c.a(parambj, paramInt1, paramInt2);
                localim.e();
            }
        }
    }

    public boolean a(boolean paramBoolean, hw paramhw) {
        int i = 0;
        for (int j = 0; j < this.f.size(); j++) {
            im localim = (im) this.f.get(j);
            if ((paramBoolean) && (!localim.p)) {
                a(localim);
            }
            if (localim.a(paramBoolean)) {
                b(localim);
                localim.o = false;
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
        
        if (!this.g.A) {
            for (int i = 0; i < 16; i++) {
                if (!this.a.isEmpty()) {
                    ir localir = (ir) this.a.iterator().next();

                    im localim = b(localir.a, localir.b);
                    localim.d();
                    b(localim);
                    a(localim);
                    this.a.remove(localir);

                    this.e.remove(localir);
                    this.f.remove(localim);
                }
            }

            if (this.d != null) {
                this.d.a();
            }
        }
        return this.c.a();
    }

    public boolean b() {
        return !this.g.A;
    }
}
