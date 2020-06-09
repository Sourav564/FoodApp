package com.example.text;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Resister extends AppCompatActivity {
    EditText email,password;
    Button button;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resister);
        button=findViewById(R.id.button);
        email=findViewById(R.id.edittext);
        password=findViewById(R.id.edittext2);
        button=findViewById(R.id.button);
        auth=FirebaseAuth.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email=email.getText().toString();
                String txt_passworword=password.getText().toString();
                if(TextUtils.isEmpty(txt_email) ||TextUtils.isEmpty(txt_passworword))
                {
                    Toast.makeText(Resister.this,"Empty Occur",Toast.LENGTH_LONG).show();
                }
                else if (txt_passworword.length()<6)
                {
                    Toast.makeText(Resister.this,"Password is too short",Toast.LENGTH_LONG).show();
                }
                else
                {
                    resisteruser(txt_email,txt_passworword);
                }
            }
        });
    }

    private void resisteruser(String email, String passworword) {
        auth.createUserWithEmailAndPassword(email,passworword).addOnCompleteListener(Resister.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Resister.this,"Resister user Successful",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Resister.this,Login.class));
                    finish();
                }
                else
                {
                    Toast.makeText(Resister.this,"Resister is Unsuccessful",Toast.LENGTH_LONG).show();
                }


            }
        });

    }
    }
