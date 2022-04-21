package uk.ac.tees.b1110843.proxviewapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import uk.ac.tees.b1110843.proxviewapp.R;
import uk.ac.tees.b1110843.proxviewapp.databinding.ActivityLoginBinding;
import uk.ac.tees.b1110843.proxviewapp.databinding.ActivityRegisterBinding;

public class LoginActivity extends AppCompatActivity {
    // ViewBinding
    private ActivityLoginBinding binding;

    private FirebaseAuth firebaseAuth;
    private String email="", password="";

        //actionbar
    private ActionBar actionBar;


    //progress dialog
    private ProgressDialog progressDialog;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // config action bar, title,
//        actionBar = getSupportActionBar();
//        actionBar.setTitle("LogIn");

        // config progress dialog

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Loggin In..");
        progressDialog.setCanceledOnTouchOutside(false);


        //initialize firebase auth
        firebaseAuth = FirebaseAuth.getInstance();
        checkUser();

        //if no accunt go to signup
        binding.buttonRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        binding.buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //validate data
                validateData();
            }
        });
    }

    private void checkUser() {
        //check if user is logged in, open profile if logged in

        //get current user
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser !=null){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private void validateData() {
        //get data
        email = binding.editEmail.getText().toString().trim();
        password = binding.editPassword.getText().toString().trim();

        //validate data
//        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
        if (TextUtils.isEmpty(email)){
            //if email format is invalid, don't proceed
            binding.editEmail.setError("Invalid email format");
        }
        else if (TextUtils.isEmpty(password)){
            //if no password is entered
            binding.editPassword.setError("Please enter password");
        } else {
            //
            firebaseLogin();
        }
    }

    private void firebaseLogin(){
        //show the progress dialog
        progressDialog.show();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //login success
                        //get user infor
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        String email = firebaseUser.getEmail();
                        Toast.makeText(LoginActivity.this, "LoggedIn\n"+email, Toast.LENGTH_SHORT).show();

                        //open profile activity
                        startActivity(new Intent(LoginActivity.this, MainActivity.class ));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // login failed, show error
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        }

    }