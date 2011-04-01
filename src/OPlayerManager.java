import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.MinecraftServer;

public class OPlayerManager {

    private List<OEntityPlayerMP> a = new ArrayList();
    private OMCHashTable2         b = new OMCHashTable2();
    private List<OPlayerInstance> c = new ArrayList();
    private MinecraftServer       d;
    private final int[][]         e = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    public OPlayerManager(MinecraftServer paramMinecraftServer) {
        d = paramMinecraftServer;
    }

    public void a() {
        for (int i = 0; i < c.size(); i++)
            c.get(i).a();
        c.clear();
    }

    private OPlayerInstance a(int paramInt1, int paramInt2, boolean paramBoolean) {
        long l = paramInt1 + 2147483647L | paramInt2 + 2147483647L << 32;
        OPlayerInstance localOPlayerInstance = (OPlayerInstance) b.a(l);
        if ((localOPlayerInstance == null) && (paramBoolean)) {
            localOPlayerInstance = new OPlayerInstance(this, paramInt1, paramInt2);
            b.a(l, localOPlayerInstance);
        }
        return localOPlayerInstance;
    }

    public void a(int paramInt1, int paramInt2, int paramInt3) {
        int i = paramInt1 >> 4;
        int j = paramInt3 >> 4;
        OPlayerInstance localOPlayerInstance = a(i, j, false);
        if (localOPlayerInstance != null)
            localOPlayerInstance.a(paramInt1 & 0xF, paramInt2, paramInt3 & 0xF);
    }

    public void a(OEntityPlayerMP paramOEntityPlayerMP) {
        int i = (int) paramOEntityPlayerMP.aK >> 4;
        int j = (int) paramOEntityPlayerMP.aM >> 4;

        paramOEntityPlayerMP.d = paramOEntityPlayerMP.aK;
        paramOEntityPlayerMP.e = paramOEntityPlayerMP.aM;

        int k = 0;
        int m = 10;
        int n = 0;
        int i1 = 0;

        a(i, j, true).a(paramOEntityPlayerMP);

        for (int i2 = 1; i2 <= m * 2; i2++)
            for (int i3 = 0; i3 < 2; i3++) {
                int[] arrayOfInt = e[(k++ % 4)];

                for (int i4 = 0; i4 < i2; i4++) {
                    n += arrayOfInt[0];
                    i1 += arrayOfInt[1];
                    a(i + n, j + i1, true).a(paramOEntityPlayerMP);
                }
            }

        k %= 4;
        for (int i2 = 0; i2 < m * 2; i2++) {
            n += e[k][0];
            i1 += e[k][1];
            a(i + n, j + i1, true).a(paramOEntityPlayerMP);
        }

        a.add(paramOEntityPlayerMP);
    }

    public void b(OEntityPlayerMP paramOEntityPlayerMP) {
        int i = (int) paramOEntityPlayerMP.d >> 4;
        int j = (int) paramOEntityPlayerMP.e >> 4;

        for (int k = i - 10; k <= i + 10; k++)
            for (int m = j - 10; m <= j + 10; m++) {
                OPlayerInstance localOPlayerInstance = a(k, m, false);
                if (localOPlayerInstance == null)
                    continue;
                localOPlayerInstance.b(paramOEntityPlayerMP);
            }
        a.remove(paramOEntityPlayerMP);
    }

    private boolean a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = paramInt1 - paramInt3;
        int j = paramInt2 - paramInt4;
        if ((i < -10) || (i > 10))
            return false;
        return (j >= -10) && (j <= 10);
    }

    public void c(OEntityPlayerMP paramOEntityPlayerMP) {
        int i = (int) paramOEntityPlayerMP.aK >> 4;
        int j = (int) paramOEntityPlayerMP.aM >> 4;

        double d1 = paramOEntityPlayerMP.d - paramOEntityPlayerMP.aK;
        double d2 = paramOEntityPlayerMP.e - paramOEntityPlayerMP.aM;
        double d3 = d1 * d1 + d2 * d2;
        if (d3 < 64.0D)
            return;

        int k = (int) paramOEntityPlayerMP.d >> 4;
        int m = (int) paramOEntityPlayerMP.e >> 4;

        int n = i - k;
        int i1 = j - m;
        if ((n == 0) && (i1 == 0))
            return;

        // hMod speed up teleporting.
        if (n > 10 || n < -10 || i1 > 10 || i1 < -10) {
            b(paramOEntityPlayerMP);
            a(paramOEntityPlayerMP);
            return;
        }

        for (int i2 = i - 10; i2 <= i + 10; i2++)
            for (int i3 = j - 10; i3 <= j + 10; i3++) {
                if (!a(i2, i3, k, m))
                    a(i2, i3, true).a(paramOEntityPlayerMP);
                if (!a(i2 - n, i3 - i1, i, j)) {
                    OPlayerInstance localOPlayerInstance = a(i2 - n, i3 - i1, false);
                    if (localOPlayerInstance == null)
                        continue;
                    localOPlayerInstance.b(paramOEntityPlayerMP);
                }
            }
        paramOEntityPlayerMP.d = paramOEntityPlayerMP.aK;
        paramOEntityPlayerMP.e = paramOEntityPlayerMP.aM;
    }

    public int b() {
        return 144;
    }

    // hMod: OPlayerInstance calls these statically
    static MinecraftServer a(OPlayerManager jh1) {
        return jh1.d;
    }

    static OMCHashTable2 b(OPlayerManager jh1) {
        return jh1.b;
    }

    static List c(OPlayerManager jh1) {
        return jh1.c;
    }

    // hMod: bring back old "send packet to chunk" method from alpha
    public void sendPacketToChunk(OPacket packetToSend, int globalx, int globaly, int globalz) {
        // Get chunk coordinates
        int chunkx = globalx >> 4;
        int chunkz = globalz >> 4;
        // Get the chunk
        OPlayerInstance localat = a(chunkx, chunkz, false);
        // if chunk != null, send packet
        if (localat != null)
            localat.a(packetToSend);
    }
    // end hMod
}
