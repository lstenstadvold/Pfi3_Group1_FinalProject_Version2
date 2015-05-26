package idk14.pfi3_finalproject_group1.Help;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import idk14.pfi3_finalproject_group1.StartFragment;
import idk14.pfi3_finalproject_group1.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScanFragment extends Fragment implements View.OnClickListener {


    public ScanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        container.removeAllViews();
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_scan, container, false);
        Button b =(Button) v.findViewById(R.id.button4);
        b.setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View v) {
        switch ((v.getId())){
            case R.id.button4:

                StartFragment startFragment = new StartFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_layout, startFragment);
                ft.commit();
                break;
        }
    }
}
