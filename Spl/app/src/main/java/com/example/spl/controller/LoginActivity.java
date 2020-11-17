package com.example.spl.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.spl.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getName();
    private static final int RC_SIGN_IN = 10001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Toast.makeText(this, "yessss", Toast.LENGTH_SHORT).show();
            try {
                final Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                this.finish();
                return;
            } catch (Exception e) {
                Log.e(TAG, "Exception while launching main activity", e);
            }
        } else {
            Toast.makeText(this, "nooo", Toast.LENGTH_SHORT).show();
            createSignInRequest();
        }
    }

    private void createSignInRequest() {
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build());

        // Create and launch sign-in intent
        Intent intent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTheme(R.style.Login)
                .setTosAndPrivacyPolicyUrls("http://example/com", "http://example/com")
                .setLogo(R.drawable.ic_launcher_foreground)
                .setAlwaysShowSignInMethodScreen(true)
                .build();
        startActivityForResult(intent,
                RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                final Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
                this.finish();
                // ...
            } else {
                Toast.makeText(this.getApplicationContext(), response.getError().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}