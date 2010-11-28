public class LivingEntity extends BaseEntity {
    jy livingEntity;

    public LivingEntity() {
    }

    public LivingEntity(jy livingEntity) {
        super(livingEntity);
        this.livingEntity = livingEntity;
    }

    /**
     * Returns the players health.
     *
     * @return
     */
    public int getHealth() {
        return livingEntity.aQ;
    }

    /**
     * Increase player health.
     * @param health
     *          amount of health to increase the players health with.
     */
    public void increaseHealth(int health) {
        entity.a(health);
    }

    /**
     * Sets the players health.
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
