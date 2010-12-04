public class hk implements ke {
    public hn[] a = new hn[37];
    public hn[] b = new hn[4];
    public hn[] c = new hn[4];

    public int d = 0;
    private fz f;
    public boolean e = false;

    public hk(fz paramfz) {
        this.f = paramfz;
    }

    public hn b() {
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
            this.a[i] = new hn(paramInt1, 0);
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

    public boolean a(hn paramhn) {
        if (paramhn.d == 0) {
            paramhn.a = a(paramhn.c, paramhn.a);
            if (paramhn.a == 0) {
                return true;
            }
        }

        int i = g();
        if (i >= 0) {
            this.a[i] = paramhn;
            this.a[i].b = 5;
            return true;
        }
        return false;
    }

    public void a(int paramInt, hn paramhn) {
        hn[] arrayOfhn = this.a;
        if (paramInt >= arrayOfhn.length) {
            paramInt -= arrayOfhn.length;
            arrayOfhn = this.b;
        }
        if (paramInt >= arrayOfhn.length) {
            paramInt -= arrayOfhn.length;
            arrayOfhn = this.c;
        }

        arrayOfhn[paramInt] = paramhn;
    }

    public float a(gc paramgc) {
        float f1 = 1.0F;
        if (this.a[this.d] != null) {
            f1 *= this.a[this.d].a(paramgc);
        }
        return f1;
    }

    public ed a(ed paramed) {
        v localv;
        // hMod: a.length has one extra slot for the hand item
        for (int i = 0; i < this.a.length - 1; i++) {
            if (this.a[i] != null) {
                localv = new v();
                localv.a("Slot", (byte) i);
                this.a[i].a(localv);
                paramed.a(localv);
            }
        }
        for (int i = 0; i < this.b.length; i++) {
            if (this.b[i] != null) {
                localv = new v();
                localv.a("Slot", (byte) (i + 100));
                this.b[i].a(localv);
                paramed.a(localv);
            }
        }
        for (int i = 0; i < this.c.length; i++) {
            if (this.c[i] != null) {
                localv = new v();
                localv.a("Slot", (byte) (i + 80));
                this.c[i].a(localv);
                paramed.a(localv);
            }
        }
        return paramed;
    }

    public void b(ed paramed) {
        // hMod: Because Notch doesn't ever test anything >.>
        this.a = new hn[37];
        this.b = new hn[4];
        this.c = new hn[4];
        for (int i = 0; i < paramed.b(); i++) {
            v localv = (v) paramed.a(i);
            int j = localv.b("Slot") & 0xFF;
            // hMod: a.length has one extra slot for the hand item
            if ((j >= 0) && (j < this.a.length - 1)) {
                this.a[j] = new hn(localv);
            }
            if ((j >= 80) && (j < this.c.length + 80)) {
                this.c[(j - 80)] = new hn(localv);
            }
            if ((j < 100) || (j >= this.b.length + 100)) {
                continue;
            }
            this.b[(j - 100)] = new hn(localv);
        }
    }

    @Override
    public int a() {
        // hMod: a.length has one extra slot for the hand item
        return (this.a.length - 1) + 4;
    }

    @Override
    public hn a(int paramInt) {
        hn[] arrayOfhn = this.a;
        if (paramInt >= arrayOfhn.length) {
            paramInt -= arrayOfhn.length;
            arrayOfhn = this.b;
        }
        if (paramInt >= arrayOfhn.length) {
            paramInt -= arrayOfhn.length;
            arrayOfhn = this.c;
        }

        return arrayOfhn[paramInt];
    }

    public int d() {
        return 64;
    }

    public int a(ea paramea) {
        hn localhn = a(this.d);
        if (localhn != null) {
            return localhn.a(paramea);
        }
        return 1;
    }

    public boolean b(gc paramgc) {
        if ((paramgc.bs != jy.d) && (paramgc.bs != jy.e) && (paramgc.bs != jy.t) && (paramgc.bs != jy.s)) {
            return true;
        }

        hn localhn = a(this.d);
        if (localhn != null) {
            return localhn.b(paramgc);
        }
        return false;
    }

    public int e() {
        int i = 0;
        int j = 0;
        int k = 0;
        for (int m = 0; m < this.b.length; m++) {
            if ((this.b[m] != null) && ((this.b[m].a() instanceof hl))) {
                int n = this.b[m].c();
                int i1 = this.b[m].d;

                int i2 = n - i1;
                j += i2;
                k += n;

                int i3 = ((hl)this.b[m].a()).bc;

                i += i3;
            }
        }
        if (k == 0) {
            return 0;
        }
        return (i - 1) * j / k + 1;
    }

    public void c(int paramInt) {
        for (int i = 0; i < this.b.length; i++) {
            if ((this.b[i] != null) && ((this.b[i].a() instanceof hl))) {
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
