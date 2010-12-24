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
    public HumanEntity(gq human) {
        super(human);
    }

    /**
     * Returns the entity we're wrapping.
     * @return
     */
    public gq getEntity() {
        return (gq) entity;
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
