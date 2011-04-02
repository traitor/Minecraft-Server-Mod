import java.util.Random;

public class OBlockButton extends OBlock {
    protected OBlockButton(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, OMaterial.n);
        a(true);
    }

    @Override
    public OAxisAlignedBB d(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    @Override
    public int b() {
        return 20;
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public boolean a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        if (paramOWorld.d(paramInt1 - 1, paramInt2, paramInt3))
            return true;
        if (paramOWorld.d(paramInt1 + 1, paramInt2, paramInt3))
            return true;
        if (paramOWorld.d(paramInt1, paramInt2, paramInt3 - 1))
            return true;
        return paramOWorld.d(paramInt1, paramInt2, paramInt3 + 1);
    }

    @Override
    public void d(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = paramOWorld.b(paramInt1, paramInt2, paramInt3);

        int j = i & 0x8;
        i &= 7;

        if ((paramInt4 == 2) && (paramOWorld.d(paramInt1, paramInt2, paramInt3 + 1)))
            i = 4;
        else if ((paramInt4 == 3) && (paramOWorld.d(paramInt1, paramInt2, paramInt3 - 1)))
            i = 3;
        else if ((paramInt4 == 4) && (paramOWorld.d(paramInt1 + 1, paramInt2, paramInt3)))
            i = 2;
        else if ((paramInt4 == 5) && (paramOWorld.d(paramInt1 - 1, paramInt2, paramInt3)))
            i = 1;
        else
            i = g(paramOWorld, paramInt1, paramInt2, paramInt3);
        paramOWorld.c(paramInt1, paramInt2, paramInt3, i + j);
    }

    private int g(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        if (paramOWorld.d(paramInt1 - 1, paramInt2, paramInt3))
            return 1;
        if (paramOWorld.d(paramInt1 + 1, paramInt2, paramInt3))
            return 2;
        if (paramOWorld.d(paramInt1, paramInt2, paramInt3 - 1))
            return 3;
        if (paramOWorld.d(paramInt1, paramInt2, paramInt3 + 1))
            return 4;
        return 1;
    }

    @Override
    public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (h(paramOWorld, paramInt1, paramInt2, paramInt3)) {
            int i = paramOWorld.b(paramInt1, paramInt2, paramInt3) & 0x7;
            int j = 0;

            if ((!paramOWorld.d(paramInt1 - 1, paramInt2, paramInt3)) && (i == 1))
                j = 1;
            if ((!paramOWorld.d(paramInt1 + 1, paramInt2, paramInt3)) && (i == 2))
                j = 1;
            if ((!paramOWorld.d(paramInt1, paramInt2, paramInt3 - 1)) && (i == 3))
                j = 1;
            if ((!paramOWorld.d(paramInt1, paramInt2, paramInt3 + 1)) && (i == 4))
                j = 1;

            if (j != 0) {
                a_(paramOWorld, paramInt1, paramInt2, paramInt3, paramOWorld.b(paramInt1, paramInt2, paramInt3));
                paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
            }
        }
    }

    private boolean h(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        if (!a(paramOWorld, paramInt1, paramInt2, paramInt3)) {
            a_(paramOWorld, paramInt1, paramInt2, paramInt3, paramOWorld.b(paramInt1, paramInt2, paramInt3));
            paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
            return false;
        }
        return true;
    }

    @Override
    public void a(OIBlockAccess paramOIBlockAccess, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramOIBlockAccess.b(paramInt1, paramInt2, paramInt3);
        int j = i & 0x7;
        int k = (i & 0x8) > 0 ? 1 : 0;

        float f1 = 0.375F;
        float f2 = 0.625F;
        float f3 = 0.1875F;
        float f4 = 0.125F;
        if (k != 0)
            f4 = 0.0625F;

        if (j == 1)
            a(0.0F, f1, 0.5F - f3, f4, f2, 0.5F + f3);
        else if (j == 2)
            a(1.0F - f4, f1, 0.5F - f3, 1.0F, f2, 0.5F + f3);
        else if (j == 3)
            a(0.5F - f3, f1, 0.0F, 0.5F + f3, f2, f4);
        else if (j == 4)
            a(0.5F - f3, f1, 1.0F - f4, 0.5F + f3, f2, 1.0F);
    }

    @Override
    public void b(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, OEntityPlayer paramOEntityPlayer) {
        a(paramOWorld, paramInt1, paramInt2, paramInt3, paramOEntityPlayer);
    }

    @Override
    public boolean a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, OEntityPlayer paramOEntityPlayer) {
        int i = paramOWorld.b(paramInt1, paramInt2, paramInt3);
        int j = i & 0x7;
        int k = 8 - (i & 0x8);
        if (k == 0)
            return true;
        
        // hMod: Allow button to provide power
        int change = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Block(bl, paramInt1, paramInt2, paramInt3), 0, 1);
        if (change == 0)
            return true;


        paramOWorld.c(paramInt1, paramInt2, paramInt3, j + k);
        paramOWorld.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);

        paramOWorld.a(paramInt1 + 0.5D, paramInt2 + 0.5D, paramInt3 + 0.5D, "random.click", 0.3F, 0.6F);

        paramOWorld.h(paramInt1, paramInt2, paramInt3, bl);
        if (j == 1)
            paramOWorld.h(paramInt1 - 1, paramInt2, paramInt3, bl);
        else if (j == 2)
            paramOWorld.h(paramInt1 + 1, paramInt2, paramInt3, bl);
        else if (j == 3)
            paramOWorld.h(paramInt1, paramInt2, paramInt3 - 1, bl);
        else if (j == 4)
            paramOWorld.h(paramInt1, paramInt2, paramInt3 + 1, bl);
        else
            paramOWorld.h(paramInt1, paramInt2 - 1, paramInt3, bl);

        paramOWorld.c(paramInt1, paramInt2, paramInt3, bl, b());

        return true;
    }

    @Override
    public void b(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramOWorld.b(paramInt1, paramInt2, paramInt3);
        if ((i & 0x8) > 0) {
            paramOWorld.h(paramInt1, paramInt2, paramInt3, bl);
            int j = i & 0x7;
            if (j == 1)
                paramOWorld.h(paramInt1 - 1, paramInt2, paramInt3, bl);
            else if (j == 2)
                paramOWorld.h(paramInt1 + 1, paramInt2, paramInt3, bl);
            else if (j == 3)
                paramOWorld.h(paramInt1, paramInt2, paramInt3 - 1, bl);
            else if (j == 4)
                paramOWorld.h(paramInt1, paramInt2, paramInt3 + 1, bl);
            else
                paramOWorld.h(paramInt1, paramInt2 - 1, paramInt3, bl);
        }
        super.b(paramOWorld, paramInt1, paramInt2, paramInt3);
    }

    @Override
    public boolean b(OIBlockAccess paramOIBlockAccess, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        return (paramOIBlockAccess.b(paramInt1, paramInt2, paramInt3) & 0x8) > 0;
    }

    @Override
    public boolean c(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = paramOWorld.b(paramInt1, paramInt2, paramInt3);
        if ((i & 0x8) == 0)
            return false;
        int j = i & 0x7;

        if ((j == 5) && (paramInt4 == 1))
            return true;
        if ((j == 4) && (paramInt4 == 2))
            return true;
        if ((j == 3) && (paramInt4 == 3))
            return true;
        if ((j == 2) && (paramInt4 == 4))
            return true;
        return (j == 1) && (paramInt4 == 5);
    }

    @Override
    public boolean c() {
        return true;
    }

    @Override
    public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        if (paramOWorld.t)
            return;
        int i = paramOWorld.b(paramInt1, paramInt2, paramInt3);
        if ((i & 0x8) == 0)
            return;
        
        // hMod: Allow button to provide power
        int change = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Block(bl, paramInt1, paramInt2, paramInt3), 1, 0);
        if (change > 0)
            return;

        
        paramOWorld.c(paramInt1, paramInt2, paramInt3, i & 0x7);

        paramOWorld.h(paramInt1, paramInt2, paramInt3, bl);
        int j = i & 0x7;
        if (j == 1)
            paramOWorld.h(paramInt1 - 1, paramInt2, paramInt3, bl);
        else if (j == 2)
            paramOWorld.h(paramInt1 + 1, paramInt2, paramInt3, bl);
        else if (j == 3)
            paramOWorld.h(paramInt1, paramInt2, paramInt3 - 1, bl);
        else if (j == 4)
            paramOWorld.h(paramInt1, paramInt2, paramInt3 + 1, bl);
        else
            paramOWorld.h(paramInt1, paramInt2 - 1, paramInt3, bl);

        paramOWorld.a(paramInt1 + 0.5D, paramInt2 + 0.5D, paramInt3 + 0.5D, "random.click", 0.3F, 0.5F);
        paramOWorld.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);
    }
}