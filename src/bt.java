public class bt extends fw {
    private int a;

    public bt(int paramInt) {
        super(paramInt);
        this.a = (paramInt + 256);
        a(gc.m[(paramInt + 256)].a(2));
    }

    @Override
    public boolean a(hn paramhn, fz paramfz, eq parameq, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        // hMod: Bail if we have nothing of the items in hand
        if (paramhn.a == 0) {
            return false;
        }

        // hMod: Store blockInfo of the one we clicked
        int blockClickedId = parameq.a(paramInt1, paramInt2, paramInt3);
        Block blockClicked = new Block(blockClickedId, paramInt1, paramInt2, paramInt3 );

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

        // hMod: Store faceClicked (must be here to have the 'snow' special case).
        blockClicked.setFaceClicked(Block.Face.fromId( paramInt4 ));

        // hMod: And the block we're about to place
        Block blockPlaced = new Block( this.a, paramInt1, paramInt2, paramInt3 );

        // hMod Store all the old settings 'externally' in case someone changes blockPlaced.
        int oldMaterial = parameq.a(paramInt1, paramInt2, paramInt3);
        int oldData = parameq.b(paramInt1, paramInt2, paramInt3);

        if (parameq.a(this.a, paramInt1, paramInt2, paramInt3, false)) {
            gc localgc = gc.m[this.a];

            //hMod: Take over block placement
            if (parameq.a(paramInt1, paramInt2, paramInt3, this.a)) {
                // hMod: Check if this was playerPlaced and call the hook
                if (paramfz instanceof et && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PLACE, ((et)paramfz).getPlayer(), blockPlaced, blockClicked, new Item(paramhn))) {
                    // hMod: Undo!

                    // Specialcase iceblocks, replace with 'glass' first (so it doesnt explode into water)
                    if (this.a == 79) {
                        parameq.a(paramInt1, paramInt2, paramInt3, 20 );
                    }
                    parameq.a(paramInt1, paramInt2, paramInt3, oldMaterial );
                    parameq.c(paramInt1, paramInt2, paramInt3, oldData );

                    // hMod: Refund the item the player lost >.>
                    // or not, this occasionally dupes items! we'll do this when notch implements serverside invs.
                    //((et)paramfz).a.b(new fh(paramhn, 1));
                    return false;
                } else {
                    parameq.f(paramInt1, paramInt2, paramInt3);
                    parameq.g(paramInt1, paramInt2, paramInt3, this.a);

                    gc.m[this.a].c(parameq, paramInt1, paramInt2, paramInt3, paramInt4);
                    // hMod: Downcast demanded for inheritance to work >.>
                    gc.m[this.a].a(parameq, paramInt1, paramInt2, paramInt3, (ka)paramfz);
                    parameq.a(paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, localgc.bq.c(), (localgc.bq.a() + 1.0F) / 2.0F, localgc.bq.b() * 0.8F);
                    paramhn.a -= 1;
                }
            }
        }

        return true;
    }
}
