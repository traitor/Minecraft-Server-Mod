/* Contains a list of packet handlers */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class hp {

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

    public static hp a(int paramInt) {
        try {
            Class localClass = (Class) a.get(Integer.valueOf(paramInt));
            if (localClass == null) {
                return null;
            }
            return (hp) localClass.newInstance();
        } catch (Exception localException) {
            localException.printStackTrace();
            System.out.println("Skipping packet with id " + paramInt);
        }
        return null;
    }

    public final int b() {
        return ((Integer) b.get(getClass())).intValue();
    }

    public static hp b(DataInputStream paramDataInputStream) throws IOException {
        int i = paramDataInputStream.read();
        if (i == -1) {
            return null;
        }

        hp localhp = a(i);
        /*if (i != 10)
        System.out.println("ID " + i);*/
        if (localhp == null) {
            throw new IOException("Bad packet id " + i);
        }
        localhp.a(paramDataInputStream);
        return localhp;
    }

    public static void a(hp paramhp, DataOutputStream paramDataOutputStream) {
        try {
            paramDataOutputStream.write(paramhp.b());
        } catch (IOException ex) {
        }
        paramhp.a(paramDataOutputStream);
    }

    public abstract void a(DataInputStream paramDataInputStream);

    public abstract void a(DataOutputStream paramDataOutputStream);

    public abstract void a(ej paramej);

    public abstract int a();

    static {
        a(0, iz.class); //Keep alive
        a(1, z.class); //Login
        a(2, e.class); //Handshake
        a(3, ba.class); //Chat
        a(4, fl.class); //Time
        a(5, r.class); //Inventory
        a(6, cb.class); //?

        a(10, gf.class); //'Flying'
        a(11, aa.class); //Player position (anim related)
        a(12, fx.class); //Player look direction
        a(13, dq.class); //Move + look
        a(14, hd.class); //Block dig
        a(15, fe.class); //Place block
        a(16, fv.class); //Switch item in hands
        a(17, en.class); //Add to inventory
        a(18, o.class); //Arm animation

        a(20, c.class); //Player spawn
        a(21, k.class); //Pickup spawn
        a(22, ce.class); //Collect item
        a(23, dl.class); //Unk.
        a(24, gv.class); //Mob spawn
        a(29, ct.class); //Destroy entity

        a(30, ex.class); //Entity
        a(31, dr.class); //Relative entity move
        a(32, cx.class); //Entity look
        a(33, bg.class); //Entity look + move
        a(34, cf.class); //Entity teleport

        a(50, da.class); //Pre-chunk
        a(51, cz.class); //Map chunk
        a(52, hh.class); //Multi-block change
        a(53, et.class); //Block change
        a(59, ib.class);

        a(255, io.class); //Kick/disconnect
    }
}
