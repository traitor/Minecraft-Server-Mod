/**
 * Chest.java - Interface to chests.
 * @author James
 */
public class Chest implements ComplexBlock {
    private hb chest;

    public Chest(hb chest) {
        this.chest = chest;
    }

    public void addItem(Item item) {
        if (item == null)
            return;

        int slot = item.getSlot();
        if (slot < 37 && slot >= 0) {
            if (item.getAmount() <= 0)
                chest.getContents()[slot] = null;
            else if(Item.isValidItem(item.getItemId()))
                chest.getContents()[slot] = new gp(item.getItemId(), item.getAmount());
        } else if (slot == -1) {
            int newSlot = getEmptySlot();
            if (newSlot != -1) {
                chest.getContents()[newSlot] = new gp(item.getItemId(), item.getAmount());
                item.setSlot(newSlot);
            }
        }
    }

    public Item getItemFromSlot(int slot) {
        if (slot < 37 && slot >= 0)
            if (chest.getContents()[slot] != null)
                return new Item(chest.getContents()[slot].c, chest.getContents()[slot].a, slot);
        return null;
    }

    public Item getItemFromId(int id) {
        for (int i = 0; chest.getContents().length > i; i++) {
            if (chest.getContents()[i] == null)
                continue;
            if (chest.getContents()[i].c == id)
                return new Item(chest.getContents()[i].c, chest.getContents()[i].a, i);
        }
        return null;
    }

    public Item getItemFromId(int id, int maxAmount) {
        for (int i = 0; chest.getContents().length > i; i++) {
            if (chest.getContents()[i] == null)
                continue;
            if (chest.getContents()[i].c == id && chest.getContents()[i].a <= maxAmount)
                return new Item(chest.getContents()[i].c, chest.getContents()[i].a, i);
        }
        return null;
    }

    public int getEmptySlot() {
        for (int i = 0; chest.getContents().length > i; i++) {
            if (chest.getContents()[i] != null)
                continue;
            return i;
        }
        return -1;
    }

    public void removeItem(int slot) {
        if (slot < 37 && slot >= 0)
            chest.getContents()[slot] = null;
    }

    public void removeItem(Item item) {
        int amount = item.getAmount();
        int itemId = item.getItemId();
        for (int i = 0; chest.getContents().length > i; i++) {
            if (chest.getContents()[i] == null)
                continue;
            if (chest.getContents()[i].c == itemId) {
                int tempAmount = chest.getContents()[i].a;
                tempAmount -= amount;
                if (tempAmount <= 0) {
                    amount -= chest.getContents()[i].a;
                    chest.getContents()[i] = null;
                } else {
                    chest.getContents()[i].a = tempAmount;
                }
                if (amount <= 0)
                    return;
            }
        }
    }

    public boolean hasItem(int itemId, int minimum, int maximum) {
        for (int i = 0; chest.getContents().length > i; i++)
            if (chest.getContents()[i] != null)
                if (chest.getContents()[i].c == itemId &&
                    chest.getContents()[i].a >= minimum &&
                    chest.getContents()[i].a <= maximum)
                    return true;
        return false;
    }

    public void update() {
        chest.c();
    }
}
