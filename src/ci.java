public class ci extends dw
{
  public int a = 0;

  public ci(em paramem) {
    super(paramem);
    this.i = true;
    a(0.98F, 0.98F);
    this.G = (this.I / 2.0F);
  }

  public ci(em paramem, float paramFloat1, float paramFloat2, float paramFloat3) {
    this(paramem);

    a(paramFloat1, paramFloat2, paramFloat3);

    float f = (float)(Math.random() * 3.141592741012573D * 2.0D);
    this.s = (-hd.a(f * 3.141593F / 180.0F) * 0.02F);
    this.t = 0.2000000029802322D;
    this.u = (-hd.b(f * 3.141593F / 180.0F) * 0.02F);

    this.L = false;
    this.a = 80;

    this.m = paramFloat1;
    this.n = paramFloat2;
    this.o = paramFloat3;
  }

  public boolean c_() {
    return !this.F;
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
      // dynamite check
      Block block = new Block((int) l.a((int)Math.floor(p),(int)Math.floor(q),(int)Math.floor(r)),(int)Math.floor(p),(int)Math.floor(q),(int)Math.floor(r));
      block.setStatus(1);
      if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.EXPLODE, new Object[]{block})) {
          c();
      }
    } else {
      this.l.a("smoke", this.p, this.q + 0.5D, this.r, 0.0D, 0.0D, 0.0D);
    }
  }

  private void c()
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