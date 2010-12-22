
import java.util.List;
import java.util.Random;

public class lc extends ep {

    public int aF = 20;
    public float aG;
    public float aH;
    public float aI;
    public float aJ = 0.0F;
    public float aK = 0.0F;
    protected float aL;
    protected float aM;
    protected float aN;
    protected float aO;
    protected boolean aP = true;
    protected String aQ = "/mob/char.png";
    protected boolean aR = true;
    protected float aS = 0.0F;
    protected String aT = null;
    protected float aU = 1.0F;
    protected int aV = 0;
    protected float aW = 0.0F;
    public boolean aX = false;
    public float aY;
    public float aZ;
    public int ba;
    public int bb;
    private int a;
    public int bc;
    public int bd;
    public float be = 0.0F;
    public int bf = 0;
    public int bg = 0;
    public float bh;
    public float bi;
    protected boolean bj = false;
    public int bk = -1;
    public float bl = (float) (Math.random() * 0.8999999761581421D + 0.1000000014901161D);
    public float bm;
    public float bn;
    public float bo;
    protected int bp;
    protected double bq;
    protected double br;
    protected double bs;
    protected double bt;
    protected double bu;
    float bv = 0.0F;
    protected int bw = 0;
    protected int bx = 0;
    protected float by;
    protected float bz;
    protected float bA;
    protected boolean bB = false;
    protected float bC = 0.0F;
    protected float bD = 0.7F;
    private ep b;
    private int c = 0;

    public lc(ff paramff) {
        super(paramff);
        this.ba = 10;
        this.i = true;

        this.aI = ((float) (Math.random() + 1.0D) * 0.01F);
        a(this.p, this.q, this.r);
        this.aG = ((float) Math.random() * 12398.0F);
        this.v = (float) (Math.random() * 3.141592741012573D * 2.0D);
        this.aH = 1.0F;

        this.S = 0.5F;
    }

    public boolean i(ep paramep) {
        // hMod fix the static reference here with a null element.
        bn bn = null;
        return this.l.a(bn.b(this.p, this.q + s(), this.r), bn.b(paramep.p, paramep.q + paramep.s(), paramep.r)) == null;
    }

    public boolean c_() {
        return !this.G;
    }

    public boolean v() {
        return !this.G;
    }

    public float s() {
        return this.J * 0.85F;
    }

    public int b() {
        return 80;
    }

