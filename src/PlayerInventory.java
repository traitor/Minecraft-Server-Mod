
public class PlayerInventory extends ItemArray<ii> implements Inventory {
    private final fi user;
    
    public PlayerInventory(Player player) {
        super(player.getUser().an);
        user = player.getUser();
    }

    public void giveItem(int itemId, int amount) {
        if (amount == -1) {
            int emptySlot = getEmptySlot();
            if (emptySlot == -1) {
                user.getPlayer().giveItemDrop(itemId, -1);
            } else {
                addItem(new Item(itemId, 255, emptySlot));
            }
            return;
        }

        int temp = amount;
        do {
            int amountToAdd = temp >= 64 ? 64 : temp;

            if (hasItem(itemId, 1, 63)) {
                Item i = getItemFromId(itemId, 63);
                if (i != null) {
                    if (amountToAdd == 64) {
                        int a = amountToAdd - i.getAmount();
                        i.setAmount(64);
                        temp -= a;
                    } else if (amountToAdd + i.getAmount() > 64) {
                        int a = amountToAdd + i.getAmount() - 64;
                        i.setAmount(64);
                        temp = a;
                    } else if (amountToAdd + i.getAmount() <= 64) {
                        i.setAmount(amountToAdd + i.getAmount());
                        temp = 0;
                    }
                    addItem(i);
                    continue;
                }
            }

            int emptySlot = getEmptySlot();
            if (emptySlot == -1)
            {
                break;
            }
            
            addItem(new Item(itemId, amountToAdd, emptySlot));
            temp -= 64;
        } while (temp > 0);

        if (temp > 0) {
            user.getPlayer().giveItemDrop(itemId, temp);
        }
    }

    public void update() {
        user.k();
    }

    /**
     * Returns a String value representing this PlayerInventory
     *
     * @return String representation of this PlayerInventory
     */
    @Override
    public String toString() {
        return String.format("PlayerInventory[user=%s]", user.getPlayer());
    }

    /**
     * Returns the owner of this PlayerInventory
     *
     * @return Player
     */
    public Player getPlayer() {
        return user.getPlayer();
    }

    public String getName() {
        return container.getName();
    }

    public void setName(String value) {
        container.setName(value);
    }
}
