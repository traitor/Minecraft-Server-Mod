
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class io {

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

    public static io a(int paramInt) {
        try {
            Class localClass = (Class) a.get(Integer.valueOf(paramInt));
            if (localClass == null) {
                return null;
            }
            return (io) localClass.newInstance();
        } catch (Exception localException) {
            localException.printStackTrace();
            System.out.println("Skipping packet with id " + paramInt);
        }
        return null;
    }

    public final int b() {
        return ((Integer) b.get(getClass())).intValue();
    }

    public static io b(DataInputStream paramDataInputStream) throws IOException {
        int i = paramDataInputStream.read();
        if (i == -1) {
            return null;
        }

        io localio = a(i);
        if (localio == null) {
            throw new IOException("Bad packet id " + i);
        }
        localio.a(paramDataInputStream);
        return localio;
    }

    public static void a(io paramio, DataOutputStream paramDataOutputStream) {
        try {
            paramDataOutputStream.write(paramio.b());
        } catch (IOException ex) {
            Logger.getLogger(io.class.getName()).log(Level.SEVERE, null, ex);
        }
        paramio.a(paramDataOutputStream);
    }

    public abstract void a(DataInputStream paramDataInputStream);

    public abstract void a(DataOutputStream paramDataOutputStream);

    public abstract void a(ey paramey);

    public abstract int a();

    static {
        a(0, ke.class); // Keep alive
        a(1, ad.class); // Login
        a(2, f.class); // Handshake
        a(3, bg.class); // Chat
        a(4, gf.class); // Time
        a(5, u.class); // Inventory
        a(6, cn.class); // ?
        a(7, a.class); // ?

        a(10, gz.class); // 'Flying'
        a(11, ae.class); // Player position (anim related)
        a(12, gq.class); // Player look direction
        a(13, ed.class); // Move + look
        a(14, hz.class); // Block dig
        a(15, fx.class); // Place block
        a(16, gp.class); // Switch item in hands
        a(17, fd.class); // Add to inventory
        a(18, q.class); // Arm animation

        a(20, d.class); // Player spawn
        a(21, l.class); // Pickup spawn
        a(22, cq.class); // Collect item
        a(23, dz.class); // Unk.
        a(24, hq.class); // Mob spawn
        a(28, fq.class); // ?
        a(29, df.class); // Destroy entity

        a(30, fp.class); // Entity
        a(31, ef.class); // Relative entity move
        a(32, dj.class); // Entity look
        a(33, bp.class); // Entity look + move
        a(34, cr.class); // Entity teleport
        a(39, s.class); // ?

        a(50, dn.class); // Pre-chunk
        a(51, dl.class); // Map chunk
        a(52, ie.class); // Multi-block change
        a(53, fj.class); // Block change
        a(59, jc.class); // ?

        a(255, jr.class); // Kick/disconnect
    }
}
