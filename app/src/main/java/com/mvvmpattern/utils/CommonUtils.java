package com.mvvmpattern.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.LocationManager;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.mvvmpattern.R;
import com.mvvmpattern.base.MvvmApplication;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.util.Log.e;


/**
 * Created by  Kuljeet Singh.
 */
public class CommonUtils  {


    public static CommonUtils util;


    public static CommonUtils getInstance() {
        if (util == null)
            util = new CommonUtils();
        return util;
    }


    //    public static String  ContactUrl="https://www.google.co.in";
//    public static String  AboutUrl="https://www.google.co.in";
//    public static String  TermOfUseUrl="https://www.google.co.in";




    /**
     * @return return list of all the states in database
     */
//    public static List<CallDetail> getCallDetails() {
//        return new RushSearch().find(CallDetail.class);
//    }


    /**
     * delete RushObjects from database
     *
     * @param objects RushObject's list
     */
//    public static void deleteRecord(List<? extends RushObject> objects) {
//        int size = objects.size();
//        for (int i = 0; i < size; i++) {
//            objects.get(i).delete();
//        }
//    }

    /**
     * @return return server time
     */
//    public static long getServerSyncTime() {
//        long uptimeDiff = SystemClock.elapsedRealtime() - BaseApp.BOOT_UP_TIME;
//        return BaseApp.SERVER_TIME + uptimeDiff;
//    }

    /**
     * @return return device Id
     */
    public static String getDeviceId() {
        String android_id = Settings.Secure.getString(MvvmApplication.getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return android_id;

    }

    /*
* This method is used to check email id format
 * */
    public boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;

        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }


    /**
     * print error log with message
     *
     * @param message message to be printed in log
     */
    public static void log(String message) {
        e(new Throwable().getStackTrace()[1].getClassName(), message);
    }

    /**
     * Show snackbar
     *
     * @param text text to be displayed on Toast
     */
    public static void showToast(String text) {
        Toast.makeText(MvvmApplication.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    /**
     * check if user has enabled Gps of device
     *
     * @return true or false depending upon device Gps status
     */
    public boolean isGpsEnabled() {
        final LocationManager manager = (LocationManager) MvvmApplication.getContext().getSystemService(Context.LOCATION_SERVICE);
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * return true if string is empty or null
     *
     * @param message string to be check
     */
    public static Boolean isEmptyString(String message) {
        return message == null || message.trim().equalsIgnoreCase("null") || message.trim().equalsIgnoreCase("");
    }

    /**
     * @param message message to be displayed in Toast.
     */
    public static void toast(@NonNull String message) {
        Toast.makeText(MvvmApplication.getContext(), message, Toast.LENGTH_SHORT).show();
    }


    public static void displayPromptForEnablingGPS(final Activity activity) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final String action = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
        final String message = "GPS location setting for the device should be on for the GPS location feature.\nDo you want open GPS setting?";

        builder.setMessage(message)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface d, int id) {
                                activity.startActivity(new Intent(action));
                                d.dismiss();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface d, int id) {
                                d.cancel();
                            }
                        });
        builder.create().show();
    }

    public static String replaceNullWithEmptString(@Nullable String input) {
        return input == null ? "" : input;
    }


    /**
     * check if n/w is available or not
     *
     * @return is connected or not
     */
    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivity = (ConnectivityManager) MvvmApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo anInfo : info) {
                    if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Hide keyboard on touch on screen
     *
     * @param view
     */
    public static void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) MvvmApplication.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }




    /**
     * Hide keyboard on touch on screen
     *
     * @param view
     */
    public static void showKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) MvvmApplication.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInputFromInputMethod(view.getWindowToken(), 0);
    }

    /**
     * Set sanackbar
     *
     * @param layout
     * @param text
     * @param duration
     * @param backgroundColor
     * @param textColor
     * @return
     */
    public static Snackbar makeSnackbar(View layout, CharSequence text, int duration, int backgroundColor, int textColor, int mGravity) {
        Snackbar snackBarView = Snackbar.make(layout, text, duration);
        View view = snackBarView.getView();
        view.setBackgroundColor(backgroundColor);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.gravity = mGravity;
        //snackBarView.setActionTextColor(actionTextColor);
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(textColor);

        return snackBarView;
    }


    /**
     * create no google play notification
     */
    private static void createGPlayNotification() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MvvmApplication.getContext());
        mBuilder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("No Google Play Service")
                .setContentText("Please Download the Google Play Service")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Please Download the Google Play Service"))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setLargeIcon(BitmapFactory.decodeResource(MvvmApplication.getContext().getResources(), R.mipmap.ic_launcher))
                .setAutoCancel(true)
        ;
        NotificationManager mNotificationManager = (NotificationManager) MvvmApplication.getContext().getSystemService(Context.NOTIFICATION_SERVICE);

        // notificationID allows you to update the notification later on.
        mNotificationManager.notify(101, mBuilder.build());
    }

    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }


    }

    /**
     * return a List popup window
     *
     * @return
     */
    public static PopupWindow popupWindowSort(List<String> results, EditText anchor, AdapterView.OnItemClickListener clickListener) {
        PopupWindow window = new PopupWindow(MvvmApplication.getContext());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(MvvmApplication.getContext(), R.layout.item_search_suggestion, results);
        // the drop down list is a list view
        ListView listViewSort = new ListView(MvvmApplication.getContext());
        // set our adapter and pass our pop up window contents
        listViewSort.setAdapter(adapter);
        //set list color
        listViewSort.setBackgroundColor(Color.WHITE);
        // set on item selected
        listViewSort.setOnItemClickListener(clickListener);
        // some other visual settings for popup window
        window.setWidth(anchor.getWidth());
        // popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.white));
        window.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        // set the listview as popup content
        window.setOutsideTouchable(true);
        window.setContentView(listViewSort);
        return window;
    }

}

