import java.util.List;

public abstract class OEntityLiving extends OEntity {

    public int        aF     = 20;
    public float      aG;
    public float      aH;
    public float      aI     = 0.0F;
    public float      aJ     = 0.0F;
    protected float   aK;
    protected float   aL;
    protected float   aM;
    protected float   aN;
    protected boolean aO     = true;
    protected String  aP     = "/mob/char.png";
    protected boolean aQ     = true;
    protected float   aR     = 0.0F;
    protected String  aS     = null;
    protected float   aT     = 1.0F;
    protected int     aU     = 0;
    protected float   aV     = 0.0F;
    public boolean    aW     = false;
    public float      aX;
    public float      aY;
    public int        aZ;
    public int        ba;
    private int       a;
    public int        bb;
    public int        bc;
    public float      bd     = 0.0F;
    public int        be     = 0;
    public int        bf     = 0;
    public float      bg;
    public float      bh;
    protected boolean bi     = false;
    public int        bj     = -1;
    public float      bk     = (float) (Math.random() * 0.8999999761581421D + 0.1000000014901161D);
    public float      bl;
    public float      bm;
    public float      bn;
    protected int     bo;
    protected double  bp;
    protected double  bq;
    protected double  br;
    protected double  bs;
    protected double  bt;
    float             bu     = 0.0F;
    protected int     bv     = 0;
    protected int     bw     = 0;
    protected float   bx;
    protected float   by;
    protected float   bz;
    protected boolean bA     = false;
    protected float   bB     = 0.0F;
    protected float   bC     = 0.7F;
    private OEntity   b;
    private int       c      = 0;

    // hMod Start
    LivingEntity      entity = new LivingEntity(this);

    // hMod end

    public OEntityLiving(OWorld paramOWorld) {
        super(paramOWorld);
        aZ = 10;
        i = true;

        aH = ((float) (Math.random() + 1.0D) * 0.01F);
        a(p, q, r);
        aG = ((float) Math.random() * 12398.0F);
        v = (float) (Math.random() * 3.141592741012573D * 2.0D);

        S = 0.5F;
    }

    @Override
    protected void a() {
    }

    public boolean i(OEntity paramOEntity) {
        return l.a(OVec3D.b(p, q + w(), r), OVec3D.b(paramOEntity.p, paramOEntity.q + paramOEntity.w(), paramOEntity.r)) == null;
    }

    @Override
    public boolean c_() {
        return !G;
    }

    @Override
    public boolean z() {
        return !G;
    }

    @Override
    public float w() {
        return J * 0.85F;
    }

    public int c() {
        return 80;
    }

