package idk14.pfi3_finalproject_group1.ActionBarIcons;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import idk14.pfi3_finalproject_group1.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class QrFragment extends Fragment implements View.OnClickListener{


    public QrFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    //container.removeAllViews();
        View v = inflater.inflate(R.layout.fragment_qr, container, false);

        Button b = (Button) v.findViewById(R.id.button3);
        b.setOnClickListener(this);

        return v;
    }


    @Override
    public void onClick(View v) {

        switch ((v.getId())){
            case R.id.button3:

                ScanFragment scanFragment = new ScanFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_layout, scanFragment,"tag");
                ft.addToBackStack(null);
                ft.commit();
                break;
        }
    }
}
