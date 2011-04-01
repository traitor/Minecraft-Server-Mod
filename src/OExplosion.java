import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class OExplosion {

    public boolean a = false;
    private Random h = new Random();
    private OWorld i;
    public double  b;
    public double  c;
    public double  d;
    public OEntity e;
    public float   f;
    public Set     g = new HashSet();

    public OExplosion(OWorld paramOWorld, OEntity paramOEntity, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat) {
        i = paramOWorld;
        e = paramOEntity;
        f = paramFloat;
        b = paramDouble1;
        c = paramDouble2;
        d = paramDouble3;
    }

    public void a() {
        // hMod: allow explosion
        Block block = new Block(i.a((int) Math.floor(b), (int) Math.floor(c), (int) Math.floor(d)), (int) Math.floor(b), (int) Math.floor(c), (int) Math.floor(d));

        // hMod: preserve source through blockstatus.
        if (e == null)
            block.setStatus(1); // TNT
        else if (e instanceof OEntityCreeper)
            block.setStatus(2); // Creeper

        // hMod: call explode hook.
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.EXPLODE, block))
            return;

        float f1 = f;

        int j = 16;
        double d5;
        double d6;
        double d7;
        for (int k = 0; k < j; k++)
            for (int m = 0; m < j; m++)
                for (int n = 0; n < j; n++) {
                    if ((k != 0) && (k != j - 1) && (m != 0) && (m != j - 1) && (n != 0) && (n != j - 1))
                        continue;
                    double d1 = k / (j - 1.0F) * 2.0F - 1.0F;
                    double d2 = m / (j - 1.0F) * 2.0F - 1.0F;
                    double d3 = n / (j - 1.0F) * 2.0F - 1.0F;
                    double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);

                    d1 /= d4;
                    d2 /= d4;
                    d3 /= d4;

                    float f2 = f * (0.7F + i.k.nextFloat() * 0.6F);
                    d5 = b;
                    d6 = c;
                    d7 = d;

                    float f3 = 0.3F;
                    while (f2 > 0.0F) {
                        int i2 = OMathHelper.b(d5);
                        int i3 = OMathHelper.b(d6);
                        int i4 = OMathHelper.b(d7);
                        int i5 = i.a(i2, i3, i4);
                        if (i5 > 0)
                            f2 -= (OBlock.m[i5].a(e) + 0.3F) * f3;
                        if (f2 > 0.0F)
                            g.add(new OChunkPosition(i2, i3, i4));

                        d5 += d1 * f3;
                        d6 += d2 * f3;
                        d7 += d3 * f3;
                        f2 -= f3 * 0.75F;
                    }
                }

        f *= 2.0F;
        int k = OMathHelper.b(b - f - 1.0D);
        int m = OMathHelper.b(b + f + 1.0D);
        int n = OMathHelper.b(c - f - 1.0D);
        int i6 = OMathHelper.b(c + f + 1.0D);
        int i7 = OMathHelper.b(d - f - 1.0D);
        int i8 = OMathHelper.b(d + f + 1.0D);
        List localList = i.b(e, OAxisAlignedBB.b(k, n, i7, m, i6, i8));
        OVec3D localOVec3D = OVec3D.b(b, c, d);
        for (int i9 = 0; i9 < localList.size(); i9++) {
            OEntity localOEntity = (OEntity) localList.get(i9);
            double d8 = localOEntity.e(b, c, d) / f;
            if (d8 <= 1.0D) {
                d5 = localOEntity.aJ - b;
                d6 = localOEntity.aK - c;
                d7 = localOEntity.aL - d;

                double d9 = OMathHelper.a(d5 * d5 + d6 * d6 + d7 * d7);

                d5 /= d9;
                d6 /= d9;
                d7 /= d9;

                double d10 = i.a(localOVec3D, localOEntity.aU);
                double d11 = (1.0D - d8) * d10;
                // hMod Damage hook: Explosions
                int damage = (int) ((d11 * d11 + d11) / 2.0D * 8.0D * f + 1.0D);
                PluginLoader.DamageType dmgType = (e instanceof OEntityCreeper) ? PluginLoader.DamageType.CREEPER_EXPLOSION : PluginLoader.DamageType.EXPLOSION;
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, dmgType, null, localOEntity.entity, damage))
                    localOEntity.a(e, (int) ((d11 * d11 + d11) / 2.0D * 8.0D * f + 1.0D));

                double d12 = d11;
                localOEntity.aM += d5 * d12;
                localOEntity.aN += d6 * d12;
                localOEntity.aO += d7 * d12;
            }
        }
        f = f1;

        ArrayList localArrayList = new ArrayList();
        localArrayList.addAll(g);

        if (a)
            for (int i10 = localArrayList.size() - 1; i10 >= 0; i10--) {
                OChunkPosition localOChunkPosition = (OChunkPosition) localArrayList.get(i10);
                int i1 = localOChunkPosition.a;
                int i11 = localOChunkPosition.b;
                int i12 = localOChunkPosition.c;
                int i13 = i.a(i1, i11, i12);
                int i14 = i.a(i1, i11 - 1, i12);
                if ((i13 == 0) && OBlock.o[i14] && (h.nextInt(3) == 0))
                    i.e(i1, i11, i12, OBlock.ar.bl);
            }
    }

    public void b() {
        i.a(b, c, d, "random.explode", 4.0F, (1.0F + (i.k.nextFloat() - i.k.nextFloat()) * 0.2F) * 0.7F);

        ArrayList localArrayList = new ArrayList();
        localArrayList.addAll(g);
        for (int j = localArrayList.size() - 1; j >= 0; j--) {
            OChunkPosition localOChunkPosition = (OChunkPosition) localArrayList.get(j);
            int k = localOChunkPosition.a;
            int m = localOChunkPosition.b;
            int n = localOChunkPosition.c;

            int i1 = i.a(k, m, n);

            for (int i2 = 0; i2 < 1; i2++) {
                double d1 = k + i.k.nextFloat();
                double d2 = m + i.k.nextFloat();
                double d3 = n + i.k.nextFloat();

                double d4 = d1 - b;
                double d5 = d2 - c;
                double d6 = d3 - d;

                double d7 = OMathHelper.a(d4 * d4 + d5 * d5 + d6 * d6);

                d4 /= d7;
                d5 /= d7;
                d6 /= d7;

                double d8 = 0.5D / (d7 / f + 0.1D);
                d8 *= (i.k.nextFloat() * i.k.nextFloat() + 0.3F);
                d4 *= d8;
                d5 *= d8;
                d6 *= d8;

                i.a("explode", (d1 + b * 1.0D) / 2.0D, (d2 + c * 1.0D) / 2.0D, (d3 + d * 1.0D) / 2.0D, d4, d5, d6);
                i.a("smoke", d1, d2, d3, d4, d5, d6);
            }

            if (i1 > 0) {
                OBlock.m[i1].a(i, k, m, n, i.b(k, m, n), 0.3F);
                i.e(k, m, n, 0);
                OBlock.m[i1].c(i, k, m, n);
            }
        }
    }
}
