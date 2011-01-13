
import java.util.Random;

public class bp extends aa {

    private int c;
    int[] b;

    protected bp(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, mh.h, false);
        c = paramInt2;
        a(true);
    }

    public void b(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        int i = 1;
        int j = i + 1;

        if (paramfv.a(paramInt1 - j, paramInt2 - j, paramInt3 - j, paramInt1 + j, paramInt2 + j, paramInt3 + j)) {
            for (int k = -i; k <= i; k++) {
                for (int m = -i; m <= i; m++) {
                    for (int n = -i; n <= i; n++) {
                        int i1 = paramfv.a(paramInt1 + k, paramInt2 + m, paramInt3 + n);
                        if (i1 == hr.K.bi) {
                            int i2 = paramfv.b(paramInt1 + k, paramInt2 + m, paramInt3 + n);
                            paramfv.d(paramInt1 + k, paramInt2 + m, paramInt3 + n, i2 | 0x4);
                        }
                    }
                }
            }
        }
    }

    public void a(fv paramfv, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        if (paramfv.z) {
            return;
        }

        int i = paramfv.b(paramInt1, paramInt2, paramInt3);
        if ((i & 0x4) != 0) {
            int j = 4;
            int k = j + 1;

            int m = 32;
            int n = m * m;
            int i1 = m / 2;
            if (b == null) {
                b = new int[m * m * m];
            }

            if (paramfv.a(paramInt1 - k, paramInt2 - k, paramInt3 - k, paramInt1 + k, paramInt2 + k, paramInt3 + k)) {
                int i2;
                int i3;
                int i4;
                int i5;
                for (i2 = -j; i2 <= j; i2++) {
                    for (i3 = -j; i3 <= j; i3++) {
                        for (i4 = -j; i4 <= j; i4++) {
                            i5 = paramfv.a(paramInt1 + i2, paramInt2 + i3, paramInt3 + i4);
                            if (i5 == hr.J.bi) {
                                b[((i2 + i1) * n + (i3 + i1) * m + (i4 + i1))] = 0;
                            } else if (i5 == hr.K.bi) {
                                b[((i2 + i1) * n + (i3 + i1) * m + (i4 + i1))] = -2;
                            } else {
                                b[((i2 + i1) * n + (i3 + i1) * m + (i4 + i1))] = -1;
                            }
                        }
                    }
                }
                for (i2 = 1; i2 <= 4; i2++) {
                    for (i3 = -j; i3 <= j; i3++) {
                        for (i4 = -j; i4 <= j; i4++) {
                            for (i5 = -j; i5 <= j; i5++) {
                                if (b[((i3 + i1) * n + (i4 + i1) * m + (i5 + i1))] == i2 - 1) {
                                    if (b[((i3 + i1 - 1) * n + (i4 + i1) * m + (i5 + i1))] == -2) {
                                        b[((i3 + i1 - 1) * n + (i4 + i1) * m + (i5 + i1))] = i2;
                                    }
                                    if (b[((i3 + i1 + 1) * n + (i4 + i1) * m + (i5 + i1))] == -2) {
                                        b[((i3 + i1 + 1) * n + (i4 + i1) * m + (i5 + i1))] = i2;
                                    }
                                    if (b[((i3 + i1) * n + (i4 + i1 - 1) * m + (i5 + i1))] == -2) {
                                        b[((i3 + i1) * n + (i4 + i1 - 1) * m + (i5 + i1))] = i2;
                                    }
                                    if (b[((i3 + i1) * n + (i4 + i1 + 1) * m + (i5 + i1))] == -2) {
                                        b[((i3 + i1) * n + (i4 + i1 + 1) * m + (i5 + i1))] = i2;
                                    }
                                    if (b[((i3 + i1) * n + (i4 + i1) * m + (i5 + i1 - 1))] == -2) {
                                        b[((i3 + i1) * n + (i4 + i1) * m + (i5 + i1 - 1))] = i2;
                                    }
                                    if (b[((i3 + i1) * n + (i4 + i1) * m + (i5 + i1 + 1))] == -2) {
                                        b[((i3 + i1) * n + (i4 + i1) * m + (i5 + i1 + 1))] = i2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            int i2 = b[(i1 * n + i1 * m + i1)];
            if (i2 >= 0) {
                paramfv.c(paramInt1, paramInt2, paramInt3, i & 0xFFFFFFFB);
            } else {
                g(paramfv, paramInt1, paramInt2, paramInt3);
            }
        }
    }

    private void g(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        //hMod: stop leaves from decaying
        Block block = new Block(paramfv.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.LEAF_DECAY, block)) {
            a_(paramfv, paramInt1, paramInt2, paramInt3, paramfv.b(paramInt1, paramInt2, paramInt3));
            paramfv.e(paramInt1, paramInt2, paramInt3, 0);
        }
    }

    public int a(Random paramRandom) {
        return paramRandom.nextInt(16) == 0 ? 1 : 0;
    }

    public int a(int paramInt, Random paramRandom) {
        return hr.y.bi;
    }

    public boolean a() {
        return !a;
    }

    public void b(fv paramfv, int paramInt1, int paramInt2, int paramInt3, fe paramfe) {
        super.b(paramfv, paramInt1, paramInt2, paramInt3, paramfe);
    }
}
