import java.util.List;
import java.util.Random;

public class cf extends ay {
    public int e = -1;
    public String f;
    public double g;
    public double h = 0.0D;

    public cf() {
        this.f = "Pig";
        this.e = 20;
    }

    public boolean a() {
        return this.a.a(this.b + 0.5D, this.c + 0.5D, this.d + 0.5D, 16.0D) != null;
    }

    @Override
    public void b() {
        this.h = this.g;

        if (!a()) {
            return;
        }

        double d1 = this.b + this.a.l.nextFloat();
        double d2 = this.c + this.a.l.nextFloat();
        double d3 = this.d + this.a.l.nextFloat();
        this.a.a("smoke", d1, d2, d3, 0.0D, 0.0D, 0.0D);
        this.a.a("flame", d1, d2, d3, 0.0D, 0.0D, 0.0D);

        this.g += 1000.0F / (this.e + 200.0F);
        while (this.g > 360.0D) {
            this.g -= 360.0D;
            this.h -= 360.0D;
        }

        if (this.e == -1) {
            d();
        }

        if (this.e > 0) {
            this.e -= 1;
            return;
        }

        int i = 4;
        for (int j = 0; j < i; j++) {
            ka localka = (ka) hp.a(this.f, this.a);
            if (localka == null) {
                return;
            }

            int k = this.a.a(localka.getClass(), dw.b(this.b, this.c, this.d, this.b + 1, this.c + 1, this.d + 1).b(8.0D, 4.0D, 8.0D)).size();
            if (k >= 6) {
                d();
                return;
            }

            if (localka != null) {
                double d4 = this.b + (this.a.l.nextDouble() - this.a.l.nextDouble()) * 4.0D;
                double d5 = this.c + this.a.l.nextInt(3) - 1;
                double d6 = this.d + (this.a.l.nextDouble() - this.a.l.nextDouble()) * 4.0D;

                localka.c(d4, d5, d6, this.a.l.nextFloat() * 360.0F, 0.0F);

                if (localka.a()) {
                    // hMod: allow entities to spawn
                    if ((Boolean) (etc.getLoader().callHook(PluginLoader.Hook.MOB_SPAWN, new Mob(localka)))) {
                        d();
                        return;
                    }
                    this.a.a(localka);

                    for (int m = 0; m < 20; m++) {
                        d1 = this.b + 0.5D + (this.a.l.nextFloat() - 0.5D) * 2.0D;
                        d2 = this.c + 0.5D + (this.a.l.nextFloat() - 0.5D) * 2.0D;
                        d3 = this.d + 0.5D + (this.a.l.nextFloat() - 0.5D) * 2.0D;

                        this.a.a("smoke", d1, d2, d3, 0.0D, 0.0D, 0.0D);
                        this.a.a("flame", d1, d2, d3, 0.0D, 0.0D, 0.0D);
                    }

                    localka.J();
                    d();
                }
            }
        }

        super.b();
    }

    private void d() {
        this.e = (200 + this.a.l.nextInt(600));
    }

    @Override
    public void a(v paramv) {
        super.a(paramv);
        this.f = paramv.h("EntityId");
        this.e = paramv.c("Delay");
    }

    @Override
    public void b(v paramv) {
        super.b(paramv);
        paramv.a("EntityId", this.f);
        paramv.a("Delay", (short) this.e);
    }
}
