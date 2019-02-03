package com.vinsol.companymeetingschedulerapp.ui.schedule;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.vinsol.companymeetingschedulerapp.R;
import com.vinsol.companymeetingschedulerapp.constants.Constants;
import com.vinsol.companymeetingschedulerapp.models.MeetingScheduleDetailsResponseModel;
import com.vinsol.companymeetingschedulerapp.ui.home.MeetingScheduleDetailsViewModel;
import com.vinsol.companymeetingschedulerapp.utills.DateUtills;
import com.vinsol.companymeetingschedulerapp.utills.OtherUtils;

import java.util.Calendar;
import java.util.List;

public class ScheduleAMeetingActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvDate;
    private TextView mTvStartTime;
    private TextView mTvEndTime;
    private EditText mEtDescription;
    private Calendar myCalendar;
    private MeetingScheduleDetailsViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_ameeting);

        initView();

        mViewModel = ViewModelProviders.of(this).get(MeetingScheduleDetailsViewModel.class);
    }

    /**
     * Method is used to initialize view.
     */
    private void initView() {
        mTvDate = findViewById(R.id.tv_date);
        mTvStartTime = findViewById(R.id.tv_start_time);
        mTvEndTime = findViewById(R.id.tv_end_time);
        mEtDescription = findViewById(R.id.et_description);

        mTvDate.setOnClickListener(this);
        mTvStartTime.setOnClickListener(this);
        mTvEndTime.setOnClickListener(this);
        findViewById(R.id.bt_submit).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);

        myCalendar = Calendar.getInstance();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.bt_submit:

                if (isValidAllData()) {

                    mViewModel.getMeetingListDetails(mTvDate.getText().toString()).observe(this, new Observer<List<MeetingScheduleDetailsResponseModel>>() {
                        @Override
                        public void onChanged(@Nullable List<MeetingScheduleDetailsResponseModel> meetingScheduleDetailsList) {
                            scheduleAMeeting(meetingScheduleDetailsList);
                        }
                    });
                } else {
                    OtherUtils.showAlertDialog(getString(R.string.error_message), getString(R.string.ok), this);
                }
                break;

            case R.id.tv_date:

                DatePickerDialog datePickerDialog = new DatePickerDialog(this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
                break;

            case R.id.tv_start_time: {

                Calendar currentTime = Calendar.getInstance();
                int hour = currentTime.get(Calendar.HOUR_OF_DAY);
                int minute = currentTime.get(Calendar.MINUTE);
                TimePickerDialog timePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        mTvStartTime.setText(String.valueOf(selectedHour + ":" + selectedMinute));
                    }
                }, hour, minute, true);//Yes 24 hour time
                timePicker.setTitle(getString(R.string.select_time));
                timePicker.show();
            }
            break;

            case R.id.tv_end_time: {

                Calendar currentTime = Calendar.getInstance();
                int hour = currentTime.get(Calendar.HOUR_OF_DAY);
                int minute = currentTime.get(Calendar.MINUTE);
                TimePickerDialog timePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        mTvEndTime.setText(String.valueOf(selectedHour + ":" + selectedMinute));
                    }
                }, hour, minute, true);//Yes 24 hour time
                timePicker.setTitle(getString(R.string.select_time));
                timePicker.show();
            }
            break;

            case R.id.iv_back:
                finish();
                break;

            default:
                break;
        }
    }

    /**
     * Method is used to check data is valid or not.
     *
     * @return true if all field is valid otherwise return false.
     */
    private boolean isValidAllData() {

        return !mTvDate.getText().equals(getResources().getString(R.string.meeting_date)) && !mTvStartTime.getText().equals(getResources().getString(R.string.start_time))
                && !mTvEndTime.getText().equals(getResources().getString(R.string.end_time)) && mEtDescription.getText().toString().trim().length() > 0;
    }

    /**
     * Method is used to schedule a meeting if possible.
     *
     * @param meetingScheduleDetailsList A list of meeting details for given date.
     */
    private void scheduleAMeeting(List<MeetingScheduleDetailsResponseModel> meetingScheduleDetailsList) {

        boolean isSlotAvailable = true;
        for(MeetingScheduleDetailsResponseModel meetingDetails: meetingScheduleDetailsList){
            if(isSlotNotAvailable(meetingDetails)){
                isSlotAvailable = false;
                OtherUtils.showAlertDialog(getString(R.string.slot_not_available), getString(R.string.ok), this);
                break;
            }
        }

        if(isSlotAvailable){
            OtherUtils.showAlertDialog(getString(R.string.slot_available), getString(R.string.ok), this);
        }
    }

    /**
     * Method is used to check slot is available or not.
     *
     * @param meetingDetails meetingDetails
     * @return true if slot is not available otherwise false.
     */
    private boolean isSlotNotAvailable(MeetingScheduleDetailsResponseModel meetingDetails) {
        return (DateUtills.compareTime(DateUtills.getParsedDate(mTvStartTime.getText().toString(),Constants.HH_MM),
                DateUtills.getParsedDate(meetingDetails.getStart_time(),Constants.HH_MM))>0
                && DateUtills.compareTime(DateUtills.getParsedDate(meetingDetails.getStart_time(),Constants.HH_MM),
                DateUtills.getParsedDate(mTvEndTime.getText().toString(),Constants.HH_MM))>0)

                || (DateUtills.compareTime(DateUtills.getParsedDate(mTvStartTime.getText().toString(),Constants.HH_MM),
                DateUtills.getParsedDate(meetingDetails.getEnd_time(),Constants.HH_MM))>0
                && DateUtills.compareTime(DateUtills.getParsedDate(meetingDetails.getEnd_time(),Constants.HH_MM),
                DateUtills.getParsedDate(mTvEndTime.getText().toString(),Constants.HH_MM))>0)

                || (DateUtills.compareTime(DateUtills.getParsedDate(meetingDetails.getStart_time(),Constants.HH_MM),
                DateUtills.getParsedDate(mTvStartTime.getText().toString(),Constants.HH_MM))>0
                && DateUtills.compareTime(DateUtills.getParsedDate(mTvEndTime.getText().toString(),Constants.HH_MM),
                DateUtills.getParsedDate(meetingDetails.getEnd_time(),Constants.HH_MM))>0);
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    /**
     * Method is used to set date on date label.
     */
    private void updateLabel() {

        mTvDate.setText(DateUtills.parseDate(Constants.DD_MM_YYYY, myCalendar));
    }
}
