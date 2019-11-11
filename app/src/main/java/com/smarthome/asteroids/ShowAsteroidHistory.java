package com.smarthome.asteroids;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarthome.asteroids.DTO.AsteroidMetaData;


public class ShowAsteroidHistory extends AppCompatActivity {

    private AsteroidHistory asteroidHistory;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_asteroid_history);
        asteroidHistory = getIntent().getParcelableExtra("History");
        firebaseDatabase = FirebaseDatabase.getInstance();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.ic_telescope);
        actionBar.setDisplayShowHomeEnabled(true);
        databaseReference = firebaseDatabase.getReference().child("Asteroid");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                AsteroidMetaData asteroidMetaData = dataSnapshot.child(String.valueOf(asteroidHistory.getId())).getValue(AsteroidMetaData.class);
                PrintData(asteroidMetaData);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                ShowMessage(databaseError.getMessage());
            }
        });
    }

    public void PrintData(AsteroidMetaData asteroidMetaData){
        TextView id = findViewById(R.id.textViewIdHistory);
        TextView name = findViewById(R.id.textViewNameHistory);
        TextView magnitude = findViewById(R.id.textViewMagnitudeHistory);
        TextView diameterMinKm = findViewById(R.id.textViewKmMinHistory);
        TextView diameterMaxKm = findViewById(R.id.textViewKmMaxHistory);
        TextView diameterMinM = findViewById(R.id.textViewMMinHistory);
        TextView diameterMaxM = findViewById(R.id.textViewMMaxHistory);
        TextView diameterMinMi = findViewById(R.id.textViewMiMinHistory);
        TextView diameterMaxMi = findViewById(R.id.textViewMiMaxHistory);
        TextView diameterMinFeet = findViewById(R.id.textViewFeetMinHistory);
        TextView diameterMaxFeet = findViewById(R.id.textViewFeetMaxHistory);
        TextView intersection = findViewById(R.id.textIntersectionHistory);
        TextView tisserand = findViewById(R.id.textTisserandHistory);
        TextView osculation  = findViewById(R.id.textOsculationHistory);
        TextView eccentricity = findViewById(R.id.textEccentricityHistory);
        TextView semieje = findViewById(R.id.textSemiEjeHistory);
        TextView inclination = findViewById(R.id.textInclinationHistory);
        TextView nodeLongitude = findViewById(R.id.textNodeLongitudeHistory);
        TextView orbitalPeriod = findViewById(R.id.textOrbitalPeriodHistory);
        TextView distancePeriheliom = findViewById(R.id.textDistancePerihelionHistory);
        TextView aphelionDistance = findViewById(R.id.textAphelionDistanceHistory);
        TextView perihileionTime = findViewById(R.id.textPerihelionTimeHistory);
        TextView equinox = findViewById(R.id.textEquinoxHistory);
        TextView typeOrbit = findViewById(R.id.textTypeOrbitHistory);
        TextView rangeOrbit = findViewById(R.id.textRangeOrbitHistory);
        TextView arumentPerihelio = findViewById(R.id.textArgumentPerihelionHistory);
        TextView isDangerous = findViewById(R.id.textViewDangerousHistory);
        id.setText(String.valueOf(asteroidMetaData.getId()));
        name.setText(asteroidMetaData.getName());
        magnitude.setText(asteroidMetaData.getAbsolute_magnitude_h());
        diameterMinKm.setText(asteroidMetaData.getEstimated_diameter().getKilometers().getEstimated_diameter_min());
        diameterMaxKm.setText(asteroidMetaData.getEstimated_diameter().getKilometers().getEstimated_diameter_max());
        diameterMinM.setText(asteroidMetaData.getEstimated_diameter().getMeters().getEstimated_diameter_min());
        diameterMaxM.setText(asteroidMetaData.getEstimated_diameter().getMeters().getEstimated_diameter_max());
        diameterMinMi.setText(asteroidMetaData.getEstimated_diameter().getMiles().getEstimated_diameter_min());
        diameterMaxMi.setText(asteroidMetaData.getEstimated_diameter().getMiles().getEstimated_diameter_max());
        diameterMinFeet.setText(asteroidMetaData.getEstimated_diameter().getFeet().getEstimated_diameter_min());
        diameterMaxFeet.setText(asteroidMetaData.getEstimated_diameter().getFeet().getEstimated_diameter_max());
        intersection.setText(asteroidMetaData.getOrbital_data().getMinimum_orbit_intersection());
        tisserand.setText(asteroidMetaData.getOrbital_data().getJupiter_tisserand_invariant());
        osculation.setText(asteroidMetaData.getOrbital_data().getEpoch_osculation());
        eccentricity.setText(asteroidMetaData.getOrbital_data().getEccentricity());
        semieje.setText(asteroidMetaData.getOrbital_data().getSemi_major_axis());
        inclination.setText(asteroidMetaData.getOrbital_data().getInclination());
        nodeLongitude.setText(asteroidMetaData.getOrbital_data().getAscending_node_longitude());
        orbitalPeriod.setText(asteroidMetaData.getOrbital_data().getOrbital_period());
        distancePeriheliom.setText(asteroidMetaData.getOrbital_data().getPerihelion_distance());
        aphelionDistance.setText(asteroidMetaData.getOrbital_data().getAphelion_distance());
        perihileionTime.setText(asteroidMetaData.getOrbital_data().getPerihelion_time());
        equinox.setText(asteroidMetaData.getOrbital_data().getEquinox());
        typeOrbit.setText(asteroidMetaData.getOrbital_data().getOrbit_class().getOrbit_class_type());
        rangeOrbit.setText(asteroidMetaData.getOrbital_data().getOrbit_class().getOrbit_class_range());
        arumentPerihelio.setText(asteroidMetaData.getOrbital_data().getPerihelion_argument());
        isDangerous.setText(asteroidMetaData.getIs_potentially_hazardous_asteroid());
    }

    private void ShowMessage(String message){
        Snackbar.make(
                findViewById(android.R.id.content),
                message,
                Snackbar.LENGTH_LONG
        ).show();
    }
}
