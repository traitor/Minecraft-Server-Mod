public class OItemRedstone extends OItem {
    public OItemRedstone(int paramInt) {
        super(paramInt);
    }

    @Override
    public boolean a(OItemStack paramOItemStack, OEntityPlayer paramOEntityPlayer, OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramInt4 == 0)
            paramInt2--;
        if (paramInt4 == 1)
            paramInt2++;
        if (paramInt4 == 2)
            paramInt3--;
        if (paramInt4 == 3)
            paramInt3++;
        if (paramInt4 == 4)
            paramInt1--;
        if (paramInt4 == 5)
            paramInt1++;
        if (!paramOWorld.e(paramInt1, paramInt2, paramInt3))
            return false;
        if (OBlock.av.a(paramOWorld, paramInt1, paramInt2, paramInt3)) {
            paramOItemStack.a -= 1;
            paramOWorld.e(paramInt1, paramInt2, paramInt3, OBlock.av.bl);
        }

        return true;
    }
}