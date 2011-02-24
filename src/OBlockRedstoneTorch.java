
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OBlockRedstoneTorch extends OBlockTorch {

    private boolean a = false;
    private static List b = new ArrayList();

    public int a(int paramInt1, int paramInt2) {
        if (paramInt1 == 1) {
            return OBlock.av.a(paramInt1, paramInt2);
        }
        return super.a(paramInt1, paramInt2);
    }

    private boolean a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
        if (paramBoolean) {
            b.add(new ORedstoneUpdateInfo(paramInt1, paramInt2, paramInt3, paramOWorld.k()));
        }
        int i = 0;
        for (int j = 0; j < b.size(); j++) {
            ORedstoneUpdateInfo localORedstoneUpdateInfo = (ORedstoneUpdateInfo) b.get(j);
            if ((localORedstoneUpdateInfo.a == paramInt1) && (localORedstoneUpdateInfo.b == paramInt2) && (localORedstoneUpdateInfo.c == paramInt3)) {
                i++;
                if (i >= 8) {
                    return true;
                }
            }
        }
        return false;
    }

    protected OBlockRedstoneTorch(int paramInt1, int paramInt2, boolean paramBoolean) {
        super(paramInt1, paramInt2);
        a = paramBoolean;
        a(true);
    }

    public int b() {
        return 2;
    }

    public void e(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        if (paramOWorld.b(paramInt1, paramInt2, paramInt3) == 0) {
            super.e(paramOWorld, paramInt1, paramInt2, paramInt3);
        }
        if (a) {
            paramOWorld.h(paramInt1, paramInt2 - 1, paramInt3, bk);
            paramOWorld.h(paramInt1, paramInt2 + 1, paramInt3, bk);
            paramOWorld.h(paramInt1 - 1, paramInt2, paramInt3, bk);
            paramOWorld.h(paramInt1 + 1, paramInt2, paramInt3, bk);
            paramOWorld.h(paramInt1, paramInt2, paramInt3 - 1, bk);
            paramOWorld.h(paramInt1, paramInt2, paramInt3 + 1, bk);
        }
    }

    public void b(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        if (a) {
            paramOWorld.h(paramInt1, paramInt2 - 1, paramInt3, bk);
            paramOWorld.h(paramInt1, paramInt2 + 1, paramInt3, bk);
            paramOWorld.h(paramInt1 - 1, paramInt2, paramInt3, bk);
            paramOWorld.h(paramInt1 + 1, paramInt2, paramInt3, bk);
            paramOWorld.h(paramInt1, paramInt2, paramInt3 - 1, bk);
            paramOWorld.h(paramInt1, paramInt2, paramInt3 + 1, bk);
        }
    }

    public boolean b(OIBlockAccess paramOIBlockAccess, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (!a) {
            return false;
        }

        int i = paramOIBlockAccess.b(paramInt1, paramInt2, paramInt3);

        if ((i == 5) && (paramInt4 == 1)) {
            return false;
        }
        if ((i == 3) && (paramInt4 == 3)) {
            return false;
        }
        if ((i == 4) && (paramInt4 == 2)) {
            return false;
        }
        if ((i == 1) && (paramInt4 == 5)) {
            return false;
        }
        return (i != 2) || (paramInt4 != 4);
    }

    private boolean g(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramOWorld.b(paramInt1, paramInt2, paramInt3);

        if ((i == 5) && (paramOWorld.j(paramInt1, paramInt2 - 1, paramInt3, 0))) {
            return true;
        }
        if ((i == 3) && (paramOWorld.j(paramInt1, paramInt2, paramInt3 - 1, 2))) {
            return true;
        }
        if ((i == 4) && (paramOWorld.j(paramInt1, paramInt2, paramInt3 + 1, 3))) {
            return true;
        }
        if ((i == 1) && (paramOWorld.j(paramInt1 - 1, paramInt2, paramInt3, 4))) {
            return true;
        }
        return (i == 2) && (paramOWorld.j(paramInt1 + 1, paramInt2, paramInt3, 5));
    }

    public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        boolean bool = g(paramOWorld, paramInt1, paramInt2, paramInt3);

        while ((b.size() > 0) && (paramOWorld.k() - ((ORedstoneUpdateInfo) b.get(0)).d > 100L)) {
            b.remove(0);
        }

        if (a) {
            if (bool) {
                paramOWorld.b(paramInt1, paramInt2, paramInt3, OBlock.aP.bk, paramOWorld.b(paramInt1, paramInt2, paramInt3));

                // hMod: Allow redstone torches to provide power
                int current = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Object[] { new Block(bk, paramInt1, paramInt2, paramInt3), 1, 0 });
                if (current == 0)
                    if (a(paramOWorld, paramInt1, paramInt2, paramInt3, true)) {
                        paramOWorld.a(paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, "random.fizz", 0.5F, 2.6F + (paramOWorld.k.nextFloat() - paramOWorld.k.nextFloat()) * 0.8F);
                        for (int i = 0; i < 5; i++) {
                            double d1 = paramInt1 + paramRandom.nextDouble() * 0.6D + 0.2D;
                            double d2 = paramInt2 + paramRandom.nextDouble() * 0.6D + 0.2D;
                            double d3 = paramInt3 + paramRandom.nextDouble() * 0.6D + 0.2D;

                            paramOWorld.a("smoke", d1, d2, d3, 0.0D, 0.0D, 0.0D);
                        }
                    }
            }
        } else if ((!bool)
                && (!a(paramOWorld, paramInt1, paramInt2, paramInt3, false))) {
            // hMod: Allow redstone torches to provide power
            int current = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Object[] { new Block(bk, paramInt1, paramInt2, paramInt3), 0, 1 });
            if (current > 0)
                paramOWorld.b(paramInt1, paramInt2, paramInt3, OBlock.aQ.bk, paramOWorld.b(paramInt1, paramInt2, paramInt3));
        }
    }

    public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        super.a(paramOWorld, paramInt1, paramInt2, paramInt3, paramInt4);
        paramOWorld.c(paramInt1, paramInt2, paramInt3, bk, b());
    }

    public boolean c(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramInt4 == 0) {
            // hMod: forced downcast!
            return b((OIBlockAccess) paramOWorld, paramInt1, paramInt2, paramInt3, paramInt4);
        }
        return false;
    }

    public int a(int paramInt, Random paramRandom) {
        return OBlock.aQ.bk;
    }

    public boolean c() {
        return true;
    }
}
