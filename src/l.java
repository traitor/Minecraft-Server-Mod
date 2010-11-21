import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class l extends io
{
    private int damage = 0;

    public l()
    {
    }

    public l(gh gh1)
    {
        a = gh1.g;
        h = gh1.a.c;
        i = gh1.a.a;
        b = hd.b(gh1.p * 32D);
        c = hd.b(gh1.q * 32D);
        d = hd.b(gh1.r * 32D);
        e = (byte)(int)(gh1.s * 128D);
        f = (byte)(int)(gh1.t * 128D);
        g = (byte)(int)(gh1.u * 128D);
        damage = gh1.getDamage();
    }

    public void a(DataInputStream datainputstream)
    {
        try {
            a = datainputstream.readInt();
            h = datainputstream.readShort();
            i = datainputstream.readByte();
            b = datainputstream.readInt();
            c = datainputstream.readInt();
            d = datainputstream.readInt();
            e = datainputstream.readByte();
            f = datainputstream.readByte();
            g = datainputstream.readByte();
        } catch (IOException ex) {
            Logger.getLogger(l.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void a(DataOutputStream dataoutputstream)
    {
        try {
            dataoutputstream.writeInt(a);
            dataoutputstream.writeShort(h);
            dataoutputstream.writeByte(i);
            dataoutputstream.writeInt(b);
            dataoutputstream.writeInt(c);
            dataoutputstream.writeInt(d);
            dataoutputstream.writeByte(e);
            dataoutputstream.writeByte(f);
            dataoutputstream.writeByte(g);
        } catch (IOException ex) {
            Logger.getLogger(l.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void a(ey ey1)
    {
        ey1.a(this);
    }

    public int a()
    {
        return 22;
    }

    public int getDamage() {
        return damage;
    }

    public int a;
    public int b;
    public int c;
    public int d;
    public byte e;
    public byte f;
    public byte g;
    public int h;
    public int i;
}
