package com.ScheduleSoccer02.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ScheduleSoccer02.Contants;
import com.ScheduleSoccer02.ModelJadwal;
import com.ScheduleSoccer02.R;
import com.ScheduleSoccer02.jadwalAdapter;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ScheduleFragment extends Fragment {

    private RecyclerView rvSurah;
    private jadwalAdapter allLeaguesAdapter;
    private List<ModelJadwal> allLeagueList = new ArrayList<>();
    private ProgressDialog mProgress;
    private SwipeRefreshLayout swipeLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rvSurah = view.findViewById(R.id.recycler_view);
        swipeLayout = view.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code here
                allLeagueList.clear();
                fetchscheduleApi();
                // To keep animation for 4 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Stop animation (This will be after 3 seconds)
                        swipeLayout.setRefreshing(false);
                    }
                }, 1000);
                Toast.makeText(getActivity(), "DATA SIAP", Toast.LENGTH_SHORT).show();// Delay in millis
            }
        });
        setupRecycler();
        fetchscheduleApi();




        return view;
    }
    private void setupRecycler() {
        allLeaguesAdapter = new jadwalAdapter(getContext(), allLeagueList);
        rvSurah.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvSurah.setHasFixedSize(true);
        rvSurah.setAdapter(allLeaguesAdapter);
    }
    private void fetchscheduleApi() {
        AndroidNetworking.get(Contants.NEXT_URL)
                .setTag("leagues")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray hasilList = response.getJSONArray("events");
                            for (int i = 0; i < hasilList.length(); i++) {
                                JSONObject hasil = hasilList.getJSONObject(i);
                                ModelJadwal item = new ModelJadwal();
                                item.setStrHomeTeam(hasil.getString("strHomeTeam"));
                                item.setStrAwayTeam(hasil.getString("strAwayTeam"));
                                item.setStrDate(hasil.getString("strDate"));
                                item.setStrTime(hasil.getString("strTime"));
                                item.setStrEvent(hasil.getString("strEvent"));
                                item.setStrThumb(hasil.getString("strThumb"));
                                System.out.println("qwert " + hasil.getString("strEvent"));
                                allLeagueList.add(item);
                            }

                            allLeaguesAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("", "onError: " + anError.getErrorBody());
                        Toast.makeText(getActivity(), Contants.EROR, Toast.LENGTH_SHORT).show();
                    }
                });


    }

}
