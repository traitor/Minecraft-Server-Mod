import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import net.minecraft.server.MinecraftServer;


/**
 * Server.java - Interface to server stuff
 * 
 * @author James
 */
public class Server implements Runnable {

	private static Server instance_;
	private static MinecraftServer server;
	private static Object synchronizeObject = new Object();
	private static final Vector<Runnable> eventQueue = new Vector<Runnable>();
	
	private Server(MinecraftServer s) {
		server = s;
	}
	
    /**
     * Attaches a Server to a MinecraftServer
     * 
     * @param server
     */
	public static Server attachTo(MinecraftServer s) {
		if (instance_ == null) {
			synchronized(synchronizeObject) {
				if (instance_ == null) {
					instance_ = new Server(s);
				}
			}
		}
		
		return instance_;
	}


    /**
     * Fetches the Server instance. Returns NULL if it is not attached to an MC-server.
     * 
     * @param server
     */
	public static Server getInstance() {
		return instance_;
	}

   

    /**
     * Sends a message to all users
     * 
     * @param msg
     *            Message text to send
     */
    public void messageAll(String msg) {
        this.server.f.a(new bg(msg));
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

        for (Object player: server.f.b) {
            String playerName = ((ep) player).ar;

            if (playerName.toLowerCase().equals(name)) {
                // Perfect match found
                lastPlayer = ((ep) player).getPlayer();
                break;
            }
            if (playerName.toLowerCase().indexOf(name.toLowerCase()) != -1) {
                // Partial match
                if (lastPlayer != null) {
                    // Found multiple
                    return null;
                }
                lastPlayer = ((ep) player).getPlayer();
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
        ep user = server.f.h(name);
        return user == null ? null : user.getPlayer();
    }

    /**
     * Returns the player list
     * 
     * @return
     */
    public List<Player> getPlayerList() {
        List<Player> toRet = new ArrayList<Player>();
        for (Object o : server.f.b) {
            toRet.add(((ep) o).getPlayer());
        }
        return toRet;
    }

    /**
     * Returns the list of mobs in all open chunks
     * 
     * @return
     */
    public List<Mob> getMobList() {
        List<Mob> toRet = new ArrayList<Mob>();
        for (Object o : server.e.b) {
            if (o instanceof hb) {
                toRet.add(new Mob((hb) o));
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
	public boolean setBlockData(final int x, final int y, final int z, final int data) {
    	synchronized(synchronizeObject) {
	    	eventQueue.add(new Runnable() {
	    		public void run() {
				    server.e.c(x, y, z, data);
				    etc.getMCServer().f.a(new fj(x, y, z, etc.getMCServer().e));
				    ComplexBlock block = getComplexBlock(x, y, z);
				    if (block != null) {
				        block.update();
				    }
	    		}
	    	});
    	}
    	return true;
	 }

	/**
	 * Sets the block type at the specified location
	 * 
	 * @param blockType
	 * @param x
	 * @param y
	 * @param z
	 */
    public boolean setBlockAt(final int blockType, final int x, final int y, final int z) {
    	synchronized(synchronizeObject) {
	    	eventQueue.add(new Runnable() {
	    		public void run() {
	    			server.e.d(x, y, z, blockType);
	    		}
	    	});
    	}
    	return true;
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
            if (localav instanceof hx) {
                return new Chest((hx) localav);
            } else if (localav instanceof ji) {
                return new Sign((ji) localav);
            } else if (localav instanceof ds) {
                return new Furnace((ds) localav);
            } else if (localav instanceof ce) {
            	return new MobSpawner((ce) localav);
            }
        }
        return null;
    }

    public void dropItem(Location loc, int itemId) {
        dropItem(loc.x, loc.y, loc.z, itemId, 1);
    }

    public void dropItem(double x, double y, double z, int itemId) {
        dropItem(x, y, z, itemId, 1);
    }

    public void dropItem(Location loc, int itemId, int quantity) {
        dropItem(loc.x, loc.y, loc.z, itemId, quantity);
    }

    public void dropItem(final double x, final double y, final double z, 
    		final int itemId, final int quantity) {
    	synchronized(synchronizeObject) {
	    	eventQueue.add(new Runnable() {
	    		public void run() {
	    			server.e.a(new gh(server.e, x, y, z, new hj(itemId, quantity)));
	    		}
	    	});
    	}
    }
    
    
    /**
     * Overloads parts of the default MinecraftServer loop, to allow processing 
     * of an event queue.  
     * 
     * Note: The event queue is meant for Runnables containing code that has to be 
     * executed from the Server Thread.
     * 
     */
	public void run() {
		// Access h(), g(), d() and 'n' from MineCraftServer.java using reflection.
		Class<?> minecraftServer;
		Method d = null, h = null, g = null; 
		Field n = null;
		
		try {
			minecraftServer = Class.forName("net.minecraft.server.MinecraftServer");
			n = minecraftServer.getDeclaredField("n");
			d = minecraftServer.getDeclaredMethod("d");
			h = minecraftServer.getDeclaredMethod("h");
			g = minecraftServer.getDeclaredMethod("g");
			
			n.setAccessible(true);
			h.setAccessible(true);
			d.setAccessible(true);
			g.setAccessible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		

		try {
			if ((Boolean) d.invoke(server)) {
				long l1 = System.currentTimeMillis();
				long l2 = 0L;
				while (n.getBoolean(server)) {
					long l3 = System.currentTimeMillis();
					long l4 = l3 - l1;
					
					if (l4 > 2000L) {
						MinecraftServer.a.warning("Can't keep up! Did the system time change, or is the server overloaded?");
							l4 = 2000L;
						}
						if (l4 < 0L) {
							MinecraftServer.a.warning("Time ran backwards! Did the system time change?");
							l4 = 0L;
						}
						l2 += l4;
						l1 = l3;
	
						while (l2 > 50L) {
							l2 -= 50L;
							
							// Here might not be the best place for this but it works...
							synchronized(synchronizeObject) {
								for(Iterator<Runnable> it = eventQueue.iterator(); it.hasNext();) {
									it.next().run();
									it.remove();
								}
							}
							
							h.invoke(server);
						}
						
						Thread.sleep(1L);
					}
				} else {
					while (n.getBoolean(server)) {
						server.b();
						try {
							Thread.sleep(10L);
						} catch (InterruptedException localInterruptedException1) {
							localInterruptedException1.printStackTrace();
						}
					}
				}
		} catch (Exception localException) {
			localException.printStackTrace();
			MinecraftServer.a.log(Level.SEVERE, "Unexpected exception", localException);
			try {
				while (n.getBoolean(server)) {
					server.b();
					try {
						Thread.sleep(10L);
					} catch (InterruptedException localInterruptedException2) {
						localInterruptedException2.printStackTrace();
					}
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} finally {
			try {
				g.invoke(server);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				minecraftServer = Class.forName("net.minecraft.server.MinecraftServer");
				Field serverG = minecraftServer.getDeclaredField("g");
				serverG.setAccessible(true);
				serverG.set(server, true);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(0);
		}
	}
}
