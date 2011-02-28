import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.DelayQueue;
import net.minecraft.server.MinecraftServer;

public class OEntityTracker {

    private Set<OEntityTrackerEntry>             a          = new HashSet<OEntityTrackerEntry>();
    private OMCHashTable                         b          = new OMCHashTable();
    private MinecraftServer                      c;
    private int                                  d;

    // hMod: New fields to store the runnables in.
    private static final DelayQueue<DelayedTask> delayQueue = new DelayQueue<DelayedTask>();

    public OEntityTracker(MinecraftServer paramMinecraftServer) {
        c = paramMinecraftServer;
        d = paramMinecraftServer.f.a();
    }

    public void a(OEntity paramOEntity) {
        OEntityPlayerMP localOEntityPlayerMP;
        if ((paramOEntity instanceof OEntityPlayerMP)) {
            a(paramOEntity, 512, 2);
            localOEntityPlayerMP = (OEntityPlayerMP) paramOEntity;
            for (OEntityTrackerEntry localOEntityTrackerEntry : a) {
                if (localOEntityTrackerEntry.a != localOEntityPlayerMP) {
                    localOEntityTrackerEntry.b(localOEntityPlayerMP);
                }
            }
        } else if ((paramOEntity instanceof OEntityFish)) {
            a(paramOEntity, 64, 5, true);
        } else if ((paramOEntity instanceof OEntityArrow)) {
            a(paramOEntity, 64, 5, true);
        } else if ((paramOEntity instanceof OEntitySnowball)) {
            a(paramOEntity, 64, 5, true);
        } else if ((paramOEntity instanceof OEntityEgg)) {
            a(paramOEntity, 64, 5, true);
        } else if ((paramOEntity instanceof OEntityItem)) {
            a(paramOEntity, 64, 20, true);
        } else if ((paramOEntity instanceof OEntityMinecart)) {
            a(paramOEntity, 160, 5, true);
        } else if ((paramOEntity instanceof OEntityBoat)) {
            a(paramOEntity, 160, 5, true);
        } else if ((paramOEntity instanceof OEntitySquid)) {
            a(paramOEntity, 160, 3, true);
        } else if ((paramOEntity instanceof OIAnimals)) {
            a(paramOEntity, 160, 3);
        } else if ((paramOEntity instanceof OEntityTNTPrimed)) {
            a(paramOEntity, 160, 10, true);
        } else if ((paramOEntity instanceof OEntityFallingSand)) {
            a(paramOEntity, 160, 20, true);
        } else if ((paramOEntity instanceof OEntityPainting)) {
            a(paramOEntity, 160, 2147483647, false);
        }
    }

    public void a(OEntity paramOEntity, int paramInt1, int paramInt2) {
        a(paramOEntity, paramInt1, paramInt2, false);
    }

    public void a(OEntity paramOEntity, int paramInt1, int paramInt2, boolean paramBoolean) {
        if (paramInt1 > d) {
            paramInt1 = d;
        }
        if (b.b(paramOEntity.aA)) {
            throw new IllegalStateException("Entity is already tracked!");
        }
        OEntityTrackerEntry localOEntityTrackerEntry = new OEntityTrackerEntry(paramOEntity, paramInt1, paramInt2, paramBoolean);
        a.add(localOEntityTrackerEntry);
        b.a(paramOEntity.aA, localOEntityTrackerEntry);
        localOEntityTrackerEntry.b(c.e.d);
    }

    public void b(OEntity paramOEntity) {
        if ((paramOEntity instanceof OEntityPlayerMP)) {
            Object localObject = (OEntityPlayerMP) paramOEntity;
            for (OEntityTrackerEntry localOEntityTrackerEntry : a) {
                localOEntityTrackerEntry.a((OEntityPlayerMP) localObject);
            }
        }
        Object localObject = (OEntityTrackerEntry) b.d(paramOEntity.aA);
        if (localObject != null) {
            a.remove(localObject);
            ((OEntityTrackerEntry) localObject).a();
        }
    }

    public void a() {
        try {
            synchronized (a) {
                ArrayList localArrayList = new ArrayList();
                for (Iterator localIterator1 = a.iterator(); localIterator1.hasNext();) {
                    Object localObject = (OEntityTrackerEntry) localIterator1.next();
                    ((OEntityTrackerEntry) localObject).a(c.e.d);
                    if ((((OEntityTrackerEntry) localObject).m) && ((((OEntityTrackerEntry) localObject).a instanceof OEntityPlayerMP))) {
                        localArrayList.add((OEntityPlayerMP) ((OEntityTrackerEntry) localObject).a);
                    }
                }
                Object localObject;
                for (int i = 0; i < localArrayList.size(); i++) {
                    localObject = (OEntityPlayerMP) localArrayList.get(i);
                    for (OEntityTrackerEntry localOEntityTrackerEntry : a) {
                        if (localOEntityTrackerEntry.a != localObject) {
                            localOEntityTrackerEntry.b((OEntityPlayerMP) localObject);
                        }
                    }
                }
            }
        } catch (ConcurrentModificationException e) {
            // people seem to get this exception often, lets just catch so it
            // doesn't crash the server.
            MinecraftServer.a.warning("hMod WARNING: ConcurrentModificationException in OEntityTracker:");
            e.printStackTrace();
        }
        // hMod: Execute runnables contained in eventQueue.
        for (DelayedTask task = delayQueue.poll(); task != null; task = delayQueue.poll())
            // should we catch exceptions here?
            task.run();
    }

    // hMod: Allow adding of tasks to the queue

    public static void add(Runnable task, long delayMillis) {
        // j.u.concurent.* classes are thread safe
        delayQueue.add(new DelayedTask(task, delayMillis));
    }

    // hMod: deprecated. Use server.addToServerQueue().
    @Deprecated
    public synchronized static void add(Runnable r) {
        add(r, 0L);
    }

    public void a(OEntity paramOEntity, OPacket paramOPacket) {
        OEntityTrackerEntry localOEntityTrackerEntry = (OEntityTrackerEntry) b.a(paramOEntity.aA);
        if (localOEntityTrackerEntry != null) {
            localOEntityTrackerEntry.a(paramOPacket);
        }
    }

    public void b(OEntity paramOEntity, OPacket paramOPacket) {
        OEntityTrackerEntry localOEntityTrackerEntry = (OEntityTrackerEntry) b.a(paramOEntity.aA);
        if (localOEntityTrackerEntry != null) {
            localOEntityTrackerEntry.b(paramOPacket);
        }
    }

    public void a(OEntityPlayerMP paramOEntityPlayerMP) {
        for (OEntityTrackerEntry localOEntityTrackerEntry : a) {
            localOEntityTrackerEntry.c(paramOEntityPlayerMP);
        }
    }
}
