
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class cw {

    public boolean a = false;
    private Random h = new Random();
    private ff i;
    public double b;
    public double c;
    public double d;
    public ep e;
    public float f;
    public Set g = new HashSet();

    public cw(ff paramff, ep paramep, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat) {
        this.i = paramff;
        this.e = paramep;
        this.f = paramFloat;
        this.b = paramDouble1;
        this.c = paramDouble2;
        this.d = paramDouble3;
    }

    public void a() {
        // hMod: allow explosion
        Block block = new Block(this.i.a((int) Math.floor(this.b), (int) Math.floor(this.c), (int) Math.floor(this.d)), (int) Math.floor(this.b), (int) Math.floor(this.c), (int) Math.floor(this.d));

        // hMod: preserve source through blockstatus.
        if (this.e == null) {
            block.setStatus(1); // TNT
        } else if (this.e instanceof ge) {
            block.setStatus(2); //Creeper
        }

        // hMod: call explode hook.
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.EXPLODE, block)) {
            return;
        }
        
        float f1 = this.f;

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

                    float f2 = this.f * (0.7F + this.i.l.nextFloat() * 0.6F);
                    d6 = this.b;
                    d7 = this.c;
                    d8 = this.d;

                    float f3 = 0.3F;
                    while (f2 > 0.0F) {
                        int i11 = ic.b(d6);
                        int i12 = ic.b(d7);
                        int i13 = ic.b(d8);
                        int i14 = this.i.a(i11, i12, i13);
                        if (i14 > 0) {
                            f2 -= (gv.m[i14].a(this.e) + 0.3F) * f3;
                        }
                        if (f2 > 0.0F) {
                            this.g.add(new iq(i11, i12, i13));
                        }

                        d6 += d1 * f3;
                        d7 += d2 * f3;
                        d8 += d3 * f3;
                        f2 -= f3 * 0.75F;
                    }
                }
            }

        }

        this.f *= 2.0F;
        int k = ic.b(this.b - this.f - 1.0D);
        int m = ic.b(this.b + this.f + 1.0D);
        int n = ic.b(this.c - this.f - 1.0D);
        int i1 = ic.b(this.c + this.f + 1.0D);
        int i2 = ic.b(this.d - this.f - 1.0D);
        int i3 = ic.b(this.d + this.f + 1.0D);
        List localList = this.i.b(this.e, el.b(k, n, i2, m, i1, i3));
        bn localbn = bn.b(this.b, this.c, this.d);
        for (int i4 = 0; i4 < localList.size(); i4++) {
            ep localep = (ep) localList.get(i4);
            double d5 = localep.e(this.b, this.c, this.d) / this.f;
            if (d5 <= 1.0D) {
                d6 = localep.p - this.b;
                d7 = localep.q - this.c;
                d8 = localep.r - this.d;

                double d9 = ic.a(d6 * d6 + d7 * d7 + d8 * d8);

                d6 /= d9;
                d7 /= d9;
                d8 /= d9;

                double d10 = this.i.a(localbn, localep.z);
                double d11 = (1.0D - d5) * d10;

                // hMod Damage hook: Explosions
                int damage = (int) ((d11 * d11 + d11) / 2.0D * 8.0D * this.f + 1.0D);
                BaseEntity exploder = new BaseEntity(localep);
                PluginLoader.DamageType dmgType = (this.e instanceof ge) ? PluginLoader.DamageType.CREEPER_EXPLOSION : PluginLoader.DamageType.EXPLOSION;
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, dmgType, null, exploder, damage)) {
                    localep.a(this.e, damage);
                }

                double d12 = d11;
                localep.s += d6 * d12;
                localep.t += d7 * d12;
                localep.u += d8 * d12;
            }
        }
        this.f = f1;

        ArrayList localArrayList = new ArrayList();
        localArrayList.addAll(this.g);

        if (this.a) {
            for (int i5 = localArrayList.size() - 1; i5 >= 0; i5--) {
                iq localip = (iq) localArrayList.get(i5);
                int i6 = localip.a;
                int i7 = localip.b;
                int i8 = localip.c;
                int i9 = this.i.a(i6, i7, i8);
                int i10 = this.i.a(i6, i7 - 1, i8);
                if ((i9 == 0) && (gv.o[i10]) && (this.h.nextInt(3) == 0)) {
                    this.i.d(i6, i7, i8, gv.ar.bh);
                }
            }
        }
    }

    public void b() {
        this.i.a(this.b, this.c, this.d, "random.explode", 4.0F, (1.0F + (this.i.l.nextFloat() - this.i.l.nextFloat()) * 0.2F) * 0.7F);

        ArrayList localArrayList = new ArrayList();
        localArrayList.addAll(this.g);
        for (int j = localArrayList.size() - 1; j >= 0; j--) {
            iq localip = (iq) localArrayList.get(j);
            int k = localip.a;
            int m = localip.b;
            int n = localip.c;

            int i1 = this.i.a(k, m, n);

            for (int i2 = 0; i2 < 1; i2++) {
                double d1 = k + this.i.l.nextFloat();
                double d2 = m + this.i.l.nextFloat();
                double d3 = n + this.i.l.nextFloat();

                double d4 = d1 - this.b;
                double d5 = d2 - this.c;
                double d6 = d3 - this.d;

                double d7 = ic.a(d4 * d4 + d5 * d5 + d6 * d6);

                d4 /= d7;
                d5 /= d7;
                d6 /= d7;

                double d8 = 0.5D / (d7 / this.f + 0.1D);
                d8 *= (this.i.l.nextFloat() * this.i.l.nextFloat() + 0.3F);
                d4 *= d8;
                d5 *= d8;
                d6 *= d8;

                this.i.a("explode", (d1 + this.b * 1.0D) / 2.0D, (d2 + this.c * 1.0D) / 2.0D, (d3 + this.d * 1.0D) / 2.0D, d4, d5, d6);
                this.i.a("smoke", d1, d2, d3, d4, d5, d6);
            }

            if (i1 > 0) {
                gv.m[i1].a(this.i, k, m, n, this.i.b(k, m, n), 0.3F);
                this.i.d(k, m, n, 0);
                gv.m[i1].c(this.i, k, m, n);
            }
        }
    }
}
