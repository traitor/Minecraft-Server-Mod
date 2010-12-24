public class Workbench extends ItemArray<it> implements Inventory {
    private final ig workbench;

    public Workbench(ig block) {
        super((it)block.b);
        workbench = block;
    }

    public void update() {
        throw new UnsupportedOperationException("Not supported. Please use update(Player player)");
        // This is ugly but we kinda need it. :(
    }

    public void update(Player player) {
        workbench.a((gq)player.getUser());
    }

    public String getName() {
        return container.getName();
    }

    public void setName(String value) {
        container.setName(value);
    }
}