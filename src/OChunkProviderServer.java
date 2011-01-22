
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OChunkProviderServer
        implements OIChunkProvider {

    private Set a = new HashSet();
    private OChunk b;
    private OIChunkProvider c;
    private OIChunkLoader d;
    private Map e = new HashMap();
    private List f = new ArrayList();
    private OWorldServer g;

    public OChunkProviderServer(OWorldServer paramOWorldServer, OIChunkLoader paramOIChunkLoader, OIChunkProvider paramOIChunkProvider) {
        b = new OEmptyChunk(paramOWorldServer, new byte[32768], 0, 0);

        g = paramOWorldServer;
        d = paramOIChunkLoader;
        c = paramOIChunkProvider;
    }

    public boolean a(int paramInt1, int paramInt2) {
        OChunkCoordinates localOChunkCoordinates = new OChunkCoordinates(paramInt1, paramInt2);
        return e.containsKey(localOChunkCoordinates);
    }

    public void c(int paramInt1, int paramInt2) {
        int i = paramInt1 * 16 + 8 - g.m;
        int j = paramInt2 * 16 + 8 - g.o;
        int k = 128;
        if ((i < -k) || (i > k) || (j < -k) || (j > k)) {
            a.add(new OChunkCoordinates(paramInt1, paramInt2));
        }
    }

    public OChunk d(int paramInt1, int paramInt2) {
        OChunkCoordinates localOChunkCoordinates = new OChunkCoordinates(paramInt1, paramInt2);
        a.remove(new OChunkCoordinates(paramInt1, paramInt2));

        OChunk localOChunk = (OChunk) e.get(localOChunkCoordinates);
        if (localOChunk == null) {
            localOChunk = e(paramInt1, paramInt2);
            if (localOChunk == null) {
                if (c == null) {
                    localOChunk = b;
                } else {
                    localOChunk = c.b(paramInt1, paramInt2);
                }

            }

            e.put(localOChunkCoordinates, localOChunk);
            f.add(localOChunk);

            if (localOChunk != null) {
                localOChunk.c();
                localOChunk.d();
            }

            if ((!localOChunk.n) && (a(paramInt1 + 1, paramInt2 + 1)) && (a(paramInt1, paramInt2 + 1)) && (a(paramInt1 + 1, paramInt2))) {
                a(this, paramInt1, paramInt2);
            }
            if ((a(paramInt1 - 1, paramInt2)) && (!b(paramInt1 - 1, paramInt2).n) && (a(paramInt1 - 1, paramInt2 + 1)) && (a(paramInt1, paramInt2 + 1)) && (a(paramInt1 - 1, paramInt2))) {
                a(this, paramInt1 - 1, paramInt2);
            }
            if ((a(paramInt1, paramInt2 - 1)) && (!b(paramInt1, paramInt2 - 1).n) && (a(paramInt1 + 1, paramInt2 - 1)) && (a(paramInt1, paramInt2 - 1)) && (a(paramInt1 + 1, paramInt2))) {
                a(this, paramInt1, paramInt2 - 1);
            }
            if ((a(paramInt1 - 1, paramInt2 - 1)) && (!b(paramInt1 - 1, paramInt2 - 1).n) && (a(paramInt1 - 1, paramInt2 - 1)) && (a(paramInt1, paramInt2 - 1)) && (a(paramInt1 - 1, paramInt2))) {
                a(this, paramInt1 - 1, paramInt2 - 1);
            }

        }

        return localOChunk;
    }

    public OChunk b(int paramInt1, int paramInt2) {
        OChunkCoordinates localOChunkCoordinates = new OChunkCoordinates(paramInt1, paramInt2);
        OChunk localOChunk = (OChunk) e.get(localOChunkCoordinates);

        if (localOChunk == null) {
            if (g.x) {
                return d(paramInt1, paramInt2);
            }
            return b;
        }

        return localOChunk;
    }

    private OChunk e(int paramInt1, int paramInt2) {
        if (d == null) {
            return null;
        }
        try {
            OChunk localOChunk = d.a(g, paramInt1, paramInt2);
            if (localOChunk != null) {
                localOChunk.r = g.e;
            }
            return localOChunk;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }

    private void a(OChunk paramOChunk) {
        if (d == null) {
            return;
        }
        try {
            d.b(g, paramOChunk);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    private void b(OChunk paramOChunk) {
        if (d == null) {
            return;
        }
        // hMod: fix decompiler error, comment this out.
        paramOChunk.r = g.e;
        d.a(g, paramOChunk);
    }

    public void a(OIChunkProvider paramOIChunkProvider, int paramInt1, int paramInt2) {
        OChunk localOChunk = b(paramInt1, paramInt2);
        if (!localOChunk.n) {
            localOChunk.n = true;
            if (c != null) {
                c.a(paramOIChunkProvider, paramInt1, paramInt2);
                localOChunk.f();
            }
        }
    }

    // hMod: Load status
    boolean loaded = false;
    public boolean a(boolean paramBoolean, OIProgressUpdate paramOIProgressUpdate) {
        // hMod: Load once!
        if (!loaded) {
            etc.getLoader();
            loaded = true;
        }
        
        int i = 0;
        for (int j = 0; j < f.size(); j++) {
            OChunk localOChunk = (OChunk) f.get(j);
            if ((paramBoolean) && (!localOChunk.p)) {
                a(localOChunk);
            }
            if (localOChunk.a(paramBoolean)) {
                b(localOChunk);
                localOChunk.o = false;
                i++;
                if ((i == 24) && (!paramBoolean)) {
                    return false;
                }
            }
        }

        if (paramBoolean) {
            if (d == null) {
                return true;
            }
            d.b();
        }
        return true;
    }

    public boolean a() {
        if (!g.C) {
            for (int i = 0; i < 100; i++) {
                if (!a.isEmpty()) {
                    OChunkCoordinates localOChunkCoordinates = (OChunkCoordinates) a.iterator().next();

                    OChunk localOChunk = b(localOChunkCoordinates.a, localOChunkCoordinates.b);
                    localOChunk.e();
                    b(localOChunk);
                    a(localOChunk);
                    a.remove(localOChunkCoordinates);

                    e.remove(localOChunkCoordinates);
                    f.remove(localOChunk);
                }
            }

            if (d != null) {
                d.a();
            }
        }

        return c.a();
    }

    public boolean b() {
        return !g.C;
    }
}
