
import java.util.Random;

public class jr extends ga {

    private int[] a = new int[256];
    private int[] b = new int[256];

    protected jr(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, jw.l);

        a(ga.x.bh, 5, 20);
        a(ga.J.bh, 5, 5);
        a(ga.K.bh, 30, 60);
        a(ga.an.bh, 30, 20);
        a(ga.am.bh, 15, 100);
        a(ga.ab.bh, 30, 60);

        a(true);
    }

    private void a(int paramInt1, int paramInt2, int paramInt3) {
        this.a[paramInt1] = paramInt2;
        this.b[paramInt1] = paramInt3;
    }

    @Override
    public du d(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
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

    public void a(eo parameo, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        int i = parameo.a(paramInt1, paramInt2 - 1, paramInt3) == ga.bb.bh ? 1 : 0;
        int j = parameo.b(paramInt1, paramInt2, paramInt3);
        if (j < 15) {
            parameo.b(paramInt1, paramInt2, paramInt3, j + 1);
            parameo.h(paramInt1, paramInt2, paramInt3, this.bh);
        }
        if ((i == 0) && (!g(parameo, paramInt1, paramInt2, paramInt3))) {
            if ((!parameo.d(paramInt1, paramInt2 - 1, paramInt3)) || (j > 3)) {
                parameo.d(paramInt1, paramInt2, paramInt3, 0);
            }
            return;
        }

        if ((i == 0) && (!b((it) parameo, paramInt1, paramInt2 - 1, paramInt3)) && (j == 15) && (paramRandom.nextInt(4) == 0)) {
            parameo.d(paramInt1, paramInt2, paramInt3, 0);
            return;
        }

        if ((j % 2 == 0) && (j > 2)) {
            a(parameo, paramInt1 + 1, paramInt2, paramInt3, 300, paramRandom);
            a(parameo, paramInt1 - 1, paramInt2, paramInt3, 300, paramRandom);
            a(parameo, paramInt1, paramInt2 - 1, paramInt3, 250, paramRandom);
            a(parameo, paramInt1, paramInt2 + 1, paramInt3, 250, paramRandom);
            a(parameo, paramInt1, paramInt2, paramInt3 - 1, 300, paramRandom);
            a(parameo, paramInt1, paramInt2, paramInt3 + 1, 300, paramRandom);

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

                        int i2 = h(parameo, k, n, m);
                        if ((i2 > 0) && (paramRandom.nextInt(i1) <= i2)) {
                            // hMod: dynamic spreading of fire.
                            // avg call amount per placed block of fire ~ 4
                            Block block = new Block(parameo.a(k, n, m), k, n, m);
                            block.setStatus(3);
                            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, new Object[]{block, null})) {
                                parameo.d(k, n, m, this.bh);
                            }
                        }
                    }
                }
            }
        }
    }

    private void a(eo parameo, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Random paramRandom) {
        int i = this.b[parameo.a(paramInt1, paramInt2, paramInt3)];
        if (paramRandom.nextInt(paramInt4) < i) {
            int j = parameo.a(paramInt1, paramInt2, paramInt3) == ga.am.bh ? 1 : 0;
            if (paramRandom.nextInt(2) == 0) {
                // hMod: VERY SLOW dynamic spreading of fire.
                Block block = new Block(parameo.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
                block.setStatus(3);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, new Object[]{block, null})) {
                    parameo.d(paramInt1, paramInt2, paramInt3, this.bh);
                }
            } else {
                parameo.d(paramInt1, paramInt2, paramInt3, 0);
            }
            if (j != 0) {
                ga.am.a(parameo, paramInt1, paramInt2, paramInt3, 0);
            }
        }
    }

    private boolean g(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        if (b((it) parameo, paramInt1 + 1, paramInt2, paramInt3)) {
            return true;
        }
        if (b((it) parameo, paramInt1 - 1, paramInt2, paramInt3)) {
            return true;
        }
        if (b((it) parameo, paramInt1, paramInt2 - 1, paramInt3)) {
            return true;
        }
        if (b((it) parameo, paramInt1, paramInt2 + 1, paramInt3)) {
            return true;
        }
        if (b((it) parameo, paramInt1, paramInt2, paramInt3 - 1)) {
            return true;
        }
        return b((it) parameo, paramInt1, paramInt2, paramInt3 + 1);
    }

    private int h(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        int i = 0;
        if (parameo.a(paramInt1, paramInt2, paramInt3) != 0) {
            return 0;
        }

        i = f(parameo, paramInt1 + 1, paramInt2, paramInt3, i);
        i = f(parameo, paramInt1 - 1, paramInt2, paramInt3, i);
        i = f(parameo, paramInt1, paramInt2 - 1, paramInt3, i);
        i = f(parameo, paramInt1, paramInt2 + 1, paramInt3, i);
        i = f(parameo, paramInt1, paramInt2, paramInt3 - 1, i);
        i = f(parameo, paramInt1, paramInt2, paramInt3 + 1, i);

        return i;
    }

    @Override
    public boolean d() {
        return false;
    }

    public boolean b(it paramit, int paramInt1, int paramInt2, int paramInt3) {
        return this.a[paramit.a(paramInt1, paramInt2, paramInt3)] > 0;
    }

    public int f(eo parameo, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = this.a[parameo.a(paramInt1, paramInt2, paramInt3)];
        if (i > paramInt4) {
            return i;
        }
        return paramInt4;
    }

    @Override
    public boolean a(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        return (parameo.d(paramInt1, paramInt2 - 1, paramInt3)) || (g(parameo, paramInt1, paramInt2, paramInt3));
    }

    @Override
    public void b(eo parameo, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if ((!parameo.d(paramInt1, paramInt2 - 1, paramInt3)) && (!g(parameo, paramInt1, paramInt2, paramInt3))) {
            parameo.d(paramInt1, paramInt2, paramInt3, 0);
            return;
        }
    }

    @Override
    public void e(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        if ((parameo.a(paramInt1, paramInt2 - 1, paramInt3) == ga.ap.bh) && (ga.be.a_(parameo, paramInt1, paramInt2, paramInt3))) {
            return;
        }

        if ((!parameo.d(paramInt1, paramInt2 - 1, paramInt3)) && (!g(parameo, paramInt1, paramInt2, paramInt3))) {
            parameo.d(paramInt1, paramInt2, paramInt3, 0);
            return;
        }
        parameo.h(paramInt1, paramInt2, paramInt3, this.bh);
    }
}
