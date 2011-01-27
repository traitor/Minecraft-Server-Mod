import java.util.HashSet;
import java.util.Set;

public final class OSpawnerAnimals {

    // hMod: Add generic here, saves zillions of casts.
    private static Set<OChunkCoordIntPair> a = new HashSet<OChunkCoordIntPair>();

    protected static OChunkPosition a(OWorld paramOWorld, int paramInt1, int paramInt2) {
        int i = paramInt1 + paramOWorld.l.nextInt(16);
        int j = paramOWorld.l.nextInt(128);
        int k = paramInt2 + paramOWorld.l.nextInt(16);

        return new OChunkPosition(i, j, k);
    }

    public static final int a(OWorld paramOWorld, boolean paramBoolean1, boolean paramBoolean2) {
        if ((!paramBoolean1) && (!paramBoolean2))
            return 0;

        a.clear();
        Object localObject;
        int j;
        int k;
        for (int i = 0; i < paramOWorld.d.size(); i++) {
            localObject = paramOWorld.d.get(i);
            j = OMathHelper.b(((OEntityPlayer) localObject).p / 16.0D);
            k = OMathHelper.b(((OEntityPlayer) localObject).r / 16.0D);

            int m = 8;
            for (int n = -m; n <= m; n++)
                for (int i1 = -m; i1 <= m; i1++)
                    a.add(new OChunkCoordIntPair(n + j, i1 + k));
        }

        // hMod: Cache config spawns to classes outside of the loop
        etc config = etc.getInstance();
        Class[] mobs = new Class[config.getMonsters().length];
        Class[] animals = new Class[config.getAnimals().length];
        Class[] waterAnimals = new Class[config.getWaterAnimals().length];

        for (int i = 0; i < mobs.length; i++)
            mobs[i] = OEntityList.getEntity(config.getMonsters()[i]);
        for (int i = 0; i < animals.length; i++)
            animals[i] = OEntityList.getEntity(config.getAnimals()[i]);
        for (int i = 0; i < waterAnimals.length; i++)
            waterAnimals[i] = OEntityList.getEntity(config.getWaterAnimals()[i]);

        int i = 0;
        OEnumCreatureType localOEnumCreatureType;
        for (j = 0; j < OEnumCreatureType.values().length; j++) {
            localOEnumCreatureType = OEnumCreatureType.values()[j];
            if (((localOEnumCreatureType.d()) && (!paramBoolean2)) || ((!localOEnumCreatureType.d()) && (!paramBoolean1)))
                continue;
            if (paramOWorld.a(localOEnumCreatureType.a()) > localOEnumCreatureType.b() * a.size() / 256)
                continue;
            for (OChunkCoordIntPair localOChunkCoordIntPair : a) {
                // OMobSpawnerBase localOMobSpawnerBase =
                // paramOWorld.a().a(localOChunkCoordIntPair);
                // Class[] arrayOfClass =
                // localOMobSpawnerBase.a(localOEnumCreatureType);
                Class[] arrayOfClass = null;
                // Monsters
                if (localOEnumCreatureType == OEnumCreatureType.a)
                    arrayOfClass = mobs;
                // Animals
                else if (localOEnumCreatureType == OEnumCreatureType.b)
                    arrayOfClass = animals;
                // Water mobs
                else if (localOEnumCreatureType == OEnumCreatureType.c)
                    arrayOfClass = waterAnimals;

                if ((arrayOfClass == null) || (arrayOfClass.length == 0))
                    continue;
                int i2 = paramOWorld.l.nextInt(arrayOfClass.length);

                OChunkPosition localOChunkPosition = a(paramOWorld, localOChunkCoordIntPair.a * 16, localOChunkCoordIntPair.b * 16);
                int i3 = localOChunkPosition.a;
                int i4 = localOChunkPosition.b;
                int i5 = localOChunkPosition.c;

                if ((paramOWorld.d(i3, i4, i5)) || (paramOWorld.c(i3, i4, i5) != localOEnumCreatureType.c()))
                    continue;
                int i6 = 0;

                for (int i7 = 0; i7 < 3; i7++) {
                    int i8 = i3;
                    int i9 = i4;
                    int i10 = i5;
                    int i11 = 6;
                    for (int i12 = 0; i12 < 4; i12++) {
                        i8 += paramOWorld.l.nextInt(i11) - paramOWorld.l.nextInt(i11);
                        i9 += paramOWorld.l.nextInt(1) - paramOWorld.l.nextInt(1);
                        i10 += paramOWorld.l.nextInt(i11) - paramOWorld.l.nextInt(i11);

                        if (a(localOEnumCreatureType, paramOWorld, i8, i9, i10)) {
                            float f1 = i8 + 0.5F;
                            float f2 = i9;
                            float f3 = i10 + 0.5F;
                            if (paramOWorld.a(f1, f2, f3, 24.0D) != null)
                                continue;
                            float f4 = f1 - paramOWorld.m;
                            float f5 = f2 - paramOWorld.n;
                            float f6 = f3 - paramOWorld.o;
                            float f7 = f4 * f4 + f5 * f5 + f6 * f6;
                            // hMod: mobs can't spawn to close to the spawn ?
                            if (f7 < 576.0F)
                                continue;
                            OEntityLiving localOEntityLiving;
                            try {
                                localOEntityLiving = (OEntityLiving) arrayOfClass[i2].getConstructor(new Class[] { OWorld.class }).newInstance(new Object[] { paramOWorld });
                            } catch (Exception localException) {
                                localException.printStackTrace();
                                return i;
                            }

                            // hMod : make sure we have something to spawn
                            // before trying to spawn anything
                            if (localOEntityLiving == null)
                                continue;

                            localOEntityLiving.c(f1, f2, f3, paramOWorld.l.nextFloat() * 360.0F, 0.0F);

                            if (localOEntityLiving.b())
                                // hMod: allow mobs to spawn!
                                if (!(Boolean) (etc.getLoader().callHook(PluginLoader.Hook.MOB_SPAWN, new Mob(localOEntityLiving)))) {
                                    i6++;
                                    paramOWorld.a(localOEntityLiving);
                                    a(localOEntityLiving, paramOWorld, f1, f2, f3);
                                    if (i6 >= localOEntityLiving.j())
                                        break;
                                }
                            i += i6;
                        }
                    }
                }
            }
        }
        return i;
    }

