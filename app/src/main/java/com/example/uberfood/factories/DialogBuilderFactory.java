package com.example.uberfood.factories;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;

import com.example.uberfood.R;

public class DialogBuilderFactory {


    public void showFilterDialog(Context context){


        final LayoutInflater inflater = LayoutInflater.from(context);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context , R.style.AppCompatAlertDialogStyleSuggest);

    }



}
