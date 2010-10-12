/**
 * ComplexBlock.java - Interface for complex blocks like chests and signs
 * @author James
 */
public interface ComplexBlock {
    public int getX();
    public int getY();
    public int getZ();
    /**
     * Sends the updated block information to clients.
     */
    public void update();
}
