package com.ScheduleSoccer02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.ScheduleSoccer02.Fragment.LeaderboardFragment;
import com.ScheduleSoccer02.Fragment.ScheduleFragment;
import com.ScheduleSoccer02.Fragment.ScoreFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private long backPressedTime;
    private Toast backToast;
    private DrawerLayout drawer;
    ImageView circleImageView;
    TextView name, email;
    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView rvSurah;
    private jadwalAdapter allLeaguesAdapter;
    private List<ModelJadwal> allLeagueList = new ArrayList<>();
    private ProgressDialog mProgress;


    SwipeRefreshLayout swipeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new LeaderboardFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_leaderboard);
        }


        View headerView = navigationView.getHeaderView(0);
        circleImageView = headerView.findViewById(R.id.imageView);
        name = headerView.findViewById(R.id.un);
        email = headerView.findViewById(R.id.em);

//        setDataOnView();

//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//
//        googleSignInClient = GoogleSignIn.getClient(MainActivity.this, gso);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (backPressedTime + 2000 > System.currentTimeMillis()) {
                backToast.cancel();
                super.onBackPressed();
                return;
            } else {
                backToast = Toast.makeText(getBaseContext(), "Tekan tombol kembali lagi untuk keluar", Toast.LENGTH_SHORT);
                backToast.show();
            }
            backPressedTime = System.currentTimeMillis();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_leaderboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new LeaderboardFragment()).commit();
                break;
            case R.id.nav_schedule:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ScheduleFragment()).commit();
                break;
            case R.id.nav_score:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ScoreFragment()).commit();
                break;
            case R.id.nav_logout:
                logout();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


//    private void setDataOnView() {
//        SharedPreferences mSettings = getApplicationContext().getSharedPreferences("Settings", Context.MODE_PRIVATE);
//        String cookiePicture = mSettings.getString("Picture", "Picture");
//        String cookieName = mSettings.getString("Name", "Name");
//        String cookieEmail = mSettings.getString("Email", "Email");
//
//        if (!cookiePicture.equals("dummy")) {
//            Glide.with(this).load(cookiePicture).apply(new RequestOptions().override(100, 100)).into(circleImageView);
//        }
//        name.setText(cookieName);
//        email.setText(cookieEmail);
//    }

    private void logout() {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage("Anda yakin ingin keluar ?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

//
//                                //On Succesfull signout we navigate the user back to LoginActivity
//                                SharedPreferences mSettings = getApplicationContext().getSharedPreferences("Settings", Context.MODE_PRIVATE);
//                                SharedPreferences.Editor editor = mSettings.edit();
//                                editor.clear();
//                                editor.commit();
//                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                startActivity(intent);
                            }


                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }


}