import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class OTileEntity
{
  private static Map a = new HashMap();
  private static Map b = new HashMap();
  public OWorld d;
  public int e;
  public int f;
  public int g;

  private static void a(Class paramClass, String paramString)
  {
    if (b.containsKey(paramString)) throw new IllegalArgumentException("Duplicate id: " + paramString);
    a.put(paramString, paramClass);
    b.put(paramClass, paramString);
  }

  public void a(ONBTTagCompound paramONBTTagCompound)
  {
    e = paramONBTTagCompound.e("x");
    f = paramONBTTagCompound.e("y");
    g = paramONBTTagCompound.e("z");
  }

  public void b(ONBTTagCompound paramONBTTagCompound) {
    String str = (String)b.get(getClass());
    if (str == null) {
      throw new RuntimeException(getClass() + " is missing a mapping! This is a bug!");
    }
    paramONBTTagCompound.a("id", str);
    paramONBTTagCompound.a("x", e);
    paramONBTTagCompound.a("y", f);
    paramONBTTagCompound.a("z", g);
  }

  public void i_() {
  }

  public static OTileEntity c(ONBTTagCompound paramONBTTagCompound) {
    OTileEntity localOTileEntity = null;
    try {
      Class localClass = (Class)a.get(paramONBTTagCompound.i("id"));
      if (localClass != null) localOTileEntity = (OTileEntity)localClass.newInstance(); 
    }
    catch (Exception localException) {
      localException.printStackTrace();
    }
    if (localOTileEntity != null)
      localOTileEntity.a(paramONBTTagCompound);
    else {
      System.out.println("Skipping TileEntity with id " + paramONBTTagCompound.i("id"));
    }
    return localOTileEntity;
  }

  public void h()
  {
    if (d != null)
      d.b(e, f, g, this);
  }

  public OPacket e()
  {
    return null;
  }

  static
  {
    a(OTileEntityFurnace.class, "Furnace");
    a(OTileEntityChest.class, "Chest");
    a(OTileEntityDispenser.class, "Trap");
    a(OTileEntitySign.class, "Sign");
    a(OTileEntityMobSpawner.class, "MobSpawner");
    a(OTileEntityNote.class, "Music");
  }
}