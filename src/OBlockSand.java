import java.util.Random;

public class OBlockSand extends OBlock {

    public static boolean a = false;

    public OBlockSand(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, OMaterial.m);
    }

    @Override
    public void e(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        // hMod: Physics
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Block(bl, paramInt1, paramInt2, paramInt3), true))
            paramOWorld.c(paramInt1, paramInt2, paramInt3, bl, b());
    }

    @Override
    public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        // hMod: Physics
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Block(bl, paramInt1, paramInt2, paramInt3), true))
            paramOWorld.c(paramInt1, paramInt2, paramInt3, bl, b());
    }

    @Override
    public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        g(paramOWorld, paramInt1, paramInt2, paramInt3);
    }

    private void g(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramInt1;
        int j = paramInt2;
        int k = paramInt3;
        if ((b_(paramOWorld, i, j - 1, k)) && (j >= 0)) {
            int m = 32;
            if ((a) || (!paramOWorld.a(paramInt1 - m, paramInt2 - m, paramInt3 - m, paramInt1 + m, paramInt2 + m, paramInt3 + m))) {
                paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
                while ((b_(paramOWorld, paramInt1, paramInt2 - 1, paramInt3)) && (paramInt2 > 0))
                    paramInt2--;
                if (paramInt2 > 0)
                    paramOWorld.e(paramInt1, paramInt2, paramInt3, bl);
            } else {
                OEntityFallingSand localOEntityFallingSand = new OEntityFallingSand(paramOWorld, paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, bl);
                paramOWorld.a(localOEntityFallingSand);
            }
        }
    }

    @Override
    public int b() {
        return 3;
    }

    public static boolean b_(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramOWorld.a(paramInt1, paramInt2, paramInt3);
        if (i == 0)
            return true;
        if (i == OBlock.ar.bl)
            return true;
        OMaterial localOMaterial = OBlock.m[i].bw;
        if (localOMaterial == OMaterial.f)
            return true;
        return localOMaterial == OMaterial.g;
    }
}
