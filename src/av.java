
import java.util.HashMap;
import java.util.Map;

public class av {

    private static Map e = new HashMap();
    private static Map f = new HashMap();
    public el a;
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

    public void a(t paramt) {
        this.b = paramt.d("x");
        this.c = paramt.d("y");
        this.d = paramt.d("z");
    }

    public void b(t paramt) {
        String str = (String) f.get(getClass());
        if (str == null) {
            throw new RuntimeException(getClass() + " is missing a mapping! This is a bug!");
        }
        paramt.a("id", str);
        paramt.a("x", this.b);
        paramt.a("y", this.c);
        paramt.a("z", this.d);
    }

    public void b() {
    }

    public static av c(t paramt) {
        av localav = null;
        try {
            Class localClass = (Class) e.get(paramt.h("id"));
            if (localClass != null) {
                localav = (av) localClass.newInstance();
            }
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        if (localav != null) {
            localav.a(paramt);
        } else {
            System.out.println("Skipping TileEntity with id " + paramt.h("id"));
        }
        return localav;
    }

    public void c() {
        /*this.a.b(this.b, this.c, this.d, this);*/
        //Hacky... but it works at least.
        for (Player player : etc.getServer().getPlayerList()) {
            av localav = this;
            ComplexBlock block = null;
            if (localav instanceof hv) {
                block = new Chest((hv) localav);
            } else if (localav instanceof dr) {
                block = new Furnace((dr) localav);
            } else if (localav instanceof jg) {
                block = new Sign((jg) localav);
            }
            if (block != null) {
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMPLEX_BLOCK_SEND, new Object[]{player.getUser(), block})) {
                    player.getUser().a.b.a(new ja(this.b, this.c, this.d, this));
                } else {
                    av toSend = null;
                    if (localav instanceof hv) {
                        toSend = new hv();
                    } else if (localav instanceof dr) {
                        toSend = new dr();
                    } else if (localav instanceof jg) {
                        toSend = new jg();
                    }
                    toSend.b = b;
                    toSend.c = c;
                    toSend.d = d;
                    player.getUser().a.b.a(new ja(this.b, this.c, this.d, toSend));
                }
            } else {
                player.getUser().a.b.a(new ja(this.b, this.c, this.d, this));
            }
        }
    }

    static {
        a(dr.class, "Furnace");
        a(hv.class, "Chest");
        a(jg.class, "Sign");
        a(cd.class, "MobSpawner");
    }
}
