
public class dt extends ay implements kc {

    private hl[] e = new hl[3];
    private int f = 0;
    private int g = 0;
    private int h = 0;

    /**
     * Get contents of the furnace block
     * @return
     */
    public hl[] getContents() {
        return e;
    }

    /**
     * Set contents of the furnace block
     * @return
     */
    public void setContents(hl[] e) {
        this.e = e;
    }

    @Override
    public int a() {
        return this.e.length;
    }

    @Override
    public hl a(int paramInt) {
        return this.e[paramInt];
    }

    @Override
    public void a(v paramv) {
        super.a(paramv);
        eb localeb = paramv.k("Items");
        this.e = new hl[a()];
        for (int i = 0; i < localeb.b(); i++) {
            v localv = (v) localeb.a(i);
            int j = localv.b("Slot");
            if ((j < 0) || (j >= this.e.length)) {
                continue;
            }
            this.e[j] = new hl(localv);
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
        eb localeb = new eb();

        for (int i = 0; i < this.e.length; i++) {
            if (this.e[i] != null) {
                v localv = new v();
                localv.a("Slot", (byte) i);
                this.e[i].a(localv);
                localeb.a(localv);
            }
        }
        paramv.a("Items", localeb);
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
                ek.a(this.f > 0, this.a, this.b, this.c, this.d);
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
        return this.e[2].a < fu.c[i].a();
    }

    public void f() {
        if (!g()) {
            return;
        }

        int i = b(this.e[0].a().aW);
        if (this.e[2] == null) {
            this.e[2] = new hl(i, 1);
        } else if (this.e[2].c == i) {
            this.e[2].a += 1;
        }

        this.e[0].a -= 1;
        if (this.e[0].a <= 0) {
            this.e[0] = null;
        }
    }

    private int b(int paramInt) {
        if (paramInt == ga.H.bh) {
            return fu.m.aW;
        }
        if (paramInt == ga.G.bh) {
            return fu.n.aW;
        }
        if (paramInt == ga.aw.bh) {
            return fu.l.aW;
        }
        if (paramInt == ga.E.bh) {
            return ga.M.bh;
        }
        if (paramInt == fu.ao.aW) {
            return fu.ap.aW;
        }
        if (paramInt == fu.aS.aW) {
            return fu.aT.aW;
        }
        if (paramInt == ga.w.bh) {
            return ga.t.bh;
        }
        if (paramInt == fu.aG.aW) {
            return fu.aF.aW;
        }
        return -1;
    }

    private int a(hl paramhl) {
        if (paramhl == null) {
            return 0;
        }
        int i = paramhl.a().aW;

        if ((i < 256) && (ga.m[i].bs == jw.c)) {
            return 300;
        }

        if (i == fu.B.aW) {
            return 100;
        }

        if (i == fu.k.aW) {
            return 1600;
        }

        if (i == fu.aw.aW) {
            return 20000;
        }

        return 0;
    }
}
