public class OTileEntityFurnace extends OTileEntity implements OIInventory, Container<OItemStack> {

    private OItemStack[] h    = new OItemStack[3];
    public int           e    = 0;
    public int           f    = 0;
    public int           g    = 0;

    private String       name = "Furnace";

    public int h_() {
        return h.length;
    }

    public OItemStack[] getContents() {
        int size = getContentsSize();
        OItemStack[] result = new OItemStack[size];

        for (int i = 0; i < size; i++)
            result[i] = getContentsAt(i);
        return result;
    }

    public void setContents(OItemStack[] values) {
        int size = getContentsSize();

        for (int i = 0; i < size; i++)
            setContentsAt(i, values[i]);
    }

    public OItemStack getContentsAt(int index) {
        return a(index);
    }

    public void setContentsAt(int index, OItemStack value) {
        a(index, value);
    }

    public int getContentsSize() {
        return h_();
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        name = value;
    }

    public OItemStack a(int paramInt) {
        return h[paramInt];
    }

    public OItemStack b(int paramInt1, int paramInt2) {
        if (h[paramInt1] != null) {
            if (h[paramInt1].a <= paramInt2) {
                OItemStack localOItemStack = h[paramInt1];
                h[paramInt1] = null;
                return localOItemStack;
            }
            OItemStack localOItemStack = h[paramInt1].a(paramInt2);
            if (h[paramInt1].a == 0)
                h[paramInt1] = null;
            return localOItemStack;
        }

        return null;
    }

    public void a(int paramInt, OItemStack paramOItemStack) {
        h[paramInt] = paramOItemStack;
        if ((paramOItemStack != null) && (paramOItemStack.a > c()))
            paramOItemStack.a = c();
    }

    public String b() {
        return name;
    }

    @Override
    public void a(ONBTTagCompound paramONBTTagCompound) {
        super.a(paramONBTTagCompound);
        ONBTTagList localONBTTagList = paramONBTTagCompound.k("Items");
        h = new OItemStack[h_()];
        for (int i = 0; i < localONBTTagList.b(); i++) {
            ONBTTagCompound localONBTTagCompound = (ONBTTagCompound) localONBTTagList.a(i);
            int j = localONBTTagCompound.b("Slot");
            if ((j < 0) || (j >= h.length))
                continue;
            h[j] = new OItemStack(localONBTTagCompound);
        }

        e = paramONBTTagCompound.c("BurnTime");
        g = paramONBTTagCompound.c("CookTime");
        f = a(h[1]);
    }

    @Override
    public void b(ONBTTagCompound paramONBTTagCompound) {
        super.b(paramONBTTagCompound);
        paramONBTTagCompound.a("BurnTime", (short) e);
        paramONBTTagCompound.a("CookTime", (short) g);
        ONBTTagList localONBTTagList = new ONBTTagList();

        for (int i = 0; i < h.length; i++)
            if (h[i] != null) {
                ONBTTagCompound localONBTTagCompound = new ONBTTagCompound();
                localONBTTagCompound.a("Slot", (byte) i);
                h[i].a(localONBTTagCompound);
                localONBTTagList.a(localONBTTagCompound);
            }
        paramONBTTagCompound.a("Items", localONBTTagList);
    }

    public int c() {
        return 64;
    }

    public boolean e() {
        return e > 0;
    }

    @Override
    public void f() {
        int i = e > 0 ? 1 : 0;
        int j = 0;
        if (e > 0)
            e -= 1;

        if (!a.z) {
            if ((e == 0) && (i())) {
                f = (e = a(h[1]));
                if (e > 0) {
                    j = 1;
                    if (h[1] != null) {
                        h[1].a -= 1;
                        if (h[1].a == 0)
                            h[1] = null;
                    }
                }
            }

            if ((e()) && (i())) {
                g += 1;
                if (g == 200) {
                    g = 0;
                    h();
                    j = 1;
                }
            } else
                g = 0;

            if (i != (e > 0 ? 1 : 0)) {
                j = 1;
                OBlockFurnace.a(e > 0, a, b, c, d);
            }
        }

        if (j != 0)
            d();
    }

    private boolean i() {
        if (h[0] == null)
            return false;
        OItemStack localOItemStack = OFurnaceRecipes.a().a(h[0].a().ba);
        if (localOItemStack == null)
            return false;
        if (h[2] == null)
            return true;
        if (!h[2].a(localOItemStack))
            return false;
        if ((h[2].a < c()) && (h[2].a < h[2].b()))
            return true;
        return h[2].a < localOItemStack.b();
    }

    public void h() {
        if (!i())
            return;

        OItemStack localOItemStack = OFurnaceRecipes.a().a(h[0].a().ba);
        if (h[2] == null)
            h[2] = localOItemStack.j();
        else if (h[2].c == localOItemStack.c)
            h[2].a += 1;

        h[0].a -= 1;
        if (h[0].a <= 0)
            h[0] = null;
    }

    private int a(OItemStack paramOItemStack) {
        if (paramOItemStack == null)
            return 0;
        int i = paramOItemStack.a().ba;

        if ((i < 256) && (OBlock.m[i].bt == OMaterial.c))
            return 300;

        if (i == OItem.B.ba)
            return 100;

        if (i == OItem.k.ba)
            return 1600;

        if (i == OItem.aw.ba)
            return 20000;

        return 0;
    }

    public boolean a_(OEntityPlayer paramOEntityPlayer) {
        if (a.m(b, c, d) != this)
            return false;
        return paramOEntityPlayer.d(b + 0.5D, c + 0.5D, d + 0.5D) <= 64.0D;
    }
}
