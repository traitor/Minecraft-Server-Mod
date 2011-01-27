import java.util.HashMap;
import java.util.Map;

public class OEntityList {

    private static Map<String, Class<?>>  a = new HashMap<String, Class<?>>();
    private static Map<Class<?>, String>  b = new HashMap<Class<?>, String>();
    private static Map<Integer, Class<?>> c = new HashMap<Integer, Class<?>>();
    private static Map<Class<?>, Integer> d = new HashMap<Class<?>, Integer>();

    private static void a(Class<?> paramClass, String paramString, int paramInt) {
        a.put(paramString, paramClass);
        b.put(paramClass, paramString);
        c.put(Integer.valueOf(paramInt), paramClass);
        d.put(paramClass, Integer.valueOf(paramInt));
    }

    public static OEntity a(String paramString, OWorld paramOWorld) {
        OEntity localOEntity = null;
        try {
            Class<?> localClass = a.get(paramString);
            if (localClass != null)
                localOEntity = (OEntity) localClass.getConstructor(new Class[] { OWorld.class }).newInstance(new Object[] { paramOWorld });
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return localOEntity;
    }

    public static OEntity a(ONBTTagCompound paramONBTTagCompound, OWorld paramOWorld) {
        OEntity localOEntity = null;
        try {
            Class<?> localClass = a.get(paramONBTTagCompound.h("id"));
            if (localClass != null)
                localOEntity = (OEntity) localClass.getConstructor(new Class[] { OWorld.class }).newInstance(new Object[] { paramOWorld });
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        if (localOEntity != null)
            localOEntity.e(paramONBTTagCompound);
        else
            System.out.println("Skipping Entity with id " + paramONBTTagCompound.h("id"));
        return localOEntity;
    }

    public static int a(OEntity paramOEntity) {
        return d.get(paramOEntity.getClass()).intValue();
    }

    public static String b(OEntity paramOEntity) {
        return b.get(paramOEntity.getClass());
    }

    // hMod: Let us do a name->class lookup for mob spawning
    public static Class<?> getEntity(String name) {
        return a.get(name);
    }

    static {
        a(OEntityArrow.class, "Arrow", 10);
        a(OEntitySnowball.class, "Snowball", 11);
        a(OEntityItem.class, "Item", 1);
        a(OEntityPainting.class, "Painting", 9);

        a(OEntityLiving.class, "Mob", 48);
        a(OEntityMobs.class, "Monster", 49);

        a(OEntityCreeper.class, "Creeper", 50);
        a(OEntitySkeleton.class, "Skeleton", 51);
        a(OEntitySpider.class, "Spider", 52);
        a(OEntityZombieSimple.class, "Giant", 53);
        a(OEntityZombie.class, "Zombie", 54);
        a(OEntitySlime.class, "Slime", 55);
        a(OEntityGhast.class, "Ghast", 56);
        a(OEntityPigZombie.class, "PigZombie", 57);

        a(OEntityPig.class, "Pig", 90);
        a(OEntitySheep.class, "Sheep", 91);
        a(OEntityCow.class, "Cow", 92);
        a(OEntityChicken.class, "Chicken", 93);
        a(OEntitySquid.class, "Squid", 94);

        a(OEntityTNTPrimed.class, "PrimedTnt", 20);
        a(OEntityFallingSand.class, "FallingSand", 21);

        a(OEntityMinecart.class, "Minecart", 40);
        a(OEntityBoat.class, "Boat", 41);
    }
}
