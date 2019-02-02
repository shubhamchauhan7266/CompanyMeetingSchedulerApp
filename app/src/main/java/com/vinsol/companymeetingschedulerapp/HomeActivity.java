package com.vinsol.companymeetingschedulerapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vinsol.companymeetingschedulerapp.adapters.MeetingSchedularListAdapter;
import com.vinsol.companymeetingschedulerapp.models.MeetingScheduleDetailsResponseModel;
import com.vinsol.companymeetingschedulerapp.ui.MeetingScheduleDetailsViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final RecyclerView rvMeetingDetailsList = findViewById(R.id.rv_meeting_details_list);

        MeetingScheduleDetailsViewModel viewModel =
                ViewModelProviders.of(this).get(MeetingScheduleDetailsViewModel.class);

        viewModel.getMeetingListDetails("7/8/2015").observe(this,
                new Observer<List<MeetingScheduleDetailsResponseModel>>() {
            @Override
            public void onChanged(@Nullable List<MeetingScheduleDetailsResponseModel> meetingDetailsList) {

                MeetingSchedularListAdapter adapter = new MeetingSchedularListAdapter((ArrayList<MeetingScheduleDetailsResponseModel>) meetingDetailsList);
                rvMeetingDetailsList.setAdapter(adapter);
            }
        });
    }
}
