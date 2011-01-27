/**
 * StorageMinecart - So we can access what's in them.
 * 
 * @author James
 */
public class StorageMinecart extends ItemArray<OEntityMinecart> {

    /**
     * Creates an interface for storage of powered and storage carts.
     * 
     * @param jo
     */
    public StorageMinecart(OEntityMinecart cart) {
        super(cart);
    }

}