
import java.util.List;
import java.util.Random;

public abstract class fe {

    private static int a = 0;
    public int g = a++;
    public double h = 1.0D;
    public boolean i = false;
    public fe j;
    public fe k;
    public fv l;
    public double m;
    public double n;
    public double o;
    public double p;
    public double q;
    public double r;
    public double s;
    public double t;
    public double u;
    public float v;
    public float w;
    public float x;
    public float y;
    public final fa z = fa.a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
    public boolean A = false;
    public boolean B;
    public boolean C;
    public boolean D = false;
    public boolean E = false;
    public boolean F = true;
    public boolean G = false;
    public float H = 0.0F;
    public float I = 0.6F;
    public float J = 1.8F;
    public float K = 0.0F;
    public float L = 0.0F;
    protected boolean M = true;
    protected float N = 0.0F;
    private int b = 1;
    public double O;
    public double P;
    public double Q;
    public float R = 0.0F;
    public float S = 0.0F;
    public boolean T = false;
    public float U = 0.0F;
    public boolean V = false;
    protected Random W = new Random();
    public int X = 0;
    public int Y = 1;
    public int Z = 0;
    protected int aa = 300;
    protected boolean ab = false;
    public int ac = 0;
    public int ad = 300;
    private boolean c = true;
    protected boolean ae = false;
    protected gx af = new gx();
    private double d;
    private double e;
    public boolean ag = false;
    public int ah;
    public int ai;
    public int aj;

    public fe(fv paramfv) {
        l = paramfv;

        a(0.0D, 0.0D, 0.0D);

        af.a(0, 0);
        a();
    }

    protected abstract void a();

    public gx p() {
        return af;
    }

    public boolean equals(Object paramObject) {
        if ((paramObject instanceof fe)) {
            return ((fe) paramObject).g == g;
        }
        return false;
    }

    public int hashCode() {
        return g;
    }

    public void q() {
        G = true;
    }

    protected void a(float paramFloat1, float paramFloat2) {
        I = paramFloat1;
        J = paramFloat2;
    }

    protected void b(float paramFloat1, float paramFloat2) {
        v = paramFloat1;
        w = paramFloat2;
    }

    public void a(double paramDouble1, double paramDouble2, double paramDouble3) {
        p = paramDouble1;
        q = paramDouble2;
        r = paramDouble3;
        float f1 = I / 2.0F;
        float f2 = J;

        z.c(paramDouble1 - f1, paramDouble2 - H + R, paramDouble3 - f1, paramDouble1 + f1, paramDouble2 - H + R + f2, paramDouble3 + f1);
    }

    public void b_() {
        r();
    }

