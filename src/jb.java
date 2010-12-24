
public class jb extends bg
        implements lg, Container<il> {

    private il[] e = new il[36];

    /**
     * Get contents of the chest block
     *
     * @return
     */
    public il[] getContents() {
        return e;
    }

    /**
     * Set contents of the chest block
     *
     * @return
     */
    public void setContents(il[] e) {
        this.e = e;
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

    public int a() {
        return 27;
    }

    public il a(int paramInt) {
        return this.e[paramInt];
    }

    public il a(int paramInt1, int paramInt2) {
        if (this.e[paramInt1] != null) {
            if (this.e[paramInt1].a <= paramInt2) {
                il localik = this.e[paramInt1];
                this.e[paramInt1] = null;
                d();
                return localik;
            }
            il localik = this.e[paramInt1].a(paramInt2);
            if (this.e[paramInt1].a == 0) {
                this.e[paramInt1] = null;
            }
            d();
            return localik;
        }

        return null;
    }

    public void a(int paramInt, il paramik) {
        this.e[paramInt] = paramik;
        if ((paramik != null) && (paramik.a > c())) {
            paramik.a = c();
        }
        d();
    }

    public String b() {
        return "Chest";
    }

    public void a(ad paramad) {
        super.a(paramad);
        es locales = paramad.k("Items");
        this.e = new il[a()];
        for (int i = 0; i < locales.b(); i++) {
            ad localad = (ad) locales.a(i);
            int j = localad.b("Slot") & 0xFF;
            if ((j < 0) || (j >= this.e.length)) {
                continue;
            }
            this.e[j] = new il(localad);
        }
    }

    public void b(ad paramad) {
        super.b(paramad);
        es locales = new es();

        for (int i = 0; i < this.e.length; i++) {
            if (this.e[i] != null) {
                ad localad = new ad();
                localad.a("Slot", (byte) i);
                this.e[i].a(localad);
                locales.a(localad);
            }
        }
        paramad.a("Items", locales);
    }

    public int c() {
        return 64;
    }

    public boolean a_(gq paramgp) {
        if (this.a.l(this.b, this.c, this.d) != this) {
            return false;
        }
        return paramgp.d(this.b + 0.5D, this.c + 0.5D, this.d + 0.5D) <= 64.0D;
    }
}
