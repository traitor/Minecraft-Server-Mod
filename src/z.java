import java.util.Random;

public class z extends da {

    protected z(int paramInt, jx paramjx) {
        super(paramInt, paramjx);

        a(false);
        if (paramjx == jx.g) {
            a(true);
        }
    }

    @Override
    public void b(ep paramep, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        super.b(paramep, paramInt1, paramInt2, paramInt3, paramInt4);
        if (paramep.a(paramInt1, paramInt2, paramInt3) == bh) {
            i(paramep, paramInt1, paramInt2, paramInt3);
        }
    }

    private void i(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramep.b(paramInt1, paramInt2, paramInt3);
        paramep.i = true;
        paramep.a(paramInt1, paramInt2, paramInt3, bh - 1, i);
        paramep.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);
        paramep.h(paramInt1, paramInt2, paramInt3, bh - 1);
        paramep.i = false;
    }

    @Override
    public void a(ep paramep, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        if (bs == jx.g) {
            int i = paramRandom.nextInt(3);
            // hMod: prevent lava from putting something on fire.
            Block block = new Block(paramep.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
            block.setStatus(1);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, new Object[]{block, null})) {
                for (int j = 0; j < i; j++) {
                    paramInt1 += paramRandom.nextInt(3) - 1;
                    paramInt2++;
                    paramInt3 += paramRandom.nextInt(3) - 1;
                    int k = paramep.a(paramInt1, paramInt2, paramInt3);
                    if (k == 0) {
                        if ((j(paramep, paramInt1 - 1, paramInt2, paramInt3)) || (j(paramep, paramInt1 + 1, paramInt2, paramInt3)) || (j(paramep, paramInt1, paramInt2, paramInt3 - 1)) || (j(paramep, paramInt1, paramInt2, paramInt3 + 1)) || (j(paramep, paramInt1, paramInt2 - 1, paramInt3)) || (j(paramep, paramInt1, paramInt2 + 1, paramInt3))) {
                            paramep.d(paramInt1, paramInt2, paramInt3, gb.ar.bh);
                            return;
                        }
                    } else if (gb.m[k].bs.c()) {
                        return;
                    }
                }
            }
        }
    }

    private boolean j(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        return paramep.c(paramInt1, paramInt2, paramInt3).e();
    }
}
