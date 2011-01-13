public class ju
  implements mn
{
  private jl[] a = new jl[1];
  private String name = "Result";

  /**
     * Get contents of the chest block
     *
     * @return
     */
    public jl[] getContents() {
        return a;
    }

    /**
     * Set contents of the chest block
     *
     * @return
     */
    public void setContents(jl[] e) {
        this.a = e;
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
    return 1;
  }

  public jl a(int paramInt) {
    return a[paramInt];
  }

  public String b() {
    return getName(); //hMod: override with getName()
  }

  public jl b(int paramInt1, int paramInt2) {
    if (a[paramInt1] != null) {
      jl localjl = a[paramInt1];
      a[paramInt1] = null;
      return localjl;
    }
    return null;
  }

  public void a(int paramInt, jl paramjl) {
    a[paramInt] = paramjl;
  }

  public int c() {
    return 64;
  }

  public void d() {
  }

  public boolean a_(hl paramhl) {
    return true;
  }
}