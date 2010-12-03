/**
 * Boat - Used for manipulating boats
 * @author James
 */
public class Boat extends BaseVehicle {
    /**
     * Interface for boats.
     * @param boat
     */
    public Boat(fl boat) {
        super(boat);
    }
    
    /**
     * Returns the entity we're wrapping.
     * @return
     */ 
    public fl getEntity() {
        return (fl) entity;
    }
}
