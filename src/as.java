
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class as {

    private static Map e = new HashMap();
    private static Map f = new HashMap();
    public dy a;
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

    public void a(s params) {
        this.b = params.d("x");
        this.c = params.d("y");
        this.d = params.d("z");
    }

    public void b(s params) {
        String str = (String) f.get(getClass());
        if (str == null) {
            throw new RuntimeException(getClass() + " is missing a mapping! This is a bug!");
        }
        params.a("id", str);
        params.a("x", this.b);
        params.a("y", this.c);
        params.a("z", this.d);
    }

    public void b() {
    }

    public static as c(s params) {
        as localas = null;
        try {
            Class localClass = (Class) e.get(params.h("id"));
            if (localClass != null) {
                localas = (as) localClass.newInstance();
            }
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        if (localas != null) {
            localas.a(params);
        } else {
            System.out.println("Skipping TileEntity with id " + params.h("id"));
        }
        return localas;
    }

    public void c() {
        //Hacky... but it works at least.
        for (Player player : etc.getServer().getPlayerList()) {
            as localas = this;
            ComplexBlock block = null;
            if (localas instanceof hb)
                block = new Chest((hb)localas);
            else if (localas instanceof df)
                block = new Furnace((df)localas);
            else if (localas instanceof ig)
                block = new Sign((ig)localas);
            if (block != null) {
                if (!(Boolean)etc.getLoader().callHook(PluginLoader.Hook.COMPLEX_BLOCK_SEND, new Object[] { player.getUser(), block })) {
                    player.getUser().a.b.a(new ib(this.b, this.c, this.d, this));
                } else {
                    as toSend = null;
                    if (localas instanceof hb)
                        toSend = new hb();
                    else if (localas instanceof df)
                        toSend = new df();
                    else if (localas instanceof ig)
                        toSend = new ig();
                    toSend.b = b;
                    toSend.c = c;
                    toSend.d = d;
                    player.getUser().a.b.a(new ib(this.b, this.c, this.d, toSend));
                }
            } else
                player.getUser().a.b.a(new ib(this.b, this.c, this.d, this));
        }
    }

    static {
        a(df.class, "Furnace");
        a(hb.class, "Chest");
        a(ig.class, "Sign");
        a(bs.class, "MobSpawner");
    }
}
