
public class df extends as
        implements iw {

    private gp[] e = new gp[3];
    private int f = 0;
    private int g = 0;
    private int h = 0;

    public gp[] getContents() {
        return e;
    }

    public void setContents(gp[] e) {
        this.e = e;
    }

    public int a() {
        return this.e.length;
    }

    public gp a(int paramInt) {
        return this.e[paramInt];
    }

    public void a(s params) {
        super.a(params);
        dm localdm = params.k("Items");
        this.e = new gp[a()];
        for (int i = 0; i < localdm.b(); i++) {
            s locals = (s) localdm.a(i);
            int j = locals.b("Slot");
            if ((j < 0) || (j >= this.e.length)) {
                continue;
            }
            this.e[j] = new gp(locals);
        }

        this.f = params.c("BurnTime");
        this.h = params.c("CookTime");
        this.g = a(this.e[1]);
    }

    public void b(s params) {
        super.b(params);
        params.a("BurnTime", (short) this.f);
        params.a("CookTime", (short) this.h);
        dm localdm = new dm();

        for (int i = 0; i < this.e.length; i++) {
            if (this.e[i] != null) {
                s locals = new s();
                locals.a("Slot", (byte) i);
                this.e[i].a(locals);
                localdm.a(locals);
            }
        }
        params.a("Items", localdm);
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

        if (!this.a.x) {
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
                dv.a(this.f > 0, this.a, this.b, this.c, this.d);
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
        int i = b(this.e[0].a().aS);
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
        return this.e[2].a < ez.c[i].a();
    }

    public void f() {
        if (!g()) {
            return;
        }

        int i = b(this.e[0].a().aS);
        if (this.e[2] == null) {
            this.e[2] = new gp(i, 1);
        } else if (this.e[2].c == i) {
            this.e[2].a += 1;
        }

        this.e[0].a -= 1;
        if (this.e[0].a <= 0) {
            this.e[0] = null;
        }
    }

    private int b(int paramInt) {
        if (paramInt == ff.I.bc) {
            return ez.m.aS;
        }
        if (paramInt == ff.H.bc) {
            return ez.n.aS;
        }
        if (paramInt == ff.ax.bc) {
            return ez.l.aS;
        }
        if (paramInt == ff.F.bc) {
            return ff.N.bc;
        }
        if (paramInt == ez.ao.aS) {
            return ez.ap.aS;
        }
        if (paramInt == ff.x.bc) {
            return ff.u.bc;
        }
        if (paramInt == ez.aG.aS) {
            return ez.aF.aS;
        }
        return -1;
    }

    private int a(gp paramgp) {
        if (paramgp == null) {
            return 0;
        }
        int i = paramgp.a().aS;

        if ((i < 256) && (ff.n[i].bn == iq.c)) {
            return 300;
        }

        if (i == ez.B.aS) {
            return 100;
        }

        if (i == ez.k.aS) {
            return 1600;
        }

        if (i == ez.aw.aS) {
            return 20000;
        }

        return 0;
    }
}
