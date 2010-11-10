import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public abstract class io
{
  private static Map a = new HashMap();
  private static Map b = new HashMap();

  public boolean j = false;

  static void a(int paramInt, Class paramClass)
  {
    if (a.containsKey(Integer.valueOf(paramInt))) throw new IllegalArgumentException("Duplicate packet id:" + paramInt);
    if (b.containsKey(paramClass)) throw new IllegalArgumentException("Duplicate packet class:" + paramClass);
    a.put(Integer.valueOf(paramInt), paramClass);
    b.put(paramClass, Integer.valueOf(paramInt));
  }

  public static io a(int paramInt)
  {
    try
    {
      Class localClass = (Class)a.get(Integer.valueOf(paramInt));
      if (localClass == null) return null;
      return (io)localClass.newInstance();
    } catch (Exception localException) {
      localException.printStackTrace();
      System.out.println("Skipping packet with id " + paramInt);
    }return null;
  }

  public final int b()
  {
    return ((Integer)b.get(getClass())).intValue();
  }

  public static io b(DataInputStream paramDataInputStream)
  {
    int i = paramDataInputStream.read();
    if (i == -1) return null;

    io localio = a(i);
    if (localio == null) throw new IOException("Bad packet id " + i);
    localio.a(paramDataInputStream);
    return localio;
  }

  public static void a(io paramio, DataOutputStream paramDataOutputStream) {
    paramDataOutputStream.write(paramio.b());
    paramio.a(paramDataOutputStream);
  }

  public abstract void a(DataInputStream paramDataInputStream);

  public abstract void a(DataOutputStream paramDataOutputStream);

  public abstract void a(ey paramey);

  public abstract int a();

  static
  {
    a(0, ke.class);
    a(1, ad.class);
    a(2, f.class);
    a(3, bg.class);
    a(4, gf.class);
    a(5, u.class);
    a(6, cn.class);
    a(7, a.class);

    a(10, gz.class);
    a(11, ae.class);
    a(12, gq.class);
    a(13, ed.class);
    a(14, hz.class);
    a(15, fx.class);
    a(16, gp.class);
    a(17, fd.class);
    a(18, q.class);

    a(20, d.class);
    a(21, l.class);
    a(22, cq.class);
    a(23, dz.class);
    a(24, hq.class);
    a(28, fq.class);
    a(29, df.class);

    a(30, fp.class);
    a(31, ef.class);
    a(32, dj.class);
    a(33, bp.class);
    a(34, cr.class);
    a(39, s.class);

    a(50, dn.class);
    a(51, dl.class);
    a(52, ie.class);
    a(53, fj.class);
    a(59, jc.class);

    a(255, jr.class);
  }
}