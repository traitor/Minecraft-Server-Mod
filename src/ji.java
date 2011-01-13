public class ji
  implements mn, Container<jl>
{
  public jl[] a = new jl[36];
  public jl[] b = new jl[4];

  public int c = 0;
  private hl e;
  private jl f;
  public boolean d = false;
private String name = "Inventory";
  public ji(hl paramhl) {
    e = paramhl;
  }

  public jl[] getContents() {
        return a;
    }

    public void setContents(jl[] values) {
        a = values;
    }

    public jl getContentsAt(int index) {
        return a(index);
    }

    public void setContentsAt(int index, jl value) {
        a(index, value);
    }

    public int getContentsSize() {
        return h_();
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        name = value;
    }

  public jl e() {
    return a[c];
  }

  private int d(int paramInt) {
    for (int i = 0; i < a.length; i++) {
      if ((a[i] != null) && (a[i].c == paramInt)) return i;
    }
    return -1;
  }

  private int c(jl paramjl) {
    for (int i = 0; i < a.length; i++) {
      if ((a[i] != null) && (a[i].c == paramjl.c) && (a[i].c()) && (a[i].a < a[i].b()) && (a[i].a < c()) && ((!a[i].e()) || (a[i].h() == paramjl.h())))
      {
        return i;
      }
    }
    return -1;
  }

  private int j() {
    for (int i = 0; i < a.length; i++) {
      if (a[i] == null) return i;
    }
    return -1;
  }

  private int d(jl paramjl)
  {
    int i = paramjl.c;
    int j = paramjl.a;

    int k = c(paramjl);
    if (k < 0) k = j();
    if (k < 0) return j;
    if (a[k] == null) {
      a[k] = new jl(i, 0, paramjl.h());
    }

    int m = j;
    if (m > a[k].b() - a[k].a) {
      m = a[k].b() - a[k].a;
    }
    if (m > c() - a[k].a) {
      m = c() - a[k].a;
    }

    if (m == 0) return j;

    j -= m;
    a[k].a += m;
    a[k].b = 5;

    return j;
  }

  public void f() {
    for (int i = 0; i < a.length; i++) {
      if ((a[i] == null) || (a[i].b <= 0)) continue; a[i].b -= 1;
    }
  }

  public boolean b(int paramInt) {
    int i = d(paramInt);
    if (i < 0) return false;
    if (--a[i].a <= 0) a[i] = null;

    return true;
  }

  public boolean a(jl paramjl)
  {
    if (!paramjl.f()) {
      paramjl.a = d(paramjl);
      if (paramjl.a == 0) return true;
    }

    int i = j();
    if (i >= 0) {
      a[i] = paramjl;
      a[i].b = 5;
      return true;
    }
    return false;
  }

  public jl b(int paramInt1, int paramInt2) {
    jl[] arrayOfjl = a;
    if (paramInt1 >= a.length) {
      arrayOfjl = b;
      paramInt1 -= a.length;
    }

    if (arrayOfjl[paramInt1] != null) {
      if (arrayOfjl[paramInt1].a <= paramInt2) {
        jl localjl = arrayOfjl[paramInt1];
        arrayOfjl[paramInt1] = null;
        return localjl;
      }
      jl localjl = arrayOfjl[paramInt1].a(paramInt2);
      if (arrayOfjl[paramInt1].a == 0) arrayOfjl[paramInt1] = null;
      return localjl;
    }

    return null;
  }

  public void a(int paramInt, jl paramjl) {
    jl[] arrayOfjl = a;
    if (paramInt >= arrayOfjl.length) {
      paramInt -= arrayOfjl.length;
      arrayOfjl = b;
    }

    arrayOfjl[paramInt] = paramjl;
  }

  public float a(hr paramhr) {
    float f1 = 1.0F;
    if (a[c] != null) f1 *= a[c].a(paramhr);
    return f1;
  }

  public fh a(fh paramfh)
  {
    ah localah;
    for (int i = 0; i < a.length; i++) {
      if (a[i] != null) {
        localah = new ah();
        localah.a("Slot", (byte)i);
        a[i].a(localah);
        paramfh.a(localah);
      }
    }
    for (int i = 0; i < b.length; i++) {
      if (b[i] != null) {
        localah = new ah();
        localah.a("Slot", (byte)(i + 100));
        b[i].a(localah);
        paramfh.a(localah);
      }
    }
    return paramfh;
  }

  public void b(fh paramfh) {
    a = new jl[36];
    b = new jl[4];
    for (int i = 0; i < paramfh.b(); i++) {
      ah localah = (ah)paramfh.a(i);
      int j = localah.b("Slot") & 0xFF;
      jl localjl = new jl(localah);
      if (localjl.a() != null) {
        if ((j >= 0) && (j < a.length)) a[j] = localjl;
        if ((j < 100) || (j >= b.length + 100)) continue; b[(j - 100)] = localjl;
      }
    }
  }

  public int h_() {
    return a.length + 4;
  }

  public jl a(int paramInt) {
    jl[] arrayOfjl = a;
    if (paramInt >= arrayOfjl.length) {
      paramInt -= arrayOfjl.length;
      arrayOfjl = b;
    }

    return arrayOfjl[paramInt];
  }

  public String b() {
    return getName(); //hMod: override with getName()
  }

  public int c() {
    return 64;
  }

  public int a(fe paramfe) {
    jl localjl = a(c);
    if (localjl != null) return localjl.a(paramfe);
    return 1;
  }

  public boolean b(hr paramhr) {
    if ((paramhr.bt != mh.d) && (paramhr.bt != mh.e) && (paramhr.bt != mh.t) && (paramhr.bt != mh.s)) return true;

    jl localjl = a(c);
    if (localjl != null) return localjl.b(paramhr);
    return false;
  }

  public int g()
  {
    int i = 0;
    int j = 0;
    int k = 0;
    for (int m = 0; m < b.length; m++) {
      if ((b[m] != null) && ((b[m].a() instanceof jj))) {
        int n = b[m].i();
        int i1 = b[m].g();

        int i2 = n - i1;
        j += i2;
        k += n;

        int i3 = ((jj)b[m].a()).bh;

        i += i3;
      }
    }
    if (k == 0) return 0;
    return (i - 1) * j / k + 1;
  }

  public void c(int paramInt) {
    for (int i = 0; i < b.length; i++)
      if ((b[i] != null) && ((b[i].a() instanceof jj))) {
        b[i].b(paramInt);
        if (b[i].a == 0) {
          b[i].a(e);
          b[i] = null;
        }
      }
  }

  public void h()
  {
    for (int i = 0; i < a.length; i++) {
      if (a[i] != null) {
        e.a(a[i], true);
        a[i] = null;
      }
    }
    for (int i = 0; i < b.length; i++)
      if (b[i] != null) {
        e.a(b[i], true);
        b[i] = null;
      }
  }

  public void d()
  {
    d = true;
  }

  public void b(jl paramjl)
  {
    f = paramjl;
    e.a(paramjl);
  }

  public jl i() {
    return f;
  }

  public boolean a_(hl paramhl) {
    if (e.G) return false;
    return paramhl.b((fe)e) <= 64.0D;
  }
}