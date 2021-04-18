package com.example.ahdiatahsan_182102_c;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {

    ListView listviewMHS;
    SQLiteDatabase db;
    int i;
    String[] daftarmahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

    db=openOrCreateDatabase("AHDIATAHSAN_182102_C", Context.MODE_PRIVATE, null);
    Cursor c = db.rawQuery("SELECT * FROM tbl_biodata", null);
    daftarmahasiswa = new String[c.getCount()];
    i = 0;
    if (c != null) {
        if (c.moveToFirst()) {
            do {
                daftarmahasiswa[i] = i+1 + ". " + c.getString(0) + " | " +
                        c.getString(1)+ " | " + c.getString(2)+ " | " + c.getString(3)
                        + " | " + c.getString(4)+ " | " + c.getString(5)+ "," +
                        c.getString(6);
                i++;
            } while (c.moveToNext());
        }
    }

    listviewMHS = findViewById(R.id.listviewMHS);
    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
            R.layout.list_view, R.id.textlistView, daftarmahasiswa);
    listviewMHS.setAdapter(arrayAdapter);
    }
}