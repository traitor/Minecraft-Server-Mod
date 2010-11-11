
import java.util.Map;

/**
 * Kit.java - Contains the stuff for a kit.
 * 
 * @author James
 */
public class Kit {

    /**
     * Kit ID - Used in database transactions
     */
    public int ID;
    /**
     * Kit Name
     */
    public String Name;
    /**
     * List of Item IDs and amounts to give
     */
    public Map<String, Integer> IDs;
    /**
     * Delay between uses
     */
    public int Delay;
    /**
     * Group that can use this kit.
     */
    public String Group;
}
