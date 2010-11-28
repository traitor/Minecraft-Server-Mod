
/**
 * StorageMinecart - So we can access what's in them.
 * @author James
 */
public class StorageMinecart extends ItemArray {

    private jm cart;

    /**
     * Creates an interface for storage of powered and storage carts.
     * @param cart
     */
    public StorageMinecart(jm cart) {
        this.cart = cart;
    }

    public hl[] getArray() {
        return cart.ak;
    }
}
