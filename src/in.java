
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class in {

    private static Map a = new HashMap();
    private static Map b = new HashMap();
    private static Map c = new HashMap();
    private static Map d = new HashMap();

    private static void a(Class paramClass, String paramString, int paramInt) {
        a.put(paramString, paramClass);
        b.put(paramClass, paramString);
        c.put(Integer.valueOf(paramInt), paramClass);
        d.put(paramClass, Integer.valueOf(paramInt));
    }

    public static ep a(String paramString, ff paramff) {
        ep localep = null;
        try {
            Class localClass = (Class) a.get(paramString);
            if (localClass != null) {
                localep = (ep) localClass.getConstructor(new Class[]{ff.class}).newInstance(new Object[]{paramff});
            }
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return localep;
    }

    public static ep a(ad paramad, ff paramff) {
        ep localep = null;
        try {
            Class localClass = (Class) a.get(paramad.h("id"));
            if (localClass != null) {
                localep = (ep) localClass.getConstructor(new Class[]{ff.class}).newInstance(new Object[]{paramff});
            }
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        if (localep != null) {
            localep.e(paramad);
        } else {
            System.out.println("Skipping Entity with id " + paramad.h("id"));
        }
        return localep;
    }

    public static int a(ep paramep) {
        return ((Integer) d.get(paramep.getClass())).intValue();
    }

    public static String b(ep paramep) {
        return (String) b.get(paramep.getClass());
    }

    // hMod: Let us do a name->class lookup for mob spawning
    public static Class getEntity(String name) {
        return (Class)a.get(name);
    }

    static {
        a(en.class, "Arrow", 10);
        a(bq.class, "Snowball", 11);
        a(hf.class, "Item", 1);
        a(ct.class, "Painting", 9);

        a(lc.class, "Mob", 48);
        a(gu.class, "Monster", 49);

        a(ge.class, "Creeper", 50);
        a(ft.class, "Skeleton", 51);
        a(ch.class, "Spider", 52);
        a(z.class, "Giant", 53);
        a(hd.class, "Zombie", 54);
        a(gx.class, "Slime", 55);
        a(bk.class, "Ghast", 56);
        a(kc.class, "PigZombie", 57);

        a(ir.class, "Pig", 90);
        a(dh.class, "Sheep", 91);
        a(bp.class, "Cow", 92);
        a(ji.class, "Chicken", 93);

        a(cx.class, "PrimedTnt", 20);
        a(jc.class, "FallingSand", 21);

        a(kq.class, "Minecart", 40);
        a(gc.class, "Boat", 41);
    }
}
