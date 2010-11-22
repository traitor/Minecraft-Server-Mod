
import java.util.Random;

public class du extends fy {

    private boolean a = true;

    public du(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, jt.n);
        a(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
    }

    public dt d(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    public boolean a() {
        return false;
    }

    public boolean a(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        return paramem.d(paramInt1, paramInt2 - 1, paramInt3);
    }

    private void g(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramem.b(paramInt1, paramInt2, paramInt3);
        int j = 0;

        this.a = false;
        boolean bool = paramem.n(paramInt1, paramInt2, paramInt3);
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

                j = f(paramem, m, paramInt2, n, j);
                if ((paramem.d(m, paramInt2, n)) && (!paramem.d(paramInt1, paramInt2 + 1, paramInt3))) {
                    j = f(paramem, m, paramInt2 + 1, n, j);
                } else if (!paramem.d(m, paramInt2, n)) {
                    j = f(paramem, m, paramInt2 - 1, n, j);
                }
            }
            if (j > 0) {
                j--;
            } else {
                j = 0;
            }
        }
        if (i != j) {
            paramem.b(paramInt1, paramInt2, paramInt3, j);
            paramem.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);

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

                if (paramem.d(m, paramInt2, n)) {
                    i1 += 2;
                }

                int i2 = f(paramem, m, paramInt2, n, -1);
                if ((i2 >= 0) && (i2 != j)) {
                    g(paramem, m, paramInt2, n);
                }
                i2 = f(paramem, m, i1, n, -1);
                if ((i2 >= 0) && (i2 != j)) {
                    g(paramem, m, i1, n);
                }
            }

            etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Object[]{new Block(this.bh, paramInt1, paramInt2, paramInt3), i, j});

            if ((i == 0) || (j == 0)) {
                paramem.g(paramInt1, paramInt2, paramInt3, this.bh);
                paramem.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
                paramem.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
                paramem.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
                paramem.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);

                paramem.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
                paramem.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
            }
        }
    }

    private void h(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        if (paramem.a(paramInt1, paramInt2, paramInt3) != this.bh) {
            return;
        }

        paramem.g(paramInt1, paramInt2, paramInt3, this.bh);
        paramem.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
        paramem.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
        paramem.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
        paramem.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);

        paramem.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
        paramem.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
    }

    public void e(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        super.e(paramem, paramInt1, paramInt2, paramInt3);
        if (paramem.z) {
            return;
        }

        g(paramem, paramInt1, paramInt2, paramInt3);
        paramem.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
        paramem.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);

        h(paramem, paramInt1 - 1, paramInt2, paramInt3);
        h(paramem, paramInt1 + 1, paramInt2, paramInt3);
        h(paramem, paramInt1, paramInt2, paramInt3 - 1);
        h(paramem, paramInt1, paramInt2, paramInt3 + 1);

        if (paramem.d(paramInt1 - 1, paramInt2, paramInt3)) {
            h(paramem, paramInt1 - 1, paramInt2 + 1, paramInt3);
        } else {
            h(paramem, paramInt1 - 1, paramInt2 - 1, paramInt3);
        }
        if (paramem.d(paramInt1 + 1, paramInt2, paramInt3)) {
            h(paramem, paramInt1 + 1, paramInt2 + 1, paramInt3);
        } else {
            h(paramem, paramInt1 + 1, paramInt2 - 1, paramInt3);
        }
        if (paramem.d(paramInt1, paramInt2, paramInt3 - 1)) {
            h(paramem, paramInt1, paramInt2 + 1, paramInt3 - 1);
        } else {
            h(paramem, paramInt1, paramInt2 - 1, paramInt3 - 1);
        }
        if (paramem.d(paramInt1, paramInt2, paramInt3 + 1)) {
            h(paramem, paramInt1, paramInt2 + 1, paramInt3 + 1);
        } else {
            h(paramem, paramInt1, paramInt2 - 1, paramInt3 + 1);
        }
    }

    public void b(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        super.b(paramem, paramInt1, paramInt2, paramInt3);
        if (paramem.z) {
            return;
        }

        paramem.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
        paramem.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
        g(paramem, paramInt1, paramInt2, paramInt3);

        h(paramem, paramInt1 - 1, paramInt2, paramInt3);
        h(paramem, paramInt1 + 1, paramInt2, paramInt3);
        h(paramem, paramInt1, paramInt2, paramInt3 - 1);
        h(paramem, paramInt1, paramInt2, paramInt3 + 1);

        if (paramem.d(paramInt1 - 1, paramInt2, paramInt3)) {
            h(paramem, paramInt1 - 1, paramInt2 + 1, paramInt3);
        } else {
            h(paramem, paramInt1 - 1, paramInt2 - 1, paramInt3);
        }
        if (paramem.d(paramInt1 + 1, paramInt2, paramInt3)) {
            h(paramem, paramInt1 + 1, paramInt2 + 1, paramInt3);
        } else {
            h(paramem, paramInt1 + 1, paramInt2 - 1, paramInt3);
        }
        if (paramem.d(paramInt1, paramInt2, paramInt3 - 1)) {
            h(paramem, paramInt1, paramInt2 + 1, paramInt3 - 1);
        } else {
            h(paramem, paramInt1, paramInt2 - 1, paramInt3 - 1);
        }
        if (paramem.d(paramInt1, paramInt2, paramInt3 + 1)) {
            h(paramem, paramInt1, paramInt2 + 1, paramInt3 + 1);
        } else {
            h(paramem, paramInt1, paramInt2 - 1, paramInt3 + 1);
        }
    }

    private int f(em paramem, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramem.a(paramInt1, paramInt2, paramInt3) != this.bh) {
            return paramInt4;
        }
        int i = paramem.b(paramInt1, paramInt2, paramInt3);
        if (i > paramInt4) {
            return i;
        }
        return paramInt4;
    }

    public void b(em paramem, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramem.z) {
            return;
        }
        int i = paramem.b(paramInt1, paramInt2, paramInt3);

        boolean bool = a(paramem, paramInt1, paramInt2, paramInt3);

        if (!bool) {
            a_(paramem, paramInt1, paramInt2, paramInt3, i);
            paramem.d(paramInt1, paramInt2, paramInt3, 0);
        } else {
            g(paramem, paramInt1, paramInt2, paramInt3);
        }

        super.b(paramem, paramInt1, paramInt2, paramInt3, paramInt4);
    }

    public int a(int paramInt, Random paramRandom) {
        return fs.aA.aW;
    }

    public boolean d(em paramem, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (!this.a) {
            return false;
        }
        return b((iq)paramem, paramInt1, paramInt2, paramInt3, paramInt4);
    }

    public boolean b(iq paramiq, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (!this.a) {
            return false;
        }
        if (paramiq.b(paramInt1, paramInt2, paramInt3) == 0) {
            return false;
        }

        if (paramInt4 == 1) {
            return true;
        }

        int i = (b(paramiq, paramInt1 - 1, paramInt2, paramInt3)) || ((!paramiq.d(paramInt1 - 1, paramInt2, paramInt3)) && (b(paramiq, paramInt1 - 1, paramInt2 - 1, paramInt3))) ? 1 : 0;
        int j = (b(paramiq, paramInt1 + 1, paramInt2, paramInt3)) || ((!paramiq.d(paramInt1 + 1, paramInt2, paramInt3)) && (b(paramiq, paramInt1 + 1, paramInt2 - 1, paramInt3))) ? 1 : 0;
        int k = (b(paramiq, paramInt1, paramInt2, paramInt3 - 1)) || ((!paramiq.d(paramInt1, paramInt2, paramInt3 - 1)) && (b(paramiq, paramInt1, paramInt2 - 1, paramInt3 - 1))) ? 1 : 0;
        int m = (b(paramiq, paramInt1, paramInt2, paramInt3 + 1)) || ((!paramiq.d(paramInt1, paramInt2, paramInt3 + 1)) && (b(paramiq, paramInt1, paramInt2 - 1, paramInt3 + 1))) ? 1 : 0;
        if (!paramiq.d(paramInt1, paramInt2 + 1, paramInt3)) {
            if ((paramiq.d(paramInt1 - 1, paramInt2, paramInt3)) && (b(paramiq, paramInt1 - 1, paramInt2 + 1, paramInt3))) {
                i = 1;
            }
            if ((paramiq.d(paramInt1 + 1, paramInt2, paramInt3)) && (b(paramiq, paramInt1 + 1, paramInt2 + 1, paramInt3))) {
                j = 1;
            }
            if ((paramiq.d(paramInt1, paramInt2, paramInt3 - 1)) && (b(paramiq, paramInt1, paramInt2 + 1, paramInt3 - 1))) {
                k = 1;
            }
            if ((paramiq.d(paramInt1, paramInt2, paramInt3 + 1)) && (b(paramiq, paramInt1, paramInt2 + 1, paramInt3 + 1))) {
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

    public static boolean b(iq paramiq, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramiq.a(paramInt1, paramInt2, paramInt3);
        if (i == fy.av.bh) {
            return true;
        }
        if (i == 0) {
            return false;
        }
        return fy.m[i].c();
    }
}
