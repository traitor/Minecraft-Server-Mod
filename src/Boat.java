/**
 * Boat - Used for manipulating boats
 * @author James
 */
public class Boat extends BaseVehicle {

    fk boat;

    /**
     * Interface for boats.
     * @param boat
     */
    public Boat(fk boat) {
        super(boat);
        this.boat = boat;
    }
}
