
import java.util.Random;

public class bg extends hr {

    protected bg(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, mh.u);
        a(true);
    }

    public void a(fv paramfv, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        if (paramfv.e(paramInt1, paramInt2 + 1, paramInt3)) {
            int i = 1;
            while (paramfv.a(paramInt1, paramInt2 - i, paramInt3) == bi) {
                i++;
            }
            if (i < 3) {
                int j = paramfv.b(paramInt1, paramInt2, paramInt3);
                if (j == 15) {
                    paramfv.e(paramInt1, paramInt2 + 1, paramInt3, bi);
                    paramfv.c(paramInt1, paramInt2, paramInt3, 0);
                } else {
                    paramfv.c(paramInt1, paramInt2, paramInt3, j + 1);
                }
            }
        }
    }

    public fa d(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        float f = 0.0625F;
        return fa.b(paramInt1 + f, paramInt2, paramInt3 + f, paramInt1 + 1 - f, paramInt2 + 1 - f, paramInt3 + 1 - f);
    }

    public int a(int paramInt) {
        if (paramInt == 1) {
            return bh - 1;
        }
        if (paramInt == 0) {
            return bh + 1;
        }
        return bh;
    }

    public boolean a() {
        return false;
    }

    public boolean a(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        if (!super.a(paramfv, paramInt1, paramInt2, paramInt3)) {
            return false;
        }

        return f(paramfv, paramInt1, paramInt2, paramInt3);
    }

    public void b(fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (!f(paramfv, paramInt1, paramInt2, paramInt3)) {
            a_(paramfv, paramInt1, paramInt2, paramInt3, paramfv.b(paramInt1, paramInt2, paramInt3));
            paramfv.e(paramInt1, paramInt2, paramInt3, 0);
        }
    }

    public boolean f(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        if (paramfv.c(paramInt1 - 1, paramInt2, paramInt3).a()) {
            return false;
        }
        if (paramfv.c(paramInt1 + 1, paramInt2, paramInt3).a()) {
            return false;
        }
        if (paramfv.c(paramInt1, paramInt2, paramInt3 - 1).a()) {
            return false;
        }
        if (paramfv.c(paramInt1, paramInt2, paramInt3 + 1).a()) {
            return false;
        }
        int i = paramfv.a(paramInt1, paramInt2 - 1, paramInt3);
        return (i == hr.aV.bi) || (i == hr.E.bi);
    }

    public void a(fv paramfv, int paramInt1, int paramInt2, int paramInt3, fe paramfe) {
        // hMod Damage hook: Cactus
        if (paramfe instanceof mj && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.CACTUS, null, new LivingEntity((mj) paramfe), 1)) {
            return;
        }
        paramfe.a((fe)null, 1);
    }
}
