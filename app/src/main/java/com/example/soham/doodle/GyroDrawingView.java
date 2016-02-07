package com.example.soham.doodle;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * Created by Soham on 2/3/2016.
 */
public class GyroDrawingView extends View implements SensorEventListener{

    Sensor sensor;
    SensorManager sensorManager;
    float X;
    float Y;
    float touchX;
    float touchY;


    //drawing path
    private Path drawPath;
    //drawing and canvas paint
    private Paint drawPaint, canvasPaint;
    //initial color
    private int paintColor = 0xFF660000;
    //canvas
    private Canvas drawCanvas;
    //canvas bitmap
    private Bitmap canvasBitmap;

    public void setColor(String newColor){
        //set color
        invalidate();
        paintColor = Color.parseColor(newColor);
        drawPaint.setColor(paintColor);


    }

    public GyroDrawingView(Context context, AttributeSet attrs){
        super(context,attrs);

        setMyDraw(context);
    }

    private void setMyDraw(Context context){

        //X=Y=(float)0.0;
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        //sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);


        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(20);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas){

        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //detect user touch
         touchX = event.getX();
         touchY = event.getY();


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
               // drawPath.lineTo(X, Y);

                break;

            /*case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(X*10, Y*10);
                break;*/
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        X = event.values[0];
        Y = event.values[1];
        drawPath.lineTo(touchX+X*10,touchY+Y*10);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