    public void r() {
        if ((k != null) && (k.G)) {
            k = null;
        }

        X += 1;
        K = L;
        m = p;
        n = q;
        o = r;
        y = w;
        x = v;

        if (v()) {
            if ((!ab) && (!c)) {
                float f1 = iz.a(s * s * 0.2000000029802322D + t * t + u * u * 0.2000000029802322D) * 0.2F;
                if (f1 > 1.0F) {
                    f1 = 1.0F;
                }
                l.a(this, "random.splash", f1, 1.0F + (W.nextFloat() - W.nextFloat()) * 0.4F);
                float f2 = iz.b(z.b);
                float f3;
                float f4;
                for (int i1 = 0; i1 < 1.0F + I * 20.0F; i1++) {
                    f3 = (W.nextFloat() * 2.0F - 1.0F) * I;
                    f4 = (W.nextFloat() * 2.0F - 1.0F) * I;
                    l.a("bubble", p + f3, f2 + 1.0F, r + f4, s, t - W.nextFloat() * 0.2F, u);
                }
                for (int i1 = 0; i1 < 1.0F + I * 20.0F; i1++) {
                    f3 = (W.nextFloat() * 2.0F - 1.0F) * I;
                    f4 = (W.nextFloat() * 2.0F - 1.0F) * I;
                    l.a("splash", p + f3, f2 + 1.0F, r + f4, s, t, u);
                }
            }
            N = 0.0F;
            ab = true;
            Z = 0;
        } else {
            ab = false;
        }

        if (l.z) {
            Z = 0;
        } else if (Z > 0) {
            if (ae) {
                Z -= 4;
                if (Z < 0) {
                    Z = 0;
                }
            } else {
                if (Z % 20 == 0) {
                    BaseEntity burner = new BaseEntity(this);
                    if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FIRE_TICK, null, burner, 1)) {
                        a((fe) null, 1);
                    }
                }
                Z -= 1;
            }

        }

        if (x()) {
            s();
        }

        if (q < -64.0D) {
            t();
        }

        if (!l.z) {
            a(0, Z > 0);
            a(2, k != null);
        }

        c = false;
    }

    protected void s() {
        if (!ae) {
            // hMod Damage hook: Lava
            if (this instanceof mj) {
                BaseEntity defender = new BaseEntity(this);
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.LAVA, null, defender, 4)) {
                    return;
                }
            }
            a((fe)null, 4);
            Z = 600;
        }
    }

    protected void t() {
        q();
    }

    public boolean b(double paramDouble1, double paramDouble2, double paramDouble3) {
        fa localfa = z.c(paramDouble1, paramDouble2, paramDouble3);
        List localList = l.a(this, localfa);
        if (localList.size() > 0) {
            return false;
        }
        return !l.b(localfa);
    }

    public void c(double paramDouble1, double paramDouble2, double paramDouble3) {
        if (T) {
            z.d(paramDouble1, paramDouble2, paramDouble3);
            p = ((z.a + z.d) / 2.0D);
            q = (z.b + H - R);
            r = ((z.c + z.f) / 2.0D);
            return;
        }

        double d1 = p;
        double d2 = r;

        double d3 = paramDouble1;
        double d4 = paramDouble2;
        double d5 = paramDouble3;

        fa localfa1 = z.b();

        int i1 = (A) && (J()) ? 1 : 0;

        if (i1 != 0) {
            double d6 = 0.05D;
            while ((paramDouble1 != 0.0D) && (l.a(this, z.c(paramDouble1, -1.0D, 0.0D)).size() == 0)) {
                if ((paramDouble1 < d6) && (paramDouble1 >= -d6)) {
                    paramDouble1 = 0.0D;
                } else if (paramDouble1 > 0.0D) {
                    paramDouble1 -= d6;
                } else {
                    paramDouble1 += d6;
                }
                d3 = paramDouble1;
            }
            while ((paramDouble3 != 0.0D) && (l.a(this, z.c(0.0D, -1.0D, paramDouble3)).size() == 0)) {
                if ((paramDouble3 < d6) && (paramDouble3 >= -d6)) {
                    paramDouble3 = 0.0D;
                } else if (paramDouble3 > 0.0D) {
                    paramDouble3 -= d6;
                } else {
                    paramDouble3 += d6;
                }
                d5 = paramDouble3;
            }
        }

        List localList = l.a(this, z.a(paramDouble1, paramDouble2, paramDouble3));

        for (int i2 = 0; i2 < localList.size(); i2++) {
            paramDouble2 = ((fa) localList.get(i2)).b(z, paramDouble2);
        }
        z.d(0.0D, paramDouble2, 0.0D);

        if ((!F) && (d4 != paramDouble2)) {
            paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;
        }

        int i2 = (A) || ((d4 != paramDouble2) && (d4 < 0.0D)) ? 1 : 0;

        for (int i3 = 0; i3 < localList.size(); i3++) {
            paramDouble1 = ((fa) localList.get(i3)).a(z, paramDouble1);
        }
        z.d(paramDouble1, 0.0D, 0.0D);

        if ((!F) && (d3 != paramDouble1)) {
            paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;
        }

        for (int i3 = 0; i3 < localList.size(); i3++) {
            paramDouble3 = ((fa) localList.get(i3)).c(z, paramDouble3);
        }
        z.d(0.0D, 0.0D, paramDouble3);

        if ((!F) && (d5 != paramDouble3)) {
            paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;
        }

        if ((S > 0.0F) && (i2 != 0) && (R < 0.05F) && ((d3 != paramDouble1) || (d5 != paramDouble3))) {
            double d7 = paramDouble1;
            double d8 = paramDouble2;
            double d9 = paramDouble3;

            paramDouble1 = d3;
            paramDouble2 = S;
            paramDouble3 = d5;

            fa localfa2 = z.b();
            z.b(localfa1);
            localList = l.a(this, z.a(paramDouble1, paramDouble2, paramDouble3));
            int i7;
            for (i7 = 0; i7 < localList.size(); i7++) {
                paramDouble2 = ((fa) localList.get(i7)).b(z, paramDouble2);
            }
            z.d(0.0D, paramDouble2, 0.0D);

            if ((!F) && (d4 != paramDouble2)) {
                paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;
            }

            for (i7 = 0; i7 < localList.size(); i7++) {
                paramDouble1 = ((fa) localList.get(i7)).a(z, paramDouble1);
            }
            z.d(paramDouble1, 0.0D, 0.0D);

            if ((!F) && (d3 != paramDouble1)) {
                paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;
            }

            for (i7 = 0; i7 < localList.size(); i7++) {
                paramDouble3 = ((fa) localList.get(i7)).c(z, paramDouble3);
            }
            z.d(0.0D, 0.0D, paramDouble3);

            if ((!F) && (d5 != paramDouble3)) {
                paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;
            }

            if (d7 * d7 + d9 * d9 >= paramDouble1 * paramDouble1 + paramDouble3 * paramDouble3) {
                paramDouble1 = d7;
                paramDouble2 = d8;
                paramDouble3 = d9;
                z.b(localfa2);
            } else {
                R = (float) (R + 0.5D);
            }

        }

        p = ((z.a + z.d) / 2.0D);
        q = (z.b + H - R);
        r = ((z.c + z.f) / 2.0D);

        B = ((d3 != paramDouble1) || (d5 != paramDouble3));
        C = (d4 != paramDouble2);
        A = ((d4 != paramDouble2) && (d4 < 0.0D));
        D = ((B) || (C));
        a(paramDouble2, A);

        if (d3 != paramDouble1) {
            s = 0.0D;
        }
        if (d4 != paramDouble2) {
            t = 0.0D;
        }
        if (d5 != paramDouble3) {
            u = 0.0D;
        }

        double d7 = p - d1;
        double d8 = r - d2;

        if ((M) && (i1 == 0)) {
            L = (float) (L + iz.a(d7 * d7 + d8 * d8) * 0.6D);
            int i4 = iz.b(p);
            int i5 = iz.b(q - 0.2000000029802322D - H);
            int i6 = iz.b(r);
            int i7 = l.a(i4, i5, i6);
            if ((L > b) && (i7 > 0)) {
                b += 1;
                cy localcy = hr.m[i7].br;
                if (l.a(i4, i5 + 1, i6) == hr.aS.bi) {
                    localcy = hr.aS.br;
                    l.a(this, localcy.c(), localcy.a() * 0.15F, localcy.b());
                } else if (!hr.m[i7].bt.d()) {
                    l.a(this, localcy.c(), localcy.a() * 0.15F, localcy.b());
                }
                hr.m[i7].b(l, i4, i5, i6, this);
            }

        }

        int i4 = iz.b(z.a);
        int i5 = iz.b(z.b);
        int i6 = iz.b(z.c);
        int i7 = iz.b(z.d);
        int i8 = iz.b(z.e);
        int i9 = iz.b(z.f);

        if (l.a(i4, i5, i6, i7, i8, i9)) {
            for (int i10 = i4; i10 <= i7; i10++) {
                for (int i11 = i5; i11 <= i8; i11++) {
                    for (int i12 = i6; i12 <= i9; i12++) {
                        int i13 = l.a(i10, i11, i12);
                        if (i13 > 0) {
                            hr.m[i13].a(l, i10, i11, i12, this);
                        }
                    }
                }
            }
        }
        R *= 0.4F;

        boolean bool = v();
        if (l.c(z)) {
            b(1);
            if (!bool) {
                Z += 1;
                if (Z == 0) {
                    Z = 300;
                }
            }
        } else if (Z <= 0) {
            Z = (-Y);
        }

        if ((bool) && (Z > 0)) {
            l.a(this, "random.fizz", 0.7F, 1.6F + (W.nextFloat() - W.nextFloat()) * 0.4F);
            Z = (-Y);
        }
    }

    protected void a(double paramDouble, boolean paramBoolean) {
        if (paramBoolean) {
            if (N > 0.0F) {
                a(N);
                N = 0.0F;
            }
        } else if (paramDouble < 0.0D) {
            N = (float) (N - paramDouble);
        }
    }

    public fa u() {
        return null;
    }

    protected void b(int paramInt) {
        if (!ae) {
            a((fe)null, paramInt);
        }
    }

    protected void a(float paramFloat) {
    }

    public boolean v() {
        return l.a(z.b(0.0D, -0.4000000059604645D, 0.0D), mh.f, this);
    }

    public boolean a(mh parammh) {
        double d1 = q + w();
        int i1 = iz.b(p);
        int i2 = iz.d(iz.b(d1));
        int i3 = iz.b(r);
        int i4 = l.a(i1, i2, i3);
        if ((i4 != 0) && (hr.m[i4].bt == parammh)) {
            float f1 = dz.c(l.b(i1, i2, i3)) - 0.1111111F;
            float f2 = i2 + 1 - f1;
            return d1 < f2;
        }
        return false;
    }

    public float w() {
        return 0.0F;
    }

    public boolean x() {
        return l.a(z.b(-0.1000000014901161D, -0.4000000059604645D, -0.1000000014901161D), mh.g);
    }

    public void a(float paramFloat1, float paramFloat2, float paramFloat3) {
        float f1 = iz.c(paramFloat1 * paramFloat1 + paramFloat2 * paramFloat2);
        if (f1 < 0.01F) {
            return;
        }

        if (f1 < 1.0F) {
            f1 = 1.0F;
        }
        f1 = paramFloat3 / f1;
        paramFloat1 *= f1;
        paramFloat2 *= f1;

        float f2 = iz.a(v * 3.141593F / 180.0F);
        float f3 = iz.b(v * 3.141593F / 180.0F);

        s += paramFloat1 * f3 - paramFloat2 * f2;
        u += paramFloat2 * f3 + paramFloat1 * f2;
    }

    public float b(float paramFloat) {
        int i1 = iz.b(p);

        double d1 = (z.e - z.b) * 0.66D;
        int i2 = iz.b(q - H + d1);
        int i3 = iz.b(r);
        if (l.a(iz.b(z.a), iz.b(z.b), iz.b(z.c), iz.b(z.d), iz.b(z.e), iz.b(z.f))) {
            return l.l(i1, i2, i3);
        }
        return 0.0F;
    }

    public void b(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {
        m = (this.p = paramDouble1);
        n = (this.q = paramDouble2);
        o = (this.r = paramDouble3);
        x = (this.v = paramFloat1);
        y = (this.w = paramFloat2);
        R = 0.0F;

        double d1 = x - paramFloat1;
        if (d1 < -180.0D) {
            x += 360.0F;
        }
        if (d1 >= 180.0D) {
            x -= 360.0F;
        }
        a(p, q, r);
        b(paramFloat1, paramFloat2);
    }

    public void c(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {
        O = (this.m = this.p = paramDouble1);
        P = (this.n = this.q = paramDouble2 + H);
        Q = (this.o = this.r = paramDouble3);
        v = paramFloat1;
        w = paramFloat2;
        a(p, q, r);
    }

    public float a(fe paramfe) {
        float f1 = (float) (p - paramfe.p);
        float f2 = (float) (q - paramfe.q);
        float f3 = (float) (r - paramfe.r);
        return iz.c(f1 * f1 + f2 * f2 + f3 * f3);
    }

    public double d(double paramDouble1, double paramDouble2, double paramDouble3) {
        double d1 = p - paramDouble1;
        double d2 = q - paramDouble2;
        double d3 = r - paramDouble3;
        return d1 * d1 + d2 * d2 + d3 * d3;
    }

    public double e(double paramDouble1, double paramDouble2, double paramDouble3) {
        double d1 = p - paramDouble1;
        double d2 = q - paramDouble2;
        double d3 = r - paramDouble3;
        return iz.a(d1 * d1 + d2 * d2 + d3 * d3);
    }

    public double b(fe paramfe) {
        double d1 = p - paramfe.p;
        double d2 = q - paramfe.q;
        double d3 = r - paramfe.r;
        return d1 * d1 + d2 * d2 + d3 * d3;
    }

    public void b(hl paramhl) {
    }

    public void c(fe paramfe) {
        if ((paramfe.j == this) || (paramfe.k == this)) {
            return;
        }

        double d1 = paramfe.p - p;
        double d2 = paramfe.r - r;

        double d3 = iz.a(d1, d2);

        if (d3 >= 0.009999999776482582D) {
            d3 = iz.a(d3);
            d1 /= d3;
            d2 /= d3;

            double d4 = 1.0D / d3;
            if (d4 > 1.0D) {
                d4 = 1.0D;
            }
            d1 *= d4;
            d2 *= d4;

            d1 *= 0.0500000007450581D;
            d2 *= 0.0500000007450581D;

            d1 *= (1.0F - U);
            d2 *= (1.0F - U);

            f(-d1, 0.0D, -d2);
            paramfe.f(d1, 0.0D, d2);
        }
    }

    public void f(double paramDouble1, double paramDouble2, double paramDouble3) {
        s += paramDouble1;
        t += paramDouble2;
        u += paramDouble3;
    }

    protected void y() {
        E = true;
    }

    public boolean a(fe paramfe, int paramInt) {
        y();
        return false;
    }

    public boolean c_() {
        return false;
    }

    public boolean z() {
        return false;
    }

    public void b(fe paramfe, int paramInt) {
    }

    public boolean c(ah paramah) {
        String str = A();
        if ((G) || (str == null)) {
            return false;
        }
        paramah.a("id", str);
        d(paramah);
        return true;
    }

    public void d(ah paramah) {
        paramah.a("Pos", a(new double[]{p, q, r}));
        paramah.a("Motion", a(new double[]{s, t, u}));
        paramah.a("Rotation", a(new float[]{v, w}));

        paramah.a("FallDistance", N);
        paramah.a("Fire", (short) Z);
        paramah.a("Air", (short) ad);
        paramah.a("OnGround", A);

        a(paramah);
    }

    public void e(ah paramah) {
        fh localfh1 = paramah.k("Pos");
        fh localfh2 = paramah.k("Motion");
        fh localfh3 = paramah.k("Rotation");
        a(0.0D, 0.0D, 0.0D);

        s = ((fo) localfh2.a(0)).a;
        t = ((fo) localfh2.a(1)).a;
        u = ((fo) localfh2.a(2)).a;

        m = (this.O = this.p = ((fo) localfh1.a(0)).a);
        n = (this.P = this.q = ((fo) localfh1.a(1)).a);
        o = (this.Q = this.r = ((fo) localfh1.a(2)).a);

        x = (this.v = ((u) localfh3.a(0)).a);
        y = (this.w = ((u) localfh3.a(1)).a);

        N = paramah.f("FallDistance");
        Z = paramah.c("Fire");
        ad = paramah.c("Air");
        A = paramah.l("OnGround");

        a(p, q, r);

        b(paramah);
    }

    protected final String A() {
        return jn.b(this);
    }

    protected abstract void b(ah paramah);

    protected abstract void a(ah paramah);

    protected fh a(double[] paramArrayOfDouble) {
        fh localfh = new fh();
        for (double d1 : paramArrayOfDouble) {
            localfh.a(new fo(d1));
        }
        return localfh;
    }

    protected fh a(float[] paramArrayOfFloat) {
        fh localfh = new fh();
        for (float f : paramArrayOfFloat) {
            localfh.a(new u(f));
        }
        return localfh;
    }

    public ic a(int paramInt1, int paramInt2) {
        return a(paramInt1, paramInt2, 0.0F);
    }

    public ic a(int paramInt1, int paramInt2, float paramFloat) {
        return a(new jl(paramInt1, paramInt2, 0), paramFloat);
    }

    public ic a(jl paramjl, float paramFloat) {
        ic localic = new ic(l, p, q + paramFloat, r, paramjl);
        localic.c = 10;
        l.a(localic);
        return localic;
    }

    public boolean B() {
        return !G;
    }

    public boolean C() {
        int i1 = iz.b(p);
        int i2 = iz.b(q + w());
        int i3 = iz.b(r);
        return l.d(i1, i2, i3);
    }

    public boolean a(hl paramhl) {
        return false;
    }

    public fa d(fe paramfe) {
        return null;
    }

    public void D() {
        if (k.G) {
            k = null;
            return;
        }
        s = 0.0D;
        t = 0.0D;
        u = 0.0D;
        b_();
        k.E();

        e += k.v - k.x;
        d += k.w - k.y;

        while (e >= 180.0D) {
            e -= 360.0D;
        }
        while (e < -180.0D) {
            e += 360.0D;
        }
        while (d >= 180.0D) {
            d -= 360.0D;
        }
        while (d < -180.0D) {
            d += 360.0D;
        }
        double d1 = e * 0.5D;
        double d2 = d * 0.5D;

        float f = 10.0F;
        if (d1 > f) {
            d1 = f;
        }
        if (d1 < -f) {
            d1 = -f;
        }
        if (d2 > f) {
            d2 = f;
        }
        if (d2 < -f) {
            d2 = -f;
        }

        e -= d1;
        d -= d2;

        v = (float) (v + d1);
        w = (float) (w + d2);
    }

    public void E() {
        j.a(p, q + k() + j.F(), r);
    }

    public double F() {
        return H;
    }

    public double k() {
        return J * 0.75D;
    }

    public void e(fe paramfe) {
        d = 0.0D;
        e = 0.0D;

        if (paramfe == null) {
            if (k != null) {
                c(k.p, k.z.b + k.J, k.r, v, w);
                k.j = null;
            }
            k = null;
            return;
        }
        if (k == paramfe) {
            k.j = null;
            k = null;
            c(paramfe.p, paramfe.z.b + paramfe.J, paramfe.r, v, w);
            return;
        }
        if (k != null) {
            k.j = null;
        }
        if (paramfe.j != null) {
            paramfe.j.k = null;
        }
        k = paramfe;
        paramfe.j = this;
    }

    public bu G() {
        return null;
    }

    public void H() {
    }

    public jl[] I() {
        return null;
    }

    public boolean J() {
        return c(1);
    }

    public void b(boolean paramBoolean) {
        a(1, paramBoolean);
    }

    protected boolean c(int paramInt) {
        return (af.a(0) & 1 << paramInt) != 0;
    }

    protected void a(int paramInt, boolean paramBoolean) {
        int i1 = af.a(0);
        if (paramBoolean) {
            af.b(0, Byte.valueOf((byte) (i1 | 1 << paramInt)));
        } else {
            af.b(0, Byte.valueOf((byte) (i1 & (1 << paramInt ^ 0xFFFFFFFF))));
        }
    }
}
