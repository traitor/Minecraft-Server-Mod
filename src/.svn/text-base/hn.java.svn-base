/* Contains a list of packet handlers */

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
    }
}
