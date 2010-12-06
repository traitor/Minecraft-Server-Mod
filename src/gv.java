import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.DelayQueue;

import net.minecraft.server.MinecraftServer;

public class gv {
    private Set<gw> a = new HashSet<gw>();
    private ig b = new ig();
    private MinecraftServer c;
    private int d;

    // hMod: New fields to store the runnables in.
    private static final DelayQueue<DelayedTask> delayQueue = new DelayQueue<DelayedTask>();

    public gv(MinecraftServer paramMinecraftServer) {
        this.c = paramMinecraftServer;
        this.d = paramMinecraftServer.f.a();
    }

    public void a(ea paramea) {
        et localet;
        if ((paramea instanceof et)) {
            a(paramea, 512, 2);
            localet = (et) paramea;
            for (gw localgw : this.a) {
                if (localgw.a != localet) {
                    localgw.b(localet);
                }
            }
        } else if ((paramea instanceof kg)) {
            a(paramea, 64, 5, true);
        } else if ((paramea instanceof dy)) {
            a(paramea, 64, 5, true);
        } else if ((paramea instanceof bg)) {
            a(paramea, 64, 5, true);
        } else if ((paramea instanceof gl)) {
            a(paramea, 64, 20, true);
        } else if ((paramea instanceof jo)) {
            a(paramea, 160, 5, true);
        } else if ((paramea instanceof fm)) {
            a(paramea, 160, 5, true);
        } else if ((paramea instanceof af)) {
            a(paramea, 160, 3);
        } else if ((paramea instanceof cj)) {
            a(paramea, 160, 10, true);
        }
    }

    public void a(ea paramea, int paramInt1, int paramInt2) {
        a(paramea, paramInt1, paramInt2, false);
    }

    public void a(ea paramea, int paramInt1, int paramInt2, boolean paramBoolean) {
        if (paramInt1 > this.d) {
            paramInt1 = this.d;
        }
        if (this.b.b(paramea.g)) {
            throw new IllegalStateException("Entity is already tracked!");
        }
        gw localgw = new gw(paramea, paramInt1, paramInt2, paramBoolean);
        this.a.add(localgw);
        this.b.a(paramea.g, localgw);
        localgw.b(this.c.e.d);
    }

    public void b(ea paramea) {
        if ((paramea instanceof et)) {
            et localObject = (et) paramea;
            for (gw localgw : this.a) {
                localgw.a((et) localObject);
            }
        }
        Object localObject = this.b.d(paramea.g);
        if (localObject != null) {
            this.a.remove(localObject);
            ((gw) localObject).a();
        }
    }

    public void a() {
        ArrayList localArrayList = new ArrayList();
        for (Iterator localIterator1 = this.a.iterator(); localIterator1.hasNext();) {
            gw localObject = (gw) localIterator1.next();
            ((gw) localObject).a(this.c.e.d);
            if ((((gw) localObject).p) && ((((gw) localObject).a instanceof et))) {
                localArrayList.add(((gw) localObject).a);
            }
        }
        Object localObject;
        for (int i = 0; i < localArrayList.size(); i++) {
            localObject = localArrayList.get(i);
            for (gw localgw : this.a) {
                if (localgw.a != localObject) {
                    localgw.b((et) localObject);
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
    public synchronized static void add(Runnable r) {
        add(r, 0L);
    }

    public void a(ea paramea, it paramit) {
        gw localgw = (gw) this.b.a(paramea.g);
        if (localgw != null) {
            localgw.a(paramit);
        }
    }

    public void b(ea paramea, it paramit) {
        gw localgw = (gw) this.b.a(paramea.g);
        if (localgw != null) {
            localgw.b(paramit);
        }
    }

    public void a(et paramet) {
        for (gw localgw : this.a) {
            localgw.c(paramet);
        }
    }
}
