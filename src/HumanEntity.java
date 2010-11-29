
/**
 *
 * @author 
 */
public class HumanEntity extends LivingEntity {

    fx human;

    /**
     *
     */
    public HumanEntity() {
    }

    /**
     *
     * @param human
     */
    public HumanEntity(fx human) {
        super((jy) human);
        this.human = human;
    }
}
