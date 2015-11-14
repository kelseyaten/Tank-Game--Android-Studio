package com.example.localadmin.g11_ajh265_kex496.sensor;



import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import org.androidannotations.annotations.EBean;


/**
 * Created by localadmin on 11/14/15.
 */
@EBean
public class ShakeListenerManager implements SensorEventListener {

    private SensorManager sManager;
    private Sensor s;
    private float[] gravity;
    private static final float ALPHA = 0.8F;
    private static final int MOV_COUNTS = 1;
    private static final int MOV_THRESHOLD = 4;
    private int counter;
    private long firstMovTime;
    private ShakeListener listener;
    private static final int SHAKE_WINDOW_TIME_INTERVAL = 500;



    public ShakeListenerManager(Context ctx) {

        gravity = new float[3];
        sManager = (SensorManager)  ctx.getSystemService(Context.SENSOR_SERVICE);
        s = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        register();
    }

    public void setListener(ShakeListener listener) {
        this.listener = listener;
    }

    public void register() {
        sManager.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float maxAcc = calcMaxAcceleration(sensorEvent);

        if (maxAcc >= MOV_THRESHOLD) {
            if (counter == 0) {
                counter++;
                firstMovTime = System.currentTimeMillis();

            } else {
                long now = System.currentTimeMillis();
                if ((now - firstMovTime) < SHAKE_WINDOW_TIME_INTERVAL)
                    counter++;
                else {
                    resetAllData();
                    counter++;
                    return;
                }


                if (counter >= MOV_COUNTS)
                    if (listener != null)
                        listener.onShake();
            }
        }

    }

    public void deregister()  {
        sManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void resetAllData() {

        counter = 0;
        firstMovTime = System.currentTimeMillis();
    }


    private float calcMaxAcceleration(SensorEvent event) {

        gravity[0] = calcGravityForce(event.values[0], 0);
        gravity[1] = calcGravityForce(event.values[1], 1);
        gravity[2] = calcGravityForce(event.values[2], 2);

        float accX = event.values[0] - gravity[0];
        float accY = event.values[1] - gravity[1];
        float accZ = event.values[2] - gravity[2];

        float max1 = Math.max(accX, accY);
        return Math.max(max1, accZ);
    }

    private float calcGravityForce(float currentVal, int index) {
        return  ALPHA * gravity[index] + (1 - ALPHA) * currentVal;
    }


}
