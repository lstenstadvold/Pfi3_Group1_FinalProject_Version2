package idk14.pfi3_finalproject_group1;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment implements View.OnClickListener {

    //public TextView contentTxt;
    //public TextView emptyTxt;
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
        //emptyTxt = (TextView) v.findViewById(R.id.textViewEmpty);


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
        if (scanningResult != null && resultCode != 0 && intent != null) {

            scanContent = scanningResult.getContents();
            //String scanFormat = scanningResult.getFormatName();
           // contentTxt.setText("CONTENT: " + scanContent);
            System.out.println("content: " + scanContent);

            checkFirebaseConnection();

            if(scanContent.equals("TREE")){
                System.out.println("You scanned the tree!");
                FragmentManager fm = getFragmentManager();
                WrongScanFragment wsf = new WrongScanFragment();
                wsf.show(fm, "No item scan");




                //contentTxt.setText("You scanned the tree! \n Go find and scan a treasure code!");
            }else{
                checkFirebase();

            }

        } else {
          /* Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();*/
            Toast.makeText(getActivity(), "No scan data received!", Toast.LENGTH_SHORT).show();
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
                    ft.addToBackStack(null);
                    ft.commit();

                    // doesn't open up the TreasureFragment if the value at treasureLocation is 0
                } else if (myTreasure.equals("0")) {
                    //emptyTxt.setVisibility(View.VISIBLE);
                    System.out.println("The treasure spot is empty!");
                    FragmentManager fm = getFragmentManager();
                    EmptyFragment ef = new EmptyFragment();
                    ef.show(fm, "Info");

                } else if (myTreasure == null){
                    System.out.println("Something is wrong, try again.");
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

    public void checkFirebaseConnection() {

        Firebase connectedRef = new Firebase(Constants.FIREBASE_URL + "/.info/connected");

        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    System.out.println("connected");
                } else {
                    System.out.println("not connected");
                    FragmentManager fm = getFragmentManager();
                    NoConnectionFragment nof = new NoConnectionFragment();
                    nof.show(fm, "No Connection");

                }
            }

            @Override
            public void onCancelled(FirebaseError error) {
                System.err.println("Listener was cancelled");
            }
        });
    }

}
