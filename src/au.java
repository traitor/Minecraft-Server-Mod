
public class au extends fw {
    private int a;

    public au(int paramInt1, int paramInt2) {
        super(paramInt1);
        this.aX = 1;
        this.aY = 64;
        this.a = paramInt2;
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
        gq localgq = parameq.a(localbd1, localbd2, this.a == 0);
        if (localgq == null) {
            return paramhn;
        }

        if (localgq.a == 0) {
            int i = localgq.b;
            int j = localgq.c;
            int k = localgq.d;

            if (!parameq.a(paramfz, i, j, k)) {
                return paramhn;
            }

            // hMod: Click == placed when handling an empty bukkit!
            Block blockClicked = new Block(parameq.a(i, j, k), i, j, k );
            blockClicked.setFaceClicked(Block.Face.fromId(localgq.e));
            Block blockPlaced  = new Block(0, i, j, k );

            if (this.a == 0) {
                if ((parameq.c(i, j, k) == jy.f) && (parameq.b(i, j, k) == 0)) {
                    // Filling a bucket with water!
                    if (paramfz instanceof et && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((et)paramfz).getPlayer(), blockPlaced, blockClicked, new Item(paramhn))) {
                        return paramhn;
                    }

                    parameq.d(i, j, k, 0);
                    return new hn(fw.av);
                }
                if ((parameq.c(i, j, k) == jy.g) && (parameq.b(i, j, k) == 0)) {
                    // Filling a bucket with lava!
                    if (paramfz instanceof et && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((et)paramfz).getPlayer(), blockPlaced, blockClicked, new Item(paramhn))) {
                        return paramhn;
                    }

                    parameq.d(i, j, k, 0);
                    return new hn(fw.aw);
                }
            } else {
                if (this.a < 0) {
                    return new hn(fw.au);
                }
                if (localgq.e == 0) {
                    j--;
                }
                if (localgq.e == 1) {
                    j++;
                }
                if (localgq.e == 2) {
                    k--;
                }
                if (localgq.e == 3) {
                    k++;
                }
                if (localgq.e == 4) {
                    i--;
                }
                if (localgq.e == 5) {
                    i++;
                }

                if ((parameq.a(i, j, k) == 0) || (!parameq.c(i, j, k).a())) {
                    if ((parameq.q.d) && (this.a == gc.A.bh)) {
                        parameq.a(d1 + 0.5D, d2 + 0.5D, d3 + 0.5D, "random.fizz", 0.5F, 2.6F + (parameq.l.nextFloat() - parameq.l.nextFloat()) * 0.8F);
                        for (int m = 0; m < 8; m++) {
                            parameq.a("largesmoke", i + Math.random(), j + Math.random(), k + Math.random(), 0.0D, 0.0D, 0.0D);
                        }
                    } else {
                        // hMod: Bucket empty.
                        blockPlaced = new Block(this.a, i, j, k );
                        if (paramfz instanceof et && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((et)paramfz).getPlayer(), blockPlaced, blockClicked, new Item(paramhn))) {
                            return paramhn;
                        }
                        parameq.b(i, j, k, this.a, 0);
                    }
                    return new hn(fw.au);
                }
            }
        } else if ((this.a == 0) && ((localgq.g instanceof bf))) {
            return new hn(fw.aE);
        }

        return paramhn;
    }
}