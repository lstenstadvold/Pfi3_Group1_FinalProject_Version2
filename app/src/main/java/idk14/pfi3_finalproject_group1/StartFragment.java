package idk14.pfi3_finalproject_group1;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment implements View.OnClickListener {


    public StartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_start, container, false); //Fills the layout
        Button button = (Button) v.findViewById(R.id.button);
        button.setOnClickListener(this);

        return v;
    }
//test

    public void onClick(View v) {

    }


}
