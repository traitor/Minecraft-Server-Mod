import java.io.PrintStream;
import java.util.Random;

public class ai extends ht // Portal blocks (id=90)
{

    public ai(int i, int j)
    {
        super(i, j, jt.x, false);
    }

    public dt d(em em1, int i, int j, int k)
    {
        return null;
    }

    public void a(iq iq1, int i, int j, int k)
    {
        if(iq1.a(i - 1, j, k) == bh || iq1.a(i + 1, j, k) == bh)
        {
            float f = 0.5F;
            float f2 = 0.125F;
            a(0.5F - f, 0.0F, 0.5F - f2, 0.5F + f, 1.0F, 0.5F + f2);
        } else
        {
            float f1 = 0.125F;
            float f3 = 0.5F;
            a(0.5F - f1, 0.0F, 0.5F - f3, 0.5F + f1, 1.0F, 0.5F + f3);
        }
    }

    public boolean a()
    {
        return false;
    }

    public boolean a_(em em1, int i, int j, int k)
    {
        int l = 0;
        int i1 = 0;
        if(em1.a(i - 1, j, k) == fy.ap.bh || em1.a(i + 1, j, k) == fy.ap.bh)
            l = 1;
        if(em1.a(i, j, k - 1) == fy.ap.bh || em1.a(i, j, k + 1) == fy.ap.bh)
            i1 = 1;
        System.out.println((new StringBuilder()).append(l).append(", ").append(i1).toString());
        if(l == i1)
            return false;
        if(em1.a(i - l, j, k - i1) == 0)
        {
            i -= l;
            k -= i1;
        }
        for(int j1 = -1; j1 <= 2; j1++)
        {
            for(int l1 = -1; l1 <= 3; l1++)
            {
                boolean flag = j1 == -1 || j1 == 2 || l1 == -1 || l1 == 3;
                if((j1 == -1 || j1 == 2) && (l1 == -1 || l1 == 3))
                    continue;
                int j2 = em1.a(i + l * j1, j + l1, k + i1 * j1);
                if(flag)
                {
                    if(j2 != fy.ap.bh)
                        return false;
                    continue;
                }
                if(j2 != 0 && j2 != fy.ar.bh)
                    return false;
            }

        }

        em1.i = true;
        for(int k1 = 0; k1 < 2; k1++)
        {
            for(int i2 = 0; i2 < 3; i2++)
                em1.d(i + l * k1, j + i2, k + i1 * k1, fy.be.bh);

        }

        em1.i = false;
        return true;
    }

    public void b(em em1, int i, int j, int k, int l)
    {
        if ((Boolean)etc.getLoader().callHook(PluginLoader.Hook.BLOCK_SPECIAL_PHYSICS, new Object[] {new Block(l, i, j, k)})) return;
        int i1 = 0;
        int j1 = 1;
        if(em1.a(i - 1, j, k) == bh || em1.a(i + 1, j, k) == bh)
        {
            i1 = 1;
            j1 = 0;
        }
        int k1;
        for(k1 = j; em1.a(i, k1 - 1, k) == bh; k1--);
        if(em1.a(i, k1 - 1, k) != fy.ap.bh)
        {
            em1.d(i, j, k, 0);
            return;
        }
        int l1;
        for(l1 = 1; l1 < 4 && em1.a(i, k1 + l1, k) == bh; l1++);
        if(l1 != 3 || em1.a(i, k1 + l1, k) != fy.ap.bh)
        {
            em1.d(i, j, k, 0);
            return;
        }
        boolean flag = em1.a(i - 1, j, k) == bh || em1.a(i + 1, j, k) == bh;
        boolean flag1 = em1.a(i, j, k - 1) == bh || em1.a(i, j, k + 1) == bh;
        if(flag && flag1)
        {
            em1.d(i, j, k, 0);
            return;
        }
        if((em1.a(i + i1, j, k + j1) != fy.ap.bh || em1.a(i - i1, j, k - j1) != bh) && (em1.a(i - i1, j, k - j1) != fy.ap.bh || em1.a(i + i1, j, k + j1) != bh))
        {
            em1.d(i, j, k, 0);
            return;
        } else
        {
            return;
        }
    }

    public boolean a(iq iq1, int i, int j, int k, int l)
    {
        return true;
    }

    public int a(Random random)
    {
        return 0;
    }

    public void a(em em1, int i, int j, int k, dw dw1)
    {
        if(em1.z)
        {
            return;
        } else
        {
            dw1.C();
            return;
        }
    }
}