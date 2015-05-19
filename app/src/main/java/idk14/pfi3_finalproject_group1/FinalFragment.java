package idk14.pfi3_finalproject_group1;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FinalFragment extends Fragment implements View.OnClickListener {


    public FinalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_final, container, false);

        TextView finalTxt = (TextView)v.findViewById(R.id.textFinal);
        Button playAgainButton = (Button)v.findViewById(R.id.buttonPlayAgain);

        finalTxt.setText("Congratulations! Enjoy your light show!");

        playAgainButton.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonPlayAgain) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.main_layout, new StartFragment());
            ft.commit();
        }
    }


}
