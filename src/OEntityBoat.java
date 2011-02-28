import java.util.List;
import java.util.Random;

public class OEntityBoat extends OEntity {

    public int     a    = 0;
    public int     b    = 0;
    public int     c    = 1;
    private int    d;
    private double e;
    private double f;
    private double g;
    private double h;
    private double i;

    // hMod Start
    Boat           boat = new Boat(this);

    // hMod end

    public OEntityBoat(OWorld paramOWorld) {
        super(paramOWorld);
        aC = true;
        a(1.5F, 0.6F);
        bb = (bd / 2.0F);
        bg = false;
    }

    protected void a() {
    }

    public OAxisAlignedBB a_(OEntity paramOEntity) {
        return paramOEntity.aT;
    }

    public OAxisAlignedBB d() {
        return aT;
    }

    public boolean e_() {
        return true;
    }

    public OEntityBoat(OWorld paramOWorld, double paramDouble1, double paramDouble2, double paramDouble3) {
        this(paramOWorld);
        a(paramDouble1, paramDouble2 + bb, paramDouble3);

        aM = 0.0D;
        aN = 0.0D;
        aO = 0.0D;

        aG = paramDouble1;
        aH = paramDouble2;
        aI = paramDouble3;

        // hMod: Creation of the boat
        manager.callHook(PluginLoader.Hook.VEHICLE_CREATE, boat);
    }

    @Override
    public double k() {
        return bd * 0.0D - 0.300000011920929D;
    }

