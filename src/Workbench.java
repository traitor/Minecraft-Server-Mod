public class Workbench extends ItemArray<it> implements Inventory {
    private final ig workbench;

    public Workbench(ig block) {
        super((it)block.b, 10);
        workbench = block;
    }

    public void update() {
        throw new UnsupportedOperationException("Not supported. Please use update(Player player)");
        // This is ugly but we kinda need it. :(
    }

    public void update(Player player) {
        workbench.a((gq)player.getUser());
    }
}