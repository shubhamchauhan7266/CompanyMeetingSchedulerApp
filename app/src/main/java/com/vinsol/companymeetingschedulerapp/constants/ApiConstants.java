package com.vinsol.companymeetingschedulerapp.constants;

/**
 * This interface is used for Api Constant.
 *
 * @author Manish Soni
 */
public interface ApiConstants {

    String BASE_URL = "http://fathomless-shelf-5846.herokuapp.com/";
    String MEETING_SCHEDULE_DETAILS_API = "api/schedule";

    interface API_REQUEST_CODE {
        int MEETING_SCHEDULE_DETAILS_CODE = 1;

    }

    interface API_PARAM_CONSTANT {
        String DATE_KEY = "date";
    }

}
