package com.vinsol.companymeetingschedulerapp.utills;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;

import com.vinsol.companymeetingschedulerapp.R;

public class OtherUtils {

    /**
     * Method to show Alert Dialog with only positive button with not callback
     *
     * @param message            message for alert
     * @param positiveButtonText ok button text
     */
    public static void showAlertDialog(String message, String positiveButtonText, Context context) {
        if (context == null || ((Activity) context).isDestroyed() || ((Activity) context).isFinishing()) {
            return;
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setPositiveButton(positiveButtonText, null);
        builder.setMessage(Html.fromHtml(message));
        builder.setTitle(context.getResources().getString(R.string.app_name));
        if (!((AppCompatActivity) context).isDestroyed() && !((AppCompatActivity) context).isFinishing()) {
            try {
                builder.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
