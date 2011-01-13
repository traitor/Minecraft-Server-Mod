import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class fb extends hr
{
  private boolean a = true;
  private Set b = new HashSet();

  public fb(int paramInt1, int paramInt2) {
    super(paramInt1, paramInt2, mh.n);
    a(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
  }

  public fa d(fv paramfv, int paramInt1, int paramInt2, int paramInt3)
  {
    return null;
  }

  public boolean a() {
    return false;
  }

  public boolean a(fv paramfv, int paramInt1, int paramInt2, int paramInt3)
  {
    return paramfv.d(paramInt1, paramInt2 - 1, paramInt3);
  }

  private void g(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
    a(paramfv, paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);
    ArrayList localArrayList = new ArrayList(b);
    b.clear();
    for (int i = 0; i < localArrayList.size(); i++) {
      jq localjq = (jq)localArrayList.get(i);
      paramfv.h(localjq.a, localjq.b, localjq.c, bi);
    }
  }

  private void a(fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    int i = paramfv.b(paramInt1, paramInt2, paramInt3);
    int j = 0;

    a = false;
    boolean bool = paramfv.p(paramInt1, paramInt2, paramInt3);
    a = true;
    int k;
    int m;
    int n;
    if (bool) {
      j = 15;
    } else {
      for (k = 0; k < 4; k++) {
        m = paramInt1;
        n = paramInt3;
        if (k == 0) m--;
        if (k == 1) m++;
        if (k == 2) n--;
        if (k == 3) n++;

        if ((m != paramInt4) || (paramInt2 != paramInt5) || (n != paramInt6)) j = f(paramfv, m, paramInt2, n, j);
        if ((paramfv.d(m, paramInt2, n)) && (!paramfv.d(paramInt1, paramInt2 + 1, paramInt3))) {
          if ((m == paramInt4) && (paramInt2 + 1 == paramInt5) && (n == paramInt6)) continue; j = f(paramfv, m, paramInt2 + 1, n, j);
        } else if ((!paramfv.d(m, paramInt2, n)) && (
          (m != paramInt4) || (paramInt2 - 1 != paramInt5) || (n != paramInt6))) { j = f(paramfv, m, paramInt2 - 1, n, j);
        }
      }
      if (j > 0) j--; else {
        j = 0;
      }
    }
     // hMod: Allow redstone wire current changes
        if (i != j) {
            j = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Object[]{new Block(this.bh, paramInt1, paramInt2, paramInt3), i, j});
        }

    if (i != j) {
      paramfv.i = true;
      paramfv.c(paramInt1, paramInt2, paramInt3, j);
      paramfv.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);
      paramfv.i = false;

      for (k = 0; k < 4; k++) {
        m = paramInt1;
        n = paramInt3;
        int i1 = paramInt2 - 1;
        if (k == 0) m--;
        if (k == 1) m++;
        if (k == 2) n--;
        if (k == 3) n++;

        if (paramfv.d(m, paramInt2, n)) i1 += 2;

        int i2 = 0;
        i2 = f(paramfv, m, paramInt2, n, -1);
        j = paramfv.b(paramInt1, paramInt2, paramInt3);
        if (j > 0) j--;
        if ((i2 >= 0) && (i2 != j)) {
          a(paramfv, m, paramInt2, n, paramInt1, paramInt2, paramInt3);
        }
        i2 = f(paramfv, m, i1, n, -1);
        j = paramfv.b(paramInt1, paramInt2, paramInt3);
        if (j > 0) j--;
        if ((i2 >= 0) && (i2 != j)) {
          a(paramfv, m, i1, n, paramInt1, paramInt2, paramInt3);
        }
      }

      if ((i == 0) || (j == 0)) {
        b.add(new jq(paramInt1, paramInt2, paramInt3));
        b.add(new jq(paramInt1 - 1, paramInt2, paramInt3));
        b.add(new jq(paramInt1 + 1, paramInt2, paramInt3));
        b.add(new jq(paramInt1, paramInt2 - 1, paramInt3));
        b.add(new jq(paramInt1, paramInt2 + 1, paramInt3));
        b.add(new jq(paramInt1, paramInt2, paramInt3 - 1));
        b.add(new jq(paramInt1, paramInt2, paramInt3 + 1));
      }
    }
  }

  private void h(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
    if (paramfv.a(paramInt1, paramInt2, paramInt3) != bi) return;

    paramfv.h(paramInt1, paramInt2, paramInt3, bi);
    paramfv.h(paramInt1 - 1, paramInt2, paramInt3, bi);
    paramfv.h(paramInt1 + 1, paramInt2, paramInt3, bi);
    paramfv.h(paramInt1, paramInt2, paramInt3 - 1, bi);
    paramfv.h(paramInt1, paramInt2, paramInt3 + 1, bi);

    paramfv.h(paramInt1, paramInt2 - 1, paramInt3, bi);
    paramfv.h(paramInt1, paramInt2 + 1, paramInt3, bi);
  }

  public void e(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
    super.e(paramfv, paramInt1, paramInt2, paramInt3);
    if (paramfv.z) return;

    g(paramfv, paramInt1, paramInt2, paramInt3);
    paramfv.h(paramInt1, paramInt2 + 1, paramInt3, bi);
    paramfv.h(paramInt1, paramInt2 - 1, paramInt3, bi);

    h(paramfv, paramInt1 - 1, paramInt2, paramInt3);
    h(paramfv, paramInt1 + 1, paramInt2, paramInt3);
    h(paramfv, paramInt1, paramInt2, paramInt3 - 1);
    h(paramfv, paramInt1, paramInt2, paramInt3 + 1);

    if (paramfv.d(paramInt1 - 1, paramInt2, paramInt3)) h(paramfv, paramInt1 - 1, paramInt2 + 1, paramInt3); else
      h(paramfv, paramInt1 - 1, paramInt2 - 1, paramInt3);
    if (paramfv.d(paramInt1 + 1, paramInt2, paramInt3)) h(paramfv, paramInt1 + 1, paramInt2 + 1, paramInt3); else
      h(paramfv, paramInt1 + 1, paramInt2 - 1, paramInt3);
    if (paramfv.d(paramInt1, paramInt2, paramInt3 - 1)) h(paramfv, paramInt1, paramInt2 + 1, paramInt3 - 1); else
      h(paramfv, paramInt1, paramInt2 - 1, paramInt3 - 1);
    if (paramfv.d(paramInt1, paramInt2, paramInt3 + 1)) h(paramfv, paramInt1, paramInt2 + 1, paramInt3 + 1); else
      h(paramfv, paramInt1, paramInt2 - 1, paramInt3 + 1);
  }

  public void b(fv paramfv, int paramInt1, int paramInt2, int paramInt3) {
    super.b(paramfv, paramInt1, paramInt2, paramInt3);
    if (paramfv.z) return;

    paramfv.h(paramInt1, paramInt2 + 1, paramInt3, bi);
    paramfv.h(paramInt1, paramInt2 - 1, paramInt3, bi);
    g(paramfv, paramInt1, paramInt2, paramInt3);

    h(paramfv, paramInt1 - 1, paramInt2, paramInt3);
    h(paramfv, paramInt1 + 1, paramInt2, paramInt3);
    h(paramfv, paramInt1, paramInt2, paramInt3 - 1);
    h(paramfv, paramInt1, paramInt2, paramInt3 + 1);

    if (paramfv.d(paramInt1 - 1, paramInt2, paramInt3)) h(paramfv, paramInt1 - 1, paramInt2 + 1, paramInt3); else
      h(paramfv, paramInt1 - 1, paramInt2 - 1, paramInt3);
    if (paramfv.d(paramInt1 + 1, paramInt2, paramInt3)) h(paramfv, paramInt1 + 1, paramInt2 + 1, paramInt3); else
      h(paramfv, paramInt1 + 1, paramInt2 - 1, paramInt3);
    if (paramfv.d(paramInt1, paramInt2, paramInt3 - 1)) h(paramfv, paramInt1, paramInt2 + 1, paramInt3 - 1); else
      h(paramfv, paramInt1, paramInt2 - 1, paramInt3 - 1);
    if (paramfv.d(paramInt1, paramInt2, paramInt3 + 1)) h(paramfv, paramInt1, paramInt2 + 1, paramInt3 + 1); else
      h(paramfv, paramInt1, paramInt2 - 1, paramInt3 + 1);
  }

  private int f(fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (paramfv.a(paramInt1, paramInt2, paramInt3) != bi) return paramInt4;
    int i = paramfv.b(paramInt1, paramInt2, paramInt3);
    if (i > paramInt4) return i;
    return paramInt4;
  }

  public void b(fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (paramfv.z) return;
    int i = paramfv.b(paramInt1, paramInt2, paramInt3);

    boolean bool = a(paramfv, paramInt1, paramInt2, paramInt3);

    if (!bool) {
      a_(paramfv, paramInt1, paramInt2, paramInt3, i);
      paramfv.e(paramInt1, paramInt2, paramInt3, 0);
    } else {
      g(paramfv, paramInt1, paramInt2, paramInt3);
    }

    super.b(paramfv, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public int a(int paramInt, Random paramRandom) {
    return hg.aA.ba;
  }

  public boolean d(fv paramfv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (!a) return false;
    // hMod: Forced downcast!
    return b((la)paramfv, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public boolean b(la paramla, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (!a) return false;
    if (paramla.b(paramInt1, paramInt2, paramInt3) == 0) return false;

    if (paramInt4 == 1) return true;

    int i = (b(paramla, paramInt1 - 1, paramInt2, paramInt3)) || ((!paramla.d(paramInt1 - 1, paramInt2, paramInt3)) && (b(paramla, paramInt1 - 1, paramInt2 - 1, paramInt3))) ? 1 : 0;
    int j = (b(paramla, paramInt1 + 1, paramInt2, paramInt3)) || ((!paramla.d(paramInt1 + 1, paramInt2, paramInt3)) && (b(paramla, paramInt1 + 1, paramInt2 - 1, paramInt3))) ? 1 : 0;
    int k = (b(paramla, paramInt1, paramInt2, paramInt3 - 1)) || ((!paramla.d(paramInt1, paramInt2, paramInt3 - 1)) && (b(paramla, paramInt1, paramInt2 - 1, paramInt3 - 1))) ? 1 : 0;
    int m = (b(paramla, paramInt1, paramInt2, paramInt3 + 1)) || ((!paramla.d(paramInt1, paramInt2, paramInt3 + 1)) && (b(paramla, paramInt1, paramInt2 - 1, paramInt3 + 1))) ? 1 : 0;
    if (!paramla.d(paramInt1, paramInt2 + 1, paramInt3)) {
      if ((paramla.d(paramInt1 - 1, paramInt2, paramInt3)) && (b(paramla, paramInt1 - 1, paramInt2 + 1, paramInt3))) i = 1;
      if ((paramla.d(paramInt1 + 1, paramInt2, paramInt3)) && (b(paramla, paramInt1 + 1, paramInt2 + 1, paramInt3))) j = 1;
      if ((paramla.d(paramInt1, paramInt2, paramInt3 - 1)) && (b(paramla, paramInt1, paramInt2 + 1, paramInt3 - 1))) k = 1;
      if ((paramla.d(paramInt1, paramInt2, paramInt3 + 1)) && (b(paramla, paramInt1, paramInt2 + 1, paramInt3 + 1))) m = 1;
    }

    if ((k == 0) && (j == 0) && (i == 0) && (m == 0) && (paramInt4 >= 2) && (paramInt4 <= 5)) return true;

    if ((paramInt4 == 2) && (k != 0) && (i == 0) && (j == 0)) return true;
    if ((paramInt4 == 3) && (m != 0) && (i == 0) && (j == 0)) return true;
    if ((paramInt4 == 4) && (i != 0) && (k == 0) && (m == 0)) return true;
    return (paramInt4 == 5) && (j != 0) && (k == 0) && (m == 0);
  }

  public boolean c()
  {
    return a;
  }

  public static boolean b(la paramla, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramla.a(paramInt1, paramInt2, paramInt3);
    if (i == hr.av.bi) return true;
    if (i == 0) return false;
    return hr.m[i].c();
  }
}