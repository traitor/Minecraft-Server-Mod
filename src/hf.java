
import java.util.Random;

public class hf extends hr {

    public static boolean a = false;

    public hf(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, mh.m);
    }

    public void e(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        // hMod: Physics
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Block(bh, paramInt1, paramInt2, paramInt3), true)) {
            paramfv.i(paramInt1, paramInt2, paramInt3, bi);
        }
    }

    public void b(fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        // hMod: Physics
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Block(bh, paramInt1, paramInt2, paramInt3), false)) {

            paramfv.i(paramInt1, paramInt2, paramInt3, bi);
        }
    }

    public void a(fv paramfv, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        h(paramfv, paramInt1, paramInt2, paramInt3);
    }

    private void h(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramInt1;
        int j = paramInt2;
        int k = paramInt3;
        if ((g(paramfv, i, j - 1, k)) && (j >= 0)) {
            int m = 32;
            if ((a) || (!paramfv.a(paramInt1 - m, paramInt2 - m, paramInt3 - m, paramInt1 + m, paramInt2 + m, paramInt3 + m))) {
                paramfv.e(paramInt1, paramInt2, paramInt3, 0);
                while ((g(paramfv, paramInt1, paramInt2 - 1, paramInt3)) && (paramInt2 > 0)) {
                    paramInt2--;
                }
                if (paramInt2 > 0) {
                    paramfv.e(paramInt1, paramInt2, paramInt3, bi);
                }
            } else {
                kd localkd = new kd(paramfv, paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, bi);
                paramfv.a(localkd);
            }
        }
    }

    public int b() {
        return 3;
    }

    public static boolean g(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramfv.a(paramInt1, paramInt2, paramInt3);
        if (i == 0) {
            return true;
        }
        if (i == hr.ar.bi) {
            return true;
        }
        mh localmh = hr.m[i].bt;
        if (localmh == mh.f) {
            return true;
        }
        return localmh == mh.g;
    }
}
