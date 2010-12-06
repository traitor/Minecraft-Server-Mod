public class cw extends fw {
    private int a;

    public cw(int paramInt1, int paramInt2) {
        super(paramInt1);
        this.a = paramInt2;
    }

    @Override
    public boolean a(hn paramhn, fz paramfz, eq parameq, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramInt4 != 1) {
            return false;
        }

        int i = parameq.a(paramInt1, paramInt2, paramInt3);

        if (i == gc.aA.bh) {

            // hMod: Seeds
            Block blockClicked = new Block(i, paramInt1, paramInt2, paramInt3);
            blockClicked.setFaceClicked(Block.Face.fromId(paramInt4));
            Block blockPlaced = new Block(parameq.a(paramInt1, paramInt2 + 1, paramInt3), paramInt1, paramInt2 + 1, paramInt3);

            // Call the hook
            Player player = ((et) paramfz).getPlayer();
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(paramhn))) {
                return false;
            }

            parameq.d(paramInt1, paramInt2 + 1, paramInt3, this.a);
            paramhn.a -= 1;
            return true;
        }
        return false;
    }
}