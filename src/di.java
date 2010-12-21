
public class di extends gl {

    private int a;

    public di(int paramInt1, int paramInt2) {
        super(paramInt1);
        this.a = paramInt2;
    }

    public boolean a(ik paramik, gp paramgp, ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramInt4 != 1) {
            return false;
        }

        int i = paramff.a(paramInt1, paramInt2, paramInt3);

        if (i == gu.aA.bh) {
            // hMod: Seeds
            Block blockClicked = new Block(i, paramInt1, paramInt2, paramInt3);
            blockClicked.setFaceClicked(Block.Face.fromId(paramInt4));
            Block blockPlaced = new Block(paramff.a(paramInt1, paramInt2 + 1, paramInt3), paramInt1, paramInt2 + 1, paramInt3);

            // Call the hook
            Player player = ((fi) paramgp).getPlayer();
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(paramik))) {
                return false;
            }
            
            paramff.d(paramInt1, paramInt2 + 1, paramInt3, this.a);
            paramik.a -= 1;
            return true;
        }
        return false;
    }
}
