//Interface for the minecraft server
import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.MinecraftServer;

/**
 * Server.java - Used for accessing server stuff.
 * @author James
 */
public class Server {

    private MinecraftServer server;

    /**
     * Creates a server
     * @param server
     */
    public Server(MinecraftServer server) {
        this.server = server;
    }

    /**
     * Uses the specified console command
     * @param command
     */
    public void useConsoleCommand(String command) {
        server.a(command, server);
    }

    /**
     * Starts a timer using the built-in timer system.
     * @param uniqueString
     * @param time
     */
    public void setTimer(String uniqueString, int time) {
        MinecraftServer.b.put(uniqueString, time);
    }

    /**
     * Check to see if your timer has expired yet.
     * @param uniqueString
     * @return true if timer has expired
     */
    public boolean isTimerExpired(String uniqueString) {
        return MinecraftServer.b.containsKey(uniqueString);
    }

    /**
     * Returns current server time (0-24000)
     * @return time
     */
    public long getTime() {
        return server.e.c;
    }

    /**
     * Sets the current server time
     * @param time time (0-24000)
     */
    public void setTime(long time) {
        server.e.c = time;
    }

    /**
     * Returns the actual Minecraft Server
     * @return
     */
    public MinecraftServer getMCServer() {
        return server;
    }

    /**
     * Returns the player list
     * @return
     */
    public List<Player> getPlayerList() {
        List<Player> toRet = new ArrayList<Player>();
        for (Object o : server.f.b)
            toRet.add(new Player((ea)o));
        return toRet;
    }

    /**
     * Sets the block
     * @param block
     */
    public void setBlock(Block block) {
        setBlockAt(block.getX(), block.getY(), block.getZ(), block.getType());
    }

    /**
     * Returns the block at the specified location
     * @param x
     * @param y
     * @param z
     * @return block
     */
    public Block getBlockAt(int x, int y, int z) {
        return new Block(getBlockIdAt(x, y, z), x, y, z);
    }

    /**
     * Sets the block type at the specified location
     * @param blockType
     * @param x
     * @param y
     * @param z
     */
    public void setBlockAt(int blockType, int x, int y, int z) {
        server.e.d(x, y, z, blockType);
    }

    /**
     * Returns the highest block Y
     * @param x
     * @param z
     * @return highest block altitude
     */
    public int getHighestBlockY(int x, int z) {
        return server.e.d(x, z);
    }

    /**
     * Returns the block type at the specified location
     * @param x
     * @param y
     * @param z
     * @return block type
     */
    public int getBlockIdAt(int x, int y, int z) {
        return server.e.a(x, y, z);
    }
}
