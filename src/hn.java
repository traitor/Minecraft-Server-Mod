import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class hn {
    private static Map a = new HashMap();
    private static Map b = new HashMap();
    public boolean j;

    public hn() {
        this.j = false;
    }

    static void a(int paramInt, Class paramClass) {
        if (a.containsKey(Integer.valueOf(paramInt))) {
            throw new IllegalArgumentException("Duplicate packet id:" + paramInt);
        }
        if (b.containsKey(paramClass)) {
            throw new IllegalArgumentException("Duplicate packet class:" + paramClass);
        }
        a.put(Integer.valueOf(paramInt), paramClass);
        b.put(paramClass, Integer.valueOf(paramInt));
    }

    public static hn a(int paramInt) {
        try {
            Class localClass = (Class) a.get(Integer.valueOf(paramInt));
            if (localClass == null) {
                return null;
            }
            return (hn) localClass.newInstance();
        } catch (Exception localException) {
            localException.printStackTrace();
            System.out.println("Skipping packet with id " + paramInt);
        }
        return null;
    }

    public final int b() {
        return ((Integer) b.get(super.getClass())).intValue();
    }

    public static hn b(DataInputStream paramDataInputStream) throws IOException {
        int i = paramDataInputStream.read();
        if (i == -1) {
            return null;
        }

        hn localhn = a(i);
        if (localhn == null) {
            throw new IOException("Bad packet id " + i);
        }
        localhn.a(paramDataInputStream);
        return localhn;
    }

    public static void a(hn paramhn, DataOutputStream paramDataOutputStream) {
        try {
            paramDataOutputStream.write(paramhn.b());
        } catch (IOException e) {
        }
        paramhn.a(paramDataOutputStream);
    }

    public abstract void a(DataInputStream paramDataInputStream);

    public abstract void a(DataOutputStream paramDataOutputStream);

    public abstract void a(eh parameh);

    public abstract int a();

    static {
        a(0, iw.class);
        a(1, z.class);
        a(2, e.class);
        a(3, az.class);
        a(4, fj.class);

        a(10, gd.class);
        a(11, aa.class);
        a(12, fv.class);
        a(13, _do.class);
        a(14, hb.class);
        a(15, fc.class);
        a(16, ft.class);
        a(17, el.class);
        a(18, o.class);

        a(20, c.class);
        a(21, k.class);
        a(22, ce.class);
        a(23, dj.class);
        a(24, gt.class);
        a(29, cq.class);

        a(30, ev.class);
        a(31, dp.class);
        a(32, cv.class);
        a(33, bf.class);
        a(34, cd.class);

        a(50, cy.class);
        a(51, cx.class);
        a(52, hf.class);
        a(53, er.class);

        a(255, il.class);
    }
}
