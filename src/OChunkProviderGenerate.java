import java.util.Random;

public class OChunkProviderGenerate implements OIChunkProvider {

    private Random                 j;
    private ONoiseGeneratorOctaves k;
    private ONoiseGeneratorOctaves l;
    private ONoiseGeneratorOctaves m;
    private ONoiseGeneratorOctaves n;
    private ONoiseGeneratorOctaves o;
    public ONoiseGeneratorOctaves  a;
    public ONoiseGeneratorOctaves  b;
    public ONoiseGeneratorOctaves  c;
    private OWorld                 p;
    private double[]               q;
    private double[]               r = new double[256];
    private double[]               s = new double[256];
    private double[]               t = new double[256];
    private OMapGenBase            u = new OMapGenCaves();
    private OMobSpawnerBase[]      v;
    double[]                       d;
    double[]                       e;
    double[]                       f;
    double[]                       g;
    double[]                       h;
    int[][]                        i = new int[32][32];
    private double[]               w;

    public OChunkProviderGenerate(OWorld paramOWorld, long paramLong) {
        p = paramOWorld;

        j = new Random(paramLong);
        k = new ONoiseGeneratorOctaves(j, 16);
        l = new ONoiseGeneratorOctaves(j, 16);
        m = new ONoiseGeneratorOctaves(j, 8);
        n = new ONoiseGeneratorOctaves(j, 4);
        o = new ONoiseGeneratorOctaves(j, 4);

        a = new ONoiseGeneratorOctaves(j, 10);
        b = new ONoiseGeneratorOctaves(j, 16);

        c = new ONoiseGeneratorOctaves(j, 8);
    }

    public void a(int paramInt1, int paramInt2, byte[] paramArrayOfByte, OMobSpawnerBase[] paramArrayOfOMobSpawnerBase, double[] paramArrayOfDouble) {
        int i1 = 4;
        int i2 = 64;

        int i3 = i1 + 1;
        int i4 = 17;
        int i5 = i1 + 1;
        q = a(q, paramInt1 * i1, 0, paramInt2 * i1, i3, i4, i5);

        for (int i6 = 0; i6 < i1; i6++)
            for (int i7 = 0; i7 < i1; i7++)
                for (int i8 = 0; i8 < 16; i8++) {
                    double d1 = 0.125D;
                    double d2 = q[(((i6 + 0) * i5 + (i7 + 0)) * i4 + (i8 + 0))];
                    double d3 = q[(((i6 + 0) * i5 + (i7 + 1)) * i4 + (i8 + 0))];
                    double d4 = q[(((i6 + 1) * i5 + (i7 + 0)) * i4 + (i8 + 0))];
                    double d5 = q[(((i6 + 1) * i5 + (i7 + 1)) * i4 + (i8 + 0))];

                    double d6 = (q[(((i6 + 0) * i5 + (i7 + 0)) * i4 + (i8 + 1))] - d2) * d1;
                    double d7 = (q[(((i6 + 0) * i5 + (i7 + 1)) * i4 + (i8 + 1))] - d3) * d1;
                    double d8 = (q[(((i6 + 1) * i5 + (i7 + 0)) * i4 + (i8 + 1))] - d4) * d1;
                    double d9 = (q[(((i6 + 1) * i5 + (i7 + 1)) * i4 + (i8 + 1))] - d5) * d1;

                    for (int i9 = 0; i9 < 8; i9++) {
                        double d10 = 0.25D;

                        double d11 = d2;
                        double d12 = d3;
                        double d13 = (d4 - d2) * d10;
                        double d14 = (d5 - d3) * d10;

                        for (int i10 = 0; i10 < 4; i10++) {
                            int i11 = i10 + i6 * 4 << 11 | 0 + i7 * 4 << 7 | i8 * 8 + i9;
                            int i12 = 128;
                            double d15 = 0.25D;

                            double d16 = d11;
                            double d17 = (d12 - d11) * d15;
                            for (int i13 = 0; i13 < 4; i13++) {
                                double d18 = paramArrayOfDouble[((i6 * 4 + i10) * 16 + (i7 * 4 + i13))];
                                int i14 = 0;
                                if (i8 * 8 + i9 < i2)
                                    if ((d18 < 0.5D) && (i8 * 8 + i9 >= i2 - 1))
                                        i14 = OBlock.aT.bi;
                                    else
                                        i14 = OBlock.B.bi;
                                if (d16 > 0.0D)
                                    i14 = OBlock.t.bi;

                                paramArrayOfByte[i11] = (byte) i14;
                                i11 += i12;
                                d16 += d17;
                            }
                            d11 += d13;
                            d12 += d14;
                        }

                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                        d5 += d9;
                    }
                }
    }

