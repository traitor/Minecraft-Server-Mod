
import java.util.Random;

public class bb extends gm {

    private int a;

    public bb(int paramInt1, int paramInt2) {
        super(paramInt1);
        this.aX = 1;
        this.aY = 64;
        this.a = paramInt2;
    }

    public il a(il paramik, ff paramff, gq paramgp) {
        float f1 = 1.0F;

        float f2 = paramgp.y + (paramgp.w - paramgp.y) * f1;
        float f3 = paramgp.x + (paramgp.v - paramgp.x) * f1;

        double d1 = paramgp.m + (paramgp.p - paramgp.m) * f1;
        double d2 = paramgp.n + (paramgp.q - paramgp.n) * f1 + 1.62D - paramgp.H;
        double d3 = paramgp.o + (paramgp.r - paramgp.o) * f1;

        bn localbn1 = bn.b(d1, d2, d3);

        float f4 = ic.b(-f3 * 0.01745329F - 3.141593F);
        float f5 = ic.a(-f3 * 0.01745329F - 3.141593F);
        float f6 = -ic.b(-f2 * 0.01745329F);
        float f7 = ic.a(-f2 * 0.01745329F);

        float f8 = f5 * f6;
        float f9 = f7;
        float f10 = f4 * f6;

        double d4 = 5.0D;
        bn localbn2 = localbn1.c(f8 * d4, f9 * d4, f10 * d4);
        hk localhj = paramff.a(localbn1, localbn2, this.a == 0);
        if (localhj == null) {
            return paramik;
        }

        if (localhj.a == 0) {
            int i = localhj.b;
            int j = localhj.c;
            int k = localhj.d;

            if (!paramff.a(paramgp, i, j, k)) {
                return paramik;
            }

            // hMod: Click == placed when handling an empty bukkit!
            Block blockClicked = new Block(paramff.a(i, j, k), i, j, k );
            blockClicked.setFaceClicked(Block.Face.fromId(localhj.e));
            Block blockPlaced  = new Block(0, i, j, k );

            if (this.a == 0) {
                if ((paramff.c(i, j, k) == la.f) && (paramff.b(i, j, k) == 0)) {
                    // Filling a bucket with water!
                    if (paramgp instanceof fi && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((fi)paramgp).getPlayer(), blockPlaced, blockClicked, new Item(paramik))) {
                        return paramik;
                    }

                    paramff.d(i, j, k, 0);
                    return new il(gm.av);
                }
                if ((paramff.c(i, j, k) == la.g) && (paramff.b(i, j, k) == 0)) {
                    // Filling a bucket with lava!
                    if (paramgp instanceof fi && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((fi)paramgp).getPlayer(), blockPlaced, blockClicked, new Item(paramik))) {
                        return paramik;
                    }

                    paramff.d(i, j, k, 0);
                    return new il(gm.aw);
                }
            } else {
                if (this.a < 0) {
                    return new il(gm.au);
                }
                if (localhj.e == 0) {
                    j--;
                }
                if (localhj.e == 1) {
                    j++;
                }
                if (localhj.e == 2) {
                    k--;
                }
                if (localhj.e == 3) {
                    k++;
                }
                if (localhj.e == 4) {
                    i--;
                }
                if (localhj.e == 5) {
                    i++;
                }

                if ((paramff.e(i, j, k)) || (!paramff.c(i, j, k).a())) {
                    if ((paramff.q.d) && (this.a == gv.A.bh)) {
                        paramff.a(d1 + 0.5D, d2 + 0.5D, d3 + 0.5D, "random.fizz", 0.5F, 2.6F + (paramff.l.nextFloat() - paramff.l.nextFloat()) * 0.8F);
                        for (int m = 0; m < 8; m++) {
                            paramff.a("largesmoke", i + Math.random(), j + Math.random(), k + Math.random(), 0.0D, 0.0D, 0.0D);
                        }
                    } else {
                        // hMod: Bucket empty.
                        blockPlaced = new Block(this.a, i, j, k );
                        if (paramgp instanceof fi && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((fi)paramgp).getPlayer(), blockPlaced, blockClicked, new Item(paramik))) {
                            return paramik;
                        }
                        
                        paramff.b(i, j, k, this.a, 0);
                    }
                    return new il(gm.au);
                }
            }

        } else if ((this.a == 0)
                && ((localhj.g instanceof bp))) {
            return new il(gm.aE);
        }

        return paramik;
    }
}
