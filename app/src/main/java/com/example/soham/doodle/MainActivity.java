package com.example.soham.doodle;

import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private DrawingView drawView;
    private ImageButton currPaint;

    public void paintClicked(View view){
        //use chosen color
        if(view!=currPaint){
        //update color
            ImageButton imgView = (ImageButton)view;
            String color = view.getTag().toString();
            drawView.setColor(color);

            imgView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.paint_pressed, null));
            currPaint.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.paint, null));
            currPaint=(ImageButton)view;

        }




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawView = (DrawingView)findViewById(R.id.drawing);
        //Access Layout
        LinearLayout paintLayout = (LinearLayout)findViewById(R.id.paint_colors);
        //Access what's inside the Layout
        currPaint = (ImageButton)paintLayout.getChildAt(0);
       // currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
        currPaint.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.paint_pressed, null));



    }
}
