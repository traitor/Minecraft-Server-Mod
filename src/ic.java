
import java.util.Random;

public class ic extends fe {

    public jl a;
    private int e;
    public int b = 0;
    public int c;
    private int f = 5;
    public float d = (float) (Math.random() * 3.141592653589793D * 2.0D);

    public ic(fv paramfv, double paramDouble1, double paramDouble2, double paramDouble3, jl paramjl) {
        super(paramfv);
        a(0.25F, 0.25F);
        H = (J / 2.0F);
        a(paramDouble1, paramDouble2, paramDouble3);
        a = paramjl;

        v = (float) (Math.random() * 360.0D);

        s = (float) (Math.random() * 0.2000000029802322D - 0.1000000014901161D);
        t = 0.2000000029802322D;
        u = (float) (Math.random() * 0.2000000029802322D - 0.1000000014901161D);
        M = false;
    }

    public ic(fv paramfv) {
        super(paramfv);
        a(0.25F, 0.25F);
        H = (J / 2.0F);
    }

    protected void a() {
    }

    public void b_() {
        super.b_();
        if (c > 0) {
            c -= 1;
        }
        m = p;
        n = q;
        o = r;

        t -= 0.03999999910593033D;
        if (l.c(iz.b(p), iz.b(q), iz.b(r)) == mh.g) {
            t = 0.2000000029802322D;
            s = ((W.nextFloat() - W.nextFloat()) * 0.2F);
            u = ((W.nextFloat() - W.nextFloat()) * 0.2F);
            l.a(this, "random.fizz", 0.4F, 2.0F + W.nextFloat() * 0.4F);
        }
        g(p, q, r);
        c(s, t, u);

        float f1 = 0.98F;
        if (A) {
            f1 = 0.5880001F;
            int i = l.a(iz.b(p), iz.b(z.b) - 1, iz.b(r));
            if (i > 0) {
                f1 = hr.m[i].bu * 0.98F;
            }
        }

        s *= f1;
        t *= 0.9800000190734863D;
        u *= f1;

        if (A) {
            t *= -0.5D;
        }

        e += 1;
        b += 1;
        if (b >= 6000) {
            q();
        }
    }

    public boolean v() {
        return l.a(z, mh.f, this);
    }

    private boolean g(double paramDouble1, double paramDouble2, double paramDouble3) {
        int i = iz.b(paramDouble1);
        int j = iz.b(paramDouble2);
        int k = iz.b(paramDouble3);

        double d1 = paramDouble1 - i;
        double d2 = paramDouble2 - j;
        double d3 = paramDouble3 - k;

        if (hr.o[l.a(i, j, k)]) {
            int m = hr.o[l.a(i - 1, j, k)] == false ? 1 : 0;
            int n = hr.o[l.a(i + 1, j, k)] == false ? 1 : 0;
            int i1 = hr.o[l.a(i, j - 1, k)] == false ? 1 : 0;
            int i2 = hr.o[l.a(i, j + 1, k)] == false ? 1 : 0;
            int i3 = hr.o[l.a(i, j, k - 1)] == false ? 1 : 0;
            int i4 = hr.o[l.a(i, j, k + 1)] == false ? 1 : 0;

            int i5 = -1;
            double d4 = 9999.0D;
            if ((m != 0) && (d1 < d4)) {
                d4 = d1;
                i5 = 0;
            }
            if ((n != 0) && (1.0D - d1 < d4)) {
                d4 = 1.0D - d1;
                i5 = 1;
            }
            if ((i1 != 0) && (d2 < d4)) {
                d4 = d2;
                i5 = 2;
            }
            if ((i2 != 0) && (1.0D - d2 < d4)) {
                d4 = 1.0D - d2;
                i5 = 3;
            }
            if ((i3 != 0) && (d3 < d4)) {
                d4 = d3;
                i5 = 4;
            }
            if ((i4 != 0) && (1.0D - d3 < d4)) {
                d4 = 1.0D - d3;
                i5 = 5;
            }

            float f1 = W.nextFloat() * 0.2F + 0.1F;
            if (i5 == 0) {
                s = (-f1);
            }
            if (i5 == 1) {
                s = f1;
            }
            if (i5 == 2) {
                t = (-f1);
            }
            if (i5 == 3) {
                t = f1;
            }
            if (i5 == 4) {
                u = (-f1);
            }
            if (i5 == 5) {
                u = f1;
            }
        }

        return false;
    }

    protected void b(int paramInt) {
        a((fe) null, paramInt);
    }

    public boolean a(fe paramfe, int paramInt) {
        y();
        f -= paramInt;
        if (f <= 0) {
            q();
        }
        return false;
    }

    public void a(ah paramah) {
        paramah.a("Health", (short) (byte) f);
        paramah.a("Age", (short) b);
        paramah.a("Item", a.a(new ah()));
    }

    public void b(ah paramah) {
        f = (paramah.c("Health") & 0xFF);
        b = paramah.c("Age");
        ah localah = paramah.j("Item");
        a = new jl(localah);
    }

    public void b(hl paramhl) {
        if (l.z) {
            return;
        }

        int i = a.a;
        if ((c == 0) && (paramhl.an.a(a))) {
            // hMod: allow item pickups
            Item item = new Item(a.c, i);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_PICK_UP, ((fy) paramhl).getPlayer(), item)) {
                l.a(this, "random.pop", 0.2F, ((W.nextFloat() - W.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                paramhl.c(this, i);
                q();
            }
        }
    }
}
