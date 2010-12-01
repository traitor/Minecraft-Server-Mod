
/**
 * BaseEntity.java - Class for accessing things that all entities share - X, Y,
 * Z, health.
 * @author James
 */
public class BaseEntity {
    dy entity;
    
    /**
     * Creates an interface for an entity
     * @param entity
     */
    public BaseEntity(dy entity) {
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
     * Returns whether or not this entity is a mob
     * @return true if mob
     */
    public boolean isMob() {
        return entity instanceof gc;
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
        return entity instanceof es;
    }

    /**
     * Returns the player for this entity
     * @return player
     */
    public Player getPlayer() {
        if (!isPlayer())
            return null;

        es p = (es) entity;
        Player player = etc.getServer().getPlayer(p.at);
        player.setUser(p);

        return player;
    }
}
