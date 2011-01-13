
import java.util.Random;

public class be extends dz {

    int a = 0;
    boolean[] b = new boolean[4];
    int[] c = new int[4];

    protected be(int paramInt, mh parammh) {
        super(paramInt, parammh);
    }

    private void i(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramfv.b(paramInt1, paramInt2, paramInt3);
        paramfv.a(paramInt1, paramInt2, paramInt3, bi + 1, i);
        paramfv.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);
        paramfv.g(paramInt1, paramInt2, paramInt3);
    }

    public void a(fv paramfv, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        // hMod: Store originating block
        Block blockFrom = new Block(this.bh, paramInt1, paramInt2, paramInt3);

        int i = g(paramfv, paramInt1, paramInt2, paramInt3);

        int j = 1;
        if ((bt == mh.g) && (!paramfv.q.d)) {
            j = 2;
        }

        int k = 1;
        int n;
        if (i > 0) {
            int m = -100;
            a = 0;
            m = e(paramfv, paramInt1 - 1, paramInt2, paramInt3, m);
            m = e(paramfv, paramInt1 + 1, paramInt2, paramInt3, m);
            m = e(paramfv, paramInt1, paramInt2, paramInt3 - 1, m);
            m = e(paramfv, paramInt1, paramInt2, paramInt3 + 1, m);

            n = m + j;
            if ((n >= 8) || (m < 0)) {
                n = -1;
            }
            if (g(paramfv, paramInt1, paramInt2 + 1, paramInt3) >= 0) {
                int i1 = g(paramfv, paramInt1, paramInt2 + 1, paramInt3);
                if (i1 >= 8) {
                    n = i1;
                } else {
                    n = i1 + 8;
                }
            }
            if ((a >= 2) && (bt == mh.f)) {
                if (paramfv.d(paramInt1, paramInt2 - 1, paramInt3)) {
                    n = 0;
                } else if ((paramfv.c(paramInt1, paramInt2 - 1, paramInt3) == bt) && (paramfv.b(paramInt1, paramInt2, paramInt3) == 0)) {
                    n = 0;
                }
            }
            if ((bt == mh.g)
                    && (i < 8) && (n < 8)
                    && (n > i)
                    && (paramRandom.nextInt(4) != 0)) {
                n = i;
                k = 0;
            }

            if (n != i) {
                i = n;
                if (i < 0) {
                    paramfv.e(paramInt1, paramInt2, paramInt3, 0);
                } else {
                    paramfv.c(paramInt1, paramInt2, paramInt3, i);
                    paramfv.i(paramInt1, paramInt2, paramInt3, bi);
                    paramfv.h(paramInt1, paramInt2, paramInt3, bi);
                }
            } else if (k != 0) {
                i(paramfv, paramInt1, paramInt2, paramInt3);
            }
        } else {
            i(paramfv, paramInt1, paramInt2, paramInt3);
        }
        if (l(paramfv, paramInt1, paramInt2 - 1, paramInt3)) {
            // hMod: downwards flow.
            Block blockTo = new Block(0, paramInt1, paramInt2 - 1, paramInt3);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, blockFrom, blockTo)) {
                if (i >= 8) {
                    paramfv.b(paramInt1, paramInt2 - 1, paramInt3, bi, i);
                } else {
                    paramfv.b(paramInt1, paramInt2 - 1, paramInt3, bi, i + 8);
                }
            }
        } else if ((i >= 0) && ((i == 0) || (k(paramfv, paramInt1, paramInt2 - 1, paramInt3)))) {
            boolean[] arrayOfBoolean = j(paramfv, paramInt1, paramInt2, paramInt3);
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
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, blockFrom, blockTo)) {
                    f(paramfv, paramInt1 - 1, paramInt2, paramInt3, n);
                }
            }
            if (arrayOfBoolean[1]) {
                Block blockTo = new Block(0, paramInt1 + 1, paramInt2, paramInt3);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, blockFrom, blockTo)) {
                    f(paramfv, paramInt1 + 1, paramInt2, paramInt3, n);
                }
            }
            if (arrayOfBoolean[2]) {
                Block blockTo = new Block(0, paramInt1, paramInt2, paramInt3 - 1);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, blockFrom, blockTo)) {
                    f(paramfv, paramInt1, paramInt2, paramInt3 - 1, n);
                }
            }
            if (arrayOfBoolean[3]) {
                Block blockTo = new Block(0, paramInt1, paramInt2, paramInt3 + 1);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, blockFrom, blockTo)) {
                    f(paramfv, paramInt1, paramInt2, paramInt3 + 1, n);
                }
            }
        }
    }

    private void f(fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (l(paramfv, paramInt1, paramInt2, paramInt3)) {
            int i = paramfv.a(paramInt1, paramInt2, paramInt3);
            if (i > 0) {
                if (bt == mh.g) {
                    h(paramfv, paramInt1, paramInt2, paramInt3);
                } else {
                    hr.m[i].a_(paramfv, paramInt1, paramInt2, paramInt3, paramfv.b(paramInt1, paramInt2, paramInt3));
                }
            }
            paramfv.b(paramInt1, paramInt2, paramInt3, bi, paramInt4);
        }
    }

    private int b(fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
        int i = 1000;
        for (int j = 0; j < 4; j++) {
            if (((j == 0) && (paramInt5 == 1))
                    || ((j == 1) && (paramInt5 == 0))
                    || ((j == 2) && (paramInt5 == 3)) || ((j == 3) && (paramInt5 == 2))) {
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

            if (k(paramfv, k, m, n)) {
                continue;
            }
            if ((paramfv.c(k, m, n) == bt) && (paramfv.b(k, m, n) == 0)) {
                continue;
            }
            if (!k(paramfv, k, m - 1, n)) {
                return paramInt4;
            }
            if (paramInt4 < 4) {
                int i1 = b(paramfv, k, m, n, paramInt4 + 1, j);
                if (i1 >= i) {
                    continue;
                }
                i = i1;
            }

        }

        return i;
    }

    private boolean[] j(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
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
            if (k(paramfv, j, k, m)) {
                continue;
            }
            if ((paramfv.c(j, k, m) == bt) && (paramfv.b(j, k, m) == 0)) {
                continue;
            }
            if (!k(paramfv, j, k - 1, m)) {
                c[i] = 0;
            } else {
                c[i] = b(paramfv, j, k, m, 1, i);
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

    private boolean k(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramfv.a(paramInt1, paramInt2, paramInt3);
        if ((i == hr.aE.bi) || (i == hr.aL.bi) || (i == hr.aD.bi) || (i == hr.aF.bi) || (i == hr.aX.bi)) {
            return true;
        }
        if (i == 0) {
            return false;
        }
        mh localmh = hr.m[i].bt;
        return localmh.a();
    }

    protected int e(fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = g(paramfv, paramInt1, paramInt2, paramInt3);
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

    private boolean l(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        // hMod: See if this liquid can destroy this block.
        Block block = new Block(paramfv.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
        PluginLoader.HookResult ret = (PluginLoader.HookResult) etc.getLoader().callHook(PluginLoader.Hook.LIQUID_DESTROY, this.bh, block);
        if (ret == PluginLoader.HookResult.PREVENT_ACTION) {
            return false;
        } else if (ret == PluginLoader.HookResult.ALLOW_ACTION) {
            return true;
        }

        mh localmh = paramfv.c(paramInt1, paramInt2, paramInt3);
        if (localmh == bt) {
            return false;
        }
        if (localmh == mh.g) {
            return false;
        }
        return !k(paramfv, paramInt1, paramInt2, paramInt3);
    }

    public void e(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        super.e(paramfv, paramInt1, paramInt2, paramInt3);
        if (paramfv.a(paramInt1, paramInt2, paramInt3) == bi) {
            paramfv.i(paramInt1, paramInt2, paramInt3, bi);
        }
    }
}
