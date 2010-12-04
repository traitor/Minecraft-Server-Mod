import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class cl extends ha {
    private boolean a = false;

    private static List b = new ArrayList();

    private boolean a(eq parameq, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
        if (paramBoolean) {
            b.add(new iy(paramInt1, paramInt2, paramInt3, parameq.e));
        }
        int i = 0;
        for (int j = 0; j < b.size(); j++) {
            iy localiy = (iy) b.get(j);
            if ((localiy.a == paramInt1) && (localiy.b == paramInt2) && (localiy.c == paramInt3)) {
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

    @Override
    public int b() {
        return 2;
    }

    @Override
    public void e(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        if (parameq.b(paramInt1, paramInt2, paramInt3) == 0) {
            super.e(parameq, paramInt1, paramInt2, paramInt3);
        }
        if (this.a) {
            parameq.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
            parameq.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
            parameq.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
            parameq.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
            parameq.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
            parameq.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);
        }
    }

    @Override
    public void b(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        if (this.a) {
            parameq.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
            parameq.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
            parameq.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
            parameq.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
            parameq.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
            parameq.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);
        }
    }

    @Override
    public boolean b(iv paramiv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (!this.a) {
            return false;
        }

        int i = paramiv.b(paramInt1, paramInt2, paramInt3);

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

    private boolean g(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        int i = parameq.b(paramInt1, paramInt2, paramInt3);

        if ((i == 5) && (parameq.j(paramInt1, paramInt2 - 1, paramInt3, 0))) {
            return true;
        }
        if ((i == 3) && (parameq.j(paramInt1, paramInt2, paramInt3 - 1, 2))) {
            return true;
        }
        if ((i == 4) && (parameq.j(paramInt1, paramInt2, paramInt3 + 1, 3))) {
            return true;
        }
        if ((i == 1) && (parameq.j(paramInt1 - 1, paramInt2, paramInt3, 4))) {
            return true;
        }
        return (i == 2) && (parameq.j(paramInt1 + 1, paramInt2, paramInt3, 5));
    }

    @Override
    public void a(eq parameq, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        boolean bool = g(parameq, paramInt1, paramInt2, paramInt3);

        while ((b.size() > 0) && (parameq.e - ((iy) b.get(0)).d > 100L)) {
            b.remove(0);
        }

        if (this.a) {
            if (bool) {
                parameq.b(paramInt1, paramInt2, paramInt3, gc.aP.bh, parameq.b(paramInt1, paramInt2, paramInt3));

                // hMod: Allow redstone torches to provide power
                int current = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Object[]{new Block(this.bh, paramInt1, paramInt2, paramInt3), 1, 0});
                if (current == 0) {
                    if (a(parameq, paramInt1, paramInt2, paramInt3, true)) {
                        parameq.a(paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, "random.fizz", 0.5F, 2.6F + (parameq.l.nextFloat() - parameq.l.nextFloat()) * 0.8F);
                        for (int i = 0; i < 5; i++) {
                            double d1 = paramInt1 + paramRandom.nextDouble() * 0.6D + 0.2D;
                            double d2 = paramInt2 + paramRandom.nextDouble() * 0.6D + 0.2D;
                            double d3 = paramInt3 + paramRandom.nextDouble() * 0.6D + 0.2D;

                            parameq.a("smoke", d1, d2, d3, 0.0D, 0.0D, 0.0D);
                        }
                    }
                }
            }
        } else if ((!bool) && (!a(parameq, paramInt1, paramInt2, paramInt3, false))) {
            // hMod: Allow redstone torches to provide power
            int current = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Object[]{new Block(this.bh, paramInt1, paramInt2, paramInt3), 0, 1});
            if (current > 0) {
                parameq.b(paramInt1, paramInt2, paramInt3, gc.aQ.bh, parameq.b(paramInt1, paramInt2, paramInt3));
            }
        }
    }

    @Override
    public void b(eq parameq, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        super.b(parameq, paramInt1, paramInt2, paramInt3, paramInt4);
        parameq.h(paramInt1, paramInt2, paramInt3, this.bh);
    }

    @Override
    public boolean d(eq parameq, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramInt4 == 0) {
            // hMod: forced downcast!
            return b((iv)parameq, paramInt1, paramInt2, paramInt3, paramInt4);
        }
        return false;
    }

    @Override
    public int a(int paramInt, Random paramRandom) {
        return gc.aQ.bh;
    }

    @Override
    public boolean c() {
        return true;
    }
}
