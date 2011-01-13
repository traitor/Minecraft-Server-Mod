// hMod: This class must extend Container<obfuscated_item> and implement the various methods within. They will not all be marked individually.

public class ay
  implements mn, Container<jl>
{
  private String a;
  private mn b;
  private mn c;

  public jl[] getContents() {
        int size = getContentsSize();
        jl[] result = new jl[size];

        for (int i = 0; i < size; i++) {
            result[i] = getContentsAt(i);
        }

        return result;
    }

    public void setContents(jl[] values) {
        int size = getContentsSize();

        for (int i = 0; i < size; i++) {
            setContentsAt(i, values[i]);
        }
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

    public Block getChestBlock() {
        if (this.b instanceof kc) {
            kc block = (kc)b;
            return etc.getServer().getBlockAt(block.b, block.c, block.d);
        }
        if (this.c instanceof kc) {
            kc block = (kc)c;
            return etc.getServer().getBlockAt(block.b, block.c, block.d);
        }
        return null;
    }

    public String getName() {
        return a;
    }

    public void setName(String value) {
        a = value;
    }

  public ay(String paramString, mn parammn1, mn parammn2)
  {
    a = paramString;
    b = parammn1;
    c = parammn2;
  }

  public int h_() {
    return b.h_() + c.h_();
  }

  public String b() {
    return a;
  }

  public jl a(int paramInt) {
    if (paramInt >= b.h_()) return c.a(paramInt - b.h_());
    return b.a(paramInt);
  }

  public jl b(int paramInt1, int paramInt2) {
    if (paramInt1 >= b.h_()) return c.b(paramInt1 - b.h_(), paramInt2);
    return b.b(paramInt1, paramInt2);
  }

  public void a(int paramInt, jl paramjl) {
    if (paramInt >= b.h_()) c.a(paramInt - b.h_(), paramjl); else
      b.a(paramInt, paramjl);
  }

  public int c() {
    return b.c();
  }

  public void d() {
    b.d();
    c.d();
  }

  public boolean a_(hl paramhl) {
    return (b.a_(paramhl)) && (c.a_(paramhl));
  }
}