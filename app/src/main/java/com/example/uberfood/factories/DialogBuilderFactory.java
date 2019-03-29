package com.example.uberfood.factories;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.uberfood.R;

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



}
