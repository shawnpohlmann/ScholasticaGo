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

/**
 * Created by spohlmann on 5/7/2017.
 * This class signs the users in.
 */

public class LoginActivity extends AppCompatActivity {
    EditText editTextUsername;
    EditText editTextPassword;
    Button buttonLogin;
    Button buttonCreateAccount;
    private FirebaseAuth mAuth;
    private String TAG = "CIS3334";

    /*
     * Run right when the class is initiated and sets the layout to the sign in activity (activity_main.xml)
     * assigns the field to the right variables and sets the listeners for the buttons
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword1);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonCreateAccount= (Button) findViewById(R.id.buttonCreateAccount);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Log.d("CIS3334", "normal login ");
                signIn(editTextUsername.getText().toString(), editTextPassword.getText().toString());


            }
        });
        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Log.d("CIS3334", "Creating Account. ");
                Intent intent = new Intent(LoginActivity.this, CreateAccount.class);
                finish();
                startActivity(intent);
            }
        });
    }

    /*
     * method that signs the users in. Passes in the username and password obtained from the fields on the layout.
     * Then runs the mauth.signInWithEmailAndPassword method to sign them in.
     * If successful it uses an intent to send it back to the Main activity and closes that activity.
     */
    public void signIn(String userName, String password){
        mAuth.signInWithEmailAndPassword(userName, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            finish();
                            startActivity(intent);
                        }




                        // ...
                    }
                });
    }
    /*
     * method to close the activity.
     */
    public void finish(){
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        super.finish();
    }

}
