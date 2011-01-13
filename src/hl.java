
import java.util.List;
import java.util.Random;

public abstract class hl extends mj {

    public ji an = new ji(this);
    public ei ao;
    public ei ap;
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
    public mq aE = null;

    public hl(fv paramfv) {
        super(paramfv);

        ao = new ae(an, !paramfv.z);

        ap = ao;

        H = 1.62F;
        c(paramfv.m + 0.5D, paramfv.n + 1, paramfv.o + 0.5D, 0.0F, 0.0F);

        aZ = 20;
        aS = "humanoid";
        aR = 180.0F;
        Y = 20;

        aP = "/mob/char.png";
    }

    public void b_() {
        super.b_();

        if ((!l.z)
                && (ap != null) && (!ap.b(this))) {
            L();
            ap = ao;
        }

        ay = aB;
        az = aC;
        aA = aD;

        double d1 = p - aB;
        double d2 = q - aC;
        double d3 = r - aD;

        double d4 = 10.0D;
        if (d1 > d4) {
            ay = (this.aB = p);
        }
        if (d3 > d4) {
            aA = (this.aD = r);
        }
        if (d2 > d4) {
            az = (this.aC = q);
        }
        if (d1 < -d4) {
            ay = (this.aB = p);
        }
        if (d3 < -d4) {
            aA = (this.aD = r);
        }
        if (d2 < -d4) {
            az = (this.aC = q);
        }

        aB += d1 * 0.25D;
        aD += d3 * 0.25D;
        aC += d2 * 0.25D;
    }

    protected void L() {
        ap = ao;
    }

    public void D() {
        super.D();
        as = at;
        at = 0.0F;
    }

    protected void d() {
        if (au) {
            av += 1;
            if (av == 8) {
                av = 0;
                au = false;
            }
        } else {
            av = 0;
        }

        aY = (av / 8.0F);
    }

    public void o() {
        // hMod: adjust 'healing over time' independent of monster-spawn=true/false (nice notchup!)
        PluginLoader.HookResult autoHeal = etc.getInstance().autoHeal();
        if ((l.k == 0) && (autoHeal == PluginLoader.HookResult.DEFAULT_ACTION) || autoHeal == PluginLoader.HookResult.ALLOW_ACTION) {
            if ((aZ < 20) && (X % 20 * 12 == 0)) {
                d(1);
            }
        }

        an.f();
        as = at;

        super.o();

        float f1 = iz.a(s * s + u * u);
        float f2 = (float) Math.atan(-t * 0.2000000029802322D) * 15.0F;
        if (f1 > 0.1F) {
            f1 = 0.1F;
        }
        if ((!A) || (aZ <= 0)) {
            f1 = 0.0F;
        }
        if ((A) || (aZ <= 0)) {
            f2 = 0.0F;
        }
        at += (f1 - at) * 0.4F;
        bh += (f2 - bh) * 0.8F;

        if (aZ > 0) {
            List localList = l.b(this, z.b(1.0D, 0.0D, 1.0D));
            if (localList != null) {
                for (int i = 0; i < localList.size(); i++) {
                    fe localfe = (fe) localList.get(i);
                    if (!localfe.G) {
                        j(localfe);
                    }
                }
            }
        }
    }

    private void j(fe paramfe) {
        paramfe.b(this);
    }

    public void f(fe paramfe) {
        super.f(paramfe);
        a(0.2F, 0.2F);
        a(p, q, r);
        t = 0.1000000014901161D;
        // gives player with name "Notch" an free apple; god may know why.
        if (aw.equals("Notch")) {
            a(new jl(hg.h, 1), true);
        }
        an.h();

        if (paramfe != null) {
            s = (-iz.b((bd + v) * 3.141593F / 180.0F) * 0.1F);
            u = (-iz.a((bd + v) * 3.141593F / 180.0F) * 0.1F);
        } else {
            s = (this.u = 0.0D);
        }
        H = 0.1F;
    }

    public void b(fe paramfe, int paramInt) {
        ar += paramInt;
    }

    public void O() {
        a(an.b(an.c, 1), false);
    }

    public void b(jl paramjl) {
        a(paramjl, false);
    }

