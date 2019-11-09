package com.smarthome.asteroids;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;

public class AsteroidsAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<AsteroidTable> asteroids;
    private int resource;

    public AsteroidsAdapter(Context context, ArrayList<AsteroidTable> asteroids, int resource) {
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
        AsteroidTable asteroidTable = asteroids.get(position);
        if (asteroidTable != null) {

            TextView tvNombre = convertView.findViewById(R.id.tvName);
            tvNombre.setText(asteroidTable.getName());
            TextView tvDistancia = convertView.findViewById(R.id.tvDistancia);
            tvDistancia.setText(String.valueOf(asteroidTable.getDistance()));
            TextView tvPeligroso = convertView.findViewById(R.id.tvPeligroso);
            tvPeligroso.setText("    "+ asteroidTable.getIsDangerous());
            TextView tvMagnitud = convertView.findViewById(R.id.tvMagnitud);
            tvMagnitud.setText(String.valueOf(asteroidTable.getMagnitude()));
            TextView tvDate = convertView.findViewById(R.id.tvFecha);
            tvDate.setText(String.valueOf(asteroidTable.getDate()));

            if(asteroidTable.getIsDangerous() == "Si")
                convertView.setBackgroundResource(R.color.blue);
            else
                convertView.setBackgroundResource(R.color.white);

        }
        return convertView;
    }
}
