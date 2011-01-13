
import java.util.List;
import java.util.Random;

public class db extends bm {

    public int e = -1;
    private String h;
    public double f;
    public double g = 0.0D;

    public db() {
        h = "Pig";
        e = 20;
    }

    public void a(String paramString) {
        h = paramString;
    }

    public boolean a() {
        return a.a(b + 0.5D, c + 0.5D, d + 0.5D, 16.0D) != null;
    }

    public void f() {
        g = f;

        if (!a()) {
            return;
        }

        double d1 = b + a.l.nextFloat();
        double d2 = c + a.l.nextFloat();
        double d3 = d + a.l.nextFloat();
        a.a("smoke", d1, d2, d3, 0.0D, 0.0D, 0.0D);
        a.a("flame", d1, d2, d3, 0.0D, 0.0D, 0.0D);

        f += 1000.0F / (e + 200.0F);
        while (f > 360.0D) {
            f -= 360.0D;
            g -= 360.0D;
        }

        if (e == -1) {
            b();
        }

        if (e > 0) {
            e -= 1;
            return;
        }

        int i = 4;
        for (int j = 0; j < i; j++) {
            mj localmj = (mj) (mj) jn.a(h, a);
            if (localmj == null) {
                return;
            }

            int k = a.a(localmj.getClass(), fa.b(b, c, d, b + 1, c + 1, d + 1).b(8.0D, 4.0D, 8.0D)).size();
            if (k >= 6) {
                b();
                return;
            }

            if (localmj != null) {
                double d4 = b + (a.l.nextDouble() - a.l.nextDouble()) * 4.0D;
                double d5 = c + a.l.nextInt(3) - 1;
                double d6 = d + (a.l.nextDouble() - a.l.nextDouble()) * 4.0D;

                localmj.c(d4, d5, d6, a.l.nextFloat() * 360.0F, 0.0F);

                if (localmj.b()) {
                    // hMod: allow entities to spawn
                    if ((Boolean) (etc.getLoader().callHook(PluginLoader.Hook.MOB_SPAWN, new Mob(localmj)))) {
                        d();
                        return;
                    }
                    a.a(localmj);

                    for (int m = 0; m < 20; m++) {
                        d1 = b + 0.5D + (a.l.nextFloat() - 0.5D) * 2.0D;
                        d2 = c + 0.5D + (a.l.nextFloat() - 0.5D) * 2.0D;
                        d3 = d + 0.5D + (a.l.nextFloat() - 0.5D) * 2.0D;

                        a.a("smoke", d1, d2, d3, 0.0D, 0.0D, 0.0D);
                        a.a("flame", d1, d2, d3, 0.0D, 0.0D, 0.0D);
                    }

                    localmj.R();
                    b();
                }
            }
        }

        super.f();
    }

    private void b() {
        e = (200 + a.l.nextInt(600));
    }

    public void a(ah paramah) {
        super.a(paramah);
        h = paramah.h("EntityId");
        e = paramah.c("Delay");
    }

    public void b(ah paramah) {
        super.b(paramah);
        paramah.a("EntityId", h);
        paramah.a("Delay", (short) e);
    }
}
