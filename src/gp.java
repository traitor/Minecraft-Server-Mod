public class gp extends fw {
    public gp(int paramInt) {
        super(paramInt);
        this.aX = 1;
    }

    @Override
    public hn a(hn paramhn, eq parameq, fz paramfz) {
        float f1 = 1.0F;

        float f2 = paramfz.y + (paramfz.w - paramfz.y) * f1;
        float f3 = paramfz.x + (paramfz.v - paramfz.x) * f1;

        double d1 = paramfz.m + (paramfz.p - paramfz.m) * f1;
        double d2 = paramfz.n + (paramfz.q - paramfz.n) * f1 + 1.62D - paramfz.H;
        double d3 = paramfz.o + (paramfz.r - paramfz.o) * f1;

        bd localbd1 = bd.b(d1, d2, d3);

        float f4 = hh.b(-f3 * 0.01745329F - 3.141593F);
        float f5 = hh.a(-f3 * 0.01745329F - 3.141593F);
        float f6 = -hh.b(-f2 * 0.01745329F);
        float f7 = hh.a(-f2 * 0.01745329F);

        float f8 = f5 * f6;
        float f9 = f7;
        float f10 = f4 * f6;

        double d4 = 5.0D;
        bd localbd2 = localbd1.c(f8 * d4, f9 * d4, f10 * d4);
        gq localgq = parameq.a(localbd1, localbd2, true);
        if (localgq == null) {
            return paramhn;
        }

        if (localgq.a == 0) {
            int i = localgq.b;
            int j = localgq.c;
            int k = localgq.d;

            if (!parameq.z) {
                // hMod: placing of a boat
                Block blockClicked = new Block(parameq.a(i, j, k), i, j, k );
                blockClicked.setFaceClicked(Block.Face.fromId(localgq.e));
                Block blockPlaced  = new Block(0, i, j, k );

                // hMod: Call hook
                if (paramfz instanceof et && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((et) paramfz).getPlayer(), blockPlaced, blockClicked, new Item(paramhn))) {
                    return paramhn;
                }

                parameq.a(new fm(parameq, i + 0.5F, j + 1.5F, k + 0.5F));
            }
            paramhn.a -= 1;
        }

        return paramhn;
    }
}