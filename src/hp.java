
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class hp {

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

    public static ea a(String paramString, eq parameq) {
        ea localea = null;
        try {
            Class localClass = (Class) a.get(paramString);
            if (localClass != null) {
                localea = (ea) localClass.getConstructor(new Class[]{eq.class}).newInstance(new Object[]{parameq});
            }
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return localea;
    }

    public static ea a(v paramv, eq parameq) {
        ea localea = null;
        try {
            Class localClass = (Class) a.get(paramv.h("id"));
            if (localClass != null) {
                localea = (ea) localClass.getConstructor(new Class[]{eq.class}).newInstance(new Object[]{parameq});
            }
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        if (localea != null) {
            localea.e(paramv);
        } else {
            System.out.println("Skipping Entity with id " + paramv.h("id"));
        }
        return localea;
    }

    public static int a(ea paramea) {
        return ((Integer) d.get(paramea.getClass())).intValue();
    }

    public static String b(ea paramea) {
        return (String) b.get(paramea.getClass());
    }

    // hMod: Let us do a name->class lookup for mob spawning
    public static Class getEntity(String name) {
        return (Class)a.get(name);
    }

    static {
        a(dy.class, "Arrow", 10);
        a(bg.class, "Snowball", 11);
        a(gl.class, "Item", 1);
        a(ch.class, "Painting", 9);

        a(ka.class, "Mob", 48);
        a(gd.class, "Monster", 49);

        a(fo.class, "Creeper", 50);
        a(fd.class, "Skeleton", 51);
        a(bv.class, "Spider", 52);
        a(t.class, "Giant", 53);
        a(gk.class, "Zombie", 54);
        a(gf.class, "Slime", 55);
        a(ba.class, "Ghast", 56);
        a(ja.class, "PigZombie", 57);

        a(hu.class, "Pig", 90);
        a(cv.class, "Sheep", 91);
        a(bf.class, "Cow", 92);
        a(ii.class, "Chicken", 93);

        a(cj.class, "PrimedTnt", 20);
        a(id.class, "FallingSand", 21);

        a(jo.class, "Minecart", 40);
        a(fm.class, "Boat", 41);
    }
}
