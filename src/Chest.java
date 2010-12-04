/**
 * Chest.java - Interface to chests.
 * @author James
 */
public class Chest extends ItemArray<ic> implements ComplexBlock {

    /**
     * Creates a chest interface
     * @param localay
     */
    public Chest(ic chest) {
        super(chest, 27);
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

    public hn[] getArray() {
        return container.getContents();
    }

    /**
     * Sets the contents
     * @param contents contents to set
     */
    public void setArray(hn[] contents) {
        container.setContents(contents);
    }

    /**
     * Returns a String value representing this object
     * 
     * @return String representation of this object
     */
    @Override
    public String toString() {
        return String.format("Chest[x=%d, y=%d, z=%d]", getX(), getY(), getZ());
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
        final Chest other = (Chest) obj;
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
