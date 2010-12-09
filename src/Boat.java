/**
 * Boat - Used for manipulating boats
 * @author James
 */
public class Boat extends BaseVehicle {
    /**
     * Interface for boats.
     * @param boat
     */
    public Boat(fm boat) {
        super(boat);
    }
    
    /**
     * Create a new Boat at the given position
     * @param x
     * @param y
     * @param z
     * @param Type Type of minecart (0=Minecart, 1=StorageCart, 2=PoweredMinecart)
     */
    public Boat(double x, double y, double z) {
        super(new fm(etc.getMCServer().e, x, y, z)); 
        etc.getMCServer().e.a(entity);        
    }
    
    /**
     * Returns the entity we're wrapping.
     * @return
     */ 
    public fm getEntity() {
        return (fm) entity;
    }
}
