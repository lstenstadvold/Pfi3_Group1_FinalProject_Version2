package idk14.pfi3_finalproject_group1.Help;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.koushikdutta.ion.Ion;

import idk14.pfi3_finalproject_group1.R;
import idk14.pfi3_finalproject_group1.StartFragment;


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
       //container.removeAllViews(); // This removes the "START THE HUNT" button, only problem is that
      // when you try to go back( add to backStack) you get a blank screen.

        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_help, container, false);
        Button b = (Button) v.findViewById(R.id.button2);

        b.setOnClickListener(this);

       //ImageView gif = (ImageView) v.findViewById(R.id.TestGif);
        //Ion.with(gif).load("http://38.media.tumblr.com/tumblr_mdgfpehkYN1r8cvzdo1_500.gif");

        return v;
    }

    public void onClick(View v) {
        int id = v.getId();

            if(id==R.id.button2){
/*      switch ((v.getId())){
            case R.id.button2:*/

                StartFragment startFragment = new StartFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_layout, startFragment,"tag");
                ft.addToBackStack(null);
                ft.commit();
               // break;
        }}

    }





