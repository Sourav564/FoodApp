package com.example.text;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Bideshi extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText e1,e2,e3,e4;
    TextView b1;
    TextView b2,b3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bideshi);
        mydb=new DatabaseHelper(this);
        e1=findViewById(R.id.edittext);
        e2=findViewById(R.id.edittext2);
        e3=findViewById(R.id.edittext3);
        b1=findViewById(R.id.textview);
        b2=findViewById(R.id.textview2);
        e4=findViewById(R.id.edittext4);
        b3=findViewById(R.id.textview3);
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
                    Toast.makeText(Bideshi.this, "Your Order Has Been Received", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Bideshi.this, "Something went wrong", Toast.LENGTH_LONG).show();
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
                showMessage("Your Order",buffer.toString());
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
                    Toast.makeText(Bideshi.this,"Order is cancelled",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(Bideshi.this,"Order is not cancelled",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
