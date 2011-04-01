import java.util.List;
import java.util.Random;

public abstract class OEntity {
    private static int          a  = 0;

    public int                  aB = a++;

    public double               aC = 1.0D;

    public boolean              aD = false;
    public OEntity              aE;
    public OEntity              aF;
    public OWorld               aG;
    public double               aH;
    public double               aI;
    public double               aJ;
    public double               aK;
    public double               aL;
    public double               aM;
    public double               aN;
    public double               aO;
    public double               aP;
    public float                aQ;
    public float                aR;
    public float                aS;
    public float                aT;
    public final OAxisAlignedBB aU = OAxisAlignedBB.a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
    public boolean              aV = false;
    public boolean              aW;
    public boolean              aX;
    public boolean              aY = false;
    public boolean              aZ = false;

    public boolean              ba = true;
    public boolean              bb = false;
    public float                bc = 0.0F;

    public float                bd = 0.6F;
    public float                be = 1.8F;

    public float                bf = 0.0F;
    public float                bg = 0.0F;
    protected float             bh = 0.0F;
    private int                 b  = 1;
    public double               bi;
    public double               bj;
    public double               bk;
    public float                bl = 0.0F;
    public float                bm = 0.0F;
    public boolean              bn = false;
    public float                bo = 0.0F;
    public boolean              bp = false;

    protected Random            bq = new Random();
    public int                  br = 0;
    public int                  bs = 1;

    public int                  bt = 0;

    protected int               bu = 300;
    protected boolean           bv = false;
    public int                  bw = 0;
    public int                  bx = 300;

    private boolean             c  = true;

    protected boolean           by = false;

    protected ODataWatcher      bz = new ODataWatcher();
    private double              d;
    private double              e;
    public boolean              bA = false;
    public int                  bB;
    public int                  bC;
    public int                  bD;

 // hMod Start
    BaseEntity                  entity  = new BaseEntity(this);
    public static PluginLoader  manager = etc.getLoader();

    // hMod end

    
    public OEntity(OWorld paramOWorld) {
        aG = paramOWorld;

        a(0.0D, 0.0D, 0.0D);

        bz.a(0, (byte) 0);
        a();
    }

    protected abstract void a();

    public ODataWatcher T() {
        return bz;
    }

    @Override
    public boolean equals(Object paramObject) {
        if ((paramObject instanceof OEntity))
            return ((OEntity) paramObject).aB == aB;
        return false;
    }

    @Override
    public int hashCode() {
        return aB;
    }

    public void D() {
        bb = true;
    }

    protected void b(float paramFloat1, float paramFloat2) {
        bd = paramFloat1;
        be = paramFloat2;
    }

    protected void c(float paramFloat1, float paramFloat2) {
        aQ = paramFloat1;
        aR = paramFloat2;
    }

    public void a(double paramDouble1, double paramDouble2, double paramDouble3) {
        aK = paramDouble1;
        aL = paramDouble2;
        aM = paramDouble3;
        float f1 = bd / 2.0F;
        float f2 = be;

        aU.c(paramDouble1 - f1, paramDouble2 - bc + bl, paramDouble3 - f1, paramDouble1 + f1, paramDouble2 - bc + bl + f2, paramDouble3 + f1);
    }

    public void f_() {
        L();
    }

    public void L() {
        if ((aF != null) && (aF.bb))
            aF = null;

        br += 1;
        bf = bg;
        aH = aK;
        aI = aL;
        aJ = aM;
        aT = aR;
        aS = aQ;

        if (g_()) {
            if ((!bv) && (!c)) {
                float f1 = OMathHelper.a(aN * aN * 0.2000000029802322D + aO * aO + aP * aP * 0.2000000029802322D) * 0.2F;
                if (f1 > 1.0F)
                    f1 = 1.0F;
                aG.a(this, "random.splash", f1, 1.0F + (bq.nextFloat() - bq.nextFloat()) * 0.4F);
                float f2 = OMathHelper.b(aU.b);
                float f3;
                float f4;
                for (int i = 0; i < 1.0F + bd * 20.0F; i++) {
                    f3 = (bq.nextFloat() * 2.0F - 1.0F) * bd;
                    f4 = (bq.nextFloat() * 2.0F - 1.0F) * bd;
                    aG.a("bubble", aK + f3, f2 + 1.0F, aM + f4, aN, aO - bq.nextFloat() * 0.2F, aP);
                }
                for (int i = 0; i < 1.0F + bd * 20.0F; i++) {
                    f3 = (bq.nextFloat() * 2.0F - 1.0F) * bd;
                    f4 = (bq.nextFloat() * 2.0F - 1.0F) * bd;
                    aG.a("splash", aK + f3, f2 + 1.0F, aM + f4, aN, aO, aP);
                }
            }
            bh = 0.0F;
            bv = true;
            bt = 0;
        } else
            bv = false;

        if (aG.t)
            bt = 0;
        else if (bt > 0)
            if (by) {
                bt -= 4;
                if (bt < 0)
                    bt = 0;
            } else {
                if (bt % 20 == 0)
                 // hMod Damage hook: Periodic burn damage
                    if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FIRE_TICK, null, entity, 1))
                        a((OEntity) null, 1);

