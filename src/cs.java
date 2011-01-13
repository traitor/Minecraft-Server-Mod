
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public final class cs {

    private static Set<mr> a = new HashSet<mr>();

    protected static jq a(fv paramfv, int paramInt1, int paramInt2) {
        int i = paramInt1 + paramfv.l.nextInt(16);
        int j = paramfv.l.nextInt(128);
        int k = paramInt2 + paramfv.l.nextInt(16);

        return new jq(i, j, k);
    }

    public static final int a(fv paramfv, boolean paramBoolean1, boolean paramBoolean2) {
        if ((!paramBoolean1) && (!paramBoolean2)) {
            return 0;
        }

        a.clear();
        Object localObject;
        int j;
        int k;
        for (int i = 0; i < paramfv.d.size(); i++) {
            localObject = (hl) paramfv.d.get(i);
            j = iz.b(((hl) localObject).p / 16.0D);
            k = iz.b(((hl) localObject).r / 16.0D);

            int m = 8;
            for (int n = -m; n <= m; n++) {
                for (int i1 = -m; i1 <= m; i1++) {
                    a.add(new mr(n + j, i1 + k));
                }
            }
        }
        int i = 0;
        ma localma;
        for (j = 0; j < ma.values().length; j++) {
            localma = ma.values()[j];
            if (((localma.d()) && (!paramBoolean2)) || ((!localma.d()) && (!paramBoolean1))) {
                continue;
            }
            if (paramfv.a(localma.a()) > localma.b() * a.size() / 256) {
                continue;
            }
            for (mr localmr : a) {
                kt localkt = paramfv.a().a(localmr);
                Class[] arrayOfClass = localkt.a(localma);
                if ((arrayOfClass == null) || (arrayOfClass.length == 0)) {
                    continue;
                }
                int i2 = paramfv.l.nextInt(arrayOfClass.length);

                jq localjq = a(paramfv, localmr.a * 16, localmr.b * 16);
                int i3 = localjq.a;
                int i4 = localjq.b;
                int i5 = localjq.c;

                if ((paramfv.d(i3, i4, i5))
                        || (paramfv.c(i3, i4, i5) != localma.c())) {
                    continue;
                }
                int i6 = 0;

                for (int i7 = 0; i7 < 3; i7++) {
                    int i8 = i3;
                    int i9 = i4;
                    int i10 = i5;
                    int i11 = 6;
                    for (int i12 = 0; i12 < 4; i12++) {
                        i8 += paramfv.l.nextInt(i11) - paramfv.l.nextInt(i11);
                        i9 += paramfv.l.nextInt(1) - paramfv.l.nextInt(1);
                        i10 += paramfv.l.nextInt(i11) - paramfv.l.nextInt(i11);

                        if (a(localma, paramfv, i8, i9, i10)) {
                            float f1 = i8 + 0.5F;
                            float f2 = i9;
                            float f3 = i10 + 0.5F;
                            if (paramfv.a(f1, f2, f3, 24.0D) != null) {
                                continue;
                            }
                            float f4 = f1 - paramfv.m;
                            float f5 = f2 - paramfv.n;
                            float f6 = f3 - paramfv.o;
                            float f7 = f4 * f4 + f5 * f5 + f6 * f6;
                            if (f7 < 576.0F) {
                                continue;
                            }
                            mj localmj;
                            try {
                                localmj = (mj) arrayOfClass[i2].getConstructor(new Class[]{fv.class}).newInstance(new Object[]{paramfv});
                            } catch (Exception localException) {
                                localException.printStackTrace();
                                return i;
                            }

                            localmj.c(f1, f2, f3, paramfv.l.nextFloat() * 360.0F, 0.0F);

                            if (localmj.b()) {
                                i6++;
                                paramfv.a(localmj);
                                a(localmj, paramfv, f1, f2, f3);
                                if (i6 >= localmj.j()) {
                                    break;
                                }
                            }
                            i += i6;
                        }
                    }
                }
            }
        }
        return i;
    }

    private static boolean a(ma paramma, fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        if (paramma.c() == mh.f) {
            return (paramfv.c(paramInt1, paramInt2, paramInt3).d()) && (!paramfv.d(paramInt1, paramInt2 + 1, paramInt3));
        }
        return (paramfv.d(paramInt1, paramInt2 - 1, paramInt3)) && (!paramfv.d(paramInt1, paramInt2, paramInt3)) && (!paramfv.c(paramInt1, paramInt2, paramInt3).d()) && (!paramfv.d(paramInt1, paramInt2 + 1, paramInt3));
    }

    private static void a(mj parammj, fv paramfv, float paramFloat1, float paramFloat2, float paramFloat3) {
        // hMod: allow mobs to spawn!
        if (!(Boolean) (etc.getLoader().callHook(PluginLoader.Hook.MOB_SPAWN, new Mob(parammj)))) {
            if (((parammj instanceof cr)) && (paramfv.l.nextInt(100) == 0)) {
                gj localgj = new gj(paramfv);
                localgj.c(paramFloat1, paramFloat2, paramFloat3, parammj.v, 0.0F);
                paramfv.a(localgj);
                localgj.e(parammj);
            } else if ((parammj instanceof dv)) {
                ((dv) parammj).a(dv.a(paramfv.l));
            }
        }
    }
}
