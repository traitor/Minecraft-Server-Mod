
import java.util.Random;

public class gl extends gv {

    public static boolean a = false;

    public gl(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, la.m);
    }

    public void e(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        // hMod: Physics
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Block(bh, paramInt1, paramInt2, paramInt3), true)) {
            paramff.h(paramInt1, paramInt2, paramInt3, this.bh);
        }
    }

    public void b(ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        // hMod: Physics
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Block(bh, paramInt1, paramInt2, paramInt3), false)) {
            paramff.h(paramInt1, paramInt2, paramInt3, this.bh);
        }
    }

    public void a(ff paramff, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        h(paramff, paramInt1, paramInt2, paramInt3);
    }

    private void h(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramInt1;
        int j = paramInt2;
        int k = paramInt3;
        if ((g(paramff, i, j - 1, k)) && (j >= 0)) {
            jc localjb = new jc(paramff, paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, this.bh);
            if (a) {
                while (!localjb.G) {
                    localjb.b_();
                }
            }
            paramff.a(localjb);
        }
    }

    public int b() {
        return 3;
    }

    public static boolean g(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramff.a(paramInt1, paramInt2, paramInt3);
        if (i == 0) {
            return true;
        }
        if (i == gv.ar.bh) {
            return true;
        }
        la localkz = gv.m[i].bs;
        if (localkz == la.f) {
            return true;
        }
        return localkz == la.g;
    }
}
