package com.ScheduleSoccer02;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DetailActivity extends AppCompatActivity {
    ImageView imageView;

    TextView homeyellowcard, awayyellowcard,leaguehome, leagueaway, date , time , scoreaway , scorehome, AwayLineupDefense, AwayLineupGoalkeeper, HomeLineupDefense, lineupgoalkepperhome, HomeLineupMidfield, AwayLineupMidfield, strEvent;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Starting Lineup");



        //inisialisasi
        strEvent = findViewById(R.id.strEvent);
        leaguehome = (TextView) findViewById(R.id.tv_Leaguehome);
        leagueaway = (TextView) findViewById(R.id.tv_Leagueaway);
        date = (TextView) findViewById(R.id.date);
        time = (TextView) findViewById(R.id.atime);
        scorehome = (TextView) findViewById(R.id.tv_scorehome);
        scoreaway = (TextView) findViewById(R.id.tv_scoreaway);
        homeyellowcard = findViewById(R.id.tv_homeforward);
        awayyellowcard = findViewById(R.id.tv_awayforward);
        AwayLineupDefense = findViewById(R.id.tv_AwayLineupDefense);
        AwayLineupGoalkeeper = findViewById(R.id.tv_AwayLineupGoalkeeper);
        HomeLineupDefense = findViewById(R.id.tv_HomeLineupDefense);
        lineupgoalkepperhome = findViewById(R.id.tv_lineupgoalkepperhome);
        HomeLineupMidfield = findViewById(R.id.tv_HomeLineupMidfield);
        AwayLineupMidfield = findViewById(R.id.tv_AwayLineupMidfield);


        final ModelJadwal surah = getIntent().getExtras().getParcelable("hasil");
        System.out.println("teasssds " + getIntent().getStringExtra("events"));

        HomeLineupDefense.setText(getIntent().getStringExtra("events11"));
        homeyellowcard.setText(getIntent().getStringExtra("events3"));
        awayyellowcard.setText(getIntent().getStringExtra("events5"));
        AwayLineupDefense.setText(getIntent().getStringExtra("events4"));
        AwayLineupGoalkeeper.setText(getIntent().getStringExtra("events7"));
        HomeLineupDefense.setText(getIntent().getStringExtra("events11"));
        lineupgoalkepperhome.setText(getIntent().getStringExtra("events9"));
        HomeLineupMidfield.setText(getIntent().getStringExtra("events6"));
        AwayLineupMidfield.setText(getIntent().getStringExtra("events8"));
        strEvent.setText(getIntent().getStringExtra("events"));
        leaguehome.setText(getIntent().getStringExtra("events12"));
        leagueaway.setText(getIntent().getStringExtra("events13"));
        date.setText(getIntent().getStringExtra("events14"));
        time.setText(getIntent().getStringExtra("events15"));
        scorehome.setText(getIntent().getStringExtra("events16"));
        scoreaway.setText(getIntent().getStringExtra("events17"));

    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}