
import java.util.Random;

public class dv extends ga {

    private boolean a = true;

    public dv(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, jw.n);
        a(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
    }

    public du d(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    public boolean a() {
        return false;
    }

    public boolean a(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        return parameo.d(paramInt1, paramInt2 - 1, paramInt3);
    }

    private void g(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        int i = parameo.b(paramInt1, paramInt2, paramInt3);
        int j = 0;

        this.a = false;
        boolean bool = parameo.n(paramInt1, paramInt2, paramInt3);
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

                j = f(parameo, m, paramInt2, n, j);
                if ((parameo.d(m, paramInt2, n)) && (!parameo.d(paramInt1, paramInt2 + 1, paramInt3))) {
                    j = f(parameo, m, paramInt2 + 1, n, j);
                } else if (!parameo.d(m, paramInt2, n)) {
                    j = f(parameo, m, paramInt2 - 1, n, j);
                }
            }
            if (j > 0) {
                j--;
            } else {
                j = 0;
            }
        }
        if (i != j) {
            // hMod: Allow redstone wire current changes
            j = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Object[]{new Block(this.bh, paramInt1, paramInt2, paramInt3), i, j});
        }
        if (i != j) {
            parameo.b(paramInt1, paramInt2, paramInt3, j);
            parameo.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);

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

                if (parameo.d(m, paramInt2, n)) {
                    i1 += 2;
                }

                int i2 = f(parameo, m, paramInt2, n, -1);
                if ((i2 >= 0) && (i2 != j)) {
                    g(parameo, m, paramInt2, n);
                }
                i2 = f(parameo, m, i1, n, -1);
                if ((i2 >= 0) && (i2 != j)) {
                    g(parameo, m, i1, n);
                }
            }

            if ((i == 0) || (j == 0)) {
                parameo.g(paramInt1, paramInt2, paramInt3, this.bh);
                parameo.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
                parameo.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
                parameo.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
                parameo.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);

                parameo.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
                parameo.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
            }
        }
    }

    private void h(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        if (parameo.a(paramInt1, paramInt2, paramInt3) != this.bh) {
            return;
        }

        parameo.g(paramInt1, paramInt2, paramInt3, this.bh);
        parameo.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
        parameo.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
        parameo.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
        parameo.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);

        parameo.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
        parameo.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
    }

    public void e(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        super.e(parameo, paramInt1, paramInt2, paramInt3);
        if (parameo.z) {
            return;
        }

        g(parameo, paramInt1, paramInt2, paramInt3);
        parameo.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
        parameo.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);

        h(parameo, paramInt1 - 1, paramInt2, paramInt3);
        h(parameo, paramInt1 + 1, paramInt2, paramInt3);
        h(parameo, paramInt1, paramInt2, paramInt3 - 1);
        h(parameo, paramInt1, paramInt2, paramInt3 + 1);

        if (parameo.d(paramInt1 - 1, paramInt2, paramInt3)) {
            h(parameo, paramInt1 - 1, paramInt2 + 1, paramInt3);
        } else {
            h(parameo, paramInt1 - 1, paramInt2 - 1, paramInt3);
        }
        if (parameo.d(paramInt1 + 1, paramInt2, paramInt3)) {
            h(parameo, paramInt1 + 1, paramInt2 + 1, paramInt3);
        } else {
            h(parameo, paramInt1 + 1, paramInt2 - 1, paramInt3);
        }
        if (parameo.d(paramInt1, paramInt2, paramInt3 - 1)) {
            h(parameo, paramInt1, paramInt2 + 1, paramInt3 - 1);
        } else {
            h(parameo, paramInt1, paramInt2 - 1, paramInt3 - 1);
        }
        if (parameo.d(paramInt1, paramInt2, paramInt3 + 1)) {
            h(parameo, paramInt1, paramInt2 + 1, paramInt3 + 1);
        } else {
            h(parameo, paramInt1, paramInt2 - 1, paramInt3 + 1);
        }
    }

    public void b(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        super.b(parameo, paramInt1, paramInt2, paramInt3);
        if (parameo.z) {
            return;
        }

        parameo.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
        parameo.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
        g(parameo, paramInt1, paramInt2, paramInt3);

        h(parameo, paramInt1 - 1, paramInt2, paramInt3);
        h(parameo, paramInt1 + 1, paramInt2, paramInt3);
        h(parameo, paramInt1, paramInt2, paramInt3 - 1);
        h(parameo, paramInt1, paramInt2, paramInt3 + 1);

        if (parameo.d(paramInt1 - 1, paramInt2, paramInt3)) {
            h(parameo, paramInt1 - 1, paramInt2 + 1, paramInt3);
        } else {
            h(parameo, paramInt1 - 1, paramInt2 - 1, paramInt3);
        }
        if (parameo.d(paramInt1 + 1, paramInt2, paramInt3)) {
            h(parameo, paramInt1 + 1, paramInt2 + 1, paramInt3);
        } else {
            h(parameo, paramInt1 + 1, paramInt2 - 1, paramInt3);
        }
        if (parameo.d(paramInt1, paramInt2, paramInt3 - 1)) {
            h(parameo, paramInt1, paramInt2 + 1, paramInt3 - 1);
        } else {
            h(parameo, paramInt1, paramInt2 - 1, paramInt3 - 1);
        }
        if (parameo.d(paramInt1, paramInt2, paramInt3 + 1)) {
            h(parameo, paramInt1, paramInt2 + 1, paramInt3 + 1);
        } else {
            h(parameo, paramInt1, paramInt2 - 1, paramInt3 + 1);
        }
    }

    private int f(eo parameo, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (parameo.a(paramInt1, paramInt2, paramInt3) != this.bh) {
            return paramInt4;
        }
        int i = parameo.b(paramInt1, paramInt2, paramInt3);
        if (i > paramInt4) {
            return i;
        }
        return paramInt4;
    }

    public void b(eo parameo, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (parameo.z) {
            return;
        }
        int i = parameo.b(paramInt1, paramInt2, paramInt3);

        boolean bool = a(parameo, paramInt1, paramInt2, paramInt3);

        if (!bool) {
            a_(parameo, paramInt1, paramInt2, paramInt3, i);
            parameo.d(paramInt1, paramInt2, paramInt3, 0);
        } else {
            g(parameo, paramInt1, paramInt2, paramInt3);
        }

        super.b(parameo, paramInt1, paramInt2, paramInt3, paramInt4);
    }

    public int a(int paramInt, Random paramRandom) {
        return fu.aA.aW;
    }

    public boolean d(eo parameo, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (!this.a) {
            return false;
        }
        return b((it) parameo, paramInt1, paramInt2, paramInt3, paramInt4);
    }

    public boolean b(it paramit, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (!this.a) {
            return false;
        }
        if (paramit.b(paramInt1, paramInt2, paramInt3) == 0) {
            return false;
        }

        if (paramInt4 == 1) {
            return true;
        }

        int i = (b(paramit, paramInt1 - 1, paramInt2, paramInt3)) || ((!paramit.d(paramInt1 - 1, paramInt2, paramInt3)) && (b(paramit, paramInt1 - 1, paramInt2 - 1, paramInt3))) ? 1 : 0;
        int j = (b(paramit, paramInt1 + 1, paramInt2, paramInt3)) || ((!paramit.d(paramInt1 + 1, paramInt2, paramInt3)) && (b(paramit, paramInt1 + 1, paramInt2 - 1, paramInt3))) ? 1 : 0;
        int k = (b(paramit, paramInt1, paramInt2, paramInt3 - 1)) || ((!paramit.d(paramInt1, paramInt2, paramInt3 - 1)) && (b(paramit, paramInt1, paramInt2 - 1, paramInt3 - 1))) ? 1 : 0;
        int m = (b(paramit, paramInt1, paramInt2, paramInt3 + 1)) || ((!paramit.d(paramInt1, paramInt2, paramInt3 + 1)) && (b(paramit, paramInt1, paramInt2 - 1, paramInt3 + 1))) ? 1 : 0;
        if (!paramit.d(paramInt1, paramInt2 + 1, paramInt3)) {
            if ((paramit.d(paramInt1 - 1, paramInt2, paramInt3)) && (b(paramit, paramInt1 - 1, paramInt2 + 1, paramInt3))) {
                i = 1;
            }
            if ((paramit.d(paramInt1 + 1, paramInt2, paramInt3)) && (b(paramit, paramInt1 + 1, paramInt2 + 1, paramInt3))) {
                j = 1;
            }
            if ((paramit.d(paramInt1, paramInt2, paramInt3 - 1)) && (b(paramit, paramInt1, paramInt2 + 1, paramInt3 - 1))) {
                k = 1;
            }
            if ((paramit.d(paramInt1, paramInt2, paramInt3 + 1)) && (b(paramit, paramInt1, paramInt2 + 1, paramInt3 + 1))) {
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

    public static boolean b(it paramit, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramit.a(paramInt1, paramInt2, paramInt3);
        if (i == ga.av.bh) {
            return true;
        }
        if (i == 0) {
            return false;
        }
        return ga.m[i].c();
    }
}
