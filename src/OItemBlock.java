public class OItemBlock extends OItem {

    private int a;

    public OItemBlock(int paramInt) {
        super(paramInt);
        a = (paramInt + 256);
        b(OBlock.m[(paramInt + 256)].a(2));
    }

    public boolean a(OItemStack paramOItemStack, OEntityPlayer paramOEntityPlayer, OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        // hMod: Bail if we have nothing of the items in hand
        if (paramOItemStack.a == 0)
            return false;
        // hMod: Store blockInfo of the one we clicked
        int blockClickedId = paramOWorld.a(paramInt1, paramInt2, paramInt3);
        Block blockClicked = new Block(blockClickedId, paramInt1, paramInt2, paramInt3);

        if (paramOWorld.a(paramInt1, paramInt2, paramInt3) == OBlock.aS.bk) {
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

        if (paramOItemStack.a == 0) {
            return false;
        }

        // hMod: Store faceClicked (must be here to have the 'snow' special
        // case).
        blockClicked.setFaceClicked(Block.Face.fromId(paramInt4));

        // hMod: And the block we're about to place
        Block blockPlaced = new Block(a, paramInt1, paramInt2, paramInt3);

        // hMod Store all the old settings 'externally' in case someone changes
        // blockPlaced.
        int oldMaterial = paramOWorld.a(paramInt1, paramInt2, paramInt3);
        int oldData = paramOWorld.b(paramInt1, paramInt2, paramInt3);

        if (paramOWorld.a(a, paramInt1, paramInt2, paramInt3, false)) {
            OBlock localOBlock = OBlock.m[a];
            // hMod: take over block placement
            if (paramOWorld.b(paramInt1, paramInt2, paramInt3, a, a(paramOItemStack.h()))) {
                // hMod: Check if this was playerPlaced and call the hook
                if (paramOEntityPlayer instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PLACE, ((OEntityPlayerMP) paramOEntityPlayer).getPlayer(), blockPlaced, blockClicked, new Item(paramOItemStack))) {
                    // hMod: Undo!

                    // Specialcase iceblocks, replace with 'glass' first (so it
                    // doesnt explode into water)
                    if (a == 79)
                        paramOWorld.b(paramInt1, paramInt2, paramInt3, 20);
                    paramOWorld.b(paramInt1, paramInt2, paramInt3, oldMaterial);
                    paramOWorld.d(paramInt1, paramInt2, paramInt3, oldData);

                    // hMod: Refund the item the player lost >.>
                    // or not, this occasionally dupes items! we'lm do this when
                    // notch implements serverside invs.
                    // ((fi)paramgp).a.b(new fh(paramhn, 1));
                    return false;
                } else {
                    OBlock.m[a].d(paramOWorld, paramInt1, paramInt2, paramInt3, paramInt4);
                    OBlock.m[a].a(paramOWorld, paramInt1, paramInt2, paramInt3, (OEntityLiving) paramOEntityPlayer);
                    paramOWorld.a(paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, localOBlock.bt.c(), (localOBlock.bt.a() + 1.0F) / 2.0F, localOBlock.bt.b() * 0.8F);
                    paramOItemStack.a -= 1;
                }
            }

        }

        return true;
    }

    public String a() {
        return OBlock.m[a].e();
    }
}
