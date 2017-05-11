package spohlmann.mobiledevices.scholasticago;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by spohlmann on 5/8/2017.
 * This is the activity that lists the details of the selected place allows the user to enter the code so they could show if they visited the
 * places on the list or not.
 */

public class PlaceDetail extends AppCompatActivity {
    TextView textViewName;
    TextView textViewLatitude;
    TextView textViewLongitude;
    TextView textViewCompleted;
    EditText editTextCode;
    Button buttonEnterCode;
    Button buttonBack;

    Bundle bundle;
    Places places;
    PlacesFirebaseData placesDataSource;

    /*
     * Gets the intent sent from MainActivity
     * displays the information from the object that was sent over in the intent.
     * sets the onclick listener for the buttons and includes and intent to send it back to the Main activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        bundle = getIntent().getExtras();
        places = (Places) bundle.getSerializable("Places");

        placesDataSource = new PlacesFirebaseData();
        DatabaseReference myPlacesDbRef = placesDataSource.open();

        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewLatitude = (TextView) findViewById(R.id.textViewLatitude);
        textViewLongitude = (TextView) findViewById(R.id.textViewLongitude);
        textViewCompleted = (TextView) findViewById(R.id.textViewCompleted);
        editTextCode = (EditText) findViewById(R.id.editTextCode);
        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonEnterCode = (Button) findViewById(R.id.buttonEnterCode);

        textViewName.setText(places.getLocationName());
        textViewLatitude.setText(places.getLocationLatitude().toString());
        textViewLongitude.setText(places.getLocationLongitude().toString());
        textViewCompleted.setText(places.getComplete());

        buttonEnterCode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                checkComplete();
                }
            }
        );
        buttonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent mainActIntent = new Intent(view.getContext(), MainActivity.class);
                finish();
                startActivity(mainActIntent);
            }
        });
    }
    /*
     * Checks to see if the code entered by the user matches the code for the object. If it does it updates
     * the Complete value of the object to Completed and updates the textView on the page.
     */
    public void checkComplete(){
        int placeCode = places.getCode();
        int checkCode = Integer.parseInt(editTextCode.getText().toString());
        if (checkCode == placeCode){
            places.setComplete("Completed");
            placesDataSource.updatePlace(places);
            textViewCompleted.setText(places.getComplete());
        }
    }
}
