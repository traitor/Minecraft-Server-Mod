
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import net.minecraft.server.MinecraftServer;

public class gt {

    private Set<gu> a = new HashSet<gu>();
    private ie b = new ie();
    private MinecraftServer c;
    private int d;
    // hMod: New fields to store the threads in.
    private static Object synchronizeObject = new Object();
    private static final Vector<Runnable> eventQueue = new Vector<Runnable>();

    public gt(MinecraftServer paramMinecraftServer) {
        this.c = paramMinecraftServer;
        this.d = paramMinecraftServer.f.a();
    }

    public void a(dx paramdx) {
        er localer;
        if ((paramdx instanceof er)) {
            a(paramdx, 512, 2);
            localer = (er) paramdx;
            for (gu localgu : this.a) {
                if (localgu.a != localer) {
                    localgu.a(localer);
                }
            }
        } else if ((paramdx instanceof ke)) {
            a(paramdx, 64, 5, true);
        } else if ((paramdx instanceof gj)) {
            a(paramdx, 64, 20, true);
        } else if ((paramdx instanceof jm)) {
            a(paramdx, 160, 5, true);
        } else if ((paramdx instanceof fk)) {
            a(paramdx, 160, 5, true);
        } else if ((paramdx instanceof af)) {
            a(paramdx, 160, 3);
        } else if ((paramdx instanceof cj)) {
            a(paramdx, 160, 10, true);
        }
    }

    public void a(dx paramdx, int paramInt1, int paramInt2) {
        a(paramdx, paramInt1, paramInt2, false);
    }

    public void a(dx paramdx, int paramInt1, int paramInt2, boolean paramBoolean) {
        if (paramInt1 > this.d) {
            paramInt1 = this.d;
        }
        if (this.b.b(paramdx.g)) {
            throw new IllegalStateException("Entity is already tracked!");
        }
        gu localgu = new gu(paramdx, paramInt1, paramInt2, paramBoolean);
        this.a.add(localgu);
        this.b.a(paramdx.g, localgu);
        localgu.b(this.c.e.d);
    }

    public void b(dx paramdx) {
        gu localgu = (gu) this.b.d(paramdx.g);
        if (localgu != null) {
            this.a.remove(localgu);
            localgu.a();
        }
    }

    public void a() {
        ArrayList localArrayList = new ArrayList();
        Object localObject;
        for (Iterator localIterator1 = this.a.iterator(); localIterator1.hasNext();) {
            localObject = localIterator1.next();
            ((gu) localObject).a(this.c.e.d);
            if ((((gu) localObject).o) && ((((gu) localObject).a instanceof er))) {
                localArrayList.add(((gu) localObject).a);
            }
        }
        for (int i = 0; i < localArrayList.size(); i++) {
            localObject = localArrayList.get(i);
            for (gu localgu : this.a) {
                if (localgu.a != localObject) {
                    localgu.a((er) localObject);
                }
            }
        }
        // hMod: Execute runnables contained in eventQueue.
        synchronized (synchronizeObject) {
            for (Iterator<Runnable> it = eventQueue.iterator(); it.hasNext();) {
                it.next().run();
                it.remove();
            }
        }
    }

    // hMod: Allow adding of items to the queue
    public synchronized static void add(Runnable r) {
        synchronized (synchronizeObject) {
            eventQueue.add(r);
        }
    }

    public void a(dx paramdx, ir paramir) {
        gu localgu = (gu) this.b.a(paramdx.g);
        if (localgu != null) {
            localgu.a(paramir);
        }
    }

    public void a(er paramer) {
        for (gu localgu : this.a) {
            localgu.b(paramer);
        }
    }
}
