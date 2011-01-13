
public class ig extends hg {

    public ig(int paramInt) {
        super(paramInt);
        bb = 1;
    }

    public jl a(jl paramjl, fv paramfv, hl paramhl) {
        float f1 = 1.0F;

        float f2 = paramhl.y + (paramhl.w - paramhl.y) * f1;
        float f3 = paramhl.x + (paramhl.v - paramhl.x) * f1;

        double d1 = paramhl.m + (paramhl.p - paramhl.m) * f1;
        double d2 = paramhl.n + (paramhl.q - paramhl.n) * f1 + 1.62D - paramhl.H;
        double d3 = paramhl.o + (paramhl.r - paramhl.o) * f1;

        bu localbu1 = bu.b(d1, d2, d3);

        float f4 = iz.b(-f3 * 0.01745329F - 3.141593F);
        float f5 = iz.a(-f3 * 0.01745329F - 3.141593F);
        float f6 = -iz.b(-f2 * 0.01745329F);
        float f7 = iz.a(-f2 * 0.01745329F);

        float f8 = f5 * f6;
        float f9 = f7;
        float f10 = f4 * f6;

        double d4 = 5.0D;
        bu localbu2 = localbu1.c(f8 * d4, f9 * d4, f10 * d4);
        ih localih = paramfv.a(localbu1, localbu2, true);
        if (localih == null) {
            return paramjl;
        }

        if (localih.a == jr.a) {
            int i = localih.b;
            int j = localih.c;
            int k = localih.d;

            if (!paramfv.z) {
                // hMod: placing of a boat
                Block blockClicked = new Block(paramfv.a(i, j, k), i, j, k);
                blockClicked.setFaceClicked(Block.Face.fromId(localih.e));
                Block blockPlaced = new Block(0, i, j, k);
                // hMod: Call hook
                if (paramhl instanceof fy && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((fy) paramhl).getPlayer(), blockPlaced, blockClicked, new Item(paramjl))) {
                    return paramjl;
                }
                paramfv.a(new gu(paramfv, i + 0.5F, j + 1.5F, k + 0.5F));
            }
            paramjl.a -= 1;
        }

        return paramjl;
    }
}
