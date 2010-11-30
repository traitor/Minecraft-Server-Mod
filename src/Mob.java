/**
 * Mob.java - Interface for mobs
 * 
 * @author James
 */
public class Mob extends LivingEntity {
    /**
     * Creates a mob interface
     * 
     * @param mob name of mob
     */
    public Mob(jz mob) {
        super(mob);
    }

    /**
     * Creates a mob interface
     * 
     * @param mob
     *            name of mob
     */
    public Mob(String mob) {
        this((jz) ho.a(mob, etc.getMCServer().e));
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
        ep localep = etc.getMCServer().e;

        entity.c(getX() + 0.5f, getY(), getZ() + 0.5f, getRotation(), 0f);
        localep.a(entity);

        if (rider != null) {
            jz mob2 = rider.getMob();
            mob2.c(getX(), getY(), getZ(), getRotation(), 0f);
            localep.a(mob2);
            mob2.e(entity);
        }
    }

    /**
     * Returns this mob's name
     * 
     * @return name
     */
    public String getName() {
        return ho.b(entity);
    }

    /**
     * Drops this mob's loot. Automatically called if health is set to 0.
     */
    public void dropLoot() {
        this.livingEntity.f(null);
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
    public jz getMob() {
        return this.livingEntity;
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
        return ho.a(mob, etc.getMCServer().e) instanceof jz;
    }
}
