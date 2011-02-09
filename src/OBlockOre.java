import java.util.Random;

public class OBlockOre extends OBlock {
    public OBlockOre(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, OMaterial.d);
    }

    public int a(int paramInt, Random paramRandom) {
        if (bi == OBlock.I.bi)
            return OItem.k.ba;
        if (bi == OBlock.aw.bi)
            return OItem.l.ba;
        if (bi == OBlock.N.bi)
            return OItem.aU.ba;
        return bi;
    }

    public int a(Random paramRandom) {
        if(bi == Block.Type.LapisLazuliOre.getType())
            return 4 + paramRandom.nextInt(5);
        
        return 1;
    }

    protected int b(int paramInt) {
        if (bi == OBlock.N.bi)
            return 4;
        return 0;
    }
}