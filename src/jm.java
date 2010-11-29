
import java.util.List;

public class jm extends dx implements kc {

    public hl[] ak = new hl[36];
    public int a = 0;
    public int b = 0;
    public int c = 1;
    private boolean al = false;
    public int d;
    public int e;
    public double f;
    public double aj;
    private static final int[][][] am = {{{0, 0, -1}, {0, 0, 1}}, {{-1, 0, 0}, {1, 0, 0}}, {{-1, -1, 0}, {1, 0, 0}},
        {{-1, 0, 0}, {1, -1, 0}}, {{0, 0, -1}, {0, -1, 1}}, {{0, -1, -1}, {0, 0, 1}}, {{0, 0, 1}, {1, 0, 0}},
        {{0, 0, 1}, {-1, 0, 0}}, {{0, 0, -1}, {-1, 0, 0}}, {{0, 0, -1}, {1, 0, 0}}};
    private int an;
    private double ao;
    private double ap;
    private double aq;
    private double ar;
    private double as;

    public jm(eo parameo) {
        super(parameo);
        this.i = true;
        a(0.98F, 0.7F);
        this.H = (this.J / 2.0F);
        this.M = false;
    }

    @Override
    public du d(dx paramdx) {
        return paramdx.z;
    }

    @Override
    public du q() {
        return null;
    }

    @Override
    public boolean v() {
        return true;
    }

