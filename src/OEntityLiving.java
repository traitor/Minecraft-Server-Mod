import java.util.List;

public abstract class OEntityLiving extends OEntity {
    public int        C  = 20;
    public float      D;
    public float      E;
    public float      F  = 0.0F;
    public float      G  = 0.0F;
    protected float   H;
    protected float   I;
    protected float   J;
    protected float   K;
    protected boolean L  = true;
    protected String  M  = "/mob/char.png";
    protected boolean N  = true;
    protected float   O  = 0.0F;
    protected String  P  = null;
    protected float   Q  = 1.0F;
    protected int     R  = 0;
    protected float   S  = 0.0F;
    public boolean    T  = false;
    public float      U;
    public float      V;
    public int        W;
    public int        X;
    private int       a;
    public int        Y;
    public int        Z;
    public float      aa = 0.0F;
    public int        ab = 0;
    public int        ac = 0;
    public float      ad;
    public float      ae;
    protected boolean af = false;

    public int        ag = -1;
    public float      ah = (float) (Math.random() * 0.8999999761581421D + 0.1000000014901161D);
    public float      ai;
    public float      aj;
    public float      ak;
    protected int     al;
    protected double  am;
    protected double  an;
    protected double  ao;
    protected double  ap;
    protected double  aq;
    float             ar = 0.0F;

    protected int     as = 0;

    protected int     at = 0;
    protected float   au;
    protected float   av;
    protected float   aw;
    protected boolean ax = false;
    protected float   ay = 0.0F;
    protected float   az = 0.7F;
    private OEntity   b;
    protected int     aA = 0;

    public OEntityLiving(OWorld paramOWorld) {
        super(paramOWorld);
        W = 10;
        aD = true;

        E = ((float) (Math.random() + 1.0D) * 0.01F);
        a(aK, aL, aM);
        D = ((float) Math.random() * 12398.0F);
        aQ = (float) (Math.random() * 3.141592741012573D * 2.0D);

        bm = 0.5F;
    }

    @Override
    protected void a() {
    }

    public boolean e(OEntity paramOEntity) {
        return aG.a(OVec3D.b(aK, aL + q(), aM), OVec3D.b(paramOEntity.aK, paramOEntity.aL + paramOEntity.q(), paramOEntity.aM)) == null;
    }

    @Override
    public boolean d_() {
        return !bb;
    }

    @Override
    public boolean e_() {
        return !bb;
    }

    @Override
    public float q() {
        return be * 0.85F;
    }

    public int c() {
        return 80;
    }

    public void K() {
        String str = e();
        if (str != null)
            aG.a(this, str, i(), (bq.nextFloat() - bq.nextFloat()) * 0.2F + 1.0F);
    }

