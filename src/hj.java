public class hj implements kd {
    public hm[] a = new hm[37];
    public hm[] b = new hm[4];
    public hm[] c = new hm[4];
    public int d = 0;
    private fy f;
    public boolean e = false;

    public hj(fy paramfy) {
        f = paramfy;
    }

    public hm b() {
        return a[d];
    }

    private int d(int paramInt) {
        // hMod: a.length has one extra slot for the hand item
        for (int i = 0; i < a.length - 1; i++) {
            if ((a[i] != null) && (a[i].c == paramInt)) {
                return i;
            }
        }
        return -1;
    }

    private int e(int paramInt) {
        // hMod: a.length has one extra slot for the hand item
        for (int i = 0; i < a.length - 1; i++) {
            if ((a[i] != null) && (a[i].c == paramInt) && (a[i].a < a[i].b()) && (a[i].a < d())) {
                return i;
            }
        }
        return -1;
    }

    private int g() {
        // hMod: a.length has one extra slot for the hand item
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private int a(int paramInt1, int paramInt2) {
        int i = e(paramInt1);
        if (i < 0) {
            i = g();
        }
        if (i < 0) {
            return paramInt2;
        }
        if (a[i] == null) {
            a[i] = new hm(paramInt1, 0);
        }

        int j = paramInt2;
        if (j > a[i].b() - a[i].a) {
            j = a[i].b() - a[i].a;
        }
        if (j > d() - a[i].a) {
            j = d() - a[i].a;
        }

        if (j == 0) {
            return paramInt2;
        }

        paramInt2 -= j;
        a[i].a += j;
        a[i].b = 5;

        return paramInt2;
    }

    public void c() {
        // hMod: a.length has one extra slot for the hand item
        for (int i = 0; i < a.length - 1; i++) {
            if ((a[i] == null) || (a[i].b <= 0)) {
                continue;
            }
            a[i].b -= 1;
        }
    }

    public boolean b(int paramInt) {
        int i = d(paramInt);
        if (i < 0) {
            return false;
        }
        if (--a[i].a <= 0) {
            a[i] = null;
        }

        return true;
    }

    public boolean a(hm paramhm) {
        if (paramhm.d == 0) {
            paramhm.a = a(paramhm.c, paramhm.a);
            if (paramhm.a == 0) {
                return true;
            }
        }

        int i = g();
        if (i >= 0) {
            a[i] = paramhm;
            a[i].b = 5;
            return true;
        }
        return false;
    }

    public void a(int paramInt, hm paramhm) {
        hm[] arrayOfhm = a;
        if (paramInt >= arrayOfhm.length) {
            paramInt -= arrayOfhm.length;
            arrayOfhm = b;
        }
        if (paramInt >= arrayOfhm.length) {
            paramInt -= arrayOfhm.length;
            arrayOfhm = c;
        }

        arrayOfhm[paramInt] = paramhm;
    }

    public float a(gb paramgb) {
        float f1 = 1.0F;
        if (a[d] != null) {
            f1 *= a[d].a(paramgb);
        }
        return f1;
    }

    public ec a(ec paramec) {
        v localv;
        // hMod: a.length has one extra slot for the hand item
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] != null) {
                localv = new v();
                localv.a("Slot", (byte) i);
                a[i].a(localv);
                paramec.a(localv);
            }
        }
        for (int i = 0; i < b.length; i++) {
            if (b[i] != null) {
                localv = new v();
                localv.a("Slot", (byte) (i + 100));
                b[i].a(localv);
                paramec.a(localv);
            }
        }
        for (int i = 0; i < c.length; i++) {
            if (c[i] != null) {
                localv = new v();
                localv.a("Slot", (byte) (i + 80));
                c[i].a(localv);
                paramec.a(localv);
            }
        }
        return paramec;
    }

    public void b(ec paramec) {
        // hMod: Because Notch doesn't ever test anything >.>
        a = new hm[37];
        b = new hm[4];
        c = new hm[4];
        for (int i = 0; i < paramec.b(); i++) {
            v localv = (v) paramec.a(i);

            int j = localv.b("Slot") & 0xFF;
            // hMod: a.length has one extra slot for the hand item
            if ((j >= 0) && (j < a.length - 1)) {
                a[j] = new hm(localv);
            }
            if ((j >= 80) && (j < c.length + 80)) {
                c[(j - 80)] = new hm(localv);
            }
            if ((j < 100) || (j >= b.length + 100)) {
                continue;
            }
            b[(j - 100)] = new hm(localv);
        }
    }

    public int a() {
        // hMod: a.length has one extra slot for the hand item
        return (a.length - 1) + 4;
    }

    public hm a(int paramInt) {
        hm[] arrayOfhm = a;
        if (paramInt >= arrayOfhm.length) {
            paramInt -= arrayOfhm.length;
            arrayOfhm = b;
        }
        if (paramInt >= arrayOfhm.length) {
            paramInt -= arrayOfhm.length;
            arrayOfhm = c;
        }

        return arrayOfhm[paramInt];
    }

    public int d() {
        return 64;
    }

    public int a(dy paramdy) {
        hm localhm = a(d);
        if (localhm != null) {
            return localhm.a(paramdy);
        }
        return 1;
    }

    public boolean b(gb paramgb) {
        if ((paramgb.bs != jx.d) && (paramgb.bs != jx.e) && (paramgb.bs != jx.t) && (paramgb.bs != jx.s)) {
            return true;
        }

        hm localhm = a(d);
        if (localhm != null) {
            return localhm.b(paramgb);
        }
        return false;
    }

    public int e() {
        int i = 0;
        int j = 0;
        int k = 0;
        for (int m = 0; m < b.length; m++) {
            if ((b[m] != null) && ((b[m].a() instanceof hk))) {
                int n = b[m].c();
                int i1 = b[m].d;

                int i2 = n - i1;
                j += i2;
                k += n;

                int i3 = ((hk) b[m].a()).bc;

                i += i3;
            }
        }
        if (k == 0) {
            return 0;
        }
        return (i - 1) * j / k + 1;
    }

    public void c(int paramInt) {
        for (int i = 0; i < b.length; i++) {
            if ((b[i] != null) && ((b[i].a() instanceof hk))) {
                b[i].a(paramInt);
                if (b[i].a == 0) {
                    b[i].a(f);
                    b[i] = null;
                }
            }
        }
    }

    // spill out inventory; called on death
    public void f() {
        // hMod: a.length has one extra slot for the hand item
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] != null) {
                f.a(a[i], true);
                a[i] = null;
            }
        }
        for (int i = 0; i < b.length; i++) {
            if (b[i] != null) {
                f.a(b[i], true);
                b[i] = null;
            }
        }
    }
}
