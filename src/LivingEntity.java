/**
 * Interface for living entities
 * 
 * @author
 */
public class LivingEntity extends BaseEntity {
    /**
     * Interface for living entities
     */
    public LivingEntity() {
    }

    /**
     * Interface for living entities
     * 
     * @param livingEntity
     */
    public LivingEntity(OEntityLiving livingEntity) {
        super(livingEntity);
    }

    /**
     * Returns the entity we're wrapping.
     * 
     * @return
     */
    @Override
    public OEntityLiving getEntity() {
        return (OEntityLiving) entity;
    }

    /**
     * Returns the entity's health.
     * 
     * @return health
     */
    public int getHealth() {
        return getEntity().W;
    }

    /**
     * Increase entity health.
     * 
     * @param health
     *            amount of health to increase the players health with.
     */
    public void increaseHealth(int health) {
        getEntity().c(health);
    }

    /**
     * Sets the entity's health. 20 = max health 1 = 1/2 heart 2 = 1 heart
     * 
     * @param health
     */
    public void setHealth(int health) {
        if (health < -1)
            health = -1;
        if (health > 20)
            health = 20;
        getEntity().W = health;
    }

    /**
     * Get the amount of ticks this entity is dead. 20 ticks per second.
     * 
     * @return
     */
    public int getDeathTicks() {
        return getEntity().ab;
    }

    /**
     * Set the amount of ticks this entity is dead. 20 ticks per second.
     * 
     * @param ticks
     */
    public void setDeathTicks(int ticks) {
        getEntity().ab = ticks;
    }

    /**
     * Get the amount of ticks this entity will not take damage. (unless it
     * heals) 20 ticks per second.
     * 
     * @return
     */
    public int getBaseNoDamageTicks() {
        return getEntity().C;
    }

    /**
     * Set the amount of ticks this entity will not take damage. (until it
     * heals) 20 ticks per second.
     * 
     * @param ticks
     */
    public void setBaseNoDamageTicks(int ticks) {
        getEntity().C = ticks;
    }

    /**
     * Get the current maximum damage taken during this NoDamageTime
     * 
     * @return
     */
    public int getLastDamage() {
        return getEntity().as;
    }

    /**
     * Set the current maximum damage taken during this NoDamageTime (if any
     * damage is higher than this number the difference will be added)
     * 
     * @param amount
     */
    public void setLastDamage(int amount) {
        getEntity().as = amount;
    }
}
