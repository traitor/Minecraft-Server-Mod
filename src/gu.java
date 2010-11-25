import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class gu
{
  public dx a;
  public boolean b = false;
  public boolean c = false;
  public int d;
  public int e;
  public int f;
  public int g;
  public int h;
  public int i;
  public int j;
  public double k;
  public double l;
  public double m;
  public int n = 0;
  private double q;
  private double r;
  private double s;
  private boolean t = false;
  private boolean u;
  public boolean o = false;

  public Set<er> p = new HashSet<er>();

  public gu(dx paramdx, int paramInt1, int paramInt2, boolean paramBoolean) {
    this.a = paramdx;
    this.d = paramInt1;
    this.e = paramInt2;
    this.u = paramBoolean;

    this.f = hf.b(paramdx.p * 32.0D);
    this.g = hf.b(paramdx.q * 32.0D);
    this.h = hf.b(paramdx.r * 32.0D);
    this.i = hf.d(paramdx.v * 256.0F / 360.0F);
    this.j = hf.d(paramdx.w * 256.0F / 360.0F);
  }

  public boolean equals(Object paramObject) {
    if ((paramObject instanceof gu)) {
      return ((gu)paramObject).a.g == this.a.g;
    }

    return false;
  }

  public int hashCode() {
    return this.a.g;
  }

  public void a(List paramList) {
    if (this.a.E) {
      a(new q(this.a, 2));
      this.a.E = false;
    }

    this.o = false;
    if ((!this.t) || (this.a.d(this.q, this.r, this.s) > 16.0D)) {
      b(paramList);
      this.q = this.a.p;
      this.r = this.a.q;
      this.s = this.a.r;
      this.t = true;
      this.o = true;
    }

    if (this.n++ % this.e == 0) {
      int i1 = hf.b(this.a.p * 32.0D);
      int i2 = hf.b(this.a.q * 32.0D);
      int i3 = hf.b(this.a.r * 32.0D);
      int i4 = hf.d(this.a.v * 256.0F / 360.0F);
      int i5 = hf.d(this.a.w * 256.0F / 360.0F);

      int i6 = (i1 != this.f) || (i2 != this.g) || (i3 != this.h) ? 1 : 0;
      int i7 = (i4 != this.i) || (i5 != this.j) ? 1 : 0;

      int i8 = i1 - this.f;
      int i9 = i2 - this.g;
      int i10 = i3 - this.h;
      Object localObject = null;

      if ((i8 < -128) || (i8 >= 128) || (i9 < -128) || (i9 >= 128) || (i10 < -128) || (i10 >= 128)) {
        localObject = new cr(this.a.g, i1, i2, i3, (byte)i4, (byte)i5);
      }
      else if ((i6 != 0) && (i7 != 0))
        localObject = new bq(this.a.g, (byte)i8, (byte)i9, (byte)i10, (byte)i4, (byte)i5);
      else if (i6 != 0)
        localObject = new eh(this.a.g, (byte)i8, (byte)i9, (byte)i10);
      else if (i7 != 0)
        localObject = new dk(this.a.g, (byte)i4, (byte)i5);
      else {
        localObject = new fr(this.a.g);
      }

      if (this.u) {
        double d1 = this.a.s - this.k;
        double d2 = this.a.t - this.l;
        double d3 = this.a.u - this.m;

        double d4 = 0.02D;

        double d5 = d1 * d1 + d2 * d2 + d3 * d3;

        if ((d5 > d4 * d4) || ((d5 > 0.0D) && (this.a.s == 0.0D) && (this.a.t == 0.0D) && (this.a.u == 0.0D))) {
          this.k = this.a.s;
          this.l = this.a.t;
          this.m = this.a.u;
          a(new fs(this.a.g, this.k, this.l, this.m));
        }
      }

      if (localObject != null) {
        a((ir)localObject);
      }

      if ((this.b) && (this.a.k == null)) {
        this.b = false;
        a(new q(this.a, 101));
      } else if ((!this.b) && (this.a.k != null)) {
        this.b = true;
        a(new q(this.a, 100));
      }

      if ((this.c) && (this.a.Z <= 0)) {
        this.c = false;
        a(new q(this.a, 103));
      } else if ((!this.c) && (this.a.Z > 0)) {
        this.c = true;
        a(new q(this.a, 102));
      }

      this.f = i1;
      this.g = i2;
      this.h = i3;
      this.i = i4;
      this.j = i5;
    }
  }

  public void a(ir paramir) {
    for (er localer : this.p)
      localer.a.b(paramir);
  }

  public void a()
  {
    a(new dg(this.a.g));
  }

  public void a(er paramer)
  {
    if (paramer == this.a) return;

    double d1 = paramer.p - this.f / 32;
    double d2 = paramer.r - this.h / 32;
    if ((d1 >= -this.d) && (d1 <= this.d) && (d2 >= -this.d) && (d2 <= this.d)) {
      if (!this.p.contains(paramer)) {
        this.p.add(paramer);
        b(paramer);
        if (this.b) {
          paramer.a.b(new q(this.a, 100));
        }
        if (this.c) {
          paramer.a.b(new q(this.a, 102));
        }
        if (this.u) {
          paramer.a.b(new fs(this.a.g, this.a.s, this.a.t, this.a.u));
        }
      }
    }
    else if (this.p.contains(paramer)) {
      this.p.remove(paramer);
      paramer.a.b(new dg(this.a.g));
    }
  }

  public void b(List paramList)
  {
    for (int i1 = 0; i1 < paramList.size(); i1++)
      a((er)paramList.get(i1));
  }

  private void b(er trigger) {
    Object localObject;
    ir toRet = null;
    BaseEntity toSend = null;
    if ((this.a instanceof gj)) { // Lava entity(?)
      localObject = (gj)this.a;
      l locall = new l((gj)localObject);
      ((gj)localObject).p = (locall.b / 32.0D);
      ((gj)localObject).q = (locall.c / 32.0D);
      ((gj)localObject).r = (locall.d / 32.0D);
      toRet = locall;
    }
    if ((this.a instanceof er)) { // player
      toRet = new d((fx)this.a);
    }
    if ((this.a instanceof jm)) { // Minecart
      localObject = (jm)this.a;
      toRet = new ea(this.a, 10 + ((jm)localObject).d);
      /*if (((jm)localObject).d == 0) return new ea(this.a, 10);
      if (((jm)localObject).d == 1) return new ea(this.a, 11);
      if (((jm)localObject).d == 2) return new ea(this.a, 12);
       *
       */
      toSend = new Minecart((jm)this.a);
    }
    if ((this.a instanceof fk)) { // Boat
      toRet = new ea(this.a, 1);
    }
    if ((this.a instanceof af)) { //Handled elsewhere/unimplemented...?
      toRet = new ht((jy)this.a);
    }
    if ((this.a instanceof ke)) { // fish
      toRet = new ea(this.a, 90);
    }
    if ((this.a instanceof cj)) { // Dynamite
      toRet = new ea(this.a, 50);
    }
    if (toSend != null) {
        if(!(Boolean)etc.getLoader().callHook(PluginLoader.Hook.ENTITY_SPAWN, new Object[] { toSend })) {
             trigger.a.b(toRet);
             System.out.println("Spawned Entity.");
        }
    } else {
         trigger.a.b(toRet);
    }
    //throw new IllegalArgumentException("Don't know how to add " + this.a.getClass() + "!");
  }
  //CONSIDER MOVING JY IN BASE ENTITIY TO A DX TO ALLOW A HOOK TO PASS THESE OVER, THEN CAST FOR HEALTH.
}
sds