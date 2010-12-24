
import java.util.HashMap;
import java.util.Map;

public class bg {

    private static Map e = new HashMap();
    private static Map f = new HashMap();
    public ff a;
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

    public void a(ad paramad) {
        this.b = paramad.d("x");
        this.c = paramad.d("y");
        this.d = paramad.d("z");
    }

    public void b(ad paramad) {
        String str = (String) f.get(getClass());
        if (str == null) {
            throw new RuntimeException(getClass() + " is missing a mapping! This is a bug!");
        }
        paramad.a("id", str);
        paramad.a("x", this.b);
        paramad.a("y", this.c);
        paramad.a("z", this.d);
    }

    public void e() {
    }

    public static bg c(ad paramad) {
        bg localbg = null;
        try {
            Class localClass = (Class) e.get(paramad.h("id"));
            if (localClass != null) {
                localbg = (bg) localClass.newInstance();
            }
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        if (localbg != null) {
            localbg.a(paramad);
        } else {
            System.out.println("Skipping TileEntity with id " + paramad.h("id"));
        }
        return localbg;
    }

    public void d() {
        if(this.a == null) return;
        this.a.b(this.b, this.c, this.d, this);
    }

    public jv f() {
        return null;
    }

    static {
        a(ek.class, "Furnace");
        a(jb.class, "Chest");
        a(kp.class, "Sign");
        a(cq.class, "MobSpawner");
    }
}
