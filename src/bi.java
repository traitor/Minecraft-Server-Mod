import java.util.Random;

public class bi extends w
{
    private int c;
    int[] b;

    protected bi(int paramInt1, int paramInt2)
    {
        super(paramInt1, paramInt2, la.h, false);
        this.c = paramInt2;
        a(true);
    }

    public void b(ff paramff, int paramInt1, int paramInt2, int paramInt3)
    {
        int i = 1;
        int j = i + 1;

        if (paramff.a(paramInt1 - j, paramInt2 - j, paramInt3 - j, paramInt1 + j, paramInt2 + j, paramInt3 + j))
        for (int k = -i; k <= i; k++)
            for (int m = -i; m <= i; m++)
                for (int n = -i; n <= i; n++) {
                    int i1 = paramff.a(paramInt1 + k, paramInt2 + m, paramInt3 + n);
                    if (i1 == gv.K.bh)
                        paramff.c(paramInt1 + k, paramInt2 + m, paramInt3 + n, 7);
                }
    }

    public void a(ff paramff, int paramInt1, int paramInt2, int paramInt3, Random paramRandom)
    {
        if (paramff.z) return;

        if (paramff.b(paramInt1, paramInt2, paramInt3) == 7) {
            int i = 4;
            int j = i + 1;
            int k = 32;
            int m = k * k;
            int n = k / 2;
            if (this.b == null) {
                this.b = new int[k * k * k];
            }

            if (paramff.a(paramInt1 - j, paramInt2 - j, paramInt3 - j, paramInt1 + j, paramInt2 + j, paramInt3 + j))
            {
                int i1;
                int i2;
                int i3;
                int i4;
                for (i1 = -i; i1 <= i; i1++) {
                    for (i2 = -i; i2 <= i; i2++)
                        for (i3 = -i; i3 <= i; i3++) {
                            i4 = paramff.a(paramInt1 + i1, paramInt2 + i2, paramInt3 + i3);
                            if (i4 == gv.J.bh)
                                this.b[((i1 + n) * m + (i2 + n) * k + (i3 + n))] = 0;
                            else if (i4 == gv.K.bh)
                                this.b[((i1 + n) * m + (i2 + n) * k + (i3 + n))] = -2;
                            else
                                this.b[((i1 + n) * m + (i2 + n) * k + (i3 + n))] = -1;
                        }
                }
                for (i1 = 1; i1 <= 4; i1++) {
                    for (i2 = -i; i2 <= i; i2++) {
                        for (i3 = -i; i3 <= i; i3++) {
                            for (i4 = -i; i4 <= i; i4++) {
                                if (this.b[((i2 + n) * m + (i3 + n) * k + (i4 + n))] == i1 - 1) {
                                    if (this.b[((i2 + n - 1) * m + (i3 + n) * k + (i4 + n))] == -2) {
                                        this.b[((i2 + n - 1) * m + (i3 + n) * k + (i4 + n))] = i1;
                                    }
                                    if (this.b[((i2 + n + 1) * m + (i3 + n) * k + (i4 + n))] == -2) {
                                        this.b[((i2 + n + 1) * m + (i3 + n) * k + (i4 + n))] = i1;
                                    }
                                    if (this.b[((i2 + n) * m + (i3 + n - 1) * k + (i4 + n))] == -2) {
                                        this.b[((i2 + n) * m + (i3 + n - 1) * k + (i4 + n))] = i1;
                                    }
                                    if (this.b[((i2 + n) * m + (i3 + n + 1) * k + (i4 + n))] == -2) {
                                        this.b[((i2 + n) * m + (i3 + n + 1) * k + (i4 + n))] = i1;
                                    }
                                    if (this.b[((i2 + n) * m + (i3 + n) * k + (i4 + n - 1))] == -2) {
                                        this.b[((i2 + n) * m + (i3 + n) * k + (i4 + n - 1))] = i1;
                                    }
                                    if (this.b[((i2 + n) * m + (i3 + n) * k + (i4 + n + 1))] == -2)
                                        this.b[((i2 + n) * m + (i3 + n) * k + (i4 + n + 1))] = i1;
                                }
                            }
                        }
                    }
                }
            }
            int i1 = this.b[(n * m + n * k + n)];
            if (i1 >= 0)
                paramff.b(paramInt1, paramInt2, paramInt3, 0);
            else
                g(paramff, paramInt1, paramInt2, paramInt3);
        }
    }

    private void g(ff paramff, int paramInt1, int paramInt2, int paramInt3)
    {
        //hMod: stop leaves from decaying
        Block block = new Block(paramff.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.LEAF_DECAY, block)) {
            a_(paramff, paramInt1, paramInt2, paramInt3, paramff.b(paramInt1, paramInt2, paramInt3));
            paramff.d(paramInt1, paramInt2, paramInt3, 0);
        }
    }

    public int a(Random paramRandom) {
        return paramRandom.nextInt(16) == 0 ? 1 : 0;
    }

    public int a(int paramInt, Random paramRandom) {
        return gv.y.bh;
    }

    public boolean a() {
        return !this.a;
    }
 
    public void b(ff paramff, int paramInt1, int paramInt2, int paramInt3, ep paramep)
    {
        super.b(paramff, paramInt1, paramInt2, paramInt3, paramep);
    }
}
