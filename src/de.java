import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class de extends is
{
  private boolean a = false;

  private static List b = new ArrayList();

  private boolean a(fv paramfv, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
    if (paramBoolean) b.add(new lf(paramInt1, paramInt2, paramInt3, paramfv.e));
    int i = 0;
    for (int j = 0; j < b.size(); j++) {
      lf locallf = (lf)b.get(j);
      if ((locallf.a == paramInt1) && (locallf.b == paramInt2) && (locallf.c == paramInt3)) {
        i++;
        if (i >= 8) {
          return true;
        }
      }
    }
    return false;
  }

  protected de(int paramInt1, int paramInt2, boolean paramBoolean) {
    super(paramInt1, paramInt2);
    a = paramBoolean;
    a(true);
  }

  public int b() {
    return 2;
  }

  public void e(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
    if (paramfv.b(paramInt1, paramInt2, paramInt3) == 0) super.e(paramfv, paramInt1, paramInt2, paramInt3);
    if (a) {
      paramfv.h(paramInt1, paramInt2 - 1, paramInt3, bi);
      paramfv.h(paramInt1, paramInt2 + 1, paramInt3, bi);
      paramfv.h(paramInt1 - 1, paramInt2, paramInt3, bi);
      paramfv.h(paramInt1 + 1, paramInt2, paramInt3, bi);
      paramfv.h(paramInt1, paramInt2, paramInt3 - 1, bi);
      paramfv.h(paramInt1, paramInt2, paramInt3 + 1, bi);
    }
  }

  public void b(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
    if (a) {
      paramfv.h(paramInt1, paramInt2 - 1, paramInt3, bi);
      paramfv.h(paramInt1, paramInt2 + 1, paramInt3, bi);
      paramfv.h(paramInt1 - 1, paramInt2, paramInt3, bi);
      paramfv.h(paramInt1 + 1, paramInt2, paramInt3, bi);
      paramfv.h(paramInt1, paramInt2, paramInt3 - 1, bi);
      paramfv.h(paramInt1, paramInt2, paramInt3 + 1, bi);
    }
  }

  public boolean b(la paramla, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (!a) return false;

    int i = paramla.b(paramInt1, paramInt2, paramInt3);

    if ((i == 5) && (paramInt4 == 1)) return false;
    if ((i == 3) && (paramInt4 == 3)) return false;
    if ((i == 4) && (paramInt4 == 2)) return false;
    if ((i == 1) && (paramInt4 == 5)) return false;
    return (i != 2) || (paramInt4 != 4);
  }

  private boolean g(fv paramfv, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramfv.b(paramInt1, paramInt2, paramInt3);

    if ((i == 5) && (paramfv.k(paramInt1, paramInt2 - 1, paramInt3, 0))) return true;
    if ((i == 3) && (paramfv.k(paramInt1, paramInt2, paramInt3 - 1, 2))) return true;
    if ((i == 4) && (paramfv.k(paramInt1, paramInt2, paramInt3 + 1, 3))) return true;
    if ((i == 1) && (paramfv.k(paramInt1 - 1, paramInt2, paramInt3, 4))) return true;
    return (i == 2) && (paramfv.k(paramInt1 + 1, paramInt2, paramInt3, 5));
  }

  public void a(fv paramfv, int paramInt1, int paramInt2, int paramInt3, Random paramRandom)
  {
    boolean bool = g(paramfv, paramInt1, paramInt2, paramInt3);

    while ((b.size() > 0) && (paramfv.e - ((lf)b.get(0)).d > 100L)) {
      b.remove(0);
    }

    if (a) {
      if (bool) {
        paramfv.b(paramInt1, paramInt2, paramInt3, hr.aP.bi, paramfv.b(paramInt1, paramInt2, paramInt3));

        // hMod: Allow redstone torches to provide power
                int current = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Object[]{new Block(this.bh, paramInt1, paramInt2, paramInt3), 1, 0});
                if (current == 0) {
                    
                
        if (a(paramfv, paramInt1, paramInt2, paramInt3, true)) {
          paramfv.a(paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, "random.fizz", 0.5F, 2.6F + (paramfv.l.nextFloat() - paramfv.l.nextFloat()) * 0.8F);
          for (int i = 0; i < 5; i++) {
            double d1 = paramInt1 + paramRandom.nextDouble() * 0.6D + 0.2D;
            double d2 = paramInt2 + paramRandom.nextDouble() * 0.6D + 0.2D;
            double d3 = paramInt3 + paramRandom.nextDouble() * 0.6D + 0.2D;

            paramfv.a("smoke", d1, d2, d3, 0.0D, 0.0D, 0.0D);
          }
        }
      }}
    }
    else if ((!bool) && 
      (!a(paramfv, paramInt1, paramInt2, paramInt3, false))){
        // hMod: Allow redstone torches to provide power
            int current = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Object[]{new Block(this.bh, paramInt1, paramInt2, paramInt3), 0, 1});
            if (current > 0) {
      paramfv.b(paramInt1, paramInt2, paramInt3, hr.aQ.bi, paramfv.b(paramInt1, paramInt2, paramInt3));}
  }}

  public void b(fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.b(paramfv, paramInt1, paramInt2, paramInt3, paramInt4);
    paramfv.i(paramInt1, paramInt2, paramInt3, bi);
  }

  public boolean d(fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (paramInt4 == 0) {
        // hMod: forced downcast!
      return b((la)paramfv, paramInt1, paramInt2, paramInt3, paramInt4);
    }
    return false;
  }

  public int a(int paramInt, Random paramRandom) {
    return hr.aQ.bi;
  }

  public boolean c() {
    return true;
  }
}