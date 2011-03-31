public class OItemFlintAndSteel extends OItem {
    public OItemFlintAndSteel(int paramInt) {
        super(paramInt);
        be = 1;
        d(64);
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

        int i = paramOWorld.a(paramInt1, paramInt2, paramInt3);

        if (i == 0) {
            paramOWorld.a(paramInt1 + 0.5D, paramInt2 + 0.5D, paramInt3 + 0.5D, "fire.ignite", 1.0F, b.nextFloat() * 0.4F + 0.8F);
            paramOWorld.e(paramInt1, paramInt2, paramInt3, OBlock.ar.bl);
        }

        paramOItemStack.a(1, paramOEntityPlayer);
        return true;
    }
}