
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
        this.l = paramfv;

        a(0.0D, 0.0D, 0.0D);

        this.af.a(0, (byte) 0);
        a();
    }

    protected abstract void a();

    public gx p() {
        return this.af;
    }

    public boolean equals(Object paramObject) {
        if ((paramObject instanceof fe)) {
            return ((fe) paramObject).g == this.g;
        }
        return false;
    }

    public int hashCode() {
        return this.g;
    }

    public void q() {
        this.G = true;
    }

    protected void a(float paramFloat1, float paramFloat2) {
        this.I = paramFloat1;
        this.J = paramFloat2;
    }

    protected void b(float paramFloat1, float paramFloat2) {
        this.v = paramFloat1;
        this.w = paramFloat2;
    }

    public void a(double paramDouble1, double paramDouble2, double paramDouble3) {
        this.p = paramDouble1;
        this.q = paramDouble2;
        this.r = paramDouble3;
        float f1 = this.I / 2.0F;
        float f2 = this.J;

        this.z.c(paramDouble1 - f1, paramDouble2 - this.H + this.R, paramDouble3 - f1, paramDouble1 + f1, paramDouble2 - this.H + this.R + f2, paramDouble3 + f1);
    }

    public void b_() {
        r();
    }

    public void r() {
        if ((this.k != null) && (this.k.G)) {
            this.k = null;
        }

        this.X += 1;
        this.K = this.L;
        this.m = this.p;
        this.n = this.q;
        this.o = this.r;
        this.y = this.w;
        this.x = this.v;

        if (v()) {
            if ((!this.ab) && (!this.c)) {
                float f1 = iz.a(this.s * this.s * 0.2000000029802322D + this.t * this.t + this.u * this.u * 0.2000000029802322D) * 0.2F;
                if (f1 > 1.0F) {
                    f1 = 1.0F;
                }
                this.l.a(this, "random.splash", f1, 1.0F + (this.W.nextFloat() - this.W.nextFloat()) * 0.4F);
                float f2 = iz.b(this.z.b);
                float f3;
                float f4;
                for (int i1 = 0; i1 < 1.0F + this.I * 20.0F; i1++) {
                    f3 = (this.W.nextFloat() * 2.0F - 1.0F) * this.I;
                    f4 = (this.W.nextFloat() * 2.0F - 1.0F) * this.I;
                    this.l.a("bubble", this.p + f3, f2 + 1.0F, this.r + f4, this.s, this.t - this.W.nextFloat() * 0.2F, this.u);
                }
                for (int i1 = 0; i1 < 1.0F + this.I * 20.0F; i1++) {
                    f3 = (this.W.nextFloat() * 2.0F - 1.0F) * this.I;
                    f4 = (this.W.nextFloat() * 2.0F - 1.0F) * this.I;
                    this.l.a("splash", this.p + f3, f2 + 1.0F, this.r + f4, this.s, this.t, this.u);
                }
            }
            this.N = 0.0F;
            this.ab = true;
            this.Z = 0;
        } else {
            this.ab = false;
        }

        if (this.l.z) {
            this.Z = 0;
        } else if (this.Z > 0) {
            if (this.ae) {
                this.Z -= 4;
                if (this.Z < 0) {
                    this.Z = 0;
                }
            } else {
                if (this.Z % 20 == 0) {
                    // hMod Damage hook: Periodic burn damage
                    BaseEntity burner = new BaseEntity(this);
                    if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FIRE_TICK, null, burner, 1)) {
                        a((fe) null, 1);
                    }

                }
                this.Z -= 1;
            }

        }

        if (x()) {
            s();
        }

        if (this.q < -64.0D) {
            t();
        }

        if (!this.l.z) {
            a(0, this.Z > 0);
            a(2, this.k != null);
        }

        this.c = false;
    }

    protected void s() {
        if (!this.ae) {
            // hMod Damage hook: Lava
            if (this instanceof mj) {
                BaseEntity defender = new BaseEntity(this);
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.LAVA, null, defender, 4)) {
                    return;
                }
            }
            a((fe) null, 4);
            this.Z = 600;
        }
    }

    protected void t() {
        q();
    }

    public boolean b(double paramDouble1, double paramDouble2, double paramDouble3) {
        fa localfa = this.z.c(paramDouble1, paramDouble2, paramDouble3);
        List localList = this.l.a(this, localfa);
        if (localList.size() > 0) {
            return false;
        }
        return !this.l.b(localfa);
    }

    public void c(double paramDouble1, double paramDouble2, double paramDouble3) {
        if (this.T) {
            this.z.d(paramDouble1, paramDouble2, paramDouble3);
            this.p = ((this.z.a + this.z.d) / 2.0D);
            this.q = (this.z.b + this.H - this.R);
            this.r = ((this.z.c + this.z.f) / 2.0D);
            return;
        }

        double d1 = this.p;
        double d2 = this.r;

        double d3 = paramDouble1;
        double d4 = paramDouble2;
        double d5 = paramDouble3;

        fa localfa1 = this.z.b();

        int i1 = (this.A) && (J()) ? 1 : 0;

        if (i1 != 0) {
            double d6 = 0.05D;
            while ((paramDouble1 != 0.0D) && (this.l.a(this, this.z.c(paramDouble1, -1.0D, 0.0D)).size() == 0)) {
                if ((paramDouble1 < d6) && (paramDouble1 >= -d6)) {
                    paramDouble1 = 0.0D;
                } else if (paramDouble1 > 0.0D) {
                    paramDouble1 -= d6;
                } else {
                    paramDouble1 += d6;
                }
                d3 = paramDouble1;
            }
            while ((paramDouble3 != 0.0D) && (this.l.a(this, this.z.c(0.0D, -1.0D, paramDouble3)).size() == 0)) {
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

        List localList = this.l.a(this, this.z.a(paramDouble1, paramDouble2, paramDouble3));

        for (int i2 = 0; i2 < localList.size(); i2++) {
            paramDouble2 = ((fa) localList.get(i2)).b(this.z, paramDouble2);
        }
        this.z.d(0.0D, paramDouble2, 0.0D);

        if ((!this.F) && (d4 != paramDouble2)) {
            paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;
        }

        int i2 = (this.A) || ((d4 != paramDouble2) && (d4 < 0.0D)) ? 1 : 0;

        for (int i3 = 0; i3 < localList.size(); i3++) {
            paramDouble1 = ((fa) localList.get(i3)).a(this.z, paramDouble1);
        }
        this.z.d(paramDouble1, 0.0D, 0.0D);

        if ((!this.F) && (d3 != paramDouble1)) {
            paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;
        }

        for (int i3 = 0; i3 < localList.size(); i3++) {
            paramDouble3 = ((fa) localList.get(i3)).c(this.z, paramDouble3);
        }
        this.z.d(0.0D, 0.0D, paramDouble3);

        if ((!this.F) && (d5 != paramDouble3)) {
            paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;
        }

        if ((this.S > 0.0F) && (i2 != 0) && (this.R < 0.05F) && ((d3 != paramDouble1) || (d5 != paramDouble3))) {
            double d7 = paramDouble1;
            double d8 = paramDouble2;
            double d9 = paramDouble3;

            paramDouble1 = d3;
            paramDouble2 = this.S;
            paramDouble3 = d5;
            int i7;

            fa localfa2 = this.z.b();
            this.z.b(localfa1);
            localList = this.l.a(this, this.z.a(paramDouble1, paramDouble2, paramDouble3));

            for (i7 = 0; i7 < localList.size(); i7++) {
                paramDouble2 = ((fa) localList.get(i7)).b(this.z, paramDouble2);
            }
            this.z.d(0.0D, paramDouble2, 0.0D);

            if ((!this.F) && (d4 != paramDouble2)) {
                paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;
            }

            for (i7 = 0; i7 < localList.size(); i7++) {
                paramDouble1 = ((fa) localList.get(i7)).a(this.z, paramDouble1);
            }
            this.z.d(paramDouble1, 0.0D, 0.0D);

            if ((!this.F) && (d3 != paramDouble1)) {
                paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;
            }

            for (i7 = 0; i7 < localList.size(); i7++) {
                paramDouble3 = ((fa) localList.get(i7)).c(this.z, paramDouble3);
            }
            this.z.d(0.0D, 0.0D, paramDouble3);

            if ((!this.F) && (d5 != paramDouble3)) {
                paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;
            }

            if (d7 * d7 + d9 * d9 >= paramDouble1 * paramDouble1 + paramDouble3 * paramDouble3) {
                paramDouble1 = d7;
                paramDouble2 = d8;
                paramDouble3 = d9;
                this.z.b(localfa2);
            } else {
                this.R = (float) (this.R + 0.5D);
            }

        }

        this.p = ((this.z.a + this.z.d) / 2.0D);
        this.q = (this.z.b + this.H - this.R);
        this.r = ((this.z.c + this.z.f) / 2.0D);

        this.B = ((d3 != paramDouble1) || (d5 != paramDouble3));
        this.C = (d4 != paramDouble2);
        this.A = ((d4 != paramDouble2) && (d4 < 0.0D));
        this.D = ((this.B) || (this.C));
        a(paramDouble2, this.A);

        if (d3 != paramDouble1) {
            this.s = 0.0D;
        }
        if (d4 != paramDouble2) {
            this.t = 0.0D;
        }
        if (d5 != paramDouble3) {
            this.u = 0.0D;
        }

        double d7 = this.p - d1;
        double d8 = this.r - d2;

        if ((this.M) && (i1 == 0)) {
            this.L = (float) (this.L + iz.a(d7 * d7 + d8 * d8) * 0.6D);
            int i4 = iz.b(this.p);
            int i5 = iz.b(this.q - 0.2000000029802322D - this.H);
            int i6 = iz.b(this.r);
            int i7 = this.l.a(i4, i5, i6);
            if ((this.L > this.b) && (i7 > 0)) {
                this.b += 1;
                cy localcy = hr.m[i7].br;
                if (this.l.a(i4, i5 + 1, i6) == hr.aS.bi) {
                    localcy = hr.aS.br;
                    this.l.a(this, localcy.c(), localcy.a() * 0.15F, localcy.b());
                } else if (!hr.m[i7].bt.d()) {
                    this.l.a(this, localcy.c(), localcy.a() * 0.15F, localcy.b());
                }
                hr.m[i7].b(this.l, i4, i5, i6, this);
            }

        }

        int i4 = iz.b(this.z.a);
        int i5 = iz.b(this.z.b);
        int i6 = iz.b(this.z.c);
        int i7 = iz.b(this.z.d);
        int i8 = iz.b(this.z.e);
        int i9 = iz.b(this.z.f);

        if (this.l.a(i4, i5, i6, i7, i8, i9)) {
            for (int i10 = i4; i10 <= i7; i10++) {
                for (int i11 = i5; i11 <= i8; i11++) {
                    for (int i12 = i6; i12 <= i9; i12++) {
                        int i13 = this.l.a(i10, i11, i12);
                        if (i13 > 0) {
                            hr.m[i13].a(this.l, i10, i11, i12, this);
                        }
                    }
                }
            }
        }
        this.R *= 0.4F;

        boolean bool = v();
        if (this.l.c(this.z)) {
            b(1);
            if (!bool) {
                this.Z += 1;
                if (this.Z == 0) {
                    this.Z = 300;
                }
            }
        } else if (this.Z <= 0) {
            this.Z = (-this.Y);
        }

        if ((bool) && (this.Z > 0)) {
            this.l.a(this, "random.fizz", 0.7F, 1.6F + (this.W.nextFloat() - this.W.nextFloat()) * 0.4F);
            this.Z = (-this.Y);
        }
    }

    protected void a(double paramDouble, boolean paramBoolean) {
        if (paramBoolean) {
            if (this.N > 0.0F) {
                a(this.N);
                this.N = 0.0F;
            }
        } else if (paramDouble < 0.0D) {
            this.N = (float) (this.N - paramDouble);
        }
    }

    public fa u() {
        return null;
    }

    protected void b(int paramInt) {
        if (!this.ae) {
            a((fe) null, paramInt);
        }
    }

    protected void a(float paramFloat) {
    }

    public boolean v() {
        return this.l.a(this.z.b(0.0D, -0.4000000059604645D, 0.0D), mh.f, this);
    }

    public boolean a(mh parammh) {
        double d1 = this.q + w();
        int i1 = iz.b(this.p);
        int i2 = iz.d(iz.b(d1));
        int i3 = iz.b(this.r);
        int i4 = this.l.a(i1, i2, i3);
        if ((i4 != 0) && (hr.m[i4].bt == parammh)) {
            float f1 = dz.c(this.l.b(i1, i2, i3)) - 0.1111111F;
            float f2 = i2 + 1 - f1;
            return d1 < f2;
        }
        return false;
    }

    public float w() {
        return 0.0F;
    }

    public boolean x() {
        return this.l.a(this.z.b(-0.1000000014901161D, -0.4000000059604645D, -0.1000000014901161D), mh.g);
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

        float f2 = iz.a(this.v * 3.141593F / 180.0F);
        float f3 = iz.b(this.v * 3.141593F / 180.0F);

        this.s += paramFloat1 * f3 - paramFloat2 * f2;
        this.u += paramFloat2 * f3 + paramFloat1 * f2;
    }

    public float b(float paramFloat) {
        int i1 = iz.b(this.p);

        double d1 = (this.z.e - this.z.b) * 0.66D;
        int i2 = iz.b(this.q - this.H + d1);
        int i3 = iz.b(this.r);
        if (this.l.a(iz.b(this.z.a), iz.b(this.z.b), iz.b(this.z.c), iz.b(this.z.d), iz.b(this.z.e), iz.b(this.z.f))) {
            return this.l.l(i1, i2, i3);
        }
        return 0.0F;
    }

    public void b(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {
        this.m = (this.p = paramDouble1);
        this.n = (this.q = paramDouble2);
        this.o = (this.r = paramDouble3);
        this.x = (this.v = paramFloat1);
        this.y = (this.w = paramFloat2);
        this.R = 0.0F;

        double d1 = this.x - paramFloat1;
        if (d1 < -180.0D) {
            this.x += 360.0F;
        }
        if (d1 >= 180.0D) {
            this.x -= 360.0F;
        }
        a(this.p, this.q, this.r);
        b(paramFloat1, paramFloat2);
    }

    public void c(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {
        this.O = (this.m = this.p = paramDouble1);
        this.P = (this.n = this.q = paramDouble2 + this.H);
        this.Q = (this.o = this.r = paramDouble3);
        this.v = paramFloat1;
        this.w = paramFloat2;
        a(this.p, this.q, this.r);
    }

    public float a(fe paramfe) {
        float f1 = (float) (this.p - paramfe.p);
        float f2 = (float) (this.q - paramfe.q);
        float f3 = (float) (this.r - paramfe.r);
        return iz.c(f1 * f1 + f2 * f2 + f3 * f3);
    }

    public double d(double paramDouble1, double paramDouble2, double paramDouble3) {
        double d1 = this.p - paramDouble1;
        double d2 = this.q - paramDouble2;
        double d3 = this.r - paramDouble3;
        return d1 * d1 + d2 * d2 + d3 * d3;
    }

    public double e(double paramDouble1, double paramDouble2, double paramDouble3) {
        double d1 = this.p - paramDouble1;
        double d2 = this.q - paramDouble2;
        double d3 = this.r - paramDouble3;
        return iz.a(d1 * d1 + d2 * d2 + d3 * d3);
    }

    public double b(fe paramfe) {
        double d1 = this.p - paramfe.p;
        double d2 = this.q - paramfe.q;
        double d3 = this.r - paramfe.r;
        return d1 * d1 + d2 * d2 + d3 * d3;
    }

    public void b(hl paramhl) {
    }

    public void c(fe paramfe) {
        if ((paramfe.j == this) || (paramfe.k == this)) {
            return;
        }

        double d1 = paramfe.p - this.p;
        double d2 = paramfe.r - this.r;

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

            d1 *= (1.0F - this.U);
            d2 *= (1.0F - this.U);

            f(-d1, 0.0D, -d2);
            paramfe.f(d1, 0.0D, d2);
        }
    }

    public void f(double paramDouble1, double paramDouble2, double paramDouble3) {
        this.s += paramDouble1;
        this.t += paramDouble2;
        this.u += paramDouble3;
    }

    protected void y() {
        this.E = true;
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
        if ((this.G) || (str == null)) {
            return false;
        }
        paramah.a("id", str);
        d(paramah);
        return true;
    }

    public void d(ah paramah) {
        paramah.a("Pos", a(new double[]{this.p, this.q, this.r}));
        paramah.a("Motion", a(new double[]{this.s, this.t, this.u}));
        paramah.a("Rotation", a(new float[]{this.v, this.w}));

        paramah.a("FallDistance", this.N);
        paramah.a("Fire", (short) this.Z);
        paramah.a("Air", (short) this.ad);
        paramah.a("OnGround", this.A);

        a(paramah);
    }

    public void e(ah paramah) {
        fh localfh1 = paramah.k("Pos");
        fh localfh2 = paramah.k("Motion");
        fh localfh3 = paramah.k("Rotation");
        a(0.0D, 0.0D, 0.0D);

        this.s = ((fo) localfh2.a(0)).a;
        this.t = ((fo) localfh2.a(1)).a;
        this.u = ((fo) localfh2.a(2)).a;

        this.m = (this.O = this.p = ((fo) localfh1.a(0)).a);
        this.n = (this.P = this.q = ((fo) localfh1.a(1)).a);
        this.o = (this.Q = this.r = ((fo) localfh1.a(2)).a);

        this.x = (this.v = ((u) localfh3.a(0)).a);
        this.y = (this.w = ((u) localfh3.a(1)).a);

        this.N = paramah.f("FallDistance");
        this.Z = paramah.c("Fire");
        this.ad = paramah.c("Air");
        this.A = paramah.l("OnGround");

        a(this.p, this.q, this.r);

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
        ic localic = new ic(this.l, this.p, this.q + paramFloat, this.r, paramjl);
        localic.c = 10;
        this.l.a(localic);
        return localic;
    }

    public boolean B() {
        return !this.G;
    }

    public boolean C() {
        int i1 = iz.b(this.p);
        int i2 = iz.b(this.q + w());
        int i3 = iz.b(this.r);
        return this.l.d(i1, i2, i3);
    }

    public boolean a(hl paramhl) {
        return false;
    }

    public fa d(fe paramfe) {
        return null;
    }

    public void D() {
        if (this.k.G) {
            this.k = null;
            return;
        }
        this.s = 0.0D;
        this.t = 0.0D;
        this.u = 0.0D;
        b_();
        this.k.E();

        this.e += this.k.v - this.k.x;
        this.d += this.k.w - this.k.y;

        while (this.e >= 180.0D) {
            this.e -= 360.0D;
        }
        while (this.e < -180.0D) {
            this.e += 360.0D;
        }
        while (this.d >= 180.0D) {
            this.d -= 360.0D;
        }
        while (this.d < -180.0D) {
            this.d += 360.0D;
        }
        double d1 = this.e * 0.5D;
        double d2 = this.d * 0.5D;

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

        this.e -= d1;
        this.d -= d2;

        this.v = (float) (this.v + d1);
        this.w = (float) (this.w + d2);
    }

    public void E() {
        this.j.a(this.p, this.q + k() + this.j.F(), this.r);
    }

    public double F() {
        return this.H;
    }

    public double k() {
        return this.J * 0.75D;
    }

    public void e(fe paramfe) {
        this.d = 0.0D;
        this.e = 0.0D;

        if (paramfe == null) {
            if (this.k != null) {
                c(this.k.p, this.k.z.b + this.k.J, this.k.r, this.v, this.w);
                this.k.j = null;
            }
            this.k = null;
            return;
        }
        if (this.k == paramfe) {
            this.k.j = null;
            this.k = null;
            c(paramfe.p, paramfe.z.b + paramfe.J, paramfe.r, this.v, this.w);
            return;
        }
        if (this.k != null) {
            this.k.j = null;
        }
        if (paramfe.j != null) {
            paramfe.j.k = null;
        }
        this.k = paramfe;
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
        return (this.af.a(0) & 1 << paramInt) != 0;
    }

    protected void a(int paramInt, boolean paramBoolean) {
        int i1 = this.af.a(0);
        if (paramBoolean) {
            this.af.b(0, Byte.valueOf((byte) (i1 | 1 << paramInt)));
        } else {
            this.af.b(0, Byte.valueOf((byte) (i1 & (1 << paramInt ^ 0xFFFFFFFF))));
        }
    }
}
