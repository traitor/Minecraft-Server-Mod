import java.util.List;

public class jn extends dy implements kd {

    // hMod: need to be public!
    public hm[] ak = new hm[36];
    public int a = 0;
    public int b = 0;
    public int c = 1;
    private boolean al = false;
    public int d;
    public int e;
    public double f;
    public double aj;
    private static final int[][][] am = {{{0, 0, -1}, {0, 0, 1}}, {{-1, 0, 0}, {1, 0, 0}}, {{-1, -1, 0}, {1, 0, 0}}, {{-1, 0, 0}, {1, -1, 0}}, {{0, 0, -1}, {0, -1, 1}}, {{0, -1, -1}, {0, 0, 1}}, {{0, 0, 1}, {1, 0, 0}}, {{0, 0, 1}, {-1, 0, 0}}, {{0, 0, -1}, {-1, 0, 0}}, {{0, 0, -1}, {1, 0, 0}}};
    private int an;
    private double ao;
    private double ap;
    private double aq;
    private double ar;
    private double as;

    public jn(ep paramep) {
        super(paramep);
        i = true;
        a(0.98F, 0.7F);
        H = (J / 2.0F);
        M = false;
    }

    @Override
    public dv d(dy paramdy) {
        return paramdy.z;
    }

    @Override
    public dv q() {
        return null;
    }

    @Override
    public boolean v() {
        return true;
    }

