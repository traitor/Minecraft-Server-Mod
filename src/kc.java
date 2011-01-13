public class kc extends bm
  implements mn, Container<jl>
{
  private jl[] e = new jl[36];

  private String name = "Chest";

    /**
     * Get contents of the chest block
     *
     * @return
     */
    public jl[] getContents() {
        return e;
    }

    /**
     * Set contents of the chest block
     *
     * @return
     */
    public void setContents(jl[] e) {
        this.e = e;
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
    return 27;
  }

  public jl a(int paramInt) {
    return e[paramInt];
  }

  public jl b(int paramInt1, int paramInt2) {
    if (e[paramInt1] != null) {
      if (e[paramInt1].a <= paramInt2) {
        jl localjl = e[paramInt1];
        e[paramInt1] = null;
        d();
        return localjl;
      }
      jl localjl = e[paramInt1].a(paramInt2);
      if (e[paramInt1].a == 0) e[paramInt1] = null;
      d();
      return localjl;
    }

    return null;
  }

  public void a(int paramInt, jl paramjl) {
    e[paramInt] = paramjl;
    if ((paramjl != null) && (paramjl.a > c())) paramjl.a = c();
    d();
  }

  public String b() {
    return getName(); //hMod: override with getName()
  }

  public void a(ah paramah)
  {
    super.a(paramah);
    fh localfh = paramah.k("Items");
    e = new jl[h_()];
    for (int i = 0; i < localfh.b(); i++) {
      ah localah = (ah)localfh.a(i);
      int j = localah.b("Slot") & 0xFF;
      if ((j < 0) || (j >= e.length)) continue; e[j] = new jl(localah);
    }
  }

  public void b(ah paramah) {
    super.b(paramah);
    fh localfh = new fh();

    for (int i = 0; i < e.length; i++) {
      if (e[i] != null) {
        ah localah = new ah();
        localah.a("Slot", (byte)i);
        e[i].a(localah);
        localfh.a(localah);
      }
    }
    paramah.a("Items", localfh);
  }

  public int c()
  {
    return 64;
  }

  public boolean a_(hl paramhl) {
    if (a.m(b, c, d) != this) return false;
    return paramhl.d(b + 0.5D, c + 0.5D, d + 0.5D) <= 64.0D;
  }
}