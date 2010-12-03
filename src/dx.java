import java.util.Random;

public class dx extends gc {
    private boolean a = true;

    public dx(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, jy.n);
        a(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
    }

    @Override
    public dw d(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public boolean a(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        return parameq.d(paramInt1, paramInt2 - 1, paramInt3);
    }

    private void g(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        int i = parameq.b(paramInt1, paramInt2, paramInt3);
        int j = 0;

        this.a = false;
        boolean bool = parameq.n(paramInt1, paramInt2, paramInt3);
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

                j = f(parameq, m, paramInt2, n, j);
                if ((parameq.d(m, paramInt2, n)) && (!parameq.d(paramInt1, paramInt2 + 1, paramInt3))) {
                    j = f(parameq, m, paramInt2 + 1, n, j);
                } else if (!parameq.d(m, paramInt2, n)) {
                    j = f(parameq, m, paramInt2 - 1, n, j);
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
            parameq.b(paramInt1, paramInt2, paramInt3, j);
            parameq.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);

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

                if (parameq.d(m, paramInt2, n)) {
                    i1 += 2;
                }

                int i2 = f(parameq, m, paramInt2, n, -1);
                if ((i2 >= 0) && (i2 != j)) {
                    g(parameq, m, paramInt2, n);
                }
                i2 = f(parameq, m, i1, n, -1);
                if ((i2 >= 0) && (i2 != j)) {
                    g(parameq, m, i1, n);
                }
            }

            if ((i == 0) || (j == 0)) {
                parameq.g(paramInt1, paramInt2, paramInt3, this.bh);
                parameq.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
                parameq.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
                parameq.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
                parameq.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);

                parameq.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
                parameq.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
            }
        }
    }

    private void h(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        if (parameq.a(paramInt1, paramInt2, paramInt3) != this.bh) {
            return;
        }

        parameq.g(paramInt1, paramInt2, paramInt3, this.bh);
        parameq.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
        parameq.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
        parameq.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
        parameq.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);

        parameq.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
        parameq.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
    }

    @Override
    public void e(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        super.e(parameq, paramInt1, paramInt2, paramInt3);
        if (parameq.z) {
            return;
        }

        g(parameq, paramInt1, paramInt2, paramInt3);
        parameq.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
        parameq.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);

        h(parameq, paramInt1 - 1, paramInt2, paramInt3);
        h(parameq, paramInt1 + 1, paramInt2, paramInt3);
        h(parameq, paramInt1, paramInt2, paramInt3 - 1);
        h(parameq, paramInt1, paramInt2, paramInt3 + 1);

        if (parameq.d(paramInt1 - 1, paramInt2, paramInt3)) {
            h(parameq, paramInt1 - 1, paramInt2 + 1, paramInt3);
        } else {
            h(parameq, paramInt1 - 1, paramInt2 - 1, paramInt3);
        }
        if (parameq.d(paramInt1 + 1, paramInt2, paramInt3)) {
            h(parameq, paramInt1 + 1, paramInt2 + 1, paramInt3);
        } else {
            h(parameq, paramInt1 + 1, paramInt2 - 1, paramInt3);
        }
        if (parameq.d(paramInt1, paramInt2, paramInt3 - 1)) {
            h(parameq, paramInt1, paramInt2 + 1, paramInt3 - 1);
        } else {
            h(parameq, paramInt1, paramInt2 - 1, paramInt3 - 1);
        }
        if (parameq.d(paramInt1, paramInt2, paramInt3 + 1)) {
            h(parameq, paramInt1, paramInt2 + 1, paramInt3 + 1);
        } else {
            h(parameq, paramInt1, paramInt2 - 1, paramInt3 + 1);
        }
    }

    @Override
    public void b(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        super.b(parameq, paramInt1, paramInt2, paramInt3);
        if (parameq.z) {
            return;
        }

        parameq.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
        parameq.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
        g(parameq, paramInt1, paramInt2, paramInt3);

        h(parameq, paramInt1 - 1, paramInt2, paramInt3);
        h(parameq, paramInt1 + 1, paramInt2, paramInt3);
        h(parameq, paramInt1, paramInt2, paramInt3 - 1);
        h(parameq, paramInt1, paramInt2, paramInt3 + 1);

        if (parameq.d(paramInt1 - 1, paramInt2, paramInt3)) {
            h(parameq, paramInt1 - 1, paramInt2 + 1, paramInt3);
        } else {
            h(parameq, paramInt1 - 1, paramInt2 - 1, paramInt3);
        }
        if (parameq.d(paramInt1 + 1, paramInt2, paramInt3)) {
            h(parameq, paramInt1 + 1, paramInt2 + 1, paramInt3);
        } else {
            h(parameq, paramInt1 + 1, paramInt2 - 1, paramInt3);
        }
        if (parameq.d(paramInt1, paramInt2, paramInt3 - 1)) {
            h(parameq, paramInt1, paramInt2 + 1, paramInt3 - 1);
        } else {
            h(parameq, paramInt1, paramInt2 - 1, paramInt3 - 1);
        }
        if (parameq.d(paramInt1, paramInt2, paramInt3 + 1)) {
            h(parameq, paramInt1, paramInt2 + 1, paramInt3 + 1);
        } else {
            h(parameq, paramInt1, paramInt2 - 1, paramInt3 + 1);
        }
    }

    private int f(eq parameq, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (parameq.a(paramInt1, paramInt2, paramInt3) != this.bh) {
            return paramInt4;
        }
        int i = parameq.b(paramInt1, paramInt2, paramInt3);
        if (i > paramInt4) {
            return i;
        }
        return paramInt4;
    }

    @Override
    public void b(eq parameq, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (parameq.z) {
            return;
        }
        int i = parameq.b(paramInt1, paramInt2, paramInt3);

        boolean bool = a(parameq, paramInt1, paramInt2, paramInt3);

        if (!bool) {
            a_(parameq, paramInt1, paramInt2, paramInt3, i);
            parameq.d(paramInt1, paramInt2, paramInt3, 0);
        } else {
            g(parameq, paramInt1, paramInt2, paramInt3);
        }

        super.b(parameq, paramInt1, paramInt2, paramInt3, paramInt4);
    }

    @Override
    public int a(int paramInt, Random paramRandom) {
        return fw.aA.aW;
    }

    @Override
    public boolean d(eq parameq, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (!this.a) {
            return false;
        }
        // hMod: Forced downcast!
        return b((iv)parameq, paramInt1, paramInt2, paramInt3, paramInt4);
    }

    @Override
    public boolean b(iv paramiv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (!this.a) {
            return false;
        }
        if (paramiv.b(paramInt1, paramInt2, paramInt3) == 0) {
            return false;
        }

        if (paramInt4 == 1) {
            return true;
        }

        int i = (b(paramiv, paramInt1 - 1, paramInt2, paramInt3)) || ((!paramiv.d(paramInt1 - 1, paramInt2, paramInt3)) && (b(paramiv, paramInt1 - 1, paramInt2 - 1, paramInt3))) ? 1 : 0;
        int j = (b(paramiv, paramInt1 + 1, paramInt2, paramInt3)) || ((!paramiv.d(paramInt1 + 1, paramInt2, paramInt3)) && (b(paramiv, paramInt1 + 1, paramInt2 - 1, paramInt3))) ? 1 : 0;
        int k = (b(paramiv, paramInt1, paramInt2, paramInt3 - 1)) || ((!paramiv.d(paramInt1, paramInt2, paramInt3 - 1)) && (b(paramiv, paramInt1, paramInt2 - 1, paramInt3 - 1))) ? 1 : 0;
        int m = (b(paramiv, paramInt1, paramInt2, paramInt3 + 1)) || ((!paramiv.d(paramInt1, paramInt2, paramInt3 + 1)) && (b(paramiv, paramInt1, paramInt2 - 1, paramInt3 + 1))) ? 1 : 0;
        if (!paramiv.d(paramInt1, paramInt2 + 1, paramInt3)) {
            if ((paramiv.d(paramInt1 - 1, paramInt2, paramInt3)) && (b(paramiv, paramInt1 - 1, paramInt2 + 1, paramInt3))) {
                i = 1;
            }
            if ((paramiv.d(paramInt1 + 1, paramInt2, paramInt3)) && (b(paramiv, paramInt1 + 1, paramInt2 + 1, paramInt3))) {
                j = 1;
            }
            if ((paramiv.d(paramInt1, paramInt2, paramInt3 - 1)) && (b(paramiv, paramInt1, paramInt2 + 1, paramInt3 - 1))) {
                k = 1;
            }
            if ((paramiv.d(paramInt1, paramInt2, paramInt3 + 1)) && (b(paramiv, paramInt1, paramInt2 + 1, paramInt3 + 1))) {
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

    public static boolean b(iv paramiv, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramiv.a(paramInt1, paramInt2, paramInt3);
        if (i == gc.av.bh) {
            return true;
        }
        if (i == 0) {
            return false;
        }
        return gc.m[i].c();
    }
}
