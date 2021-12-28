package com.example.DeDeforest;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class RequestDataFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        String stringArgs=getArguments().getString("stringArgKey");
        Toast.makeText(getContext(), "bounds:\n"+stringArgs, Toast.LENGTH_LONG).show();

        Log.i("ARGS", stringArgs);

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Select Date To Request Image")
                .setMessage(""+stringArgs)
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //action to happen on click
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
