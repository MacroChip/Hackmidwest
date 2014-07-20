package edu.truman.android.hackmidwest.main_screen_view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.truman.android.hackmidwest.R;
import edu.truman.android.hackmidwest.major_dialog.MajorDialog;
import roboguice.fragment.RoboFragment;

public class MajorSpinnerFragment extends RoboFragment {
    public static final String MAJOR_DIALOG = "major dialog";
    private Button submitCompanyButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_spinner_major, container, false);

        submitCompanyButton = (Button) view.findViewById(R.id.spinner_major_submit);
        submitCompanyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                MajorDialog dialog = new MajorDialog();
                dialog.show(fm, MAJOR_DIALOG);
            }
        });
        return view;
    }
}
