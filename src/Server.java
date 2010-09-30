//Interface for the minecraft server
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
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
     *  Get player by string name iteratively
     * @param name Name of player to search for
     * @return Player object or null if not found
     */
    public Player getPlayer(String name)
    {
        List<Player> list = getPlayerList();
        Iterator<Player> i = list.iterator();
        while(i.hasNext())
        {
            Player pl = i.next();
            if (pl.getName().equalsIgnoreCase(name)) return pl;
        }
        return null;
    }


    /**
     * Normalize a player name. It looks up to see if there's a player by that name on the server and then returns it with the correct capitolization as per what the actual username is
     * @param name Player name to attempt to normalize
     * @return Name of player, or null if the player was not found
     */
    public String getPlayerName(String name)
    {
        Player pl = getPlayer(name);
        if (pl == null)
            return null;
        return pl.getName();
    }

    /**
     * Get the global spawn location
     * @return Location object for spawn
     */
    public Location getSpawnLocation()
    {
        Location spawn = new Location();
        spawn.x = (server.e.n + 0.5D);
        spawn.y = server.e.d(server.e.n, server.e.p) + 1.5D;
        spawn.z = server.e.p + 0.5D;
        spawn.rotX = 0.0F;
        spawn.rotY = 0.0F;
        return spawn;
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
