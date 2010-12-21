
import java.util.List;
import java.util.Random;

public abstract class gp extends lb {

    public ih an = new ih(this);
    public dw ao;
    public dw ap;
    public byte aq = 0;
    public int ar = 0;
    public float as;
    public float at;
    public boolean au = false;
    public int av = 0;
    public String aw;
    public int ax;
    public double ay;
    public double az;
    public double aA;
    public double aB;
    public double aC;
    public double aD;
    private int a = 0;
    public li aE = null;

    public gp(ff paramff) {
        super(paramff);

        this.ao = new aa(this.an, !paramff.z);

        this.ap = this.ao;

        this.H = 1.62F;
        c(paramff.m + 0.5D, paramff.n + 1, paramff.o + 0.5D, 0.0F, 0.0F);

        this.ba = 20;
        this.aT = "humanoid";
        this.aS = 180.0F;
        this.Y = 20;

        this.aQ = "/mob/char.png";
    }

    public void b_() {
        super.b_();

        if ((!this.l.z)
                && (this.ap != null) && (!this.ap.b(this))) {
            I();
            this.ap = this.ao;
        }

        this.ay = this.aB;
        this.az = this.aC;
        this.aA = this.aD;

        double d1 = this.p - this.aB;
        double d2 = this.q - this.aC;
        double d3 = this.r - this.aD;

        double d4 = 10.0D;
        if (d1 > d4) {
            this.ay = (this.aB = this.p);
        }
        if (d3 > d4) {
            this.aA = (this.aD = this.r);
        }
        if (d2 > d4) {
            this.az = (this.aC = this.q);
        }
        if (d1 < -d4) {
            this.ay = (this.aB = this.p);
        }
        if (d3 < -d4) {
            this.aA = (this.aD = this.r);
        }
        if (d2 < -d4) {
            this.az = (this.aC = this.q);
        }

        this.aB += d1 * 0.25D;
        this.aD += d3 * 0.25D;
        this.aC += d2 * 0.25D;
    }

    protected void I() {
        this.ap = this.ao;
    }

    public void z() {
        super.z();
        this.as = this.at;
        this.at = 0.0F;
    }

    protected void c() {
        if (this.au) {
            this.av += 1;
            if (this.av == 8) {
                this.av = 0;
                this.au = false;
            }
        } else {
            this.av = 0;
        }

        this.aZ = (this.av / 8.0F);
    }

    public void G() {
        // hMod: adjust 'healing over time' independent of monster-spawn=true/false (nice notchup!)
        PluginLoader.HookResult autoHeal = etc.getInstance().autoHeal();
        if ((this.l.k == 0) && (autoHeal == PluginLoader.HookResult.DEFAULT_ACTION) || autoHeal == PluginLoader.HookResult.ALLOW_ACTION) {
            if ((this.ba < 20) && (this.X % 20 * 12 == 0)) {
                c(1);
            }
        }

        this.an.f();
        this.as = this.at;

        super.G();

        float f1 = ib.a(this.s * this.s + this.u * this.u);
        float f2 = (float) Math.atan(-this.t * 0.2000000029802322D) * 15.0F;
        if (f1 > 0.1F) {
            f1 = 0.1F;
        }
        if ((!this.A) || (this.ba <= 0)) {
            f1 = 0.0F;
        }
        if ((this.A) || (this.ba <= 0)) {
            f2 = 0.0F;
        }
        this.at += (f1 - this.at) * 0.4F;
        this.bi += (f2 - this.bi) * 0.8F;

        if (this.ba > 0) {
            List localList = this.l.b(this, this.z.b(1.0D, 0.0D, 1.0D));
            if (localList != null) {
                for (int i = 0; i < localList.size(); i++) {
                    j((ep) localList.get(i));
                }
            }
        }
    }

    private void j(ep paramep) {
        paramep.b(this);
    }

    public void f(ep paramep) {
        super.f(paramep);
        a(0.2F, 0.2F);
        a(this.p, this.q, this.r);
        this.t = 0.1000000014901161D;

        if (this.aw.equals("Notch")) {
            a(new ik(gl.h, 1), true);
        }
        this.an.h();

        if (paramep != null) {
            this.s = (-ib.b((this.be + this.v) * 3.141593F / 180.0F) * 0.1F);
            this.u = (-ib.a((this.be + this.v) * 3.141593F / 180.0F) * 0.1F);
        } else {
            this.s = (this.u = 0.0D);
        }
        this.H = 0.1F;
    }

