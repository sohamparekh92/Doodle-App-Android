package com.example.soham.doodle;

import android.content.Intent;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import java.util.UUID;
import android.provider.MediaStore;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawingView drawView;
    private ImageButton currPaint, newBtn, saveBtn;
    private DoodleManager doodle;
    private DoodleBase dBase;


    public void goToGyDraw(View view) {
        Intent i = new Intent(this, DrawGyro.class);
        startActivity(i);
    }

    public void goToTable(View view) {
        Intent i = new Intent(this, TableView.class);
        startActivity(i);
    }

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

        //New file Button
        newBtn = (ImageButton) findViewById(R.id.new_btn);
        //Set OnClickListener to New file button
        newBtn.setOnClickListener(this);
        //Save Button Initializer
        saveBtn = (ImageButton) findViewById(R.id.save);
        //Also add OnlcikListener to this button
        saveBtn.setOnClickListener(this);

        //Initialize draw view with the custom view created
        drawView = (DrawingView)findViewById(R.id.drawing);

        //
        dBase = new DoodleBase(this,null,null,1);

        //Access Layout
        LinearLayout paintLayout = (LinearLayout)findViewById(R.id.paint_colors);
        //Access what's inside the Layout
        currPaint = (ImageButton)paintLayout.getChildAt(0);
       // currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
        currPaint.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.paint_pressed, null));
    }



    @Override
    public void onClick(View view) {


        if(view.getId()==R.id.save){
            final AlertDialog.Builder saveAlert = new AlertDialog.Builder(this);
            saveAlert.setTitle("Save Doodle");

            doodle = new DoodleManager();
            final EditText uInput = new EditText(this);
            uInput.setInputType(InputType.TYPE_CLASS_TEXT);
            saveAlert.setView(uInput);
            saveAlert.setMessage("Would you like to save this awesome doodle? :)");
            saveAlert.setPositiveButton("Totally!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Save the drawing code
                    drawView.setDrawingCacheEnabled(true);


                    String imgSaved = MediaStore.Images.Media.insertImage(
                            getContentResolver(), drawView.getDrawingCache(),
                            uInput.getText().toString() + ".png", "drawing");
                    if (imgSaved != null) {
                        Toast savedToast = Toast.makeText(getApplicationContext(),
                                "Doodle saved! B)", Toast.LENGTH_SHORT);

                        doodle.setName(uInput.getText().toString());
                        dBase.addDoodle(doodle);

                        savedToast.show();

                    } else {
                        Toast unsavedToast = Toast.makeText(getApplicationContext(),
                                "Oops! Image could not be saved :/", Toast.LENGTH_SHORT);
                        unsavedToast.show();
                    }
                    drawView.destroyDrawingCache();
                }
            });
            saveAlert.setNegativeButton("Ummm no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Cancel
                    dialog.cancel();
                }
            });
            saveAlert.show();

        }
        //Checks if new file button is clicked
        else if(view.getId()==R.id.new_btn){
            //new button
            /*final Dialog newFileDialog = new Dialog(this);
            newFileDialog.setTitle("New Drawing");*/
            AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
            newDialog.setTitle("New drawing");
            newDialog.setMessage("Start new drawing (you will lose the current drawing)? :O");
            newDialog.setPositiveButton("Oh Yes!", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    drawView.startNew();
                    dialog.dismiss();
                }
            });
            newDialog.setNegativeButton("No!!!", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            newDialog.show();

        }


    }
}
