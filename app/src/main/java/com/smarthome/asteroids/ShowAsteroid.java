package com.smarthome.asteroids;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smarthome.asteroids.DTO.AsteroidMetaData;
import com.smarthome.asteroids.DTO.Close_approach_data;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowAsteroid extends AppCompatActivity {

    private MyApiService api;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private AsteroidMetaData asteroidMetaData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_asteroid);
        FirebaseApp.initializeApp(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.ic_telescope);
        actionBar.setDisplayShowHomeEnabled(true);
        AsteroidTable asteroid = getIntent().getParcelableExtra("Resultado");
        api = MyApiAdapter.getApiService();
        api.getOnlyAsteroid(asteroid.getId(),getString(R.string.apiKey)).enqueue(new Callback<AsteroidMetaData>() {

            @Override
            public void onResponse(Call<AsteroidMetaData> call, Response<AsteroidMetaData> response) {
                asteroidMetaData = response.body();
                TextView id = findViewById(R.id.textViewId);
                TextView name = findViewById(R.id.textViewName);
                TextView magnitude = findViewById(R.id.textViewMagnitude);
                TextView diameterMinKm = findViewById(R.id.textViewKmMin);
                TextView diameterMaxKm = findViewById(R.id.textViewKmMax);
                TextView diameterMinM = findViewById(R.id.textViewMMin);
                TextView diameterMaxM = findViewById(R.id.textViewMMax);
                TextView diameterMinMi = findViewById(R.id.textViewMiMin);
                TextView diameterMaxMi = findViewById(R.id.textViewMiMax);
                TextView diameterMinFeet = findViewById(R.id.textViewFeetMin);
                TextView diameterMaxFeet = findViewById(R.id.textViewFeetMax);
                TextView date = findViewById(R.id.textDateAproach);
                TextView velocity = findViewById(R.id.textVelocity);
                TextView distanceLunar = findViewById(R.id.textDistanceLunar);
                TextView distanceAstronimica = findViewById(R.id.textDistanceAstronomical);
                TextView distanciaKm = findViewById(R.id.textDistanceKm);
                TextView orbitando = findViewById(R.id.textOrbit);
                TextView intersection = findViewById(R.id.textIntersection);
                TextView tisserand = findViewById(R.id.textTisserand);
                TextView osculation  = findViewById(R.id.textOsculation);
                TextView eccentricity = findViewById(R.id.textEccentricity);
                TextView semieje = findViewById(R.id.textSemiEje);
                TextView inclination = findViewById(R.id.textInclination);
                TextView nodeLongitude = findViewById(R.id.textNodeLongitude);
                TextView orbitalPeriod = findViewById(R.id.textOrbitalPeriod);
                TextView distancePeriheliom = findViewById(R.id.textDistancePerihelion);
                TextView aphelionDistance = findViewById(R.id.textAphelionDistance);
                TextView perihileionTime = findViewById(R.id.textPerihelionTime);
                TextView equinox = findViewById(R.id.textEquinox);
                TextView typeOrbit = findViewById(R.id.textTypeOrbit);
                TextView rangeOrbit = findViewById(R.id.textRangeOrbit);
                TextView arumentPerihelio = findViewById(R.id.textArgumentPerihelion);
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
                Close_approach_data history = asteroidMetaData.getClose_approach_data().stream().filter(u->u.getClose_approach_date_full().equals(asteroid.getDate())).findAny().get();
                date.setText(history.getClose_approach_date_full());
                velocity.setText(history.getRelative_velocity().getKilometers_per_second());
                distanceLunar.setText(history.getMiss_distance().getLunar());
                distanceAstronimica.setText(history.getMiss_distance().getAstronomical());
                distanciaKm.setText(history.getMiss_distance().getKilometers());
                orbitando.setText(history.getOrbiting_body());
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
        }

            @Override
            public void onFailure(Call<AsteroidMetaData> call, Throwable t) {
                ShowMessage(t.getMessage());
            }
        });
    }

    public void Save(View view)
    {
        try {
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference().child("Asteroid");
            databaseReference.child(String.valueOf(asteroidMetaData.getId())).setValue(asteroidMetaData).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    ShowMessage("El registro ha sido guardado en firebase.");
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    ShowMessage(e.getMessage());
                }
            });
        }
        catch (Exception ex)
        {
            ShowMessage(ex.getMessage());
        }

    }

    private void ShowMessage(String message){
        Snackbar.make(
                findViewById(android.R.id.content),
                message,
                Snackbar.LENGTH_LONG
        ).show();
    }
}