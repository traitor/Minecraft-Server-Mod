/**
 * MobSpawner.java - Wrapper for mob spawners.
 * @author James
 */
public class MobSpawner implements ComplexBlock {
    private int storedDelay = 0;
    private cf spawner;

    /**
     * Creates an interface for the spawner.
     * @param spawner
     */
    public MobSpawner(cf spawner) {
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

    public void update() {
        spawner.c();
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

    @Override
    public void saveState() {
        storedDelay = spawner.e;
    }

    @Override
    public void loadState() {
        if (storedDelay != 0) {
            spawner.e = storedDelay;
        }
    }
}