    public void b(ep paramep, int paramInt) {
        this.ar += paramInt;
    }

    public void L() {
        a(this.an.a(this.an.c, 1), false);
    }

    public void b(ik paramik) {
        a(paramik, false);
    }

    public void a(ik paramik, boolean paramBoolean) {
        if (paramik == null) {
            return;
        }

        he localhe = new he(this.l, this.p, this.q - 0.300000011920929D + s(), this.r, paramik);
        localhe.c = 40;

        float f1 = 0.1F;
        float f2;
        if (paramBoolean) {
            f2 = this.W.nextFloat() * 0.5F;
            float f3 = this.W.nextFloat() * 3.141593F * 2.0F;
            localhe.s = (-ib.a(f3) * f2);
            localhe.u = (ib.b(f3) * f2);
            localhe.t = 0.2000000029802322D;
        } else {
            f1 = 0.3F;
            localhe.s = (-ib.a(this.v / 180.0F * 3.141593F) * ib.b(this.w / 180.0F * 3.141593F) * f1);
            localhe.u = (ib.b(this.v / 180.0F * 3.141593F) * ib.b(this.w / 180.0F * 3.141593F) * f1);
            localhe.t = (-ib.a(this.w / 180.0F * 3.141593F) * f1 + 0.1F);
            f1 = 0.02F;

            f2 = this.W.nextFloat() * 3.141593F * 2.0F;
            f1 *= this.W.nextFloat();
            localhe.s += Math.cos(f2) * f1;
            localhe.t += (this.W.nextFloat() - this.W.nextFloat()) * 0.1F;
            localhe.u += Math.sin(f2) * f1;
        }

        a(localhe);
    }

    protected void a(he paramhe) {
        this.l.a(paramhe);
    }

    public float a(gu paramgu) {
        float f = this.an.a(paramgu);
        if (a(kz.f)) {
            f /= 5.0F;
        }
        if (!this.A) {
            f /= 5.0F;
        }

        return f;
    }

    public boolean b(gu paramgu) {
        return this.an.b(paramgu);
    }

    public void b(ad paramad) {
        super.b(paramad);
        es locales = paramad.k("Inventory");
        this.an.b(locales);
        this.ax = paramad.d("Dimension");
    }

    public void a(ad paramad) {
        super.a(paramad);
        paramad.a("Inventory", this.an.a(new es()));
        paramad.a("Dimension", this.ax);
    }

    public void a(lf paramlf) {
    }

    public void a(int paramInt1, int paramInt2, int paramInt3) {
    }

    public void c(ep paramep, int paramInt) {
    }

    public float s() {
        return 0.12F;
    }

    public boolean a(ep paramep, int paramInt) {
        this.bx = 0;
        if (this.ba <= 0) {
            return false;
        }

        if (((paramep instanceof gt)) || ((paramep instanceof en))) {
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

        return super.a(paramep, paramInt);
    }

    protected void d(int paramInt) {
        int i = 25 - this.an.g();
        int j = paramInt * i + this.a;
        this.an.c(paramInt);
        paramInt = j / 25;
        this.a = (j % 25);
        super.d(paramInt);
    }

    public void a(ek paramek) {
    }

    public void a(ko paramko) {
    }

    public void g(ep paramep) {
        paramep.a(this);
    }

    public ik M() {
        return this.an.e();
    }

    public void N() {
        this.an.a(this.an.c, null);
    }

    public double B() {
        return this.H - 0.5F;
    }

    public void H() {
        this.av = -1;
        this.au = true;
    }

    public void h(ep paramep) {
        int i = this.an.a(paramep);
        if (i > 0) {
            paramep.a(this, i);
            ik localik = M();
            if ((localik != null) && ((paramep instanceof lb))) {
                localik.a((lb) paramep);
                if (localik.a <= 0) {
                    localik.a(this);
                    N();
                }
            }
        }
    }

    public void a(ik paramik) {
    }

    public void l() {
        super.l();
        this.ao.a(this);
        if (this.ap != null) {
            this.ap.a(this);
        }
    }
}
