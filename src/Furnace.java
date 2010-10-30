/**
 * Furnace.java - Interface for furnaces
 * @author James
 */
public class Furnace extends ItemArray implements ComplexBlock {
    private dr furnace;

    /**
     * Creates a furnace interface
     * @param furnace
     */
    public Furnace(dr furnace) {
        this.furnace = furnace;
    }

    public int getX() {
        return furnace.b;
    }

    public int getY() {
        return furnace.c;
    }

    public int getZ() {
        return furnace.d;
    }

    public void update() {
        furnace.c();
    }

    public hh[] getArray() {
        return furnace.getContents();
    }
}
