import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import net.minecraft.server.MinecraftServer;

public class ep extends fv
{
  public je a;
  public MinecraftServer b;
  public jq c;
  public double d;
  public double e;
  public List f = new LinkedList();

  public Set ai = new HashSet();
  public double aj;

  public ep(MinecraftServer paramMinecraftServer, em paramem, String paramString, jq paramjq)
  {
    super(paramem);

    int i = paramem.m;
    int j = paramem.o;
    int k = paramem.n;

    if (!paramem.q.e) {
      i += this.V.nextInt(20) - 10;
      k = paramem.e(i, j);
      j += this.V.nextInt(20) - 10;
    }
    c(i + 0.5D, k, j + 0.5D, 0.0F, 0.0F);
    this.b = paramMinecraftServer;
    this.R = 0.0F;
    paramjq.a = this;
    this.ar = paramString;
    this.c = paramjq;
    this.G = 0.0F;
  }

  public void b_() {
  }

  public void f(dw paramdw) {
  }

  public boolean a(dw paramdw, int paramInt) {
    return false;
  }

  public void a(int paramInt) {
  }

  public void k() {
    super.b_();

    Object localObject1 = null;

    double d1 = 0.0D;
    Object localObject2;
    for (int i = 0; i < this.f.size(); i++) {
      localObject2 = (kc)this.f.get(i);
      double d2 = ((kc)localObject2).a(this);
      if ((i == 0) || (d2 < d1)) {
        localObject1 = localObject2;
        d1 = ((kc)localObject2).a(this);
      }
    }

    if (localObject1 != null) {
      i = 0;

      if (d1 < 1024.0D) i = 1;
      if (this.a.b() < 2) i = 1;

      if (i != 0) {
        this.f.remove(localObject1);
        this.a.b(new dl(localObject1.a * 16, 0, localObject1.b * 16, 16, 128, 16, this.b.e));
        localObject2 = this.b.e.d(localObject1.a * 16, 0, localObject1.b * 16, localObject1.a * 16 + 16, 128, localObject1.b * 16 + 16);
        for (int j = 0; j < ((List)localObject2).size(); j++) {
          ay localay = (ay)((List)localObject2).get(j);
          this.a.b(new jc(localay.b, localay.c, localay.d, localay));
        }
      }
    }
  }

  public void D() {
    this.s = (this.t = this.u = 0.0D);
    this.bp = false;
    super.D();
  }

  public void c(dw paramdw, int paramInt) {
    if ((!paramdw.F) && 
      ((paramdw instanceof gh))) {
      this.a.b(new fd(((gh)paramdw).a, paramInt));
      this.b.k.a(paramdw, new cq(paramdw.g, this.g));
    }

    super.c(paramdw, paramInt);
  }

  public void E() {
    if (!this.ap) {
      this.aq = -1;
      this.ap = true;
      this.b.k.a(this, new q(this, 1));
    }
  }

  public float s() {
    return 1.62F;
  }

  public void e(dw paramdw) {
    super.e(paramdw);
    this.a.b(new s(this, this.k));
    this.a.a(this.p, this.q, this.r, this.v, this.w);
  }
}