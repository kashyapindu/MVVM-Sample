package com.mvvmpattern.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.mvvmpattern.R;

import java.util.List;




/**
 * Created by kuljeet on 06/09/16.
 * contains Dialogs to be used in the application
 */
public class DialogsUtil  {

    private Context mContext;
    public static Dialog connectionDialog;

    public DialogsUtil(Context context) {
        this.mContext = context;
    }

    /**
     * Return an alert dialog
     *
     * @param message  message for the alert dialog
     * @param listener listener to trigger selection methods
     */
    public void openAlertDialog(Context context, String message, String positiveBtnText, String negativeBtnText,
                                final OnDialogButtonClickListener listener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setPositiveButton(positiveBtnText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        arg0.dismiss();
                        listener.onPositiveButtonClicked();
                    }
                });

        builder.setNegativeButton(negativeBtnText,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        arg0.dismiss();
                        listener.onNegativeButtonClicked();
                    }
                });
        builder.setTitle(context.getResources().getString(R.string.app_name));
        builder.setMessage(message);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setCancelable(false);
        builder.create().show();
    }

    /**
     * return a dialog object
     * @return
     */
    public Dialog openDialog(@LayoutRes int layoutId) {
        Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(layoutId);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    /**
     * show loader dialog
     */

    public  void showLoading(Context context) {
        hideLoading(context);
        connectionDialog = new Dialog(context, R.style.CustomAlertDialog);
        connectionDialog.setCancelable(true);
        connectionDialog.setCanceledOnTouchOutside(false);
        connectionDialog.setContentView(R.layout.custom_dialog);
        connectionDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
            }
        });
        connectionDialog.show();
    }

    /**
     * hide loading dialog
     */

    public  void hideLoading(Context context) {
        if (connectionDialog != null) {
            connectionDialog.dismiss();
            connectionDialog = null;
        }
    }
    /**
     * return a List popup window
     *
     * @return
     */
    public PopupWindow popupWindowSort(List<String> results, EditText anchor, AdapterView.OnItemClickListener clickListener) {
        PopupWindow window = new PopupWindow(mContext);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, R.layout.item_search_suggestion, results);
        // the drop down list is a list view
        ListView listViewSort = new ListView(mContext);
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