    public void a(int paramInt1, int paramInt2, byte[] paramArrayOfByte, OMobSpawnerBase[] paramArrayOfOMobSpawnerBase) {
        int i1 = 64;

        double d1 = 0.03125D;
        r = n.a(r, paramInt1 * 16, paramInt2 * 16, 0.0D, 16, 16, 1, d1, d1, 1.0D);
        // hMod fix chunk generation
        s = n.a(s, paramInt1 * 16, 109.0134D, paramInt2 * 16, 16, 1, 16, d1, 1.0D, d1);
        t = o.a(t, paramInt1 * 16, paramInt2 * 16, 0.0D, 16, 16, 1, d1 * 2.0D, d1 * 2.0D, d1 * 2.0D);

        for (int i2 = 0; i2 < 16; i2++)
            for (int i3 = 0; i3 < 16; i3++) {
                OMobSpawnerBase localOMobSpawnerBase = paramArrayOfOMobSpawnerBase[(i2 + i3 * 16)];
                int i4 = r[(i2 + i3 * 16)] + j.nextDouble() * 0.2D > 0.0D ? 1 : 0;
                int i5 = s[(i2 + i3 * 16)] + j.nextDouble() * 0.2D > 3.0D ? 1 : 0;
                int i6 = (int) (t[(i2 + i3 * 16)] / 3.0D + 3.0D + j.nextDouble() * 0.25D);

                int i7 = -1;

                int i8 = localOMobSpawnerBase.o;
                int i9 = localOMobSpawnerBase.p;

                for (int i10 = 127; i10 >= 0; i10--) {
                    // hMod: fix chunk generation
                    int i11 = (i2 + 16 * i3) * 128 + i10;

                    if (i10 <= 0 + j.nextInt(5))
                        paramArrayOfByte[i11] = (byte) OBlock.z.bi;
                    else {
                        int i12 = paramArrayOfByte[i11];

                        if (i12 == 0)
                            i7 = -1;
                        else if (i12 == OBlock.t.bi)
                            if (i7 == -1) {
                                if (i6 <= 0) {
                                    i8 = 0;
                                    i9 = (byte) OBlock.t.bi;
                                } else if ((i10 >= i1 - 4) && (i10 <= i1 + 1)) {
                                    i8 = localOMobSpawnerBase.o;
                                    i9 = localOMobSpawnerBase.p;
                                    if (i5 != 0)
                                        i8 = 0;
                                    if (i5 != 0)
                                        i9 = (byte) OBlock.F.bi;
                                    if (i4 != 0)
                                        i8 = (byte) OBlock.E.bi;
                                    if (i4 != 0)
                                        i9 = (byte) OBlock.E.bi;
                                }

                                if ((i10 < i1) && (i8 == 0))
                                    i8 = (byte) OBlock.B.bi;

                                i7 = i6;
                                if (i10 >= i1 - 1)
                                    paramArrayOfByte[i11] = (byte) i8;
                                else
                                    paramArrayOfByte[i11] = (byte) i9;
                            } else if (i7 > 0) {
                                i7--;
                                paramArrayOfByte[i11] = (byte) i9;
                            }
                    }
                }
            }
    }

    public OChunk b(int paramInt1, int paramInt2) {
        j.setSeed(paramInt1 * 341873128712L + paramInt2 * 132897987541L);

        byte[] arrayOfByte = new byte[32768];
        OChunk localOChunk = new OChunk(p, arrayOfByte, paramInt1, paramInt2);
        v = p.a().a(v, paramInt1 * 16, paramInt2 * 16, 16, 16);
        double[] arrayOfDouble = p.a().a;

        a(paramInt1, paramInt2, arrayOfByte, v, arrayOfDouble);
        a(paramInt1, paramInt2, arrayOfByte, v);

        u.a(this, p, paramInt1, paramInt2, arrayOfByte);

        localOChunk.b();

        return localOChunk;
    }

