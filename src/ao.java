
import java.io.PrintStream;
import java.util.Random;

public class ao extends iw {

    public ao(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, kz.x, false);
    }

    public el d(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    public void a(jw paramjw, int paramInt1, int paramInt2, int paramInt3) {
        float f1;
        float f2;
        if ((paramjw.a(paramInt1 - 1, paramInt2, paramInt3) == this.bh) || (paramjw.a(paramInt1 + 1, paramInt2, paramInt3) == this.bh)) {
            f1 = 0.5F;
            f2 = 0.125F;
            a(0.5F - f1, 0.0F, 0.5F - f2, 0.5F + f1, 1.0F, 0.5F + f2);
        } else {
            f1 = 0.125F;
            f2 = 0.5F;
            a(0.5F - f1, 0.0F, 0.5F - f2, 0.5F + f1, 1.0F, 0.5F + f2);
        }
    }

    public boolean a() {
        return false;
    }

    public boolean a_(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        int i = 0;
        int j = 0;
        if ((paramff.a(paramInt1 - 1, paramInt2, paramInt3) == gu.ap.bh) || (paramff.a(paramInt1 + 1, paramInt2, paramInt3) == gu.ap.bh)) {
            i = 1;
        }
        if ((paramff.a(paramInt1, paramInt2, paramInt3 - 1) == gu.ap.bh) || (paramff.a(paramInt1, paramInt2, paramInt3 + 1) == gu.ap.bh)) {
            j = 1;
        }

        System.out.println(i + ", " + j);
        if (i == j) {
            return false;
        }

        if (paramff.a(paramInt1 - i, paramInt2, paramInt3 - j) == 0) {
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
                int i1 = paramff.a(paramInt1 + i * k, paramInt2 + m, paramInt3 + j * k);

                if (n != 0) {
                    if (i1 != gu.ap.bh) {
                        return false;
                    }
                } else if ((i1 != 0) && (i1 != gu.ar.bh)) {
                    return false;
                }
            }

        }

        paramff.i = true;
        for (int k = 0; k < 2; k++) {
            for (m = 0; m < 3; m++) {
                paramff.d(paramInt1 + i * k, paramInt2 + m, paramInt3 + j * k, gu.be.bh);
            }
        }
        paramff.i = false;

        return true;
    }

    public void b(ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        // hMod: Block Physics
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Block(this.bh, paramInt1, paramInt2, paramInt3), false)) {
            int i = 0;
            int j = 1;
            if ((paramff.a(paramInt1 - 1, paramInt2, paramInt3) == this.bh) || (paramff.a(paramInt1 + 1, paramInt2, paramInt3) == this.bh)) {
                i = 1;
                j = 0;
            }

            int k = paramInt2;
            while (paramff.a(paramInt1, k - 1, paramInt3) == this.bh) {
                k--;
            }
            if (paramff.a(paramInt1, k - 1, paramInt3) != gu.ap.bh) {
                paramff.d(paramInt1, paramInt2, paramInt3, 0);
                return;
            }

            int m = 1;
            while ((m < 4) && (paramff.a(paramInt1, k + m, paramInt3) == this.bh)) {
                m++;
            }
            if ((m != 3) || (paramff.a(paramInt1, k + m, paramInt3) != gu.ap.bh)) {
                paramff.d(paramInt1, paramInt2, paramInt3, 0);
                return;
            }

            int n = (paramff.a(paramInt1 - 1, paramInt2, paramInt3) == this.bh) || (paramff.a(paramInt1 + 1, paramInt2, paramInt3) == this.bh) ? 1 : 0;
            int i1 = (paramff.a(paramInt1, paramInt2, paramInt3 - 1) == this.bh) || (paramff.a(paramInt1, paramInt2, paramInt3 + 1) == this.bh) ? 1 : 0;
            if ((n != 0) && (i1 != 0)) {
                paramff.d(paramInt1, paramInt2, paramInt3, 0);
                return;
            }

            if (((paramff.a(paramInt1 + i, paramInt2, paramInt3 + j) != gu.ap.bh) || (paramff.a(paramInt1 - i, paramInt2, paramInt3 - j) != this.bh)) && ((paramff.a(paramInt1 - i, paramInt2, paramInt3 - j) != gu.ap.bh) || (paramff.a(paramInt1 + i, paramInt2, paramInt3 + j) != this.bh))) {
                paramff.d(paramInt1, paramInt2, paramInt3, 0);
                return;
            }
        }
    }

    public boolean a(jw paramjw, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        return true;
    }

    public int a(Random paramRandom) {
        return 0;
    }

    public void a(ff paramff, int paramInt1, int paramInt2, int paramInt3, ep paramep) {
        if (paramff.z) {
            return;
        }

        paramep.D();
    }
}
