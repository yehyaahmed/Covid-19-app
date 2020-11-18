package com.example.finalprojectcovid_19.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.finalprojectcovid_19.Model.TextContent;
import com.example.finalprojectcovid_19.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class AboutCoronaActivity extends AppCompatActivity {

    TextView aboutCoronaTv;

    FirebaseFirestore firestore;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_corona);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        firestore = FirebaseFirestore.getInstance();

        Objects.requireNonNull(getSupportActionBar()).setTitle("What is covid_19");

        aboutCoronaTv = findViewById(R.id.about_coronaTv);

        CollectionReference reference = firestore.collection("what_is_covid_19");
        Task<QuerySnapshot> q = reference.get();
        q.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                    TextContent t = queryDocumentSnapshot.toObject(TextContent.class);
                    aboutCoronaTv.setText(t.getTextC());
                }
            }
        });

    }
}
