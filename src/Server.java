import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.MinecraftServer;

/**
 * Server.java - Interface to server stuff
 * 
 * @author James
 */
public class Server {

    private MinecraftServer server;

    /**
     * Creates a server
     * 
     * @param server
     */
    public Server(MinecraftServer server) {
        this.server = server;
    }

    /**
     * Sends a message to all users
     * 
     * @param msg
     *            Message text to send
     */
    public void messageAll(String msg) {
        this.server.f.a(new bh(msg));
    }

    /**
     * Bans specified player
     *
     * @param player Name of the player to ban
     *
     */
    public void ban(String player) {
        this.server.f.a(player);
    }

    /**
     * Unbans specified user
     *
     * @param player Player name to unban
     *
     */
    public void unban(String player) {
        this.server.f.b(player);
    }

    /**
     * Uses the specified console command
     * 
     * @param command
     */
    public void useConsoleCommand(String command) {
        server.a(command, server);
    }

    /**
     * Uses the specified console command
     * 
     * @param command
     *            command to use
     * @param player
     *            player to use command as
     */
    public void useConsoleCommand(String command, Player player) {
        server.a(command, player.getUser().a);
    }

    /**
     * Starts a timer using the built-in timer system.
     * 
     * @param uniqueString
     *            must be unique identifier for this timer
     * @param time
     *            time till it expires (6000 roughly equals 5 minutes)
     */
    public void setTimer(String uniqueString, int time) {
        MinecraftServer.b.put(uniqueString, time);
    }

    /**
     * Check to see if your timer has expired yet.
     * 
     * @param uniqueString
     *            unique identifier
     * @return false if timer has expired
     */
    public boolean isTimerExpired(String uniqueString) {
        return MinecraftServer.b.containsKey(uniqueString);
    }

    /**
     * Returns actual server time (-2^63 to 2^63-1)
     * 
     * @return time server time
     */
    public long getTime() {
        return server.e.e;
    }

    /**
     * Returns current server time (0-24000)
     * 
     * @return time server time
     */
    public long getRelativeTime() {
        long time = (server.e.e % 24000);
        // Java modulus is stupid.
        if (time < 0) {
            time += 24000;
        }
        return time;
    }

    /**
     * Sets the actual server time
     * 
     * @param time
     *            time (-2^63 to 2^63-1)
     */
    public void setTime(long time) {
        server.e.e = time;
    }

    /**
     * Sets the current server time
     * 
     * @param time
     *            time (0-24000)
     */
    public void setRelativeTime(long time) {
        long margin = (time - server.e.e) % 24000;
        // Java modulus is stupid.
        if (margin < 0) {
            margin += 24000;
        }
        server.e.e += margin;
    }

    /**
     * Returns the actual Minecraft Server
     * 
     * @return
     */
    public MinecraftServer getMCServer() {
        return server;
    }

    /**
     * Tries to match a character's name.
     * 
     * @param name
     * @return
     */
    public Player matchPlayer(String name) {
        Player lastPlayer = null;
        name = name.toLowerCase();

        for (Object player : server.f.b) {
            String playerName = ((et) player).at;

            if (playerName.toLowerCase().equals(name)) {
                // Perfect match found
                lastPlayer = ((et) player).getPlayer();
                break;
            }
            if (playerName.toLowerCase().indexOf(name.toLowerCase()) != -1) {
                // Partial match
                if (lastPlayer != null) {
                    // Found multiple
                    return null;
                }
                lastPlayer = ((et) player).getPlayer();
            }
        }

        return lastPlayer;
    }

    /**
     * Returns specified player
     * 
     * @param name
     * @return
     */
    public Player getPlayer(String name) {
        et user = server.f.h(name);
        return user == null ? null : user.getPlayer();
    }

    /**
     * Returns the player list.
     * @return list of players
     */
    public List<Player> getPlayerList() {
        List<Player> toRet = new ArrayList<Player>();
        for (Object o : server.f.b) {
            toRet.add(((et) o).getPlayer());
        }
        return toRet;
    }

    /**
     * Returns the list of mobs in all open chunks.
     * @return list of mobs
     */
    public List<Mob> getMobList() {
        List<Mob> toRet = new ArrayList<Mob>();
        for (Object o : server.e.b) {
            if (o instanceof gd || o instanceof ba) {
                toRet.add(new Mob((ka) o));
            }
        }
        return toRet;
    }

    /**
     * Returns the list of minecarts in all open chunks.
     * @return list of minecarts
     */
    public List<Minecart> getMinecartList() {
        List<Minecart> toRet = new ArrayList<Minecart>();
        for (Object o : server.e.b) {
            if (o instanceof jo) {
                toRet.add(new Minecart((jo) o));
            }
        }
        return toRet;
    }

    /**
     * Returns the list of boats in all open chunks.
     * @return list of boats
     */
    public List<Boat> getBoatList() {
        List<Boat> toRet = new ArrayList<Boat>();
        for (Object o : server.e.b) {
            if (o instanceof fl) {
                toRet.add(new Boat((fm) o));
            }
        }
        return toRet;
    }

