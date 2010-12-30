
import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class jh {

	public jh(MinecraftServer minecraftserver) {
		// hMod: generificy
		a = new ArrayList<fi>();
		b = new fv();
		c = new ArrayList<ap>();
		d = minecraftserver;
	}

	public void a() {
		for (int i = 0; i < c.size(); i++) {
			// hMod: remove unnecessary cast
			 c.get(i).a();
		}
		c.clear();
	}

	private ap a(int i, int j, boolean flag) {
		long l = (long) i + 0x7fffffffL | (long) j + 0x7fffffffL << 32;
		ap ap1 = (ap) b.a(l);
		if (ap1 == null && flag) {
			ap1 = new ap(this, i, j);
			b.a(l, ap1);
		}
		return ap1;
	}

	public void a(int i, int j, int k) {
		int l = i >> 4;
		int i1 = k >> 4;
		ap ap1 = a(l, i1, false);
		if (ap1 != null)
			ap1.a(i & 0xf, j, k & 0xf);
	}

	public void a(fi fi1) {
		int i = (int) fi1.p >> 4;
		int j = (int) fi1.r >> 4;
		fi1.d = fi1.p;
		fi1.e = fi1.r;
		for (int k = i - 10; k <= i + 10; k++) {
			for (int l = j - 10; l <= j + 10; l++) {
				a(k, l, true).a(fi1);
			}
		}
		a.add(fi1);
	}

	public void b(fi fi1) {
		int i = (int) fi1.d >> 4;
		int j = (int) fi1.e >> 4;
		for (int k = i - 10; k <= i + 10; k++) {
			for (int l = j - 10; l <= j + 10; l++) {
				ap ap1 = a(k, l, false);
				if (ap1 != null)
					ap1.b(fi1);
			}
		}
		a.remove(fi1);
	}

	private boolean a(int i, int j, int k, int l) {
		int i1 = i - k;
		int j1 = j - l;
		if (i1 < -10 || i1 > 10)
			return false;
		return j1 >= -10 && j1 <= 10;
	}

	public void c(fi fi1) {
		int i = (int) fi1.p >> 4;
		int j = (int) fi1.r >> 4;
		double d1 = fi1.d - fi1.p;
		double d2 = fi1.e - fi1.r;
		double d3 = d1 * d1 + d2 * d2;
		if (d3 < 64D)
			return;
		int k = (int) fi1.d >> 4;
		int l = (int) fi1.e >> 4;
		int i1 = i - k;
		int j1 = j - l;
		if (i1 == 0 && j1 == 0)
			return;
		for (int k1 = i - 10; k1 <= i + 10; k1++) {
			for (int l1 = j - 10; l1 <= j + 10; l1++) {
				if (!a(k1, l1, k, l))
					a(k1, l1, true).a(fi1);
				if (a(k1 - i1, l1 - j1, i, j))
					continue;
				ap ap1 = a(k1 - i1, l1 - j1, false);
				if (ap1 != null)
					ap1.b(fi1);
			}
		}
		fi1.d = fi1.p;
		fi1.e = fi1.r;
	}

	public int b() {
		return 144;
	}

	static MinecraftServer a(jh jh1) {
		return jh1.d;
	}

	static fv b(jh jh1) {
		return jh1.b;
	}

	static List c(jh jh1) {
		return jh1.c;
	}

	// hMod: generificy
	private List<fi> a;
	private fv b;
	private List<ap> c;
	private MinecraftServer d;

	// hMod: bring back old "send packet to chunk" method from alpha
	public void sendPacketToChunk(jv packetToSend, int globalx, int globaly, int globalz) {
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
}
