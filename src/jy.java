
import java.util.List;

public class jy extends dx {

    public int av = 20;
    public float aw;
    public float ax;
    public float ay;
    public float az = 0.0F;
    public float aA = 0.0F;
    protected float aB;
    protected float aC;
    protected float aD;
    protected float aE;
    protected boolean aF = true;
    protected String aG = "/mob/char.png";
    protected boolean aH = true;
    protected float aI = 0.0F;
    protected String aJ = null;
    protected float aK = 1.0F;
    protected int aL = 0;
    protected float aM = 0.0F;
    public boolean aN = false;
    public float aO;
    public float aP;
    public int aQ;
    public int aR;
    private int a;
    public int aS;
    public int aT;
    public float aU = 0.0F;
    public int aV = 0;
    public int aW = 0;
    public float aX;
    public float aY;
    protected boolean aZ = false;
    public int ba = -1;
    public float bb = (float) (Math.random() * 0.8999999761581421D + 0.1000000014901161D);
    public float bc;
    public float bd;
    public float be;
    protected int bf;
    protected double bg;
    protected double bh;
    protected double bi;
    protected double bj;
    protected double bk;
    float bl = 0.0F;
    protected int bm = 0;
    protected int bn = 0;
    protected float bo;
    protected float bp;
    protected float bq;
    protected boolean br = false;
    protected float bs = 0.0F;
    protected float bt = 0.7F;
    private dx b;
    private int c = 0;

    public jy(eo parameo) {
        super(parameo);
        aQ = 10;
        i = true;

        ay = ((float) (Math.random() + 1.0D) * 0.01F);
        a(p, q, r);
        aw = ((float) Math.random() * 12398.0F);
        v = (float) (Math.random() * 3.141592741012573D * 2.0D);
        ax = 1.0F;

        S = 0.5F;
    }

    public boolean i(dx paramdx) {
        bd bd = null;
        return l.a(bd.b(p, q + s(), r), bd.b(paramdx.p, paramdx.q + paramdx.s(), paramdx.r)) == null;
    }

    @Override
    public boolean c_() {
        return !G;
    }

    @Override
    public boolean v() {
        return !G;
    }

    @Override
    public float s() {
        return J * 0.85F;
    }

    public int b() {
        return 80;
    }

    @Override
    public void m() {
        aO = aP;
        super.m();

        if (W.nextInt(1000) < a++) {
            a = (-b());
            String str = d();
            if (str != null) {
                l.a(this, str, h(), (W.nextFloat() - W.nextFloat()) * 0.2F + 1.0F);
            }
        }

        if ((x()) && (y())) {
            a(null, 1);
        }

        if ((ae) || (l.z)) {
            Z = 0;
        }
        int i;
        if ((x()) && (a(jw.f))) {
            ad -= 1;
            if (ad == -20) {
                ad = 0;
                for (i = 0; i < 8; i++) {
                    float f1 = W.nextFloat() - W.nextFloat();
                    float f2 = W.nextFloat() - W.nextFloat();
                    float f3 = W.nextFloat() - W.nextFloat();
                    l.a("bubble", p + f1, q + f2, r + f3, s, t, u);
                }
                // hMod Damage hook: Drowning
                LivingEntity defender = new LivingEntity(this);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new Object[]{PluginLoader.DamageType.WATER, null, defender, 2})) {
                    a(null, 2);
                }
            }

