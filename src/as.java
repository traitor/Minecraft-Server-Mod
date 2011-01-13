
import java.io.PrintStream;
import java.util.Random;

public class as extends jy {

    public as(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, mh.x, false);
    }

    public fa d(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    public void a(la paramla, int paramInt1, int paramInt2, int paramInt3) {
        float f1;
        float f2;
        if ((paramla.a(paramInt1 - 1, paramInt2, paramInt3) == bi) || (paramla.a(paramInt1 + 1, paramInt2, paramInt3) == bi)) {
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

    public boolean b_(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        int i = 0;
        int j = 0;
        if ((paramfv.a(paramInt1 - 1, paramInt2, paramInt3) == hr.ap.bi) || (paramfv.a(paramInt1 + 1, paramInt2, paramInt3) == hr.ap.bi)) {
            i = 1;
        }
        if ((paramfv.a(paramInt1, paramInt2, paramInt3 - 1) == hr.ap.bi) || (paramfv.a(paramInt1, paramInt2, paramInt3 + 1) == hr.ap.bi)) {
            j = 1;
        }

        System.out.println(i + ", " + j);
        if (i == j) {
            return false;
        }

        if (paramfv.a(paramInt1 - i, paramInt2, paramInt3 - j) == 0) {
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
                int i1 = paramfv.a(paramInt1 + i * k, paramInt2 + m, paramInt3 + j * k);

                if (n != 0) {
                    if (i1 != hr.ap.bi) {
                        return false;
                    }
                } else if ((i1 != 0) && (i1 != hr.ar.bi)) {
                    return false;
                }
            }

        }

        paramfv.i = true;
        for (int k = 0; k < 2; k++) {
            for (m = 0; m < 3; m++) {
                paramfv.e(paramInt1 + i * k, paramInt2 + m, paramInt3 + j * k, hr.be.bi);
            }
        }
        paramfv.i = false;

        return true;
    }

    public void b(fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        // hMod: Block Physics
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Block(this.bh, paramInt1, paramInt2, paramInt3), false)) {
            int i = 0;
            int j = 1;
            if ((paramfv.a(paramInt1 - 1, paramInt2, paramInt3) == bi) || (paramfv.a(paramInt1 + 1, paramInt2, paramInt3) == bi)) {
                i = 1;
                j = 0;
            }

            int k = paramInt2;
            while (paramfv.a(paramInt1, k - 1, paramInt3) == bi) {
                k--;
            }
            if (paramfv.a(paramInt1, k - 1, paramInt3) != hr.ap.bi) {
                paramfv.e(paramInt1, paramInt2, paramInt3, 0);
                return;
            }

            int m = 1;
            while ((m < 4) && (paramfv.a(paramInt1, k + m, paramInt3) == bi)) {
                m++;
            }
            if ((m != 3) || (paramfv.a(paramInt1, k + m, paramInt3) != hr.ap.bi)) {
                paramfv.e(paramInt1, paramInt2, paramInt3, 0);
                return;
            }

            int n = (paramfv.a(paramInt1 - 1, paramInt2, paramInt3) == bi) || (paramfv.a(paramInt1 + 1, paramInt2, paramInt3) == bi) ? 1 : 0;
            int i1 = (paramfv.a(paramInt1, paramInt2, paramInt3 - 1) == bi) || (paramfv.a(paramInt1, paramInt2, paramInt3 + 1) == bi) ? 1 : 0;
            if ((n != 0) && (i1 != 0)) {
                paramfv.e(paramInt1, paramInt2, paramInt3, 0);
                return;
            }

            if (((paramfv.a(paramInt1 + i, paramInt2, paramInt3 + j) != hr.ap.bi) || (paramfv.a(paramInt1 - i, paramInt2, paramInt3 - j) != bi)) && ((paramfv.a(paramInt1 - i, paramInt2, paramInt3 - j) != hr.ap.bi) || (paramfv.a(paramInt1 + i, paramInt2, paramInt3 + j) != bi))) {
                paramfv.e(paramInt1, paramInt2, paramInt3, 0);
                return;
            }
        }
    }

    public boolean a(la paramla, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        return true;
    }

    public int a(Random paramRandom) {
        return 0;
    }

    public void a(fv paramfv, int paramInt1, int paramInt2, int paramInt3, fe paramfe) {
        if (paramfv.z) {
            return;
        }

        paramfe.H();
    }
}
