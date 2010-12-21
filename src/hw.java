
public class hw extends gl {

    public hw(int paramInt) {
        super(paramInt);
    }

    public boolean a(ik paramik, gp paramgp, ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {

        // hMod: Store block data clicked
        Block blockClicked = new Block(paramff.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3 );
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
        if (!paramff.e(paramInt1, paramInt2, paramInt3)) {
            return false;
        }
        if (gu.av.a(paramff, paramInt1, paramInt2, paramInt3)) {
            // hMod: Redstone dust hook!
            Block blockPlaced = new Block(Block.Type.RedstoneWire.getType(), paramInt1, paramInt2, paramInt3);
            Player player = ((fi) paramgp).getPlayer();
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(paramik))) {
                return false;
            }
            
            paramik.a -= 1;
            paramff.d(paramInt1, paramInt2, paramInt3, gu.av.bh);
        }

        return true;
    }
}
