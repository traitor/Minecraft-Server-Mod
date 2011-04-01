public class OItemSeeds extends OItem {

    private int a;

    public OItemSeeds(int paramInt1, int paramInt2) {
        super(paramInt1);
        a = paramInt2;
    }

    @Override
    public boolean a(OItemStack paramOItemStack, OEntityPlayer paramOEntityPlayer, OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramInt4 != 1)
            return false;

        int i = paramOWorld.a(paramInt1, paramInt2, paramInt3);

        if ((i == OBlock.aA.bl) && (paramOWorld.e(paramInt1, paramInt2 + 1, paramInt3))) {
            // hMod: Seeds
            Block blockClicked = new Block(i, paramInt1, paramInt2, paramInt3);
            blockClicked.setFaceClicked(Block.Face.fromId(paramInt4));
            Block blockPlaced = new Block(paramOWorld.a(paramInt1, paramInt2 + 1, paramInt3), paramInt1, paramInt2 + 1, paramInt3);

            // Call the hook
            Player player = ((OEntityPlayerMP) paramOEntityPlayer).getPlayer();
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(paramOItemStack)))
                return false;

            paramOWorld.e(paramInt1, paramInt2 + 1, paramInt3, a);
            paramOItemStack.a -= 1;
            return true;
        }
        return false;
    }
}
