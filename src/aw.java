
import java.util.Random;

public class aw extends gu {

    protected aw(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, kz.n);
        a(true);
    }

    public el d(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    public int b() {
        return 20;
    }

    public boolean a() {
        return false;
    }

    public boolean a(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        if (paramff.d(paramInt1 - 1, paramInt2, paramInt3)) {
            return true;
        }
        if (paramff.d(paramInt1 + 1, paramInt2, paramInt3)) {
            return true;
        }
        if (paramff.d(paramInt1, paramInt2, paramInt3 - 1)) {
            return true;
        }
        return paramff.d(paramInt1, paramInt2, paramInt3 + 1);
    }

    public void c(ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = paramff.b(paramInt1, paramInt2, paramInt3);

        int j = i & 0x8;
        i &= 7;

        if ((paramInt4 == 2) && (paramff.d(paramInt1, paramInt2, paramInt3 + 1))) {
            i = 4;
        }
        if ((paramInt4 == 3) && (paramff.d(paramInt1, paramInt2, paramInt3 - 1))) {
            i = 3;
        }
        if ((paramInt4 == 4) && (paramff.d(paramInt1 + 1, paramInt2, paramInt3))) {
            i = 2;
        }
        if ((paramInt4 == 5) && (paramff.d(paramInt1 - 1, paramInt2, paramInt3))) {
            i = 1;
        }

        paramff.b(paramInt1, paramInt2, paramInt3, i + j);
    }

    public void e(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        if (paramff.d(paramInt1 - 1, paramInt2, paramInt3)) {
            paramff.b(paramInt1, paramInt2, paramInt3, 1);
        } else if (paramff.d(paramInt1 + 1, paramInt2, paramInt3)) {
            paramff.b(paramInt1, paramInt2, paramInt3, 2);
        } else if (paramff.d(paramInt1, paramInt2, paramInt3 - 1)) {
            paramff.b(paramInt1, paramInt2, paramInt3, 3);
        } else if (paramff.d(paramInt1, paramInt2, paramInt3 + 1)) {
            paramff.b(paramInt1, paramInt2, paramInt3, 4);
        }
        g(paramff, paramInt1, paramInt2, paramInt3);
    }

    public void b(ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (g(paramff, paramInt1, paramInt2, paramInt3)) {
            int i = paramff.b(paramInt1, paramInt2, paramInt3) & 0x7;
            int j = 0;

            if ((!paramff.d(paramInt1 - 1, paramInt2, paramInt3)) && (i == 1)) {
                j = 1;
            }
            if ((!paramff.d(paramInt1 + 1, paramInt2, paramInt3)) && (i == 2)) {
                j = 1;
            }
            if ((!paramff.d(paramInt1, paramInt2, paramInt3 - 1)) && (i == 3)) {
                j = 1;
            }
            if ((!paramff.d(paramInt1, paramInt2, paramInt3 + 1)) && (i == 4)) {
                j = 1;
            }

            if (j != 0) {
                a_(paramff, paramInt1, paramInt2, paramInt3, paramff.b(paramInt1, paramInt2, paramInt3));
                paramff.d(paramInt1, paramInt2, paramInt3, 0);
            }
        }
    }

    private boolean g(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        if (!a(paramff, paramInt1, paramInt2, paramInt3)) {
            a_(paramff, paramInt1, paramInt2, paramInt3, paramff.b(paramInt1, paramInt2, paramInt3));
            paramff.d(paramInt1, paramInt2, paramInt3, 0);
            return false;
        }
        return true;
    }

    public void a(jw paramjw, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramjw.b(paramInt1, paramInt2, paramInt3);
        int j = i & 0x7;
        int k = (i & 0x8) > 0 ? 1 : 0;

        float f1 = 0.375F;
        float f2 = 0.625F;
        float f3 = 0.1875F;
        float f4 = 0.125F;
        if (k != 0) {
            f4 = 0.0625F;
        }

        if (j == 1) {
            a(0.0F, f1, 0.5F - f3, f4, f2, 0.5F + f3);
        } else if (j == 2) {
            a(1.0F - f4, f1, 0.5F - f3, 1.0F, f2, 0.5F + f3);
        } else if (j == 3) {
            a(0.5F - f3, f1, 0.0F, 0.5F + f3, f2, f4);
        } else if (j == 4) {
            a(0.5F - f3, f1, 1.0F - f4, 0.5F + f3, f2, 1.0F);
        }
    }

    public void b(ff paramff, int paramInt1, int paramInt2, int paramInt3, gp paramgp) {
        a(paramff, paramInt1, paramInt2, paramInt3, paramgp);
    }

    public boolean a(ff paramff, int paramInt1, int paramInt2, int paramInt3, gp paramgp) {
        if (paramff.z) {
            return true;
        }

        int i = paramff.b(paramInt1, paramInt2, paramInt3);
        int j = i & 0x7;
        int k = 8 - (i & 0x8);
        if (k == 0) {
            return true;
        }

        // hMod: Allow button to provide power
        int change = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Block(this.bh, paramInt1, paramInt2, paramInt3), 0, 1);
        if (change == 0) {
            return true;
        }

        paramff.b(paramInt1, paramInt2, paramInt3, j + k);
        paramff.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);

        paramff.a(paramInt1 + 0.5D, paramInt2 + 0.5D, paramInt3 + 0.5D, "random.click", 0.3F, 0.6F);

        paramff.g(paramInt1, paramInt2, paramInt3, this.bh);
        if (j == 1) {
            paramff.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
        } else if (j == 2) {
            paramff.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
        } else if (j == 3) {
            paramff.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
        } else if (j == 4) {
            paramff.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);
        } else {
            paramff.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
        }

        paramff.h(paramInt1, paramInt2, paramInt3, this.bh);

        return true;
    }

    public void b(ff paramff, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramff.b(paramInt1, paramInt2, paramInt3);
        if ((i & 0x8) > 0) {
            paramff.g(paramInt1, paramInt2, paramInt3, this.bh);
            int j = i & 0x7;
            if (j == 1) {
                paramff.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
            } else if (j == 2) {
                paramff.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
            } else if (j == 3) {
                paramff.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
            } else if (j == 4) {
                paramff.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);
            } else {
                paramff.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
            }
        }
        super.b(paramff, paramInt1, paramInt2, paramInt3);
    }

    public boolean b(jw paramjw, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        return (paramjw.b(paramInt1, paramInt2, paramInt3) & 0x8) > 0;
    }

    public boolean d(ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = paramff.b(paramInt1, paramInt2, paramInt3);
        if ((i & 0x8) == 0) {
            return false;
        }
        int j = i & 0x7;

        if ((j == 5) && (paramInt4 == 1)) {
            return true;
        }
        if ((j == 4) && (paramInt4 == 2)) {
            return true;
        }
        if ((j == 3) && (paramInt4 == 3)) {
            return true;
        }
        if ((j == 2) && (paramInt4 == 4)) {
            return true;
        }
        return (j == 1) && (paramInt4 == 5);
    }

    public boolean c() {
        return true;
    }

    public void a(ff paramff, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        if (paramff.z) {
            return;
        }
        int i = paramff.b(paramInt1, paramInt2, paramInt3);
        if ((i & 0x8) == 0) {
            return;
        }
        
        // hMod: Allow button to provide power
        int change = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Block(this.bh, paramInt1, paramInt2, paramInt3), 1, 0);
        if (change > 0) {
            return;
        }

        paramff.b(paramInt1, paramInt2, paramInt3, i & 0x7);

        paramff.g(paramInt1, paramInt2, paramInt3, this.bh);
        int j = i & 0x7;
        if (j == 1) {
            paramff.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
        } else if (j == 2) {
            paramff.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
        } else if (j == 3) {
            paramff.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
        } else if (j == 4) {
            paramff.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);
        } else {
            paramff.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
        }

        paramff.a(paramInt1 + 0.5D, paramInt2 + 0.5D, paramInt3 + 0.5D, "random.click", 0.3F, 0.5F);
        paramff.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);
    }
}
