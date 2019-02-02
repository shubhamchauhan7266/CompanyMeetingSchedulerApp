package com.vinsol.companymeetingschedulerapp.ui.schedule;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.vinsol.companymeetingschedulerapp.R;
import com.vinsol.companymeetingschedulerapp.constants.Constants;
import com.vinsol.companymeetingschedulerapp.utills.DateUtills;

import java.util.Calendar;

public class ScheduleAMeetingActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvDate;
    private TextView mTvStartTime;
    private TextView mTvEndTime;
    private EditText mEtDescription;
    private Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_ameeting);

        initView();
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

        myCalendar = Calendar.getInstance();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.bt_submit:
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
                timePicker.setTitle("Select Time");
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
                timePicker.setTitle("Select Time");
                timePicker.show();
            }
            break;

            default:
                break;
        }
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
