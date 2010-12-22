
import java.io.PrintStream;
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
        // hMod: Allow visibility of 'complex blocks'??
        // Hacky... but it works at least.
        for (Player player : etc.getServer().getPlayerList()) {
            bg localbg = this;
            ComplexBlock block = null;
            if (localbg instanceof jb) {
                block = new Chest((jb) localbg);
            } else if (localbg instanceof ek) {
                block = new Furnace((ek) localbg);
            } else if (localbg instanceof kp) {
                block = new Sign((kp) localbg);
            } else if (localbg instanceof cq) {
                block = new MobSpawner((cq) localbg);
            }
            if (block != null) {
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMPLEX_BLOCK_SEND, player, block) && player.canBuild()) {
                    this.a.b(this.b, this.c, this.d, this);
                } else {
                    bg toSend = null;
                    if (localbg instanceof jb) {
                        toSend = new jb();
                    } else if (localbg instanceof ek) {
                        toSend = new ek();
                    } else if (localbg instanceof kp) {
                        toSend = new kp();
                    } else if (localbg instanceof cq) {
                        toSend = new cq();
                    }
                    toSend.b = b;
                    toSend.c = c;
                    toSend.d = d;
                    this.a.b(this.b, this.c, this.d, toSend);
                }
            } else {
                this.a.b(this.b, this.c, this.d, this);
            }
        }
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
