
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.DelayQueue;
import net.minecraft.server.MinecraftServer;

public class hp {

    private Set<hr> a = new HashSet<hr>();
    private jf b = new jf();
    private MinecraftServer c;
    private int d;

    // hMod: New fields to store the runnables in.
    private static final DelayQueue<DelayedTask> delayQueue = new DelayQueue<DelayedTask>();

    public hp(MinecraftServer paramMinecraftServer) {
        this.c = paramMinecraftServer;
        this.d = paramMinecraftServer.f.a();
    }

    public void a(ep paramep) {
        fi localfi;
        if ((paramep instanceof fi)) {
            a(paramep, 512, 2);
            localfi = (fi) paramep;
            for (hr localhq : this.a) {
                if (localhq.a != localfi) {
                    localhq.b(localfi);
                }
            }
        } else if ((paramep instanceof lj)) {
            a(paramep, 64, 5, true);
        } else if ((paramep instanceof en)) {
            a(paramep, 64, 5, true);
        } else if ((paramep instanceof bq)) {
            a(paramep, 64, 5, true);
        } else if ((paramep instanceof id)) {
            a(paramep, 64, 5, true);
        } else if ((paramep instanceof hf)) {
            a(paramep, 64, 20, true);
        } else if ((paramep instanceof kq)) {
            a(paramep, 160, 5, true);
        } else if ((paramep instanceof gc)) {
            a(paramep, 160, 5, true);
        } else if ((paramep instanceof al)) {
            a(paramep, 160, 3);
        } else if ((paramep instanceof cx)) {
            a(paramep, 160, 10, true);
        } else if ((paramep instanceof jc)) {
            a(paramep, 160, 20, true);
        }
    }

    public void a(ep paramep, int paramInt1, int paramInt2) {
        a(paramep, paramInt1, paramInt2, false);
    }

    public void a(ep paramep, int paramInt1, int paramInt2, boolean paramBoolean) {
        if (paramInt1 > this.d) {
            paramInt1 = this.d;
        }
        if (this.b.b(paramep.g)) {
            throw new IllegalStateException("Entity is already tracked!");
        }
        hr localhq = new hr(paramep, paramInt1, paramInt2, paramBoolean);
        this.a.add(localhq);
        this.b.a(paramep.g, localhq);
        localhq.b(this.c.e.d);
    }

    public void b(ep paramep) {
        if ((paramep instanceof fi)) {
            fi localObject = (fi) paramep;
            for (hr localhq : this.a) {
                localhq.a((fi) localObject);
            }
        }
        Object localObject = (hr) this.b.d(paramep.g);
        if (localObject != null) {
            this.a.remove(localObject);
            ((hr) localObject).a();
        }
    }

    public void a() {
        ArrayList localArrayList = new ArrayList();
        for (Iterator localIterator1 = this.a.iterator(); localIterator1.hasNext();) {
            hr localObject = (hr) localIterator1.next();
            ((hr) localObject).a(this.c.e.d);
            if ((((hr) localObject).p) && ((((hr) localObject).a instanceof fi))) {
                localArrayList.add((fi) ((hr) localObject).a);
            }
        }
        Object localObject;
        for (int i = 0; i < localArrayList.size(); i++) {
            localObject = (fi) localArrayList.get(i);
            for (hr localhq : this.a) {
                if (localhq.a != localObject) {
                    localhq.b((fi) localObject);
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

    public void a(ep paramep, jv paramju) {
        hr localhq = (hr) this.b.a(paramep.g);
        if (localhq != null) {
            localhq.a(paramju);
        }
    }

    public void b(ep paramep, jv paramju) {
        hr localhq = (hr) this.b.a(paramep.g);
        if (localhq != null) {
            localhq.b(paramju);
        }
    }

    public void a(fi paramfi) {
        for (hr localhq : this.a) {
            localhq.c(paramfi);
        }
    }
}
