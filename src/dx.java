import java.util.List;
import java.util.Random;

public abstract class dx
{
  private static int a = 0;

  public int g = a++;

  public double h = 1.0D;

  public boolean i = false;
  public dx j;
  public dx k;
  public eo l;
  public double m;
  public double n;
  public double o;
  public double p;
  public double q;
  public double r;
  public double s;
  public double t;
  public double u;
  public float v;
  public float w;
  public float x;
  public float y;
  public final du z = du.a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
  public boolean A = false;
  public boolean B;
  public boolean C;
  public boolean D = false;
  public boolean E = false;

  public boolean F = true;
  public boolean G = false;
  public float H = 0.0F;

  public float I = 0.6F;
  public float J = 1.8F;

  public float K = 0.0F;
  public float L = 0.0F;
  protected boolean M = true;
  protected float N = 0.0F;
  private int b = 1;
  public double O;
  public double P;
  public double Q;
  public float R = 0.0F;
  public float S = 0.0F;
  public boolean T = false;
  public float U = 0.0F;
  public boolean V = false;

  protected Random W = new Random();
  public int X = 0;
  public int Y = 1;

  public int Z = 0;

  protected int aa = 300;
  protected boolean ab = false;
  public int ac = 0;
  public int ad = 300;

  private boolean c = true;

  protected boolean ae = false;
  private double d;
  private double e;
  public boolean af = false;
  public int ag;
  public int ah;
  public int ai;

  public dx(eo parameo)
  {
    this.l = parameo;

    a(0.0D, 0.0D, 0.0D);
  }

  public boolean equals(Object paramObject) {
    if ((paramObject instanceof dx)) {
      return ((dx)paramObject).g == this.g;
    }
    return false;
  }

  public int hashCode() {
    return this.g;
  }

  public void l()
  {
    this.G = true;
  }

  protected void a(float paramFloat1, float paramFloat2) {
    this.I = paramFloat1;
    this.J = paramFloat2;
  }

  protected void b(float paramFloat1, float paramFloat2)
  {
    this.v = paramFloat1;
    this.w = paramFloat2;
  }

  public void a(double paramDouble1, double paramDouble2, double paramDouble3) {
    this.p = paramDouble1;
    this.q = paramDouble2;
    this.r = paramDouble3;
    float f1 = this.I / 2.0F;
    float f2 = this.J;

    this.z.c(paramDouble1 - f1, paramDouble2 - this.H + this.R, paramDouble3 - f1, paramDouble1 + f1, paramDouble2 - this.H + this.R + f2, paramDouble3 + f1);
  }

  public void b_()
  {
    m();
  }

