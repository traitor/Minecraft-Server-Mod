import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ju
{
  public static boolean a;
  public byte[] b;
  public boolean c;
  public eq d;
  public ht e;
  public ht f;
  public ht g;
  public byte[] h;
  public int i;
  public final int j;
  public final int k;
  public Map l = new HashMap();
  public List[] m = new List[8];

  public boolean n = false;
  public boolean o = false;
  public boolean p;
  public boolean q = false;
  public boolean r = false;
  public long s = 0L;

  public ju(eq parameq, int paramInt1, int paramInt2) {
    d = parameq;
    j = paramInt1;
    k = paramInt2;
    h = new byte[256];
    for (int i1 = 0; i1 < m.length; i1++)
      m[i1] = new ArrayList();
  }

  public ju(eq parameq, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this(parameq, paramInt1, paramInt2);

    b = paramArrayOfByte;
    e = new ht(paramArrayOfByte.length);
    f = new ht(paramArrayOfByte.length);
    g = new ht(paramArrayOfByte.length);
  }

  public boolean a(int paramInt1, int paramInt2) {
    return (paramInt1 == j) && (paramInt2 == k);
  }

  public int b(int paramInt1, int paramInt2) {
    return h[(paramInt2 << 4 | paramInt1)] & 0xFF;
  }

  public void a()
  {
  }

  public void b()
  {
    int i1 = 127;
    int i3;
    for (int i2 = 0; i2 < 16; i2++) {
      for (i3 = 0; i3 < 16; i3++) {
        h[(i3 << 4 | i2)] = -128;
        g(i2, 127, i3);
        if ((h[(i3 << 4 | i2)] & 0xFF) >= i1) continue; i1 = h[(i3 << 4 | i2)] & 0xFF;
      }
    }
    i = i1;

    for (int i2 = 0; i2 < 16; i2++) {
      for (i3 = 0; i3 < 16; i3++) {
        c(i2, i3);
      }
    }
    o = true;
  }

  public void c()
  {
    int i1 = 32;
    for (int i2 = 0; i2 < 16; i2++) {
      for (int i3 = 0; i3 < 16; i3++) {
        int i4 = i2 << 11 | i3 << 7;
        for (int i5 = 0; i5 < 128; i5++) {
          int i6 = gc.s[b[(i4 + i5)]];
          if (i6 > 0) {
            g.a(i2, i5, i3, i6);
          }
        }

        int i5 = 15;
        int i6 = i1 - 2;
        while ((i6 < 128) && (i5 > 0)) {
          i6++;
          int i7 = b[(i4 + i6)];
          int i8 = gc.q[i7];
          int i9 = gc.s[i7];
          if (i8 == 0) i8 = 1;
          i5 -= i8;
          if (i9 > i5) i5 = i9;
          if (i5 < 0) i5 = 0;

          g.a(i2, i6, i3, i5);
        }

      }

    }

    d.a(dn.b, j * 16, i1 - 1, k * 16, j * 16 + 16, i1 + 1, k * 16 + 16);

    o = true;
  }

  private void c(int paramInt1, int paramInt2) {
    int i1 = b(paramInt1, paramInt2);

    int i2 = j * 16 + paramInt1;
    int i3 = k * 16 + paramInt2;
    f(i2 - 1, i3, i1);
    f(i2 + 1, i3, i1);
    f(i2, i3 - 1, i1);
    f(i2, i3 + 1, i1);
  }

  private void f(int paramInt1, int paramInt2, int paramInt3) {
    int i1 = d.d(paramInt1, paramInt2);
    if (i1 > paramInt3)
      d.a(dn.a, paramInt1, paramInt3, paramInt2, paramInt1, i1, paramInt2);
    else if (i1 < paramInt3) {
      d.a(dn.a, paramInt1, i1, paramInt2, paramInt1, paramInt3, paramInt2);
    }
    o = true;
  }

  private void g(int paramInt1, int paramInt2, int paramInt3) {
    int i1 = h[(paramInt3 << 4 | paramInt1)] & 0xFF;
    int i2 = i1;
    if (paramInt2 > i1) i2 = paramInt2;

    int i3 = paramInt1 << 11 | paramInt3 << 7;
    while ((i2 > 0) && (gc.q[b[(i3 + i2 - 1)]] == 0))
      i2--;
    if (i2 == i1) return;

    d.f(paramInt1, paramInt3, i2, i1);
    h[(paramInt3 << 4 | paramInt1)] = (byte)i2;

    if (i2 < i) {
      i = i2;
    } else {
      int i4 = 127;
      for (int i5 = 0; i5 < 16; i5++)
        for (int i6 = 0; i6 < 16; i6++) {
          if ((h[(i6 << 4 | i5)] & 0xFF) >= i4) continue; i4 = h[(i6 << 4 | i5)] & 0xFF;
        }
      i = i4;
    }

    int i4 = j * 16 + paramInt1;
    int i5 = k * 16 + paramInt3;
    if (i2 < i1) {
      for (int i6 = i2; i6 < i1; i6++)
        f.a(paramInt1, i6, paramInt3, 15);
    }
    else {
      d.a(dn.a, i4, i1, i5, i4, i2, i5);
      for (int i6 = i1; i6 < i2; i6++) {
        f.a(paramInt1, i6, paramInt3, 0);
      }
    }

    int i6 = 15;
    int i7 = i2;
    while ((i2 > 0) && (i6 > 0)) {
      i2--;
      int i8 = gc.q[a(paramInt1, i2, paramInt3)];
      if (i8 == 0) i8 = 1;
      i6 -= i8;
      if (i6 < 0) i6 = 0;
      f.a(paramInt1, i2, paramInt3, i6);
    }

    while ((i2 > 0) && (gc.q[a(paramInt1, i2 - 1, paramInt3)] == 0))
      i2--;
    if (i2 != i7) {
      d.a(dn.a, i4 - 1, i2, i5 - 1, i4 + 1, i7, i5 + 1);
    }

    o = true;
  }

  public int a(int paramInt1, int paramInt2, int paramInt3) {
    return b[(paramInt1 << 11 | paramInt3 << 7 | paramInt2)];
  }

  public boolean a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    int i1 = (byte)paramInt4;

    int i2 = h[(paramInt3 << 4 | paramInt1)] & 0xFF;

    int i3 = b[(paramInt1 << 11 | paramInt3 << 7 | paramInt2)] & 0xFF;
    if ((i3 == paramInt4) && (e.a(paramInt1, paramInt2, paramInt3) == paramInt5)) return false;
    int i4 = j * 16 + paramInt1;
    int i5 = k * 16 + paramInt3;
    b[(paramInt1 << 11 | paramInt3 << 7 | paramInt2)] = (byte) i1;
    if ((i3 != 0) && 
      (!d.z)) gc.m[i3].b(d, i4, paramInt2, i5);

    e.a(paramInt1, paramInt2, paramInt3, paramInt5);

    if (!d.q.e) {
      if (gc.q[i1] != 0) {
        if (paramInt2 >= i2) {
          g(paramInt1, paramInt2 + 1, paramInt3);
        }
      }
      else if (paramInt2 == i2 - 1) {
        g(paramInt1, paramInt2, paramInt3);
      }

      d.a(dn.a, i4, paramInt2, i5, i4, paramInt2, i5);
    }

    d.a(dn.b, i4, paramInt2, i5, i4, paramInt2, i5);

    c(paramInt1, paramInt3);

    if (paramInt4 != 0) {
      gc.m[paramInt4].e(d, i4, paramInt2, i5);
    }
    e.a(paramInt1, paramInt2, paramInt3, paramInt5);

    o = true;
    return true;
  }

  public boolean a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i1 = (byte)paramInt4;
    int i2 = h[(paramInt3 << 4 | paramInt1)] & 0xFF;

    int i3 = b[(paramInt1 << 11 | paramInt3 << 7 | paramInt2)] & 0xFF;
    if (i3 == paramInt4) return false;
    int i4 = j * 16 + paramInt1;
    int i5 = k * 16 + paramInt3;
    b[(paramInt1 << 11 | paramInt3 << 7 | paramInt2)] = (byte) i1;
    if (i3 != 0) {
      gc.m[i3].b(d, i4, paramInt2, i5);
    }
    e.a(paramInt1, paramInt2, paramInt3, 0);

    if (gc.q[i1] != 0) {
      if (paramInt2 >= i2) {
        g(paramInt1, paramInt2 + 1, paramInt3);
      }
    }
    else if (paramInt2 == i2 - 1) {
      g(paramInt1, paramInt2, paramInt3);
    }

    d.a(dn.a, i4, paramInt2, i5, i4, paramInt2, i5);
    d.a(dn.b, i4, paramInt2, i5, i4, paramInt2, i5);

    c(paramInt1, paramInt3);

    if ((paramInt4 != 0) && 
      (!d.z)) gc.m[paramInt4].e(d, i4, paramInt2, i5);

    o = true;
    return true;
  }

  public int b(int paramInt1, int paramInt2, int paramInt3) {
    return e.a(paramInt1, paramInt2, paramInt3);
  }

  public void b(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    o = true;
    e.a(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public int a(dn paramdn, int paramInt1, int paramInt2, int paramInt3) {
    if (paramdn == dn.a) return f.a(paramInt1, paramInt2, paramInt3);
    if (paramdn == dn.b) return g.a(paramInt1, paramInt2, paramInt3);
    return 0;
  }

  public void a(dn paramdn, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    o = true;
    //Afforess Set Light Level
    int change = (Integer) etc.getLoader().callHook(PluginLoader.Hook.LIGHT_LEVEL_CHANGE, etc.getServer().getBlockAt(paramInt1, paramInt2, paramInt3), this.a(paramdn, paramInt1, paramInt2, paramInt3), paramInt4);
    if (change != paramInt4) {
    	paramInt4 = change;
    }
    if (paramdn == dn.a) f.a(paramInt1, paramInt2, paramInt3, paramInt4);
    else if (paramdn == dn.b) g.a(paramInt1, paramInt2, paramInt3, paramInt4); else
      return;
  }

  public int c(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i1 = f.a(paramInt1, paramInt2, paramInt3);
    if (i1 > 0) a = true;
    i1 -= paramInt4;
    int i2 = g.a(paramInt1, paramInt2, paramInt3);
    if (i2 > i1) i1 = i2;

    return i1;
  }

  public void a(ea paramea) {
    if (q) {
      return;
    }
    r = true;

    int i1 = hh.b(paramea.p / 16.0D);
    int i2 = hh.b(paramea.r / 16.0D);
    if ((i1 != j) || (i2 != k)) {
      System.out.println("Wrong location! " + paramea);
      Thread.dumpStack();
    }
    int i3 = hh.b(paramea.q / 16.0D);
    if (i3 < 0) i3 = 0;
    if (i3 >= m.length) i3 = m.length - 1;
    paramea.af = true;
    paramea.ag = j;
    paramea.ah = i3;
    paramea.ai = k;
    m[i3].add(paramea);
  }

  public void b(ea paramea) {
    a(paramea, paramea.ah);
  }

  public void a(ea paramea, int paramInt) {
    if (paramInt < 0) paramInt = 0;
    if (paramInt >= m.length) paramInt = m.length - 1;
    m[paramInt].remove(paramea);
  }

  public boolean c(int paramInt1, int paramInt2, int paramInt3) {
    return paramInt2 >= (h[(paramInt3 << 4 | paramInt1)] & 0xFF);
  }

  public ay d(int paramInt1, int paramInt2, int paramInt3)
  {
    hs localhs = new hs(paramInt1, paramInt2, paramInt3);

    ay localay = (ay)l.get(localhs);
    if (localay == null) {
      int i1 = a(paramInt1, paramInt2, paramInt3);
      if (gc.p[i1] == false) return null;
      dh localdh = (dh)gc.m[i1];
      localdh.e(d, j * 16 + paramInt1, paramInt2, k * 16 + paramInt3);
      localay = (ay)l.get(localhs);
    }
    return localay;
  }

  public void a(ay paramay) {
    int i1 = paramay.b - j * 16;
    int i2 = paramay.c;
    int i3 = paramay.d - k * 16;
    a(i1, i2, i3, paramay);
  }

  public void a(int paramInt1, int paramInt2, int paramInt3, ay paramay) {
    hs localhs = new hs(paramInt1, paramInt2, paramInt3);

    paramay.a = d;
    paramay.b = (j * 16 + paramInt1);
    paramay.c = paramInt2;
    paramay.d = (k * 16 + paramInt3);

    if ((a(paramInt1, paramInt2, paramInt3) == 0) || (!(gc.m[a(paramInt1, paramInt2, paramInt3)] instanceof dh))) {
      System.out.println("Attempted to place a tile entity where there was no entity tile!");
      return;
    }

    if (c) {
      if (l.get(localhs) != null) {
        d.c.remove(l.get(localhs));
      }
      d.c.add(paramay);
    }
    l.put(localhs, paramay);
  }

  public void e(int paramInt1, int paramInt2, int paramInt3) {
    hs localhs = new hs(paramInt1, paramInt2, paramInt3);

    if (c)
      d.c.remove(l.remove(localhs));
  }

  public void d()
  {
    c = true;
    d.c.addAll(l.values());
    for (int i1 = 0; i1 < m.length; i1++)
      d.a(m[i1]);
  }

  public void e()
  {
    c = false;
    d.c.removeAll(l.values());
    for (int i1 = 0; i1 < m.length; i1++)
      d.b(m[i1]);
  }

  public void f()
  {
    o = true;
  }

  public void a(ea paramea, dw paramdw, List paramList) {
    int i1 = hh.b((paramdw.b - 2.0D) / 16.0D);
    int i2 = hh.b((paramdw.e + 2.0D) / 16.0D);
    if (i1 < 0) i1 = 0;
    if (i2 >= m.length) i2 = m.length - 1;
    for (int i3 = i1; i3 <= i2; i3++) {
      List localList = m[i3];
      for (int i4 = 0; i4 < localList.size(); i4++) {
        ea localea = (ea)localList.get(i4);
        if ((localea == paramea) || (!localea.z.a(paramdw))) continue; paramList.add(localea);
      }
    }
  }

  public void a(Class paramClass, dw paramdw, List paramList) {
    int i1 = hh.b((paramdw.b - 2.0D) / 16.0D);
    int i2 = hh.b((paramdw.e + 2.0D) / 16.0D);
    if (i1 < 0) i1 = 0;
    if (i2 >= m.length) i2 = m.length - 1;
    for (int i3 = i1; i3 <= i2; i3++) {
      List localList = m[i3];
      for (int i4 = 0; i4 < localList.size(); i4++) {
        ea localea = (ea)localList.get(i4);
        if ((!paramClass.isAssignableFrom(localea.getClass())) || (!localea.z.a(paramdw))) continue; paramList.add(localea);
      }
    }
  }

  public boolean a(boolean paramBoolean)
  {
    if (p) return false;
    if ((r) && (d.e != s)) return true;
    return o;
  }

  public int a(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
  {
    int i2;
    int i3;
    int i4;
    for (int i1 = paramInt1; i1 < paramInt4; i1++) {
      for (i2 = paramInt3; i2 < paramInt6; i2++) {
        i3 = i1 << 11 | i2 << 7 | paramInt2;
        i4 = paramInt5 - paramInt2;
        System.arraycopy(b, i3, paramArrayOfByte, paramInt7, i4);
        paramInt7 += i4;
      }
    }
    for (int i1 = paramInt1; i1 < paramInt4; i1++) {
      for (i2 = paramInt3; i2 < paramInt6; i2++) {
        i3 = (i1 << 11 | i2 << 7 | paramInt2) >> 1;
        i4 = (paramInt5 - paramInt2) / 2;
        System.arraycopy(e.a, i3, paramArrayOfByte, paramInt7, i4);
        paramInt7 += i4;
      }
    }
    for (int i1 = paramInt1; i1 < paramInt4; i1++) {
      for (i2 = paramInt3; i2 < paramInt6; i2++) {
        i3 = (i1 << 11 | i2 << 7 | paramInt2) >> 1;
        i4 = (paramInt5 - paramInt2) / 2;
        System.arraycopy(g.a, i3, paramArrayOfByte, paramInt7, i4);
        paramInt7 += i4;
      }
    }
    for (int i1 = paramInt1; i1 < paramInt4; i1++) {
      for (i2 = paramInt3; i2 < paramInt6; i2++) {
        i3 = (i1 << 11 | i2 << 7 | paramInt2) >> 1;
        i4 = (paramInt5 - paramInt2) / 2;
        System.arraycopy(f.a, i3, paramArrayOfByte, paramInt7, i4);
        paramInt7 += i4;
      }
    }
    return paramInt7;
  }

  public Random a(long paramLong)
  {
    return new Random(d.u + j * j * 4987142 + j * 5947611 + k * k * 4392871L + k * 389711 ^ paramLong);
  }
}