    public void m() {
        this.aY = this.aZ;
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
        if ((x()) && (a(la.f))) {
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

        this.bh = this.bi;

        if (this.bg > 0) {
            this.bg -= 1;
        }
        if (this.bc > 0) {
            this.bc -= 1;
        }
        if (this.ac > 0) {
            this.ac -= 1;
        }
        if (this.ba <= 0) {
            this.bf += 1;
            if (this.bf > 20) {
                Q();
                l();
                for (i = 0; i < 20; i++) {
                    double d1 = this.W.nextGaussian() * 0.02D;
                    double d2 = this.W.nextGaussian() * 0.02D;
                    double d3 = this.W.nextGaussian() * 0.02D;
                    this.l.a("explode", this.p + this.W.nextFloat() * this.I * 2.0F - this.I, this.q + this.W.nextFloat() * this.J, this.r + this.W.nextFloat() * this.I * 2.0F - this.I, d1, d2, d3);
                }
            }
        }

        this.aO = this.aN;

        this.aK = this.aJ;
        this.x = this.v;
        this.y = this.w;
    }

    public void O() {
        for (int i = 0; i < 20; i++) {
            double d1 = this.W.nextGaussian() * 0.02D;
            double d2 = this.W.nextGaussian() * 0.02D;
            double d3 = this.W.nextGaussian() * 0.02D;
            double d4 = 10.0D;
            this.l.a("explode", this.p + this.W.nextFloat() * this.I * 2.0F - this.I - d1 * d4, this.q + this.W.nextFloat() * this.J - d2 * d4, this.r + this.W.nextFloat() * this.I * 2.0F - this.I - d3 * d4, d1, d2, d3);
        }
    }

    public void z() {
        super.z();
        this.aL = this.aM;
        this.aM = 0.0F;
    }

    public void b_() {
        super.b_();

        G();

        double d1 = this.p - this.m;
        double d2 = this.r - this.o;

        float f1 = ic.a(d1 * d1 + d2 * d2);

        float f2 = this.aJ;

        float f3 = 0.0F;
        this.aL = this.aM;
        float f4 = 0.0F;
        if (f1 > 0.05F) {
            f4 = 1.0F;
            f3 = f1 * 3.0F;
            f2 = (float) Math.atan2(d2, d1) * 180.0F / 3.141593F - 90.0F;
        }
        if (this.aZ > 0.0F) {
            f2 = this.v;
        }
        if (!this.A) {
            f4 = 0.0F;
        }
        this.aM += (f4 - this.aM) * 0.3F;

        float f5 = f2 - this.aJ;
        while (f5 < -180.0F) {
            f5 += 360.0F;
        }
        while (f5 >= 180.0F) {
            f5 -= 360.0F;
        }
        this.aJ += f5 * 0.3F;

        float f6 = this.v - this.aJ;
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
        this.aJ = (this.v - f6);
        if (f6 * f6 > 2500.0F) {
            this.aJ += f6 * 0.2F;
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
        while (this.aJ - this.aK < -180.0F) {
            this.aK -= 360.0F;
        }
        while (this.aJ - this.aK >= 180.0F) {
            this.aK += 360.0F;
        }
        while (this.w - this.y < -180.0F) {
            this.y -= 360.0F;
        }
        while (this.w - this.y >= 180.0F) {
            this.y += 360.0F;
        }
        this.aN += f3;
    }

    protected void a(float paramFloat1, float paramFloat2) {
        super.a(paramFloat1, paramFloat2);
    }

    public void c(int paramInt) {
        if (this.ba <= 0) {
            return;
        }
        this.ba += paramInt;
        if (this.ba > 20) {
            this.ba = 20;
        }
        this.ac = (this.aF / 2);
    }

    public boolean a(ep paramep, int paramInt) {
        if (this.l.z) {
            return false;
        }
        this.bx = 0;
        if (this.ba <= 0) {
            return false;
        }

        this.bn = 1.5F;

        // hMod damage entities.
        LivingEntity attacker = (paramep != null && paramep instanceof lc) ? new LivingEntity((lc) paramep) : null;
        LivingEntity defender = new LivingEntity(this);

        // hMod attack by entity, but it might not do damage!
        if (attacker != null && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ATTACK, attacker, defender, paramInt)) {
            return false;
        }

        int i = 1;
        if (this.ac > this.aF / 2.0F) {
            if (paramInt <= this.bw) {
                return false;
            }
            // hMod: partial damage
            if (attacker != null && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, defender, paramInt - bn)) {
                return false;
            }
            d(paramInt - this.bw);
            this.bw = paramInt;
            i = 0;
        } else {
            // hMod: full damage
            if (attacker != null && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, defender, paramInt)) {
                return false;
            }
            this.bw = paramInt;
            this.bb = this.ba;
            this.ac = this.aF;
            d(paramInt);
            this.bc = (this.bd = 10);
        }

        this.be = 0.0F;

        if (i != 0) {
            // hMod: Forced cast to send 'damage animation'.
            this.l.a(this, (byte)2);
            u();
            if (paramep != null) {
                double d1 = paramep.p - this.p;
                double d2 = paramep.r - this.r;
                while (d1 * d1 + d2 * d2 < 0.0001D) {
                    d1 = (Math.random() - Math.random()) * 0.01D;
                    d2 = (Math.random() - Math.random()) * 0.01D;
                }
                this.be = ((float) (Math.atan2(d2, d1) * 180.0D / 3.141592741012573D) - this.v);
                a(paramep, paramInt, d1, d2);
            } else {
                this.be = ((int) (Math.random() * 2.0D) * 180);
            }
        }

        if (this.ba <= 0) {
            if (i != 0) {
                this.l.a(this, f(), h(), (this.W.nextFloat() - this.W.nextFloat()) * 0.2F + 1.0F);
            }
            f(paramep);
        } else if (i != 0) {
            this.l.a(this, e(), h(), (this.W.nextFloat() - this.W.nextFloat()) * 0.2F + 1.0F);
        }

