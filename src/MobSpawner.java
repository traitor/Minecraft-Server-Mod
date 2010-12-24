/**
 * MobSpawner.java - Wrapper for mob spawners.
 * @author James
 */
public class MobSpawner implements ComplexBlock {

    cq spawner;

    /**
     * Creates an interface for the spawner.
     * @param spawner
     */
    public MobSpawner(cq spawner) {
        this.spawner = spawner;
    }

    public int getX() {
        return spawner.b;
    }

    public int getY() {
        return spawner.c;
    }

    public int getZ() {
        return spawner.d;
    }

    public Block getBlock() {
        return etc.getServer().getBlockAt(getX(), getY(), getZ());
    }

    public void update() {
        spawner.d();
    }

    /**
     * Allows what to spawn to change on-the-fly
     * @param spawn
     */
    public void setSpawn(String spawn) {
        spawner.f = spawn;
    }
    
    /**
     * Returns the spawn used.
     * @return
     */
    public String getSpawn() {
        return spawner.f;
    }

    /**
     * Allows delay of what to spawn to change on-the-fly
     * Modification of this is near-useless as delays get randomized after spawn.
     *  See: Block.setSpawnData() if you want to adjust this value.
     * @param delay
     */
    public void setDelay(int delay) {
        spawner.e = delay;
    }
}
