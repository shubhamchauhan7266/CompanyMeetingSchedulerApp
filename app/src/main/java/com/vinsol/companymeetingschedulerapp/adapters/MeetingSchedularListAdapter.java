package com.vinsol.companymeetingschedulerapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vinsol.companymeetingschedulerapp.R;
import com.vinsol.companymeetingschedulerapp.models.MeetingSchedulDetailsResponseModel;

import java.util.ArrayList;

public class MeetingSchedularListAdapter extends RecyclerView.Adapter<MeetingSchedularListAdapter.ViewHolder> {

    public ArrayList<MeetingSchedulDetailsResponseModel> mMeetingScheduleDetailsList;

    public MeetingSchedularListAdapter(ArrayList<MeetingSchedulDetailsResponseModel> meetingScheduleDetailsList) {

        mMeetingScheduleDetailsList = meetingScheduleDetailsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_meeting_schedular_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        MeetingSchedulDetailsResponseModel meetingScheduleDetails = mMeetingScheduleDetailsList.get(position);

        // TODO update ui
    }

    @Override
    public int getItemCount() {
        return mMeetingScheduleDetailsList != null ? mMeetingScheduleDetailsList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
