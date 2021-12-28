package com.example.DeDeforest;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

import DeDeforest.R;

public class RequestDataFragment extends DialogFragment {
    final Calendar newCalender=Calendar.getInstance();
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        String stringArgs=getArguments().getString("stringArgKey");

        Log.i("ARGS", stringArgs);

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=this.getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_get_bounds,null);
        builder.setTitle("Select Date To Request Image")
                .setView(view)
                .setMessage(""+stringArgs)
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //action to happen on click
                        DatePicker datePicker=view.findViewById(R.id.datePicker1);
                        int day=datePicker.getDayOfMonth();
                        int month=datePicker.getMonth()+1;
                        int year=datePicker.getYear();
                        Toast.makeText(getContext(), "Date:\n"+day+" - "+month+" - "+year, Toast.LENGTH_LONG).show();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //what should happen on click
                    }
                });
        //create the AlertDialog object and return it
        return builder.create();
        //return super.onCreateDialog(savedInstanceState);
    }
    public static RequestDataFragment newInstance(String stringArg) {
        RequestDataFragment f = new RequestDataFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("stringArgKey", stringArg);
        f.setArguments(args);
        return f;
    }
}
