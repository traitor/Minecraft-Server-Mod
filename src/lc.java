
import java.util.Random;

public class lc extends hr {

    protected lc(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, mh.n);
    }

    public fa d(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
        return null;
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
        if (paramfv.d(paramInt1, paramInt2, paramInt3 + 1)) {
            return true;
        }
        return paramfv.d(paramInt1, paramInt2 - 1, paramInt3);
    }

    public void c(fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = paramfv.b(paramInt1, paramInt2, paramInt3);

        int j = i & 0x8;
        i &= 7;

        if ((paramInt4 == 1) && (paramfv.d(paramInt1, paramInt2 - 1, paramInt3))) {
            i = 5 + paramfv.l.nextInt(2);
        }
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
        } else if (paramfv.d(paramInt1, paramInt2 - 1, paramInt3)) {
            paramfv.c(paramInt1, paramInt2, paramInt3, 5 + paramfv.l.nextInt(2));
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
            if ((!paramfv.d(paramInt1, paramInt2 - 1, paramInt3)) && (i == 5)) {
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
        int i = paramla.b(paramInt1, paramInt2, paramInt3) & 0x7;
        float f = 0.1875F;
        if (i == 1) {
            a(0.0F, 0.2F, 0.5F - f, f * 2.0F, 0.8F, 0.5F + f);
        } else if (i == 2) {
            a(1.0F - f * 2.0F, 0.2F, 0.5F - f, 1.0F, 0.8F, 0.5F + f);
        } else if (i == 3) {
            a(0.5F - f, 0.2F, 0.0F, 0.5F + f, 0.8F, f * 2.0F);
        } else if (i == 4) {
            a(0.5F - f, 0.2F, 1.0F - f * 2.0F, 0.5F + f, 0.8F, 1.0F);
        } else {
            f = 0.25F;
            a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.6F, 0.5F + f);
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

        // hMod: Allow the lever to change the current
        int old = (k != 8) ? 1 : 0;
        int current = (k == 8) ? 1 : 0;
        current = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Block(this.bh, paramInt1, paramInt2, paramInt3), old, current);

        if ((current > 0) == (k == 8)) {
            paramfv.c(paramInt1, paramInt2, paramInt3, j + k);
            paramfv.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);

            paramfv.a(paramInt1 + 0.5D, paramInt2 + 0.5D, paramInt3 + 0.5D, "random.click", 0.3F, k > 0 ? 0.6F : 0.5F);

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
        }
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
}
