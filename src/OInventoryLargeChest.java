public class OInventoryLargeChest implements OIInventory, Container<OItemStack> {

    private String      a;
    private OIInventory b;
    private OIInventory c;

    public OInventoryLargeChest(String paramString, OIInventory paramOIInventory1, OIInventory paramOIInventory2) {
        a = paramString;
        b = paramOIInventory1;
        c = paramOIInventory2;
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

    public Block getChestBlock() {
        if (b instanceof OTileEntityChest) {
            OTileEntityChest block = (OTileEntityChest) b;
            return etc.getServer().getBlockAt(block.b, block.c, block.d);
        }
        if (c instanceof OTileEntityChest) {
            OTileEntityChest block = (OTileEntityChest) c;
            return etc.getServer().getBlockAt(block.b, block.c, block.d);
        }
        return null;
    }

    public String getName() {
        return a;
    }

    public void setName(String value) {
        a = value;
    }

    public int h_() {
        return b.h_() + c.h_();
    }

    public String b() {
        return a;
    }

    public OItemStack a(int paramInt) {
        if (paramInt >= b.h_())
            return c.a(paramInt - b.h_());
        return b.a(paramInt);
    }

    public OItemStack b(int paramInt1, int paramInt2) {
        if (paramInt1 >= b.h_())
            return c.b(paramInt1 - b.h_(), paramInt2);
        return b.b(paramInt1, paramInt2);
    }

    public void a(int paramInt, OItemStack paramOItemStack) {
        if (paramInt >= b.h_())
            c.a(paramInt - b.h_(), paramOItemStack);
        else
            b.a(paramInt, paramOItemStack);
    }

    public int c() {
        return b.c();
    }

    public void d() {
        b.d();
        c.d();
    }

    public boolean a_(OEntityPlayer paramOEntityPlayer) {
        return (b.a_(paramOEntityPlayer)) && (c.a_(paramOEntityPlayer));
    }
}
