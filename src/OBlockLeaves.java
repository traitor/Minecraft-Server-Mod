import java.util.Random;

public class OBlockLeaves extends OBlockLeavesBase {

    private int c;
    int[]       a;

    protected OBlockLeaves(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, OMaterial.h, false);
        c = paramInt2;
        a(true);
    }

    @Override
    public void b(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        int i = 1;
        int j = i + 1;

        if (paramOWorld.a(paramInt1 - j, paramInt2 - j, paramInt3 - j, paramInt1 + j, paramInt2 + j, paramInt3 + j))
            for (int k = -i; k <= i; k++)
                for (int m = -i; m <= i; m++)
                    for (int n = -i; n <= i; n++) {
                        int i1 = paramOWorld.a(paramInt1 + k, paramInt2 + m, paramInt3 + n);
                        if (i1 == OBlock.K.bl) {
                            int i2 = paramOWorld.b(paramInt1 + k, paramInt2 + m, paramInt3 + n);
                            paramOWorld.d(paramInt1 + k, paramInt2 + m, paramInt3 + n, i2 | 0x4);
                        }
                    }
    }

    @Override
    public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        if (paramOWorld.t)
            return;

        int i = paramOWorld.b(paramInt1, paramInt2, paramInt3);
        if ((i & 0x4) != 0) {
            int j = 4;
            int k = j + 1;

            int m = 32;
            int n = m * m;
            int i1 = m / 2;
            if (a == null)
                a = new int[m * m * m];

            if (paramOWorld.a(paramInt1 - k, paramInt2 - k, paramInt3 - k, paramInt1 + k, paramInt2 + k, paramInt3 + k)) {
                int i3;
                int i4;
                int i5;
                for (int i2 = -j; i2 <= j; i2++)
                    for (i3 = -j; i3 <= j; i3++)
                        for (i4 = -j; i4 <= j; i4++) {
                            i5 = paramOWorld.a(paramInt1 + i2, paramInt2 + i3, paramInt3 + i4);
                            if (i5 == OBlock.J.bl)
                                a[((i2 + i1) * n + (i3 + i1) * m + (i4 + i1))] = 0;
                            else if (i5 == OBlock.K.bl)
                                a[((i2 + i1) * n + (i3 + i1) * m + (i4 + i1))] = -2;
                            else
                                a[((i2 + i1) * n + (i3 + i1) * m + (i4 + i1))] = -1;
                        }
                for (int i2 = 1; i2 <= 4; i2++)
                    for (i3 = -j; i3 <= j; i3++)
                        for (i4 = -j; i4 <= j; i4++)
                            for (i5 = -j; i5 <= j; i5++)
                                if (a[((i3 + i1) * n + (i4 + i1) * m + (i5 + i1))] == i2 - 1) {
                                    if (a[((i3 + i1 - 1) * n + (i4 + i1) * m + (i5 + i1))] == -2)
                                        a[((i3 + i1 - 1) * n + (i4 + i1) * m + (i5 + i1))] = i2;
                                    if (a[((i3 + i1 + 1) * n + (i4 + i1) * m + (i5 + i1))] == -2)
                                        a[((i3 + i1 + 1) * n + (i4 + i1) * m + (i5 + i1))] = i2;
                                    if (a[((i3 + i1) * n + (i4 + i1 - 1) * m + (i5 + i1))] == -2)
                                        a[((i3 + i1) * n + (i4 + i1 - 1) * m + (i5 + i1))] = i2;
                                    if (a[((i3 + i1) * n + (i4 + i1 + 1) * m + (i5 + i1))] == -2)
                                        a[((i3 + i1) * n + (i4 + i1 + 1) * m + (i5 + i1))] = i2;
                                    if (a[((i3 + i1) * n + (i4 + i1) * m + (i5 + i1 - 1))] == -2)
                                        a[((i3 + i1) * n + (i4 + i1) * m + (i5 + i1 - 1))] = i2;
                                    if (a[((i3 + i1) * n + (i4 + i1) * m + (i5 + i1 + 1))] == -2)
                                        a[((i3 + i1) * n + (i4 + i1) * m + (i5 + i1 + 1))] = i2;
                                }
            }
            int i2 = a[(i1 * n + i1 * m + i1)];
            if (i2 >= 0)
                paramOWorld.c(paramInt1, paramInt2, paramInt3, i & 0xFFFFFFFB);
            else
                g(paramOWorld, paramInt1, paramInt2, paramInt3);
        }
    }

    private void g(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        // hMod: stop leaves from decaying
        Block block = new Block(etc.getServer().getBlockIdAt(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.LEAF_DECAY, block)) {
            a_(paramOWorld, paramInt1, paramInt2, paramInt3, paramOWorld.b(paramInt1, paramInt2, paramInt3));
            paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
        }
    }

    @Override
    public int a(Random paramRandom) {
        return paramRandom.nextInt(16) == 0 ? 1 : 0;
    }

    @Override
    public int a(int paramInt, Random paramRandom) {
        return OBlock.y.bl;
    }

    @Override
    public boolean a() {
        return !b;
    }

    @Override
    public int a(int paramInt1, int paramInt2) {
        if ((paramInt2 & 0x3) == 1)
            return bk + 80;
        return bk;
    }

    @Override
    public void b(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, OEntity paramOEntity) {
        super.b(paramOWorld, paramInt1, paramInt2, paramInt3, paramOEntity);
    }
}
