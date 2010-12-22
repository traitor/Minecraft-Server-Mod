/**
 * Sign.java - Interface to signs
 * 
 * @author James
 */
public class Sign implements ComplexBlock {
    private kp sign;

    /**
     * Creates a sign interface
     * 
     * @param localav
     */
    public Sign(kp localay) {
        this.sign = localay;
    }

    /**
     * Sets the line of text at specified index
     * 
     * @param index
     *            line
     * @param text
     *            text
     */
    public void setText(int index, String text) {
        if (index >= 0 && sign.e.length > index) {
            sign.e[index] = text;
        }
    }

    /**
     * Returns the line of text
     * 
     * @param index
     *            line of text
     * @return text
     */
    public String getText(int index) {
        if (index >= 0 && sign.e.length > index) {
            return sign.e[index];
        }
        return "";
    }

    public int getX() {
        return sign.b;
    }

    public int getY() {
        return sign.c;
    }

    public int getZ() {
        return sign.d;
    }

    public void update() {
        sign.d();
    }

    /**
     * Returns a String value representing this Block
     *
     * @return String representation of this block
     */
    @Override
    public String toString() {
        return String.format("Sign [x=%d, y=%d, z=%d]", getX(), getY(), getZ());
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
        final Sign other = (Sign) obj;
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
     * Returns a semi-unique hashcode for this block
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
