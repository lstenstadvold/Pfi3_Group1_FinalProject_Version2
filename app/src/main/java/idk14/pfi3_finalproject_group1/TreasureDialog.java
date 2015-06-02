package idk14.pfi3_finalproject_group1;


import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;


/**
 * Created by Lars
 * A simple {@link Fragment} subclass.
 */
public class TreasureDialog extends DialogFragment implements View.OnClickListener {
    public Button scanButton;
    public String treasureValue;
    public TextView treasureText;
    public String scanContent;
    public TextView desText;
    public ImageView treasureImage;

    public TreasureDialog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_treasure_dialog, container, false);

        treasureValue = (String) getArguments().getSerializable("treasure");

         scanButton = (Button) view.findViewById(R.id.scan_button);

        treasureText = (TextView) view.findViewById(R.id.treasureText);
        desText = (TextView) view.findViewById(R.id.treasureDescription);

        treasureImage = (ImageView) view.findViewById(R.id.treasureImage);

        if(treasureValue.equals("1")){
            treasureText.setText("You Found Water");
            treasureImage.setImageDrawable(getResources().getDrawable(R.drawable.treasure_water));

        }
        if(treasureValue.equals("2")){
            treasureText.setText("You Found Air");
            treasureImage.setImageDrawable(getResources().getDrawable(R.drawable.treasure_air));

        }
        if(treasureValue.equals("3")){
            treasureText.setText("You Found Sun");
            treasureImage.setImageDrawable(getResources().getDrawable(R.drawable.treasure_sun));

        }
        if(treasureValue.equals("0")){
            treasureText.setText("Nothing here!");
            treasureImage.setImageDrawable(getResources().getDrawable(R.drawable.treasure_empty));
            desText.setVisibility(View.INVISIBLE);
            scanButton.setVisibility(View.INVISIBLE);



        }

        scanButton.setOnClickListener(this);


        getDialog().setCanceledOnTouchOutside(true);
        getDialog().dismiss();

        return view;

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
        if (scanningResult != null && resultCode != 0 && intent != null) {

            scanContent = scanningResult.getContents();
            //String scanFormat = scanningResult.getFormatName();
            //contentTxt.setText("CONTENT: " + scanContent);
            System.out.println("content: " + scanContent);

            if(scanContent.equals("TREE")){
                System.out.println("You scanned the tree!");
                updateLightCue();


            }else{
                System.out.println("This is not the tree");
                treasureText.setText("Bring your treasure back to the tree!");

            }

        } else {
            Toast.makeText(getActivity(), "No scan data received!", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateLightCue(){

        Firebase lightCueRef = StartFragment.ref.child("LightCue");

        lightCueRef.setValue(Integer.parseInt(treasureValue));

        System.out.println("You got a light show!");
        treasureText.setText("You got a light show!");
        treasureImage.setVisibility(View.INVISIBLE);
        desText.setVisibility(View.GONE);
        scanButton.setVisibility(View.GONE);



        //remove the item that has been delivered from the arraylist
        UserData.inventory.remove(InventoryFragment.selectedTreasure);
        //add an empty element to the end of the list
        UserData.inventory.add("0");
    }


}

