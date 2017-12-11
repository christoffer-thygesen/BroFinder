package mike.magic.com.brofinder;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MyProfileActivity extends AppCompatActivity {
    FirebaseUser broUser = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseAuth broAuth = FirebaseAuth.getInstance();
    String emailAuth = broUser.getEmail();

    private static final String TAG = "ManageUser";
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        databaseManager = DatabaseManager.getInstance(this);

        android.support.v7.widget.Toolbar brobar = (Toolbar) findViewById(R.id.toptoolbar);
        setSupportActionBar(brobar);

        ActionBar broActionBar = getSupportActionBar();
        broActionBar.setDisplayHomeAsUpEnabled(true);


        TextView account_Email = (TextView) findViewById(R.id.accountEmail);
        account_Email.setText("Current E-mail: " + broUser.getEmail());
        EditText inputEmail = (EditText) findViewById(R.id.inputEmail);
    }


    public void changeEmailButton(View v) {
        EditText inputEmail = findViewById(R.id.inputEmail);
        String emailString = inputEmail.getText().toString();
        if (emailString.matches("")) {
            return;

        } else {
            broUser.updateEmail(emailString).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "User email updated");
                        Toast.makeText(MyProfileActivity.this, "e-mail successfully updated!",
                                Toast.LENGTH_SHORT).show();
                        recreate();
                    } else {
                        Toast.makeText(MyProfileActivity.this, "e-mail not complete",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    public void changePasswordButton(View v) {

        EditText oldPassword = findViewById(R.id.oldPassword);
        String oldPasswordTest = oldPassword.getText().toString(); //Is this good practice? prolly not

        EditText newPassword = findViewById(R.id.newPasswordManage);
        String newPasswordTest = newPassword.getText().toString();

        EditText newPasswordConfirm = findViewById(R.id.newPasswordManageConfirm);
        final String newPasswordConfirmtest = newPasswordConfirm.getText().toString();

        AuthCredential credential = EmailAuthProvider.getCredential(broUser.getEmail(), oldPasswordTest);

        if(newPasswordTest != null && newPasswordConfirmtest != null && oldPasswordTest != null ){
            if(newPasswordTest == newPasswordConfirmtest){
                broUser.reauthenticate(credential)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()) {
                                    broUser.updatePassword(newPasswordConfirmtest).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()) {
                                                Log.d(TAG, "ChangePassword:Complete", task.getException());
                                                Toast.makeText(MyProfileActivity.this, "Password change is successful",
                                                        Toast.LENGTH_SHORT).show();

                                            } else {
                                                Log.w(TAG, "ChangePassword:Failure", task.getException());
                                                Toast.makeText(MyProfileActivity.this, "Password change failed",
                                                        Toast.LENGTH_SHORT).show();

                                            }
                                        }
                                    });
                                }
                                else{
                                    Toast.makeText(MyProfileActivity.this, "New Password & New Password Confirmation are not identical!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }
        else{
            Toast.makeText(MyProfileActivity.this, "One of the edit fields are empty! Please fill out all forms!",
                    Toast.LENGTH_SHORT).show();
        }
    }



    public void deleteAccount(View v) {
        User currentUser = databaseManager.getCurrentUser(broUser.getUid());
        databaseManager.deleteUser(currentUser);
        broUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MyProfileActivity.this, "User is deleted",
                            Toast.LENGTH_SHORT).show();
                    userDeleted();
                } else {
                    Toast.makeText(MyProfileActivity.this, "failed to delete user, try again !",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    public void userDeleted()
    {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}