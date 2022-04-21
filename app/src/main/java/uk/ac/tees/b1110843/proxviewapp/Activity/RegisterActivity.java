package uk.ac.tees.b1110843.proxviewapp.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

import uk.ac.tees.b1110843.proxviewapp.Constant.Constants;
import uk.ac.tees.b1110843.proxviewapp.Permissions.AppPermissions;
import uk.ac.tees.b1110843.proxviewapp.R;
import uk.ac.tees.b1110843.proxviewapp.UserModel;
import uk.ac.tees.b1110843.proxviewapp.databinding.ActivityRegisterBinding;

//import com.theartofdev.edmodo.cropper.CropImage;
//import com.theartofdev.edmodo.cropper.CropImageView;
//import com.github.CanHub:Android-Image-Cropper

//import uk.ac.tees.b1110843.proxviewapp.Utilities.SpinnerDialog;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private AppPermissions appPermissions;
    private FirebaseAuth firebaseAuth;
//    private String username="", email="", password="";
    private String email, username, password;

    private Uri imageUri;
    private StorageReference storageReference;
    private ArrayList<Uri> imageUris;
    private static final int PICK_IMAGES_CODE = 0;


    // progress dialog
    private ProgressDialog progressDialog;

    //action bar
    private ActionBar actionBar;

//    private ImageSwitcher imageIs;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        storageReference = FirebaseStorage.getInstance().getReference();
        progressDialog = new ProgressDialog(this);
        appPermissions = new AppPermissions();

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

//    private void firebaseRegister() {
//
//        //show the progress dialog
//        progressDialog.show();
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
//        firebaseAuth.createUserWithEmailAndPassword(email, password)
//                .addOnSuccessListener(new OnSuccessListener<AuthResult>(){
//                    @Override
//                    public void onSuccess(AuthResult authResult){
//                        //register success
//                        progressDialog.dismiss();
//                        //get user infor
//                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//                        String email = firebaseUser.getEmail();
//                        Toast.makeText(RegisterActivity.this, "Account creted\n"+email, Toast.LENGTH_SHORT).show();
//
//                        //open profile activity
//                        startActivity(new Intent(RegisterActivity.this, ProfileActivity.class ));
//                        finish();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                // registration failed
//                progressDialog.dismiss();
//                Toast.makeText(RegisterActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
private void firebaseRegister() {
    progressDialog.show();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> register) {

            if (register.isSuccessful()) {

//                storageReference.child(firebaseAuth.getUid() + Constants.IMAGE_PATH).putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                Task<Uri> image = taskSnapshot.getStorage().getDownloadUrl();
//                image.addOnCompleteListener(new OnCompleteListener<Uri>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Uri> imageTask) {

//            if (imageTask.isSuccessful()) {

//                    String url = imageTask.getResult().toString();
            UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                    .setDisplayName(username)
//                            .setPhotoUri(Uri.parse(url))
                    .build();

        firebaseAuth.getCurrentUser().updateProfile(profileChangeRequest).
                addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            UserModel userModel = new UserModel(email,
                                    username,  true);
                            databaseReference.child(firebaseAuth.getUid())
                                    .setValue(userModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    firebaseAuth.getCurrentUser().sendEmailVerification()
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                        public void onSuccess(Void aVoid) {
                                            progressDialog.dismiss();
                                            Toast.makeText(RegisterActivity.this, "Account Registeredab", Toast.LENGTH_SHORT).show();
                                            onBackPressed();
                                        }
                                    });

                                    }

                                        });
                                    } else {
                                        progressDialog.dismiss();
                                        Log.d("TAG", "onComplete: Update Profile" + task.getException());
                                        Toast.makeText(RegisterActivity.this, "Update Profile" + task.getException(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        } else {
                            progressDialog.dismiss();
//                                    Log.d("TAG", "onComplete: Image Path" + imageTask.getException());
//                                    Toast.makeText(RegisterActivity.this, "Image Path" + imageTask.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                    }
//                });
//
//            }
////            else {
////                progressDialog.dismiss();
////                Log.d("TAG", "onComplete: Create user" + register.getException());
////                Toast.makeText(RegisterActivity.this, "" + register.getException(), Toast.LENGTH_SHORT).show();
////            }
//        }
//    });
//}


}
