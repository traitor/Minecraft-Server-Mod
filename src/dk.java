
public class dk extends gm {

    public int a;

    public dk(int paramInt1, int paramInt2) {
        super(paramInt1);
        this.aX = 1;
        this.a = paramInt2;
    }

    public boolean a(il paramik, gq paramgp, ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = paramff.a(paramInt1, paramInt2, paramInt3);

        if (i == gv.aG.bh) {
            if (!paramff.z) {
                // hMod: placing of a mine cart
                Block block = new Block(i, paramInt1, paramInt2, paramInt3);
                Player player = ((fi) paramgp).getPlayer();
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, block, block, new Item(paramik))) {
                    return false;
                }
                
                paramff.a(new kq(paramff, paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, this.a));
            }
            paramik.a -= 1;
            return true;
        }
        return false;
    }
}
