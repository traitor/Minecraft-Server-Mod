import java.util.Random;

public class OBlockSapling extends OBlockFlower {
    protected OBlockSapling(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2);

        float f = 0.4F;
        a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
    }

    @Override
    public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        super.a(paramOWorld, paramInt1, paramInt2, paramInt3, paramRandom);

        if ((paramOWorld.j(paramInt1, paramInt2 + 1, paramInt3) >= 9) && (paramRandom.nextInt(5) == 0)) {
            int i = paramOWorld.b(paramInt1, paramInt2, paramInt3);
            if (i < 15)
                paramOWorld.c(paramInt1, paramInt2, paramInt3, i + 1);
            else
                b(paramOWorld, paramInt1, paramInt2, paramInt3, paramRandom);
        }
    }

    public void b(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        paramOWorld.b(paramInt1, paramInt2, paramInt3, 0);
        Object localObject = new OWorldGenTrees();
        if (paramRandom.nextInt(10) == 0)
            localObject = new OWorldGenBigTree();
        if (!((OWorldGenerator) localObject).a(paramOWorld, paramRandom, paramInt1, paramInt2, paramInt3))
            paramOWorld.b(paramInt1, paramInt2, paramInt3, bl);
    }
}