/**
 * Furnace.java - Interface for furnaces
 * @author James
 */
public class Furnace extends ItemArray implements ComplexBlock {
    private df furnace;

    /**
     * Creates a furnace interface
     * @param furnace
     */
    public Furnace(df furnace) {
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

    public gp[] getArray() {
        return furnace.getContents();
    }
}
