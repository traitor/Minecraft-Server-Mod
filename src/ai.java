import java.util.Random;

public class ai extends hy {
    public ai(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, jy.x, false);
    }

    @Override
    public dw d(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    @Override
    public void a(iv paramiv, int paramInt1, int paramInt2, int paramInt3) {
        float f1;
        float f2;
        if ((paramiv.a(paramInt1 - 1, paramInt2, paramInt3) == this.bh) || (paramiv.a(paramInt1 + 1, paramInt2, paramInt3) == this.bh)) {
            f1 = 0.5F;
            f2 = 0.125F;
            a(0.5F - f1, 0.0F, 0.5F - f2, 0.5F + f1, 1.0F, 0.5F + f2);
        } else {
            f1 = 0.125F;
            f2 = 0.5F;
            a(0.5F - f1, 0.0F, 0.5F - f2, 0.5F + f1, 1.0F, 0.5F + f2);
        }
    }

    @Override
    public boolean a() {
        return false;
    }

    public boolean a_(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        int i = 0;
        int j = 0;
        if ((parameq.a(paramInt1 - 1, paramInt2, paramInt3) == gc.ap.bh) || (parameq.a(paramInt1 + 1, paramInt2, paramInt3) == gc.ap.bh)) {
            i = 1;
        }
        if ((parameq.a(paramInt1, paramInt2, paramInt3 - 1) == gc.ap.bh) || (parameq.a(paramInt1, paramInt2, paramInt3 + 1) == gc.ap.bh)) {
            j = 1;
        }

        System.out.println(i + ", " + j);
        if (i == j) {
            return false;
        }

        if (parameq.a(paramInt1 - i, paramInt2, paramInt3 - j) == 0) {
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
                int i1 = parameq.a(paramInt1 + i * k, paramInt2 + m, paramInt3 + j * k);

                if (n != 0) {
                    if (i1 != gc.ap.bh) {
                        return false;
                    }
                } else if ((i1 != 0) && (i1 != gc.ar.bh)) {
                    return false;
                }
            }

        }

        parameq.i = true;
        for (int k = 0; k < 2; k++) {
            for (m = 0; m < 3; m++) {
                parameq.d(paramInt1 + i * k, paramInt2 + m, paramInt3 + j * k, gc.be.bh);
            }
        }
        parameq.i = false;

        return true;
    }

    @Override
    public void b(eq parameq, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        // hMod: Block Physics
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Block(this.bh, paramInt1, paramInt2, paramInt3), false)) {
            int i = 0;
            int j = 1;
            if ((parameq.a(paramInt1 - 1, paramInt2, paramInt3) == this.bh) || (parameq.a(paramInt1 + 1, paramInt2, paramInt3) == this.bh)) {
                i = 1;
                j = 0;
            }

            int k = paramInt2;
            while (parameq.a(paramInt1, k - 1, paramInt3) == this.bh) {
                k--;
            }
            if (parameq.a(paramInt1, k - 1, paramInt3) != gc.ap.bh) {
                parameq.d(paramInt1, paramInt2, paramInt3, 0);
                return;
            }

            int m = 1;
            while ((m < 4) && (parameq.a(paramInt1, k + m, paramInt3) == this.bh)) {
                m++;
            }
            if ((m != 3) || (parameq.a(paramInt1, k + m, paramInt3) != gc.ap.bh)) {
                parameq.d(paramInt1, paramInt2, paramInt3, 0);
                return;
            }

            int n = (parameq.a(paramInt1 - 1, paramInt2, paramInt3) == this.bh) || (parameq.a(paramInt1 + 1, paramInt2, paramInt3) == this.bh) ? 1 : 0;
            int i1 = (parameq.a(paramInt1, paramInt2, paramInt3 - 1) == this.bh) || (parameq.a(paramInt1, paramInt2, paramInt3 + 1) == this.bh) ? 1 : 0;
            if ((n != 0) && (i1 != 0)) {
                parameq.d(paramInt1, paramInt2, paramInt3, 0);
                return;
            }

            if (((parameq.a(paramInt1 + i, paramInt2, paramInt3 + j) != gc.ap.bh) || (parameq.a(paramInt1 - i, paramInt2, paramInt3 - j) != this.bh)) && ((parameq.a(paramInt1 - i, paramInt2, paramInt3 - j) != gc.ap.bh) || (parameq.a(paramInt1 + i, paramInt2, paramInt3 + j) != this.bh))) {
                parameq.d(paramInt1, paramInt2, paramInt3, 0);
                return;
            }
        }
    }

    @Override
    public boolean a(iv paramiv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        return true;
    }

    @Override
    public int a(Random paramRandom) {
        return 0;
    }

    @Override
    public void a(eq parameq, int paramInt1, int paramInt2, int paramInt3, ea paramea) {
        if (parameq.z) {
            return;
        }

        paramea.D();
    }
}
