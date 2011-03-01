import java.util.List;
import net.minecraft.server.MinecraftServer;

public class OEntityMinecart extends OEntity implements OIInventory, Container<OItemStack> {

    private OItemStack[]           h    = new OItemStack[36];
    public int                     a    = 0;
    public int                     b    = 0;
    public int                     c    = 1;
    private boolean                i    = false;
    public int                     d;
    public int                     e;
    public double                  f;
    public double                  g;
    private static final int[][][] j    = { { { 0, 0, -1 }, { 0, 0, 1 } }, { { -1, 0, 0 }, { 1, 0, 0 } }, { { -1, -1, 0 }, { 1, 0, 0 } }, { { -1, 0, 0 }, { 1, -1, 0 } }, { { 0, 0, -1 }, { 0, -1, 1 } }, { { 0, -1, -1 }, { 0, 0, 1 } }, { { 0, 0, 1 }, { 1, 0, 0 } }, { { 0, 0, 1 }, { -1, 0, 0 } }, { { 0, 0, -1 }, { -1, 0, 0 } }, { { 0, 0, -1 }, { 1, 0, 0 } } };
    private int                    k;
    private double                 l;
    private double                 m;
    private double                 n;
    private double                 o;
    private double                 p;
    private String                 name = "Minecart";

    // hMod start
    Minecart                       cart = new Minecart(this);

    // hMod end

    @Override
    public OItemStack[] getContents() {
        return h;
    }

    @Override
    public void setContents(OItemStack[] values) {
        h = values;
    }

    public OItemStack getContentsAt(int index) {
        return c_(index);
    }

    public void setContentsAt(int index, OItemStack value) {
        a(index, value);
    }

    public int getContentsSize() {
        return m_();
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        name = value;
    }

    public OEntityMinecart(OWorld paramOWorld) {
        super(paramOWorld);
        aC = true;
        a(0.98F, 0.7F);
        bb = (bd / 2.0F);
        bg = false;
    }

    protected void a() {
    }

    @Override
    public OAxisAlignedBB a_(OEntity paramOEntity) {
        return paramOEntity.aT;
    }

    public OAxisAlignedBB d() {
        return null;
    }

    public boolean e_() {
        return true;
    }

    public OEntityMinecart(OWorld paramOWorld, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt) {
        this(paramOWorld);
        a(paramDouble1, paramDouble2 + bb, paramDouble3);

        aM = 0.0D;
        aN = 0.0D;
        aO = 0.0D;

        aG = paramDouble1;
        aH = paramDouble2;
        aI = paramDouble3;
        d = paramInt;

        // hMod: Creation of the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_CREATE, cart);
    }

    public double k() {
        return bd * 0.0D - 0.300000011920929D;
    }

