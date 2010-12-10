import java.util.HashSet;
import java.util.Set;

public final class bw {
    // hMod: Add generic here, saves zillions of casts.
    private static Set<kh> a = new HashSet<kh>();

    protected static hs a(eq parameq, int paramInt1, int paramInt2) {
        int i = paramInt1 + parameq.l.nextInt(16);
        int j = parameq.l.nextInt(128);
        int k = paramInt2 + parameq.l.nextInt(16);

        return new hs(i, j, k);
    }

    public static final int a(eq parameq) {
        a.clear();

        for (int i = 0; i < parameq.d.size(); i++) {
            fz localfz = (fz) parameq.d.get(i);
            int k = hh.b(localfz.p / 16.0D);
            int m = hh.b(localfz.r / 16.0D);

            int n = 8;
            for (int i1 = -n; i1 <= n; i1++) {
                for (int i2 = -n; i2 <= n; i2++) {
                    a.add(new kh(i1 + k, i2 + m));
                }
            }
        }

        // hMod: Cache config spawns to classes outside of the loop
        etc config = etc.getInstance();
        Class[] mobs = new Class[config.getMonsters().length];
        Class[] animals = new Class[config.getAnimals().length];

        for (int i = 0; i < mobs.length; i++) {
            mobs[i] = hp.getEntity(config.getMonsters()[i]);
        }
        for (int i = 0; i < animals.length; i++) {
            animals[i] = hp.getEntity(config.getAnimals()[i]);
        }

        int i = 0;
        js localjs;
        for (int j = 0; j < js.values().length; j++) {
            localjs = js.values()[j];

            if (parameq.a(localjs.c) > localjs.d * a.size() / 256) {
                continue;
            }
            for (kh localkh : a) {
                // hMod: from nextInt(50) == 0 to nextInt(100) <= spawnRate, allow customisable value
                if (parameq.l.nextInt(100) <= etc.getInstance().getMobSpawnRate()) {
                    // hMod: ignore default spawns, load from config
                    Class[] arrayOfClass = null;
                    if (localjs == js.a) {
                        arrayOfClass = mobs;
                    } else if (localjs == js.b) {
                        arrayOfClass = animals;
                    } else {
                        continue;
                    }
                    
                    int i3 = parameq.l.nextInt(arrayOfClass.length);

                    hs localhs = a(parameq, localkh.a * 16, localkh.b * 16);
                    int i4 = localhs.a;
                    int i5 = localhs.b;
                    int i6 = localhs.c;

                    if ((parameq.d(i4, i5, i6)) || (parameq.c(i4, i5, i6) != jy.a)) {
                        continue;
                    }
                    int i7 = 0;

                    for (int i8 = 0; i8 < 3; i8++) {
                        int i9 = i4;
                        int i10 = i5;
                        int i11 = i6;
                        int i12 = 6;
                        for (int i13 = 0; i13 < 4; i13++) {
                            i9 += parameq.l.nextInt(i12) - parameq.l.nextInt(i12);
                            i10 += parameq.l.nextInt(1) - parameq.l.nextInt(1);
                            i11 += parameq.l.nextInt(i12) - parameq.l.nextInt(i12);

                            if ((parameq.d(i9, i10 - 1, i11)) && (!parameq.d(i9, i10, i11)) && (!parameq.c(i9, i10, i11).d()) && (!parameq.d(i9, i10 + 1, i11))) {
                                float f1 = i9 + 0.5F;
                                float f2 = i10;
                                float f3 = i11 + 0.5F;
                                if (parameq.a(f1, f2, f3, 24.0D) != null) {
                                    continue;
                                }
                                float f4 = f1 - parameq.m;
                                float f5 = f2 - parameq.n;
                                float f6 = f3 - parameq.o;
                                float f7 = f4 * f4 + f5 * f5 + f6 * f6;
                                if (f7 < 576.0F) {
                                    continue;
                                }
                                ka localka;
                                try {
                                    localka = (ka) arrayOfClass[i3].getConstructor(new Class[]{eq.class}).newInstance(new Object[]{parameq});
                                } catch (Exception localException) {
                                    localException.printStackTrace();
                                    return i;
                                }

                                localka.c(f1, f2, f3, parameq.l.nextFloat() * 360.0F, 0.0F);

                                if (localka.a()) {
                                    i7++;
                                    // hMod: allow mobs to spawn!
                                    if (!(Boolean) (etc.getLoader().callHook(PluginLoader.Hook.MOB_SPAWN, new Mob(localka)))) {
                                        parameq.a(localka);
                                        if (((localka instanceof bv)) && (parameq.l.nextInt(100) == 0)) {
                                            fd localfd = new fd(parameq);
                                            localfd.c(f1, f2, f3, localka.v, 0.0F);
                                            parameq.a(localfd);
                                            localfd.e(localka);
                                        }
                                        if (i7 >= localka.i()) {
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