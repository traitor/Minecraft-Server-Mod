import java.util.List;

public class OEntityBoat extends OEntity {

    public int     a    = 0;
    public int     b    = 0;
    public int     c    = 1;
    private int    d;
    private double e;
    private double f;
    private double ak;
    private double al;
    private double am;

    // hMod Start
    Boat           boat = new Boat(this);
    // hMod end

    public OEntityBoat(OWorld paramOWorld) {
        super(paramOWorld);
        i = true;
        a(1.5F, 0.6F);
        H = (J / 2.0F);
        M = false;
    }

    @Override
    protected void a() {
    }

    @Override
    public OAxisAlignedBB d(OEntity paramOEntity) {
        return paramOEntity.z;
    }

    @Override
    public OAxisAlignedBB u() {
        return z;
    }

    @Override
    public boolean z() {
        return true;
    }

    public OEntityBoat(OWorld paramOWorld, double paramDouble1, double paramDouble2, double paramDouble3) {
        this(paramOWorld);
        a(paramDouble1, paramDouble2 + H, paramDouble3);

        s = 0.0D;
        t = 0.0D;
        u = 0.0D;

        m = paramDouble1;
        n = paramDouble2;
        o = paramDouble3;
        // hMod: Creation of the boat
        manager.callHook(PluginLoader.Hook.VEHICLE_CREATE, boat);
    }

    @Override
    public double k() {
        return J * 0.0D - 0.300000011920929D;
    }

    @Override
    public boolean a(OEntity paramOEntity, int paramInt) {
        // hMod: Attack of the boat
        if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_DAMAGE, boat, paramOEntity == null ? null : paramOEntity.entity, paramInt))
            return true;

        if ((l.z) || (G))
            return true;
        c = (-c);
        b = 10;
        a += paramInt * 10;
        y();
        if (a > 40) {
            for (int i = 0; i < 3; i++)
                a(OBlock.x.bi, 1, 0.0F);
            for (int i = 0; i < 2; i++)
                a(OItem.B.ba, 1, 0.0F);
            q();
        }
        return true;
    }

    @Override
    public boolean c_() {
        return !G;
    }

    @Override
    public void b_() {
        // hMod: Update of the boat
        manager.callHook(PluginLoader.Hook.VEHICLE_UPDATE, boat);

        super.b_();
        if (b > 0)
            b -= 1;
        if (a > 0)
            a -= 1;
        m = p;
        n = q;
        o = r;

        int i = 5;
        double d1 = 0.0D;
        for (int j = 0; j < i; j++) {
            double d2 = z.b + (z.e - z.b) * (j + 0) / i - 0.125D;
            double d3 = z.b + (z.e - z.b) * (j + 1) / i - 0.125D;
            OAxisAlignedBB localOAxisAlignedBB = OAxisAlignedBB.b(z.a, d2, z.c, z.d, d3, z.f);
            if (l.b(localOAxisAlignedBB, OMaterial.f))
                d1 += 1.0D / i;
        }
        double d4, d5, d6, d7, d8;
        if (l.z) {
            if (d > 0) {
                d4 = p + (e - p) / d;
                d5 = q + (f - q) / d;
                d6 = r + (ak - r) / d;

                d7 = al - v;
                while (d7 < -180.0D)
                    d7 += 360.0D;
                while (d7 >= 180.0D)
                    d7 -= 360.0D;
                v = (float) (v + d7 / d);
                w = (float) (w + (am - w) / d);

                d -= 1;
                a(d4, d5, d6);
                b(v, w);
            } else {
                d4 = p + s;
                d5 = q + t;
                d6 = r + u;
                a(d4, d5, d6);
                if (A) {
                    s *= 0.5D;
                    t *= 0.5D;
                    u *= 0.5D;
                }
                s *= 0.9900000095367432D;
                t *= 0.949999988079071D;
                u *= 0.9900000095367432D;
            }
            return;
        }

        d4 = d1 * 2.0D - 1.0D;
        t += 0.03999999910593033D * d4;

        if (j != null) {
            s += j.s * 0.2D;
            u += j.u * 0.2D;
        }

        d5 = 0.4D;

        if (s < -d5)
            s = (-d5);
        if (s > d5)
            s = d5;
        if (u < -d5)
            u = (-d5);
        if (u > d5)
            u = d5;
        if (A) {
            s *= 0.5D;
            t *= 0.5D;
            u *= 0.5D;
        }
        c(s, t, u);
        d6 = Math.sqrt(s * s + u * u);
        if (d6 > 0.15D) {
            d7 = Math.cos(v * 3.141592653589793D / 180.0D);
            d8 = Math.sin(v * 3.141592653589793D / 180.0D);

            for (int k = 0; k < 1.0D + d6 * 60.0D; k++) {
                double d9 = W.nextFloat() * 2.0F - 1.0F;

                double d10 = (W.nextInt(2) * 2 - 1) * 0.7D;
                double d11;
                double d12;
                if (W.nextBoolean()) {
                    d11 = p - d7 * d9 * 0.8D + d8 * d10;
                    d12 = r - d8 * d9 * 0.8D - d7 * d10;
                    l.a("splash", d11, q - 0.125D, d12, s, t, u);
                } else {
                    d11 = p + d7 + d8 * d9 * 0.7D;
                    d12 = r + d8 - d7 * d9 * 0.7D;
                    l.a("splash", d11, q - 0.125D, d12, s, t, u);
                }
            }
        }

        if ((B) && (d6 > 0.15D)) {
            if (!l.z) {
                q();
                for (int m = 0; m < 3; m++)
                    a(OBlock.x.bi, 1, 0.0F);
                for (m = 0; m < 2; m++)
                    a(OItem.B.ba, 1, 0.0F);
            }
        } else {
            s *= 0.9900000095367432D;
            t *= 0.949999988079071D;
            u *= 0.9900000095367432D;
        }

        w = 0.0F;
        d7 = v;
        d8 = m - p;
        double d13 = o - r;
        if (d8 * d8 + d13 * d13 > 0.001D)
            d7 = (float) (Math.atan2(d13, d8) * 180.0D / 3.141592653589793D);

        double d14 = d7 - v;
        while (d14 >= 180.0D)
            d14 -= 360.0D;
        while (d14 < -180.0D)
            d14 += 360.0D;
        if (d14 > 20.0D)
            d14 = 20.0D;
        if (d14 < -20.0D)
            d14 = -20.0D;

        v = (float) (v + d14);
        b(v, w);

        List<?> localList = l.b(this, z.b(0.2000000029802322D, 0.0D, 0.2000000029802322D));
        if ((localList != null) && (localList.size() > 0))
            for (int n = 0; n < localList.size(); n++) {
                OEntity localOEntity = (OEntity) localList.get(n);
                if ((localOEntity != j) && (localOEntity.z()) && ((localOEntity instanceof OEntityBoat)))
                    localOEntity.c(this);
            }

        if ((j != null) && (j.G))
            j = null;
    }

    @Override
    public void E() {
        if (j == null)
            return;

        double d1 = Math.cos(v * 3.141592653589793D / 180.0D) * 0.4D;
        double d2 = Math.sin(v * 3.141592653589793D / 180.0D) * 0.4D;
        j.a(p + d1, q + k() + j.F(), r + d2);
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

        if ((j != null) && ((j instanceof OEntityPlayer)) && (j != paramOEntityPlayer))
            return true;
        if (!l.z)
            paramOEntityPlayer.e(this);
        return true;
    }
}
