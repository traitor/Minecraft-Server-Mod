
/**
 * Group.java - Group stuff.
 * 
 * @author James
 */
public class Group {

    /**
     * Group ID - used for database transactions
     */
    public int ID;
    /**
     * Group Name
     */
    public String Name;
    /**
     * Group Prefix/Color
     */
    public String Prefix;
    /**
     * List of commands this group can use
     */
    public String[] Commands;
    /**
     * List of groups this group inherits/has control over
     */
    public String[] InheritedGroups;
    /**
     * Is true if it's the default group
     */
    public boolean DefaultGroup;
    /**
     * If true all players within this group ignore restrictions
     */
    public boolean IgnoreRestrictions;
    /**
     * If true all players within this group have administrator privileges
     */
    public boolean Administrator;
    /**
     * If false this player can not modify chests or furnaces and can not
     * destroy/create blocks
     */
    public boolean CanModifyWorld = true;
}