    private double[] a(double[] paramArrayOfDouble, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
        if (paramArrayOfDouble == null)
            paramArrayOfDouble = new double[paramInt4 * paramInt5 * paramInt6];

        double d1 = 684.41200000000003D;
        double d2 = 684.41200000000003D;

        double[] arrayOfDouble1 = p.a().a;
        double[] arrayOfDouble2 = p.a().b;
        g = a.a(g, paramInt1, paramInt3, paramInt4, paramInt6, 1.121D, 1.121D, 0.5D);
        h = b.a(h, paramInt1, paramInt3, paramInt4, paramInt6, 200.0D, 200.0D, 0.5D);

        d = m.a(d, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, d1 / 80.0D, d2 / 160.0D, d1 / 80.0D);
        e = k.a(e, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, d1, d2, d1);
        f = l.a(f, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, d1, d2, d1);

        int i1 = 0;
        int i2 = 0;

        int i3 = 16 / paramInt4;
        for (int i4 = 0; i4 < paramInt4; i4++) {
            int i5 = i4 * i3 + i3 / 2;

            for (int i6 = 0; i6 < paramInt6; i6++) {
                int i7 = i6 * i3 + i3 / 2;
                double d3 = arrayOfDouble1[(i5 * 16 + i7)];
                double d4 = arrayOfDouble2[(i5 * 16 + i7)] * d3;
                double d5 = 1.0D - d4;
                d5 *= d5;
                d5 *= d5;
                d5 = 1.0D - d5;

                double d6 = (g[i2] + 256.0D) / 512.0D;
                d6 *= d5;
                if (d6 > 1.0D)
                    d6 = 1.0D;

                double d7 = h[i2] / 8000.0D;
                if (d7 < 0.0D)
                    d7 = -d7 * 0.3D;
                d7 = d7 * 3.0D - 2.0D;

                if (d7 < 0.0D) {
                    d7 /= 2.0D;
                    if (d7 < -1.0D)
                        d7 = -1.0D;
                    d7 /= 1.4D;
                    d7 /= 2.0D;
                    d6 = 0.0D;
                } else {
                    if (d7 > 1.0D)
                        d7 = 1.0D;
                    d7 /= 8.0D;
                }

                if (d6 < 0.0D)
                    d6 = 0.0D;
                d6 += 0.5D;
                d7 = d7 * paramInt5 / 16.0D;

                double d8 = paramInt5 / 2.0D + d7 * 4.0D;

                i2++;

                for (int i8 = 0; i8 < paramInt5; i8++) {
                    double d9 = 0.0D;

                    double d10 = (i8 - d8) * 12.0D / d6;
                    if (d10 < 0.0D)
                        d10 *= 4.0D;

                    double d11 = e[i1] / 512.0D;
                    double d12 = f[i1] / 512.0D;

                    double d13 = (d[i1] / 10.0D + 1.0D) / 2.0D;
                    if (d13 < 0.0D)
                        d9 = d11;
                    else if (d13 > 1.0D)
                        d9 = d12;
                    else
                        d9 = d11 + (d12 - d11) * d13;
                    d9 -= d10;

                    if (i8 > paramInt5 - 4) {
                        double d14 = (i8 - (paramInt5 - 4)) / 3.0F;
                        d9 = d9 * (1.0D - d14) + -10.0D * d14;
                    }

                    paramArrayOfDouble[i1] = d9;
                    i1++;
                }
            }
        }
        return paramArrayOfDouble;
    }

    public boolean a(int paramInt1, int paramInt2) {
        return true;
    }

