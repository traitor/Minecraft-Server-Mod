public class OItemDoor extends OItem {

    private OMaterial a;

    public OItemDoor(int paramInt, OMaterial paramOMaterial) {
        super(paramInt);
        a = paramOMaterial;
        be = 1;
    }

    @Override
    public boolean a(OItemStack paramOItemStack, OEntityPlayer paramOEntityPlayer, OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramInt4 != 1)
            return false;
        paramInt2++;
        OBlock localOBlock;
        if (a == OMaterial.c)
            localOBlock = OBlock.aE;
        else
            localOBlock = OBlock.aL;
        if (!localOBlock.a(paramOWorld, paramInt1, paramInt2, paramInt3))
            return false;

        // hMod: Doors onItemUse
        Block blockClicked = new Block(paramOWorld.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
        blockClicked.setFaceClicked(Block.Face.fromId(paramInt4));
        Block blockPlaced = new Block(paramOWorld.a(paramInt1, paramInt2 + 1, paramInt3), paramInt1, paramInt2 + 1, paramInt3);

        // Call the hook
        if (paramOEntityPlayer instanceof OEntityPlayerMP) {
            Player player = ((OEntityPlayerMP) paramOEntityPlayer).getPlayer();
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(paramOItemStack)))
                return false;
        }

        int i = OMathHelper.b((paramOEntityPlayer.aQ + 180.0F) * 4.0F / 360.0F - 0.5D) & 0x3;

        int j = 0;
        int k = 0;
        if (i == 0)
            k = 1;
        if (i == 1)
            j = -1;
        if (i == 2)
            k = -1;
        if (i == 3)
            j = 1;

        int m = (paramOWorld.d(paramInt1 - j, paramInt2, paramInt3 - k) ? 1 : 0) + (paramOWorld.d(paramInt1 - j, paramInt2 + 1, paramInt3 - k) ? 1 : 0);
        int n = (paramOWorld.d(paramInt1 + j, paramInt2, paramInt3 + k) ? 1 : 0) + (paramOWorld.d(paramInt1 + j, paramInt2 + 1, paramInt3 + k) ? 1 : 0);

        int i1 = (paramOWorld.a(paramInt1 - j, paramInt2, paramInt3 - k) == localOBlock.bl) || (paramOWorld.a(paramInt1 - j, paramInt2 + 1, paramInt3 - k) == localOBlock.bl) ? 1 : 0;
        int i2 = (paramOWorld.a(paramInt1 + j, paramInt2, paramInt3 + k) == localOBlock.bl) || (paramOWorld.a(paramInt1 + j, paramInt2 + 1, paramInt3 + k) == localOBlock.bl) ? 1 : 0;

        int i3 = 0;
        if ((i1 != 0) && (i2 == 0))
            i3 = 1;
        else if (n > m)
            i3 = 1;

        if (i3 != 0) {
            i = i - 1 & 0x3;
            i += 4;
        }

        paramOWorld.e(paramInt1, paramInt2, paramInt3, localOBlock.bl);
        paramOWorld.c(paramInt1, paramInt2, paramInt3, i);

        paramOWorld.e(paramInt1, paramInt2 + 1, paramInt3, localOBlock.bl);
        paramOWorld.c(paramInt1, paramInt2 + 1, paramInt3, i + 8);

        paramOItemStack.a -= 1;
        return true;
    }
}
