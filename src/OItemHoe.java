public class OItemHoe extends OItem {

    public OItemHoe(int paramInt, OEnumToolMaterial paramOEnumToolMaterial) {
        super(paramInt);
        bb = 1;
        bc = paramOEnumToolMaterial.a();
    }

    @Override
    public boolean a(OItemStack paramOItemStack, OEntityPlayer paramOEntityPlayer, OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = paramOWorld.a(paramInt1, paramInt2, paramInt3);
        OMaterial localOMaterial = paramOWorld.c(paramInt1, paramInt2 + 1, paramInt3);

        if (((!localOMaterial.a()) && (i == OBlock.u.bi)) || (i == OBlock.v.bi)) {
            // hMod: Hoes
            Block blockClicked = new Block(i, paramInt1, paramInt2, paramInt3);
            blockClicked.setFaceClicked(Block.Face.fromId(paramInt4));
            Block blockPlaced = new Block(paramOWorld.a(paramInt1, paramInt2 + 1, paramInt3), paramInt1, paramInt2 + 1, paramInt3);

            // Call the hook
            if (paramOEntityPlayer instanceof OEntityPlayerMP) {
                Player player = ((OEntityPlayerMP) paramOEntityPlayer).getPlayer();
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(paramOItemStack)))
                    return false;
            }

            OBlock localOBlock = OBlock.aA;
            paramOWorld.a(paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, localOBlock.br.c(), (localOBlock.br.a() + 1.0F) / 2.0F, localOBlock.br.b() * 0.8F);

            if (paramOWorld.z)
                return true;
            paramOWorld.e(paramInt1, paramInt2, paramInt3, localOBlock.bi);
            paramOItemStack.b(1);

            if ((paramOWorld.l.nextInt(8) == 0) && (i == OBlock.u.bi)) {
                int j = 1;
                for (int k = 0; k < j; k++) {
                    float f1 = 0.7F;
                    float f2 = paramOWorld.l.nextFloat() * f1 + (1.0F - f1) * 0.5F;
                    float f3 = 1.2F;
                    float f4 = paramOWorld.l.nextFloat() * f1 + (1.0F - f1) * 0.5F;
                    OEntityItem localOEntityItem = new OEntityItem(paramOWorld, paramInt1 + f2, paramInt2 + f3, paramInt3 + f4, new OItemStack(OItem.Q));
                    localOEntityItem.c = 10;
                    paramOWorld.a(localOEntityItem);
                }
            }

            return true;
        }

        return false;
    }
}
