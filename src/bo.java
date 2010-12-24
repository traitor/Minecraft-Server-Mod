
import java.util.List;
import java.util.Random;

public class bo extends gv {

    private ds a;

    protected bo(int paramInt1, int paramInt2, ds paramds) {
        super(paramInt1, paramInt2, la.d);
        this.a = paramds;
        a(true);

        float f = 0.0625F;
        a(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
    }

    public int b() {
        return 20;
    }

    public el d(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    public boolean a() {
        return false;
    }

    public boolean a(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        return paramff.d(paramInt1, paramInt2 - 1, paramInt3);
    }

    public void e(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
    }

    public void b(ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = 0;

        if (!paramff.d(paramInt1, paramInt2 - 1, paramInt3)) {
            i = 1;
        }

        if (i != 0) {
            a_(paramff, paramInt1, paramInt2, paramInt3, paramff.b(paramInt1, paramInt2, paramInt3));
            paramff.d(paramInt1, paramInt2, paramInt3, 0);
        }
    }

    public void a(ff paramff, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        if (paramff.z) {
            return;
        }
        if (paramff.b(paramInt1, paramInt2, paramInt3) == 0) {
            return;
        }

        g(paramff, paramInt1, paramInt2, paramInt3);
    }

    public void a(ff paramff, int paramInt1, int paramInt2, int paramInt3, ep paramep) {
        if (paramff.z) {
            return;
        }

        if (paramff.b(paramInt1, paramInt2, paramInt3) == 1) {
            return;
        }

        g(paramff, paramInt1, paramInt2, paramInt3);
    }

    private void g(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramff.b(paramInt1, paramInt2, paramInt3) == 1 ? 1 : 0;
        int j = 0;

        float f = 0.125F;
        List localList = null;
        if (this.a == ds.a) {
            localList = paramff.b(null, el.b(paramInt1 + f, paramInt2, paramInt3 + f, paramInt1 + 1 - f, paramInt2 + 0.25D, paramInt3 + 1 - f));
        }
        if (this.a == ds.b) {
            localList = paramff.a(lc.class, el.b(paramInt1 + f, paramInt2, paramInt3 + f, paramInt1 + 1 - f, paramInt2 + 0.25D, paramInt3 + 1 - f));
        }
        if (this.a == ds.c) {
            localList = paramff.a(gq.class, el.b(paramInt1 + f, paramInt2, paramInt3 + f, paramInt1 + 1 - f, paramInt2 + 0.25D, paramInt3 + 1 - f));
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
            paramff.b(paramInt1, paramInt2, paramInt3, 1);
            paramff.g(paramInt1, paramInt2, paramInt3, this.bh);
            paramff.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
            paramff.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);

            paramff.a(paramInt1 + 0.5D, paramInt2 + 0.1D, paramInt3 + 0.5D, "random.click", 0.3F, 0.6F);
        }
        if ((j == 0) && (i != 0)) {
            paramff.b(paramInt1, paramInt2, paramInt3, 0);
            paramff.g(paramInt1, paramInt2, paramInt3, this.bh);
            paramff.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
            paramff.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);

            paramff.a(paramInt1 + 0.5D, paramInt2 + 0.1D, paramInt3 + 0.5D, "random.click", 0.3F, 0.5F);
        }

        if (j != 0) {
            paramff.h(paramInt1, paramInt2, paramInt3, this.bh);
        }
    }

    public void b(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramff.b(paramInt1, paramInt2, paramInt3);
        if (i > 0) {
            paramff.g(paramInt1, paramInt2, paramInt3, this.bh);
            paramff.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
        }
        super.b(paramff, paramInt1, paramInt2, paramInt3);
    }

    public void a(jx paramjw, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramjw.b(paramInt1, paramInt2, paramInt3) == 1 ? 1 : 0;

        float f = 0.0625F;
        if (i != 0) {
            a(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
        } else {
            a(f, 0.0F, f, 1.0F - f, 0.0625F, 1.0F - f);
        }
    }

    public boolean b(jx paramjw, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        return paramjw.b(paramInt1, paramInt2, paramInt3) > 0;
    }

    public boolean d(ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramff.b(paramInt1, paramInt2, paramInt3) == 0) {
            return false;
        }
        return paramInt4 == 1;
    }

    public boolean c() {
        return true;
    }
}
