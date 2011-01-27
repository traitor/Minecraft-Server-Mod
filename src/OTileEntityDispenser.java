import java.util.Random;

public class OTileEntityDispenser extends OTileEntity implements OIInventory, Container<OItemStack> {

    private OItemStack[] e    = new OItemStack[9];
    private Random       f    = new Random();
    public String        name = "Trap";

    public int h_() {
        return 9;
    }

    public OItemStack a(int paramInt) {
        return e[paramInt];
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

    public OItemStack b(int paramInt1, int paramInt2) {
        if (e[paramInt1] != null) {
            if (e[paramInt1].a <= paramInt2) {
                OItemStack localOItemStack = e[paramInt1];
                e[paramInt1] = null;
                d();
                return localOItemStack;
            }
            OItemStack localOItemStack = e[paramInt1].a(paramInt2);
            if (e[paramInt1].a == 0)
                e[paramInt1] = null;
            d();
            return localOItemStack;
        }

        return null;
    }

    public OItemStack e() {
        int i = -1;
        int j = 1;
        for (int k = 0; k < e.length; k++)
            if ((e[k] != null) && (f.nextInt(j) == 0)) {
                i = k;

                j++;
            }

        if (i >= 0)
            return b(i, 1);
        return null;
    }

    public void a(int paramInt, OItemStack paramOItemStack) {
        e[paramInt] = paramOItemStack;
        if ((paramOItemStack != null) && (paramOItemStack.a > c()))
            paramOItemStack.a = c();
        d();
    }

    public String b() {
        return name;
    }

    @Override
    public void a(ONBTTagCompound paramONBTTagCompound) {
        super.a(paramONBTTagCompound);
        ONBTTagList localONBTTagList = paramONBTTagCompound.k("Items");
        e = new OItemStack[h_()];
        for (int i = 0; i < localONBTTagList.b(); i++) {
            ONBTTagCompound localONBTTagCompound = (ONBTTagCompound) localONBTTagList.a(i);
            int j = localONBTTagCompound.b("Slot") & 0xFF;
            if ((j < 0) || (j >= e.length))
                continue;
            e[j] = new OItemStack(localONBTTagCompound);
        }
    }

    @Override
    public void b(ONBTTagCompound paramONBTTagCompound) {
        super.b(paramONBTTagCompound);
        ONBTTagList localONBTTagList = new ONBTTagList();

        for (int i = 0; i < e.length; i++)
            if (e[i] != null) {
                ONBTTagCompound localONBTTagCompound = new ONBTTagCompound();
                localONBTTagCompound.a("Slot", (byte) i);
                e[i].a(localONBTTagCompound);
                localONBTTagList.a(localONBTTagCompound);
            }
        paramONBTTagCompound.a("Items", localONBTTagList);
    }

    public int c() {
        return 64;
    }

    public boolean a_(OEntityPlayer paramOEntityPlayer) {
        if (a.m(b, c, d) != this)
            return false;
        return paramOEntityPlayer.d(b + 0.5D, c + 0.5D, d + 0.5D) <= 64.0D;
    }
}
