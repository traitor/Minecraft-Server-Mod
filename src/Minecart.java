import java.util.HashMap;
import java.util.Map;

/**
 * Minecart - Used for manipulating minecarts
 *
 * @author tw1nk
 */
public class Minecart extends BaseVehicle {
    /**
     * Type of minecart
     */
    public enum Type {
        /**
         * Base minecart.
         */
        Minecart(0),
        /**
         * Storage minecart. Has storage for items.
         */
        StorageCart(1),
        /**
         * Powered minecart. Has storage for fuel.
         */
        PoweredMinecart(2);
        
        private final int id;
        private static final Map<Integer, Type> lookup = new HashMap<Integer, Type>();
        
        static {
            for(Type t : Type.values()) {
                lookup.put(t.getType(), t);
            }
        }

        private Type(int id){
            this.id = id;
        }

        public int getType() {
            return id;
        }

        public static Type fromId(final int type) {
            return lookup.get(type);
        }
    }

    /**
     * Creates an interface for minecart.
     * @param o
     */
    public Minecart(lw o) {
        super(o);
    }
    
    /**
     * Create a new Minecart at the given position
     * @param x
     * @param y
     * @param z
     * @param Type 0=Minecart, 1=StorageCart, 2=PoweredMinecart
     */
    public Minecart(double x, double y, double z, Type type) {
        super(new lw(etc.getMCServer().e, x, y, z, type.getType()));
        etc.getMCServer().e.a(entity);        
    }
    
    /**
     * Returns the entity we're wrapping.
     * @return
     */ 
    public lw getEntity() {
        return (lw) entity;
    }

    /**
     * Set damage on Mineentity
     * @param damage over 40 and you "kill" the mineentity
     */
    public void setDamage(int damage) {
        getEntity().a = damage;
    }

    /**
     * Returns damage for mineentity
     * @return returns current damage
     */
    public int getDamage() {
        return getEntity().a;
    }

    /**
     * Returns the type of this minecart.
     * @return type
     */
    public Type getType() {
       return Type.fromId(getEntity().d);
    }

    /**
     * Returns the storage for this minecart. Returns null if minecart is not a storage or powered
     * minecart.
     * @return storage
     */
    public StorageMinecart getStorage() {
        if (getType() == Type.StorageCart || getType() == Type.PoweredMinecart)
            return new StorageMinecart(getEntity());
        return null;
    }
}
