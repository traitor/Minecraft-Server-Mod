
import java.util.List;
import java.util.Random;

public class cq extends bg {

    public int e = -1;
    public String f;
    public double g;
    public double h = 0.0D;

    public cq() {
        this.f = "Pig";
        this.e = 20;
    }

    public boolean a() {
        return this.a.a(this.b + 0.5D, this.c + 0.5D, this.d + 0.5D, 16.0D) != null;
    }

    public void e() {
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
            b();
        }

        if (this.e > 0) {
            this.e -= 1;
            return;
        }

        int i = 4;
        for (int j = 0; j < i; j++) {
            lc locallb = (lc) (lc) in.a(this.f, this.a);
            if (locallb == null) {
                return;
            }

            int k = this.a.a(locallb.getClass(), el.b(this.b, this.c, this.d, this.b + 1, this.c + 1, this.d + 1).b(8.0D, 4.0D, 8.0D)).size();
            if (k >= 6) {
                b();
                return;
            }

            if (locallb != null) {
                double d4 = this.b + (this.a.l.nextDouble() - this.a.l.nextDouble()) * 4.0D;
                double d5 = this.c + this.a.l.nextInt(3) - 1;
                double d6 = this.d + (this.a.l.nextDouble() - this.a.l.nextDouble()) * 4.0D;

                locallb.c(d4, d5, d6, this.a.l.nextFloat() * 360.0F, 0.0F);

                if (locallb.a()) {
                    // hMod: allow entities to spawn
                    if ((Boolean) (etc.getLoader().callHook(PluginLoader.Hook.MOB_SPAWN, new Mob(locallb)))) {
                        d();
                        return;
                    }
                    
                    this.a.a(locallb);

                    for (int m = 0; m < 20; m++) {
                        d1 = this.b + 0.5D + (this.a.l.nextFloat() - 0.5D) * 2.0D;
                        d2 = this.c + 0.5D + (this.a.l.nextFloat() - 0.5D) * 2.0D;
                        d3 = this.d + 0.5D + (this.a.l.nextFloat() - 0.5D) * 2.0D;

                        this.a.a("smoke", d1, d2, d3, 0.0D, 0.0D, 0.0D);
                        this.a.a("flame", d1, d2, d3, 0.0D, 0.0D, 0.0D);
                    }

                    locallb.O();
                    b();
                }
            }
        }

        super.e();
    }

    private void b() {
        this.e = (200 + this.a.l.nextInt(600));
    }

    public void a(ad paramad) {
        super.a(paramad);
        this.f = paramad.h("EntityId");
        this.e = paramad.c("Delay");
    }

    public void b(ad paramad) {
        super.b(paramad);
        paramad.a("EntityId", this.f);
        paramad.a("Delay", (short) this.e);
    }
}
