
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class cu extends hv {

    private boolean a = false;
    private static List b = new ArrayList();

    private boolean a(ff paramff, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
        if (paramBoolean) {
            b.add(new ka(paramInt1, paramInt2, paramInt3, paramff.e));
        }
        int i = 0;
        for (int j = 0; j < b.size(); j++) {
            ka localjz = (ka) b.get(j);
            if ((localjz.a == paramInt1) && (localjz.b == paramInt2) && (localjz.c == paramInt3)) {
                i++;
                if (i >= 8) {
                    return true;
                }
            }
        }
        return false;
    }

    protected cu(int paramInt1, int paramInt2, boolean paramBoolean) {
        super(paramInt1, paramInt2);
        this.a = paramBoolean;
        a(true);
    }

    public int b() {
        return 2;
    }

    public void e(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        if (paramff.b(paramInt1, paramInt2, paramInt3) == 0) {
            super.e(paramff, paramInt1, paramInt2, paramInt3);
        }
        if (this.a) {
            paramff.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
            paramff.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
            paramff.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
            paramff.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
            paramff.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
            paramff.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);
        }
    }

    public void b(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        if (this.a) {
            paramff.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
            paramff.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
            paramff.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
            paramff.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
            paramff.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
            paramff.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);
        }
    }

    public boolean b(jx paramjw, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (!this.a) {
            return false;
        }

        int i = paramjw.b(paramInt1, paramInt2, paramInt3);

        if ((i == 5) && (paramInt4 == 1)) {
            return false;
        }
        if ((i == 3) && (paramInt4 == 3)) {
            return false;
        }
        if ((i == 4) && (paramInt4 == 2)) {
            return false;
        }
        if ((i == 1) && (paramInt4 == 5)) {
            return false;
        }
        return (i != 2) || (paramInt4 != 4);
    }

    private boolean g(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramff.b(paramInt1, paramInt2, paramInt3);

        if ((i == 5) && (paramff.j(paramInt1, paramInt2 - 1, paramInt3, 0))) {
            return true;
        }
        if ((i == 3) && (paramff.j(paramInt1, paramInt2, paramInt3 - 1, 2))) {
            return true;
        }
        if ((i == 4) && (paramff.j(paramInt1, paramInt2, paramInt3 + 1, 3))) {
            return true;
        }
        if ((i == 1) && (paramff.j(paramInt1 - 1, paramInt2, paramInt3, 4))) {
            return true;
        }
        return (i == 2) && (paramff.j(paramInt1 + 1, paramInt2, paramInt3, 5));
    }

    public void a(ff paramff, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        boolean bool = g(paramff, paramInt1, paramInt2, paramInt3);

        while ((b.size() > 0) && (paramff.e - ((ka) b.get(0)).d > 100L)) {
            b.remove(0);
        }

        if (this.a) {
            if (bool) {
                paramff.b(paramInt1, paramInt2, paramInt3, gv.aP.bh, paramff.b(paramInt1, paramInt2, paramInt3));

                // hMod: Allow redstone torches to provide power
                int current = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Object[]{new Block(this.bh, paramInt1, paramInt2, paramInt3), 1, 0});
                if (current == 0) {
                    if (a(paramff, paramInt1, paramInt2, paramInt3, true)) {
                        paramff.a(paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, "random.fizz", 0.5F, 2.6F + (paramff.l.nextFloat() - paramff.l.nextFloat()) * 0.8F);
                        for (int i = 0; i < 5; i++) {
                            double d1 = paramInt1 + paramRandom.nextDouble() * 0.6D + 0.2D;
                            double d2 = paramInt2 + paramRandom.nextDouble() * 0.6D + 0.2D;
                            double d3 = paramInt3 + paramRandom.nextDouble() * 0.6D + 0.2D;

                            paramff.a("smoke", d1, d2, d3, 0.0D, 0.0D, 0.0D);
                        }
                    }
                }
            }
        } else if ((!bool)
                && (!a(paramff, paramInt1, paramInt2, paramInt3, false))) {
            // hMod: Allow redstone torches to provide power
            int current = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Object[]{new Block(this.bh, paramInt1, paramInt2, paramInt3), 0, 1});
            if (current > 0) {
                paramff.b(paramInt1, paramInt2, paramInt3, gv.aQ.bh, paramff.b(paramInt1, paramInt2, paramInt3));
            }
        }
    }

    public void b(ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        super.b(paramff, paramInt1, paramInt2, paramInt3, paramInt4);
        paramff.h(paramInt1, paramInt2, paramInt3, this.bh);
    }

    public boolean d(ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramInt4 == 0) {
            // hMod: forced downcast!
            return b((jx) paramff, paramInt1, paramInt2, paramInt3, paramInt4);
        }
        return false;
    }

    public int a(int paramInt, Random paramRandom) {
        return gv.aQ.bh;
    }

    public boolean c() {
        return true;
    }
}
