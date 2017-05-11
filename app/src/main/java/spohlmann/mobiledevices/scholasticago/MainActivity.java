package spohlmann.mobiledevices.scholasticago;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button buttonLogOut;
    Button buttonDetails;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String TAG = "CIS3334";
    PlacesFirebaseData placeDataSource;
    ArrayAdapter<Places> placeAdapter;
    List<Places> placesList;
    ListView placesListView;
    DatabaseReference myPlacesDbRef;
    int positionSelected;
    Places placeSelected;

    /*
     * This is the onCreate for the when the app starts.
     * Checks if user is logged in sets up a database connection sets the list view along with the
     * fields and the onClickListeners for the buttons.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mAuth = FirebaseAuth.getInstance();
        checkUserAuthentication();
        setupFirebaseDataChange();
        setupListView();

        buttonDetails = (Button) findViewById(R.id.buttonDetails);
        buttonDetails.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("MAIN", "onClick for Details");
                Intent detailActIntent = new Intent(MainActivity.this, PlaceDetail.class);
                detailActIntent.putExtra("Places",placesList.get(positionSelected));
                finish();
                startActivity(detailActIntent); //App Crashes here

            }
        });
        buttonLogOut = (Button) findViewById(R.id.buttonLogOut);
        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Log.d("CIS3334", "Creating Account. ");
                FirebaseAuth.getInstance().signOut();
                Intent signInIntent = new Intent(getBaseContext(), LoginActivity.class);
                finish();
                startActivity(signInIntent);
            }
        });

    }

    /*
     * Checks to see if a user is signed in. If no one is signed in it uses an intent to send the user to the sign in activity
     */
    private void checkUserAuthentication() {
        mAuthListener = new FirebaseAuth.AuthStateListener() { //initialized mAuthListener
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //track the user when they sign in or out using the firebaseAuth
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // User is signed out
                    Log.d("CSS3334","onAuthStateChanged - User NOT is signed in");
                    Intent signInIntent = new Intent(getBaseContext(), LoginActivity.class);
                    finish();
                    startActivity(signInIntent);
                }

            }
        };
    }
    /*
     * Opens the Firebase connection gets the data and displays in it in the ListView using the adapter
     */
    private void setupFirebaseDataChange() {
        placeDataSource = new PlacesFirebaseData();
        myPlacesDbRef = placeDataSource.open();
        myPlacesDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("CIS3334", "Starting onDataChange()");
                placesList = placeDataSource.getAllPlaces(dataSnapshot);
                placeAdapter = new PlaceAdapter(MainActivity.this, android.R.layout.simple_list_item_single_choice, placesList);
                placesListView.setAdapter(placeAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("CIS3334", "onCancelled: ");
            }
        });


    }
    /*
     * sets up the ListView and assigns the onItemClickListener
     */
    private void setupListView() {
        placesListView = (ListView) findViewById(R.id.listViewPlaces);
        placesListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapter, View parent,
                                    int position, long id) {
                positionSelected = position;
                Log.d("MAIN", "Place selected at position " + positionSelected);
            }
        });
    }




    /**
     * Attaches the mAuthListener on startup
     */
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    /**
     * Detaches the mAuthListener on Stop
     */
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }



}

