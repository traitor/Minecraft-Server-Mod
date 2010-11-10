/* Contains a list of packet handlers */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class im {

    private static Map a = new HashMap();
    private static Map b = new HashMap();
    public boolean j = false;

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

    public static im a(int paramInt) {
        try {
            Class localClass = (Class) a.get(Integer.valueOf(paramInt));
            if (localClass == null) {
                return null;
            }
            return (im) localClass.newInstance();
        } catch (Exception localException) {
            localException.printStackTrace();
            System.out.println("Skipping packet with id " + paramInt);
        }
        return null;
    }

    public final int b() {
        return ((Integer) b.get(getClass())).intValue();
    }

    public static im b(DataInputStream paramDataInputStream) throws IOException {
        int i = paramDataInputStream.read();
        if (i == -1) {
            return null;
        }

        im localim = a(i);
        if (localim == null) {
            throw new IOException("Bad packet id " + i);
        }
        localim.a(paramDataInputStream);
        return localim;
    }

    public static void a(im paramim, DataOutputStream paramDataOutputStream) {
        try {
            paramDataOutputStream.write(paramim.b());
        } catch (IOException ex) {
            Logger.getLogger(im.class.getName()).log(Level.SEVERE, null, ex);
        }
        paramim.a(paramDataOutputStream);
    }

    public abstract void a(DataInputStream paramDataInputStream);

    public abstract void a(DataOutputStream paramDataOutputStream);

    public abstract void a(ex paramex);

    public abstract int a();

    static {
        a(0, kc.class);
        a(1, ab.class);
        a(2, e.class);
        a(3, be.class);
        a(4, gd.class);
        a(5, s.class);
        a(6, cm.class);

        a(10, gx.class);
        a(11, ac.class);
        a(12, go.class);
        a(13, ec.class);
        a(14, hx.class);
        a(15, fv.class);
        a(16, gn.class);
        a(17, fc.class);
        a(18, p.class);

        a(20, c.class);
        a(21, k.class);
        a(22, cp.class);
        a(23, dx.class);
        a(24, ho.class);
        a(29, de.class);

        a(30, fo.class);
        a(31, ee.class);
        a(32, di.class);
        a(33, bn.class);
        a(34, cq.class);

        a(50, dm.class);
        a(51, dk.class);
        a(52, ic.class);
        a(53, fi.class);
        a(59, ja.class);

        a(255, jp.class);
    }
}