    /**
     * Create Minecart
     * 
     * @param parameo world object
     * @param paramDouble1 X
     * @param paramDouble2 Y
     * @param paramDouble3 Z
     * @param paramInt Not sure
     */
    public jm(eo parameo, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt) {
        this(parameo);

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
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_CREATE, new Object[]{cart});
    }

    @Override
    public double j() {
        return this.J * 0.0D - 0.300000011920929D;
    }

    /**
     * Attack Minecart
     * 
     * @param paramdx attacker
     * @param paramInt damage
     * @return
     */
    @Override
    public boolean a(dx paramdx, int paramInt) {
        // hMod: Attack of the cart
        BaseEntity attacker = new BaseEntity(paramdx);
        Minecart cart = new Minecart(this);
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_DAMAGE, new Object[]{cart, attacker, paramInt}))
            return true;

        if (this.l.z) {
            return true;
        }
        this.c = (-this.c);
        this.b = 10;
        u();
        this.a += paramInt * 10;
        if (this.a > 40) {
            a(fu.ax.aW, 1, 0.0F);
            if (this.d == 1) {
                a(ga.au.bh, 1, 0.0F);
            } else if (this.d == 2) {
                a(ga.aB.bh, 1, 0.0F);
            }
            l();
        }
        return true;
    }

    @Override
    public boolean c_() {
        return !this.G;
    }

    @Override
    public void l() {
        // hMod: Destruction of the cart
        Minecart cart = new Minecart(this);
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_DESTROYED, new Object[]{cart});

        for (int i = 0; i < a(); i++) {
            hl localhl = a(i);
            if (localhl != null) {
                float f1 = this.W.nextFloat() * 0.8F + 0.1F;
                float f2 = this.W.nextFloat() * 0.8F + 0.1F;
                float f3 = this.W.nextFloat() * 0.8F + 0.1F;

                while (localhl.a > 0) {
                    int j = this.W.nextInt(21) + 10;
                    if (j > localhl.a) {
                        j = localhl.a;
                    }
                    localhl.a -= j;

                    gj localgj = new gj(this.l, this.p + f1, this.q + f2, this.r + f3, new hl(localhl.c, j, localhl.d));
                    float f4 = 0.05F;
                    localgj.s = ((float) this.W.nextGaussian() * f4);
                    localgj.t = ((float) this.W.nextGaussian() * f4 + 0.2F);
                    localgj.u = ((float) this.W.nextGaussian() * f4);
                    this.l.a(localgj);
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

        int i = hf.b(this.p);
        int j = hf.b(this.q);
        int k = hf.b(this.r);
        if (this.l.a(i, j - 1, k) == ga.aG.bh) {
            j--;
        }

        double d3 = 0.4D;
        int m = 0;

        double d5 = 0.0078125D;
        if (this.l.a(i, j, k) == ga.aG.bh) {
            bd localbd1 = g(this.p, this.q, this.r);
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

            if ((arrayOfInt[0][1] != 0) && (hf.b(this.p) - i == arrayOfInt[0][0]) && (hf.b(this.r) - k == arrayOfInt[0][2])) {
                a(this.p, this.q + arrayOfInt[0][1], this.r);
            } else if ((arrayOfInt[1][1] != 0) && (hf.b(this.p) - i == arrayOfInt[1][0]) && (hf.b(this.r) - k == arrayOfInt[1][2])) {
                a(this.p, this.q + arrayOfInt[1][1], this.r);
            }

            if (this.j != null) {
                this.s *= 0.996999979019165D;
                this.t *= 0.0D;
                this.u *= 0.996999979019165D;
            } else {
                if (this.d == 2) {
                    d21 = hf.a(this.f * this.f + this.aj * this.aj);
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

            bd localbd2 = g(this.p, this.q, this.r);
            if ((localbd2 != null) && (localbd1 != null)) {
                double d22 = (localbd1.b - localbd2.b) * 0.05D;

                d13 = Math.sqrt(this.s * this.s + this.u * this.u);
                if (d13 > 0.0D) {
                    this.s = (this.s / d13 * (d13 + d22));
                    this.u = (this.u / d13 * (d13 + d22));
                }
                a(this.p, localbd2.b, this.r);
            }

            int i2 = hf.b(this.p);
            int i3 = hf.b(this.r);
            if ((i2 != i) || (i3 != k)) {
                d13 = Math.sqrt(this.s * this.s + this.u * this.u);

                this.s = (d13 * (i2 - i));
                this.u = (d13 * (i3 - k));
            }

            if (this.d == 2) {
                double d24 = hf.a(this.f * this.f + this.aj * this.aj);
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
                dx localdx = (dx) localList.get(i1);
                if ((localdx != this.j) && (localdx.v()) && ((localdx instanceof jm))) {
                    localdx.c(this);
                }
            }

        }

        if ((this.j != null) && (this.j.G)) {
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

    /**
     * set position?
     * 
     * @param paramDouble1
     * @param paramDouble2
     * @param paramDouble3
     * @return
     */
    public bd g(double paramDouble1, double paramDouble2, double paramDouble3) {
        int i = hf.b(paramDouble1);
        int j = hf.b(paramDouble2);
        int k = hf.b(paramDouble3);
        // hMod: Change of the cart
        Minecart minecart = new Minecart(this);
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, new Object[]{minecart,i,j,k});
        if (this.l.a(i, j - 1, k) == ga.aG.bh) {
            j--;
        }

        if (this.l.a(i, j, k) == ga.aG.bh) {
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
            return bd.b(paramDouble1, paramDouble2, paramDouble3);
        }
        return null;
    }

    @Override
    protected void a(v paramv) {
        paramv.a("Type", this.d);

        if (this.d == 2) {
            paramv.a("PushX", this.f);
            paramv.a("PushZ", this.aj);
            paramv.a("Fuel", (short) this.e);
        } else if (this.d == 1) {
            eb localeb = new eb();

            for (int i = 0; i < this.ak.length; i++) {
                if (this.ak[i] != null) {
                    v localv = new v();
                    localv.a("Slot", (byte) i);
                    this.ak[i].a(localv);
                    localeb.a(localv);
                }
            }
            paramv.a("Items", localeb);
        }
    }

    @Override
    protected void b(v paramv) {
        this.d = paramv.d("Type");
        if (this.d == 2) {
            this.f = paramv.g("PushX");
            this.aj = paramv.g("PushZ");
            this.e = paramv.c("Fuel");
        } else if (this.d == 1) {
            eb localeb = paramv.k("Items");
            this.ak = new hl[a()];
            for (int i = 0; i < localeb.b(); i++) {
                v localv = (v) localeb.a(i);
                int j = localv.b("Slot") & 0xFF;
                if ((j < 0) || (j >= this.ak.length)) {
                    continue;
                }
                this.ak[j] = new hl(localv);
            }
        }
    }

    /**
     * Minecart Collision
     * 
     * @param paramdx
     */
    @Override
    public void c(dx paramdx) {
        if (this.l.z) {
            return;
        }

        if (paramdx == this.j) {
            return;
        }
        // hMod: Collision of a cart
        Minecart cart = new Minecart(this);
        BaseEntity baseEntity = new BaseEntity(paramdx);
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_COLLISION, new Object[]{cart, baseEntity});

        if (((paramdx instanceof jy)) && (!(paramdx instanceof fx)) && (this.d == 0) && (this.s * this.s + this.u * this.u > 0.01D)
                && (this.j == null) && (paramdx.k == null)) {
            paramdx.e(this);
        }

        double d1 = paramdx.p - this.p;
        double d2 = paramdx.r - this.r;

        double d3 = d1 * d1 + d2 * d2;
        if (d3 >= 9.999999747378752E-05D) {
            d3 = hf.a(d3);
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

            if ((paramdx instanceof jm)) {
                double d5 = paramdx.s + this.s;
                double d6 = paramdx.u + this.u;

                if ((((jm) paramdx).d == 2) && (this.d != 2)) {
                    this.s *= 0.2000000029802322D;
                    this.u *= 0.2000000029802322D;
                    f(paramdx.s - d1, 0.0D, paramdx.u - d2);
                    paramdx.s *= 0.699999988079071D;
                    paramdx.u *= 0.699999988079071D;
                } else if ((((jm) paramdx).d != 2) && (this.d == 2)) {
                    paramdx.s *= 0.2000000029802322D;
                    paramdx.u *= 0.2000000029802322D;
                    paramdx.f(this.s + d1, 0.0D, this.u + d2);
                    this.s *= 0.699999988079071D;
                    this.u *= 0.699999988079071D;
                } else {
                    d5 /= 2.0D;
                    d6 /= 2.0D;
                    this.s *= 0.2000000029802322D;
                    this.u *= 0.2000000029802322D;
                    f(d5 - d1, 0.0D, d6 - d2);
                    paramdx.s *= 0.2000000029802322D;
                    paramdx.u *= 0.2000000029802322D;
                    paramdx.f(d5 + d1, 0.0D, d6 + d2);
                }
            } else {
                f(-d1, 0.0D, -d2);
                paramdx.f(d1 / 4.0D, 0.0D, d2 / 4.0D);
            }
        }
    }

    @Override
    public int a() {
        return 27;
    }

    @Override
    public hl a(int paramInt) {
        return this.ak[paramInt];
    }

    /**
     * Runs when adding player to cart
     * 
     * @param paramfx Player entity
     * @return true
     */
    @Override
    public boolean a(fx paramfx) {
        // hMod: Entering the cart
        Minecart cart = new Minecart(this);
        HumanEntity player = new HumanEntity(paramfx);
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_ENTERED, new Object[]{cart, player});

        if (this.d == 0) {
            if ((this.j != null) && ((this.j instanceof fx)) && (this.j != paramfx)) {
                return true;
            }
            if (!this.l.z) {
                paramfx.e(this);
            }
        } else if (this.d == 1) {
            paramfx.a((kc) this);
        } else if (this.d == 2) {
            hl localhl = paramfx.al.b();
            if ((localhl != null) && (localhl.c == fu.k.aW)) {
                if (--localhl.a == 0) {
                    paramfx.al.a(paramfx.al.d, null);
                }
                this.e += 1200;
            }

            this.f = (this.p - paramfx.p);
            this.aj = (this.r - paramfx.r);
        }
        return true;
    }
}
