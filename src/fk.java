
import java.util.Random;

public class fk extends fz {

    int a;
    int b;
    int c = 30;
    int d = -1;

    public fk(em paramem) {
        super(paramem);
        this.aF = "/mob/creeper.png";
    }

    public void a(v paramv) {
        super.a(paramv);
    }

    public void b(v paramv) {
        super.b(paramv);
    }

    protected void c() {
        this.b = this.a;
        if ((this.a > 0) && (this.d < 0)) {
            this.a -= 1;
        }
        if (this.d >= 0) {
            this.d = 2;
        }
        super.c();
        if (this.d != 1) {
            this.d = -1;
        }
    }

    protected String e() {
        return "mob.creeper";
    }

    protected String f() {
        return "mob.creeperdeath";
    }

    public void f(dw paramdw) {
        super.f(paramdw);

        if ((paramdw instanceof ez)) {
            a(fs.aU.aW + this.V.nextInt(2), 1);
        }
    }

    protected void a(dw paramdw, float paramFloat) {
        if (((this.d <= 0) && (paramFloat < 3.0F)) || ((this.d > 0) && (paramFloat < 7.0F))) {
            if (this.a == 0) {
                this.l.a(this, "random.fuse", 1.0F, 0.5F);
            }
            this.d = 1;
            this.a += 1;

            if (this.a == this.c) {
                Block block = new Block((int) l.a((int) Math.floor(p), (int) Math.floor(q), (int) Math.floor(r)), (int) Math.floor(p), (int) Math.floor(q), (int) Math.floor(r));
                block.setStatus(2);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.EXPLODE, new Object[]{block})) {
                    this.l.a(this, this.p, this.q, this.r, 3.0F);
                }
                l();
            }
            this.ai = true;
        }
    }

    protected int g() {
        return fs.K.aW;
    }
}
