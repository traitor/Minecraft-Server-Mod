import java.util.List;
import java.util.Random;

public abstract class OEntity {

    private static int          a       = 0;
    public int                  aA      = a++;
    public double               aB      = 1.0D;
    public boolean              aC      = false;
    public OEntity              aD;
    public OEntity              aE;
    public OWorld               aF;
    public double               aG;
    public double               aH;
    public double               aI;
    public double               aJ;
    public double               aK;
    public double               aL;
    public double               aM;
    public double               aN;
    public double               aO;
    public float                aP;
    public float                aQ;
    public float                aR;
    public float                aS;
    public final OAxisAlignedBB aT      = OAxisAlignedBB.a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
    public boolean              aU      = false;
    public boolean              aV;
    public boolean              aW;
    public boolean              aX      = false;
    public boolean              aY      = false;
    public boolean              aZ      = true;
    public boolean              ba      = false;
    public float                bb      = 0.0F;
    public float                bc      = 0.6F;
    public float                bd      = 1.8F;
    public float                be      = 0.0F;
    public float                bf      = 0.0F;
    protected boolean           bg      = true;
    protected float             bh      = 0.0F;
    private int                 b       = 1;
    public double               bi;
    public double               bj;
    public double               bk;
    public float                bl      = 0.0F;
    public float                bm      = 0.0F;
    public boolean              bn      = false;
    public float                bo      = 0.0F;
    public boolean              bp      = false;
    protected Random            bq      = new Random();
    public int                  br      = 0;
    public int                  bs      = 1;
    public int                  bt      = 0;
    protected int               bu      = 300;
    protected boolean           bv      = false;
    public int                  bw      = 0;
    public int                  bx      = 300;
    private boolean             c       = true;
    protected boolean           by      = false;
    protected ODataWatcher      bz      = new ODataWatcher();
    private double              d;
    private double              e;
    public boolean              bA      = false;
    public int                  bB;
    public int                  bC;
    public int                  bD;

    // hMod Start
    BaseEntity                  entity  = new BaseEntity(this);
    public static PluginLoader  manager = etc.getLoader();

    // hMod end

    public OEntity(OWorld paramOWorld) {
        aF = paramOWorld;

        a(0.0D, 0.0D, 0.0D);

        bz.a(0, (byte) 0);
        a();
    }

    protected abstract void a();

    public ODataWatcher O() {
        return bz;
    }

    public boolean equals(Object paramObject) {
        if ((paramObject instanceof OEntity)) {
            return ((OEntity) paramObject).aA == aA;
        }
        return false;
    }

    public int hashCode() {
        return aA;
    }

    public void C() {
        ba = true;
    }

    protected void a(float paramFloat1, float paramFloat2) {
        bc = paramFloat1;
        bd = paramFloat2;
    }

    protected void c(float paramFloat1, float paramFloat2) {
        aP = paramFloat1;
        aQ = paramFloat2;
    }

    public void a(double paramDouble1, double paramDouble2, double paramDouble3) {
        aJ = paramDouble1;
        aK = paramDouble2;
        aL = paramDouble3;
        float f1 = bc / 2.0F;
        float f2 = bd;

        aT.c(paramDouble1 - f1, paramDouble2 - bb + bl, paramDouble3 - f1, paramDouble1 + f1, paramDouble2 - bb + bl + f2, paramDouble3 + f1);
    }

    public void f_() {
        H();
    }

