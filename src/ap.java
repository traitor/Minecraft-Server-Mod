
import java.util.Random;

public class ap extends fy {

    protected ap(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, jt.n);
        a(true);
    }

    public dt d(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    public int b() {
        return 20;
    }

    public boolean a() {
        return false;
    }

    public boolean a(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        if (paramem.d(paramInt1 - 1, paramInt2, paramInt3)) {
            return true;
        }
        if (paramem.d(paramInt1 + 1, paramInt2, paramInt3)) {
            return true;
        }
        if (paramem.d(paramInt1, paramInt2, paramInt3 - 1)) {
            return true;
        }
        return paramem.d(paramInt1, paramInt2, paramInt3 + 1);
    }

    public void c(em paramem, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = paramem.b(paramInt1, paramInt2, paramInt3);

        int j = i & 0x8;
        i &= 7;

        if ((paramInt4 == 2) && (paramem.d(paramInt1, paramInt2, paramInt3 + 1))) {
            i = 4;
        }
        if ((paramInt4 == 3) && (paramem.d(paramInt1, paramInt2, paramInt3 - 1))) {
            i = 3;
        }
        if ((paramInt4 == 4) && (paramem.d(paramInt1 + 1, paramInt2, paramInt3))) {
            i = 2;
        }
        if ((paramInt4 == 5) && (paramem.d(paramInt1 - 1, paramInt2, paramInt3))) {
            i = 1;
        }

        paramem.b(paramInt1, paramInt2, paramInt3, i + j);
    }

    public void e(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        if (paramem.d(paramInt1 - 1, paramInt2, paramInt3)) {
            paramem.b(paramInt1, paramInt2, paramInt3, 1);
        } else if (paramem.d(paramInt1 + 1, paramInt2, paramInt3)) {
            paramem.b(paramInt1, paramInt2, paramInt3, 2);
        } else if (paramem.d(paramInt1, paramInt2, paramInt3 - 1)) {
            paramem.b(paramInt1, paramInt2, paramInt3, 3);
        } else if (paramem.d(paramInt1, paramInt2, paramInt3 + 1)) {
            paramem.b(paramInt1, paramInt2, paramInt3, 4);
        }
        g(paramem, paramInt1, paramInt2, paramInt3);
    }

    public void b(em paramem, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (g(paramem, paramInt1, paramInt2, paramInt3)) {
            int i = paramem.b(paramInt1, paramInt2, paramInt3) & 0x7;
            int j = 0;

            if ((!paramem.d(paramInt1 - 1, paramInt2, paramInt3)) && (i == 1)) {
                j = 1;
            }
            if ((!paramem.d(paramInt1 + 1, paramInt2, paramInt3)) && (i == 2)) {
                j = 1;
            }
            if ((!paramem.d(paramInt1, paramInt2, paramInt3 - 1)) && (i == 3)) {
                j = 1;
            }
            if ((!paramem.d(paramInt1, paramInt2, paramInt3 + 1)) && (i == 4)) {
                j = 1;
            }

            if (j != 0) {
                a_(paramem, paramInt1, paramInt2, paramInt3, paramem.b(paramInt1, paramInt2, paramInt3));
                paramem.d(paramInt1, paramInt2, paramInt3, 0);
            }
        }
    }

    private boolean g(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        if (!a(paramem, paramInt1, paramInt2, paramInt3)) {
            a_(paramem, paramInt1, paramInt2, paramInt3, paramem.b(paramInt1, paramInt2, paramInt3));
            paramem.d(paramInt1, paramInt2, paramInt3, 0);
            return false;
        }
        return true;
    }

    public void a(iq paramiq, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramiq.b(paramInt1, paramInt2, paramInt3);
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

    public void b(em paramem, int paramInt1, int paramInt2, int paramInt3, fv paramfv) {
        a(paramem, paramInt1, paramInt2, paramInt3, paramfv);
    }

    public boolean a(em paramem, int paramInt1, int paramInt2, int paramInt3, fv paramfv) {
        int i = paramem.b(paramInt1, paramInt2, paramInt3);
        int j = i & 0x7;
        int k = 8 - (i & 0x8);
        if (k == 0) {
            return true;
        }

        paramem.b(paramInt1, paramInt2, paramInt3, j + k);
        paramem.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);

        paramem.a(paramInt1 + 0.5D, paramInt2 + 0.5D, paramInt3 + 0.5D, "random.click", 0.3F, 0.6F);

        paramem.g(paramInt1, paramInt2, paramInt3, this.bh);
        if (j == 1) {
            paramem.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
        } else if (j == 2) {
            paramem.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
        } else if (j == 3) {
            paramem.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
        } else if (j == 4) {
            paramem.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);
        } else {
            paramem.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
        }

        paramem.h(paramInt1, paramInt2, paramInt3, this.bh);

        etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Object[]{new Block(this.bh, paramInt1, paramInt2, paramInt3), 0, 1});

        return true;
    }

    public void b(em paramem, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramem.b(paramInt1, paramInt2, paramInt3);
        if ((i & 0x8) > 0) {
            paramem.g(paramInt1, paramInt2, paramInt3, this.bh);
            int j = i & 0x7;
            if (j == 1) {
                paramem.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
            } else if (j == 2) {
                paramem.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
            } else if (j == 3) {
                paramem.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
            } else if (j == 4) {
                paramem.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);
            } else {
                paramem.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
            }
        }
        super.b(paramem, paramInt1, paramInt2, paramInt3);
    }

    public boolean b(iq paramiq, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        return (paramiq.b(paramInt1, paramInt2, paramInt3) & 0x8) > 0;
    }

    public boolean d(em paramem, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = paramem.b(paramInt1, paramInt2, paramInt3);
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

    public void a(em paramem, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        int i = paramem.b(paramInt1, paramInt2, paramInt3);
        if ((i & 0x8) == 0) {
            return;
        }
        paramem.b(paramInt1, paramInt2, paramInt3, i & 0x7);

        etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Object[]{new Block(this.bh, paramInt1, paramInt2, paramInt3), 1, 0});

        paramem.g(paramInt1, paramInt2, paramInt3, this.bh);
        int j = i & 0x7;
        if (j == 1) {
            paramem.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
        } else if (j == 2) {
            paramem.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
        } else if (j == 3) {
            paramem.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
        } else if (j == 4) {
            paramem.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);
        } else {
            paramem.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
        }

        paramem.a(paramInt1 + 0.5D, paramInt2 + 0.5D, paramInt3 + 0.5D, "random.click", 0.3F, 0.5F);
        paramem.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);
    }
}
