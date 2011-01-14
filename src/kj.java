
import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class kj {
    // hMod: generificy

    private List<fy> a = new ArrayList<fy>();
    private gm b = new gm();
    private List<at> c = new ArrayList<at>();
    private MinecraftServer d;

    public kj(MinecraftServer paramMinecraftServer) {
        d = paramMinecraftServer;
    }

    public void a() {
        for (int i = 0; i < c.size(); i++) {
            // hMod: remove unnecessary cast
            c.get(i).a();
        }
        c.clear();
    }

    private at a(int paramInt1, int paramInt2, boolean paramBoolean) {
        long l = paramInt1 + 2147483647L | paramInt2 + 2147483647L << 32;
        at localat = (at) b.a(l);
        if ((localat == null) && (paramBoolean)) {
            localat = new at(this, paramInt1, paramInt2);
            b.a(l, localat);
        }
        return localat;
    }

    public void a(int paramInt1, int paramInt2, int paramInt3) {
        int i = paramInt1 >> 4;
        int j = paramInt3 >> 4;
        at localat = a(i, j, false);
        if (localat != null) {
            localat.a(paramInt1 & 0xF, paramInt2, paramInt3 & 0xF);
        }
    }

    public void a(fy paramfy) {
        int i = (int) paramfy.p >> 4;
        int j = (int) paramfy.r >> 4;

        paramfy.d = paramfy.p;
        paramfy.e = paramfy.r;

        for (int k = i - 10; k <= i + 10; k++) {
            for (int m = j - 10; m <= j + 10; m++) {
                a(k, m, true).a(paramfy);
            }
        }
        a.add(paramfy);
    }

    public void b(fy paramfy) {
        int i = (int) paramfy.d >> 4;
        int j = (int) paramfy.e >> 4;

        for (int k = i - 10; k <= i + 10; k++) {
            for (int m = j - 10; m <= j + 10; m++) {
                at localat = a(k, m, false);
                if (localat == null) {
                    continue;
                }
                localat.b(paramfy);
            }
        }
        a.remove(paramfy);
    }

    private boolean a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = paramInt1 - paramInt3;
        int j = paramInt2 - paramInt4;
        if ((i < -10) || (i > 10)) {
            return false;
        }
        return (j >= -10) && (j <= 10);
    }

    public void c(fy paramfy) {
        int i = (int) paramfy.p >> 4;
        int j = (int) paramfy.r >> 4;

        double d1 = paramfy.d - paramfy.p;
        double d2 = paramfy.e - paramfy.r;
        double d3 = d1 * d1 + d2 * d2;
        if (d3 < 64.0D) {
            return;
        }

        int k = (int) paramfy.d >> 4;
        int m = (int) paramfy.e >> 4;

        int n = i - k;
        int i1 = j - m;
        if ((n == 0) && (i1 == 0)) {
            return;
        }

        for (int i2 = i - 10; i2 <= i + 10; i2++) {
            for (int i3 = j - 10; i3 <= j + 10; i3++) {
                if (!a(i2, i3, k, m)) {
                    a(i2, i3, true).a(paramfy);
                }
                if (!a(i2 - n, i3 - i1, i, j)) {
                    at localat = a(i2 - n, i3 - i1, false);
                    if (localat == null) {
                        continue;
                    }
                    localat.b(paramfy);
                }
            }
        }
        paramfy.d = paramfy.p;
        paramfy.e = paramfy.r;
    }

    public int b() {
        return 144;
    }

    static MinecraftServer a(kj jh1) {
        return jh1.d;
    }

    static gm b(kj jh1) {
        return jh1.b;
    }

    static List c(kj jh1) {
        return jh1.c;
    }
    // hMod: bring back old "send packet to chunk" method from alpha

    public void sendPacketToChunk(jv packetToSend, int globalx, int globaly, int globalz) {
        // Get chunk coordinates
        int chunkx = globalx >> 4;
        int chunkz = globalz >> 4;
        // Get the chunk
        at localat = a(chunkx, chunkz, false);
        // if chunk != null, send packet
        if (localat != null) {
            localat.a(packetToSend);
        }
    }
    // end hMod
}
