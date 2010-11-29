
import java.util.Random;

public class ar extends cz {

    int a = 0;
    boolean[] b = new boolean[4];
    int[] c = new int[4];

    protected ar(int paramInt, jw paramjw) {
        super(paramInt, paramjw);
    }

    private void i(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        int i = parameo.b(paramInt1, paramInt2, paramInt3);
        parameo.a(paramInt1, paramInt2, paramInt3, this.bh + 1, i);
        parameo.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);
        parameo.f(paramInt1, paramInt2, paramInt3);
    }

    public void a(eo parameo, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        // hMod: Store originating block
        Block blockFrom = new Block(this.bh, paramInt1, paramInt2, paramInt3);

        int i = g(parameo, paramInt1, paramInt2, paramInt3);

        int j = 1;
        if ((this.bs == jw.g) && (!parameo.q.d)) {
            j = 2;
        }

        int k = 1;
        int n;
        if (i > 0) {
            int m = -100;
            this.a = 0;
            m = e(parameo, paramInt1 - 1, paramInt2, paramInt3, m);
            m = e(parameo, paramInt1 + 1, paramInt2, paramInt3, m);
            m = e(parameo, paramInt1, paramInt2, paramInt3 - 1, m);
            m = e(parameo, paramInt1, paramInt2, paramInt3 + 1, m);

            n = m + j;
            if ((n >= 8) || (m < 0)) {
                n = -1;
            }
            if (g(parameo, paramInt1, paramInt2 + 1, paramInt3) >= 0) {
                int i1 = g(parameo, paramInt1, paramInt2 + 1, paramInt3);
                if (i1 >= 8) {
                    n = i1;
                } else {
                    n = i1 + 8;
                }
            }
            if ((this.a >= 2) && (this.bs == jw.f)) {
                if (parameo.d(paramInt1, paramInt2 - 1, paramInt3)) {
                    n = 0;
                } else if ((parameo.c(paramInt1, paramInt2 - 1, paramInt3) == this.bs)
                        && (parameo.b(paramInt1, paramInt2, paramInt3) == 0)) {
                    n = 0;
                }
            }
            if ((this.bs == jw.g) && (i < 8) && (n < 8) && (n > i) && (paramRandom.nextInt(4) != 0)) {
                n = i;
                k = 0;
            }

            if (n != i) {
                i = n;
                if (i < 0) {
                    parameo.d(paramInt1, paramInt2, paramInt3, 0);
                } else {
                    parameo.b(paramInt1, paramInt2, paramInt3, i);
                    parameo.h(paramInt1, paramInt2, paramInt3, this.bh);
                    parameo.g(paramInt1, paramInt2, paramInt3, this.bh);
                }
            } else if (k != 0) {
                i(parameo, paramInt1, paramInt2, paramInt3);
            }
        } else {
            i(parameo, paramInt1, paramInt2, paramInt3);
        }
        if (l(parameo, paramInt1, paramInt2 - 1, paramInt3)) {
            // hMod: downwards flow.
            Block blockTo = new Block(0, paramInt1, paramInt2 - 1, paramInt3);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, new Object[]{blockFrom, blockTo})) {
                if (i >= 8) {
                    parameo.b(paramInt1, paramInt2 - 1, paramInt3, this.bh, i);
                } else {
                    parameo.b(paramInt1, paramInt2 - 1, paramInt3, this.bh, i + 8);
                }
            }
        } else if ((i >= 0) && ((i == 0) || (k(parameo, paramInt1, paramInt2 - 1, paramInt3)))) {
            boolean[] arrayOfBoolean = j(parameo, paramInt1, paramInt2, paramInt3);
            n = i + j;
            if (i >= 8) {
                n = 1;
            }
            if (n >= 8) {
                return;
            }
            // hMod: sidewards flow.
            if (arrayOfBoolean[0] != false) {
                Block blockTo = new Block(0, paramInt1 - 1, paramInt2, paramInt3);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, new Object[]{blockFrom, blockTo}))
                    f(parameo, paramInt1 - 1, paramInt2, paramInt3, n);
            }
            if (arrayOfBoolean[1] != false) {
                Block blockTo = new Block(0, paramInt1 + 1, paramInt2, paramInt3);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, new Object[]{blockFrom, blockTo}))
                    f(parameo, paramInt1 + 1, paramInt2, paramInt3, n);
            }
            if (arrayOfBoolean[2] != false) {
                Block blockTo = new Block(0, paramInt1, paramInt2, paramInt3 - 1);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, new Object[]{blockFrom, blockTo}))
                    f(parameo, paramInt1, paramInt2, paramInt3 - 1, n);
            }
            if (arrayOfBoolean[3] != false) {
                Block blockTo = new Block(0, paramInt1, paramInt2, paramInt3 + 1);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, new Object[]{blockFrom, blockTo}))
                    f(parameo, paramInt1, paramInt2, paramInt3 + 1, n);
            }
        }
    }

    private void f(eo parameo, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (l(parameo, paramInt1, paramInt2, paramInt3)) {
            int i = parameo.a(paramInt1, paramInt2, paramInt3);
            if (i > 0) {
                if (this.bs == jw.g) {
                    h(parameo, paramInt1, paramInt2, paramInt3);
                } else {
                    ga.m[i].a_(parameo, paramInt1, paramInt2, paramInt3, parameo.b(paramInt1, paramInt2, paramInt3));
                }
            }
            parameo.b(paramInt1, paramInt2, paramInt3, this.bh, paramInt4);
        }
    }

    private int a(eo parameo, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
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

            if (k(parameo, k, m, n)) {
                continue;
            }
            if ((parameo.c(k, m, n) == this.bs) && (parameo.b(k, m, n) == 0)) {
                continue;
            }
            if (!k(parameo, k, m - 1, n)) {
                return paramInt4;
            }
            if (paramInt4 < 4) {
                int i1 = a(parameo, k, m, n, paramInt4 + 1, j);
                if (i1 >= i)
                    continue;
                i = i1;
            }

        }

        return i;
    }

    private boolean[] j(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        for (int i = 0; i < 4; i++) {
            this.c[i] = 1000;
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
            if (k(parameo, j, k, m)) {
                continue;
            }
            if ((parameo.c(j, k, m) == this.bs) && (parameo.b(j, k, m) == 0)) {
                continue;
            }
            if (!k(parameo, j, k - 1, m)) {
                this.c[i] = 0;
            } else {
                this.c[i] = a(parameo, j, k, m, 1, i);
            }

        }

        int i = this.c[0];
        for (int j = 1; j < 4; j++) {
            if (this.c[j] >= i) {
                continue;
            }
            i = this.c[j];
        }

        for (int j = 0; j < 4; j++) {
            this.b[j] = (this.c[j] == i ? true : false);
        }
        return this.b;
    }

    private boolean k(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        int i = parameo.a(paramInt1, paramInt2, paramInt3);
        if ((i == ga.aE.bh) || (i == ga.aL.bh) || (i == ga.aD.bh) || (i == ga.aF.bh) || (i == ga.aX.bh)) {
            return true;
        }
        if (i == 0) {
            return false;
        }
        jw localjw = ga.m[i].bs;
        return localjw.a();
    }

    protected int e(eo parameo, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = g(parameo, paramInt1, paramInt2, paramInt3);
        if (i < 0) {
            return paramInt4;
        }
        if (i == 0) {
            this.a += 1;
        }
        if (i >= 8) {
            i = 0;
        }
        return (paramInt4 < 0) || (i < paramInt4) ? i : paramInt4;
    }

    private boolean l(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        // hMod: See if this liquid can destroy this block.
        Block block = new Block(parameo.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
        PluginLoader.HookResult ret = (PluginLoader.HookResult) etc.getLoader().callHook(PluginLoader.Hook.LIQUID_DESTROY, new Object[]{this.bh, block});
        if (ret == PluginLoader.HookResult.PREVENT_ACTION) {
            return false;
        } else if (ret == PluginLoader.HookResult.ALLOW_ACTION) {
            return true;
        }
        
        jw localjw = parameo.c(paramInt1, paramInt2, paramInt3);
        if (localjw == this.bs) {
            return false;
        }
        if (localjw == jw.g) {
            return false;
        }
        return !k(parameo, paramInt1, paramInt2, paramInt3);
    }

    public void e(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        super.e(parameo, paramInt1, paramInt2, paramInt3);
        if (parameo.a(paramInt1, paramInt2, paramInt3) == this.bh) {
            parameo.h(paramInt1, paramInt2, paramInt3, this.bh);
        }
    }
}
