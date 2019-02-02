package com.vinsol.companymeetingschedulerapp.ui.home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vinsol.companymeetingschedulerapp.R;
import com.vinsol.companymeetingschedulerapp.adapters.MeetingSchedularListAdapter;
import com.vinsol.companymeetingschedulerapp.models.MeetingScheduleDetailsResponseModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final RecyclerView rvMeetingDetailsList = findViewById(R.id.rv_meeting_details_list);

        findViewById(R.id.bt_schedule).setOnClickListener(this);

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

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.bt_schedule:
                break;
        }
    }
}
