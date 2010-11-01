import java.util.Random;

public class jm extends fw
{
  private int[] a = new int[256];
  private int[] b = new int[256];

  protected jm(int paramInt1, int paramInt2) {
    super(paramInt1, paramInt2, jr.l);

    a(fw.y.bi, 5, 20);
    a(fw.K.bi, 5, 5);
    a(fw.L.bi, 30, 60);
    a(fw.ao.bi, 30, 20);
    a(fw.an.bi, 15, 100);
    a(fw.ac.bi, 30, 60);

    a(true);
  }

  private void a(int paramInt1, int paramInt2, int paramInt3) {
    this.a[paramInt1] = paramInt2;
    this.b[paramInt1] = paramInt3;
  }

  public ds d(el paramel, int paramInt1, int paramInt2, int paramInt3)
  {
    return null;
  }

  public boolean a()
  {
    return false;
  }

  public int a(Random paramRandom)
  {
    return 0;
  }

  public int b() {
    return 10;
  }

  public void a(el paramel, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
    int i = (paramel.a(paramInt1, paramInt2 - 1, paramInt3) == fw.bc.bi) ? 1 : 0;

    int j = paramel.b(paramInt1, paramInt2, paramInt3);
    if (j < 15) {
      paramel.b(paramInt1, paramInt2, paramInt3, j + 1);
      paramel.h(paramInt1, paramInt2, paramInt3, this.bi);
    }
    if ((i == 0) && (!g(paramel, paramInt1, paramInt2, paramInt3))) {
      if ((!paramel.d(paramInt1, paramInt2 - 1, paramInt3)) || (j > 3)) paramel.d(paramInt1, paramInt2, paramInt3, 0);
      return;
    }

/*    if ((i == 0) && (!b(paramel, paramInt1, paramInt2 - 1, paramInt3)) && 
      (j == 15) && (paramRandom.nextInt(4) == 0)) {
      paramel.d(paramInt1, paramInt2, paramInt3, 0); 
      return; 
    } */

    if ((j % 2 == 0) && (j > 2)) {
      a(paramel, paramInt1 + 1, paramInt2, paramInt3, 300, paramRandom);
      a(paramel, paramInt1 - 1, paramInt2, paramInt3, 300, paramRandom);
      a(paramel, paramInt1, paramInt2 - 1, paramInt3, 200, paramRandom);
      a(paramel, paramInt1, paramInt2 + 1, paramInt3, 250, paramRandom);
      a(paramel, paramInt1, paramInt2, paramInt3 - 1, 300, paramRandom);
      a(paramel, paramInt1, paramInt2, paramInt3 + 1, 300, paramRandom);

      for (int k = paramInt1 - 1; k <= paramInt1 + 1; ++k)
        for (int l = paramInt3 - 1; l <= paramInt3 + 1; ++l)
          for (int i1 = paramInt2 - 1; i1 <= paramInt2 + 4; ++i1) {
            if ((k == paramInt1) && (i1 == paramInt2) && (l == paramInt3))
              continue;
            int i2 = 100;
            if (i1 > paramInt2 + 1) {
              i2 += (i1 - (paramInt2 + 1)) * 100;
            }

            int i3 = h(paramel, k, i1, l);
            if ((i3 > 0) && (paramRandom.nextInt(i2) <= i3))
              paramel.d(k, i1, l, this.bi);
          }
    }
  }

  private void a(el paramel, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Random paramRandom)
  {
    int i = this.b[paramel.a(paramInt1, paramInt2, paramInt3)];
    if (paramRandom.nextInt(paramInt4) < i) {
      int j = (paramel.a(paramInt1, paramInt2, paramInt3) == fw.an.bi) ? 1 : 0;
      if (paramRandom.nextInt(2) == 0)
        paramel.d(paramInt1, paramInt2, paramInt3, this.bi);
      else {
        paramel.d(paramInt1, paramInt2, paramInt3, 0);
      }
      if (j != 0)
        fw.an.a(paramel, paramInt1, paramInt2, paramInt3, 0);
    }
  }

  private boolean g(el paramel, int paramInt1, int paramInt2, int paramInt3)
  {
  /*
    if (b(paramel, paramInt1 + 1, paramInt2, paramInt3)) return true;
    if (b(paramel, paramInt1 - 1, paramInt2, paramInt3)) return true;
    if (b(paramel, paramInt1, paramInt2 - 1, paramInt3)) return true;
    if (b(paramel, paramInt1, paramInt2 + 1, paramInt3)) return true;
    if (b(paramel, paramInt1, paramInt2, paramInt3 - 1)) return true;
    return b(paramel, paramInt1, paramInt2, paramInt3 + 1); */
	
	return false;
  }

  private int h(el paramel, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = 0;
    if (paramel.a(paramInt1, paramInt2, paramInt3) != 0) return 0;

    i = f(paramel, paramInt1 + 1, paramInt2, paramInt3, i);
    i = f(paramel, paramInt1 - 1, paramInt2, paramInt3, i);
    i = f(paramel, paramInt1, paramInt2 - 1, paramInt3, i);
    i = f(paramel, paramInt1, paramInt2 + 1, paramInt3, i);
    i = f(paramel, paramInt1, paramInt2, paramInt3 - 1, i);
    i = f(paramel, paramInt1, paramInt2, paramInt3 + 1, i);

    return i;
  }

  public boolean d() {
    return false;
  }

  public boolean b(io paramio, int paramInt1, int paramInt2, int paramInt3) {
    return this.a[paramio.a(paramInt1, paramInt2, paramInt3)] > 0;
  }

  public int f(el paramel, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = this.a[paramel.a(paramInt1, paramInt2, paramInt3)];
    if (i > paramInt4) return i;
    return paramInt4;
  }

  public boolean a(el paramel, int paramInt1, int paramInt2, int paramInt3)
  {
    return (paramel.d(paramInt1, paramInt2 - 1, paramInt3)) || (g(paramel, paramInt1, paramInt2, paramInt3));
  }

  public void b(el paramel, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if ((!paramel.d(paramInt1, paramInt2 - 1, paramInt3)) && (!g(paramel, paramInt1, paramInt2, paramInt3))) {
      paramel.d(paramInt1, paramInt2, paramInt3, 0);
      return;
    }
  }

  public void e(el paramel, int paramInt1, int paramInt2, int paramInt3) {
    if ((paramel.a(paramInt1, paramInt2 - 1, paramInt3) == fw.aq.bi) && 
      (fw.bf.a_(paramel, paramInt1, paramInt2, paramInt3))) {
      return;
    }

    if ((!paramel.d(paramInt1, paramInt2 - 1, paramInt3)) && (!g(paramel, paramInt1, paramInt2, paramInt3))) {
      paramel.d(paramInt1, paramInt2, paramInt3, 0);
      return;
    }
    paramel.h(paramInt1, paramInt2, paramInt3, this.bi);
  }
}