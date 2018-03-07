package com.example.fauziw97.reptilamfibijogja;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.fauziw97.reptilamfibijogja.Adapter.InstitutionAdapter;
import com.example.fauziw97.reptilamfibijogja.Adapter.TeamAdapter;
import com.example.fauziw97.reptilamfibijogja.Model.InstitutionModel;
import com.example.fauziw97.reptilamfibijogja.Model.TeamModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity {
    @BindView(R.id.recycler_view_team)
    RecyclerView rv_team;
    @BindView(R.id.recycler_view_thanks)
    RecyclerView rv_thanks;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
    private List<TeamModel> mTeamModels;
    private List<InstitutionModel> mInstModels;
    private RecyclerView.Adapter mAdapter, instAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        mInstModels = new ArrayList<>();
        mTeamModels = new ArrayList<>();
        retrieveInst();
        retrieveData();

        //Recycler View Thanks
        rv_thanks.setHasFixedSize(true);
        rv_thanks.setNestedScrollingEnabled(false);
        rv_thanks.setLayoutManager(new LinearLayoutManager(this));

        //Recycler View Team
        rv_team.setHasFixedSize(true);
        rv_team.setNestedScrollingEnabled(false);
        rv_team.setLayoutManager(new LinearLayoutManager(this));


        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Tentang");


    }

    private void retrieveInst() {
        DatabaseReference refThanks = mRef.child("Thanks");
        refThanks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    InstitutionModel instModel = new InstitutionModel(
                            snapshot.child("nama").getValue(String.class),
                            snapshot.child("logo").getValue(String.class));

                    mInstModels.add(instModel);

                    instAdapter = new InstitutionAdapter(getApplicationContext(), mInstModels);
                    rv_thanks.setAdapter(instAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void retrieveData() {
        DatabaseReference refTeam = mRef.child("Team");
        refTeam.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    TeamModel teamModel = new TeamModel(
                            snapshot.child("foto").getValue(String.class),
                            snapshot.child("nama").getValue(String.class),
                            snapshot.child("role").getValue(String.class),
                            snapshot.child("email").getValue(String.class),
                            snapshot.child("facebook").getValue(String.class));

                    mTeamModels.add(teamModel);

                    mAdapter = new TeamAdapter(mTeamModels, getApplicationContext());
                    rv_team.setAdapter(mAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
