package com.example.soham.doodle;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TableView extends AppCompatActivity {

    public void addToTable(String content){

        String row[] = content.split("<BREAK>");
        String id = row[0];
        String name = row[1];
        String comment = row[2];

        TextView idV = new TextView(this);
        TextView nameV = new TextView(this);
        TextView commentV = new TextView(this);

        idV.setText(id);
        nameV.setText(name);
        commentV.setText(comment);

        TableRow tr = new TableRow(this);
        tr.addView(idV);
        tr.addView(nameV);
        tr.addView(commentV);

        TableLayout myTable = (TableLayout) findViewById(R.id.myTable);
        myTable.addView(tr);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_view);


        DoodleBase dBase = new DoodleBase(this,null,null,1);

        ArrayList<String> rows  = dBase.getContent();

        for(int i=0; i<rows.size();++i){
            this.addToTable(rows.get(i));
        }

        TableLayout tableclicked = (TableLayout) findViewById(R.id.myTable);
        for(int i = 0; i < tableclicked.getChildCount(); ++i)
        {
            tableclicked.getChildAt(i).setClickable(true);
            tableclicked.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TableRow clickedRow = (TableRow) v;
                    TextView clickedName = (TextView)clickedRow.getChildAt(1);
                    String name = clickedName.getText().toString();
                    Toast.makeText(getApplicationContext(),name , Toast.LENGTH_LONG).show();
                }
            });
        }



    }


}
