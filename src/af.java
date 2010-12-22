
import java.util.Random;

public class af extends dn {

    protected af(int paramInt, la paramkz) {
        super(paramInt, paramkz);

        a(false);
        if (paramkz == la.g) {
            a(true);
        }
    }

    public void b(ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        super.b(paramff, paramInt1, paramInt2, paramInt3, paramInt4);
        if (paramff.a(paramInt1, paramInt2, paramInt3) == this.bh) {
            i(paramff, paramInt1, paramInt2, paramInt3);
        }
    }

    private void i(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramff.b(paramInt1, paramInt2, paramInt3);
        paramff.i = true;
        paramff.a(paramInt1, paramInt2, paramInt3, this.bh - 1, i);
        paramff.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);
        paramff.h(paramInt1, paramInt2, paramInt3, this.bh - 1);
        paramff.i = false;
    }

    public void a(ff paramff, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        if (this.bs == la.g) {
            int i = paramRandom.nextInt(3);
            // hMod: prevent lava from putting something on fire.
            Block block = new Block(paramff.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
            block.setStatus(1);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, block, null)) {
                for (int j = 0; j < i; j++) {
                    paramInt1 += paramRandom.nextInt(3) - 1;
                    paramInt2++;
                    paramInt3 += paramRandom.nextInt(3) - 1;
                    int k = paramff.a(paramInt1, paramInt2, paramInt3);
                    if (k == 0) {
                        if ((j(paramff, paramInt1 - 1, paramInt2, paramInt3)) || (j(paramff, paramInt1 + 1, paramInt2, paramInt3)) || (j(paramff, paramInt1, paramInt2, paramInt3 - 1)) || (j(paramff, paramInt1, paramInt2, paramInt3 + 1)) || (j(paramff, paramInt1, paramInt2 - 1, paramInt3)) || (j(paramff, paramInt1, paramInt2 + 1, paramInt3))) {
                            paramff.d(paramInt1, paramInt2, paramInt3, gv.ar.bh);
                            return;
                        }
                    } else if (gv.m[k].bs.c()) {
                        return;
                    }
                }
            }
        }
    }

    private boolean j(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        return paramff.c(paramInt1, paramInt2, paramInt3).e();
    }
}
