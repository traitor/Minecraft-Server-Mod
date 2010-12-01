import java.util.Random;

public class js extends gb {

    private int[] a = new int[256];
    private int[] b = new int[256];

    protected js(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, jx.l);

        a(gb.x.bh, 5, 20);
        a(gb.J.bh, 5, 5);
        a(gb.K.bh, 30, 60);
        a(gb.an.bh, 30, 20);
        a(gb.am.bh, 15, 100);
        a(gb.ab.bh, 30, 60);

        a(true);
    }

    private void a(int paramInt1, int paramInt2, int paramInt3) {
        a[paramInt1] = paramInt2;
        b[paramInt1] = paramInt3;
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
    public int a(Random paramRandom) {
        return 0;
    }

    @Override
    public int b() {
        return 10;
    }

    @Override
    public void a(ep paramep, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        int i = paramep.a(paramInt1, paramInt2 - 1, paramInt3) == gb.bb.bh ? 1 : 0;

        int j = paramep.b(paramInt1, paramInt2, paramInt3);
        if (j < 15) {
            paramep.b(paramInt1, paramInt2, paramInt3, j + 1);
            paramep.h(paramInt1, paramInt2, paramInt3, bh);
        }
        if ((i == 0) && (!g(paramep, paramInt1, paramInt2, paramInt3))) {
            if ((!paramep.d(paramInt1, paramInt2 - 1, paramInt3)) || (j > 3)) {
                paramep.d(paramInt1, paramInt2, paramInt3, 0);
            }
            return;
        }
        // hMod: cast ep to iu to fix decompiler error.
        if ((i == 0) && (!b((iu) paramep, paramInt1, paramInt2 - 1, paramInt3)) && (j == 15) && (paramRandom.nextInt(4) == 0)) {
            paramep.d(paramInt1, paramInt2, paramInt3, 0);
            return;
        }

        if ((j % 2 == 0) && (j > 2)) {
            a(paramep, paramInt1 + 1, paramInt2, paramInt3, 300, paramRandom);
            a(paramep, paramInt1 - 1, paramInt2, paramInt3, 300, paramRandom);
            a(paramep, paramInt1, paramInt2 - 1, paramInt3, 250, paramRandom);
            a(paramep, paramInt1, paramInt2 + 1, paramInt3, 250, paramRandom);
            a(paramep, paramInt1, paramInt2, paramInt3 - 1, 300, paramRandom);
            a(paramep, paramInt1, paramInt2, paramInt3 + 1, 300, paramRandom);

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

                        int i2 = h(paramep, k, n, m);
                        if ((i2 > 0) && (paramRandom.nextInt(i1) <= i2)) {
                            // hMod: dynamic spreading of fire.
                            // avg call amount per placed block of fire ~ 4
                            Block block = new Block(paramep.a(k, n, m), k, n, m);
                            block.setStatus(3);
                            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, new Object[]{block, null})) {
                                paramep.d(k, n, m, bh);
                            }
                        }
                    }
                }
            }
        }
    }

    private void a(ep paramep, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Random paramRandom) {
        int i = b[paramep.a(paramInt1, paramInt2, paramInt3)];
        if (paramRandom.nextInt(paramInt4) < i) {
            int j = paramep.a(paramInt1, paramInt2, paramInt3) == gb.am.bh ? 1 : 0;
            if (paramRandom.nextInt(2) == 0) {
                // hMod: VERY SLOW dynamic spreading of fire.
                Block block = new Block(paramep.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
                block.setStatus(3);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, new Object[]{block, null})) {
                    paramep.d(paramInt1, paramInt2, paramInt3, bh);
                }
            } else {
                paramep.d(paramInt1, paramInt2, paramInt3, 0);
            }
            if (j != 0) {
                gb.am.a(paramep, paramInt1, paramInt2, paramInt3, 0);
            }
        }
    }

    private boolean g(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        // hMod: cast ep to iu to fix decompiler error.(6 times)
        if (b((iu) paramep, paramInt1 + 1, paramInt2, paramInt3)) {
            return true;
        }
        if (b((iu) paramep, paramInt1 - 1, paramInt2, paramInt3)) {
            return true;
        }
        if (b((iu) paramep, paramInt1, paramInt2 - 1, paramInt3)) {
            return true;
        }
        if (b((iu) paramep, paramInt1, paramInt2 + 1, paramInt3)) {
            return true;
        }
        if (b((iu) paramep, paramInt1, paramInt2, paramInt3 - 1)) {
            return true;
        }
        return b((iu) paramep, paramInt1, paramInt2, paramInt3 + 1);
    }

    private int h(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        int i = 0;
        if (paramep.a(paramInt1, paramInt2, paramInt3) != 0) {
            return 0;
        }

        i = f(paramep, paramInt1 + 1, paramInt2, paramInt3, i);
        i = f(paramep, paramInt1 - 1, paramInt2, paramInt3, i);
        i = f(paramep, paramInt1, paramInt2 - 1, paramInt3, i);
        i = f(paramep, paramInt1, paramInt2 + 1, paramInt3, i);
        i = f(paramep, paramInt1, paramInt2, paramInt3 - 1, i);
        i = f(paramep, paramInt1, paramInt2, paramInt3 + 1, i);

        return i;
    }

    @Override
    public boolean d() {
        return false;
    }

    public boolean b(iu paramiu, int paramInt1, int paramInt2, int paramInt3) {
        return a[paramiu.a(paramInt1, paramInt2, paramInt3)] > 0;
    }

    public int f(ep paramep, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = a[paramep.a(paramInt1, paramInt2, paramInt3)];
        if (i > paramInt4) {
            return i;
        }
        return paramInt4;
    }

    @Override
    public boolean a(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        return (paramep.d(paramInt1, paramInt2 - 1, paramInt3)) || (g(paramep, paramInt1, paramInt2, paramInt3));
    }

    @Override
    public void b(ep paramep, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if ((!paramep.d(paramInt1, paramInt2 - 1, paramInt3)) && (!g(paramep, paramInt1, paramInt2, paramInt3))) {
            paramep.d(paramInt1, paramInt2, paramInt3, 0);
            return;
        }
    }

    @Override
    public void e(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        if ((paramep.a(paramInt1, paramInt2 - 1, paramInt3) == gb.ap.bh) && (gb.be.a_(paramep, paramInt1, paramInt2, paramInt3))) {
            return;
        }

        if ((!paramep.d(paramInt1, paramInt2 - 1, paramInt3)) && (!g(paramep, paramInt1, paramInt2, paramInt3))) {
            paramep.d(paramInt1, paramInt2, paramInt3, 0);
            return;
        }
        paramep.h(paramInt1, paramInt2, paramInt3, bh);
    }
}
