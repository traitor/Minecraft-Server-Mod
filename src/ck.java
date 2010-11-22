
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ck extends gw {

    private boolean a = false;
    private static List b = new ArrayList();

    private boolean a(em paramem, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
        if (paramBoolean) {
            b.add(new it(paramInt1, paramInt2, paramInt3, paramem.e));
        }
        int i = 0;
        for (int j = 0; j < b.size(); j++) {
            it localit = (it) b.get(j);
            if ((localit.a == paramInt1) && (localit.b == paramInt2) && (localit.c == paramInt3)) {
                i++;
                if (i >= 8) {
                    return true;
                }
            }
        }
        return false;
    }

    protected ck(int paramInt1, int paramInt2, boolean paramBoolean) {
        super(paramInt1, paramInt2);
        this.a = paramBoolean;
        a(true);
    }

    public int b() {
        return 2;
    }

    public void e(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        if (paramem.b(paramInt1, paramInt2, paramInt3) == 0) {
            super.e(paramem, paramInt1, paramInt2, paramInt3);
        }
        if (this.a) {
            paramem.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
            paramem.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
            paramem.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
            paramem.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
            paramem.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
            paramem.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);
        }
    }

    public void b(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        if (this.a) {
            paramem.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
            paramem.g(paramInt1, paramInt2 + 1, paramInt3, this.bh);
            paramem.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
            paramem.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
            paramem.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
            paramem.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);
        }
    }

    public boolean b(iq paramiq, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (!this.a) {
            return false;
        }

        int i = paramiq.b(paramInt1, paramInt2, paramInt3);

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

    private boolean g(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramem.b(paramInt1, paramInt2, paramInt3);

        if ((i == 5) && (paramem.j(paramInt1, paramInt2 - 1, paramInt3, 0))) {
            return true;
        }
        if ((i == 3) && (paramem.j(paramInt1, paramInt2, paramInt3 - 1, 2))) {
            return true;
        }
        if ((i == 4) && (paramem.j(paramInt1, paramInt2, paramInt3 + 1, 3))) {
            return true;
        }
        if ((i == 1) && (paramem.j(paramInt1 - 1, paramInt2, paramInt3, 4))) {
            return true;
        }
        return (i == 2) && (paramem.j(paramInt1 + 1, paramInt2, paramInt3, 5));
    }

    public void a(em paramem, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        boolean bool = g(paramem, paramInt1, paramInt2, paramInt3);
        int oldType = this.bh;
        int newType = oldType;

        while ((b.size() > 0) && (paramem.e - ((it) b.get(0)).d > 100L)) {
            b.remove(0);
        }

        if (this.a) {
            if (bool) {
                paramem.b(paramInt1, paramInt2, paramInt3, fy.aP.bh, paramem.b(paramInt1, paramInt2, paramInt3));
                newType = fy.aP.bh;

                if (a(paramem, paramInt1, paramInt2, paramInt3, true)) {
                    paramem.a(paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, "random.fizz", 0.5F, 2.6F + (paramem.l.nextFloat() - paramem.l.nextFloat()) * 0.8F);
                    for (int i = 0; i < 5; i++) {
                        double d1 = paramInt1 + paramRandom.nextDouble() * 0.6D + 0.2D;
                        double d2 = paramInt2 + paramRandom.nextDouble() * 0.6D + 0.2D;
                        double d3 = paramInt3 + paramRandom.nextDouble() * 0.6D + 0.2D;

                        paramem.a("smoke", d1, d2, d3, 0.0D, 0.0D, 0.0D);
                    }
                }
            }
        } else if ((!bool)
                && (!a(paramem, paramInt1, paramInt2, paramInt3, false))) {
            paramem.b(paramInt1, paramInt2, paramInt3, fy.aQ.bh, paramem.b(paramInt1, paramInt2, paramInt3));
            newType = fy.aQ.bh;
        }

        int old = (oldType == fy.aP.bh) ? 0 : 1;
        int current = (newType == fy.aP.bh) ? 0 : 1;
        if (old != current) {
            etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Object[]{new Block(newType, paramInt1, paramInt2, paramInt3), old, current});
        }
    }

    public void b(em paramem, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        super.b(paramem, paramInt1, paramInt2, paramInt3, paramInt4);
        paramem.h(paramInt1, paramInt2, paramInt3, this.bh);
    }

    public boolean d(em paramem, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramInt4 == 0) {
            return b((iq)paramem, paramInt1, paramInt2, paramInt3, paramInt4);
        }
        return false;
    }

    public int a(int paramInt, Random paramRandom) {
        return fy.aQ.bh;
    }

    public boolean c() {
        return true;
    }
}
