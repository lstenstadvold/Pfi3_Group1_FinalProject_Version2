package idk14.pfi3_finalproject_group1;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    Treasure myTreasure = new Treasure("0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_layout,new StartFragment());
        ft.commit();

        Firebase.setAndroidContext(this);

        updateFirebase();
        checkFirebase();





/*
        ref.setValue("I'm writing data", new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                if (firebaseError != null) {
                    System.out.println("Data could not be saved. " + firebaseError.getMessage());
                } else {
                    System.out.println("Data saved successfully.");
                }
            }
        });
        */

    }

    public void updateFirebase() {

        //testing add treasures, data structure

        Firebase ref = new Firebase(Constants.FIREBASE_URL);

        Treasure treasure1 = new Treasure("2");
        Treasure treasure2 = new Treasure("3");

        Firebase treasuresRef = ref.child("treasures");

        Map<String, Treasure> treasures = new HashMap<String, Treasure>();
        treasures.put("treasure1", treasure1);
        treasures.put("treasure2", treasure2);

        treasuresRef.setValue(treasures);




    }


    public void checkFirebase() {

        Firebase ref = new Firebase(Constants.FIREBASE_URL);
        Firebase treasure1Ref = ref.child("treasures/treasure1/treasureType");


        treasure1Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("Treasure value is: " + snapshot.getValue());
                myTreasure.setTreasureType(snapshot.getValue().toString());
                System.out.println("My treasure type is: " + myTreasure.getTreasureType());

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //hhgg

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {

            case R.id.action_settings:
                return true;

                case R.id.Map:
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.start_layout, new MapFragment());
                    ft.addToBackStack(null);
                    ft.commit();
                    return false;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed(){
        if(getFragmentManager().getBackStackEntryCount() >0) {
            getFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }
    }
}
