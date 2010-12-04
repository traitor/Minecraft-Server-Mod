import java.util.List;
import java.util.Random;

public class be extends gc {
    private df a;

    protected be(int paramInt1, int paramInt2, df paramdf) {
        super(paramInt1, paramInt2, jy.d);
        this.a = paramdf;
        a(true);

        float f = 0.0625F;
        a(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
    }

    @Override
    public int b() {
        return 20;
    }

    @Override
    public dw d(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public boolean a(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        return parameq.d(paramInt1, paramInt2 - 1, paramInt3);
    }

    @Override
    public void e(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
    }

    @Override
    public void b(eq parameq, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = 0;

        if (!parameq.d(paramInt1, paramInt2 - 1, paramInt3)) {
            i = 1;
        }

        if (i != 0) {
            a_(parameq, paramInt1, paramInt2, paramInt3, parameq.b(paramInt1, paramInt2, paramInt3));
            parameq.d(paramInt1, paramInt2, paramInt3, 0);
        }
    }

    @Override
    public void a(eq parameq, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        if (parameq.z) {
            return;
        }
        if (parameq.b(paramInt1, paramInt2, paramInt3) == 0) {
            return;
        }

        g(parameq, paramInt1, paramInt2, paramInt3);
    }

    @Override
    public void a(eq parameq, int paramInt1, int paramInt2, int paramInt3, ea paramea) {
        if (parameq.z) {
            return;
        }

        if (parameq.b(paramInt1, paramInt2, paramInt3) == 1) {
            return;
        }

        g(parameq, paramInt1, paramInt2, paramInt3);
    }

    private void g(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        int i = parameq.b(paramInt1, paramInt2, paramInt3) == 1 ? 1 : 0;
        int j = 0;

        float f = 0.125F;
        List localList = null;
        if (this.a == df.a) {
            localList = parameq.b(null, dw.b(paramInt1 + f, paramInt2, paramInt3 + f, paramInt1 + 1 - f, paramInt2 + 0.25D, paramInt3 + 1 - f));
        }
        if (this.a == df.b) {
            localList = parameq.a(ka.class, dw.b(paramInt1 + f, paramInt2, paramInt3 + f, paramInt1 + 1 - f, paramInt2 + 0.25D, paramInt3 + 1 - f));
        }
        if (this.a == df.c) {
            localList = parameq.a(fz.class, dw.b(paramInt1 + f, paramInt2, paramInt3 + f, paramInt1 + 1 - f, paramInt2 + 0.25D, paramInt3 + 1 - f));
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
            parameq.b(paramInt1, paramInt2, paramInt3, 1);
            parameq.g(paramInt1, paramInt2, paramInt3, this.bh);
            parameq.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
            parameq.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);

            parameq.a(paramInt1 + 0.5D, paramInt2 + 0.1D, paramInt3 + 0.5D, "random.click", 0.3F, 0.6F);
        }
        if ((j == 0) && (i != 0)) {
            parameq.b(paramInt1, paramInt2, paramInt3, 0);
            parameq.g(paramInt1, paramInt2, paramInt3, this.bh);
            parameq.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
            parameq.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);

            parameq.a(paramInt1 + 0.5D, paramInt2 + 0.1D, paramInt3 + 0.5D, "random.click", 0.3F, 0.5F);
        }

        if (j != 0) {
            parameq.h(paramInt1, paramInt2, paramInt3, this.bh);
        }
    }

    @Override
    public void b(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        int i = parameq.b(paramInt1, paramInt2, paramInt3);
        if (i > 0) {
            parameq.g(paramInt1, paramInt2, paramInt3, this.bh);
            parameq.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
        }
        super.b(parameq, paramInt1, paramInt2, paramInt3);
    }

    @Override
    public void a(iv paramiv, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramiv.b(paramInt1, paramInt2, paramInt3) == 1 ? 1 : 0;

        float f = 0.0625F;
        if (i != 0) {
            a(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
        } else {
            a(f, 0.0F, f, 1.0F - f, 0.0625F, 1.0F - f);
        }
    }

    @Override
    public boolean b(iv paramiv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        return paramiv.b(paramInt1, paramInt2, paramInt3) > 0;
    }

    @Override
    public boolean d(eq parameq, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (parameq.b(paramInt1, paramInt2, paramInt3) == 0) {
            return false;
        }
        return paramInt4 == 1;
    }

    @Override
    public boolean c() {
        return true;
    }
}
