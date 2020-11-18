package com.example.finalprojectcovid_19.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.finalprojectcovid_19.Activity.MainActivity;
import com.example.finalprojectcovid_19.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class HomeFragment extends Fragment{

    private TextView recoveredNumber;
    private TextView deathsNumber;
    private TextView casesNumber;
    private TextView activeNumber;
    private TextView dangerousNumber;

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        MainActivity.menuTitle.setText("Home");

        recoveredNumber = view.findViewById(R.id.recovered_number_tv);
        deathsNumber = view.findViewById(R.id.deaths_number_tv);
        casesNumber = view.findViewById(R.id.cases_number_tv);
        activeNumber = view.findViewById(R.id.active_number_tv);
        dangerousNumber = view.findViewById(R.id.dangerous_number_tv);


        fetchData();

        return view;
    }

    private void fetchData() {
        String url = "https://corona.lmao.ninja/v2/all/";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());

                    recoveredNumber.setText(jsonObject.getString("recovered"));
                    deathsNumber.setText(jsonObject.getString("deaths"));
                    casesNumber.setText(jsonObject.getString("cases"));
                    activeNumber.setText(jsonObject.getString("active"));
                    dangerousNumber.setText(jsonObject.getString("critical"));

                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toasty.error(Objects.requireNonNull(getContext()),"Couldn't refresh feed", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        requestQueue.add(request);
    }

}
