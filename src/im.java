
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.DelayQueue;
import net.minecraft.server.MinecraftServer;

public class im {

    private Set<io> a = new HashSet<io>();
    private kh b = new kh();
    private MinecraftServer c;
    private int d;
    // hMod: New fields to store the runnables in.
    private static final DelayQueue<DelayedTask> delayQueue = new DelayQueue<DelayedTask>();

    public im(MinecraftServer paramMinecraftServer) {
        c = paramMinecraftServer;
        d = paramMinecraftServer.f.a();
    }

    public void a(fe paramfe) {
        fy localfy;
        if ((paramfe instanceof fy)) {
            a(paramfe, 512, 2);
            localfy = (fy) paramfe;
            for (io localio : a) {
                if (localio.a != localfy) {
                    localio.b(localfy);
                }
            }
        } else if ((paramfe instanceof mq)) {
            a(paramfe, 64, 5, true);
        } else if ((paramfe instanceof fc)) {
            a(paramfe, 64, 5, true);
        } else if ((paramfe instanceof by)) {
            a(paramfe, 64, 5, true);
        } else if ((paramfe instanceof jb)) {
            a(paramfe, 64, 5, true);
        } else if ((paramfe instanceof ic)) {
            a(paramfe, 64, 20, true);
        } else if ((paramfe instanceof lw)) {
            a(paramfe, 160, 5, true);
        } else if ((paramfe instanceof gu)) {
            a(paramfe, 160, 5, true);
        } else if ((paramfe instanceof le)) {
            a(paramfe, 160, 3, true);
        } else if ((paramfe instanceof ap)) {
            a(paramfe, 160, 3);
        } else if ((paramfe instanceof dh)) {
            a(paramfe, 160, 10, true);
        } else if ((paramfe instanceof kd)) {
            a(paramfe, 160, 20, true);
        } else if ((paramfe instanceof dd)) {
            a(paramfe, 160, 2147483647, false);
        }
    }

    public void a(fe paramfe, int paramInt1, int paramInt2) {
        a(paramfe, paramInt1, paramInt2, false);
    }

    public void a(fe paramfe, int paramInt1, int paramInt2, boolean paramBoolean) {
        if (paramInt1 > d) {
            paramInt1 = d;
        }
        if (b.b(paramfe.g)) {
            throw new IllegalStateException("Entity is already tracked!");
        }
        io localio = new io(paramfe, paramInt1, paramInt2, paramBoolean);
        a.add(localio);
        b.a(paramfe.g, localio);
        localio.b(c.e.d);
    }

    public void b(fe paramfe) {
        if ((paramfe instanceof fy)) {
            fy localObject = (fy) paramfe;
            for (io localio : a) {
                localio.a((fy) localObject);
            }
        }
        Object localObject = (io) b.d(paramfe.g);
        if (localObject != null) {
            a.remove(localObject);
            ((io) localObject).a();
        }
    }

    public void a() {
        ArrayList localArrayList = new ArrayList();
        for (Iterator localIterator1 = a.iterator(); localIterator1.hasNext();) {
            io localObject = (io) localIterator1.next();
            ((io) localObject).a(c.e.d);
            if ((((io) localObject).m) && ((((io) localObject).a instanceof fy))) {
                localArrayList.add((fy) ((io) localObject).a);
            }
        }
        Object localObject;
        for (int i = 0; i < localArrayList.size(); i++) {
            localObject = (fy) localArrayList.get(i);
            for (io localio : a) {
                if (localio.a != localObject) {
                    localio.b((fy) localObject);
                }
            }
        }
        // hMod: Execute runnables contained in eventQueue.
        for (DelayedTask task = delayQueue.poll(); task != null; task = delayQueue.poll()) {
            // should we catch exceptions here?
            task.run();
        }
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

    public void a(fe paramfe, kx paramkx) {
        io localio = (io) b.a(paramfe.g);
        if (localio != null) {
            localio.a(paramkx);
        }
    }

    public void b(fe paramfe, kx paramkx) {
        io localio = (io) b.a(paramfe.g);
        if (localio != null) {
            localio.b(paramkx);
        }
    }

    public void a(fy paramfy) {
        for (io localio : a) {
            localio.c(paramfy);
        }
    }
}
