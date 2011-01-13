/**
 * Mob.java - Interface for mobs
 * 
 * @author James
 */
public class Mob extends LivingEntity {
    /**
     * Creates a mob interface
     * 
     * @param locallb name of mob
     */
    public Mob(mj locallb) {
        super(locallb);
    }
    
    /**
     * Creates a mob interface 
     * 
     * @param mob
     *            name of mob
     */
    public Mob(String mob) {
        this((mj) jn.a(mob, etc.getMCServer().e));
    }

    /**
     * Creates a mob interface
     * 
     * @param mobName name of mob
     * @param location location of mob
     */
    public Mob(String mobName, Location location) {
        this(mobName);
        teleportTo(location);
    }

    /**
     * Spawns this mob
     */
    public void spawn() {
        spawn(null);
    }

    /**
     * Spawns this mob with a rider
     * 
     * @param rider
     */
    public void spawn(Mob rider) {
        fv localff = etc.getMCServer().e;

        entity.c(getX() + 0.5f, getY(), getZ() + 0.5f, getRotation(), 0f);
        localff.a(entity);

        if (rider != null) {
            mj mob2 = rider.getMob();
            mob2.c(getX(), getY(), getZ(), getRotation(), 0f);
            localff.a(mob2);
            mob2.e(entity);
        }
    }

    /**
     * Returns this mob's name
     * 
     * @return name
     */
    public String getName() {
        return jn.b(entity);
    }

    /**
     * Drops this mob's loot. Automatically called if health is set to 0.
     */
    public void dropLoot() {
        getEntity().f(null);
    }
    
    public void setHealth(int health) {
        super.setHealth(health);
        if (health <= 0) {
            dropLoot();
        }
    }

    /**
     * Returns the actual mob
     * 
     * @return
     */
    public mj getMob() {
        return getEntity();
    }

    /**
     * Checks to see if the mob is a valid mob
     * 
     * @param mob
     *            the mob to check
     * @return true of mob is valid
     */
    public static boolean isValid(String mob) {
        if (mob == null) {
            return false;
        }
        return jn.a(mob, etc.getMCServer().e) instanceof mj;
    }
}
