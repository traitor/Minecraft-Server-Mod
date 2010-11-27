
import java.util.List;
import java.util.Random;

public class be extends ga {

    private dd a;

    protected be(int paramInt1, int paramInt2, dd paramdd) {
        super(paramInt1, paramInt2, jw.d);
        this.a = paramdd;
        a(true);

        float f = 0.0625F;
        a(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
    }

    public int b() {
        return 20;
    }

    public du d(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    public boolean a() {
        return false;
    }

    public boolean a(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        return parameo.d(paramInt1, paramInt2 - 1, paramInt3);
    }

    public void e(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
    }

    public void b(eo parameo, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = 0;

        if (!parameo.d(paramInt1, paramInt2 - 1, paramInt3)) {
            i = 1;
        }

        if (i != 0) {
            a_(parameo, paramInt1, paramInt2, paramInt3, parameo.b(paramInt1, paramInt2, paramInt3));
            parameo.d(paramInt1, paramInt2, paramInt3, 0);
        }
    }

    public void a(eo parameo, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        if (parameo.b(paramInt1, paramInt2, paramInt3) == 0) {
            return;
        }

        g(parameo, paramInt1, paramInt2, paramInt3);
    }

    public void a(eo parameo, int paramInt1, int paramInt2, int paramInt3, dx paramdx) {
        if (parameo.b(paramInt1, paramInt2, paramInt3) == 1) {
            return;
        }

        g(parameo, paramInt1, paramInt2, paramInt3);
    }

    private void g(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        int i = parameo.b(paramInt1, paramInt2, paramInt3) == 1 ? 1 : 0;
        int j = 0;

        float f = 0.125F;
        List localList = null;
        if (this.a == dd.a) {
            localList = parameo.b(null, du.b(paramInt1 + f, paramInt2, paramInt3 + f, paramInt1 + 1 - f, paramInt2 + 0.25D, paramInt3 + 1 - f));
        }
        if (this.a == dd.b) {
            localList = parameo.a(jy.class, du.b(paramInt1 + f, paramInt2, paramInt3 + f, paramInt1 + 1 - f, paramInt2 + 0.25D, paramInt3 + 1 - f));
        }
        if (this.a == dd.c) {
            localList = parameo.a(fx.class, du.b(paramInt1 + f, paramInt2, paramInt3 + f, paramInt1 + 1 - f, paramInt2 + 0.25D, paramInt3 + 1 - f));
        }
        if (localList.size() > 0) {
            j = 1;
        }

        if (j != i) {
            // hMod: Allow pressure plate interaction to power redstone
            j = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Object[]{new Block(this.bh, paramInt1, paramInt2, paramInt3), i, j});
            if (j > 0)
                j = 1;
        }

        if ((j != 0) && (i == 0)) {
            parameo.b(paramInt1, paramInt2, paramInt3, 1);
            parameo.g(paramInt1, paramInt2, paramInt3, this.bh);
            parameo.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
            parameo.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);

            parameo.a(paramInt1 + 0.5D, paramInt2 + 0.1D, paramInt3 + 0.5D, "random.click", 0.3F, 0.6F);
        }
        if ((j == 0) && (i != 0)) {
            parameo.b(paramInt1, paramInt2, paramInt3, 0);
            parameo.g(paramInt1, paramInt2, paramInt3, this.bh);
            parameo.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
            parameo.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);

            parameo.a(paramInt1 + 0.5D, paramInt2 + 0.1D, paramInt3 + 0.5D, "random.click", 0.3F, 0.5F);
        }

        if (j != 0) {
            parameo.h(paramInt1, paramInt2, paramInt3, this.bh);
        }
    }

    public void b(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        int i = parameo.b(paramInt1, paramInt2, paramInt3);
        if (i > 0) {
            parameo.g(paramInt1, paramInt2, paramInt3, this.bh);
            parameo.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
        }
        super.b(parameo, paramInt1, paramInt2, paramInt3);
    }

    public void a(it paramit, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramit.b(paramInt1, paramInt2, paramInt3) == 1 ? 1 : 0;

        float f = 0.0625F;
        if (i != 0) {
            a(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
        } else {
            a(f, 0.0F, f, 1.0F - f, 0.0625F, 1.0F - f);
        }
    }

    public boolean b(it paramit, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        return paramit.b(paramInt1, paramInt2, paramInt3) > 0;
    }

    public boolean d(eo parameo, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (parameo.b(paramInt1, paramInt2, paramInt3) == 0) {
            return false;
        }
        return paramInt4 == 1;
    }

    public boolean c() {
        return true;
    }
}
