public class OTileEntityFurnace extends OTileEntity implements OIInventory, Container<OItemStack> {

    private OItemStack[] h    = new OItemStack[3];
    public int           a    = 0;
    public int           b    = 0;
    public int           c    = 0;
    private String       name = "Furnace";

    public int m_() {
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
        return c_(index);
    }

    public void setContentsAt(int index, OItemStack value) {
        a(index, value);
    }

    public int getContentsSize() {
        return m_();
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        name = value;
    }

    public OItemStack c_(int paramInt) {
        return h[paramInt];
    }

    public OItemStack a(int paramInt1, int paramInt2) {
        if (h[paramInt1] != null) {
            if (h[paramInt1].a <= paramInt2) {
                OItemStack localOItemStack = h[paramInt1];
                h[paramInt1] = null;
                return localOItemStack;
            }
            OItemStack localOItemStack = h[paramInt1].a(paramInt2);
            if (h[paramInt1].a == 0) {
                h[paramInt1] = null;
            }
            return localOItemStack;
        }

        return null;
    }

    public void a(int paramInt, OItemStack paramOItemStack) {
        h[paramInt] = paramOItemStack;
        if ((paramOItemStack != null) && (paramOItemStack.a > n_())) {
            paramOItemStack.a = n_();
        }
    }

    public String c() {
        return name;
    }

    public void a(ONBTTagCompound paramONBTTagCompound) {
        super.a(paramONBTTagCompound);
        ONBTTagList localONBTTagList = paramONBTTagCompound.l("Items");
        h = new OItemStack[m_()];
        for (int i = 0; i < localONBTTagList.c(); i++) {
            ONBTTagCompound localONBTTagCompound = (ONBTTagCompound) localONBTTagList.a(i);
            int j = localONBTTagCompound.c("Slot");
            if ((j < 0) || (j >= h.length)) {
                continue;
            }
            h[j] = new OItemStack(localONBTTagCompound);
        }

        a = paramONBTTagCompound.d("BurnTime");
        c = paramONBTTagCompound.d("CookTime");
        b = a(h[1]);
    }

    public void b(ONBTTagCompound paramONBTTagCompound) {
        super.b(paramONBTTagCompound);
        paramONBTTagCompound.a("BurnTime", (short) a);
        paramONBTTagCompound.a("CookTime", (short) c);
        ONBTTagList localONBTTagList = new ONBTTagList();

        for (int i = 0; i < h.length; i++) {
            if (h[i] != null) {
                ONBTTagCompound localONBTTagCompound = new ONBTTagCompound();
                localONBTTagCompound.a("Slot", (byte) i);
                h[i].a(localONBTTagCompound);
                localONBTTagList.a(localONBTTagCompound);
            }
        }
        paramONBTTagCompound.a("Items", localONBTTagList);
    }

    public int n_() {
        return 64;
    }

    public boolean f() {
        return a > 0;
    }

    public void i_() {
        int i = a > 0 ? 1 : 0;
        int j = 0;
        if (a > 0) {
            a -= 1;
        }

        if (!d.t) {
            if ((a == 0) && (i())) {
                b = (this.a = a(h[1]));
                if (a > 0) {
                    j = 1;
                    if (h[1] != null) {
                        h[1].a -= 1;
                        if (h[1].a == 0) {
                            h[1] = null;
                        }
                    }
                }
            }

            if ((f()) && (i())) {
                c += 1;
                if (c == 200) {
                    c = 0;
                    g();
                    j = 1;
                }
            } else {
                c = 0;
            }

            if (i != (a > 0 ? 1 : 0)) {
                j = 1;
                OBlockFurnace.a(a > 0, d, e, f, g);
            }
        }

        if (j != 0) {
            h();
        }
    }

    private boolean i() {
        if (h[0] == null) {
            return false;
        }
        OItemStack localOItemStack = OFurnaceRecipes.a().a(h[0].a().bc);
        if (localOItemStack == null) {
            return false;
        }
        if (h[2] == null) {
            return true;
        }
        if (!h[2].a(localOItemStack)) {
            return false;
        }
        if ((h[2].a < n_()) && (h[2].a < h[2].b())) {
            return true;
        }
        return h[2].a < localOItemStack.b();
    }

    public void g() {
        if (!i()) {
            return;
        }

        OItemStack localOItemStack = OFurnaceRecipes.a().a(h[0].a().bc);
        if (h[2] == null) {
            h[2] = localOItemStack.j();
        } else if (h[2].c == localOItemStack.c) {
            h[2].a += 1;
        }

        h[0].a -= 1;
        if (h[0].a <= 0) {
            h[0] = null;
        }
    }

    private int a(OItemStack paramOItemStack) {
        if (paramOItemStack == null) {
            return 0;
        }
        int i = paramOItemStack.a().bc;

        if ((i < 256) && (OBlock.m[i].bv == OMaterial.c)) {
            return 300;
        }

        if (i == OItem.B.bc) {
            return 100;
        }

        if (i == OItem.k.bc) {
            return 1600;
        }

        if (i == OItem.aw.bc) {
            return 20000;
        }

        return 0;
    }

    public boolean a_(OEntityPlayer paramOEntityPlayer) {
        if (d.m(e, f, g) != this) {
            return false;
        }
        return paramOEntityPlayer.d(e + 0.5D, f + 0.5D, g + 0.5D) <= 64.0D;
    }
}
