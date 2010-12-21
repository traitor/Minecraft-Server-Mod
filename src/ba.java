
import java.util.Random;

public class ba extends gu {

    protected ba(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, kz.u);
        a(true);
    }

    public void a(ff paramff, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        if (paramff.e(paramInt1, paramInt2 + 1, paramInt3)) {
            int i = 1;
            while (paramff.a(paramInt1, paramInt2 - i, paramInt3) == this.bh) {
                i++;
            }
            if (i < 3) {
                int j = paramff.b(paramInt1, paramInt2, paramInt3);
                if (j == 15) {
                    paramff.d(paramInt1, paramInt2 + 1, paramInt3, this.bh);
                    paramff.b(paramInt1, paramInt2, paramInt3, 0);
                } else {
                    paramff.b(paramInt1, paramInt2, paramInt3, j + 1);
                }
            }
        }
    }

    public el d(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        float f = 0.0625F;
        return el.b(paramInt1 + f, paramInt2, paramInt3 + f, paramInt1 + 1 - f, paramInt2 + 1 - f, paramInt3 + 1 - f);
    }

    public int a(int paramInt) {
        if (paramInt == 1) {
            return this.bg - 1;
        }
        if (paramInt == 0) {
            return this.bg + 1;
        }
        return this.bg;
    }

    public boolean a() {
        return false;
    }

    public boolean a(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        if (!super.a(paramff, paramInt1, paramInt2, paramInt3)) {
            return false;
        }

        return f(paramff, paramInt1, paramInt2, paramInt3);
    }

    public void b(ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (!f(paramff, paramInt1, paramInt2, paramInt3)) {
            a_(paramff, paramInt1, paramInt2, paramInt3, paramff.b(paramInt1, paramInt2, paramInt3));
            paramff.d(paramInt1, paramInt2, paramInt3, 0);
        }
    }

    public boolean f(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        if (paramff.c(paramInt1 - 1, paramInt2, paramInt3).a()) {
            return false;
        }
        if (paramff.c(paramInt1 + 1, paramInt2, paramInt3).a()) {
            return false;
        }
        if (paramff.c(paramInt1, paramInt2, paramInt3 - 1).a()) {
            return false;
        }
        if (paramff.c(paramInt1, paramInt2, paramInt3 + 1).a()) {
            return false;
        }
        int i = paramff.a(paramInt1, paramInt2 - 1, paramInt3);
        return (i == gu.aV.bh) || (i == gu.E.bh);
    }

    public void a(ff paramff, int paramInt1, int paramInt2, int paramInt3, ep paramep) {
        // hMod Damage hook: Cactus
        if (paramep instanceof lb && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.CACTUS, null, new LivingEntity((lb) paramep), 1)) {
            return;
        }
        paramep.a(null, 1);
    }
}
