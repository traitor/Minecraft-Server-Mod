public class ez extends bm
  implements mn, Container<jl>
{
  private jl[] h = new jl[3];
  public int e = 0;
  public int f = 0;
  public int g = 0;
private String name = "Furnace";
  /**
     * Get contents of the furnace block
     * @return
     */
    public jl[] getContents() {
        return h;
    }

    /**
     * Set contents of the furnace block
     * @return
     */
    public void setContents(jl[] e) {
        this.h = e;
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

  public int h_() {
    return h.length;
  }

  public jl a(int paramInt) {
    return h[paramInt];
  }

  public jl b(int paramInt1, int paramInt2) {
    if (h[paramInt1] != null) {
      if (h[paramInt1].a <= paramInt2) {
        jl localjl = h[paramInt1];
        h[paramInt1] = null;
        return localjl;
      }
      jl localjl = h[paramInt1].a(paramInt2);
      if (h[paramInt1].a == 0) h[paramInt1] = null;
      return localjl;
    }

    return null;
  }

  public void a(int paramInt, jl paramjl) {
    h[paramInt] = paramjl;
    if ((paramjl != null) && (paramjl.a > c())) paramjl.a = c(); 
  }

  public String b()
  {
    return getName(); //hMod: override with getName()
  }

  public void a(ah paramah)
  {
    super.a(paramah);
    fh localfh = paramah.k("Items");
    h = new jl[h_()];
    for (int i = 0; i < localfh.b(); i++) {
      ah localah = (ah)localfh.a(i);
      int j = localah.b("Slot");
      if ((j < 0) || (j >= h.length)) continue; h[j] = new jl(localah);
    }

    e = paramah.c("BurnTime");
    g = paramah.c("CookTime");
    f = a(h[1]);
  }

  public void b(ah paramah) {
    super.b(paramah);
    paramah.a("BurnTime", (short)e);
    paramah.a("CookTime", (short)g);
    fh localfh = new fh();

    for (int i = 0; i < h.length; i++) {
      if (h[i] != null) {
        ah localah = new ah();
        localah.a("Slot", (byte)i);
        h[i].a(localah);
        localfh.a(localah);
      }
    }
    paramah.a("Items", localfh);
  }

  public int c() {
    return 64;
  }

  public boolean e()
  {
    return e > 0;
  }

  public void f() {
    int i = e > 0 ? 1 : 0;
    int j = 0;
    if (e > 0) {
      e -= 1;
    }

    if (!a.z) {
      if ((e == 0) && (i())) {
        f = (this.e = a(h[1]));
        if (e > 0) {
          j = 1;
          if (h[1] != null) {
            h[1].a -= 1;
            if (h[1].a == 0) h[1] = null;
          }
        }
      }

      if ((e()) && (i())) {
        g += 1;
        if (g == 200) {
          g = 0;
          h();
          j = 1;
        }
      } else {
        g = 0;
      }

      if (i != (e > 0 ? 1 : 0)) {
        j = 1;
        fr.a(e > 0, a, b, c, d);
      }
    }

    if (j != 0) d(); 
  }

  private boolean i()
  {
    if (h[0] == null) return false;
    jl localjl = fp.a().a(h[0].a().ba);
    if (localjl == null) return false;
    if (h[2] == null) return true;
    if (!h[2].a(localjl)) return false;
    if ((h[2].a < c()) && (h[2].a < h[2].b())) return true;
    return h[2].a < localjl.b();
  }

  public void h()
  {
    if (!i()) return;

    jl localjl = fp.a().a(h[0].a().ba);
    if (h[2] == null) h[2] = localjl.j();
    else if (h[2].c == localjl.c) h[2].a += 1;

    h[0].a -= 1;
    if (h[0].a <= 0) h[0] = null; 
  }

  private int a(jl paramjl)
  {
    if (paramjl == null) return 0;
    int i = paramjl.a().ba;

    if ((i < 256) && (hr.m[i].bt == mh.c)) {
      return 300;
    }

    if (i == hg.B.ba) {
      return 100;
    }

    if (i == hg.k.ba) return 1600;

    if (i == hg.aw.ba) return 20000;

    return 0;
  }

  public boolean a_(hl paramhl) {
    if (a.m(b, c, d) != this) return false;
    return paramhl.d(b + 0.5D, c + 0.5D, d + 0.5D) <= 64.0D;
  }
}