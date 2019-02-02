package com.vinsol.companymeetingschedulerapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vinsol.companymeetingschedulerapp.R;
import com.vinsol.companymeetingschedulerapp.models.MeetingScheduleDetailsResponseModel;

import java.util.ArrayList;

public class MeetingSchedularListAdapter extends RecyclerView.Adapter<MeetingSchedularListAdapter.ViewHolder> {

    public ArrayList<MeetingScheduleDetailsResponseModel> mMeetingScheduleDetailsList;

    public MeetingSchedularListAdapter(ArrayList<MeetingScheduleDetailsResponseModel> meetingScheduleDetailsList) {

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

        MeetingScheduleDetailsResponseModel meetingScheduleDetails = mMeetingScheduleDetailsList.get(position);

        viewHolder.tvTime.setText(String.valueOf(meetingScheduleDetails.getStart_time() + " - " + meetingScheduleDetails.getEnd_time()));
        viewHolder.tvDescription.setText(meetingScheduleDetails.getDescription() != null ? meetingScheduleDetails.getDescription() : "");
    }

    @Override
    public int getItemCount() {
        return mMeetingScheduleDetailsList != null ? mMeetingScheduleDetailsList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTime;
        private final TextView tvDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTime = itemView.findViewById(R.id.tv_time);
            tvDescription = itemView.findViewById(R.id.tv_description);
        }
    }
}
