import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.MinecraftServer;

public class OPlayerManager {

    private List<OEntityPlayerMP> a = new ArrayList<OEntityPlayerMP>();
    private OMCHashTable2         b = new OMCHashTable2();
    private List<OPlayerInstance> c = new ArrayList<OPlayerInstance>();
    private MinecraftServer       d;

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
        int i = (int) paramOEntityPlayerMP.p >> 4;
        int j = (int) paramOEntityPlayerMP.r >> 4;

        paramOEntityPlayerMP.d = paramOEntityPlayerMP.p;
        paramOEntityPlayerMP.e = paramOEntityPlayerMP.r;

        for (int k = i - 10; k <= i + 10; k++)
            for (int m = j - 10; m <= j + 10; m++)
                a(k, m, true).a(paramOEntityPlayerMP);
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
        int i = (int) paramOEntityPlayerMP.p >> 4;
        int j = (int) paramOEntityPlayerMP.r >> 4;

        double d1 = paramOEntityPlayerMP.d - paramOEntityPlayerMP.p;
        double d2 = paramOEntityPlayerMP.e - paramOEntityPlayerMP.r;
        double d3 = d1 * d1 + d2 * d2;
        if (d3 < 64.0D)
            return;

        int k = (int) paramOEntityPlayerMP.d >> 4;
        int m = (int) paramOEntityPlayerMP.e >> 4;

        int n = i - k;
        int i1 = j - m;
        if ((n == 0) && (i1 == 0))
            return;

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
        paramOEntityPlayerMP.d = paramOEntityPlayerMP.p;
        paramOEntityPlayerMP.e = paramOEntityPlayerMP.r;
    }

    public int b() {
        return 144;
    }

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
