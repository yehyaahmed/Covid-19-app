package com.example.finalprojectcovid_19.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ListView;

import com.example.finalprojectcovid_19.Model.ImagePreventionMethods;
import com.example.finalprojectcovid_19.Adapter.MyImageAdapter;
import com.example.finalprojectcovid_19.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PreventionMethodsActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prevention_methods);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Prevention Methods");

        listView = findViewById(R.id.prevention_methodsListView);

        List<ImagePreventionMethods> imagePreventionMethods = new ArrayList<>();
        imagePreventionMethods.add(new ImagePreventionMethods("1","https://firebasestorage.googleapis.com/v0/b/chatapp-db313.appspot.com/o/uploads%2FAsset%201xxhdpi.png?alt=media&token=1099f8f4-6cf0-4eaf-9169-28964363386a"));
        imagePreventionMethods.add(new ImagePreventionMethods("2","https://firebasestorage.googleapis.com/v0/b/chatapp-db313.appspot.com/o/uploads%2FAsset%202xxhdpi.png?alt=media&token=faa2c612-cb8f-4ab7-8444-cc1921cfb309"));
        imagePreventionMethods.add(new ImagePreventionMethods("3","https://firebasestorage.googleapis.com/v0/b/chatapp-db313.appspot.com/o/uploads%2FAsset%203xxhdpi.png?alt=media&token=7d8b2c54-74cf-4521-8aab-cac9020dbf15"));
        imagePreventionMethods.add(new ImagePreventionMethods("4","https://firebasestorage.googleapis.com/v0/b/chatapp-db313.appspot.com/o/uploads%2FAsset%204xxhdpi.png?alt=media&token=20cc9f7d-ee35-4e14-8db1-acc48d0ad501"));
        imagePreventionMethods.add(new ImagePreventionMethods("5","https://firebasestorage.googleapis.com/v0/b/chatapp-db313.appspot.com/o/uploads%2FAsset%205xxhdpi.png?alt=media&token=d25d6084-cd10-4169-b8e2-3198e2b1e3cc"));
        imagePreventionMethods.add(new ImagePreventionMethods("6","https://firebasestorage.googleapis.com/v0/b/chatapp-db313.appspot.com/o/uploads%2FAsset%206xxhdpi.png?alt=media&token=4476869c-e952-4094-badd-99121c4fa7b4"));
        imagePreventionMethods.add(new ImagePreventionMethods("7","https://firebasestorage.googleapis.com/v0/b/chatapp-db313.appspot.com/o/uploads%2FAsset%207xxhdpi.png?alt=media&token=92482187-543f-45b5-a87f-0a1c7acd8ffe"));
        imagePreventionMethods.add(new ImagePreventionMethods("8","https://firebasestorage.googleapis.com/v0/b/chatapp-db313.appspot.com/o/uploads%2FAsset%208xxhdpi.png?alt=media&token=b7992dd0-9546-4d1f-b4d7-c1310f885eb1"));


        MyImageAdapter myImageAdapter = new MyImageAdapter(getApplicationContext(),imagePreventionMethods);
        listView.setAdapter(myImageAdapter);
        myImageAdapter.notifyDataSetChanged();

    }
}
