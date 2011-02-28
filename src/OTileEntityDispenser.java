import java.util.Random;

public class OTileEntityDispenser extends OTileEntity implements OIInventory, Container<OItemStack> {

    private OItemStack[] a    = new OItemStack[9];
    private Random       b    = new Random();
    private String       name = "Trap";

    public int m_() {
        return 9;
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
        return a[paramInt];
    }

    public OItemStack a(int paramInt1, int paramInt2) {
        if (a[paramInt1] != null) {
            if (a[paramInt1].a <= paramInt2) {
                OItemStack localOItemStack = a[paramInt1];
                a[paramInt1] = null;
                h();
                return localOItemStack;
            }
            OItemStack localOItemStack = a[paramInt1].a(paramInt2);
            if (a[paramInt1].a == 0) {
                a[paramInt1] = null;
            }
            h();
            return localOItemStack;
        }

        return null;
    }

    public OItemStack b() {
        int i = -1;
        int j = 1;
        for (int k = 0; k < a.length; k++) {
            if ((a[k] != null) && (b.nextInt(j) == 0)) {
                i = k;

                j++;
            }
        }

        if (i >= 0) {
            return a(i, 1);
        }
        return null;
    }

    public void a(int paramInt, OItemStack paramOItemStack) {
        a[paramInt] = paramOItemStack;
        if ((paramOItemStack != null) && (paramOItemStack.a > n_())) {
            paramOItemStack.a = n_();
        }
        h();
    }

    public String c() {
        return name;
    }

    public void a(ONBTTagCompound paramONBTTagCompound) {
        super.a(paramONBTTagCompound);
        ONBTTagList localONBTTagList = paramONBTTagCompound.l("Items");
        a = new OItemStack[m_()];
        for (int i = 0; i < localONBTTagList.c(); i++) {
            ONBTTagCompound localONBTTagCompound = (ONBTTagCompound) localONBTTagList.a(i);
            int j = localONBTTagCompound.c("Slot") & 0xFF;
            if ((j < 0) || (j >= a.length)) {
                continue;
            }
            a[j] = new OItemStack(localONBTTagCompound);
        }
    }

    public void b(ONBTTagCompound paramONBTTagCompound) {
        super.b(paramONBTTagCompound);
        ONBTTagList localONBTTagList = new ONBTTagList();

        for (int i = 0; i < a.length; i++) {
            if (a[i] != null) {
                ONBTTagCompound localONBTTagCompound = new ONBTTagCompound();
                localONBTTagCompound.a("Slot", (byte) i);
                a[i].a(localONBTTagCompound);
                localONBTTagList.a(localONBTTagCompound);
            }
        }
        paramONBTTagCompound.a("Items", localONBTTagList);
    }

    public int n_() {
        return 64;
    }

    public boolean a_(OEntityPlayer paramOEntityPlayer) {
        if (d.m(e, f, g) != this) {
            return false;
        }
        return paramOEntityPlayer.d(e + 0.5D, f + 0.5D, g + 0.5D) <= 64.0D;
    }
}