                bt -= 1;
            }

        if (V())
            U();

        if (aL < -64.0D)
            R();

        if (!aG.t) {
            a(0, bt > 0);
            a(2, aF != null);
        }

        c = false;
    }

    protected void U() {
        if (!by) {
         // hMod Damage hook: Lava
            if (this instanceof OEntityLiving)
                if ((Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.LAVA, null, entity, 4))
                    return;
            a((OEntity) null, 4);

            bt = 600;
        }
    }

    protected void R() {
        D();
    }

    public boolean b(double paramDouble1, double paramDouble2, double paramDouble3) {
        OAxisAlignedBB localOAxisAlignedBB = aU.c(paramDouble1, paramDouble2, paramDouble3);
        List localList = aG.a(this, localOAxisAlignedBB);
        if (localList.size() > 0)
            return false;
        return !aG.b(localOAxisAlignedBB);
    }

    public void c(double paramDouble1, double paramDouble2, double paramDouble3) {
        if (bn) {
            aU.d(paramDouble1, paramDouble2, paramDouble3);
            aK = ((aU.a + aU.d) / 2.0D);
            aL = (aU.b + bc - bl);
            aM = ((aU.c + aU.f) / 2.0D);
            return;
        }

        double d1 = aK;
        double d2 = aM;

        double d3 = paramDouble1;
        double d4 = paramDouble2;
        double d5 = paramDouble3;

        OAxisAlignedBB localOAxisAlignedBB1 = aU.b();

        int i = (aV) && (Z()) ? 1 : 0;

        if (i != 0) {
            double d6 = 0.05D;
            while ((paramDouble1 != 0.0D) && (aG.a(this, aU.c(paramDouble1, -1.0D, 0.0D)).size() == 0)) {
                if ((paramDouble1 < d6) && (paramDouble1 >= -d6))
                    paramDouble1 = 0.0D;
                else if (paramDouble1 > 0.0D)
                    paramDouble1 -= d6;
                else
                    paramDouble1 += d6;
                d3 = paramDouble1;
            }
            while ((paramDouble3 != 0.0D) && (aG.a(this, aU.c(0.0D, -1.0D, paramDouble3)).size() == 0)) {
                if ((paramDouble3 < d6) && (paramDouble3 >= -d6))
                    paramDouble3 = 0.0D;
                else if (paramDouble3 > 0.0D)
                    paramDouble3 -= d6;
                else
                    paramDouble3 += d6;
                d5 = paramDouble3;
            }
        }

        List localList = aG.a(this, aU.a(paramDouble1, paramDouble2, paramDouble3));

        for (int j = 0; j < localList.size(); j++)
            paramDouble2 = ((OAxisAlignedBB) localList.get(j)).b(aU, paramDouble2);
        aU.d(0.0D, paramDouble2, 0.0D);

        if ((!ba) && (d4 != paramDouble2))
            paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;

        int j = (aV) || ((d4 != paramDouble2) && (d4 < 0.0D)) ? 1 : 0;

        for (int k = 0; k < localList.size(); k++)
            paramDouble1 = ((OAxisAlignedBB) localList.get(k)).a(aU, paramDouble1);
        aU.d(paramDouble1, 0.0D, 0.0D);

        if ((!ba) && (d3 != paramDouble1))
            paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;

        for (int k = 0; k < localList.size(); k++)
            paramDouble3 = ((OAxisAlignedBB) localList.get(k)).c(aU, paramDouble3);
        aU.d(0.0D, 0.0D, paramDouble3);

        if ((!ba) && (d5 != paramDouble3))
            paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;

        if ((bm > 0.0F) && (j != 0) && (bl < 0.05F) && ((d3 != paramDouble1) || (d5 != paramDouble3))) {
            double d7 = paramDouble1;
            double d8 = paramDouble2;
            double d9 = paramDouble3;

            paramDouble1 = d3;
            paramDouble2 = bm;
            paramDouble3 = d5;

            OAxisAlignedBB localOAxisAlignedBB2 = aU.b();
            aU.b(localOAxisAlignedBB1);
            localList = aG.a(this, aU.a(paramDouble1, paramDouble2, paramDouble3));

            for (int n = 0; n < localList.size(); n++)
                paramDouble2 = ((OAxisAlignedBB) localList.get(n)).b(aU, paramDouble2);
            aU.d(0.0D, paramDouble2, 0.0D);

            if ((!ba) && (d4 != paramDouble2))
                paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;

            for (int n = 0; n < localList.size(); n++)
                paramDouble1 = ((OAxisAlignedBB) localList.get(n)).a(aU, paramDouble1);
            aU.d(paramDouble1, 0.0D, 0.0D);

            if ((!ba) && (d3 != paramDouble1))
                paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;

            for (int n = 0; n < localList.size(); n++)
                paramDouble3 = ((OAxisAlignedBB) localList.get(n)).c(aU, paramDouble3);
            aU.d(0.0D, 0.0D, paramDouble3);

            if ((!ba) && (d5 != paramDouble3))
                paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;

            if (d7 * d7 + d9 * d9 >= paramDouble1 * paramDouble1 + paramDouble3 * paramDouble3) {
                paramDouble1 = d7;
                paramDouble2 = d8;
                paramDouble3 = d9;
                aU.b(localOAxisAlignedBB2);
            } else
                bl = (float) (bl + 0.5D);

        }

        aK = ((aU.a + aU.d) / 2.0D);
        aL = (aU.b + bc - bl);
        aM = ((aU.c + aU.f) / 2.0D);

        aW = ((d3 != paramDouble1) || (d5 != paramDouble3));
        aX = (d4 != paramDouble2);
        aV = ((d4 != paramDouble2) && (d4 < 0.0D));
        aY = ((aW) || (aX));
        a(paramDouble2, aV);

        if (d3 != paramDouble1)
            aN = 0.0D;
        if (d4 != paramDouble2)
            aO = 0.0D;
        if (d5 != paramDouble3)
            aP = 0.0D;

        double d7 = aK - d1;
        double d8 = aM - d2;

        if ((l()) && (i == 0)) {
            bg = (float) (bg + OMathHelper.a(d7 * d7 + d8 * d8) * 0.6D);
            int i1 = OMathHelper.b(aK);
            int i2 = OMathHelper.b(aL - 0.2000000029802322D - bc);
            int m = OMathHelper.b(aM);
            int n = aG.a(i1, i2, m);
            if ((bg > b) && (n > 0)) {
                b += 1;
                OStepSound localOStepSound = OBlock.m[n].bu;
                if (aG.a(i1, i2 + 1, m) == OBlock.aS.bl) {
                    localOStepSound = OBlock.aS.bu;
                    aG.a(this, localOStepSound.c(), localOStepSound.a() * 0.15F, localOStepSound.b());
                } else if (!OBlock.m[n].bw.d())
                    aG.a(this, localOStepSound.c(), localOStepSound.a() * 0.15F, localOStepSound.b());
                OBlock.m[n].b(aG, i1, i2, m, this);
            }

        }

        int i1 = OMathHelper.b(aU.a);
        int i2 = OMathHelper.b(aU.b);
        int m = OMathHelper.b(aU.c);
        int n = OMathHelper.b(aU.d);
        int i3 = OMathHelper.b(aU.e);
        int i4 = OMathHelper.b(aU.f);

        if (aG.a(i1, i2, m, n, i3, i4))
            for (int i5 = i1; i5 <= n; i5++)
                for (int i6 = i2; i6 <= i3; i6++)
                    for (int i7 = m; i7 <= i4; i7++) {
                        int i8 = aG.a(i5, i6, i7);
                        if (i8 > 0)
                            OBlock.m[i8].a(aG, i5, i6, i7, this);
                    }
        bl *= 0.4F;

        boolean bool = g_();
        if (aG.c(aU)) {
            a(1);
            if (!bool) {
                bt += 1;
                if (bt == 0)
                    bt = 300;
            }
        } else if (bt <= 0)
            bt = (-bs);

        if ((bool) && (bt > 0)) {
            aG.a(this, "random.fizz", 0.7F, 1.6F + (bq.nextFloat() - bq.nextFloat()) * 0.4F);
            bt = (-bs);
        }
    }

    protected boolean l() {
        return true;
    }

    protected void a(double paramDouble, boolean paramBoolean) {
        if (paramBoolean) {
            if (bh > 0.0F) {
                a(bh);
                bh = 0.0F;
            }
        } else if (paramDouble < 0.0D)
            bh = (float) (bh - paramDouble);
    }

    public OAxisAlignedBB d() {
        return null;
    }

    protected void a(int paramInt) {
        if (!by)
            if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FIRE, null, entity, paramInt))
                a((OEntity) null, paramInt);

    }

    protected void a(float paramFloat) {
    }

    public boolean g_() {
        return aG.a(aU.b(0.0D, -0.4000000059604645D, 0.0D), OMaterial.f, this);
    }

    public boolean a(OMaterial paramOMaterial) {
        double d1 = aL + q();
        int i = OMathHelper.b(aK);
        int j = OMathHelper.d(OMathHelper.b(d1));
        int k = OMathHelper.b(aM);
        int m = aG.a(i, j, k);
        if ((m != 0) && (OBlock.m[m].bw == paramOMaterial)) {
            float f1 = OBlockFluids.c(aG.b(i, j, k)) - 0.1111111F;
            float f2 = j + 1 - f1;
            return d1 < f2;
        }
        return false;
    }

    public float q() {
        return 0.0F;
    }

    public boolean V() {
        return aG.a(aU.b(-0.1000000014901161D, -0.4000000059604645D, -0.1000000014901161D), OMaterial.g);
    }

    public void a(float paramFloat1, float paramFloat2, float paramFloat3) {
        float f1 = OMathHelper.c(paramFloat1 * paramFloat1 + paramFloat2 * paramFloat2);
        if (f1 < 0.01F)
            return;

        if (f1 < 1.0F)
            f1 = 1.0F;
        f1 = paramFloat3 / f1;
        paramFloat1 *= f1;
        paramFloat2 *= f1;

        float f2 = OMathHelper.a(aQ * 3.141593F / 180.0F);
        float f3 = OMathHelper.b(aQ * 3.141593F / 180.0F);

        aN += paramFloat1 * f3 - paramFloat2 * f2;
        aP += paramFloat2 * f3 + paramFloat1 * f2;
    }

    public float c(float paramFloat) {
        int i = OMathHelper.b(aK);

        double d1 = (aU.e - aU.b) * 0.66D;
        int j = OMathHelper.b(aL - bc + d1);
        int k = OMathHelper.b(aM);
        if (aG.a(OMathHelper.b(aU.a), OMathHelper.b(aU.b), OMathHelper.b(aU.c), OMathHelper.b(aU.d), OMathHelper.b(aU.e), OMathHelper.b(aU.f)))
            return aG.l(i, j, k);
        return 0.0F;
    }

    public void b(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {
        aH = (aK = paramDouble1);
        aI = (aL = paramDouble2);
        aJ = (aM = paramDouble3);
        aS = (aQ = paramFloat1);
        aT = (aR = paramFloat2);
        bl = 0.0F;

        double d1 = aS - paramFloat1;
        if (d1 < -180.0D)
            aS += 360.0F;
        if (d1 >= 180.0D)
            aS -= 360.0F;
        a(aK, aL, aM);
        c(paramFloat1, paramFloat2);
    }

    public void c(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {
        bi = (aH = aK = paramDouble1);
        bj = (aI = aL = paramDouble2 + bc);
        bk = (aJ = aM = paramDouble3);
        aQ = paramFloat1;
        aR = paramFloat2;
        a(aK, aL, aM);
    }

    public float f(OEntity paramOEntity) {
        float f1 = (float) (aK - paramOEntity.aK);
        float f2 = (float) (aL - paramOEntity.aL);
        float f3 = (float) (aM - paramOEntity.aM);
        return OMathHelper.c(f1 * f1 + f2 * f2 + f3 * f3);
    }

    public double d(double paramDouble1, double paramDouble2, double paramDouble3) {
        double d1 = aK - paramDouble1;
        double d2 = aL - paramDouble2;
        double d3 = aM - paramDouble3;
        return d1 * d1 + d2 * d2 + d3 * d3;
    }

    public double e(double paramDouble1, double paramDouble2, double paramDouble3) {
        double d1 = aK - paramDouble1;
        double d2 = aL - paramDouble2;
        double d3 = aM - paramDouble3;
        return OMathHelper.a(d1 * d1 + d2 * d2 + d3 * d3);
    }

    public double g(OEntity paramOEntity) {
        double d1 = aK - paramOEntity.aK;
        double d2 = aL - paramOEntity.aL;
        double d3 = aM - paramOEntity.aM;
        return d1 * d1 + d2 * d2 + d3 * d3;
    }

    public void b(OEntityPlayer paramOEntityPlayer) {
    }

    public void h(OEntity paramOEntity) {
        if ((paramOEntity.aE == this) || (paramOEntity.aF == this))
            return;

        double d1 = paramOEntity.aK - aK;
        double d2 = paramOEntity.aM - aM;

        double d3 = OMathHelper.a(d1, d2);

        if (d3 >= 0.009999999776482582D) {
            d3 = OMathHelper.a(d3);
            d1 /= d3;
            d2 /= d3;

            double d4 = 1.0D / d3;
            if (d4 > 1.0D)
                d4 = 1.0D;
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
        aN += paramDouble1;
        aO += paramDouble2;
        aP += paramDouble3;
    }

    protected void W() {
        aZ = true;
    }

    public boolean a(OEntity paramOEntity, int paramInt) {
        W();
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
        String str = X();
        if ((bb) || (str == null))
            return false;
        paramONBTTagCompound.a("id", str);
        d(paramONBTTagCompound);
        return true;
    }

    public void d(ONBTTagCompound paramONBTTagCompound) {
        paramONBTTagCompound.a("Pos", a(new double[] { aK, aL, aM }));
        paramONBTTagCompound.a("Motion", a(new double[] { aN, aO, aP }));
        paramONBTTagCompound.a("Rotation", a(new float[] { aQ, aR }));

        paramONBTTagCompound.a("FallDistance", bh);
        paramONBTTagCompound.a("Fire", (short) bt);
        paramONBTTagCompound.a("Air", (short) bx);
        paramONBTTagCompound.a("OnGround", aV);

        a(paramONBTTagCompound);
    }

    public void e(ONBTTagCompound paramONBTTagCompound) {
        ONBTTagList localONBTTagList1 = paramONBTTagCompound.l("Pos");
        ONBTTagList localONBTTagList2 = paramONBTTagCompound.l("Motion");
        ONBTTagList localONBTTagList3 = paramONBTTagCompound.l("Rotation");
        a(0.0D, 0.0D, 0.0D);

        aN = ((ONBTTagDouble) localONBTTagList2.a(0)).a;
        aO = ((ONBTTagDouble) localONBTTagList2.a(1)).a;
        aP = ((ONBTTagDouble) localONBTTagList2.a(2)).a;

        if (Math.abs(aN) > 10.0D)
            aN = 0.0D;
        if (Math.abs(aO) > 10.0D)
            aO = 0.0D;
        if (Math.abs(aP) > 10.0D)
            aP = 0.0D;

        aH = (bi = aK = ((ONBTTagDouble) localONBTTagList1.a(0)).a);
        aI = (bj = aL = ((ONBTTagDouble) localONBTTagList1.a(1)).a);
        aJ = (bk = aM = ((ONBTTagDouble) localONBTTagList1.a(2)).a);

        aS = (aQ = ((ONBTTagFloat) localONBTTagList3.a(0)).a % 6.283186F);
        aT = (aR = ((ONBTTagFloat) localONBTTagList3.a(1)).a % 6.283186F);

        bh = paramONBTTagCompound.g("FallDistance");
        bt = paramONBTTagCompound.d("Fire");
        bx = paramONBTTagCompound.d("Air");
        aV = paramONBTTagCompound.m("OnGround");

        a(aK, aL, aM);

        b(paramONBTTagCompound);
    }

    protected final String X() {
        return OEntityList.b(this);
    }

    protected abstract void b(ONBTTagCompound paramONBTTagCompound);

    protected abstract void a(ONBTTagCompound paramONBTTagCompound);

    protected ONBTTagList a(double[] paramArrayOfDouble) {
        ONBTTagList localONBTTagList = new ONBTTagList();
        for (double d1 : paramArrayOfDouble)
            localONBTTagList.a(new ONBTTagDouble(d1));
        return localONBTTagList;
    }

    protected ONBTTagList a(float[] paramArrayOfFloat) {
        ONBTTagList localONBTTagList = new ONBTTagList();
        for (float f : paramArrayOfFloat)
            localONBTTagList.a(new ONBTTagFloat(f));
        return localONBTTagList;
    }

    public OEntityItem b(int paramInt1, int paramInt2) {
        return a(paramInt1, paramInt2, 0.0F);
    }

    public OEntityItem a(int paramInt1, int paramInt2, float paramFloat) {
        return a(new OItemStack(paramInt1, paramInt2, 0), paramFloat);
    }

    public OEntityItem a(OItemStack paramOItemStack, float paramFloat) {
        OEntityItem localOEntityItem = new OEntityItem(aG, aK, aL + paramFloat, aM, paramOItemStack);
        localOEntityItem.c = 10;
        aG.a(localOEntityItem);
        return localOEntityItem;
    }

    public boolean N() {
        return !bb;
    }

    public boolean E() {
        int i = OMathHelper.b(aK);
        int j = OMathHelper.b(aL + q());
        int k = OMathHelper.b(aM);
        return aG.d(i, j, k);
    }

    public boolean a(OEntityPlayer paramOEntityPlayer) {
        return false;
    }

    public OAxisAlignedBB a_(OEntity paramOEntity) {
        return null;
    }

    public void o_() {
        if (aF.bb) {
            aF = null;
            return;
        }
        aN = 0.0D;
        aO = 0.0D;
        aP = 0.0D;
        f_();
        aF.h_();

        e += aF.aQ - aF.aS;
        d += aF.aR - aF.aT;

        while (e >= 180.0D)
            e -= 360.0D;
        while (e < -180.0D)
            e += 360.0D;
        while (d >= 180.0D)
            d -= 360.0D;
        while (d < -180.0D)
            d += 360.0D;
        double d1 = e * 0.5D;
        double d2 = d * 0.5D;

        float f = 10.0F;
        if (d1 > f)
            d1 = f;
        if (d1 < -f)
            d1 = -f;
        if (d2 > f)
            d2 = f;
        if (d2 < -f)
            d2 = -f;

        e -= d1;
        d -= d2;

        aQ = (float) (aQ + d1);
        aR = (float) (aR + d2);
    }

    public void h_() {
        aE.a(aK, aL + k() + aE.C(), aM);
    }

    public double C() {
        return bc;
    }

    public double k() {
        return be * 0.75D;
    }

    public void b(OEntity paramOEntity) {
        d = 0.0D;
        e = 0.0D;

        if (paramOEntity == null) {
            if (aF != null) {
                c(aF.aK, aF.aU.b + aF.be, aF.aM, aQ, aR);
                aF.aE = null;
            }
            aF = null;
            return;
        }
        if (aF == paramOEntity) {
            aF.aE = null;
            aF = null;
            c(paramOEntity.aK, paramOEntity.aU.b + paramOEntity.be, paramOEntity.aM, aQ, aR);
            return;
        }
        if (aF != null)
            aF.aE = null;
        if (paramOEntity.aE != null)
            paramOEntity.aE.aF = null;
        aF = paramOEntity;
        paramOEntity.aE = this;
    }

    public OVec3D S() {
        return null;
    }

    public void Y() {
    }

    public OItemStack[] k_() {
        return null;
    }

    public boolean Z() {
        return d(1);
    }

    public void e(boolean paramBoolean) {
        a(1, paramBoolean);
    }

    protected boolean d(int paramInt) {
        return (bz.a(0) & 1 << paramInt) != 0;
    }

    protected void a(int paramInt, boolean paramBoolean) {
        int i = bz.a(0);
        if (paramBoolean)
            bz.b(0, Byte.valueOf((byte) (i | 1 << paramInt)));
        else
            bz.b(0, Byte.valueOf((byte) (i & (1 << paramInt ^ 0xFFFFFFFF))));
    }
}