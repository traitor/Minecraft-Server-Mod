import java.util.Random;

public class fu extends gb {
    public static boolean a = false;

    public fu(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, jx.m);
    }

    @Override
    public void e(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        // hMod: Physics
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Object[]{new Block(bh, paramInt1, paramInt2, paramInt3), true})) {
            paramep.h(paramInt1, paramInt2, paramInt3, this.bh);
        }
    }

    @Override
    public void b(ep paramep, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        // hMod: Physics
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Object[]{new Block(bh, paramInt1, paramInt2, paramInt3), false})) {
            paramep.h(paramInt1, paramInt2, paramInt3, this.bh);
        }
    }

    @Override
    public void a(ep paramep, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        h(paramep, paramInt1, paramInt2, paramInt3);
    }

    private void h(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramInt1;
        int j = paramInt2;
        int k = paramInt3;
        if ((g(paramep, i, j - 1, k)) && (j >= 0)) {
            ic localic = new ic(paramep, paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, this.bh);
            if (a) {
                while (!localic.G) {
                    localic.b_();
                }
            }
            paramep.a(localic);
        }
    }

    @Override
    public int b() {
        return 3;
    }

    public static boolean g(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramep.a(paramInt1, paramInt2, paramInt3);
        if (i == 0) {
            return true;
        }
        if (i == gb.ar.bh) {
            return true;
        }
        jx localjx = gb.m[i].bs;
        if (localjx == jx.f) {
            return true;
        }
        return localjx == jx.g;
    }
}
