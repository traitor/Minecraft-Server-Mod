
import java.util.HashMap;
import java.util.Map;

public class ay {

    private static Map e = new HashMap();
    private static Map f = new HashMap();
    public eo a;
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
        // hMod: Allow visibility of 'complex blocks'?? 
        // Hacky... but it works at least.
        for (Player player : etc.getServer().getPlayerList()) {
            ay localay = this;
            ComplexBlock block = null;
            if (localay instanceof ia) {
                block = new Chest((ia) localay);
            } else if (localay instanceof dt) {
                block = new Furnace((dt) localay);
            } else if (localay instanceof jl) {
                block = new Sign((jl) localay);
            } else if (localay instanceof cf) {
                block = new MobSpawner((cf) localay);
            }
            if (block != null) {
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMPLEX_BLOCK_SEND, new Object[]{player.getUser(), block})
                        && player.canBuild()) {
                    player.getUser().a.b.a(new jf(this.b, this.c, this.d, this));
                } else {
                    ay toSend = null;
                    if (localay instanceof ia) {
                        toSend = new ia();
                    } else if (localay instanceof dt) {
                        toSend = new dt();
                    } else if (localay instanceof jl) {
                        toSend = new jl();
                    } else if (localay instanceof cf) {
                        toSend = new cf();
                    }
                    toSend.b = b;
                    toSend.c = c;
                    toSend.d = d;
                    player.getUser().a.b.a(new jf(this.b, this.c, this.d, toSend));
                }
            } else {
                player.getUser().a.b.a(new jf(this.b, this.c, this.d, this));
            }
        }
    }

    static {
        a(dt.class, "Furnace");
        a(ia.class, "Chest");
        a(jl.class, "Sign");
        a(cf.class, "MobSpawner");
    }
}
