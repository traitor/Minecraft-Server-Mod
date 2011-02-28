import java.util.Random;

public class OItemBucket extends OItem {

    private int a;

    public OItemBucket(int paramInt1, int paramInt2) {
        super(paramInt1);
        bd = 1;
        be = 64;
        a = paramInt2;
    }

    public OItemStack a(OItemStack paramOItemStack, OWorld paramOWorld, OEntityPlayer paramOEntityPlayer) {
        float f1 = 1.0F;

        float f2 = paramOEntityPlayer.aS + (paramOEntityPlayer.aQ - paramOEntityPlayer.aS) * f1;
        float f3 = paramOEntityPlayer.aR + (paramOEntityPlayer.aP - paramOEntityPlayer.aR) * f1;

        double d1 = paramOEntityPlayer.aG + (paramOEntityPlayer.aJ - paramOEntityPlayer.aG) * f1;
        double d2 = paramOEntityPlayer.aH + (paramOEntityPlayer.aK - paramOEntityPlayer.aH) * f1 + 1.62D - paramOEntityPlayer.bb;
        double d3 = paramOEntityPlayer.aI + (paramOEntityPlayer.aL - paramOEntityPlayer.aI) * f1;

        OVec3D localOVec3D1 = OVec3D.b(d1, d2, d3);

        float f4 = OMathHelper.b(-f3 * 0.01745329F - 3.141593F);
        float f5 = OMathHelper.a(-f3 * 0.01745329F - 3.141593F);
        float f6 = -OMathHelper.b(-f2 * 0.01745329F);
        float f7 = OMathHelper.a(-f2 * 0.01745329F);

        float f8 = f5 * f6;
        float f9 = f7;
        float f10 = f4 * f6;

        double d4 = 5.0D;
        OVec3D localOVec3D2 = localOVec3D1.c(f8 * d4, f9 * d4, f10 * d4);
        OMovingObjectPosition localOMovingObjectPosition = paramOWorld.a(localOVec3D1, localOVec3D2, a == 0);
        if (localOMovingObjectPosition == null) {
            return paramOItemStack;
        }

        if (localOMovingObjectPosition.a == OEnumMovingObjectType.a) {
            int i = localOMovingObjectPosition.b;
            int j = localOMovingObjectPosition.c;
            int k = localOMovingObjectPosition.d;

            if (!paramOWorld.a(paramOEntityPlayer, i, j, k)) {
                return paramOItemStack;
            }
            // hMod: Click == placed when handling an empty bukkit!
            Block blockClicked = new Block(paramOWorld.a(i, j, k), i, j, k);
            blockClicked.setFaceClicked(Block.Face.fromId(localOMovingObjectPosition.e));
            Block blockPlaced = new Block(0, i, j, k);

            if (a == 0) {
                if ((paramOWorld.c(i, j, k) == OMaterial.f) && (paramOWorld.b(i, j, k) == 0)) {
                    // Filling a bucket with water!
                    if (paramOEntityPlayer instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) paramOEntityPlayer).getPlayer(), blockPlaced, blockClicked, new Item(paramOItemStack)))
                        return paramOItemStack;

                    paramOWorld.e(i, j, k, 0);
                    return new OItemStack(OItem.av);
                }
                if ((paramOWorld.c(i, j, k) == OMaterial.g) && (paramOWorld.b(i, j, k) == 0)) {
                    // Filling a bucket with lava!
                    if (paramOEntityPlayer instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) paramOEntityPlayer).getPlayer(), blockPlaced, blockClicked, new Item(paramOItemStack)))
                        return paramOItemStack;

                    paramOWorld.e(i, j, k, 0);
                    return new OItemStack(OItem.aw);
                }
            } else {
                if (a < 0) {
                    return new OItemStack(OItem.au);
                }
                if (localOMovingObjectPosition.e == 0) {
                    j--;
                }
                if (localOMovingObjectPosition.e == 1) {
                    j++;
                }
                if (localOMovingObjectPosition.e == 2) {
                    k--;
                }
                if (localOMovingObjectPosition.e == 3) {
                    k++;
                }
                if (localOMovingObjectPosition.e == 4) {
                    i--;
                }
                if (localOMovingObjectPosition.e == 5) {
                    i++;
                }

                if ((paramOWorld.e(i, j, k)) || (!paramOWorld.c(i, j, k).a())) {
                    if ((paramOWorld.m.d) && (a == OBlock.A.bk)) {
                        paramOWorld.a(d1 + 0.5D, d2 + 0.5D, d3 + 0.5D, "random.fizz", 0.5F, 2.6F + (paramOWorld.k.nextFloat() - paramOWorld.k.nextFloat()) * 0.8F);
                        for (int m = 0; m < 8; m++) {
                            paramOWorld.a("largesmoke", i + Math.random(), j + Math.random(), k + Math.random(), 0.0D, 0.0D, 0.0D);
                        }
                    } else {
                        // hMod: Bucket empty.
                        blockPlaced = new Block(a, i, j, k);
                        if (paramOEntityPlayer instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) paramOEntityPlayer).getPlayer(), blockPlaced, blockClicked, new Item(paramOItemStack)))
                            return paramOItemStack;

                        paramOWorld.b(i, j, k, a, 0);
                    }
                    return new OItemStack(OItem.au);
                }
            }

        } else if ((a == 0) && ((localOMovingObjectPosition.g instanceof OEntityCow))) {
            return new OItemStack(OItem.aE);
        }

        return paramOItemStack;
    }
}
