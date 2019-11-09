package com.smarthome.asteroids;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smarthome.asteroids.DTO.AsteroidMetaData;
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
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Asteroid");
        AsteroidTable asteroid = getIntent().getParcelableExtra("Resultado");
        api = MyApiAdapter.getApiService();

        api.getOnlyAsteroid(asteroid.getId(),getString(R.string.apiKey)).enqueue(new Callback<AsteroidMetaData>() {

            @Override
            public void onResponse(Call<AsteroidMetaData> call, Response<AsteroidMetaData> response) {
                asteroidMetaData = response.body();
                TextView id = findViewById(R.id.textViewId);
                TextView name = findViewById(R.id.textViewName);
                id.setText(String.valueOf(response.body().getId()));
                name.setText(response.body().getName());

            }

            @Override
            public void onFailure(Call<AsteroidMetaData> call, Throwable t) {

            }
        });
    }

    public void Save(View view)
    {
        databaseReference.child(String.valueOf(asteroidMetaData.getId())).setValue(asteroidMetaData);
    }
}