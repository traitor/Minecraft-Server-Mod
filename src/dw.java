
public class dw extends hg {

    public int a;

    public dw(int paramInt1, int paramInt2) {
        super(paramInt1);
        bb = 1;
        a = paramInt2;
    }

    public boolean a(jl paramjl, hl paramhl, fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = paramfv.a(paramInt1, paramInt2, paramInt3);

        if (i == hr.aG.bi) {
            if (!paramfv.z) {
                // hMod: placing of a mine cart
                Block block = new Block(i, paramInt1, paramInt2, paramInt3);
                Player player = ((fy) paramhl).getPlayer();
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, block, block, new Item(paramjl))) {
                    return false;
                }

                paramfv.a(new lw(paramfv, paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, a));
            }
            paramjl.a -= 1;
            return true;
        }
        return false;
    }
}
