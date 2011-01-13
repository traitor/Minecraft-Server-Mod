
import java.util.Random;

public class bb extends hr {

    protected bb(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, mh.n);
        a(true);
    }

    public fa d(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    public int b() {
        return 20;
    }

    public boolean a() {
        return false;
    }

    public boolean a(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        if (paramfv.d(paramInt1 - 1, paramInt2, paramInt3)) {
            return true;
        }
        if (paramfv.d(paramInt1 + 1, paramInt2, paramInt3)) {
            return true;
        }
        if (paramfv.d(paramInt1, paramInt2, paramInt3 - 1)) {
            return true;
        }
        return paramfv.d(paramInt1, paramInt2, paramInt3 + 1);
    }

    public void c(fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = paramfv.b(paramInt1, paramInt2, paramInt3);

        int j = i & 0x8;
        i &= 7;

        if ((paramInt4 == 2) && (paramfv.d(paramInt1, paramInt2, paramInt3 + 1))) {
            i = 4;
        }
        if ((paramInt4 == 3) && (paramfv.d(paramInt1, paramInt2, paramInt3 - 1))) {
            i = 3;
        }
        if ((paramInt4 == 4) && (paramfv.d(paramInt1 + 1, paramInt2, paramInt3))) {
            i = 2;
        }
        if ((paramInt4 == 5) && (paramfv.d(paramInt1 - 1, paramInt2, paramInt3))) {
            i = 1;
        }

        paramfv.c(paramInt1, paramInt2, paramInt3, i + j);
    }

    public void e(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        if (paramfv.d(paramInt1 - 1, paramInt2, paramInt3)) {
            paramfv.c(paramInt1, paramInt2, paramInt3, 1);
        } else if (paramfv.d(paramInt1 + 1, paramInt2, paramInt3)) {
            paramfv.c(paramInt1, paramInt2, paramInt3, 2);
        } else if (paramfv.d(paramInt1, paramInt2, paramInt3 - 1)) {
            paramfv.c(paramInt1, paramInt2, paramInt3, 3);
        } else if (paramfv.d(paramInt1, paramInt2, paramInt3 + 1)) {
            paramfv.c(paramInt1, paramInt2, paramInt3, 4);
        }
        g(paramfv, paramInt1, paramInt2, paramInt3);
    }

    public void b(fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (g(paramfv, paramInt1, paramInt2, paramInt3)) {
            int i = paramfv.b(paramInt1, paramInt2, paramInt3) & 0x7;
            int j = 0;

            if ((!paramfv.d(paramInt1 - 1, paramInt2, paramInt3)) && (i == 1)) {
                j = 1;
            }
            if ((!paramfv.d(paramInt1 + 1, paramInt2, paramInt3)) && (i == 2)) {
                j = 1;
            }
            if ((!paramfv.d(paramInt1, paramInt2, paramInt3 - 1)) && (i == 3)) {
                j = 1;
            }
            if ((!paramfv.d(paramInt1, paramInt2, paramInt3 + 1)) && (i == 4)) {
                j = 1;
            }

            if (j != 0) {
                a_(paramfv, paramInt1, paramInt2, paramInt3, paramfv.b(paramInt1, paramInt2, paramInt3));
                paramfv.e(paramInt1, paramInt2, paramInt3, 0);
            }
        }
    }

    private boolean g(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        if (!a(paramfv, paramInt1, paramInt2, paramInt3)) {
            a_(paramfv, paramInt1, paramInt2, paramInt3, paramfv.b(paramInt1, paramInt2, paramInt3));
            paramfv.e(paramInt1, paramInt2, paramInt3, 0);
            return false;
        }
        return true;
    }

    public void a(la paramla, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramla.b(paramInt1, paramInt2, paramInt3);
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

    public void b(fv paramfv, int paramInt1, int paramInt2, int paramInt3, hl paramhl) {
        a(paramfv, paramInt1, paramInt2, paramInt3, paramhl);
    }

    public boolean a(fv paramfv, int paramInt1, int paramInt2, int paramInt3, hl paramhl) {
        if (paramfv.z) {
            return true;
        }

        int i = paramfv.b(paramInt1, paramInt2, paramInt3);
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

        paramfv.c(paramInt1, paramInt2, paramInt3, j + k);
        paramfv.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);

        paramfv.a(paramInt1 + 0.5D, paramInt2 + 0.5D, paramInt3 + 0.5D, "random.click", 0.3F, 0.6F);

        paramfv.h(paramInt1, paramInt2, paramInt3, bi);
        if (j == 1) {
            paramfv.h(paramInt1 - 1, paramInt2, paramInt3, bi);
        } else if (j == 2) {
            paramfv.h(paramInt1 + 1, paramInt2, paramInt3, bi);
        } else if (j == 3) {
            paramfv.h(paramInt1, paramInt2, paramInt3 - 1, bi);
        } else if (j == 4) {
            paramfv.h(paramInt1, paramInt2, paramInt3 + 1, bi);
        } else {
            paramfv.h(paramInt1, paramInt2 - 1, paramInt3, bi);
        }

        paramfv.i(paramInt1, paramInt2, paramInt3, bi);

        return true;
    }

    public void b(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramfv.b(paramInt1, paramInt2, paramInt3);
        if ((i & 0x8) > 0) {
            paramfv.h(paramInt1, paramInt2, paramInt3, bi);
            int j = i & 0x7;
            if (j == 1) {
                paramfv.h(paramInt1 - 1, paramInt2, paramInt3, bi);
            } else if (j == 2) {
                paramfv.h(paramInt1 + 1, paramInt2, paramInt3, bi);
            } else if (j == 3) {
                paramfv.h(paramInt1, paramInt2, paramInt3 - 1, bi);
            } else if (j == 4) {
                paramfv.h(paramInt1, paramInt2, paramInt3 + 1, bi);
            } else {
                paramfv.h(paramInt1, paramInt2 - 1, paramInt3, bi);
            }
        }
        super.b(paramfv, paramInt1, paramInt2, paramInt3);
    }

    public boolean b(la paramla, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        return (paramla.b(paramInt1, paramInt2, paramInt3) & 0x8) > 0;
    }

    public boolean d(fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = paramfv.b(paramInt1, paramInt2, paramInt3);
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

    public void a(fv paramfv, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        if (paramfv.z) {
            return;
        }
        int i = paramfv.b(paramInt1, paramInt2, paramInt3);
        if ((i & 0x8) == 0) {
            return;
        }
        // hMod: Allow button to provide power
        int change = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Block(this.bh, paramInt1, paramInt2, paramInt3), 1, 0);
        if (change > 0) {
            return;
        }

        paramfv.c(paramInt1, paramInt2, paramInt3, i & 0x7);

        paramfv.h(paramInt1, paramInt2, paramInt3, bi);
        int j = i & 0x7;
        if (j == 1) {
            paramfv.h(paramInt1 - 1, paramInt2, paramInt3, bi);
        } else if (j == 2) {
            paramfv.h(paramInt1 + 1, paramInt2, paramInt3, bi);
        } else if (j == 3) {
            paramfv.h(paramInt1, paramInt2, paramInt3 - 1, bi);
        } else if (j == 4) {
            paramfv.h(paramInt1, paramInt2, paramInt3 + 1, bi);
        } else {
            paramfv.h(paramInt1, paramInt2 - 1, paramInt3, bi);
        }

        paramfv.a(paramInt1 + 0.5D, paramInt2 + 0.5D, paramInt3 + 0.5D, "random.click", 0.3F, 0.5F);
        paramfv.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);
    }
}
