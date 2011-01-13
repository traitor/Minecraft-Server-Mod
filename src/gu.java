
import java.util.List;
import java.util.Random;

public class gu extends fe {

    public int a = 0;
    public int b = 0;
    public int c = 1;
    private int d;
    private double e;
    private double f;
    private double ak;
    private double al;
    private double am;

    public gu(fv paramfv) {
        super(paramfv);
        i = true;
        a(1.5F, 0.6F);
        H = (J / 2.0F);
        M = false;
    }

    protected void a() {
    }

    public fa d(fe paramfe) {
        return paramfe.z;
    }

    public fa u() {
        return z;
    }

    public boolean z() {
        return true;
    }

    public gu(fv paramfv, double paramDouble1, double paramDouble2, double paramDouble3) {
        this(paramfv);
        a(paramDouble1, paramDouble2 + H, paramDouble3);

        s = 0.0D;
        t = 0.0D;
        u = 0.0D;

        m = paramDouble1;
        n = paramDouble2;
        o = paramDouble3;
        // hMod: Creation of the boat
        Boat boat = new Boat(this);
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_CREATE, new Object[]{boat});
    }

    public double k() {
        return J * 0.0D - 0.300000011920929D;
    }

    public boolean a(fe paramfe, int paramInt) {
        // hMod: Attack of the boat
        BaseEntity attacker = new BaseEntity(paramfe);
        Boat boat = new Boat(this);
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_DAMAGE, boat, attacker, paramInt)) {
            return true;
        }

        if ((l.z) || (G)) {
            return true;
        }
        c = (-c);
        b = 10;
        a += paramInt * 10;
        y();
        if (a > 40) {
            for (int i = 0; i < 3; i++) {
                a(hr.x.bi, 1, 0.0F);
            }
            for (int i = 0; i < 2; i++) {
                a(hg.B.ba, 1, 0.0F);
            }
            q();
        }
        return true;
    }

    public boolean c_() {
        return !G;
    }

    public void b_() {
        // hMod: Update of the boat
        Boat boat = new Boat(this);
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_UPDATE, boat);

        super.b_();
        if (b > 0) {
            b -= 1;
        }
        if (a > 0) {
            a -= 1;
        }
        this.m = p;
        this.n = q;
        o = r;

        int i = 5;
        double d1 = 0.0D;
        for (int j = 0; j < i; j++) {
            double d3 = z.b + (z.e - z.b) * (j + 0) / i - 0.125D;
            double d5 = z.b + (z.e - z.b) * (j + 1) / i - 0.125D;
            fa localfa = fa.b(z.a, d3, z.c, z.d, d5, z.f);
            if (l.b(localfa, mh.f)) {
                d1 += 1.0D / i;
            }
        }
        double d2, d4, d6, d7;
        if (l.z) {
            if (d > 0) {
                d2 = p + (e - p) / d;
                d4 = q + (f - q) / d;
                d6 = r + (ak - r) / d;

                d7 = al - v;
                while (d7 < -180.0D) {
                    d7 += 360.0D;
                }
                while (d7 >= 180.0D) {
                    d7 -= 360.0D;
                }
                v = (float) (v + d7 / d);
                w = (float) (w + (am - w) / d);

                d -= 1;
                a(d2, d4, d6);
                b(v, w);
            } else {
                d2 = p + s;
                d4 = q + t;
                d6 = r + u;
                a(d2, d4, d6);
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

        d2 = d1 * 2.0D - 1.0D;
        t += 0.03999999910593033D * d2;

        if (this.j != null) {
            s += this.j.s * 0.2D;
            u += this.j.u * 0.2D;
        }

        d4 = 0.4D;

        if (s < -d4) {
            s = (-d4);
        }
        if (s > d4) {
            s = d4;
        }
        if (u < -d4) {
            u = (-d4);
        }
        if (u > d4) {
            u = d4;
        }
        if (A) {
            s *= 0.5D;
            t *= 0.5D;
            u *= 0.5D;
        }
        c(s, t, u);
        d6 = Math.sqrt(s * s + u * u);
        if (d6 > 0.15D) {
            d7 = Math.cos(v * 3.141592653589793D / 180.0D);
            double d9 = Math.sin(v * 3.141592653589793D / 180.0D);

            for (int m = 0; m < 1.0D + d6 * 60.0D; m++) {
                double d11 = W.nextFloat() * 2.0F - 1.0F;

                double d13 = (W.nextInt(2) * 2 - 1) * 0.7D;
                double d14;
                double d15;
                if (W.nextBoolean()) {
                    d14 = p - d7 * d11 * 0.8D + d9 * d13;
                    d15 = r - d9 * d11 * 0.8D - d7 * d13;
                    l.a("splash", d14, q - 0.125D, d15, s, t, u);
                } else {
                    d14 = p + d7 + d9 * d11 * 0.7D;
                    d15 = r + d9 - d7 * d11 * 0.7D;
                    l.a("splash", d14, q - 0.125D, d15, s, t, u);
                }
            }
        }

        if ((B) && (d6 > 0.15D)) {
            if (!l.z) {
                q();
                for (int k = 0; k < 3; k++) {
                    a(hr.x.bi, 1, 0.0F);
                }
                for (int k = 0; k < 2; k++) {
                    a(hg.B.ba, 1, 0.0F);
                }
            }
        } else {
            s *= 0.9900000095367432D;
            t *= 0.949999988079071D;
            u *= 0.9900000095367432D;
        }

        w = 0.0F;
        double d8 = v;
        double d9 = this.m - p;
        double d10 = o - r;
        if (d9 * d9 + d10 * d10 > 0.001D) {
            d8 = (float) (Math.atan2(d10, d9) * 180.0D / 3.141592653589793D);
        }

        double d12 = d8 - v;
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

        v = (float) (v + d12);
        b(v, w);

        List localList = l.b(this, z.b(0.2000000029802322D, 0.0D, 0.2000000029802322D));
        if ((localList != null) && (localList.size() > 0)) {
            for (int n = 0; n < localList.size(); n++) {
                fe localfe = (fe) localList.get(n);
                if ((localfe != this.j) && (localfe.z()) && ((localfe instanceof gu))) {
                    localfe.c(this);
                }
            }
        }

        if ((this.j != null)
                && (this.j.G)) {
            this.j = null;
        }
    }

    public void E() {
        if (j == null) {
            return;
        }

        double d1 = Math.cos(v * 3.141592653589793D / 180.0D) * 0.4D;
        double d2 = Math.sin(v * 3.141592653589793D / 180.0D) * 0.4D;
        j.a(p + d1, q + k() + j.F(), r + d2);
    }

    protected void a(ah paramah) {
    }

    protected void b(ah paramah) {
    }

    public boolean a(hl paramhl) {
        // hMod: Entering the boat
        Boat boat = new Boat(this);
        HumanEntity player = new HumanEntity(paramhl);
        etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_ENTERED, boat, player);

        if ((j != null) && ((j instanceof hl)) && (j != paramhl)) {
            return true;
        }
        if (!l.z) {
            paramhl.e(this);
        }
        return true;
    }
}
