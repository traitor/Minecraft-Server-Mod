
import java.util.Random;

public class lo extends hg {

    public lo(int paramInt, bv parambv) {
        super(paramInt);
        bb = 1;
        bc = parambv.a();
    }

    public boolean a(jl paramjl, hl paramhl, fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = paramfv.a(paramInt1, paramInt2, paramInt3);
        mh localmh = paramfv.c(paramInt1, paramInt2 + 1, paramInt3);

        if (((!localmh.a()) && (i == hr.u.bi)) || (i == hr.v.bi)) {
            // hMod: Hoes
            Block blockClicked = new Block(i, paramInt1, paramInt2, paramInt3);
            blockClicked.setFaceClicked(Block.Face.fromId(paramInt4));
            Block blockPlaced = new Block(paramfv.a(paramInt1, paramInt2 + 1, paramInt3), paramInt1, paramInt2 + 1, paramInt3);

            // Call the hook
            if (paramhl instanceof fy) {
                Player player = ((fy) paramhl).getPlayer();
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(paramjl))) {
                    return false;
                }
            }
            hr localhr = hr.aA;
            paramfv.a(paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, localhr.br.c(), (localhr.br.a() + 1.0F) / 2.0F, localhr.br.b() * 0.8F);

            if (paramfv.z) {
                return true;
            }
            paramfv.e(paramInt1, paramInt2, paramInt3, localhr.bi);
            paramjl.b(1);

            if ((paramfv.l.nextInt(8) == 0) && (i == hr.u.bi)) {
                int j = 1;
                for (int k = 0; k < j; k++) {
                    float f1 = 0.7F;
                    float f2 = paramfv.l.nextFloat() * f1 + (1.0F - f1) * 0.5F;
                    float f3 = 1.2F;
                    float f4 = paramfv.l.nextFloat() * f1 + (1.0F - f1) * 0.5F;
                    ic localic = new ic(paramfv, paramInt1 + f2, paramInt2 + f3, paramInt3 + f4, new jl(hg.Q));
                    localic.c = 10;
                    paramfv.a(localic);
                }
            }

            return true;
        }

        return false;
    }
}
