
public class ii
        implements lg, Container<il> {

    public il[] a = new il[36];
    public il[] b = new il[4];
    public int c = 0;
    private gq e;
    private il f;
    public boolean d = false;

    public il[] getContents() {
        return a;
    }

    public void setContents(il[] values) {
        a = values;
    }

    public il getContentsAt(int index) {
        return a(index);
    }

    public void setContentsAt(int index, il value) {
        a(index, value);
    }

    public int getContentsSize() {
        return a();
    }

    public ii(gq paramgp) {
        this.e = paramgp;
    }

    public il e() {
        return this.a[this.c];
    }

    private int d(int paramInt) {
        for (int i = 0; i < this.a.length; i++) {
            if ((this.a[i] != null) && (this.a[i].c == paramInt)) {
                return i;
            }
        }
        return -1;
    }

    private int e(int paramInt) {
        for (int i = 0; i < this.a.length; i++) {
            if ((this.a[i] != null) && (this.a[i].c == paramInt) && (this.a[i].a < this.a[i].b()) && (this.a[i].a < c())) {
                return i;
            }
        }
        return -1;
    }

    private int j() {
        for (int i = 0; i < this.a.length; i++) {
            if (this.a[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private int b(int paramInt1, int paramInt2) {
        int i = e(paramInt1);
        if (i < 0) {
            i = j();
        }
        if (i < 0) {
            return paramInt2;
        }
        if (this.a[i] == null) {
            this.a[i] = new il(paramInt1, 0);
        }

        int j = paramInt2;
        if (j > this.a[i].b() - this.a[i].a) {
            j = this.a[i].b() - this.a[i].a;
        }
        if (j > c() - this.a[i].a) {
            j = c() - this.a[i].a;
        }

        if (j == 0) {
            return paramInt2;
        }

        paramInt2 -= j;
        this.a[i].a += j;
        this.a[i].b = 5;

        return paramInt2;
    }

    public void f() {
        for (int i = 0; i < this.a.length; i++) {
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

    public boolean a(il paramik) {
        if (paramik.d == 0) {
            paramik.a = b(paramik.c, paramik.a);
            if (paramik.a == 0) {
                return true;
            }
        }

        int i = j();
        if (i >= 0) {
            this.a[i] = paramik;
            this.a[i].b = 5;
            return true;
        }
        return false;
    }

    public il a(int paramInt1, int paramInt2) {
        il[] arrayOfik = this.a;
        if (paramInt1 >= this.a.length) {
            arrayOfik = this.b;
            paramInt1 -= this.a.length;
        }

        if (arrayOfik[paramInt1] != null) {
            if (arrayOfik[paramInt1].a <= paramInt2) {
                il localik = arrayOfik[paramInt1];
                arrayOfik[paramInt1] = null;
                return localik;
            }
            il localik = arrayOfik[paramInt1].a(paramInt2);
            if (arrayOfik[paramInt1].a == 0) {
                arrayOfik[paramInt1] = null;
            }
            return localik;
        }

        return null;
    }

    public void a(int paramInt, il paramik) {
        il[] arrayOfik = this.a;
        if (paramInt >= arrayOfik.length) {
            paramInt -= arrayOfik.length;
            arrayOfik = this.b;
        }

        arrayOfik[paramInt] = paramik;
    }

    public float a(gv paramgu) {
        float f1 = 1.0F;
        if (this.a[this.c] != null) {
            f1 *= this.a[this.c].a(paramgu);
        }
        return f1;
    }

    public es a(es parames) {
        ad localad;
        for (int i = 0; i < this.a.length; i++) {
            if (this.a[i] != null) {
                localad = new ad();
                localad.a("Slot", (byte) i);
                this.a[i].a(localad);
                parames.a(localad);
            }
        }
        for (int i = 0; i < this.b.length; i++) {
            if (this.b[i] != null) {
                localad = new ad();
                localad.a("Slot", (byte) (i + 100));
                this.b[i].a(localad);
                parames.a(localad);
            }
        }
        return parames;
    }

    public void b(es parames) {
        this.a = new il[36];
        this.b = new il[4];
        for (int i = 0; i < parames.b(); i++) {
            ad localad = (ad) parames.a(i);
            int j = localad.b("Slot") & 0xFF;
            il localik = new il(localad);
            if (localik.a() != null) {
                if ((j >= 0) && (j < this.a.length)) this.a[j] = localik;
                if ((j < 100) || (j >= this.b.length + 100)) continue;
                this.b[(j - 100)] = localik;
            }
        }
    }

    public int a() {
        return this.a.length + 4;
    }

    public il a(int paramInt) {
        il[] arrayOfik = this.a;
        if (paramInt >= arrayOfik.length) {
            paramInt -= arrayOfik.length;
            arrayOfik = this.b;
        }

        return arrayOfik[paramInt];
    }

    public String b() {
        return "Inventory";
    }

    public int c() {
        return 64;
    }

    public int a(ep paramep) {
        il localik = a(this.c);
        if (localik != null) {
            return localik.a(paramep);
        }
        return 1;
    }

    public boolean b(gv paramgu) {
        if ((paramgu.bs != la.d) && (paramgu.bs != la.e) && (paramgu.bs != la.t) && (paramgu.bs != la.s)) {
            return true;
        }

        il localik = a(this.c);
        if (localik != null) {
            return localik.b(paramgu);
        }
        return false;
    }

    public int g() {
        int i = 0;
        int j = 0;
        int k = 0;
        for (int m = 0; m < this.b.length; m++) {
            if ((this.b[m] != null) && ((this.b[m].a() instanceof ij))) {
                int n = this.b[m].c();
                int i1 = this.b[m].d;

                int i2 = n - i1;
                j += i2;
                k += n;

                int i3 = ((ij) this.b[m].a()).bc;

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
            if ((this.b[i] != null) && ((this.b[i].a() instanceof ij))) {
                this.b[i].b(paramInt);
                if (this.b[i].a == 0) {
                    this.b[i].a(this.e);
                    this.b[i] = null;
                }
            }
        }
    }

    public void h() {
        for (int i = 0; i < this.a.length; i++) {
            if (this.a[i] != null) {
                this.e.a(this.a[i], true);
                this.a[i] = null;
            }
        }
        for (int i = 0; i < this.b.length; i++) {
            if (this.b[i] != null) {
                this.e.a(this.b[i], true);
                this.b[i] = null;
            }
        }
    }

    public void d() {
        this.d = true;
    }

    public void b(il paramik) {
        this.f = paramik;
        this.e.a(paramik);
    }

    public il i() {
        return this.f;
    }

    public boolean a_(gq paramgp) {
        if (this.e.G) {
            return false;
        }
        return paramgp.b((ep)this.e) <= 64.0D;
    }
}
