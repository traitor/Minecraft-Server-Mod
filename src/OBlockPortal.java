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
        if ((paramOIBlockAccess.a(paramInt1 - 1, paramInt2, paramInt3) == bl) || (paramOIBlockAccess.a(paramInt1 + 1, paramInt2, paramInt3) == bl)) {
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

    public boolean a_(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        int i = 0;
        int j = 0;
        if ((paramOWorld.a(paramInt1 - 1, paramInt2, paramInt3) == OBlock.ap.bl) || (paramOWorld.a(paramInt1 + 1, paramInt2, paramInt3) == OBlock.ap.bl))
            i = 1;
        if ((paramOWorld.a(paramInt1, paramInt2, paramInt3 - 1) == OBlock.ap.bl) || (paramOWorld.a(paramInt1, paramInt2, paramInt3 + 1) == OBlock.ap.bl))
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
                    if (i1 != OBlock.ap.bl)
                        return false;
                } else if ((i1 != 0) && (i1 != OBlock.ar.bl))
                    return false;
            }

        paramOWorld.h = true;
        for (int k = 0; k < 2; k++)
            for (m = 0; m < 3; m++)
                paramOWorld.e(paramInt1 + i * k, paramInt2 + m, paramInt3 + j * k, OBlock.be.bl);
        paramOWorld.h = false;

        return true;
    }

    @Override
    public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = 0;
        int j = 1;
        if ((paramOWorld.a(paramInt1 - 1, paramInt2, paramInt3) == bl) || (paramOWorld.a(paramInt1 + 1, paramInt2, paramInt3) == bl)) {
            i = 1;
            j = 0;
        }

        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Block(bl, paramInt1, paramInt2, paramInt3), false)) {
            int k = paramInt2;
            while (paramOWorld.a(paramInt1, k - 1, paramInt3) == bl)
                k--;
            if (paramOWorld.a(paramInt1, k - 1, paramInt3) != OBlock.ap.bl) {
                paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
                return;
            }

            int m = 1;
            while ((m < 4) && (paramOWorld.a(paramInt1, k + m, paramInt3) == bl))
                m++;
            if ((m != 3) || (paramOWorld.a(paramInt1, k + m, paramInt3) != OBlock.ap.bl)) {
                paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
                return;
            }

            int n = (paramOWorld.a(paramInt1 - 1, paramInt2, paramInt3) == bl) || (paramOWorld.a(paramInt1 + 1, paramInt2, paramInt3) == bl) ? 1 : 0;
            int i1 = (paramOWorld.a(paramInt1, paramInt2, paramInt3 - 1) == bl) || (paramOWorld.a(paramInt1, paramInt2, paramInt3 + 1) == bl) ? 1 : 0;
            if ((n != 0) && (i1 != 0)) {
                paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
                return;
            }

            if (((paramOWorld.a(paramInt1 + i, paramInt2, paramInt3 + j) != OBlock.ap.bl) || (paramOWorld.a(paramInt1 - i, paramInt2, paramInt3 - j) != bl)) && ((paramOWorld.a(paramInt1 - i, paramInt2, paramInt3 - j) != OBlock.ap.bl) || (paramOWorld.a(paramInt1 + i, paramInt2, paramInt3 + j) != bl))) {
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
        if (paramOWorld.t)
            return;

        paramOEntity.Y();
    }
}
