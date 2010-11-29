
import java.util.Random;

public class ft extends ga { // Sand block (gravel inherits)

    public static boolean a = false;

    public ft(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, jw.m);
    }

    public void e(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Object[]{new Block(bh, paramInt1, paramInt2, paramInt3), true})) {
            parameo.h(paramInt1, paramInt2, paramInt3, this.bh);
        }
    }

    public void b(eo parameo, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Object[]{new Block(bh, paramInt1, paramInt2, paramInt3), false})) {
            parameo.h(paramInt1, paramInt2, paramInt3, this.bh);
        }
    }

    public void a(eo parameo, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        h(parameo, paramInt1, paramInt2, paramInt3);
    }

    private void h(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramInt1;
        int j = paramInt2;
        int k = paramInt3;
        if ((g(parameo, i, j - 1, k)) && (j >= 0)) {
            ib localib = new ib(parameo, paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, this.bh);
            if (a) {
                while (!localib.G) {
                    localib.b_();
                }
            }
            parameo.a(localib);
        }
    }

    public int b() {
        return 3;
    }

    public static boolean g(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        int i = parameo.a(paramInt1, paramInt2, paramInt3);
        if (i == 0) {
            return true;
        }
        if (i == ga.ar.bh) {
            return true;
        }
        jw localjw = ga.m[i].bs;
        if (localjw == jw.f) {
            return true;
        }
        return localjw == jw.g;
    }
}
