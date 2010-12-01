import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class is {
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

    public static is a(int paramInt) {
        try {
            Class localClass = (Class) a.get(Integer.valueOf(paramInt));
            if (localClass == null) {
                return null;
            }
            return (is) localClass.newInstance();
        } catch (Exception localException) {
            localException.printStackTrace();
            System.out.println("Skipping packet with id " + paramInt);
        }
        return null;
    }

    public final int b() {
        return ((Integer) b.get(getClass())).intValue();
    }

    public static is b(DataInputStream paramDataInputStream) throws IOException {
        int i = paramDataInputStream.read();
        if (i == -1) {
            return null;
        }

        is localis = a(i);
        if (localis == null) {
            throw new IOException("Bad packet id " + i);
        }
        localis.a(paramDataInputStream);
        return localis;
    }

    public static void a(is paramis, DataOutputStream paramDataOutputStream) {
        try {
            paramDataOutputStream.write(paramis.b());
        } catch (IOException ex) {
            Logger.getLogger(is.class.getName()).log(Level.SEVERE, null, ex);
        }
        paramis.a(paramDataOutputStream);
    }

    public abstract void a(DataInputStream paramDataInputStream);

    public abstract void a(DataOutputStream paramDataOutputStream);

    public abstract void a(fb paramfb);

    public abstract int a();

    static {
        /**
         * Packet List.
         * 0 = Keep Alive
         * 1 = Login
         * 2 = Handshake
         * 3 = Chat
         * 4 = Time
         * 5 = Inventory
         * 6 = do respawn ?
         * 7 = ?
         * 8 = Receive Damage
         * 9 = do Die
         *
         * 10 = "Flying"
         * 11 = Player Change Position
         * 12 = Player Change Look direction
         * 13 = Player Change position and look direction
         * 14 = Block Dig
         * 15 = Place Block
         * 16 = Switch item in hands
         * 17 = Add to inventory
         * 18 = Arm animation
         *
         * 20 = Player spawn
         * 21 = Pickup spawn
         * 22 = collect item
         * 23 = ?
         * 24 = Mob spawn
         * 28 = ?
         * 29 = Destroy entity
         *
         * 30 = entity
         * 31 = relative entity move
         * 32 = Entity look
         * 33 = Entity look + move
         * 34 = Entity teleport
         * 38 = ?
         * 39 = ?
         *
         * 50 = Pre-Chunk
         * 51 = Map Chunk
         * 52 = Multi Block Change
         * 53 = Block Change
         * 59 = Complex block send/receive
         *
         * 60 = ? (Creeper related ? / sound related ?)
         *
         * 255 = Kick/Disconnect
         */
        a(0, ki.class);
        a(1, ad.class);
        a(2, f.class); 
        a(3, bh.class);
        a(4, gi.class);
        a(5, u.class);
        a(6, co.class);
        a(7, a.class);
        a(8, ee.class);
        a(9, az.class);

        a(10, hc.class);
        a(11, ae.class);
        a(12, gt.class);
        a(13, eg.class);
        a(14, id.class);
        a(15, ga.class);
        a(16, gs.class);
        a(17, fg.class);
        a(18, q.class);

        a(20, d.class);
        a(21, l.class);
        a(22, cs.class);
        a(23, eb.class);
        a(24, hu.class);
        a(28, ft.class);
        a(29, dh.class);

        a(30, fs.class);
        a(31, ei.class);
        a(32, dl.class);
        a(33, bq.class);
        a(34, cr.class);
        a(38, hp.class);
        a(39, s.class);

        a(50, dp.class);
        a(51, dn.class);
        a(52, ii.class);
        a(53, fm.class);
        a(59, jg.class);

        a(60, cy.class);

        a(255, jv.class);
    }
}
