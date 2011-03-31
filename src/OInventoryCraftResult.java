public class OInventoryCraftResult implements OIInventory {
    private OItemStack[] a = new OItemStack[1];

    public int q_() {
        return 1;
    }

    public OItemStack c_(int paramInt) {
        return a[paramInt];
    }

    public String c() {
        return "Result";
    }

    public OItemStack a(int paramInt1, int paramInt2) {
        if (a[paramInt1] != null) {
            OItemStack localOItemStack = a[paramInt1];
            a[paramInt1] = null;
            return localOItemStack;
        }
        return null;
    }

    public void a(int paramInt, OItemStack paramOItemStack) {
        a[paramInt] = paramOItemStack;
    }

    public int r_() {
        return 64;
    }

    public void i() {
    }

    public boolean a_(OEntityPlayer paramOEntityPlayer) {
        return true;
    }
}