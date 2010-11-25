/* Patch by Zeerix */
public class Digging extends jt {

    public Digging(eo world) {
        super(world);
    }

    public Digging(eo world, er player) {
        this(world);
        a = player;
    }

    public boolean c(int x, int y, int z) {
        Block block = etc.getServer().getBlockAt(x, y, z);
        Boolean result = (Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_BROKEN, new Object[]{(er) a, block});
        if (result)
            return true;
        return super.c(x, y, z);
    }
}
