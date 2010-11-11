
public class ds extends ay implements jz {

    private hj[] e = new hj[3];
    private int f = 0;
    private int g = 0;
    private int h = 0;

    public hj[] getContents() {
        return e;
    }

    public void setContents(hj[] e) {
        this.e = e;
    }

    public int a() {
        return this.e.length;
    }

    public hj a(int paramInt) {
        return this.e[paramInt];
    }

    public void a(v paramv) {
        super.a(paramv);
        ea localea = paramv.k("Items");
        this.e = new hj[a()];
        for (int i = 0; i < localea.b(); i++) {
            v localv = (v) localea.a(i);
            int j = localv.b("Slot");
            if ((j < 0) || (j >= this.e.length)) {
                continue;
            }

            this.e[j] = new hj(localv);
        }

        this.f = paramv.c("BurnTime");
        this.h = paramv.c("CookTime");
        this.g = a(this.e[1]);
    }

    public void b(v paramv) {
        super.b(paramv);
        paramv.a("BurnTime", (short) this.f);
        paramv.a("CookTime", (short) this.h);
        ea localea = new ea();

        for (int i = 0; i < this.e.length; i++) {
            if (this.e[i] != null) {
                v localv = new v();
                localv.a("Slot", (byte) i);
                this.e[i].a(localv);
                localea.a(localv);
            }
        }
        paramv.a("Items", localea);
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
                ei.a(this.f > 0, this.a, this.b, this.c, this.d);
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
        return this.e[2].a < fs.c[i].a();
    }

    public void f() {
        if (!g()) {
            return;
        }

        int i = b(this.e[0].a().aW);
        if (this.e[2] == null) {
            this.e[2] = new hj(i, 1);
        } else if (this.e[2].c == i) {
            this.e[2].a += 1;
        }

        this.e[0].a -= 1;
        if (this.e[0].a <= 0) {
            this.e[0] = null;
        }
    }

    private int b(int paramInt) {
        if (paramInt == fy.H.bh) {
            return fs.m.aW;
        }
        if (paramInt == fy.G.bh) {
            return fs.n.aW;
        }
        if (paramInt == fy.aw.bh) {
            return fs.l.aW;
        }
        if (paramInt == fy.E.bh) {
            return fy.M.bh;
        }
        if (paramInt == fs.ao.aW) {
            return fs.ap.aW;
        }
        if (paramInt == fs.aS.aW) {
            return fs.aT.aW;
        }
        if (paramInt == fy.w.bh) {
            return fy.t.bh;
        }
        if (paramInt == fs.aG.aW) {
            return fs.aF.aW;
        }
        return -1;
    }

    private int a(hj paramhj) {
        if (paramhj == null) {
            return 0;
        }
        int i = paramhj.a().aW;

        if ((i < 256) && (fy.m[i].bs == jt.c)) {
            return 300;
        }

        if (i == fs.B.aW) {
            return 100;
        }

        if (i == fs.k.aW) {
            return 1600;
        }

        if (i == fs.aw.aW) {
            return 20000;
        }

        return 0;
    }
}
