
import java.util.Random;

public class lm extends hg {

    public lm(int paramInt) {
        super(paramInt);
        bb = 1;
        bc = 64;
    }

    public boolean a(jl paramjl, hl paramhl, fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        // hMod: Store block data clicked
        Block blockClicked = new Block(paramfv.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
        blockClicked.setFaceClicked(Block.Face.fromId(paramInt4));
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

        int i = paramfv.a(paramInt1, paramInt2, paramInt3);

        if (i == 0) {
            // hMod: Hook to control ignites AND ligther use
            Block blockPlaced = new Block(Block.Type.Fire.getType(), paramInt1, paramInt2, paramInt3);
            Player player = ((fy) paramhl).getPlayer();

            Boolean preventLighter = (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(paramjl));
            blockPlaced.setStatus(2); // Specificly to mark this ignite as from a lighter
            Boolean preventIgnite = (Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, blockPlaced, player);
            if (preventIgnite || preventLighter) {
                return false;
            } else {
                paramfv.a(paramInt1 + 0.5D, paramInt2 + 0.5D, paramInt3 + 0.5D, "fire.ignite", 1.0F, b.nextFloat() * 0.4F + 0.8F);
                paramfv.e(paramInt1, paramInt2, paramInt3, hr.ar.bi);
            }
        }

        paramjl.b(1);
        return true;
    }
}
