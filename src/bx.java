import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;

import net.minecraft.server.MinecraftServer;
 
public final class bx extends Thread
{
    private MinecraftServer server;
	private static Object synchronizeObject = new Object();
	private static final Vector<Runnable> eventQueue = new Vector<Runnable>();
	
	public bx(String paramString, MinecraftServer paramMinecraftServer)
	{
		super(paramString);
		server = paramMinecraftServer;
	}

	public synchronized static void add(Runnable r) {
		synchronized(synchronizeObject) {
			eventQueue.add(r);
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
		Field loopCondition = null;
		
		try {
			minecraftServer = Class.forName("net.minecraft.server.MinecraftServer");
			loopCondition = minecraftServer.getDeclaredField("o");
			d = minecraftServer.getDeclaredMethod("d");
			h = minecraftServer.getDeclaredMethod("h");
			g = minecraftServer.getDeclaredMethod("g");
			
			loopCondition.setAccessible(true);
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
	        while (loopCondition.getBoolean(server)) {
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
	        while (loopCondition.getBoolean(server)) {
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
	      while (loopCondition.getBoolean(server)) {
	        server.b();
	        try {
	          Thread.sleep(10L);
	        } catch (InterruptedException localInterruptedException2) {
	          localInterruptedException2.printStackTrace();
	        }
	      }
	      } catch (Exception e) {
				e.printStackTrace();
			}
	    } finally {
	    	try {
				g.invoke(server);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				// Not sure why g = true is important but lets make sure it is!
				minecraftServer = Class.forName("net.minecraft.server.MinecraftServer");
				Field serverG = minecraftServer.getDeclaredField("g");
				serverG.setAccessible(true);
				serverG.set(server, true);
			} catch (Exception e) {
				e.printStackTrace();
			}
	      System.exit(0);
	    }
	  }
}