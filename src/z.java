
import java.util.Random;

public class z extends cy {

    protected z(int paramInt, jt paramjt) {
        super(paramInt, paramjt);

        a(false);
        if (paramjt == jt.g) {
            a(true);
        }
    }

    public void b(em paramem, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        super.b(paramem, paramInt1, paramInt2, paramInt3, paramInt4);
        if (paramem.a(paramInt1, paramInt2, paramInt3) == this.bh) {
            i(paramem, paramInt1, paramInt2, paramInt3);
        }
    }

    private void i(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramem.b(paramInt1, paramInt2, paramInt3);
        paramem.i = true;
        paramem.a(paramInt1, paramInt2, paramInt3, this.bh - 1, i);
        paramem.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);
        paramem.h(paramInt1, paramInt2, paramInt3, this.bh - 1);
        paramem.i = false;
    }

    public void a(em paramem, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        if (this.bs == jt.g) {
            int i = paramRandom.nextInt(3);
            //prevent lava from putting something on fire.
            //can catch fire ?
            Block block = new Block(paramem.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
            block.setStatus(1);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, new Object[]{block , null})) {
                for (int j = 0; j < i; j++) {
                    paramInt1 += paramRandom.nextInt(3) - 1;
                    paramInt2++;
                    paramInt3 += paramRandom.nextInt(3) - 1;
                    int k = paramem.a(paramInt1, paramInt2, paramInt3);
                    if (k == 0) {
                        if ((j(paramem, paramInt1 - 1, paramInt2, paramInt3)) || (j(paramem, paramInt1 + 1, paramInt2, paramInt3)) || (j(paramem, paramInt1, paramInt2, paramInt3 - 1)) || (j(paramem, paramInt1, paramInt2, paramInt3 + 1)) || (j(paramem, paramInt1, paramInt2 - 1, paramInt3)) || (j(paramem, paramInt1, paramInt2 + 1, paramInt3))) {


                                paramem.d(paramInt1, paramInt2, paramInt3, fy.ar.bh);


                            return;
                        }
                    } else if (fy.m[k].bs.c()) {
                        return;
                    }
                }
            }
        }
    }

    private boolean j(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        return paramem.c(paramInt1, paramInt2, paramInt3).e();
    }
}
