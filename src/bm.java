
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class bm {

    private static Map e = new HashMap();
    private static Map f = new HashMap();
    public fv a;
    public int b;
    public int c;
    public int d;

    private static void a(Class paramClass, String paramString) {
        if (f.containsKey(paramString)) {
            throw new IllegalArgumentException("Duplicate id: " + paramString);
        }
        e.put(paramString, paramClass);
        f.put(paramClass, paramString);
    }

    public void a(ah paramah) {
        b = paramah.d("x");
        c = paramah.d("y");
        d = paramah.d("z");
    }

    public void b(ah paramah) {
        String str = (String) f.get(getClass());
        if (str == null) {
            throw new RuntimeException(getClass() + " is missing a mapping! This is a bug!");
        }
        paramah.a("id", str);
        paramah.a("x", b);
        paramah.a("y", c);
        paramah.a("z", d);
    }

    public void f() {
    }

    public static bm c(ah paramah) {
        bm localbm = null;
        try {
            Class localClass = (Class) e.get(paramah.h("id"));
            if (localClass != null) {
                localbm = (bm) localClass.newInstance();
            }
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        if (localbm != null) {
            localbm.a(paramah);
        } else {
            System.out.println("Skipping TileEntity with id " + paramah.h("id"));
        }
        return localbm;
    }

    public void d() {
        if (a != null) {
            a.b(b, c, d, this);
        }
    }

    public kx g() {
        return null;
    }

    static {
        a(ez.class, "Furnace");
        a(kc.class, "Chest");
        a(bf.class, "Trap");
        a(lv.class, "Sign");
        a(db.class, "MobSpawner");
        a(gl.class, "Music");
    }
}
