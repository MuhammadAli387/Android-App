package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SensorManager sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        if(sensorManager != null){
            Sensor accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this,accel, SensorManager.SENSOR_DELAY_UI);
        }
        else{

        }

        try {
            writeToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if(sensorEvent.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            ((TextView)findViewById(R.id.txtVal)).setText("X: "+ sensorEvent.values[0]+", Y: "+sensorEvent.values[1]+" Z: "+sensorEvent.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void writeToFile() throws IOException {

        String fileName = "Ali.txt";
        File file = new File(getFilesDir(), fileName);

        FileOutputStream fos = new FileOutputStream(file);

        String content = "This is an example text.";
        fos.write(content.getBytes());

        fos.close();

    }
}