package mike.magic.com.brofinder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "EmailPassword";
    private FirebaseAuth broAuth;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        broAuth = FirebaseAuth.getInstance();
        loginButton = (Button)findViewById(R.id.loginButton);

        android.support.v7.widget.Toolbar brobar = (Toolbar)findViewById(R.id.toptoolbar);
        setSupportActionBar(brobar);

        ActionBar broActionBar = getSupportActionBar();
        broActionBar.setDisplayHomeAsUpEnabled(true);

        /*
        if(broAuth.getCurrentUser() != null){
            toMainActivity();
        }
        else{

        } UNCOMMENT TO CHECK FOR LOGIN ON APPLICATION START */
    }

    @Override
    public void onStart(){
        super.onStart();

        FirebaseUser currentUser = broAuth.getCurrentUser();

    }

    public void passwordRecovery(View v){

        Intent intent = new Intent(this,ForgotPasswordActivity.class);
        startActivity(intent);
    }

    public void userRegister(View v){
        Intent intent = new Intent(this,RegisterUserActivity.class);
        startActivity(intent);
    }



    public void loginButtonClick(View v){
        EditText userEmail = findViewById(R.id.userEmail); //
        EditText userPassword = findViewById(R.id.userPassword);

        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();

        signIn(email,password);

    }

    public void signIn(String email,String password){
        broAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Log.d(TAG, "sgnInWithEmail:Success");
                            FirebaseUser user = broAuth.getCurrentUser();
                            toMainActivity();

                        } else {
                            Log.w(TAG, "signInWithEmail:Failure", task.getException());
                            Toast.makeText( LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }}});}

    public void toMainActivity(){
        Intent backtoMain = new Intent(this,MainActivity.class);
        finish();
        startActivity(backtoMain);
    }

}
//https://firebase.google.com/docs/auth/web/manage-users