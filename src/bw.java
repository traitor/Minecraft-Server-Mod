import net.minecraft.server.MinecraftServer;
 
public final class bw extends Thread
{
	private Server wrapper;
			
	public bw(String paramString, MinecraftServer paramMinecraftServer)
	{
		super(paramString);
		wrapper = Server.attachTo(paramMinecraftServer);
	}
	
	public void run() { 
		wrapper.run();
	}
}