    /**
     * Returns the list of all entities in the server in open chunks.
     * @return list of entities
     */
    public List<BaseEntity> getEntityList() {
        List<BaseEntity> toRet = new ArrayList<BaseEntity>();
        for (Object o : server.e.b) {
            if (o instanceof gd || o instanceof ba) {
                toRet.add(new Mob((ka) o));
            } else if (o instanceof jo) {
                toRet.add(new Minecart((jo) o));
            } else if (o instanceof fm) {
                toRet.add(new Boat((fm) o));
            } else if (o instanceof et) {
                toRet.add(((et)o).getPlayer());
            }
        }
        return toRet;
    }

    /**
     * Returns the list of all living entities (players, mobs) in open chunks.
     * @return list of living entities
     */
    public List<LivingEntity> getLivingEntityList() {
        List<LivingEntity> toRet = new ArrayList<LivingEntity>();
        for (Object o : server.e.b) {
            if (o instanceof gd || o instanceof ba) {
                toRet.add(new Mob((ka) o));
            } else if (o instanceof et) {
                toRet.add(((et)o).getPlayer());
            }
        }
        return toRet;
    }

    /**
     * Returns the list of vehicles in open chunks.
     * @return list of vehicles
     */
    public List<BaseVehicle> getVehicleEntityList() {
        List<BaseVehicle> toRet = new ArrayList<BaseVehicle>();
        for (Object o : server.e.b) {
            if (o instanceof jo) {
                toRet.add(new Minecart((jo) o));
            } else if (o instanceof fm) {
                toRet.add(new Boat((fm) o));
            }
        }
        return toRet;
    }

    /**
     * Get the global spawn location
     * 
     * @return Location object for spawn
     */
    public Location getSpawnLocation() {
        Location spawn = new Location();
        spawn.x = (server.e.m + 0.5D);
        spawn.y = server.e.e(this.server.e.m, this.server.e.o) + 1.5D;
        spawn.z = server.e.o + 0.5D;
        spawn.rotX = 0.0F;
        spawn.rotY = 0.0F;
        return spawn;
    }

    /**
     * Sets the block
     * 
     * @param block
     * @return
     */
    public boolean setBlock(Block block) {
        return setBlockAt(block.getType(), block.getX(), block.getY(), block.getZ()) && setBlockData(block.getX(), block.getY(), block.getZ(), block.getData());
    }

    /**
     * Returns the block at the specified location
     * 
     * @param x
     * @param y
     * @param z
     * @return block
     */
    public Block getBlockAt(int x, int y, int z) {
        return new Block(getBlockIdAt(x, y, z), x, y, z, getBlockData(x, y, z));
    }

    /**
     * Returns the block data at the specified coordinates
     * 
     * @param x
     *            x
     * @param y
     *            y
     * @param z
     *            z
     * @return block data
     */
    public int getBlockData(int x, int y, int z) {
        return server.e.b(x, y, z);
    }

    /**
     * Sets the block data at the specified coordinates
     * 
     * @param x
     *            x
     * @param y
     *            y
     * @param z
     *            z
     * @param data
     *            data
     * @return true if it was successful
     */
    public boolean setBlockData(int x, int y, int z, int data) {
        boolean toRet = server.e.c(x, y, z, data);
        etc.getMCServer().f.a(new fn(x, y, z, etc.getMCServer().e));
        ComplexBlock block = getComplexBlock(x, y, z);
        if (block != null) {
            block.update();
        }
        return toRet;
    }

    /**
     * Sets the block type at the specified location
     * 
     * @param blockType
     * @param x
     * @param y
     * @param z
     * @return true if successful
     */
    public boolean setBlockAt(int blockType, int x, int y, int z) {
        return server.e.d(x, y, z, blockType);
    }

    /**
     * Returns the highest block Y
     * 
     * @param x
     * @param z
     * @return highest block altitude
     */
    public int getHighestBlockY(int x, int z) {
        return server.e.d(x, z);
    }

    /**
     * Returns the block type at the specified location
     * 
     * @param x
     * @param y
     * @param z
     * @return block type
     */
    public int getBlockIdAt(int x, int y, int z) {
        return server.e.a(x, y, z);
    }

    /**
     * Returns the complex block at the specified location. Null if there's no
     * complex block there.
     * 
     * @param x
     *            x
     * @param y
     *            y
     * @param z
     *            z
     * @return complex block
     */
    public ComplexBlock getComplexBlock(int x, int y, int z) {
        ay localav = server.e.k(x, y, z);
        if (localav != null) {
            if (localav instanceof ic) {
                return new Chest((ic) localav);
            } else if (localav instanceof jn) {
                return new Sign((jn) localav);
            } else if (localav instanceof dv) {
                return new Furnace((dv) localav);
            } else if (localav instanceof cf) {
                return new MobSpawner((cf) localav);
            }
        }
        return null;
    }

    /**
     * Drops an item at the specified location
     * @param loc
     * @param itemId
     */
    public void dropItem(Location loc, int itemId) {
        dropItem(loc.x, loc.y, loc.z, itemId, 1);
    }

