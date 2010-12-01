public class bt extends fv {
    private int a;

    public bt(int paramInt) {
        super(paramInt);
        this.a = (paramInt + 256);
        a(gb.m[(paramInt + 256)].a(2));
    }

    @Override
    public boolean a(hm paramhm, fy paramfy, ep paramep, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        // hMod: Bail if we have nothing of the items in hand
        if (paramhm.a == 0) {
            return false;
        }

        // hMod: Store blockInfo of the one we clicked
        int blockClickedId = paramep.a(paramInt1, paramInt2, paramInt3);
        Block blockClicked = new Block(blockClickedId, paramInt1, paramInt2, paramInt3 );

        if (paramep.a(paramInt1, paramInt2, paramInt3) == gb.aS.bh) {
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

        if (paramhm.a == 0) {
            return false;
        }

        // hMod: Store faceClicked (must be here to have the 'snow' special case).
        blockClicked.setFaceClicked(Block.Face.fromId( paramInt4 ));

        // hMod: And the block we're about to place
        Block blockPlaced = new Block( this.a, paramInt1, paramInt2, paramInt3 );

        // hMod Store all the old settings 'externally' in case someone changes blockPlaced.
        int oldMaterial = paramep.a(paramInt1, paramInt2, paramInt3);
        int oldData = paramep.b(paramInt1, paramInt2, paramInt3);

        if (paramep.a(this.a, paramInt1, paramInt2, paramInt3, false)) {
            gb localgb = gb.m[this.a];

            //hMod: Take over block placement
            if (paramep.a(paramInt1, paramInt2, paramInt3, this.a)) {
                // hMod: Check if this was playerPlaced and call the hook
                if (paramfy instanceof es && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PLACE, new Object[]{((es)paramfy).getPlayer(), blockPlaced, blockClicked, new Item(paramhm)})) {
                    // hMod: Undo!

                    // Specialcase iceblocks, replace with 'glass' first (so it doesnt explode into water)
                    if (this.a == 79) {
                        paramep.a(paramInt1, paramInt2, paramInt3, 20 );
                    }
                    paramep.a(paramInt1, paramInt2, paramInt3, oldMaterial );
                    paramep.c(paramInt1, paramInt2, paramInt3, oldData );

                    // hMod: Refund the item the player lost >.>
                    // or not, this occasionally dupes items! we'll do this when notch implements serverside invs.
                    //((es)paramfy).a.b(new fg(paramhm, 1));
                    return false;
                } else {
                    paramep.f(paramInt1, paramInt2, paramInt3);
                    paramep.g(paramInt1, paramInt2, paramInt3, this.a);

                    gb.m[this.a].c(paramep, paramInt1, paramInt2, paramInt3, paramInt4);
                    // hMod: Downcast fx to jy; demanded for inheritance to work >.>
                    gb.m[this.a].a(paramep, paramInt1, paramInt2, paramInt3, (jz)paramfy);
                    paramep.a(paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, localgb.bq.c(), (localgb.bq.a() + 1.0F) / 2.0F, localgb.bq.b() * 0.8F);
                    paramhm.a -= 1;
                }
            }
        }

        return true;
    }
}
