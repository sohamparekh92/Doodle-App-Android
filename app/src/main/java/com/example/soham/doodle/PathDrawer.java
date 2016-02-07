package com.example.soham.doodle;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by Soham on 2/3/2016.
 */
public class PathDrawer implements SensorEventListener{

    Sensor sensor;
    SensorManager sensorManager;
    float X;
    float Y;

    PathDrawer(Context context){

        X=Y=(float)0.0;
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        //sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    float getX(){
        return X;
    }

    float getY(){
        return Y;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        X = event.values[0];
        Y = event.values[1];

        X = X%10;
        Y = Y%10;


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


    }
}
