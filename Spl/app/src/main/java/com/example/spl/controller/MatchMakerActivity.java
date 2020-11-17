package com.example.spl.controller;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spl.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MatchMakerActivity extends AppCompatActivity {
    private static final String TAG = MatchMakerActivity.class.getName();
    private TextView matchNumber;
    private DatabaseReference rootRef;
    private DatabaseReference namesRef;
    private FirebaseUser firebaseUser;
    private ValueEventListener matchListner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_maker);
        init();
        try {
            matchListner = new ValueEventListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onDataChange(final DataSnapshot snapshot) {
                    final Long totalMatchesPlayer = (Long) snapshot.getValue();
                    Toast.makeText(getApplicationContext(), "DEi naan ulla vanten daww " + totalMatchesPlayer, Toast.LENGTH_SHORT).show();
                    Long currentMatch = totalMatchesPlayer + 1;
                    matchNumber.setText("Match " + String.valueOf(currentMatch));
                }

                @Override
                public void onCancelled(final DatabaseError error) {

                }
            };
        } catch (Exception e) {
            Log.e(TAG, "onCreate: Exception while updating match number "+ e.getMessage());
        }
    }

    private void init() {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        rootRef = FirebaseDatabase.getInstance().getReference();
        namesRef = rootRef.child("totalMatchesPlayed");
        matchNumber = (TextView) findViewById(R.id.match_number);
    }

    @Override
    protected void onStart() {
        if (namesRef != null && matchListner != null) {
            namesRef.addValueEventListener(matchListner);
        }
        super.onStart();
    }

    @Override
    protected void onPause() {
        if (namesRef != null && matchListner != null) {
            namesRef.removeEventListener(matchListner);
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (namesRef != null && matchListner != null) {
            namesRef.addValueEventListener(matchListner);
        }
        super.onResume();
    }

    @Override
    protected void onStop() {
        if (namesRef != null && matchListner != null) {
            namesRef.removeEventListener(matchListner);
        }
        super.onStop();
    }

}
