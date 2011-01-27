import java.util.List;
import java.util.Random;

public abstract class OEntity {

    private static int          a       = 0;
    public int                  g       = a++;
    public double               h       = 1.0D;
    public boolean              i       = false;
    public OEntity              j;
    public OEntity              k;
    public OWorld               l;
    public double               m;
    public double               n;
    public double               o;
    public double               p;
    public double               q;
    public double               r;
    public double               s;
    public double               t;
    public double               u;
    public float                v;
    public float                w;
    public float                x;
    public float                y;
    public final OAxisAlignedBB z       = OAxisAlignedBB.a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
    public boolean              A       = false;
    public boolean              B;
    public boolean              C;
    public boolean              D       = false;
    public boolean              E       = false;
    public boolean              F       = true;
    public boolean              G       = false;
    public float                H       = 0.0F;
    public float                I       = 0.6F;
    public float                J       = 1.8F;
    public float                K       = 0.0F;
    public float                L       = 0.0F;
    protected boolean           M       = true;
    protected float             N       = 0.0F;
    private int                 b       = 1;
    public double               O;
    public double               P;
    public double               Q;
    public float                R       = 0.0F;
    public float                S       = 0.0F;
    public boolean              T       = false;
    public float                U       = 0.0F;
    public boolean              V       = false;
    protected Random            W       = new Random();
    public int                  X       = 0;
    public int                  Y       = 1;
    public int                  Z       = 0;
    protected int               aa      = 300;
    protected boolean           ab      = false;
    public int                  ac      = 0;
    public int                  ad      = 300;
    private boolean             c       = true;
    protected boolean           ae      = false;
    protected ODataWatcher      af      = new ODataWatcher();
    private double              d;
    private double              e;
    public boolean              ag      = false;
    public int                  ah;
    public int                  ai;
    public int                  aj;

    // hMod Start
    BaseEntity                  entity  = new BaseEntity(this);
    public static PluginLoader  manager = etc.getLoader();

    // hMod end

    public OEntity(OWorld paramOWorld) {
        l = paramOWorld;

        a(0.0D, 0.0D, 0.0D);

        af.a(0, (byte) 0);
        a();
    }

    protected abstract void a();

    public ODataWatcher p() {
        return af;
    }

    @Override
    public boolean equals(Object paramObject) {
        if ((paramObject instanceof OEntity))
            return ((OEntity) paramObject).g == g;
        return false;
    }

    @Override
    public int hashCode() {
        return g;
    }

    public void q() {
        G = true;
    }

    protected void a(float paramFloat1, float paramFloat2) {
        I = paramFloat1;
        J = paramFloat2;
    }

    protected void b(float paramFloat1, float paramFloat2) {
        v = paramFloat1;
        w = paramFloat2;
    }

    public void a(double paramDouble1, double paramDouble2, double paramDouble3) {
        p = paramDouble1;
        q = paramDouble2;
        r = paramDouble3;
        float f1 = I / 2.0F;
        float f2 = J;

        z.c(paramDouble1 - f1, paramDouble2 - H + R, paramDouble3 - f1, paramDouble1 + f1, paramDouble2 - H + R + f2, paramDouble3 + f1);
    }

    public void b_() {
        r();
    }

