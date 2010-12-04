public class gn extends fw {
    public gn(int paramInt) {
        super(paramInt);
        this.aY = 64;
        this.aX = 1;
    }

    @Override
    public boolean a(hn paramhn, fz paramfz, eq parameq, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramInt4 == 0) {
            return false;
        }
        if (!parameq.c(paramInt1, paramInt2, paramInt3).a()) {
            return false;
        }

        // hMod: Store block data clicked
        Block blockClicked = new Block(parameq.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3 );
        blockClicked.setFaceClicked(Block.Face.fromId( paramInt4 ));

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

        if (!gc.aD.a(parameq, paramInt1, paramInt2, paramInt3)) {
            return false;
        }

        // hMod: Now we can call itemUse :)
        Block blockPlaced = new Block((paramInt4 == 1 ? gc.aD.bh : gc.ai.bh), paramInt1, paramInt2, paramInt3);
        if (paramfz instanceof et && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((et)paramfz).getPlayer(), blockPlaced, blockClicked, new Item(paramhn))) {
            return false;
        }

        if (paramInt4 == 1) {
            parameq.b(paramInt1, paramInt2, paramInt3, gc.aD.bh, hh.b((paramfz.v + 180.0F) * 16.0F / 360.0F + 0.5D) & 0xF);
        } else {
            parameq.b(paramInt1, paramInt2, paramInt3, gc.aI.bh, paramInt4);
        }

        paramhn.a -= 1;
        jn localjn = (jn) parameq.k(paramInt1, paramInt2, paramInt3);
        if (localjn != null) {
            paramfz.a(localjn);
        }
        return true;
    }
}