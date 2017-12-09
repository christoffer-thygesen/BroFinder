package mike.magic.com.brofinder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPasswordActivity extends AppCompatActivity {
    FirebaseAuth broAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }

    public void backToLogin(View v){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    public void pwRecover(View v){

        EditText email = findViewById(R.id.emailrecover);
        String emailAddress = email.getText().toString();

        broAuth.sendPasswordResetEmail(emailAddress).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPasswordActivity.this, "Password recovery send to E-mail",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(ForgotPasswordActivity.this, "That e-mail is not registered",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}