
import java.util.Random;

public class kh extends gm {

    public kh(int paramInt) {
        super(paramInt);
        this.aX = 1;
        this.aY = 64;
    }

    public boolean a(il paramik, gq paramgp, ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        // hMod: Store block data clicked
        Block blockClicked = new Block(paramff.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3 );
        blockClicked.setFaceClicked(Block.Face.fromId( paramInt4 ));
        
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

        int i = paramff.a(paramInt1, paramInt2, paramInt3);

        if (i == 0) {
             // hMod: Hook to control ignites AND ligther use
            Block blockPlaced = new Block(Block.Type.Fire.getType(), paramInt1, paramInt2, paramInt3);
            Player player = ((fi) paramgp).getPlayer();

            Boolean preventLighter = (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(paramik));
            blockPlaced.setStatus(2); // Specificly to mark this ignite as from a lighter
            Boolean preventIgnite = (Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, blockPlaced, player);

            if ( preventIgnite || preventLighter ) {
                return false;
            } else {
                paramff.a(paramInt1 + 0.5D, paramInt2 + 0.5D, paramInt3 + 0.5D, "fire.ignite", 1.0F, b.nextFloat() * 0.4F + 0.8F);
                paramff.d(paramInt1, paramInt2, paramInt3, gv.ar.bh);
            }
        }

        paramik.b(1);
        return true;
    }
}
