import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class OBlockRedstoneWire extends OBlock {

    private boolean a = true;
    private Set     b = new HashSet();

    public OBlockRedstoneWire(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, OMaterial.n);
        a(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
    }

    @Override
    public int a(int paramInt1, int paramInt2) {
        return bj;
    }

    @Override
    public OAxisAlignedBB d(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public boolean a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        return paramOWorld.d(paramInt1, paramInt2 - 1, paramInt3);
    }

    private void g(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        a(paramOWorld, paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);
        ArrayList localArrayList = new ArrayList(b);
        b.clear();
        for (int i = 0; i < localArrayList.size(); i++) {
            OChunkPosition localOChunkPosition = (OChunkPosition) localArrayList.get(i);
            paramOWorld.h(localOChunkPosition.a, localOChunkPosition.b, localOChunkPosition.c, bk);
        }
    }

    private void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
        int i = paramOWorld.b(paramInt1, paramInt2, paramInt3);
        int j = 0;

        a = false;
        boolean bool = paramOWorld.p(paramInt1, paramInt2, paramInt3);
        a = true;
        int k;
        int m;
        int n;
        if (bool) {
            j = 15;
        } else {
            for (k = 0; k < 4; k++) {
                m = paramInt1;
                n = paramInt3;
                if (k == 0) {
                    m--;
                }
                if (k == 1) {
                    m++;
                }
                if (k == 2) {
                    n--;
                }
                if (k == 3) {
                    n++;
                }

                if ((m != paramInt4) || (paramInt2 != paramInt5) || (n != paramInt6)) {
                    j = g(paramOWorld, m, paramInt2, n, j);
                }
                if ((paramOWorld.d(m, paramInt2, n)) && (!paramOWorld.d(paramInt1, paramInt2 + 1, paramInt3))) {
                    if ((m == paramInt4) && (paramInt2 + 1 == paramInt5) && (n == paramInt6)) {
                        continue;
                    }
                    j = g(paramOWorld, m, paramInt2 + 1, n, j);
                } else if ((!paramOWorld.d(m, paramInt2, n)) && ((m != paramInt4) || (paramInt2 - 1 != paramInt5) || (n != paramInt6))) {
                    j = g(paramOWorld, m, paramInt2 - 1, n, j);
                }
            }
            if (j > 0) {
                j--;
            } else {
                j = 0;
            }
        }
        // hMod: Allow redstone wire current changes
        if (i != j)
            j = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Object[] { new Block(bk, paramInt1, paramInt2, paramInt3), i, j });

        if (i != j) {
            paramOWorld.h = true;
            paramOWorld.c(paramInt1, paramInt2, paramInt3, j);
            paramOWorld.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);
            paramOWorld.h = false;

            for (k = 0; k < 4; k++) {
                m = paramInt1;
                n = paramInt3;
                int i1 = paramInt2 - 1;
                if (k == 0) {
                    m--;
                }
                if (k == 1) {
                    m++;
                }
                if (k == 2) {
                    n--;
                }
                if (k == 3) {
                    n++;
                }

                if (paramOWorld.d(m, paramInt2, n)) {
                    i1 += 2;
                }

                int i2 = 0;
                i2 = g(paramOWorld, m, paramInt2, n, -1);
                j = paramOWorld.b(paramInt1, paramInt2, paramInt3);
                if (j > 0) {
                    j--;
                }
                if ((i2 >= 0) && (i2 != j)) {
                    a(paramOWorld, m, paramInt2, n, paramInt1, paramInt2, paramInt3);
                }
                i2 = g(paramOWorld, m, i1, n, -1);
                j = paramOWorld.b(paramInt1, paramInt2, paramInt3);
                if (j > 0) {
                    j--;
                }
                if ((i2 >= 0) && (i2 != j)) {
                    a(paramOWorld, m, i1, n, paramInt1, paramInt2, paramInt3);
                }
            }

            if ((i == 0) || (j == 0)) {
                b.add(new OChunkPosition(paramInt1, paramInt2, paramInt3));
                b.add(new OChunkPosition(paramInt1 - 1, paramInt2, paramInt3));
                b.add(new OChunkPosition(paramInt1 + 1, paramInt2, paramInt3));
                b.add(new OChunkPosition(paramInt1, paramInt2 - 1, paramInt3));
                b.add(new OChunkPosition(paramInt1, paramInt2 + 1, paramInt3));
                b.add(new OChunkPosition(paramInt1, paramInt2, paramInt3 - 1));
                b.add(new OChunkPosition(paramInt1, paramInt2, paramInt3 + 1));
            }
        }
    }

    private void h(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        if (paramOWorld.a(paramInt1, paramInt2, paramInt3) != bk) {
            return;
        }

        paramOWorld.h(paramInt1, paramInt2, paramInt3, bk);
        paramOWorld.h(paramInt1 - 1, paramInt2, paramInt3, bk);
        paramOWorld.h(paramInt1 + 1, paramInt2, paramInt3, bk);
        paramOWorld.h(paramInt1, paramInt2, paramInt3 - 1, bk);
        paramOWorld.h(paramInt1, paramInt2, paramInt3 + 1, bk);

        paramOWorld.h(paramInt1, paramInt2 - 1, paramInt3, bk);
        paramOWorld.h(paramInt1, paramInt2 + 1, paramInt3, bk);
    }

    @Override
    public void e(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        super.e(paramOWorld, paramInt1, paramInt2, paramInt3);
        if (paramOWorld.t) {
            return;
        }

        g(paramOWorld, paramInt1, paramInt2, paramInt3);
        paramOWorld.h(paramInt1, paramInt2 + 1, paramInt3, bk);
        paramOWorld.h(paramInt1, paramInt2 - 1, paramInt3, bk);

        h(paramOWorld, paramInt1 - 1, paramInt2, paramInt3);
        h(paramOWorld, paramInt1 + 1, paramInt2, paramInt3);
        h(paramOWorld, paramInt1, paramInt2, paramInt3 - 1);
        h(paramOWorld, paramInt1, paramInt2, paramInt3 + 1);

        if (paramOWorld.d(paramInt1 - 1, paramInt2, paramInt3)) {
            h(paramOWorld, paramInt1 - 1, paramInt2 + 1, paramInt3);
        } else {
            h(paramOWorld, paramInt1 - 1, paramInt2 - 1, paramInt3);
        }
        if (paramOWorld.d(paramInt1 + 1, paramInt2, paramInt3)) {
            h(paramOWorld, paramInt1 + 1, paramInt2 + 1, paramInt3);
        } else {
            h(paramOWorld, paramInt1 + 1, paramInt2 - 1, paramInt3);
        }
        if (paramOWorld.d(paramInt1, paramInt2, paramInt3 - 1)) {
            h(paramOWorld, paramInt1, paramInt2 + 1, paramInt3 - 1);
        } else {
            h(paramOWorld, paramInt1, paramInt2 - 1, paramInt3 - 1);
        }
        if (paramOWorld.d(paramInt1, paramInt2, paramInt3 + 1)) {
            h(paramOWorld, paramInt1, paramInt2 + 1, paramInt3 + 1);
        } else {
            h(paramOWorld, paramInt1, paramInt2 - 1, paramInt3 + 1);
        }
    }

    @Override
    public void b(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        super.b(paramOWorld, paramInt1, paramInt2, paramInt3);
        if (paramOWorld.t) {
            return;
        }

        paramOWorld.h(paramInt1, paramInt2 + 1, paramInt3, bk);
        paramOWorld.h(paramInt1, paramInt2 - 1, paramInt3, bk);
        g(paramOWorld, paramInt1, paramInt2, paramInt3);

        h(paramOWorld, paramInt1 - 1, paramInt2, paramInt3);
        h(paramOWorld, paramInt1 + 1, paramInt2, paramInt3);
        h(paramOWorld, paramInt1, paramInt2, paramInt3 - 1);
        h(paramOWorld, paramInt1, paramInt2, paramInt3 + 1);

        if (paramOWorld.d(paramInt1 - 1, paramInt2, paramInt3)) {
            h(paramOWorld, paramInt1 - 1, paramInt2 + 1, paramInt3);
        } else {
            h(paramOWorld, paramInt1 - 1, paramInt2 - 1, paramInt3);
        }
        if (paramOWorld.d(paramInt1 + 1, paramInt2, paramInt3)) {
            h(paramOWorld, paramInt1 + 1, paramInt2 + 1, paramInt3);
        } else {
            h(paramOWorld, paramInt1 + 1, paramInt2 - 1, paramInt3);
        }
        if (paramOWorld.d(paramInt1, paramInt2, paramInt3 - 1)) {
            h(paramOWorld, paramInt1, paramInt2 + 1, paramInt3 - 1);
        } else {
            h(paramOWorld, paramInt1, paramInt2 - 1, paramInt3 - 1);
        }
        if (paramOWorld.d(paramInt1, paramInt2, paramInt3 + 1)) {
            h(paramOWorld, paramInt1, paramInt2 + 1, paramInt3 + 1);
        } else {
            h(paramOWorld, paramInt1, paramInt2 - 1, paramInt3 + 1);
        }
    }

    private int g(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramOWorld.a(paramInt1, paramInt2, paramInt3) != bk) {
            return paramInt4;
        }
        int i = paramOWorld.b(paramInt1, paramInt2, paramInt3);
        if (i > paramInt4) {
            return i;
        }
        return paramInt4;
    }

    @Override
    public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramOWorld.t) {
            return;
        }
        int i = paramOWorld.b(paramInt1, paramInt2, paramInt3);

        boolean bool = a(paramOWorld, paramInt1, paramInt2, paramInt3);

        if (!bool) {
            b_(paramOWorld, paramInt1, paramInt2, paramInt3, i);
            paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
        } else {
            g(paramOWorld, paramInt1, paramInt2, paramInt3);
        }

        super.a(paramOWorld, paramInt1, paramInt2, paramInt3, paramInt4);
    }

    @Override
    public int a(int paramInt, Random paramRandom) {
        return OItem.aA.bc;
    }

    @Override
    public boolean c(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (!a) {
            return false;
        }
        return b((OIBlockAccess) paramOWorld, paramInt1, paramInt2, paramInt3, paramInt4);
    }

    @Override
    public boolean b(OIBlockAccess paramOIBlockAccess, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (!a) {
            return false;
        }
        if (paramOIBlockAccess.b(paramInt1, paramInt2, paramInt3) == 0) {
            return false;
        }

        if (paramInt4 == 1) {
            return true;
        }

        int i = (b(paramOIBlockAccess, paramInt1 - 1, paramInt2, paramInt3)) || ((!paramOIBlockAccess.d(paramInt1 - 1, paramInt2, paramInt3)) && (b(paramOIBlockAccess, paramInt1 - 1, paramInt2 - 1, paramInt3))) ? 1 : 0;
        int j = (b(paramOIBlockAccess, paramInt1 + 1, paramInt2, paramInt3)) || ((!paramOIBlockAccess.d(paramInt1 + 1, paramInt2, paramInt3)) && (b(paramOIBlockAccess, paramInt1 + 1, paramInt2 - 1, paramInt3))) ? 1 : 0;
        int k = (b(paramOIBlockAccess, paramInt1, paramInt2, paramInt3 - 1)) || ((!paramOIBlockAccess.d(paramInt1, paramInt2, paramInt3 - 1)) && (b(paramOIBlockAccess, paramInt1, paramInt2 - 1, paramInt3 - 1))) ? 1 : 0;
        int m = (b(paramOIBlockAccess, paramInt1, paramInt2, paramInt3 + 1)) || ((!paramOIBlockAccess.d(paramInt1, paramInt2, paramInt3 + 1)) && (b(paramOIBlockAccess, paramInt1, paramInt2 - 1, paramInt3 + 1))) ? 1 : 0;
        if (!paramOIBlockAccess.d(paramInt1, paramInt2 + 1, paramInt3)) {
            if ((paramOIBlockAccess.d(paramInt1 - 1, paramInt2, paramInt3)) && (b(paramOIBlockAccess, paramInt1 - 1, paramInt2 + 1, paramInt3))) {
                i = 1;
            }
            if ((paramOIBlockAccess.d(paramInt1 + 1, paramInt2, paramInt3)) && (b(paramOIBlockAccess, paramInt1 + 1, paramInt2 + 1, paramInt3))) {
                j = 1;
            }
            if ((paramOIBlockAccess.d(paramInt1, paramInt2, paramInt3 - 1)) && (b(paramOIBlockAccess, paramInt1, paramInt2 + 1, paramInt3 - 1))) {
                k = 1;
            }
            if ((paramOIBlockAccess.d(paramInt1, paramInt2, paramInt3 + 1)) && (b(paramOIBlockAccess, paramInt1, paramInt2 + 1, paramInt3 + 1))) {
                m = 1;
            }
        }

        if ((k == 0) && (j == 0) && (i == 0) && (m == 0) && (paramInt4 >= 2) && (paramInt4 <= 5)) {
            return true;
        }

        if ((paramInt4 == 2) && (k != 0) && (i == 0) && (j == 0)) {
            return true;
        }
        if ((paramInt4 == 3) && (m != 0) && (i == 0) && (j == 0)) {
            return true;
        }
        if ((paramInt4 == 4) && (i != 0) && (k == 0) && (m == 0)) {
            return true;
        }
        return (paramInt4 == 5) && (j != 0) && (k == 0) && (m == 0);
    }

    @Override
    public boolean c() {
        return a;
    }

    public static boolean b(OIBlockAccess paramOIBlockAccess, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramOIBlockAccess.a(paramInt1, paramInt2, paramInt3);
        if (i == OBlock.av.bk) {
            return true;
        }
        if (i == 0) {
            return false;
        }
        return OBlock.m[i].c();
    }
}
