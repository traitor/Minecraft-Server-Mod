/**
 * Used so we don't have class collisions with Runecraft
 *  - Patch by Zeerix
 * @author James
 */
public class Digging extends ju {

    /**
     * Creates a digging class
     * @param world
     */
    public Digging(ep world) {
        super(world);
    }

    /**
     * Bloop.
     * @param world
     * @param player
     */
    public Digging(ep world, es player) {
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
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_BROKEN, new Object[]{(es) a, block})) {
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
    public boolean a(fy player, ep world, hm item) {
        if (player instanceof es && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, new Object[]{((es)player).getPlayer(), new Item(item)})) {
            return false;
        }
        return super.a(player, world, item);
    }
}
