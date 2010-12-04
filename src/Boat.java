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
     * Returns the entity we're wrapping.
     * @return
     */ 
    public fm getEntity() {
        return (fm) entity;
    }
}
