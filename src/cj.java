public class cj extends ea
{
  public int a = 0;

  public cj(eq parameq) {
    super(parameq);
    this.i = true;
    a(0.98F, 0.98F);
    this.H = (this.J / 2.0F);
  }

  public cj(eq parameq, double paramDouble1, double paramDouble2, double paramDouble3) {
    this(parameq);

    a(paramDouble1, paramDouble2, paramDouble3);

    float f = (float)(Math.random() * 3.141592741012573D * 2.0D);
    this.s = (-hh.a(f * 3.141593F / 180.0F) * 0.02F);
    this.t = 0.2000000029802322D;
    this.u = (-hh.b(f * 3.141593F / 180.0F) * 0.02F);

    this.M = false;
    this.a = 80;

    this.m = paramDouble1;
    this.n = paramDouble2;
    this.o = paramDouble3;
  }

  public boolean c_() {
    return !this.G;
  }

  public void b_() {
    this.m = this.p;
    this.n = this.q;
    this.o = this.r;

    this.t -= 0.03999999910593033D;
    c(this.s, this.t, this.u);
    this.s *= 0.9800000190734863D;
    this.t *= 0.9800000190734863D;
    this.u *= 0.9800000190734863D;

    if (this.A) {
      this.s *= 0.699999988079071D;
      this.u *= 0.699999988079071D;
      this.t *= -0.5D;
    }

    if (this.a-- <= 0) {
      l();
      c();
    } else {
      this.l.a("smoke", this.p, this.q + 0.5D, this.r, 0.0D, 0.0D, 0.0D);
    }
  }

  public void c()
  {
    float f = 4.0F;
    this.l.a(null, this.p, this.q, this.r, f);
  }

  protected void a(v paramv)
  {
    paramv.a("Fuse", (byte)this.a);
  }

  protected void b(v paramv) {
    this.a = paramv.b("Fuse");
  }
}