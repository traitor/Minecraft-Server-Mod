import java.util.Random;

public class ix extends fq
{
  public ix(int paramInt)
  {
    super(paramInt);
    this.aX = 1;
    this.aY = 64;
  }

  public boolean a(hh paramhh, ft paramft, el paramel, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (paramInt4 == 0) --paramInt2;
    if (paramInt4 == 1) ++paramInt2;
    if (paramInt4 == 2) --paramInt3;
    if (paramInt4 == 3) ++paramInt3;
    if (paramInt4 == 4) --paramInt1;
    if (paramInt4 == 5) ++paramInt1;

    int i = paramel.a(paramInt1, paramInt2, paramInt3);

    if (i == 0 && !etc.getInstance().blockFire()) {
      paramel.a(paramInt1 + 0.5D, paramInt2 + 0.5D, paramInt3 + 0.5D, "fire.ignite", 1.0F, b.nextFloat() * 0.4F + 0.8F);
      paramel.d(paramInt1, paramInt2, paramInt3, fw.as.bi);
    }

    paramhh.a(1);
    return true;
  }
}