package idk14.pfi3_finalproject_group1;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Firebase;


/**
 * A simple {@link Fragment} subclass.
 */
public class TreasureFragment extends Fragment implements View.OnClickListener {

    public String scanContent;
    public TextView treasureText;


    public TreasureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_treasure, container, false);

        Button scanButton = (Button) v.findViewById(R.id.scan_button);

        treasureText = (TextView) v.findViewById(R.id.treasureText);

        ImageView treasureImage = (ImageView) v.findViewById(R.id.treasureImage);

        if(StartFragment.myTreasure.equals("1")){
            treasureText.setText("You Found Water");
            treasureImage.setImageDrawable(getResources().getDrawable(R.drawable.wateritem));

        }
        if(StartFragment.myTreasure.equals("2")){
            treasureText.setText("You Found Air");
            treasureImage.setImageDrawable(getResources().getDrawable(R.drawable.airitem));

        }
        if(StartFragment.myTreasure.equals("3")){
            treasureText.setText("You Found Sun");
            treasureImage.setImageDrawable(getResources().getDrawable(R.drawable.sunitem));

        }
        if(StartFragment.myTreasure.equals("0")){
            treasureText.setText("You Found Nothing");
            treasureImage.setImageDrawable(getResources().getDrawable(R.drawable.sadface));

        }


        scanButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.scan_button) {
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {

            scanContent = scanningResult.getContents();
            //String scanFormat = scanningResult.getFormatName();
            //contentTxt.setText("CONTENT: " + scanContent);
            System.out.println("content: " + scanContent);

            if(scanContent.equals("TREE")){
                System.out.println("You scanned the tree!");
                updateLightCue();

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_layout, new FinalFragment());
                ft.commit();

            }else{
                System.out.println("This is not the tree");
                treasureText.setText("Bring your treasure back to the tree!");

            }

        } else {
            //Toast toast = Toast.makeText(getApplicationContext(),
              //       "No scan data received!", Toast.LENGTH_SHORT);
            //toast.show();
        }
    }

    public void updateLightCue(){

        Firebase lightCueRef = StartFragment.ref.child("LightCue");

        lightCueRef.setValue(Integer.parseInt(StartFragment.myTreasure));

        System.out.println("You got a light show!");
        treasureText.setText("You got a light show!");

        StartFragment.myTreasure = null;
    }


}
