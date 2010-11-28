/**
 * Interface for living entities
 * @author
 */
public class LivingEntity extends BaseEntity {
    jy livingEntity;

    /**
     * Interface for living entities
     */
    public LivingEntity() {
    }

    /**
     * Interface for living entities
     * @param livingEntity
     */
    public LivingEntity(jy livingEntity) {
        super(livingEntity);
        this.livingEntity = livingEntity;
    }

    /**
     * Returns the entity's health.
     *
     * @return health
     */
    public int getHealth() {
        return livingEntity.aQ;
    }

    /**
     * Increase entity health.
     * @param health
     *          amount of health to increase the players health with.
     */
    public void increaseHealth(int health) {
        entity.a(health);
    }

    /**
     * Sets the entity's health.
     * 20 = max health
     * 1 = 1/2 heart
     * 2 = 1 heart
     *
     * @param health
     */
    public void setHealth(int health) {
        if (health < -1)
            health = -1;
        if (health > 20)
            health = 20;
        livingEntity.aQ = health;
    }
}
