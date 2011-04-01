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
        return c_(index);
    }

    public void setContentsAt(int index, OItemStack value) {
        a(index, value);
    }

    public int getContentsSize() {
        return q_();
    }

    public Block getChestBlock() {
        if (b instanceof OTileEntityChest) {
            OTileEntityChest block = (OTileEntityChest) b;
            return etc.getServer().getBlockAt(block.e, block.f, block.g);
        }
        if (c instanceof OTileEntityChest) {
            OTileEntityChest block = (OTileEntityChest) c;
            return etc.getServer().getBlockAt(block.e, block.f, block.g);
        }
        return null;
    }

    public String getName() {
        return a;
    }

    public void setName(String value) {
        a = value;
    }

    public int q_() {
        return b.q_() + c.q_();
    }

    public String c() {
        return a;
    }

    public OItemStack c_(int paramInt) {
        if (paramInt >= b.q_())
            return c.c_(paramInt - b.q_());
        return b.c_(paramInt);
    }

    public OItemStack a(int paramInt1, int paramInt2) {
        if (paramInt1 >= b.q_())
            return c.a(paramInt1 - b.q_(), paramInt2);
        return b.a(paramInt1, paramInt2);
    }

    public void a(int paramInt, OItemStack paramOItemStack) {
        if (paramInt >= b.q_())
            c.a(paramInt - b.q_(), paramOItemStack);
        else
            b.a(paramInt, paramOItemStack);
    }

    public int r_() {
        return b.r_();
    }

    public void i() {
        b.i();
        c.i();
    }

    public boolean a_(OEntityPlayer paramOEntityPlayer) {
        return (b.a_(paramOEntityPlayer)) && (c.a_(paramOEntityPlayer));
    }
}