    /**
     * Drops an item at the specified location
     * @param x
     * @param y
     * @param z
     * @param itemId
     */
    public void dropItem(double x, double y, double z, int itemId) {
        dropItem(x, y, z, itemId, 1);
    }

    /**
     * Drops an item with desired quantity at the specified location
     * @param loc
     * @param itemId
     * @param quantity
     */
    public void dropItem(Location loc, int itemId, int quantity) {
        dropItem(loc.x, loc.y, loc.z, itemId, quantity);
    }

    /**
     * Drops an item with desired quantity at the specified location
     * @param x
     * @param y
     * @param z
     * @param itemId
     * @param quantity
     */
    public void dropItem(double x, double y, double z, int itemId, int quantity) {
        double d1 = server.e.l.nextFloat() * 0.7F + (1.0F - 0.7F) * 0.5D;
        double d2 = server.e.l.nextFloat() * 0.7F + (1.0F - 0.7F) * 0.5D;
        double d3 = server.e.l.nextFloat() * 0.7F + (1.0F - 0.7F) * 0.5D;

        gl localgl = new gl(server.e, x + d1, y + d2, z + d3, new hn(itemId, quantity));
        localgl.c = 10;
        server.e.a(localgl);
    }

    /**
     * Forces the server to update the physics for blocks around the given block
     * @param block the block that changed
     */
    public void updateBlockPhysics(Block block) {
        updateBlockPhysics(block.getX(), block.getY(), block.getZ(), block.getData());
    }

    /**
     * Forces the server to update the physics for blocks around the given block
     * @param x the X coordinate of the block
     * @param y the Y coordinate of the block
     * @param z the Z coordinate of the block
     * @param data the new data for the block
     */
    public void updateBlockPhysics(int x, int y, int z, int data) {
        server.e.b(x, y, z, data);
    }

    /**
     * Adds a runnable to the Server Queue, so that it will be executed in the Server Thread.
     *  
     * @param r - the runnable
     */
    public void addToServerQueue(Runnable r) {
        gv.add(r);
    }

    /**
     * Saves all player inventories to file
     */
    public void saveInventories() {
        server.f.d();
    }

    /**
     * Checks to see whether or not the chunk containing the given block is
     * loaded into memory.
     * 
     * @param block the Block to check
     * @return true if the chunk is loaded
     */
    public boolean isChunkLoaded(Block block) {
        return isChunkLoaded(block.getX(), block.getY(), block.getZ());
    }

    /**
     * Checks to see whether or not the chunk containing the given block
     * coordinates is loaded into memory.
     * 
     * @param x a block x-coordinate
     * @param y a block y-coordinate
     * @param z a block z-coordinate
     * @return true if the chunk is loaded
     */
    public boolean isChunkLoaded(int x, int y, int z) {
        return this.server.e.e(x, y, z);
    }

    /**
     * Loads the chunk containing the given block. If the chunk does not
     * exist, it will be generated.
     *
     * @param block the Block to check
     */
    public void loadChunk(Block block) {
        loadChunk(block.getX(), block.getY(), block.getZ());
    }

    /**
     * Loads the chunk containing the given block coordinates. If the chunk
     * does not exist, it will be generated.
     *
     * @param x a block x-coordinate
     * @param y a block y-coordinate
     * @param z a block z-coordinate
     */
    public void loadChunk(int x, int y, int z) {
        loadChunk(x >> 4, z >> 4);
    }

    /**
     * Loads the chunk containing the given chunk coordinates. If the chunk
     * does not exist, it will be generated.
     *
     * @param x a chunk x-coordinate
     * @param z a chunk z-coordinate
     */
    public void loadChunk(int x, int z) {
        this.server.e.A.d(x, z);
    }

    /**
     * Checks if the provided block is being powered through redstone
     * 
     * @param block Block to check
     * @return true if the block is being powered
     */
    public boolean isBlockPowered(Block block) {
        return isBlockPowered(block.getX(), block.getY(), block.getZ());
    }

    /**
     * Checks if the provided block is being powered through redstone
     *
     * @param x a block x-coordinate
     * @param y a block y-coordinate
     * @param z a block z-coordinate
     * @return true if the block is being powered
     */
    public boolean isBlockPowered(int x, int y, int z) {
        return this.server.e.m(x, y, z);
    }

    /**
     * Checks if the provided block is being indirectly powered through redstone
     *
     * @param block Block to check
     * @return true if the block is being indirectly powered
     */
    public boolean isBlockIndirectlyPowered(Block block) {
        return isBlockIndirectlyPowered(block.getX(), block.getY(), block.getZ());
    }

    /**
     * Checks if the provided block is being indirectly powered through redstone
     *
     * @param x a block x-coordinate
     * @param y a block y-coordinate
     * @param z a block z-coordinate
     * @return true if the block is being indirectly powered
     */
    public boolean isBlockIndirectlyPowered(int x, int y, int z) {
        return this.server.e.n(x, y, z);
    }
}
