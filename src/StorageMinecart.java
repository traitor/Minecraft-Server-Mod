/**
 * StorageMinecart - So we can access what's in them.
 * @author James
 */
public class StorageMinecart extends ItemArray {

    private jn cart;

    /**
     * Creates an interface for storage of powered and storage carts.
     * @param cart
     */
    public StorageMinecart(jn cart) {
        this.cart = cart;
    }

    public hm[] getArray() {
        return cart.ak;
    }
}
