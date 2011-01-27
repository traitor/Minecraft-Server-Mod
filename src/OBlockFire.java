import java.util.Random;

public class OBlockFire extends OBlock {

    private int[] a = new int[256];
    private int[] b = new int[256];

    protected OBlockFire(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, OMaterial.l);

        a(OBlock.x.bi, 5, 20);
        a(OBlock.J.bi, 5, 5);
        a(OBlock.K.bi, 30, 60);
        a(OBlock.an.bi, 30, 20);
        a(OBlock.am.bi, 15, 100);
        a(OBlock.ab.bi, 30, 60);

        a(true);
    }

    private void a(int paramInt1, int paramInt2, int paramInt3) {
        a[paramInt1] = paramInt2;
        b[paramInt1] = paramInt3;
    }

    @Override
    public OAxisAlignedBB d(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public int a(Random paramRandom) {
        return 0;
    }

    @Override
    public int b() {
        return 10;
    }

    @Override
    public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        int i = paramOWorld.a(paramInt1, paramInt2 - 1, paramInt3) == OBlock.bb.bi ? 1 : 0;

        int j = paramOWorld.b(paramInt1, paramInt2, paramInt3);
        if (j < 15) {
            paramOWorld.c(paramInt1, paramInt2, paramInt3, j + 1);
            paramOWorld.i(paramInt1, paramInt2, paramInt3, bi);
        }
        if ((i == 0) && (!g(paramOWorld, paramInt1, paramInt2, paramInt3))) {
            if ((!paramOWorld.d(paramInt1, paramInt2 - 1, paramInt3)) || (j > 3))
                paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
            return;
        }

        if ((i == 0) && (!b((OIBlockAccess) paramOWorld, paramInt1, paramInt2 - 1, paramInt3)) && (j == 15) && (paramRandom.nextInt(4) == 0)) {
            paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
            return;
        }

        if ((j % 2 == 0) && (j > 2)) {
            a(paramOWorld, paramInt1 + 1, paramInt2, paramInt3, 300, paramRandom);
            a(paramOWorld, paramInt1 - 1, paramInt2, paramInt3, 300, paramRandom);
            a(paramOWorld, paramInt1, paramInt2 - 1, paramInt3, 250, paramRandom);
            a(paramOWorld, paramInt1, paramInt2 + 1, paramInt3, 250, paramRandom);
            a(paramOWorld, paramInt1, paramInt2, paramInt3 - 1, 300, paramRandom);
            a(paramOWorld, paramInt1, paramInt2, paramInt3 + 1, 300, paramRandom);

            for (int k = paramInt1 - 1; k <= paramInt1 + 1; k++)
                for (int m = paramInt3 - 1; m <= paramInt3 + 1; m++)
                    for (int n = paramInt2 - 1; n <= paramInt2 + 4; n++) {
                        if ((k == paramInt1) && (n == paramInt2) && (m == paramInt3))
                            continue;
                        int i1 = 100;
                        if (n > paramInt2 + 1)
                            i1 += (n - (paramInt2 + 1)) * 100;

                        int i2 = h(paramOWorld, k, n, m);
                        if ((i2 > 0) && (paramRandom.nextInt(i1) <= i2)) {
                            // hMod: dynamic spreading of fire.{
                            // avg call amount per placed block of fire ~ 4
                            Block block = new Block(paramOWorld.a(k, n, m), k, n, m);
                            block.setStatus(3);
                            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, block, null))
                                paramOWorld.e(k, n, m, bi);
                        }
                    }
        }
    }

    private void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Random paramRandom) {
        int i = b[paramOWorld.a(paramInt1, paramInt2, paramInt3)];
        if (paramRandom.nextInt(paramInt4) < i) {
            int j = paramOWorld.a(paramInt1, paramInt2, paramInt3) == OBlock.am.bi ? 1 : 0;
            if (paramRandom.nextInt(2) == 0) {
                // hMod: VERY SLOW dynamic spreading of fire.
                Block block = new Block(paramOWorld.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
                block.setStatus(3);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, block, null))
                    paramOWorld.e(paramInt1, paramInt2, paramInt3, bi);
            } else {
                // hMod: fire destroying a block.
                Block block = new Block(paramOWorld.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
                block.setStatus(4);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, block, null))
                    paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
            }
            if (j != 0)
                OBlock.am.a(paramOWorld, paramInt1, paramInt2, paramInt3, 0);
        }
    }

    private boolean g(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        // hMod: cast down to fix decompiler error.(6 times)
        if (b((OIBlockAccess) paramOWorld, paramInt1 + 1, paramInt2, paramInt3))
            return true;
        if (b((OIBlockAccess) paramOWorld, paramInt1 - 1, paramInt2, paramInt3))
            return true;
        if (b((OIBlockAccess) paramOWorld, paramInt1, paramInt2 - 1, paramInt3))
            return true;
        if (b((OIBlockAccess) paramOWorld, paramInt1, paramInt2 + 1, paramInt3))
            return true;
        if (b((OIBlockAccess) paramOWorld, paramInt1, paramInt2, paramInt3 - 1))
            return true;
        return b((OIBlockAccess) paramOWorld, paramInt1, paramInt2, paramInt3 + 1);
    }

    private int h(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        int i = 0;
        if (!paramOWorld.e(paramInt1, paramInt2, paramInt3))
            return 0;

        i = f(paramOWorld, paramInt1 + 1, paramInt2, paramInt3, i);
        i = f(paramOWorld, paramInt1 - 1, paramInt2, paramInt3, i);
        i = f(paramOWorld, paramInt1, paramInt2 - 1, paramInt3, i);
        i = f(paramOWorld, paramInt1, paramInt2 + 1, paramInt3, i);
        i = f(paramOWorld, paramInt1, paramInt2, paramInt3 - 1, i);
        i = f(paramOWorld, paramInt1, paramInt2, paramInt3 + 1, i);

        return i;
    }

    @Override
    public boolean d() {
        return false;
    }

    public boolean b(OIBlockAccess paramOIBlockAccess, int paramInt1, int paramInt2, int paramInt3) {
        return a[paramOIBlockAccess.a(paramInt1, paramInt2, paramInt3)] > 0;
    }

    public int f(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = a[paramOWorld.a(paramInt1, paramInt2, paramInt3)];
        if (i > paramInt4)
            return i;
        return paramInt4;
    }

    @Override
    public boolean a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        return (paramOWorld.d(paramInt1, paramInt2 - 1, paramInt3)) || (g(paramOWorld, paramInt1, paramInt2, paramInt3));
    }

    @Override
    public void b(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if ((!paramOWorld.d(paramInt1, paramInt2 - 1, paramInt3)) && (!g(paramOWorld, paramInt1, paramInt2, paramInt3))) {
            paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
            return;
        }
    }

    @Override
    public void e(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        if ((paramOWorld.a(paramInt1, paramInt2 - 1, paramInt3) == OBlock.ap.bi) && (OBlock.be.b_(paramOWorld, paramInt1, paramInt2, paramInt3)))
            return;

        if ((!paramOWorld.d(paramInt1, paramInt2 - 1, paramInt3)) && (!g(paramOWorld, paramInt1, paramInt2, paramInt3))) {
            paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
            return;
        }
        paramOWorld.i(paramInt1, paramInt2, paramInt3, bi);
    }
}
