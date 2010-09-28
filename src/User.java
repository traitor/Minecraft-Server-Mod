
/**
 *
 * @author James
 */
public class User {
    /**
     * User ID - Used in database transactions.
     */
    public int ID = -1;
    /**
     * Name
     */
    public String Name = "";
    /**
     * Prefix/Color
     */
    public String Prefix = "";
    /**
     * List of commands this player can use
     */
    public String[] Commands = new String[] { "" };
    /**
     * List of groups this player is in
     */
    public String[] Groups = new String[] { };
    /**
     * List of allowed IPs for this user
     */
    public String[] IPs = new String[] { "" };
    /**
     * If true this player can ignore restrictions
     */
    public boolean IgnoreRestrictions = false;
    /**
     * If true this player has administrator privileges
     */
    public boolean Administrator = false;
    /**
     * If false this player can't use furnaces or chests and can't modify the
     *  world
     */
    public boolean CanModifyWorld = true;
}
