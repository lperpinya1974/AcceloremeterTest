package com.exemple.profedam.acceloremetertest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    TextView valueX, valueY, valueZ;
    SensorManager sensorMgr;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valueX = (TextView) findViewById(R.id.valueX);
        valueY = (TextView) findViewById(R.id.valueY);
        valueZ = (TextView) findViewById(R.id.valueZ);

        sensorMgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorMgr.registerListener(this, sensor , SensorManager.
                SENSOR_DELAY_NORMAL);

    }


    @Override
    protected void onPause() {
         super.onPause();
         sensorMgr.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorMgr.registerListener(this,sensor, SensorManager.
                SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

             valueX.setText(String.valueOf(event.values[0]));
             valueY.setText(String.valueOf(event.values[1]));
             valueZ.setText(String.valueOf(event.values[2]));

             }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
