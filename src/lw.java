
import java.util.List;
import java.util.Random;

public class lw extends fe
        implements mn, Container<jl> {

    public jl[] al = new jl[36];
    public int a = 0;
    public int b = 0;
    public int c = 1;
    private boolean am = false;
    public int d;
    public int e;
    public double f;
    public double ak;
    private static final int[][][] an = {{{0, 0, -1}, {0, 0, 1}}, {{-1, 0, 0}, {1, 0, 0}}, {{-1, -1, 0}, {1, 0, 0}}, {{-1, 0, 0}, {1, -1, 0}}, {{0, 0, -1}, {0, -1, 1}}, {{0, -1, -1}, {0, 0, 1}}, {{0, 0, 1}, {1, 0, 0}}, {{0, 0, 1}, {-1, 0, 0}}, {{0, 0, -1}, {-1, 0, 0}}, {{0, 0, -1}, {1, 0, 0}}};
    private int ao;
    private double ap;
    private double aq;
    private double ar;
    private double as;
    private double at;
    private String name = "Minecart";

    @Override
    public jl[] getContents() {
        return al;
    }

    @Override
    public void setContents(jl[] values) {
        al = values;
    }

    public jl getContentsAt(int index) {
        return a(index);
    }

    public void setContentsAt(int index, jl value) {
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

    public lw(fv paramfv) {
        super(paramfv);
        i = true;
        a(0.98F, 0.7F);
        H = (J / 2.0F);
        M = false;
    }

    protected void a() {
    }

    public fa d(fe paramfe) {
        return paramfe.z;
    }

    public fa u() {
        return null;
    }

    public boolean z() {
        return true;
    }

    public lw(fv paramfv, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt) {
        this(paramfv);
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
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_CREATE, cart);
    }

    public double k() {
        return J * 0.0D - 0.300000011920929D;
    }

    public boolean a(fe paramfe, int paramInt) {

        // hMod: Attack of the cart
        BaseEntity attacker = new BaseEntity(paramfe);
        Minecart cart = new Minecart(this);

        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_DAMAGE, cart, attacker, paramInt)) {
            return true;
        }

        if ((l.z) || (G)) {
            return true;
        }
        c = (-c);
        b = 10;
        y();
        a += paramInt * 10;
        if (a > 40) {
            a(hg.ax.ba, 1, 0.0F);
            if (d == 1) {
                a(hr.au.bi, 1, 0.0F);
            } else if (d == 2) {
                a(hr.aB.bi, 1, 0.0F);
            }
            q();
        }
        return true;
    }

    public boolean c_() {
        return !G;
    }

    public void q() {
        // hMod: Destruction of the cart
        Minecart cart = new Minecart(this);
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_DESTROYED, cart);

        for (int i = 0; i < h_(); i++) {
            jl localjl = a(i);
            if (localjl != null) {
                float f1 = W.nextFloat() * 0.8F + 0.1F;
                float f2 = W.nextFloat() * 0.8F + 0.1F;
                float f3 = W.nextFloat() * 0.8F + 0.1F;

                while (localjl.a > 0) {
                    int j = W.nextInt(21) + 10;
                    if (j > localjl.a) {
                        j = localjl.a;
                    }
                    localjl.a -= j;

                    ic localic = new ic(l, p + f1, q + f2, r + f3, new jl(localjl.c, j, localjl.h()));
                    float f4 = 0.05F;
                    localic.s = ((float) W.nextGaussian() * f4);
                    localic.t = ((float) W.nextGaussian() * f4 + 0.2F);
                    localic.u = ((float) W.nextGaussian() * f4);
                    l.a(localic);
                }
            }
        }
        super.q();
    }

    public void b_() {
        // hMod: Update of the cart
        Minecart cart = new Minecart(this);
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_UPDATE, cart);

        if (b > 0) {
            b -= 1;
        }
        if (a > 0) {
            a -= 1;
        }

        if ((l.z) && (ao > 0)) {
            if (ao > 0) {
                double d1 = p + (ap - p) / ao;
                double d2 = q + (aq - q) / ao;
                double d4 = r + (ar - r) / ao;

                double d5 = as - v;
                while (d5 < -180.0D) {
                    d5 += 360.0D;
                }
                while (d5 >= 180.0D) {
                    d5 -= 360.0D;
                }
                v = (float) (v + d5 / ao);
                w = (float) (w + (at - w) / ao);

                ao -= 1;
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

        int i = iz.b(p);
        int j = iz.b(q);
        int k = iz.b(r);
        if (l.a(i, j - 1, k) == hr.aG.bi) {
            j--;
        }

        double d3 = 0.4D;
        int m = 0;

        double d5 = 0.0078125D;
        if (l.a(i, j, k) == hr.aG.bi) {
            bu localbu1 = g(p, q, r);
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

            int[][] arrayOfInt = an[n];

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

            if ((arrayOfInt[0][1] != 0) && (iz.b(p) - i == arrayOfInt[0][0]) && (iz.b(r) - k == arrayOfInt[0][2])) {
                a(p, q + arrayOfInt[0][1], r);
            } else if ((arrayOfInt[1][1] != 0) && (iz.b(p) - i == arrayOfInt[1][0]) && (iz.b(r) - k == arrayOfInt[1][2])) {
                a(p, q + arrayOfInt[1][1], r);
            }

            if (this.j != null) {
                s *= 0.996999979019165D;
                t *= 0.0D;
                u *= 0.996999979019165D;
            } else {
                if (d == 2) {
                    d21 = iz.a(f * f + ak * ak);
                    if (d21 > 0.01D) {
                        m = 1;
                        f /= d21;
                        ak /= d21;
                        double d23 = 0.04D;
                        s *= 0.800000011920929D;
                        t *= 0.0D;
                        u *= 0.800000011920929D;
                        s += f * d23;
                        u += ak * d23;
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

            bu localbu2 = g(p, q, r);
            if ((localbu2 != null) && (localbu1 != null)) {
                double d22 = (localbu1.b - localbu2.b) * 0.05D;

                d13 = Math.sqrt(s * s + u * u);
                if (d13 > 0.0D) {
                    s = (s / d13 * (d13 + d22));
                    u = (u / d13 * (d13 + d22));
                }
                a(p, localbu2.b, r);
            }

            int i2 = iz.b(p);
            int i3 = iz.b(r);
            if ((i2 != i) || (i3 != k)) {
                d13 = Math.sqrt(s * s + u * u);

                s = (d13 * (i2 - i));
                u = (d13 * (i3 - k));
            }

            if (d == 2) {
                double d24 = iz.a(f * f + ak * ak);
                if ((d24 > 0.01D) && (s * s + u * u > 0.001D)) {
                    f /= d24;
                    ak /= d24;

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
            if (am) {
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
            am = (!am);
        }
        b(v, w);

        List localList = l.b(this, z.b(0.2000000029802322D, 0.0D, 0.2000000029802322D));
        if ((localList != null) && (localList.size() > 0)) {
            for (int i1 = 0; i1 < localList.size(); i1++) {
                fe localfe = (fe) localList.get(i1);
                if ((localfe != this.j) && (localfe.z()) && ((localfe instanceof lw))) {
                    localfe.c(this);
                }
            }

        }

        if ((this.j != null)
                && (this.j.G)) {
            this.j = null;
        }

        if ((m != 0) && (W.nextInt(4) == 0)) {
            e -= 1;
            if (e < 0) {
                f = (this.ak = 0.0D);
            }
            l.a("largesmoke", p, q + 0.8D, r, 0.0D, 0.0D, 0.0D);
        }
    }

    public bu g(double paramDouble1, double paramDouble2, double paramDouble3) {
        int i = iz.b(paramDouble1);
        int j = iz.b(paramDouble2);
        int k = iz.b(paramDouble3);

        // hMod: Change of the cart
        Minecart minecart = new Minecart(this);
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, minecart, i, j, k);

        if (l.a(i, j - 1, k) == hr.aG.bi) {
            j--;
        }

        if (l.a(i, j, k) == hr.aG.bi) {
            int m = l.b(i, j, k);
            paramDouble2 = j;
            if ((m >= 2) && (m <= 5)) {
                paramDouble2 = j + 1;
            }

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
            if (d9 < 0.0D) {
                paramDouble2 += 1.0D;
            }
            if (d9 > 0.0D) {
                paramDouble2 += 0.5D;
            }
            return bu.b(paramDouble1, paramDouble2, paramDouble3);
        }
        return null;
    }

    protected void a(ah paramah) {
        paramah.a("Type", d);

        if (d == 2) {
            paramah.a("PushX", f);
            paramah.a("PushZ", ak);
            paramah.a("Fuel", (short) e);
        } else if (d == 1) {
            fh localfh = new fh();

            for (int i = 0; i < al.length; i++) {
                if (al[i] != null) {
                    ah localah = new ah();
                    localah.a("Slot", (byte) i);
                    al[i].a(localah);
                    localfh.a(localah);
                }
            }
            paramah.a("Items", localfh);
        }
    }

    protected void b(ah paramah) {
        d = paramah.d("Type");
        if (d == 2) {
            f = paramah.g("PushX");
            ak = paramah.g("PushZ");
            e = paramah.c("Fuel");
        } else if (d == 1) {
            fh localfh = paramah.k("Items");
            al = new jl[h_()];
            for (int i = 0; i < localfh.b(); i++) {
                ah localah = (ah) localfh.a(i);
                int j = localah.b("Slot") & 0xFF;
                if ((j < 0) || (j >= al.length)) {
                    continue;
                }
                al[j] = new jl(localah);
            }
        }
    }

    public void c(fe paramfe) {
        if (l.z) {
            return;
        }

        if (paramfe == j) {
            return;
        }
        // hMod: Collision of a cart
        Minecart cart = new Minecart(this);
        BaseEntity baseEntity = new BaseEntity(paramfe);
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_COLLISION, cart, baseEntity)) {
            return;
        }

        if (((paramfe instanceof mj)) && (!(paramfe instanceof hl)) && (d == 0) && (s * s + u * u > 0.01D)
                && (j == null) && (paramfe.k == null)) {
            paramfe.e(this);
        }

        double d1 = paramfe.p - p;
        double d2 = paramfe.r - r;

        double d3 = d1 * d1 + d2 * d2;
        if (d3 >= 9.999999747378752E-005D) {
            d3 = iz.a(d3);
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

            if ((paramfe instanceof lw)) {
                double d5 = paramfe.s + s;
                double d6 = paramfe.u + u;

                if ((((lw) paramfe).d == 2) && (d != 2)) {
                    s *= 0.2000000029802322D;
                    u *= 0.2000000029802322D;
                    f(paramfe.s - d1, 0.0D, paramfe.u - d2);
                    paramfe.s *= 0.699999988079071D;
                    paramfe.u *= 0.699999988079071D;
                } else if ((((lw) paramfe).d != 2) && (d == 2)) {
                    paramfe.s *= 0.2000000029802322D;
                    paramfe.u *= 0.2000000029802322D;
                    paramfe.f(s + d1, 0.0D, u + d2);
                    s *= 0.699999988079071D;
                    u *= 0.699999988079071D;
                } else {
                    d5 /= 2.0D;
                    d6 /= 2.0D;
                    s *= 0.2000000029802322D;
                    u *= 0.2000000029802322D;
                    f(d5 - d1, 0.0D, d6 - d2);
                    paramfe.s *= 0.2000000029802322D;
                    paramfe.u *= 0.2000000029802322D;
                    paramfe.f(d5 + d1, 0.0D, d6 + d2);
                }
            } else {
                f(-d1, 0.0D, -d2);
                paramfe.f(d1 / 4.0D, 0.0D, d2 / 4.0D);
            }
        }
    }

    public int h_() {
        return 27;
    }

    public jl a(int paramInt) {
        return al[paramInt];
    }

    public jl b(int paramInt1, int paramInt2) {
        if (al[paramInt1] != null) {
            if (al[paramInt1].a <= paramInt2) {
                jl localjl = al[paramInt1];
                al[paramInt1] = null;
                return localjl;
            }
            jl localjl = al[paramInt1].a(paramInt2);
            if (al[paramInt1].a == 0) {
                al[paramInt1] = null;
            }
            return localjl;
        }

        return null;
    }

    public void a(int paramInt, jl paramjl) {
        al[paramInt] = paramjl;
        if ((paramjl != null) && (paramjl.a > c())) {
            paramjl.a = c();
        }
    }

    public String b() {
        return getName(); //hMod: override with getName()
    }

    public int c() {
        return 64;
    }

    public void d() {
    }

    public boolean a(hl paramhl) {
        // hMod: Entering the cart
        Minecart cart = new Minecart(this);
        HumanEntity player = new HumanEntity(paramhl);
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_ENTERED, cart, player);

        if (d == 0) {
            if ((j != null) && ((j instanceof hl)) && (j != paramhl)) {
                return true;
            }
            if (!l.z) {
                paramhl.e(this);
            }
        } else if (d == 1) {
            if (!l.z) {
                // hMod cast this down to fix decompiler error.
                paramhl.a((mn) this);
            }
        } else if (d == 2) {
            jl localjl = paramhl.an.e();
            if ((localjl != null) && (localjl.c == hg.k.ba)) {
                if (--localjl.a == 0) {
                    paramhl.an.a(paramhl.an.c, null);
                }
                e += 1200;
            }

            f = (p - paramhl.p);
            ak = (r - paramhl.r);
        }
        return true;
    }

    public boolean a_(hl paramhl) {
        if (G) {
            return false;
        }
        return paramhl.b(this) <= 64.0D;
    }
}
