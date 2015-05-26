package idk14.pfi3_finalproject_group1;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.firebase.client.Firebase;
import idk14.pfi3_finalproject_group1.Help.HelpFragment;
import idk14.pfi3_finalproject_group1.Help.MapFragment;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Firebase.setAndroidContext(this);

        setContentView(R.layout.activity_main);

        final String PREFS_NAME = "MyPrefsFile";

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);


       // START THE HELP FRAGMENT FOR FIRST TIME USERS

        if (settings.getBoolean("my_first_time", true)) {
            //the app is being launched for first time, do something
            Log.d("Comments", "First time");

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.main_layout, new HelpFragment());
            ft.addToBackStack(null);
            ft.commit();

            // record the fact that the app has been started at least once
            settings.edit().putBoolean("my_first_time", false).commit();

        } else {

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.main_layout, new StartFragment());
            ft.commit();
        }

    }

// ACTIONSBAR MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

            if(id==R.id.Map){
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.main_layout, new MapFragment());
                ft.addToBackStack(null);
                ft.commit();
                return false;
            }

            if(id==R.id.Help){
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_layout, new HelpFragment());
                ft.addToBackStack(null);
                ft.commit();
                return false;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() > 0 ){
            getFragmentManager().popBackStack();

            //Click the back button twice -> AlertDialog
        } else if (getFragmentManager().getBackStackEntryCount() <1){
            new AlertDialog.Builder(this)
                    .setTitle("Exit")
                    .setMessage("Do you want to exit?")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            MainActivity.super.onBackPressed();
                        }
                    }).create().show();
        } else {
            super.onBackPressed();
        }
    }
}