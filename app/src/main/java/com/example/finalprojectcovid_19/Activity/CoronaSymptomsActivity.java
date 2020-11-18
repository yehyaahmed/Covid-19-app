package com.example.finalprojectcovid_19.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.finalprojectcovid_19.Model.TextContent;
import com.example.finalprojectcovid_19.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class CoronaSymptomsActivity extends AppCompatActivity {

    VideoView videoView;

    TextView most,less,serious;

    FirebaseFirestore firestore;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona_symptoms);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        firestore = FirebaseFirestore.getInstance();

        Objects.requireNonNull(getSupportActionBar()).setTitle("Corona Symptoms");

        videoView = findViewById(R.id.videoView);
        most = findViewById(R.id.most_symTv);
        less = findViewById(R.id.less_symTv);
        serious = findViewById(R.id.serious_symTv);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);

        mediaController.setAnchorView(videoView);

        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/chatapp-db313.appspot.com/o/uploads%2FGlobal%20COVID-19%20Prevention.mp4?alt=media&token=71bf2478-5b9f-47a4-b40b-41dfbb223ab1");
        videoView.setVideoURI(uri);
        videoView.start();

        CollectionReference reference1 = firestore.collection("less_symptoms");
        Task<QuerySnapshot> q1 = reference1.get();
        q1.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {

                    TextContent t = queryDocumentSnapshot.toObject(TextContent.class);
                    less.setText(t.getTextC());

                }
            }
        });

        CollectionReference reference2 = firestore.collection("most_symptoms");
        Task<QuerySnapshot> q2 = reference2.get();
        q2.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {

                    TextContent t = queryDocumentSnapshot.toObject(TextContent.class);
                    most.setText(t.getTextC());

                }
            }
        });

        CollectionReference reference3 = firestore.collection("serious_symptoms");
        Task<QuerySnapshot> q3 = reference3.get();
        q3.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {

                    TextContent t = queryDocumentSnapshot.toObject(TextContent.class);
                    serious.setText(t.getTextC());

                }
            }
        });




    }
}
