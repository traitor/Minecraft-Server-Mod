
import java.util.Random;

public class jo extends fy {

    private int[] a = new int[256];
    private int[] b = new int[256];

    protected jo(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, jt.l);

        a(fy.x.bh, 5, 20);
        a(fy.J.bh, 5, 5);
        a(fy.K.bh, 30, 60);
        a(fy.an.bh, 30, 20);
        a(fy.am.bh, 15, 100);
        a(fy.ab.bh, 30, 60);

        a(true);
    }

    private void a(int paramInt1, int paramInt2, int paramInt3) {
        this.a[paramInt1] = paramInt2;
        this.b[paramInt1] = paramInt3;
    }

    public dt d(em paramem, int paramInt1, int paramInt2, int paramInt3) {
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

    public void a(em paramem, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        int i = paramem.a(paramInt1, paramInt2 - 1, paramInt3) == fy.bb.bh ? 1 : 0;

        int j = paramem.b(paramInt1, paramInt2, paramInt3);
        if (j < 15) {
            paramem.b(paramInt1, paramInt2, paramInt3, j + 1);
            paramem.h(paramInt1, paramInt2, paramInt3, this.bh);
        }
        if ((i == 0) && (!g(paramem, paramInt1, paramInt2, paramInt3))) {
            if ((!paramem.d(paramInt1, paramInt2 - 1, paramInt3)) || (j > 3)) {
                paramem.d(paramInt1, paramInt2, paramInt3, 0);
            }
            return;
        }

        if ((i == 0) && (!b((iq) paramem, paramInt1, paramInt2 - 1, paramInt3))
                && (j == 15) && (paramRandom.nextInt(4) == 0)) {
            paramem.d(paramInt1, paramInt2, paramInt3, 0);
            return;
        }

        if ((j % 2 == 0) && (j > 2)) {
            a(paramem, paramInt1 + 1, paramInt2, paramInt3, 300, paramRandom);
            a(paramem, paramInt1 - 1, paramInt2, paramInt3, 300, paramRandom);
            a(paramem, paramInt1, paramInt2 - 1, paramInt3, 200, paramRandom);
            a(paramem, paramInt1, paramInt2 + 1, paramInt3, 250, paramRandom);
            a(paramem, paramInt1, paramInt2, paramInt3 - 1, 300, paramRandom);
            a(paramem, paramInt1, paramInt2, paramInt3 + 1, 300, paramRandom);

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

                        int i2 = h(paramem, k, n, m);
                        if ((i2 > 0) && (paramRandom.nextInt(i1) <= i2)) {
                            //dynamic spreading of fire.
                            // avg call amount per placed block of fire ~ 4
                            Block block = new Block(paramem.a(k, n, m), k, n, m);
                            block.setStatus(3);
                            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, new Object[]{block , null})) {
                                paramem.d(k, n, m, this.bh);

                            }
                        }
                    }
                }
            }
        }
    }

    private void a(em paramem, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Random paramRandom) {
        int i = this.b[paramem.a(paramInt1, paramInt2, paramInt3)];
        if (paramRandom.nextInt(paramInt4) < i) {
            int j = paramem.a(paramInt1, paramInt2, paramInt3) == fy.am.bh ? 1 : 0;
            if (paramRandom.nextInt(2) == 0) {
                //VERY SLOW dynamic spreading of fire.
                Block block = new Block(paramem.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
                block.setStatus(3);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, new Object[]{block, null})) {
                   paramem.d(paramInt1, paramInt2, paramInt3, this.bh);
                }
            } else {
                paramem.d(paramInt1, paramInt2, paramInt3, 0);
            }
            if (j != 0) {
                fy.am.a(paramem, paramInt1, paramInt2, paramInt3, 0);
            }
        }
    }

    private boolean g(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        if (b((iq) paramem, paramInt1 + 1, paramInt2, paramInt3)) {
            return true;
        }
        if (b((iq) paramem, paramInt1 - 1, paramInt2, paramInt3)) {
            return true;
        }
        if (b((iq) paramem, paramInt1, paramInt2 - 1, paramInt3)) {
            return true;
        }
        if (b((iq) paramem, paramInt1, paramInt2 + 1, paramInt3)) {
            return true;
        }
        if (b((iq) paramem, paramInt1, paramInt2, paramInt3 - 1)) {
            return true;
        }
        return b((iq) paramem, paramInt1, paramInt2, paramInt3 + 1);
    }

    private int h(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        int i = 0;
        if (paramem.a(paramInt1, paramInt2, paramInt3) != 0) {
            return 0;
        }

        i = f(paramem, paramInt1 + 1, paramInt2, paramInt3, i);
        i = f(paramem, paramInt1 - 1, paramInt2, paramInt3, i);
        i = f(paramem, paramInt1, paramInt2 - 1, paramInt3, i);
        i = f(paramem, paramInt1, paramInt2 + 1, paramInt3, i);
        i = f(paramem, paramInt1, paramInt2, paramInt3 - 1, i);
        i = f(paramem, paramInt1, paramInt2, paramInt3 + 1, i);

        return i;
    }

    public boolean d() {
        return false;
    }

    public boolean b(iq paramiq, int paramInt1, int paramInt2, int paramInt3) {
        return this.a[paramiq.a(paramInt1, paramInt2, paramInt3)] > 0;
    }

    public int f(em paramem, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = this.a[paramem.a(paramInt1, paramInt2, paramInt3)];
        if (i > paramInt4) {
            return i;
        }
        return paramInt4;
    }

    public boolean a(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        return (paramem.d(paramInt1, paramInt2 - 1, paramInt3)) || (g(paramem, paramInt1, paramInt2, paramInt3));
    }

    public void b(em paramem, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if ((!paramem.d(paramInt1, paramInt2 - 1, paramInt3)) && (!g(paramem, paramInt1, paramInt2, paramInt3))) {
            paramem.d(paramInt1, paramInt2, paramInt3, 0);
            return;
        }
    }

    public void e(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        if ((paramem.a(paramInt1, paramInt2 - 1, paramInt3) == fy.ap.bh)
                && (fy.be.a_(paramem, paramInt1, paramInt2, paramInt3))) {
            return;
        }

        if ((!paramem.d(paramInt1, paramInt2 - 1, paramInt3)) && (!g(paramem, paramInt1, paramInt2, paramInt3))) {
            paramem.d(paramInt1, paramInt2, paramInt3, 0);
            return;
        }
        paramem.h(paramInt1, paramInt2, paramInt3, this.bh);
    }
}
