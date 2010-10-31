import java.util.Random;

public class y extends cx
{

    protected y(int k, jr jr1)
    {
        super(k, jr1);
        a(false);
        if(jr1 == jr.g) return; a(true);
    }

    public void b(el el1, int k, int l, int i1, int j1)
    {
        super.b(el1, k, l, i1, j1);
        if(el1.a(k, l, i1) == bi)
            i(el1, k, l, i1);
    }

    private void i(el el1, int k, int l, int i1)
    {
        int j1 = el1.b(k, l, i1);
        el1.i = true;
        el1.a(k, l, i1, bi - 1, j1);
        el1.b(k, l, i1, k, l, i1);
        el1.h(k, l, i1, bi - 1);
        el1.i = false;
    }

    public void a(el el1, int k, int l, int i1, Random random)
    {
        if(bt == jr.g)
        {
            int j1 = random.nextInt(3);
            for(int k1 = 0; k1 < j1; k1++)
            {
                k += random.nextInt(3) - 1;
                l++;
                i1 += random.nextInt(3) - 1;
                int l1 = el1.a(k, l, i1);
                if(l1 == 0)
                {
                    if(j(el1, k - 1, l, i1) || j(el1, k + 1, l, i1) || j(el1, k, l, i1 - 1) || j(el1, k, l, i1 + 1) || j(el1, k, l - 1, i1) || j(el1, k, l + 1, i1))
                    {
                        el1.d(k, l, i1, fw.as.bi);
                        return;
                    }
                    continue;
                }
                if(fw.n[l1].bt.c())
                    return;
            }

        }
    }

    private boolean j(el el1, int k, int l, int i1)
    {
        return el1.c(k, l, i1).e();
    }
}