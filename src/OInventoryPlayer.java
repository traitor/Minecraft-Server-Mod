public class OInventoryPlayer implements OIInventory, Container<OItemStack> {

    public OItemStack[]   a    = new OItemStack[36];
    public OItemStack[]   b    = new OItemStack[4];
    public int            c    = 0;
    public OEntityPlayer d;
    private OItemStack    f;
    public boolean        e    = false;
    private String        name = "Inventory";

    public OInventoryPlayer(OEntityPlayer paramOEntityPlayer) {
        d = paramOEntityPlayer;
    }

    public OItemStack[] getContents() {
        return a;
    }

    public void setContents(OItemStack[] values) {
        a = values;
    }

    public OItemStack getContentsAt(int index) {
        return c_(index);
    }

    public void setContentsAt(int index, OItemStack value) {
        a(index, value);
    }

    public int getContentsSize() {
        return q_();
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        name = value;
    }

    public OItemStack b() {
        if ((c < 9) && (c >= 0))
            return a[c];
        return null;
    }


    public static int e() {
        return 9;
    }

    
    private int d(int paramInt) {
        for (int i = 0; i < a.length; i++)
            if ((a[i] != null) && (a[i].c == paramInt))
                return i;
        return -1;
    }

    private int c(OItemStack paramOItemStack) {
        for (int i = 0; i < a.length; i++)
            if ((a[i] != null) && (a[i].c == paramOItemStack.c) && (a[i].c()) && (a[i].a < a[i].b()) && (a[i].a < r_()) && ((!a[i].e()) || (a[i].h() == paramOItemStack.h())))
                return i;
        return -1;
    }

    private int k() {
        for (int i = 0; i < a.length; i++)
            if (a[i] == null)
                return i;
        return -1;
    }

    private int d(OItemStack paramOItemStack) {
        int i = paramOItemStack.c;
        int j = paramOItemStack.a;

        int k = c(paramOItemStack);
        if (k < 0)
            k = k();
        if (k < 0)
            return j;
        if (a[k] == null)
            a[k] = new OItemStack(i, 0, paramOItemStack.h());

        int m = j;
        if (m > a[k].b() - a[k].a)
            m = a[k].b() - a[k].a;
        if (m > r_() - a[k].a)
            m = r_() - a[k].a;

        if (m == 0)
            return j;

        j -= m;
        a[k].a += m;
        a[k].b = 5;

        return j;
    }

    public void f() {
        for (int i = 0; i < a.length; i++) {
            if ((a[i] == null) || (a[i].b <= 0))
                continue;
            a[i].b -= 1;
        }
    }

    public boolean b(int paramInt) {
        int i = d(paramInt);
        if (i < 0)
            return false;
        if (--a[i].a <= 0)
            a[i] = null;

        return true;
    }

    public boolean a(OItemStack paramOItemStack) {
        if (!paramOItemStack.f()) {
            paramOItemStack.a = d(paramOItemStack);
            if (paramOItemStack.a == 0)
                return true;
        }

        int i = k();
        if (i >= 0) {
            a[i] = paramOItemStack;
            a[i].b = 5;
            return true;
        }
        return false;
    }

    public OItemStack a(int paramInt1, int paramInt2) {
        OItemStack[] arrayOfOItemStack = a;
        if (paramInt1 >= a.length) {
            arrayOfOItemStack = b;
            paramInt1 -= a.length;
        }

        if (arrayOfOItemStack[paramInt1] != null) {
            if (arrayOfOItemStack[paramInt1].a <= paramInt2) {
                OItemStack localOItemStack = arrayOfOItemStack[paramInt1];
                arrayOfOItemStack[paramInt1] = null;
                return localOItemStack;
            }
            OItemStack localOItemStack = arrayOfOItemStack[paramInt1].a(paramInt2);
            if (arrayOfOItemStack[paramInt1].a == 0)
                arrayOfOItemStack[paramInt1] = null;
            return localOItemStack;
        }

        return null;
    }

    public void a(int paramInt, OItemStack paramOItemStack) {
        OItemStack[] arrayOfOItemStack = a;
        if (paramInt >= arrayOfOItemStack.length) {
            paramInt -= arrayOfOItemStack.length;
            arrayOfOItemStack = b;
        }

        arrayOfOItemStack[paramInt] = paramOItemStack;
    }

    public float a(OBlock paramOBlock) {
        float f1 = 1.0F;
        if (a[c] != null)
            f1 *= a[c].a(paramOBlock);
        return f1;
    }

    public ONBTTagList a(ONBTTagList paramONBTTagList) {
        ONBTTagCompound localONBTTagCompound;
        for (int i = 0; i < a.length; i++)
            if (a[i] != null) {
                localONBTTagCompound = new ONBTTagCompound();
                localONBTTagCompound.a("Slot", (byte) i);
                a[i].a(localONBTTagCompound);
                paramONBTTagList.a(localONBTTagCompound);
            }
        for (int i = 0; i < b.length; i++)
            if (b[i] != null) {
                localONBTTagCompound = new ONBTTagCompound();
                localONBTTagCompound.a("Slot", (byte) (i + 100));
                b[i].a(localONBTTagCompound);
                paramONBTTagList.a(localONBTTagCompound);
            }
        return paramONBTTagList;
    }

    public void b(ONBTTagList paramONBTTagList) {
        a = new OItemStack[36];
        b = new OItemStack[4];
        for (int i = 0; i < paramONBTTagList.c(); i++) {
            ONBTTagCompound localONBTTagCompound = (ONBTTagCompound) paramONBTTagList.a(i);
            int j = localONBTTagCompound.c("Slot") & 0xFF;
            OItemStack localOItemStack = new OItemStack(localONBTTagCompound);
            if (localOItemStack.a() != null) {
                if ((j >= 0) && (j < a.length))
                    a[j] = localOItemStack;
                if ((j < 100) || (j >= b.length + 100))
                    continue;
                b[(j - 100)] = localOItemStack;
            }
        }
    }

    public int q_() {
        return a.length + 4;
    }

    public OItemStack c_(int paramInt) {
        OItemStack[] arrayOfOItemStack = a;
        if (paramInt >= arrayOfOItemStack.length) {
            paramInt -= arrayOfOItemStack.length;
            arrayOfOItemStack = b;
        }

        return arrayOfOItemStack[paramInt];
    }

    public String c() {
        return "Inventory";
    }

    public int r_() {
        return 64;
    }

    public int a(OEntity paramOEntity) {
        OItemStack localOItemStack = c_(c);
        if (localOItemStack != null)
            return localOItemStack.a(paramOEntity);
        return 1;
    }

    public boolean b(OBlock paramOBlock) {
        if ((paramOBlock.bw != OMaterial.d) && (paramOBlock.bw != OMaterial.e) && (paramOBlock.bw != OMaterial.t) && (paramOBlock.bw != OMaterial.s))
            return true;

        OItemStack localOItemStack = c_(c);
        if (localOItemStack != null)
            return localOItemStack.b(paramOBlock);
        return false;
    }

    public int g() {
        int i = 0;
        int j = 0;
        int k = 0;
        for (OItemStack element : b)
            if ((element != null) && ((element.a() instanceof OItemArmor))) {
                int n = element.i();
                int i1 = element.g();

                int i2 = n - i1;
                j += i2;
                k += n;

                int i3 = ((OItemArmor) element.a()).bj;

                i += i3;
            }
        if (k == 0)
            return 0;
        return (i - 1) * j / k + 1;
    }

    public void c(int paramInt) {
        for (int i = 0; i < b.length; i++)
            if ((b[i] != null) && ((b[i].a() instanceof OItemArmor))) {
                b[i].a(paramInt, d);
                if (b[i].a == 0) {
                    b[i].a(d);
                    b[i] = null;
                }
            }
    }

    public void h() {
        for (int i = 0; i < a.length; i++)
            if (a[i] != null) {
                d.a(a[i], true);
                a[i] = null;
            }
        for (int i = 0; i < b.length; i++)
            if (b[i] != null) {
                d.a(b[i], true);
                b[i] = null;
            }
    }

    public void i() {
        e = true;
    }

    public void b(OItemStack paramOItemStack) {
        f = paramOItemStack;
        d.a(paramOItemStack);
    }

    public OItemStack j() {
        return f;
    }

    public boolean a_(OEntityPlayer paramOEntityPlayer) {
        if (d.bb)
            return false;
        return paramOEntityPlayer.g(d) <= 64.0D;
    }
}
