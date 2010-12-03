public class jd extends fv {

    public jd(int paramInt) {
        super(paramInt);
        aX = 1;
        aY = 64;
    }

    @Override
    public boolean a(hm paramhm, fy paramfy, ep paramep, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
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

        int i = paramep.a(paramInt1, paramInt2, paramInt3);

        if (i == 0) {
            // hMod: Hook to control ignites -- TODO This needs some looking over!
            Block block = new Block(paramInt1, paramInt2, paramInt3, 0);
            block.setStatus(2);
            // fire lighter check
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, new Object[]{block, ((es) paramfy).getPlayer()})) {
                // create fire effects ? (probably this)
                paramep.a(paramInt1 + 0.5D, paramInt2 + 0.5D, paramInt3 + 0.5D, "fire.ignite", 1.0F, b.nextFloat() * 0.4F + 0.8F);
                // set the block type to fire.
                paramep.d(paramInt1, paramInt2, paramInt3, gb.ar.bh);
            } else {
                // set block to glass.
                paramep.d(paramInt1, paramInt2, paramInt3, 20);
                // set block to air.
                paramep.d(paramInt1, paramInt2, paramInt3, 0);
            }
        }

        paramhm.a(1);
        return true;
    }
}
