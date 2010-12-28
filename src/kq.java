// hMod: This class must extend Container<obfuscated_item> and implement the various methods within. They will not all be marked individually.

import java.util.List;
import java.util.Random;

public class kq extends ep
        implements lg, Container<il> {

    // hMod: need to be public!
    public il[] ak = new il[36];
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
    private String name = "Minecart";

    @Override
    public il[] getContents() {
        return ak;
    }

    @Override
    public void setContents(il[] values) {
        ak = values;
    }

    public il getContentsAt(int index) {
        return a(index);
    }

    public void setContentsAt(int index, il value) {
        a(index, value);
    }

    public int getContentsSize() {
        return a();
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        name = value;
    }

    public kq(ff paramff) {
        super(paramff);
        this.i = true;
        a(0.98F, 0.7F);
        this.H = (this.J / 2.0F);
        this.M = false;
    }

    public el d(ep paramep) {
        return paramep.z;
    }

    public el q() {
        return null;
    }

    public boolean v() {
        return true;
    }

    public kq(ff paramff, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt) {
        this(paramff);
        a(paramDouble1, paramDouble2 + this.H, paramDouble3);

        this.s = 0.0D;
        this.t = 0.0D;
        this.u = 0.0D;

        this.m = paramDouble1;
        this.n = paramDouble2;
        this.o = paramDouble3;
        this.d = paramInt;

        // hMod: Creation of the cart
        Minecart cart = new Minecart(this);
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_CREATE, cart);
    }

    public double j() {
        return this.J * 0.0D - 0.300000011920929D;
    }

    public boolean a(ep paramep, int paramInt) {
        // hMod: Attack of the cart
        BaseEntity attacker = new BaseEntity(paramep);
        Minecart cart = new Minecart(this);

        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_DAMAGE, cart, attacker, paramInt)) {
            return true;
        }

        if ((this.l.z) || (this.G)) {
            return true;
        }
        this.c = (-this.c);
        this.b = 10;
        u();
        this.a += paramInt * 10;
        if (this.a > 40) {
            a(gm.ax.aW, 1, 0.0F);
            if (this.d == 1) {
                a(gv.au.bh, 1, 0.0F);
            } else if (this.d == 2) {
                a(gv.aB.bh, 1, 0.0F);
            }
            l();
        }
        return true;
    }

    public boolean c_() {
        return !this.G;
    }

    public void l() {
        // hMod: Destruction of the cart
        Minecart cart = new Minecart(this);
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_DESTROYED, cart);

        for (int i = 0; i < a(); i++) {
            il localik = a(i);
            if (localik != null) {
                float f1 = this.W.nextFloat() * 0.8F + 0.1F;
                float f2 = this.W.nextFloat() * 0.8F + 0.1F;
                float f3 = this.W.nextFloat() * 0.8F + 0.1F;

                while (localik.a > 0) {
                    int j = this.W.nextInt(21) + 10;
                    if (j > localik.a) {
                        j = localik.a;
                    }
                    localik.a -= j;

                    hf localhe = new hf(this.l, this.p + f1, this.q + f2, this.r + f3, new il(localik.c, j, localik.d));
                    float f4 = 0.05F;
                    localhe.s = ((float) this.W.nextGaussian() * f4);
                    localhe.t = ((float) this.W.nextGaussian() * f4 + 0.2F);
                    localhe.u = ((float) this.W.nextGaussian() * f4);
                    this.l.a(localhe);
                }
            }
        }
        super.l();
    }

    public void b_() {
        // hMod: Update of the cart
        Minecart cart = new Minecart(this);
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_UPDATE, cart);

        if (this.b > 0) {
            this.b -= 1;
        }
        if (this.a > 0) {
            this.a -= 1;
        }

        if ((this.l.z) && (this.an > 0)) {
            if (this.an > 0) {
                double d1 = this.p + (this.ao - this.p) / this.an;
                double d2 = this.q + (this.ap - this.q) / this.an;
                double d4 = this.r + (this.aq - this.r) / this.an;

                double d5 = this.ar - this.v;
                while (d5 < -180.0D) {
                    d5 += 360.0D;
                }
                while (d5 >= 180.0D) {
                    d5 -= 360.0D;
                }
                this.v = (float) (this.v + d5 / this.an);
                this.w = (float) (this.w + (this.as - this.w) / this.an);

                this.an -= 1;
                a(d1, d2, d4);
                b(this.v, this.w);
            } else {
                a(this.p, this.q, this.r);
                b(this.v, this.w);
            }

            return;
        }
        this.m = this.p;
        this.n = this.q;
        this.o = this.r;

        this.t -= 0.03999999910593033D;

        int i = ic.b(this.p);
        int j = ic.b(this.q);
        int k = ic.b(this.r);
        if (this.l.a(i, j - 1, k) == gv.aG.bh) {
            j--;
        }

        double d3 = 0.4D;
        int m = 0;

        double d5 = 0.0078125D;
        if (this.l.a(i, j, k) == gv.aG.bh) {
            bn localbn1 = g(this.p, this.q, this.r);
            int n = this.l.b(i, j, k);
            this.q = j;
            if ((n >= 2) && (n <= 5)) {
                this.q = (j + 1);
            }

            if (n == 2) {
                this.s -= d5;
            }
            if (n == 3) {
                this.s += d5;
            }
            if (n == 4) {
                this.u += d5;
            }
            if (n == 5) {
                this.u -= d5;
            }

            int[][] arrayOfInt = am[n];

            double d8 = arrayOfInt[1][0] - arrayOfInt[0][0];
            double d10 = arrayOfInt[1][2] - arrayOfInt[0][2];
            double d11 = Math.sqrt(d8 * d8 + d10 * d10);

            double d12 = this.s * d8 + this.u * d10;
            if (d12 < 0.0D) {
                d8 = -d8;
                d10 = -d10;
            }

            double d13 = Math.sqrt(this.s * this.s + this.u * this.u);

            this.s = (d13 * d8 / d11);
            this.u = (d13 * d10 / d11);

            double d14 = 0.0D;
            double d15 = i + 0.5D + arrayOfInt[0][0] * 0.5D;
            double d16 = k + 0.5D + arrayOfInt[0][2] * 0.5D;
            double d17 = i + 0.5D + arrayOfInt[1][0] * 0.5D;
            double d18 = k + 0.5D + arrayOfInt[1][2] * 0.5D;

            d8 = d17 - d15;
            d10 = d18 - d16;
            double d21;
            if (d8 == 0.0D) {
                this.p = (i + 0.5D);
                d14 = this.r - k;
            } else if (d10 == 0.0D) {
                this.r = (k + 0.5D);
                d14 = this.p - i;
            } else {
                double d19 = this.p - d15;
                double d20 = this.r - d16;

                d21 = (d19 * d8 + d20 * d10) * 2.0D;
                d14 = d21;
            }

            this.p = (d15 + d8 * d14);
            this.r = (d16 + d10 * d14);

            a(this.p, this.q + this.H, this.r);

            double d19 = this.s;
            double d20 = this.u;
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

            if ((arrayOfInt[0][1] != 0) && (ic.b(this.p) - i == arrayOfInt[0][0]) && (ic.b(this.r) - k == arrayOfInt[0][2])) {
                a(this.p, this.q + arrayOfInt[0][1], this.r);
            } else if ((arrayOfInt[1][1] != 0) && (ic.b(this.p) - i == arrayOfInt[1][0]) && (ic.b(this.r) - k == arrayOfInt[1][2])) {
                a(this.p, this.q + arrayOfInt[1][1], this.r);
            }

            if (this.j != null) {
                this.s *= 0.996999979019165D;
                this.t *= 0.0D;
                this.u *= 0.996999979019165D;
            } else {
                if (this.d == 2) {
                    d21 = ic.a(this.f * this.f + this.aj * this.aj);
                    if (d21 > 0.01D) {
                        m = 1;
                        this.f /= d21;
                        this.aj /= d21;
                        double d23 = 0.04D;
                        this.s *= 0.800000011920929D;
                        this.t *= 0.0D;
                        this.u *= 0.800000011920929D;
                        this.s += this.f * d23;
                        this.u += this.aj * d23;
                    } else {
                        this.s *= 0.8999999761581421D;
                        this.t *= 0.0D;
                        this.u *= 0.8999999761581421D;
                    }
                }
                this.s *= 0.9599999785423279D;
                this.t *= 0.0D;
                this.u *= 0.9599999785423279D;
            }

            bn localbn2 = g(this.p, this.q, this.r);
            if ((localbn2 != null) && (localbn1 != null)) {
                double d22 = (localbn1.b - localbn2.b) * 0.05D;

                d13 = Math.sqrt(this.s * this.s + this.u * this.u);
                if (d13 > 0.0D) {
                    this.s = (this.s / d13 * (d13 + d22));
                    this.u = (this.u / d13 * (d13 + d22));
                }
                a(this.p, localbn2.b, this.r);
            }

            int i2 = ic.b(this.p);
            int i3 = ic.b(this.r);
            if ((i2 != i) || (i3 != k)) {
                d13 = Math.sqrt(this.s * this.s + this.u * this.u);

                this.s = (d13 * (i2 - i));
                this.u = (d13 * (i3 - k));
            }

            if (this.d == 2) {
                double d24 = ic.a(this.f * this.f + this.aj * this.aj);
                if ((d24 > 0.01D) && (this.s * this.s + this.u * this.u > 0.001D)) {
                    this.f /= d24;
                    this.aj /= d24;

                    if (this.f * this.s + this.aj * this.u < 0.0D) {
                        this.f = 0.0D;
                        this.aj = 0.0D;
                    } else {
                        this.f = this.s;
                        this.aj = this.u;
                    }
                }
            }
        } else {
            if (this.s < -d3) {
                this.s = (-d3);
            }
            if (this.s > d3) {
                this.s = d3;
            }
            if (this.u < -d3) {
                this.u = (-d3);
            }
            if (this.u > d3) {
                this.u = d3;
            }
            if (this.A) {
                this.s *= 0.5D;
                this.t *= 0.5D;
                this.u *= 0.5D;
            }
            c(this.s, this.t, this.u);

            if (!this.A) {
                this.s *= 0.949999988079071D;
                this.t *= 0.949999988079071D;
                this.u *= 0.949999988079071D;
            }
        }

        this.w = 0.0F;
        double d6 = this.m - this.p;
        double d7 = this.o - this.r;
        if (d6 * d6 + d7 * d7 > 0.001D) {
            this.v = (float) (Math.atan2(d7, d6) * 180.0D / 3.141592653589793D);
            if (this.al) {
                this.v += 180.0F;
            }
        }

        double d9 = this.v - this.x;
        while (d9 >= 180.0D) {
            d9 -= 360.0D;
        }
        while (d9 < -180.0D) {
            d9 += 360.0D;
        }
        if ((d9 < -170.0D) || (d9 >= 170.0D)) {
            this.v += 180.0F;
            this.al = (!this.al);
        }
        b(this.v, this.w);

        List localList = this.l.b(this, this.z.b(0.2000000029802322D, 0.0D, 0.2000000029802322D));
        if ((localList != null) && (localList.size() > 0)) {
            for (int i1 = 0; i1 < localList.size(); i1++) {
                ep localep = (ep) localList.get(i1);
                if ((localep != this.j) && (localep.v()) && ((localep instanceof kq))) {
                    localep.c(this);
                }
            }

        }

        if ((this.j != null)
                && (this.j.G)) {
            this.j = null;
        }

        if ((m != 0) && (this.W.nextInt(4) == 0)) {
            this.e -= 1;
            if (this.e < 0) {
                this.f = (this.aj = 0.0D);
            }
            this.l.a("largesmoke", this.p, this.q + 0.8D, this.r, 0.0D, 0.0D, 0.0D);
        }
    }

    public bn g(double paramDouble1, double paramDouble2, double paramDouble3) {
        int i = ic.b(paramDouble1);
        int j = ic.b(paramDouble2);
        int k = ic.b(paramDouble3);

        // hMod: Change of the cart
        Minecart minecart = new Minecart(this);
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, minecart, i, j, k);

        if (this.l.a(i, j - 1, k) == gv.aG.bh) {
            j--;
        }

        if (this.l.a(i, j, k) == gv.aG.bh) {
            int m = this.l.b(i, j, k);
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
            return bn.b(paramDouble1, paramDouble2, paramDouble3);
        }
        return null;
    }

    protected void a(ad paramad) {
        paramad.a("Type", this.d);

        if (this.d == 2) {
            paramad.a("PushX", this.f);
            paramad.a("PushZ", this.aj);
            paramad.a("Fuel", (short) this.e);
        } else if (this.d == 1) {
            es locales = new es();

            for (int i = 0; i < this.ak.length; i++) {
                if (this.ak[i] != null) {
                    ad localad = new ad();
                    localad.a("Slot", (byte) i);
                    this.ak[i].a(localad);
                    locales.a(localad);
                }
            }
            paramad.a("Items", locales);
        }
    }

    protected void b(ad paramad) {
        this.d = paramad.d("Type");
        if (this.d == 2) {
            this.f = paramad.g("PushX");
            this.aj = paramad.g("PushZ");
            this.e = paramad.c("Fuel");
        } else if (this.d == 1) {
            es locales = paramad.k("Items");
            this.ak = new il[a()];
            for (int i = 0; i < locales.b(); i++) {
                ad localad = (ad) locales.a(i);
                int j = localad.b("Slot") & 0xFF;
                if ((j < 0) || (j >= this.ak.length)) {
                    continue;
                }
                this.ak[j] = new il(localad);
            }
        }
    }

    public void c(ep paramep) {
        if (this.l.z) {
            return;
        }

        if (paramep == this.j) {
            return;
        }

        // hMod: Collision of a cart
        Minecart cart = new Minecart(this);
        BaseEntity baseEntity = new BaseEntity(paramep);
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_COLLISION, cart, baseEntity)) {
            return;
        }
        
        if (((paramep instanceof lc)) && (!(paramep instanceof gq)) && (this.d == 0) && (this.s * this.s + this.u * this.u > 0.01D)
                && (this.j == null) && (paramep.k == null)) {
            paramep.e(this);
        }

        double d1 = paramep.p - this.p;
        double d2 = paramep.r - this.r;

        double d3 = d1 * d1 + d2 * d2;
        if (d3 >= 9.999999747378752E-005D) {
            d3 = ic.a(d3);
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

            d1 *= (1.0F - this.U);
            d2 *= (1.0F - this.U);
            d1 *= 0.5D;
            d2 *= 0.5D;

            if ((paramep instanceof kq)) {
                double d5 = paramep.s + this.s;
                double d6 = paramep.u + this.u;

                if ((((kq) paramep).d == 2) && (this.d != 2)) {
                    this.s *= 0.2000000029802322D;
                    this.u *= 0.2000000029802322D;
                    f(paramep.s - d1, 0.0D, paramep.u - d2);
                    paramep.s *= 0.699999988079071D;
                    paramep.u *= 0.699999988079071D;
                } else if ((((kq) paramep).d != 2) && (this.d == 2)) {
                    paramep.s *= 0.2000000029802322D;
                    paramep.u *= 0.2000000029802322D;
                    paramep.f(this.s + d1, 0.0D, this.u + d2);
                    this.s *= 0.699999988079071D;
                    this.u *= 0.699999988079071D;
                } else {
                    d5 /= 2.0D;
                    d6 /= 2.0D;
                    this.s *= 0.2000000029802322D;
                    this.u *= 0.2000000029802322D;
                    f(d5 - d1, 0.0D, d6 - d2);
                    paramep.s *= 0.2000000029802322D;
                    paramep.u *= 0.2000000029802322D;
                    paramep.f(d5 + d1, 0.0D, d6 + d2);
                }
            } else {
                f(-d1, 0.0D, -d2);
                paramep.f(d1 / 4.0D, 0.0D, d2 / 4.0D);
            }
        }
    }

    public int a() {
        return 27;
    }

    public il a(int paramInt) {
        return this.ak[paramInt];
    }

    public il a(int paramInt1, int paramInt2) {
        if (this.ak[paramInt1] != null) {
            if (this.ak[paramInt1].a <= paramInt2) {
                il localik = this.ak[paramInt1];
                this.ak[paramInt1] = null;
                return localik;
            }
            il localik = this.ak[paramInt1].a(paramInt2);
            if (this.ak[paramInt1].a == 0) {
                this.ak[paramInt1] = null;
            }
            return localik;
        }

        return null;
    }

    public void a(int paramInt, il paramik) {
        this.ak[paramInt] = paramik;
        if ((paramik != null) && (paramik.a > c())) {
            paramik.a = c();
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

    public boolean a(gq paramgp) {
        // hMod: Entering the cart
        Minecart cart = new Minecart(this);
        HumanEntity player = new HumanEntity(paramgp);
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_ENTERED, cart, player);
        
        if (this.d == 0) {
            if ((this.j != null) && ((this.j instanceof gq)) && (this.j != paramgp)) {
                return true;
            }
            if (!this.l.z) {
                paramgp.e(this);
            }
        } else if (this.d == 1) {
            if (!this.l.z) {
                // hMod cast this down to fix decompiler error.
                paramgp.a((lg)this);
            }
        } else if (this.d == 2) {
            il localik = paramgp.an.e();
            if ((localik != null) && (localik.c == gm.k.aW)) {
                if (--localik.a == 0) {
                    paramgp.an.a(paramgp.an.c, null);
                }
                this.e += 1200;
            }

            this.f = (this.p - paramgp.p);
            this.aj = (this.r - paramgp.r);
        }
        return true;
    }

    public boolean a_(gq paramgp) {
        if (this.G) {
            return false;
        }
        return paramgp.b(this) <= 64.0D;
    }
}
