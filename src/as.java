import java.util.Random;

public class as extends gc {
    protected as(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, jy.u);
        a(true);
    }

    @Override
    public void a(eq parameq, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        if (parameq.a(paramInt1, paramInt2 + 1, paramInt3) == 0) {
            int i = 1;
            while (parameq.a(paramInt1, paramInt2 - i, paramInt3) == this.bh) {
                i++;
            }
            if (i < 3) {
                int j = parameq.b(paramInt1, paramInt2, paramInt3);
                if (j == 15) {
                    parameq.d(paramInt1, paramInt2 + 1, paramInt3, this.bh);
                    parameq.b(paramInt1, paramInt2, paramInt3, 0);
                } else {
                    parameq.b(paramInt1, paramInt2, paramInt3, j + 1);
                }
            }
        }
    }

    @Override
    public dw d(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        float f = 0.0625F;
        return dw.b(paramInt1 + f, paramInt2, paramInt3 + f, paramInt1 + 1 - f, paramInt2 + 1 - f, paramInt3 + 1 - f);
    }

    @Override
    public int a(int paramInt) {
        if (paramInt == 1) {
            return this.bg - 1;
        }
        if (paramInt == 0) {
            return this.bg + 1;
        }
        return this.bg;
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public boolean a(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        if (!super.a(parameq, paramInt1, paramInt2, paramInt3)) {
            return false;
        }

        return f(parameq, paramInt1, paramInt2, paramInt3);
    }

    @Override
    public void b(eq parameq, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (!f(parameq, paramInt1, paramInt2, paramInt3)) {
            a_(parameq, paramInt1, paramInt2, paramInt3, parameq.b(paramInt1, paramInt2, paramInt3));
            parameq.d(paramInt1, paramInt2, paramInt3, 0);
        }
    }

    @Override
    public boolean f(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        if (parameq.c(paramInt1 - 1, paramInt2, paramInt3).a()) {
            return false;
        }
        if (parameq.c(paramInt1 + 1, paramInt2, paramInt3).a()) {
            return false;
        }
        if (parameq.c(paramInt1, paramInt2, paramInt3 - 1).a()) {
            return false;
        }
        if (parameq.c(paramInt1, paramInt2, paramInt3 + 1).a()) {
            return false;
        }
        int i = parameq.a(paramInt1, paramInt2 - 1, paramInt3);
        return (i == gc.aV.bh) || (i == gc.E.bh);
    }

    @Override
    public void a(eq parameq, int paramInt1, int paramInt2, int paramInt3, ea paramea) {
        // hMod Damage hook: Cactus
        if (paramea instanceof ka && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.CACTUS, null, new LivingEntity((ka) paramea), 1)) {
            return;
        }
        paramea.a(null, 1);
    }
}
