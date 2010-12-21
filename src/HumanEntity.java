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
    public HumanEntity(gp human) {
        super(human);
    }

    /**
     * Returns the entity we're wrapping.
     * @return
     */
    public gp getEntity() {
        return (gp) entity;
    }

    /**
     * Returns the name
     * 
     * @return
     */
    public String getName() {
        return getEntity().aw;
    }
}
