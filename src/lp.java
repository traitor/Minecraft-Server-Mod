
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class lp extends gi
        implements gd {

    public static Logger a = Logger.getLogger("Minecraft");
    public ca b;
    public boolean c = false;
    private MinecraftServer d;
    private fy e;
    private int f = 0;
    private double g;
    private double h;
    private double i;
    private boolean j = true;
    private Map k = new HashMap();

    public lp(MinecraftServer paramMinecraftServer, ca paramca, fy paramfy) {
        d = paramMinecraftServer;
        b = paramca;
        paramca.a(this);
        e = paramfy;
        paramfy.a = this;
    }

    /**
     * Returns the item in player's hand
     *
     * @return
     */
    public int getItemInHand() {
        if (this.e.an.e() != null) {
            return this.e.an.e().c;
        }
        return -1;
    }

    public Player getPlayer() {
        return e.getPlayer();
    }

    /**
     * Sends a message to the player
     *
     * @param msg
     */
    public void msg(String msg) {
        b(new bz(msg));
    }

    public void a() {
        b.a();
        if (f++ % 20 == 0) {
            b.a(new mt());
        }
    }

    public void a(String paramString) {
        b.a(new mf(paramString));
        b.c();
        d.f.a(new bz("\u00A7e" + e.aw + " left the game."));
        d.f.c(e);
        c = true;
    }

    public void a(iv paramiv) {
        if (!j) {
            double d1 = paramiv.b - h;
            if ((paramiv.a == g) && (d1 * d1 < 0.01D) && (paramiv.c == i)) {
                j = true;
            }
        }
        if (j) {
            if (e.k != null) {
                float f1 = e.v;
                float f2 = e.w;
                e.k.E();
                double d3 = e.p;
                double d4 = e.q;
                double d5 = e.r;
                double d6 = 0.0D;
                double d7 = 0.0D;
                if (paramiv.i) {
                    f1 = paramiv.e;
                    f2 = paramiv.f;
                }
                if ((paramiv.h) && (paramiv.b == -999.0D) && (paramiv.d == -999.0D)) {
                    d6 = paramiv.a;
                    d7 = paramiv.c;
                }

                e.A = paramiv.g;

                e.n();
                e.c(d6, 0.0D, d7);
                e.b(d3, d4, d5, f1, f2);
                e.s = d6;
                e.u = d7;
                if (e.k != null) {
                    d.e.b(e.k, true);
                }
                if (e.k != null) {
                    e.k.E();
                }
                d.f.b(e);
                g = e.p;
                h = e.q;
                i = e.r;
                d.e.f(e);

                return;
            }

            double d2 = e.q;
            g = e.p;
            h = e.q;
            i = e.r;

            double d3 = e.p;
            double d4 = e.q;
            double d5 = e.r;

            float f3 = e.v;
            float f4 = e.w;

            if ((paramiv.h) && (paramiv.b == -999.0D) && (paramiv.d == -999.0D)) {
                paramiv.h = false;
            }

            if (paramiv.h) {
                d3 = paramiv.a;
                d4 = paramiv.b;
                d5 = paramiv.c;
                double d7 = paramiv.d - paramiv.b;
                if ((d7 > 1.65D) || (d7 < 0.1D)) {
                    a("Illegal stance");
                    a.warning(e.aw + " had an illegal stance: " + d7);
                }
                e.al = paramiv.d;
            }
            if (paramiv.i) {
                f3 = paramiv.e;
                f4 = paramiv.f;
            }

            e.n();
            e.R = 0.0F;
            e.b(g, h, i, f3, f4);

            double d7 = d3 - e.p;
            double d8 = d4 - e.q;
            double d9 = d5 - e.r;

            float f5 = 0.0625F;
            int m = d.e.a(e, e.z.b().e(f5, f5, f5)).size() == 0 ? 1 : 0;

            e.c(d7, d8, d9);
            d7 = d3 - e.p;
            d8 = d4 - e.q;
            if ((d8 > -0.5D) || (d8 < 0.5D)) {
                d8 = 0.0D;
            }
            d9 = d5 - e.r;
            double d10 = d7 * d7 + d8 * d8 + d9 * d9;
            int n = 0;
            if (d10 > 0.0625D) {
                n = 1;
                a.warning(e.aw + " moved wrongly!");
                System.out.println("Got position " + d3 + ", " + d4 + ", " + d5);
                System.out.println("Expected " + e.p + ", " + e.q + ", " + e.r);
            }
            e.b(d3, d4, d5, f3, f4);

            int i1 = d.e.a(e, e.z.b().e(f5, f5, f5)).size() == 0 ? 1 : 0;
            if ((m != 0) && ((n != 0) || (i1 == 0))) {
                a(g, h, i, f3, f4);
                return;
            }

            e.A = paramiv.g;
            d.f.b(e);
            e.b(e.q - d2, paramiv.g);
        }
    }

    public void a(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {
        j = false;
        g = paramDouble1;
        h = paramDouble2;
        i = paramDouble3;
        e.b(paramDouble1, paramDouble2, paramDouble3, paramFloat1, paramFloat2);
        e.a.b(new fl(paramDouble1, paramDouble2 + 1.620000004768372D, paramDouble2, paramDouble3, paramFloat1, paramFloat2, false));
    }

    public void a(kf paramkf) {
        if (paramkf.e == 4) {
            e.O();
            return;
        }
        boolean bool = d.e.B = d.f.g(e.aw);
        int m = 0;
        if (paramkf.e == 0) {
            m = 1;
        }
        if (paramkf.e == 1) {
            m = 1;
        }

        int n = paramkf.a;
        int i1 = paramkf.b;
        int i2 = paramkf.c;
        if (m != 0) {
            double d1 = e.p - (n + 0.5D);
            double d2 = e.q - (i1 + 0.5D);
            double d4 = e.r - (i2 + 0.5D);
            double d6 = d1 * d1 + d2 * d2 + d4 * d4;
            if (d6 > 36.0D) {
                return;
            }

            double d8 = e.q;
            e.q = e.al;

            e.q = d8;
        }

        int i3 = paramkf.d;
        int i4 = (int) iz.e(n - d.e.m);
        int i5 = (int) iz.e(i2 - d.e.o);
        if (i4 > i5) {
            i5 = i4;
        }
        if (paramkf.e == 0) {
            if ((i5 > 16) || (bool)) {
                e.c.a(n, i1, i2);
            }
        } else if (paramkf.e == 2) {
            e.c.a();
        } else if (paramkf.e == 1) {
            if ((i5 > 16) || (bool)) {
                e.c.a(n, i1, i2, i3);
            }
        } else if (paramkf.e == 3) {
            double d3 = e.p - (n + 0.5D);
            double d5 = e.q - (i1 + 0.5D);
            double d7 = e.r - (i2 + 0.5D);
            double d9 = d3 * d3 + d5 * d5 + d7 * d7;
            if (d9 < 256.0D) {
                e.a.b(new gw(n, i1, i2, d.e));
            }
        }
        d.e.B = false;
    }

    public void a(hp paramhp) {
        jl localjl = e.an.e();

        boolean bool = d.e.B = d.f.g(e.aw);
        if (paramhp.d == 255) {
            if (localjl == null) {
                return;
            }
            e.c.a(e, d.e, localjl);
        } else {
            int m = paramhp.a;
            int n = paramhp.b;
            int i1 = paramhp.c;
            int i2 = paramhp.d;
            int i3 = (int) iz.e(m - d.e.m);
            int i4 = (int) iz.e(i1 - d.e.o);
            if (i3 > i4) {
                i4 = i3;
            }
            if ((i4 > 16) || (bool)) {
                e.c.a(e, d.e, localjl, m, n, i1, i2);
            }

            e.a.b(new gw(m, n, i1, d.e));

            if (i2 == 0) {
                n--;
            }
            if (i2 == 1) {
                n++;
            }
            if (i2 == 2) {
                i1--;
            }
            if (i2 == 3) {
                i1++;
            }
            if (i2 == 4) {
                m--;
            }
            if (i2 == 5) {
                m++;
            }

            e.a.b(new gw(m, n, i1, d.e));
        }
        if ((localjl != null) && (localjl.a == 0)) {
            e.an.a[e.an.c] = null;
        }

        e.am = true;
        e.an.a[e.an.c] = jl.b(e.an.a[e.an.c]);
        hj localhj = e.ap.a(e.an, e.an.c);
        e.ap.a();
        e.am = false;

        if (!jl.a(e.an.e(), paramhp.e)) {
            b(new in(e.ap.f, localhj.c, e.an.e()));
        }

        d.e.B = false;
    }

    public void a(String paramString, Object[] paramArrayOfObject) {
        a.info(e.aw + " lost connection: " + paramString);
        d.f.a(new bz("\u00A7e" + e.aw + " left the game."));
        d.f.c(e);
        c = true;
    }

    public void a(kx paramkx) {
        a.warning(getClass() + " wasn't prepared to deal with a " + paramkx.getClass());
        a("Protocol error, unexpected packet");
    }

    public void b(kx paramkx) {
        b.a(paramkx);
    }

    public void a(ik paramik) {
        e.an.c = paramik.a;
    }

    public void a(bz parambz) {
        String str = parambz.a;
        // hMod: redirect chathandling to player class
        getPlayer().chat(str);
    }

    public void a(z paramz) {
        if (paramz.b == 1) {
            e.K();
        }
    }

    public void a(gv paramgv) {
        System.out.println("handlePlayerCommand " + paramgv.a + " " + paramgv.b);
        if (paramgv.b == 1) {
            e.b(true);
        } else if (paramgv.b == 2) {
            e.b(false);
        }
    }

    public void a(mf parammf) {
        b.a("disconnect.quitting", new Object[0]);
    }

    public int b() {
        return b.d();
    }

    public void b(String paramString) {
        b(new bz("\u00A77" + paramString));
    }

    public String c() {
        return e.aw;
    }

    public void a(a parama) {
        fe localfe = d.e.a(parama.b);

        if ((localfe != null) && (e.i(localfe))) {
            if (parama.c == 0) {
                e.g(localfe);
            } else if (parama.c == 1) {
                e.h(localfe);
            }
        }
    }

    public void a(bo parambo) {
        if (e.aZ > 0) {
            return;
        }

        e = d.f.d(e);
    }

    public void a(h paramh) {
        e.N();
    }

    public void a(dc paramdc) {
        if ((e.ap.f == paramdc.a) && (e.ap.c(e))) {
            jl localjl = e.ap.a(paramdc.b, paramdc.c, e);

            if (jl.a(paramdc.e, localjl)) {
                e.a.b(new bd(paramdc.a, paramdc.d, true));
                e.am = true;
                e.ap.a();
                e.M();
                e.am = false;
            } else {
                k.put(Integer.valueOf(e.ap.f), Short.valueOf(paramdc.d));
                e.a.b(new bd(paramdc.a, paramdc.d, false));
                e.ap.a(e, false);

                ArrayList localArrayList = new ArrayList();
                for (int m = 0; m < e.ap.e.size(); m++) {
                    localArrayList.add(((hj) e.ap.e.get(m)).c());
                }
                e.a(e.ap, localArrayList);
            }
        }
    }

    public void a(bd parambd) {
        Short localShort = (Short) k.get(Integer.valueOf(e.ap.f));
        if ((localShort != null) && (parambd.b == localShort.shortValue()) && (e.ap.f == parambd.a) && (!e.ap.c(e))) {
            e.ap.a(e, true);
        }
    }

    public void a(hh paramhh) {
        if (d.e.f(paramhh.a, paramhh.b, paramhh.c)) {
            bm localbm = d.e.m(paramhh.a, paramhh.b, paramhh.c);
            int n, m;
            int i1;
            for (m = 0; m < 4; m++) {
                n = 1;
                // hMod: Remove the char limit, for plugins.
                //if (paramhh.d[m].length() > 15)
                //  n = 0;
                //else {
                for (i1 = 0; i1 < paramhh.d[m].length(); i1++) {
                    if (gg.a.indexOf(paramhh.d[m].charAt(i1)) < 0) {
                        n = 0;
                    }
                }
                //}
                if (n == 0) {
                    paramhh.d[m] = "!?";
                }
            }
            if ((localbm instanceof lv)) {

                m = paramhh.a;
                n = paramhh.b;
                i1 = paramhh.c;
                lv locallv = (lv) localbm;
                // hMod: Copy the old line text
                String[] old = Arrays.copyOf(locallv.e, locallv.e.length);

                for (int i2 = 0; i2 < 4; i2++) {
                    locallv.e[i2] = paramhh.d[i2];
                }

                // hMod: Check if we can change it
                Sign sign = new Sign(locallv);
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.SIGN_CHANGE, getPlayer(), sign)) {
                    locallv.e = Arrays.copyOf(old, old.length);
                }
                locallv.d();
                d.e.g(m, n, i1);
            }
        }
    }
}
