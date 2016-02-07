package com.example.soham.doodle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DrawGyro extends AppCompatActivity {

    GyroDrawingView gyroView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_gyro);

        gyroView = (GyroDrawingView)findViewById(R.id.gyroDrawing);
    }
}
