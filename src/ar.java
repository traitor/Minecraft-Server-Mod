import java.util.Random;

public class ar extends da {
    int a = 0;
    boolean[] b = new boolean[4];
    int[] c = new int[4];

    protected ar(int paramInt, jx paramjx) {
        super(paramInt, paramjx);
    }

    private void i(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramep.b(paramInt1, paramInt2, paramInt3);
        paramep.a(paramInt1, paramInt2, paramInt3, this.bh + 1, i);
        paramep.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);
        paramep.f(paramInt1, paramInt2, paramInt3);
    }

    @Override
    public void a(ep paramep, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        // hMod: Store originating block
        Block blockFrom = new Block(this.bh, paramInt1, paramInt2, paramInt3);

        int i = g(paramep, paramInt1, paramInt2, paramInt3);

        int j = 1;
        if ((this.bs == jx.g) && (!paramep.q.d)) {
            j = 2;
        }

        int k = 1;
        int n;
        if (i > 0) {
            int m = -100;
            this.a = 0;
            m = e(paramep, paramInt1 - 1, paramInt2, paramInt3, m);
            m = e(paramep, paramInt1 + 1, paramInt2, paramInt3, m);
            m = e(paramep, paramInt1, paramInt2, paramInt3 - 1, m);
            m = e(paramep, paramInt1, paramInt2, paramInt3 + 1, m);

            n = m + j;
            if ((n >= 8) || (m < 0)) {
                n = -1;
            }
            if (g(paramep, paramInt1, paramInt2 + 1, paramInt3) >= 0) {
                int i1 = g(paramep, paramInt1, paramInt2 + 1, paramInt3);
                if (i1 >= 8) {
                    n = i1;
                } else {
                    n = i1 + 8;
                }
            }
            if ((this.a >= 2) && (this.bs == jx.f)) {
                if (paramep.d(paramInt1, paramInt2 - 1, paramInt3)) {
                    n = 0;
                } else if ((paramep.c(paramInt1, paramInt2 - 1, paramInt3) == this.bs) && (paramep.b(paramInt1, paramInt2, paramInt3) == 0)) {
                    n = 0;
                }
            }
            if ((this.bs == jx.g) && (i < 8) && (n < 8) && (n > i) && (paramRandom.nextInt(4) != 0)) {
                n = i;
                k = 0;
            }

            if (n != i) {
                i = n;
                if (i < 0) {
                    paramep.d(paramInt1, paramInt2, paramInt3, 0);
                } else {
                    paramep.b(paramInt1, paramInt2, paramInt3, i);
                    paramep.h(paramInt1, paramInt2, paramInt3, this.bh);
                    paramep.g(paramInt1, paramInt2, paramInt3, this.bh);
                }
            } else if (k != 0) {
                i(paramep, paramInt1, paramInt2, paramInt3);
            }
        } else {
            i(paramep, paramInt1, paramInt2, paramInt3);
        }
        if (l(paramep, paramInt1, paramInt2 - 1, paramInt3)) {
            // hMod: downwards flow.
            Block blockTo = new Block(0, paramInt1, paramInt2 - 1, paramInt3);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, new Object[]{blockFrom, blockTo})) {
                if (i >= 8) {
                    paramep.b(paramInt1, paramInt2 - 1, paramInt3, this.bh, i);
                } else {
                    paramep.b(paramInt1, paramInt2 - 1, paramInt3, this.bh, i + 8);
                }
            }
        } else if ((i >= 0) && ((i == 0) || (k(paramep, paramInt1, paramInt2 - 1, paramInt3)))) {
            boolean[] arrayOfBoolean = j(paramep, paramInt1, paramInt2, paramInt3);
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
                f(paramep, paramInt1 - 1, paramInt2, paramInt3, n);
            }
            if (arrayOfBoolean[1] != false) {
                Block blockTo = new Block(0, paramInt1 + 1, paramInt2, paramInt3);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, new Object[]{blockFrom, blockTo}))
                f(paramep, paramInt1 + 1, paramInt2, paramInt3, n);
            }
            if (arrayOfBoolean[2] != false) {
                Block blockTo = new Block(0, paramInt1, paramInt2, paramInt3 - 1);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, new Object[]{blockFrom, blockTo}))
                f(paramep, paramInt1, paramInt2, paramInt3 - 1, n);
            }
            if (arrayOfBoolean[3] != false) {
                Block blockTo = new Block(0, paramInt1, paramInt2, paramInt3 + 1);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, new Object[]{blockFrom, blockTo}))
                f(paramep, paramInt1, paramInt2, paramInt3 + 1, n);
            }
        }
    }

    private void f(ep paramep, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (l(paramep, paramInt1, paramInt2, paramInt3)) {
            int i = paramep.a(paramInt1, paramInt2, paramInt3);
            if (i > 0) {
                if (this.bs == jx.g) {
                    h(paramep, paramInt1, paramInt2, paramInt3);
                } else {
                    gb.m[i].a_(paramep, paramInt1, paramInt2, paramInt3, paramep.b(paramInt1, paramInt2, paramInt3));
                }
            }
            paramep.b(paramInt1, paramInt2, paramInt3, this.bh, paramInt4);
        }
    }

    private int a(ep paramep, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
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

            if (k(paramep, k, m, n)) {
                continue;
            }
            if ((paramep.c(k, m, n) == this.bs) && (paramep.b(k, m, n) == 0)) {
                continue;
            }
            if (!k(paramep, k, m - 1, n)) {
                return paramInt4;
            }
            if (paramInt4 < 4) {
                int i1 = a(paramep, k, m, n, paramInt4 + 1, j);
                if (i1 >= i) {
                    continue;
                }
                i = i1;
            }

        }

        return i;
    }

    private boolean[] j(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
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
            if (k(paramep, j, k, m)) {
                continue;
            }
            if ((paramep.c(j, k, m) == this.bs) && (paramep.b(j, k, m) == 0)) {
                continue;
            }
            if (!k(paramep, j, k - 1, m)) {
                this.c[i] = 0;
            } else {
                this.c[i] = a(paramep, j, k, m, 1, i);
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

    private boolean k(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramep.a(paramInt1, paramInt2, paramInt3);
        if ((i == gb.aE.bh) || (i == gb.aL.bh) || (i == gb.aD.bh) || (i == gb.aF.bh) || (i == gb.aX.bh)) {
            return true;
        }
        if (i == 0) {
            return false;
        }
        jx localjx = gb.m[i].bs;
        return localjx.a();
    }

    protected int e(ep paramep, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = g(paramep, paramInt1, paramInt2, paramInt3);
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

    private boolean l(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        // hMod: See if this liquid can destroy this block.
        Block block = new Block(paramep.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
        PluginLoader.HookResult ret = (PluginLoader.HookResult) etc.getLoader().callHook(PluginLoader.Hook.LIQUID_DESTROY, new Object[]{this.bh, block});
        if (ret == PluginLoader.HookResult.PREVENT_ACTION) {
            return false;
        } else if (ret == PluginLoader.HookResult.ALLOW_ACTION) {
            return true;
        }

        jx localjx = paramep.c(paramInt1, paramInt2, paramInt3);
        if (localjx == this.bs) {
            return false;
        }
        if (localjx == jx.g) {
            return false;
        }
        return !k(paramep, paramInt1, paramInt2, paramInt3);
    }

    @Override
    public void e(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        super.e(paramep, paramInt1, paramInt2, paramInt3);
        if (paramep.a(paramInt1, paramInt2, paramInt3) == this.bh) {
            paramep.h(paramInt1, paramInt2, paramInt3, this.bh);
        }
    }
}
