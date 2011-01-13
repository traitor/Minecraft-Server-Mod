
import java.util.Random;

public class aj extends dz {

    protected aj(int paramInt, mh parammh) {
        super(paramInt, parammh);

        a(false);
        if (parammh == mh.g) {
            a(true);
        }
    }

    public void b(fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        super.b(paramfv, paramInt1, paramInt2, paramInt3, paramInt4);
        if (paramfv.a(paramInt1, paramInt2, paramInt3) == bi) {
            i(paramfv, paramInt1, paramInt2, paramInt3);
        }
    }

    private void i(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramfv.b(paramInt1, paramInt2, paramInt3);
        paramfv.i = true;
        paramfv.a(paramInt1, paramInt2, paramInt3, bi - 1, i);
        paramfv.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);
        paramfv.i(paramInt1, paramInt2, paramInt3, bi - 1);
        paramfv.i = false;
    }

    public void a(fv paramfv, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        if (bt == mh.g) {
            int i = paramRandom.nextInt(3);
            // hMod: prevent lava from putting something on fire.
            Block block = new Block(paramfv.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
            block.setStatus(1);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, block, null)) {
                for (int j = 0; j < i; j++) {
                    paramInt1 += paramRandom.nextInt(3) - 1;
                    paramInt2++;
                    paramInt3 += paramRandom.nextInt(3) - 1;
                    int k = paramfv.a(paramInt1, paramInt2, paramInt3);
                    if (k == 0) {
                        if ((j(paramfv, paramInt1 - 1, paramInt2, paramInt3)) || (j(paramfv, paramInt1 + 1, paramInt2, paramInt3)) || (j(paramfv, paramInt1, paramInt2, paramInt3 - 1)) || (j(paramfv, paramInt1, paramInt2, paramInt3 + 1)) || (j(paramfv, paramInt1, paramInt2 - 1, paramInt3)) || (j(paramfv, paramInt1, paramInt2 + 1, paramInt3))) {
                            paramfv.e(paramInt1, paramInt2, paramInt3, hr.ar.bi);
                            return;
                        }
                    } else if (hr.m[k].bt.c()) {
                        return;
                    }
                }
            }
        }
    }

    private boolean j(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        return paramfv.c(paramInt1, paramInt2, paramInt3).e();
    }
}
