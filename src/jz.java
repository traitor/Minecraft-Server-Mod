import java.util.List;

public class jz extends dy {
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
    private dy b;
    private int c = 0;

    public jz(ep paramep) {
        super(paramep);
        aR = 10;
        i = true;

        az = ((float) (Math.random() + 1.0D) * 0.01F);
        a(p, q, r);
        ax = ((float) Math.random() * 12398.0F);
        v = (float) (Math.random() * 3.141592741012573D * 2.0D);
        ay = 1.0F;

        S = 0.5F;
    }

    public boolean i(dy paramdy) {
        // hMod fix the static reference here with a null element.
        bd bd = null;
        return l.a(bd.b(p, q + s(), r), bd.b(paramdy.p, paramdy.q + paramdy.s(), paramdy.r)) == null;
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
        aP = aQ;
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
        if ((x()) && (a(jx.f))) {
            ad -= 1;
            if (ad == -20) {
                ad = 0;
                for (i = 0; i < 8; i++) {
                    float f1 = W.nextFloat() - W.nextFloat();
                    float f2 = W.nextFloat() - W.nextFloat();
                    float f3 = W.nextFloat() - W.nextFloat();
                    l.a("bubble", p + f1, q + f2, r + f3, s, t, u);
                    // hMod Damage hook: Drowning
                    LivingEntity defender = new LivingEntity(this);
                    if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new Object[]{PluginLoader.DamageType.WATER, null, defender, 2})) {
                        a(null, 2);
                    }
                }
                a(null, 2);
            }

