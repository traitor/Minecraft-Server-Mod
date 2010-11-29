import java.util.List;

public class fx extends jy {
    public hi al = new hi(this);
    public byte am = 0;
    public int an = 0;
    public float ao;
    public float ap;
    public boolean aq = false;
    public int ar = 0;
    public String as;
    public int at;
    private int a = 0;
    public ke au = null;

    public fx(eo parameo) {
        super(parameo);

        this.H = 1.62F;
        c(parameo.m + 0.5D, parameo.n + 1, parameo.o + 0.5D, 0.0F, 0.0F);

        this.aQ = 20;
        this.aJ = "humanoid";
        this.aI = 180.0F;
        this.Y = 20;

        this.aG = "/mob/char.png";
    }

    @Override
    public void z() {
        super.z();
        this.ao = this.ap;
        this.ap = 0.0F;
    }

    @Override
    protected void c() {
        if (this.aq) {
            this.ar += 1;
            if (this.ar == 8) {
                this.ar = 0;
                this.aq = false;
            }
        } else {
            this.ar = 0;
        }

        this.aP = (this.ar / 8.0F);
    }

    @Override
    public void E() {
        // hMod: prevent 'healing over time' when monster-spawn=true (nice notchup!)
        if (etc.getInstance().autoHeal() != PluginLoader.HookResult.PREVENT_ACTION) {
            if ((this.l.k == 0) && (this.aQ < 20) && (this.X % 20 * 4 == 0)) {
                a(1);
            }
        }

        this.al.c();
        this.ao = this.ap;

        super.E();

        float f1 = hf.a(this.s * this.s + this.u * this.u);
        float f2 = (float) Math.atan(-this.t * 0.2000000029802322D) * 15.0F;
        if (f1 > 0.1F) {
            f1 = 0.1F;
        }
        if ((!this.A) || (this.aQ <= 0)) {
            f1 = 0.0F;
        }
        if ((this.A) || (this.aQ <= 0)) {
            f2 = 0.0F;
        }
        this.ap += (f1 - this.ap) * 0.4F;
        this.aY += (f2 - this.aY) * 0.8F;

        if (this.aQ > 0) {
            List localList = this.l.b(this, this.z.b(1.0D, 0.0D, 1.0D));
            if (localList != null) {
                for (int i = 0; i < localList.size(); ++i) {
                    j((dx) localList.get(i));
                }
            }
        }
    }

    private void j(dx paramdx) {
        paramdx.b(this);
    }

    @Override
    public void f(dx paramdx) {
        super.f(paramdx);
        a(0.2F, 0.2F);
        a(this.p, this.q, this.r);
        this.t = 0.1000000014901161D;

        if (this.as.equals("Notch")) {
            a(new hl(fu.h, 1), true);
        }
        this.al.f();

        if (paramdx != null) {
            this.s = (-hf.b((this.aU + this.v) * 3.141593F / 180.0F) * 0.1F);
            this.u = (-hf.a((this.aU + this.v) * 3.141593F / 180.0F) * 0.1F);
        } else {
            this.s = (this.u = 0.0D);
        }
        this.H = 0.1F;
    }

    @Override
    public void b(dx paramdx, int paramInt) {
        this.an += paramInt;
    }

    public void a(hl paramhl) {
        a(paramhl, false);
    }

    public void a(hl paramhl, boolean paramBoolean) {
        if (paramhl == null) {
            return;
        }

        gj localgj = new gj(this.l, this.p, this.q - 0.300000011920929D + s(), this.r, paramhl);
        localgj.c = 40;

        float f1 = 0.1F;
        float f2;
        if (paramBoolean) {
            f2 = this.W.nextFloat() * 0.5F;
            float f3 = this.W.nextFloat() * 3.141593F * 2.0F;
            localgj.s = (-hf.a(f3) * f2);
            localgj.u = (hf.b(f3) * f2);
            localgj.t = 0.2000000029802322D;
        } else {
            f1 = 0.3F;
            localgj.s = (-hf.a(this.v / 180.0F * 3.141593F) * hf.b(this.w / 180.0F * 3.141593F) * f1);
            localgj.u = (hf.b(this.v / 180.0F * 3.141593F) * hf.b(this.w / 180.0F * 3.141593F) * f1);
            localgj.t = (-hf.a(this.w / 180.0F * 3.141593F) * f1 + 0.1F);
            f1 = 0.02F;

            f2 = this.W.nextFloat() * 3.141593F * 2.0F;
            f1 *= this.W.nextFloat();
            localgj.s += Math.cos(f2) * f1;
            localgj.t += (this.W.nextFloat() - this.W.nextFloat()) * 0.1F;
            localgj.u += Math.sin(f2) * f1;
        }

        a(localgj);
    }

    protected void a(gj paramgj) {
        this.l.a(paramgj);
    }

    public float a(ga paramga) {
        float f = this.al.a(paramga);
        if (a(jw.f)) {
            f /= 5.0F;
        }
        if (!this.A) {
            f /= 5.0F;
        }

        return f;
    }

    public boolean b(ga paramga) {
        return this.al.b(paramga);
    }

    @Override
    public void b(v paramv) {
        super.b(paramv);
        eb localeb = paramv.k("Inventory");
        this.al.b(localeb);
        this.at = paramv.d("Dimension");
    }

    @Override
    public void a(v paramv) {
        super.a(paramv);
        paramv.a("Inventory", this.al.a(new eb()));
        paramv.a("Dimension", this.at);
    }

    public void a(kc paramkc) {
    }

    public void G() {
    }

    public void c(dx paramdx, int paramInt) {
    }

    @Override
    public float s() {
        return 0.12F;
    }

    @Override
    public boolean a(dx paramdx, int paramInt) {
        this.bn = 0;
        if (this.aQ <= 0) {
            return false;
        }

        if ((paramdx instanceof gb) || (paramdx instanceof dw)) {
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

        return super.a(paramdx, paramInt);
    }

    @Override
    protected void c(int paramInt) {
        int i = 25 - this.al.e();
        int j = paramInt * i + this.a;
        this.al.c(paramInt);
        paramInt = j / 25;
        this.a = (j % 25);
        super.c(paramInt);
    }

    public void a(dt paramdt) {
    }

    public void a(jl paramjl) {
    }

    public void g(dx paramdx) {
        paramdx.a(this);
    }

    public hl H() {
        return this.al.b();
    }

    public void I() {
        this.al.a(this.al.d, null);
    }

    @Override
    public double B() {
        return this.H - 0.5F;
    }

    public void F() {
        this.ar = -1;
        this.aq = true;
    }

    public void h(dx paramdx) {
        int i = this.al.a(paramdx);
        if (i > 0) {
            paramdx.a(this, i);
            hl localhl = H();
            if ((localhl != null) && (paramdx instanceof jy)) {
                localhl.a((jy) paramdx);
                if (localhl.a <= 0) {
                    localhl.a(this);
                    I();
                }
            }
        }
    }
}
