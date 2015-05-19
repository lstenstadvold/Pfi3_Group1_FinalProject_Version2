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
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import idk14.pfi3_finalproject_group1.Scanner.IntentIntegrator;
import idk14.pfi3_finalproject_group1.Scanner.IntentResult;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment implements View.OnClickListener {

    public TextView contentTxt;
    static String myTreasure;
    public String scanContent;
    static Firebase ref = new Firebase(Constants.FIREBASE_URL);


    public StartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_start, container, false); //Fills the layout
        Button button = (Button) v.findViewById(R.id.button);
        //contentTxt = (TextView) v.findViewById(R.id.scan_content);

        button.setOnClickListener(this);

        return v;
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {

            scanContent = scanningResult.getContents();
            //String scanFormat = scanningResult.getFormatName();
           // contentTxt.setText("CONTENT: " + scanContent);
            System.out.println("content: " + scanContent);

            if(scanContent.equals("TREE")){
                System.out.println("You scanned the tree!");
                contentTxt.setText("You scanned the tree! \n Go find and scan a treasure code!");
            }else{
                checkFirebase();

            }

        } else {
/*            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();*/
        }
    }

    //Method that reads what value is under the TreasureLocation that is specified by the
    //scanned QR code (scanContent)

    public void checkFirebase() {


        Firebase treasureRef = ref.child(scanContent);


        treasureRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("Treasure value is: " + snapshot.getValue());

                //stores value in the myTreasure object
                myTreasure = snapshot.getValue().toString();

                //checks that the value has been stored correctly
                System.out.println("My treasure type is: " + myTreasure);

                updateFirebase();

                //checks if myTreasure contains a value or if it is 0 (no treasure)

                if ((myTreasure != null) && (myTreasure.equals("0") == false)) {
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.main_layout, new TreasureFragment());
                    ft.commit();

                    // doesn't open up the TreasureFragment if the value at treasureLocation is 0
                } else if (myTreasure.equals("0")) {
                    contentTxt.setText("This spot was empty!");
                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });


    }

    public void updateFirebase() {

        Firebase treasureRef = ref.child(scanContent);
        treasureRef.setValue(0);


    }

}
