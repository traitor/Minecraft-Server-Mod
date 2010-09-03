/* Player packet */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class c extends hn {
    public int a;
    public String b;
    public int c;
    public int d;
    public int e;
    public byte f;
    public byte g;
    public int h;

    public c() {
    }

    public c(fa paramfa) {
        this.a = paramfa.c;
        this.b = paramfa.ap;
        this.c = gh.b(paramfa.k * 32.0D);
        this.d = gh.b(paramfa.l * 32.0D);
        this.e = gh.b(paramfa.m * 32.0D);
        this.f = (byte) (int) (paramfa.q * 256.0F / 360.0F);
        this.g = (byte) (int) (paramfa.r * 256.0F / 360.0F);

        gn localgn = paramfa.ai.b();
        this.h = ((localgn == null) ? 0 : localgn.c);
    }

    public void a(DataInputStream paramDataInputStream) {
        try {
            this.a = paramDataInputStream.readInt();
            this.b = paramDataInputStream.readUTF();
            this.c = paramDataInputStream.readInt();
            this.d = paramDataInputStream.readInt();
            this.e = paramDataInputStream.readInt();
            this.f = paramDataInputStream.readByte();
            this.g = paramDataInputStream.readByte();
            this.h = paramDataInputStream.readShort();
        } catch (IOException e1) {
        }
    }

    public void a(DataOutputStream paramDataOutputStream) {
        try {
            paramDataOutputStream.writeInt(this.a);
            //paramDataOutputStream.writeUTF(etc.getInstance().getUserColor(this.b) + this.b);
            paramDataOutputStream.writeUTF(this.b);
            paramDataOutputStream.writeInt(this.c);
            paramDataOutputStream.writeInt(this.d);
            paramDataOutputStream.writeInt(this.e);
            paramDataOutputStream.writeByte(this.f);
            paramDataOutputStream.writeByte(this.g);
            paramDataOutputStream.writeShort(this.h);
        } catch (IOException e1) {
        }
    }

    public void a(eh parameh) {
        parameh.a(this);
    }

    public int a() {
        return 28;
    }
}
