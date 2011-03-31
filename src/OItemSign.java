public class OItemSign extends OItem {
    public OItemSign(int paramInt) {
        super(paramInt);
        be = 1;
    }

    @Override
    public boolean a(OItemStack paramOItemStack, OEntityPlayer paramOEntityPlayer, OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramInt4 == 0)
            return false;
        if (!paramOWorld.c(paramInt1, paramInt2, paramInt3).a())
            return false;

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

        if (!OBlock.aD.a(paramOWorld, paramInt1, paramInt2, paramInt3))
            return false;

        if (paramInt4 == 1)
            paramOWorld.b(paramInt1, paramInt2, paramInt3, OBlock.aD.bl, OMathHelper.b((paramOEntityPlayer.aQ + 180.0F) * 16.0F / 360.0F + 0.5D) & 0xF);
        else
            paramOWorld.b(paramInt1, paramInt2, paramInt3, OBlock.aI.bl, paramInt4);

        paramOItemStack.a -= 1;
        OTileEntitySign localOTileEntitySign = (OTileEntitySign) paramOWorld.m(paramInt1, paramInt2, paramInt3);
        if (localOTileEntitySign != null)
            paramOEntityPlayer.a(localOTileEntitySign);
        return true;
    }
}