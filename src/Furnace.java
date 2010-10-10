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

    public void update() {
        furnace.c();
    }

    public gp[] getArray() {
        return furnace.getContents();
    }
}
