public class hc extends fw {
    public hc(int paramInt) {
        super(paramInt);
    }

    @Override
    public boolean a(hn paramhn, fz paramfz, eq parameq, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {

        // hMod: Store block data clicked
        Block blockClicked = new Block(parameq.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3 );
        blockClicked.setFaceClicked(Block.Face.fromId( paramInt4 ));

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
        if (parameq.a(paramInt1, paramInt2, paramInt3) != 0) {
            return false;
        }

        if (gc.av.a(parameq, paramInt1, paramInt2, paramInt3)) {

            // hMod: Redstone dust hook!
            Block blockPlaced = new Block(Block.Type.RedstoneWire.getType(), paramInt1, paramInt2, paramInt3);
            Player player = ((et) paramfz).getPlayer();
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(paramhn))) {
                return false;
            }

            paramhn.a -= 1;
            parameq.d(paramInt1, paramInt2, paramInt3, gc.av.bh);
        }

        return true;
    }
}