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
public final class ShakeDetector implements SensorEventListener {
    private long timeOfLastShake = 0;
    private OnShakeListener mShakeListener;

    private static final int SHAKE_TIME_LAPSE = 2000;
    private static final float SHAKE_THRESHOLD = 25.0f;

    /**
     * Overloaded constructor that creates a ShakeDetector object based off of a specified
     * listener.
     * @param listener The predefined shake listener.
     */
    public ShakeDetector(final OnShakeListener listener) {
        mShakeListener = listener;
    }

    /**
     * Calculates the amount of g-forces applied in an 3D (x-y-z) span where it
     * then determines if the motion constitutes as a shake. Only applies
     * for Accelerometers.
     * @param sensorEvent The type of event.
     */
    @Override
    public void onSensorChanged(final SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            final float X = sensorEvent.values[0];
            final float Y = sensorEvent.values[1];
            final float Z = sensorEvent.values[2];

            // Distance formula -> d = sqrt((x2 - x1)^2 + (y2 - y1)^2 + (z2 - z1)^2)
            // Modified Distance Formula -> d = sqrt((x - gravity)^2 + (y - gravity)^2 + (z - gravity)^2)
            final float G_FORCE_X = X - SensorManager.GRAVITY_EARTH;
            final float G_FORCE_Y = Y - SensorManager.GRAVITY_EARTH;
            final float G_FORCE_Z = Z - SensorManager.GRAVITY_EARTH;

            final double VECTOR = Math.pow(G_FORCE_X, 2.0) + Math.pow(G_FORCE_Y, 2.0)
                    + Math.pow(G_FORCE_Z, 2.0);

            final float G_FORCE = (float) Math.sqrt(VECTOR);

            if (G_FORCE > SHAKE_THRESHOLD) {
                final long DURATION = System.currentTimeMillis();
                if (DURATION - timeOfLastShake >= SHAKE_TIME_LAPSE) {
                    timeOfLastShake = DURATION;
                    mShakeListener.onShake();
                }
            }
        }
    }

    /**
     * Unused
     */
    @Override
    public void onAccuracyChanged(final Sensor sensor, final int i) {}

    /**
     * Custom interface for the other activities to call the onShake() method.
     * The activities implement the method itself.
     */
    public interface OnShakeListener {
        void onShake();
    }
}