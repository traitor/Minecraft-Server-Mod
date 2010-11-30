import java.util.List;

public class fy extends jz {
    public hj am = new hj(this);
    public byte an = 0;
    public int ao = 0;
    public float ap;
    public float aq;
    public boolean ar = false;
    public int as = 0;
    public String at;
    public int au;
    private int a = 0;
    public kf av = null;

    public fy(ep paramep) {
        super(paramep);

        this.H = 1.62F;
        c(paramep.m + 0.5D, paramep.n + 1, paramep.o + 0.5D, 0.0F, 0.0F);

        this.aR = 20;
        this.aK = "humanoid";
        this.aJ = 180.0F;
        this.Y = 20;

        this.aH = "/mob/char.png";
    }

    @Override
    public void z() {
        super.z();
        this.ap = this.aq;
        this.aq = 0.0F;
    }

    @Override
    protected void c() {
        if (this.ar) {
            this.as += 1;
            if (this.as == 8) {
                this.as = 0;
                this.ar = false;
            }
        } else {
            this.as = 0;
        }

        this.aQ = (this.as / 8.0F);
    }

    @Override
    public void E() {
        // hMod: prevent 'healing over time' when monster-spawn=true (nice notchup!)
        if (etc.getInstance().autoHeal() != PluginLoader.HookResult.PREVENT_ACTION) {
            if ((this.l.k == 0) && (this.aR < 20) && (this.X % 20 * 4 == 0)) {
                a(1);
            }
        }

        this.am.c();
        this.ap = this.aq;

        super.E();

        float f1 = hg.a(this.s * this.s + this.u * this.u);
        float f2 = (float) Math.atan(-this.t * 0.2000000029802322D) * 15.0F;
        if (f1 > 0.1F) {
            f1 = 0.1F;
        }
        if ((!this.A) || (this.aR <= 0)) {
            f1 = 0.0F;
        }
        if ((this.A) || (this.aR <= 0)) {
            f2 = 0.0F;
        }
        this.aq += (f1 - this.aq) * 0.4F;
        this.aZ += (f2 - this.aZ) * 0.8F;

        if (this.aR > 0) {
            List localList = this.l.b(this, this.z.b(1.0D, 0.0D, 1.0D));
            if (localList != null) {
                for (int i = 0; i < localList.size(); i++) {
                    j((dy) localList.get(i));
                }
            }
        }
    }

    private void j(dy paramdy) {
        paramdy.b(this);
    }

    @Override
    public void f(dy paramdy) {
        super.f(paramdy);
        a(0.2F, 0.2F);
        a(this.p, this.q, this.r);
        this.t = 0.1000000014901161D;

        if (this.at.equals("Notch")) {
            a(new hm(fv.h, 1), true);
        }
        this.am.f();

        if (paramdy != null) {
            this.s = (-hg.b((this.aV + this.v) * 3.141593F / 180.0F) * 0.1F);
            this.u = (-hg.a((this.aV + this.v) * 3.141593F / 180.0F) * 0.1F);
        } else {
            this.s = (this.u = 0.0D);
        }
        this.H = 0.1F;
    }

    @Override
    public void b(dy paramdy, int paramInt) {
        this.ao += paramInt;
    }

    public void a(hm paramhm) {
        a(paramhm, false);
    }

    public void a(hm paramhm, boolean paramBoolean) {
        if (paramhm == null) {
            return;
        }

        gk localgk = new gk(this.l, this.p, this.q - 0.300000011920929D + s(), this.r, paramhm);
        localgk.c = 40;

        float f1 = 0.1F;
        float f2;
        if (paramBoolean) {
            f2 = this.W.nextFloat() * 0.5F;
            float f3 = this.W.nextFloat() * 3.141593F * 2.0F;
            localgk.s = (-hg.a(f3) * f2);
            localgk.u = (hg.b(f3) * f2);
            localgk.t = 0.2000000029802322D;
        } else {
            f1 = 0.3F;
            localgk.s = (-hg.a(this.v / 180.0F * 3.141593F) * hg.b(this.w / 180.0F * 3.141593F) * f1);
            localgk.u = (hg.b(this.v / 180.0F * 3.141593F) * hg.b(this.w / 180.0F * 3.141593F) * f1);
            localgk.t = (-hg.a(this.w / 180.0F * 3.141593F) * f1 + 0.1F);
            f1 = 0.02F;

            f2 = this.W.nextFloat() * 3.141593F * 2.0F;
            f1 *= this.W.nextFloat();
            localgk.s += Math.cos(f2) * f1;
            localgk.t += (this.W.nextFloat() - this.W.nextFloat()) * 0.1F;
            localgk.u += Math.sin(f2) * f1;
        }

        a(localgk);
    }

    protected void a(gk paramgk) {
        this.l.a(paramgk);
    }

    public float a(gb paramgb) {
        float f = this.am.a(paramgb);
        if (a(jx.f)) {
            f /= 5.0F;
        }
        if (!this.A) {
            f /= 5.0F;
        }

        return f;
    }

    public boolean b(gb paramgb) {
        return this.am.b(paramgb);
    }

    @Override
    public void b(v paramv) {
        super.b(paramv);
        ec localec = paramv.k("Inventory");
        this.am.b(localec);
        this.au = paramv.d("Dimension");
    }

    @Override
    public void a(v paramv) {
        super.a(paramv);
        paramv.a("Inventory", this.am.a(new ec()));
        paramv.a("Dimension", this.au);
    }

    public void a(kd paramkd) {
    }

    public void G() {
    }

    public void c(dy paramdy, int paramInt) {
    }

    @Override
    public float s() {
        return 0.12F;
    }

    @Override
    public boolean a(dy paramdy, int paramInt) {
        this.bo = 0;
        if (this.aR <= 0) {
            return false;
        }

        if (((paramdy instanceof gc)) || ((paramdy instanceof dx))) {
            if (this.l.k == 0) {
                paramInt = 0;
            }
            if (this.l.k == 1) {
                paramInt = paramInt / 3 + 1;
            }
            if (this.l.k == 3) {
                paramInt = paramInt * 3 / 2;
            }
        }

        if (paramInt == 0) {
            return false;
        }

        return super.a(paramdy, paramInt);
    }

    @Override
    protected void c(int paramInt) {
        int i = 25 - this.am.e();
        int j = paramInt * i + this.a;
        this.am.c(paramInt);
        paramInt = j / 25;
        this.a = (j % 25);
        super.c(paramInt);
    }

    public void a(du paramdu) {
    }

    public void a(jm paramjm) {
    }

    public void g(dy paramdy) {
        paramdy.a(this);
    }

    public hm H() {
        return this.am.b();
    }

    public void I() {
        this.am.a(this.am.d, null);
    }

    @Override
    public double B() {
        return this.H - 0.5F;
    }

    public void F() {
        this.as = -1;
        this.ar = true;
    }

    public void h(dy paramdy) {
        int i = this.am.a(paramdy);
        if (i > 0) {
            paramdy.a(this, i);
            hm localhm = H();
            if ((localhm != null) && ((paramdy instanceof jz))) {
                localhm.a((jz) paramdy);
                if (localhm.a <= 0) {
                    localhm.a(this);
                    I();
                }
            }
        }
    }
}
