
import java.util.Random;

public class bh extends hg {

    private int a;

    public bh(int paramInt1, int paramInt2) {
        super(paramInt1);
        bb = 1;
        bc = 64;
        a = paramInt2;
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
        ih localih = paramfv.a(localbu1, localbu2, a == 0);
        if (localih == null) {
            return paramjl;
        }

        if (localih.a == jr.a) {
            int i = localih.b;
            int j = localih.c;
            int k = localih.d;

            if (!paramfv.a(paramhl, i, j, k)) {
                return paramjl;
            }

            // hMod: Click == placed when handling an empty bukkit!
            Block blockClicked = new Block(paramfv.a(i, j, k), i, j, k);
            blockClicked.setFaceClicked(Block.Face.fromId(localih.e));
            Block blockPlaced = new Block(0, i, j, k);

            if (a == 0) {
                if ((paramfv.c(i, j, k) == mh.f) && (paramfv.b(i, j, k) == 0)) {
                    // Filling a bucket with water!
                    if (paramhl instanceof fy && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((fy) paramhl).getPlayer(), blockPlaced, blockClicked, new Item(paramjl))) {
                        return paramjl;
                    }

                    paramfv.e(i, j, k, 0);
                    return new jl(hg.av);
                }
                if ((paramfv.c(i, j, k) == mh.g) && (paramfv.b(i, j, k) == 0)) {
                    // Filling a bucket with lava!
                    if (paramhl instanceof fy && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((fy) paramhl).getPlayer(), blockPlaced, blockClicked, new Item(paramjl))) {
                        return paramjl;
                    }

                    paramfv.e(i, j, k, 0);
                    return new jl(hg.aw);
                }
            } else {
                if (a < 0) {
                    return new jl(hg.au);
                }
                if (localih.e == 0) {
                    j--;
                }
                if (localih.e == 1) {
                    j++;
                }
                if (localih.e == 2) {
                    k--;
                }
                if (localih.e == 3) {
                    k++;
                }
                if (localih.e == 4) {
                    i--;
                }
                if (localih.e == 5) {
                    i++;
                }

                if ((paramfv.e(i, j, k)) || (!paramfv.c(i, j, k).a())) {
                    if ((paramfv.q.d) && (a == hr.A.bi)) {
                        paramfv.a(d1 + 0.5D, d2 + 0.5D, d3 + 0.5D, "random.fizz", 0.5F, 2.6F + (paramfv.l.nextFloat() - paramfv.l.nextFloat()) * 0.8F);
                        for (int m = 0; m < 8; m++) {
                            paramfv.a("largesmoke", i + Math.random(), j + Math.random(), k + Math.random(), 0.0D, 0.0D, 0.0D);
                        }
                    } else {
                        // hMod: Bucket empty.
                        blockPlaced = new Block(this.a, i, j, k);
                        if (paramhl instanceof fy && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((fy) paramhl).getPlayer(), blockPlaced, blockClicked, new Item(paramjl))) {
                            return paramjl;
                        }

                        paramfv.b(i, j, k, a, 0);
                    }
                    return new jl(hg.au);
                }
            }

        } else if ((a == 0)
                && ((localih.g instanceof bx))) {
            return new jl(hg.aE);
        }

        return paramjl;
    }
}
