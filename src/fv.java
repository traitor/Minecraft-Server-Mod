import java.util.Random;

public class fv extends gc {
    public static boolean a = false;

    public fv(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, jy.m);
    }

    @Override
    public void e(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        // hMod: Physics
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Block(bh, paramInt1, paramInt2, paramInt3), true)) {
            parameq.h(paramInt1, paramInt2, paramInt3, this.bh);
        }
    }

    @Override
    public void b(eq parameq, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        // hMod: Physics
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Block(bh, paramInt1, paramInt2, paramInt3), false)) {
            parameq.h(paramInt1, paramInt2, paramInt3, this.bh);
        }
    }

    @Override
    public void a(eq parameq, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        h(parameq, paramInt1, paramInt2, paramInt3);
    }

    private void h(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramInt1;
        int j = paramInt2;
        int k = paramInt3;
        if ((g(parameq, i, j - 1, k)) && (j >= 0)) {
            id localid = new id(parameq, paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, this.bh);
            if (a) {
                while (!localid.G) {
                    localid.b_();
                }
            }
            parameq.a(localid);
        }
    }

    @Override
    public int b() {
        return 3;
    }

    public static boolean g(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        int i = parameq.a(paramInt1, paramInt2, paramInt3);
        if (i == 0) {
            return true;
        }
        if (i == gc.ar.bh) {
            return true;
        }
        jy localjy = gc.m[i].bs;
        if (localjy == jy.f) {
            return true;
        }
        return localjy == jy.g;
    }
}