    public void H() {
        if ((aE != null) && (aE.ba)) {
            aE = null;
        }

        br += 1;
        be = bf;
        aG = aJ;
        aH = aK;
        aI = aL;
        aS = aQ;
        aR = aP;

        if (g_()) {
            if ((!bv) && (!c)) {
                float f1 = OMathHelper.a(aM * aM * 0.2000000029802322D + aN * aN + aO * aO * 0.2000000029802322D) * 0.2F;
                if (f1 > 1.0F) {
                    f1 = 1.0F;
                }
                aF.a(this, "random.splash", f1, 1.0F + (bq.nextFloat() - bq.nextFloat()) * 0.4F);
                float f2 = OMathHelper.b(aT.b);
                float f3;
                float f4;
                for (int i = 0; i < 1.0F + bc * 20.0F; i++) {
                    f3 = (bq.nextFloat() * 2.0F - 1.0F) * bc;
                    f4 = (bq.nextFloat() * 2.0F - 1.0F) * bc;
                    aF.a("bubble", aJ + f3, f2 + 1.0F, aL + f4, aM, aN - bq.nextFloat() * 0.2F, aO);
                }
                for (int i = 0; i < 1.0F + bc * 20.0F; i++) {
                    f3 = (bq.nextFloat() * 2.0F - 1.0F) * bc;
                    f4 = (bq.nextFloat() * 2.0F - 1.0F) * bc;
                    aF.a("splash", aJ + f3, f2 + 1.0F, aL + f4, aM, aN, aO);
                }
            }
            bh = 0.0F;
            bv = true;
            bt = 0;
        } else {
            bv = false;
        }

        if (aF.t) {
            bt = 0;
        } else if (bt > 0) {
            if (by) {
                bt -= 4;
                if (bt < 0) {
                    bt = 0;
                }
            } else {
                if (bt % 20 == 0) {
                    // hMod Damage hook: Periodic burn damage
                    if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FIRE_TICK, null, entity, 1))
                        a((OEntity) null, 1);
                }
                bt -= 1;
            }

        }

        if (Q()) {
            P();
        }

        if (aK < -64.0D) {
            M();
        }

        if (!aF.t) {
            a(0, bt > 0);
            a(2, aE != null);
        }

        c = false;
    }

    protected void P() {
        if (!by) {
            // hMod Damage hook: Lava
            if (this instanceof OEntityLiving)
                if ((Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.LAVA, null, entity, 4))
                    return;
            a((OEntity) null, 4);
            bt = 600;
        }
    }

    protected void M() {
        C();
    }

    public boolean b(double paramDouble1, double paramDouble2, double paramDouble3) {
        OAxisAlignedBB localOAxisAlignedBB = aT.c(paramDouble1, paramDouble2, paramDouble3);
        List localList = aF.a(this, localOAxisAlignedBB);
        if (localList.size() > 0) {
            return false;
        }
        return !aF.b(localOAxisAlignedBB);
    }

    public void c(double paramDouble1, double paramDouble2, double paramDouble3) {
        if (bn) {
            aT.d(paramDouble1, paramDouble2, paramDouble3);
            aJ = ((aT.a + aT.d) / 2.0D);
            aK = (aT.b + bb - bl);
            aL = ((aT.c + aT.f) / 2.0D);
            return;
        }

        double d1 = aJ;
        double d2 = aL;

        double d3 = paramDouble1;
        double d4 = paramDouble2;
        double d5 = paramDouble3;

        OAxisAlignedBB localOAxisAlignedBB1 = aT.b();

        int i = (aU) && (U()) ? 1 : 0;

        if (i != 0) {
            double d6 = 0.05D;
            while ((paramDouble1 != 0.0D) && (aF.a(this, aT.c(paramDouble1, -1.0D, 0.0D)).size() == 0)) {
                if ((paramDouble1 < d6) && (paramDouble1 >= -d6)) {
                    paramDouble1 = 0.0D;
                } else if (paramDouble1 > 0.0D) {
                    paramDouble1 -= d6;
                } else {
                    paramDouble1 += d6;
                }
                d3 = paramDouble1;
            }
            while ((paramDouble3 != 0.0D) && (aF.a(this, aT.c(0.0D, -1.0D, paramDouble3)).size() == 0)) {
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

        List localList = aF.a(this, aT.a(paramDouble1, paramDouble2, paramDouble3));

        for (int j = 0; j < localList.size(); j++) {
            paramDouble2 = ((OAxisAlignedBB) localList.get(j)).b(aT, paramDouble2);
        }
        aT.d(0.0D, paramDouble2, 0.0D);

        if ((!aZ) && (d4 != paramDouble2)) {
            paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;
        }

        int j = (aU) || ((d4 != paramDouble2) && (d4 < 0.0D)) ? 1 : 0;

        for (int k = 0; k < localList.size(); k++) {
            paramDouble1 = ((OAxisAlignedBB) localList.get(k)).a(aT, paramDouble1);
        }
        aT.d(paramDouble1, 0.0D, 0.0D);

        if ((!aZ) && (d3 != paramDouble1)) {
            paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;
        }

        for (int k = 0; k < localList.size(); k++) {
            paramDouble3 = ((OAxisAlignedBB) localList.get(k)).c(aT, paramDouble3);
        }
        aT.d(0.0D, 0.0D, paramDouble3);

        if ((!aZ) && (d5 != paramDouble3)) {
            paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;
        }

        if ((bm > 0.0F) && (j != 0) && (bl < 0.05F) && ((d3 != paramDouble1) || (d5 != paramDouble3))) {
            double d7 = paramDouble1;
            double d8 = paramDouble2;
            double d9 = paramDouble3;

            paramDouble1 = d3;
            paramDouble2 = bm;
            paramDouble3 = d5;

            OAxisAlignedBB localOAxisAlignedBB2 = aT.b();
            aT.b(localOAxisAlignedBB1);
            localList = aF.a(this, aT.a(paramDouble1, paramDouble2, paramDouble3));

            for (int n = 0; n < localList.size(); n++) {
                paramDouble2 = ((OAxisAlignedBB) localList.get(n)).b(aT, paramDouble2);
            }
            aT.d(0.0D, paramDouble2, 0.0D);

            if ((!aZ) && (d4 != paramDouble2)) {
                paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;
            }

            for (int n = 0; n < localList.size(); n++) {
                paramDouble1 = ((OAxisAlignedBB) localList.get(n)).a(aT, paramDouble1);
            }
            aT.d(paramDouble1, 0.0D, 0.0D);

            if ((!aZ) && (d3 != paramDouble1)) {
                paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;
            }

            for (int n = 0; n < localList.size(); n++) {
                paramDouble3 = ((OAxisAlignedBB) localList.get(n)).c(aT, paramDouble3);
            }
            aT.d(0.0D, 0.0D, paramDouble3);

            if ((!aZ) && (d5 != paramDouble3)) {
                paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;
            }

            if (d7 * d7 + d9 * d9 >= paramDouble1 * paramDouble1 + paramDouble3 * paramDouble3) {
                paramDouble1 = d7;
                paramDouble2 = d8;
                paramDouble3 = d9;
                aT.b(localOAxisAlignedBB2);
            } else {
                bl = (float) (bl + 0.5D);
            }

        }

        aJ = ((aT.a + aT.d) / 2.0D);
        aK = (aT.b + bb - bl);
        aL = ((aT.c + aT.f) / 2.0D);

        aV = ((d3 != paramDouble1) || (d5 != paramDouble3));
        aW = (d4 != paramDouble2);
        aU = ((d4 != paramDouble2) && (d4 < 0.0D));
        aX = ((aV) || (aW));
        a(paramDouble2, aU);

        if (d3 != paramDouble1) {
            aM = 0.0D;
        }
        if (d4 != paramDouble2) {
            aN = 0.0D;
        }
        if (d5 != paramDouble3) {
            aO = 0.0D;
        }

        double d7 = aJ - d1;
        double d8 = aL - d2;

        if ((bg) && (i == 0)) {
            bf = (float) (bf + OMathHelper.a(d7 * d7 + d8 * d8) * 0.6D);
            int i1 = OMathHelper.b(aJ);
            int i2 = OMathHelper.b(aK - 0.2000000029802322D - bb);
            int m = OMathHelper.b(aL);
            int n = aF.a(i1, i2, m);
            if ((bf > b) && (n > 0)) {
                b += 1;
                OStepSound localOStepSound = OBlock.m[n].bt;
                if (aF.a(i1, i2 + 1, m) == OBlock.aS.bk) {
                    localOStepSound = OBlock.aS.bt;
                    aF.a(this, localOStepSound.c(), localOStepSound.a() * 0.15F, localOStepSound.b());
                } else if (!OBlock.m[n].bv.d()) {
                    aF.a(this, localOStepSound.c(), localOStepSound.a() * 0.15F, localOStepSound.b());
                }
                OBlock.m[n].b(aF, i1, i2, m, this);
            }

        }

        int i1 = OMathHelper.b(aT.a);
        int i2 = OMathHelper.b(aT.b);
        int m = OMathHelper.b(aT.c);
        int n = OMathHelper.b(aT.d);
        int i3 = OMathHelper.b(aT.e);
        int i4 = OMathHelper.b(aT.f);

        if (aF.a(i1, i2, m, n, i3, i4)) {
            for (int i5 = i1; i5 <= n; i5++) {
                for (int i6 = i2; i6 <= i3; i6++) {
                    for (int i7 = m; i7 <= i4; i7++) {
                        int i8 = aF.a(i5, i6, i7);
                        if (i8 > 0) {
                            OBlock.m[i8].a(aF, i5, i6, i7, this);
                        }
                    }
                }
            }
        }
        bl *= 0.4F;

        boolean bool = g_();
        if (aF.c(aT)) {
            a(1);
            if (!bool) {
                bt += 1;
                if (bt == 0) {
                    bt = 300;
                }
            }
        } else if (bt <= 0) {
            bt = (-bs);
        }

        if ((bool) && (bt > 0)) {
            aF.a(this, "random.fizz", 0.7F, 1.6F + (bq.nextFloat() - bq.nextFloat()) * 0.4F);
            bt = (-bs);
        }
    }

    protected void a(double paramDouble, boolean paramBoolean) {
        if (paramBoolean) {
            if (bh > 0.0F) {
                a(bh);
                bh = 0.0F;
            }
        } else if (paramDouble < 0.0D) {
            bh = (float) (bh - paramDouble);
        }
    }

    public OAxisAlignedBB d() {
        return null;
    }

    protected void a(int paramInt) {
        if (!by) {
            if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FIRE, null, entity, paramInt))
                a((OEntity) null, paramInt);
        }
    }

    protected void a(float paramFloat) {
    }

    public boolean g_() {
        return aF.a(aT.b(0.0D, -0.4000000059604645D, 0.0D), OMaterial.f, this);
    }

    public boolean a(OMaterial paramOMaterial) {
        double d1 = aK + p();
        int i = OMathHelper.b(aJ);
        int j = OMathHelper.d(OMathHelper.b(d1));
        int k = OMathHelper.b(aL);
        int m = aF.a(i, j, k);
        if ((m != 0) && (OBlock.m[m].bv == paramOMaterial)) {
            float f1 = OBlockFluids.c(aF.b(i, j, k)) - 0.1111111F;
            float f2 = j + 1 - f1;
            return d1 < f2;
        }
        return false;
    }

    public float p() {
        return 0.0F;
    }

    public boolean Q() {
        return aF.a(aT.b(-0.1000000014901161D, -0.4000000059604645D, -0.1000000014901161D), OMaterial.g);
    }

    public void a(float paramFloat1, float paramFloat2, float paramFloat3) {
        float f1 = OMathHelper.c(paramFloat1 * paramFloat1 + paramFloat2 * paramFloat2);
        if (f1 < 0.01F) {
            return;
        }

        if (f1 < 1.0F) {
            f1 = 1.0F;
        }
        f1 = paramFloat3 / f1;
        paramFloat1 *= f1;
        paramFloat2 *= f1;

        float f2 = OMathHelper.a(aP * 3.141593F / 180.0F);
        float f3 = OMathHelper.b(aP * 3.141593F / 180.0F);

        aM += paramFloat1 * f3 - paramFloat2 * f2;
        aO += paramFloat2 * f3 + paramFloat1 * f2;
    }

    public float c(float paramFloat) {
        int i = OMathHelper.b(aJ);

        double d1 = (aT.e - aT.b) * 0.66D;
        int j = OMathHelper.b(aK - bb + d1);
        int k = OMathHelper.b(aL);
        if (aF.a(OMathHelper.b(aT.a), OMathHelper.b(aT.b), OMathHelper.b(aT.c), OMathHelper.b(aT.d), OMathHelper.b(aT.e), OMathHelper.b(aT.f))) {
            return aF.l(i, j, k);
        }
        return 0.0F;
    }

    public void b(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {
        aG = (this.aJ = paramDouble1);
        aH = (this.aK = paramDouble2);
        aI = (this.aL = paramDouble3);
        aR = (this.aP = paramFloat1);
        aS = (this.aQ = paramFloat2);
        bl = 0.0F;

        double d1 = aR - paramFloat1;
        if (d1 < -180.0D) {
            aR += 360.0F;
        }
        if (d1 >= 180.0D) {
            aR -= 360.0F;
        }
        a(aJ, aK, aL);
        c(paramFloat1, paramFloat2);
    }

    public void c(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {
        bi = (this.aG = this.aJ = paramDouble1);
        bj = (this.aH = this.aK = paramDouble2 + bb);
        bk = (this.aI = this.aL = paramDouble3);
        aP = paramFloat1;
        aQ = paramFloat2;
        a(aJ, aK, aL);
    }

    public float f(OEntity paramOEntity) {
        float f1 = (float) (aJ - paramOEntity.aJ);
        float f2 = (float) (aK - paramOEntity.aK);
        float f3 = (float) (aL - paramOEntity.aL);
        return OMathHelper.c(f1 * f1 + f2 * f2 + f3 * f3);
    }

    public double d(double paramDouble1, double paramDouble2, double paramDouble3) {
        double d1 = aJ - paramDouble1;
        double d2 = aK - paramDouble2;
        double d3 = aL - paramDouble3;
        return d1 * d1 + d2 * d2 + d3 * d3;
    }

    public double e(double paramDouble1, double paramDouble2, double paramDouble3) {
        double d1 = aJ - paramDouble1;
        double d2 = aK - paramDouble2;
        double d3 = aL - paramDouble3;
        return OMathHelper.a(d1 * d1 + d2 * d2 + d3 * d3);
    }

    public double g(OEntity paramOEntity) {
        double d1 = aJ - paramOEntity.aJ;
        double d2 = aK - paramOEntity.aK;
        double d3 = aL - paramOEntity.aL;
        return d1 * d1 + d2 * d2 + d3 * d3;
    }

    public void b(OEntityPlayer paramOEntityPlayer) {
    }

    public void h(OEntity paramOEntity) {
        if ((paramOEntity.aD == this) || (paramOEntity.aE == this)) {
            return;
        }

        double d1 = paramOEntity.aJ - aJ;
        double d2 = paramOEntity.aL - aL;

        double d3 = OMathHelper.a(d1, d2);

        if (d3 >= 0.009999999776482582D) {
            d3 = OMathHelper.a(d3);
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

            d1 *= (1.0F - bo);
            d2 *= (1.0F - bo);

            f(-d1, 0.0D, -d2);
            paramOEntity.f(d1, 0.0D, d2);
        }
    }

    public void f(double paramDouble1, double paramDouble2, double paramDouble3) {
        aM += paramDouble1;
        aN += paramDouble2;
        aO += paramDouble3;
    }

    protected void R() {
        aY = true;
    }

    public boolean a(OEntity paramOEntity, int paramInt) {
        R();
        return false;
    }

    public boolean d_() {
        return false;
    }

    public boolean e_() {
        return false;
    }

    public void c(OEntity paramOEntity, int paramInt) {
    }

    public boolean c(ONBTTagCompound paramONBTTagCompound) {
        String str = S();
        if ((ba) || (str == null)) {
            return false;
        }
        paramONBTTagCompound.a("id", str);
        d(paramONBTTagCompound);
        return true;
    }

    public void d(ONBTTagCompound paramONBTTagCompound) {
        paramONBTTagCompound.a("Pos", a(new double[] { aJ, aK, aL }));
        paramONBTTagCompound.a("Motion", a(new double[] { aM, aN, aO }));
        paramONBTTagCompound.a("Rotation", a(new float[] { aP, aQ }));

        paramONBTTagCompound.a("FallDistance", bh);
        paramONBTTagCompound.a("Fire", (short) bt);
        paramONBTTagCompound.a("Air", (short) bx);
        paramONBTTagCompound.a("OnGround", aU);

        a(paramONBTTagCompound);
    }

    public void e(ONBTTagCompound paramONBTTagCompound) {
        ONBTTagList localONBTTagList1 = paramONBTTagCompound.l("Pos");
        ONBTTagList localONBTTagList2 = paramONBTTagCompound.l("Motion");
        ONBTTagList localONBTTagList3 = paramONBTTagCompound.l("Rotation");
        a(0.0D, 0.0D, 0.0D);

        aM = ((ONBTTagDouble) localONBTTagList2.a(0)).a;
        aN = ((ONBTTagDouble) localONBTTagList2.a(1)).a;
        aO = ((ONBTTagDouble) localONBTTagList2.a(2)).a;

        if (Math.abs(aM) > 10.0D) {
            aM = 0.0D;
        }
        if (Math.abs(aN) > 10.0D) {
            aN = 0.0D;
        }
        if (Math.abs(aO) > 10.0D) {
            aO = 0.0D;
        }

        aG = (this.bi = this.aJ = ((ONBTTagDouble) localONBTTagList1.a(0)).a);
        aH = (this.bj = this.aK = ((ONBTTagDouble) localONBTTagList1.a(1)).a);
        aI = (this.bk = this.aL = ((ONBTTagDouble) localONBTTagList1.a(2)).a);

        aR = (this.aP = ((ONBTTagFloat) localONBTTagList3.a(0)).a % 6.283186F);
        aS = (this.aQ = ((ONBTTagFloat) localONBTTagList3.a(1)).a % 6.283186F);

        bh = paramONBTTagCompound.g("FallDistance");
        bt = paramONBTTagCompound.d("Fire");
        bx = paramONBTTagCompound.d("Air");
        aU = paramONBTTagCompound.m("OnGround");

        a(aJ, aK, aL);

        b(paramONBTTagCompound);
    }

    protected final String S() {
        return OEntityList.b(this);
    }

    protected abstract void b(ONBTTagCompound paramONBTTagCompound);

    protected abstract void a(ONBTTagCompound paramONBTTagCompound);

    protected ONBTTagList a(double[] paramArrayOfDouble) {
        ONBTTagList localONBTTagList = new ONBTTagList();
        for (double d1 : paramArrayOfDouble) {
            localONBTTagList.a(new ONBTTagDouble(d1));
        }
        return localONBTTagList;
    }

    protected ONBTTagList a(float[] paramArrayOfFloat) {
        ONBTTagList localONBTTagList = new ONBTTagList();
        for (float f : paramArrayOfFloat) {
            localONBTTagList.a(new ONBTTagFloat(f));
        }
        return localONBTTagList;
    }

    public OEntityItem b(int paramInt1, int paramInt2) {
        return a(paramInt1, paramInt2, 0.0F);
    }

    public OEntityItem a(int paramInt1, int paramInt2, float paramFloat) {
        return a(new OItemStack(paramInt1, paramInt2, 0), paramFloat);
    }

    public OEntityItem a(OItemStack paramOItemStack, float paramFloat) {
        OEntityItem localOEntityItem = new OEntityItem(aF, aJ, aK + paramFloat, aL, paramOItemStack);
        localOEntityItem.c = 10;
        aF.a(localOEntityItem);
        return localOEntityItem;
    }

    public boolean J() {
        return !ba;
    }

    public boolean D() {
        int i = OMathHelper.b(aJ);
        int j = OMathHelper.b(aK + p());
        int k = OMathHelper.b(aL);
        return aF.d(i, j, k);
    }

    public boolean a(OEntityPlayer paramOEntityPlayer) {
        return false;
    }

    public OAxisAlignedBB a_(OEntity paramOEntity) {
        return null;
    }

    public void x() {
        if (aE.ba) {
            aE = null;
            return;
        }
        aM = 0.0D;
        aN = 0.0D;
        aO = 0.0D;
        f_();
        aE.h_();

        e += aE.aP - aE.aR;
        d += aE.aQ - aE.aS;

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

        aP = (float) (aP + d1);
        aQ = (float) (aQ + d2);
    }

    public void h_() {
        aD.a(aJ, aK + k() + aD.B(), aL);
    }

    public double B() {
        return bb;
    }

    public double k() {
        return bd * 0.75D;
    }

    public void b(OEntity paramOEntity) {
        d = 0.0D;
        e = 0.0D;

        if (paramOEntity == null) {
            if (aE != null) {
                c(aE.aJ, aE.aT.b + aE.bd, aE.aL, aP, aQ);
                aE.aD = null;
            }
            aE = null;
            return;
        }
        if (aE == paramOEntity) {
            aE.aD = null;
            aE = null;
            c(paramOEntity.aJ, paramOEntity.aT.b + paramOEntity.bd, paramOEntity.aL, aP, aQ);
            return;
        }
        if (aE != null) {
            aE.aD = null;
        }
        if (paramOEntity.aD != null) {
            paramOEntity.aD.aE = null;
        }
        aE = paramOEntity;
        paramOEntity.aD = this;
    }

    public OVec3D N() {
        return null;
    }

    public void T() {
    }

    public OItemStack[] k_() {
        return null;
    }

    public boolean U() {
        return d(1);
    }

    public void b(boolean paramBoolean) {
        a(1, paramBoolean);
    }

    protected boolean d(int paramInt) {
        return (bz.a(0) & 1 << paramInt) != 0;
    }

    protected void a(int paramInt, boolean paramBoolean) {
        int i = bz.a(0);
        if (paramBoolean) {
            bz.b(0, Byte.valueOf((byte) (i | 1 << paramInt)));
        } else {
            bz.b(0, Byte.valueOf((byte) (i & (1 << paramInt ^ 0xFFFFFFFF))));
        }
    }
}
