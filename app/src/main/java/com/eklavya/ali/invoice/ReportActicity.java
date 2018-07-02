package com.eklavya.ali.invoice;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ReportActicity extends AppCompatActivity {
    RecyclerView reportRecycle;
    ArrayList<Modeltable> arrayList=new ArrayList<>();
    SQLiteDatabase dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_acticity);
        reportRecycle= (RecyclerView) findViewById(R.id.recyclerReport);
        dbHandler = openOrCreateDatabase("salesinvoice", MODE_PRIVATE, null);
        Cursor c= dbHandler.rawQuery("SELECT * FROM tbl_productdetails",null);
        if(c.getCount()>0){
            c.moveToFirst();
            do  {
                Modeltable m= new Modeltable();
                m.productname=c.getString(c.getColumnIndex("name"));
                m.total=c.getString(c.getColumnIndex("total"));
                m.qty=c.getString(c.getColumnIndex("qty"));
                m.rate=c.getString(c.getColumnIndex("rate"));
                arrayList.add(m);
            }
               while(c.moveToNext());
        }

        Modeltable m=new Modeltable();
        arrayList.add(m);
        reportRecycle.setLayoutManager(new LinearLayoutManager(this));
        ReportAdapter adap = new ReportAdapter(this,arrayList);
        reportRecycle.setAdapter(adap);

    }
}