    public void r() {
        if ((k != null) && (k.G))
            k = null;

        X += 1;
        K = L;
        m = p;
        n = q;
        o = r;
        y = w;
        x = v;

        if (v()) {
            if ((!ab) && (!c)) {
                float f1 = OMathHelper.a(s * s * 0.2000000029802322D + t * t + u * u * 0.2000000029802322D) * 0.2F;
                if (f1 > 1.0F)
                    f1 = 1.0F;
                l.a(this, "random.splash", f1, 1.0F + (W.nextFloat() - W.nextFloat()) * 0.4F);
                float f2 = OMathHelper.b(z.b);
                float f3;
                float f4;
                for (int i1 = 0; i1 < 1.0F + I * 20.0F; i1++) {
                    f3 = (W.nextFloat() * 2.0F - 1.0F) * I;
                    f4 = (W.nextFloat() * 2.0F - 1.0F) * I;
                    l.a("bubble", p + f3, f2 + 1.0F, r + f4, s, t - W.nextFloat() * 0.2F, u);
                }
                for (int i1 = 0; i1 < 1.0F + I * 20.0F; i1++) {
                    f3 = (W.nextFloat() * 2.0F - 1.0F) * I;
                    f4 = (W.nextFloat() * 2.0F - 1.0F) * I;
                    l.a("splash", p + f3, f2 + 1.0F, r + f4, s, t, u);
                }
            }
            N = 0.0F;
            ab = true;
            Z = 0;
        } else
            ab = false;

        if (l.z)
            Z = 0;
        else if (Z > 0)
            if (ae) {
                Z -= 4;
                if (Z < 0)
                    Z = 0;
            } else {
                if (Z % 20 == 0)
                    // hMod Damage hook: Periodic burn damage
                    if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FIRE_TICK, null, entity, 1))
                        a((OEntity) null, 1);
                Z -= 1;
            }

        if (x())
            s();

        if (q < -64.0D)
            t();

        if (!l.z) {
            a(0, Z > 0);
            a(2, k != null);
        }

        c = false;
    }

    protected void s() {
        if (!ae) {
            // hMod Damage hook: Lava
            if (this instanceof OEntityLiving)
                if ((Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.LAVA, null, entity, 4))
                    return;
            a((OEntity) null, 4);
            Z = 600;
        }
    }

    protected void t() {
        q();
    }

    public boolean b(double paramDouble1, double paramDouble2, double paramDouble3) {
        OAxisAlignedBB localOAxisAlignedBB = z.c(paramDouble1, paramDouble2, paramDouble3);
        List<?> localList = l.a(this, localOAxisAlignedBB);
        if (localList.size() > 0)
            return false;
        return !l.b(localOAxisAlignedBB);
    }

    public void c(double paramDouble1, double paramDouble2, double paramDouble3) {
        if (T) {
            z.d(paramDouble1, paramDouble2, paramDouble3);
            p = ((z.a + z.d) / 2.0D);
            q = (z.b + H - R);
            r = ((z.c + z.f) / 2.0D);
            return;
        }

        double d1 = p;
        double d2 = r;

        double d3 = paramDouble1;
        double d4 = paramDouble2;
        double d5 = paramDouble3;

        OAxisAlignedBB localOAxisAlignedBB1 = z.b();

        int i1 = (A) && (J()) ? 1 : 0;

        if (i1 != 0) {
            double d6 = 0.05D;
            while ((paramDouble1 != 0.0D) && (l.a(this, z.c(paramDouble1, -1.0D, 0.0D)).size() == 0)) {
                if ((paramDouble1 < d6) && (paramDouble1 >= -d6))
                    paramDouble1 = 0.0D;
                else if (paramDouble1 > 0.0D)
                    paramDouble1 -= d6;
                else
                    paramDouble1 += d6;
                d3 = paramDouble1;
            }
            while ((paramDouble3 != 0.0D) && (l.a(this, z.c(0.0D, -1.0D, paramDouble3)).size() == 0)) {
                if ((paramDouble3 < d6) && (paramDouble3 >= -d6))
                    paramDouble3 = 0.0D;
                else if (paramDouble3 > 0.0D)
                    paramDouble3 -= d6;
                else
                    paramDouble3 += d6;
                d5 = paramDouble3;
            }
        }

        List<?> localList = l.a(this, z.a(paramDouble1, paramDouble2, paramDouble3));

        for (int i2 = 0; i2 < localList.size(); i2++)
            paramDouble2 = ((OAxisAlignedBB) localList.get(i2)).b(z, paramDouble2);
        z.d(0.0D, paramDouble2, 0.0D);

        if ((!F) && (d4 != paramDouble2))
            paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;

        int i2 = (A) || ((d4 != paramDouble2) && (d4 < 0.0D)) ? 1 : 0;

        for (int i3 = 0; i3 < localList.size(); i3++)
            paramDouble1 = ((OAxisAlignedBB) localList.get(i3)).a(z, paramDouble1);
        z.d(paramDouble1, 0.0D, 0.0D);

        if ((!F) && (d3 != paramDouble1))
            paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;

        for (int i3 = 0; i3 < localList.size(); i3++)
            paramDouble3 = ((OAxisAlignedBB) localList.get(i3)).c(z, paramDouble3);
        z.d(0.0D, 0.0D, paramDouble3);

        if ((!F) && (d5 != paramDouble3))
            paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;

        if ((S > 0.0F) && (i2 != 0) && (R < 0.05F) && ((d3 != paramDouble1) || (d5 != paramDouble3))) {
            double d7 = paramDouble1;
            double d8 = paramDouble2;
            double d9 = paramDouble3;

            paramDouble1 = d3;
            paramDouble2 = S;
            paramDouble3 = d5;

            OAxisAlignedBB localOAxisAlignedBB2 = z.b();
            z.b(localOAxisAlignedBB1);
            localList = l.a(this, z.a(paramDouble1, paramDouble2, paramDouble3));

            for (int i5 = 0; i5 < localList.size(); i5++)
                paramDouble2 = ((OAxisAlignedBB) localList.get(i5)).b(z, paramDouble2);
            z.d(0.0D, paramDouble2, 0.0D);

            if ((!F) && (d4 != paramDouble2))
                paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;

            for (int i5 = 0; i5 < localList.size(); i5++)
                paramDouble1 = ((OAxisAlignedBB) localList.get(i5)).a(z, paramDouble1);
            z.d(paramDouble1, 0.0D, 0.0D);

            if ((!F) && (d3 != paramDouble1))
                paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;

            for (int i5 = 0; i5 < localList.size(); i5++)
                paramDouble3 = ((OAxisAlignedBB) localList.get(i5)).c(z, paramDouble3);
            z.d(0.0D, 0.0D, paramDouble3);

            if ((!F) && (d5 != paramDouble3))
                paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;

            if (d7 * d7 + d9 * d9 >= paramDouble1 * paramDouble1 + paramDouble3 * paramDouble3) {
                paramDouble1 = d7;
                paramDouble2 = d8;
                paramDouble3 = d9;
                z.b(localOAxisAlignedBB2);
            } else
                R = (float) (R + 0.5D);

        }

        p = ((z.a + z.d) / 2.0D);
        q = (z.b + H - R);
        r = ((z.c + z.f) / 2.0D);

        B = ((d3 != paramDouble1) || (d5 != paramDouble3));
        C = (d4 != paramDouble2);
        A = ((d4 != paramDouble2) && (d4 < 0.0D));
        D = ((B) || (C));
        a(paramDouble2, A);

        if (d3 != paramDouble1)
            s = 0.0D;
        if (d4 != paramDouble2)
            t = 0.0D;
        if (d5 != paramDouble3)
            u = 0.0D;

        double d7 = p - d1;
        double d8 = r - d2;

        if ((M) && (i1 == 0)) {
            L = (float) (L + OMathHelper.a(d7 * d7 + d8 * d8) * 0.6D);
            int i6 = OMathHelper.b(p);
            int i7 = OMathHelper.b(q - 0.2000000029802322D - H);
            int i4 = OMathHelper.b(r);
            int i5 = l.a(i6, i7, i4);
            if ((L > b) && (i5 > 0)) {
                b += 1;
                OStepSound localOStepSound = OBlock.m[i5].br;
                if (l.a(i6, i7 + 1, i4) == OBlock.aS.bi) {
                    localOStepSound = OBlock.aS.br;
                    l.a(this, localOStepSound.c(), localOStepSound.a() * 0.15F, localOStepSound.b());
                } else if (!OBlock.m[i5].bt.d())
                    l.a(this, localOStepSound.c(), localOStepSound.a() * 0.15F, localOStepSound.b());
                OBlock.m[i5].b(l, i6, i7, i4, this);
            }

        }

        int i6 = OMathHelper.b(z.a);
        int i7 = OMathHelper.b(z.b);
        int i4 = OMathHelper.b(z.c);
        int i5 = OMathHelper.b(z.d);
        int i8 = OMathHelper.b(z.e);
        int i9 = OMathHelper.b(z.f);

        if (l.a(i6, i7, i4, i5, i8, i9))
            for (int i10 = i6; i10 <= i5; i10++)
                for (int i11 = i7; i11 <= i8; i11++)
                    for (int i12 = i4; i12 <= i9; i12++) {
                        int i13 = l.a(i10, i11, i12);
                        if (i13 > 0)
                            OBlock.m[i13].a(l, i10, i11, i12, this);
                    }
        R *= 0.4F;

        boolean bool = v();
        if (l.c(z)) {
            b(1);
            if (!bool) {
                Z += 1;
                if (Z == 0)
                    Z = 300;
            }
        } else if (Z <= 0)
            Z = (-Y);

        if ((bool) && (Z > 0)) {
            l.a(this, "random.fizz", 0.7F, 1.6F + (W.nextFloat() - W.nextFloat()) * 0.4F);
            Z = (-Y);
        }
    }

    protected void a(double paramDouble, boolean paramBoolean) {
        if (paramBoolean) {
            if (N > 0.0F) {
                a(N);
                N = 0.0F;
            }
        } else if (paramDouble < 0.0D)
            N = (float) (N - paramDouble);
    }

    public OAxisAlignedBB u() {
        return null;
    }

    protected void b(int paramInt) {
        if (!ae)
            if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FIRE, null, entity, paramInt))
                a((OEntity) null, paramInt);
    }

    protected void a(float paramFloat) {
    }

    public boolean v() {
        return l.a(z.b(0.0D, -0.4000000059604645D, 0.0D), OMaterial.f, this);
    }

    public boolean a(OMaterial paramOMaterial) {
        double d1 = q + w();
        int i1 = OMathHelper.b(p);
        int i2 = OMathHelper.d(OMathHelper.b(d1));
        int i3 = OMathHelper.b(r);
        int i4 = l.a(i1, i2, i3);
        if ((i4 != 0) && (OBlock.m[i4].bt == paramOMaterial)) {
            float f1 = OBlockFluids.c(l.b(i1, i2, i3)) - 0.1111111F;
            float f2 = i2 + 1 - f1;
            return d1 < f2;
        }
        return false;
    }

    public float w() {
        return 0.0F;
    }

    public boolean x() {
        return l.a(z.b(-0.1000000014901161D, -0.4000000059604645D, -0.1000000014901161D), OMaterial.g);
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

        float f2 = OMathHelper.a(v * 3.141593F / 180.0F);
        float f3 = OMathHelper.b(v * 3.141593F / 180.0F);

        s += paramFloat1 * f3 - paramFloat2 * f2;
        u += paramFloat2 * f3 + paramFloat1 * f2;
    }

    public float b(float paramFloat) {
        int i1 = OMathHelper.b(p);

        double d1 = (z.e - z.b) * 0.66D;
        int i2 = OMathHelper.b(q - H + d1);
        int i3 = OMathHelper.b(r);
        if (l.a(OMathHelper.b(z.a), OMathHelper.b(z.b), OMathHelper.b(z.c), OMathHelper.b(z.d), OMathHelper.b(z.e), OMathHelper.b(z.f)))
            return l.l(i1, i2, i3);
        return 0.0F;
    }

    public void b(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {
        m = (p = paramDouble1);
        n = (q = paramDouble2);
        o = (r = paramDouble3);
        x = (v = paramFloat1);
        y = (w = paramFloat2);
        R = 0.0F;

        double d1 = x - paramFloat1;
        if (d1 < -180.0D)
            x += 360.0F;
        if (d1 >= 180.0D)
            x -= 360.0F;
        a(p, q, r);
        b(paramFloat1, paramFloat2);
    }

    public void c(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {
        O = (m = p = paramDouble1);
        P = (n = q = paramDouble2 + H);
        Q = (o = r = paramDouble3);
        v = paramFloat1;
        w = paramFloat2;
        a(p, q, r);
    }

    public float a(OEntity paramOEntity) {
        float f1 = (float) (p - paramOEntity.p);
        float f2 = (float) (q - paramOEntity.q);
        float f3 = (float) (r - paramOEntity.r);
        return OMathHelper.c(f1 * f1 + f2 * f2 + f3 * f3);
    }

    public double d(double paramDouble1, double paramDouble2, double paramDouble3) {
        double d1 = p - paramDouble1;
        double d2 = q - paramDouble2;
        double d3 = r - paramDouble3;
        return d1 * d1 + d2 * d2 + d3 * d3;
    }

    public double e(double paramDouble1, double paramDouble2, double paramDouble3) {
        double d1 = p - paramDouble1;
        double d2 = q - paramDouble2;
        double d3 = r - paramDouble3;
        return OMathHelper.a(d1 * d1 + d2 * d2 + d3 * d3);
    }

    public double b(OEntity paramOEntity) {
        double d1 = p - paramOEntity.p;
        double d2 = q - paramOEntity.q;
        double d3 = r - paramOEntity.r;
        return d1 * d1 + d2 * d2 + d3 * d3;
    }

    public void b(OEntityPlayer paramOEntityPlayer) {
    }

    public void c(OEntity paramOEntity) {
        if ((paramOEntity.j == this) || (paramOEntity.k == this))
            return;

        double d1 = paramOEntity.p - p;
        double d2 = paramOEntity.r - r;

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

            d1 *= (1.0F - U);
            d2 *= (1.0F - U);

            f(-d1, 0.0D, -d2);
            paramOEntity.f(d1, 0.0D, d2);
        }
    }

    public void f(double paramDouble1, double paramDouble2, double paramDouble3) {
        s += paramDouble1;
        t += paramDouble2;
        u += paramDouble3;
    }

    protected void y() {
        E = true;
    }

    public boolean a(OEntity paramOEntity, int paramInt) {
        y();
        return false;
    }

    public boolean c_() {
        return false;
    }

    public boolean z() {
        return false;
    }

    public void b(OEntity paramOEntity, int paramInt) {
    }

    public boolean c(ONBTTagCompound paramONBTTagCompound) {
        String str = A();
        if ((G) || (str == null))
            return false;
        paramONBTTagCompound.a("id", str);
        d(paramONBTTagCompound);
        return true;
    }

    public void d(ONBTTagCompound paramONBTTagCompound) {
        paramONBTTagCompound.a("Pos", a(new double[] { p, q, r }));
        paramONBTTagCompound.a("Motion", a(new double[] { s, t, u }));
        paramONBTTagCompound.a("Rotation", a(new float[] { v, w }));

        paramONBTTagCompound.a("FallDistance", N);
        paramONBTTagCompound.a("Fire", (short) Z);
        paramONBTTagCompound.a("Air", (short) ad);
        paramONBTTagCompound.a("OnGround", A);

        a(paramONBTTagCompound);
    }

    public void e(ONBTTagCompound paramONBTTagCompound) {
        ONBTTagList localONBTTagList1 = paramONBTTagCompound.k("Pos");
        ONBTTagList localONBTTagList2 = paramONBTTagCompound.k("Motion");
        ONBTTagList localONBTTagList3 = paramONBTTagCompound.k("Rotation");
        a(0.0D, 0.0D, 0.0D);

        s = ((ONBTTagDouble) localONBTTagList2.a(0)).a;
        t = ((ONBTTagDouble) localONBTTagList2.a(1)).a;
        u = ((ONBTTagDouble) localONBTTagList2.a(2)).a;

        m = (O = p = ((ONBTTagDouble) localONBTTagList1.a(0)).a);
        n = (P = q = ((ONBTTagDouble) localONBTTagList1.a(1)).a);
        o = (Q = r = ((ONBTTagDouble) localONBTTagList1.a(2)).a);

        x = (v = ((ONBTTagFloat) localONBTTagList3.a(0)).a);
        y = (w = ((ONBTTagFloat) localONBTTagList3.a(1)).a);

        N = paramONBTTagCompound.f("FallDistance");
        Z = paramONBTTagCompound.c("Fire");
        ad = paramONBTTagCompound.c("Air");
        A = paramONBTTagCompound.l("OnGround");

        a(p, q, r);

        b(paramONBTTagCompound);
    }

    protected final String A() {
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

    public OEntityItem a(int paramInt1, int paramInt2) {
        return a(paramInt1, paramInt2, 0.0F);
    }

    public OEntityItem a(int paramInt1, int paramInt2, float paramFloat) {
        return a(new OItemStack(paramInt1, paramInt2, 0), paramFloat);
    }

    public OEntityItem a(OItemStack paramOItemStack, float paramFloat) {
        OEntityItem localOEntityItem = new OEntityItem(l, p, q + paramFloat, r, paramOItemStack);
        localOEntityItem.c = 10;
        l.a(localOEntityItem);
        return localOEntityItem;
    }

    public boolean B() {
        return !G;
    }

    public boolean C() {
        int i1 = OMathHelper.b(p);
        int i2 = OMathHelper.b(q + w());
        int i3 = OMathHelper.b(r);
        return l.d(i1, i2, i3);
    }

    public boolean a(OEntityPlayer paramOEntityPlayer) {
        return false;
    }

    public OAxisAlignedBB d(OEntity paramOEntity) {
        return null;
    }

    public void D() {
        if (k.G) {
            k = null;
            return;
        }
        s = 0.0D;
        t = 0.0D;
        u = 0.0D;
        b_();
        k.E();

        e += k.v - k.x;
        d += k.w - k.y;

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

        v = (float) (v + d1);
        w = (float) (w + d2);
    }

    public void E() {
        j.a(p, q + k() + j.F(), r);
    }

    public double F() {
        return H;
    }

    public double k() {
        return J * 0.75D;
    }

    public void e(OEntity paramOEntity) {
        d = 0.0D;
        e = 0.0D;

        if (paramOEntity == null) {
            if (k != null) {
                c(k.p, k.z.b + k.J, k.r, v, w);
                k.j = null;
            }
            k = null;
            return;
        }
        if (k == paramOEntity) {
            k.j = null;
            k = null;
            c(paramOEntity.p, paramOEntity.z.b + paramOEntity.J, paramOEntity.r, v, w);
            return;
        }
        if (k != null)
            k.j = null;
        if (paramOEntity.j != null)
            paramOEntity.j.k = null;
        k = paramOEntity;
        paramOEntity.j = this;
    }

    public OVec3D G() {
        return null;
    }

    public void H() {
    }

    public OItemStack[] I() {
        return null;
    }

    public boolean J() {
        return c(1);
    }

    public void b(boolean paramBoolean) {
        a(1, paramBoolean);
    }

    protected boolean c(int paramInt) {
        return (af.a(0) & 1 << paramInt) != 0;
    }

    protected void a(int paramInt, boolean paramBoolean) {
        int i1 = af.a(0);
        if (paramBoolean)
            af.b(0, Byte.valueOf((byte) (i1 | 1 << paramInt)));
        else
            af.b(0, Byte.valueOf((byte) (i1 & (1 << paramInt ^ 0xFFFFFFFF))));
    }
}