    @Override
    public boolean a(OEntity paramOEntity, int paramInt) {
        // hMod: Attack of the boat
        if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_DAMAGE, boat, paramOEntity == null ? null : paramOEntity.entity, paramInt))
            return true;

        if ((aF.t) || (ba)) {
            return true;
        }
        c = (-c);
        b = 10;
        a += paramInt * 10;
        R();
        if (a > 40) {
            for (int j = 0; j < 3; j++) {
                a(OBlock.x.bk, 1, 0.0F);
            }
            for (int j = 0; j < 2; j++) {
                a(OItem.B.bc, 1, 0.0F);
            }
            C();
        }
        return true;
    }

    @Override
    public boolean d_() {
        return !ba;
    }

    @Override
    public void f_() {
        // hMod: Update of the boat
        manager.callHook(PluginLoader.Hook.VEHICLE_UPDATE, boat);

        super.f_();
        if (b > 0) {
            b -= 1;
        }
        if (a > 0) {
            a -= 1;
        }
        aG = aJ;
        aH = aK;
        aI = aL;

        int j = 5;
        double d1 = 0.0D;
        for (int k = 0; k < j; k++) {
            double d2 = aT.b + (aT.e - aT.b) * (k + 0) / j - 0.125D;
            double d3 = aT.b + (aT.e - aT.b) * (k + 1) / j - 0.125D;
            OAxisAlignedBB localOAxisAlignedBB = OAxisAlignedBB.b(aT.a, d2, aT.c, aT.d, d3, aT.f);
            if (aF.b(localOAxisAlignedBB, OMaterial.f)) {
                d1 += 1.0D / j;
            }
        }

        if (aF.t) {
            if (d > 0) {
                double d4 = aJ + (e - aJ) / d;
                double d5 = aK + (f - aK) / d;
                double d6 = aL + (g - aL) / d;

                double d7 = h - aP;
                while (d7 < -180.0D) {
                    d7 += 360.0D;
                }
                while (d7 >= 180.0D) {
                    d7 -= 360.0D;
                }
                aP = (float) (aP + d7 / d);
                aQ = (float) (aQ + (i - aQ) / d);

                d -= 1;
                a(d4, d5, d6);
                c(aP, aQ);
            } else {
                double d4 = aJ + aM;
                double d5 = aK + aN;
                double d6 = aL + aO;
                a(d4, d5, d6);
                if (aU) {
                    aM *= 0.5D;
                    aN *= 0.5D;
                    aO *= 0.5D;
                }
                aM *= 0.9900000095367432D;
                aN *= 0.949999988079071D;
                aO *= 0.9900000095367432D;
            }
            return;
        }

        double d4 = d1 * 2.0D - 1.0D;
        aN += 0.03999999910593033D * d4;

        if (aD != null) {
            aM += aD.aM * 0.2D;
            aO += aD.aO * 0.2D;
        }

        double d5 = 0.4D;

        if (aM < -d5) {
            aM = (-d5);
        }
        if (aM > d5) {
            aM = d5;
        }
        if (aO < -d5) {
            aO = (-d5);
        }
        if (aO > d5) {
            aO = d5;
        }
        if (aU) {
            aM *= 0.5D;
            aN *= 0.5D;
            aO *= 0.5D;
        }
        c(aM, aN, aO);
        double d6 = Math.sqrt(aM * aM + aO * aO);
        if (d6 > 0.15D) {
            double d7 = Math.cos(aP * 3.141592653589793D / 180.0D);
            double d8 = Math.sin(aP * 3.141592653589793D / 180.0D);

            for (int m = 0; m < 1.0D + d6 * 60.0D; m++) {
                double d9 = bq.nextFloat() * 2.0F - 1.0F;

                double d10 = (bq.nextInt(2) * 2 - 1) * 0.7D;
                double d11;
                double d12;
                if (bq.nextBoolean()) {
                    d11 = aJ - d7 * d9 * 0.8D + d8 * d10;
                    d12 = aL - d8 * d9 * 0.8D - d7 * d10;
                    aF.a("splash", d11, aK - 0.125D, d12, aM, aN, aO);
                } else {
                    d11 = aJ + d7 + d8 * d9 * 0.7D;
                    d12 = aL + d8 - d7 * d9 * 0.7D;
                    aF.a("splash", d11, aK - 0.125D, d12, aM, aN, aO);
                }
            }
        }

        if ((aV) && (d6 > 0.15D)) {
            if (!aF.t) {
                C();
                for (int n = 0; n < 3; n++) {
                    a(OBlock.x.bk, 1, 0.0F);
                }
                for (int n = 0; n < 2; n++) {
                    a(OItem.B.bc, 1, 0.0F);
                }
            }
        } else {
            aM *= 0.9900000095367432D;
            aN *= 0.949999988079071D;
            aO *= 0.9900000095367432D;
        }

        aQ = 0.0F;
        double d7 = aP;
        double d8 = aG - aJ;
        double d13 = aI - aL;
        if (d8 * d8 + d13 * d13 > 0.001D) {
            d7 = (float) (Math.atan2(d13, d8) * 180.0D / 3.141592653589793D);
        }

        double d14 = d7 - aP;
        while (d14 >= 180.0D) {
            d14 -= 360.0D;
        }
        while (d14 < -180.0D) {
            d14 += 360.0D;
        }
        if (d14 > 20.0D) {
            d14 = 20.0D;
        }
        if (d14 < -20.0D) {
            d14 = -20.0D;
        }

        aP = (float) (aP + d14);
        c(aP, aQ);

        List localList = aF.b(this, aT.b(0.2000000029802322D, 0.0D, 0.2000000029802322D));
        if ((localList != null) && (localList.size() > 0)) {
            for (int i1 = 0; i1 < localList.size(); i1++) {
                OEntity localOEntity = (OEntity) localList.get(i1);
                if ((localOEntity != aD) && (localOEntity.e_()) && ((localOEntity instanceof OEntityBoat))) {
                    localOEntity.h(this);
                }
            }
        }

        if ((aD != null) && (aD.ba)) {
            aD = null;
        }
    }

    @Override
    public void h_() {
        if (aD == null) {
            return;
        }

        double d1 = Math.cos(aP * 3.141592653589793D / 180.0D) * 0.4D;
        double d2 = Math.sin(aP * 3.141592653589793D / 180.0D) * 0.4D;
        aD.a(aJ + d1, aK + k() + aD.B(), aL + d2);
    }

    protected void a(ONBTTagCompound paramONBTTagCompound) {
    }

    protected void b(ONBTTagCompound paramONBTTagCompound) {
    }

    @Override
    public boolean a(OEntityPlayer paramOEntityPlayer) {
        // hMod: Entering the boat
        manager.callHook(PluginLoader.Hook.VEHICLE_ENTERED, boat, paramOEntityPlayer.entity);

        if ((aD != null) && ((aD instanceof OEntityPlayer)) && (aD != paramOEntityPlayer)) {
            return true;
        }
        if (!aF.t) {
            paramOEntityPlayer.b(this);
        }
        return true;
    }
}
