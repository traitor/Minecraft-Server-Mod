import java.util.List;
import java.util.Random;

public class ka extends ea {
    public int aw = 20;
    public float ax;
    public float ay;
    public float az;
    public float aA = 0.0F;
    public float aB = 0.0F;
    protected float aC;
    protected float aD;
    protected float aE;
    protected float aF;
    protected boolean aG = true;
    protected String aH = "/mob/char.png";
    protected boolean aI = true;
    protected float aJ = 0.0F;
    protected String aK = null;
    protected float aL = 1.0F;
    protected int aM = 0;
    protected float aN = 0.0F;
    public boolean aO = false;
    public float aP;
    public float aQ;
    public int aR;
    public int aS;
    private int a;
    public int aT;
    public int aU;
    public float aV = 0.0F;
    public int aW = 0;
    public int aX = 0;
    public float aY;
    public float aZ;
    protected boolean ba = false;

    public int bb = -1;
    public float bc = (float) (Math.random() * 0.8999999761581421D + 0.1000000014901161D);
    public float bd;
    public float be;
    public float bf;
    protected int bg;
    protected double bh;
    protected double bi;
    protected double bj;
    protected double bk;
    protected double bl;
    float bm = 0.0F;

    protected int bn = 0;

    protected int bo = 0;
    protected float bp;
    protected float bq;
    protected float br;
    protected boolean bs = false;
    protected float bt = 0.0F;
    protected float bu = 0.7F;
    private ea b;
    private int c = 0;

    public ka(eq parameq) {
        super(parameq);
        this.aR = 10;
        this.i = true;

        this.az = ((float) (Math.random() + 1.0D) * 0.01F);
        a(this.p, this.q, this.r);
        this.ax = ((float) Math.random() * 12398.0F);
        this.v = (float) (Math.random() * 3.141592741012573D * 2.0D);
        this.ay = 1.0F;

        this.S = 0.5F;
    }

    public boolean i(ea paramea) {
        // hMod fix the static reference here with a null element.
        bd bd = null;
        return this.l.a(bd.b(this.p, this.q + s(), this.r), bd.b(paramea.p, paramea.q + paramea.s(), paramea.r)) == null;
    }

    @Override
    public boolean c_() {
        return !this.G;
    }

    @Override
    public boolean v() {
        return !this.G;
    }

    @Override
    public float s() {
        return this.J * 0.85F;
    }

    public int b() {
        return 80;
    }

    @Override
    public void m() {
        this.aP = this.aQ;
        super.m();

        if (this.W.nextInt(1000) < this.a++) {
            this.a = (-b());
            String str = d();
            if (str != null) {
                this.l.a(this, str, h(), (this.W.nextFloat() - this.W.nextFloat()) * 0.2F + 1.0F);
            }
        }

        if ((x()) && (y())) {
            a(null, 1);
        }

        if ((this.ae) || (this.l.z)) {
            this.Z = 0;
        }
        int i;
        if ((x()) && (a(jy.f))) {
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
                    a(null, 2);
                }
            }

