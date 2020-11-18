package com.example.finalprojectcovid_19.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.finalprojectcovid_19.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class RegisterActivity extends AppCompatActivity {


    FirebaseAuth auth;

    TextInputEditText emailEt, passwordEt;
    TextInputEditText  nameEt;
    Button registerBtn;

    public static String namePerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        auth = FirebaseAuth.getInstance();

        nameEt = findViewById(R.id.name_register);
        emailEt = findViewById(R.id.email_register);
        passwordEt = findViewById(R.id.password_register);
        registerBtn = findViewById(R.id.registerBtn);

        namePerson = Objects.requireNonNull(nameEt.getText()).toString();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name = Objects.requireNonNull(emailEt.getText()).toString();
                String password = Objects.requireNonNull(passwordEt.getText()).toString();

                if (!name.isEmpty() && !password.isEmpty()) {

                        Task<AuthResult> task = auth.createUserWithEmailAndPassword(name, password);
                        task.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);

                                }else{

                                    Toast toast = Toasty.error(Objects.requireNonNull(getApplicationContext()),"Register Failed, Try Again", Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER, 0, 0);
                                    toast.show();

                                }
                            }
                        });

                }else {
                    Toast toast = Toasty.error(Objects.requireNonNull(getApplicationContext()),"Enter Name and Password, Please.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                }
            }
        });


    }
}
