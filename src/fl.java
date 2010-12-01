import java.util.List;

public class fl extends dy {
    public int a = 0;
    public int b = 0;
    public int c = 1;
    private int d;
    private double e;
    private double f;
    private double aj;
    private double ak;
    private double al;

    public fl(ep paramep) {
        super(paramep);
        this.i = true;
        a(1.5F, 0.6F);
        this.H = (this.J / 2.0F);
        this.M = false;
    }

    @Override
    public dv d(dy paramdy) {
        return paramdy.z;
    }

    @Override
    public dv q() {
        return this.z;
    }

    @Override
    public boolean v() {
        return true;
    }

    public fl(ep paramep, double paramDouble1, double paramDouble2, double paramDouble3) {
        this(paramep);
        a(paramDouble1, paramDouble2 + this.H, paramDouble3);

        this.s = 0.0D;
        this.t = 0.0D;
        this.u = 0.0D;

        this.m = paramDouble1;
        this.n = paramDouble2;
        this.o = paramDouble3;

        // hMod: Creation of the boat
        Boat boat = new Boat(this);
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_CREATE, new Object[]{boat});
    }

    @Override
    public double j() {
        return this.J * 0.0D - 0.300000011920929D;
    }

    @Override
    public boolean a(dy paramdy, int paramInt) {
        // hMod: Attack of the boat
        BaseEntity attacker = new BaseEntity(paramdy);
        Boat boat = new Boat(this);
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_DAMAGE, new Object[]{boat, attacker, paramInt})) {
            return true;
        }

        if (this.l.z) {
            return true;
        }
        this.c = (-this.c);
        this.b = 10;
        this.a += paramInt * 10;
        u();
        if (this.a > 40) {
            for (int i = 0; i < 3; i++) {
                a(gb.x.bh, 1, 0.0F);
            }
            for (int i = 0; i < 2; i++) {
                a(fv.B.aW, 1, 0.0F);
            }
            l();
        }
        return true;
    }

    // hMod: Destruction of the boat
    @Override
    public void l() {
        Boat boat = new Boat(this);
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_DESTROYED, new Object[]{boat});
        super.l();
    }

    @Override
    public boolean c_() {
        return !this.G;
    }

    @Override
    public void b_() {
        // hMod: Update of the boat
        Boat boat = new Boat(this);
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_UPDATE, new Object[]{boat});

        super.b_();
        if (this.b > 0) {
            this.b -= 1;
        }
        if (this.a > 0) {
            this.a -= 1;
        }
        this.m = this.p;
        this.n = this.q;
        this.o = this.r;

        int i = 5;
        double d1 = 0.0D;
        for (int j = 0; j < i; j++) {
            double d3 = this.z.b + (this.z.e - this.z.b) * (j + 0) / i - 0.125D;
            double d5 = this.z.b + (this.z.e - this.z.b) * (j + 1) / i - 0.125D;
            dv localdv = dv.b(this.z.a, d3, this.z.c, this.z.d, d5, this.z.f);
            if (this.l.b(localdv, jx.f)) {
                d1 += 1.0D / i;
            }
        }
        double d7;
        if (this.l.z) {
            if (this.d > 0) {
                double d2 = this.p + (this.e - this.p) / this.d;
                double d4 = this.q + (this.f - this.q) / this.d;
                double d6 = this.r + (this.aj - this.r) / this.d;

                d7 = this.ak - this.v;
                while (d7 < -180.0D) {
                    d7 += 360.0D;
                }
                while (d7 >= 180.0D) {
                    d7 -= 360.0D;
                }
                this.v = (float) (this.v + d7 / this.d);
                this.w = (float) (this.w + (this.al - this.w) / this.d);

                this.d -= 1;
                a(d2, d4, d6);
                b(this.v, this.w);
            } else {
                double d2 = this.p + this.s;
                double d4 = this.q + this.t;
                double d6 = this.r + this.u;
                a(d2, d4, d6);
                if (this.A) {
                    this.s *= 0.5D;
                    this.t *= 0.5D;
                    this.u *= 0.5D;
                }
                this.s *= 0.9900000095367432D;
                this.t *= 0.949999988079071D;
                this.u *= 0.9900000095367432D;
            }
            return;
        }

        double d2 = d1 * 2.0D - 1.0D;
        this.t += 0.03999999910593033D * d2;

        if (this.j != null) {
            this.s += this.j.s * 0.2D;
            this.u += this.j.u * 0.2D;
        }

        double d4 = 0.4D;

        if (this.s < -d4) {
            this.s = (-d4);
        }
        if (this.s > d4) {
            this.s = d4;
        }
        if (this.u < -d4) {
            this.u = (-d4);
        }
        if (this.u > d4) {
            this.u = d4;
        }
        if (this.A) {
            this.s *= 0.5D;
            this.t *= 0.5D;
            this.u *= 0.5D;
        }
        c(this.s, this.t, this.u);
        double d6 = Math.sqrt(this.s * this.s + this.u * this.u);
        if (d6 > 0.15D) {
            d7 = Math.cos(this.v * 3.141592653589793D / 180.0D);
            double d9 = Math.sin(this.v * 3.141592653589793D / 180.0D);

            for (int m = 0; m < 1.0D + d6 * 60.0D; m++) {
                double d11 = this.W.nextFloat() * 2.0F - 1.0F;

                double d13 = (this.W.nextInt(2) * 2 - 1) * 0.7D;
                double d14;
                double d15;
                if (this.W.nextBoolean()) {
                    d14 = this.p - d7 * d11 * 0.8D + d9 * d13;
                    d15 = this.r - d9 * d11 * 0.8D - d7 * d13;
                    this.l.a("splash", d14, this.q - 0.125D, d15, this.s, this.t, this.u);
                } else {
                    d14 = this.p + d7 + d9 * d11 * 0.7D;
                    d15 = this.r + d9 - d7 * d11 * 0.7D;
                    this.l.a("splash", d14, this.q - 0.125D, d15, this.s, this.t, this.u);
                }
            }
        }

        if ((this.B) && (d6 > 0.15D)) {
            if (!this.l.z) {
                l();
                for (int k = 0; k < 3; k++) {
                    a(gb.x.bh, 1, 0.0F);
                }
                for (int k = 0; k < 2; k++) {
                    a(fv.B.aW, 1, 0.0F);
                }
            }
        } else {
            this.s *= 0.9900000095367432D;
            this.t *= 0.949999988079071D;
            this.u *= 0.9900000095367432D;
        }

        this.w = 0.0F;
        double d8 = this.v;
        double d9 = this.m - this.p;
        double d10 = this.o - this.r;
        if (d9 * d9 + d10 * d10 > 0.001D) {
            d8 = (float) (Math.atan2(d10, d9) * 180.0D / 3.141592653589793D);
        }

        double d12 = d8 - this.v;
        while (d12 >= 180.0D) {
            d12 -= 360.0D;
        }
        while (d12 < -180.0D) {
            d12 += 360.0D;
        }
        if (d12 > 20.0D) {
            d12 = 20.0D;
        }
        if (d12 < -20.0D) {
            d12 = -20.0D;
        }

        this.v = (float) (this.v + d12);
        b(this.v, this.w);

        List localList = this.l.b(this, this.z.b(0.2000000029802322D, 0.0D, 0.2000000029802322D));
        if ((localList != null) && (localList.size() > 0)) {
            for (int n = 0; n < localList.size(); n++) {
                dy localdy = (dy) localList.get(n);
                if ((localdy != this.j) && (localdy.v()) && ((localdy instanceof fl))) {
                    localdy.c(this);
                }
            }
        }

        if ((this.j != null) && (this.j.G)) {
            this.j = null;
        }
    }

    @Override
    public void A() {
        if (this.j == null) {
            return;
        }

        double d1 = Math.cos(this.v * 3.141592653589793D / 180.0D) * 0.4D;
        double d2 = Math.sin(this.v * 3.141592653589793D / 180.0D) * 0.4D;
        this.j.a(this.p + d1, this.q + j() + this.j.B(), this.r + d2);
    }

    @Override
    protected void a(v paramv) {
    }

    @Override
    protected void b(v paramv) {
    }

    // hMod: Collision of a boat
    @Override
    public void c(dy paramdy) {
        super.c(paramdy);
        if (paramdy == this.j) {
            return;
        }
        Boat boat = new Boat(this);
        BaseEntity baseEntity = new BaseEntity(paramdy);
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_COLLISION, new Object[]{boat, baseEntity});
    }

    @Override
    public boolean a(fy paramfy) {
        // hMod: Entering the boat
        Boat boat = new Boat(this);
        HumanEntity player = new HumanEntity(paramfy);
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_ENTERED, new Object[]{boat, player});

        if ((this.j != null) && ((this.j instanceof fy)) && (this.j != paramfy)) {
            return true;
        }
        if (!this.l.z) {
            paramfy.e(this);
        }
        return true;
    }
}
