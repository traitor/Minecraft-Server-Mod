public class jo {
    private el b;
    public ft a;
    private float c;
    private float d = 0.0F;
    private int e = 0;
    private float f = 0.0F;
    private int g;
    private int h;
    private int i;

    public jo(el paramel) {
        this.b = paramel;
    }
    
    public void a(int paramInt1, int paramInt2, int paramInt3) {
        int j = this.b.a(paramInt1, paramInt2, paramInt3);
        if ((j > 0) && (this.d == 0.0F)) {
            fw.n[j].b(this.b, paramInt1, paramInt2, paramInt3, this.a);
        }
        if ((j > 0) && (fw.n[j].a(this.a) >= 1.0F)) {
            Block block = etc.getServer().getBlockAt(paramInt1, paramInt2, paramInt3);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_BROKEN, new Object[]{(eo) a, block})) {
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
            fw localfw = fw.n[j];
            this.d += localfw.a(this.a);
            this.f += 1.0F;
            if (this.d >= 1.0F) {
                Block block = etc.getServer().getBlockAt(paramInt1, paramInt2, paramInt3);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_BROKEN, new Object[]{(eo) a, block})) {
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
        fw localfw = fw.n[this.b.a(paramInt1, paramInt2, paramInt3)];
        int j = this.b.b(paramInt1, paramInt2, paramInt3);
        boolean bool = this.b.d(paramInt1, paramInt2, paramInt3, 0);
        if ((localfw != null) && (bool)) {
            localfw.a(this.b, paramInt1, paramInt2, paramInt3, j);
        }
        return bool;
    }
        
    public boolean c(int paramInt1, int paramInt2, int paramInt3) {
        int j = this.b.a(paramInt1, paramInt2, paramInt3);
        int k = this.b.b(paramInt1, paramInt2, paramInt3);
        boolean bool = b(paramInt1, paramInt2, paramInt3);
            
        hh localhh = this.a.G();
        if (localhh != null) {
            localhh.a(j, paramInt1, paramInt2, paramInt3);
            if (localhh.a == 0) {
                localhh.a(this.a);
                this.a.H();
            }
        }
        if ((bool) && (this.a.b(fw.n[j]))) {
            fw.n[j].a_(this.b, paramInt1, paramInt2, paramInt3, k);
        }
        return bool;
    }
        
    public boolean a(ft paramft, el paramel, hh paramhh, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int j = paramel.a(paramInt1, paramInt2, paramInt3);
        if ((j > 0) && (fw.n[j].a(paramel, paramInt1, paramInt2, paramInt3, paramft))) {
            return true;
        }
        if (paramhh == null) {
            return false;
        }
        return paramhh.a(paramft, paramel, paramInt1, paramInt2, paramInt3, paramInt4);
    }
}