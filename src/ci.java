
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class ci {

    public boolean a = false;
    private Random b = new Random();

    public void a(eo parameo, dx paramdx, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat) {
        // hMod: store block location of explosion
        Block block = new Block(parameo.a((int) Math.floor(paramDouble1), (int) Math.floor(paramDouble2), (int) Math.floor(paramDouble3)), (int) Math.floor(paramDouble1), (int) Math.floor(paramDouble2), (int) Math.floor(paramDouble3));

        // hMod: dynamite (notch failed since he made the type null) :/
        if (paramdx == null) {
            block.setStatus(1);
        } else if (paramdx instanceof fm) {
            //Creeper
            block.setStatus(2);
        }

        // hMod: allow explosion
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.EXPLODE, new Object[]{block})) {
            return;
        }
        parameo.a(paramDouble1, paramDouble2, paramDouble3, "random.explode", 4.0F, (1.0F + (parameo.l.nextFloat() - parameo.l.nextFloat()) * 0.2F) * 0.7F);

        HashSet localHashSet = new HashSet();

        float f1 = paramFloat;

        int i = 16;
        double d6;
        double d7;
        double d8;
        for (int j = 0; j < i; j++) {
            for (int k = 0; k < i; k++) {
                for (int m = 0; m < i; m++) {
                    if ((j != 0) && (j != i - 1) && (k != 0) && (k != i - 1) && (m != 0) && (m != i - 1)) {
                        continue;
                    }
                    double d1 = j / (i - 1.0F) * 2.0F - 1.0F;
                    double d2 = k / (i - 1.0F) * 2.0F - 1.0F;
                    double d3 = m / (i - 1.0F) * 2.0F - 1.0F;
                    double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);

                    d1 /= d4;
                    d2 /= d4;
                    d3 /= d4;

                    float f2 = paramFloat * (0.7F + parameo.l.nextFloat() * 0.6F);
                    d6 = paramDouble1;
                    d7 = paramDouble2;
                    d8 = paramDouble3;

                    float f3 = 0.3F;
                    while (f2 > 0.0F) {
                        int i10 = hf.b(d6);
                        int i11 = hf.b(d7);
                        int i12 = hf.b(d8);
                        int i13 = parameo.a(i10, i11, i12);
                        if (i13 > 0) {
                            f2 -= (ga.m[i13].a(paramdx) + 0.3F) * f3;
                        }
                        if (f2 > 0.0F) {
                            localHashSet.add(new hq(i10, i11, i12));
                        }

                        d6 += d1 * f3;
                        d7 += d2 * f3;
                        d8 += d3 * f3;
                        f2 -= f3 * 0.75F;
                    }
                }
            }

        }

        paramFloat *= 2.0F;
        int j = hf.b(paramDouble1 - paramFloat - 1.0D);
        int k = hf.b(paramDouble1 + paramFloat + 1.0D);
        int m = hf.b(paramDouble2 - paramFloat - 1.0D);
        int n = hf.b(paramDouble2 + paramFloat + 1.0D);
        int i1 = hf.b(paramDouble3 - paramFloat - 1.0D);
        int i2 = hf.b(paramDouble3 + paramFloat + 1.0D);
        List localList = parameo.b(paramdx, du.b(j, m, i1, k, n, i2));
        bd localbd = bd.b(paramDouble1, paramDouble2, paramDouble3);
        double d9;
        double d10;
        double d11;
        double d12;
        for (int i3 = 0; i3 < localList.size(); i3++) {
            dx localdx = (dx) localList.get(i3);
            double d5 = localdx.e(paramDouble1, paramDouble2, paramDouble3) / paramFloat;
            if (d5 <= 1.0D) {
                d6 = localdx.p - paramDouble1;
                d7 = localdx.q - paramDouble2;
                d8 = localdx.r - paramDouble3;

                d9 = hf.a(d6 * d6 + d7 * d7 + d8 * d8);

                d6 /= d9;
                d7 /= d9;
                d8 /= d9;

                d10 = parameo.a(localbd, localdx.z);
                d11 = (1.0D - d5) * d10;
                
                // hMod Damage hook: Explosions
                int damage = (int) ((d11 * d11 + d11) / 2.0D * 8.0D * paramFloat + 1.0D);
                BaseEntity exploder = new BaseEntity(localdx);
                PluginLoader.DamageType dmgType = (paramdx instanceof fm) ? PluginLoader.DamageType.CREEPER_EXPLOSION : PluginLoader.DamageType.EXPLOSION;
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new Object[]{dmgType, null, exploder, damage})) {
                    localdx.a(paramdx, damage);
                }

                d12 = d11;
                localdx.s += d6 * d12;
                localdx.t += d7 * d12;
                localdx.u += d8 * d12;
            }
        }
        paramFloat = f1;

        ArrayList localArrayList = new ArrayList();
        localArrayList.addAll(localHashSet);
        hq localhq;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        for (int i4 = localArrayList.size() - 1; i4 >= 0; i4--) {
            localhq = (hq) localArrayList.get(i4);
            i5 = localhq.a;
            i6 = localhq.b;
            i7 = localhq.c;

            i8 = parameo.a(i5, i6, i7);

            for (i9 = 0; i9 < 1; i9++) {
                d8 = i5 + parameo.l.nextFloat();
                d9 = i6 + parameo.l.nextFloat();
                d10 = i7 + parameo.l.nextFloat();

                d11 = d8 - paramDouble1;
                d12 = d9 - paramDouble2;
                double d13 = d10 - paramDouble3;

                double d14 = hf.a(d11 * d11 + d12 * d12 + d13 * d13);

                d11 /= d14;
                d12 /= d14;
                d13 /= d14;

                double d15 = 0.5D / (d14 / paramFloat + 0.1D);
                d15 *= (parameo.l.nextFloat() * parameo.l.nextFloat() + 0.3F);
                d11 *= d15;
                d12 *= d15;
                d13 *= d15;

                parameo.a("explode", (d8 + paramDouble1 * 1.0D) / 2.0D, (d9 + paramDouble2 * 1.0D) / 2.0D, (d10 + paramDouble3 * 1.0D) / 2.0D, d11, d12, d13);
                parameo.a("smoke", d8, d9, d10, d11, d12, d13);
            }

            if (i8 > 0) {
                ga.m[i8].a(parameo, i5, i6, i7, parameo.b(i5, i6, i7), 0.3F);
                parameo.d(i5, i6, i7, 0);
                ga.m[i8].c(parameo, i5, i6, i7);
            }

        }

        if (this.a) {
            for (int i4 = localArrayList.size() - 1; i4 >= 0; i4--) {
                localhq = (hq) localArrayList.get(i4);
                i5 = localhq.a;
                i6 = localhq.b;
                i7 = localhq.c;
                i8 = parameo.a(i5, i6, i7);
                i9 = parameo.a(i5, i6 - 1, i7);
                if ((i8 == 0) && (ga.o[i9] != false) && (this.b.nextInt(3) == 0)) {
                    parameo.d(i5, i6, i7, ga.ar.bh);
                }
            }
        }
    }
}