    /**
     * Create Minecart
     * 
     * @param paramep world object
     * @param paramDouble1 X
     * @param paramDouble2 Y
     * @param paramDouble3 Z
     * @param paramInt Not sure
     */
    public jn(ep paramep, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt) {
        this(paramep);
        a(paramDouble1, paramDouble2 + H, paramDouble3);

        s = 0.0D;
        t = 0.0D;
        u = 0.0D;

        m = paramDouble1;
        n = paramDouble2;
        o = paramDouble3;
        d = paramInt;
        // hMod: Creation of the cart
        Minecart cart = new Minecart(this);
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_CREATE, new Object[]{cart});
    }

    @Override
    public double j() {
        return J * 0.0D - 0.300000011920929D;
    }

    /**
     * Attack Minecart
     * 
     * @param paramdx attacker
     * @param paramInt damage
     * @return
     */
    @Override
    public boolean a(dy paramdy, int paramInt) {
        // hMod: Attack of the cart
        BaseEntity attacker = new BaseEntity(paramdy);
        Minecart cart = new Minecart(this);
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_DAMAGE, new Object[]{cart, attacker, paramInt})) {
            return true;
        }
        if (l.z) {
            return true;
        }
        c = (-c);
        b = 10;
        u();
        a += paramInt * 10;
        if (a > 40) {
            a(fv.ax.aW, 1, 0.0F);
            if (d == 1) {
                a(gb.au.bh, 1, 0.0F);
            } else if (d == 2) {
                a(gb.aB.bh, 1, 0.0F);
            }
            l();
        }
        return true;
    }

    @Override
    public boolean c_() {
        return !G;
    }

    @Override
    public void l() {
        // hMod: Destruction of the cart
        Minecart cart = new Minecart(this);
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_DESTROYED, new Object[]{cart});
        for (int i = 0; i < a(); i++) {
            hm localhm = a(i);
            if (localhm != null) {
                float f1 = W.nextFloat() * 0.8F + 0.1F;
                float f2 = W.nextFloat() * 0.8F + 0.1F;
                float f3 = W.nextFloat() * 0.8F + 0.1F;

                while (localhm.a > 0) {
                    int j = W.nextInt(21) + 10;
                    if (j > localhm.a) {
                        j = localhm.a;
                    }
                    localhm.a -= j;

                    gk localgk = new gk(l, p + f1, q + f2, r + f3, new hm(localhm.c, j, localhm.d));
                    float f4 = 0.05F;
                    localgk.s = ((float) W.nextGaussian() * f4);
                    localgk.t = ((float) W.nextGaussian() * f4 + 0.2F);
                    localgk.u = ((float) W.nextGaussian() * f4);
                    l.a(localgk);
                }
            }
        }
        super.l();
    }

    /**
     * Minecart update
     */
    @Override
    public void b_() {
        // hMod: Update of the cart
        Minecart cart = new Minecart(this);
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_UPDATE, new Object[]{cart});

        if (b > 0) {
            b -= 1;
        }
        if (a > 0) {
            a -= 1;
        }

        if ((l.z) && (an > 0)) {
            if (an > 0) {
                double d1 = p + (ao - p) / an;
                double d2 = q + (ap - q) / an;
                double d4 = r + (aq - r) / an;
                // hMod add "double" to fix decompiler error.
                double d5 = ar - v;
                while (d5 < -180.0D) {
                    d5 += 360.0D;
                }
                while (d5 >= 180.0D) {
                    d5 -= 360.0D;
                }
                v = (float) (v + d5 / an);
                w = (float) (w + (as - w) / an);

                an -= 1;
                a(d1, d2, d4);
                b(v, w);
            } else {
                a(p, q, r);
                b(v, w);
            }

            return;
        }
        this.m = p;
        this.n = q;
        o = r;

        t -= 0.03999999910593033D;

        int i = hg.b(p);
        int j = hg.b(q);
        int k = hg.b(r);
        if (l.a(i, j - 1, k) == gb.aG.bh) {
            j--;
        }

        double d3 = 0.4D;
        int m = 0;

        double d5 = 0.0078125D;
        if (l.a(i, j, k) == gb.aG.bh) {
            bd localbd1 = g(p, q, r);
            int n = l.b(i, j, k);
            q = j;
            if ((n >= 2) && (n <= 5)) {
                q = (j + 1);
            }

            if (n == 2) {
                s -= d5;
            }
            if (n == 3) {
                s += d5;
            }
            if (n == 4) {
                u += d5;
            }
            if (n == 5) {
                u -= d5;
            }

            int[][] arrayOfInt = am[n];

            double d8 = arrayOfInt[1][0] - arrayOfInt[0][0];
            double d10 = arrayOfInt[1][2] - arrayOfInt[0][2];
            double d11 = Math.sqrt(d8 * d8 + d10 * d10);

            double d12 = s * d8 + u * d10;
            if (d12 < 0.0D) {
                d8 = -d8;
                d10 = -d10;
            }

            double d13 = Math.sqrt(s * s + u * u);

            s = (d13 * d8 / d11);
            u = (d13 * d10 / d11);

            double d14 = 0.0D;
            double d15 = i + 0.5D + arrayOfInt[0][0] * 0.5D;
            double d16 = k + 0.5D + arrayOfInt[0][2] * 0.5D;
            double d17 = i + 0.5D + arrayOfInt[1][0] * 0.5D;
            double d18 = k + 0.5D + arrayOfInt[1][2] * 0.5D;

            d8 = d17 - d15;
            d10 = d18 - d16;
            double d21;
            if (d8 == 0.0D) {
                p = (i + 0.5D);
                d14 = r - k;
            } else if (d10 == 0.0D) {
                r = (k + 0.5D);
                d14 = p - i;
            } else {
                double d19 = p - d15;
                double d20 = r - d16;

                d21 = (d19 * d8 + d20 * d10) * 2.0D;
                d14 = d21;
            }

            p = (d15 + d8 * d14);
            r = (d16 + d10 * d14);

            a(p, q + H, r);

            double d19 = s;
            double d20 = u;
            if (this.j != null) {
                d19 *= 0.75D;
                d20 *= 0.75D;
            }
            if (d19 < -d3) {
                d19 = -d3;
            }
            if (d19 > d3) {
                d19 = d3;
            }
            if (d20 < -d3) {
                d20 = -d3;
            }
            if (d20 > d3) {
                d20 = d3;
            }
            c(d19, 0.0D, d20);

            if ((arrayOfInt[0][1] != 0) && (hg.b(p) - i == arrayOfInt[0][0]) && (hg.b(r) - k == arrayOfInt[0][2])) {
                a(p, q + arrayOfInt[0][1], r);
            } else if ((arrayOfInt[1][1] != 0) && (hg.b(p) - i == arrayOfInt[1][0]) && (hg.b(r) - k == arrayOfInt[1][2])) {
                a(p, q + arrayOfInt[1][1], r);
            }

            if (this.j != null) {
                s *= 0.996999979019165D;
                t *= 0.0D;
                u *= 0.996999979019165D;
            } else {
                if (d == 2) {
                    d21 = hg.a(f * f + aj * aj);
                    if (d21 > 0.01D) {
                        m = 1;
                        f /= d21;
                        aj /= d21;
                        double d23 = 0.04D;
                        s *= 0.800000011920929D;
                        t *= 0.0D;
                        u *= 0.800000011920929D;
                        s += f * d23;
                        u += aj * d23;
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

            bd localbd2 = g(p, q, r);
            if ((localbd2 != null) && (localbd1 != null)) {
                double d22 = (localbd1.b - localbd2.b) * 0.05D;

                d13 = Math.sqrt(s * s + u * u);
                if (d13 > 0.0D) {
                    s = (s / d13 * (d13 + d22));
                    u = (u / d13 * (d13 + d22));
                }
                a(p, localbd2.b, r);
            }

            int i2 = hg.b(p);
            int i3 = hg.b(r);
            if ((i2 != i) || (i3 != k)) {
                d13 = Math.sqrt(s * s + u * u);

                s = (d13 * (i2 - i));
                u = (d13 * (i3 - k));
            }

            if (d == 2) {
                double d24 = hg.a(f * f + aj * aj);
                if ((d24 > 0.01D) && (s * s + u * u > 0.001D)) {
                    f /= d24;
                    aj /= d24;

                    if (f * s + aj * u < 0.0D) {
                        f = 0.0D;
                        aj = 0.0D;
                    } else {
                        f = s;
                        aj = u;
                    }
                }
            }
        } else {
            if (s < -d3) {
                s = (-d3);
            }
            if (s > d3) {
                s = d3;
            }
            if (u < -d3) {
                u = (-d3);
            }
            if (u > d3) {
                u = d3;
            }
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
        double d6 = this.m - p;
        double d7 = o - r;
        if (d6 * d6 + d7 * d7 > 0.001D) {
            v = (float) (Math.atan2(d7, d6) * 180.0D / 3.141592653589793D);
            if (al) {
                v += 180.0F;
            }
        }

        double d9 = v - x;
        while (d9 >= 180.0D) {
            d9 -= 360.0D;
        }
        while (d9 < -180.0D) {
            d9 += 360.0D;
        }
        if ((d9 < -170.0D) || (d9 >= 170.0D)) {
            v += 180.0F;
            al = (!al);
        }
        b(v, w);

        List localList = l.b(this, z.b(0.2000000029802322D, 0.0D, 0.2000000029802322D));
        if ((localList != null) && (localList.size() > 0)) {
            for (int i1 = 0; i1 < localList.size(); i1++) {
                dy localdy = (dy) localList.get(i1);
                if ((localdy != this.j) && (localdy.v()) && ((localdy instanceof jn))) {
                    localdy.c(this);
                }
            }

        }

        if ((this.j != null) && (this.j.G)) {
            this.j = null;
        }

        if ((m != 0) && (W.nextInt(4) == 0)) {
            e -= 1;
            if (e < 0) {
                f = (this.aj = 0.0D);
            }
            l.a("largesmoke", p, q + 0.8D, r, 0.0D, 0.0D, 0.0D);
        }
    }

    /**
     * set position?
     * 
     * @param paramDouble1
     * @param paramDouble2
     * @param paramDouble3
     * @return
     */
    public bd g(double paramDouble1, double paramDouble2, double paramDouble3) {
        int i = hg.b(paramDouble1);
        int j = hg.b(paramDouble2);
        int k = hg.b(paramDouble3);
        // hMod: Change of the cart
        Minecart minecart = new Minecart(this);
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, new Object[]{minecart, i, j, k});

        if (l.a(i, j - 1, k) == gb.aG.bh) {
            j--;
        }

        if (l.a(i, j, k) == gb.aG.bh) {
            int m = l.b(i, j, k);
            paramDouble2 = j;
            if ((m >= 2) && (m <= 5)) {
                paramDouble2 = j + 1;
            }

            int[][] arrayOfInt = am[m];

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
            if (d9 < 0.0D) {
                paramDouble2 += 1.0D;
            }
            if (d9 > 0.0D) {
                paramDouble2 += 0.5D;
            }
            return bd.b(paramDouble1, paramDouble2, paramDouble3);
        }
        return null;
    }

    @Override
    protected void a(v paramv) {
        paramv.a("Type", d);

        if (d == 2) {
            paramv.a("PushX", f);
            paramv.a("PushZ", aj);
            paramv.a("Fuel", (short) e);
        } else if (d == 1) {
            ec localec = new ec();

            for (int i = 0; i < ak.length; i++) {
                if (ak[i] != null) {
                    v localv = new v();
                    localv.a("Slot", (byte) i);
                    ak[i].a(localv);
                    localec.a(localv);
                }
            }
            paramv.a("Items", localec);
        }
    }

    @Override
    protected void b(v paramv) {
        d = paramv.d("Type");
        if (d == 2) {
            f = paramv.g("PushX");
            aj = paramv.g("PushZ");
            e = paramv.c("Fuel");
        } else if (d == 1) {
            ec localec = paramv.k("Items");
            ak = new hm[a()];
            for (int i = 0; i < localec.b(); i++) {
                v localv = (v) localec.a(i);
                int j = localv.b("Slot") & 0xFF;
                if ((j < 0) || (j >= ak.length)) {
                    continue;
                }
                ak[j] = new hm(localv);
            }
        }
    }

    /**
     * Minecart Collision
     * 
     * @param paramdy
     */
    @Override
    public void c(dy paramdy) {
        if (l.z) {
            return;
        }

        if (paramdy == j) {
            return;
        }

        // hMod: Collision of a cart
        Minecart cart = new Minecart(this);
        BaseEntity baseEntity = new BaseEntity(paramdy);
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_COLLISION, new Object[]{cart, baseEntity});

        if (((paramdy instanceof jz)) && (!(paramdy instanceof fy)) && (d == 0) && (s * s + u * u > 0.01D) && (j == null) && (paramdy.k == null)) {
            paramdy.e(this);
        }

        double d1 = paramdy.p - p;
        double d2 = paramdy.r - r;

        double d3 = d1 * d1 + d2 * d2;
        if (d3 >= 9.999999747378752E-005D) {
            d3 = hg.a(d3);
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

            d1 *= (1.0F - U);
            d2 *= (1.0F - U);
            d1 *= 0.5D;
            d2 *= 0.5D;

            if ((paramdy instanceof jn)) {
                double d5 = paramdy.s + s;
                double d6 = paramdy.u + u;

                if ((((jn) paramdy).d == 2) && (d != 2)) {
                    s *= 0.2000000029802322D;
                    u *= 0.2000000029802322D;
                    f(paramdy.s - d1, 0.0D, paramdy.u - d2);
                    paramdy.s *= 0.699999988079071D;
                    paramdy.u *= 0.699999988079071D;
                } else if ((((jn) paramdy).d != 2) && (d == 2)) {
                    paramdy.s *= 0.2000000029802322D;
                    paramdy.u *= 0.2000000029802322D;
                    paramdy.f(s + d1, 0.0D, u + d2);
                    s *= 0.699999988079071D;
                    u *= 0.699999988079071D;
                } else {
                    d5 /= 2.0D;
                    d6 /= 2.0D;
                    s *= 0.2000000029802322D;
                    u *= 0.2000000029802322D;
                    f(d5 - d1, 0.0D, d6 - d2);
                    paramdy.s *= 0.2000000029802322D;
                    paramdy.u *= 0.2000000029802322D;
                    paramdy.f(d5 + d1, 0.0D, d6 + d2);
                }
            } else {
                f(-d1, 0.0D, -d2);
                paramdy.f(d1 / 4.0D, 0.0D, d2 / 4.0D);
            }
        }
    }

    @Override
    public int a() {
        return 27;
    }

    @Override
    public hm a(int paramInt) {
        return ak[paramInt];
    }

    /**
     * Runs when adding player to cart
     * 
     * @param paramfy Player entity
     * @return true
     */
    @Override
    public boolean a(fy paramfy) {
        // hMod: Entering the cart
        Minecart cart = new Minecart(this);
        HumanEntity player = new HumanEntity(paramfy);
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_ENTERED, new Object[]{cart, player});

        if (d == 0) {
            if ((j != null) && ((j instanceof fy)) && (j != paramfy)) {
                return true;
            }
            if (!l.z) {
                paramfy.e(this);
            }
        } else if (d == 1) {
            // hMod cast this to kd to fix decompiler error.
            paramfy.a((kd) this);
        } else if (d == 2) {
            hm localhm = paramfy.am.b();
            if ((localhm != null) && (localhm.c == fv.k.aW)) {
                if (--localhm.a == 0) {
                    paramfy.am.a(paramfy.am.d, null);
                }
                e += 1200;
            }

            f = (p - paramfy.p);
            aj = (r - paramfy.r);
        }
        return true;
    }
}
