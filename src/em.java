
import java.util.Random;

public class em extends gv {

    private boolean a = true;

    public em(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, la.n);
        a(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
    }

    public el d(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    public boolean a() {
        return false;
    }

    public boolean a(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        return paramff.d(paramInt1, paramInt2 - 1, paramInt3);
    }

    private void g(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramff.b(paramInt1, paramInt2, paramInt3);
        int j = 0;

        this.a = false;
        boolean bool = paramff.o(paramInt1, paramInt2, paramInt3);
        this.a = true;
        int k;
        int m;
        int n;
        if (bool) {
            j = 15;
        } else {
            for (k = 0; k < 4; k++) {
                m = paramInt1;
                n = paramInt3;
                if (k == 0) {
                    m--;
                }
                if (k == 1) {
                    m++;
                }
                if (k == 2) {
                    n--;
                }
                if (k == 3) {
                    n++;
                }

                j = f(paramff, m, paramInt2, n, j);
                if ((paramff.d(m, paramInt2, n)) && (!paramff.d(paramInt1, paramInt2 + 1, paramInt3))) {
                    j = f(paramff, m, paramInt2 + 1, n, j);
                } else if (!paramff.d(m, paramInt2, n)) {
                    j = f(paramff, m, paramInt2 - 1, n, j);
                }
            }
            if (j > 0) {
                j--;
            } else {
                j = 0;
            }
        }
         // hMod: Allow redstone wire current changes
        if (i != j) {
            j = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Object[]{new Block(this.bh, paramInt1, paramInt2, paramInt3), i, j});
        }
        if (i != j) {
            paramff.b(paramInt1, paramInt2, paramInt3, j);
            paramff.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);

            if (j > 0) {
                j--;
            }
            for (k = 0; k < 4; k++) {
                m = paramInt1;
                n = paramInt3;
                int i1 = paramInt2 - 1;
                if (k == 0) {
                    m--;
                }
                if (k == 1) {
                    m++;
                }
                if (k == 2) {
                    n--;
                }
                if (k == 3) {
                    n++;
                }

                if (paramff.d(m, paramInt2, n)) {
                    i1 += 2;
                }

                int i2 = f(paramff, m, paramInt2, n, -1);
                if ((i2 >= 0) && (i2 != j)) {
                    g(paramff, m, paramInt2, n);
                }
                i2 = f(paramff, m, i1, n, -1);
                if ((i2 >= 0) && (i2 != j)) {
                    g(paramff, m, i1, n);
                }
            }

            if ((i == 0) || (j == 0)) {
                paramff.g(paramInt1, paramInt2, paramInt3, this.bh);
                paramff.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
                paramff.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
                paramff.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
                paramff.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);

                paramff.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
                paramff.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
            }
        }
    }

    private void h(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        if (paramff.a(paramInt1, paramInt2, paramInt3) != this.bh) {
            return;
        }

        paramff.g(paramInt1, paramInt2, paramInt3, this.bh);
        paramff.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
        paramff.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
        paramff.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
        paramff.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);

        paramff.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
        paramff.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
    }

    public void e(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        super.e(paramff, paramInt1, paramInt2, paramInt3);
        if (paramff.z) {
            return;
        }

        g(paramff, paramInt1, paramInt2, paramInt3);
        paramff.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
        paramff.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);

        h(paramff, paramInt1 - 1, paramInt2, paramInt3);
        h(paramff, paramInt1 + 1, paramInt2, paramInt3);
        h(paramff, paramInt1, paramInt2, paramInt3 - 1);
        h(paramff, paramInt1, paramInt2, paramInt3 + 1);

        if (paramff.d(paramInt1 - 1, paramInt2, paramInt3)) {
            h(paramff, paramInt1 - 1, paramInt2 + 1, paramInt3);
        } else {
            h(paramff, paramInt1 - 1, paramInt2 - 1, paramInt3);
        }
        if (paramff.d(paramInt1 + 1, paramInt2, paramInt3)) {
            h(paramff, paramInt1 + 1, paramInt2 + 1, paramInt3);
        } else {
            h(paramff, paramInt1 + 1, paramInt2 - 1, paramInt3);
        }
        if (paramff.d(paramInt1, paramInt2, paramInt3 - 1)) {
            h(paramff, paramInt1, paramInt2 + 1, paramInt3 - 1);
        } else {
            h(paramff, paramInt1, paramInt2 - 1, paramInt3 - 1);
        }
        if (paramff.d(paramInt1, paramInt2, paramInt3 + 1)) {
            h(paramff, paramInt1, paramInt2 + 1, paramInt3 + 1);
        } else {
            h(paramff, paramInt1, paramInt2 - 1, paramInt3 + 1);
        }
    }

    public void b(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        super.b(paramff, paramInt1, paramInt2, paramInt3);
        if (paramff.z) {
            return;
        }

        paramff.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
        paramff.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
        g(paramff, paramInt1, paramInt2, paramInt3);

        h(paramff, paramInt1 - 1, paramInt2, paramInt3);
        h(paramff, paramInt1 + 1, paramInt2, paramInt3);
        h(paramff, paramInt1, paramInt2, paramInt3 - 1);
        h(paramff, paramInt1, paramInt2, paramInt3 + 1);

        if (paramff.d(paramInt1 - 1, paramInt2, paramInt3)) {
            h(paramff, paramInt1 - 1, paramInt2 + 1, paramInt3);
        } else {
            h(paramff, paramInt1 - 1, paramInt2 - 1, paramInt3);
        }
        if (paramff.d(paramInt1 + 1, paramInt2, paramInt3)) {
            h(paramff, paramInt1 + 1, paramInt2 + 1, paramInt3);
        } else {
            h(paramff, paramInt1 + 1, paramInt2 - 1, paramInt3);
        }
        if (paramff.d(paramInt1, paramInt2, paramInt3 - 1)) {
            h(paramff, paramInt1, paramInt2 + 1, paramInt3 - 1);
        } else {
            h(paramff, paramInt1, paramInt2 - 1, paramInt3 - 1);
        }
        if (paramff.d(paramInt1, paramInt2, paramInt3 + 1)) {
            h(paramff, paramInt1, paramInt2 + 1, paramInt3 + 1);
        } else {
            h(paramff, paramInt1, paramInt2 - 1, paramInt3 + 1);
        }
    }

    private int f(ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramff.a(paramInt1, paramInt2, paramInt3) != this.bh) {
            return paramInt4;
        }
        int i = paramff.b(paramInt1, paramInt2, paramInt3);
        if (i > paramInt4) {
            return i;
        }
        return paramInt4;
    }

    public void b(ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramff.z) {
            return;
        }
        int i = paramff.b(paramInt1, paramInt2, paramInt3);

        boolean bool = a(paramff, paramInt1, paramInt2, paramInt3);

        if (!bool) {
            a_(paramff, paramInt1, paramInt2, paramInt3, i);
            paramff.d(paramInt1, paramInt2, paramInt3, 0);
        } else {
            g(paramff, paramInt1, paramInt2, paramInt3);
        }

        super.b(paramff, paramInt1, paramInt2, paramInt3, paramInt4);
    }

    public int a(int paramInt, Random paramRandom) {
        return gm.aA.aW;
    }

    public boolean d(ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (!this.a) {
            return false;
        }
        // hMod: Forced downcast!
        return b((jx)paramff, paramInt1, paramInt2, paramInt3, paramInt4);
    }

    public boolean b(jx paramjw, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (!this.a) {
            return false;
        }
        if (paramjw.b(paramInt1, paramInt2, paramInt3) == 0) {
            return false;
        }

        if (paramInt4 == 1) {
            return true;
        }

        int i = (b(paramjw, paramInt1 - 1, paramInt2, paramInt3)) || ((!paramjw.d(paramInt1 - 1, paramInt2, paramInt3)) && (b(paramjw, paramInt1 - 1, paramInt2 - 1, paramInt3))) ? 1 : 0;
        int j = (b(paramjw, paramInt1 + 1, paramInt2, paramInt3)) || ((!paramjw.d(paramInt1 + 1, paramInt2, paramInt3)) && (b(paramjw, paramInt1 + 1, paramInt2 - 1, paramInt3))) ? 1 : 0;
        int k = (b(paramjw, paramInt1, paramInt2, paramInt3 - 1)) || ((!paramjw.d(paramInt1, paramInt2, paramInt3 - 1)) && (b(paramjw, paramInt1, paramInt2 - 1, paramInt3 - 1))) ? 1 : 0;
        int m = (b(paramjw, paramInt1, paramInt2, paramInt3 + 1)) || ((!paramjw.d(paramInt1, paramInt2, paramInt3 + 1)) && (b(paramjw, paramInt1, paramInt2 - 1, paramInt3 + 1))) ? 1 : 0;
        if (!paramjw.d(paramInt1, paramInt2 + 1, paramInt3)) {
            if ((paramjw.d(paramInt1 - 1, paramInt2, paramInt3)) && (b(paramjw, paramInt1 - 1, paramInt2 + 1, paramInt3))) {
                i = 1;
            }
            if ((paramjw.d(paramInt1 + 1, paramInt2, paramInt3)) && (b(paramjw, paramInt1 + 1, paramInt2 + 1, paramInt3))) {
                j = 1;
            }
            if ((paramjw.d(paramInt1, paramInt2, paramInt3 - 1)) && (b(paramjw, paramInt1, paramInt2 + 1, paramInt3 - 1))) {
                k = 1;
            }
            if ((paramjw.d(paramInt1, paramInt2, paramInt3 + 1)) && (b(paramjw, paramInt1, paramInt2 + 1, paramInt3 + 1))) {
                m = 1;
            }
        }

        if ((k == 0) && (j == 0) && (i == 0) && (m == 0) && (paramInt4 >= 2) && (paramInt4 <= 5)) {
            return true;
        }

        if ((paramInt4 == 2) && (k != 0) && (i == 0) && (j == 0)) {
            return true;
        }
        if ((paramInt4 == 3) && (m != 0) && (i == 0) && (j == 0)) {
            return true;
        }
        if ((paramInt4 == 4) && (i != 0) && (k == 0) && (m == 0)) {
            return true;
        }
        return (paramInt4 == 5) && (j != 0) && (k == 0) && (m == 0);
    }

    public boolean c() {
        return this.a;
    }

    public static boolean b(jx paramjw, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramjw.a(paramInt1, paramInt2, paramInt3);
        if (i == gv.av.bh) {
            return true;
        }
        if (i == 0) {
            return false;
        }
        return gv.m[i].c();
    }
}
