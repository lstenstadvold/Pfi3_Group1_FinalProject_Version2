package idk14.pfi3_finalproject_group1.Help;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import idk14.pfi3_finalproject_group1.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment {


    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
      //  container.removeAllViews();
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        return v;
    }

/*
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0 ){
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
*/




}

