package spohlmann.mobiledevices.scholasticago;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by spohlmann on 5/7/2017.
 */

public class CreateAccount extends AppCompatActivity {
    EditText editTextEmail1;
    EditText editTextPassword1;
    Button buttonCreateAccount1;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String TAG = "CIS3334";
    PlacesFirebaseData placesDataSource;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        mAuth = FirebaseAuth.getInstance();

        editTextEmail1 = (EditText) findViewById(R.id.editTextEmail1);
        editTextPassword1 = (EditText) findViewById(R.id.editTextPassword1);
        buttonCreateAccount1 = (Button) findViewById(R.id.buttonCreateAccount1);

        buttonCreateAccount1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("CIS3334", "Creating Account setOnClickListener. ");
                createAccount(editTextEmail1.getText().toString(), editTextPassword1.getText().toString());
                //signIn(editTextEmail1.getText().toString(), editTextPassword1.getText().toString());


            }
        });
        placesDataSource = new PlacesFirebaseData();
        DatabaseReference myPlacesDbRef = placesDataSource.open();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    private void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.d(TAG, "createAccount FAIL");
                            Toast.makeText(CreateAccount.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d(TAG, "createAccount succeeded");

                            placesDataSource.createPlace("Scary Mary", "Not Complete", 46.813871, -92.108404);
                            placesDataSource.createPlace("Tower Hall", "Not Complete", 46.816121, -92.106042);
                            placesDataSource.createPlace("Welcome Sign", "Not Complete", 46.816681, -92.100650);
                            placesDataSource.createPlace("BWC", "Not Complete", 46.817553, -92.106601);
                            placesDataSource.createPlace("Garden", "Not Complete", 46.815606, -92.107103);

                            Intent intent = new Intent(CreateAccount.this, MainActivity.class);
                            finish();
                            startActivity(intent);
                        }

                        // ...
                    }
                });
    }

}

