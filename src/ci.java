
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public final class ci {

    // hMod: Add generic here, saves zillions of casts.
    private static Set<lk> a = new HashSet<lk>();

    protected static iq a(ff paramff, int paramInt1, int paramInt2) {
        int i = paramInt1 + paramff.l.nextInt(16);
        int j = paramff.l.nextInt(128);
        int k = paramInt2 + paramff.l.nextInt(16);

        return new iq(i, j, k);
    }

    public static final int a(ff paramff) {
        a.clear();

        for (int i = 0; i < paramff.d.size(); i++) {
            gq localgp = (gq) paramff.d.get(i);
            int k = ic.b(localgp.p / 16.0D);
            int m = ic.b(localgp.r / 16.0D);

            int n = 8;
            for (int i1 = -n; i1 <= n; i1++) {
                for (int i2 = -n; i2 <= n; i2++) {
                    a.add(new lk(i1 + k, i2 + m));
                }
            }
        }
        
        // hMod: Cache config spawns to classes outside of the loop
        etc config = etc.getInstance();
        Class[] mobs = new Class[config.getMonsters().length];
        Class[] animals = new Class[config.getAnimals().length];

        for (int i = 0; i < mobs.length; i++) {
            mobs[i] = in.getEntity(config.getMonsters()[i]);
        }
        for (int i = 0; i < animals.length; i++) {
            animals[i] = in.getEntity(config.getAnimals()[i]);
        }

        int i = 0;
        ku localkt;
        for (int j = 0; j < ku.values().length; j++) {
            localkt = ku.values()[j];

            if (paramff.a(localkt.c) > localkt.d * a.size() / 256) {
                continue;
            }
            for (lk locallj : a) {
                // hMod: from nextInt(50) == 0 to nextInt(100) <= spawnRate, allow customisable value
                if (paramff.l.nextInt(100) <= etc.getInstance().getMobSpawnRate()) {
                    // hMod: ignore default spawns, load from config
                    Class[] arrayOfClass = null;
                    if (localkt == ku.a) {
                        arrayOfClass = mobs;
                    } else if (localkt == ku.b) {
                        arrayOfClass = animals;
                    } else {
                        continue;
                    }
                    
                    int i3 = paramff.l.nextInt(arrayOfClass.length);

                    iq localip = a(paramff, locallj.a * 16, locallj.b * 16);
                    int i4 = localip.a;
                    int i5 = localip.b;
                    int i6 = localip.c;

                    if ((paramff.d(i4, i5, i6))
                            || (paramff.c(i4, i5, i6) != la.a)) {
                        continue;
                    }
                    int i7 = 0;

                    for (int i8 = 0;; i8++) {
                        if (i8 >= 3) {
                            break;
                        }
                        int i9 = i4;
                        int i10 = i5;
                        int i11 = i6;
                        int i12 = 6;
                        for (int i13 = 0;; i13++) {
                            if (i13 >= 4) {
                                break;
                            }
                            i9 += paramff.l.nextInt(i12) - paramff.l.nextInt(i12);
                            i10 += paramff.l.nextInt(1) - paramff.l.nextInt(1);
                            i11 += paramff.l.nextInt(i12) - paramff.l.nextInt(i12);

                            if ((paramff.d(i9, i10 - 1, i11)) && (!paramff.d(i9, i10, i11)) && (!paramff.c(i9, i10, i11).d()) && (!paramff.d(i9, i10 + 1, i11))) {
                                float f1 = i9 + 0.5F;
                                float f2 = i10;
                                float f3 = i11 + 0.5F;
                                if (paramff.a(f1, f2, f3, 24.0D) != null) {
                                    continue;
                                }
                                float f4 = f1 - paramff.m;
                                float f5 = f2 - paramff.n;
                                float f6 = f3 - paramff.o;
                                float f7 = f4 * f4 + f5 * f5 + f6 * f6;
                                if (f7 < 576.0F) {
                                    continue;
                                }
                                lc locallb = null;
                                try {
                                    //hMod : make sure we have something to spawn before fetching calling newInstance.
                                    if(arrayOfClass != null && arrayOfClass[i3] != null) {
                                        locallb = (lc) arrayOfClass[i3].getConstructor(new Class[]{ff.class}).newInstance(new Object[]{paramff});
                                    }
                                } catch (Exception localException) {
                                    localException.printStackTrace();
                                    return i;
                                }

                                if(locallb == null) continue;

                                locallb.c(f1, f2, f3, paramff.l.nextFloat() * 360.0F, 0.0F);

                                if (locallb.a()) {
                                    // hMod: allow mobs to spawn!
                                    if (!(Boolean) (etc.getLoader().callHook(PluginLoader.Hook.MOB_SPAWN, new Mob(locallb)))) {
                                        i7++;
                                        paramff.a(locallb);
                                        if (((locallb instanceof ch)) && (paramff.l.nextInt(100) == 0)) {
                                            ft localfs = new ft(paramff);
                                            localfs.c(f1, f2, f3, locallb.v, 0.0F);
                                            paramff.a(localfs);
                                            localfs.e(locallb);
                                        }
                                        if (i7 >= locallb.i()) {
                                            break;
                                        }
                                    }
                                }
                                i += i7;
                            }
                        }
                    }
                }
            }
        }
        return i;
    }
}
