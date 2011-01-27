public final class OItemStack {

    public int  a = 0;
    public int  b;
    public int  c;
    private int d;

    public OItemStack(OBlock paramOBlock) {
        this(paramOBlock, 1);
    }

    public OItemStack(OBlock paramOBlock, int paramInt) {
        this(paramOBlock.bi, paramInt, 0);
    }

    public OItemStack(OBlock paramOBlock, int paramInt1, int paramInt2) {
        this(paramOBlock.bi, paramInt1, paramInt2);
    }

    public OItemStack(OItem paramOItem) {
        this(paramOItem.ba, 1, 0);
    }

    public OItemStack(OItem paramOItem, int paramInt) {
        this(paramOItem.ba, paramInt, 0);
    }

    public OItemStack(OItem paramOItem, int paramInt1, int paramInt2) {
        this(paramOItem.ba, paramInt1, paramInt2);
    }

    public OItemStack(int paramInt1, int paramInt2, int paramInt3) {
        c = paramInt1;
        a = paramInt2;
        d = paramInt3;
    }

    public OItemStack(ONBTTagCompound paramONBTTagCompound) {
        b(paramONBTTagCompound);
    }

    public OItemStack a(int paramInt) {
        a -= paramInt;
        return new OItemStack(c, paramInt, d);
    }

    public OItem a() {
        return OItem.c[c];
    }

    public boolean a(OEntityPlayer paramOEntityPlayer, OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        return a().a(this, paramOEntityPlayer, paramOWorld, paramInt1, paramInt2, paramInt3, paramInt4);
    }

    public float a(OBlock paramOBlock) {
        return a().a(this, paramOBlock);
    }

    public OItemStack a(OWorld paramOWorld, OEntityPlayer paramOEntityPlayer) {
        return a().a(this, paramOWorld, paramOEntityPlayer);
    }

    public ONBTTagCompound a(ONBTTagCompound paramONBTTagCompound) {
        paramONBTTagCompound.a("id", (short) c);
        paramONBTTagCompound.a("Count", (byte) a);
        paramONBTTagCompound.a("Damage", (short) d);
        return paramONBTTagCompound;
    }

    public void b(ONBTTagCompound paramONBTTagCompound) {
        c = paramONBTTagCompound.c("id");
        a = paramONBTTagCompound.b("Count");
        d = paramONBTTagCompound.c("Damage");
    }

    public int b() {
        return a().b();
    }

    public boolean c() {
        return (b() > 1) && ((!d()) || (!f()));
    }

    public boolean d() {
        return OItem.c[c].d() > 0;
    }

    public boolean e() {
        return OItem.c[c].c();
    }

    public boolean f() {
        return (d()) && (d > 0);
    }

    public int g() {
        return d;
    }

    public int h() {
        return d;
    }

    public int i() {
        return OItem.c[c].d();
    }

    public void b(int paramInt) {
        if (!d())
            return;

        d += paramInt;
        if (d > i()) {
            a -= 1;
            if (a < 0)
                a = 0;
            d = 0;
        }
    }

    public void a(OEntityLiving paramOEntityLiving) {
        OItem.c[c].a(this, paramOEntityLiving);
    }

    public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        OItem.c[c].a(this, paramInt1, paramInt2, paramInt3, paramInt4);
    }

    public int a(OEntity paramOEntity) {
        return OItem.c[c].a(paramOEntity);
    }

    public boolean b(OBlock paramOBlock) {
        return OItem.c[c].a(paramOBlock);
    }

    public void a(OEntityPlayer paramOEntityPlayer) {
    }

    public void b(OEntityLiving paramOEntityLiving) {
        OItem.c[c].b(this, paramOEntityLiving);
    }

    public OItemStack j() {
        return new OItemStack(c, a, d);
    }

    public static boolean a(OItemStack paramOItemStack1, OItemStack paramOItemStack2) {
        if ((paramOItemStack1 == null) && (paramOItemStack2 == null))
            return true;
        if ((paramOItemStack1 == null) || (paramOItemStack2 == null))
            return false;
        return paramOItemStack1.c(paramOItemStack2);
    }

    private boolean c(OItemStack paramOItemStack) {
        if (a != paramOItemStack.a)
            return false;
        if (c != paramOItemStack.c)
            return false;
        return d == paramOItemStack.d;
    }

    public boolean a(OItemStack paramOItemStack) {
        return (c == paramOItemStack.c) && (d == paramOItemStack.d);
    }

    public static OItemStack b(OItemStack paramOItemStack) {
        return paramOItemStack == null ? null : paramOItemStack.j();
    }

    @Override
    public String toString() {
        return a + "OBlockIce" + OItem.c[c].a() + "@" + d;
    }
}
