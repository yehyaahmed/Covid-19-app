package com.example.finalprojectcovid_19.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.finalprojectcovid_19.Model.CountryModal;
import com.example.finalprojectcovid_19.Fragment.SearchFragment;
import com.example.finalprojectcovid_19.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyCustomAdapter extends ArrayAdapter<CountryModal> {

    private Context context;
    private List<CountryModal> countryModalList;
    private List<CountryModal> countryModalListFiltered;

    public MyCustomAdapter( Context context, List<CountryModal> countryModalsList) {
        super(context, R.layout.list_custom_item,countryModalsList);

        this.context = context;
        this.countryModalList = countryModalsList;
        this.countryModalListFiltered = countryModalsList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        @SuppressLint("ViewHolder") View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom_item,null,true);

        TextView countryName = view.findViewById(R.id.countryNameTv);
        ImageView countryImage = view.findViewById(R.id.countryImage);

        countryName.setText(countryModalListFiltered.get(position).getCountry());
        Picasso.get().load(countryModalListFiltered.get(position).getFlag()).into(countryImage);

        return view;

    }

    @Override
    public int getCount() {
        return countryModalListFiltered.size();
    }

    @Nullable
    @Override
    public CountryModal getItem(int position) {
        return countryModalListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                FilterResults filterResults = new FilterResults();
                if (charSequence == null || charSequence.length() == 0){
                    filterResults.count = countryModalList.size();
                    filterResults.values = countryModalList;
                }else {
                    List<CountryModal> resultModals = new ArrayList<>();
                    String searchStr = charSequence.toString().toLowerCase();

                    for (CountryModal itemsModal : countryModalList){
                        if (itemsModal.getCountry().toLowerCase().contains(searchStr)){
                            resultModals.add(itemsModal);
                        }
                        filterResults.count = resultModals.size();
                        filterResults.values = resultModals;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                countryModalListFiltered = (List<CountryModal>) filterResults.values;
                SearchFragment.countryModalList= (List<CountryModal>) filterResults.values;
                notifyDataSetChanged();

            }
        };

        return filter;
    }
}
