import java.util.Random;

public class OBlockCactus extends OBlock {
    protected OBlockCactus(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, OMaterial.u);
        a(true);
    }

    @Override
    public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        if (paramOWorld.e(paramInt1, paramInt2 + 1, paramInt3)) {
            int i = 1;
            while (paramOWorld.a(paramInt1, paramInt2 - i, paramInt3) == bl)
                i++;
            if (i < 3) {
                int j = paramOWorld.b(paramInt1, paramInt2, paramInt3);
                if (j == 15) {
                    paramOWorld.e(paramInt1, paramInt2 + 1, paramInt3, bl);
                    paramOWorld.c(paramInt1, paramInt2, paramInt3, 0);
                } else
                    paramOWorld.c(paramInt1, paramInt2, paramInt3, j + 1);
            }
        }
    }

    @Override
    public OAxisAlignedBB d(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        float f = 0.0625F;
        return OAxisAlignedBB.b(paramInt1 + f, paramInt2, paramInt3 + f, paramInt1 + 1 - f, paramInt2 + 1 - f, paramInt3 + 1 - f);
    }

    @Override
    public int a(int paramInt) {
        if (paramInt == 1)
            return bk - 1;
        if (paramInt == 0)
            return bk + 1;
        return bk;
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public boolean a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        if (!super.a(paramOWorld, paramInt1, paramInt2, paramInt3))
            return false;

        return f(paramOWorld, paramInt1, paramInt2, paramInt3);
    }

    @Override
    public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (!f(paramOWorld, paramInt1, paramInt2, paramInt3)) {
            a_(paramOWorld, paramInt1, paramInt2, paramInt3, paramOWorld.b(paramInt1, paramInt2, paramInt3));
            paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
        }
    }

    @Override
    public boolean f(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        if (paramOWorld.c(paramInt1 - 1, paramInt2, paramInt3).a())
            return false;
        if (paramOWorld.c(paramInt1 + 1, paramInt2, paramInt3).a())
            return false;
        if (paramOWorld.c(paramInt1, paramInt2, paramInt3 - 1).a())
            return false;
        if (paramOWorld.c(paramInt1, paramInt2, paramInt3 + 1).a())
            return false;
        int i = paramOWorld.a(paramInt1, paramInt2 - 1, paramInt3);
        return (i == OBlock.aV.bl) || (i == OBlock.E.bl);
    }

    @Override
    public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, OEntity paramOEntity) {
        // hMod Damage hook: Cactus
        if (paramOEntity instanceof OEntityLiving && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.CACTUS, null, new LivingEntity((OEntityLiving) paramOEntity), 1))
            return;
        paramOEntity.a((OEntity) null, 1);

    }
}