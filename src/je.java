import java.util.List;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class je extends ey implements eu {
	public static Logger a = Logger.getLogger("Minecraft");
	public bh b;
	public boolean c = false;
	private MinecraftServer d;
	private ep e;
	private int f = 0;
	private double g;
	private double h;
	private double i;
	private boolean j = true;

	private hj k = null;

	public je(MinecraftServer paramMinecraftServer, bh parambh, ep paramep) {
		this.d = paramMinecraftServer;
		this.b = parambh;
		parambh.a(this);
		this.e = paramep;
		paramep.a = this;
	}
	
    /**
     * Returns the item in player's hand
     * @return
     */
    public int getItemInHand() {
        if (k != null) //Check to see if we are even holding anything
        {
            return k.c;
        }
        return -1;
    }

	public void a() {
		this.b.a();
		if (this.f++ % 20 == 0)
			this.b.a(new ke());
	}

	public void c(String paramString) {
		this.b.a(new jr(paramString));
		this.b.c();
		this.d.f.c(this.e);
		this.c = true;
	}

	public void a(gz paramgz) {
		if (!this.j) {
			double d1 = paramgz.b - this.h;
			if ((paramgz.a == this.g) && (d1 * d1 < 0.01D) && (paramgz.c == this.i)) {
				this.j = true;
			}
		}
		if (this.j) {
			if (this.e.k != null) {
				float f1 = this.e.v;
				float f2 = this.e.w;
				this.e.k.z();
				d3 = this.e.p;
				d4 = this.e.q;
				double d5 = this.e.r;
				d6 = 0.0D;
				d7 = 0.0D;
				if (paramgz.i) {
					f1 = paramgz.e;
					f2 = paramgz.f;
				}
				if ((paramgz.h) && (paramgz.b == -999.0D) && (paramgz.d == -999.0D)) {
					d6 = paramgz.a;
					d7 = paramgz.c;
				}

				this.e.k();
				this.e.b(d3, d4, d5, f1, f2);
				this.e.s = d6;
				this.e.u = d7;
				this.e.k.b_();

				return;
			}

			this.g = this.e.p;
			this.h = this.e.q;
			this.i = this.e.r;

			double d2 = this.e.p;
			double d3 = this.e.q;
			double d4 = this.e.r;

			float f3 = this.e.v;
			float f4 = this.e.w;

			if ((paramgz.h) && (paramgz.b == -999.0D) && (paramgz.d == -999.0D)) {
				paramgz.h = false;
			}

			if (paramgz.h) {
				d2 = paramgz.a;
				d3 = paramgz.b;
				d4 = paramgz.c;
				d6 = paramgz.d - paramgz.b;
				if ((d6 > 1.65D) || (d6 < 0.1D)) {
					c("Illegal stance");
					a.warning(this.e.ar + " had an illegal stance: " + d6);
				}
				this.e.aj = paramgz.d;
			}
			if (paramgz.i) {
				f3 = paramgz.e;
				f4 = paramgz.f;
			}

			this.e.k();
			this.e.Q = 0.0F;
			this.e.b(this.g, this.h, this.i, f3, f4);

			double d6 = d2 - this.e.p;
			double d7 = d3 - this.e.q;
			double d8 = d4 - this.e.r;

			float f5 = 0.0625F;
			int m = this.d.e.a(this.e, this.e.z.b().e(f5, f5, f5)).size() == 0 ? 1 : 0;

			this.e.c(d6, d7, d8);
			d6 = d2 - this.e.p;
			d7 = d3 - this.e.q;
			if ((d7 > -0.5D) || (d7 < 0.5D)) {
				d7 = 0.0D;
			}
			d8 = d4 - this.e.r;
			double d9 = d6 * d6 + d7 * d7 + d8 * d8;
			int n = 0;
			if (d9 > 0.0625D) {
				n = 1;
				a.warning(this.e.ar + " moved wrongly!");
			}
			this.e.b(d2, d3, d4, f3, f4);

			int i1 = this.d.e.a(this.e, this.e.z.b().e(f5, f5, f5)).size() == 0 ? 1 : 0;
			if ((m != 0) && ((n != 0) || (i1 == 0))) {
				a(this.g, this.h, this.i, f3, f4);
				return;
			}

			this.e.A = paramgz.g;
			this.d.f.b(this.e);
		}
	}

	public void a(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {
		this.j = false;
		this.g = paramDouble1;
		this.h = paramDouble2;
		this.i = paramDouble3;
		this.e.b(paramDouble1, paramDouble2, paramDouble3, paramFloat1, paramFloat2);
		this.e.a.b(new ed(paramDouble1, paramDouble2 + 1.620000004768372D, paramDouble2, paramDouble3, paramFloat1, paramFloat2, false));
	}

	public void a(hz paramhz) {
		this.e.ak.a[this.e.ak.d] = this.k;
		boolean bool = this.d.e.B = this.d.f.g(this.e.ar);
		int m = 0;
		if (paramhz.e == 0)
			m = 1;
		if (paramhz.e == 1)
			m = 1;

		if (m != 0) {
			double d1 = this.e.q;
			this.e.q = this.e.aj;
			gm localgm = this.e.a(4.0D, 1.0F);
			this.e.q = d1;
			if (localgm == null)
				return;
			if ((localgm.b != paramhz.a) || (localgm.c != paramhz.b) || (localgm.d != paramhz.c) || (localgm.e != paramhz.d)) {
				return;
			}
		}
		int n = paramhz.a;
		int i1 = paramhz.b;
		int i2 = paramhz.c;
		int i3 = paramhz.d;
		int i4 = (int) hd.e(n - this.d.e.m);
		int i5 = (int) hd.e(i2 - this.d.e.o);
		if (i4 > i5)
			i5 = i4;
		if (paramhz.e == 0) {
			if ((i5 > 16) || (bool))
				this.e.c.a(n, i1, i2);
		} else if (paramhz.e == 2) {
			this.e.c.a();
		} else if (paramhz.e == 1) {
			if ((i5 > 16) || (bool))
				this.e.c.a(n, i1, i2, i3);
		} else if (paramhz.e == 3) {
			double d2 = this.e.p - (n + 0.5D);
			double d3 = this.e.q - (i1 + 0.5D);
			double d4 = this.e.r - (i2 + 0.5D);
			double d5 = d2 * d2 + d3 * d3 + d4 * d4;
			if (d5 < 256.0D) {
				this.e.a.b(new fj(n, i1, i2, this.d.e));
			}
		}
		this.d.e.B = false;
	}

	public void a(fx paramfx) {
		boolean bool = this.d.e.B = this.d.f.g(this.e.ar);
		if (paramfx.e == 255) {
			hj localhj1 = paramfx.a >= 0 ? new hj(paramfx.a) : null;
			this.e.c.a(this.e, this.d.e, localhj1);
		} else {
			int m = paramfx.b;
			int n = paramfx.c;
			int i1 = paramfx.d;
			int i2 = paramfx.e;
			int i3 = (int) hd.e(m - this.d.e.m);
			int i4 = (int) hd.e(i1 - this.d.e.o);
			if (i3 > i4)
				i4 = i3;
			if ((i4 > 16) || (bool)) {
				hj localhj2 = paramfx.a >= 0 ? new hj(paramfx.a) : null;
				this.e.c.a(this.e, this.d.e, localhj2, m, n, i1, i2);
			}

			this.e.a.b(new fj(m, n, i1, this.d.e));

			if (i2 == 0)
				n--;
			if (i2 == 1)
				n++;
			if (i2 == 2)
				i1--;
			if (i2 == 3)
				i1++;
			if (i2 == 4)
				m--;
			if (i2 == 5)
				m++;

			this.e.a.b(new fj(m, n, i1, this.d.e));
		}
		this.d.e.B = false;
	}

	public void a(String paramString) {
		a.info(this.e.ar + " lost connection: " + paramString);
		this.d.f.c(this.e);
		this.c = true;
	}

	public void a(io paramio) {
		a.warning(getClass() + " wasn't prepared to deal with a " + paramio.getClass());
		c("Protocol error, unexpected packet");
	}

	public void b(io paramio) {
		this.b.a(paramio);
	}

	public void a(gp paramgp) {
		int m = paramgp.b;
		this.e.ak.d = (this.e.ak.a.length - 1);
		if (m == 0)
			this.k = null;
		else {
			this.k = new hj(m);
		}
		this.e.ak.a[this.e.ak.d] = this.k;
		this.d.k.a(this.e, new gp(this.e.g, m));
	}

	public void a(l paraml) {
		double d1 = paraml.b / 32.0D;
		double d2 = paraml.c / 32.0D;
		double d3 = paraml.d / 32.0D;
		gh localgh = new gh(this.d.e, d1, d2, d3, new hj(paraml.h, paraml.i));
		localgh.s = (paraml.e / 128.0D);
		localgh.t = (paraml.f / 128.0D);
		localgh.u = (paraml.g / 128.0D);
		localgh.c = 10;
		this.d.e.a(localgh);
	}

	public void a(bg parambg) {
		String str = parambg.a;
		if (str.length() > 100) {
			c("Chat message too long");
			return;
		}
		str = str.trim();
		for (int m = 0; m < str.length(); m++) {
			if (" !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_'abcdefghijklmnopqrstuvwxyz{|}~⌂ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»"
					.indexOf(str.charAt(m)) < 0) {
				c("Illegal characters in chat");
				return;
			}
		}

		if (str.startsWith("/")) {
			d(str);
		} else {
			str = "<" + this.e.ar + "> " + str;
			a.info(str);
			this.d.f.a(new bg(str));
		}
	}
	
    /**
     * Sends a message to the player
     * @param msg
     */
    public void msg(String msg) {
        b(new bg(msg));
    }

	private void d(String paramString) {
		if (paramString.toLowerCase().startsWith("/me ")) {
			paramString = "* " + this.e.ar + " " + paramString.substring(paramString.indexOf(" ")).trim();
			a.info(paramString);
			this.d.f.a(new bg(paramString));
		} else if (paramString.toLowerCase().startsWith("/tell ")) {
			String[] arrayOfString = paramString.split(" ");
			if (arrayOfString.length >= 3) {
				paramString = paramString.substring(paramString.indexOf(" ")).trim();
				paramString = paramString.substring(paramString.indexOf(" ")).trim();

				paramString = "§7" + this.e.ar + " whispers " + paramString;
				a.info(paramString + " to " + arrayOfString[1]);
				if (!this.d.f.a(arrayOfString[1], new bg(paramString))) {
					b(new bg("§cThere's no player by that name online."));
				}
			}
		} else if (paramString.toLowerCase().equalsIgnoreCase("/home")) {
			a.info(this.e.ar + " returned home");
			int m = this.d.e.e(this.d.e.m, this.d.e.o);
			a(this.d.e.m + 0.5D, m + 1.5D, this.d.e.o + 0.5D, 0.0F, 0.0F);
		} else {
			String str;
			if (this.d.f.g(this.e.ar)) {
				str = paramString.substring(1);
				a.info(this.e.ar + " issued server command: " + str);
				this.d.a(str, this);
			} else {
				str = paramString.substring(1);
				a.info(this.e.ar + " tried command: " + str);
			}
		}
	}

	public void a(q paramq) {
		if (paramq.b == 1)
			this.e.E();
	}

	public void a(jr paramjr) {
		this.b.a("Quitting");
	}

	public int b() {
		return this.b.d();
	}

	public void b(String paramString) {
		b(new bg("§7" + paramString));
	}

	public String c() {
		return this.e.ar;
	}

	public void a(u paramu) {
		if (paramu.a == -1)
			this.e.ak.a = paramu.b;
		if (paramu.a == -2)
			this.e.ak.c = paramu.b;
		if (paramu.a == -3)
			this.e.ak.b = paramu.b;
	}

	public void d() {
		this.b.a(new u(-1, this.e.ak.a));
		this.b.a(new u(-2, this.e.ak.c));
		this.b.a(new u(-3, this.e.ak.b));
	}

	public void a(jc paramjc) {
		if (paramjc.e.d("x") != paramjc.a)
			return;
		if (paramjc.e.d("y") != paramjc.b)
			return;
		if (paramjc.e.d("z") != paramjc.c)
			return;

		ay localay = this.d.e.k(paramjc.a, paramjc.b, paramjc.c);
		if (localay != null) {
			try {
				localay.a(paramjc.e);
			} catch (Exception localException) {
			}
			localay.c();
		}
	}

	public void a(a parama) {
		dw localdw = this.d.e.a(parama.b);
		if ((localdw != null) && (this.e.g(localdw)))
			localdw.a(this.e);
	}
}