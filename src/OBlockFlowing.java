import java.util.Random;

public class OBlockFlowing extends OBlockFluids {

    int       a = 0;
    boolean[] b = new boolean[4];
    int[]     c = new int[4];

    protected OBlockFlowing(int paramInt, OMaterial paramOMaterial) {
        super(paramInt, paramOMaterial);
    }

    private void i(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramOWorld.b(paramInt1, paramInt2, paramInt3);
        paramOWorld.a(paramInt1, paramInt2, paramInt3, bk + 1, i);
        paramOWorld.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);
        paramOWorld.g(paramInt1, paramInt2, paramInt3);
    }

    @Override
    public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        // hMod: Store originating block
        Block blockFrom = new Block(bk, paramInt1, paramInt2, paramInt3);

        int i = g(paramOWorld, paramInt1, paramInt2, paramInt3);

        int j = 1;
        if ((bv == OMaterial.g) && (!paramOWorld.m.d)) {
            j = 2;
        }

        int k = 1;
        int n;
        if (i > 0) {
            int m = -100;
            a = 0;
            m = f(paramOWorld, paramInt1 - 1, paramInt2, paramInt3, m);
            m = f(paramOWorld, paramInt1 + 1, paramInt2, paramInt3, m);
            m = f(paramOWorld, paramInt1, paramInt2, paramInt3 - 1, m);
            m = f(paramOWorld, paramInt1, paramInt2, paramInt3 + 1, m);

            n = m + j;
            if ((n >= 8) || (m < 0)) {
                n = -1;
            }
            if (g(paramOWorld, paramInt1, paramInt2 + 1, paramInt3) >= 0) {
                int i1 = g(paramOWorld, paramInt1, paramInt2 + 1, paramInt3);
                if (i1 >= 8) {
                    n = i1;
                } else {
                    n = i1 + 8;
                }
            }
            if ((a >= 2) && (bv == OMaterial.f)) {
                if (paramOWorld.d(paramInt1, paramInt2 - 1, paramInt3)) {
                    n = 0;
                } else if ((paramOWorld.c(paramInt1, paramInt2 - 1, paramInt3) == bv) && (paramOWorld.b(paramInt1, paramInt2, paramInt3) == 0)) {
                    n = 0;
                }
            }
            if ((bv == OMaterial.g) && (i < 8) && (n < 8) && (n > i) && (paramRandom.nextInt(4) != 0)) {
                n = i;
                k = 0;
            }

            if (n != i) {
                i = n;
                if (i < 0) {
                    paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
                } else {
                    paramOWorld.c(paramInt1, paramInt2, paramInt3, i);
                    paramOWorld.c(paramInt1, paramInt2, paramInt3, bk, b());
                    paramOWorld.h(paramInt1, paramInt2, paramInt3, bk);
                }
            } else if (k != 0) {
                i(paramOWorld, paramInt1, paramInt2, paramInt3);
            }
        } else {
            i(paramOWorld, paramInt1, paramInt2, paramInt3);
        }
        if (l(paramOWorld, paramInt1, paramInt2 - 1, paramInt3)) {
            // hMod: downwards flow.
            Block blockTo = new Block(0, paramInt1, paramInt2 - 1, paramInt3);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, blockFrom, blockTo))
                if (i >= 8) {
                    paramOWorld.b(paramInt1, paramInt2 - 1, paramInt3, bk, i);
                } else {
                    paramOWorld.b(paramInt1, paramInt2 - 1, paramInt3, bk, i + 8);
                }
        } else if ((i >= 0) && ((i == 0) || (k(paramOWorld, paramInt1, paramInt2 - 1, paramInt3)))) {
            boolean[] arrayOfBoolean = j(paramOWorld, paramInt1, paramInt2, paramInt3);
            n = i + j;
            if (i >= 8) {
                n = 1;
            }
            if (n >= 8) {
                return;
            }
            // hMod: sidewards flow.
            if (arrayOfBoolean[0]) {
                Block blockTo = new Block(0, paramInt1 - 1, paramInt2, paramInt3);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, blockFrom, blockTo))
                    g(paramOWorld, paramInt1 - 1, paramInt2, paramInt3, n);
            }
            if (arrayOfBoolean[1]) {
                Block blockTo = new Block(0, paramInt1 + 1, paramInt2, paramInt3);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, blockFrom, blockTo))
                    g(paramOWorld, paramInt1 + 1, paramInt2, paramInt3, n);
            }
            if (arrayOfBoolean[2]) {
                Block blockTo = new Block(0, paramInt1, paramInt2, paramInt3 - 1);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, blockFrom, blockTo))
                    g(paramOWorld, paramInt1, paramInt2, paramInt3 - 1, n);
            }
            if (arrayOfBoolean[3]) {
                Block blockTo = new Block(0, paramInt1, paramInt2, paramInt3 + 1);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, blockFrom, blockTo))
                    g(paramOWorld, paramInt1, paramInt2, paramInt3 + 1, n);
            }
        }
    }

    private void g(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (l(paramOWorld, paramInt1, paramInt2, paramInt3)) {
            int i = paramOWorld.a(paramInt1, paramInt2, paramInt3);
            if (i > 0) {
                if (bv == OMaterial.g) {
                    h(paramOWorld, paramInt1, paramInt2, paramInt3);
                } else {
                    OBlock.m[i].b_(paramOWorld, paramInt1, paramInt2, paramInt3, paramOWorld.b(paramInt1, paramInt2, paramInt3));
                }
            }
            paramOWorld.b(paramInt1, paramInt2, paramInt3, bk, paramInt4);
        }
    }

    private int b(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
        int i = 1000;
        for (int j = 0; j < 4; j++) {
            if (((j == 0) && (paramInt5 == 1)) || ((j == 1) && (paramInt5 == 0)) || ((j == 2) && (paramInt5 == 3)) || ((j == 3) && (paramInt5 == 2))) {
                continue;
            }
            int k = paramInt1;
            int m = paramInt2;
            int n = paramInt3;

            if (j == 0) {
                k--;
            }
            if (j == 1) {
                k++;
            }
            if (j == 2) {
                n--;
            }
            if (j == 3) {
                n++;
            }

            if (k(paramOWorld, k, m, n)) {
                continue;
            }
            if ((paramOWorld.c(k, m, n) == bv) && (paramOWorld.b(k, m, n) == 0)) {
                continue;
            }
            if (!k(paramOWorld, k, m - 1, n)) {
                return paramInt4;
            }
            if (paramInt4 < 4) {
                int i1 = b(paramOWorld, k, m, n, paramInt4 + 1, j);
                if (i1 >= i) {
                    continue;
                }
                i = i1;
            }

        }

        return i;
    }

    private boolean[] j(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        for (int i = 0; i < 4; i++) {
            c[i] = 1000;
            int j = paramInt1;
            int k = paramInt2;
            int m = paramInt3;

            if (i == 0) {
                j--;
            }
            if (i == 1) {
                j++;
            }
            if (i == 2) {
                m--;
            }
            if (i == 3) {
                m++;
            }
            if (k(paramOWorld, j, k, m)) {
                continue;
            }
            if ((paramOWorld.c(j, k, m) == bv) && (paramOWorld.b(j, k, m) == 0)) {
                continue;
            }
            if (!k(paramOWorld, j, k - 1, m)) {
                c[i] = 0;
            } else {
                c[i] = b(paramOWorld, j, k, m, 1, i);
            }

        }

        int i = c[0];
        for (int j = 1; j < 4; j++) {
            if (c[j] >= i) {
                continue;
            }
            i = c[j];
        }

        for (int j = 0; j < 4; j++) {
            b[j] = (c[j] == i ? true : false);
        }
        return b;
    }

    private boolean k(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramOWorld.a(paramInt1, paramInt2, paramInt3);
        if ((i == OBlock.aE.bk) || (i == OBlock.aL.bk) || (i == OBlock.aD.bk) || (i == OBlock.aF.bk) || (i == OBlock.aX.bk)) {
            return true;
        }
        if (i == 0) {
            return false;
        }
        OMaterial localOMaterial = OBlock.m[i].bv;
        return localOMaterial.a();
    }

    protected int f(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = g(paramOWorld, paramInt1, paramInt2, paramInt3);
        if (i < 0) {
            return paramInt4;
        }
        if (i == 0) {
            a += 1;
        }
        if (i >= 8) {
            i = 0;
        }
        return (paramInt4 < 0) || (i < paramInt4) ? i : paramInt4;
    }

    private boolean l(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        // hMod: See if this liquid can destroy this block.
        Block block = new Block(paramOWorld.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
        PluginLoader.HookResult ret = (PluginLoader.HookResult) etc.getLoader().callHook(PluginLoader.Hook.LIQUID_DESTROY, bk, block);
        if (ret == PluginLoader.HookResult.PREVENT_ACTION)
            return false;
        else if (ret == PluginLoader.HookResult.ALLOW_ACTION)
            return true;

        OMaterial localOMaterial = paramOWorld.c(paramInt1, paramInt2, paramInt3);
        if (localOMaterial == bv) {
            return false;
        }
        if (localOMaterial == OMaterial.g) {
            return false;
        }
        return !k(paramOWorld, paramInt1, paramInt2, paramInt3);
    }

    @Override
    public void e(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        super.e(paramOWorld, paramInt1, paramInt2, paramInt3);
        if (paramOWorld.a(paramInt1, paramInt2, paramInt3) == bk) {
            paramOWorld.c(paramInt1, paramInt2, paramInt3, bk, b());
        }
    }
}
