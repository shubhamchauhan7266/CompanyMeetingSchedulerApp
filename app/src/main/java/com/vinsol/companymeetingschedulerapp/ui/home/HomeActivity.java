package com.vinsol.companymeetingschedulerapp.ui.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vinsol.companymeetingschedulerapp.BaseActivity;
import com.vinsol.companymeetingschedulerapp.R;
import com.vinsol.companymeetingschedulerapp.adapters.MeetingSchedularListAdapter;
import com.vinsol.companymeetingschedulerapp.constants.Constants;
import com.vinsol.companymeetingschedulerapp.models.MeetingScheduleDetailsResponseModel;
import com.vinsol.companymeetingschedulerapp.ui.schedule.ScheduleAMeetingActivity;
import com.vinsol.companymeetingschedulerapp.utills.ConnectivityUtils;
import com.vinsol.companymeetingschedulerapp.utills.DateUtills;
import com.vinsol.companymeetingschedulerapp.utills.OtherUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class HomeActivity extends BaseActivity implements View.OnClickListener, Observer<List<MeetingScheduleDetailsResponseModel>> {

    private Calendar mCalendar;
    private RecyclerView mRvMeetingDetailsList;
    private MeetingScheduleDetailsViewModel mViewModel;
    private LiveData<List<MeetingScheduleDetailsResponseModel>> mMeetingDetailsListObserver;
    private TextView mTvDate;
    private MeetingSchedularListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mRvMeetingDetailsList = findViewById(R.id.rv_meeting_details_list);
        mTvDate = findViewById(R.id.tv_date);

        findViewById(R.id.bt_schedule).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.iv_forward).setOnClickListener(this);
        findViewById(R.id.tv_forward).setOnClickListener(this);
        findViewById(R.id.tv_prev).setOnClickListener(this);

        mViewModel = ViewModelProviders.of(this).get(MeetingScheduleDetailsViewModel.class);

        mCalendar = Calendar.getInstance();
        mTvDate.setText(DateUtills.parseDate(Constants.DD_MM_YYYY_hypen, mCalendar));

        mRvMeetingDetailsList.setLayoutManager(new LinearLayoutManager(this));
        mRvMeetingDetailsList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new MeetingSchedularListAdapter(new ArrayList<MeetingScheduleDetailsResponseModel>());
        mRvMeetingDetailsList.setAdapter(mAdapter);

        mViewModel.getMeetingListDetails(DateUtills.parseDate(Constants.DD_MM_YYYY, mCalendar)).observe(this,this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.bt_schedule:

                Intent intent = new Intent(HomeActivity.this, ScheduleAMeetingActivity.class);
                startActivity(intent);
                break;

            case R.id.tv_prev:
            case R.id.iv_back:

                if (!ConnectivityUtils.isNetworkEnabled(this)) {
                    OtherUtils.showAlertDialog(getString(R.string.no_internet_connection), getString(R.string.ok), this);
                    return;
                }
                showProgressDialog();
                mCalendar.add(Calendar.DAY_OF_MONTH, -1);
                mTvDate.setText(DateUtills.parseDate(Constants.DD_MM_YYYY_hypen, mCalendar));
                mViewModel.getMeetingListDetails(DateUtills.parseDate(Constants.DD_MM_YYYY, mCalendar));
                break;

            case R.id.tv_forward:
            case R.id.iv_forward:

                if (!ConnectivityUtils.isNetworkEnabled(this)) {
                    OtherUtils.showAlertDialog(getString(R.string.no_internet_connection), getString(R.string.ok), this);
                    return;
                }
                showProgressDialog();
                mCalendar.add(Calendar.DAY_OF_MONTH, 1);
                mTvDate.setText(DateUtills.parseDate(Constants.DD_MM_YYYY_hypen, mCalendar));
                mViewModel.getMeetingListDetails(DateUtills.parseDate(Constants.DD_MM_YYYY, mCalendar));
                break;
        }
    }

    @Override
    public void onChanged(@Nullable List<MeetingScheduleDetailsResponseModel> meetingDetailsList) {

        removeProgressDialog();
        if (meetingDetailsList != null) {
            Collections.sort(meetingDetailsList, MeetingScheduleDetailsResponseModel.COMPARE_BY_DATE);
            mAdapter.setMeetingScheduleDetailsList((ArrayList<MeetingScheduleDetailsResponseModel>) meetingDetailsList);
            mAdapter.notifyDataSetChanged();
        }
    }
}
