
import java.util.List;
import java.util.Random;

public class bw extends hr {

    private ee a;

    protected bw(int paramInt1, int paramInt2, ee paramee) {
        super(paramInt1, paramInt2, mh.d);
        a = paramee;
        a(true);

        float f = 0.0625F;
        a(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
    }

    public int b() {
        return 20;
    }

    public fa d(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    public boolean a() {
        return false;
    }

    public boolean a(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        return paramfv.d(paramInt1, paramInt2 - 1, paramInt3);
    }

    public void e(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
    }

    public void b(fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = 0;

        if (!paramfv.d(paramInt1, paramInt2 - 1, paramInt3)) {
            i = 1;
        }

        if (i != 0) {
            a_(paramfv, paramInt1, paramInt2, paramInt3, paramfv.b(paramInt1, paramInt2, paramInt3));
            paramfv.e(paramInt1, paramInt2, paramInt3, 0);
        }
    }

    public void a(fv paramfv, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        if (paramfv.z) {
            return;
        }
        if (paramfv.b(paramInt1, paramInt2, paramInt3) == 0) {
            return;
        }

        g(paramfv, paramInt1, paramInt2, paramInt3);
    }

    public void a(fv paramfv, int paramInt1, int paramInt2, int paramInt3, fe paramfe) {
        if (paramfv.z) {
            return;
        }

        if (paramfv.b(paramInt1, paramInt2, paramInt3) == 1) {
            return;
        }

        g(paramfv, paramInt1, paramInt2, paramInt3);
    }

    private void g(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramfv.b(paramInt1, paramInt2, paramInt3) == 1 ? 1 : 0;
        int j = 0;

        float f = 0.125F;
        List localList = null;
        if (a == ee.a) {
            localList = paramfv.b(null, fa.b(paramInt1 + f, paramInt2, paramInt3 + f, paramInt1 + 1 - f, paramInt2 + 0.25D, paramInt3 + 1 - f));
        }
        if (a == ee.b) {
            localList = paramfv.a(mj.class, fa.b(paramInt1 + f, paramInt2, paramInt3 + f, paramInt1 + 1 - f, paramInt2 + 0.25D, paramInt3 + 1 - f));
        }
        if (a == ee.c) {
            localList = paramfv.a(hl.class, fa.b(paramInt1 + f, paramInt2, paramInt3 + f, paramInt1 + 1 - f, paramInt2 + 0.25D, paramInt3 + 1 - f));
        }
        if (localList.size() > 0) {
            j = 1;
        }
        // hMod: Allow pressure plate interaction to power redstone
        if (j != i) {
            j = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Block(this.bh, paramInt1, paramInt2, paramInt3), i, j);
            if (j > 0) {
                j = 1;
            }
        }

        if ((j != 0) && (i == 0)) {
            paramfv.c(paramInt1, paramInt2, paramInt3, 1);
            paramfv.h(paramInt1, paramInt2, paramInt3, bi);
            paramfv.h(paramInt1, paramInt2 - 1, paramInt3, bi);
            paramfv.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);

            paramfv.a(paramInt1 + 0.5D, paramInt2 + 0.1D, paramInt3 + 0.5D, "random.click", 0.3F, 0.6F);
        }
        if ((j == 0) && (i != 0)) {
            paramfv.c(paramInt1, paramInt2, paramInt3, 0);
            paramfv.h(paramInt1, paramInt2, paramInt3, bi);
            paramfv.h(paramInt1, paramInt2 - 1, paramInt3, bi);
            paramfv.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);

            paramfv.a(paramInt1 + 0.5D, paramInt2 + 0.1D, paramInt3 + 0.5D, "random.click", 0.3F, 0.5F);
        }

        if (j != 0) {
            paramfv.i(paramInt1, paramInt2, paramInt3, bi);
        }
    }

    public void b(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramfv.b(paramInt1, paramInt2, paramInt3);
        if (i > 0) {
            paramfv.h(paramInt1, paramInt2, paramInt3, bi);
            paramfv.h(paramInt1, paramInt2 - 1, paramInt3, bi);
        }
        super.b(paramfv, paramInt1, paramInt2, paramInt3);
    }

    public void a(la paramla, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramla.b(paramInt1, paramInt2, paramInt3) == 1 ? 1 : 0;

        float f = 0.0625F;
        if (i != 0) {
            a(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
        } else {
            a(f, 0.0F, f, 1.0F - f, 0.0625F, 1.0F - f);
        }
    }

    public boolean b(la paramla, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        return paramla.b(paramInt1, paramInt2, paramInt3) > 0;
    }

    public boolean d(fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramfv.b(paramInt1, paramInt2, paramInt3) == 0) {
            return false;
        }
        return paramInt4 == 1;
    }

    public boolean c() {
        return true;
    }
}