            Z = 0;
        } else {
            ad = aa;
        }

        aX = aY;

        if (aW > 0) {
            aW -= 1;
        }
        if (aS > 0) {
            aS -= 1;
        }
        if (ac > 0) {
            ac -= 1;
        }
        if (aQ <= 0) {
            aV += 1;
            if (aV > 20) {
                L();
                l();
                for (i = 0; i < 20; i++) {
                    double d1 = W.nextGaussian() * 0.02D;
                    double d2 = W.nextGaussian() * 0.02D;
                    double d3 = W.nextGaussian() * 0.02D;
                    l.a("explode", p + W.nextFloat() * I * 2.0F - I, q + W.nextFloat() * J, r + W.nextFloat() * I * 2.0F - I, d1, d2,
                            d3);
                }
            }
        }

        aE = aD;

        aA = az;
        x = v;
        y = w;
    }

    public void J() {
        for (int i = 0; i < 20; i++) {
            double d1 = W.nextGaussian() * 0.02D;
            double d2 = W.nextGaussian() * 0.02D;
            double d3 = W.nextGaussian() * 0.02D;
            double d4 = 10.0D;
            l.a("explode", p + W.nextFloat() * I * 2.0F - I - d1 * d4, q + W.nextFloat() * J - d2 * d4, r + W.nextFloat() * I * 2.0F
                    - I - d3 * d4, d1, d2, d3);
        }
    }

    @Override
    public void z() {
        super.z();
        aB = aC;
        aC = 0.0F;
    }

    @Override
    public void b_() {
        super.b_();

        E();

        double d1 = p - m;
        double d2 = r - o;

        float f1 = hf.a(d1 * d1 + d2 * d2);

        float f2 = az;

        float f3 = 0.0F;
        aB = aC;
        float f4 = 0.0F;
        if (f1 > 0.05F) {
            f4 = 1.0F;
            f3 = f1 * 3.0F;
            f2 = (float) Math.atan2(d2, d1) * 180.0F / 3.141593F - 90.0F;
        }
        if (aP > 0.0F) {
            f2 = v;
        }
        if (!A) {
            f4 = 0.0F;
        }
        aC += (f4 - aC) * 0.3F;

        float f5 = f2 - az;
        while (f5 < -180.0F) {
            f5 += 360.0F;
        }
        while (f5 >= 180.0F) {
            f5 -= 360.0F;
        }
        az += f5 * 0.3F;

        float f6 = v - az;
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
        az = (v - f6);
        if (f6 * f6 > 2500.0F) {
            az += f6 * 0.2F;
        }

        if (i != 0) {
            f3 *= -1.0F;
        }
        while (v - x < -180.0F) {
            x -= 360.0F;
        }
        while (v - x >= 180.0F) {
            x += 360.0F;
        }
        while (az - aA < -180.0F) {
            aA -= 360.0F;
        }
        while (az - aA >= 180.0F) {
            aA += 360.0F;
        }
        while (w - y < -180.0F) {
            y -= 360.0F;
        }
        while (w - y >= 180.0F) {
            y += 360.0F;
        }
        aD += f3;
    }

    @Override
    protected void a(float paramFloat1, float paramFloat2) {
        super.a(paramFloat1, paramFloat2);
    }

    public void a(int paramInt) {
        if (aQ <= 0) {
            return;
        }
        aQ += paramInt;
        if (aQ > 20) {
            aQ = 20;
        }
        ac = (av / 2);
    }

    @Override
    public boolean a(dx paramdx, int paramInt) {
        if (l.z) {
            return false;
        }
        bn = 0;
        if (aQ <= 0) {
            return false;
        }

        bd = 1.5F;
        // hMod: the different entities are defined in hn
        if (paramdx != null && (paramdx instanceof jy)) {
            LivingEntity attacker = new LivingEntity((jy)paramdx);
            LivingEntity defender = new LivingEntity(this);
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new Object[]{PluginLoader.DamageType.ENTITY, attacker, defender, paramInt})) {
                return false;
            }
        }

        int i = 1;
        if (ac > av / 2.0F) {
            if (paramInt <= bm) {
                return false;
            }
            c(paramInt - bm);
            bm = paramInt;
            i = 0;
        } else {
            bm = paramInt;
            aR = aQ;
            ac = av;
            c(paramInt);
            aS = (this.aT = 10); // <=
        }

        aU = 0.0F;

        if (i != 0) {
            u();
            if (paramdx != null) {
                double d1 = paramdx.p - p;
                double d2 = paramdx.r - r;
                while (d1 * d1 + d2 * d2 < 0.0001D) {
                    d1 = (Math.random() - Math.random()) * 0.01D;
                    d2 = (Math.random() - Math.random()) * 0.01D;
                }
                aU = ((float) (Math.atan2(d2, d1) * 180.0D / 3.141592741012573D) - v);
                a(paramdx, paramInt, d1, d2);
            } else {
                aU = ((int) (Math.random() * 2.0D) * 180);
            }
        }

        if (aQ <= 0) {
            if (i != 0) {
                l.a(this, f(), h(), (W.nextFloat() - W.nextFloat()) * 0.2F + 1.0F);
            }
            f(paramdx);
        } else if (i != 0) {
            l.a(this, e(), h(), (W.nextFloat() - W.nextFloat()) * 0.2F + 1.0F);
        }

        return true;
    }

    protected void c(int paramInt) {
        aQ -= paramInt;
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

    public void a(dx paramdx, int paramInt, double paramDouble1, double paramDouble2) {
        float f1 = hf.a(paramDouble1 * paramDouble1 + paramDouble2 * paramDouble2);
        float f2 = 0.4F;

        s /= 2.0D;
        t /= 2.0D;
        u /= 2.0D;

        s -= paramDouble1 / f1 * f2;
        t += 0.4000000059604645D;
        u -= paramDouble2 / f1 * f2;

        if (t > 0.4000000059604645D) {
            t = 0.4000000059604645D;
        }
    }

    public void f(dx paramdx) {
        if ((aL > 0) && (paramdx != null)) {
            paramdx.b(this, aL);
        }
        aZ = true;

        if (!l.z) {
            int i = g();
            if (i > 0) {
                int j = W.nextInt(3);
                for (int k = 0; k < j; k++) {
                    a(i, 1);
                }
            }
        }

        l.a(this, 3);
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
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new Object[]{PluginLoader.DamageType.FALL, null, defender, i})) {
                a(null, i);
            }

            int j = l.a(hf.b(p), hf.b(q - 0.2000000029802322D - H), hf.b(r));
            if (j > 0) {
                cd localcd = ga.m[j].bq;
                l.a(this, localcd.c(), localcd.a() * 0.5F, localcd.b() * 0.75F);
            }
        }
    }

    public void c(float paramFloat1, float paramFloat2) {
        double d1;
        if (r()) {
            d1 = q;
            a(paramFloat1, paramFloat2, 0.02F);
            c(s, t, u);

            s *= 0.800000011920929D;
            t *= 0.800000011920929D;
            u *= 0.800000011920929D;
            t -= 0.02D;

            if ((B) && (b(s, t + 0.6000000238418579D - q + d1, u))) {
                t = 0.300000011920929D;
            }
        } else if (t()) {
            d1 = q;
            a(paramFloat1, paramFloat2, 0.02F);
            c(s, t, u);
            s *= 0.5D;
            t *= 0.5D;
            u *= 0.5D;
            t -= 0.02D;

            if ((B) && (b(s, t + 0.6000000238418579D - q + d1, u))) {
                t = 0.300000011920929D;
            }
        } else {
            float f1 = 0.91F;
            if (A) {
                f1 = 0.5460001F;
                int i = l.a(hf.b(p), hf.b(z.b) - 1, hf.b(r));
                if (i > 0) {
                    f1 = ga.m[i].bt * 0.91F;
                }
            }

            float f2 = 0.1627714F / (f1 * f1 * f1);
            a(paramFloat1, paramFloat2, A ? 0.1F * f2 : 0.02F);

            f1 = 0.91F;
            if (A) {
                f1 = 0.5460001F;
                int j = l.a(hf.b(p), hf.b(z.b) - 1, hf.b(r));
                if (j > 0) {
                    f1 = ga.m[j].bt * 0.91F;
                }
            }

            if (d_()) {
                N = 0.0F;
                if (t < -0.15D) {
                    t = -0.15D;
                }

            }

            c(s, t, u);

            if ((B) && (d_())) {
                t = 0.2D;
            }

            t -= 0.08D;
            t *= 0.9800000190734863D;
            s *= f1;
            u *= f1;
        }
        bc = bd;
        double d2 = p - m;
        double d3 = r - o;
        float f3 = hf.a(d2 * d2 + d3 * d3) * 4.0F;
        if (f3 > 1.0F) {
            f3 = 1.0F;
        }
        bd += (f3 - bd) * 0.4F;
        be += bd;
    }

    public boolean d_() {
        int i = hf.b(p);
        int j = hf.b(z.b);
        int k = hf.b(r);
        return (l.a(i, j, k) == ga.aF.bh) || (l.a(i, j + 1, k) == ga.aF.bh);
    }

    @Override
    public void a(v paramv) {
        paramv.a("Health", (short) aQ);
        paramv.a("HurtTime", (short) aS);
        paramv.a("DeathTime", (short) aV);
        paramv.a("AttackTime", (short) aW);
    }

    @Override
    public void b(v paramv) {
        aQ = paramv.c("Health");
        if (!paramv.a("Health")) {
            aQ = 10;
        }
        aS = paramv.c("HurtTime");
        aV = paramv.c("DeathTime");
        aW = paramv.c("AttackTime");

        // hMod: Lets unbreak 'dead' characters so admins don't have to delete .dat files anymore >.>
        if (aQ < 0 || aV != 0) {
            aQ = 20; // Health
            aV = 0; // DeathTime
        }
    }

    @Override
    public boolean x() {
        return (!G) && (aQ > 0);
    }

    public void E() {
        if (bf > 0) {
            double d1 = p + (bg - p) / bf;
            double d2 = q + (bh - q) / bf;
            double d3 = r + (bi - r) / bf;

            double d4 = bj - v;
            while (d4 < -180.0D) {
                d4 += 360.0D;
            }
            while (d4 >= 180.0D) {
                d4 -= 360.0D;
            }
            v = (float) (v + d4 / bf);
            w = (float) (w + (bk - w) / bf);

            bf -= 1;
            a(d1, d2, d3);
            b(v, w);
        }

        if (aQ <= 0) {
            br = false;
            bo = 0.0F;
            bp = 0.0F;
            bq = 0.0F;
        } else if (!aN) {
            c();
        }

        boolean bool1 = r();
        boolean bool2 = t();

        if (br) {
            if (bool1) {
                t += 0.03999999910593033D;
            } else if (bool2) {
                t += 0.03999999910593033D;
            } else if (A) {
                K();
            }

        }

        bo *= 0.98F;
        bp *= 0.98F;
        bq *= 0.9F;
        c(bo, bp);

        List localList = l.b(this, z.b(0.2000000029802322D, 0.0D, 0.2000000029802322D));
        if ((localList != null) && (localList.size() > 0)) {
            for (int i = 0; i < localList.size(); i++) {
                dx localdx = (dx) localList.get(i);
                if (!localdx.v()) {
                    continue;
                }
                localdx.c(this);
            }
        }
    }

    protected void K() {
        t = 0.4199999868869782D;
    }

    protected void c() {
        bn += 1;

        fx localfx = l.a(this, -1.0D);

        if (localfx != null) {
            double d1 = localfx.p - p;
            double d2 = localfx.q - q;
            double d3 = localfx.r - r;
            double d4 = d1 * d1 + d2 * d2 + d3 * d3;

            if (d4 > 16384.0D) {
                l();
            }

            if ((bn > 600) && (W.nextInt(800) == 0)) {
                if (d4 < 1024.0D) {
                    bn = 0;
                } else {
                    l();
                }
            }
        }

        bo = 0.0F;
        bp = 0.0F;

        float f = 8.0F;
        if (W.nextFloat() < 0.02F) {
            localfx = l.a(this, f);
            if (localfx != null) {
                b = localfx;
                c = (10 + W.nextInt(20));
            } else {
                bq = ((W.nextFloat() - 0.5F) * 20.0F);
            }
        }

        if (b != null) {
            b(b, 10.0F);
            if ((c-- <= 0) || (b.G) || (b.b(this) > f * f)) {
                b = null;
            }
        } else {
            if (W.nextFloat() < 0.05F) {
                bq = ((W.nextFloat() - 0.5F) * 20.0F);
            }
            v += bq;
            w = bs;
        }

        boolean bool1 = r();
        boolean bool2 = t();
        if ((bool1) || (bool2)) {
            br = (W.nextFloat() < 0.8F);
        }
    }

    public void b(dx paramdx, float paramFloat) {
        double d1 = paramdx.p - p;

        double d3 = paramdx.r - r;
        double d2;
        if ((paramdx instanceof jy)) {
            jy localjy = (jy) paramdx;
            d2 = localjy.q + localjy.s() - (q + s());
        } else {
            d2 = (paramdx.z.b + paramdx.z.e) / 2.0D - (q + s());
        }

        double d4 = hf.a(d1 * d1 + d3 * d3);

        float f1 = (float) (Math.atan2(d3, d1) * 180.0D / 3.141592741012573D) - 90.0F;
        float f2 = (float) (Math.atan2(d2, d4) * 180.0D / 3.141592741012573D);
        w = b(w, f2, paramFloat);
        v = b(v, f1, paramFloat);
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
        return (l.a(z)) && (l.a(this, z).size() == 0) && (!l.b(z));
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
        bd bd = null;
        if (paramFloat == 1.0F) {
            float f1 = hf.b(-v * 0.01745329F - 3.141593F);
            float f2 = hf.a(-v * 0.01745329F - 3.141593F);
            float f3 = -hf.b(-w * 0.01745329F);
            float f4 = hf.a(-w * 0.01745329F);

            return bd.b(((double) f2 * f3), f4, (double) f1 * f3);
        }
        float f1 = y + (w - y) * paramFloat;
        float f2 = x + (v - x) * paramFloat;

        float f3 = hf.b(-f2 * 0.01745329F - 3.141593F);
        float f4 = hf.a(-f2 * 0.01745329F - 3.141593F);
        float f5 = -hf.b(-f1 * 0.01745329F);
        float f6 = hf.a(-f1 * 0.01745329F);

        return bd.b(f4 * f5, f6, f3 * f5);
    }

    public int i() {
        return 4;
    }
}
