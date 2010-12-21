
public class hi extends gl {

    public hi(int paramInt) {
        super(paramInt);
        this.aX = 1;
    }

    public ik a(ik paramik, ff paramff, gp paramgp) {
        float f1 = 1.0F;

        float f2 = paramgp.y + (paramgp.w - paramgp.y) * f1;
        float f3 = paramgp.x + (paramgp.v - paramgp.x) * f1;

        double d1 = paramgp.m + (paramgp.p - paramgp.m) * f1;
        double d2 = paramgp.n + (paramgp.q - paramgp.n) * f1 + 1.62D - paramgp.H;
        double d3 = paramgp.o + (paramgp.r - paramgp.o) * f1;

        bn localbn1 = bn.b(d1, d2, d3);

        float f4 = ib.b(-f3 * 0.01745329F - 3.141593F);
        float f5 = ib.a(-f3 * 0.01745329F - 3.141593F);
        float f6 = -ib.b(-f2 * 0.01745329F);
        float f7 = ib.a(-f2 * 0.01745329F);

        float f8 = f5 * f6;
        float f9 = f7;
        float f10 = f4 * f6;

        double d4 = 5.0D;
        bn localbn2 = localbn1.c(f8 * d4, f9 * d4, f10 * d4);
        hj localhj = paramff.a(localbn1, localbn2, true);
        if (localhj == null) {
            return paramik;
        }

        if (localhj.a == 0) {
            int i = localhj.b;
            int j = localhj.c;
            int k = localhj.d;

            if (!paramff.z) {
                // hMod: placing of a boat
                Block blockClicked = new Block(paramff.a(i, j, k), i, j, k );
                blockClicked.setFaceClicked(Block.Face.fromId(localhj.e));
                Block blockPlaced  = new Block(0, i, j, k );

                // hMod: Call hook
                if (paramgp instanceof fi && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((fi) paramgp).getPlayer(), blockPlaced, blockClicked, new Item(paramik))) {
                    return paramik;
                }
                
                paramff.a(new gb(paramff, i + 0.5F, j + 1.5F, k + 0.5F));
            }
            paramik.a -= 1;
        }

        return paramik;
    }
}