        return true;
    }

    protected void d(int paramInt) {
        this.ba -= paramInt;
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

    public void a(ep paramep, int paramInt, double paramDouble1, double paramDouble2) {
        float f1 = ic.a(paramDouble1 * paramDouble1 + paramDouble2 * paramDouble2);
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

    public void f(ep paramep) {
        if ((this.aV > 0) && (paramep != null)) {
            paramep.b(this, this.aV);
        }
        this.bj = true;

        if (!this.l.z) {
            int i = g();
            if (i > 0) {
                int j = this.W.nextInt(3);
                for (int k = 0; k < j; k++) {
                    b(i, 1);
                }
            }
        }

        // hMod: Forced cast to play Death Animations.
        this.l.a(this, (byte)3);
    }

    protected int g() {
        return 0;
    }

    protected void a(float paramFloat) {
        int i = (int) Math.ceil(paramFloat - 3.0F);
        if (i > 0) {
            // hMod Damage hook: Falling
            LivingEntity defender = new LivingEntity(this);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FALL, null, defender, i)) {
                a(null, i);
            }

            int j = this.l.a(ic.b(this.p), ic.b(this.q - 0.2000000029802322D - this.H), ic.b(this.r));
            if (j > 0) {
                cp localcp = gv.m[j].bq;
                this.l.a(this, localcp.c(), localcp.a() * 0.5F, localcp.b() * 0.75F);
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
                int i = this.l.a(ic.b(this.p), ic.b(this.z.b) - 1, ic.b(this.r));
                if (i > 0) {
                    f1 = gv.m[i].bt * 0.91F;
                }
            }

            float f2 = 0.1627714F / (f1 * f1 * f1);
            a(paramFloat1, paramFloat2, this.A ? 0.1F * f2 : 0.02F);

            f1 = 0.91F;
            if (this.A) {
                f1 = 0.5460001F;
                int j = this.l.a(ic.b(this.p), ic.b(this.z.b) - 1, ic.b(this.r));
                if (j > 0) {
                    f1 = gv.m[j].bt * 0.91F;
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
        this.bm = this.bn;
        double d2 = this.p - this.m;
        double d3 = this.r - this.o;
        float f3 = ic.a(d2 * d2 + d3 * d3) * 4.0F;
        if (f3 > 1.0F) {
            f3 = 1.0F;
        }
        this.bn += (f3 - this.bn) * 0.4F;
        this.bo += this.bn;
    }

    public boolean d_() {
        int i = ic.b(this.p);
        int j = ic.b(this.z.b);
        int k = ic.b(this.r);
        return (this.l.a(i, j, k) == gv.aF.bh) || (this.l.a(i, j + 1, k) == gv.aF.bh);
    }

    public void a(ad paramad) {
        paramad.a("Health", (short) this.ba);
        paramad.a("HurtTime", (short) this.bc);
        paramad.a("DeathTime", (short) this.bf);
        paramad.a("AttackTime", (short) this.bg);
    }

    public void b(ad paramad) {
        this.ba = paramad.c("Health");
        if (!paramad.a("Health")) {
            this.ba = 10;
        }
        this.bc = paramad.c("HurtTime");
        this.bf = paramad.c("DeathTime");
        this.bg = paramad.c("AttackTime");

        // TODO: Death code, is it needed anymore?
    }

    public boolean x() {
        return (!this.G) && (this.ba > 0);
    }

    public void G() {
        if (this.bp > 0) {
            double d1 = this.p + (this.bq - this.p) / this.bp;
            double d2 = this.q + (this.br - this.q) / this.bp;
            double d3 = this.r + (this.bs - this.r) / this.bp;

            double d4 = this.bt - this.v;
            while (d4 < -180.0D) {
                d4 += 360.0D;
            }
            while (d4 >= 180.0D) {
                d4 -= 360.0D;
            }
            this.v = (float) (this.v + d4 / this.bp);
            this.w = (float) (this.w + (this.bu - this.w) / this.bp);

            this.bp -= 1;
            a(d1, d2, d3);
            b(this.v, this.w);
        }

        if (this.ba <= 0) {
            this.bB = false;
            this.by = 0.0F;
            this.bz = 0.0F;
            this.bA = 0.0F;
        } else if (!this.aX) {
            c();
        }

        boolean bool1 = r();
        boolean bool2 = t();

        if (this.bB) {
            if (bool1) {
                this.t += 0.03999999910593033D;
            } else if (bool2) {
                this.t += 0.03999999910593033D;
            } else if (this.A) {
                P();
            }

        }

        this.by *= 0.98F;
        this.bz *= 0.98F;
        this.bA *= 0.9F;
        c(this.by, this.bz);

        List localList = this.l.b(this, this.z.b(0.2000000029802322D, 0.0D, 0.2000000029802322D));
        if ((localList != null) && (localList.size() > 0)) {
            for (int i = 0; i < localList.size(); i++) {
                ep localep = (ep) localList.get(i);
                if (!localep.v()) {
                    continue;
                }
                localep.c(this);
            }
        }
    }

    protected void P() {
        this.t = 0.4199999868869782D;
    }

    protected void c() {
        this.bx += 1;

        gq localgp = this.l.a(this, -1.0D);

        if (localgp != null) {
            double d1 = localgp.p - this.p;
            double d2 = localgp.q - this.q;
            double d3 = localgp.r - this.r;
            double d4 = d1 * d1 + d2 * d2 + d3 * d3;

            if (d4 > 16384.0D) {
                l();
            }

            if ((this.bx > 600) && (this.W.nextInt(800) == 0)) {
                if (d4 < 1024.0D) {
                    this.bx = 0;
                } else {
                    l();
                }
            }
        }

        this.by = 0.0F;
        this.bz = 0.0F;

        float f = 8.0F;
        if (this.W.nextFloat() < 0.02F) {
            localgp = this.l.a(this, f);
            if (localgp != null) {
                this.b = localgp;
                this.c = (10 + this.W.nextInt(20));
            } else {
                this.bA = ((this.W.nextFloat() - 0.5F) * 20.0F);
            }
        }

        if (this.b != null) {
            b(this.b, 10.0F);
            if ((this.c-- <= 0) || (this.b.G) || (this.b.b(this) > f * f)) {
                this.b = null;
            }
        } else {
            if (this.W.nextFloat() < 0.05F) {
                this.bA = ((this.W.nextFloat() - 0.5F) * 20.0F);
            }
            this.v += this.bA;
            this.w = this.bC;
        }

        boolean bool1 = r();
        boolean bool2 = t();
        if ((bool1) || (bool2)) {
            this.bB = (this.W.nextFloat() < 0.8F);
        }
    }

    public void b(ep paramep, float paramFloat) {
        double d1 = paramep.p - this.p;

        double d3 = paramep.r - this.r;
        double d2;
        if ((paramep instanceof lc)) {
            lc locallb = (lc) paramep;
            d2 = locallb.q + locallb.s() - (this.q + s());
        } else {
            d2 = (paramep.z.b + paramep.z.e) / 2.0D - (this.q + s());
        }

        double d4 = ic.a(d1 * d1 + d3 * d3);

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

    public void Q() {
    }

    public boolean a() {
        return (this.l.a(this.z)) && (this.l.a(this, this.z).size() == 0) && (!this.l.b(this.z));
    }

    protected void o() {
        a(null, 4);
    }

    public bn C() {
        return c(1.0F);
    }

    public bn c(float paramFloat) {
        // hMod fix the static reference here with a null element.
        bn bn = null;
        if (paramFloat == 1.0F) {
            float f1 = ic.b(-this.v * 0.01745329F - 3.141593F);
            float f2 = ic.a(-this.v * 0.01745329F - 3.141593F);
            float f3 = -ic.b(-this.w * 0.01745329F);
            float f4 = ic.a(-this.w * 0.01745329F);

            return bn.b(f2 * f3, f4, f1 * f3);
        }
        float f1 = this.y + (this.w - this.y) * paramFloat;
        float f2 = this.x + (this.v - this.x) * paramFloat;

        float f3 = ic.b(-f2 * 0.01745329F - 3.141593F);
        float f4 = ic.a(-f2 * 0.01745329F - 3.141593F);
        float f5 = -ic.b(-f1 * 0.01745329F);
        float f6 = ic.a(-f1 * 0.01745329F);

        return bn.b(f4 * f5, f6, f3 * f5);
    }

    public int i() {
        return 4;
    }
}
