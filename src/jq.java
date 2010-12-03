import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class jq implements bu {
    private Set a = new HashSet();
    private ju b;
    private bu c;
    private av d;
    private Map e = new HashMap();
    private List f = new ArrayList();
    private ex g;

    public jq(ex paramex, av paramav, bu parambu) {
        this.b = new ju(paramex, new byte[32768], 0, 0);
        this.b.q = true;
        this.b.p = true;

        this.g = paramex;
        this.d = paramav;
        this.c = parambu;
    }

    @Override
    public boolean a(int paramInt1, int paramInt2) {
        jz localjz = new jz(paramInt1, paramInt2);
        return this.e.containsKey(localjz);
    }

    public void c(int paramInt1, int paramInt2) {
        int i = paramInt1 * 16 + 8 - this.g.m;
        int j = paramInt2 * 16 + 8 - this.g.o;
        int k = 128;
        if ((i < -k) || (i > k) || (j < -k) || (j > k)) {
            this.a.add(new jz(paramInt1, paramInt2));
        }
    }

    public ju d(int paramInt1, int paramInt2) {
        jz localjz = new jz(paramInt1, paramInt2);
        this.a.remove(new jz(paramInt1, paramInt2));

        ju localju = (ju) this.e.get(localjz);
        if (localju == null) {
            localju = e(paramInt1, paramInt2);
            if (localju == null) {
                if (this.c == null) {
                    localju = this.b;
                } else {
                    localju = this.c.b(paramInt1, paramInt2);
                }

            }

            this.e.put(localjz, localju);
            this.f.add(localju);
            localju.c();

            if (localju != null) {
                localju.d();
            }

            if ((!localju.n) && (a(paramInt1 + 1, paramInt2 + 1)) && (a(paramInt1, paramInt2 + 1)) && (a(paramInt1 + 1, paramInt2))) {
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

        return localju;
    }

    @Override
    public ju b(int paramInt1, int paramInt2) {
        jz localjz = new jz(paramInt1, paramInt2);
        ju localju = (ju) this.e.get(localjz);

        if (localju == null) {
            if (this.g.x) {
                return d(paramInt1, paramInt2);
            }
            return this.b;
        }

        return localju;
    }

    private ju e(int paramInt1, int paramInt2) {
        if (this.d == null) {
            return null;
        }
        try {
            ju localju = this.d.a(this.g, paramInt1, paramInt2);
            if (localju != null) {
                localju.s = this.g.e;
            }
            return localju;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }

    private void a(ju paramju) {
        if (this.d == null) {
            return;
        }
        try {
            this.d.b(this.g, paramju);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    private void b(ju paramju) {
        if (this.d == null) {
            return;
        }
        // hMod: fix decompiler error, comment this out.
        //try {
        paramju.s = this.g.e;
        this.d.a(this.g, paramju);
        //} catch (IOException localIOException) {
        //localIOException.printStackTrace();
        //}
    }

    @Override
    public void a(bu parambu, int paramInt1, int paramInt2) {
        ju localju = b(paramInt1, paramInt2);
        if (!localju.n) {
            localju.n = true;
            if (this.c != null) {
                this.c.a(parambu, paramInt1, paramInt2);
                localju.f();
            }
        }
    }

    @Override
    public boolean a(boolean paramBoolean, jc paramjc) {
        int i = 0;
        for (int j = 0; j < this.f.size(); j++) {
            ju localju = (ju) this.f.get(j);
            if ((paramBoolean) && (!localju.p)) {
                a(localju);
            }
            if (localju.a(paramBoolean)) {
                b(localju);
                localju.o = false;
                i++;
                if ((i == 32) && (!paramBoolean)) {
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

    @Override
    public boolean a() {
        // hMod: Load once!
        if (!loaded) {
            etc.getLoader();
            loaded = true;
        }
        if (!this.g.C) {
            for (int i = 0; i < 100; i++) {
                if (!this.a.isEmpty()) {
                    jz localjz = (jz) this.a.iterator().next();

                    ju localju = b(localjz.a, localjz.b);
                    localju.e();
                    b(localju);
                    a(localju);
                    this.a.remove(localjz);

                    this.e.remove(localjz);
                    this.f.remove(localju);
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
