package com.example.uberfood.factories;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.uberfood.R;
import com.example.uberfood.activities.MenuActivity;
import com.example.uberfood.utils.Utils;

public class DialogBuilderFactory {

    private static ImageView closeFilter ;
    private static AppCompatTextView clearAll , okFilter ;


    public static void showFilterDialog(Context context){


        final LayoutInflater inflater = LayoutInflater.from(context);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context , R.style.AppCompatAlertDialogStyleSuggest);

        final View customDialog = inflater.inflate(R.layout.alert_dialog_layout, null , false);
        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setView(customDialog);

        closeFilter = customDialog.findViewById(R.id.close_filter);
        clearAll = customDialog.findViewById(R.id.clear_all);
        okFilter = customDialog.findViewById(R.id.ok_filter);

        closeFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        okFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();


    }


    public static void showDialog(final Context context ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                context, R.style.AlertDialogCustom);

        builder.setTitle("Exit ");
        builder.setMessage("Delete order");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {


                MenuActivity.priceText.setText("0 DT");
                Utils.price = 0 ;
                ((Activity) context).finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        AlertDialog alert = builder.create();
        alert.show();
        alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(context.getResources().getColor(R.color.colorBlack));
        alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(context.getResources().getColor(R.color.colorOrange));
        alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                // gridviewPhoto.setVisibility(View.VISIBLE);
            }
        });
    }




}
