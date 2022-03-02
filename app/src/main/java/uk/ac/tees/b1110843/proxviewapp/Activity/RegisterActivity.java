package uk.ac.tees.b1110843.proxviewapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import uk.ac.tees.b1110843.proxviewapp.Constant.AllConstant;
import uk.ac.tees.b1110843.proxviewapp.Permissions.AppPermissions;
import uk.ac.tees.b1110843.proxviewapp.Utilities.SpinnerDialog;
import uk.ac.tees.b1110843.proxviewapp.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;

    private FirebaseAuth firebaseAuth;
    private String username="", email="", password="";

    // progress dialog
    private ProgressDialog progressDialog;

    //action bar
    private ActionBar actionBar;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // config action bar, title,
        actionBar = getSupportActionBar();
        actionBar.setTitle("Register");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);



        //initialize firebase auth
        firebaseAuth = FirebaseAuth.getInstance();

        // config progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("creating your account..");
        progressDialog.setCanceledOnTouchOutside(false);


        binding.buttonRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                validateData();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void validateData() {

        //get data
        username = binding.editUsername.getText().toString().trim();
        email = binding.editEmail.getText().toString().trim();
        password = binding.editPassword.getText().toString().trim();

        // validate data
        if (TextUtils.isEmpty(username)){
            //if no password is entered
            binding.editUsername.setError("Please enter username");
        }
       else if (TextUtils.isEmpty(email)){
            //if email format is invalid, don't proceed
            binding.editEmail.setError("Invalid email format");
        }
        else if (TextUtils.isEmpty(password)){
            //if no password is entered
            binding.editPassword.setError("Please enter password");
        }
        else if (password.length() < 6){
            binding.editPassword.setError("password should be a minimum of 6 characters");
        } else {
            //
            firebaseRegister();
        }
    }

    private void firebaseRegister() {

        //show the progress dialog
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>(){
            @Override
            public void onSuccess(AuthResult authResult){
                //register success
                progressDialog.dismiss();
                //get user infor
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                String email = firebaseUser.getEmail();
                Toast.makeText(RegisterActivity.this, "Account creted\n"+email, Toast.LENGTH_SHORT).show();

                //open profile activity
                startActivity(new Intent(RegisterActivity.this, ProfileActivity.class ));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // registration failed
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    }