  public void m() {
    if ((this.k != null) && (this.k.G)) this.k = null;

    this.X += 1;
    this.K = this.L;
    this.m = this.p;
    this.n = this.q;
    this.o = this.r;
    this.y = this.w;
    this.x = this.v;

    if (r()) {
      if ((!this.ab) && (!this.c)) {
        float f1 = hf.a(this.s * this.s * 0.2000000029802322D + this.t * this.t + this.u * this.u * 0.2000000029802322D) * 0.2F;
        if (f1 > 1.0F) f1 = 1.0F;
        this.l.a(this, "random.splash", f1, 1.0F + (this.W.nextFloat() - this.W.nextFloat()) * 0.4F);
        float f2 = hf.b(this.z.b);
        float f3;
        float f4;
        for (int i1 = 0; i1 < 1.0F + this.I * 20.0F; i1++) {
          f3 = (this.W.nextFloat() * 2.0F - 1.0F) * this.I;
          f4 = (this.W.nextFloat() * 2.0F - 1.0F) * this.I;
          this.l.a("bubble", this.p + f3, f2 + 1.0F, this.r + f4, this.s, this.t - this.W.nextFloat() * 0.2F, this.u);
        }
        for (int i1 = 0; i1 < 1.0F + this.I * 20.0F; i1++) {
          f3 = (this.W.nextFloat() * 2.0F - 1.0F) * this.I;
          f4 = (this.W.nextFloat() * 2.0F - 1.0F) * this.I;
          this.l.a("splash", this.p + f3, f2 + 1.0F, this.r + f4, this.s, this.t, this.u);
        }
      }
      this.N = 0.0F;
      this.ab = true;
      this.Z = 0;
    } else {
      this.ab = false;
    }

    if (this.l.z) {
      this.Z = 0;
    }
    else if (this.Z > 0) {
      if (this.ae) {
        this.Z -= 4;
        if (this.Z < 0) this.Z = 0; 
      }
      else {
        if (this.Z % 20 == 0) {
            // hMod Damage hook: Periodic burn damage
	        BaseEntity burner = new BaseEntity(this);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE,
            		new Object[]{PluginLoader.DamageType.FIRE_TICK, null, burner, 1})) {        	
            	a(null, 1);
            }
        }
        this.Z -= 1;
      }

    }

    if (t()) {
      n();
    }

    if (this.q < -64.0D) {
      o();
    }

    this.c = false;
  }

  protected void n() {
  	// hMod Damage hook: Lava	  
    if(this instanceof jy) {
		BaseEntity defender = new BaseEntity(this);
		if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, 
				new Object[]{PluginLoader.DamageType.LAVA, null, defender, 4}))
			return;
    }
    
    if (!this.ae)
    {
      a(null, 4);
      this.Z = 600;
    }
  }

  protected void o() {
    l();
  }

  public boolean b(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    du localdu = this.z.c(paramDouble1, paramDouble2, paramDouble3);
    List localList = this.l.a(this, localdu);
    if (localList.size() > 0) return false;
    return !this.l.b(localdu);
  }

  public void c(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    if (this.T) {
      this.z.d(paramDouble1, paramDouble2, paramDouble3);
      this.p = ((this.z.a + this.z.d) / 2.0D);
      this.q = (this.z.b + this.H - this.R);
      this.r = ((this.z.c + this.z.f) / 2.0D);
      return;
    }

    double d1 = this.p;
    double d2 = this.r;

    double d3 = paramDouble1;
    double d4 = paramDouble2;
    double d5 = paramDouble3;

    du localdu1 = this.z.b();

    int i1 = (this.A) && (p()) ? 1 : 0;

    if (i1 != 0) {
      double d6 = 0.05D;
      while ((paramDouble1 != 0.0D) && (this.l.a(this, this.z.c(paramDouble1, -1.0D, 0.0D)).size() == 0)) {
        if ((paramDouble1 < d6) && (paramDouble1 >= -d6)) paramDouble1 = 0.0D;
        else if (paramDouble1 > 0.0D) paramDouble1 -= d6; else
          paramDouble1 += d6;
        d3 = paramDouble1;
      }
      while ((paramDouble3 != 0.0D) && (this.l.a(this, this.z.c(0.0D, -1.0D, paramDouble3)).size() == 0)) {
        if ((paramDouble3 < d6) && (paramDouble3 >= -d6)) paramDouble3 = 0.0D;
        else if (paramDouble3 > 0.0D) paramDouble3 -= d6; else
          paramDouble3 += d6;
        d5 = paramDouble3;
      }
    }

    List localList = this.l.a(this, this.z.a(paramDouble1, paramDouble2, paramDouble3));

    for (int i2 = 0; i2 < localList.size(); i2++)
      paramDouble2 = ((du)localList.get(i2)).b(this.z, paramDouble2);
    this.z.d(0.0D, paramDouble2, 0.0D);

    if ((!this.F) && (d4 != paramDouble2)) {
      paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;
    }

    int i2 = (this.A) || ((d4 != paramDouble2) && (d4 < 0.0D)) ? 1 : 0;

    for (int i3 = 0; i3 < localList.size(); i3++) {
      paramDouble1 = ((du)localList.get(i3)).a(this.z, paramDouble1);
    }
    this.z.d(paramDouble1, 0.0D, 0.0D);

    if ((!this.F) && (d3 != paramDouble1)) {
      paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;
    }

    for (int i3 = 0; i3 < localList.size(); i3++)
      paramDouble3 = ((du)localList.get(i3)).c(this.z, paramDouble3);
    this.z.d(0.0D, 0.0D, paramDouble3);

    if ((!this.F) && (d5 != paramDouble3)) {
      paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;
    }

    if ((this.S > 0.0F) && (i2 != 0) && (this.R < 0.05F) && ((d3 != paramDouble1) || (d5 != paramDouble3))) {
      double d7 = paramDouble1;
      double d8 = paramDouble2;
      double d9 = paramDouble3;

      paramDouble1 = d3;
      paramDouble2 = this.S;
      paramDouble3 = d5;

      du localdu2 = this.z.b();
      this.z.b(localdu1);
      localList = this.l.a(this, this.z.a(paramDouble1, paramDouble2, paramDouble3));

      for (int i7 = 0; i7 < localList.size(); i7++)
        paramDouble2 = ((du)localList.get(i7)).b(this.z, paramDouble2);
      this.z.d(0.0D, paramDouble2, 0.0D);

      if ((!this.F) && (d4 != paramDouble2)) {
        paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;
      }

      for (int i7 = 0; i7 < localList.size(); i7++)
        paramDouble1 = ((du)localList.get(i7)).a(this.z, paramDouble1);
      this.z.d(paramDouble1, 0.0D, 0.0D);

      if ((!this.F) && (d3 != paramDouble1)) {
        paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;
      }

      for (int i7 = 0; i7 < localList.size(); i7++)
        paramDouble3 = ((du)localList.get(i7)).c(this.z, paramDouble3);
      this.z.d(0.0D, 0.0D, paramDouble3);

      if ((!this.F) && (d5 != paramDouble3)) {
        paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;
      }

      if (d7 * d7 + d9 * d9 >= paramDouble1 * paramDouble1 + paramDouble3 * paramDouble3) {
        paramDouble1 = d7;
        paramDouble2 = d8;
        paramDouble3 = d9;
        this.z.b(localdu2);
      } else {
        this.R = (float)(this.R + 0.5D);
      }

    }

    this.p = ((this.z.a + this.z.d) / 2.0D);
    this.q = (this.z.b + this.H - this.R);
    this.r = ((this.z.c + this.z.f) / 2.0D);

    this.B = ((d3 != paramDouble1) || (d5 != paramDouble3));
    this.C = (d4 != paramDouble2);
    this.A = ((d4 != paramDouble2) && (d4 < 0.0D));
    this.D = ((this.B) || (this.C));
    a(paramDouble2, this.A);

    if (d3 != paramDouble1) this.s = 0.0D;
    if (d4 != paramDouble2) this.t = 0.0D;
    if (d5 != paramDouble3) this.u = 0.0D;

    double d7 = this.p - d1;
    double d8 = this.r - d2;
    this.L = (float)(this.L + hf.a(d7 * d7 + d8 * d8) * 0.6D);

    if ((this.M) && (i1 == 0)) {
      int i4 = hf.b(this.p);
      int i5 = hf.b(this.q - 0.2000000029802322D - this.H);
      int i6 = hf.b(this.r);
      int i7 = this.l.a(i4, i5, i6);
      if ((this.L > this.b) && (i7 > 0)) {
        this.b += 1;
        cd localcd = ga.m[i7].bq;
        if (this.l.a(i4, i5 + 1, i6) == ga.aS.bh) {
          localcd = ga.aS.bq;
          this.l.a(this, localcd.c(), localcd.a() * 0.15F, localcd.b());
        } else if (!ga.m[i7].bs.d()) {
          this.l.a(this, localcd.c(), localcd.a() * 0.15F, localcd.b());
        }
        ga.m[i7].b(this.l, i4, i5, i6, this);
      }

    }

    int i4 = hf.b(this.z.a);
    int i5 = hf.b(this.z.b);
    int i6 = hf.b(this.z.c);
    int i7 = hf.b(this.z.d);
    int i8 = hf.b(this.z.e);
    int i9 = hf.b(this.z.f);
    for (int i10 = i4; i10 <= i7; i10++) {
      for (int i11 = i5; i11 <= i8; i11++) {
        for (int i12 = i6; i12 <= i9; i12++) {
          int i13 = this.l.a(i10, i11, i12);
          if (i13 > 0) {
            ga.m[i13].a(this.l, i10, i11, i12, this);
          }
        }
      }
    }
    this.R *= 0.4F;

    boolean bool = r();
    if (this.l.c(this.z)) {
      b(1);
      if (!bool) {
        this.Z += 1;
        if (this.Z == 0) this.Z = 300;
      }
    }
    else if (this.Z <= 0) {
      this.Z = (-this.Y);
    }

    if ((bool) && (this.Z > 0)) {
      this.l.a(this, "random.fizz", 0.7F, 1.6F + (this.W.nextFloat() - this.W.nextFloat()) * 0.4F);
      this.Z = (-this.Y);
    }
  }

  protected void a(double paramDouble, boolean paramBoolean) {
    if (paramBoolean) {
      if (this.N > 0.0F) {
        a(this.N);
        this.N = 0.0F;
      }
    }
    else if (paramDouble < 0.0D) this.N = (float)(this.N - paramDouble);
  }

  public boolean p()
  {
    return false;
  }

  public du q() {
    return null;
  }

  protected void b(int paramInt) {
	  // hMod Damage hook: Fire
	  if (this instanceof jy) {
		LivingEntity defender = new LivingEntity((jy)this);
		if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, 
				new Object[]{PluginLoader.DamageType.FIRE, null, defender, paramInt}))
			return;
	  }
	
    if (!this.ae)
      a(null, paramInt);
  }

  protected void a(float paramFloat)
  {
  }

  public boolean r() {
    return this.l.a(this.z.b(0.0D, -0.4000000059604645D, 0.0D), jw.f, this);
  }

  public boolean a(jw paramjw) {
    double d1 = this.q + s();
    int i1 = hf.b(this.p);
    int i2 = hf.d(hf.b(d1));
    int i3 = hf.b(this.r);
    int i4 = this.l.a(i1, i2, i3);
    if ((i4 != 0) && (ga.m[i4].bs == paramjw)) {
      float f1 = cz.b(this.l.b(i1, i2, i3)) - 0.1111111F;
      float f2 = i2 + 1 - f1;
      return d1 < f2;
    }
    return false;
  }

  public float s() {
    return 0.0F;
  }

  public boolean t() {
    return this.l.a(this.z.b(0.0D, -0.4000000059604645D, 0.0D), jw.g);
  }

  public void a(float paramFloat1, float paramFloat2, float paramFloat3) {
    float f1 = hf.c(paramFloat1 * paramFloat1 + paramFloat2 * paramFloat2);
    if (f1 < 0.01F) return;

    if (f1 < 1.0F) f1 = 1.0F;
    f1 = paramFloat3 / f1;
    paramFloat1 *= f1;
    paramFloat2 *= f1;

    float f2 = hf.a(this.v * 3.141593F / 180.0F);
    float f3 = hf.b(this.v * 3.141593F / 180.0F);

    this.s += paramFloat1 * f3 - paramFloat2 * f2;
    this.u += paramFloat2 * f3 + paramFloat1 * f2;
  }

  public float b(float paramFloat)
  {
    int i1 = hf.b(this.p);

    double d1 = (this.z.e - this.z.b) * 0.66D;
    int i2 = hf.b(this.q - this.H + d1);
    int i3 = hf.b(this.r);
    return this.l.j(i1, i2, i3);
  }

  public void b(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2)
  {
    this.m = (this.p = paramDouble1);
    this.n = (this.q = paramDouble2);
    this.o = (this.r = paramDouble3);
    this.x = (this.v = paramFloat1);
    this.y = (this.w = paramFloat2);
    this.R = 0.0F;

    double d1 = this.x - paramFloat1;
    if (d1 < -180.0D) this.x += 360.0F;
    if (d1 >= 180.0D) this.x -= 360.0F;
    a(this.p, this.q, this.r);
    b(paramFloat1, paramFloat2);
  }

  public void c(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {
    this.m = (this.p = paramDouble1);
    this.n = (this.q = paramDouble2 + this.H);
    this.o = (this.r = paramDouble3);
    this.v = paramFloat1;
    this.w = paramFloat2;
    a(this.p, this.q, this.r);
  }

  public float a(dx paramdx) {
    float f1 = (float)(this.p - paramdx.p);
    float f2 = (float)(this.q - paramdx.q);
    float f3 = (float)(this.r - paramdx.r);
    return hf.c(f1 * f1 + f2 * f2 + f3 * f3);
  }

  public double d(double paramDouble1, double paramDouble2, double paramDouble3) {
    double d1 = this.p - paramDouble1;
    double d2 = this.q - paramDouble2;
    double d3 = this.r - paramDouble3;
    return d1 * d1 + d2 * d2 + d3 * d3;
  }

  public double e(double paramDouble1, double paramDouble2, double paramDouble3) {
    double d1 = this.p - paramDouble1;
    double d2 = this.q - paramDouble2;
    double d3 = this.r - paramDouble3;
    return hf.a(d1 * d1 + d2 * d2 + d3 * d3);
  }

  public double b(dx paramdx) {
    double d1 = this.p - paramdx.p;
    double d2 = this.q - paramdx.q;
    double d3 = this.r - paramdx.r;
    return d1 * d1 + d2 * d2 + d3 * d3;
  }

  public void b(fx paramfx) {
  }

  public void c(dx paramdx) {
    if ((paramdx.j == this) || (paramdx.k == this)) return;

    double d1 = paramdx.p - this.p;
    double d2 = paramdx.r - this.r;

    double d3 = hf.a(d1, d2);

    if (d3 >= 0.009999999776482582D) {
      d3 = hf.a(d3);
      d1 /= d3;
      d2 /= d3;

      double d4 = 1.0D / d3;
      if (d4 > 1.0D) d4 = 1.0D;
      d1 *= d4;
      d2 *= d4;

      d1 *= 0.0500000007450581D;
      d2 *= 0.0500000007450581D;

      d1 *= (1.0F - this.U);
      d2 *= (1.0F - this.U);

      f(-d1, 0.0D, -d2);
      paramdx.f(d1, 0.0D, d2);
    }
  }

  public void f(double paramDouble1, double paramDouble2, double paramDouble3) {
    this.s += paramDouble1;
    this.t += paramDouble2;
    this.u += paramDouble3;
  }

  protected void u() {
    this.E = true;
  }

  public boolean a(dx paramdx, int paramInt) {
    u();
    return false;
  }

  public boolean c_()
  {
    return false;
  }

  public boolean v() {
    return false;
  }

  public void b(dx paramdx, int paramInt)
  {
  }

  public boolean c(v paramv)
  {
    String str = w();
    if ((this.G) || (str == null)) {
      return false;
    }
    paramv.a("id", str);
    d(paramv);
    return true;
  }

  public void d(v paramv) {
    paramv.a("Pos", a(new double[] { this.p, this.q, this.r }));
    paramv.a("Motion", a(new double[] { this.s, this.t, this.u }));
    paramv.a("Rotation", a(new float[] { this.v, this.w }));

    paramv.a("FallDistance", this.N);
    paramv.a("Fire", (short)this.Z);
    paramv.a("Air", (short)this.ad);
    paramv.a("OnGround", this.A);

    a(paramv);
  }

  public void e(v paramv)
  {
    eb localeb1 = paramv.k("Pos");
    eb localeb2 = paramv.k("Motion");
    eb localeb3 = paramv.k("Rotation");
    a(0.0D, 0.0D, 0.0D);

    this.s = ((ei)localeb2.a(0)).a;
    this.t = ((ei)localeb2.a(1)).a;
    this.u = ((ei)localeb2.a(2)).a;

    this.m = (this.O = this.p = ((ei)localeb1.a(0)).a);
    this.n = (this.P = this.q = ((ei)localeb1.a(1)).a);
    this.o = (this.Q = this.r = ((ei)localeb1.a(2)).a);

    this.x = (this.v = ((m)localeb3.a(0)).a);
    this.y = (this.w = ((m)localeb3.a(1)).a);

    this.N = paramv.f("FallDistance");
    this.Z = paramv.c("Fire");
    this.ad = paramv.c("Air");
    this.A = paramv.l("OnGround");

    a(this.p, this.q, this.r);

    b(paramv);
  }

  protected final String w() {
    return hn.b(this);
  }
  protected abstract void b(v paramv);

  protected abstract void a(v paramv);

  protected eb a(double[] paramArrayOfDouble) { eb localeb = new eb();
    for (double d1 : paramArrayOfDouble)
      localeb.a(new ei(d1));
    return localeb; }

  protected eb a(float[] paramArrayOfFloat)
  {
    eb localeb = new eb();
    for (float f : paramArrayOfFloat)
      localeb.a(new m(f));
    return localeb;
  }

  public gj a(int paramInt1, int paramInt2)
  {
    return a(paramInt1, paramInt2, 0.0F);
  }

  public gj a(int paramInt1, int paramInt2, float paramFloat) {
    gj localgj = new gj(this.l, this.p, this.q + paramFloat, this.r, new hl(paramInt1, paramInt2));
    localgj.c = 10;
    this.l.a(localgj);
    return localgj;
  }

  public boolean x() {
    return !this.G;
  }

  public boolean y() {
    int i1 = hf.b(this.p);
    int i2 = hf.b(this.q + s());
    int i3 = hf.b(this.r);
    return this.l.d(i1, i2, i3);
  }

  public boolean a(fx paramfx) {
    return false;
  }

  public du d(dx paramdx) {
    return null;
  }

  public void z()
  {
    if (this.k.G) {
      this.k = null;
      return;
    }
    this.s = 0.0D;
    this.t = 0.0D;
    this.u = 0.0D;
    b_();
    this.k.A();

    this.e += this.k.v - this.k.x;
    this.d += this.k.w - this.k.y;

    while (this.e >= 180.0D)
      this.e -= 360.0D;
    while (this.e < -180.0D) {
      this.e += 360.0D;
    }
    while (this.d >= 180.0D)
      this.d -= 360.0D;
    while (this.d < -180.0D) {
      this.d += 360.0D;
    }
    double d1 = this.e * 0.5D;
    double d2 = this.d * 0.5D;

    float f = 10.0F;
    if (d1 > f) d1 = f;
    if (d1 < -f) d1 = -f;
    if (d2 > f) d2 = f;
    if (d2 < -f) d2 = -f;

    this.e -= d1;
    this.d -= d2;

    this.v = (float)(this.v + d1);
    this.w = (float)(this.w + d2);
  }

  public void A() {
    this.j.a(this.p, this.q + j() + this.j.B(), this.r);
  }

  public double B() {
    return this.H;
  }

  public double j() {
    return this.J * 0.75D;
  }

  public void e(dx paramdx) {
    this.d = 0.0D;
    this.e = 0.0D;

    if (paramdx == null) {
      if (this.k != null) {
        c(this.k.p, this.k.z.b + this.k.J, this.k.r, this.v, this.w);
        this.k.j = null;
      }
      this.k = null;
      return;
    }
    if (this.k == paramdx) {
      this.k.j = null;
      this.k = null;
      c(paramdx.p, paramdx.z.b + paramdx.J, paramdx.r, this.v, this.w);
      return;
    }
    if (this.k != null) {
      this.k.j = null;
    }
    if (paramdx.j != null) {
      paramdx.j.k = null;
    }
    this.k = paramdx;
    paramdx.j = this;
  }

  public bd C()
  {
    return null;
  }

  public void D()
  {
  }
}