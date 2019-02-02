package com.vinsol.companymeetingschedulerapp.utills;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateUtills {

    public static String parseDate(String format, Calendar calendar){

        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);

        return sdf.format(calendar.getTime());
    }
}
