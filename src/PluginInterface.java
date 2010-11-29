
/**
 * PluginInterface.java - Used for interfacing with other plugins.
 */
public interface PluginInterface {

    /**
     * Returns the name of this interface
     * @return name
     */
    public String getName();

    /**
     * Returns the amount of parameters
     * @return number of parameters
     */
    public int getNumParameters();

    /**
     * Checks parameters
     * @param parameters parameters to check
     * @return string
     */
    public String checkParameters(Object[] parameters);

    /**
     * Runs the command and returns the result
     * @param parameters parameters to run
     * @return object
     */
    public Object run(Object[] parameters);
}
