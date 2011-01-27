/**
 * Boat - Used for manipulating boats
 * 
 * @author James
 */
public class Boat extends BaseVehicle {
    /**
     * Interface for boats.
     * 
     * @param boat
     */
    public Boat(OEntityBoat boat) {
        super(boat);
    }

    /**
     * Create a new Boat at the given position
     * 
     * @param x
     * @param y
     * @param z
     */
    public Boat(double x, double y, double z) {
        super(new OEntityBoat(etc.getMCServer().e, x, y, z));
        etc.getMCServer().e.a(entity);
    }

    /**
     * Returns the entity we're wrapping.
     * 
     * @return
     */
    @Override
    public OEntityBoat getEntity() {
        return (OEntityBoat) entity;
    }
}