    @Override
    public void r() {
        aX = aY;
        super.r();

        if (W.nextInt(1000) < a++) {
            a = (-c());
            String str = e();
            if (str != null)
                l.a(this, str, i(), (W.nextFloat() - W.nextFloat()) * 0.2F + 1.0F);
        }

        if ((B()) && (C()))
            // hMod Damage hook: Suffocation
            if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.SUFFOCATION, null, entity, 1))
                a((OEntity) null, 1);

        if ((ae) || (l.z))
            Z = 0;
        int i;
        if ((B()) && (a(OMaterial.f)) && (!d_())) {
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
                if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.WATER, null, entity, 2))
                    a((OEntity) null, 2);
            }

            Z = 0;
        } else
            ad = aa;

        bg = bh;

        if (bf > 0)
            bf -= 1;
        if (bb > 0)
            bb -= 1;
        if (ac > 0)
            ac -= 1;
        if (aZ <= 0) {
            be += 1;
            if (be > 20) {
                T();
                q();
                for (i = 0; i < 20; i++) {
                    double d1 = W.nextGaussian() * 0.02D;
                    double d2 = W.nextGaussian() * 0.02D;
                    double d3 = W.nextGaussian() * 0.02D;
                    l.a("explode", p + W.nextFloat() * I * 2.0F - I, q + W.nextFloat() * J, r + W.nextFloat() * I * 2.0F - I, d1, d2, d3);
                }
            }
        }

        aN = aM;

        aJ = aI;
        x = v;
        y = w;
    }

    public void R() {
        for (int i = 0; i < 20; i++) {
            double d1 = W.nextGaussian() * 0.02D;
            double d2 = W.nextGaussian() * 0.02D;
            double d3 = W.nextGaussian() * 0.02D;
            double d4 = 10.0D;
            l.a("explode", p + W.nextFloat() * I * 2.0F - I - d1 * d4, q + W.nextFloat() * J - d2 * d4, r + W.nextFloat() * I * 2.0F - I - d3 * d4, d1, d2, d3);
        }
    }

    @Override
    public void D() {
        super.D();
        aK = aL;
        aL = 0.0F;
    }

    @Override
    public void b_() {
        super.b_();

        o();

        double d1 = p - m;
        double d2 = r - o;

        float f1 = OMathHelper.a(d1 * d1 + d2 * d2);

        float f2 = aI;

        float f3 = 0.0F;
        aK = aL;
        float f4 = 0.0F;
        if (f1 > 0.05F) {
            f4 = 1.0F;
            f3 = f1 * 3.0F;
            f2 = (float) Math.atan2(d2, d1) * 180.0F / 3.141593F - 90.0F;
        }
        if (aY > 0.0F)
            f2 = v;
        if (!A)
            f4 = 0.0F;
        aL += (f4 - aL) * 0.3F;

        float f5 = f2 - aI;
        while (f5 < -180.0F)
            f5 += 360.0F;
        while (f5 >= 180.0F)
            f5 -= 360.0F;
        aI += f5 * 0.3F;

        float f6 = v - aI;
        while (f6 < -180.0F)
            f6 += 360.0F;
        while (f6 >= 180.0F)
            f6 -= 360.0F;
        int i = (f6 < -90.0F) || (f6 >= 90.0F) ? 1 : 0;
        if (f6 < -75.0F)
            f6 = -75.0F;
        if (f6 >= 75.0F)
            f6 = 75.0F;
        aI = (v - f6);
        if (f6 * f6 > 2500.0F)
            aI += f6 * 0.2F;

        if (i != 0)
            f3 *= -1.0F;
        while (v - x < -180.0F)
            x -= 360.0F;
        while (v - x >= 180.0F)
            x += 360.0F;
        while (aI - aJ < -180.0F)
            aJ -= 360.0F;
        while (aI - aJ >= 180.0F)
            aJ += 360.0F;
        while (w - y < -180.0F)
            y -= 360.0F;
        while (w - y >= 180.0F)
            y += 360.0F;
        aM += f3;
    }

    @Override
    protected void a(float paramFloat1, float paramFloat2) {
        super.a(paramFloat1, paramFloat2);
    }

    public void d(int paramInt) {
        if (aZ <= 0)
            return;
        aZ += paramInt;
        if (aZ > 20)
            aZ = 20;
        ac = (aF / 2);
    }

    @Override
    public boolean a(OEntity paramOEntity, int paramInt) {
        if (l.z)
            return false;
        bw = 0;
        if (aZ <= 0)
            return false;

        bm = 1.5F;

        // hMod damage entities.
        LivingEntity attacker = (paramOEntity != null && paramOEntity instanceof OEntityLiving) ? new LivingEntity((OEntityLiving) paramOEntity) : null;

        // hMod attack by entity, but it might not do damage!
        if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.ATTACK, attacker, entity, paramInt))
            return false;

        int i = 1;
        if (ac > aF / 2.0F) {
            if (paramInt <= bv)
                return false;
            // hMod: partial damage
            if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, entity, paramInt - bv))
                return false;
            e(paramInt - bv);
            bv = paramInt;
            i = 0;
        } else {
            // hMod: partial damage
            if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, entity, paramInt))
                return false;

            bv = paramInt;
            ba = aZ;
            ac = aF;
            e(paramInt);
            bb = (bc = 10);
        }

        bd = 0.0F;

        if (i != 0) {
            // hMod: Forced cast to send 'damage animation'.
            l.a(this, (byte) 2);
            y();
            if (paramOEntity != null) {
                double d1 = paramOEntity.p - p;
                double d2 = paramOEntity.r - r;
                while (d1 * d1 + d2 * d2 < 0.0001D) {
                    d1 = (Math.random() - Math.random()) * 0.01D;
                    d2 = (Math.random() - Math.random()) * 0.01D;
                }
                bd = ((float) (Math.atan2(d2, d1) * 180.0D / 3.141592741012573D) - v);
                a(paramOEntity, paramInt, d1, d2);
            } else
                bd = ((int) (Math.random() * 2.0D) * 180);
        }

        if (aZ <= 0) {
            if (i != 0)
                l.a(this, g(), i(), (W.nextFloat() - W.nextFloat()) * 0.2F + 1.0F);
            f(paramOEntity);
        } else if (i != 0)
            l.a(this, f(), i(), (W.nextFloat() - W.nextFloat()) * 0.2F + 1.0F);

        return true;
    }

    protected void e(int paramInt) {
        aZ -= paramInt;
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

    public void a(OEntity paramOEntity, int paramInt, double paramDouble1, double paramDouble2) {
        float f1 = OMathHelper.a(paramDouble1 * paramDouble1 + paramDouble2 * paramDouble2);
        float f2 = 0.4F;

        s /= 2.0D;
        t /= 2.0D;
        u /= 2.0D;

        s -= paramDouble1 / f1 * f2;
        t += 0.4000000059604645D;
        u -= paramDouble2 / f1 * f2;

        if (t > 0.4000000059604645D)
            t = 0.4000000059604645D;
    }

    public void f(OEntity paramOEntity) {
        if ((aU > 0) && (paramOEntity != null))
            paramOEntity.b(this, aU);
        bi = true;

        if (!l.z)
            g_();

        // hMod: Forced cast to play Death Animations.
        l.a(this, (byte) 3);
    }

    protected void g_() {
        int i = h();
        if (i > 0) {
            int j = W.nextInt(3);
            for (int k = 0; k < j; k++)
                a(i, 1);
        }
    }

    protected int h() {
        return 0;
    }

    @Override
    protected void a(float paramFloat) {
        int i = (int) Math.ceil(paramFloat - 3.0F);
        if (i > 0) {
            // hMod Damage hook: Falling
            if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FALL, null, entity, i))
                a((OEntity) null, i);

            int j = l.a(OMathHelper.b(p), OMathHelper.b(q - 0.2000000029802322D - H), OMathHelper.b(r));
            if (j > 0) {
                OStepSound localOStepSound = OBlock.m[j].br;
                l.a(this, localOStepSound.c(), localOStepSound.a() * 0.5F, localOStepSound.b() * 0.75F);
            }
        }
    }

    public void c(float paramFloat1, float paramFloat2) {
        double d1;
        if (v()) {
            d1 = q;
            a(paramFloat1, paramFloat2, 0.02F);
            c(s, t, u);

            s *= 0.800000011920929D;
            t *= 0.800000011920929D;
            u *= 0.800000011920929D;
            t -= 0.02D;

            if ((B) && (b(s, t + 0.6000000238418579D - q + d1, u)))
                t = 0.300000011920929D;
        } else if (x()) {
            d1 = q;
            a(paramFloat1, paramFloat2, 0.02F);
            c(s, t, u);
            s *= 0.5D;
            t *= 0.5D;
            u *= 0.5D;
            t -= 0.02D;

            if ((B) && (b(s, t + 0.6000000238418579D - q + d1, u)))
                t = 0.300000011920929D;
        } else {
            float f1 = 0.91F;
            if (A) {
                f1 = 0.5460001F;
                int i = l.a(OMathHelper.b(p), OMathHelper.b(z.b) - 1, OMathHelper.b(r));
                if (i > 0)
                    f1 = OBlock.m[i].bu * 0.91F;
            }

            float f2 = 0.1627714F / (f1 * f1 * f1);
            a(paramFloat1, paramFloat2, A ? 0.1F * f2 : 0.02F);

            f1 = 0.91F;
            if (A) {
                f1 = 0.5460001F;
                int j = l.a(OMathHelper.b(p), OMathHelper.b(z.b) - 1, OMathHelper.b(r));
                if (j > 0)
                    f1 = OBlock.m[j].bu * 0.91F;
            }

            if (m()) {
                N = 0.0F;
                if (t < -0.15D)
                    t = -0.15D;

            }

            c(s, t, u);

            if ((B) && (m()))
                t = 0.2D;

            t -= 0.08D;
            t *= 0.9800000190734863D;
            s *= f1;
            u *= f1;
        }
        bl = bm;
        d1 = p - m;
        double d2 = r - o;
        float f3 = OMathHelper.a(d1 * d1 + d2 * d2) * 4.0F;
        if (f3 > 1.0F)
            f3 = 1.0F;
        bm += (f3 - bm) * 0.4F;
        bn += bm;
    }

    public boolean m() {
        int i = OMathHelper.b(p);
        int j = OMathHelper.b(z.b);
        int k = OMathHelper.b(r);
        return (l.a(i, j, k) == OBlock.aF.bi) || (l.a(i, j + 1, k) == OBlock.aF.bi);
    }

    @Override
    public void a(ONBTTagCompound paramONBTTagCompound) {
        paramONBTTagCompound.a("Health", (short) aZ);
        paramONBTTagCompound.a("HurtTime", (short) bb);
        paramONBTTagCompound.a("DeathTime", (short) be);
        paramONBTTagCompound.a("AttackTime", (short) bf);
    }

    @Override
    public void b(ONBTTagCompound paramONBTTagCompound) {
        aZ = paramONBTTagCompound.c("Health");
        if (!paramONBTTagCompound.a("Health"))
            aZ = 10;
        bb = paramONBTTagCompound.c("HurtTime");
        be = paramONBTTagCompound.c("DeathTime");
        bf = paramONBTTagCompound.c("AttackTime");
    }

    @Override
    public boolean B() {
        return (!G) && (aZ > 0);
    }

    public boolean d_() {
        return false;
    }

    public void o() {
        if (bo > 0) {
            double d1 = p + (bp - p) / bo;
            double d2 = q + (bq - q) / bo;
            double d3 = r + (br - r) / bo;

            double d4 = bs - v;
            while (d4 < -180.0D)
                d4 += 360.0D;
            while (d4 >= 180.0D)
                d4 -= 360.0D;
            v = (float) (v + d4 / bo);
            w = (float) (w + (bt - w) / bo);

            bo -= 1;
            a(d1, d2, d3);
            b(v, w);
        }

        if (aZ <= 0) {
            bA = false;
            bx = 0.0F;
            by = 0.0F;
            bz = 0.0F;
        } else if (!aW)
            d();

        boolean bool1 = v();
        boolean bool2 = x();

        if (bA)
            if (bool1)
                t += 0.03999999910593033D;
            else if (bool2)
                t += 0.03999999910593033D;
            else if (A)
                S();

        bx *= 0.98F;
        by *= 0.98F;
        bz *= 0.9F;
        c(bx, by);

        List<?> localList = l.b(this, z.b(0.2000000029802322D, 0.0D, 0.2000000029802322D));
        if ((localList != null) && (localList.size() > 0))
            for (int i = 0; i < localList.size(); i++) {
                OEntity localOEntity = (OEntity) localList.get(i);
                if (!localOEntity.z())
                    continue;
                localOEntity.c(this);
            }
    }

    protected void S() {
        t = 0.4199999868869782D;
    }

    protected void d() {
        bw += 1;

        OEntityPlayer localOEntityPlayer = l.a(this, -1.0D);

        if (localOEntityPlayer != null) {
            double d1 = localOEntityPlayer.p - p;
            double d2 = localOEntityPlayer.q - q;
            double d3 = localOEntityPlayer.r - r;
            double d4 = d1 * d1 + d2 * d2 + d3 * d3;

            if (d4 > 16384.0D)
                q();

            if ((bw > 600) && (W.nextInt(800) == 0))
                if (d4 < 1024.0D)
                    bw = 0;
                else
                    q();
        }

        bx = 0.0F;
        by = 0.0F;

        float f = 8.0F;
        if (W.nextFloat() < 0.02F) {
            localOEntityPlayer = l.a(this, f);
            if (localOEntityPlayer != null) {
                b = localOEntityPlayer;
                c = (10 + W.nextInt(20));
            } else
                bz = ((W.nextFloat() - 0.5F) * 20.0F);
        }

        if (b != null) {
            b(b, 10.0F);
            if ((c-- <= 0) || (b.G) || (b.b(this) > f * f))
                b = null;
        } else {
            if (W.nextFloat() < 0.05F)
                bz = ((W.nextFloat() - 0.5F) * 20.0F);
            v += bz;
            w = bB;
        }

        boolean bool1 = v();
        boolean bool2 = x();
        if ((bool1) || (bool2))
            bA = (W.nextFloat() < 0.8F);
    }

    public void b(OEntity paramOEntity, float paramFloat) {
        double d1 = paramOEntity.p - p;

        double d2 = paramOEntity.r - r;
        double d3;
        if ((paramOEntity instanceof OEntityLiving)) {
            OEntityLiving localOEntityLiving = (OEntityLiving) paramOEntity;
            d3 = localOEntityLiving.q + localOEntityLiving.w() - (q + w());
        } else
            d3 = (paramOEntity.z.b + paramOEntity.z.e) / 2.0D - (q + w());

        double d4 = OMathHelper.a(d1 * d1 + d2 * d2);

        float f1 = (float) (Math.atan2(d2, d1) * 180.0D / 3.141592741012573D) - 90.0F;
        float f2 = (float) (Math.atan2(d3, d4) * 180.0D / 3.141592741012573D);
        w = (-b(w, f2, paramFloat));
        v = b(v, f1, paramFloat);
    }

    private float b(float paramFloat1, float paramFloat2, float paramFloat3) {
        float f = paramFloat2 - paramFloat1;
        while (f < -180.0F)
            f += 360.0F;
        while (f >= 180.0F)
            f -= 360.0F;
        if (f > paramFloat3)
            f = paramFloat3;
        if (f < -paramFloat3)
            f = -paramFloat3;
        return paramFloat1 + f;
    }

    public void T() {
    }

    public boolean b() {
        return (l.a(z)) && (l.a(this, z).size() == 0) && (!l.b(z));
    }

    @Override
    protected void t() {
        a((OEntity) null, 4);
    }

    @Override
    public OVec3D G() {
        return c(1.0F);
    }

    public OVec3D c(float paramFloat) {
        if (paramFloat == 1.0F) {
            float f1 = OMathHelper.b(-v * 0.01745329F - 3.141593F);
            float f2 = OMathHelper.a(-v * 0.01745329F - 3.141593F);
            float f3 = -OMathHelper.b(-w * 0.01745329F);
            float f4 = OMathHelper.a(-w * 0.01745329F);

            return OVec3D.b(f2 * f3, f4, f1 * f3);
        }
        float f1 = y + (w - y) * paramFloat;
        float f2 = x + (v - x) * paramFloat;

        float f3 = OMathHelper.b(-f2 * 0.01745329F - 3.141593F);
        float f4 = OMathHelper.a(-f2 * 0.01745329F - 3.141593F);
        float f5 = -OMathHelper.b(-f1 * 0.01745329F);
        float f6 = OMathHelper.a(-f1 * 0.01745329F);

        return OVec3D.b(f4 * f5, f6, f3 * f5);
    }

    public int j() {
        return 4;
    }
}