    private static boolean a(OEnumCreatureType paramOEnumCreatureType, OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        if (paramOEnumCreatureType.c() == OMaterial.f)
            return (paramOWorld.c(paramInt1, paramInt2, paramInt3).d()) && (!paramOWorld.d(paramInt1, paramInt2 + 1, paramInt3));
        return (paramOWorld.d(paramInt1, paramInt2 - 1, paramInt3)) && (!paramOWorld.d(paramInt1, paramInt2, paramInt3)) && (!paramOWorld.c(paramInt1, paramInt2, paramInt3).d()) && (!paramOWorld.d(paramInt1, paramInt2 + 1, paramInt3));
    }

    private static void a(OEntityLiving paramOEntityLiving, OWorld paramOWorld, float paramFloat1, float paramFloat2, float paramFloat3) {
        if (((paramOEntityLiving instanceof OEntitySpider)) && (paramOWorld.l.nextInt(100) == 0)) {
            OEntitySkeleton localOEntitySkeleton = new OEntitySkeleton(paramOWorld);
            localOEntitySkeleton.c(paramFloat1, paramFloat2, paramFloat3, paramOEntityLiving.v, 0.0F);
            paramOWorld.a(localOEntitySkeleton);
            localOEntitySkeleton.e(paramOEntityLiving);
        } else if ((paramOEntityLiving instanceof OEntitySheep))
            ((OEntitySheep) paramOEntityLiving).a(OEntitySheep.a(paramOWorld.l));
    }
}
