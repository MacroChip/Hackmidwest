package edu.truman.android.hackmidwest.major_dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import roboguice.fragment.RoboDialogFragment;

public class MajorDialog extends RoboDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        return new AlertDialog.Builder(getActivity())
                .setTitle("Majors")
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}
