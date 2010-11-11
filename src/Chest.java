
/**
 * Chest.java - Interface to chests.
 * 
 * @author James
 */
public class Chest extends ItemArray implements ComplexBlock {

    private hx chest;

    /**
     * Creates a chest interface
     * 
     * @param chest
     */
    public Chest(hx chest) {
        this.chest = chest;
    }

    public int getX() {
        return chest.b;
    }

    public int getY() {
        return chest.c;
    }

    public int getZ() {
        return chest.d;
    }

    public void update() {
        chest.c();
    }

    public hj[] getArray() {
        return chest.getContents();
    }
}
