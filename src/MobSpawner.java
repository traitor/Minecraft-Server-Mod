
public class MobSpawner implements ComplexBlock {

	ce spawner;
	
	public MobSpawner(ce spawner) {
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
     * See: Block.setSpawnData() if you want to adjust this value.
     */
    public void setSpawn(String spawn) {
        spawner.f = spawn;
    }


    /**
     * Allows delay of what to spawn to change on-the-fly
     * Modification of this is near-useless as delays get randomized after spawn.
     *  See: Block.setSpawnData() if you want to adjust this value.
     */
    public void setDelay(int delay) {
        spawner.e = delay;
    }
}