    @Override
    public void L() {
        U = V;
        super.L();

        if (bq.nextInt(1000) < a++) {
            a = (-c());
            K();
        }

        if ((N()) && (E()))
         // hMod Damage hook: Suffocation
            if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.SUFFOCATION, null, entity, 1))
                a((OEntity) null, 1);


        if ((by) || (aG.t))
            bt = 0;
        int i;
        if ((N()) && (a(OMaterial.f)) && (!b_())) {
            bx -= 1;
            if (bx == -20) {
                bx = 0;
                for (i = 0; i < 8; i++) {
                    float f1 = bq.nextFloat() - bq.nextFloat();
                    float f2 = bq.nextFloat() - bq.nextFloat();
                    float f3 = bq.nextFloat() - bq.nextFloat();
                    aG.a("bubble", aK + f1, aL + f2, aM + f3, aN, aO, aP);
                }
             // hMod Damage hook: Drowning
                if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.WATER, null, entity, 2))
                    a((OEntity) null, 2);

            }

            bt = 0;
        } else
            bx = bu;

        ad = ae;

        if (ac > 0)
            ac -= 1;
        if (Y > 0)
            Y -= 1;
        if (bw > 0)
            bw -= 1;
        if (W <= 0) {
            ab += 1;
            if (ab > 20) {
                Q();
                D();
                for (i = 0; i < 20; i++) {
                    double d1 = bq.nextGaussian() * 0.02D;
                    double d2 = bq.nextGaussian() * 0.02D;
                    double d3 = bq.nextGaussian() * 0.02D;
                    aG.a("explode", aK + bq.nextFloat() * bd * 2.0F - bd, aL + bq.nextFloat() * be, aM + bq.nextFloat() * bd * 2.0F - bd, d1, d2, d3);
                }
            }
        }

        K = J;

        G = F;
        aS = aQ;
        aT = aR;
    }

    public void M() {
        for (int i = 0; i < 20; i++) {
            double d1 = bq.nextGaussian() * 0.02D;
            double d2 = bq.nextGaussian() * 0.02D;
            double d3 = bq.nextGaussian() * 0.02D;
            double d4 = 10.0D;
            aG.a("explode", aK + bq.nextFloat() * bd * 2.0F - bd - d1 * d4, aL + bq.nextFloat() * be - d2 * d4, aM + bq.nextFloat() * bd * 2.0F - bd - d3 * d4, d1, d2, d3);
        }
    }

    @Override
    public void o_() {
        super.o_();
        H = I;
        I = 0.0F;
    }

    @Override
    public void f_() {
        super.f_();

        r();

        double d1 = aK - aH;
        double d2 = aM - aJ;

        float f1 = OMathHelper.a(d1 * d1 + d2 * d2);

        float f2 = F;

        float f3 = 0.0F;
        H = I;
        float f4 = 0.0F;
        if (f1 > 0.05F) {
            f4 = 1.0F;
            f3 = f1 * 3.0F;
            f2 = (float) Math.atan2(d2, d1) * 180.0F / 3.141593F - 90.0F;
        }
        if (V > 0.0F)
            f2 = aQ;
        if (!aV)
            f4 = 0.0F;
        I += (f4 - I) * 0.3F;

        float f5 = f2 - F;
        while (f5 < -180.0F)
            f5 += 360.0F;
        while (f5 >= 180.0F)
            f5 -= 360.0F;
        F += f5 * 0.3F;

        float f6 = aQ - F;
        while (f6 < -180.0F)
            f6 += 360.0F;
        while (f6 >= 180.0F)
            f6 -= 360.0F;
        int i = (f6 < -90.0F) || (f6 >= 90.0F) ? 1 : 0;
        if (f6 < -75.0F)
            f6 = -75.0F;
        if (f6 >= 75.0F)
            f6 = 75.0F;
        F = (aQ - f6);
        if (f6 * f6 > 2500.0F)
            F += f6 * 0.2F;

        if (i != 0)
            f3 *= -1.0F;
        while (aQ - aS < -180.0F)
            aS -= 360.0F;
        while (aQ - aS >= 180.0F)
            aS += 360.0F;
        while (F - G < -180.0F)
            G -= 360.0F;
        while (F - G >= 180.0F)
            G += 360.0F;
        while (aR - aT < -180.0F)
            aT -= 360.0F;
        while (aR - aT >= 180.0F)
            aT += 360.0F;
        J += f3;
    }

    @Override
    protected void b(float paramFloat1, float paramFloat2) {
        super.b(paramFloat1, paramFloat2);
    }

    public void b(int paramInt) {
        if (W <= 0)
            return;
        W += paramInt;
        if (W > 20)
            W = 20;
        bw = (C / 2);
    }

    @Override
    public boolean a(OEntity paramOEntity, int paramInt) {
        if (aG.t)
            return false;
        at = 0;
        if (W <= 0)
            return false;

        aj = 1.5F;

     // hMod damage entities.
        LivingEntity attacker = (paramOEntity != null && paramOEntity instanceof OEntityLiving) ? new LivingEntity((OEntityLiving) paramOEntity) : null;

        // hMod attack by entity, but it might not do damage!
        if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.ATTACK, attacker, entity, paramInt))
            return false;

        
        int i = 1;
        if (bw > C / 2.0F) {
            if (paramInt <= as)
                return false;
            // hMod: partial damage
            if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, entity, paramInt - as))
                    return false;

            c(paramInt - as);
            as = paramInt;
            i = 0;
        } else {
         // hMod: full damage
            if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, entity, paramInt))
                return false;

            as = paramInt;
            X = W;
            bw = C;
            c(paramInt);
            Y = (Z = 10);
        }

        aa = 0.0F;

        if (i != 0) {
         // hMod: Forced cast to play Death Animations.
            aG.a(this, (byte)2);
            W();
            if (paramOEntity != null) {
                double d1 = paramOEntity.aK - aK;
                double d2 = paramOEntity.aM - aM;
                while (d1 * d1 + d2 * d2 < 0.0001D) {
                    d1 = (Math.random() - Math.random()) * 0.01D;
                    d2 = (Math.random() - Math.random()) * 0.01D;
                }
                aa = ((float) (Math.atan2(d2, d1) * 180.0D / 3.141592741012573D) - aQ);
                a(paramOEntity, paramInt, d1, d2);
            } else
                aa = ((int) (Math.random() * 2.0D) * 180);
        }

        if (W <= 0) {
            if (i != 0)
                aG.a(this, g(), i(), (bq.nextFloat() - bq.nextFloat()) * 0.2F + 1.0F);
            a(paramOEntity);
        } else if (i != 0)
            aG.a(this, f(), i(), (bq.nextFloat() - bq.nextFloat()) * 0.2F + 1.0F);

        return true;
    }

    protected void c(int paramInt) {
        W -= paramInt;
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

        aN /= 2.0D;
        aO /= 2.0D;
        aP /= 2.0D;

        aN -= paramDouble1 / f1 * f2;
        aO += 0.4000000059604645D;
        aP -= paramDouble2 / f1 * f2;

        if (aO > 0.4000000059604645D)
            aO = 0.4000000059604645D;
    }

    public void a(OEntity paramOEntity) {
        if ((R >= 0) && (paramOEntity != null))
            paramOEntity.c(this, R);
        af = true;

        if (!aG.t)
            p();
     // hMod: Forced cast to play Death Animations.
        aG.a(this, (byte)3);
    }

    protected void p() {
        int i = h();
        if (i > 0) {
            int j = bq.nextInt(3);
            for (int k = 0; k < j; k++)
                b(i, 1);
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


            int j = aG.a(OMathHelper.b(aK), OMathHelper.b(aL - 0.2000000029802322D - bc), OMathHelper.b(aM));
            if (j > 0) {
                OStepSound localOStepSound = OBlock.m[j].bu;
                aG.a(this, localOStepSound.c(), localOStepSound.a() * 0.5F, localOStepSound.b() * 0.75F);
            }
        }
    }

    public void a(float paramFloat1, float paramFloat2) {
        if (g_()) {
            double d1 = aL;
            a(paramFloat1, paramFloat2, 0.02F);
            c(aN, aO, aP);

            aN *= 0.800000011920929D;
            aO *= 0.800000011920929D;
            aP *= 0.800000011920929D;
            aO -= 0.02D;

            if ((aW) && (b(aN, aO + 0.6000000238418579D - aL + d1, aP)))
                aO = 0.300000011920929D;
        } else if (V()) {
            double d1 = aL;
            a(paramFloat1, paramFloat2, 0.02F);
            c(aN, aO, aP);
            aN *= 0.5D;
            aO *= 0.5D;
            aP *= 0.5D;
            aO -= 0.02D;

            if ((aW) && (b(aN, aO + 0.6000000238418579D - aL + d1, aP)))
                aO = 0.300000011920929D;
        } else {
            float f1 = 0.91F;
            if (aV) {
                f1 = 0.5460001F;
                int i = aG.a(OMathHelper.b(aK), OMathHelper.b(aU.b) - 1, OMathHelper.b(aM));
                if (i > 0)
                    f1 = OBlock.m[i].bx * 0.91F;
            }

            float f2 = 0.1627714F / (f1 * f1 * f1);
            a(paramFloat1, paramFloat2, aV ? 0.1F * f2 : 0.02F);

            f1 = 0.91F;
            if (aV) {
                f1 = 0.5460001F;
                int j = aG.a(OMathHelper.b(aK), OMathHelper.b(aU.b) - 1, OMathHelper.b(aM));
                if (j > 0)
                    f1 = OBlock.m[j].bx * 0.91F;
            }

            if (n()) {
                bh = 0.0F;
                if (aO < -0.15D)
                    aO = -0.15D;
                if ((Z()) && (aO < 0.0D))
                    aO = 0.0D;

            }

            c(aN, aO, aP);

            if ((aW) && (n()))
                aO = 0.2D;

            aO -= 0.08D;
            aO *= 0.9800000190734863D;
            aN *= f1;
            aP *= f1;
        }
        ai = aj;
        double d1 = aK - aH;
        double d2 = aM - aJ;
        float f3 = OMathHelper.a(d1 * d1 + d2 * d2) * 4.0F;
        if (f3 > 1.0F)
            f3 = 1.0F;
        aj += (f3 - aj) * 0.4F;
        ak += aj;
    }

    public boolean n() {
        int i = OMathHelper.b(aK);
        int j = OMathHelper.b(aU.b);
        int k = OMathHelper.b(aM);
        return (aG.a(i, j, k) == OBlock.aF.bl) || (aG.a(i, j + 1, k) == OBlock.aF.bl);
    }

    @Override
    public void a(ONBTTagCompound paramONBTTagCompound) {
        paramONBTTagCompound.a("Health", (short) W);
        paramONBTTagCompound.a("HurtTime", (short) Y);
        paramONBTTagCompound.a("DeathTime", (short) ab);
        paramONBTTagCompound.a("AttackTime", (short) ac);
    }

    @Override
    public void b(ONBTTagCompound paramONBTTagCompound) {
        W = paramONBTTagCompound.d("Health");
        if (!paramONBTTagCompound.b("Health"))
            W = 10;
        Y = paramONBTTagCompound.d("HurtTime");
        ab = paramONBTTagCompound.d("DeathTime");
        ac = paramONBTTagCompound.d("AttackTime");
    }

    @Override
    public boolean N() {
        return (!bb) && (W > 0);
    }

    public boolean b_() {
        return false;
    }

    public void r() {
        if (al > 0) {
            double d1 = aK + (am - aK) / al;
            double d2 = aL + (an - aL) / al;
            double d3 = aM + (ao - aM) / al;

            double d4 = ap - aQ;
            while (d4 < -180.0D)
                d4 += 360.0D;
            while (d4 >= 180.0D)
                d4 -= 360.0D;
            aQ = (float) (aQ + d4 / al);
            aR = (float) (aR + (aq - aR) / al);

            al -= 1;
            a(d1, d2, d3);
            c(aQ, aR);
        }

        if (p_()) {
            ax = false;
            au = 0.0F;
            av = 0.0F;
            aw = 0.0F;
        } else if (!T)
            c_();

        boolean bool1 = g_();
        boolean bool2 = V();

        if (ax)
            if (bool1)
                aO += 0.03999999910593033D;
            else if (bool2)
                aO += 0.03999999910593033D;
            else if (aV)
                I();

        au *= 0.98F;
        av *= 0.98F;
        aw *= 0.9F;
        a(au, av);

        List localList = aG.b(this, aU.b(0.2000000029802322D, 0.0D, 0.2000000029802322D));
        if ((localList != null) && (localList.size() > 0))
            for (int i = 0; i < localList.size(); i++) {
                OEntity localOEntity = (OEntity) localList.get(i);
                if (!localOEntity.e_())
                    continue;
                localOEntity.h(this);
            }
    }

    protected boolean p_() {
        return W <= 0;
    }

    protected void I() {
        aO = 0.4199999868869782D;
    }

    protected boolean s() {
        return true;
    }

    protected void c_() {
        at += 1;

        OEntityPlayer localOEntityPlayer = aG.a(this, -1.0D);

        if ((s()) && (localOEntityPlayer != null)) {
            double d1 = localOEntityPlayer.aK - aK;
            double d2 = localOEntityPlayer.aL - aL;
            double d3 = localOEntityPlayer.aM - aM;
            double d4 = d1 * d1 + d2 * d2 + d3 * d3;

            if (d4 > 16384.0D)
                D();

            if ((at > 600) && (bq.nextInt(800) == 0))
                if (d4 < 1024.0D)
                    at = 0;
                else
                    D();
        }

        au = 0.0F;
        av = 0.0F;

        float f = 8.0F;
        if (bq.nextFloat() < 0.02F) {
            localOEntityPlayer = aG.a(this, f);
            if (localOEntityPlayer != null) {
                b = localOEntityPlayer;
                aA = (10 + bq.nextInt(20));
            } else
                aw = ((bq.nextFloat() - 0.5F) * 20.0F);
        }

        if (b != null) {
            a(b, 10.0F, n_());
            if ((aA-- <= 0) || (b.bb) || (b.g(this) > f * f))
                b = null;
        } else {
            if (bq.nextFloat() < 0.05F)
                aw = ((bq.nextFloat() - 0.5F) * 20.0F);
            aQ += aw;
            aR = ay;
        }

        boolean bool1 = g_();
        boolean bool2 = V();
        if ((bool1) || (bool2))
            ax = (bq.nextFloat() < 0.8F);
    }

    protected int n_() {
        return 10;
    }

    public void a(OEntity paramOEntity, float paramFloat1, float paramFloat2) {
        double d1 = paramOEntity.aK - aK;

        double d2 = paramOEntity.aM - aM;
        double d3;
        if ((paramOEntity instanceof OEntityLiving)) {
            OEntityLiving localOEntityLiving = (OEntityLiving) paramOEntity;
            d3 = aL + q() - (localOEntityLiving.aL + localOEntityLiving.q());
        } else
            d3 = (paramOEntity.aU.b + paramOEntity.aU.e) / 2.0D - (aL + q());

        double d4 = OMathHelper.a(d1 * d1 + d2 * d2);

        float f1 = (float) (Math.atan2(d2, d1) * 180.0D / 3.141592741012573D) - 90.0F;
        float f2 = (float) (Math.atan2(d3, d4) * 180.0D / 3.141592741012573D);
        aR = (-b(aR, f2, paramFloat2));
        aQ = b(aQ, f1, paramFloat1);
    }

    public boolean O() {
        return b != null;
    }

    public OEntity P() {
        return b;
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

    public void Q() {
    }

    public boolean b() {
        return (aG.a(aU)) && (aG.a(this, aU).size() == 0) && (!aG.b(aU));
    }

    @Override
    protected void R() {
        a((OEntity)null, 4);
    }

    @Override
    public OVec3D S() {
        return b(1.0F);
    }

    public OVec3D b(float paramFloat) {
        if (paramFloat == 1.0F) {
            float f1 = OMathHelper.b(-aQ * 0.01745329F - 3.141593F);
            float f2 = OMathHelper.a(-aQ * 0.01745329F - 3.141593F);
            float f3 = -OMathHelper.b(-aR * 0.01745329F);
            float f4 = OMathHelper.a(-aR * 0.01745329F);

            return OVec3D.b(f2 * f3, f4, f1 * f3);
        }
        float f1 = aT + (aR - aT) * paramFloat;
        float f2 = aS + (aQ - aS) * paramFloat;

        float f3 = OMathHelper.b(-f2 * 0.01745329F - 3.141593F);
        float f4 = OMathHelper.a(-f2 * 0.01745329F - 3.141593F);
        float f5 = -OMathHelper.b(-f1 * 0.01745329F);
        float f6 = OMathHelper.a(-f1 * 0.01745329F);

        return OVec3D.b(f4 * f5, f6, f3 * f5);
    }

    public int j() {
        return 4;
    }

    public boolean F() {
        return false;
    }
}