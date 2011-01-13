
public class iq extends hg {

    private mh a;

    public iq(int paramInt, mh parammh) {
        super(paramInt);
        a = parammh;
        bc = 64;
        bb = 1;
    }

    public boolean a(jl paramjl, hl paramhl, fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramInt4 != 1) {
            return false;
        }
        paramInt2++;
        hr localhr;
        if (a == mh.c) {
            localhr = hr.aE;
        } else {
            localhr = hr.aL;
        }
        if (!localhr.a(paramfv, paramInt1, paramInt2, paramInt3)) {
            return false;
        }

        // hMod: Doors onItemUse
        Block blockClicked = new Block(paramfv.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
        blockClicked.setFaceClicked(Block.Face.fromId(paramInt4));
        Block blockPlaced = new Block(paramfv.a(paramInt1, paramInt2 + 1, paramInt3), paramInt1, paramInt2 + 1, paramInt3);

        // Call the hook
        if (paramhl instanceof fy) {
            Player player = ((fy) paramhl).getPlayer();
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(paramjl))) {
                return false;
            }
        }
        int i = iz.b((paramhl.v + 180.0F) * 4.0F / 360.0F - 0.5D) & 0x3;

        int j = 0;
        int k = 0;
        if (i == 0) {
            k = 1;
        }
        if (i == 1) {
            j = -1;
        }
        if (i == 2) {
            k = -1;
        }
        if (i == 3) {
            j = 1;
        }

        int m = (paramfv.d(paramInt1 - j, paramInt2, paramInt3 - k) ? 1 : 0) + (paramfv.d(paramInt1 - j, paramInt2 + 1, paramInt3 - k) ? 1 : 0);
        int n = (paramfv.d(paramInt1 + j, paramInt2, paramInt3 + k) ? 1 : 0) + (paramfv.d(paramInt1 + j, paramInt2 + 1, paramInt3 + k) ? 1 : 0);

        int i1 = (paramfv.a(paramInt1 - j, paramInt2, paramInt3 - k) == localhr.bi) || (paramfv.a(paramInt1 - j, paramInt2 + 1, paramInt3 - k) == localhr.bi) ? 1 : 0;
        int i2 = (paramfv.a(paramInt1 + j, paramInt2, paramInt3 + k) == localhr.bi) || (paramfv.a(paramInt1 + j, paramInt2 + 1, paramInt3 + k) == localhr.bi) ? 1 : 0;

        int i3 = 0;
        if ((i1 != 0) && (i2 == 0)) {
            i3 = 1;
        } else if (n > m) {
            i3 = 1;
        }

        if (i3 != 0) {
            i = i - 1 & 0x3;
            i += 4;
        }

        paramfv.e(paramInt1, paramInt2, paramInt3, localhr.bi);
        paramfv.c(paramInt1, paramInt2, paramInt3, i);

        paramfv.e(paramInt1, paramInt2 + 1, paramInt3, localhr.bi);
        paramfv.c(paramInt1, paramInt2 + 1, paramInt3, i + 8);

        paramjl.a -= 1;
        return true;
    }
}
