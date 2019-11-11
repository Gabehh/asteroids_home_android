package com.smarthome.asteroids;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.ui.auth.AuthUI;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.smarthome.asteroids.DTO.Asteroid;
import com.smarthome.asteroids.DTO.Asteroids;
import com.smarthome.asteroids.DTOLamp.Color;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener{

    final static String LOG_TAG = "MiW";
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private static final int RC_SIGN_IN = 2019;
    private MyApiService api;
    private LampApiServer lampApi;
    private DatePickerDialog mDatePickerDialog;
    private EditText date;
    private EditText initialDate;
    private EditText endDate;
    private List<AsteroidTable> table;
    private ListView lvListAsteroids;
    private TextView countAsteroids;
    private long asteroidDangerous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAuth = FirebaseAuth.getInstance();
        initialDate =  findViewById(R.id.dateInitial);
        endDate = findViewById(R.id.dateEnd);
        lvListAsteroids = findViewById(R.id.lvListAsteroids);
        countAsteroids = findViewById(R.id.textCount);
        setDateTimeField();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.ic_telescope);
        actionBar.setDisplayShowHomeEnabled(true);

        initialDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                date = initialDate;
                mDatePickerDialog.show();
                return false;
            }
        });

        endDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                date = endDate;
                mDatePickerDialog.show();
                return false;
            }
        });

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    CharSequence username = user.getDisplayName();
                    Toast.makeText(MainActivity.this, getString(R.string.firebase_user_fmt, username), Toast.LENGTH_LONG).show();
                    Log.i(LOG_TAG, "onAuthStateChanged() " + getString(R.string.firebase_user_fmt, username));
                    api = MyApiAdapter.getApiService();
                } else {
                    startActivityForResult(
                            AuthUI.getInstance().
                                    createSignInIntentBuilder().
                                    setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.GoogleBuilder().build(),
                                            new AuthUI.IdpConfig.EmailBuilder().build()
                                    )).
                                    setIsSmartLockEnabled(!BuildConfig.DEBUG /* credentials */, true /* hints */).
                                    build(),
                            RC_SIGN_IN
                    );
                }
            }
        };

    }

    @Override
    protected void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);

    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.optionOff:
                mFirebaseAuth.signOut();
                lvListAsteroids.setAdapter(null);
                countAsteroids.setText("");
                ((EditText)findViewById(R.id.dateInitial)).setText("");
                ((EditText)findViewById(R.id.dateEnd)).setText("");
                ((TextView)findViewById(R.id.textLamp)).setBackgroundResource(R.color.white);
                ((TextView)findViewById(R.id.textLamp)).setText("");
                return true;
            case R.id.optionHistorial:
                startActivity(new Intent(this, History.class));
                return true;
            case R.id.optionHelp:
                startActivity(new Intent(this, Help.class));
                return true;
        }
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    public void Find(View view){
        String dateInitial = ((EditText)findViewById(R.id.dateInitial)).getText().toString();
        String dateEnd = ((EditText)findViewById(R.id.dateEnd)).getText().toString();
        if(dateInitial.equals("") || dateEnd.equals("")){
            this.ShowMessage("Debe ingresar ambas fechas");
            return;
        }
        LocalDate d1 = LocalDate.parse(dateInitial, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate d2 = LocalDate.parse(dateEnd, DateTimeFormatter.ISO_LOCAL_DATE);
        Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
        long diffDays = diff.toDays();
        if(diffDays>=0 && diffDays<=7) {
            api.getAsteroids(((EditText) findViewById(R.id.dateInitial)).getText().toString(),
                    ((EditText) findViewById(R.id.dateEnd)).getText().toString()
                    , getString(R.string.apiKey)).enqueue(new Callback<Asteroids>() {
                @Override
                public void onResponse(Call<Asteroids> call, Response<Asteroids> response) {
                    GetData(response);
                }

                @Override
                public void onFailure(Call<Asteroids> call, Throwable t) {
                    ShowMessage(t.getMessage());
                }
            });
        }
        else
            this.ShowMessage("La fecha solo puede tener hasta 7 días de diferencia.");
    }

    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        mDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
                final Date startDate = newDate.getTime();
                String fdate = sd.format(startDate);
                date.setText(fdate);
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        mDatePickerDialog.getDatePicker();
    }

    public void GetData(Response<Asteroids> response){
        try {
            ArrayList<ArrayList<Asteroid>> valueList = new ArrayList<>((response.body().getNear_earth_objects()).values());
            ArrayList<Asteroid> asteroidsTable = new ArrayList<>();
            valueList.forEach(u -> asteroidsTable.addAll(u));
            table = asteroidsTable.stream().map(u -> new AsteroidTable(Integer.parseInt(u.getId()), u.getName(), u.getIs_potentially_hazardous_asteroid(),
                    u.getAbsolute_magnitude_h(), u.getClose_approach_data()[0].getClose_approach_date_full(),
                    u.getClose_approach_data()[0].getMiss_distance().getKilometers()))
                    .collect(Collectors.toList());
            lvListAsteroids.setAdapter(new AsteroidsAdapter(
                    this,
                    new ArrayList<>(table),
                    R.layout.list_asteroids
            ));
            lvListAsteroids.setOnItemClickListener(this);
            countAsteroids.setText(String.valueOf(table.size()));
            asteroidDangerous = table.stream().filter(u->u.getIsDangerous().equals("Si")).count();
            this.CallLamp();
        }
        catch (Exception ex){
            this.ShowMessage(ex.getMessage());
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(MainActivity.this, ShowAsteroid.class);
        intent.putExtra("Resultado", table.get(position));
        startActivity(intent);
    }

    private void ShowMessage(String message){
        Snackbar.make(
                findViewById(android.R.id.content),
                message,
                Snackbar.LENGTH_LONG
        ).show();
    }

    public void CallLamp(){
        try {
            TextView textLamp = findViewById(R.id.textLamp);
            lampApi = LampApiadapter.getApiService();
            OnLamp();
            SoundLamp();
            switch ((int)asteroidDangerous){
                case 0:
                    ColorLamp(new Color(19,62,187));
                    textLamp.setBackgroundResource(R.color.blue);
                    textLamp.setText("Azul");
                    break;
                case 1:
                case 2:
                case 3:
                    ColorLamp(new Color(34,187,19));
                    textLamp.setBackgroundResource(R.color.green);
                    textLamp.setText("Verde");
                    break;
                default:
                    RainbowLamp();
                    textLamp.setText("Arcoíris");
                    textLamp.setBackgroundResource(R.color.white);
                    break;
            }
        }
        catch (Exception ex){
            this.ShowMessage(ex.getMessage());
        }
    }

    public void SoundLamp(){
        if (table.size() >= 10) {
            lampApi.setBeep().enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    //ShowMessage("Sonido");
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    ShowMessage(t.getMessage());
                }
            });
        }
    }

    public void ColorLamp(Color color)
    {
        lampApi.setColor(color).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                //ShowMessage("Color");
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                ShowMessage(t.getMessage());
            }
        });
    }

    public void RainbowLamp()
    {
        lampApi.setRainbow().enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
               // ShowMessage("Arcoíris");
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                ShowMessage(t.getMessage());
            }
        });
    }

    public void OnLamp()
    {
        lampApi.onLamp().enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                ShowMessage("On");
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                ShowMessage(t.getMessage());
            }
        });
    }

    public void OffLamp()
    {
        lampApi.offLamp().enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                ShowMessage("Off");
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                ShowMessage(t.getMessage());
            }
        });
    }



}
