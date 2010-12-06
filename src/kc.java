public class kc extends fw {
    private int a;

    public kc(int paramInt, gc paramgc) {
        super(paramInt);
        this.a = paramgc.bh;
    }

    @Override
    public boolean a(hn paramhn, fz paramfz, eq parameq, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {

        // hMod: Store blockClicked
        Block blockClicked = new Block(parameq.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);

        if (parameq.a(paramInt1, paramInt2, paramInt3) == gc.aS.bh) {
            paramInt4 = 0;
        } else {
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
        }

        if (paramhn.a == 0) {
            return false;
        }

        if (parameq.a(this.a, paramInt1, paramInt2, paramInt3, false)) {
            gc localgc = gc.m[this.a];

            // hMod: Reed placement
            Block blockPlaced = new Block(parameq.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
            blockClicked.setFaceClicked(Block.Face.fromId(paramInt4));
            Player player = ((et) paramfz).getPlayer();

            // hMod Store all the old settings 'externally' in case someone changes blockPlaced.
            int oldMaterial = parameq.a(paramInt1, paramInt2, paramInt3);
            int oldData = parameq.b(paramInt1, paramInt2, paramInt3);

            //hMod: Take over block placement
            //if (parameq.d(paramInt1, paramInt2, paramInt3, this.a)) {
            if (parameq.a(paramInt1, paramInt2, paramInt3, this.a)) {

                // hMod: Call the hook
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(paramhn))) {
                    // hMod: Undo!
                    parameq.a(paramInt1, paramInt2, paramInt3, oldMaterial );
                    parameq.c(paramInt1, paramInt2, paramInt3, oldData );

                    return false;
                } else {
                    parameq.f(paramInt1, paramInt2, paramInt3);
                    parameq.g(paramInt1, paramInt2, paramInt3, this.a);

                    gc.m[this.a].c(parameq, paramInt1, paramInt2, paramInt3, paramInt4);
                    parameq.a(paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, localgc.bq.c(), (localgc.bq.a() + 1.0F) / 2.0F, localgc.bq.b() * 0.8F);
                    paramhn.a -= 1;
                }
            }

        }

        return true;
    }
}