/**
 * Used so we don't have class collisions with Runecraft
 *  - Patch by Zeerix
 * @author James
 */
public class Digging extends kv {
    /**
     * Creates a digging class
     * @param world
     */
    public Digging(ff world) {
        super(world);
    }

    /**
     * Bloop.
     * @param world
     * @param player
     */
    public Digging(ff world, fi player) {
        this(world);
        a = player;
    }

    /**
     * Called when a block is destroyed. We intercept it.
     * @param x
     * @param y
     * @param z
     * @return
     */
    public boolean c(int x, int y, int z) {
        Block block = etc.getServer().getBlockAt(x, y, z);
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_BROKEN, ((fi) a).getPlayer(), block)) {
            return true;
        }
        return super.c(x, y, z);
    }

    /**
     * Called when a player right-click air with an item in hand. We intercept it.
     * @param player
     * @param world
     * @param item
     * @return
     */
    public boolean a(gp player, ff world, ik item, Block blockToPlace, Block blockClicked) {
        // hMod: only call this hook if we're not using buckets/signs
        if (item != null) {
            if (item.a > 0 && item.c != Item.Type.Sign.getId() && item.c != Item.Type.Bucket.getId() && item.c != Item.Type.WaterBucket.getId() && item.c != Item.Type.LavaBucket.getId()) {
                if (player instanceof fi && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((fi) player).getPlayer(), blockToPlace, blockClicked, new Item(item))) {
                    return false;
                }
            }
        }
        return super.a(player, world, item);
    }
}