            Z = 0;
        } else {
            ad = aa;
        }

        aY = aZ;

        if (aX > 0) {
            aX -= 1;
        }
        if (aT > 0) {
            aT -= 1;
        }
        if (ac > 0) {
            ac -= 1;
        }
        if (aR <= 0) {
            aW += 1;
            if (aW > 20) {
                L();
                l();
                for (i = 0; i < 20; i++) {
                    double d1 = W.nextGaussian() * 0.02D;
                    double d2 = W.nextGaussian() * 0.02D;
                    double d3 = W.nextGaussian() * 0.02D;
                    l.a("explode", p + W.nextFloat() * I * 2.0F - I, q + W.nextFloat() * J, r + W.nextFloat() * I * 2.0F - I, d1, d2, d3);
                }
            }
        }

        aF = aE;

        aB = aA;
        x = v;
        y = w;
    }

    public void J() {
        for (int i = 0; i < 20; i++) {
            double d1 = W.nextGaussian() * 0.02D;
            double d2 = W.nextGaussian() * 0.02D;
            double d3 = W.nextGaussian() * 0.02D;
            double d4 = 10.0D;
            l.a("explode", p + W.nextFloat() * I * 2.0F - I - d1 * d4, q + W.nextFloat() * J - d2 * d4, r + W.nextFloat() * I * 2.0F - I - d3 * d4, d1, d2, d3);
        }
    }

    @Override
    public void z() {
        super.z();
        aC = aD;
        aD = 0.0F;
    }

    @Override
    public void b_() {
        super.b_();

        E();

        double d1 = p - m;
        double d2 = r - o;

        float f1 = hg.a(d1 * d1 + d2 * d2);

        float f2 = aA;

        float f3 = 0.0F;
        aC = aD;
        float f4 = 0.0F;
        if (f1 > 0.05F) {
            f4 = 1.0F;
            f3 = f1 * 3.0F;
            f2 = (float) Math.atan2(d2, d1) * 180.0F / 3.141593F - 90.0F;
        }
        if (aQ > 0.0F) {
            f2 = v;
        }
        if (!A) {
            f4 = 0.0F;
        }
        aD += (f4 - aD) * 0.3F;

        float f5 = f2 - aA;
        while (f5 < -180.0F) {
            f5 += 360.0F;
        }
        while (f5 >= 180.0F) {
            f5 -= 360.0F;
        }
        aA += f5 * 0.3F;

        float f6 = v - aA;
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
        aA = (v - f6);
        if (f6 * f6 > 2500.0F) {
            aA += f6 * 0.2F;
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
        while (aA - aB < -180.0F) {
            aB -= 360.0F;
        }
        while (aA - aB >= 180.0F) {
            aB += 360.0F;
        }
        while (w - y < -180.0F) {
            y -= 360.0F;
        }
        while (w - y >= 180.0F) {
            y += 360.0F;
        }
        aE += f3;
    }

    @Override
    protected void a(float paramFloat1, float paramFloat2) {
        super.a(paramFloat1, paramFloat2);
    }

    public void a(int paramInt) {
        if (aR <= 0) {
            return;
        }
        aR += paramInt;
        if (aR > 20) {
            aR = 20;
        }
        ac = (aw / 2);
    }

    @Override
    public boolean a(dy paramdy, int paramInt) {
        if (l.z) {
            return false;
        }
        bo = 0;
        if (aR <= 0) {
            return false;
        }

        be = 1.5F;

        // hMod: the different entities are defined in hn
        if (paramdy != null && (paramdy instanceof jz)) {
            LivingEntity attacker = new LivingEntity((jz) paramdy);
            LivingEntity defender = new LivingEntity(this);
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new Object[]{PluginLoader.DamageType.ENTITY, attacker, defender, paramInt})) {
                return false;
            }
        }

        int i = 1;
        if (ac > aw / 2.0F) {
            if (paramInt <= bn) {
                return false;
            }
            c(paramInt - bn);
            bn = paramInt;
            i = 0;
        } else {
            bn = paramInt;
            aS = aR;
            ac = aw;
            c(paramInt);
            aT = (this.aU = 10);
        }

        aV = 0.0F;

        if (i != 0) {
            l.a(this, 2);
            u();
            if (paramdy != null) {
                double d1 = paramdy.p - p;
                double d2 = paramdy.r - r;
                while (d1 * d1 + d2 * d2 < 0.0001D) {
                    d1 = (Math.random() - Math.random()) * 0.01D;
                    d2 = (Math.random() - Math.random()) * 0.01D;
                }
                aV = ((float) (Math.atan2(d2, d1) * 180.0D / 3.141592741012573D) - v);
                a(paramdy, paramInt, d1, d2);
            } else {
                aV = ((int) (Math.random() * 2.0D) * 180);
            }
        }

        if (aR <= 0) {
            if (i != 0) {
                l.a(this, f(), h(), (W.nextFloat() - W.nextFloat()) * 0.2F + 1.0F);
            }
            f(paramdy);
        } else if (i != 0) {
            l.a(this, e(), h(), (W.nextFloat() - W.nextFloat()) * 0.2F + 1.0F);
        }

        return true;
    }

    protected void c(int paramInt) {
        aR -= paramInt;
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

    public void a(dy paramdy, int paramInt, double paramDouble1, double paramDouble2) {
        float f1 = hg.a(paramDouble1 * paramDouble1 + paramDouble2 * paramDouble2);
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

    public void f(dy paramdy) {
        if ((aM > 0) && (paramdy != null)) {
            paramdy.b(this, aM);
        }
        ba = true;

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

            int j = l.a(hg.b(p), hg.b(q - 0.2000000029802322D - H), hg.b(r));
            if (j > 0) {
                cd localcd = gb.m[j].bq;
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
                int i = l.a(hg.b(p), hg.b(z.b) - 1, hg.b(r));
                if (i > 0) {
                    f1 = gb.m[i].bt * 0.91F;
                }
            }

            float f2 = 0.1627714F / (f1 * f1 * f1);
            a(paramFloat1, paramFloat2, A ? 0.1F * f2 : 0.02F);

            f1 = 0.91F;
            if (A) {
                f1 = 0.5460001F;
                int j = l.a(hg.b(p), hg.b(z.b) - 1, hg.b(r));
                if (j > 0) {
                    f1 = gb.m[j].bt * 0.91F;
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
        bd = be;
        double d2 = p - m;
        double d3 = r - o;
        float f3 = hg.a(d2 * d2 + d3 * d3) * 4.0F;
        if (f3 > 1.0F) {
            f3 = 1.0F;
        }
        be += (f3 - be) * 0.4F;
        bf += be;
    }

    public boolean d_() {
        int i = hg.b(p);
        int j = hg.b(z.b);
        int k = hg.b(r);
        return (l.a(i, j, k) == gb.aF.bh) || (l.a(i, j + 1, k) == gb.aF.bh);
    }

    @Override
    public void a(v paramv) {
        paramv.a("Health", (short) aR);
        paramv.a("HurtTime", (short) aT);
        paramv.a("DeathTime", (short) aW);
        paramv.a("AttackTime", (short) aX);
    }

    @Override
    public void b(v paramv) {
        aR = paramv.c("Health");
        if (!paramv.a("Health")) {
            aR = 10;
        }
        aT = paramv.c("HurtTime");
        aW = paramv.c("DeathTime");
        aX = paramv.c("AttackTime");
        // hMod: Lets unbreak 'dead' characters so admins don't have to delete .dat files anymore >.>
        if (aQ < 0 || aV != 0) {
            aQ = 20; // Health
            aV = 0; // DeathTime
        }
    }

    @Override
    public boolean x() {
        return (!G) && (aR > 0);
    }

    public void E() {
        if (bg > 0) {
            double d1 = p + (bh - p) / bg;
            double d2 = q + (bi - q) / bg;
            double d3 = r + (bj - r) / bg;

            double d4 = bk - v;
            while (d4 < -180.0D) {
                d4 += 360.0D;
            }
            while (d4 >= 180.0D) {
                d4 -= 360.0D;
            }
            v = (float) (v + d4 / bg);
            w = (float) (w + (bl - w) / bg);

            bg -= 1;
            a(d1, d2, d3);
            b(v, w);
        }

        if (aR <= 0) {
            bs = false;
            bp = 0.0F;
            bq = 0.0F;
            br = 0.0F;
        } else if (!aO) {
            c();
        }

        boolean bool1 = r();
        boolean bool2 = t();

        if (bs) {
            if (bool1) {
                t += 0.03999999910593033D;
            } else if (bool2) {
                t += 0.03999999910593033D;
            } else if (A) {
                K();
            }

        }

        bp *= 0.98F;
        bq *= 0.98F;
        br *= 0.9F;
        c(bp, bq);

        List localList = l.b(this, z.b(0.2000000029802322D, 0.0D, 0.2000000029802322D));
        if ((localList != null) && (localList.size() > 0)) {
            for (int i = 0; i < localList.size(); i++) {
                dy localdy = (dy) localList.get(i);
                if (!localdy.v()) {
                    continue;
                }
                localdy.c(this);
            }
        }
    }

    protected void K() {
        t = 0.4199999868869782D;
    }

    protected void c() {
        bo += 1;

        fy localfy = l.a(this, -1.0D);

        if (localfy != null) {
            double d1 = localfy.p - p;
            double d2 = localfy.q - q;
            double d3 = localfy.r - r;
            double d4 = d1 * d1 + d2 * d2 + d3 * d3;

            if (d4 > 16384.0D) {
                l();
            }

            if ((bo > 600) && (W.nextInt(800) == 0)) {
                if (d4 < 1024.0D) {
                    bo = 0;
                } else {
                    l();
                }
            }
        }

        bp = 0.0F;
        bq = 0.0F;

        float f = 8.0F;
        if (W.nextFloat() < 0.02F) {
            localfy = l.a(this, f);
            if (localfy != null) {
                b = localfy;
                c = (10 + W.nextInt(20));
            } else {
                br = ((W.nextFloat() - 0.5F) * 20.0F);
            }
        }

        if (b != null) {
            b(b, 10.0F);
            if ((c-- <= 0) || (b.G) || (b.b(this) > f * f)) {
                b = null;
            }
        } else {
            if (W.nextFloat() < 0.05F) {
                br = ((W.nextFloat() - 0.5F) * 20.0F);
            }
            v += br;
            w = bt;
        }

        boolean bool1 = r();
        boolean bool2 = t();
        if ((bool1) || (bool2)) {
            bs = (W.nextFloat() < 0.8F);
        }
    }

    public void b(dy paramdy, float paramFloat) {
        double d1 = paramdy.p - p;

        double d3 = paramdy.r - r;
        double d2;
        if ((paramdy instanceof jz)) {
            jz localjz = (jz) paramdy;
            d2 = localjz.q + localjz.s() - (q + s());
        } else {
            d2 = (paramdy.z.b + paramdy.z.e) / 2.0D - (q + s());
        }

        double d4 = hg.a(d1 * d1 + d3 * d3);

        float f1 = (float) (Math.atan2(d3, d1) * 180.0D / 3.141592741012573D) - 90.0F;
        float f2 = (float) (Math.atan2(d2, d4) * 180.0D / 3.141592741012573D);
        w = (-b(w, f2, paramFloat));
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
        // hMod fix the static reference here with a null element.
        bd bd = null;
        if (paramFloat == 1.0F) {
            float f1 = hg.b(-v * 0.01745329F - 3.141593F);
            float f2 = hg.a(-v * 0.01745329F - 3.141593F);
            float f3 = -hg.b(-w * 0.01745329F);
            float f4 = hg.a(-w * 0.01745329F);

            return bd.b(f2 * f3, f4, f1 * f3);
        }
        float f1 = y + (w - y) * paramFloat;
        float f2 = x + (v - x) * paramFloat;

        float f3 = hg.b(-f2 * 0.01745329F - 3.141593F);
        float f4 = hg.a(-f2 * 0.01745329F - 3.141593F);
        float f5 = -hg.b(-f1 * 0.01745329F);
        float f6 = hg.a(-f1 * 0.01745329F);

        return bd.b(f4 * f5, f6, f3 * f5);
    }

    public int i() {
        return 4;
    }
}
