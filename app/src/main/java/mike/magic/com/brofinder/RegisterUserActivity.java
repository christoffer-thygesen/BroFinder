package mike.magic.com.brofinder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterUserActivity extends AppCompatActivity {

    String userName;
    private static final String TAG = "EmailPassword";
    private DatabaseManager databaseManager;
    private FirebaseAuth broAuth;
    private Toolbar brobar;

    // private TextView mStatusTextView;
    // private TextView mDetailTextView;
    //   private EditText newUserName = findViewById(R.id.newUsername); //EditText ID name for field Username


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        broAuth = FirebaseAuth.getInstance();
        databaseManager = DatabaseManager.getInstance(this);

        brobar = (Toolbar)findViewById(R.id.toptoolbar);
        setSupportActionBar(brobar);

        ActionBar broActionBar = getSupportActionBar();
        broActionBar.setDisplayHomeAsUpEnabled(true);

    }

    public void registerAccount(View V){
        EditText newPassword = findViewById(R.id.newPassword); //
        EditText newEmail = findViewById(R.id.newEmail);
        EditText newUsername = findViewById(R.id.newUsername);


        String email = newEmail.getText().toString();
        String password = newPassword.getText().toString();
        userName = newUsername.getText().toString();

        createAccount(email,password);
    }

    public void createAccount(String email, String password){

        Log.d(TAG, "createAccount:" + email);

        if (!validateForm()) {
            return;
        }

        broAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = broAuth.getCurrentUser();
                            user.sendEmailVerification(); //send verification for user

                            Log.d("USERID", user.getUid());
                            Log.d("USERNAME", userName);

                            databaseManager.addUser(user.getUid(), userName);
                            Toast.makeText(RegisterUserActivity.this,"I saved your data", Toast.LENGTH_LONG).show();
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterUserActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private boolean validateForm() {
        boolean valid = true;

        EditText newPassword = findViewById(R.id.newPassword); //
        EditText newEmail = findViewById(R.id.newEmail);

        String email = newEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            newEmail.setError("Required.");
            valid = false;
        } else {
            newEmail.setError(null);
        }

        String password = newPassword.getText().toString();

        if (TextUtils.isEmpty(password)) {
            newPassword.setError("Required.");
            valid = false;
        } else {
            newPassword.setError(null);
        }

        return valid;
    }

}