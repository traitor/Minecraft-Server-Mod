
public class dr extends av
        implements jx {

    private hh[] e = new hh[3];
    private int f = 0;
    private int g = 0;
    private int h = 0;

    public hh[] getContents() {
        return e;
    }

    public void setContents(hh[] e) {
        this.e = e;
    }

    public int a() {
        return this.e.length;
    }

    public hh a(int paramInt) {
        return this.e[paramInt];
    }

    public void a(t paramt) {
        super.a(paramt);
        dy localdy = paramt.k("Items");
        this.e = new hh[a()];
        for (int i = 0; i < localdy.b(); i++) {
            t localt = (t) localdy.a(i);
            int j = localt.b("Slot");
            if ((j < 0) || (j >= this.e.length)) {
                continue;
            }
            this.e[j] = new hh(localt);
        }

        this.f = paramt.c("BurnTime");
        this.h = paramt.c("CookTime");
        this.g = a(this.e[1]);
    }

    public void b(t paramt) {
        super.b(paramt);
        paramt.a("BurnTime", (short) this.f);
        paramt.a("CookTime", (short) this.h);
        dy localdy = new dy();

        for (int i = 0; i < this.e.length; i++) {
            if (this.e[i] != null) {
                t localt = new t();
                localt.a("Slot", (byte) i);
                this.e[i].a(localt);
                localdy.a(localt);
            }
        }
        paramt.a("Items", localdy);
    }

    public int d() {
        return 64;
    }

    public boolean e() {
        return this.f > 0;
    }

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
                eh.a(this.f > 0, this.a, this.b, this.c, this.d);
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
        return this.e[2].a < fq.c[i].a();
    }

    public void f() {
        if (!g()) {
            return;
        }

        int i = b(this.e[0].a().aW);
        if (this.e[2] == null) {
            this.e[2] = new hh(i, 1);
        } else if (this.e[2].c == i) {
            this.e[2].a += 1;
        }

        this.e[0].a -= 1;
        if (this.e[0].a <= 0) {
            this.e[0] = null;
        }
    }

    private int b(int paramInt) {
        if (paramInt == fw.I.bi) {
            return fq.m.aW;
        }
        if (paramInt == fw.H.bi) {
            return fq.n.aW;
        }
        if (paramInt == fw.ax.bi) {
            return fq.l.aW;
        }
        if (paramInt == fw.F.bi) {
            return fw.N.bi;
        }
        if (paramInt == fq.ao.aW) {
            return fq.ap.aW;
        }
        if (paramInt == fq.aS.aW) {
            return fq.aT.aW;
        }
        if (paramInt == fw.x.bi) {
            return fw.u.bi;
        }
        if (paramInt == fq.aG.aW) {
            return fq.aF.aW;
        }
        return -1;
    }

    private int a(hh paramhh) {
        if (paramhh == null) {
            return 0;
        }
        int i = paramhh.a().aW;

        if ((i < 256) && (fw.n[i].bt == jr.c)) {
            return 300;
        }

        if (i == fq.B.aW) {
            return 100;
        }

        if (i == fq.k.aW) {
            return 1600;
        }

        if (i == fq.aw.aW) {
            return 20000;
        }

        return 0;
    }
}
