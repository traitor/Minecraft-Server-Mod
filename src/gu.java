import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import net.minecraft.server.MinecraftServer;

public class gu {
    private Set<gv> a = new HashSet<gv>();
    // hMod: reflection to get around 'if'
    private Object b;
    private Method if_aI, if_aIO, if_bI, if_dI;

    private MinecraftServer c;
    private int d;

    // hMod: New fields to store the threads in.
    private static Object synchronizeObject = new Object();
    private static final Vector<Runnable> eventQueue = new Vector<Runnable>();

    public gu(MinecraftServer paramMinecraftServer) {
        try {
            Class<?> reallyIf = Class.forName("if");
            Constructor<?> ct = reallyIf.getConstructor();
            b = ct.newInstance();

            if_aI = reallyIf.getMethod("a", Integer.TYPE);
            if_aI.setAccessible(true);

            Class<?> aParamTypes[] = {Integer.TYPE, Object.class};
            if_aIO = reallyIf.getMethod("a", aParamTypes);
            if_aIO.setAccessible(true);

            if_bI = reallyIf.getMethod("b", Integer.TYPE);
            if_bI.setAccessible(true);

            if_dI = reallyIf.getMethod("d", Integer.TYPE);
            if_dI.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        c = paramMinecraftServer;
        d = paramMinecraftServer.f.a();
    }

    public void a(dy paramdy) {
        es locales;
        if ((paramdy instanceof es)) {
            a(paramdy, 512, 2);
            locales = (es) paramdy;
            for (gv localgv : a) {
                if (localgv.a != locales) {
                    localgv.a(locales);
                }
            }
        } else if ((paramdy instanceof kf)) {
            a(paramdy, 64, 5, true);
        } else if ((paramdy instanceof dx)) {
            a(paramdy, 64, 5, true);
        } else if ((paramdy instanceof bg)) {
            a(paramdy, 64, 5, true);
        } else if ((paramdy instanceof gk)) {
            a(paramdy, 64, 20, true);
        } else if ((paramdy instanceof jn)) {
            a(paramdy, 160, 5, true);
        } else if ((paramdy instanceof fl)) {
            a(paramdy, 160, 5, true);
        } else if ((paramdy instanceof af)) {
            a(paramdy, 160, 3);
        } else if ((paramdy instanceof cj)) {
            a(paramdy, 160, 10, true);
        }
    }

    public void a(dy paramdy, int paramInt1, int paramInt2) {
        a(paramdy, paramInt1, paramInt2, false);
    }

    public void a(dy paramdy, int paramInt1, int paramInt2, boolean paramBoolean) {
        if (paramInt1 > d) {
            paramInt1 = d;
        }
        try {
            if (((Boolean) if_bI.invoke(b, paramdy.g))) {
                throw new IllegalStateException("Entity is already tracked!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        gv localgv = new gv(paramdy, paramInt1, paramInt2, paramBoolean);
        a.add(localgv);
        try {
            if_aIO.invoke(b, paramdy.g, localgv);
        } catch (Exception e) {
            e.printStackTrace();
        }
        localgv.b(c.e.d);
    }

    public void b(dy paramdy) {
        gv localgv = null;
        try {
            localgv = (gv) if_dI.invoke(b, paramdy.g);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (localgv != null) {
            a.remove(localgv);
            localgv.a();
        }
    }

    public void a() {
        ArrayList localArrayList = new ArrayList();
        for (Iterator localIterator1 = a.iterator(); localIterator1.hasNext();) {
            gv localObject = (gv) localIterator1.next();
            (localObject).a(c.e.d);
            if (((localObject).p) && (((localObject).a instanceof es))) {
                localArrayList.add((localObject).a);
            }
        }
        Object localObject;
        for (int i = 0; i < localArrayList.size(); i++) {
            localObject = localArrayList.get(i);
            for (gv localgv : a) {
                if (localgv.a != localObject) {
                    localgv.a((es) localObject);
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

    public void a(dy paramdy, is paramis) {
        gv localgv = null;
        try {
            localgv = (gv) if_aI.invoke(b, paramdy.g);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (localgv != null) {
            localgv.a(paramis);
        }
    }

    public void b(dy paramdy, is paramis) {
        gv localgv = null;
        try {
            localgv = (gv) if_aI.invoke(b, paramdy.g);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (localgv != null) {
            localgv.b(paramis);
        }
    }

    public void a(es parames) {
        for (gv localgv : a) {
            localgv.b(parames);
        }
    }
}
