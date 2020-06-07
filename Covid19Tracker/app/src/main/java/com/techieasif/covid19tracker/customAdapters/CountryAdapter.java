package com.techieasif.covid19tracker.customAdapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.techieasif.covid19tracker.AffectedCountries;
import com.techieasif.covid19tracker.Models.CountryModel;
import com.techieasif.covid19tracker.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CountryAdapter extends ArrayAdapter<CountryModel> {

    private Context context;
    private List<CountryModel> countryModelList;
    private List<CountryModel> countryModelListFiltered;

    public CountryAdapter(Context context, List<CountryModel> countryModelList) {
        super(context, R.layout.custom_list_item, countryModelList);
        this.context = context;
        this.countryModelList = countryModelList;
        this.countryModelListFiltered = countryModelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //Instantiates a layout XML file into its corresponding View objects.
        @SuppressLint("ViewHolder")
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item, null, true);
        TextView tvCountryName = view.findViewById(R.id.tvCountryName);
        ImageView countryFlag = view.findViewById(R.id.imageFlag);

        tvCountryName.setText(countryModelListFiltered.get(position).getCountry());
        //using glide library to set image to image View.
        Glide.with(context).load(countryModelListFiltered.get(position).getFlag()).into(countryFlag);
        return view;

    }

    @Override
    public int getCount() {
        return countryModelListFiltered.size();
    }

    @Nullable
    @Override
    public CountryModel getItem(int position) {
        return countryModelListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Filter Logic


    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    filterResults.count = countryModelList.size();
                    filterResults.values = countryModelList;
                } else {

                    List<CountryModel> resultModel = new ArrayList<>();
                    String searchString = constraint.toString().toLowerCase();

                    for (CountryModel itemModel:countryModelList) {
                        if (itemModel.getCountry().toLowerCase().contains(searchString)) {
                            resultModel.add(itemModel);
                        }
                        filterResults.count = resultModel.size();
                        filterResults.values = itemModel;
                    }
                }
                return filterResults;
            }
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                Log.d("Cast:", "publishResults: " + results.values.toString());

//                list.stream().forEach(x->cusList.add((Customer)x))

                countryModelListFiltered = ((List<CountryModel>) results.values);
                AffectedCountries.countryModelList = (List<CountryModel>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
