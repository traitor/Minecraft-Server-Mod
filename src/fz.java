import java.util.List;
import java.util.Random;

public class fz extends ka {
    public hk am = new hk(this);
    public byte an = 0;
    public int ao = 0;
    public float ap;
    public float aq;
    public boolean ar = false;
    public int as = 0;
    public String at;
    public int au;
    private int a = 0;
    public kg av = null;

    public fz(eq parameq) {
        super(parameq);

        this.H = 1.62F;
        c(parameq.m + 0.5D, parameq.n + 1, parameq.o + 0.5D, 0.0F, 0.0F);

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
        // hMod: adjust 'healing over time' independent of monster-spawn=true/false (nice notchup!)
        PluginLoader.HookResult autoHeal = etc.getInstance().autoHeal();
        if ((this.l.k == 0 && autoHeal == PluginLoader.HookResult.DEFAULT_ACTION) || autoHeal == PluginLoader.HookResult.ALLOW_ACTION) {
            if ((this.aR < 20) && (this.X % 20 == 0)) {
                a(1);
            }
        }

        this.am.c();
        this.ap = this.aq;

        super.E();

        float f1 = hh.a(this.s * this.s + this.u * this.u);
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
                    j((ea) localList.get(i));
                }
            }
        }
    }

    private void j(ea paramea) {
        paramea.b(this);
    }

    @Override
    public void f(ea paramea) {
        super.f(paramea);
        a(0.2F, 0.2F);
        a(this.p, this.q, this.r);
        this.t = 0.1000000014901161D;

        if (this.at.equals("Notch")) {
            a(new hn(fw.h, 1), true);
        }
        this.am.f();

        if (paramea != null) {
            this.s = (-hh.b((this.aV + this.v) * 3.141593F / 180.0F) * 0.1F);
            this.u = (-hh.a((this.aV + this.v) * 3.141593F / 180.0F) * 0.1F);
        } else {
            this.s = (this.u = 0.0D);
        }
        this.H = 0.1F;
    }

    @Override
    public void b(ea paramea, int paramInt) {
        this.ao += paramInt;
    }

    public void a(hn paramhn) {
        a(paramhn, false);
    }

    public void a(hn paramhn, boolean paramBoolean) {
        if (paramhn == null) {
            return;
        }

        gl localgl = new gl(this.l, this.p, this.q - 0.300000011920929D + s(), this.r, paramhn);
        localgl.c = 40;

        float f1 = 0.1F;
        float f2;
        if (paramBoolean) {
            f2 = this.W.nextFloat() * 0.5F;
            float f3 = this.W.nextFloat() * 3.141593F * 2.0F;
            localgl.s = (-hh.a(f3) * f2);
            localgl.u = (hh.b(f3) * f2);
            localgl.t = 0.2000000029802322D;
        } else {
            f1 = 0.3F;
            localgl.s = (-hh.a(this.v / 180.0F * 3.141593F) * hh.b(this.w / 180.0F * 3.141593F) * f1);
            localgl.u = (hh.b(this.v / 180.0F * 3.141593F) * hh.b(this.w / 180.0F * 3.141593F) * f1);
            localgl.t = (-hh.a(this.w / 180.0F * 3.141593F) * f1 + 0.1F);
            f1 = 0.02F;

            f2 = this.W.nextFloat() * 3.141593F * 2.0F;
            f1 *= this.W.nextFloat();
            localgl.s += Math.cos(f2) * f1;
            localgl.t += (this.W.nextFloat() - this.W.nextFloat()) * 0.1F;
            localgl.u += Math.sin(f2) * f1;
        }

        a(localgl);
    }

    protected void a(gl paramgl) {
        this.l.a(paramgl);
    }

    public float a(gc paramgc) {
        float f = this.am.a(paramgc);
        if (a(jy.f)) {
            f /= 5.0F;
        }
        if (!this.A) {
            f /= 5.0F;
        }

        return f;
    }

    public boolean b(gc paramgc) {
        return this.am.b(paramgc);
    }

    @Override
    public void b(v paramv) {
        super.b(paramv);
        ed localed = paramv.k("Inventory");
        this.am.b(localed);
        this.au = paramv.d("Dimension");
    }

    @Override
    public void a(v paramv) {
        super.a(paramv);
        paramv.a("Inventory", this.am.a(new ed()));
        paramv.a("Dimension", this.au);
    }

    public void a(ke paramke) {
    }

    public void G() {
    }

    public void c(ea paramea, int paramInt) {
    }

    @Override
    public float s() {
        return 0.12F;
    }

    @Override
    public boolean a(ea paramea, int paramInt) {
        this.bo = 0;
        if (this.aR <= 0) {
            return false;
        }

        if (((paramea instanceof gd)) || ((paramea instanceof dy))) {
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

        return super.a(paramea, paramInt);
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

    public void a(dv paramdv) {
    }

    public void a(jn paramjn) {
    }

    public void g(ea paramea) {
        paramea.a(this);
    }

    public hn H() {
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

    public void h(ea paramea) {
        int i = this.am.a(paramea);
        if (i > 0) {
            paramea.a(this, i);
            hn localhn = H();
            if ((localhn != null) && ((paramea instanceof ka))) {
                localhn.a((ka) paramea);
                if (localhn.a <= 0) {
                    localhn.a(this);
                    I();
                }
            }
        }
    }
}
