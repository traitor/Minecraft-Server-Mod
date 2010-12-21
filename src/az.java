
import java.util.Random;

public class az extends dn {

    int a = 0;
    boolean[] b = new boolean[4];
    int[] c = new int[4];

    protected az(int paramInt, kz paramkz) {
        super(paramInt, paramkz);
    }

    private void i(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramff.b(paramInt1, paramInt2, paramInt3);
        paramff.a(paramInt1, paramInt2, paramInt3, this.bh + 1, i);
        paramff.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);
        paramff.g(paramInt1, paramInt2, paramInt3);
    }

    public void a(ff paramff, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        // hMod: Store originating block
        Block blockFrom = new Block(this.bh, paramInt1, paramInt2, paramInt3);
        
        int i = g(paramff, paramInt1, paramInt2, paramInt3);

        int j = 1;
        if ((this.bs == kz.g) && (!paramff.q.d)) {
            j = 2;
        }

        int k = 1;
        int n;
        if (i > 0) {
            int m = -100;
            this.a = 0;
            m = e(paramff, paramInt1 - 1, paramInt2, paramInt3, m);
            m = e(paramff, paramInt1 + 1, paramInt2, paramInt3, m);
            m = e(paramff, paramInt1, paramInt2, paramInt3 - 1, m);
            m = e(paramff, paramInt1, paramInt2, paramInt3 + 1, m);

            n = m + j;
            if ((n >= 8) || (m < 0)) {
                n = -1;
            }
            if (g(paramff, paramInt1, paramInt2 + 1, paramInt3) >= 0) {
                int i1 = g(paramff, paramInt1, paramInt2 + 1, paramInt3);
                if (i1 >= 8) {
                    n = i1;
                } else {
                    n = i1 + 8;
                }
            }
            if ((this.a >= 2) && (this.bs == kz.f)) {
                if (paramff.d(paramInt1, paramInt2 - 1, paramInt3)) {
                    n = 0;
                } else if ((paramff.c(paramInt1, paramInt2 - 1, paramInt3) == this.bs) && (paramff.b(paramInt1, paramInt2, paramInt3) == 0)) {
                    n = 0;
                }
            }
            if ((this.bs == kz.g)
                    && (i < 8) && (n < 8)
                    && (n > i)
                    && (paramRandom.nextInt(4) != 0)) {
                n = i;
                k = 0;
            }

            if (n != i) {
                i = n;
                if (i < 0) {
                    paramff.d(paramInt1, paramInt2, paramInt3, 0);
                } else {
                    paramff.b(paramInt1, paramInt2, paramInt3, i);
                    paramff.h(paramInt1, paramInt2, paramInt3, this.bh);
                    paramff.g(paramInt1, paramInt2, paramInt3, this.bh);
                }
            } else if (k != 0) {
                i(paramff, paramInt1, paramInt2, paramInt3);
            }
        } else {
            i(paramff, paramInt1, paramInt2, paramInt3);
        }
        if (l(paramff, paramInt1, paramInt2 - 1, paramInt3)) {
            // hMod: downwards flow.
            Block blockTo = new Block(0, paramInt1, paramInt2 - 1, paramInt3);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, blockFrom, blockTo)) {
                if (i >= 8) {
                    paramff.b(paramInt1, paramInt2 - 1, paramInt3, this.bh, i);
                } else {
                    paramff.b(paramInt1, paramInt2 - 1, paramInt3, this.bh, i + 8);
                }
            }
        } else if ((i >= 0) && ((i == 0) || (k(paramff, paramInt1, paramInt2 - 1, paramInt3)))) {
            boolean[] arrayOfBoolean = j(paramff, paramInt1, paramInt2, paramInt3);
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
                    f(paramff, paramInt1 - 1, paramInt2, paramInt3, n);
                }
            }
            if (arrayOfBoolean[1]) {
                Block blockTo = new Block(0, paramInt1 + 1, paramInt2, paramInt3);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, blockFrom, blockTo)) {
                    f(paramff, paramInt1 + 1, paramInt2, paramInt3, n);
                }
            }
            if (arrayOfBoolean[2]) {
                Block blockTo = new Block(0, paramInt1, paramInt2, paramInt3 - 1);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, blockFrom, blockTo)) {
                    f(paramff, paramInt1, paramInt2, paramInt3 - 1, n);
                }
            }
            if (arrayOfBoolean[3]) {
                Block blockTo = new Block(0, paramInt1, paramInt2, paramInt3 + 1);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, blockFrom, blockTo)) {
                    f(paramff, paramInt1, paramInt2, paramInt3 + 1, n);
                }
            }
        }
    }

    private void f(ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (l(paramff, paramInt1, paramInt2, paramInt3)) {
            int i = paramff.a(paramInt1, paramInt2, paramInt3);
            if (i > 0) {
                if (this.bs == kz.g) {
                    h(paramff, paramInt1, paramInt2, paramInt3);
                } else {
                    gu.m[i].a_(paramff, paramInt1, paramInt2, paramInt3, paramff.b(paramInt1, paramInt2, paramInt3));
                }
            }
            paramff.b(paramInt1, paramInt2, paramInt3, this.bh, paramInt4);
        }
    }

    private int a(ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
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

            if (k(paramff, k, m, n)) {
                continue;
            }
            if ((paramff.c(k, m, n) == this.bs) && (paramff.b(k, m, n) == 0)) {
                continue;
            }
            if (!k(paramff, k, m - 1, n)) {
                return paramInt4;
            }
            if (paramInt4 < 4) {
                int i1 = a(paramff, k, m, n, paramInt4 + 1, j);
                if (i1 >= i) {
                    continue;
                }
                i = i1;
            }

        }

        return i;
    }

    private boolean[] j(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
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
            if (k(paramff, j, k, m)) {
                continue;
            }
            if ((paramff.c(j, k, m) == this.bs) && (paramff.b(j, k, m) == 0)) {
                continue;
            }
            if (!k(paramff, j, k - 1, m)) {
                this.c[i] = 0;
            } else {
                this.c[i] = a(paramff, j, k, m, 1, i);
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

    private boolean k(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        // hMod: See if this liquid can destroy this block.
        Block block = new Block(paramff.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
        PluginLoader.HookResult ret = (PluginLoader.HookResult) etc.getLoader().callHook(PluginLoader.Hook.LIQUID_DESTROY, this.bh, block);
        if (ret == PluginLoader.HookResult.PREVENT_ACTION) {
            return false;
        } else if (ret == PluginLoader.HookResult.ALLOW_ACTION) {
            return true;
        }
        
        int i = paramff.a(paramInt1, paramInt2, paramInt3);
        if ((i == gu.aE.bh) || (i == gu.aL.bh) || (i == gu.aD.bh) || (i == gu.aF.bh) || (i == gu.aX.bh)) {
            return true;
        }
        if (i == 0) {
            return false;
        }
        kz localkz = gu.m[i].bs;
        return localkz.a();
    }

    protected int e(ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = g(paramff, paramInt1, paramInt2, paramInt3);
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

    private boolean l(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        kz localkz = paramff.c(paramInt1, paramInt2, paramInt3);
        if (localkz == this.bs) {
            return false;
        }
        if (localkz == kz.g) {
            return false;
        }
        return !k(paramff, paramInt1, paramInt2, paramInt3);
    }

    public void e(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        super.e(paramff, paramInt1, paramInt2, paramInt3);
        if (paramff.a(paramInt1, paramInt2, paramInt3) == this.bh) {
            paramff.h(paramInt1, paramInt2, paramInt3, this.bh);
        }
    }
}
