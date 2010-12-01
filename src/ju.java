public class ju {

    private ep b;
    public fy a;
    private float c;
    private float d = 0.0F;
    private int e = 0;
    private float f = 0.0F;
    private int g;
    private int h;
    private int i;

    public ju(ep paramep) {
        b = paramep;
    }

    public void a(int paramInt1, int paramInt2, int paramInt3) {
        int j = b.a(paramInt1, paramInt2, paramInt3);
        if ((j > 0) && (d == 0.0F)) {
            gb.m[j].b(b, paramInt1, paramInt2, paramInt3, a);
        }
        if ((j > 0) && (gb.m[j].a(a) >= 1.0F)) {
            c(paramInt1, paramInt2, paramInt3);
        }
    }

    public void a() {
        d = 0.0F;
        e = 0;
    }

    public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (e > 0) {
            e -= 1;
            return;
        }
        if ((paramInt1 == g) && (paramInt2 == h) && (paramInt3 == i)) {
            int j = b.a(paramInt1, paramInt2, paramInt3);
            if (j == 0) {
                return;
            }
            gb localgb = gb.m[j];

            d += localgb.a(a);

            f += 1.0F;

            if (d >= 1.0F) {
                c(paramInt1, paramInt2, paramInt3);
                d = 0.0F;
                c = 0.0F;
                f = 0.0F;
                e = 5;
            }
        } else {
            d = 0.0F;
            c = 0.0F;
            f = 0.0F;
            g = paramInt1;
            h = paramInt2;
            i = paramInt3;
        }
    }

    public boolean b(int paramInt1, int paramInt2, int paramInt3) {
        gb localgb = gb.m[b.a(paramInt1, paramInt2, paramInt3)];
        int j = b.b(paramInt1, paramInt2, paramInt3);
        boolean bool = b.d(paramInt1, paramInt2, paramInt3, 0);
        if ((localgb != null) && (bool)) {
            localgb.a(b, paramInt1, paramInt2, paramInt3, j);
        }
        return bool;
    }

    public boolean c(int paramInt1, int paramInt2, int paramInt3) {
        int j = b.a(paramInt1, paramInt2, paramInt3);
        int k = b.b(paramInt1, paramInt2, paramInt3);
        boolean bool = b(paramInt1, paramInt2, paramInt3);

        hm localhm = a.H();
        if (localhm != null) {
            localhm.a(j, paramInt1, paramInt2, paramInt3);
            if (localhm.a == 0) {
                localhm.a(a);
                a.I();
            }
        }
        if ((bool) && (a.b(gb.m[j]))) {
            gb.m[j].g(b, paramInt1, paramInt2, paramInt3, k);
        }
        return bool;
    }

    public boolean a(fy paramfy, ep paramep, hm paramhm) {
        int j = paramhm.a;
        // hMod: onItemUse
        if (paramfy instanceof es && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, new Object[]{((es) paramfy).getPlayer(), new Item(paramhm)})) {
            return false;
        }
        hm localhm = paramhm.a(paramep, paramfy);
        if ((localhm != paramhm) || ((localhm != null) && (localhm.a != j))) {
            paramfy.am.a[paramfy.am.d] = localhm;
            if (localhm.a == 0) {
                paramfy.am.a[paramfy.am.d] = null;
            }
            return true;
        }
        return false;
    }

    public boolean a(fy paramfy, ep paramep, hm paramhm, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int j = paramep.a(paramInt1, paramInt2, paramInt3);
        if ((j > 0) && (gb.m[j].a(paramep, paramInt1, paramInt2, paramInt3, paramfy))) {
            return true;
        }

        if (paramhm == null) {
            return false;
        }
        return paramhm.a(paramfy, paramep, paramInt1, paramInt2, paramInt3, paramInt4);
    }
}
