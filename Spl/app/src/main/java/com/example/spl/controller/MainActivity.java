package com.example.spl.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spl.R;
import com.example.spl.model.PlayerProfile;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private TextView userName;
    private Button signOut;
    private Button playerProfile;
    private Button allPlayerProfile;
    private DatabaseReference namesRef;
    private DatabaseReference rootRef;
    private Button createMatch;
    private DatabaseReference userRef;
    private ValueEventListener adminButtonListner;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            Log.i("TAG", "onCreate: sdnjksnjk");
            Intent intent = new Intent(this, LoginActivity.class);
            this.startActivity(intent);
            this.finish();
        } else {
            try {
                rootRef = FirebaseDatabase.getInstance().getReference();
                namesRef = rootRef.child("players");
                if (firebaseUser.getMetadata().getCreationTimestamp() == firebaseUser.getMetadata().getLastSignInTimestamp()) {
                    final PlayerProfile.Builder playerProfileBuilder = new PlayerProfile.Builder(firebaseUser.getDisplayName(),
                            firebaseUser.getPhotoUrl().toString());
                    final PlayerProfile playerProfile = playerProfileBuilder.build();
                    final Map<String, Object> playerProfileMap = new HashMap<>();
                    playerProfileMap.put(firebaseUser.getUid(), playerProfile);
                    namesRef.updateChildren(playerProfileMap);
                }
                userRef = namesRef.child(firebaseUser.getUid());
            } catch (Exception e) {
                Log.e(TAG, "onCreate: Exception while updating player data " + e.getMessage());
            }

            try {

                adminButtonListner = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        // Get Post object and use the values to update the UI
                        PlayerProfile playerProfile = snapshot.getValue(PlayerProfile.class);
                        if (playerProfile.isAdmin()) {
                            createMatch.setVisibility(View.VISIBLE);
                        } else {
                            createMatch.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                };
            } catch (Exception e) {
                Log.e(TAG, "onCreate: Exception while updating match button " + e.getMessage());
            }

            init();
            userName.setText("Welcome " + firebaseUser.getDisplayName());
            playerProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(getApplicationContext(), PlayerProfileActivity.class);
                        MainActivity.this.startActivity(intent);
                    } catch (Exception e) {
                        Log.e(TAG, "onClick: calling player profile activity " + e.getMessage());
                    }

                }
            });
            createMatch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(getApplicationContext(), MatchMakerActivity.class);
                        MainActivity.this.startActivity(intent);
                    } catch (Exception e) {
                        Log.e(TAG, "onClick: calling player match maker activity " + e.getMessage());
                    }
                }
            });

            //Sign out clicked
            signOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AuthUI.getInstance()
                            .signOut(getApplicationContext())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                public void onComplete(@NonNull Task<Void> task) {
                                    // ...
                                    if (task.isComplete()) {
                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                        MainActivity.this.startActivity(intent);
                                        MainActivity.this.finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Error during sign out", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }
                            });
                }
            });
        }
    }

    private void init() {
        userName = (TextView) findViewById(R.id.userName);
        signOut = (Button) findViewById(R.id.logout);
        playerProfile = (Button) findViewById(R.id.profile_button);
        allPlayerProfile = (Button) findViewById(R.id.all_profile_button);
        createMatch = (Button) findViewById(R.id.create_match_button);
    }

    @Override
    protected void onStart() {
        if (userRef != null && adminButtonListner != null) {
            userRef.addValueEventListener(adminButtonListner);
        }
        super.onStart();
    }

    @Override
    protected void onPause() {
        if (userRef != null && adminButtonListner != null) {
            userRef.removeEventListener(adminButtonListner);
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (userRef != null && adminButtonListner != null) {
            userRef.addValueEventListener(adminButtonListner);
        }
        super.onResume();
    }

    @Override
    protected void onStop() {
        if (userRef != null && adminButtonListner != null) {
            userRef.removeEventListener(adminButtonListner);
        }
        super.onStop();
    }
}
