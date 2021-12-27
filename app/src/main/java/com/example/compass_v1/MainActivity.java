package com.example.compass_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import static android.hardware.Sensor.TYPE_GYROSCOPE;
import static android.hardware.Sensor.TYPE_ORIENTATION;

public class MainActivity extends AppCompatActivity implements SensorEventListener
{
    private TextView textDegree;
    private ImageView imgDynamic;
    private int current_degree = 0;
    private SensorManager senManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init()
    {
        textDegree=findViewById(R.id.textView_Degree);
        imgDynamic=findViewById(R.id.imageView_Dynamic);
        senManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        senManager.registerListener(this, senManager.getDefaultSensor(TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        senManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        int degree =Math.round(sensorEvent.values[0]);

        textDegree.setText("Degree from North: " + Integer.toString(degree) + " degrees");


        RotateAnimation ra = new RotateAnimation(current_degree, -degree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        ra.setDuration(210);

        ra.setFillAfter(true);

        imgDynamic.startAnimation(ra);
        current_degree = -degree;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i)
    {

    }
}