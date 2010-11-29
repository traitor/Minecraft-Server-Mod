/* Patch by Zeerix */

/**
 * Used so we don't have class collisions with Runecraft
 * @author James
 */
public class Digging extends jt {

    /**
     * Creates a digging class
     * @param world
     */
    public Digging(eo world) {
        super(world);
    }

    /**
     * Bloop.
     * @param world
     * @param player
     */
    public Digging(eo world, er player) {
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
        Boolean result = (Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_BROKEN, new Object[]{(er) a, block});
        if (result)
            return true;
        return super.c(x, y, z);
    }
}
