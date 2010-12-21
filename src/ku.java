
import java.util.Random;

public class ku extends gu {

    private int[] a = new int[256];
    private int[] b = new int[256];

    protected ku(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, kz.l);

        a(gu.x.bh, 5, 20);
        a(gu.J.bh, 5, 5);
        a(gu.K.bh, 30, 60);
        a(gu.an.bh, 30, 20);
        a(gu.am.bh, 15, 100);
        a(gu.ab.bh, 30, 60);

        a(true);
    }

    private void a(int paramInt1, int paramInt2, int paramInt3) {
        this.a[paramInt1] = paramInt2;
        this.b[paramInt1] = paramInt3;
    }

    public el d(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    public boolean a() {
        return false;
    }

    public int a(Random paramRandom) {
        return 0;
    }

    public int b() {
        return 10;
    }

    public void a(ff paramff, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        int i = paramff.a(paramInt1, paramInt2 - 1, paramInt3) == gu.bb.bh ? 1 : 0;

        int j = paramff.b(paramInt1, paramInt2, paramInt3);
        if (j < 15) {
            paramff.b(paramInt1, paramInt2, paramInt3, j + 1);
            paramff.h(paramInt1, paramInt2, paramInt3, this.bh);
        }
        if ((i == 0) && (!g(paramff, paramInt1, paramInt2, paramInt3))) {
            if ((!paramff.d(paramInt1, paramInt2 - 1, paramInt3)) || (j > 3)) {
                paramff.d(paramInt1, paramInt2, paramInt3, 0);
            }
            return;
        }

        // hMod: cast to fix decompiler error.
        if ((i == 0) && (!b((jw)paramff, paramInt1, paramInt2 - 1, paramInt3))
                && (j == 15) && (paramRandom.nextInt(4) == 0)) {
            paramff.d(paramInt1, paramInt2, paramInt3, 0);
            return;
        }

        if ((j % 2 == 0) && (j > 2)) {
            a(paramff, paramInt1 + 1, paramInt2, paramInt3, 300, paramRandom);
            a(paramff, paramInt1 - 1, paramInt2, paramInt3, 300, paramRandom);
            a(paramff, paramInt1, paramInt2 - 1, paramInt3, 250, paramRandom);
            a(paramff, paramInt1, paramInt2 + 1, paramInt3, 250, paramRandom);
            a(paramff, paramInt1, paramInt2, paramInt3 - 1, 300, paramRandom);
            a(paramff, paramInt1, paramInt2, paramInt3 + 1, 300, paramRandom);

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

                        int i2 = h(paramff, k, n, m);
                        if ((i2 > 0) && (paramRandom.nextInt(i1) <= i2)) {
                            // hMod: dynamic spreading of fire.
                            // avg call amount per placed block of fire ~ 4
                            Block block = new Block(paramff.a(k, n, m), k, n, m);
                            block.setStatus(3);
                            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, block, null)) {
                                paramff.d(k, n, m, this.bh);
                            }
                        }
                    }
                }
            }
        }
    }

    private void a(ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Random paramRandom) {
        int i = this.b[paramff.a(paramInt1, paramInt2, paramInt3)];
        if (paramRandom.nextInt(paramInt4) < i) {
            int j = paramff.a(paramInt1, paramInt2, paramInt3) == gu.am.bh ? 1 : 0;
            if (paramRandom.nextInt(2) == 0) {
                // hMod: VERY SLOW dynamic spreading of fire.
                Block block = new Block(paramff.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
                block.setStatus(3);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, block, null)) {
                    paramff.d(paramInt1, paramInt2, paramInt3, this.bh);
                }
            } else {
                paramff.d(paramInt1, paramInt2, paramInt3, 0);
            }
            if (j != 0) {
                gu.am.a(paramff, paramInt1, paramInt2, paramInt3, 0);
            }
        }
    }

    private boolean g(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        // hMod: cast down to fix decompiler error.(6 times)
        if (b((jw)paramff, paramInt1 + 1, paramInt2, paramInt3)) {
            return true;
        }
        if (b((jw)paramff, paramInt1 - 1, paramInt2, paramInt3)) {
            return true;
        }
        if (b((jw)paramff, paramInt1, paramInt2 - 1, paramInt3)) {
            return true;
        }
        if (b((jw)paramff, paramInt1, paramInt2 + 1, paramInt3)) {
            return true;
        }
        if (b((jw)paramff, paramInt1, paramInt2, paramInt3 - 1)) {
            return true;
        }
        return b((jw)paramff, paramInt1, paramInt2, paramInt3 + 1);
    }

    private int h(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        int i = 0;
        if (!paramff.e(paramInt1, paramInt2, paramInt3)) {
            return 0;
        }

        i = f(paramff, paramInt1 + 1, paramInt2, paramInt3, i);
        i = f(paramff, paramInt1 - 1, paramInt2, paramInt3, i);
        i = f(paramff, paramInt1, paramInt2 - 1, paramInt3, i);
        i = f(paramff, paramInt1, paramInt2 + 1, paramInt3, i);
        i = f(paramff, paramInt1, paramInt2, paramInt3 - 1, i);
        i = f(paramff, paramInt1, paramInt2, paramInt3 + 1, i);

        return i;
    }

    public boolean d() {
        return false;
    }

    public boolean b(jw paramjw, int paramInt1, int paramInt2, int paramInt3) {
        return this.a[paramjw.a(paramInt1, paramInt2, paramInt3)] > 0;
    }

    public int f(ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = this.a[paramff.a(paramInt1, paramInt2, paramInt3)];
        if (i > paramInt4) {
            return i;
        }
        return paramInt4;
    }

    public boolean a(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        return (paramff.d(paramInt1, paramInt2 - 1, paramInt3)) || (g(paramff, paramInt1, paramInt2, paramInt3));
    }

    public void b(ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if ((!paramff.d(paramInt1, paramInt2 - 1, paramInt3)) && (!g(paramff, paramInt1, paramInt2, paramInt3))) {
            paramff.d(paramInt1, paramInt2, paramInt3, 0);
            return;
        }
    }

    public void e(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        if ((paramff.a(paramInt1, paramInt2 - 1, paramInt3) == gu.ap.bh)
                && (gu.be.a_(paramff, paramInt1, paramInt2, paramInt3))) {
            return;
        }

        if ((!paramff.d(paramInt1, paramInt2 - 1, paramInt3)) && (!g(paramff, paramInt1, paramInt2, paramInt3))) {
            paramff.d(paramInt1, paramInt2, paramInt3, 0);
            return;
        }
        paramff.h(paramInt1, paramInt2, paramInt3, this.bh);
    }
}
