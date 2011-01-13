
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class dg {

    public boolean a = false;
    private Random h = new Random();
    private fv i;
    public double b;
    public double c;
    public double d;
    public fe e;
    public float f;
    public Set g = new HashSet();

    public dg(fv paramfv, fe paramfe, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat) {
        i = paramfv;
        e = paramfe;
        f = paramFloat;
        b = paramDouble1;
        c = paramDouble2;
        d = paramDouble3;
    }

    public void a() {
        // hMod: allow explosion
        Block block = new Block(this.i.a((int) Math.floor(this.b), (int) Math.floor(this.c), (int) Math.floor(this.d)), (int) Math.floor(this.b), (int) Math.floor(this.c), (int) Math.floor(this.d));

        // hMod: preserve source through blockstatus.
        if (this.e == null) {
            block.setStatus(1); // TNT
        } else if (this.e instanceof gy) {
            block.setStatus(2); //Creeper
        }

        // hMod: call explode hook.
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.EXPLODE, block)) {
            return;
        }

        float f1 = f;

        int j = 16;
        double d6;
        double d7;
        double d8;
        for (int k = 0; k < j; k++) {
            for (int m = 0; m < j; m++) {
                for (int n = 0; n < j; n++) {
                    if ((k != 0) && (k != j - 1) && (m != 0) && (m != j - 1) && (n != 0) && (n != j - 1)) {
                        continue;
                    }
                    double d1 = k / (j - 1.0F) * 2.0F - 1.0F;
                    double d2 = m / (j - 1.0F) * 2.0F - 1.0F;
                    double d3 = n / (j - 1.0F) * 2.0F - 1.0F;
                    double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);

                    d1 /= d4;
                    d2 /= d4;
                    d3 /= d4;

                    float f2 = f * (0.7F + i.l.nextFloat() * 0.6F);
                    d6 = b;
                    d7 = c;
                    d8 = d;

                    float f3 = 0.3F;
                    while (f2 > 0.0F) {
                        int i11 = iz.b(d6);
                        int i12 = iz.b(d7);
                        int i13 = iz.b(d8);
                        int i14 = i.a(i11, i12, i13);
                        if (i14 > 0) {
                            f2 -= (hr.m[i14].a(e) + 0.3F) * f3;
                        }
                        if (f2 > 0.0F) {
                            g.add(new jq(i11, i12, i13));
                        }

                        d6 += d1 * f3;
                        d7 += d2 * f3;
                        d8 += d3 * f3;
                        f2 -= f3 * 0.75F;
                    }
                }
            }

        }

        f *= 2.0F;
        int k = iz.b(b - f - 1.0D);
        int m = iz.b(b + f + 1.0D);
        int n = iz.b(c - f - 1.0D);
        int i1 = iz.b(c + f + 1.0D);
        int i2 = iz.b(d - f - 1.0D);
        int i3 = iz.b(d + f + 1.0D);
        List localList = i.b(e, fa.b(k, n, i2, m, i1, i3));
        bu localbu = bu.b(b, c, d);
        for (int i4 = 0; i4 < localList.size(); i4++) {
            fe localfe = (fe) localList.get(i4);
            double d5 = localfe.e(b, c, d) / f;
            if (d5 <= 1.0D) {
                d6 = localfe.p - b;
                d7 = localfe.q - c;
                d8 = localfe.r - d;

                double d9 = iz.a(d6 * d6 + d7 * d7 + d8 * d8);

                d6 /= d9;
                d7 /= d9;
                d8 /= d9;

                double d10 = i.a(localbu, localfe.z);
                double d11 = (1.0D - d5) * d10;
                // hMod Damage hook: Explosions
                int damage = (int) ((d11 * d11 + d11) / 2.0D * 8.0D * this.f + 1.0D);
                BaseEntity exploder = new BaseEntity(localfe);
                PluginLoader.DamageType dmgType = (this.e instanceof gy) ? PluginLoader.DamageType.CREEPER_EXPLOSION : PluginLoader.DamageType.EXPLOSION;
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, dmgType, null, exploder, damage)) {
                    localfe.a(this.e, damage);
                }

                double d12 = d11;
                localfe.s += d6 * d12;
                localfe.t += d7 * d12;
                localfe.u += d8 * d12;
            }
        }
        f = f1;

        ArrayList localArrayList = new ArrayList();
        localArrayList.addAll(g);

        if (a) {
            for (int i5 = localArrayList.size() - 1; i5 >= 0; i5--) {
                jq localjq = (jq) localArrayList.get(i5);
                int i6 = localjq.a;
                int i7 = localjq.b;
                int i8 = localjq.c;
                int i9 = i.a(i6, i7, i8);
                int i10 = i.a(i6, i7 - 1, i8);
                if ((i9 == 0) && (hr.o[i10]) && (h.nextInt(3) == 0)) {
                    i.e(i6, i7, i8, hr.ar.bi);
                }
            }
        }
    }

    public void b() {
        i.a(b, c, d, "random.explode", 4.0F, (1.0F + (i.l.nextFloat() - i.l.nextFloat()) * 0.2F) * 0.7F);

        ArrayList localArrayList = new ArrayList();
        localArrayList.addAll(g);
        for (int j = localArrayList.size() - 1; j >= 0; j--) {
            jq localjq = (jq) localArrayList.get(j);
            int k = localjq.a;
            int m = localjq.b;
            int n = localjq.c;

            int i1 = i.a(k, m, n);

            for (int i2 = 0; i2 < 1; i2++) {
                double d1 = k + i.l.nextFloat();
                double d2 = m + i.l.nextFloat();
                double d3 = n + i.l.nextFloat();

                double d4 = d1 - b;
                double d5 = d2 - c;
                double d6 = d3 - d;

                double d7 = iz.a(d4 * d4 + d5 * d5 + d6 * d6);

                d4 /= d7;
                d5 /= d7;
                d6 /= d7;

                double d8 = 0.5D / (d7 / f + 0.1D);
                d8 *= (i.l.nextFloat() * i.l.nextFloat() + 0.3F);
                d4 *= d8;
                d5 *= d8;
                d6 *= d8;

                i.a("explode", (d1 + b * 1.0D) / 2.0D, (d2 + c * 1.0D) / 2.0D, (d3 + d * 1.0D) / 2.0D, d4, d5, d6);
                i.a("smoke", d1, d2, d3, d4, d5, d6);
            }

            if (i1 > 0) {
                hr.m[i1].a(i, k, m, n, i.b(k, m, n), 0.3F);
                i.e(k, m, n, 0);
                hr.m[i1].a_(i, k, m, n);
            }
        }
    }
}