    public void a(jl paramjl, boolean paramBoolean) {
        if (paramjl == null) {
            return;
        }

        ic localic = new ic(l, p, q - 0.300000011920929D + w(), r, paramjl);
        localic.c = 40;

        float f1 = 0.1F;
        float f2;
        if (paramBoolean) {
            f2 = W.nextFloat() * 0.5F;
            float f3 = W.nextFloat() * 3.141593F * 2.0F;
            localic.s = (-iz.a(f3) * f2);
            localic.u = (iz.b(f3) * f2);
            localic.t = 0.2000000029802322D;
        } else {
            f1 = 0.3F;
            localic.s = (-iz.a(v / 180.0F * 3.141593F) * iz.b(w / 180.0F * 3.141593F) * f1);
            localic.u = (iz.b(v / 180.0F * 3.141593F) * iz.b(w / 180.0F * 3.141593F) * f1);
            localic.t = (-iz.a(w / 180.0F * 3.141593F) * f1 + 0.1F);
            f1 = 0.02F;

            f2 = W.nextFloat() * 3.141593F * 2.0F;
            f1 *= W.nextFloat();
            localic.s += Math.cos(f2) * f1;
            localic.t += (W.nextFloat() - W.nextFloat()) * 0.1F;
            localic.u += Math.sin(f2) * f1;
        }

        a(localic);
    }

    protected void a(ic paramic) {
        l.a(paramic);
    }

    public float a(hr paramhr) {
        float f = an.a(paramhr);
        if (a(mh.f)) {
            f /= 5.0F;
        }
        if (!A) {
            f /= 5.0F;
        }

        return f;
    }

    public boolean b(hr paramhr) {
        return an.b(paramhr);
    }

    public void b(ah paramah) {
        super.b(paramah);
        fh localfh = paramah.k("Inventory");
        an.b(localfh);
        ax = paramah.d("Dimension");
    }

    public void a(ah paramah) {
        super.a(paramah);
        paramah.a("Inventory", an.a(new fh()));
        paramah.a("Dimension", ax);
    }

    public void a(mn parammn) {
    }

    public void a(int paramInt1, int paramInt2, int paramInt3) {
    }

    public void c(fe paramfe, int paramInt) {
    }

    public float w() {
        return 0.12F;
    }

    public boolean a(fe paramfe, int paramInt) {
        bw = 0;
        if (aZ <= 0) {
            return false;
        }

        if (((paramfe instanceof hq)) || ((paramfe instanceof fc))) {
            if (l.k == 0) {
                paramInt = 0;
            }
            if (l.k == 1) {
                paramInt = paramInt / 3 + 1;
            }
            if (l.k == 3) {
                paramInt = paramInt * 3 / 2;
            }
        }

        if (paramInt == 0) {
            return false;
        }

        return super.a(paramfe, paramInt);
    }

    protected void e(int paramInt) {
        int i = 25 - an.g();
        int j = paramInt * i + a;
        an.c(paramInt);
        paramInt = j / 25;
        a = (j % 25);
        super.e(paramInt);
    }

    public void a(ez paramez) {
    }

    public void a(bf parambf) {
    }

    public void a(lv paramlv) {
    }

    public void g(fe paramfe) {
        if (paramfe.a(this)) {
            return;
        }
        jl localjl = P();
        if ((localjl != null) && ((paramfe instanceof mj))) {
            localjl.b((mj) paramfe);
            if (localjl.a <= 0) {
                localjl.a(this);
                Q();
            }
        }
    }

    public jl P() {
        return an.e();
    }

    public void Q() {
        an.a(an.c, null);
    }

    public double F() {
        return H - 0.5F;
    }

    public void K() {
        av = -1;
        au = true;
    }

    public void h(fe paramfe) {
        int i = an.a(paramfe);
        if (i > 0) {
            paramfe.a(this, i);
            jl localjl = P();
            if ((localjl != null) && ((paramfe instanceof mj))) {
                localjl.a((mj) paramfe);
                if (localjl.a <= 0) {
                    localjl.a(this);
                    Q();
                }
            }
        }
    }

    public void a(jl paramjl) {
    }

    public void q() {
        super.q();
        ao.a(this);
        if (ap != null) {
            ap.a(this);
        }
    }
}
