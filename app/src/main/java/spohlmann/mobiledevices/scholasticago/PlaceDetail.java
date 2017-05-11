package spohlmann.mobiledevices.scholasticago;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    boolean result = false;

    Bundle bundle = getIntent().getExtras();
    Places places = (Places) bundle.getSerializable("Places");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);



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
    public void checkComplete(){
        int placeCode = places.getCode();
        int checkCode = Integer.parseInt(editTextCode.getText().toString());
        if (checkCode == placeCode){
            places.setComplete("Completed");
        }
    }
}
