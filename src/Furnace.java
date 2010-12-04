/**
 * Furnace.java - Interface for furnaces
 * @author James
 */
public class Furnace extends ItemArray<dv> implements ComplexBlock {

    /**
     * Creates a furnace interface
     * @param localay
     */
    public Furnace(dv furnace) {
        super(furnace, 3);
    }

    public int getX() {
        return container.b;
    }

    public int getY() {
        return container.c;
    }

    public int getZ() {
        return container.d;
    }

    public void update() {
        container.c();
    }

    /**
     * Returns a String value representing this object
     * 
     * @return String representation of this object
     */
    @Override
    public String toString() {
        return String.format("Furnace[x=%d, y=%d, z=%d]", getX(), getY(), getZ());
    }

    /**
     * Tests the given object to see if it equals this object
     * 
     * @param obj the object to test
     * @return true if the two objects match
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Furnace other = (Furnace) obj;
        if (this.getX() != other.getX()) {
            return false;
        }
        if (this.getY() != other.getY()) {
            return false;
        }
        if (this.getZ() != other.getZ()) {
            return false;
        }
        return true;
    }

    /**
     * Returns a semi-unique hashcode for this object
     * 
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.getX();
        hash = 97 * hash + this.getY();
        hash = 97 * hash + this.getZ();
        return hash;
    }
}
