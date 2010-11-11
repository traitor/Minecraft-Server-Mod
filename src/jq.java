
public class jq {

    private em b;
    public fv a;
    private float c;
    private float d = 0.0F;
    private int e = 0;
    private float f = 0.0F;
    private int g;
    private int h;
    private int i;

    public jq(em paramel) {
        this.b = paramel;
    }

    public void a(int paramInt1, int paramInt2, int paramInt3) {
        int j = this.b.a(paramInt1, paramInt2, paramInt3);
        if ((j > 0) && (this.d == 0.0F)) {
            fy.m[j].b(this.b, paramInt1, paramInt2, paramInt3, this.a);
        }

        if ((j > 0) && (fy.m[j].a(this.a) >= 1.0F)) {
            Block block = etc.getServer().getBlockAt(paramInt1, paramInt2, paramInt3);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_BROKEN, new Object[]{(ep) a, block})) {
                c(paramInt1, paramInt2, paramInt3);
            }
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

            fy localfy = fy.m[j];
            this.d += localfy.a(this.a);
            this.f += 1.0F;
            if (this.d >= 1.0F) {
                Block block = etc.getServer().getBlockAt(paramInt1, paramInt2, paramInt3);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_BROKEN, new Object[]{(ep) a, block})) {
                    c(paramInt1, paramInt2, paramInt3);
                }
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
        fy localfy = fy.m[this.b.a(paramInt1, paramInt2, paramInt3)];
        int j = this.b.b(paramInt1, paramInt2, paramInt3);
        boolean bool = this.b.d(paramInt1, paramInt2, paramInt3, 0);
        if ((localfy != null) && (bool)) {
            localfy.a(this.b, paramInt1, paramInt2, paramInt3, j);
        }
        return bool;
    }

    public boolean c(int paramInt1, int paramInt2, int paramInt3) {
        int j = this.b.a(paramInt1, paramInt2, paramInt3);
        int k = this.b.b(paramInt1, paramInt2, paramInt3);
        boolean bool = b(paramInt1, paramInt2, paramInt3);

        hj localhj = this.a.G();
        if (localhj != null) {
            localhj.a(j, paramInt1, paramInt2, paramInt3);
            if (localhj.a == 0) {
                localhj.a(this.a);
                this.a.H();
            }
        }
        if ((bool) && (this.a.b(fy.m[j]))) {
            fy.m[j].a_(this.b, paramInt1, paramInt2, paramInt3, k);
        }
        return bool;
    }

    public boolean a(fv paramfv, em paramem, hj paramhj) {
        int j = paramhj.a;
        hj localhj = paramhj.a(paramem, paramfv);
        if ((localhj != paramhj) || ((localhj != null) && (localhj.a != j))) {
            paramfv.ak.a[paramfv.ak.d] = localhj;
            if (localhj.a == 0) {
                paramfv.ak.a[paramfv.ak.d] = null;
            }
            return true;
        }
        return false;
    }

    public boolean a(fv paramft, em paramem, hj paramhh, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int j = paramem.a(paramInt1, paramInt2, paramInt3);

        if ((j > 0) && (fy.m[j].a(paramem, paramInt1, paramInt2, paramInt3, paramft))) {
            return true;
        }

        if (paramhh == null) {
            return false;
        }

        return paramhh.a(paramft, paramem, paramInt1, paramInt2, paramInt3, paramInt4);
    }
}
