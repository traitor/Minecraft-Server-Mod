
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class ay {

    private static Map e = new HashMap();
    private static Map f = new HashMap();
    public em a;
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

    public void a(v paramv) {
        this.b = paramv.d("x");
        this.c = paramv.d("y");
        this.d = paramv.d("z");
    }

    public void b(v paramv) {
        String str = (String) f.get(getClass());
        if (str == null) {
            throw new RuntimeException(getClass() + " is missing a mapping! This is a bug!");
        }
        paramv.a("id", str);
        paramv.a("x", this.b);
        paramv.a("y", this.c);
        paramv.a("z", this.d);
    }

    public void b() {
    }

    public static ay c(v paramv) {
        ay localay = null;
        try {
            Class localClass = (Class) e.get(paramv.h("id"));
            if (localClass != null) {
                localay = (ay) localClass.newInstance();
            }
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        if (localay != null) {
            localay.a(paramv);
        } else {
            System.out.println("Skipping TileEntity with id " + paramv.h("id"));
        }
        return localay;
    }

    public void c() {
        /* this.a.b(this.b, this.c, this.d, this); */
        // Hacky... but it works at least.
        for (Player player : etc.getServer().getPlayerList()) {
            ay localay = this;
            ComplexBlock block = null;
            if (localay instanceof hx) {
                block = new Chest((hx) localay);
            } else if (localay instanceof ds) {
                block = new Furnace((ds) localay);
            } else if (localay instanceof ji) {
                block = new Sign((ji) localay);
            }
            if (block != null) {
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMPLEX_BLOCK_SEND, new Object[]{player.getUser(), block})) {
                    player.getUser().a.b.a(new jc(this.b, this.c, this.d, this));
                } else {
                    ay toSend = null;
                    if (localay instanceof hx) {
                        toSend = new hx();
                    } else if (localay instanceof ds) {
                        toSend = new ds();
                    } else if (localay instanceof ji) {
                        toSend = new ji();
                    }
                    toSend.b = b;
                    toSend.c = c;
                    toSend.d = d;
                    player.getUser().a.b.a(new jc(this.b, this.c, this.d, toSend));
                }
            } else {
                player.getUser().a.b.a(new jc(this.b, this.c, this.d, this));
            }
        }
    }

    static {
        a(ds.class, "Furnace");
        a(hx.class, "Chest");
        a(ji.class, "Sign");
        a(ce.class, "MobSpawner");
    }
}
