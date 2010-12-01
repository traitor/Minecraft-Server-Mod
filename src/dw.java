import java.util.Random;

public class dw extends gb {
    private boolean a = true;

    public dw(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, jx.n);
        a(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
    }

    @Override
    public dv d(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public boolean a(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        return paramep.d(paramInt1, paramInt2 - 1, paramInt3);
    }

    private void g(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramep.b(paramInt1, paramInt2, paramInt3);
        int j = 0;

        this.a = false;
        boolean bool = paramep.n(paramInt1, paramInt2, paramInt3);
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

                j = f(paramep, m, paramInt2, n, j);
                if ((paramep.d(m, paramInt2, n)) && (!paramep.d(paramInt1, paramInt2 + 1, paramInt3))) {
                    j = f(paramep, m, paramInt2 + 1, n, j);
                } else if (!paramep.d(m, paramInt2, n)) {
                    j = f(paramep, m, paramInt2 - 1, n, j);
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
            paramep.b(paramInt1, paramInt2, paramInt3, j);
            paramep.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);

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

                if (paramep.d(m, paramInt2, n)) {
                    i1 += 2;
                }

                int i2 = f(paramep, m, paramInt2, n, -1);
                if ((i2 >= 0) && (i2 != j)) {
                    g(paramep, m, paramInt2, n);
                }
                i2 = f(paramep, m, i1, n, -1);
                if ((i2 >= 0) && (i2 != j)) {
                    g(paramep, m, i1, n);
                }
            }

            if ((i == 0) || (j == 0)) {
                paramep.g(paramInt1, paramInt2, paramInt3, this.bh);
                paramep.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
                paramep.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
                paramep.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
                paramep.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);

                paramep.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
                paramep.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
            }
        }
    }

    private void h(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        if (paramep.a(paramInt1, paramInt2, paramInt3) != this.bh) {
            return;
        }

        paramep.g(paramInt1, paramInt2, paramInt3, this.bh);
        paramep.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
        paramep.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
        paramep.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
        paramep.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);

        paramep.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
        paramep.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
    }

    @Override
    public void e(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        super.e(paramep, paramInt1, paramInt2, paramInt3);
        if (paramep.z) {
            return;
        }

        g(paramep, paramInt1, paramInt2, paramInt3);
        paramep.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
        paramep.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);

        h(paramep, paramInt1 - 1, paramInt2, paramInt3);
        h(paramep, paramInt1 + 1, paramInt2, paramInt3);
        h(paramep, paramInt1, paramInt2, paramInt3 - 1);
        h(paramep, paramInt1, paramInt2, paramInt3 + 1);

        if (paramep.d(paramInt1 - 1, paramInt2, paramInt3)) {
            h(paramep, paramInt1 - 1, paramInt2 + 1, paramInt3);
        } else {
            h(paramep, paramInt1 - 1, paramInt2 - 1, paramInt3);
        }
        if (paramep.d(paramInt1 + 1, paramInt2, paramInt3)) {
            h(paramep, paramInt1 + 1, paramInt2 + 1, paramInt3);
        } else {
            h(paramep, paramInt1 + 1, paramInt2 - 1, paramInt3);
        }
        if (paramep.d(paramInt1, paramInt2, paramInt3 - 1)) {
            h(paramep, paramInt1, paramInt2 + 1, paramInt3 - 1);
        } else {
            h(paramep, paramInt1, paramInt2 - 1, paramInt3 - 1);
        }
        if (paramep.d(paramInt1, paramInt2, paramInt3 + 1)) {
            h(paramep, paramInt1, paramInt2 + 1, paramInt3 + 1);
        } else {
            h(paramep, paramInt1, paramInt2 - 1, paramInt3 + 1);
        }
    }

    @Override
    public void b(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        super.b(paramep, paramInt1, paramInt2, paramInt3);
        if (paramep.z) {
            return;
        }

        paramep.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
        paramep.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
        g(paramep, paramInt1, paramInt2, paramInt3);

        h(paramep, paramInt1 - 1, paramInt2, paramInt3);
        h(paramep, paramInt1 + 1, paramInt2, paramInt3);
        h(paramep, paramInt1, paramInt2, paramInt3 - 1);
        h(paramep, paramInt1, paramInt2, paramInt3 + 1);

        if (paramep.d(paramInt1 - 1, paramInt2, paramInt3)) {
            h(paramep, paramInt1 - 1, paramInt2 + 1, paramInt3);
        } else {
            h(paramep, paramInt1 - 1, paramInt2 - 1, paramInt3);
        }
        if (paramep.d(paramInt1 + 1, paramInt2, paramInt3)) {
            h(paramep, paramInt1 + 1, paramInt2 + 1, paramInt3);
        } else {
            h(paramep, paramInt1 + 1, paramInt2 - 1, paramInt3);
        }
        if (paramep.d(paramInt1, paramInt2, paramInt3 - 1)) {
            h(paramep, paramInt1, paramInt2 + 1, paramInt3 - 1);
        } else {
            h(paramep, paramInt1, paramInt2 - 1, paramInt3 - 1);
        }
        if (paramep.d(paramInt1, paramInt2, paramInt3 + 1)) {
            h(paramep, paramInt1, paramInt2 + 1, paramInt3 + 1);
        } else {
            h(paramep, paramInt1, paramInt2 - 1, paramInt3 + 1);
        }
    }

    private int f(ep paramep, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramep.a(paramInt1, paramInt2, paramInt3) != this.bh) {
            return paramInt4;
        }
        int i = paramep.b(paramInt1, paramInt2, paramInt3);
        if (i > paramInt4) {
            return i;
        }
        return paramInt4;
    }

    @Override
    public void b(ep paramep, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramep.z) {
            return;
        }
        int i = paramep.b(paramInt1, paramInt2, paramInt3);

        boolean bool = a(paramep, paramInt1, paramInt2, paramInt3);

        if (!bool) {
            a_(paramep, paramInt1, paramInt2, paramInt3, i);
            paramep.d(paramInt1, paramInt2, paramInt3, 0);
        } else {
            g(paramep, paramInt1, paramInt2, paramInt3);
        }

        super.b(paramep, paramInt1, paramInt2, paramInt3, paramInt4);
    }

    @Override
    public int a(int paramInt, Random paramRandom) {
        return fv.aA.aW;
    }

    @Override
    public boolean d(ep paramep, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (!this.a) {
            return false;
        }
        // hMod: Forced downcast!
        return b((iu)paramep, paramInt1, paramInt2, paramInt3, paramInt4);
    }

    @Override
    public boolean b(iu paramiu, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (!this.a) {
            return false;
        }
        if (paramiu.b(paramInt1, paramInt2, paramInt3) == 0) {
            return false;
        }

        if (paramInt4 == 1) {
            return true;
        }

        int i = (b(paramiu, paramInt1 - 1, paramInt2, paramInt3)) || ((!paramiu.d(paramInt1 - 1, paramInt2, paramInt3)) && (b(paramiu, paramInt1 - 1, paramInt2 - 1, paramInt3))) ? 1 : 0;
        int j = (b(paramiu, paramInt1 + 1, paramInt2, paramInt3)) || ((!paramiu.d(paramInt1 + 1, paramInt2, paramInt3)) && (b(paramiu, paramInt1 + 1, paramInt2 - 1, paramInt3))) ? 1 : 0;
        int k = (b(paramiu, paramInt1, paramInt2, paramInt3 - 1)) || ((!paramiu.d(paramInt1, paramInt2, paramInt3 - 1)) && (b(paramiu, paramInt1, paramInt2 - 1, paramInt3 - 1))) ? 1 : 0;
        int m = (b(paramiu, paramInt1, paramInt2, paramInt3 + 1)) || ((!paramiu.d(paramInt1, paramInt2, paramInt3 + 1)) && (b(paramiu, paramInt1, paramInt2 - 1, paramInt3 + 1))) ? 1 : 0;
        if (!paramiu.d(paramInt1, paramInt2 + 1, paramInt3)) {
            if ((paramiu.d(paramInt1 - 1, paramInt2, paramInt3)) && (b(paramiu, paramInt1 - 1, paramInt2 + 1, paramInt3))) {
                i = 1;
            }
            if ((paramiu.d(paramInt1 + 1, paramInt2, paramInt3)) && (b(paramiu, paramInt1 + 1, paramInt2 + 1, paramInt3))) {
                j = 1;
            }
            if ((paramiu.d(paramInt1, paramInt2, paramInt3 - 1)) && (b(paramiu, paramInt1, paramInt2 + 1, paramInt3 - 1))) {
                k = 1;
            }
            if ((paramiu.d(paramInt1, paramInt2, paramInt3 + 1)) && (b(paramiu, paramInt1, paramInt2 + 1, paramInt3 + 1))) {
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

    @Override
    public boolean c() {
        return this.a;
    }

    public static boolean b(iu paramiu, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramiu.a(paramInt1, paramInt2, paramInt3);
        if (i == gb.av.bh) {
            return true;
        }
        if (i == 0) {
            return false;
        }
        return gb.m[i].c();
    }
}