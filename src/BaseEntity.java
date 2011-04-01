/**
 * BaseEntity.java - Class for accessing things that all entities share - X, Y,
 * Z, health.
 * 
 * @author James
 */
public class BaseEntity {
    OEntity entity;

    /**
     * Creates an interface for an entity
     * 
     * @param entity
     */
    public BaseEntity(OEntity entity) {
        this.entity = entity;
    }

    /**
     * Interface for entities.
     */
    public BaseEntity() {
    }

    /**
     * Returns the ID for this mob
     * 
     * @return id
     */
    public int getId() {
        return entity.aB;
    }

    /**
     * Teleports to the provided location
     * 
     * @param x
     * @param rotation
     * @param y
     * @param z
     * @param pitch
     */
    public void teleportTo(double x, double y, double z, float rotation, float pitch) {
        entity.b(x, y, z, rotation, pitch);
    }

    /**
     * Teleports to the other entity
     * 
     * @param ent
     *            entity to teleport to
     */
    public void teleportTo(BaseEntity ent) {
        teleportTo(ent.getX(), ent.getY(), ent.getZ(), ent.getRotation(), ent.getPitch());
    }

    /**
     * Teleports to the provided location
     * 
     * @param location
     *            location to teleport to
     */
    public void teleportTo(Location location) {
        teleportTo(location.x, location.y, location.z, location.rotX, location.rotY);
    }

    /**
     * Returns the entity's X
     * 
     * @return x
     */
    public double getX() {
        return entity.aK;
    }

    /**
     * Sets the entity's X
     * 
     * @param x
     *            x to set
     */
    public void setX(double x) {
        teleportTo(x, getY(), getZ(), getRotation(), getPitch());
    }

    /**
     * Returns the entity's Y
     * 
     * @return y
     */
    public double getY() {
        return entity.aL;
    }

    /**
     * Sets the entity's Y
     * 
     * @param y
     *            y to set
     */
    public void setY(double y) {
        teleportTo(getX(), y, getZ(), getRotation(), getPitch());
    }

    /**
     * Returns the entity's Z
     * 
     * @return z
     */
    public double getZ() {
        return entity.aM;
    }

    /**
     * Sets the entity's Z
     * 
     * @param z
     *            z to set
     */
    public void setZ(double z) {
        teleportTo(getX(), getY(), z, getRotation(), getPitch());
    }

    /**
     * Returns the entity's pitch
     * 
     * @return pitch
     */
    public float getPitch() {
        return entity.aQ;
    }

    /**
     * Sets the entity's pitch
     * 
     * @param pitch
     *            pitch to set
     */
    public void setPitch(float pitch) {
        teleportTo(getX(), getY(), getZ(), getRotation(), pitch);
    }

    /**
     * Returns the entity's rotation
     * 
     * @return rotation
     */
    public float getRotation() {
        return entity.aR;
    }

    /**
     * Sets the entity's rotation
     * 
     * @param rotation
     *            rotation to set
     */
    public void setRotation(float rotation) {
        teleportTo(getX(), getY(), getZ(), rotation, getPitch());
    }

    /**
     * Returns the entity we're wrapping.
     * 
     * @return
     */
    public OEntity getEntity() {
        return entity;
    }

    /**
     * Returns whether or not this entity is a mob
     * 
     * @return true if mob
     */
    public boolean isMob() {
        return entity instanceof OIMobs;
    }

    public static boolean isMob(OEntity entity) {
        return entity instanceof OIMobs;
    }

    /**
     * Returns whether or not this entity is an animal
     * 
     * @return true if animal
     */
    public boolean isAnimal() {
        return entity instanceof OIAnimals;
    }

    public static boolean isAnimal(OEntity entity) {
        return entity instanceof OIAnimals;
    }

    /**
     * Returns true if this entity is a player
     * 
     * @return true if player
     */
    public boolean isPlayer() {
        return entity instanceof OEntityPlayerMP;
    }

    public static boolean isPlayer(OEntity entity) {
        return entity instanceof OEntityPlayerMP;
    }

    /**
     * Returns whether or not this entity is alive
     * 
     * @return true if living entity
     */
    public boolean isLiving() {
        return entity instanceof OEntityLiving;
    }

    public static boolean isLiving(OEntity entity) {
        return entity instanceof OEntityLiving;
    }

    /**
     * Returns the player for this entity
     * 
     * @return player
     */
    public Player getPlayer() {
        if (!isPlayer())
            return null;

        OEntityPlayerMP p = (OEntityPlayerMP) entity;

        return p.getPlayer();
    }

    /**
     * Get the default amount of AirTicks for this entity 20 ticks per second.
     * 
     * @return
     */
    public int getBaseAirTicks() {
        return getEntity().bu;
    }

    /**
     * Set the default amount of AirTicks for this entity 20 ticks per second.
     * 
     * @param ticks
     */
    public void setBaseAirTicks(int ticks) {
        getEntity().bu = ticks;
    }

    /**
     * Get the current NoDamageTicks for this entity
     * 
     * This gets lowered every game tick, until its smaller than half the
     * BaseNoDamageTicks it only registers any damage more than
     * {@link LivingEntity#getLastDamage()}. 20 ticks per second.
     * 
     * @return
     */
    public int getNoDamageTicks() {
        return getEntity().bw;
    }

    /**
     * Set the current NoDamageTicks for this entity
     * 
     * This gets lowered every game tick, until its smaller than half the
     * BaseNoDamageTicks it only registers any damage more than
     * {@link LivingEntity#getLastDamage()}. 20 ticks per second.
     * 
     * @param ticks
     */
    public void setNoDamageTicks(int ticks) {
        getEntity().bw = ticks;
    }

    /**
     * Get the amount of AirTicks left.
     * 
     * This gets lowered every game tick when you are under water. 20 ticks per
     * second.
     * 
     * @return
     */
    public int getAirTicks() {
        return getEntity().bx;
    }

    /**
     * Set the amount of AirTicks left.
     * 
     * This gets lowered every game tick when you are under water. 20 ticks per
     * second.
     * 
     * @return
     */
    public void setAirTicks(int ticks) {
        getEntity().bx = ticks;
    }

    /**
     * Get the amount of FireTicks left.
     * 
     * This gets lowered every game tick when you are on fire. 20 ticks per
     * second.
     * 
     * @return
     */
    public int getFireTicks() {
        return getEntity().bt;
    }

    /**
     * Set the amount of FireTicks left.
     * 
     * This gets lowered every game tick when you are on fire. 20 ticks per
     * second.
     * 
     * @return
     */
    public void setFireTicks(int ticks) {
        getEntity().bt = ticks;
    }
}
