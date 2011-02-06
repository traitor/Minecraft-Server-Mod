import java.util.Random;

public class OMobSpawnerBase {
    public static final OMobSpawnerBase a = new OMobSpawnerRainforest().b(588342).a("Rainforest").a(2094168);
    public static final OMobSpawnerBase b = new OMobSpawnerSwamp().b(522674).a("Swampland").a(9154376);
    public static final OMobSpawnerBase c = new OMobSpawnerBase().b(10215459).a("Seasonal Forest");
    public static final OMobSpawnerBase d = new OMobSpawnerForest().b(353825).a("Forest").a(5159473);
    public static final OMobSpawnerBase e = new OMobSpawnerDesert().b(14278691).a("Savanna");
    public static final OMobSpawnerBase f = new OMobSpawnerBase().b(10595616).a("Shrubland");
    public static final OMobSpawnerBase g = new OMobSpawnerTaiga().b(3060051).a("Taiga").b().a(8107825);
    public static final OMobSpawnerBase h = new OMobSpawnerDesert().b(16421912).a("Desert");
    public static final OMobSpawnerBase i = new OMobSpawnerDesert().b(16767248).a("Plains");
    public static final OMobSpawnerBase j = new OMobSpawnerDesert().b(16772499).a("Ice Desert").b().a(12899129);
    public static final OMobSpawnerBase k = new OMobSpawnerBase().b(5762041).a("Tundra").b().a(12899129);

    public static final OMobSpawnerBase l = new OMobSpawnerHell().b(16711680).a("Hell");
    public String                       m;
    public int                          n;
    public byte                         o = (byte) OBlock.u.bi;
    public byte                         p = (byte) OBlock.v.bi;
    public int                          q = 5169201;

    protected Class<?>[]                r;
    // protected Class[] r = { OEntitySpider.class, OEntityZombie.class,
    // OEntitySkeleton.class, OEntityCreeper.class };

    protected Class<?>[]                s;
    // protected Class[] s = { OEntitySheep.class, OEntityPig.class,
    // OEntityChicken.class, OEntityCow.class };

    protected Class<?>[]                t;
    // protected Class[] t = { OEntitySquid.class };

    private static OMobSpawnerBase[]    u = new OMobSpawnerBase[4096];

    public static void a() {
        for (int i1 = 0; i1 < 64; i1++) {
            for (int i2 = 0; i2 < 64; i2++) {
                u[(i1 + i2 * 64)] = a(i1 / 63.0F, i2 / 63.0F);
            }
        }

        h.o = (h.p = (byte) OBlock.E.bi);
        j.o = (j.p = (byte) OBlock.E.bi);
    }

    public OWorldGenerator a(Random paramRandom) {
        if (paramRandom.nextInt(10) == 0) {
            return new OWorldGenBigTree();
        }
        return new OWorldGenTrees();
    }

    protected OMobSpawnerBase b() {
        return this;
    }

    protected OMobSpawnerBase a(String paramString) {
        m = paramString;
        return this;
    }

    protected OMobSpawnerBase a(int paramInt) {
        q = paramInt;
        return this;
    }

    protected OMobSpawnerBase b(int paramInt) {
        n = paramInt;
        return this;
    }

    public static OMobSpawnerBase a(double paramDouble1, double paramDouble2) {
        int i1 = (int) (paramDouble1 * 63.0D);
        int i2 = (int) (paramDouble2 * 63.0D);
        return u[(i1 + i2 * 64)];
    }

    public static OMobSpawnerBase a(float paramFloat1, float paramFloat2) {
        paramFloat2 *= paramFloat1;
        if (paramFloat1 < 0.1F)
            return k;
        if (paramFloat2 < 0.2F) {
            if (paramFloat1 < 0.5F)
                return k;
            if (paramFloat1 < 0.95F) {
                return e;
            }
            return h;
        }
        if ((paramFloat2 > 0.5F) && (paramFloat1 < 0.7F))
            return b;
        if (paramFloat1 < 0.5F)
            return g;
        if (paramFloat1 < 0.97F) {
            if (paramFloat2 < 0.35F) {
                return f;
            }
            return d;
        }

        if (paramFloat2 < 0.45F)
            return i;
        if (paramFloat2 < 0.9F) {
            return c;
        }
        return a;
    }

    public Class<?>[] a(OEnumCreatureType paramOEnumCreatureType) {
        etc config = etc.getInstance();
        if (paramOEnumCreatureType == OEnumCreatureType.a)
            return config.getMonstersClass();
        if (paramOEnumCreatureType == OEnumCreatureType.b)
            return config.getAnimalsClass();
        if (paramOEnumCreatureType == OEnumCreatureType.c)
            return config.getWaterAnimalsClass();
        return null;
    }

    static {
        a();
    }
}