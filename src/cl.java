import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class cl extends gz {
    private boolean a = false;

    private static List b = new ArrayList();

    private boolean a(ep paramep, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
        if (paramBoolean) {
            b.add(new ix(paramInt1, paramInt2, paramInt3, paramep.e));
        }
        int i = 0;
        for (int j = 0; j < b.size(); j++) {
            ix localix = (ix) b.get(j);
            if ((localix.a == paramInt1) && (localix.b == paramInt2) && (localix.c == paramInt3)) {
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
    public void e(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        if (paramep.b(paramInt1, paramInt2, paramInt3) == 0) {
            super.e(paramep, paramInt1, paramInt2, paramInt3);
        }
        if (this.a) {
            paramep.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
            paramep.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
            paramep.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
            paramep.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
            paramep.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
            paramep.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);
        }
    }

    @Override
    public void b(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        if (this.a) {
            paramep.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
            paramep.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
            paramep.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
            paramep.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
            paramep.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
            paramep.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);
        }
    }

    @Override
    public boolean b(iu paramiu, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (!this.a) {
            return false;
        }

        int i = paramiu.b(paramInt1, paramInt2, paramInt3);

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

    private boolean g(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramep.b(paramInt1, paramInt2, paramInt3);

        if ((i == 5) && (paramep.j(paramInt1, paramInt2 - 1, paramInt3, 0))) {
            return true;
        }
        if ((i == 3) && (paramep.j(paramInt1, paramInt2, paramInt3 - 1, 2))) {
            return true;
        }
        if ((i == 4) && (paramep.j(paramInt1, paramInt2, paramInt3 + 1, 3))) {
            return true;
        }
        if ((i == 1) && (paramep.j(paramInt1 - 1, paramInt2, paramInt3, 4))) {
            return true;
        }
        return (i == 2) && (paramep.j(paramInt1 + 1, paramInt2, paramInt3, 5));
    }

    @Override
    public void a(ep paramep, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        boolean bool = g(paramep, paramInt1, paramInt2, paramInt3);

        while ((b.size() > 0) && (paramep.e - ((ix) b.get(0)).d > 100L)) {
            b.remove(0);
        }

        if (this.a) {
            if (bool) {
                paramep.b(paramInt1, paramInt2, paramInt3, gb.aP.bh, paramep.b(paramInt1, paramInt2, paramInt3));

                // hMod: Allow redstone torches to provide power
                int current = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Object[]{new Block(this.bh, paramInt1, paramInt2, paramInt3), 1, 0});
                if (current == 0) {
                    if (a(paramep, paramInt1, paramInt2, paramInt3, true)) {
                        paramep.a(paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, "random.fizz", 0.5F, 2.6F + (paramep.l.nextFloat() - paramep.l.nextFloat()) * 0.8F);
                        for (int i = 0; i < 5; i++) {
                            double d1 = paramInt1 + paramRandom.nextDouble() * 0.6D + 0.2D;
                            double d2 = paramInt2 + paramRandom.nextDouble() * 0.6D + 0.2D;
                            double d3 = paramInt3 + paramRandom.nextDouble() * 0.6D + 0.2D;

                            paramep.a("smoke", d1, d2, d3, 0.0D, 0.0D, 0.0D);
                        }
                    }
                }
            }
        } else if ((!bool) && (!a(paramep, paramInt1, paramInt2, paramInt3, false))) {
            // hMod: Allow redstone torches to provide power
            int current = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Object[]{new Block(this.bh, paramInt1, paramInt2, paramInt3), 0, 1});
            if (current > 0) {
                paramep.b(paramInt1, paramInt2, paramInt3, gb.aQ.bh, paramep.b(paramInt1, paramInt2, paramInt3));
            }
        }
    }

    @Override
    public void b(ep paramep, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        super.b(paramep, paramInt1, paramInt2, paramInt3, paramInt4);
        paramep.h(paramInt1, paramInt2, paramInt3, this.bh);
    }

    @Override
    public boolean d(ep paramep, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramInt4 == 0) {
            // hMod: forced downcast!
            return b((iu)paramep, paramInt1, paramInt2, paramInt3, paramInt4);
        }
        return false;
    }

    @Override
    public int a(int paramInt, Random paramRandom) {
        return gb.aQ.bh;
    }

    @Override
    public boolean c() {
        return true;
    }
}
