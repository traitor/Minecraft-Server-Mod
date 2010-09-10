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
        a(0, iz.class);
        a(1, z.class);
        a(2, e.class);
        a(3, ba.class);
        a(4, fl.class);
        a(5, r.class);
        a(6, cb.class);

        a(10, gf.class);
        a(11, aa.class);
        a(12, fx.class);
        a(13, dq.class);
        a(14, hd.class);
        a(15, fe.class);
        a(16, fv.class);
        a(17, en.class);
        a(18, o.class);

        a(20, c.class);
        a(21, k.class);
        a(22, ce.class);
        a(23, dl.class);
        a(24, gv.class);
        a(29, ct.class);

        a(30, ex.class);
        a(31, dr.class);
        a(32, cx.class);
        a(33, bg.class);
        a(34, cf.class);

        a(50, da.class);
        a(51, cz.class);
        a(52, hh.class);
        a(53, et.class);
        a(59, ib.class);

        a(255, io.class);

        /* PREVIOUSLY:
        a(0, iw.class); //Keep alive
        a(1, z.class); //Login
        a(2, e.class); //Handshake
        a(3, az.class); //Chat
        a(4, fj.class); //Time

        a(10, gd.class); //'Flying'
        a(11, aa.class); //Player position (Anim related)
        a(12, fv.class); //Player look direction
        a(13, _do.class); //Move + look
        a(14, hb.class); //Block dig
        a(15, fc.class); //Place block
        a(16, ft.class); //Switch item in hands
        a(17, el.class); //Add to inven.
        a(18, o.class); //Arm anim.

        a(20, c.class); //Player spawn
        a(21, k.class); //Pickup spawn
        a(22, ce.class); //Collect item
        a(23, dj.class); //Unk.
        a(24, gt.class); //Mob spawn.
        a(29, cq.class); //Destroy entity.

        a(30, ev.class); //Entity
        a(31, dp.class); //Relative entity move
        a(32, cv.class); //Entity look
        a(33, bf.class); //Entity move + look
        a(34, cd.class); //Entity teleport

        a(50, cy.class); //Pre-chunk
        a(51, cx.class); //Map chunk
        a(52, hf.class); //Multi block change
        a(53, er.class); //Block change

        a(255, il.class); //Kick/disconnect
         */
    }
}
