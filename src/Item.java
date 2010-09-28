
/**
 *
 * @author James
 */
public class Item {

    private int itemId = 1, amount = 1;

    /**
     * Create an item with an id of 1 and amount of 1
     */
    public Item() {
    }

    /**
     * Creates an item with specified id and amount
     * @param itemId
     * @param amount
     */
    public Item(int itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    /**
     * Returns the item id
     * @return item id
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * Sets item id to specified id
     * @param itemId
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    /**
     * Returns the amount
     * @return amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets the amount
     * @param amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Returns true if specified item id is a valid item id.
     * @param itemId
     * @return
     */
    public static boolean isValidItem(int itemId) {
        if (itemId < ez.c.length)
            return ez.c[itemId] != null;
        return false;
    }
}
