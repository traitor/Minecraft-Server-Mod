import java.util.List;

public class OEntityBoat extends OEntity {
    public int     a = 0;
    public int     b = 0;
    public int     c = 1;
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
        aD = true;
        b(1.5F, 0.6F);
        bc = (be / 2.0F);
    }

    @Override
    protected boolean l() {
        return false;
    }

    @Override
    protected void a() {
    }

    @Override
    public OAxisAlignedBB a_(OEntity paramOEntity) {
        return paramOEntity.aU;
    }

    @Override
    public OAxisAlignedBB d() {
        return aU;
    }

    @Override
    public boolean e_() {
        return true;
    }

    public OEntityBoat(OWorld paramOWorld, double paramDouble1, double paramDouble2, double paramDouble3) {
        this(paramOWorld);
        a(paramDouble1, paramDouble2 + bc, paramDouble3);

        aN = 0.0D;
        aO = 0.0D;
        aP = 0.0D;

        aH = paramDouble1;
        aI = paramDouble2;
        aJ = paramDouble3;
    }

    @Override
    public double k() {
        return be * 0.0D - 0.300000011920929D;
    }

    @Override
    public boolean a(OEntity paramOEntity, int paramInt) {
     // hMod: Attack of the boat
        if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_DAMAGE, boat, paramOEntity == null ? null : paramOEntity.entity, paramInt))
            return true;


        
        if ((aG.t) || (bb))
            return true;
        c = (-c);
        b = 10;
        a += paramInt * 10;
        W();
        if (a > 40) {
            for (int j = 0; j < 3; j++)
                a(OBlock.x.bl, 1, 0.0F);
            for (int j = 0; j < 2; j++)
                a(OItem.B.bd, 1, 0.0F);
            D();
        }
        return true;
    }

    @Override
    public boolean d_() {
        return !bb;
    }

    @Override
    public void f_() {
        super.f_();
        
     // hMod: Update of the boat
        manager.callHook(PluginLoader.Hook.VEHICLE_UPDATE, boat);

        double prevX = aK;
        double prevY = aL;
        double prevZ = aM;
        
        if (b > 0)
            b -= 1;
        if (a > 0)
            a -= 1;
        aH = aK;
        aI = aL;
        aJ = aM;

        int j = 5;
        double d1 = 0.0D;
        for (int k = 0; k < j; k++) {
            double d2 = aU.b + (aU.e - aU.b) * (k + 0) / j - 0.125D;
            double d3 = aU.b + (aU.e - aU.b) * (k + 1) / j - 0.125D;
            OAxisAlignedBB localOAxisAlignedBB = OAxisAlignedBB.b(aU.a, d2, aU.c, aU.d, d3, aU.f);
            if (aG.b(localOAxisAlignedBB, OMaterial.f))
                d1 += 1.0D / j;
        }

        if (aG.t) {
            if (d > 0) {
                double d4 = aK + (e - aK) / d;
                double d5 = aL + (f - aL) / d;
                double d6 = aM + (g - aM) / d;

                double d7 = h - aQ;
                while (d7 < -180.0D)
                    d7 += 360.0D;
                while (d7 >= 180.0D)
                    d7 -= 360.0D;
                aQ = (float) (aQ + d7 / d);
                aR = (float) (aR + (i - aR) / d);

                d -= 1;
                a(d4, d5, d6);
                c(aQ, aR);
            } else {
                double d4 = aK + aN;
                double d5 = aL + aO;
                double d6 = aM + aP;
                a(d4, d5, d6);
                if (aV) {
                    aN *= 0.5D;
                    aO *= 0.5D;
                    aP *= 0.5D;
                }
                aN *= 0.9900000095367432D;
                aO *= 0.949999988079071D;
                aP *= 0.9900000095367432D;
            }
            return;
        }

        double d4 = d1 * 2.0D - 1.0D;
        aO += 0.03999999910593033D * d4;

        if (aE != null) {
            aN += aE.aN * 0.2D;
            aP += aE.aP * 0.2D;
        }

        double d5 = 0.4D;

        if (aN < -d5)
            aN = (-d5);
        if (aN > d5)
            aN = d5;
        if (aP < -d5)
            aP = (-d5);
        if (aP > d5)
            aP = d5;
        if (aV) {
            aN *= 0.5D;
            aO *= 0.5D;
            aP *= 0.5D;
        }
        c(aN, aO, aP);
        double d6 = Math.sqrt(aN * aN + aP * aP);
        if (d6 > 0.15D) {
            double d7 = Math.cos(aQ * 3.141592653589793D / 180.0D);
            double d8 = Math.sin(aQ * 3.141592653589793D / 180.0D);

            for (int m = 0; m < 1.0D + d6 * 60.0D; m++) {
                double d9 = bq.nextFloat() * 2.0F - 1.0F;

                double d10 = (bq.nextInt(2) * 2 - 1) * 0.7D;
                double d11;
                double d12;
                if (bq.nextBoolean()) {
                    d11 = aK - d7 * d9 * 0.8D + d8 * d10;
                    d12 = aM - d8 * d9 * 0.8D - d7 * d10;
                    aG.a("splash", d11, aL - 0.125D, d12, aN, aO, aP);
                } else {
                    d11 = aK + d7 + d8 * d9 * 0.7D;
                    d12 = aM + d8 - d7 * d9 * 0.7D;
                    aG.a("splash", d11, aL - 0.125D, d12, aN, aO, aP);
                }
            }
        }

        if ((aW) && (d6 > 0.15D)) {
            if (!aG.t) {
                D();
                for (int n = 0; n < 3; n++)
                    a(OBlock.x.bl, 1, 0.0F);
                for (int n = 0; n < 2; n++)
                    a(OItem.B.bd, 1, 0.0F);
            }
        } else {
            aN *= 0.9900000095367432D;
            aO *= 0.949999988079071D;
            aP *= 0.9900000095367432D;
        }

        aR = 0.0F;
        double d7 = aQ;
        double d8 = aH - aK;
        double d13 = aJ - aM;
        if (d8 * d8 + d13 * d13 > 0.001D)
            d7 = (float) (Math.atan2(d13, d8) * 180.0D / 3.141592653589793D);

        double d14 = d7 - aQ;
        while (d14 >= 180.0D)
            d14 -= 360.0D;
        while (d14 < -180.0D)
            d14 += 360.0D;
        if (d14 > 20.0D)
            d14 = 20.0D;
        if (d14 < -20.0D)
            d14 = -20.0D;

        aQ = (float) (aQ + d14);
        c(aQ, aR);

     // hMod: Change of the cart
        if (aK != prevX || aL != prevY || aM != prevZ)
            manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, boat, (int) aK, (int) aL, (int) aM);

        
        List localList = aG.b(this, aU.b(0.2000000029802322D, 0.0D, 0.2000000029802322D));
        if ((localList != null) && (localList.size() > 0))
            for (int i1 = 0; i1 < localList.size(); i1++) {
                OEntity localOEntity = (OEntity) localList.get(i1);
                if ((localOEntity != aE) && (localOEntity.e_()) && ((localOEntity instanceof OEntityBoat)))
                    localOEntity.h(this);
            }

        if ((aE != null) && (aE.bb))
            aE = null;
    }

    @Override
    public void h_() {
        if (aE == null)
            return;

        double d1 = Math.cos(aQ * 3.141592653589793D / 180.0D) * 0.4D;
        double d2 = Math.sin(aQ * 3.141592653589793D / 180.0D) * 0.4D;
        aE.a(aK + d1, aL + k() + aE.C(), aM + d2);
    }

    @Override
    protected void a(ONBTTagCompound paramONBTTagCompound) {
    }

    @Override
    protected void b(ONBTTagCompound paramONBTTagCompound) {
    }

    @Override
    public boolean a(OEntityPlayer paramOEntityPlayer) {
     // hMod: Entering the boat
        manager.callHook(PluginLoader.Hook.VEHICLE_ENTERED, boat, paramOEntityPlayer.entity);

        
        if ((aE != null) && ((aE instanceof OEntityPlayer)) && (aE != paramOEntityPlayer))
            return true;
        if (!aG.t)
            paramOEntityPlayer.b(this);
        return true;
    }
}