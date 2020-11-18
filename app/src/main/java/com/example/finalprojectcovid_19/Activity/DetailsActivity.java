package com.example.finalprojectcovid_19.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.finalprojectcovid_19.Fragment.SearchFragment;
import com.example.finalprojectcovid_19.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class DetailsActivity extends AppCompatActivity {

    private int positionCountry;
    TextView country,cases,todayCases,deaths,todayDeaths,recovered,active,dangerous;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Country Details");

        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position",0);

        country = findViewById(R.id.countryTv);
        cases = findViewById(R.id.casesTv);
        todayCases = findViewById(R.id.todayCasesTv);
        deaths = findViewById(R.id.deathsTv);
        todayDeaths = findViewById(R.id.todayDeathsTv);
        recovered = findViewById(R.id.recoveredTv);
        active = findViewById(R.id.activeTv);
        dangerous = findViewById(R.id.dangerousTv);

        country.setText(SearchFragment.countryModalList.get(positionCountry).getCountry());
        cases.setText(SearchFragment.countryModalList.get(positionCountry).getCases());
        todayCases.setText(SearchFragment.countryModalList.get(positionCountry).getTodayCases());
        deaths.setText(SearchFragment.countryModalList.get(positionCountry).getDeaths());
        todayDeaths.setText(SearchFragment.countryModalList.get(positionCountry).getTodayDeaths());
        recovered.setText(SearchFragment.countryModalList.get(positionCountry).getRecovered());
        active.setText(SearchFragment.countryModalList.get(positionCountry).getActive());
        dangerous.setText(SearchFragment.countryModalList.get(positionCountry).getDangerous());

    }
}
