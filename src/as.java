import java.util.Random;

public class as extends gb
{
  protected as(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2, jx.u);
    a(true);
  }

  public void a(ep paramep, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
    if (paramep.a(paramInt1, paramInt2 + 1, paramInt3) == 0) {
      int i = 1;
      while (paramep.a(paramInt1, paramInt2 - i, paramInt3) == this.bh) {
        i++;
      }
      if (i < 3) {
        int j = paramep.b(paramInt1, paramInt2, paramInt3);
        if (j == 15) {
          paramep.d(paramInt1, paramInt2 + 1, paramInt3, this.bh);
          paramep.b(paramInt1, paramInt2, paramInt3, 0);
        } else {
          paramep.b(paramInt1, paramInt2, paramInt3, j + 1);
        }
      }
    }
  }

  public dv d(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
    float f = 0.0625F;
    return dv.b(paramInt1 + f, paramInt2, paramInt3 + f, paramInt1 + 1 - f, paramInt2 + 1 - f, paramInt3 + 1 - f);
  }

  public int a(int paramInt)
  {
    if (paramInt == 1) return this.bg - 1;
    if (paramInt == 0) return this.bg + 1;
    return this.bg;
  }

  public boolean a()
  {
    return false;
  }

  public boolean a(ep paramep, int paramInt1, int paramInt2, int paramInt3)
  {
    if (!super.a(paramep, paramInt1, paramInt2, paramInt3)) return false;

    return f(paramep, paramInt1, paramInt2, paramInt3);
  }

  public void b(ep paramep, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (!f(paramep, paramInt1, paramInt2, paramInt3)) {
      a_(paramep, paramInt1, paramInt2, paramInt3, paramep.b(paramInt1, paramInt2, paramInt3));
      paramep.d(paramInt1, paramInt2, paramInt3, 0);
    }
  }

  public boolean f(ep paramep, int paramInt1, int paramInt2, int paramInt3) {
    if (paramep.c(paramInt1 - 1, paramInt2, paramInt3).a()) return false;
    if (paramep.c(paramInt1 + 1, paramInt2, paramInt3).a()) return false;
    if (paramep.c(paramInt1, paramInt2, paramInt3 - 1).a()) return false;
    if (paramep.c(paramInt1, paramInt2, paramInt3 + 1).a()) return false;
    int i = paramep.a(paramInt1, paramInt2 - 1, paramInt3);
    return (i == gb.aV.bh) || (i == gb.E.bh);
  }

  public void a(ep paramep, int paramInt1, int paramInt2, int paramInt3, dy paramdy) {
    // hMod Damage hook: Cactus
	if (paramdy instanceof jz) {
		LivingEntity defender = new LivingEntity((jz) paramdy);
	    if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new Object[]{PluginLoader.DamageType.CACTUS, null, defender, 1})) {
	        paramdy.a(null, 1);
	    }
	} else {
		paramdy.a(null, 1);
	}
  }
}

/* Location:           D:\minecraft\server\minecraft_server.jar
 * Qualified Name:     as
 * JD-Core Version:    0.6.0
 */