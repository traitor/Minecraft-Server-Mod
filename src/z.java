import java.util.Random;

public class z extends db {
    protected z(int paramInt, jy paramjy) {
        super(paramInt, paramjy);

        a(false);
        if (paramjy == jy.g) {
            a(true);
        }
    }

    @Override
    public void b(eq parameq, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        super.b(parameq, paramInt1, paramInt2, paramInt3, paramInt4);
        if (parameq.a(paramInt1, paramInt2, paramInt3) == this.bh) {
            i(parameq, paramInt1, paramInt2, paramInt3);
        }
    }

    private void i(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        int i = parameq.b(paramInt1, paramInt2, paramInt3);
        parameq.i = true;
        parameq.a(paramInt1, paramInt2, paramInt3, this.bh - 1, i);
        parameq.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);
        parameq.h(paramInt1, paramInt2, paramInt3, this.bh - 1);
        parameq.i = false;
    }

    @Override
    public void a(eq parameq, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        if (this.bs == jy.g) {
            int i = paramRandom.nextInt(3);
            // hMod: prevent lava from putting something on fire.
            Block block = new Block(parameq.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
            block.setStatus(1);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, block, null)) {
                for (int j = 0; j < i; j++) {
                    paramInt1 += paramRandom.nextInt(3) - 1;
                    paramInt2++;
                    paramInt3 += paramRandom.nextInt(3) - 1;
                    int k = parameq.a(paramInt1, paramInt2, paramInt3);
                    if (k == 0) {
                        if ((j(parameq, paramInt1 - 1, paramInt2, paramInt3)) || (j(parameq, paramInt1 + 1, paramInt2, paramInt3)) || (j(parameq, paramInt1, paramInt2, paramInt3 - 1)) || (j(parameq, paramInt1, paramInt2, paramInt3 + 1)) || (j(parameq, paramInt1, paramInt2 - 1, paramInt3)) || (j(parameq, paramInt1, paramInt2 + 1, paramInt3))) {
                            parameq.d(paramInt1, paramInt2, paramInt3, gc.ar.bh);
                            return;
                        }
                    } else if (gc.m[k].bs.c()) {
                        return;
                    }
                }
            }
        }
    }

    private boolean j(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        return parameq.c(paramInt1, paramInt2, paramInt3).e();
    }
}
