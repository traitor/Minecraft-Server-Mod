
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class cl extends gy {

    private boolean a = false;
    private static List b = new ArrayList();

    private boolean a(eo parameo, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
        if (paramBoolean) {
            b.add(new iw(paramInt1, paramInt2, paramInt3, parameo.e));
        }
        int i = 0;
        for (int j = 0; j < b.size(); j++) {
            iw localiw = (iw) b.get(j);
            if ((localiw.a == paramInt1) && (localiw.b == paramInt2) && (localiw.c == paramInt3)) {
                i++;
                if (i >= 8) {
                    return true;
                }
            }
        }
        return false;
    }

    protected cl(int paramInt1, int paramInt2, boolean paramBoolean) {
        super(paramInt1, paramInt2);
        this.a = paramBoolean;
        a(true);
    }

    public int b() {
        return 2;
    }

    public void e(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        if (parameo.b(paramInt1, paramInt2, paramInt3) == 0) {
            super.e(parameo, paramInt1, paramInt2, paramInt3);
        }
        if (this.a) {
            parameo.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
            parameo.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
            parameo.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
            parameo.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
            parameo.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
            parameo.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);
        }
    }

    public void b(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        if (this.a) {
            parameo.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
            parameo.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
            parameo.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
            parameo.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
            parameo.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
            parameo.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);
        }
    }

    public boolean b(it paramit, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (!this.a) {
            return false;
        }

        int i = paramit.b(paramInt1, paramInt2, paramInt3);

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

    private boolean g(eo parameo, int paramInt1, int paramInt2, int paramInt3) {
        int i = parameo.b(paramInt1, paramInt2, paramInt3);

        if ((i == 5) && (parameo.j(paramInt1, paramInt2 - 1, paramInt3, 0))) {
            return true;
        }
        if ((i == 3) && (parameo.j(paramInt1, paramInt2, paramInt3 - 1, 2))) {
            return true;
        }
        if ((i == 4) && (parameo.j(paramInt1, paramInt2, paramInt3 + 1, 3))) {
            return true;
        }
        if ((i == 1) && (parameo.j(paramInt1 - 1, paramInt2, paramInt3, 4))) {
            return true;
        }
        return (i == 2) && (parameo.j(paramInt1 + 1, paramInt2, paramInt3, 5));
    }

    public void a(eo parameo, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        boolean bool = g(parameo, paramInt1, paramInt2, paramInt3);
        while ((b.size() > 0) && (parameo.e - ((iw) b.get(0)).d > 100L)) {
            b.remove(0);
        }

        if (this.a) {
            if (bool) {
                parameo.b(paramInt1, paramInt2, paramInt3, ga.aP.bh, parameo.b(paramInt1, paramInt2, paramInt3));
                // hMod: Allow redstone torches to provide power
                int current = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Object[]{new Block(this.bh, paramInt1, paramInt2, paramInt3), 1, 0});
                if (current == 0) {
                    if (a(parameo, paramInt1, paramInt2, paramInt3, true)) {
                        parameo.a(paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, "random.fizz", 0.5F, 2.6F + (parameo.l.nextFloat() - parameo.l.nextFloat()) * 0.8F);
                        for (int i = 0; i < 5; i++) {
                            double d1 = paramInt1 + paramRandom.nextDouble() * 0.6D + 0.2D;
                            double d2 = paramInt2 + paramRandom.nextDouble() * 0.6D + 0.2D;
                            double d3 = paramInt3 + paramRandom.nextDouble() * 0.6D + 0.2D;

                            parameo.a("smoke", d1, d2, d3, 0.0D, 0.0D, 0.0D);
                        }
                    }
                }
            }
        } else if ((!bool)
                && (!a(parameo, paramInt1, paramInt2, paramInt3, false))) {
            // hMod: Allow redstone torches to provide power
            int current = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Object[]{new Block(this.bh, paramInt1, paramInt2, paramInt3), 0, 1});
            if (current > 0) {
                parameo.b(paramInt1, paramInt2, paramInt3, ga.aQ.bh, parameo.b(paramInt1, paramInt2, paramInt3));
            }
        }
    }

    public void b(eo parameo, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        super.b(parameo, paramInt1, paramInt2, paramInt3, paramInt4);
        parameo.h(paramInt1, paramInt2, paramInt3, this.bh);
    }

    public boolean d(eo parameo, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramInt4 == 0) {
            return b((it) parameo, paramInt1, paramInt2, paramInt3, paramInt4);
        }
        return false;
    }

    public int a(int paramInt, Random paramRandom) {
        return ga.aQ.bh;
    }

    public boolean c() {
        return true;
    }
}
