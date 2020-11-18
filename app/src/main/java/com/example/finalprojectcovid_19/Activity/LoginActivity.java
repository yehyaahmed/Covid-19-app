package com.example.finalprojectcovid_19.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalprojectcovid_19.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {


    FirebaseAuth auth;

    TextView new_user_tv;
    Button login_btn;

    TextInputEditText emailEt , passwordEt;

    ProgressDialog pd;
    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        auth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null){
            Intent intent = new Intent(getApplicationContext() , MainActivity.class);
            startActivity(intent);
            finish();
        }

        new_user_tv = findViewById(R.id.new_user_tv);

        emailEt = findViewById(R.id.email_login);
        passwordEt = findViewById(R.id.password_login);

        pd = new ProgressDialog(this , R.style.CustomPD);


        login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pd.setMessage("Loading . . .");
                pd.show();

                final String name = Objects.requireNonNull(emailEt.getText()).toString();
                final String password = Objects.requireNonNull(passwordEt.getText()).toString();

                if (!name.isEmpty() && !password.isEmpty()) {

                    Task<AuthResult> task = auth.signInWithEmailAndPassword(name, password);
                    task.addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                if (name.equals("admin123@gmail.com") && password.equals("admin123")) {

                                    Intent intent = new Intent(getApplicationContext(), AllMessageActivity.class);
                                    startActivity(intent);
                                    pd.dismiss();

                                } else {

                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    Toast toast = Toasty.success(Objects.requireNonNull(getApplicationContext()), "Hello", Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER, 0, 0);
                                    toast.show();

                                    pd.dismiss();
                                }
                            }
                        }
                    });
                    task.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            pd.dismiss();
                            Toast toast = Toasty.error(Objects.requireNonNull(getApplicationContext()),"Email or Password Error.", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();

                        }
                    });


                }else {
                    pd.dismiss();
                    Toast toast = Toasty.error(Objects.requireNonNull(getApplicationContext()),"Enter Name and Password, Please.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                }



            }
        });

        new_user_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this , RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null){
            Intent intent = new Intent(getApplicationContext() , MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
