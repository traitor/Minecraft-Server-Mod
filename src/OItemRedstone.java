public class OItemRedstone extends OItem {

    public OItemRedstone(int paramInt) {
        super(paramInt);
    }

    public boolean a(OItemStack paramOItemStack, OEntityPlayer paramOEntityPlayer, OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        // hMod: Store block data clicked
        Block blockClicked = new Block(paramOWorld.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
        blockClicked.setFaceClicked(Block.Face.fromId(paramInt4));

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
        if (!paramOWorld.e(paramInt1, paramInt2, paramInt3)) {
            return false;
        }
        if (OBlock.av.a(paramOWorld, paramInt1, paramInt2, paramInt3)) {
            // hMod: Redstone dust hook!
            Block blockPlaced = new Block(Block.Type.RedstoneWire.getType(), paramInt1, paramInt2, paramInt3);
            Player player = ((OEntityPlayerMP) paramOEntityPlayer).getPlayer();
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(paramOItemStack)))
                return false;

            paramOItemStack.a -= 1;
            paramOWorld.e(paramInt1, paramInt2, paramInt3, OBlock.av.bk);
        }

        return true;
    }
}
