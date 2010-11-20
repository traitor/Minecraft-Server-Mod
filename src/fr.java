import java.util.Random;

public class fr extends fy // Sand blocks (+ gravel inherits)
{

    public fr(int i, int j)
    {
        super(i, j, jt.m);
    }

    public void e(em em1, int i, int j, int k)
    {
        if ((Boolean)etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Object[] {new Block(bh, i, j, k), true})) return;
        em1.h(i, j, k, bh);
    }

    public void b(em em1, int i, int j, int k, int l)
    {
        if ((Boolean)etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Object[] {new Block(l, i, j, k), false})) return;
        em1.h(i, j, k, bh);
    }

    public void a(em em1, int i, int j, int k, Random random)
    {
        h(em1, i, j, k);
    }

    private void h(em em1, int i, int j, int k)
    {
        int l = i;
        int i1 = j;
        int j1 = k;
        if(g(em1, l, i1 - 1, j1) && i1 >= 0)
        {
            hy hy1 = new hy(em1, (float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, bh);
            if(a)
                while(!hy1.F)
                    hy1.b_();
            else
                em1.a(hy1);
        }
    }

    public int b()
    {
        return 3;
    }

    public static boolean g(em em1, int i, int j, int k)
    {
        int l = em1.a(i, j, k);
        if(l == 0)
            return true;
        if(l == fy.ar.bh)
            return true;
        jt jt1 = fy.m[l].bs;
        if(jt1 == jt.f)
            return true;
        return jt1 == jt.g;
    }

    public static boolean a = false;

}
