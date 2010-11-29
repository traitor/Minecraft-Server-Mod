public class jt {
    private eo b;
    public fx a;
    private float c;
    private float d = 0.0F;
    private int e = 0;
    private float f = 0.0F;
    private int g;
    private int h;
    private int i;

    public jt(eo parameo) {
        this.b = parameo;
    }

    public void a(int paramInt1, int paramInt2, int paramInt3) {
        int j = this.b.a(paramInt1, paramInt2, paramInt3);
        if ((j > 0) && (this.d == 0.0F)) {
            ga.m[j].b(this.b, paramInt1, paramInt2, paramInt3, this.a);
        }
        if ((j > 0) && (ga.m[j].a(this.a) >= 1.0F)) {
            c(paramInt1, paramInt2, paramInt3);
        }
    }

    public void a() {
        this.d = 0.0F;
        this.e = 0;
    }

    public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (this.e > 0) {
            this.e -= 1;
            return;
        }
        if ((paramInt1 == this.g) && (paramInt2 == this.h) && (paramInt3 == this.i)) {
            int j = this.b.a(paramInt1, paramInt2, paramInt3);
            if (j == 0) {
                return;
            }
            ga localga = ga.m[j];

            this.d += localga.a(this.a);

            this.f += 1.0F;

            if (this.d >= 1.0F) {
                c(paramInt1, paramInt2, paramInt3);
                this.d = 0.0F;
                this.c = 0.0F;
                this.f = 0.0F;
                this.e = 5;
            }
        } else {
            this.d = 0.0F;
            this.c = 0.0F;
            this.f = 0.0F;
            this.g = paramInt1;
            this.h = paramInt2;
            this.i = paramInt3;
        }
    }

    public boolean b(int paramInt1, int paramInt2, int paramInt3) {
        ga localga = ga.m[this.b.a(paramInt1, paramInt2, paramInt3)];
        int j = this.b.b(paramInt1, paramInt2, paramInt3);
        boolean bool = this.b.d(paramInt1, paramInt2, paramInt3, 0);
        if ((localga != null) && (bool)) {
            localga.a(this.b, paramInt1, paramInt2, paramInt3, j);
        }
        return bool;
    }

    public boolean c(int paramInt1, int paramInt2, int paramInt3) {
        int j = this.b.a(paramInt1, paramInt2, paramInt3);
        int k = this.b.b(paramInt1, paramInt2, paramInt3);
        boolean bool = b(paramInt1, paramInt2, paramInt3);

        hl localhl = this.a.H();
        if (localhl != null) {
            localhl.a(j, paramInt1, paramInt2, paramInt3);
            if (localhl.a == 0) {
                localhl.a(this.a);
                this.a.I();
            }
        }
        if ((bool) && (this.a.b(ga.m[j]))) {
            ga.m[j].a_(this.b, paramInt1, paramInt2, paramInt3, k);
        }
        return bool;
    }

    public boolean a(fx paramfx, eo parameo, hl paramhl) {
        int j = paramhl.a;
        // hMod: onItemUse
        if (paramfx instanceof er && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, new Object[]{((er) paramfx).getPlayer(), new Item(paramhl)})) {
            return false;
        }
        hl localhl = paramhl.a(parameo, paramfx);
        if ((localhl != paramhl) || ((localhl != null) && (localhl.a != j))) {
            paramfx.al.a[paramfx.al.d] = localhl;
            if (localhl.a == 0) {
                paramfx.al.a[paramfx.al.d] = null;
            }
            return true;
        }
        return false;
    }

    public boolean a(fx paramfx, eo parameo, hl paramhl, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int j = parameo.a(paramInt1, paramInt2, paramInt3);
        if ((j > 0) && (ga.m[j].a(parameo, paramInt1, paramInt2, paramInt3, paramfx))) {
            return true;
        }

        if (paramhl == null) {
            return false;
        }
        return paramhl.a(paramfx, parameo, paramInt1, paramInt2, paramInt3, paramInt4);
    }
}