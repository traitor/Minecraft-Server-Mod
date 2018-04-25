import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;

public class OPacket16BlockItemSwitch extends OPacket
{
  public int a;

  public void a(DataInputStream paramDataInputStream)
  {
    try {
      a = paramDataInputStream.readShort();
    } catch (IOException e) {
      Main.log.log(Level.SEVERE, "Exception while reading data from Packet16: " + e, e);
    }
    if ((a < 0) || (a > 8))
        a = 0;
  }

  public void a(DataOutputStream paramDataOutputStream) {
    try {
      paramDataOutputStream.writeShort(a);
    } catch (IOException e) {
      Main.log.log(Level.SEVERE, "Exception while reading data from Packet16: " + e, e);
    }
  }

  public void a(ONetHandler paramONetHandler) {
    paramONetHandler.a(this);
  }

  public int a() {
    return 2;
  }
}
