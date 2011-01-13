import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class jn
{
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

  public static fe a(String paramString, fv paramfv)
  {
    fe localfe = null;
    try {
      Class localClass = (Class)a.get(paramString);
      if (localClass != null) localfe = (fe)localClass.getConstructor(new Class[] { fv.class }).newInstance(new Object[] { paramfv });
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return localfe;
  }

  public static fe a(ah paramah, fv paramfv) {
    fe localfe = null;
    try {
      Class localClass = (Class)a.get(paramah.h("id"));
      if (localClass != null) localfe = (fe)localClass.getConstructor(new Class[] { fv.class }).newInstance(new Object[] { paramfv });
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    if (localfe != null)
      localfe.e(paramah);
    else {
      System.out.println("Skipping Entity with id " + paramah.h("id"));
    }
    return localfe;
  }

  public static int a(fe paramfe)
  {
    return ((Integer)d.get(paramfe.getClass())).intValue();
  }

  public static String b(fe paramfe) {
    return (String)b.get(paramfe.getClass());
  }

  // hMod: Let us do a name->class lookup for mob spawning
    public static Class getEntity(String name) {
        return (Class)a.get(name);
    }

  static
  {
    a(fc.class, "Arrow", 10);
    a(by.class, "Snowball", 11);
    a(ic.class, "Item", 1);
    a(dd.class, "Painting", 9);

    a(mj.class, "Mob", 48);
    a(hq.class, "Monster", 49);

    a(gy.class, "Creeper", 50);
    a(gj.class, "Skeleton", 51);
    a(cr.class, "Spider", 52);
    a(ad.class, "Giant", 53);
    a(ia.class, "Zombie", 54);
    a(ht.class, "Slime", 55);
    a(br.class, "Ghast", 56);
    a(lh.class, "PigZombie", 57);

    a(js.class, "Pig", 90);
    a(dv.class, "Sheep", 91);
    a(bx.class, "Cow", 92);
    a(kk.class, "Chicken", 93);
    a(le.class, "Squid", 94);

    a(dh.class, "PrimedTnt", 20);
    a(kd.class, "FallingSand", 21);

    a(lw.class, "Minecart", 40);
    a(gu.class, "Boat", 41);
  }
}