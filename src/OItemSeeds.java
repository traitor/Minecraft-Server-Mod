public class OItemSeeds extends OItem {
    private int a;

    public OItemSeeds(int paramInt1, int paramInt2) {
        super(paramInt1);
        a = paramInt2;
    }

    @Override
    public boolean a(OItemStack paramOItemStack, OEntityPlayer paramOEntityPlayer, OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramInt4 != 1)
            return false;

        int i = paramOWorld.a(paramInt1, paramInt2, paramInt3);

        if ((i == OBlock.aA.bl) && (paramOWorld.e(paramInt1, paramInt2 + 1, paramInt3))) {
            paramOWorld.e(paramInt1, paramInt2 + 1, paramInt3, a);
            paramOItemStack.a -= 1;
            return true;
        }
        return false;
    }
}