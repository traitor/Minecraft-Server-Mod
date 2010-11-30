import java.util.Random;

public class ai extends hx {
    public ai(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, jx.x, false);
    }

    @Override
    public dv d(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    @Override
    public void a(iu paramiu, int paramInt1, int paramInt2, int paramInt3) {
        float f1;
        float f2;
        if ((paramiu.a(paramInt1 - 1, paramInt2, paramInt3) == this.bh) || (paramiu.a(paramInt1 + 1, paramInt2, paramInt3) == this.bh)) {
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

    public boolean a_(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        int i = 0;
        int j = 0;
        if ((paramep.a(paramInt1 - 1, paramInt2, paramInt3) == gb.ap.bh) || (paramep.a(paramInt1 + 1, paramInt2, paramInt3) == gb.ap.bh)) {
            i = 1;
        }
        if ((paramep.a(paramInt1, paramInt2, paramInt3 - 1) == gb.ap.bh) || (paramep.a(paramInt1, paramInt2, paramInt3 + 1) == gb.ap.bh)) {
            j = 1;
        }

        System.out.println(i + ", " + j);
        if (i == j) {
            return false;
        }

        if (paramep.a(paramInt1 - i, paramInt2, paramInt3 - j) == 0) {
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
                int i1 = paramep.a(paramInt1 + i * k, paramInt2 + m, paramInt3 + j * k);

                if (n != 0) {
                    if (i1 != gb.ap.bh) {
                        return false;
                    }
                } else if ((i1 != 0) && (i1 != gb.ar.bh)) {
                    return false;
                }
            }

        }

        paramep.i = true;
        for (int k = 0; k < 2; k++) {
            for (m = 0; m < 3; m++) {
                paramep.d(paramInt1 + i * k, paramInt2 + m, paramInt3 + j * k, gb.be.bh);
            }
        }
        paramep.i = false;

        return true;
    }

    @Override
    public void b(ep paramep, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        // hMod: Block Physics
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Object[]{new Block(this.bh, paramInt1, paramInt2, paramInt3), false})) {
            int i = 0;
            int j = 1;
            if ((paramep.a(paramInt1 - 1, paramInt2, paramInt3) == this.bh) || (paramep.a(paramInt1 + 1, paramInt2, paramInt3) == this.bh)) {
                i = 1;
                j = 0;
            }

            int k = paramInt2;
            while (paramep.a(paramInt1, k - 1, paramInt3) == this.bh) {
                k--;
            }
            if (paramep.a(paramInt1, k - 1, paramInt3) != gb.ap.bh) {
                paramep.d(paramInt1, paramInt2, paramInt3, 0);
                return;
            }

            int m = 1;
            while ((m < 4) && (paramep.a(paramInt1, k + m, paramInt3) == this.bh)) {
                m++;
            }
            if ((m != 3) || (paramep.a(paramInt1, k + m, paramInt3) != gb.ap.bh)) {
                paramep.d(paramInt1, paramInt2, paramInt3, 0);
                return;
            }

            int n = (paramep.a(paramInt1 - 1, paramInt2, paramInt3) == this.bh) || (paramep.a(paramInt1 + 1, paramInt2, paramInt3) == this.bh) ? 1 : 0;
            int i1 = (paramep.a(paramInt1, paramInt2, paramInt3 - 1) == this.bh) || (paramep.a(paramInt1, paramInt2, paramInt3 + 1) == this.bh) ? 1 : 0;
            if ((n != 0) && (i1 != 0)) {
                paramep.d(paramInt1, paramInt2, paramInt3, 0);
                return;
            }

            if (((paramep.a(paramInt1 + i, paramInt2, paramInt3 + j) != gb.ap.bh) || (paramep.a(paramInt1 - i, paramInt2, paramInt3 - j) != this.bh)) && ((paramep.a(paramInt1 - i, paramInt2, paramInt3 - j) != gb.ap.bh) || (paramep.a(paramInt1 + i, paramInt2, paramInt3 + j) != this.bh))) {
                paramep.d(paramInt1, paramInt2, paramInt3, 0);
                return;

            }
        }
    }

    @Override
    public boolean a(iu paramiu, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        return true;
    }

    @Override
    public int a(Random paramRandom) {
        return 0;
    }

    @Override
    public void a(ep paramep, int paramInt1, int paramInt2, int paramInt3, dy paramdy) {
        if (paramep.z) {
            return;
        }

        paramdy.D();
    }
}