            this.Z = 0;
        } else {
            this.ad = this.aa;
        }

        this.aY = this.aZ;

        if (this.aX > 0) {
            this.aX -= 1;
        }
        if (this.aT > 0) {
            this.aT -= 1;
        }
        if (this.ac > 0) {
            this.ac -= 1;
        }
        if (this.aR <= 0) {
            this.aW += 1;
            if (this.aW > 20) {
                L();
                l();
                for (i = 0; i < 20; i++) {
                    double d1 = this.W.nextGaussian() * 0.02D;
                    double d2 = this.W.nextGaussian() * 0.02D;
                    double d3 = this.W.nextGaussian() * 0.02D;
                    this.l.a("explode", this.p + this.W.nextFloat() * this.I * 2.0F - this.I, this.q + this.W.nextFloat() * this.J, this.r + this.W.nextFloat() * this.I * 2.0F - this.I, d1, d2, d3);
                }
            }
        }

        this.aF = this.aE;

        this.aB = this.aA;
        this.x = this.v;
        this.y = this.w;
    }

    public void J() {
        for (int i = 0; i < 20; i++) {
            double d1 = this.W.nextGaussian() * 0.02D;
            double d2 = this.W.nextGaussian() * 0.02D;
            double d3 = this.W.nextGaussian() * 0.02D;
            double d4 = 10.0D;
            this.l.a("explode", this.p + this.W.nextFloat() * this.I * 2.0F - this.I - d1 * d4, this.q + this.W.nextFloat() * this.J - d2 * d4, this.r + this.W.nextFloat() * this.I * 2.0F - this.I - d3 * d4, d1, d2, d3);
        }
    }

    @Override
    public void z() {
        super.z();
        this.aC = this.aD;
        this.aD = 0.0F;
    }

    @Override
    public void b_() {
        super.b_();

        E();

        double d1 = this.p - this.m;
        double d2 = this.r - this.o;

        float f1 = hh.a(d1 * d1 + d2 * d2);

        float f2 = this.aA;

        float f3 = 0.0F;
        this.aC = this.aD;
        float f4 = 0.0F;
        if (f1 > 0.05F) {
            f4 = 1.0F;
            f3 = f1 * 3.0F;
            f2 = (float) Math.atan2(d2, d1) * 180.0F / 3.141593F - 90.0F;
        }
        if (this.aQ > 0.0F) {
            f2 = this.v;
        }
        if (!this.A) {
            f4 = 0.0F;
        }
        this.aD += (f4 - this.aD) * 0.3F;

        float f5 = f2 - this.aA;
        while (f5 < -180.0F) {
            f5 += 360.0F;
        }
        while (f5 >= 180.0F) {
            f5 -= 360.0F;
        }
        this.aA += f5 * 0.3F;

        float f6 = this.v - this.aA;
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
        this.aA = (this.v - f6);
        if (f6 * f6 > 2500.0F) {
            this.aA += f6 * 0.2F;
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
        while (this.aA - this.aB < -180.0F) {
            this.aB -= 360.0F;
        }
        while (this.aA - this.aB >= 180.0F) {
            this.aB += 360.0F;
        }
        while (this.w - this.y < -180.0F) {
            this.y -= 360.0F;
        }
        while (this.w - this.y >= 180.0F) {
            this.y += 360.0F;
        }
        this.aE += f3;
    }

    @Override
    protected void a(float paramFloat1, float paramFloat2) {
        super.a(paramFloat1, paramFloat2);
    }

    public void a(int paramInt) {
        if (this.aR <= 0) {
            return;
        }
        this.aR += paramInt;
        if (this.aR > 20) {
            this.aR = 20;
        }
        this.ac = (this.aw / 2);
    }

    @Override
    public boolean a(ea paramea, int paramInt) {
        if (this.l.z) {
            return false;
        }
        this.bo = 0;
        if (this.aR <= 0) {
            return false;
        }

        this.be = 1.5F;

        // hMod damage entities.
        LivingEntity attacker = (paramea != null && paramea instanceof ka) ? new LivingEntity((ka) paramea) : null;
        LivingEntity defender = new LivingEntity(this);

        // hMod attack by entity, but it might not do damage!
        if (attacker != null && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ATTACK, attacker, defender, paramInt)) {
            return false;
        }

        int i = 1;
        if (this.ac > this.aw / 2.0F) {
            if (paramInt <= this.bn) {
                return false;
            }
            // hMod: partial damage
            if (attacker != null && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, defender, paramInt - bn)) {
                return false;
            }
            c(paramInt - this.bn);
            this.bn = paramInt;
            i = 0;
        } else {
            // hMod: full damage
            if (attacker != null && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, defender, paramInt)) {
                return false;
            }
            this.bn = paramInt;
            this.aS = this.aR;
            this.ac = this.aw;
            c(paramInt);
            this.aT = (this.aU = 10);
        }

        this.aV = 0.0F;

        if (i != 0) {
            // hMod: Forced cast to send 'damage animation'.
            this.l.a(this, (byte)2);
            u();
            if (paramea != null) {
                double d1 = paramea.p - this.p;
                double d2 = paramea.r - this.r;
                while (d1 * d1 + d2 * d2 < 0.0001D) {
                    d1 = (Math.random() - Math.random()) * 0.01D;
                    d2 = (Math.random() - Math.random()) * 0.01D;
                }
                this.aV = ((float) (Math.atan2(d2, d1) * 180.0D / 3.141592741012573D) - this.v);
                a(paramea, paramInt, d1, d2);
            } else {
                this.aV = ((int) (Math.random() * 2.0D) * 180);
            }
        }

        if (this.aR <= 0) {
            if (i != 0) {
                this.l.a(this, f(), h(), (this.W.nextFloat() - this.W.nextFloat()) * 0.2F + 1.0F);
            }
            f(paramea);
        } else if (i != 0) {
            this.l.a(this, e(), h(), (this.W.nextFloat() - this.W.nextFloat()) * 0.2F + 1.0F);
        }

        return true;
    }

    protected void c(int paramInt) {
        this.aR -= paramInt;
    }

    protected float h() {
        return 1.0F;
    }

    protected String d() {
        return null;
    }

    protected String e() {
        return "random.hurt";
    }

    protected String f() {
        return "random.hurt";
    }

    public void a(ea paramea, int paramInt, double paramDouble1, double paramDouble2) {
        float f1 = hh.a(paramDouble1 * paramDouble1 + paramDouble2 * paramDouble2);
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

    public void f(ea paramea) {
        if ((this.aM > 0) && (paramea != null)) {
            paramea.b(this, this.aM);
        }
        this.ba = true;

        if (!this.l.z) {
            int i = g();
            if (i > 0) {
                int j = this.W.nextInt(3);
                for (int k = 0; k < j; k++) {
                    a(i, 1);
                }
            }
        }

        // hMod: Forced cast to play Death Animations.
        this.l.a(this, (byte)3);
    }

    protected int g() {
        return 0;
    }

    @Override
    protected void a(float paramFloat) {
        int i = (int) Math.ceil(paramFloat - 3.0F);
        if (i > 0) {
            // hMod Damage hook: Falling
            LivingEntity defender = new LivingEntity(this);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FALL, null, defender, i)) {
                a(null, i);
            }

            int j = this.l.a(hh.b(this.p), hh.b(this.q - 0.2000000029802322D - this.H), hh.b(this.r));
            if (j > 0) {
                cd localcd = gc.m[j].bq;
                this.l.a(this, localcd.c(), localcd.a() * 0.5F, localcd.b() * 0.75F);
            }
        }
    }

    public void c(float paramFloat1, float paramFloat2) {
        double d1;
        if (r()) {
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
        } else if (t()) {
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
                int i = this.l.a(hh.b(this.p), hh.b(this.z.b) - 1, hh.b(this.r));
                if (i > 0) {
                    f1 = gc.m[i].bt * 0.91F;
                }
            }

            float f2 = 0.1627714F / (f1 * f1 * f1);
            a(paramFloat1, paramFloat2, this.A ? 0.1F * f2 : 0.02F);

            f1 = 0.91F;
            if (this.A) {
                f1 = 0.5460001F;
                int j = this.l.a(hh.b(this.p), hh.b(this.z.b) - 1, hh.b(this.r));
                if (j > 0) {
                    f1 = gc.m[j].bt * 0.91F;
                }
            }

            if (d_()) {
                this.N = 0.0F;
                if (this.t < -0.15D) {
                    this.t = -0.15D;
                }

            }

            c(this.s, this.t, this.u);

            if ((this.B) && (d_())) {
                this.t = 0.2D;
            }

            this.t -= 0.08D;
            this.t *= 0.9800000190734863D;
            this.s *= f1;
            this.u *= f1;
        }
        this.bd = this.be;
        double d2 = this.p - this.m;
        double d3 = this.r - this.o;
        float f3 = hh.a(d2 * d2 + d3 * d3) * 4.0F;
        if (f3 > 1.0F) {
            f3 = 1.0F;
        }
        this.be += (f3 - this.be) * 0.4F;
        this.bf += this.be;
    }

    public boolean d_() {
        int i = hh.b(this.p);
        int j = hh.b(this.z.b);
        int k = hh.b(this.r);
        return (this.l.a(i, j, k) == gc.aF.bh) || (this.l.a(i, j + 1, k) == gc.aF.bh);
    }

    @Override
    public void a(v paramv) {
        paramv.a("Health", (short) this.aR);
        paramv.a("HurtTime", (short) this.aT);
        paramv.a("DeathTime", (short) this.aW);
        paramv.a("AttackTime", (short) this.aX);
    }

    @Override
    public void b(v paramv) {
        this.aR = paramv.c("Health");
        if (!paramv.a("Health")) {
            this.aR = 10;
        }
        this.aT = paramv.c("HurtTime");
        this.aW = paramv.c("DeathTime");
        this.aX = paramv.c("AttackTime");

        // hMod: Lets unbreak 'dead' characters so admins don't have to delete .dat files anymore >.>
        if (aR < 0 || aW != 0) {
            aR = 20; // Health
            aW = 0; // DeathTime
            
            // Dead people belong @ Spawn on servers with health
            if (this instanceof et && etc.getInstance().isHealthEnabled()) {
                ((et) this).getPlayer().teleportTo(etc.getServer().getSpawnLocation());
            }
        }
    }

    @Override
    public boolean x() {
        return (!this.G) && (this.aR > 0);
    }

    public void E() {
        if (this.bg > 0) {
            double d1 = this.p + (this.bh - this.p) / this.bg;
            double d2 = this.q + (this.bi - this.q) / this.bg;
            double d3 = this.r + (this.bj - this.r) / this.bg;

            double d4 = this.bk - this.v;
            while (d4 < -180.0D) {
                d4 += 360.0D;
            }
            while (d4 >= 180.0D) {
                d4 -= 360.0D;
            }
            this.v = (float) (this.v + d4 / this.bg);
            this.w = (float) (this.w + (this.bl - this.w) / this.bg);

            this.bg -= 1;
            a(d1, d2, d3);
            b(this.v, this.w);
        }

        if (this.aR <= 0) {
            this.bs = false;
            this.bp = 0.0F;
            this.bq = 0.0F;
            this.br = 0.0F;
        } else if (!this.aO) {
            c();
        }

        boolean bool1 = r();
        boolean bool2 = t();

        if (this.bs) {
            if (bool1) {
                this.t += 0.03999999910593033D;
            } else if (bool2) {
                this.t += 0.03999999910593033D;
            } else if (this.A) {
                K();
            }

        }

        this.bp *= 0.98F;
        this.bq *= 0.98F;
        this.br *= 0.9F;
        c(this.bp, this.bq);

        List localList = this.l.b(this, this.z.b(0.2000000029802322D, 0.0D, 0.2000000029802322D));
        if ((localList != null) && (localList.size() > 0)) {
            for (int i = 0; i < localList.size(); i++) {
                ea localea = (ea) localList.get(i);
                if (!localea.v()) {
                    continue;
                }
                localea.c(this);
            }
        }
    }

    protected void K() {
        this.t = 0.4199999868869782D;
    }

    protected void c() {
        this.bo += 1;

        fz localfz = this.l.a(this, -1.0D);

        if (localfz != null) {
            double d1 = localfz.p - this.p;
            double d2 = localfz.q - this.q;
            double d3 = localfz.r - this.r;
            double d4 = d1 * d1 + d2 * d2 + d3 * d3;

            if (d4 > 16384.0D) {
                l();
            }

            if ((this.bo > 600) && (this.W.nextInt(800) == 0)) {
                if (d4 < 1024.0D) {
                    this.bo = 0;
                } else {
                    l();
                }
            }
        }

        this.bp = 0.0F;
        this.bq = 0.0F;

        float f = 8.0F;
        if (this.W.nextFloat() < 0.02F) {
            localfz = this.l.a(this, f);
            if (localfz != null) {
                this.b = localfz;
                this.c = (10 + this.W.nextInt(20));
            } else {
                this.br = ((this.W.nextFloat() - 0.5F) * 20.0F);
            }
        }

        if (this.b != null) {
            b(this.b, 10.0F);
            if ((this.c-- <= 0) || (this.b.G) || (this.b.b(this) > f * f)) {
                this.b = null;
            }
        } else {
            if (this.W.nextFloat() < 0.05F) {
                this.br = ((this.W.nextFloat() - 0.5F) * 20.0F);
            }
            this.v += this.br;
            this.w = this.bt;
        }

        boolean bool1 = r();
        boolean bool2 = t();
        if ((bool1) || (bool2)) {
            this.bs = (this.W.nextFloat() < 0.8F);
        }
    }

    public void b(ea paramea, float paramFloat) {
        double d1 = paramea.p - this.p;

        double d3 = paramea.r - this.r;
        double d2;
        if ((paramea instanceof ka)) {
            ka localka = (ka) paramea;
            d2 = localka.q + localka.s() - (this.q + s());
        } else {
            d2 = (paramea.z.b + paramea.z.e) / 2.0D - (this.q + s());
        }

        double d4 = hh.a(d1 * d1 + d3 * d3);

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

    public void L() {
    }

    public boolean a() {
        return (this.l.a(this.z)) && (this.l.a(this, this.z).size() == 0) && (!this.l.b(this.z));
    }

    @Override
    protected void o() {
        a(null, 4);
    }

    @Override
    public bd C() {
        return c(1.0F);
    }

    public bd c(float paramFloat) {
        // hMod fix the static reference here with a null element.
        bd bd = null;
        if (paramFloat == 1.0F) {
            float f1 = hh.b(-this.v * 0.01745329F - 3.141593F);
            float f2 = hh.a(-this.v * 0.01745329F - 3.141593F);
            float f3 = -hh.b(-this.w * 0.01745329F);
            float f4 = hh.a(-this.w * 0.01745329F);

            return bd.b(f2 * f3, f4, f1 * f3);
        }
        float f1 = this.y + (this.w - this.y) * paramFloat;
        float f2 = this.x + (this.v - this.x) * paramFloat;

        float f3 = hh.b(-f2 * 0.01745329F - 3.141593F);
        float f4 = hh.a(-f2 * 0.01745329F - 3.141593F);
        float f5 = -hh.b(-f1 * 0.01745329F);
        float f6 = hh.a(-f1 * 0.01745329F);

        return bd.b(f4 * f5, f6, f3 * f5);
    }

    public int i() {
        return 4;
    }
}
