package idk14.pfi3_finalproject_group1;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class HelpFragment extends Fragment implements View.OnClickListener {


    public HelpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_help, container, false);
        Button b = (Button) v.findViewById(R.id.button2);

        b.setOnClickListener(this);

        return v;
    }

    public void onClick(View v) {
        int id = v.getId();

            if(id==R.id.button2){
                StartFragment startFragment = new StartFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_layout, startFragment,"tag");
                ft.commit();
        }}

    }





