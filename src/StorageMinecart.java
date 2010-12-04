/**
 * StorageMinecart - So we can access what's in them.
 * @author James
 */
public class StorageMinecart extends ItemArray {

    private jo cart;

    /**
     * Creates an interface for storage of powered and storage carts.
     * @param jo
     */
    public StorageMinecart(jo jo) {
        this.cart = jo;
    }

    public hn[] getArray() {
        return cart.ak;
    }
}
