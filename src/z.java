
import java.util.Random;

public class z extends cz {

    protected z(int paramInt, jw paramjw) {
        super(paramInt, paramjw);

        a(false);
        if (paramjw == jw.g) {
            a(true);
        }
    }

    public void b(eo parameo, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        super.b(parameo, paramInt1, paramInt2, paramInt3, paramInt4);
        if (parameo.a(paramInt1, paramInt2, paramInt3) == this.bh)
            i(parameo, paramInt1, paramInt2, paramInt3);
    }

    private void i(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        int i = parameo.b(paramInt1, paramInt2, paramInt3);
        parameo.i = true;
        parameo.a(paramInt1, paramInt2, paramInt3, this.bh - 1, i);
        parameo.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);
        parameo.h(paramInt1, paramInt2, paramInt3, this.bh - 1);
        parameo.i = false;
    }

    public void a(eo parameo, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        if (this.bs == jw.g) {
            int i = paramRandom.nextInt(3);
            // hMod: prevent lava from putting something on fire.
            Block block = new Block(parameo.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
            block.setStatus(1);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, new Object[]{block, null})) {
                for (int j = 0; j < i; j++) {
                    paramInt1 += paramRandom.nextInt(3) - 1;
                    paramInt2++;
                    paramInt3 += paramRandom.nextInt(3) - 1;
                    int k = parameo.a(paramInt1, paramInt2, paramInt3);
                    if (k == 0) {
                        if ((j(parameo, paramInt1 - 1, paramInt2, paramInt3)) || (j(parameo, paramInt1 + 1, paramInt2, paramInt3)) || (j(parameo, paramInt1, paramInt2, paramInt3 - 1)) || (j(parameo, paramInt1, paramInt2, paramInt3 + 1)) || (j(parameo, paramInt1, paramInt2 - 1, paramInt3)) || (j(parameo, paramInt1, paramInt2 + 1, paramInt3))) {
                            parameo.d(paramInt1, paramInt2, paramInt3, ga.ar.bh);
                            return;
                        }
                    } else if (ga.m[k].bs.c()) {
                        return;
                    }
                }
            }
        }
    }

    private boolean j(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        return parameo.c(paramInt1, paramInt2, paramInt3).e();
    }
}
