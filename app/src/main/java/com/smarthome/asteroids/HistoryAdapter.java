package com.smarthome.asteroids;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;

public class HistoryAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<AsteroidHistory> asteroids;
    private int resource;

    public HistoryAdapter(Context context, ArrayList<AsteroidHistory> asteroids, int resource) {
        super(context, resource, asteroids);
        this.context = context;
        this.asteroids = asteroids;
        this.resource = resource;
        setNotifyOnChange(true);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.resource, null);
        }
        AsteroidHistory asteroidHistory = asteroids.get(position);
        if (asteroidHistory != null) {
            TextView tvid = convertView.findViewById(R.id.tvIdHistory);
            tvid.setText(String.valueOf(asteroidHistory.getId()));
            TextView tvNombre = convertView.findViewById(R.id.tvNameHistory);
            tvNombre.setText(asteroidHistory.getName());
            TextView tvPeligroso = convertView.findViewById(R.id.tvPeligrosoHistory);
            tvPeligroso.setText("    " + asteroidHistory.getIsDangerous());
            TextView tvMagnitud = convertView.findViewById(R.id.tvMagnitudHistory);
            tvMagnitud.setText(String.valueOf(asteroidHistory.getMagnitude()));
        }
        return convertView;
    }
}