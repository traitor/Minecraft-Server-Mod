/**
 * ComplexBlock.java - Interface for complex blocks like chests and signs
 * 
 * @author James
 */
public interface ComplexBlock {

    /**
     * Returns the X coordinates of this block
     * 
     * @return X
     */
    int getX();

    /**
     * Returns the Y coordinates of this block
     * 
     * @return Y
     */
    int getY();

    /**
     * Returns the Z coordinates of this block
     * 
     * @return Z
     */
    int getZ();

    /**
     * Sends the updated block information to clients.
     */
    void update();
    
    /**
     * Stores the current state of this complex block.
     */
    void saveState();
    
    /** 
     * Restores state of this complex block to that which is stored.
     */
    void loadState();
}
