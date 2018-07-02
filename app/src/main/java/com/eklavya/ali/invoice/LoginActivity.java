package com.eklavya.ali.invoice;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button login;
    SQLiteDatabase dbHandler;
    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupDb();
     setContentView(R.layout.activity_login);
     username=(EditText) findViewById(R.id.username);
     password=(EditText) findViewById(R.id.password);
     login=(Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().trim().equals("admin")&&password.getText().toString().trim().equals("admin"))
                {
                    Intent i=new Intent(LoginActivity.this,Dashboard.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Username or password Error", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void setupDb(){
        dbHandler=openOrCreateDatabase("salesinvoice",MODE_PRIVATE,null);
        dbHandler.execSQL("CREATE TABLE IF NOT EXISTS tbl_products(id integer primary key " +
                "autoincrement,name varchar(200))");
        dbHandler.execSQL("CREATE TABLE IF NOT EXISTS tbl_productdetails(id integer primary key ," +
                "name varchar(200),qty decimal(18,2),rate decimal(18,2),total varchar(100));");

    }
    public void fetchData(){

    }
}
