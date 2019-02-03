package com.vinsol.companymeetingschedulerapp.models;

import com.vinsol.companymeetingschedulerapp.constants.Constants;
import com.vinsol.companymeetingschedulerapp.utills.DateUtills;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class MeetingScheduleDetailsResponseModel implements Serializable {

    private String start_time;
    private String end_time;
    private String description;
    private ArrayList<String> participants;

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<String> participants) {
        this.participants = participants;
    }

    /**
     * Comparator class for MeetingScheduleDetailsResponseModel model to sort by date
     */
    public static final Comparator<MeetingScheduleDetailsResponseModel> COMPARE_BY_DATE = new Comparator<MeetingScheduleDetailsResponseModel>() {
        public int compare(MeetingScheduleDetailsResponseModel one, MeetingScheduleDetailsResponseModel other) {
            return DateUtills.getParsedDate(one.start_time, Constants.HH_MM).compareTo(DateUtills.getParsedDate(other.start_time,Constants.HH_MM));
        }
    };
}
