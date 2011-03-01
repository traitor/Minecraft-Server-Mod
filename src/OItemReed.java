public class OItemReed extends OItem {

    private int a;

    public OItemReed(int paramInt, OBlock paramOBlock) {
        super(paramInt);
        a = paramOBlock.bk;
    }

    public boolean a(OItemStack paramOItemStack, OEntityPlayer paramOEntityPlayer, OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        // hMod: Store blockClicked
        int clicked = paramOWorld.a(paramInt1, paramInt2, paramInt3);
        Block blockClicked = new Block(clicked, paramInt1, paramInt2, paramInt3);

        if (clicked == OBlock.aS.bk) {
            paramInt4 = 0;
        } else {
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
        }

        if (paramOItemStack.a == 0) {
            return false;
        }

        if (paramOWorld.a(a, paramInt1, paramInt2, paramInt3, false)) {
            // hMod: Reed placement
            Block blockPlaced = new Block(paramOWorld.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
            blockClicked.setFaceClicked(Block.Face.fromId(paramInt4));
            Player player = ((OEntityPlayerMP) paramOEntityPlayer).getPlayer();

            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(paramOItemStack)))
                return false;

            OBlock localOBlock = OBlock.m[a];
            if (paramOWorld.e(paramInt1, paramInt2, paramInt3, a)) {
                OBlock.m[a].d(paramOWorld, paramInt1, paramInt2, paramInt3, paramInt4);
                OBlock.m[a].a(paramOWorld, paramInt1, paramInt2, paramInt3, (OEntityLiving) paramOEntityPlayer);
                paramOWorld.a(paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, localOBlock.bt.c(), (localOBlock.bt.a() + 1.0F) / 2.0F, localOBlock.bt.b() * 0.8F);
                paramOItemStack.a -= 1;
            }

        }

        return true;
    }
}
