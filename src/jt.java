import java.util.Random;

public class jt extends gc {
    private int[] a = new int[256];
    private int[] b = new int[256];

    protected jt(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, jy.l);

        a(gc.x.bh, 5, 20);
        a(gc.J.bh, 5, 5);
        a(gc.K.bh, 30, 60);
        a(gc.an.bh, 30, 20);
        a(gc.am.bh, 15, 100);
        a(gc.ab.bh, 30, 60);

        a(true);
    }

    private void a(int paramInt1, int paramInt2, int paramInt3) {
        this.a[paramInt1] = paramInt2;
        this.b[paramInt1] = paramInt3;
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
    public int a(Random paramRandom) {
        return 0;
    }

    @Override
    public int b() {
        return 10;
    }

    @Override
    public void a(eq parameq, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        int i = parameq.a(paramInt1, paramInt2 - 1, paramInt3) == gc.bb.bh ? 1 : 0;

        int j = parameq.b(paramInt1, paramInt2, paramInt3);
        if (j < 15) {
            parameq.b(paramInt1, paramInt2, paramInt3, j + 1);
            parameq.h(paramInt1, paramInt2, paramInt3, this.bh);
        }
        if ((i == 0) && (!g(parameq, paramInt1, paramInt2, paramInt3))) {
            if ((!parameq.d(paramInt1, paramInt2 - 1, paramInt3)) || (j > 3)) {
                parameq.d(paramInt1, paramInt2, paramInt3, 0);
            }
            return;
        }

        // hMod: cast ep tofix decompiler error.
        if ((i == 0) && (!b((iv)parameq, paramInt1, paramInt2 - 1, paramInt3)) && (j == 15) && (paramRandom.nextInt(4) == 0)) {
            parameq.d(paramInt1, paramInt2, paramInt3, 0);
            return;
        }

        if ((j % 2 == 0) && (j > 2)) {
            a(parameq, paramInt1 + 1, paramInt2, paramInt3, 300, paramRandom);
            a(parameq, paramInt1 - 1, paramInt2, paramInt3, 300, paramRandom);
            a(parameq, paramInt1, paramInt2 - 1, paramInt3, 250, paramRandom);
            a(parameq, paramInt1, paramInt2 + 1, paramInt3, 250, paramRandom);
            a(parameq, paramInt1, paramInt2, paramInt3 - 1, 300, paramRandom);
            a(parameq, paramInt1, paramInt2, paramInt3 + 1, 300, paramRandom);

            for (int k = paramInt1 - 1; k <= paramInt1 + 1; k++) {
                for (int m = paramInt3 - 1; m <= paramInt3 + 1; m++) {
                    for (int n = paramInt2 - 1; n <= paramInt2 + 4; n++) {
                        if ((k == paramInt1) && (n == paramInt2) && (m == paramInt3)) {
                            continue;
                        }
                        int i1 = 100;
                        if (n > paramInt2 + 1) {
                            i1 += (n - (paramInt2 + 1)) * 100;
                        }

                        int i2 = h(parameq, k, n, m);
                        if ((i2 > 0) && (paramRandom.nextInt(i1) <= i2)) {
                            // hMod: dynamic spreading of fire.
                            // avg call amount per placed block of fire ~ 4
                            Block block = new Block(parameq.a(k, n, m), k, n, m);
                            block.setStatus(3);
                            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, block, null)) {
                                parameq.d(k, n, m, this.bh);
                            }
                        }
                    }
                }
            }
        }
    }

    private void a(eq parameq, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Random paramRandom) {
        int i = this.b[parameq.a(paramInt1, paramInt2, paramInt3)];
        if (paramRandom.nextInt(paramInt4) < i) {
            int j = parameq.a(paramInt1, paramInt2, paramInt3) == gc.am.bh ? 1 : 0;
            if (paramRandom.nextInt(2) == 0) {
                // hMod: VERY SLOW dynamic spreading of fire.
                Block block = new Block(parameq.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
                block.setStatus(3);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, block, null)) {
                    parameq.d(paramInt1, paramInt2, paramInt3, this.bh);
                }
            } else {
                parameq.d(paramInt1, paramInt2, paramInt3, 0);
            }
            if (j != 0) {
                gc.am.a(parameq, paramInt1, paramInt2, paramInt3, 0);
            }
        }
    }

    private boolean g(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        // hMod: cast down to fix decompiler error.(6 times)
        if (b((iv) parameq, paramInt1 + 1, paramInt2, paramInt3)) {
            return true;
        }
        if (b((iv) parameq, paramInt1 - 1, paramInt2, paramInt3)) {
            return true;
        }
        if (b((iv) parameq, paramInt1, paramInt2 - 1, paramInt3)) {
            return true;
        }
        if (b((iv) parameq, paramInt1, paramInt2 + 1, paramInt3)) {
            return true;
        }
        if (b((iv) parameq, paramInt1, paramInt2, paramInt3 - 1)) {
            return true;
        }
        return b((iv) parameq, paramInt1, paramInt2, paramInt3 + 1);
    }

    private int h(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        int i = 0;
        if (parameq.a(paramInt1, paramInt2, paramInt3) != 0) {
            return 0;
        }

        i = f(parameq, paramInt1 + 1, paramInt2, paramInt3, i);
        i = f(parameq, paramInt1 - 1, paramInt2, paramInt3, i);
        i = f(parameq, paramInt1, paramInt2 - 1, paramInt3, i);
        i = f(parameq, paramInt1, paramInt2 + 1, paramInt3, i);
        i = f(parameq, paramInt1, paramInt2, paramInt3 - 1, i);
        i = f(parameq, paramInt1, paramInt2, paramInt3 + 1, i);

        return i;
    }

    @Override
    public boolean d() {
        return false;
    }

    public boolean b(iv paramiv, int paramInt1, int paramInt2, int paramInt3) {
        return this.a[paramiv.a(paramInt1, paramInt2, paramInt3)] > 0;
    }

    public int f(eq parameq, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = this.a[parameq.a(paramInt1, paramInt2, paramInt3)];
        if (i > paramInt4) {
            return i;
        }
        return paramInt4;
    }

    @Override
    public boolean a(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        return (parameq.d(paramInt1, paramInt2 - 1, paramInt3)) || (g(parameq, paramInt1, paramInt2, paramInt3));
    }

    @Override
    public void b(eq parameq, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if ((!parameq.d(paramInt1, paramInt2 - 1, paramInt3)) && (!g(parameq, paramInt1, paramInt2, paramInt3))) {
            parameq.d(paramInt1, paramInt2, paramInt3, 0);
            return;
        }
    }

    @Override
    public void e(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        if ((parameq.a(paramInt1, paramInt2 - 1, paramInt3) == gc.ap.bh) && (gc.be.a_(parameq, paramInt1, paramInt2, paramInt3))) {
            return;
        }

        if ((!parameq.d(paramInt1, paramInt2 - 1, paramInt3)) && (!g(parameq, paramInt1, paramInt2, paramInt3))) {
            parameq.d(paramInt1, paramInt2, paramInt3, 0);
            return;
        }
        parameq.h(paramInt1, paramInt2, paramInt3, this.bh);
    }
}
