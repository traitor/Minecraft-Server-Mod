import java.io.PrintStream;
import java.util.Random;

public class ag extends hr
{

  private Portal portal;

  public ag(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2, jr.x, false);
    portal = new Portal("null");
    System.out.println("ag.init" + paramInt1 + "," + paramInt2);
  }

  public ds d(el paramel, int paramInt1, int paramInt2, int paramInt3) {
    return null;
  }

  public void a(io paramio, int paramInt1, int paramInt2, int paramInt3)
  {
    float f1;
    float f2;
    if ((paramio.a(paramInt1 - 1, paramInt2, paramInt3) == this.bi) || (paramio.a(paramInt1 + 1, paramInt2, paramInt3) == this.bi)) {
      f1 = 0.5F;
      f2 = 0.125F;
      a(0.5F - f1, 0.0F, 0.5F - f2, 0.5F + f1, 1.0F, 0.5F + f2);
    } else {
      f1 = 0.125F;
      f2 = 0.5F;
      a(0.5F - f1, 0.0F, 0.5F - f2, 0.5F + f1, 1.0F, 0.5F + f2);
    }
  }

  public boolean a() {
    return false;
  }

  public boolean a_(el paramel, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = 0;
    int j = 0;
    if ((paramel.a(paramInt1 - 1, paramInt2, paramInt3) == fw.aq.bi) || (paramel.a(paramInt1 + 1, paramInt2, paramInt3) == fw.aq.bi)) i = 1;
    if ((paramel.a(paramInt1, paramInt2, paramInt3 - 1) == fw.aq.bi) || (paramel.a(paramInt1, paramInt2, paramInt3 + 1) == fw.aq.bi)) j = 1;

    if (i == j) return false;

    portal = etc.getDataSource().getPortal(paramInt1,paramInt2,paramInt3);
    if (portal == null) {
        System.out.println("No portal at: "+paramInt1+","+paramInt2+","+paramInt3+" found, making new one");
        portal = new Portal(paramInt1+","+paramInt2+","+paramInt3);
        etc.getDataSource().addPortal(portal);
       }
    
	
	if ((Boolean)etc.getLoader().callHook(PluginLoader.Hook.PORTALCREATE, new Object[] {portal}))
	{
		return false; // Portal creation interrupted
	}
	
	portal.setActive(true);
    

    if (paramel.a(paramInt1 - i, paramInt2, paramInt3 - j) == 0) {
      paramInt1 -= i;
      paramInt3 -= j;
    }
    int m;
	int k;
    for (k = -1; k <= 2; k++) {
      for (m = -1; m <= 3; m++) {
        int n = (k == -1) || (k == 2) || (m == -1) || (m == 3) ? 1 : 0;
        if (((k == -1) || (k == 2)) && ((m == -1) || (m == 3)))
          continue;
        int i1 = paramel.a(paramInt1 + i * k, paramInt2 + m, paramInt3 + j * k);

        if (n != 0) {
          if (i1 != fw.aq.bi) return false;
        }
        else if ((i1 != 0) && (i1 != fw.as.bi)) return false;
      }

    }

    paramel.i = true;
    for (k = 0; k < 2; k++) {
      for (m = 0; m < 3; m++) {
        paramel.d(paramInt1 + i * k, paramInt2 + m, paramInt3 + j * k, fw.bf.bi); // Set 6 blocks to purple
        // Add block to portal array
        portal.addCurtain(paramInt1 + i * k, paramInt2 + m, paramInt3 + j * k);
      }
    }
    paramel.i = false;

    etc.getDataSource().changePortal(portal);
    
    
    return true;
  }

  public void b(el paramel, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i = 0;
    int j = 1;
	//System.out.println("portal check at: " + paramInt1 + "," + paramInt2 + "," + paramInt3 + " : " + paramInt4);
    if ((paramel.a(paramInt1 - 1, paramInt2, paramInt3) == this.bi) || (paramel.a(paramInt1 + 1, paramInt2, paramInt3) == this.bi)) {
      i = 1;
      j = 0;
    }

    int k = paramInt2;
    while (paramel.a(paramInt1, k - 1, paramInt3) == this.bi) {
      k--;
    }
    if (paramel.a(paramInt1, k - 1, paramInt3) != fw.aq.bi) {
      portal.collapseCurtain(paramInt1, paramInt2, paramInt3);
      paramel.d(paramInt1, paramInt2, paramInt3, 0);
      return;
    }

    int m = 1;
    while ((m < 4) && (paramel.a(paramInt1, k + m, paramInt3) == this.bi)) {
      m++;
    }
    if ((m != 3) || (paramel.a(paramInt1, k + m, paramInt3) != fw.aq.bi)) {
      portal.collapseCurtain(paramInt1, paramInt2, paramInt3);
      paramel.d(paramInt1, paramInt2, paramInt3, 0);
      return;
    }

    int n = (paramel.a(paramInt1 - 1, paramInt2, paramInt3) == this.bi) || (paramel.a(paramInt1 + 1, paramInt2, paramInt3) == this.bi) ? 1 : 0;
    int i1 = (paramel.a(paramInt1, paramInt2, paramInt3 - 1) == this.bi) || (paramel.a(paramInt1, paramInt2, paramInt3 + 1) == this.bi) ? 1 : 0;
    if ((n != 0) && (i1 != 0)) {
      portal.collapseCurtain(paramInt1, paramInt2, paramInt3);
      paramel.d(paramInt1, paramInt2, paramInt3, 0);
      return;
    }

    if (((paramel.a(paramInt1 + i, paramInt2, paramInt3 + j) != fw.aq.bi) || (paramel.a(paramInt1 - i, paramInt2, paramInt3 - j) != this.bi)) && ((paramel.a(paramInt1 - i, paramInt2, paramInt3 - j) != fw.aq.bi) || (paramel.a(paramInt1 + i, paramInt2, paramInt3 + j) != this.bi)))
    {
      portal.collapseCurtain(paramInt1, paramInt2, paramInt3);
      paramel.d(paramInt1, paramInt2, paramInt3, 0);
      return;
    }
  }

  public boolean a(io paramio, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    return true;
  }

  public int a(Random paramRandom) {
    return 0;
  }

  public void a(el paramel, int paramInt1, int paramInt2, int paramInt3, dv paramdv)
  {
    if (paramel.z) return;

    paramdv.C();
  }
}