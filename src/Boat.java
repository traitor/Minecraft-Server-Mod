/**
 * Boat - Used for manipulating boats
 * @author James
 */
public class Boat extends BaseVehicle {
    /**
     * Interface for boats.
     * @param boat
     */
    public Boat(gc boat) {
        super(boat);
    }
    
    /**
     * Create a new Boat at the given position
     * @param x
     * @param y
     * @param z
     */
    public Boat(double x, double y, double z) {
        super(new gc(etc.getMCServer().e, x, y, z));
        etc.getMCServer().e.a(entity);        
    }
    
    /**
     * Returns the entity we're wrapping.
     * @return
     */ 
    public gc getEntity() {
        return (gc) entity;
    }
}
