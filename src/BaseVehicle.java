/**
 * BaseVehicle - Base class for interfacing boats and minecarts
 * 
 * @author James
 */
public class BaseVehicle extends BaseEntity {
    /**
     * Creptes an interface for a vehicle
     * 
     * @param entity
     */
    public BaseVehicle(OEntity entity) {
        this.entity = entity;
    }
    
    public enum vehicleTypes {
    	EmptyMinecart,
    	StorageMinecart,
    	PoweredMinecart,
    	Boat, }

	public BaseVehicle(vehicleTypes e, double x, double y, double z) {
	        switch (e){
	        case Boat: fm boat = new fm(etc.getMCServer().e, x, y, z); etc.getMCServer().e.a(boat); this.entity = boat; break;
	        case EmptyMinecart: jo minecart = new jo(etc.getMCServer().e, x, y, z, 0); etc.getMCServer().e.a(minecart); this.entity = minecart; break;
	        case StorageMinecart: jo storageCart = new jo(etc.getMCServer().e, x, y, z, 1); etc.getMCServer().e.a(storageCart); this.entity = storageCart; break;
	        case PoweredMinecart: jo poweredCart = new jo(etc.getMCServer().e, x, y, z, 2); etc.getMCServer().e.a(poweredCart); this.entity = poweredCart; break;                        
	    }
	}

    /**
     * Interface for vehicles.
     */
    public BaseVehicle() {
    }

    /**
     * Returns the x-motion of this vehicle
     * 
     * @return x-motion
     */
    public double getMotionX() {
        return entity.aN;
    }

    /**
     * Returns the y-motion of this vehicle
     * 
     * @return y-motion
     */
    public double getMotionY() {
        return entity.aO;
    }

    /**
     * Returns the z-motion of this vehicle
     * 
     * @return z-motion
     */
    public double getMotionZ() {
        return entity.aP;
    }

    /**
     * Sets the x-motion of this vehicle
     * 
     * @param motion
     *            motion to set
     */
    public void setMotionX(double motion) {
        entity.aN = motion;
    }

    /**
     * Sets the y-motion of this vehicle
     * 
     * @param motion
     *            motion to set
     */
    public void setMotionY(double motion) {
        entity.aO = motion;
    }

    /**
     * Sets the z-motion of this vehicle
     * 
     * @param motion
     *            motion to set
     */
    public void setMotionZ(double motion) {
        entity.aP = motion;
    }

    /**
     * Set vehicle motion
     * 
     * @param motionX
     * @param motionY
     * @param motionZ
     */
    public void setMotion(double motionX, double motionY, double motionZ) {
        setMotionX(motionX);
        setMotionY(motionY);
        setMotionZ(motionZ);
    }

    /**
     * Destroys this vehicle
     */
    public void destroy() {
        entity.C();
    }

    /**
     * Checks if this vehicle is empty (unoccupied)
     * 
     * @return true if unoccupied.
     */
    public boolean isEmpty() {
        if (entity.aE == null)
            return true;
        else
            return false;
    }

    /**
     * Returns the passenger. If there is no passenger this function returns
     * null.
     * 
     * @return passenger
     */
    public Player getPassenger() {
        if (entity.aE != null)
            if (isPlayer(entity.aE))
                return ((OEntityPlayerMP) entity.aE).getPlayer();

        return null;
    }
}
