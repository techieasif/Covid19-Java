package com.techieasif.covid19tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.techieasif.covid19tracker.Models.CountryModel;

public class DetailsActivity extends AppCompatActivity {
    private int countryPosition;
    TextView tvCases, tvTodayCases, tvCountry, tvCritical, tvActive, tvTotalDeath, tvTodayDeath, tvRecovered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        countryPosition = intent.getIntExtra("position", 0);
        final CountryModel country = AffectedCountries.countryModelList.get(countryPosition);
        //setting title
        getSupportActionBar().setTitle(country.getCountry());
        //Setting up back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        tvCases = findViewById(R.id.tvCasesDetail);
        tvCountry = findViewById(R.id.tvCountry);
        tvTodayCases = findViewById(R.id.tvTotalCasesDetail);
        tvTodayDeath = findViewById(R.id.tvTodayDeathDetail);
        tvTotalDeath = findViewById(R.id.tvDeaths);
        tvActive = findViewById(R.id.tvActiveDetail);
        tvCritical = findViewById(R.id.tvCriticalDetail);
        tvRecovered = findViewById(R.id.tvRecoveredDetail);


        tvCases.setText(country.getCases());
        tvCountry.setText(country.getCountry());
        tvCritical.setText(country.getCritical());
        tvActive.setText(country.getActive());
        tvTotalDeath.setText(country.getDeaths());
        tvTodayDeath.setText(country.getTodayDeaths());
        tvTodayCases.setText(country.getTodayCases());
        tvRecovered.setText(country.getRecovered());


    }

    //adding back Button Functionality
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}