
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class ia extends eh implements ed {
    public static Logger a = Logger.getLogger("Minecraft");
    public ba b;
    public boolean c = false;
    private MinecraftServer d;
    private dy e;
    private int f = 0;
    private double g;
    private double h;
    private double i;
    private boolean j = true;

    public ia(MinecraftServer paramMinecraftServer, ba paramba, dy paramdy) {
        this.d = paramMinecraftServer;
        this.b = paramba;
        paramba.a(this);
        this.e = paramdy;
        paramdy.a = this;
    }

    public void a() {
        this.b.a();
        if (this.f++ % 20 == 0) {
            this.b.a(new iw());
        }
    }

    public void c(String paramString) {
        this.b.a(new il(paramString));
        this.b.c();
        this.d.f.c(this.e);
        this.c = true;
    }

    public void a(gd paramgd) {
        double d1;
        if (!this.j) {
            d1 = paramgd.b - this.h;
            if ((paramgd.a == this.g) && (d1 * d1 < 0.01D) && (paramgd.c == this.i)) {
                this.j = true;
            }
        }
        if (this.j) {
            this.g = this.e.k;
            this.h = this.e.l;
            this.i = this.e.m;

            d1 = this.e.k;
            double d2 = this.e.l;
            double d3 = this.e.m;

            float f1 = this.e.q;
            float f2 = this.e.r;

            if (paramgd.h) {
                d1 = paramgd.a;
                d2 = paramgd.b;
                d3 = paramgd.c;
                double d4 = paramgd.d - paramgd.b;
                if ((d4 > 1.65D) || (d4 < 0.1D)) {
                    c("Illegal stance");
                    a.warning(this.e.ap + " had an illegal stance: " + d4);
                }
                this.e.ah = paramgd.d;
            }
            if (paramgd.i) {
                f1 = paramgd.e;
                f2 = paramgd.f;
            }

            this.e.i();
            this.e.L = 0.0F;
            this.e.b(this.g, this.h, this.i, f1, f2);

            double d4 = d1 - this.e.k;
            double d5 = d2 - this.e.l;
            double d6 = d3 - this.e.m;

            float f3 = 0.0625F;
            int k = (this.d.e.a(this.e, this.e.u.b().e(f3, f3, f3)).size() == 0) ? 1 : 0;

            this.e.c(d4, d5, d6);
            d4 = d1 - this.e.k;
            d5 = d2 - this.e.l;
            if ((d5 > -0.5D) || (d5 < 0.5D)) {
                d5 = 0.0D;
            }
            d6 = d3 - this.e.m;
            double d7 = d4 * d4 + d5 * d5 + d6 * d6;
            int l = 0;
            if (d7 > 0.0625D) {
                l = 1;
                a.warning(this.e.ap + " moved wrongly!");
            }
            this.e.b(d1, d2, d3, f1, f2);

            int i1 = (this.d.e.a(this.e, this.e.u.b().e(f3, f3, f3)).size() == 0) ? 1 : 0;
            if ((k != 0) && (((l != 0) || (i1 == 0)))) {
                a(this.g, this.h, this.i, f1, f2);
                return;
            }

            this.e.v = paramgd.g;
            this.d.f.b(this.e);
        }
    }

    public void a(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {
        this.j = false;
        this.g = paramDouble1;
        this.h = paramDouble2;
        this.i = paramDouble3;
        this.e.b(paramDouble1, paramDouble2, paramDouble3, paramFloat1, paramFloat2);
        this.e.a.b(new _do(paramDouble1, paramDouble2 + 1.620000004768372D, paramDouble2, paramDouble3, paramFloat1, paramFloat2, false));
    }

    public void a(hb paramhb) {
        boolean bool = this.d.e.x = this.d.f.g(this.e.ap);
        int k = 0;
        if (paramhb.e == 0) {
            k = 1;
        }
        if (paramhb.e == 1) {
            k = 1;
        }

        if (k != 0) {
            double d1 = this.e.l;
            this.e.l = this.e.ah;
            fp localfp = this.e.a(4.0D, 1.0F);
            this.e.l = d1;
            if (localfp == null) {
                return;
            }
            if ((localfp.b != paramhb.a) || (localfp.c != paramhb.b) || (localfp.d != paramhb.c) || (localfp.e != paramhb.d)) {
                return;
            }
        }
        int l = paramhb.a;
        int i1 = paramhb.b;
        int i2 = paramhb.c;
        int i3 = paramhb.d;
        int i4 = (int) gh.e(l - this.d.e.m);
        int i5 = (int) gh.e(i2 - this.d.e.o);
        if (i4 > i5) {
            i5 = i4;
        }
        if (paramhb.e == 0) {
            if ((i5 > 16) || (bool)) {
                this.e.ac.a(l, i1, i2);
            }
        } else if (paramhb.e == 2) {
            this.e.ac.a();
        } else if (paramhb.e == 1) {
            if ((i5 > 16) || (bool)) {
                this.e.ac.a(l, i1, i2, i3);
            }
        } else if (paramhb.e == 3) {
            double d2 = this.e.k - (l + 0.5D);
            double d3 = this.e.l - (i1 + 0.5D);
            double d4 = this.e.m - (i2 + 0.5D);
            double d5 = d2 * d2 + d3 * d3 + d4 * d4;
            if (d5 < 256.0D) {
                this.e.a.b(new er(l, i1, i2, this.d.e));
            }
        }
        this.d.e.x = false;
    }

    public void a(fc paramfc) {
        boolean bool = this.d.e.x = this.d.f.g(this.e.ap);
        int k = paramfc.b;
        int l = paramfc.c;
        int i1 = paramfc.d;
        int i2 = paramfc.e;
        int i3 = (int) gh.e(k - this.d.e.m);
        int i4 = (int) gh.e(i1 - this.d.e.o);
        if (i3 > i4) {
            i4 = i3;
        }
        if ((i4 > 16) || (bool)) {
            gn localgn = (paramfc.a >= 0) ? new gn(paramfc.a) : null;
            this.e.ac.a(this.e, this.d.e, localgn, k, l, i1, i2);
        }
        this.e.a.b(new er(k, l, i1, this.d.e));
        this.d.e.x = false;
    }

    public void a(String paramString) {
        a.info(this.e.ap + " lost connection: " + paramString);
        this.d.f.c(this.e);
        this.c = true;
    }

    public void a(hn paramhn) {
        a.warning(super.getClass() + " wasn't prepared to deal with a " + paramhn.getClass());
        c("Protocol error, unexpected packet");
    }

    public void b(hn paramhn) {
        this.b.a(paramhn);
    }

    public void a(ft paramft) {
        int k = paramft.b;
        if (k == 0) {
            this.e.ai.a[this.e.ai.d] = null;
        } else {
            this.e.ai.a[this.e.ai.d] = new gn(k);
        }
        this.d.k.a(this.e, new ft(this.e.c, k));
    }

    public void a(k paramk) {
        double d1 = paramk.b / 32.0D;
        double d2 = paramk.c / 32.0D;
        double d3 = paramk.d / 32.0D;
        fl localfl = new fl(this.d.e, d1, d2, d3, new gn(paramk.h, paramk.i));
        localfl.n = (paramk.e / 128.0D);
        localfl.o = (paramk.f / 128.0D);
        localfl.p = (paramk.g / 128.0D);
        localfl.ac = 10;
        this.d.e.a(localfl);
    }

    public void a(az paramaz) {
        String str = paramaz.a;
        if (str.length() > 100) {
            c("Chat message too long");
            return;
        }
        str = str.trim();
        for (int k = 0; k < str.length(); ++k) {
            if (" !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_'abcdefghijklmnopqrstuvwxyz{|}~⌂ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»".indexOf(str.charAt(k)) < 0) {
                c("Illegal characters in chat");
                return;
            }
        }
        if (!Scripting.getInstance(e).chat(str, str.split(" "))) {
            if (str.startsWith("/")) {
                d(str);
            } else {
                str = "<" + this.e.ap + "> " + str;
                a.info(str);
                this.d.f.a(new az(str));
            }
        }
    }

    private void d(String paramString) {
        if (paramString.toLowerCase().startsWith("/me ")) {
            paramString = "* " + this.e.ap + " " + paramString.substring(paramString.indexOf(" ")).trim();
            a.info(paramString);
            this.d.f.a(new az(paramString));
        } else if (paramString.toLowerCase().startsWith("/tell ")) {
            String[] arrayOfString = paramString.split(" ");
            if (arrayOfString.length >= 3) {
                paramString = paramString.substring(paramString.indexOf(" ")).trim();
                paramString = paramString.substring(paramString.indexOf(" ")).trim();

                paramString = "§7" + this.e.ap + " whispers " + paramString;
                a.info(paramString + " to " + arrayOfString[1]);
                if (!this.d.f.a(arrayOfString[1], new az(paramString))) {
                    b(new az("§cThere's no player by that name online."));
                }
            }
        } else {
            int k;
            if (paramString.toLowerCase().equalsIgnoreCase("/home")) {
                a.info(this.e.ap + " returned home");
                k = this.d.e.d(this.d.e.m, this.d.e.o);
                a(this.d.e.m + 0.5D, k + 1.5D, this.d.e.o + 0.5D, 0.0F, 0.0F);
            } else if (paramString.toLowerCase().equalsIgnoreCase("/iron")) {
                if (MinecraftServer.b.containsKey(this.e.ap)) {
                    a.info(this.e.ap + " failed to iron!");
                    b(new az("§cYou can't /iron again so soon!"));
                } else {
                    MinecraftServer.b.put(this.e.ap, Integer.valueOf(6000));
                    a.info(this.e.ap + " ironed!");
                    for (k = 0; k < 4; ++k) {
                        this.e.a(new gn(ex.m, 1));
                    }
                }
            } else if (paramString.toLowerCase().equalsIgnoreCase("/wood")) {
                if (MinecraftServer.b.containsKey(this.e.ap)) {
                    a.info(this.e.ap + " failed to wood!");
                    b(new az("§cYou can't /wood again so soon!"));
                } else {
                    MinecraftServer.b.put(this.e.ap, Integer.valueOf(6000));
                    a.info(this.e.ap + " wooded!");
                    for (k = 0; k < 4; ++k) {
                        this.e.a(new gn(fd.y, 1));
                    }
                }
            } else {
                String str;
                if (this.d.f.g(this.e.ap)) {
                    str = paramString.substring(1);
                    a.info(this.e.ap + " issued server command: " + str);
                    this.d.a(str, this);
                } else {
                    str = paramString.substring(1);
                    a.info(this.e.ap + " tried command: " + str);
                }
            }
        }
    }

    public void a(o paramo) {
        if (paramo.b == 1) {
            this.e.y();
        }
    }

    public void a(il paramil) {
        this.b.a("Quitting");
    }

    public int b() {
        return this.b.d();
    }

    public void b(String paramString) {
        b(new az("§7" + paramString));
    }

    public String c() {
        return this.e.ap;
    }
}
