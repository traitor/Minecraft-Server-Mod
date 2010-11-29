
import java.io.PrintStream;
import java.util.Random;

public class ai extends hw {

    public ai(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, jw.x, false);
    }

    public du d(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    public void a(it paramit, int paramInt1, int paramInt2, int paramInt3) {
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Object[]{new Block(bh, paramInt1, paramInt2, paramInt3), true})) {
            float f1;
            float f2;
            if ((paramit.a(paramInt1 - 1, paramInt2, paramInt3) == this.bh) || (paramit.a(paramInt1 + 1, paramInt2, paramInt3) == this.bh)) {
                f1 = 0.5F;
                f2 = 0.125F;
                a(0.5F - f1, 0.0F, 0.5F - f2, 0.5F + f1, 1.0F, 0.5F + f2);
            } else {
                f1 = 0.125F;
                f2 = 0.5F;
                a(0.5F - f1, 0.0F, 0.5F - f2, 0.5F + f1, 1.0F, 0.5F + f2);
            }
        }
    }

    public boolean a() {
        return false;
    }

    public boolean a_(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        int i = 0;
        int j = 0;
        if ((parameo.a(paramInt1 - 1, paramInt2, paramInt3) == ga.ap.bh) || (parameo.a(paramInt1 + 1, paramInt2, paramInt3) == ga.ap.bh)) {
            i = 1;
        }
        if ((parameo.a(paramInt1, paramInt2, paramInt3 - 1) == ga.ap.bh) || (parameo.a(paramInt1, paramInt2, paramInt3 + 1) == ga.ap.bh)) {
            j = 1;
        }

        if (i == j) {
            return false;
        }

        if (parameo.a(paramInt1 - i, paramInt2, paramInt3 - j) == 0) {
            paramInt1 -= i;
            paramInt3 -= j;
        }
        int m;
        for (int k = -1; k <= 2; k++) {
            for (m = -1; m <= 3; m++) {
                int n = (k == -1) || (k == 2) || (m == -1) || (m == 3) ? 1 : 0;
                if (((k == -1) || (k == 2)) && ((m == -1) || (m == 3))) {
                    continue;
                }
                int i1 = parameo.a(paramInt1 + i * k, paramInt2 + m, paramInt3 + j * k);

                if (n != 0) {
                    if (i1 != ga.ap.bh) {
                        return false;
                    }
                } else if ((i1 != 0) && (i1 != ga.ar.bh)) {
                    return false;
                }
            }

        }

        parameo.i = true;
        for (int k = 0; k < 2; k++) {
            for (m = 0; m < 3; m++) {
                parameo.d(paramInt1 + i * k, paramInt2 + m, paramInt3 + j * k, ga.be.bh);
            }
        }
        parameo.i = false;

        return true;
    }

    public void b(eo parameo, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Object[]{new Block(bh, paramInt1, paramInt2, paramInt3), false})) {
            int i = 0;
            int j = 1;
            if ((parameo.a(paramInt1 - 1, paramInt2, paramInt3) == this.bh) || (parameo.a(paramInt1 + 1, paramInt2, paramInt3) == this.bh)) {
                i = 1;
                j = 0;
            }

            int k = paramInt2;
            while (parameo.a(paramInt1, k - 1, paramInt3) == this.bh) {
                k--;
            }
            if (parameo.a(paramInt1, k - 1, paramInt3) != ga.ap.bh) {
                parameo.d(paramInt1, paramInt2, paramInt3, 0);
                return;
            }

            int m = 1;
            while ((m < 4) && (parameo.a(paramInt1, k + m, paramInt3) == this.bh)) {
                m++;
            }
            if ((m != 3) || (parameo.a(paramInt1, k + m, paramInt3) != ga.ap.bh)) {
                parameo.d(paramInt1, paramInt2, paramInt3, 0);
                return;
            }

            int n = (parameo.a(paramInt1 - 1, paramInt2, paramInt3) == this.bh) || (parameo.a(paramInt1 + 1, paramInt2, paramInt3) == this.bh) ? 1 : 0;
            int i1 = (parameo.a(paramInt1, paramInt2, paramInt3 - 1) == this.bh) || (parameo.a(paramInt1, paramInt2, paramInt3 + 1) == this.bh) ? 1 : 0;
            if ((n != 0) && (i1 != 0)) {
                parameo.d(paramInt1, paramInt2, paramInt3, 0);
                return;
            }

            if (((parameo.a(paramInt1 + i, paramInt2, paramInt3 + j) != ga.ap.bh) || (parameo.a(paramInt1 - i, paramInt2, paramInt3 - j) != this.bh)) && ((parameo.a(paramInt1 - i, paramInt2, paramInt3 - j) != ga.ap.bh) || (parameo.a(paramInt1 + i, paramInt2, paramInt3 + j) != this.bh))) {
                parameo.d(paramInt1, paramInt2, paramInt3, 0);
                return;
            }
        }
    }

    public boolean a(it paramit, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        return true;
    }

    public int a(Random paramRandom) {
        return 0;
    }

    public void a(eo parameo, int paramInt1, int paramInt2, int paramInt3, dx paramdx) {
        if (parameo.z) {
            return;
        }

        paramdx.D();
    }
}
