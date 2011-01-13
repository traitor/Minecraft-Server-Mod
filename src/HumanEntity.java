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
    public HumanEntity(hl human) {
        super(human);
    }

    /**
     * Returns the entity we're wrapping.
     * @return
     */
    public hl getEntity() {
        return (hl) entity;
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
