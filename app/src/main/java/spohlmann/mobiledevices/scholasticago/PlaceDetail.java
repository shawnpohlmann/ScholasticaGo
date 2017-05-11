package spohlmann.mobiledevices.scholasticago;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by spohlmann on 5/8/2017.
 */

public class PlaceDetail extends AppCompatActivity {
    TextView textViewName;
    TextView textViewLatitude;
    TextView textViewLongitude;
    TextView textViewCompleted;
    EditText editTextCode;
    Button buttonEnterCode;
    Button buttonBack;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        Bundle bundle = getIntent().getExtras();
        //Places places = (Places) bundle.getSerializable("Places");
        String places = (String) bundle.getSerializable("Places");
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewLatitude = (TextView) findViewById(R.id.textViewLatitude);
        textViewLongitude = (TextView) findViewById(R.id.textViewLongitude);
        textViewCompleted = (TextView) findViewById(R.id.textViewCompleted);
        editTextCode = (EditText) findViewById(R.id.editTextCode);
        buttonBack = (Button) findViewById(R.id.buttonBack);

        /*textViewName.setText(places.getLocationName());
        textViewLatitude.setText(places.getLocationLatitude().toString());
        textViewLongitude.setText(places.getLocationLongitude().toString());
        textViewCompleted.setText(places.getComplete()); */

        textViewName.setText(places);



    }
}
