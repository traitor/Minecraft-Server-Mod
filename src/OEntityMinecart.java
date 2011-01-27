import java.util.List;

import net.minecraft.server.MinecraftServer;

public class OEntityMinecart extends OEntity implements OIInventory, Container<OItemStack> {

    private OItemStack[]           al   = new OItemStack[36];
    public int                     a    = 0;
    public int                     b    = 0;
    public int                     c    = 1;
    private boolean                am   = false;
    public int                     d;
    public int                     e;
    public double                  f;
    public double                  ak;
    private static final int[][][] an   = { { { 0, 0, -1 }, { 0, 0, 1 } }, { { -1, 0, 0 }, { 1, 0, 0 } }, { { -1, -1, 0 }, { 1, 0, 0 } }, { { -1, 0, 0 }, { 1, -1, 0 } }, { { 0, 0, -1 }, { 0, -1, 1 } }, { { 0, -1, -1 }, { 0, 0, 1 } }, { { 0, 0, 1 }, { 1, 0, 0 } }, { { 0, 0, 1 }, { -1, 0, 0 } }, { { 0, 0, -1 }, { -1, 0, 0 } }, { { 0, 0, -1 }, { 1, 0, 0 } } };
    private int                    ao;
    private double                 ap;
    private double                 aq;
    private double                 ar;
    private double                 as;
    private double                 at;
    private String                 name = "Minecart";

    // hMod start
    Minecart                       cart = new Minecart(this);

    // hMod end

    @Override
    public OItemStack[] getContents() {
        return al;
    }

    @Override
    public void setContents(OItemStack[] values) {
        al = values;
    }

    public OItemStack getContentsAt(int index) {
        return a(index);
    }

    public void setContentsAt(int index, OItemStack value) {
        a(index, value);
    }

    public int getContentsSize() {
        return h_();
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        name = value;
    }

    public OEntityMinecart(OWorld paramOWorld) {
        super(paramOWorld);
        i = true;
        a(0.98F, 0.7F);
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
        return null;
    }

    @Override
    public boolean z() {
        return true;
    }

    public OEntityMinecart(OWorld paramOWorld, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt) {
        this(paramOWorld);
        a(paramDouble1, paramDouble2 + H, paramDouble3);

        s = 0.0D;
        t = 0.0D;
        u = 0.0D;

        m = paramDouble1;
        n = paramDouble2;
        o = paramDouble3;
        d = paramInt;

        // hMod: Creation of the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_CREATE, cart);
    }

    @Override
    public double k() {
        return J * 0.0D - 0.300000011920929D;
    }

