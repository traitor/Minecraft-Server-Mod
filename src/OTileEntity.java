import java.util.HashMap;
import java.util.Map;

public class OTileEntity {

    private static Map<String, Class> e = new HashMap<String, Class>();
    private static Map<Class, String> f = new HashMap<Class, String>();
    public OWorld                     a;
    public int                        b;
    public int                        c;
    public int                        d;

    private static void a(Class paramClass, String paramString) {
        if (e.containsKey(paramString))
            throw new IllegalArgumentException("Duplicate id: " + paramString);
        e.put(paramString, paramClass);
        f.put(paramClass, paramString);
    }

    public void a(ONBTTagCompound paramONBTTagCompound) {
        b = paramONBTTagCompound.d("x");
        c = paramONBTTagCompound.d("y");
        d = paramONBTTagCompound.d("z");
    }

    public void b(ONBTTagCompound paramONBTTagCompound) {
        String str = f.get(getClass());
        if (str == null)
            throw new RuntimeException(getClass() + " is missing a mapping! This is a bug!");
        paramONBTTagCompound.a("id", str);
        paramONBTTagCompound.a("x", b);
        paramONBTTagCompound.a("y", c);
        paramONBTTagCompound.a("z", d);
    }

    public void f() {
    }

    public static OTileEntity c(ONBTTagCompound paramONBTTagCompound) {
        OTileEntity localOTileEntity = null;
        try {
            Class localClass = e.get(paramONBTTagCompound.h("id"));
            if (localClass != null)
                localOTileEntity = (OTileEntity) localClass.newInstance();
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        if (localOTileEntity != null)
            localOTileEntity.a(paramONBTTagCompound);
        else
            System.out.println("Skipping TileEntity with id " + paramONBTTagCompound.h("id"));
        return localOTileEntity;
    }

    public void d() {
        if (a != null)
            a.b(b, c, d, this);
    }

    public OPacket g() {
        return null;
    }

    static {
        a(OTileEntityFurnace.class, "Furnace");
        a(OTileEntityChest.class, "Chest");
        a(OTileEntityDispenser.class, "Trap");
        a(OTileEntitySign.class, "Sign");
        a(OTileEntityMobSpawner.class, "MobSpawner");
        a(OTileEntityNote.class, "Music");
    }
}
