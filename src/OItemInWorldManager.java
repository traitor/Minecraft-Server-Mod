
public class OItemInWorldManager {

    private OWorld b;
    public OEntityPlayer a;
    private float c = 0.0F;
    private int d;
    private int e = 0;
    private float f = 0.0F;
    private int g;
    private int h;
    private int i;
    private int j;
    private boolean k;
    private int l;
    private int m;
    private int n;
    private int o;

    public OItemInWorldManager(OWorld paramOWorld) {
        b = paramOWorld;
    }

    public void a() {
        // hMod: workaround for servers that can't keep up
        j = (int) System.currentTimeMillis() / 50;

        if (k) {
            int i1 = j - o;
            int i2 = b.a(l, m, n);
            if (i2 != 0) {
                OBlock localOBlock = OBlock.m[i2];
                float f1 = localOBlock.a(a) * (i1 + 1);
                if (f1 >= 1.0F) {
                    k = false;
                    d(l, m, n);
                }
            } else {
                k = false;
            }
        }
    }

    public void a(int paramInt1, int paramInt2, int paramInt3) {
        d = j;
        int i1 = b.a(paramInt1, paramInt2, paramInt3);
        if (i1 > 0) {
            OBlock.m[i1].b(b, paramInt1, paramInt2, paramInt3, a);
        }
        if ((i1 > 0) && (OBlock.m[i1].a(a) >= 1.0F)) {
            d(paramInt1, paramInt2, paramInt3);
        } else {
            g = paramInt1;
            h = paramInt2;
            i = paramInt3;
        }
    }

    public void b(int paramInt1, int paramInt2, int paramInt3) {
        if ((paramInt1 == g) && (paramInt2 == h) && (paramInt3 == i)) {
            int i1 = j - d;

            int i2 = b.a(paramInt1, paramInt2, paramInt3);
            if (i2 != 0) {
                OBlock localOBlock = OBlock.m[i2];
                float f1 = localOBlock.a(a) * (i1 + 1);
                if (f1 >= 1.0F) {
                    d(paramInt1, paramInt2, paramInt3);
                } else if (!k) {
                    k = true;
                    l = paramInt1;
                    m = paramInt2;
                    n = paramInt3;
                    o = d;
                }
            }
        }
        c = 0.0F;
        e = 0;
    }

    public boolean c(int paramInt1, int paramInt2, int paramInt3) {
        OBlock localOBlock = OBlock.m[b.a(paramInt1, paramInt2, paramInt3)];
        int i1 = b.b(paramInt1, paramInt2, paramInt3);
        boolean bool = b.e(paramInt1, paramInt2, paramInt3, 0);
        if ((localOBlock != null) && (bool)) {
            localOBlock.b(b, paramInt1, paramInt2, paramInt3, i1);
        }
        return bool;
    }

    public boolean d(int paramInt1, int paramInt2, int paramInt3) {
        int i1 = b.a(paramInt1, paramInt2, paramInt3);
        int i2 = b.b(paramInt1, paramInt2, paramInt3);
        boolean bool = c(paramInt1, paramInt2, paramInt3);

        OItemStack localOItemStack = a.z();
        if (localOItemStack != null) {
            localOItemStack.a(i1, paramInt1, paramInt2, paramInt3);
            if (localOItemStack.a == 0) {
                localOItemStack.a(a);
                a.A();
            }
        }
        if ((bool) && (a.b(OBlock.m[i1]))) {
            OBlock.m[i1].a_(b, paramInt1, paramInt2, paramInt3, i2);
            ((OEntityPlayerMP) a).a.b(new OPacket53BlockChange(paramInt1, paramInt2, paramInt3, b));
        }
        return bool;
    }

    public boolean a(OEntityPlayer paramOEntityPlayer, OWorld paramOWorld, OItemStack paramOItemStack) {
        int i1 = paramOItemStack.a;
        OItemStack localOItemStack = paramOItemStack.a(paramOWorld, paramOEntityPlayer);
        if ((localOItemStack != paramOItemStack) || ((localOItemStack != null) && (localOItemStack.a != i1))) {
            paramOEntityPlayer.i.a[paramOEntityPlayer.i.c] = localOItemStack;
            if (localOItemStack.a == 0) {
                paramOEntityPlayer.i.a[paramOEntityPlayer.i.c] = null;
            }
            return true;
        }
        return false;
    }

    public boolean a(OEntityPlayer paramOEntityPlayer, OWorld paramOWorld, OItemStack paramOItemStack, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i1 = paramOWorld.a(paramInt1, paramInt2, paramInt3);
        if ((i1 > 0)
                && (OBlock.m[i1].a(paramOWorld, paramInt1, paramInt2, paramInt3, paramOEntityPlayer))) {
            return true;
        }

        if (paramOItemStack == null) {
            return false;
        }
        return paramOItemStack.a(paramOEntityPlayer, paramOWorld, paramInt1, paramInt2, paramInt3, paramInt4);
    }
}
