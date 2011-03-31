public class OInventoryLargeChest implements OIInventory {
    private String      a;
    private OIInventory b;
    private OIInventory c;

    public OInventoryLargeChest(String paramString, OIInventory paramOIInventory1, OIInventory paramOIInventory2) {
        a = paramString;
        b = paramOIInventory1;
        c = paramOIInventory2;
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