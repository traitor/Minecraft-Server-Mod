public interface PluginInterface
{
	public String getName();
	public int getNumParameters();
	public String checkParameters(Object[] parameters);
	public Object run(Object[] parameters);
}