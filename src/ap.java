import java.util.Random;

public class ap extends gb {
    protected ap(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, jx.n);
        a(true);
    }

    @Override
    public dv d(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    @Override
    public int b() {
        return 20;
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public boolean a(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        if (paramep.d(paramInt1 - 1, paramInt2, paramInt3)) {
            return true;
        }
        if (paramep.d(paramInt1 + 1, paramInt2, paramInt3)) {
            return true;
        }
        if (paramep.d(paramInt1, paramInt2, paramInt3 - 1)) {
            return true;
        }
        return paramep.d(paramInt1, paramInt2, paramInt3 + 1);
    }

    @Override
    public void c(ep paramep, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = paramep.b(paramInt1, paramInt2, paramInt3);

        int j = i & 0x8;
        i &= 7;

        if ((paramInt4 == 2) && (paramep.d(paramInt1, paramInt2, paramInt3 + 1))) {
            i = 4;
        }
        if ((paramInt4 == 3) && (paramep.d(paramInt1, paramInt2, paramInt3 - 1))) {
            i = 3;
        }
        if ((paramInt4 == 4) && (paramep.d(paramInt1 + 1, paramInt2, paramInt3))) {
            i = 2;
        }
        if ((paramInt4 == 5) && (paramep.d(paramInt1 - 1, paramInt2, paramInt3))) {
            i = 1;
        }

        paramep.b(paramInt1, paramInt2, paramInt3, i + j);
    }

    @Override
    public void e(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        if (paramep.d(paramInt1 - 1, paramInt2, paramInt3)) {
            paramep.b(paramInt1, paramInt2, paramInt3, 1);
        } else if (paramep.d(paramInt1 + 1, paramInt2, paramInt3)) {
            paramep.b(paramInt1, paramInt2, paramInt3, 2);
        } else if (paramep.d(paramInt1, paramInt2, paramInt3 - 1)) {
            paramep.b(paramInt1, paramInt2, paramInt3, 3);
        } else if (paramep.d(paramInt1, paramInt2, paramInt3 + 1)) {
            paramep.b(paramInt1, paramInt2, paramInt3, 4);
        }
        g(paramep, paramInt1, paramInt2, paramInt3);
    }

    @Override
    public void b(ep paramep, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (g(paramep, paramInt1, paramInt2, paramInt3)) {
            int i = paramep.b(paramInt1, paramInt2, paramInt3) & 0x7;
            int j = 0;

            if ((!paramep.d(paramInt1 - 1, paramInt2, paramInt3)) && (i == 1)) {
                j = 1;
            }
            if ((!paramep.d(paramInt1 + 1, paramInt2, paramInt3)) && (i == 2)) {
                j = 1;
            }
            if ((!paramep.d(paramInt1, paramInt2, paramInt3 - 1)) && (i == 3)) {
                j = 1;
            }
            if ((!paramep.d(paramInt1, paramInt2, paramInt3 + 1)) && (i == 4)) {
                j = 1;
            }

            if (j != 0) {
                a_(paramep, paramInt1, paramInt2, paramInt3, paramep.b(paramInt1, paramInt2, paramInt3));
                paramep.d(paramInt1, paramInt2, paramInt3, 0);
            }
        }
    }

    private boolean g(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        if (!a(paramep, paramInt1, paramInt2, paramInt3)) {
            a_(paramep, paramInt1, paramInt2, paramInt3, paramep.b(paramInt1, paramInt2, paramInt3));
            paramep.d(paramInt1, paramInt2, paramInt3, 0);
            return false;
        }
        return true;
    }

    @Override
    public void a(iu paramiu, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramiu.b(paramInt1, paramInt2, paramInt3);
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

    @Override
    public void b(ep paramep, int paramInt1, int paramInt2, int paramInt3, fy paramfy) {
        a(paramep, paramInt1, paramInt2, paramInt3, paramfy);
    }

    @Override
    public boolean a(ep paramep, int paramInt1, int paramInt2, int paramInt3, fy paramfy) {
        if (paramep.z) {
            return true;
        }

        int i = paramep.b(paramInt1, paramInt2, paramInt3);
        int j = i & 0x7;
        int k = 8 - (i & 0x8);
        if (k == 0) {
            return true;
        }

        // hMod: Allow button to provide power
        int change = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Object[]{new Block(this.bh, paramInt1, paramInt2, paramInt3), 0, 1});
        if (change == 0) {
            return true;
        }

        paramep.b(paramInt1, paramInt2, paramInt3, j + k);
        paramep.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);

        paramep.a(paramInt1 + 0.5D, paramInt2 + 0.5D, paramInt3 + 0.5D, "random.click", 0.3F, 0.6F);

        paramep.g(paramInt1, paramInt2, paramInt3, this.bh);
        if (j == 1) {
            paramep.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
        } else if (j == 2) {
            paramep.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
        } else if (j == 3) {
            paramep.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
        } else if (j == 4) {
            paramep.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);
        } else {
            paramep.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
        }

        paramep.h(paramInt1, paramInt2, paramInt3, this.bh);

        return true;
    }

    @Override
    public void b(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramep.b(paramInt1, paramInt2, paramInt3);
        if ((i & 0x8) > 0) {
            paramep.g(paramInt1, paramInt2, paramInt3, this.bh);
            int j = i & 0x7;
            if (j == 1) {
                paramep.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
            } else if (j == 2) {
                paramep.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
            } else if (j == 3) {
                paramep.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
            } else if (j == 4) {
                paramep.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);
            } else {
                paramep.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
            }
        }
        super.b(paramep, paramInt1, paramInt2, paramInt3);
    }

    @Override
    public boolean b(iu paramiu, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        return (paramiu.b(paramInt1, paramInt2, paramInt3) & 0x8) > 0;
    }

    @Override
    public boolean d(ep paramep, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = paramep.b(paramInt1, paramInt2, paramInt3);
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

    @Override
    public boolean c() {
        return true;
    }

    @Override
    public void a(ep paramep, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        if (paramep.z) {
            return;
        }
        int i = paramep.b(paramInt1, paramInt2, paramInt3);
        if ((i & 0x8) == 0) {
            return;
        }
        // hMod: Allow button to provide power
        int change = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Object[]{new Block(this.bh, paramInt1, paramInt2, paramInt3), 1, 0});
        if (change > 0) {
            return;
        }
        paramep.b(paramInt1, paramInt2, paramInt3, i & 0x7);

        paramep.g(paramInt1, paramInt2, paramInt3, this.bh);
        int j = i & 0x7;
        if (j == 1) {
            paramep.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
        } else if (j == 2) {
            paramep.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
        } else if (j == 3) {
            paramep.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
        } else if (j == 4) {
            paramep.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);
        } else {
            paramep.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
        }

        paramep.a(paramInt1 + 0.5D, paramInt2 + 0.5D, paramInt3 + 0.5D, "random.click", 0.3F, 0.5F);
        paramep.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);
    }
}
