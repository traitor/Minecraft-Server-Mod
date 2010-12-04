import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class it {
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

    public static it a(int paramInt) {
        try {
            Class localClass = (Class) a.get(Integer.valueOf(paramInt));
            if (localClass == null) {
                return null;
            }
            return (it) localClass.newInstance();
        } catch (Exception localException) {
            localException.printStackTrace();
            System.out.println("Skipping packet with id " + paramInt);
        }
        return null;
    }

    public final int b() {
        return ((Integer) b.get(getClass())).intValue();
    }

    public static it b(DataInputStream paramDataInputStream) throws IOException {
        int i = paramDataInputStream.read();
        if (i == -1) {
            return null;
        }

        it localit = a(i);
        if (localit == null) {
            throw new IOException("Bad packet id " + i);
        }
        localit.a(paramDataInputStream);
        return localit;
    }

    public static void a(it paramit, DataOutputStream paramDataOutputStream) throws IOException  {
        paramDataOutputStream.write(paramit.b());
        paramit.a(paramDataOutputStream);
    }

    public abstract void a(DataInputStream paramDataInputStream);

    public abstract void a(DataOutputStream paramDataOutputStream);

    public abstract void a(fc paramfc);

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
        a(0, kj.class);
        a(1, ad.class);
        a(2, f.class);
        a(3, bh.class);
        a(4, gj.class);
        a(5, u.class);
        a(6, co.class);
        a(7, a.class);
        a(8, ef.class);
        a(9, az.class);

        a(10, hd.class);
        a(11, ae.class);
        a(12, gu.class);
        a(13, eh.class);
        a(14, ie.class);
        a(15, gb.class);
        a(16, gt.class);
        a(17, fh.class);
        a(18, q.class);

        a(20, d.class);
        a(21, l.class);
        a(22, cs.class);
        a(23, ec.class);
        a(24, hv.class);
        a(28, fu.class);
        a(29, di.class);

        a(30, ft.class);
        a(31, ej.class);
        a(32, dm.class);
        a(33, bq.class);
        a(34, cr.class);
        a(38, hq.class);
        a(39, s.class);

        a(50, dq.class);
        try {
            a(51, Class.forName("do"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        a(52, ij.class);
        a(53, fn.class);
        a(59, jh.class);

        a(60, cz.class);

        a(255, jw.class);
    }
}
