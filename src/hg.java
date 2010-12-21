
public class hg extends gl {

    public hg(int paramInt) {
        super(paramInt);
        this.aY = 64;
        this.aX = 1;
    }

    public boolean a(ik paramik, gp paramgp, ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramInt4 == 0) {
            return false;
        }
        if (!paramff.c(paramInt1, paramInt2, paramInt3).a()) {
            return false;
        }

        // hMod: Store block data clicked
        Block blockClicked = new Block(paramff.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3 );
        blockClicked.setFaceClicked(Block.Face.fromId( paramInt4 ));

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

        if (!gu.aD.a(paramff, paramInt1, paramInt2, paramInt3)) {
            return false;
        }

         // hMod: Now we can call itemUse :)
        Block blockPlaced = new Block((paramInt4 == 1 ? gu.aD.bh : gu.ai.bh), paramInt1, paramInt2, paramInt3);
        if (paramgp instanceof fi && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((fi)paramgp).getPlayer(), blockPlaced, blockClicked, new Item(paramik))) {
            return false;
        }

        if (paramInt4 == 1) {
            paramff.b(paramInt1, paramInt2, paramInt3, gu.aD.bh, ib.b((paramgp.v + 180.0F) * 16.0F / 360.0F + 0.5D) & 0xF);
        } else {
            paramff.b(paramInt1, paramInt2, paramInt3, gu.aI.bh, paramInt4);
        }

        paramik.a -= 1;
        ko localko = (ko) paramff.l(paramInt1, paramInt2, paramInt3);
        if (localko != null) {
            paramgp.a(localko);
        }
        return true;
    }
}
