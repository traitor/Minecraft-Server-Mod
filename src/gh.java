import java.util.Random;
import java.util.logging.Logger;

public class gh extends dw
{

    public gh(em em1, double d1, double d2, double d3,
            hj hj1)
    {
        super(em1);
        b = 0;
        f = hj1.d;
        d = (float)(Math.random() * 3.1415926535897931D * 2D);
        a(0.25F, 0.25F);
        G = I / 2.0F;
        a(d1, d2, d3);
        a = hj1;
        v = (float)(Math.random() * 360D);
        s = (float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D);
        t = 0.20000000298023224D;
        u = (float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D);
        L = false;
    }

    public gh(em em1)
    {
        super(em1);
        b = 0;
        f = 0;
        d = (float)(Math.random() * 3.1415926535897931D * 2D);
        a(0.25F, 0.25F);
        G = I / 2.0F;
    }

    public void b_()
    {
        super.b_();
        if(c > 0)
            c--;
        m = p;
        n = q;
        o = r;
        t -= 0.039999999105930328D;
        if(l.c(hd.b(p), hd.b(q), hd.b(r)) == jt.g)
        {
            t = 0.20000000298023224D;
            s = (V.nextFloat() - V.nextFloat()) * 0.2F;
            u = (V.nextFloat() - V.nextFloat()) * 0.2F;
            l.a(this, "random.fizz", 0.4F, 2.0F + V.nextFloat() * 0.4F);
        }
        g(p, q, r);
        r();
        c(s, t, u);
        float f1 = 0.98F;
        if(A)
        {
            f1 = 0.5880001F;
            int i = l.a(hd.b(p), hd.b(z.b) - 1, hd.b(r));
            if(i > 0)
                f1 = fy.m[i].bt * 0.98F;
        }
        s *= f1;
        t *= 0.98000001907348633D;
        u *= f1;
        if(A)
            t *= -0.5D;
        e++;
        b++;
        if(b >= 6000)
            l();
    }

    public boolean r()
    {
        return l.a(z, jt.f, this);
    }

    private boolean g(double d1, double d2, double d3)
    {
        int i = hd.b(d1);
        int j = hd.b(d2);
        int k = hd.b(d3);
        double d4 = d1 - (double)i;
        double d5 = d2 - (double)j;
        double d6 = d3 - (double)k;
        if(fy.o[l.a(i, j, k)])
        {
            boolean flag = !fy.o[l.a(i - 1, j, k)];
            boolean flag1 = !fy.o[l.a(i + 1, j, k)];
            boolean flag2 = !fy.o[l.a(i, j - 1, k)];
            boolean flag3 = !fy.o[l.a(i, j + 1, k)];
            boolean flag4 = !fy.o[l.a(i, j, k - 1)];
            boolean flag5 = !fy.o[l.a(i, j, k + 1)];
            byte byte0 = -1;
            double d7 = 9999D;
            if(flag && d4 < d7)
            {
                d7 = d4;
                byte0 = 0;
            }
            if(flag1 && 1.0D - d4 < d7)
            {
                d7 = 1.0D - d4;
                byte0 = 1;
            }
            if(flag2 && d5 < d7)
            {
                d7 = d5;
                byte0 = 2;
            }
            if(flag3 && 1.0D - d5 < d7)
            {
                d7 = 1.0D - d5;
                byte0 = 3;
            }
            if(flag4 && d6 < d7)
            {
                d7 = d6;
                byte0 = 4;
            }
            if(flag5 && 1.0D - d6 < d7)
            {
                double d8 = 1.0D - d6;
                byte0 = 5;
            }
            float f1 = V.nextFloat() * 0.2F + 0.1F;
            if(byte0 == 0)
                s = -f1;
            if(byte0 == 1)
                s = f1;
            if(byte0 == 2)
                t = -f1;
            if(byte0 == 3)
                t = f1;
            if(byte0 == 4)
                u = -f1;
            if(byte0 == 5)
                u = f1;
        }
        return false;
    }

    protected void b(int i)
    {
        a(null, i);
    }

    public boolean a(dw dw1, int i)
    {
        f -= i;
        if(f <= 0)
            l();
        return false;
    }

    public void a(v v1)
    {
        v1.a("Health", (byte)f);
        v1.a("Age", (short)b);
        v1.a("Item", a.a(new v()));
    }

    public void b(v v1)
    {
        f = v1.c("Health") & 0xff;
        b = v1.c("Age");
        v v2 = v1.j("Item");
        a = new hj(v2);
    }

    public int getDamage() {
        return f;
    }

    public void setDamage(int damage) {
        f = damage;
    }

    public void b(fv fv1)
    {
        if(l.z)
            return;
        int i = a.a;
        if(c == 0 && fv1.ak.a(a))
        {
            Item item = new Item(a.c, a.a);
            item.setDamage(a.d);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_PICK_UP, new Object[]{fv1, item})) {
                if (f > 0) a.d = f;
                l.a(this, "random.pop", 0.2F, ((V.nextFloat() - V.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                fv1.c(this, i);
                l();
            }
        }
    }

    public hj a;
    private int e;
    public int b;
    public int c;
    private int f;
    public float d;
}
