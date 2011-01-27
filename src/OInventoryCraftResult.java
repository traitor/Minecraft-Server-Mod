public class OInventoryCraftResult implements OIInventory, Container<OItemStack> {

    private OItemStack[] a    = new OItemStack[1];
    private String       name = "Result";

    public int h_() {
        return 1;
    }

    public OItemStack a(int paramInt) {
        return a[paramInt];
    }

    public OItemStack[] getContents() {
        return a;
    }

    public void setContents(OItemStack[] e) {
        a = e;
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

    public String b() {
        return name;
    }

    public OItemStack b(int paramInt1, int paramInt2) {
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

    public int c() {
        return 64;
    }

    public void d() {
    }

    public boolean a_(OEntityPlayer paramOEntityPlayer) {
        return true;
    }
}
