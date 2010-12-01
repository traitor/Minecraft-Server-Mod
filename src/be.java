import java.util.List;
import java.util.Random;

public class be extends gb {
    private de a;

    protected be(int paramInt1, int paramInt2, de paramde) {
        super(paramInt1, paramInt2, jx.d);
        this.a = paramde;
        a(true);

        float f = 0.0625F;
        a(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
    }

    @Override
    public int b() {
        return 20;
    }

    @Override
    public dv d(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public boolean a(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        return paramep.d(paramInt1, paramInt2 - 1, paramInt3);
    }

    @Override
    public void e(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
    }

    @Override
    public void b(ep paramep, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = 0;

        if (!paramep.d(paramInt1, paramInt2 - 1, paramInt3)) {
            i = 1;
        }

        if (i != 0) {
            a_(paramep, paramInt1, paramInt2, paramInt3, paramep.b(paramInt1, paramInt2, paramInt3));
            paramep.d(paramInt1, paramInt2, paramInt3, 0);
        }
    }

    @Override
    public void a(ep paramep, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        if (paramep.z) {
            return;
        }
        if (paramep.b(paramInt1, paramInt2, paramInt3) == 0) {
            return;
        }

        g(paramep, paramInt1, paramInt2, paramInt3);
    }

    @Override
    public void a(ep paramep, int paramInt1, int paramInt2, int paramInt3, dy paramdy) {
        if (paramep.z) {
            return;
        }

        if (paramep.b(paramInt1, paramInt2, paramInt3) == 1) {
            return;
        }

        g(paramep, paramInt1, paramInt2, paramInt3);
    }

    private void g(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramep.b(paramInt1, paramInt2, paramInt3) == 1 ? 1 : 0;
        int j = 0;

        float f = 0.125F;
        List localList = null;
        if (this.a == de.a) {
            localList = paramep.b(null, dv.b(paramInt1 + f, paramInt2, paramInt3 + f, paramInt1 + 1 - f, paramInt2 + 0.25D, paramInt3 + 1 - f));
        }
        if (this.a == de.b) {
            localList = paramep.a(jz.class, dv.b(paramInt1 + f, paramInt2, paramInt3 + f, paramInt1 + 1 - f, paramInt2 + 0.25D, paramInt3 + 1 - f));
        }
        if (this.a == de.c) {
            localList = paramep.a(fy.class, dv.b(paramInt1 + f, paramInt2, paramInt3 + f, paramInt1 + 1 - f, paramInt2 + 0.25D, paramInt3 + 1 - f));
        }
        if (localList.size() > 0) {
            j = 1;
        }

        // hMod: Allow pressure plate interaction to power redstone
        if (j != i) {
            j = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Object[]{new Block(this.bh, paramInt1, paramInt2, paramInt3), i, j});
            if (j > 0) {
                j = 1;
            }
        }

        if ((j != 0) && (i == 0)) {
            paramep.b(paramInt1, paramInt2, paramInt3, 1);
            paramep.g(paramInt1, paramInt2, paramInt3, this.bh);
            paramep.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
            paramep.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);

            paramep.a(paramInt1 + 0.5D, paramInt2 + 0.1D, paramInt3 + 0.5D, "random.click", 0.3F, 0.6F);
        }
        if ((j == 0) && (i != 0)) {
            paramep.b(paramInt1, paramInt2, paramInt3, 0);
            paramep.g(paramInt1, paramInt2, paramInt3, this.bh);
            paramep.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
            paramep.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);

            paramep.a(paramInt1 + 0.5D, paramInt2 + 0.1D, paramInt3 + 0.5D, "random.click", 0.3F, 0.5F);
        }

        if (j != 0) {
            paramep.h(paramInt1, paramInt2, paramInt3, this.bh);
        }
    }

    @Override
    public void b(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramep.b(paramInt1, paramInt2, paramInt3);
        if (i > 0) {
            paramep.g(paramInt1, paramInt2, paramInt3, this.bh);
            paramep.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
        }
        super.b(paramep, paramInt1, paramInt2, paramInt3);
    }

    @Override
    public void a(iu paramiu, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramiu.b(paramInt1, paramInt2, paramInt3) == 1 ? 1 : 0;

        float f = 0.0625F;
        if (i != 0) {
            a(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
        } else {
            a(f, 0.0F, f, 1.0F - f, 0.0625F, 1.0F - f);
        }
    }

    @Override
    public boolean b(iu paramiu, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        return paramiu.b(paramInt1, paramInt2, paramInt3) > 0;
    }

    @Override
    public boolean d(ep paramep, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramep.b(paramInt1, paramInt2, paramInt3) == 0) {
            return false;
        }
        return paramInt4 == 1;
    }

    @Override
    public boolean c() {
        return true;
    }
}
