public class dv extends ay implements ke {
    private hn[] e = new hn[3];
    private int f = 0;
    private int g = 0;
    private int h = 0;

    /**
     * Get contents of the furnace block
     * @return
     */
    public hn[] getContents() {
        return e;
    }

    /**
     * Set contents of the furnace block
     * @return
     */
    public void setContents(hn[] e) {
        this.e = e;
    }

    @Override
    public int a() {
        return this.e.length;
    }

    @Override
    public hn a(int paramInt) {
        return this.e[paramInt];
    }

    @Override
    public void a(v paramv) {
        super.a(paramv);
        ed localed = paramv.k("Items");
        this.e = new hn[a()];
        for (int i = 0; i < localed.b(); i++) {
            v localv = (v) localed.a(i);
            int j = localv.b("Slot");
            if ((j < 0) || (j >= this.e.length)) {
                continue;
            }
            this.e[j] = new hn(localv);
        }

        this.f = paramv.c("BurnTime");
        this.h = paramv.c("CookTime");
        this.g = a(this.e[1]);
    }

    @Override
    public void b(v paramv) {
        super.b(paramv);
        paramv.a("BurnTime", (short) this.f);
        paramv.a("CookTime", (short) this.h);
        ed localed = new ed();

        for (int i = 0; i < this.e.length; i++) {
            if (this.e[i] != null) {
                v localv = new v();
                localv.a("Slot", (byte) i);
                this.e[i].a(localv);
                localed.a(localv);
            }
        }
        paramv.a("Items", localed);
    }

    public int d() {
        return 64;
    }

    public boolean e() {
        return this.f > 0;
    }

    @Override
    public void b() {
        int i = this.f > 0 ? 1 : 0;
        int j = 0;
        if (this.f > 0) {
            this.f -= 1;
        }

        if (!this.a.z) {
            if ((this.f == 0) && (g())) {
                this.g = (this.f = a(this.e[1]));
                if (this.f > 0) {
                    j = 1;
                    if (this.e[1] != null) {
                        this.e[1].a -= 1;
                        if (this.e[1].a == 0) {
                            this.e[1] = null;
                        }
                    }
                }
            }

            if ((e()) && (g())) {
                this.h += 1;
                if (this.h == 200) {
                    this.h = 0;
                    f();
                    j = 1;
                }
            } else {
                this.h = 0;
            }

            if (i != (this.f > 0 ? 1 : 0)) {
                j = 1;
                em.a(this.f > 0, this.a, this.b, this.c, this.d);
            }
        }

        if (j != 0) {
            c();
        }
    }

    private boolean g() {
        if (this.e[0] == null) {
            return false;
        }
        int i = b(this.e[0].a().aW);
        if (i < 0) {
            return false;
        }
        if (this.e[2] == null) {
            return true;
        }
        if (this.e[2].c != i) {
            return false;
        }
        if ((this.e[2].a < d()) && (this.e[2].a < this.e[2].b())) {
            return true;
        }
        return this.e[2].a < fw.c[i].a();
    }

    public void f() {
        if (!g()) {
            return;
        }

        int i = b(this.e[0].a().aW);
        if (this.e[2] == null) {
            this.e[2] = new hn(i, 1);
        } else if (this.e[2].c == i) {
            this.e[2].a += 1;
        }

        this.e[0].a -= 1;
        if (this.e[0].a <= 0) {
            this.e[0] = null;
        }
    }

    private int b(int paramInt) {
        if (paramInt == gc.H.bh) {
            return fw.m.aW;
        }
        if (paramInt == gc.G.bh) {
            return fw.n.aW;
        }
        if (paramInt == gc.aw.bh) {
            return fw.l.aW;
        }
        if (paramInt == gc.E.bh) {
            return gc.M.bh;
        }
        if (paramInt == fw.ao.aW) {
            return fw.ap.aW;
        }
        if (paramInt == fw.aS.aW) {
            return fw.aT.aW;
        }
        if (paramInt == gc.w.bh) {
            return gc.t.bh;
        }
        if (paramInt == fw.aG.aW) {
            return fw.aF.aW;
        }
        return -1;
    }

    private int a(hn paramhn) {
        if (paramhn == null) {
            return 0;
        }
        int i = paramhn.a().aW;

        if ((i < 256) && (gc.m[i].bs == jy.c)) {
            return 300;
        }

        if (i == fw.B.aW) {
            return 100;
        }

        if (i == fw.k.aW) {
            return 1600;
        }

        if (i == fw.aw.aW) {
            return 20000;
        }

        return 0;
    }
}
