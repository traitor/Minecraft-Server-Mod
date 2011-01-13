
import java.util.Random;

public class mc extends hr {

    private int[] a = new int[256];
    private int[] b = new int[256];

    protected mc(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, mh.l);

        a(hr.x.bi, 5, 20);
        a(hr.J.bi, 5, 5);
        a(hr.K.bi, 30, 60);
        a(hr.an.bi, 30, 20);
        a(hr.am.bi, 15, 100);
        a(hr.ab.bi, 30, 60);

        a(true);
    }

    private void a(int paramInt1, int paramInt2, int paramInt3) {
        a[paramInt1] = paramInt2;
        b[paramInt1] = paramInt3;
    }

    public fa d(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
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

    public void a(fv paramfv, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        int i = paramfv.a(paramInt1, paramInt2 - 1, paramInt3) == hr.bb.bi ? 1 : 0;

        int j = paramfv.b(paramInt1, paramInt2, paramInt3);
        if (j < 15) {
            paramfv.c(paramInt1, paramInt2, paramInt3, j + 1);
            paramfv.i(paramInt1, paramInt2, paramInt3, bi);
        }
        if ((i == 0) && (!g(paramfv, paramInt1, paramInt2, paramInt3))) {
            if ((!paramfv.d(paramInt1, paramInt2 - 1, paramInt3)) || (j > 3)) {
                paramfv.e(paramInt1, paramInt2, paramInt3, 0);
            }
            return;
        }
        // hMod: cast to fix decompiler error.
        if ((i == 0) && (!b((la) paramfv, paramInt1, paramInt2 - 1, paramInt3))
                && (j == 15) && (paramRandom.nextInt(4) == 0)) {
            paramfv.e(paramInt1, paramInt2, paramInt3, 0);
            return;
        }

        if ((j % 2 == 0) && (j > 2)) {
            a(paramfv, paramInt1 + 1, paramInt2, paramInt3, 300, paramRandom);
            a(paramfv, paramInt1 - 1, paramInt2, paramInt3, 300, paramRandom);
            a(paramfv, paramInt1, paramInt2 - 1, paramInt3, 250, paramRandom);
            a(paramfv, paramInt1, paramInt2 + 1, paramInt3, 250, paramRandom);
            a(paramfv, paramInt1, paramInt2, paramInt3 - 1, 300, paramRandom);
            a(paramfv, paramInt1, paramInt2, paramInt3 + 1, 300, paramRandom);

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

                        int i2 = h(paramfv, k, n, m);
                        if ((i2 > 0) && (paramRandom.nextInt(i1) <= i2)) {
                            // hMod: dynamic spreading of fire.{
                            // avg call amount per placed block of fire ~ 4
                            Block block = new Block(paramfv.a(k, n, m), k, n, m);
                            block.setStatus(3);
                            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, block, null)) {
                                paramfv.e(k, n, m, bi);
                            }
                        }
                    }
                }
            }
        }
    }

    private void a(fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Random paramRandom) {
        int i = b[paramfv.a(paramInt1, paramInt2, paramInt3)];
        if (paramRandom.nextInt(paramInt4) < i) {
            int j = paramfv.a(paramInt1, paramInt2, paramInt3) == hr.am.bi ? 1 : 0;
            if (paramRandom.nextInt(2) == 0) {
                // hMod: VERY SLOW dynamic spreading of fire.
                Block block = new Block(paramfv.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
                block.setStatus(3);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, block, null)) {
                    paramfv.e(paramInt1, paramInt2, paramInt3, bi);
                }
            } else {
                paramfv.e(paramInt1, paramInt2, paramInt3, 0);
            }
            if (j != 0) {
                hr.am.a(paramfv, paramInt1, paramInt2, paramInt3, 0);
            }
        }
    }

    private boolean g(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        // hMod: cast down to fix decompiler error.(6 times)
        if (b((la) paramfv, paramInt1 + 1, paramInt2, paramInt3)) {
            return true;
        }
        if (b((la) paramfv, paramInt1 - 1, paramInt2, paramInt3)) {
            return true;
        }
        if (b((la) paramfv, paramInt1, paramInt2 - 1, paramInt3)) {
            return true;
        }
        if (b((la) paramfv, paramInt1, paramInt2 + 1, paramInt3)) {
            return true;
        }
        if (b((la) paramfv, paramInt1, paramInt2, paramInt3 - 1)) {
            return true;
        }
        return b((la) paramfv, paramInt1, paramInt2, paramInt3 + 1);
    }

    private int h(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        int i = 0;
        if (!paramfv.e(paramInt1, paramInt2, paramInt3)) {
            return 0;
        }

        i = f(paramfv, paramInt1 + 1, paramInt2, paramInt3, i);
        i = f(paramfv, paramInt1 - 1, paramInt2, paramInt3, i);
        i = f(paramfv, paramInt1, paramInt2 - 1, paramInt3, i);
        i = f(paramfv, paramInt1, paramInt2 + 1, paramInt3, i);
        i = f(paramfv, paramInt1, paramInt2, paramInt3 - 1, i);
        i = f(paramfv, paramInt1, paramInt2, paramInt3 + 1, i);

        return i;
    }

    public boolean d() {
        return false;
    }

    public boolean b(la paramla, int paramInt1, int paramInt2, int paramInt3) {
        return a[paramla.a(paramInt1, paramInt2, paramInt3)] > 0;
    }

    public int f(fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = a[paramfv.a(paramInt1, paramInt2, paramInt3)];
        if (i > paramInt4) {
            return i;
        }
        return paramInt4;
    }

    public boolean a(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        return (paramfv.d(paramInt1, paramInt2 - 1, paramInt3)) || (g(paramfv, paramInt1, paramInt2, paramInt3));
    }

    public void b(fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if ((!paramfv.d(paramInt1, paramInt2 - 1, paramInt3)) && (!g(paramfv, paramInt1, paramInt2, paramInt3))) {
            paramfv.e(paramInt1, paramInt2, paramInt3, 0);
            return;
        }
    }

    public void e(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        if ((paramfv.a(paramInt1, paramInt2 - 1, paramInt3) == hr.ap.bi)
                && (hr.be.b_(paramfv, paramInt1, paramInt2, paramInt3))) {
            return;
        }

        if ((!paramfv.d(paramInt1, paramInt2 - 1, paramInt3)) && (!g(paramfv, paramInt1, paramInt2, paramInt3))) {
            paramfv.e(paramInt1, paramInt2, paramInt3, 0);
            return;
        }
        paramfv.i(paramInt1, paramInt2, paramInt3, bi);
    }
}
