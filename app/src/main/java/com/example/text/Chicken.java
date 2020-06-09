package com.example.text;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Chicken extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText e1,e2,e3,e4;
    TextView b1;
    TextView b2,b3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicken);
        mydb=new DatabaseHelper(this);
        e1=findViewById(R.id.edittext);
        e2=findViewById(R.id.edittext2);
        e3=findViewById(R.id.edittext3);
        b1=findViewById(R.id.textview);
        b2=findViewById(R.id.textview2);
        b3=findViewById(R.id.textview3);
        e4=findViewById(R.id.edittext4);
        AddData();
        viewAll();
        DeleteData();
    }
    public void AddData() {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isinserted = mydb.insertData(e1.getText().toString(), e2.getText().toString(), e3.getText().toString());
                if (isinserted == true) {
                    Toast.makeText(Chicken.this, "Your Order Has Been Received", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Chicken.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void viewAll()
    {
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res= mydb.getallData();
                if(res.getCount()==0)
                {
                    showMessage("Error","Nothing is found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("ID:"+res.getString(0)+"\n");
                    buffer.append("Name:"+res.getString(1)+"\n");
                    buffer.append("Address:"+res.getString(2)+"\n");
                    buffer.append("Order:"+res.getString(3)+"\n");

                }
                showMessage("Data",buffer.toString());
            }
        });
    }
    public  void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void DeleteData()
    {
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleterows=mydb.Deletedata(e4.getText().toString());
                if(deleterows>0)
                {
                    Toast.makeText(Chicken.this,"Data is Deleted",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(Chicken.this,"Data is not deleted",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}

