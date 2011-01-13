
public class cp extends hg {

    private int a;

    public cp(int paramInt) {
        super(paramInt);
        a = (paramInt + 256);
        b(hr.m[(paramInt + 256)].a(2));
    }

    public boolean a(jl paramjl, hl paramhl, fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        // hMod: Bail if we have nothing of the items in hand
        if (paramjl.a == 0) {
            return false;
        }
        // hMod: Store blockInfo of the one we clicked
        int blockClickedId = paramfv.a(paramInt1, paramInt2, paramInt3);
        Block blockClicked = new Block(blockClickedId, paramInt1, paramInt2, paramInt3);
        if (paramfv.a(paramInt1, paramInt2, paramInt3) == hr.aS.bi) {
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

        if (paramjl.a == 0) {
            return false;
        }

        // hMod: Store faceClicked (must be here to have the 'snow' special case).
        blockClicked.setFaceClicked(Block.Face.fromId(paramInt4));

        // hMod: And the block we're about to place
        Block blockPlaced = new Block(this.a, paramInt1, paramInt2, paramInt3);

        // hMod Store all the old settings 'externally' in case someone changes blockPlaced.
        int oldMaterial = paramfv.a(paramInt1, paramInt2, paramInt3);
        int oldData = paramfv.b(paramInt1, paramInt2, paramInt3);

        if (paramfv.a(a, paramInt1, paramInt2, paramInt3, false)) {
            hr localhr = hr.m[a];
            //hMod: Take over block placement
            if (paramfv.a(paramInt1, paramInt2, paramInt3, this.a)) {
                // hMod: Check if this was playerPlaced and call the hook
                if (paramhl instanceof fy && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PLACE, ((fy) paramhl).getPlayer(), blockPlaced, blockClicked, new Item(paramjl))) {
                    // hMod: Undo!

                    // Specialcase iceblocks, replace with 'glass' first (so it doesnt explode into water)
                    if (this.a == 79) {
                        paramfv.a(paramInt1, paramInt2, paramInt3, 20);
                    }
                    paramfv.a(paramInt1, paramInt2, paramInt3, oldMaterial);
                    paramfv.c(paramInt1, paramInt2, paramInt3, oldData);

                    // hMod: Refund the item the player lost >.>
                    // or not, this occasionally dupes items! we'lm do this when notch implements serverside invs.
                    //((fi)paramgp).a.b(new fh(paramhn, 1));
                    return false;
                } else {
                    paramfv.g(paramInt1, paramInt2, paramInt3);
                    paramfv.g(paramInt1, paramInt2, paramInt3, this.a);

                    hr.m[this.a].c(paramfv, paramInt1, paramInt2, paramInt3, paramInt4);
                    // hMod: Downcast demanded for inheritance to work >.>
                    hr.m[this.a].a(paramfv, paramInt1, paramInt2, paramInt3, (mj) paramhl);
                    paramfv.a(paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, localhr.br.c(), (localhr.br.a() + 1.0F) / 2.0F, localhr.br.b() * 0.8F);
                    paramjl.a -= 1;
                }
            }


        }

        return true;
    }

    public String a() {
        return hr.m[a].e();
    }
}
