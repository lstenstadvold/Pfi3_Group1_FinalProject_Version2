package idk14.pfi3_finalproject_group1;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    //Lars test

    Treasure myTreasure = new Treasure("0");

    //Test comment Test comment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_layout, new StartFragment());
        ft.commit();

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
