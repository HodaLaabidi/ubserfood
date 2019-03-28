package com.example.uberfood.factories;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

import com.example.uberfood.R;

public class DialogBuilderFactory {


    public static void showFilterDialog(Context context){


        final LayoutInflater inflater = LayoutInflater.from(context);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context , R.style.AppCompatAlertDialogStyleSuggest);

        final View customDialog = inflater.inflate(R.layout.alert_dialog_layout, null , false);
        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setView(customDialog);
        alertDialog.show();


    }



}