    public boolean a(OEntity paramOEntity, int paramInt) {
        // hMod: Attack of the cart
        if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_DAMAGE, cart, paramOEntity == null ? null : paramOEntity.entity, paramInt))
            return true;

        if ((aF.t) || (ba)) {
            return true;
        }
        c = (-c);
        b = 10;
        R();
        a += paramInt * 10;
        if (a > 40) {
            a(OItem.ax.bc, 1, 0.0F);
            if (d == 1) {
                a(OBlock.au.bk, 1, 0.0F);
            } else if (d == 2) {
                a(OBlock.aB.bk, 1, 0.0F);
            }
            C();
        }
        return true;
    }

    public boolean d_() {
        return !ba;
    }

    public void C() {
        // hMod: Destruction of the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_DESTROYED, cart);

        for (int i1 = 0; i1 < m_(); i1++) {
            OItemStack localOItemStack = c_(i1);
            if (localOItemStack != null) {
                float f1 = bq.nextFloat() * 0.8F + 0.1F;
                float f2 = bq.nextFloat() * 0.8F + 0.1F;
                float f3 = bq.nextFloat() * 0.8F + 0.1F;

                while (localOItemStack.a > 0) {
                    int i2 = bq.nextInt(21) + 10;
                    if (i2 > localOItemStack.a) {
                        i2 = localOItemStack.a;
                    }
                    localOItemStack.a -= i2;

                    OEntityItem localOEntityItem = new OEntityItem(aF, aJ + f1, aK + f2, aL + f3, new OItemStack(localOItemStack.c, i2, localOItemStack.h()));
                    float f4 = 0.05F;
                    localOEntityItem.aM = ((float) bq.nextGaussian() * f4);
                    localOEntityItem.aN = ((float) bq.nextGaussian() * f4 + 0.2F);
                    localOEntityItem.aO = ((float) bq.nextGaussian() * f4);
                    aF.a(localOEntityItem);
                }
            }
        }
        super.C();
    }

    public void f_() {
        // hMod: Update of the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_UPDATE, cart);

        if (b > 0) {
            b -= 1;
        }
        if (a > 0) {
            a -= 1;
        }

        if ((aF.t) && (k > 0)) {
            if (k > 0) {
                double d1 = aJ + (l - aJ) / k;
                double d2 = aK + (m - aK) / k;
                double d3 = aL + (n - aL) / k;

                double d4 = o - aP;
                while (d4 < -180.0D) {
                    d4 += 360.0D;
                }
                while (d4 >= 180.0D) {
                    d4 -= 360.0D;
                }
                aP = (float) (aP + d4 / k);
                aQ = (float) (aQ + (p - aQ) / k);

                k -= 1;
                a(d1, d2, d3);
                c(aP, aQ);
            } else {
                a(aJ, aK, aL);
                c(aP, aQ);
            }

            return;
        }
        aG = aJ;
        aH = aK;
        aI = aL;

        aN -= 0.03999999910593033D;

        int i1 = OMathHelper.b(aJ);
        int i2 = OMathHelper.b(aK);
        int i3 = OMathHelper.b(aL);
        if (aF.a(i1, i2 - 1, i3) == OBlock.aG.bk) {
            i2--;
        }

        double d5 = 0.4D;
        int i4 = 0;

        double d4 = 0.0078125D;
        if (aF.a(i1, i2, i3) == OBlock.aG.bk) {
            OVec3D localOVec3D1 = g(aJ, aK, aL);
            int i5 = aF.b(i1, i2, i3);
            aK = i2;
            if ((i5 >= 2) && (i5 <= 5)) {
                aK = (i2 + 1);
            }

            if (i5 == 2) {
                aM -= d4;
            }
            if (i5 == 3) {
                aM += d4;
            }
            if (i5 == 4) {
                aO += d4;
            }
            if (i5 == 5) {
                aO -= d4;
            }

            int[][] arrayOfInt = j[i5];

            double d6 = arrayOfInt[1][0] - arrayOfInt[0][0];
            double d7 = arrayOfInt[1][2] - arrayOfInt[0][2];
            double d8 = Math.sqrt(d6 * d6 + d7 * d7);

            double d9 = aM * d6 + aO * d7;
            if (d9 < 0.0D) {
                d6 = -d6;
                d7 = -d7;
            }

            double d10 = Math.sqrt(aM * aM + aO * aO);

            aM = (d10 * d6 / d8);
            aO = (d10 * d7 / d8);

            double d11 = 0.0D;
            double d12 = i1 + 0.5D + arrayOfInt[0][0] * 0.5D;
            double d13 = i3 + 0.5D + arrayOfInt[0][2] * 0.5D;
            double d14 = i1 + 0.5D + arrayOfInt[1][0] * 0.5D;
            double d15 = i3 + 0.5D + arrayOfInt[1][2] * 0.5D;

            d6 = d14 - d12;
            d7 = d15 - d13;
            double d18;
            if (d6 == 0.0D) {
                aJ = (i1 + 0.5D);
                d11 = aL - i3;
            } else if (d7 == 0.0D) {
                aL = (i3 + 0.5D);
                d11 = aJ - i1;
            } else {
                double d16 = aJ - d12;
                double d17 = aL - d13;

                d18 = (d16 * d6 + d17 * d7) * 2.0D;
                d11 = d18;
            }

            aJ = (d12 + d6 * d11);
            aL = (d13 + d7 * d11);

            a(aJ, aK + bb, aL);

            double d16 = aM;
            double d17 = aO;
            if (aD != null) {
                d16 *= 0.75D;
                d17 *= 0.75D;
            }
            if (d16 < -d5) {
                d16 = -d5;
            }
            if (d16 > d5) {
                d16 = d5;
            }
            if (d17 < -d5) {
                d17 = -d5;
            }
            if (d17 > d5) {
                d17 = d5;
            }
            c(d16, 0.0D, d17);

            if ((arrayOfInt[0][1] != 0) && (OMathHelper.b(aJ) - i1 == arrayOfInt[0][0]) && (OMathHelper.b(aL) - i3 == arrayOfInt[0][2])) {
                a(aJ, aK + arrayOfInt[0][1], aL);
            } else if ((arrayOfInt[1][1] != 0) && (OMathHelper.b(aJ) - i1 == arrayOfInt[1][0]) && (OMathHelper.b(aL) - i3 == arrayOfInt[1][2])) {
                a(aJ, aK + arrayOfInt[1][1], aL);
            }

            if (aD != null) {
                aM *= 0.996999979019165D;
                aN *= 0.0D;
                aO *= 0.996999979019165D;
            } else {
                if (d == 2) {
                    d18 = OMathHelper.a(f * f + g * g);
                    if (d18 > 0.01D) {
                        i4 = 1;
                        f /= d18;
                        g /= d18;
                        double d19 = 0.04D;
                        aM *= 0.800000011920929D;
                        aN *= 0.0D;
                        aO *= 0.800000011920929D;
                        aM += f * d19;
                        aO += g * d19;
                    } else {
                        aM *= 0.8999999761581421D;
                        aN *= 0.0D;
                        aO *= 0.8999999761581421D;
                    }
                }
                aM *= 0.9599999785423279D;
                aN *= 0.0D;
                aO *= 0.9599999785423279D;
            }

            OVec3D localOVec3D2 = g(aJ, aK, aL);
            if ((localOVec3D2 != null) && (localOVec3D1 != null)) {
                double d20 = (localOVec3D1.b - localOVec3D2.b) * 0.05D;

                d10 = Math.sqrt(aM * aM + aO * aO);
                if (d10 > 0.0D) {
                    aM = (aM / d10 * (d10 + d20));
                    aO = (aO / d10 * (d10 + d20));
                }
                a(aJ, localOVec3D2.b, aL);
            }

            int i6 = OMathHelper.b(aJ);
            int i7 = OMathHelper.b(aL);
            if ((i6 != i1) || (i7 != i3)) {
                d10 = Math.sqrt(aM * aM + aO * aO);

                aM = (d10 * (i6 - i1));
                aO = (d10 * (i7 - i3));
            }

            if (d == 2) {
                double d21 = OMathHelper.a(f * f + g * g);
                if ((d21 > 0.01D) && (aM * aM + aO * aO > 0.001D)) {
                    f /= d21;
                    g /= d21;

                    if (f * aM + g * aO < 0.0D) {
                        f = 0.0D;
                        g = 0.0D;
                    } else {
                        f = aM;
                        g = aO;
                    }
                }
            }
        } else {
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

            if (!aU) {
                aM *= 0.949999988079071D;
                aN *= 0.949999988079071D;
                aO *= 0.949999988079071D;
            }
        }

        aQ = 0.0F;
        double d22 = aG - aJ;
        double d23 = aI - aL;
        if (d22 * d22 + d23 * d23 > 0.001D) {
            aP = (float) (Math.atan2(d23, d22) * 180.0D / 3.141592653589793D);
            if (i) {
                aP += 180.0F;
            }
        }

        double d24 = aP - aR;
        while (d24 >= 180.0D) {
            d24 -= 360.0D;
        }
        while (d24 < -180.0D) {
            d24 += 360.0D;
        }
        if ((d24 < -170.0D) || (d24 >= 170.0D)) {
            aP += 180.0F;
            i = (!i);
        }
        c(aP, aQ);

        List localList = aF.b(this, aT.b(0.2000000029802322D, 0.0D, 0.2000000029802322D));
        if ((localList != null) && (localList.size() > 0)) {
            for (int i8 = 0; i8 < localList.size(); i8++) {
                OEntity localOEntity = (OEntity) localList.get(i8);
                if ((localOEntity != aD) && (localOEntity.e_()) && ((localOEntity instanceof OEntityMinecart))) {
                    localOEntity.h(this);
                }
            }

        }

        if ((aD != null) && (aD.ba)) {
            aD = null;
        }

        if ((i4 != 0) && (bq.nextInt(4) == 0)) {
            e -= 1;
            if (e < 0) {
                f = (this.g = 0.0D);
            }
            aF.a("largesmoke", aJ, aK + 0.8D, aL, 0.0D, 0.0D, 0.0D);
        }
    }

    public OVec3D g(double paramDouble1, double paramDouble2, double paramDouble3) {
        int i1 = OMathHelper.b(paramDouble1);
        int i2 = OMathHelper.b(paramDouble2);
        int i3 = OMathHelper.b(paramDouble3);
        // hMod: Change of the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, cart, i1, i2, i3);

        if (aF.a(i1, i2 - 1, i3) == OBlock.aG.bk) {
            i2--;
        }

        if (aF.a(i1, i2, i3) == OBlock.aG.bk) {
            int i4 = aF.b(i1, i2, i3);
            paramDouble2 = i2;
            if ((i4 >= 2) && (i4 <= 5))
                paramDouble2 = i2 + 1;
            if (i4 > 9) {
                MinecraftServer.a.warning("hMod Warning: Scambled minecart track data at (" + i + "," + j + "," + k + ") data " + m);
                i4 = 9;
            }

            int[][] arrayOfInt = j[i4];

            double d1 = 0.0D;
            double d2 = i1 + 0.5D + arrayOfInt[0][0] * 0.5D;
            double d3 = i2 + 0.5D + arrayOfInt[0][1] * 0.5D;
            double d4 = i3 + 0.5D + arrayOfInt[0][2] * 0.5D;
            double d5 = i1 + 0.5D + arrayOfInt[1][0] * 0.5D;
            double d6 = i2 + 0.5D + arrayOfInt[1][1] * 0.5D;
            double d7 = i3 + 0.5D + arrayOfInt[1][2] * 0.5D;

            double d8 = d5 - d2;
            double d9 = (d6 - d3) * 2.0D;
            double d10 = d7 - d4;

            if (d8 == 0.0D) {
                paramDouble1 = i1 + 0.5D;
                d1 = paramDouble3 - i3;
            } else if (d10 == 0.0D) {
                paramDouble3 = i3 + 0.5D;
                d1 = paramDouble1 - i1;
            } else {
                double d11 = paramDouble1 - d2;
                double d12 = paramDouble3 - d4;

                double d13 = (d11 * d8 + d12 * d10) * 2.0D;
                d1 = d13;
            }

            paramDouble1 = d2 + d8 * d1;
            paramDouble2 = d3 + d9 * d1;
            paramDouble3 = d4 + d10 * d1;
            if (d9 < 0.0D) {
                paramDouble2 += 1.0D;
            }
            if (d9 > 0.0D) {
                paramDouble2 += 0.5D;
            }
            return OVec3D.b(paramDouble1, paramDouble2, paramDouble3);
        }
        return null;
    }

    protected void a(ONBTTagCompound paramONBTTagCompound) {
        paramONBTTagCompound.a("Type", d);

        if (d == 2) {
            paramONBTTagCompound.a("PushX", f);
            paramONBTTagCompound.a("PushZ", g);
            paramONBTTagCompound.a("Fuel", (short) e);
        } else if (d == 1) {
            ONBTTagList localONBTTagList = new ONBTTagList();

            for (int i1 = 0; i1 < h.length; i1++) {
                if (h[i1] != null) {
                    ONBTTagCompound localONBTTagCompound = new ONBTTagCompound();
                    localONBTTagCompound.a("Slot", (byte) i1);
                    h[i1].a(localONBTTagCompound);
                    localONBTTagList.a(localONBTTagCompound);
                }
            }
            paramONBTTagCompound.a("Items", localONBTTagList);
        }
    }

    protected void b(ONBTTagCompound paramONBTTagCompound) {
        d = paramONBTTagCompound.e("Type");
        if (d == 2) {
            f = paramONBTTagCompound.h("PushX");
            g = paramONBTTagCompound.h("PushZ");
            e = paramONBTTagCompound.d("Fuel");
        } else if (d == 1) {
            ONBTTagList localONBTTagList = paramONBTTagCompound.l("Items");
            h = new OItemStack[m_()];
            for (int i1 = 0; i1 < localONBTTagList.c(); i1++) {
                ONBTTagCompound localONBTTagCompound = (ONBTTagCompound) localONBTTagList.a(i1);
                int i2 = localONBTTagCompound.c("Slot") & 0xFF;
                if ((i2 < 0) || (i2 >= h.length)) {
                    continue;
                }
                h[i2] = new OItemStack(localONBTTagCompound);
            }
        }
    }

    public void h(OEntity paramOEntity) {
        if (aF.t) {
            return;
        }

        if (paramOEntity == aD) {
            return;
        }
        // hMod: Collision of a cart
        if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_COLLISION, cart, paramOEntity.entity))
            return;

        if (((paramOEntity instanceof OEntityLiving)) && (!(paramOEntity instanceof OEntityPlayer)) && (d == 0) && (aM * aM + aO * aO > 0.01D) && (aD == null) && (paramOEntity.aE == null)) {
            paramOEntity.b(this);
        }

        double d1 = paramOEntity.aJ - aJ;
        double d2 = paramOEntity.aL - aL;

        double d3 = d1 * d1 + d2 * d2;
        if (d3 >= 9.999999747378752E-05D) {
            d3 = OMathHelper.a(d3);
            d1 /= d3;
            d2 /= d3;
            double d4 = 1.0D / d3;
            if (d4 > 1.0D) {
                d4 = 1.0D;
            }
            d1 *= d4;
            d2 *= d4;
            d1 *= 0.1000000014901161D;
            d2 *= 0.1000000014901161D;

            d1 *= (1.0F - bo);
            d2 *= (1.0F - bo);
            d1 *= 0.5D;
            d2 *= 0.5D;

            if ((paramOEntity instanceof OEntityMinecart)) {
                double d5 = paramOEntity.aM + aM;
                double d6 = paramOEntity.aO + aO;

                if ((((OEntityMinecart) paramOEntity).d == 2) && (d != 2)) {
                    aM *= 0.2000000029802322D;
                    aO *= 0.2000000029802322D;
                    f(paramOEntity.aM - d1, 0.0D, paramOEntity.aO - d2);
                    paramOEntity.aM *= 0.699999988079071D;
                    paramOEntity.aO *= 0.699999988079071D;
                } else if ((((OEntityMinecart) paramOEntity).d != 2) && (d == 2)) {
                    paramOEntity.aM *= 0.2000000029802322D;
                    paramOEntity.aO *= 0.2000000029802322D;
                    paramOEntity.f(aM + d1, 0.0D, aO + d2);
                    aM *= 0.699999988079071D;
                    aO *= 0.699999988079071D;
                } else {
                    d5 /= 2.0D;
                    d6 /= 2.0D;
                    aM *= 0.2000000029802322D;
                    aO *= 0.2000000029802322D;
                    f(d5 - d1, 0.0D, d6 - d2);
                    paramOEntity.aM *= 0.2000000029802322D;
                    paramOEntity.aO *= 0.2000000029802322D;
                    paramOEntity.f(d5 + d1, 0.0D, d6 + d2);
                }
            } else {
                f(-d1, 0.0D, -d2);
                paramOEntity.f(d1 / 4.0D, 0.0D, d2 / 4.0D);
            }
        }
    }

    public int m_() {
        return 27;
    }

    public OItemStack c_(int paramInt) {
        return h[paramInt];
    }

    public OItemStack a(int paramInt1, int paramInt2) {
        if (h[paramInt1] != null) {
            if (h[paramInt1].a <= paramInt2) {
                OItemStack localOItemStack = h[paramInt1];
                h[paramInt1] = null;
                return localOItemStack;
            }
            OItemStack localOItemStack = h[paramInt1].a(paramInt2);
            if (h[paramInt1].a == 0) {
                h[paramInt1] = null;
            }
            return localOItemStack;
        }

        return null;
    }

    public void a(int paramInt, OItemStack paramOItemStack) {
        h[paramInt] = paramOItemStack;
        if ((paramOItemStack != null) && (paramOItemStack.a > n_())) {
            paramOItemStack.a = n_();
        }
    }

    public String c() {
        return "Minecart";
    }

    public int n_() {
        return 64;
    }

    public void h() {
    }

    public boolean a(OEntityPlayer paramOEntityPlayer) {
        // hMod: Entering the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_ENTERED, cart, paramOEntityPlayer.entity);

        if (d == 0) {
            if ((aD != null) && ((aD instanceof OEntityPlayer)) && (aD != paramOEntityPlayer)) {
                return true;
            }
            if (!aF.t) {
                paramOEntityPlayer.b(this);
            }
        } else if (d == 1) {
            if (!aF.t) {
                // hMod cast this down to fix decompiler error.
                paramOEntityPlayer.a((OIInventory) this);
            }
        } else if (d == 2) {
            OItemStack localOItemStack = paramOEntityPlayer.i.b();
            if ((localOItemStack != null) && (localOItemStack.c == OItem.k.bc)) {
                if (--localOItemStack.a == 0) {
                    paramOEntityPlayer.i.a(paramOEntityPlayer.i.c, null);
                }
                e += 1200;
            }

            f = (aJ - paramOEntityPlayer.aJ);
            g = (aL - paramOEntityPlayer.aL);
        }
        return true;
    }

    public boolean a_(OEntityPlayer paramOEntityPlayer) {
        if (ba) {
            return false;
        }
        return paramOEntityPlayer.g(this) <= 64.0D;
    }
}
