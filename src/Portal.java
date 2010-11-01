
import java.util.ArrayList;
/**
 * Portal.java - Contains the stuff for a portal (Name, location, etc.). Based
 * on warps
 * @author mrsheen
 */
public class Portal {

    //private ag portal; // hopefully, not needed, cause we only have one
    private ArrayList<Integer> curtainBlocks = new ArrayList<Integer>();
    private int activeCurtainBlocks = 0;
    private boolean active = false;
    /**
     * Portal ID - Used in database transactions
     */
    public int ID;
    /**
     * Portal name
     */
    /**
     * Portal group
     */
    public String Name, Group, Label;
    /**
     * Portal's location
     */
    public Location loc1, loc2;
    
    public Portal(String name){
        // By default, the portal name is the string containing the x,y,z location
        // of the block clicked to create it
        // It is advisable to use getPortal(x,y,z) unless a name is explicitly set
        // by a plugin
        this.Name = name;
        this.Label = name;
        
        Location nullBlock = new Location(0,128,0);
        loc1 = loc2 = nullBlock;
        
        
    }
    
    public boolean getActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        System.out.println("portal "+this.Label+" activated");
        this.active = active;
    }
    
    public void setName(String name) {
        this.Name = name;
        etc.getDataSource().changePortal(this);
    }
    
    public void setLabel(String label) {
        this.Label = label;
        etc.getDataSource().changePortal(this);
    }
    
    public void collapseCurtain(int x, int y, int z) {
        // set this block in the portal to removed
        // Check if this location is one of our curtain pieces
        int pt1, pt2, pt3;
        for (int i=0; i<(curtainBlocks.size()/3);i++) {
            pt1 = curtainBlocks.get(i*3);
            pt2 = curtainBlocks.get(i*3+1);
            pt3 = curtainBlocks.get(i*3+2);
            if (x == pt1 && y == pt2 && z == pt3) {
                 curtainBlocks.remove(i*3+2);
                 curtainBlocks.remove(i*3+1);
                 curtainBlocks.remove(i*3);
                //System.out.println("block removed");
                activeCurtainBlocks--;
                break;
            }
        }
        // check if we have removed all, if so, portal is deactivated
        if (activeCurtainBlocks <= 1) {
            System.out.println("portal "+this.Label+" deactivated");
            this.active = false;
        }
    }
    
    public void addCurtain(int x, int y, int z) {
        // Add this location to our curtain
        
        curtainBlocks.add(x);
        curtainBlocks.add(y);
        curtainBlocks.add(z);
        activeCurtainBlocks++;
        
        // Check for lowest block
        if (y < (int)Math.floor(loc1.y)) {
            loc1 = new Location(x,y,z);
            etc.getDataSource().changePortal(this);
        }
        else if (y < (int)Math.floor(loc2.y)) {
            loc2 = new Location(x,y,z);
            etc.getDataSource().changePortal(this);
        }
        
    }
    
    public boolean containsLoc(int x, int y, int z) {
        // Check if this location is one of our curtain pieces
        if (x == (int)Math.floor(loc1.x) && y == (int)Math.floor(loc1.y) && z == (int)Math.floor(loc1.z)) {
            return true;
        }
        else if (x == (int)Math.floor(loc2.x) && y == (int)Math.floor(loc2.y) && z == (int)Math.floor(loc2.z)) {
            return true;
        }
        //System.out.println("no portal found at: "+x+","+y+","+z);
        return false;
    }
}
