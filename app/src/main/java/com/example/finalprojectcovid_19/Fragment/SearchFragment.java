package com.example.finalprojectcovid_19.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
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
import com.example.finalprojectcovid_19.Activity.DetailsActivity;
import com.example.finalprojectcovid_19.Activity.MainActivity;
import com.example.finalprojectcovid_19.Model.CountryModal;
import com.example.finalprojectcovid_19.Adapter.MyCustomAdapter;
import com.example.finalprojectcovid_19.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class SearchFragment extends Fragment {

    EditText search;
    ListView listView;
    ProgressBar progressBar;

    public static List<CountryModal> countryModalList = new ArrayList<>();
    CountryModal countryModal;
    MyCustomAdapter myCustomAdapter;

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,container,false);

        MainActivity.menuTitle.setText("Search");

        search = view.findViewById(R.id.search_edt);
        listView = view.findViewById(R.id.list_view_country);
        progressBar = view.findViewById(R.id.progress_bar);

        fetchData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getContext(), DetailsActivity.class).putExtra("position",i));
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                myCustomAdapter.getFilter().filter(charSequence);
                myCustomAdapter.notifyDataSetChanged();
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        return view;
    }

    private void fetchData() {

        String url = "https://corona.lmao.ninja/v2/countries/";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i=0 ; i<jsonArray.length() ; i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String countryName = jsonObject.getString("country");
                        String cases = jsonObject.getString("cases");
                        String todayCases = jsonObject.getString("todayCases");
                        String deaths = jsonObject.getString("deaths");
                        String todayDeaths = jsonObject.getString("todayDeaths");
                        String recovered = jsonObject.getString("recovered");
                        String active = jsonObject.getString("active");
                        String dangerous = jsonObject.getString("critical");

                        JSONObject flagObject = jsonObject.getJSONObject("countryInfo");
                        String flag = flagObject.getString("flag");

                        countryModal = new CountryModal(flag,countryName,cases,todayCases,deaths,todayDeaths,recovered,active,dangerous);
                        countryModalList.add(countryModal);
                    }
                    myCustomAdapter = new MyCustomAdapter(getContext(),countryModalList);
                    listView.setAdapter(myCustomAdapter);
                    progressBar.setVisibility(View.GONE);

                } catch (JSONException e) {
                    e.printStackTrace();
                    progressBar.setVisibility(View.GONE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.setVisibility(View.GONE);
                Toast toast = Toasty.error(Objects.requireNonNull(getContext()),"Couldn't refresh feed", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        requestQueue.add(request);


    }

}
