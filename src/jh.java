
import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class jh {

	private List a = new ArrayList();
	private fv b = new fv();
	private List c = new ArrayList();
	private MinecraftServer d;

	public jh(MinecraftServer paramMinecraftServer) {
		this.d = paramMinecraftServer;
	}

	public void a() {
		for (int i = 0; i < this.c.size(); i++) {
			((ap) this.c.get(i)).a();
		}
		this.c.clear();
	}

	private ap a(int paramInt1, int paramInt2, boolean paramBoolean) {
		long l = paramInt1 + 2147483647L | paramInt2 + 2147483647L << 32;
		ap localap = (ap) this.b.a(l);
		if ((localap == null) && (paramBoolean)) {
			localap = new ap(this, paramInt1, paramInt2);
			this.b.a(l, localap);
		}
		return localap;
	}

	// hMod: bring back old "send packet to chunk" method from alpha
	public void a(jv packetToSend, int globalx, int globaly, int globalz) {
		// Get chunk coordinates
		int chunkx = globalx >> 4;
		int chunkz = globalz >> 4;
		// Get the chunk
		ap localap = a(chunkx, chunkz, false);

		// if chunk != null, send packet
		if (localap != null)
			localap.a(packetToSend);
	}
	// end hMod

	public void a(int paramInt1, int paramInt2, int paramInt3) {
		int i = paramInt1 >> 4;
		int j = paramInt3 >> 4;
		ap localap = a(i, j, false);
		if (localap != null)
			localap.a(paramInt1 & 0xF, paramInt2, paramInt3 & 0xF);
	}

	public void a(fi paramfi) {
		int i = (int) paramfi.p >> 4;
		int j = (int) paramfi.r >> 4;

		paramfi.d = paramfi.p;
		paramfi.e = paramfi.r;

		for (int k = i - 10; k <= i + 10; k++) {
			for (int m = j - 10; m <= j + 10; m++) {
				a(k, m, true).a(paramfi);
			}
		}
		this.a.add(paramfi);
	}

	public void b(fi paramfi) {
		int i = (int) paramfi.d >> 4;
		int j = (int) paramfi.e >> 4;

		for (int k = i - 10; k <= i + 10; k++) {
			for (int m = j - 10; m <= j + 10; m++) {
				ap localap = a(k, m, false);
				if (localap == null)
					continue;
				localap.b(paramfi);
			}
		}
		this.a.remove(paramfi);
	}

	private boolean a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
		int i = paramInt1 - paramInt3;
		int j = paramInt2 - paramInt4;
		if ((i < -10) || (i > 10))
			return false;
		return (j >= -10) && (j <= 10);
	}

	public void c(fi paramfi) {
		int i = (int) paramfi.p >> 4;
		int j = (int) paramfi.r >> 4;

		double d1 = paramfi.d - paramfi.p;
		double d2 = paramfi.e - paramfi.r;
		double d3 = d1 * d1 + d2 * d2;
		if (d3 < 64.0D)
			return;

		int k = (int) paramfi.d >> 4;
		int m = (int) paramfi.e >> 4;

		int n = i - k;
		int i1 = j - m;
		if ((n == 0) && (i1 == 0))
			return;

		for (int i2 = i - 10; i2 <= i + 10; i2++) {
			for (int i3 = j - 10; i3 <= j + 10; i3++) {
				if (!a(i2, i3, k, m))
					a(i2, i3, true).a(paramfi);
				if (!a(i2 - n, i3 - i1, i, j)) {
					ap localap = a(i2 - n, i3 - i1, false);
					if (localap == null)
						continue;
					localap.b(paramfi);
				}
			}
		}
		paramfi.d = paramfi.p;
		paramfi.e = paramfi.r;
	}

	public int b() {
		return 144;
	}
}
