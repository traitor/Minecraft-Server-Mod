
import java.util.Random;

public class iz extends fs {

    public iz(int paramInt) {
        super(paramInt);
        this.aX = 1;
        this.aY = 64;
    }

    public boolean a(hj paramhj, fv paramfv, em paramem, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {


        if (paramInt4 == 0) {
            paramInt2--;
        }
        if (paramInt4 == 1) {
            paramInt2++;
        }
        if (paramInt4 == 2) {
            paramInt3--;
        }
        if (paramInt4 == 3) {
            paramInt3++;
        }
        if (paramInt4 == 4) {
            paramInt1--;
        }
        if (paramInt4 == 5) {
            paramInt1++;
        }

        int i = paramem.a(paramInt1, paramInt2, paramInt3);

        if (i == 0) {
            Block block = new Block(paramInt1, paramInt2, paramInt3, 0);
            block.setStatus(2);
            //fire lighter check
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, new Object[]{block , (ep) paramfv })) {
                //create fire effects ? (probably this)
                paramem.a(paramInt1 + 0.5D, paramInt2 + 0.5D, paramInt3 + 0.5D, "fire.ignite", 1.0F, b.nextFloat() * 0.4F + 0.8F);
                //set the block type to fire.
                paramem.d(paramInt1, paramInt2, paramInt3, fy.ar.bh);
            } else {
                //set block to glass.
                // fix some random graphics bug ?
                paramem.d(paramInt1, paramInt2, paramInt3, 20);
                //set block to air.
                paramem.d(paramInt1, paramInt2, paramInt3, 0);
            }
        }

        paramhj.a(1);
        return true;
    }
}