    public void a(OIChunkProvider paramOIChunkProvider, int paramInt1, int paramInt2) {
        OBlockSand.a = true;
        int i1 = paramInt1 * 16;
        int i2 = paramInt2 * 16;

        OMobSpawnerBase localOMobSpawnerBase = p.a().a(i1 + 16, i2 + 16);

        j.setSeed(p.u);
        long l1 = j.nextLong() / 2L * 2L + 1L;
        long l2 = j.nextLong() / 2L * 2L + 1L;
        j.setSeed(paramInt1 * l1 + paramInt2 * l2 ^ p.u);
        double d1 = 0.25D;
        int i3, i4, i5, i6;
        if (j.nextInt(4) == 0) {
            i3 = i1 + j.nextInt(16) + 8;
            i4 = j.nextInt(128);
            i5 = i2 + j.nextInt(16) + 8;
            new OWorldGenLakes(OBlock.B.bi).a(p, j, i3, i4, i5);
        }

        if (j.nextInt(8) == 0) {
            i3 = i1 + j.nextInt(16) + 8;
            i4 = j.nextInt(j.nextInt(120) + 8);
            i5 = i2 + j.nextInt(16) + 8;
            if ((i4 < 64) || (j.nextInt(10) == 0))
                new OWorldGenLakes(OBlock.D.bi).a(p, j, i3, i4, i5);
        }

        for (i3 = 0; i3 < 8; i3++) {
            i4 = i1 + j.nextInt(16) + 8;
            i5 = j.nextInt(128);
            i6 = i2 + j.nextInt(16) + 8;
            new OWorldGenDungeons().a(p, j, i4, i5, i6);
        }

        for (i3 = 0; i3 < 10; i3++) {
            i4 = i1 + j.nextInt(16);
            i5 = j.nextInt(128);
            i6 = i2 + j.nextInt(16);
            new OWorldGenClay(32).a(p, j, i4, i5, i6);
        }

        for (i3 = 0; i3 < 20; i3++) {
            i4 = i1 + j.nextInt(16);
            i5 = j.nextInt(128);
            i6 = i2 + j.nextInt(16);
            new OWorldGenMinable(OBlock.v.bi, 32).a(p, j, i4, i5, i6);
        }

        for (i3 = 0; i3 < 10; i3++) {
            i4 = i1 + j.nextInt(16);
            i5 = j.nextInt(128);
            i6 = i2 + j.nextInt(16);
            new OWorldGenMinable(OBlock.F.bi, 32).a(p, j, i4, i5, i6);
        }

        for (i3 = 0; i3 < 20; i3++) {
            i4 = i1 + j.nextInt(16);
            i5 = j.nextInt(128);
            i6 = i2 + j.nextInt(16);
            new OWorldGenMinable(OBlock.I.bi, 16).a(p, j, i4, i5, i6);
        }

        for (i3 = 0; i3 < 20; i3++) {
            i4 = i1 + j.nextInt(16);
            i5 = j.nextInt(64);
            i6 = i2 + j.nextInt(16);
            new OWorldGenMinable(OBlock.H.bi, 8).a(p, j, i4, i5, i6);
        }

        for (i3 = 0; i3 < 2; i3++) {
            i4 = i1 + j.nextInt(16);
            i5 = j.nextInt(32);
            i6 = i2 + j.nextInt(16);
            new OWorldGenMinable(OBlock.G.bi, 8).a(p, j, i4, i5, i6);
        }

        for (i3 = 0; i3 < 8; i3++) {
            i4 = i1 + j.nextInt(16);
            i5 = j.nextInt(16);
            i6 = i2 + j.nextInt(16);
            new OWorldGenMinable(OBlock.aN.bi, 7).a(p, j, i4, i5, i6);
        }

        for (i3 = 0; i3 < 1; i3++) {
            i4 = i1 + j.nextInt(16);
            i5 = j.nextInt(16);
            i6 = i2 + j.nextInt(16);
            new OWorldGenMinable(OBlock.aw.bi, 7).a(p, j, i4, i5, i6);
        }

        for (i3 = 0; i3 < 1; i3++) {
            i4 = i1 + j.nextInt(16);
            i5 = j.nextInt(16) + j.nextInt(16);
            i6 = i2 + j.nextInt(16);
            new OWorldGenMinable(OBlock.N.bi, 6).a(p, j, i4, i5, i6);
        }

        d1 = 0.5D;
        i3 = (int) ((c.a(i1 * d1, i2 * d1) / 8.0D + j.nextDouble() * 4.0D + 4.0D) / 3.0D);

        i4 = 0;
        if (j.nextInt(10) == 0)
            i4++;

        if (localOMobSpawnerBase == OMobSpawnerBase.d)
            i4 += i3 + 5;
        if (localOMobSpawnerBase == OMobSpawnerBase.a)
            i4 += i3 + 5;
        if (localOMobSpawnerBase == OMobSpawnerBase.c)
            i4 += i3 + 2;
        if (localOMobSpawnerBase == OMobSpawnerBase.g)
            i4 += i3 + 5;

        if (localOMobSpawnerBase == OMobSpawnerBase.h)
            i4 -= 20;
        if (localOMobSpawnerBase == OMobSpawnerBase.k)
            i4 -= 20;
        if (localOMobSpawnerBase == OMobSpawnerBase.i)
            i4 -= 20;
        int i7;
        for (i5 = 0; i5 < i4; i5++) {
            i6 = i1 + j.nextInt(16) + 8;
            i7 = i2 + j.nextInt(16) + 8;
            OWorldGenerator localOWorldGenerator = localOMobSpawnerBase.a(j);
            localOWorldGenerator.a(1.0D, 1.0D, 1.0D);
            localOWorldGenerator.a(p, j, i6, p.d(i6, i7), i7);
        }
        int i8;
        for (i5 = 0; i5 < 2; i5++) {
            i6 = i1 + j.nextInt(16) + 8;
            i7 = j.nextInt(128);
            i8 = i2 + j.nextInt(16) + 8;
            new OWorldGenFlowers(OBlock.ad.bi).a(p, j, i6, i7, i8);
        }

        if (j.nextInt(2) == 0) {
            i5 = i1 + j.nextInt(16) + 8;
            i6 = j.nextInt(128);
            i7 = i2 + j.nextInt(16) + 8;
            new OWorldGenFlowers(OBlock.ae.bi).a(p, j, i5, i6, i7);
        }

        if (j.nextInt(4) == 0) {
            i5 = i1 + j.nextInt(16) + 8;
            i6 = j.nextInt(128);
            i7 = i2 + j.nextInt(16) + 8;
            new OWorldGenFlowers(OBlock.af.bi).a(p, j, i5, i6, i7);
        }

        if (j.nextInt(8) == 0) {
            i5 = i1 + j.nextInt(16) + 8;
            i6 = j.nextInt(128);
            i7 = i2 + j.nextInt(16) + 8;
            new OWorldGenFlowers(OBlock.ag.bi).a(p, j, i5, i6, i7);
        }

        for (i5 = 0; i5 < 10; i5++) {
            i6 = i1 + j.nextInt(16) + 8;
            i7 = j.nextInt(128);
            i8 = i2 + j.nextInt(16) + 8;
            new OWorldGenReed().a(p, j, i6, i7, i8);
        }

        if (j.nextInt(32) == 0) {
            i5 = i1 + j.nextInt(16) + 8;
            i6 = j.nextInt(128);
            i7 = i2 + j.nextInt(16) + 8;
            new OWorldGenPumpkin().a(p, j, i5, i6, i7);
        }

        i5 = 0;
        if (localOMobSpawnerBase == OMobSpawnerBase.h)
            i5 += 10;
        int i9;
        for (i6 = 0; i6 < i5; i6++) {
            i7 = i1 + j.nextInt(16) + 8;
            i8 = j.nextInt(128);
            i9 = i2 + j.nextInt(16) + 8;
            new OWorldGenCactus().a(p, j, i7, i8, i9);
        }

        for (i6 = 0; i6 < 50; i6++) {
            i7 = i1 + j.nextInt(16) + 8;
            i8 = j.nextInt(j.nextInt(120) + 8);
            i9 = i2 + j.nextInt(16) + 8;
            new OWorldGenLiquids(OBlock.A.bi).a(p, j, i7, i8, i9);
        }

        for (i6 = 0; i6 < 20; i6++) {
            i7 = i1 + j.nextInt(16) + 8;
            i8 = j.nextInt(j.nextInt(j.nextInt(112) + 8) + 8);
            i9 = i2 + j.nextInt(16) + 8;
            new OWorldGenLiquids(OBlock.C.bi).a(p, j, i7, i8, i9);
        }

        w = p.a().a(w, i1 + 8, i2 + 8, 16, 16);
        for (i6 = i1 + 8; i6 < i1 + 8 + 16; i6++)
            for (i7 = i2 + 8; i7 < i2 + 8 + 16; i7++) {
                i8 = i6 - (i1 + 8);
                i9 = i7 - (i2 + 8);
                int i10 = p.e(i6, i7);
                double d2 = w[(i8 * 16 + i9)] - (i10 - 64) / 64.0D * 0.3D;
                if ((d2 >= 0.5D) || (i10 <= 0) || (i10 >= 128) || (!p.e(i6, i10, i7)) || (!p.c(i6, i10 - 1, i7).c()) || (p.c(i6, i10 - 1, i7) == OMaterial.r))
                    continue;
                p.e(i6, i10, i7, OBlock.aS.bi);
            }

        OBlockSand.a = false;
    }

    public boolean a(boolean paramBoolean, OIProgressUpdate paramOIProgressUpdate) {
        return true;
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return true;
    }
}
