import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ir {
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

    public static ir a(int paramInt) {
        try {
            Class localClass = (Class) a.get(Integer.valueOf(paramInt));
            if (localClass == null) {
                return null;
            }
            return (ir) localClass.newInstance();
        } catch (Exception localException) {
            localException.printStackTrace();
            System.out.println("Skipping packet with id " + paramInt);
        }
        return null;
    }

    public final int b() {
        return ((Integer) b.get(getClass())).intValue();
    }

    public static ir b(DataInputStream paramDataInputStream) throws IOException {
        int i = paramDataInputStream.read();
        if (i == -1) {
            return null;
        }

        ir localir = a(i);
        if (localir == null) {
            throw new IOException("Bad packet id " + i);
        }
        localir.a(paramDataInputStream);
        return localir;
    }

    public static void a(ir paramir, DataOutputStream paramDataOutputStream) {
        try {
            paramDataOutputStream.write(paramir.b());
        } catch (IOException e) {
            Logger.getLogger(ir.class.getName()).log(Level.SEVERE, null, e);
        }
        paramir.a(paramDataOutputStream);
    }

    public abstract void a(DataInputStream paramDataInputStream);

    public abstract void a(DataOutputStream paramDataOutputStream);

    public abstract void a(fa paramfa);

    public abstract int a();

    static {
        a(0, kh.class); // Keep alive
        a(1, ad.class); // Login
        a(2, f.class); // Handshake
        a(3, bh.class); // Chat
        a(4, gh.class); // Time
        a(5, u.class); // Inventory
        a(6, co.class); // ?
        a(7, a.class); // ?
        a(8, ed.class); // ?
        a(9, az.class); // ?

        a(10, hb.class); // 'Flying'
        a(11, ae.class); // Player position (anim related)
        a(12, gs.class); // Player look direction
        a(13, ef.class); // Move + look
        a(14, ic.class); // Block dig
        a(15, fz.class); // Place block
        a(16, gr.class); // Switch item in hands
        a(17, ff.class); // Add to inventory
        a(18, q.class); // Arm animation

        a(20, d.class); // Player spawn
        a(21, l.class); // Pickup spawn
        a(22, cs.class); // Collect item
        a(23, ea.class); // Unk.
        a(24, ht.class); // Mob spawn
        a(28, fs.class); // ?
        a(29, dg.class); // Destroy entity

        a(30, fr.class); // Entity
        a(31, eh.class); // Relative entity move
        a(32, dk.class); // Entity look
        a(33, bq.class); // Entity look + move
        a(34, cr.class); // Entity teleport
        a(38, ho.class); // ??
        a(39, s.class); // ?? 

        try {
            a(50, Class.forName("do") ); // Pre-chunk
        } catch (ClassNotFoundException e) {
        } //do.class);
        a(51, dm.class); // Map chunk
        a(52, ih.class); // Multi-block change
        a(53, fl.class); // Block change
        a(59, jf.class); // complex block send/receive

        a(255, ju.class); // Kick/disconnect
    }
}