import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class _do extends gd {
    public _do() {
        this.i = true;
        this.h = true;
    }

    public _do(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, float paramFloat1, float paramFloat2, boolean paramBoolean) {
        this.a = paramDouble1;
        this.b = paramDouble2;
        this.d = paramDouble3;
        this.c = paramDouble4;
        this.e = paramFloat1;
        this.f = paramFloat2;
        this.g = paramBoolean;
        this.i = true;
        this.h = true;
    }

    public void a(DataInputStream paramDataInputStream) {
        try {
            this.a = paramDataInputStream.readDouble();
            this.b = paramDataInputStream.readDouble();
            this.d = paramDataInputStream.readDouble();
            this.c = paramDataInputStream.readDouble();
            this.e = paramDataInputStream.readFloat();
            this.f = paramDataInputStream.readFloat();
        } catch (IOException e1) {
        }
        super.a(paramDataInputStream);
    }

    public void a(DataOutputStream paramDataOutputStream) {
        try {
            paramDataOutputStream.writeDouble(this.a);
            paramDataOutputStream.writeDouble(this.b);
            paramDataOutputStream.writeDouble(this.d);
            paramDataOutputStream.writeDouble(this.c);
            paramDataOutputStream.writeFloat(this.e);
            paramDataOutputStream.writeFloat(this.f);
        } catch (IOException e1) {
        }
        super.a(paramDataOutputStream);
    }

    public int a() {
        return 41;
    }
}
