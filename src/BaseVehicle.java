
/**
 * BaseVehicle - Base class for interfacing boats and minecarts
 * @author James
 */
public class BaseVehicle extends BaseEntity {
    /**
     * Creates an interface for a vehicle
     * @param entity
     */
    public BaseVehicle(ea entity) {
        this.entity = entity;
    }

    /**
     * Interface for vehicles.
     */
    public BaseVehicle() {
    }

    /**
     * Returns the x-motion of this vehicle
     * @return x-motion
     */
    public double getMotionX() {
        return entity.s;
    }

    /**
     * Returns the y-motion of this vehicle
     * @return y-motion
     */
    public double getMotionY() {
        return entity.t;
    }

    /**
     * Returns the z-motion of this vehicle
     * @return z-motion
     */
    public double getMotionZ() {
        return entity.u;
    }

    /**
     * Sets the x-motion of this vehicle
     * @param motion motion to set
     */
    public void setMotionX(double motion) {
        entity.s = motion;
    }

    /**
     * Sets the y-motion of this vehicle
     * @param motion motion to set
     */
    public void setMotionY(double motion) {
        entity.t = motion;
    }

    /**
     * Sets the z-motion of this vehicle
     * @param motion motion to set
     */
    public void setMotionZ(double motion) {
        entity.u = motion;
    }

    /**
     * Set vehicle motion
     * @param motionX
     * @param motionY
     * @param motionZ
     */
    public void setMotion(double motionX, double motionY, double motionZ) {
        entity.s = motionX;
        entity.t = motionY;
        entity.u = motionZ;
    }

    /**
     * Destroys this vehicle
     */
    public void destroy() {
        entity.l();
    }

    /**
     * Checks if this vehicle is empty (unoccupied)
     * @return true if unoccupied.
     */
    public boolean isEmpty() {
        if (entity.j == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the passenger. If there is no passenger this function returns null.
     * @return passenger
     */
    public Player getPassenger() {
        if (entity.j != null)
            if (entity.j instanceof et)
                return ((et)entity.j).getPlayer();
        return null;
    }
}
