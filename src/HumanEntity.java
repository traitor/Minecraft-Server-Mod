/**
 *
 * @author 
 */
public class HumanEntity extends LivingEntity {
    /**
     * Constructor
     */
    public HumanEntity() {
    }

    /**
     * Constructor
     * 
     * @param human
     */
    public HumanEntity(fz human) {
        super(human);
    }

    /**
     * Returns the entity we're wrapping.
     * @return
     */
    public fz getEntity() {
        return (fz) entity;
    }

    /**
     * Returns the name
     * 
     * @return
     */
    public String getName() {
        return getEntity().at;
    }
}
