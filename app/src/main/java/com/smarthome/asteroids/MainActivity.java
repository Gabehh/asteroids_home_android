package com.smarthome.asteroids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                // user is signed in
                CharSequence username = user.getDisplayName();
                //Toast.makeText(MainActivity.this, getString(R.string.firebase_user_fmt, username), Toast.LENGTH_LONG).show();
                //Log.i(LOG_TAG, "onAuthStateChanged() " + getString(R.string.firebase_user_fmt, username));
                //((TextView) findViewById(R.id.textView)).setText(getString(R.string.firebase_user_fmt, username));
            } else {
                // user is signed out
                startActivityForResult(
                        // Get an instance of AuthUI based on the default app
                        AuthUI.getInstance().
                                createSignInIntentBuilder().
                                setAvailableProviders(Arrays.asList(
                                        //new AuthUI.IdpConfig.FacebookBuilder().build(),
                                        //new AuthUI.IdpConfig.GitHubBuilder().build(),
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
    private static final int RC_SIGN_IN = 2019;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAuth = FirebaseAuth.getInstance();
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


    @Override
    public void onClick(View v) {

    }
}
