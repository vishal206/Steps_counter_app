package com.example.healthcare_mlh;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private Boolean running=false;
    private SensorManager mSensorManager;
    private TextView steps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        running=true;
        Sensor stepsSensor=mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(stepsSensor==null)
        {
            Toast.makeText(this, "Sensor not found", Toast.LENGTH_SHORT).show();
        }
        else
        {
            mSensorManager.registerListener(this,stepsSensor,SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        running=false;
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //here we update the UI
        if(running){
            steps=findViewById(R.id.txt_count);
            steps.setText(""+event.values[0]);
//            steps.setText("hi");
//            Toast.makeText(this, "Sensor found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}