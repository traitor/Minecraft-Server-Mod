
import java.util.List;
import java.util.Random;

public abstract class mj extends fe {

    public int aF = 20;
    public float aG;
    public float aH;
    public float aI = 0.0F;
    public float aJ = 0.0F;
    protected float aK;
    protected float aL;
    protected float aM;
    protected float aN;
    protected boolean aO = true;
    protected String aP = "/mob/char.png";
    protected boolean aQ = true;
    protected float aR = 0.0F;
    protected String aS = null;
    protected float aT = 1.0F;
    protected int aU = 0;
    protected float aV = 0.0F;
    public boolean aW = false;
    public float aX;
    public float aY;
    public int aZ;
    public int ba;
    private int a;
    public int bb;
    public int bc;
    public float bd = 0.0F;
    public int be = 0;
    public int bf = 0;
    public float bg;
    public float bh;
    protected boolean bi = false;
    public int bj = -1;
    public float bk = (float) (Math.random() * 0.8999999761581421D + 0.1000000014901161D);
    public float bl;
    public float bm;
    public float bn;
    protected int bo;
    protected double bp;
    protected double bq;
    protected double br;
    protected double bs;
    protected double bt;
    float bu = 0.0F;
    protected int bv = 0;
    protected int bw = 0;
    protected float bx;
    protected float by;
    protected float bz;
    protected boolean bA = false;
    protected float bB = 0.0F;
    protected float bC = 0.7F;
    private fe b;
    private int c = 0;

    public mj(fv paramfv) {
        super(paramfv);
        this.aZ = 10;
        this.i = true;

        this.aH = ((float) (Math.random() + 1.0D) * 0.01F);
        a(this.p, this.q, this.r);
        this.aG = ((float) Math.random() * 12398.0F);
        this.v = (float) (Math.random() * 3.141592741012573D * 2.0D);

        this.S = 0.5F;
    }

    protected void a() {
    }

    public boolean i(fe paramfe) {
        // hMod fix the static reference here with a null element.
        bu bu = null;
        return this.l.a(bu.b(this.p, this.q + w(), this.r), bu.b(paramfe.p, paramfe.q + paramfe.w(), paramfe.r)) == null;
    }

    public boolean c_() {
        return !this.G;
    }

    public boolean z() {
        return !this.G;
    }

    public float w() {
        return this.J * 0.85F;
    }

    public int c() {
        return 80;
    }

    public void r() {
        this.aX = this.aY;
        super.r();

        if (this.W.nextInt(1000) < this.a++) {
            this.a = (-c());
            String str = e();
            if (str != null) {
                this.l.a(this, str, i(), (this.W.nextFloat() - this.W.nextFloat()) * 0.2F + 1.0F);
            }
        }

        if ((B()) && (C())) {
            a((fe) null, 1);
        }

        if ((this.ae) || (this.l.z)) {
            this.Z = 0;
        }
        int i;
        if ((B()) && (a(mh.f)) && (!d_())) {
            this.ad -= 1;
            if (this.ad == -20) {
                this.ad = 0;
                for (i = 0; i < 8; i++) {
                    float f1 = this.W.nextFloat() - this.W.nextFloat();
                    float f2 = this.W.nextFloat() - this.W.nextFloat();
                    float f3 = this.W.nextFloat() - this.W.nextFloat();
                    this.l.a("bubble", this.p + f1, this.q + f2, this.r + f3, this.s, this.t, this.u);
                }
                // hMod Damage hook: Drowning
                LivingEntity defender = new LivingEntity(this);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.WATER, null, defender, 2)) {
                    a((fe) null, 2);
                }

            }

