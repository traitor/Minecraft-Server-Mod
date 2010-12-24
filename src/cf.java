
public class cf extends gm {

    private int a;

    public cf(int paramInt) {
        super(paramInt);
        this.a = (paramInt + 256);
        a(gv.m[(paramInt + 256)].a(2));
    }

    public boolean a(il paramik, gq paramgp, ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
         // hMod: Bail if we have nothing of the items in hand
        if (paramik.a == 0) {
            return false;
        }

        // hMod: Store blockInfo of the one we clicked
        int blockClickedId = paramff.a(paramInt1, paramInt2, paramInt3);
        Block blockClicked = new Block(blockClickedId, paramInt1, paramInt2, paramInt3 );

        if (paramff.a(paramInt1, paramInt2, paramInt3) == gv.aS.bh) {
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

        if (paramik.a == 0) {
            return false;
        }

        // hMod: Store faceClicked (must be here to have the 'snow' special case).
        blockClicked.setFaceClicked(Block.Face.fromId( paramInt4 ));

        // hMod: And the block we're about to place
        Block blockPlaced = new Block( this.a, paramInt1, paramInt2, paramInt3 );

        // hMod Store all the old settings 'externally' in case someone changes blockPlaced.
        int oldMaterial = paramff.a(paramInt1, paramInt2, paramInt3);
        int oldData = paramff.b(paramInt1, paramInt2, paramInt3);

        if (paramff.a(this.a, paramInt1, paramInt2, paramInt3, false)) {
            gv localgu = gv.m[this.a];

            //hMod: Take over block placement
            if (paramff.a(paramInt1, paramInt2, paramInt3, this.a)) {
                // hMod: Check if this was playerPlaced and call the hook
                if (paramgp instanceof fi && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PLACE, ((fi)paramgp).getPlayer(), blockPlaced, blockClicked, new Item(paramik))) {
                    // hMod: Undo!

                    // Specialcase iceblocks, replace with 'glass' first (so it doesnt explode into water)
                    if (this.a == 79) {
                        paramff.a(paramInt1, paramInt2, paramInt3, 20 );
                    }
                    paramff.a(paramInt1, paramInt2, paramInt3, oldMaterial );
                    paramff.c(paramInt1, paramInt2, paramInt3, oldData );

                    // hMod: Refund the item the player lost >.>
                    // or not, this occasionally dupes items! we'lm do this when notch implements serverside invs.
                    //((fi)paramgp).a.b(new fh(paramhn, 1));
                    return false;
                } else {
                    paramff.g(paramInt1, paramInt2, paramInt3);
                    paramff.g(paramInt1, paramInt2, paramInt3, this.a);

                    gv.m[this.a].c(paramff, paramInt1, paramInt2, paramInt3, paramInt4);
                    // hMod: Downcast demanded for inheritance to work >.>
                    gv.m[this.a].a(paramff, paramInt1, paramInt2, paramInt3, (lc)paramgp);
                    paramff.a(paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, localgu.bq.c(), (localgu.bq.a() + 1.0F) / 2.0F, localgu.bq.b() * 0.8F);
                    paramik.a -= 1;
                }
            }
        }

        return true;
    }

    public String a() {
        return gv.m[this.a].e();
    }
}
