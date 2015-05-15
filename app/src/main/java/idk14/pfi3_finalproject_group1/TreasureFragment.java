package idk14.pfi3_finalproject_group1;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TreasureFragment extends Fragment {


    public TreasureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_treasure, container, false);

        TextView treasureText = (TextView) v.findViewById(R.id.treasureText);

        if(StartFragment.myTreasure.equals("1")){
            treasureText.setText("You found water!");
        }
        if(StartFragment.myTreasure.equals("2")){
            treasureText.setText("You found air!");
        }
        if(StartFragment.myTreasure.equals("3")){
            treasureText.setText("You found sun!");
        }
        if(StartFragment.myTreasure.equals("0")){
            treasureText.setText("You found nothing!");
        }

        return v;
    }


}
