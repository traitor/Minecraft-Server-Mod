import java.util.List;
import java.util.Random;

public class OBlockPressurePlate extends OBlock {

    private OEnumMobType a;

    protected OBlockPressurePlate(int paramInt1, int paramInt2, OEnumMobType paramOEnumMobType) {
        super(paramInt1, paramInt2, OMaterial.d);
        a = paramOEnumMobType;
        a(true);

        float f = 0.0625F;
        a(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
    }

    public int b() {
        return 20;
    }

    public OAxisAlignedBB d(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    public boolean a() {
        return false;
    }

    public boolean a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        return paramOWorld.d(paramInt1, paramInt2 - 1, paramInt3);
    }

    public void e(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
    }

    public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = 0;

        if (!paramOWorld.d(paramInt1, paramInt2 - 1, paramInt3)) {
            i = 1;
        }

        if (i != 0) {
            b_(paramOWorld, paramInt1, paramInt2, paramInt3, paramOWorld.b(paramInt1, paramInt2, paramInt3));
            paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
        }
    }

    public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        if (paramOWorld.t) {
            return;
        }
        if (paramOWorld.b(paramInt1, paramInt2, paramInt3) == 0) {
            return;
        }

        g(paramOWorld, paramInt1, paramInt2, paramInt3);
    }

    public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, OEntity paramOEntity) {
        if (paramOWorld.t) {
            return;
        }

        if (paramOWorld.b(paramInt1, paramInt2, paramInt3) == 1) {
            return;
        }

        g(paramOWorld, paramInt1, paramInt2, paramInt3);
    }

    private void g(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramOWorld.b(paramInt1, paramInt2, paramInt3) == 1 ? 1 : 0;
        int j = 0;

        float f = 0.125F;
        List localList = null;
        if (a == OEnumMobType.a) {
            localList = paramOWorld.b(null, OAxisAlignedBB.b(paramInt1 + f, paramInt2, paramInt3 + f, paramInt1 + 1 - f, paramInt2 + 0.25D, paramInt3 + 1 - f));
        }
        if (a == OEnumMobType.b) {
            localList = paramOWorld.a(OEntityLiving.class, OAxisAlignedBB.b(paramInt1 + f, paramInt2, paramInt3 + f, paramInt1 + 1 - f, paramInt2 + 0.25D, paramInt3 + 1 - f));
        }
        if (a == OEnumMobType.c) {
            localList = paramOWorld.a(OEntityPlayer.class, OAxisAlignedBB.b(paramInt1 + f, paramInt2, paramInt3 + f, paramInt1 + 1 - f, paramInt2 + 0.25D, paramInt3 + 1 - f));
        }
        if (localList.size() > 0) {
            j = 1;
        }

        // hMod: Allow pressure plate interaction to power redstone
        if (j != i) {
            j = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Block(bk, paramInt1, paramInt2, paramInt3), i, j);
            if (j > 0)
                j = 1;
        }

        if ((j != 0) && (i == 0)) {
            paramOWorld.c(paramInt1, paramInt2, paramInt3, 1);
            paramOWorld.h(paramInt1, paramInt2, paramInt3, bk);
            paramOWorld.h(paramInt1, paramInt2 - 1, paramInt3, bk);
            paramOWorld.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);

            paramOWorld.a(paramInt1 + 0.5D, paramInt2 + 0.1D, paramInt3 + 0.5D, "random.click", 0.3F, 0.6F);
        }
        if ((j == 0) && (i != 0)) {
            paramOWorld.c(paramInt1, paramInt2, paramInt3, 0);
            paramOWorld.h(paramInt1, paramInt2, paramInt3, bk);
            paramOWorld.h(paramInt1, paramInt2 - 1, paramInt3, bk);
            paramOWorld.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);

            paramOWorld.a(paramInt1 + 0.5D, paramInt2 + 0.1D, paramInt3 + 0.5D, "random.click", 0.3F, 0.5F);
        }

        if (j != 0) {
            paramOWorld.c(paramInt1, paramInt2, paramInt3, bk, b());
        }
    }

    public void b(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramOWorld.b(paramInt1, paramInt2, paramInt3);
        if (i > 0) {
            paramOWorld.h(paramInt1, paramInt2, paramInt3, bk);
            paramOWorld.h(paramInt1, paramInt2 - 1, paramInt3, bk);
        }
        super.b(paramOWorld, paramInt1, paramInt2, paramInt3);
    }

    public void a(OIBlockAccess paramOIBlockAccess, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramOIBlockAccess.b(paramInt1, paramInt2, paramInt3) == 1 ? 1 : 0;

        float f = 0.0625F;
        if (i != 0) {
            a(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
        } else {
            a(f, 0.0F, f, 1.0F - f, 0.0625F, 1.0F - f);
        }
    }

    public boolean b(OIBlockAccess paramOIBlockAccess, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        return paramOIBlockAccess.b(paramInt1, paramInt2, paramInt3) > 0;
    }

    public boolean c(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramOWorld.b(paramInt1, paramInt2, paramInt3) == 0) {
            return false;
        }
        return paramInt4 == 1;
    }

    public boolean c() {
        return true;
    }
}
