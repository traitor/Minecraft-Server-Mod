
/**
 * BaseEntity.java - Class for accessing things that all entities share - X, Y,
 * Z, health.
 * @author James
 */
public class BaseEntity {
    ea entity;
    
    /**
     * Creates an interface for an entity
     * @param entity
     */
    public BaseEntity(ea entity) {
        this.entity = entity;
    }

    /**
     * Interface for entities.
     */
    public BaseEntity() {
    }

    /**
     * Returns the ID for this mob
     * @return id
     */
    public int getId() {
        return entity.g;
    }

    /**
     * Teleports to the provided location
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
     * @param ent entity to teleport to
     */
    public void teleportTo(BaseEntity ent) {
        teleportTo(ent.getX(), ent.getY(), ent.getZ(), ent.getRotation(), ent.getPitch());
    }

    /**
     * Teleports to the provided location
     * @param location location to teleport to
     */
    public void teleportTo(Location location) {
        teleportTo(location.x, location.y, location.z, location.rotX, location.rotY);
    }

    /**
     * Returns the entity's X
     * @return x
     */
    public double getX() {
        return entity.p;
    }

    /**
     * Sets the entity's X
     * @param x x to set
     */
    public void setX(double x) {
        teleportTo(x, getY(), getZ(), getRotation(), getPitch());
    }

    /**
     * Returns the entity's Y
     * @return y
     */
    public double getY() {
        return entity.q;
    }

    /**
     * Sets the entity's Y
     * @param y y to set
     */
    public void setY(double y) {
        teleportTo(getX(), y, getZ(), getRotation(), getPitch());
    }

    /**
     * Returns the entity's Z
     * @return z
     */
    public double getZ() {
        return entity.r;
    }

    /**
     * Sets the entity's Z
     * @param z z to set
     */
    public void setZ(double z) {
        teleportTo(getX(), getY(), z, getRotation(), getPitch());
    }

    /**
     * Returns the entity's pitch
     * @return pitch
     */
    public float getPitch() {
        return entity.w;
    }

    /**
     * Sets the entity's pitch
     * @param pitch pitch to set
     */
    public void setPitch(float pitch) {
        teleportTo(getX(), getY(), getZ(), getRotation(), pitch);
    }

    /**
     * Returns the entity's rotation
     * @return rotation
     */
    public float getRotation() {
        return entity.v;
    }

    /**
     * Sets the entity's rotation
     * @param rotation rotation to set
     */
    public void setRotation(float rotation) {
        teleportTo(getX(), getY(), getZ(), rotation, getPitch());
    }
    
    /**
     * Returns the entity we're wrapping.
     * @return
     */
    public ea getEntity() {
        return entity;
    }

    /**
     * Returns whether or not this entity is a mob
     * @return true if mob
     */
    public boolean isMob() {
        return entity instanceof gd || entity instanceof fz;
    }

    /**
     * Returns whether or not this entity is an animal
     * @return true if animal
     */
    public boolean isAnimal() {
        return entity instanceof ax;
    }

    /**
     * Returns true if this entity is a player
     * @return true if player
     */
    public boolean isPlayer() {
        return entity instanceof et;
    }
	
    /**
     * Returns whether or not this entity is alive
     * @return true if living entity
     */
    public boolean isLiving() {
        return entity instanceof ka;
    }

    /**
     * Returns the player for this entity
     * @return player
     */
    public Player getPlayer() {
        if (!isPlayer())
            return null;

        et p = (et) entity;
        Player player = etc.getServer().getPlayer(p.at);
        player.setUser(p);

        return player;
    }

    /**
     * Get the default amount of AirTicks for this entity
     * 20 ticks per second.
     * 
     * @return
     */
    public int getBaseAirTicks() {
        return getEntity().aa;
    }

    /**
     * Set the default amount of AirTicks for this entity
     * 20 ticks per second.
     * 
     * @param ticks
     */
    public void setBaseAirTicks(int ticks) {
        getEntity().aa = ticks;
    }

    /**
     * Get the current NoDamageTicks for this entity
     * 
     * This gets lowered every game tick, until its smaller than half the BaseNoDamageTicks
     * it only registers any damage more than {@link LivingEntity#getLastDamage()}.
     * 20 ticks per second.
     * 
     * @return
     */
    public int getNoDamageTicks() {
        return getEntity().ac;
    }

    /**
     * Set the current NoDamageTicks for this entity
     * 
     * This gets lowered every game tick, until its smaller than half the BaseNoDamageTicks
     * it only registers any damage more than {@link LivingEntity#getLastDamage()}.
     * 20 ticks per second.
     * 
     * @param ticks
     */
    public void setNoDamageTicks(int ticks) {
        getEntity().ac = ticks;
    }

    /**
     * Get the amount of AirTicks left.
     * 
     * This gets lowered every game tick when you are under water. 
     * 20 ticks per second.
     * 
     * @return
     */
    public int getAirTicks() {
        return getEntity().ad;
    }

    /**
     * Set the amount of AirTicks left.
     * 
     * This gets lowered every game tick when you are under water. 
     * 20 ticks per second.
     * 
     * @return
     */
    public void setAirTicks(int ticks) {
        getEntity().ad = ticks;
    }

    /**
     * Get the amount of FireTicks left.
     * 
     * This gets lowered every game tick when you are on fire. 
     * 20 ticks per second.
     * 
     * @return
     */
    public int getFireTicks() {
        return getEntity().Z;
    }

    /**
     * Set the amount of FireTicks left.
     * 
     * This gets lowered every game tick when you are on fire. 
     * 20 ticks per second.
     * 
     * @return
     */
    public void setFireTicks(int ticks) {
        getEntity().Z = ticks;
    }
}
