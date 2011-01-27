import java.util.Random;

public class OBlockPortal extends OBlockBreakable {

    public OBlockPortal(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, OMaterial.x, false);
    }

    @Override
    public OAxisAlignedBB d(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    @Override
    public void a(OIBlockAccess paramOIBlockAccess, int paramInt1, int paramInt2, int paramInt3) {
        float f1;
        float f2;
        if ((paramOIBlockAccess.a(paramInt1 - 1, paramInt2, paramInt3) == bi) || (paramOIBlockAccess.a(paramInt1 + 1, paramInt2, paramInt3) == bi)) {
            f1 = 0.5F;
            f2 = 0.125F;
            a(0.5F - f1, 0.0F, 0.5F - f2, 0.5F + f1, 1.0F, 0.5F + f2);
        } else {
            f1 = 0.125F;
            f2 = 0.5F;
            a(0.5F - f1, 0.0F, 0.5F - f2, 0.5F + f1, 1.0F, 0.5F + f2);
        }
    }

    @Override
    public boolean a() {
        return false;
    }

    public boolean b_(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        int i = 0;
        int j = 0;
        if ((paramOWorld.a(paramInt1 - 1, paramInt2, paramInt3) == OBlock.ap.bi) || (paramOWorld.a(paramInt1 + 1, paramInt2, paramInt3) == OBlock.ap.bi))
            i = 1;
        if ((paramOWorld.a(paramInt1, paramInt2, paramInt3 - 1) == OBlock.ap.bi) || (paramOWorld.a(paramInt1, paramInt2, paramInt3 + 1) == OBlock.ap.bi))
            j = 1;
        // Hmod: comment out debug message.
        // System.out.println(i + ", " + j);
        if (i == j)
            return false;

        if (paramOWorld.a(paramInt1 - i, paramInt2, paramInt3 - j) == 0) {
            paramInt1 -= i;
            paramInt3 -= j;
        }
        int m;
        for (int k = -1; k <= 2; k++)
            for (m = -1; m <= 3; m++) {
                int n = (k == -1) || (k == 2) || (m == -1) || (m == 3) ? 1 : 0;
                if (((k == -1) || (k == 2)) && ((m == -1) || (m == 3)))
                    continue;
                int i1 = paramOWorld.a(paramInt1 + i * k, paramInt2 + m, paramInt3 + j * k);

                if (n != 0) {
                    if (i1 != OBlock.ap.bi)
                        return false;
                } else if ((i1 != 0) && (i1 != OBlock.ar.bi))
                    return false;
            }

        paramOWorld.i = true;
        for (int k = 0; k < 2; k++)
            for (m = 0; m < 3; m++)
                paramOWorld.e(paramInt1 + i * k, paramInt2 + m, paramInt3 + j * k, OBlock.be.bi);
        paramOWorld.i = false;

        return true;
    }

    @Override
    public void b(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = 0;
        int j = 1;
        if ((paramOWorld.a(paramInt1 - 1, paramInt2, paramInt3) == bi) || (paramOWorld.a(paramInt1 + 1, paramInt2, paramInt3) == bi)) {
            i = 1;
            j = 0;
        }
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Block(bi, paramInt1, paramInt2, paramInt3), false)) {
            int k = paramInt2;
            while (paramOWorld.a(paramInt1, k - 1, paramInt3) == bi)
                k--;
            if (paramOWorld.a(paramInt1, k - 1, paramInt3) != OBlock.ap.bi) {
                paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
                return;
            }

            int m = 1;
            while ((m < 4) && (paramOWorld.a(paramInt1, k + m, paramInt3) == bi))
                m++;
            if ((m != 3) || (paramOWorld.a(paramInt1, k + m, paramInt3) != OBlock.ap.bi)) {
                paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
                return;
            }

            int n = (paramOWorld.a(paramInt1 - 1, paramInt2, paramInt3) == bi) || (paramOWorld.a(paramInt1 + 1, paramInt2, paramInt3) == bi) ? 1 : 0;
            int i1 = (paramOWorld.a(paramInt1, paramInt2, paramInt3 - 1) == bi) || (paramOWorld.a(paramInt1, paramInt2, paramInt3 + 1) == bi) ? 1 : 0;
            if ((n != 0) && (i1 != 0)) {
                paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
                return;
            }

            if (((paramOWorld.a(paramInt1 + i, paramInt2, paramInt3 + j) != OBlock.ap.bi) || (paramOWorld.a(paramInt1 - i, paramInt2, paramInt3 - j) != bi)) && ((paramOWorld.a(paramInt1 - i, paramInt2, paramInt3 - j) != OBlock.ap.bi) || (paramOWorld.a(paramInt1 + i, paramInt2, paramInt3 + j) != bi))) {
                paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
                return;
            }
        }
    }

    @Override
    public boolean a(OIBlockAccess paramOIBlockAccess, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        return true;
    }

    @Override
    public int a(Random paramRandom) {
        return 0;
    }

    @Override
    public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, OEntity paramOEntity) {
        if (paramOWorld.z)
            return;

        paramOEntity.H();
    }
}
