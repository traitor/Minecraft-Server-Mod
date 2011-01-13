
public class iu extends hg {

    public iu(int paramInt) {
        super(paramInt);
    }

    public boolean a(jl paramjl, hl paramhl, fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        // hMod: Store block data clicked
        Block blockClicked = new Block(paramfv.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
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
        if (!paramfv.e(paramInt1, paramInt2, paramInt3)) {
            return false;
        }
        if (hr.av.a(paramfv, paramInt1, paramInt2, paramInt3)) {
            // hMod: Redstone dust hook!
            Block blockPlaced = new Block(Block.Type.RedstoneWire.getType(), paramInt1, paramInt2, paramInt3);
            Player player = ((fy) paramhl).getPlayer();
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(paramjl))) {
                return false;
            }

            paramjl.a -= 1;
            paramfv.e(paramInt1, paramInt2, paramInt3, hr.av.bi);
        }

        return true;
    }
}
