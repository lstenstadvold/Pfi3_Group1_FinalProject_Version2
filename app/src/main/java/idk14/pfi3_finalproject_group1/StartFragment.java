package idk14.pfi3_finalproject_group1;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment implements View.OnClickListener {

   public TextView contentTxt;


    public StartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_start, container, false); //Fills the layout
        Button button = (Button) v.findViewById(R.id.button);
        contentTxt = (TextView) v.findViewById(R.id.scan_content);



        button.setOnClickListener(this);

        return v;
    }
//test


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            contentTxt.setText("CONTENT: " + scanContent);
        } else {
           // Toast toast = Toast.makeText(getApplicationContext(),
           //         "No scan data received!", Toast.LENGTH_SHORT);
           // toast.show();
        }
    }


}
