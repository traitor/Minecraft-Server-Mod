import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import net.minecraft.server.MinecraftServer;

public class by extends Thread
{
  public by(MinecraftServer paramMinecraftServer)
  {
  }

  public void run()
  {
    BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(System.in));
    String str = null;
    try {
      while ((!this.a.g) && (MinecraftServer.a(this.a)) && ((str = localBufferedReader.readLine()) != null))
        this.a.a(str, this.a);
    }
    catch (IOException localIOException) {
      localIOException.printStackTrace();
    }
  }
}