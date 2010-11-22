
import java.util.List;
import java.util.Random;

public class bd extends fy {

    private dc a;

    protected bd(int paramInt1, int paramInt2, dc paramdc) {
        super(paramInt1, paramInt2, jt.d);
        this.a = paramdc;
        a(true);

        float f = 0.0625F;
        a(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
    }

    public int b() {
        return 20;
    }

    public dt d(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    public boolean a() {
        return false;
    }

    public boolean a(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        return paramem.d(paramInt1, paramInt2 - 1, paramInt3);
    }

    public void e(em paramem, int paramInt1, int paramInt2, int paramInt3) {
    }

    public void b(em paramem, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = 0;

        if (!paramem.d(paramInt1, paramInt2 - 1, paramInt3)) {
            i = 1;
        }

        if (i != 0) {
            a_(paramem, paramInt1, paramInt2, paramInt3, paramem.b(paramInt1, paramInt2, paramInt3));
            paramem.d(paramInt1, paramInt2, paramInt3, 0);
        }
    }

    public void a(em paramem, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        if (paramem.b(paramInt1, paramInt2, paramInt3) == 0) {
            return;
        }

        g(paramem, paramInt1, paramInt2, paramInt3);
    }

    public void a(em paramem, int paramInt1, int paramInt2, int paramInt3, dw paramdw) {
        if (paramem.b(paramInt1, paramInt2, paramInt3) == 1) {
            return;
        }

        g(paramem, paramInt1, paramInt2, paramInt3);
    }

    private void g(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramem.b(paramInt1, paramInt2, paramInt3) == 1 ? 1 : 0;
        int j = 0;

        float f = 0.125F;
        List localList = null;
        if (this.a == dc.a) {
            localList = paramem.b(null, dt.b(paramInt1 + f, paramInt2, paramInt3 + f, paramInt1 + 1 - f, paramInt2 + 0.25D, paramInt3 + 1 - f));
        }
        if (this.a == dc.b) {
            localList = paramem.a(jv.class, dt.b(paramInt1 + f, paramInt2, paramInt3 + f, paramInt1 + 1 - f, paramInt2 + 0.25D, paramInt3 + 1 - f));
        }
        if (this.a == dc.c) {
            localList = paramem.a(fv.class, dt.b(paramInt1 + f, paramInt2, paramInt3 + f, paramInt1 + 1 - f, paramInt2 + 0.25D, paramInt3 + 1 - f));
        }
        if (localList.size() > 0) {
            j = 1;
        }

        if ((j != 0) && (i == 0)) {
            paramem.b(paramInt1, paramInt2, paramInt3, 1);
            paramem.g(paramInt1, paramInt2, paramInt3, this.bh);
            paramem.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
            paramem.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);

            paramem.a(paramInt1 + 0.5D, paramInt2 + 0.1D, paramInt3 + 0.5D, "random.click", 0.3F, 0.6F);
        }
        if ((j == 0) && (i != 0)) {
            paramem.b(paramInt1, paramInt2, paramInt3, 0);
            paramem.g(paramInt1, paramInt2, paramInt3, this.bh);
            paramem.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
            paramem.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);

            paramem.a(paramInt1 + 0.5D, paramInt2 + 0.1D, paramInt3 + 0.5D, "random.click", 0.3F, 0.5F);
        }

        if (i != j) {
            etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Object[]{new Block(this.bh, paramInt1, paramInt2, paramInt3), i, j});
        }

        if (j != 0) {
            paramem.h(paramInt1, paramInt2, paramInt3, this.bh);
        }
    }

    public void b(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramem.b(paramInt1, paramInt2, paramInt3);
        if (i > 0) {
            paramem.g(paramInt1, paramInt2, paramInt3, this.bh);
            paramem.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
        }
        super.b(paramem, paramInt1, paramInt2, paramInt3);
    }

    public void a(iq paramiq, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramiq.b(paramInt1, paramInt2, paramInt3) == 1 ? 1 : 0;

        float f = 0.0625F;
        if (i != 0) {
            a(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
        } else {
            a(f, 0.0F, f, 1.0F - f, 0.0625F, 1.0F - f);
        }
    }

    public boolean b(iq paramiq, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        return paramiq.b(paramInt1, paramInt2, paramInt3) > 0;
    }

    public boolean d(em paramem, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramem.b(paramInt1, paramInt2, paramInt3) == 0) {
            return false;
        }
        return paramInt4 == 1;
    }

    public boolean c() {
        return true;
    }
}
