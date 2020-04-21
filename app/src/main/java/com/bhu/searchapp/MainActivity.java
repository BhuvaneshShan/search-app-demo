package com.bhu.searchapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Insert your API KEY here.
        Places.initialize(getApplicationContext(), "AIzaSyD1fLjQuRgsgAMrJFVZbs84wGhOpfxEwzk");
        //PlacesClient placesClient = Places.createClient(this);

        // Initalize the fragment with listener
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
                TextView t = findViewById(R.id.textView);
                t.setText("Selected place: "+place.getName());

                //Reset button and search frag's visibility when a place is selected
                Button b = findViewById(R.id.button);
                b.setVisibility(View.VISIBLE);
                View v = findViewById(R.id.autocomplete_fragment);
                v.setVisibility(View.GONE);
            }

            @Override
            public void onError(Status status) {
                Log.i(TAG, "An error occurred: " + status);
            }
        });

        //Set on click listener for Button
        Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchClick(v);
            }
        });

        //Set search fragment to be invisible
        View v = findViewById(R.id.autocomplete_fragment);
        v.setVisibility(View.GONE);
    }

    public void onSearchClick(View view){
        //Switch visibility
        Button b = findViewById(R.id.button);
        b.setVisibility(View.GONE);

        View v = findViewById(R.id.autocomplete_fragment);
        v.setVisibility(View.VISIBLE);


    }
}
