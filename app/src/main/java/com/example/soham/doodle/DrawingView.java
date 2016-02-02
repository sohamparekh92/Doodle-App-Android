package com.example.soham.doodle;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;

import java.util.jar.Attributes;

public class DrawingView extends View {

    public DrawingView(Context context, AttributeSet attrs){
        super(context,attrs);

        setMyDraw();
    }

    private void setMyDraw(){

    }
}
