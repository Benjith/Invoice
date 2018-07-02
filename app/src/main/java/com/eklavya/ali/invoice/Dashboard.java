package com.eklavya.ali.invoice;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
RecyclerView recycleTable;
    SQLiteDatabase dbHandler;
    Button addRow,save,report;
    TextView totalamount;
    ArrayList<Modeltable> arrayList=new ArrayList<>();


    Dashboard obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        recycleTable=(RecyclerView) findViewById(R.id.recycler);
        totalamount= (TextView) findViewById(R.id.totalfinal);
        save = (Button) findViewById(R.id.saveBtn);
        report = (Button) findViewById(R.id.reportBtn);
        obj=this;
    addRow= (Button) findViewById(R.id.addrow);
        addRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Modeltable m=new Modeltable();
                arrayList.add(m);
                recycleTable.setLayoutManager(new LinearLayoutManager(view.getContext()));
                TableAdapter adap = new TableAdapter(view.getContext(),arrayList,obj);
                recycleTable.setAdapter(adap);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    dbHandler = openOrCreateDatabase("salesinvoice", MODE_PRIVATE, null);
                    for (int i = 0; i < arrayList.size(); i++) {
                        Modeltable m = arrayList.get(i);
                        dbHandler.execSQL("INSERT INTO tbl_productdetails(name,qty,rate,total) " +
                                "VALUES('" + m.productname + "'," + m.qty + "," + m.rate + ",'" + m.total + "')");

                    }
                    arrayList.clear();
                    recycleTable.setLayoutManager(new LinearLayoutManager(view.getContext()));
                    TableAdapter adap = new TableAdapter(view.getContext(), arrayList, obj);
                    recycleTable.setAdapter(adap);

                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this,ReportActicity.class);
                startActivity(i);
            }
        });
    }
}
