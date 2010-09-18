
public class Item {

    private int itemId = 1, amount = 1;

    public Item() {
    }

    public Item(int itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    public int getItemId() {
        return itemId;
    }

    public void saveItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public static boolean isValidItem(int itemId) {
        if (itemId < ez.c.length)
            return ez.c[itemId] != null;
        return false;
    }
}