    @Override
    public boolean a(OEntity paramOEntity, int paramInt) {
        // hMod: Attack of the cart
        if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_DAMAGE, cart, paramOEntity == null ? null : paramOEntity.entity, paramInt))
            return true;

        if ((l.z) || (G))
            return true;
        c = (-c);
        b = 10;
        y();
        a += paramInt * 10;
        if (a > 40) {
            a(OItem.ax.ba, 1, 0.0F);
            if (d == 1)
                a(OBlock.au.bi, 1, 0.0F);
            else if (d == 2)
                a(OBlock.aB.bi, 1, 0.0F);
            q();
        }
        return true;
    }

    @Override
    public boolean c_() {
        return !G;
    }

    @Override
    public void q() {
        // hMod: Destruction of the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_DESTROYED, cart);

        for (int i = 0; i < h_(); i++) {
            OItemStack localOItemStack = a(i);
            if (localOItemStack != null) {
                float f1 = W.nextFloat() * 0.8F + 0.1F;
                float f2 = W.nextFloat() * 0.8F + 0.1F;
                float f3 = W.nextFloat() * 0.8F + 0.1F;

                while (localOItemStack.a > 0) {
                    int j = W.nextInt(21) + 10;
                    if (j > localOItemStack.a)
                        j = localOItemStack.a;
                    localOItemStack.a -= j;

                    OEntityItem localOEntityItem = new OEntityItem(l, p + f1, q + f2, r + f3, new OItemStack(localOItemStack.c, j, localOItemStack.h()));
                    float f4 = 0.05F;
                    localOEntityItem.s = ((float) W.nextGaussian() * f4);
                    localOEntityItem.t = ((float) W.nextGaussian() * f4 + 0.2F);
                    localOEntityItem.u = ((float) W.nextGaussian() * f4);
                    l.a(localOEntityItem);
                }
            }
        }
        super.q();
    }

    @Override
    public void b_() {
        // hMod: Update of the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_UPDATE, cart);

        if (b > 0)
            b -= 1;
        if (a > 0)
            a -= 1;

        if ((l.z) && (ao > 0)) {
            if (ao > 0) {
                double d1 = p + (ap - p) / ao;
                double d2 = q + (aq - q) / ao;
                double d3 = r + (ar - r) / ao;

                double d4 = as - v;
                while (d4 < -180.0D)
                    d4 += 360.0D;
                while (d4 >= 180.0D)
                    d4 -= 360.0D;
                v = (float) (v + d4 / ao);
                w = (float) (w + (at - w) / ao);

                ao -= 1;
                a(d1, d2, d3);
                b(v, w);
            } else {
                a(p, q, r);
                b(v, w);
            }

            return;
        }
        m = p;
        n = q;
        o = r;

        t -= 0.03999999910593033D;

        int i = OMathHelper.b(p);
        int j = OMathHelper.b(q);
        int k = OMathHelper.b(r);
        if (l.a(i, j - 1, k) == OBlock.aG.bi)
            j--;

        double d5 = 0.4D;
        int m = 0;

        double d4 = 0.0078125D;
        if (l.a(i, j, k) == OBlock.aG.bi) {
            OVec3D localOVec3D1 = g(p, q, r);
            int n = l.b(i, j, k);
            q = j;
            if ((n >= 2) && (n <= 5))
                q = (j + 1);

            if (n == 2)
                s -= d4;
            if (n == 3)
                s += d4;
            if (n == 4)
                u += d4;
            if (n == 5)
                u -= d4;

            int[][] arrayOfInt = an[n];

            double d6 = arrayOfInt[1][0] - arrayOfInt[0][0];
            double d7 = arrayOfInt[1][2] - arrayOfInt[0][2];
            double d8 = Math.sqrt(d6 * d6 + d7 * d7);

            double d9 = s * d6 + u * d7;
            if (d9 < 0.0D) {
                d6 = -d6;
                d7 = -d7;
            }

            double d10 = Math.sqrt(s * s + u * u);

            s = (d10 * d6 / d8);
            u = (d10 * d7 / d8);

            double d11 = 0.0D;
            double d12 = i + 0.5D + arrayOfInt[0][0] * 0.5D;
            double d13 = k + 0.5D + arrayOfInt[0][2] * 0.5D;
            double d14 = i + 0.5D + arrayOfInt[1][0] * 0.5D;
            double d15 = k + 0.5D + arrayOfInt[1][2] * 0.5D;

            d6 = d14 - d12;
            d7 = d15 - d13;
            double d18;
            if (d6 == 0.0D) {
                p = (i + 0.5D);
                d11 = r - k;
            } else if (d7 == 0.0D) {
                r = (k + 0.5D);
                d11 = p - i;
            } else {
                double d16 = p - d12;
                double d17 = r - d13;

                d18 = (d16 * d6 + d17 * d7) * 2.0D;
                d11 = d18;
            }

            p = (d12 + d6 * d11);
            r = (d13 + d7 * d11);

            a(p, q + H, r);

            double d16 = s;
            double d17 = u;
            if (this.j != null) {
                d16 *= 0.75D;
                d17 *= 0.75D;
            }
            if (d16 < -d5)
                d16 = -d5;
            if (d16 > d5)
                d16 = d5;
            if (d17 < -d5)
                d17 = -d5;
            if (d17 > d5)
                d17 = d5;
            c(d16, 0.0D, d17);

            if ((arrayOfInt[0][1] != 0) && (OMathHelper.b(p) - i == arrayOfInt[0][0]) && (OMathHelper.b(r) - k == arrayOfInt[0][2]))
                a(p, q + arrayOfInt[0][1], r);
            else if ((arrayOfInt[1][1] != 0) && (OMathHelper.b(p) - i == arrayOfInt[1][0]) && (OMathHelper.b(r) - k == arrayOfInt[1][2]))
                a(p, q + arrayOfInt[1][1], r);

            if (this.j != null) {
                s *= 0.996999979019165D;
                t *= 0.0D;
                u *= 0.996999979019165D;
            } else {
                if (d == 2) {
                    d18 = OMathHelper.a(f * f + ak * ak);
                    if (d18 > 0.01D) {
                        m = 1;
                        f /= d18;
                        ak /= d18;
                        double d19 = 0.04D;
                        s *= 0.800000011920929D;
                        t *= 0.0D;
                        u *= 0.800000011920929D;
                        s += f * d19;
                        u += ak * d19;
                    } else {
                        s *= 0.8999999761581421D;
                        t *= 0.0D;
                        u *= 0.8999999761581421D;
                    }
                }
                s *= 0.9599999785423279D;
                t *= 0.0D;
                u *= 0.9599999785423279D;
            }

            OVec3D localOVec3D2 = g(p, q, r);
            if ((localOVec3D2 != null) && (localOVec3D1 != null)) {
                double d20 = (localOVec3D1.b - localOVec3D2.b) * 0.05D;

                d10 = Math.sqrt(s * s + u * u);
                if (d10 > 0.0D) {
                    s = (s / d10 * (d10 + d20));
                    u = (u / d10 * (d10 + d20));
                }
                a(p, localOVec3D2.b, r);
            }

            int i1 = OMathHelper.b(p);
            int i2 = OMathHelper.b(r);
            if ((i1 != i) || (i2 != k)) {
                d10 = Math.sqrt(s * s + u * u);

                s = (d10 * (i1 - i));
                u = (d10 * (i2 - k));
            }

            if (d == 2) {
                double d21 = OMathHelper.a(f * f + ak * ak);
                if ((d21 > 0.01D) && (s * s + u * u > 0.001D)) {
                    f /= d21;
                    ak /= d21;

                    if (f * s + ak * u < 0.0D) {
                        f = 0.0D;
                        ak = 0.0D;
                    } else {
                        f = s;
                        ak = u;
                    }
                }
            }
        } else {
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

            if (!A) {
                s *= 0.949999988079071D;
                t *= 0.949999988079071D;
                u *= 0.949999988079071D;
            }
        }

        w = 0.0F;
        double d22 = this.m - p;
        double d23 = o - r;
        if (d22 * d22 + d23 * d23 > 0.001D) {
            v = (float) (Math.atan2(d23, d22) * 180.0D / 3.141592653589793D);
            if (am)
                v += 180.0F;
        }

        double d24 = v - x;
        while (d24 >= 180.0D)
            d24 -= 360.0D;
        while (d24 < -180.0D)
            d24 += 360.0D;
        if ((d24 < -170.0D) || (d24 >= 170.0D)) {
            v += 180.0F;
            am = (!am);
        }
        b(v, w);

        List localList = l.b(this, z.b(0.2000000029802322D, 0.0D, 0.2000000029802322D));
        if ((localList != null) && (localList.size() > 0))
            for (int i3 = 0; i3 < localList.size(); i3++) {
                OEntity localOEntity = (OEntity) localList.get(i3);
                if ((localOEntity != this.j) && (localOEntity.z()) && ((localOEntity instanceof OEntityMinecart)))
                    localOEntity.c(this);
            }

        if ((this.j != null) && (this.j.G))
            this.j = null;

        if ((m != 0) && (W.nextInt(4) == 0)) {
            e -= 1;
            if (e < 0)
                f = (ak = 0.0D);
            l.a("largesmoke", p, q + 0.8D, r, 0.0D, 0.0D, 0.0D);
        }
    }

    public OVec3D g(double paramDouble1, double paramDouble2, double paramDouble3) {
        int i = OMathHelper.b(paramDouble1);
        int j = OMathHelper.b(paramDouble2);
        int k = OMathHelper.b(paramDouble3);
        // hMod: Change of the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, cart, i, j, k);

        if (l.a(i, j - 1, k) == OBlock.aG.bi)
            j--;

        if (l.a(i, j, k) == OBlock.aG.bi) {
            int m = l.b(i, j, k);
            paramDouble2 = j;
            if ((m >= 2) && (m <= 5))
                paramDouble2 = j + 1;
            if (m > 9) {
                MinecraftServer.a.warning("hMod Warning: Scambled minecart track data at (" + i + "," + j + "," + k + ") data " + m);
                m = 9;
            }
            // index out of bounds exception ?
            int[][] arrayOfInt = an[m];

            double d1 = 0.0D;
            double d2 = i + 0.5D + arrayOfInt[0][0] * 0.5D;
            double d3 = j + 0.5D + arrayOfInt[0][1] * 0.5D;
            double d4 = k + 0.5D + arrayOfInt[0][2] * 0.5D;
            double d5 = i + 0.5D + arrayOfInt[1][0] * 0.5D;
            double d6 = j + 0.5D + arrayOfInt[1][1] * 0.5D;
            double d7 = k + 0.5D + arrayOfInt[1][2] * 0.5D;

            double d8 = d5 - d2;
            double d9 = (d6 - d3) * 2.0D;
            double d10 = d7 - d4;

            if (d8 == 0.0D) {
                paramDouble1 = i + 0.5D;
                d1 = paramDouble3 - k;
            } else if (d10 == 0.0D) {
                paramDouble3 = k + 0.5D;
                d1 = paramDouble1 - i;
            } else {
                double d11 = paramDouble1 - d2;
                double d12 = paramDouble3 - d4;

                double d13 = (d11 * d8 + d12 * d10) * 2.0D;
                d1 = d13;
            }

            paramDouble1 = d2 + d8 * d1;
            paramDouble2 = d3 + d9 * d1;
            paramDouble3 = d4 + d10 * d1;
            if (d9 < 0.0D)
                paramDouble2 += 1.0D;
            if (d9 > 0.0D)
                paramDouble2 += 0.5D;
            return OVec3D.b(paramDouble1, paramDouble2, paramDouble3);
        }
        return null;
    }

    @Override
    protected void a(ONBTTagCompound paramONBTTagCompound) {
        paramONBTTagCompound.a("Type", d);

        if (d == 2) {
            paramONBTTagCompound.a("PushX", f);
            paramONBTTagCompound.a("PushZ", ak);
            paramONBTTagCompound.a("Fuel", (short) e);
        } else if (d == 1) {
            ONBTTagList localONBTTagList = new ONBTTagList();

            for (int i = 0; i < al.length; i++)
                if (al[i] != null) {
                    ONBTTagCompound localONBTTagCompound = new ONBTTagCompound();
                    localONBTTagCompound.a("Slot", (byte) i);
                    al[i].a(localONBTTagCompound);
                    localONBTTagList.a(localONBTTagCompound);
                }
            paramONBTTagCompound.a("Items", localONBTTagList);
        }
    }

    @Override
    protected void b(ONBTTagCompound paramONBTTagCompound) {
        d = paramONBTTagCompound.d("Type");
        if (d == 2) {
            f = paramONBTTagCompound.g("PushX");
            ak = paramONBTTagCompound.g("PushZ");
            e = paramONBTTagCompound.c("Fuel");
        } else if (d == 1) {
            ONBTTagList localONBTTagList = paramONBTTagCompound.k("Items");
            al = new OItemStack[h_()];
            for (int i = 0; i < localONBTTagList.b(); i++) {
                ONBTTagCompound localONBTTagCompound = (ONBTTagCompound) localONBTTagList.a(i);
                int j = localONBTTagCompound.b("Slot") & 0xFF;
                if ((j < 0) || (j >= al.length))
                    continue;
                al[j] = new OItemStack(localONBTTagCompound);
            }
        }
    }

    @Override
    public void c(OEntity paramOEntity) {
        if (l.z)
            return;

        if (paramOEntity == j)
            return;
        // hMod: Collision of a cart
        if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_COLLISION, cart, paramOEntity.entity))
            return;

        if (((paramOEntity instanceof OEntityLiving)) && (!(paramOEntity instanceof OEntityPlayer)) && (d == 0) && (s * s + u * u > 0.01D) && (j == null) && (paramOEntity.k == null))
            paramOEntity.e(this);

        double d1 = paramOEntity.p - p;
        double d2 = paramOEntity.r - r;

        double d3 = d1 * d1 + d2 * d2;
        if (d3 >= 9.999999747378752E-005D) {
            d3 = OMathHelper.a(d3);
            d1 /= d3;
            d2 /= d3;
            double d4 = 1.0D / d3;
            if (d4 > 1.0D)
                d4 = 1.0D;
            d1 *= d4;
            d2 *= d4;
            d1 *= 0.1000000014901161D;
            d2 *= 0.1000000014901161D;

            d1 *= (1.0F - U);
            d2 *= (1.0F - U);
            d1 *= 0.5D;
            d2 *= 0.5D;

            if ((paramOEntity instanceof OEntityMinecart)) {
                double d5 = paramOEntity.s + s;
                double d6 = paramOEntity.u + u;

                if ((((OEntityMinecart) paramOEntity).d == 2) && (d != 2)) {
                    s *= 0.2000000029802322D;
                    u *= 0.2000000029802322D;
                    f(paramOEntity.s - d1, 0.0D, paramOEntity.u - d2);
                    paramOEntity.s *= 0.699999988079071D;
                    paramOEntity.u *= 0.699999988079071D;
                } else if ((((OEntityMinecart) paramOEntity).d != 2) && (d == 2)) {
                    paramOEntity.s *= 0.2000000029802322D;
                    paramOEntity.u *= 0.2000000029802322D;
                    paramOEntity.f(s + d1, 0.0D, u + d2);
                    s *= 0.699999988079071D;
                    u *= 0.699999988079071D;
                } else {
                    d5 /= 2.0D;
                    d6 /= 2.0D;
                    s *= 0.2000000029802322D;
                    u *= 0.2000000029802322D;
                    f(d5 - d1, 0.0D, d6 - d2);
                    paramOEntity.s *= 0.2000000029802322D;
                    paramOEntity.u *= 0.2000000029802322D;
                    paramOEntity.f(d5 + d1, 0.0D, d6 + d2);
                }
            } else {
                f(-d1, 0.0D, -d2);
                paramOEntity.f(d1 / 4.0D, 0.0D, d2 / 4.0D);
            }
        }
    }

    public int h_() {
        return 27;
    }

    public OItemStack a(int paramInt) {
        return al[paramInt];
    }

    public OItemStack b(int paramInt1, int paramInt2) {
        if (al[paramInt1] != null) {
            if (al[paramInt1].a <= paramInt2) {
                OItemStack localOItemStack = al[paramInt1];
                al[paramInt1] = null;
                return localOItemStack;
            }
            OItemStack localOItemStack = al[paramInt1].a(paramInt2);
            if (al[paramInt1].a == 0)
                al[paramInt1] = null;
            return localOItemStack;
        }

        return null;
    }

    public void a(int paramInt, OItemStack paramOItemStack) {
        al[paramInt] = paramOItemStack;
        if ((paramOItemStack != null) && (paramOItemStack.a > c()))
            paramOItemStack.a = c();
    }

    public String b() {
        return name;
    }

    public int c() {
        return 64;
    }

    public void d() {
    }

    @Override
    public boolean a(OEntityPlayer paramOEntityPlayer) {
        // hMod: Entering the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_ENTERED, cart, paramOEntityPlayer.entity);

        if (d == 0) {
            if ((j != null) && ((j instanceof OEntityPlayer)) && (j != paramOEntityPlayer))
                return true;
            if (!l.z)
                paramOEntityPlayer.e(this);
        } else if (d == 1) {
            if (!l.z)
                // hMod cast this down to fix decompiler error.
                paramOEntityPlayer.a((OIInventory) this);
        } else if (d == 2) {
            OItemStack localOItemStack = paramOEntityPlayer.an.e();
            if ((localOItemStack != null) && (localOItemStack.c == OItem.k.ba)) {
                if (--localOItemStack.a == 0)
                    paramOEntityPlayer.an.a(paramOEntityPlayer.an.c, null);
                e += 1200;
            }

            f = (p - paramOEntityPlayer.p);
            ak = (r - paramOEntityPlayer.r);
        }
        return true;
    }

    public boolean a_(OEntityPlayer paramOEntityPlayer) {
        if (G)
            return false;
        return paramOEntityPlayer.b(this) <= 64.0D;
    }
}
