package com.example.text;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    TextView tv;
    Button button;
    EditText email,password;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tv=findViewById(R.id.textview3);
        button=findViewById(R.id.button);
        email=findViewById(R.id.edittext);
        password=findViewById(R.id.edittext2);
        auth=FirebaseAuth.getInstance();
        resisterpage();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtemail=email.getText().toString();
                String txtPassword=password.getText().toString();
                loginuser(txtemail,txtPassword);
            }
        });
    }

    private void loginuser(String email,String password) {
        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(Login.this,"LogIn is Successful",Toast.LENGTH_LONG).show();
                startActivity(new Intent(Login.this, MainActivity.class));

            }
        });

    }
    public void resisterpage()
    {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Resister.class));
            }
        });

    }
}
