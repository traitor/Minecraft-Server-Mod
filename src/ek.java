
public class ek extends bg
        implements lg, Container<il> {

    private il[] h = new il[3];
    public int e = 0;
    public int f = 0;
    public int g = 0;

    /**
     * Get contents of the furnace block
     * @return
     */
    public il[] getContents() {
        return h;
    }

    /**
     * Set contents of the furnace block
     * @return
     */
    public void setContents(il[] e) {
        this.h = e;
    }

    public int a() {
        return this.h.length;
    }

    public il a(int paramInt) {
        return this.h[paramInt];
    }

    public il a(int paramInt1, int paramInt2) {
        if (this.h[paramInt1] != null) {
            if (this.h[paramInt1].a <= paramInt2) {
                il localik = this.h[paramInt1];
                this.h[paramInt1] = null;
                return localik;
            }
            il localik = this.h[paramInt1].a(paramInt2);
            if (this.h[paramInt1].a == 0) {
                this.h[paramInt1] = null;
            }
            return localik;
        }

        return null;
    }

    public void a(int paramInt, il paramik) {
        this.h[paramInt] = paramik;
        if ((paramik != null) && (paramik.a > c())) {
            paramik.a = c();
        }
    }

    public String b() {
        return "Furnace";
    }

    public void a(ad paramad) {
        super.a(paramad);
        es locales = paramad.k("Items");
        this.h = new il[a()];
        for (int i = 0; i < locales.b(); i++) {
            ad localad = (ad) locales.a(i);
            int j = localad.b("Slot");
            if ((j < 0) || (j >= this.h.length)) {
                continue;
            }
            this.h[j] = new il(localad);
        }

        this.e = paramad.c("BurnTime");
        this.g = paramad.c("CookTime");
        this.f = a(this.h[1]);
    }

    public void b(ad paramad) {
        super.b(paramad);
        paramad.a("BurnTime", (short) this.e);
        paramad.a("CookTime", (short) this.g);
        es locales = new es();

        for (int i = 0; i < this.h.length; i++) {
            if (this.h[i] != null) {
                ad localad = new ad();
                localad.a("Slot", (byte) i);
                this.h[i].a(localad);
                locales.a(localad);
            }
        }
        paramad.a("Items", locales);
    }

    public int c() {
        return 64;
    }

    public boolean g() {
        return this.e > 0;
    }

    public void e() {
        int i = this.e > 0 ? 1 : 0;
        int j = 0;
        if (this.e > 0) {
            this.e -= 1;
        }

        if (!this.a.z) {
            if ((this.e == 0) && (i())) {
                this.f = (this.e = a(this.h[1]));
                if (this.e > 0) {
                    j = 1;
                    if (this.h[1] != null) {
                        this.h[1].a -= 1;
                        if (this.h[1].a == 0) {
                            this.h[1] = null;
                        }
                    }
                }
            }

            if ((g()) && (i())) {
                this.g += 1;
                if (this.g == 200) {
                    this.g = 0;
                    h();
                    j = 1;
                }
            } else {
                this.g = 0;
            }

            if (i != (this.e > 0 ? 1 : 0)) {
                j = 1;
                fb.a(this.e > 0, this.a, this.b, this.c, this.d);
            }
        }

        if (j != 0) {
            d();
        }
    }

    private boolean i() {
        if (this.h[0] == null) {
            return false;
        }
        int i = b(this.h[0].a().aW);
        if (i < 0) {
            return false;
        }
        if (this.h[2] == null) {
            return true;
        }
        if (this.h[2].c != i) {
            return false;
        }
        if ((this.h[2].a < c()) && (this.h[2].a < this.h[2].b())) {
            return true;
        }
        return this.h[2].a < gm.c[i].b();
    }

    public void h() {
        if (!i()) {
            return;
        }

        int i = b(this.h[0].a().aW);
        if (this.h[2] == null) {
            this.h[2] = new il(i, 1);
        } else if (this.h[2].c == i) {
            this.h[2].a += 1;
        }

        this.h[0].a -= 1;
        if (this.h[0].a <= 0) {
            this.h[0] = null;
        }
    }

    private int b(int paramInt) {
        if (paramInt == gv.H.bh) {
            return gm.m.aW;
        }
        if (paramInt == gv.G.bh) {
            return gm.n.aW;
        }
        if (paramInt == gv.aw.bh) {
            return gm.l.aW;
        }
        if (paramInt == gv.E.bh) {
            return gv.M.bh;
        }
        if (paramInt == gm.ao.aW) {
            return gm.ap.aW;
        }
        if (paramInt == gm.aS.aW) {
            return gm.aT.aW;
        }
        if (paramInt == gv.w.bh) {
            return gv.t.bh;
        }
        if (paramInt == gm.aG.aW) {
            return gm.aF.aW;
        }
        return -1;
    }

    private int a(il paramik) {
        if (paramik == null) {
            return 0;
        }
        int i = paramik.a().aW;

        if ((i < 256) && (gv.m[i].bs == la.c)) {
            return 300;
        }

        if (i == gm.B.aW) {
            return 100;
        }

        if (i == gm.k.aW) {
            return 1600;
        }

        if (i == gm.aw.aW) {
            return 20000;
        }

        return 0;
    }

    public boolean a_(gq paramgp) {
        if (this.a.l(this.b, this.c, this.d) != this) {
            return false;
        }
        return paramgp.d(this.b + 0.5D, this.c + 0.5D, this.d + 0.5D) <= 64.0D;
    }
}
