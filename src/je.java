import java.util.Random;

public class je extends fw {
    public je(int paramInt) {
        super(paramInt);
        this.aX = 1;
        this.aY = 64;
    }

    @Override
    public boolean a(hn paramhn, fz paramfz, eq parameq, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
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

        int i = parameq.a(paramInt1, paramInt2, paramInt3);

        if (i == 0) {
            // hMod: Hook to control ignites -- TODO This needs some looking over!
            Block block = new Block(0, paramInt1, paramInt2, paramInt3);
            block.setStatus(2);
            // fire lighter check
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, block, ((et) paramfz).getPlayer())) {
                // create fire effects ? (probably this)
                parameq.a(paramInt1 + 0.5D, paramInt2 + 0.5D, paramInt3 + 0.5D, "fire.ignite", 1.0F, b.nextFloat() * 0.4F + 0.8F);
                // set the block type to fire.
                parameq.d(paramInt1, paramInt2, paramInt3, gc.ar.bh);
            } else {
                // set block to glass.
                parameq.d(paramInt1, paramInt2, paramInt3, 20);
                // set block to air.
                parameq.d(paramInt1, paramInt2, paramInt3, 0);
            }
        }

        paramhn.a(1);
        return true;
    }
}
