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
    public HumanEntity(fy human) {
        super(human);
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
