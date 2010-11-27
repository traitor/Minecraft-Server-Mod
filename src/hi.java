
public class hi implements kc {

    public hl[] a = new hl[37];
    public hl[] b = new hl[4];
    public hl[] c = new hl[4];
    public int d = 0;
    private fx f;
    public boolean e = false;

    public hi(fx paramfx) {
        this.f = paramfx;
    }

    public hl b() {
        return this.a[this.d];
    }

    private int d(int paramInt) {
        // hMod: a.length has one extra slot for the hand item
        for (int i = 0; i < this.a.length - 1; i++) {
            if ((this.a[i] != null) && (this.a[i].c == paramInt)) {
                return i;
            }
        }
        return -1;
    }

    private int e(int paramInt) {
        // hMod: a.length has one extra slot for the hand item
        for (int i = 0; i < this.a.length - 1; i++) {
            if ((this.a[i] != null) && (this.a[i].c == paramInt) && (this.a[i].a < this.a[i].b()) && (this.a[i].a < d())) {
                return i;
            }
        }
        return -1;
    }

    private int g() {
        // hMod: a.length has one extra slot for the hand item
        for (int i = 0; i < this.a.length - 1; i++) {
            if (this.a[i] == null) {
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
        if (this.a[i] == null) {
            this.a[i] = new hl(paramInt1, 0);
        }

        int j = paramInt2;
        if (j > this.a[i].b() - this.a[i].a) {
            j = this.a[i].b() - this.a[i].a;
        }
        if (j > d() - this.a[i].a) {
            j = d() - this.a[i].a;
        }

        if (j == 0) {
            return paramInt2;
        }

        paramInt2 -= j;
        this.a[i].a += j;
        this.a[i].b = 5;

        return paramInt2;
    }

    public void c() {
        // hMod: a.length has one extra slot for the hand item
        for (int i = 0; i < this.a.length - 1; i++) {
            if ((this.a[i] == null) || (this.a[i].b <= 0)) {
                continue;
            }
            this.a[i].b -= 1;
        }
    }

    public boolean b(int paramInt) {
        int i = d(paramInt);
        if (i < 0) {
            return false;
        }
        if (--this.a[i].a <= 0) {
            this.a[i] = null;
        }

        return true;
    }

    public boolean a(hl paramhl) {
        if (paramhl.d == 0) {
            paramhl.a = a(paramhl.c, paramhl.a);
            if (paramhl.a == 0) {
                return true;
            }
        }

        int i = g();
        if (i >= 0) {
            this.a[i] = paramhl;
            this.a[i].b = 5;
            return true;
        }
        return false;
    }

    public void a(int paramInt, hl paramhl) {
        hl[] arrayOfhl = this.a;
        if (paramInt >= arrayOfhl.length) {
            paramInt -= arrayOfhl.length;
            arrayOfhl = this.b;
        }
        if (paramInt >= arrayOfhl.length) {
            paramInt -= arrayOfhl.length;
            arrayOfhl = this.c;
        }

        arrayOfhl[paramInt] = paramhl;
    }

    public float a(ga paramga) {
        float f1 = 1.0F;
        if (this.a[this.d] != null) {
            f1 *= this.a[this.d].a(paramga);
        }
        return f1;
    }

    public eb a(eb parameb) {
        // hMod: a.length has one extra slot for the hand item
        for (int i = 0; i < this.a.length - 1; i++) {
            if (this.a[i] != null) {
                v localv = new v();
                localv.a("Slot", (byte) i);
                this.a[i].a(localv);
                parameb.a(localv);
            }
        }
        for (int i = 0; i < this.b.length; i++) {
            if (this.b[i] != null) {
                v localv = new v();
                localv.a("Slot", (byte) (i + 100));
                this.b[i].a(localv);
                parameb.a(localv);
            }
        }
        for (int i = 0; i < this.c.length; i++) {
            if (this.c[i] != null) {
                v localv = new v();
                localv.a("Slot", (byte) (i + 80));
                this.c[i].a(localv);
                parameb.a(localv);
            }
        }
        return parameb;
    }

    public void b(eb parameb) {
        // hMod: Because Notch doesn't have to test anything >.>
        this.a = new hl[37];
        this.b = new hl[4];
        this.c = new hl[4];
        for (int i = 0; i < parameb.b(); i++) {
            v localv = (v) parameb.a(i);
            int j = localv.b("Slot") & 0xFF;
            // hMod: a.length has one extra slot for the hand item
            if ((j >= 0) && (j < this.a.length - 1)) {
                this.a[j] = new hl(localv);
            }
            if ((j >= 80) && (j < this.c.length + 80)) {
                this.c[(j - 80)] = new hl(localv);
            }
            if ((j < 100) || (j >= this.b.length + 100)) {
                continue;
            }
            this.b[(j - 100)] = new hl(localv);
        }
    }

    @Override
    public int a() {
        // hMod: a.length has one extra slot for the hand item
        return (this.a.length - 1) + 4;
    }

    @Override
    public hl a(int paramInt) {
        hl[] arrayOfhl = this.a;
        if (paramInt >= arrayOfhl.length) {
            paramInt -= arrayOfhl.length;
            arrayOfhl = this.b;
        }
        if (paramInt >= arrayOfhl.length) {
            paramInt -= arrayOfhl.length;
            arrayOfhl = this.c;
        }

        return arrayOfhl[paramInt];
    }

    public int d() {
        return 64;
    }

    public int a(dx paramdx) {
        hl localhl = a(this.d);
        if (localhl != null) {
            return localhl.a(paramdx);
        }
        return 1;
    }

    public boolean b(ga paramga) {
        if ((paramga.bs != jw.d) && (paramga.bs != jw.e) && (paramga.bs != jw.t) && (paramga.bs != jw.s)) {
            return true;
        }

        hl localhl = a(this.d);
        if (localhl != null) {
            return localhl.b(paramga);
        }
        return false;
    }

    public int e() {
        int i = 0;
        int j = 0;
        int k = 0;
        for (int l = 0; l < this.b.length; l++) {
            if ((this.b[l] != null) && ((this.b[l].a() instanceof hj))) {
                int i1 = this.b[l].c();
                int i2 = this.b[l].d;

                int i3 = i1 - i2;
                j += i3;
                k += i1;

                int i4 = ((hj) this.b[l].a()).bc;

                i += i4;
            }
        }
        if (k == 0) {
            return 0;
        }
        return (i - 1) * j / k + 1;
    }

    public void c(int paramInt) {
        for (int i = 0; i < this.b.length; i++) {
            if ((this.b[i] != null) && ((this.b[i].a() instanceof hj))) {
                this.b[i].a(paramInt);
                if (this.b[i].a == 0) {
                    this.b[i].a(this.f);
                    this.b[i] = null;
                }
            }
        }
    }

    // spill out inventory; called on death
    public void f() {
        // hMod: a.length has one extra slot for the hand item
        for (int i = 0; i < this.a.length - 1; i++) {
            if (this.a[i] != null) {
                this.f.a(this.a[i], true);
                this.a[i] = null;
            }
        }
        for (int i = 0; i < this.b.length; i++) {
            if (this.b[i] != null) {
                this.f.a(this.b[i], true);
                this.b[i] = null;
            }
        }
    }
}
