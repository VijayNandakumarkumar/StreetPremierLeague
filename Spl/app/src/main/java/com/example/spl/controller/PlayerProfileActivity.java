package com.example.spl.controller;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spl.R;
import com.example.spl.model.PlayerProfile;
import com.google.android.gms.common.util.Strings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class PlayerProfileActivity extends AppCompatActivity {
    private static final String TAG = PlayerProfileActivity.class.getName();
    private FirebaseUser firebaseUser;


    private TextView playerName;
    private TextView playerNumber;
    private ImageView playerDisplayPhoto;
    private TextView playerTotalRuns;
    private TextView playerWickets;
    private TextView playerStrikeRate;
    private DatabaseReference rootRef;
    private DatabaseReference namesRef;
    private TextView playerTotalMatchesPlayed;
    private ValueEventListener postListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_profile);
        init();
        if (firebaseUser == null) {
            Toast.makeText(this, "The fire user is null at profile", Toast.LENGTH_SHORT).show();
        } else {
            updateprofile(firebaseUser);
        }
    }

    private void updateprofile(FirebaseUser firebaseUser) {
        try {
            postListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // Get Post object and use the values to update the UI
                    PlayerProfile playerProfile = dataSnapshot.getValue(PlayerProfile.class);

                    if (!Strings.isEmptyOrWhitespace(playerProfile.getPlayerName())) {
                        playerName.setText(playerProfile.getPlayerName());
                    }

                    Picasso.get().load(playerProfile.getDisplayPhoto()).fit().into(playerDisplayPhoto);

                    if (!Strings.isEmptyOrWhitespace(playerProfile.getPlayerNumber())) {
                        playerNumber.setText(playerProfile.getPlayerNumber());
                    }

                    if (!Strings.isEmptyOrWhitespace(playerProfile.getPlayerTotalRunsScored())) {
                        playerTotalRuns.setText(playerProfile.getPlayerTotalRunsScored());
                    }

                    if (!Strings.isEmptyOrWhitespace(playerProfile.getPlayerTotalMatchesPlayed())) {
                        playerTotalMatchesPlayed.setText(playerProfile.getPlayerTotalMatchesPlayed());
                    }

                    if (!Strings.isEmptyOrWhitespace(playerProfile.getPlayerStrikeRate())) {
                        playerStrikeRate.setText(playerProfile.getPlayerStrikeRate());
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Getting Post failed, log a message
                    Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                }
            };
        } catch (Exception e) {
            Toast.makeText(this, "daiiii", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "onCreate: Exception while fetching data from firebase" + e.getMessage());
        }
    }

    private void init() {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        rootRef = FirebaseDatabase.getInstance().getReference();
        namesRef = rootRef.child("players/" + firebaseUser.getUid());
        playerName = (TextView) findViewById(R.id.playerName);
        playerNumber = (TextView) findViewById(R.id.playerNumber);
        playerDisplayPhoto = (ImageView) findViewById(R.id.playerDisplayPhoto);
        playerTotalRuns = (TextView) findViewById(R.id.playerTotalRuns);
        playerWickets = (TextView) findViewById(R.id.playerTotalWicktes);
        playerStrikeRate = (TextView) findViewById(R.id.playerStrikeRate);
        playerTotalMatchesPlayed = (TextView) findViewById(R.id.playerTotalMatches);
    }
    @Override
    protected void onStart() {
        if (namesRef != null && postListener != null) {
            namesRef.addValueEventListener(postListener);
        }
        super.onStart();
    }

    @Override
    protected void onPause() {
        if (namesRef != null && postListener != null) {
            namesRef.removeEventListener(postListener);
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (namesRef != null && postListener != null) {
            namesRef.addValueEventListener(postListener);
        }
        super.onResume();
    }

    @Override
    protected void onStop() {
        if (namesRef != null && postListener != null) {
            namesRef.removeEventListener(postListener);
        }
        super.onStop();
    }
}