            this.Z = 0;
        } else {
            this.ad = this.aa;
        }

        this.bg = this.bh;

        if (this.bf > 0) {
            this.bf -= 1;
        }
        if (this.bb > 0) {
            this.bb -= 1;
        }
        if (this.ac > 0) {
            this.ac -= 1;
        }
        if (this.aZ <= 0) {
            this.be += 1;
            if (this.be > 20) {
                T();
                q();
                for (i = 0; i < 20; i++) {
                    double d1 = this.W.nextGaussian() * 0.02D;
                    double d2 = this.W.nextGaussian() * 0.02D;
                    double d3 = this.W.nextGaussian() * 0.02D;
                    this.l.a("explode", this.p + this.W.nextFloat() * this.I * 2.0F - this.I, this.q + this.W.nextFloat() * this.J, this.r + this.W.nextFloat() * this.I * 2.0F - this.I, d1, d2, d3);
                }
            }
        }

        this.aN = this.aM;

        this.aJ = this.aI;
        this.x = this.v;
        this.y = this.w;
    }

    public void R() {
        for (int i = 0; i < 20; i++) {
            double d1 = this.W.nextGaussian() * 0.02D;
            double d2 = this.W.nextGaussian() * 0.02D;
            double d3 = this.W.nextGaussian() * 0.02D;
            double d4 = 10.0D;
            this.l.a("explode", this.p + this.W.nextFloat() * this.I * 2.0F - this.I - d1 * d4, this.q + this.W.nextFloat() * this.J - d2 * d4, this.r + this.W.nextFloat() * this.I * 2.0F - this.I - d3 * d4, d1, d2, d3);
        }
    }

    public void D() {
        super.D();
        this.aK = this.aL;
        this.aL = 0.0F;
    }

    public void b_() {
        super.b_();

        o();

        double d1 = this.p - this.m;
        double d2 = this.r - this.o;

        float f1 = iz.a(d1 * d1 + d2 * d2);

        float f2 = this.aI;

        float f3 = 0.0F;
        this.aK = this.aL;
        float f4 = 0.0F;
        if (f1 > 0.05F) {
            f4 = 1.0F;
            f3 = f1 * 3.0F;
            f2 = (float) Math.atan2(d2, d1) * 180.0F / 3.141593F - 90.0F;
        }
        if (this.aY > 0.0F) {
            f2 = this.v;
        }
        if (!this.A) {
            f4 = 0.0F;
        }
        this.aL += (f4 - this.aL) * 0.3F;

        float f5 = f2 - this.aI;
        while (f5 < -180.0F) {
            f5 += 360.0F;
        }
        while (f5 >= 180.0F) {
            f5 -= 360.0F;
        }
        this.aI += f5 * 0.3F;

        float f6 = this.v - this.aI;
        while (f6 < -180.0F) {
            f6 += 360.0F;
        }
        while (f6 >= 180.0F) {
            f6 -= 360.0F;
        }
        int i = (f6 < -90.0F) || (f6 >= 90.0F) ? 1 : 0;
        if (f6 < -75.0F) {
            f6 = -75.0F;
        }
        if (f6 >= 75.0F) {
            f6 = 75.0F;
        }
        this.aI = (this.v - f6);
        if (f6 * f6 > 2500.0F) {
            this.aI += f6 * 0.2F;
        }

        if (i != 0) {
            f3 *= -1.0F;
        }
        while (this.v - this.x < -180.0F) {
            this.x -= 360.0F;
        }
        while (this.v - this.x >= 180.0F) {
            this.x += 360.0F;
        }
        while (this.aI - this.aJ < -180.0F) {
            this.aJ -= 360.0F;
        }
        while (this.aI - this.aJ >= 180.0F) {
            this.aJ += 360.0F;
        }
        while (this.w - this.y < -180.0F) {
            this.y -= 360.0F;
        }
        while (this.w - this.y >= 180.0F) {
            this.y += 360.0F;
        }
        this.aM += f3;
    }

    protected void a(float paramFloat1, float paramFloat2) {
        super.a(paramFloat1, paramFloat2);
    }

    public void d(int paramInt) {
        if (this.aZ <= 0) {
            return;
        }
        this.aZ += paramInt;
        if (this.aZ > 20) {
            this.aZ = 20;
        }
        this.ac = (this.aF / 2);
    }

    public boolean a(fe paramfe, int paramInt) {
        if (this.l.z) {
            return false;
        }
        this.bw = 0;
        if (this.aZ <= 0) {
            return false;
        }

        this.bm = 1.5F;

        // hMod damage entities.
        LivingEntity attacker = (paramfe != null && paramfe instanceof mj) ? new LivingEntity((mj) paramfe) : null;
        LivingEntity defender = new LivingEntity(this);

        // hMod attack by entity, but it might not do damage!
        if (attacker != null && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ATTACK, attacker, defender, paramInt)) {
            return false;
        }

        int i = 1;
        if (this.ac > this.aF / 2.0F) {
            if (paramInt <= this.bv) {
                return false;
            }
            // hMod: partial damage
            if (attacker != null && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, defender, paramInt - bv)) {
                return false;
            }
            e(paramInt - this.bv);
            this.bv = paramInt;
            i = 0;
        } else {
            // hMod: full damage
            if (attacker != null && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, defender, paramInt)) {
                return false;
            }

            this.bv = paramInt;
            this.ba = this.aZ;
            this.ac = this.aF;
            e(paramInt);
            this.bb = (this.bc = 10);
        }

        this.bd = 0.0F;

        if (i != 0) {
            // hMod: Forced cast to send 'damage animation'.
            this.l.a(this, (byte) 2);
            y();
            if (paramfe != null) {
                double d1 = paramfe.p - this.p;
                double d2 = paramfe.r - this.r;
                while (d1 * d1 + d2 * d2 < 0.0001D) {
                    d1 = (Math.random() - Math.random()) * 0.01D;
                    d2 = (Math.random() - Math.random()) * 0.01D;
                }
                this.bd = ((float) (Math.atan2(d2, d1) * 180.0D / 3.141592741012573D) - this.v);
                a(paramfe, paramInt, d1, d2);
            } else {
                this.bd = ((int) (Math.random() * 2.0D) * 180);
            }
        }

        if (this.aZ <= 0) {
            if (i != 0) {
                this.l.a(this, g(), i(), (this.W.nextFloat() - this.W.nextFloat()) * 0.2F + 1.0F);
            }
            f(paramfe);
        } else if (i != 0) {
            this.l.a(this, f(), i(), (this.W.nextFloat() - this.W.nextFloat()) * 0.2F + 1.0F);
        }

        return true;
    }

    protected void e(int paramInt) {
        this.aZ -= paramInt;
    }

    protected float i() {
        return 1.0F;
    }

    protected String e() {
        return null;
    }

    protected String f() {
        return "random.hurt";
    }

    protected String g() {
        return "random.hurt";
    }

    public void a(fe paramfe, int paramInt, double paramDouble1, double paramDouble2) {
        float f1 = iz.a(paramDouble1 * paramDouble1 + paramDouble2 * paramDouble2);
        float f2 = 0.4F;

        this.s /= 2.0D;
        this.t /= 2.0D;
        this.u /= 2.0D;

        this.s -= paramDouble1 / f1 * f2;
        this.t += 0.4000000059604645D;
        this.u -= paramDouble2 / f1 * f2;

        if (this.t > 0.4000000059604645D) {
            this.t = 0.4000000059604645D;
        }
    }

    public void f(fe paramfe) {
        if ((this.aU > 0) && (paramfe != null)) {
            paramfe.b(this, this.aU);
        }
        this.bi = true;

        if (!this.l.z) {
            g_();
        }

        // hMod: Forced cast to play Death Animations.
        this.l.a(this, (byte) 3);
    }

    protected void g_() {
        int i = h();
        if (i > 0) {
            int j = this.W.nextInt(3);
            for (int k = 0; k < j; k++) {
                a(i, 1);
            }
        }
    }

    protected int h() {
        return 0;
    }

    protected void a(float paramFloat) {
        int i = (int) Math.ceil(paramFloat - 3.0F);
        if (i > 0) {
            // hMod Damage hook: Falling
            LivingEntity defender = new LivingEntity(this);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FALL, null, defender, i)) {
                a((fe) null, i);
            }

            int j = this.l.a(iz.b(this.p), iz.b(this.q - 0.2000000029802322D - this.H), iz.b(this.r));
            if (j > 0) {
                cy localcy = hr.m[j].br;
                this.l.a(this, localcy.c(), localcy.a() * 0.5F, localcy.b() * 0.75F);
            }
        }
    }

    public void c(float paramFloat1, float paramFloat2) {
        double d1;
        if (v()) {
            d1 = this.q;
            a(paramFloat1, paramFloat2, 0.02F);
            c(this.s, this.t, this.u);

            this.s *= 0.800000011920929D;
            this.t *= 0.800000011920929D;
            this.u *= 0.800000011920929D;
            this.t -= 0.02D;

            if ((this.B) && (b(this.s, this.t + 0.6000000238418579D - this.q + d1, this.u))) {
                this.t = 0.300000011920929D;
            }
        } else if (x()) {
            d1 = this.q;
            a(paramFloat1, paramFloat2, 0.02F);
            c(this.s, this.t, this.u);
            this.s *= 0.5D;
            this.t *= 0.5D;
            this.u *= 0.5D;
            this.t -= 0.02D;

            if ((this.B) && (b(this.s, this.t + 0.6000000238418579D - this.q + d1, this.u))) {
                this.t = 0.300000011920929D;
            }
        } else {
            float f1 = 0.91F;
            if (this.A) {
                f1 = 0.5460001F;
                int i = this.l.a(iz.b(this.p), iz.b(this.z.b) - 1, iz.b(this.r));
                if (i > 0) {
                    f1 = hr.m[i].bu * 0.91F;
                }
            }

            float f2 = 0.1627714F / (f1 * f1 * f1);
            a(paramFloat1, paramFloat2, this.A ? 0.1F * f2 : 0.02F);

            f1 = 0.91F;
            if (this.A) {
                f1 = 0.5460001F;
                int j = this.l.a(iz.b(this.p), iz.b(this.z.b) - 1, iz.b(this.r));
                if (j > 0) {
                    f1 = hr.m[j].bu * 0.91F;
                }
            }

            if (m()) {
                this.N = 0.0F;
                if (this.t < -0.15D) {
                    this.t = -0.15D;
                }

            }

            c(this.s, this.t, this.u);

            if ((this.B) && (m())) {
                this.t = 0.2D;
            }

            this.t -= 0.08D;
            this.t *= 0.9800000190734863D;
            this.s *= f1;
            this.u *= f1;
        }
        this.bl = this.bm;
        double d2 = this.p - this.m;
        double d3 = this.r - this.o;
        float f3 = iz.a(d2 * d2 + d3 * d3) * 4.0F;
        if (f3 > 1.0F) {
            f3 = 1.0F;
        }
        this.bm += (f3 - this.bm) * 0.4F;
        this.bn += this.bm;
    }

    public boolean m() {
        int i = iz.b(this.p);
        int j = iz.b(this.z.b);
        int k = iz.b(this.r);
        return (this.l.a(i, j, k) == hr.aF.bi) || (this.l.a(i, j + 1, k) == hr.aF.bi);
    }

    public void a(ah paramah) {
        paramah.a("Health", (short) this.aZ);
        paramah.a("HurtTime", (short) this.bb);
        paramah.a("DeathTime", (short) this.be);
        paramah.a("AttackTime", (short) this.bf);
    }

    public void b(ah paramah) {
        this.aZ = paramah.c("Health");
        if (!paramah.a("Health")) {
            this.aZ = 10;
        }
        this.bb = paramah.c("HurtTime");
        this.be = paramah.c("DeathTime");
        this.bf = paramah.c("AttackTime");
    }

    public boolean B() {
        return (!this.G) && (this.aZ > 0);
    }

    public boolean d_() {
        return false;
    }

    public void o() {
        if (this.bo > 0) {
            double d1 = this.p + (this.bp - this.p) / this.bo;
            double d2 = this.q + (this.bq - this.q) / this.bo;
            double d3 = this.r + (this.br - this.r) / this.bo;

            double d4 = this.bs - this.v;
            while (d4 < -180.0D) {
                d4 += 360.0D;
            }
            while (d4 >= 180.0D) {
                d4 -= 360.0D;
            }
            this.v = (float) (this.v + d4 / this.bo);
            this.w = (float) (this.w + (this.bt - this.w) / this.bo);

            this.bo -= 1;
            a(d1, d2, d3);
            b(this.v, this.w);
        }

        if (this.aZ <= 0) {
            this.bA = false;
            this.bx = 0.0F;
            this.by = 0.0F;
            this.bz = 0.0F;
        } else if (!this.aW) {
            d();
        }

        boolean bool1 = v();
        boolean bool2 = x();

        if (this.bA) {
            if (bool1) {
                this.t += 0.03999999910593033D;
            } else if (bool2) {
                this.t += 0.03999999910593033D;
            } else if (this.A) {
                S();
            }

        }

        this.bx *= 0.98F;
        this.by *= 0.98F;
        this.bz *= 0.9F;
        c(this.bx, this.by);

        List localList = this.l.b(this, this.z.b(0.2000000029802322D, 0.0D, 0.2000000029802322D));
        if ((localList != null) && (localList.size() > 0)) {
            for (int i = 0; i < localList.size(); i++) {
                fe localfe = (fe) localList.get(i);
                if (!localfe.z()) {
                    continue;
                }
                localfe.c(this);
            }
        }
    }

    protected void S() {
        this.t = 0.4199999868869782D;
    }

    protected void d() {
        this.bw += 1;

        hl localhl = this.l.a(this, -1.0D);

        if (localhl != null) {
            double d1 = localhl.p - this.p;
            double d2 = localhl.q - this.q;
            double d3 = localhl.r - this.r;
            double d4 = d1 * d1 + d2 * d2 + d3 * d3;

            if (d4 > 16384.0D) {
                q();
            }

            if ((this.bw > 600) && (this.W.nextInt(800) == 0)) {
                if (d4 < 1024.0D) {
                    this.bw = 0;
                } else {
                    q();
                }
            }
        }

        this.bx = 0.0F;
        this.by = 0.0F;

        float f = 8.0F;
        if (this.W.nextFloat() < 0.02F) {
            localhl = this.l.a(this, f);
            if (localhl != null) {
                this.b = localhl;
                this.c = (10 + this.W.nextInt(20));
            } else {
                this.bz = ((this.W.nextFloat() - 0.5F) * 20.0F);
            }
        }

        if (this.b != null) {
            b(this.b, 10.0F);
            if ((this.c-- <= 0) || (this.b.G) || (this.b.b(this) > f * f)) {
                this.b = null;
            }
        } else {
            if (this.W.nextFloat() < 0.05F) {
                this.bz = ((this.W.nextFloat() - 0.5F) * 20.0F);
            }
            this.v += this.bz;
            this.w = this.bB;
        }

        boolean bool1 = v();
        boolean bool2 = x();
        if ((bool1) || (bool2)) {
            this.bA = (this.W.nextFloat() < 0.8F);
        }
    }

    public void b(fe paramfe, float paramFloat) {
        double d1 = paramfe.p - this.p;

        double d3 = paramfe.r - this.r;
        double d2;
        if ((paramfe instanceof mj)) {
            mj localmj = (mj) paramfe;
            d2 = localmj.q + localmj.w() - (this.q + w());
        } else {
            d2 = (paramfe.z.b + paramfe.z.e) / 2.0D - (this.q + w());
        }

        double d4 = iz.a(d1 * d1 + d3 * d3);

        float f1 = (float) (Math.atan2(d3, d1) * 180.0D / 3.141592741012573D) - 90.0F;
        float f2 = (float) (Math.atan2(d2, d4) * 180.0D / 3.141592741012573D);
        this.w = (-b(this.w, f2, paramFloat));
        this.v = b(this.v, f1, paramFloat);
    }

    private float b(float paramFloat1, float paramFloat2, float paramFloat3) {
        float f = paramFloat2 - paramFloat1;
        while (f < -180.0F) {
            f += 360.0F;
        }
        while (f >= 180.0F) {
            f -= 360.0F;
        }
        if (f > paramFloat3) {
            f = paramFloat3;
        }
        if (f < -paramFloat3) {
            f = -paramFloat3;
        }
        return paramFloat1 + f;
    }

    public void T() {
    }

    public boolean b() {
        return (this.l.a(this.z)) && (this.l.a(this, this.z).size() == 0) && (!this.l.b(this.z));
    }

    protected void t() {
        a((fe) null, 4);
    }

    public bu G() {
        return c(1.0F);
    }

    public bu c(float paramFloat) {
        // hMod fix the static reference here with a null element.
        bu bu = null;
        if (paramFloat == 1.0F) {
            float f1 = iz.b(-this.v * 0.01745329F - 3.141593F);
            float f2 = iz.a(-this.v * 0.01745329F - 3.141593F);
            float f3 = -iz.b(-this.w * 0.01745329F);
            float f4 = iz.a(-this.w * 0.01745329F);

            return bu.b(f2 * f3, f4, f1 * f3);
        }
        float f1 = this.y + (this.w - this.y) * paramFloat;
        float f2 = this.x + (this.v - this.x) * paramFloat;

        float f3 = iz.b(-f2 * 0.01745329F - 3.141593F);
        float f4 = iz.a(-f2 * 0.01745329F - 3.141593F);
        float f5 = -iz.b(-f1 * 0.01745329F);
        float f6 = iz.a(-f1 * 0.01745329F);

        return bu.b(f4 * f5, f6, f3 * f5);
    }

    public int j() {
        return 4;
    }
}
