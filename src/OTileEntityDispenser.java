import java.util.Random;

public class OTileEntityDispenser extends OTileEntity implements OIInventory {
    private OItemStack[] a = new OItemStack[9];
    private Random       b = new Random();

    public int q_() {
        return 9;
    }

    public OItemStack c_(int paramInt) {
        return a[paramInt];
    }

    public OItemStack a(int paramInt1, int paramInt2) {
        if (a[paramInt1] != null) {
            if (a[paramInt1].a <= paramInt2) {
                localOItemStack = a[paramInt1];
                a[paramInt1] = null;
                i();
                return localOItemStack;
            }
            OItemStack localOItemStack = a[paramInt1].a(paramInt2);
            if (a[paramInt1].a == 0)
                a[paramInt1] = null;
            i();
            return localOItemStack;
        }

        return null;
    }

    public OItemStack b() {
        int i = -1;
        int j = 1;
        for (int k = 0; k < a.length; k++)
            if ((a[k] != null) && (b.nextInt(j) == 0)) {
                i = k;

                j++;
            }

        if (i >= 0)
            return a(i, 1);
        return null;
    }

    public void a(int paramInt, OItemStack paramOItemStack) {
        a[paramInt] = paramOItemStack;
        if ((paramOItemStack != null) && (paramOItemStack.a > r_()))
            paramOItemStack.a = r_();
        i();
    }

    public String c() {
        return "Trap";
    }

    @Override
    public void a(ONBTTagCompound paramONBTTagCompound) {
        super.a(paramONBTTagCompound);
        ONBTTagList localONBTTagList = paramONBTTagCompound.l("Items");
        a = new OItemStack[q_()];
        for (int i = 0; i < localONBTTagList.c(); i++) {
            ONBTTagCompound localONBTTagCompound = (ONBTTagCompound) localONBTTagList.a(i);
            int j = localONBTTagCompound.c("Slot") & 0xFF;
            if ((j < 0) || (j >= a.length))
                continue;
            a[j] = new OItemStack(localONBTTagCompound);
        }
    }

    @Override
    public void b(ONBTTagCompound paramONBTTagCompound) {
        super.b(paramONBTTagCompound);
        ONBTTagList localONBTTagList = new ONBTTagList();

        for (int i = 0; i < a.length; i++)
            if (a[i] != null) {
                ONBTTagCompound localONBTTagCompound = new ONBTTagCompound();
                localONBTTagCompound.a("Slot", (byte) i);
                a[i].a(localONBTTagCompound);
                localONBTTagList.a(localONBTTagCompound);
            }
        paramONBTTagCompound.a("Items", localONBTTagList);
    }

    public int r_() {
        return 64;
    }

    public boolean a_(OEntityPlayer paramOEntityPlayer) {
        if (d.m(e, f, g) != this)
            return false;
        return paramOEntityPlayer.d(e + 0.5D, f + 0.5D, g + 0.5D) <= 64.0D;
    }
}