/**
 * Location.java - Used for passing a location to other functions and such.
 * 
 * @author James
 */
public class Location {

    /**
     * Creates a location
     */
    public Location() {
    }

    /**
     * Creates a location with the specified coordinates
     * 
     * @param X
     * @param Y
     * @param Z
     */
    public Location(double X, double Y, double Z) {
        x = X;
        y = Y;
        z = Z;
    }

    /**
     * Creates a location with the specified coordinates and rotation
     * 
     * @param X
     * @param Y
     * @param Z
     * @param rotation
     * @param pitch
     */
    public Location(double X, double Y, double Z, float rotation, float pitch) {
        x = X;
        y = Y;
        z = Z;
        rotX = rotation;
        rotY = pitch;
    }

    /**
     * X location
     */
    public double x;
    /**
     * Z location
     */
    public double z;
    /**
     * Y location
     */
    public double y;
    /**
     * Rotation
     */
    public float  rotX;
    /**
     * Pitch
     */
    public float  rotY;
}
