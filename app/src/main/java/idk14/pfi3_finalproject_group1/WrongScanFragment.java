package idk14.pfi3_finalproject_group1;

import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class WrongScanFragment extends DialogFragment implements View.OnClickListener {


    public WrongScanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_wrong_scan, container, false);
        v.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        WrongScanFragment.this.dismiss();
    }
}
