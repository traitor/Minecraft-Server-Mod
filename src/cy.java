public class cy extends fw {
    public int a;

    public cy(int paramInt1, int paramInt2) {
        super(paramInt1);
        this.aX = 1;
        this.a = paramInt2;
    }

    @Override
    public boolean a(hn paramhn, fz paramfz, eq parameq, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = parameq.a(paramInt1, paramInt2, paramInt3);

        if (i == gc.aG.bh) {
            if (!parameq.z) {

                // hMod: placing of a mine cart
                Block block = new Block(i, paramInt1, paramInt2, paramInt3);
                Player player = ((et) paramfz).getPlayer();
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, block, block, new Item(paramhn))) {
                    return false;
                }

                parameq.a(new jo(parameq, paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, this.a));
            }
            paramhn.a -= 1;
            return true;
        }
        return false;
    }
}