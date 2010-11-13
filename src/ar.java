
import java.util.Random;

public class ar extends cy {

    int a = 0;
    boolean[] b = new boolean[4];
    int[] c = new int[4];

    protected ar(int paramInt, jt paramjt) {
        super(paramInt, paramjt);
    }

    private void i(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramem.b(paramInt1, paramInt2, paramInt3);
        paramem.a(paramInt1, paramInt2, paramInt3, this.bh + 1, i);
        paramem.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);
        paramem.f(paramInt1, paramInt2, paramInt3);
    }

    public void a(em paramem, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        //lava flow prevention
        Block block = new Block(paramem.a(paramInt1, paramInt2 - 1, paramInt3), paramInt1, paramInt2 - 1, paramInt3);
        //change the status of the block to the type that is trying to flow ( 10 / 11 for lava).
        block.setStatus(this.bh);
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, new Object[]{block})) {
            return;
        }

        int i = g(paramem, paramInt1, paramInt2, paramInt3);

        int j = 1;
        if ((this.bs == jt.g) && (!paramem.q.d)) {
            j = 2;
        }

        int k = 1;

        if (i > 0) {
            int l = -100;
            this.a = 0;
            l = e(paramem, paramInt1 - 1, paramInt2, paramInt3, l);
            l = e(paramem, paramInt1 + 1, paramInt2, paramInt3, l);
            l = e(paramem, paramInt1, paramInt2, paramInt3 - 1, l);
            l = e(paramem, paramInt1, paramInt2, paramInt3 + 1, l);

            int i1 = l + j;
            if ((i1 >= 8) || (l < 0)) {
                i1 = -1;
            }
            if (g(paramem, paramInt1, paramInt2 + 1, paramInt3) >= 0) {
                int i2 = g(paramem, paramInt1, paramInt2 + 1, paramInt3);
                if (i2 >= 8) {
                    i1 = i2;
                } else {
                    i1 = i2 + 8;
                }
            }
            if ((this.a >= 2) && (this.bs == jt.f)) {
                if (paramem.d(paramInt1, paramInt2 - 1, paramInt3)) {
                    i1 = 0;
                } else if ((paramem.c(paramInt1, paramInt2 - 1, paramInt3) == this.bs) && (paramem.b(paramInt1, paramInt2, paramInt3) == 0)) {
                    i1 = 0;
                }
            }
            if ((this.bs == jt.g) && (i < 8) && (i1 < 8) && (i1 > i) && (paramRandom.nextInt(4) != 0)) {
                i1 = i;
                k = 0;
            }

            if (i1 != i) {
                i = i1;
                if (i < 0) {
                    paramem.d(paramInt1, paramInt2, paramInt3, 0);
                } else {
                    paramem.b(paramInt1, paramInt2, paramInt3, i);
                    paramem.h(paramInt1, paramInt2, paramInt3, this.bh);
                    paramem.g(paramInt1, paramInt2, paramInt3, this.bh);
                }
            } else if (k != 0) {
                i(paramem, paramInt1, paramInt2, paramInt3);
            }
        } else {
            i(paramem, paramInt1, paramInt2, paramInt3);
        }
        if (l(paramem, paramInt1, paramInt2 - 1, paramInt3)) {
            if (i >= 8) {
                paramem.b(paramInt1, paramInt2 - 1, paramInt3, this.bh, i);
            } else {
                paramem.b(paramInt1, paramInt2 - 1, paramInt3, this.bh, i + 8);
            }
        } else if ((i >= 0) && ((i == 0) || (k(paramem, paramInt1, paramInt2 - 1, paramInt3)))) {
            boolean[] arrayOfBoolean = j(paramem, paramInt1, paramInt2, paramInt3);
            int i1 = i + j;
            if (i >= 8) {
                i1 = 1;
            }
            if (i1 >= 8) {
                return;
            }
            if (arrayOfBoolean[0] != false) {
                f(paramem, paramInt1 - 1, paramInt2, paramInt3, i1);
            }
            if (arrayOfBoolean[1] != false) {
                f(paramem, paramInt1 + 1, paramInt2, paramInt3, i1);
            }
            if (arrayOfBoolean[2] != false) {
                f(paramem, paramInt1, paramInt2, paramInt3 - 1, i1);
            }
            if (arrayOfBoolean[3] != false) {
                f(paramem, paramInt1, paramInt2, paramInt3 + 1, i1);
            }
        }
    }

    private void f(em paramem, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (l(paramem, paramInt1, paramInt2, paramInt3)) {
            int i = paramem.a(paramInt1, paramInt2, paramInt3);
            if (i > 0) {
                if (this.bs == jt.g) {
                    h(paramem, paramInt1, paramInt2, paramInt3);
                } else {
                    fy.m[i].a_(paramem, paramInt1, paramInt2, paramInt3, paramem.b(paramInt1, paramInt2, paramInt3));
                }
            }
            paramem.b(paramInt1, paramInt2, paramInt3, this.bh, paramInt4);
        }
    }

    private int a(em paramem, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
        int i = 1000;
        for (int j = 0; j < 4; j++) {
            if (((j == 0) && (paramInt5 == 1))
                    || ((j == 1) && (paramInt5 == 0))
                    || ((j == 2) && (paramInt5 == 3)) || ((j == 3) && (paramInt5 == 2))) {
                continue;
            }
            int k = paramInt1;
            int l = paramInt2;
            int i1 = paramInt3;

            if (j == 0) {
                k--;
            }
            if (j == 1) {
                k++;
            }
            if (j == 2) {
                i1--;
            }
            if (j == 3) {
                i1++;
            }

            if (k(paramem, k, l, i1)) {
                continue;
            }
            if ((paramem.c(k, l, i1) == this.bs) && (paramem.b(k, l, i1) == 0)) {
                continue;
            }
            if (!k(paramem, k, l - 1, i1)) {
                return paramInt4;
            }
            if (paramInt4 < 4) {
                int i2 = a(paramem, k, l, i1, paramInt4 + 1, j);
                if (i2 >= i) {
                    continue;
                }
                i = i2;
            }

        }

        return i;
    }

    private boolean[] j(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        for (int i = 0; i < 4; i++) {
            this.c[i] = 1000;
            int j = paramInt1;
            int k = paramInt2;
            int l = paramInt3;

            if (i == 0) {
                j--;
            }
            if (i == 1) {
                j++;
            }
            if (i == 2) {
                l--;
            }
            if (i == 3) {
                l++;
            }
            if (k(paramem, j, k, l)) {
                continue;
            }
            if ((paramem.c(j, k, l) == this.bs) && (paramem.b(j, k, l) == 0)) {
                continue;
            }
            if (!k(paramem, j, k - 1, l)) {
                this.c[i] = 0;
            } else {
                this.c[i] = a(paramem, j, k, l, 1, i);
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

    private boolean k(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramem.a(paramInt1, paramInt2, paramInt3);
        if ((i == fy.aE.bh) || (i == fy.aL.bh) || (i == fy.aD.bh) || (i == fy.aF.bh) || (i == fy.aX.bh)) {
            return true;
        }
        if (i == 0) {
            return false;
        }
        jt localjt = fy.m[i].bs;
        return localjt.a();
    }

    protected int e(em paramem, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = g(paramem, paramInt1, paramInt2, paramInt3);
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

    private boolean l(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        jt localjt = paramem.c(paramInt1, paramInt2, paramInt3);
        if (localjt == this.bs) {
            return false;
        }
        if (localjt == jt.g) {
            return false;
        }
        return !k(paramem, paramInt1, paramInt2, paramInt3);
    }

    public void e(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        super.e(paramem, paramInt1, paramInt2, paramInt3);
        if (paramem.a(paramInt1, paramInt2, paramInt3) == this.bh) {
            paramem.h(paramInt1, paramInt2, paramInt3, this.bh);
        }
    }
}
