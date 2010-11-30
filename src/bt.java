public class bt extends fu {
    private int a;

    public bt(int paramInt) {
        super(paramInt);
        this.a = (paramInt + 256);
        a(ga.m[(paramInt + 256)].a(2));
    }

    @Override
    public boolean a(hl paramhl, fx paramfx, eo parameo, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        // hMod: Bail if we have nothing of the items in hand
        if (paramhl.a == 0) {
            return false;
        }
        
        // hMod: Store blockInfo of the one we clicked
        int blockClickedId = parameo.a(paramInt1, paramInt2, paramInt3);
        Block blockClicked = new Block(blockClickedId, paramInt1, paramInt2, paramInt3 );
        
        if (blockClickedId == ga.aS.bh) {
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
        
        // hMod: Store faceClicked (must be here to have the 'snow' special case).
        blockClicked.setFaceClicked(Block.Face.fromId( paramInt4 ));
        
        // hMod: And the block we're about to place
        Block blockPlaced = new Block( this.a, paramInt1, paramInt2, paramInt3 );
        
        // hMod Store all the old settings 'externally' in case someone changes blockPlaced.
        int oldMaterial = parameo.a(paramInt1, paramInt2, paramInt3);
        int oldData = parameo.b(paramInt1, paramInt2, paramInt3);
        
        if (parameo.a(this.a, paramInt1, paramInt2, paramInt3, false)) {
            ga localga = ga.m[this.a];
            
            //hMod: Take over block placement
            if (parameo.a(paramInt1, paramInt2, paramInt3, this.a)) {
                // hMod: Check if this was playerPlaced and call the hook
                if (paramfx instanceof er && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PLACE, new Object[]{((er)paramfx).getPlayer(), blockPlaced, blockClicked, new Item(paramhl)})) {
                    // hMod: Undo!
                    
                    // Specialcase iceblocks, replace with 'glass' first (so it doesnt explode into water)
                    if (this.a == 79) {
                        parameo.a(paramInt1, paramInt2, paramInt3, 20 );
                    }
                    parameo.a(paramInt1, paramInt2, paramInt3, oldMaterial );
                    parameo.c(paramInt1, paramInt2, paramInt3, oldData );
                    
                    // hMod: Refund the item the player lost >.>
                    // or not, this occasionally dupes items! we'll do this when notch implements serverside invs.
                    //((er)paramfx).a.b(new ff(paramhl, 1));
                    return false;
                } else {
                    parameo.f(paramInt1, paramInt2, paramInt3);
                    parameo.g(paramInt1, paramInt2, paramInt3, this.a);
                    
                    ga.m[this.a].c(parameo, paramInt1, paramInt2, paramInt3, paramInt4);
                    // hMod: Downcast fx to jy; demanded for inheritane to work >.>
                    ga.m[this.a].a(parameo, paramInt1, paramInt2, paramInt3, (jy)paramfx);
                    parameo.a(paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, localga.bq.c(), (localga.bq.a() + 1.0F) / 2.0F, localga.bq.b() * 0.8F);
                    paramhl.a -= 1; 
                }
            }
        }

        return true;
    }
}
