package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Takes in device movements, analyzing and determining whether or not
 * the movements are appropriate shakes. The user can shake their phone
 * to easily open up the item list/report lost item page from most of the
 * activities inside the app.
 *
 * @author Vincent Nguyen
 */
public class ShakeDetector implements SensorEventListener {
    private long timeOfLastShake = 0;
    private OnShakeListener mShakeListener;

    private static int SHAKE_TIME_LAPSE = 2000;
    private static float SHAKE_THRESHOLD = 25.0f;

    /**
     * Overloaded constructor that creates a ShakeDetector object based off of a specified
     * listener.
     * @param listener The predefined shake listener.
     */
    public ShakeDetector(OnShakeListener listener) {
        mShakeListener = listener;
    }

    /**
     * Calculates the amount of g-forces applied in an 3D (x-y-z) span where it
     * then determines if the motion constitutes as a shake. Only applies
     * for Accelerometers.
     * @param sensorEvent The type of event.
     */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            // Distance formula -> d = sqrt((x2 - x1)^2 + (y2 - y1)^2 + (z2 - z1)^2)
            // Modified Distance Formula -> d = sqrt((x - gravity)^2 + (y - gravity)^2 + (z - gravity)^2)
            float gForceX = x - SensorManager.GRAVITY_EARTH;
            float gForceY = y - SensorManager.GRAVITY_EARTH;
            float gForceZ = z - SensorManager.GRAVITY_EARTH;

            double vector = Math.pow(gForceX, 2.0) + Math.pow(gForceY, 2.0)
                    + Math.pow(gForceZ, 2.0);

            float gForce = (float) Math.sqrt(vector);

            if (gForce > SHAKE_THRESHOLD) {
                long duration = System.currentTimeMillis();
                if (duration - timeOfLastShake >= SHAKE_TIME_LAPSE) {
                    timeOfLastShake = duration;
                    mShakeListener.onShake();
                }
            }
        }
    }

    /**
     * Unused
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}

    /**
     * Custom interface for the other activities to call the onShake() method.
     * The activities implement the method itself.
     */
    public interface OnShakeListener {
        void onShake();
    }
}