package com.smarthome.asteroids;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarthome.asteroids.DTO.AsteroidMetaData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class History extends AppCompatActivity implements ListView.OnItemClickListener{

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private AsteroidMetaData asteroidMetaData;
    private ListView lvListHistory;
    private List<AsteroidMetaData> list;
    private List<AsteroidHistory> table;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_history);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Asteroid");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    asteroidMetaData = ds.getValue(AsteroidMetaData.class);
                    list.add(asteroidMetaData);
                }
                GetData();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void GetData() {
        table = list.stream().map(u -> new AsteroidHistory(Integer.parseInt(u.getId()), u.getName(), u.getIs_potentially_hazardous_asteroid(),
                u.getAbsolute_magnitude_h()))
                .collect(Collectors.toList());
        lvListHistory = findViewById(R.id.lvListHistory);
        lvListHistory.setAdapter(new HistoryAdapter(
                this,
                new ArrayList<>(table),
                R.layout.list_history
        ));
        lvListHistory.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(History.this, ShowAsteroidHistory.class);
        intent.putExtra("History", table.get(position));
        startActivity(intent);
    }

}
