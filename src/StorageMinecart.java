/**
 * StorageMinecart - So we can access what's in them.
 * @author James
 */
public class StorageMinecart extends ItemArray<jo> {

    /**
     * Creates an interface for storage of powered and storage carts.
     * @param jo
     */
    public StorageMinecart(jo cart) {
        super(cart, 27);
    }

    public hn[] getArray() {
        return container.ak;
    }

    @Override
    public void setArray(hn[] values) {
        container.ak = values;
    }
}
