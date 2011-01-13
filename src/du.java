
public class du extends hg {

    private int a;

    public du(int paramInt1, int paramInt2) {
        super(paramInt1);
        a = paramInt2;
    }

    public boolean a(jl paramjl, hl paramhl, fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramInt4 != 1) {
            return false;
        }

        int i = paramfv.a(paramInt1, paramInt2, paramInt3);

        if ((i == hr.aA.bi) && (paramfv.e(paramInt1, paramInt2 + 1, paramInt3))) {
            // hMod: Seeds
            Block blockClicked = new Block(i, paramInt1, paramInt2, paramInt3);
            blockClicked.setFaceClicked(Block.Face.fromId(paramInt4));
            Block blockPlaced = new Block(paramfv.a(paramInt1, paramInt2 + 1, paramInt3), paramInt1, paramInt2 + 1, paramInt3);

            // Call the hook
            Player player = ((fy) paramhl).getPlayer();
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(paramjl))) {
                return false;
            }

            paramfv.e(paramInt1, paramInt2 + 1, paramInt3, a);
            paramjl.a -= 1;
            return true;
        }
        return false;
    }
}
